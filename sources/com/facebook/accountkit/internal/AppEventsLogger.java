package com.facebook.accountkit.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitError.Type;
import com.facebook.accountkit.AccountKitException;
import com.facebook.accountkit.LoggingBehavior;
import com.facebook.accountkit.internal.AccountKitGraphRequest.Callback;
import com.facebook.appevents.internal.Constants;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class AppEventsLogger {
    private static final Object APP_EVENTS_LOGGER_LOCK = new Object();
    private static final String APP_EVENT_PREFERENCES = "com.facebook.accountkit.sdk.appEventPreferences";
    private static final int FLUSH_PERIOD_IN_SECONDS = 15;
    private static final int MAX_POOL_SIZE = 4;
    private static final int NUM_LOG_EVENTS_TO_TRY_TO_FLUSH_AFTER = 30;
    private static final String TAG = AppEventsLogger.class.getCanonicalName();
    private static String anonymousAppDeviceGUID;
    private static boolean requestInFlight;
    private static final Executor sAppEventExecutor = new ThreadPoolExecutor(1, 4, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(256), new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        public Thread newThread(Runnable runnable) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("App Event Thread #");
            stringBuilder.append(this.mCount.getAndIncrement());
            return new Thread(runnable, stringBuilder.toString());
        }
    }, new DiscardPolicy() {
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
            super.rejectedExecution(runnable, threadPoolExecutor);
            Log.e(AppEventsLogger.TAG, "App Event Dropped");
        }
    });
    private static final Map<SessionEventsStateKey, SessionEventsState> stateMap = new ConcurrentHashMap();
    private final Context applicationContext;
    private final SessionEventsStateKey stateKey;

    private static class AppEvent implements Serializable {
        private static final String IDENTIFIER_REGEX = "^[0-9a-zA-Z_]+[0-9a-zA-Z _-]*$";
        private static final int MAX_IDENTIFIER_LENGTH = 40;
        private static final HashSet<String> VALIDATED_IDENTIFIERS = new HashSet();
        private static final long serialVersionUID = 1;
        final boolean isImplicit;
        final JSONObject jsonObject;

        private static class SerializationProxyV1 implements Serializable {
            private static final long serialVersionUID = -2488473066578201069L;
            private final boolean isImplicit;
            private final String jsonString;

            /* synthetic */ SerializationProxyV1(String str, boolean z, AnonymousClass1 anonymousClass1) {
                this(str, z);
            }

            private SerializationProxyV1(String str, boolean z) {
                this.jsonString = str;
                this.isImplicit = z;
            }

            private Object readResolve() throws JSONException {
                return new AppEvent(this.jsonString, this.isImplicit, null);
            }
        }

        /* synthetic */ AppEvent(String str, boolean z, AnonymousClass1 anonymousClass1) throws JSONException {
            this(str, z);
        }

        AppEvent(String str, Double d, Bundle bundle, boolean z) {
            this.isImplicit = z;
            JSONObject jSONObject = null;
            try {
                validateIdentifier(str);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(Constants.EVENT_NAME_EVENT_KEY, str);
                jSONObject2.put(Constants.LOG_TIME_APP_EVENT_KEY, System.currentTimeMillis() / 1000);
                if (d != null) {
                    jSONObject2.put("_valueToSum", d.doubleValue());
                }
                if (z) {
                    jSONObject2.put("_implicitlyLogged", "1");
                }
                if (bundle != null) {
                    for (String str2 : bundle.keySet()) {
                        validateIdentifier(str2);
                        Object obj = bundle.get(str2);
                        if ((obj instanceof String) || (obj instanceof Number)) {
                            jSONObject2.put(str2, obj.toString());
                        } else {
                            throw new AccountKitException(Type.ARGUMENT_ERROR, new InternalAccountKitError(InternalAccountKitError.INVALID_PARAMETER_TYPE, obj, str2));
                        }
                    }
                }
                if (!z) {
                    ConsoleLogger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "Created app event '%s'", jSONObject2.toString());
                }
                jSONObject = jSONObject2;
            } catch (JSONException e) {
                ConsoleLogger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "JSON encoding for app event failed: '%s'", e.toString());
            } catch (AccountKitException e2) {
                ConsoleLogger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "Invalid app event name or parameter:", e2.toString());
            }
            this.jsonObject = jSONObject;
        }

        private AppEvent(String str, boolean z) throws JSONException {
            this.jsonObject = new JSONObject(str);
            this.isImplicit = z;
        }

        private void validateIdentifier(String str) throws AccountKitException {
            if (str == null || str.length() == 0 || str.length() > 40) {
                if (str == null) {
                    str = "<None Provided>";
                }
                throw new AccountKitException(Type.INTERNAL_ERROR, new InternalAccountKitError(InternalAccountKitError.INVALID_PARAMETER_TYPE, str, Integer.valueOf(40)));
            }
            boolean contains;
            synchronized (VALIDATED_IDENTIFIERS) {
                contains = VALIDATED_IDENTIFIERS.contains(str);
            }
            if (!contains) {
                if (str.matches(IDENTIFIER_REGEX)) {
                    synchronized (VALIDATED_IDENTIFIERS) {
                        VALIDATED_IDENTIFIERS.add(str);
                    }
                    return;
                }
                throw new AccountKitException(Type.INTERNAL_ERROR, new InternalAccountKitError(InternalAccountKitError.INVALID_PARAMETER_TYPE, str));
            }
        }

        private Object writeReplace() {
            return new SerializationProxyV1(this.jsonObject.toString(), this.isImplicit, null);
        }

        public String toString() {
            return String.format("\"%s\", implicit: %b, json: %s", new Object[]{this.jsonObject.optString(Constants.EVENT_NAME_EVENT_KEY), Boolean.valueOf(this.isImplicit), this.jsonObject.toString()});
        }
    }

    private enum FlushReason {
        EXPLICIT,
        TIMER,
        SESSION_CHANGE,
        PERSISTED_EVENTS,
        EVENT_THRESHOLD,
        EAGER_FLUSHING_EVENT
    }

    private enum FlushResult {
        SUCCESS,
        SERVER_ERROR,
        NO_CONNECTIVITY,
        UNKNOWN_ERROR
    }

    private static class FlushStatistics {
        int numEvents;
        public FlushResult result;

        private FlushStatistics() {
            this.numEvents = 0;
            this.result = FlushResult.SUCCESS;
        }

        /* synthetic */ FlushStatistics(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    private static class PersistedEvents {
        private static final String PERSISTED_EVENTS_FILENAME = "AccountKitAppEventsLogger.persistedevents";
        private static final Object PERSISTED_EVENTS_LOCK = new Object();
        private final Context context;
        private HashMap<SessionEventsStateKey, List<AppEvent>> persistedEvents = new HashMap();

        private PersistedEvents(Context context) {
            this.context = context;
        }

        static PersistedEvents readAndClearStore(Context context) {
            PersistedEvents persistedEvents;
            synchronized (PERSISTED_EVENTS_LOCK) {
                persistedEvents = new PersistedEvents(context);
                persistedEvents.readAndClearStore();
            }
            return persistedEvents;
        }

        static void persistEvents(Context context, SessionEventsStateKey sessionEventsStateKey, SessionEventsState sessionEventsState) {
            List eventsToPersist = sessionEventsState.getEventsToPersist();
            if (eventsToPersist.size() != 0) {
                synchronized (PERSISTED_EVENTS_LOCK) {
                    PersistedEvents readAndClearStore = readAndClearStore(context);
                    readAndClearStore.addEvents(sessionEventsStateKey, eventsToPersist);
                    readAndClearStore.write();
                }
            }
        }

        private void write() {
            FileOutputStream fileOutputStream;
            Exception e;
            String access$000;
            StringBuilder stringBuilder;
            Throwable th;
            Closeable closeable = null;
            try {
                FileOutputStream openFileOutput = this.context.openFileOutput(PERSISTED_EVENTS_FILENAME, 0);
                try {
                    closeable = new BufferedOutputStream(openFileOutput);
                    openFileOutput = new ObjectOutputStream(closeable);
                    openFileOutput.writeObject(this.persistedEvents);
                    Utility.closeQuietly(openFileOutput);
                } catch (Exception e2) {
                    fileOutputStream = openFileOutput;
                    e = e2;
                    closeable = fileOutputStream;
                    try {
                        access$000 = AppEventsLogger.TAG;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Got unexpected exception: ");
                        stringBuilder.append(e.toString());
                        Log.d(access$000, stringBuilder.toString());
                        Utility.closeQuietly(closeable);
                    } catch (Throwable th2) {
                        th = th2;
                        Utility.closeQuietly(closeable);
                        throw th;
                    }
                } catch (Throwable th3) {
                    fileOutputStream = openFileOutput;
                    th = th3;
                    closeable = fileOutputStream;
                    Utility.closeQuietly(closeable);
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
                access$000 = AppEventsLogger.TAG;
                stringBuilder = new StringBuilder();
                stringBuilder.append("Got unexpected exception: ");
                stringBuilder.append(e.toString());
                Log.d(access$000, stringBuilder.toString());
                Utility.closeQuietly(closeable);
            }
        }

        private void readAndClearStore() {
            BufferedInputStream bufferedInputStream;
            Exception e;
            String access$000;
            StringBuilder stringBuilder;
            Throwable th;
            Closeable closeable = null;
            try {
                BufferedInputStream bufferedInputStream2 = new BufferedInputStream(this.context.openFileInput(PERSISTED_EVENTS_FILENAME));
                try {
                    closeable = new ObjectInputStream(bufferedInputStream2);
                    HashMap hashMap = (HashMap) closeable.readObject();
                    if (!this.context.getFileStreamPath(PERSISTED_EVENTS_FILENAME).delete()) {
                        Log.d(AppEventsLogger.TAG, "Error deleting file: AccountKitAppEventsLogger.persistedevents");
                    }
                    this.persistedEvents = hashMap;
                } catch (FileNotFoundException unused) {
                    closeable = bufferedInputStream2;
                } catch (Exception e2) {
                    bufferedInputStream = bufferedInputStream2;
                    e = e2;
                    closeable = bufferedInputStream;
                    try {
                        access$000 = AppEventsLogger.TAG;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Got unexpected exception: ");
                        stringBuilder.append(e.toString());
                        Log.d(access$000, stringBuilder.toString());
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Throwable th3) {
                    bufferedInputStream = bufferedInputStream2;
                    th = th3;
                    closeable = bufferedInputStream;
                    Utility.closeQuietly(closeable);
                    throw th;
                }
            } catch (FileNotFoundException unused2) {
            } catch (Exception e3) {
                e = e3;
                access$000 = AppEventsLogger.TAG;
                stringBuilder = new StringBuilder();
                stringBuilder.append("Got unexpected exception: ");
                stringBuilder.append(e.toString());
                Log.d(access$000, stringBuilder.toString());
            }
            Utility.closeQuietly(closeable);
        }

        /* Access modifiers changed, original: 0000 */
        public void addEvents(SessionEventsStateKey sessionEventsStateKey, List<AppEvent> list) {
            if (!this.persistedEvents.containsKey(sessionEventsStateKey)) {
                this.persistedEvents.put(sessionEventsStateKey, new ArrayList());
            }
            ((List) this.persistedEvents.get(sessionEventsStateKey)).addAll(list);
        }
    }

    private static class SessionEventsState {
        private static final int MAX_ACCUMULATED_LOG_EVENTS = 1000;
        private List<AppEvent> accumulatedEvents = new ArrayList();
        private final String anonymousAppDeviceGUID;
        private final Context applicationContext;
        private final List<AppEvent> inFlightEvents = new ArrayList();
        private int numSkippedEventsDueToFullBuffer;

        SessionEventsState(Context context, String str) {
            this.applicationContext = context;
            this.anonymousAppDeviceGUID = str;
        }

        /* Access modifiers changed, original: declared_synchronized */
        public synchronized void addEvent(AppEvent appEvent) {
            if (this.accumulatedEvents.size() + this.inFlightEvents.size() >= 1000) {
                this.numSkippedEventsDueToFullBuffer++;
            } else {
                this.accumulatedEvents.add(appEvent);
            }
        }

        /* Access modifiers changed, original: declared_synchronized */
        public synchronized int getAccumulatedEventCount() {
            return this.accumulatedEvents.size();
        }

        /* Access modifiers changed, original: declared_synchronized */
        public synchronized void clearInFlightAndStats(boolean z) {
            if (z) {
                try {
                    this.accumulatedEvents.addAll(this.inFlightEvents);
                } catch (Throwable th) {
                }
            }
            this.inFlightEvents.clear();
            this.numSkippedEventsDueToFullBuffer = 0;
        }

        /* Access modifiers changed, original: 0000 */
        /* JADX WARNING: Missing block: B:13:?, code skipped:
            r2 = getJSONObject();
     */
        /* JADX WARNING: Missing block: B:14:0x003c, code skipped:
            if (r5.numSkippedEventsDueToFullBuffer <= 0) goto L_0x0049;
     */
        /* JADX WARNING: Missing block: B:15:0x003e, code skipped:
            r2.put("num_skipped_events", r0);
     */
        /* JADX WARNING: Missing block: B:16:0x0044, code skipped:
            r2 = new org.json.JSONObject();
     */
        public int populateRequest(com.facebook.accountkit.internal.AccountKitGraphRequest r6) {
            /*
            r5 = this;
            monitor-enter(r5);
            r0 = r5.numSkippedEventsDueToFullBuffer;	 Catch:{ all -> 0x0071 }
            r1 = r5.inFlightEvents;	 Catch:{ all -> 0x0071 }
            r2 = r5.accumulatedEvents;	 Catch:{ all -> 0x0071 }
            r1.addAll(r2);	 Catch:{ all -> 0x0071 }
            r1 = r5.accumulatedEvents;	 Catch:{ all -> 0x0071 }
            r1.clear();	 Catch:{ all -> 0x0071 }
            r1 = new org.json.JSONArray;	 Catch:{ all -> 0x0071 }
            r1.<init>();	 Catch:{ all -> 0x0071 }
            r2 = r5.inFlightEvents;	 Catch:{ all -> 0x0071 }
            r2 = r2.iterator();	 Catch:{ all -> 0x0071 }
        L_0x001a:
            r3 = r2.hasNext();	 Catch:{ all -> 0x0071 }
            if (r3 == 0) goto L_0x002c;
        L_0x0020:
            r3 = r2.next();	 Catch:{ all -> 0x0071 }
            r3 = (com.facebook.accountkit.internal.AppEventsLogger.AppEvent) r3;	 Catch:{ all -> 0x0071 }
            r3 = r3.jsonObject;	 Catch:{ all -> 0x0071 }
            r1.put(r3);	 Catch:{ all -> 0x0071 }
            goto L_0x001a;
        L_0x002c:
            r2 = r1.length();	 Catch:{ all -> 0x0071 }
            if (r2 != 0) goto L_0x0035;
        L_0x0032:
            r6 = 0;
            monitor-exit(r5);	 Catch:{ all -> 0x0071 }
            return r6;
        L_0x0035:
            monitor-exit(r5);	 Catch:{ all -> 0x0071 }
            r2 = r5.getJSONObject();	 Catch:{ JSONException -> 0x0044 }
            r3 = r5.numSkippedEventsDueToFullBuffer;	 Catch:{ JSONException -> 0x0044 }
            if (r3 <= 0) goto L_0x0049;
        L_0x003e:
            r3 = "num_skipped_events";
            r2.put(r3, r0);	 Catch:{ JSONException -> 0x0044 }
            goto L_0x0049;
        L_0x0044:
            r2 = new org.json.JSONObject;
            r2.<init>();
        L_0x0049:
            r6.setRequestObject(r2);
            r0 = r6.getParameters();
            if (r0 != 0) goto L_0x0057;
        L_0x0052:
            r0 = new android.os.Bundle;
            r0.<init>();
        L_0x0057:
            r2 = r1.toString();
            if (r2 == 0) goto L_0x0069;
        L_0x005d:
            r3 = "events_file";
            r4 = r5.getStringAsByteArray(r2);
            r0.putByteArray(r3, r4);
            r6.setTag(r2);
        L_0x0069:
            r6.setParameters(r0);
            r6 = r1.length();
            return r6;
        L_0x0071:
            r6 = move-exception;
            monitor-exit(r5);	 Catch:{ all -> 0x0071 }
            throw r6;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.accountkit.internal.AppEventsLogger$SessionEventsState.populateRequest(com.facebook.accountkit.internal.AccountKitGraphRequest):int");
        }

        /* Access modifiers changed, original: declared_synchronized */
        public synchronized List<AppEvent> getEventsToPersist() {
            List list;
            list = this.accumulatedEvents;
            this.accumulatedEvents = new ArrayList();
            return list;
        }

        public JSONObject getJSONObject() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            Utility.setAppEventAttributionParameters(jSONObject, this.anonymousAppDeviceGUID);
            try {
                Utility.setAppEventExtendedDeviceInfoParameters(jSONObject, this.applicationContext);
            } catch (Exception e) {
                ConsoleLogger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "Fetching extended device info parameters failed: '%s'", e.toString());
            }
            return jSONObject;
        }

        private byte[] getStringAsByteArray(String str) {
            try {
                return str.getBytes("UTF-8");
            } catch (UnsupportedEncodingException e) {
                Utility.logd("Encoding exception: ", e);
                return null;
            }
        }
    }

    private static class SessionEventsStateKey implements Serializable {
        private static final long serialVersionUID = 1;
        private final String accessTokenString;
        public final String applicationId;

        private static class SerializationProxyV1 implements Serializable {
            private static final long serialVersionUID = -1;
            private final String accessTokenString;
            private final String appId;

            /* synthetic */ SerializationProxyV1(String str, String str2, AnonymousClass1 anonymousClass1) {
                this(str, str2);
            }

            private SerializationProxyV1(String str, String str2) {
                this.accessTokenString = str;
                this.appId = str2;
            }

            private Object readResolve() {
                return new SessionEventsStateKey(this.accessTokenString, this.appId);
            }
        }

        SessionEventsStateKey(AccessToken accessToken) {
            this(accessToken.getToken(), AccountKit.getApplicationId());
        }

        SessionEventsStateKey(String str, String str2) {
            if (Utility.isNullOrEmpty(str)) {
                str = null;
            }
            this.accessTokenString = str;
            this.applicationId = str2;
        }

        public int hashCode() {
            return Utility.getHashCode(this.accessTokenString) ^ Utility.getHashCode(this.applicationId);
        }

        public boolean equals(Object obj) {
            boolean z = false;
            if (!(obj instanceof SessionEventsStateKey)) {
                return false;
            }
            SessionEventsStateKey sessionEventsStateKey = (SessionEventsStateKey) obj;
            if (Utility.areObjectsEqual(sessionEventsStateKey.accessTokenString, this.accessTokenString) && Utility.areObjectsEqual(sessionEventsStateKey.applicationId, this.applicationId)) {
                z = true;
            }
            return z;
        }

        private Object writeReplace() {
            return new SerializationProxyV1(this.accessTokenString, this.applicationId, null);
        }
    }

    AppEventsLogger(@NonNull Context context, String str) {
        AccessToken currentAccessToken = AccountKit.getCurrentAccessToken();
        if (currentAccessToken == null || !(str == null || str.equals(currentAccessToken.getApplicationId()))) {
            if (str == null) {
                str = Utility.getMetadataApplicationId();
            }
            this.stateKey = new SessionEventsStateKey(null, str);
        } else {
            this.stateKey = new SessionEventsStateKey(currentAccessToken);
        }
        this.applicationContext = context;
        initializeTimersIfNeeded();
    }

    /* Access modifiers changed, original: 0000 */
    public void logSdkEvent(String str, Double d, Bundle bundle) {
        final AppEvent appEvent = new AppEvent(str, d, bundle, true);
        sAppEventExecutor.execute(new Runnable() {
            public void run() {
                AppEventsLogger.this.getSessionEventsState(AppEventsLogger.this.stateKey).addEvent(appEvent);
                AppEventsLogger.this.flushIfNecessary();
            }
        });
    }

    public String getApplicationId() {
        return this.stateKey.applicationId;
    }

    private void initializeTimersIfNeeded() {
        Utility.getBackgroundExecutor().scheduleAtFixedRate(new Runnable() {
            public void run() {
                AppEventsLogger.this.flushAndWait(FlushReason.TIMER);
            }
        }, 0, 15, TimeUnit.SECONDS);
    }

    private static String getAnonymousAppDeviceGUID(Context context) {
        if (anonymousAppDeviceGUID == null) {
            synchronized (APP_EVENTS_LOGGER_LOCK) {
                if (anonymousAppDeviceGUID == null) {
                    SharedPreferences sharedPreferences = context.getSharedPreferences(APP_EVENT_PREFERENCES, 0);
                    anonymousAppDeviceGUID = sharedPreferences.getString("anonymousAppDeviceGUID", null);
                    if (anonymousAppDeviceGUID == null) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("XZ");
                        stringBuilder.append(UUID.randomUUID().toString());
                        anonymousAppDeviceGUID = stringBuilder.toString();
                        sharedPreferences.edit().putString("anonymousAppDeviceGUID", anonymousAppDeviceGUID).apply();
                    }
                }
            }
        }
        return anonymousAppDeviceGUID;
    }

    private void flushIfNecessary() {
        synchronized (APP_EVENTS_LOGGER_LOCK) {
            if (getAccumulatedEventCount() > 30) {
                flush(FlushReason.EVENT_THRESHOLD);
            }
        }
    }

    private static int getAccumulatedEventCount() {
        int i;
        synchronized (APP_EVENTS_LOGGER_LOCK) {
            i = 0;
            for (SessionEventsState accumulatedEventCount : stateMap.values()) {
                i += accumulatedEventCount.getAccumulatedEventCount();
            }
        }
        return i;
    }

    private SessionEventsState getSessionEventsState(SessionEventsStateKey sessionEventsStateKey) {
        SessionEventsState sessionEventsState = (SessionEventsState) stateMap.get(sessionEventsStateKey);
        if (sessionEventsState == null) {
            synchronized (APP_EVENTS_LOGGER_LOCK) {
                sessionEventsState = (SessionEventsState) stateMap.get(sessionEventsStateKey);
                if (sessionEventsState == null) {
                    sessionEventsState = new SessionEventsState(this.applicationContext, getAnonymousAppDeviceGUID(this.applicationContext));
                    stateMap.put(sessionEventsStateKey, sessionEventsState);
                }
            }
        }
        return sessionEventsState;
    }

    private void flush(final FlushReason flushReason) {
        sAppEventExecutor.execute(new Runnable() {
            public void run() {
                AppEventsLogger.this.flushAndWait(flushReason);
            }
        });
    }

    /* JADX WARNING: Missing block: B:10:?, code skipped:
            buildAndExecuteRequests(r4, r1);
     */
    /* JADX WARNING: Missing block: B:11:0x001c, code skipped:
            r4 = move-exception;
     */
    /* JADX WARNING: Missing block: B:12:0x001d, code skipped:
            com.facebook.accountkit.internal.Utility.logd(TAG, "Caught unexpected exception while flushing: ", r4);
     */
    private void flushAndWait(com.facebook.accountkit.internal.AppEventsLogger.FlushReason r4) {
        /*
        r3 = this;
        r0 = APP_EVENTS_LOGGER_LOCK;
        monitor-enter(r0);
        r1 = requestInFlight;	 Catch:{ all -> 0x002f }
        if (r1 == 0) goto L_0x0009;
    L_0x0007:
        monitor-exit(r0);	 Catch:{ all -> 0x002f }
        return;
    L_0x0009:
        r1 = 1;
        requestInFlight = r1;	 Catch:{ all -> 0x002f }
        r1 = new java.util.HashSet;	 Catch:{ all -> 0x002f }
        r2 = stateMap;	 Catch:{ all -> 0x002f }
        r2 = r2.keySet();	 Catch:{ all -> 0x002f }
        r1.<init>(r2);	 Catch:{ all -> 0x002f }
        monitor-exit(r0);	 Catch:{ all -> 0x002f }
        r3.buildAndExecuteRequests(r4, r1);	 Catch:{ Exception -> 0x001c }
        goto L_0x0024;
    L_0x001c:
        r4 = move-exception;
        r0 = TAG;
        r1 = "Caught unexpected exception while flushing: ";
        com.facebook.accountkit.internal.Utility.logd(r0, r1, r4);
    L_0x0024:
        r4 = APP_EVENTS_LOGGER_LOCK;
        monitor-enter(r4);
        r0 = 0;
        requestInFlight = r0;	 Catch:{ all -> 0x002c }
        monitor-exit(r4);	 Catch:{ all -> 0x002c }
        return;
    L_0x002c:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x002c }
        throw r0;
    L_0x002f:
        r4 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x002f }
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.accountkit.internal.AppEventsLogger.flushAndWait(com.facebook.accountkit.internal.AppEventsLogger$FlushReason):void");
    }

    private void buildAndExecuteRequests(FlushReason flushReason, Set<SessionEventsStateKey> set) {
        FlushStatistics flushStatistics = new FlushStatistics();
        ArrayList<AccountKitGraphRequestAsyncTask> arrayList = new ArrayList();
        for (SessionEventsStateKey sessionEventsStateKey : set) {
            SessionEventsState sessionEventsState = getSessionEventsState(sessionEventsStateKey);
            if (sessionEventsState != null) {
                AccountKitGraphRequestAsyncTask buildRequestForSession = buildRequestForSession(sessionEventsStateKey, sessionEventsState, flushStatistics);
                if (buildRequestForSession != null) {
                    arrayList.add(buildRequestForSession);
                }
            }
        }
        if (arrayList.size() > 0) {
            ConsoleLogger.log(LoggingBehavior.APP_EVENTS, TAG, "Flushing %d events due to %s.", Integer.valueOf(flushStatistics.numEvents), flushReason.toString());
            for (AccountKitGraphRequestAsyncTask executeOnExecutor : arrayList) {
                executeOnExecutor.executeOnExecutor(sAppEventExecutor, new Void[0]);
            }
        }
    }

    private AccountKitGraphRequestAsyncTask buildRequestForSession(SessionEventsStateKey sessionEventsStateKey, SessionEventsState sessionEventsState, FlushStatistics flushStatistics) {
        AccountKitGraphRequest accountKitGraphRequest = new AccountKitGraphRequest(null, String.format("%s/events", new Object[]{sessionEventsStateKey.applicationId}), null, false, HttpMethod.POST);
        int populateRequest = sessionEventsState.populateRequest(accountKitGraphRequest);
        if (populateRequest == 0) {
            return null;
        }
        flushStatistics.numEvents += populateRequest;
        final SessionEventsStateKey sessionEventsStateKey2 = sessionEventsStateKey;
        final AccountKitGraphRequest accountKitGraphRequest2 = accountKitGraphRequest;
        final SessionEventsState sessionEventsState2 = sessionEventsState;
        final FlushStatistics flushStatistics2 = flushStatistics;
        return new AccountKitGraphRequestAsyncTask(accountKitGraphRequest, new Callback() {
            public void onCompleted(AccountKitGraphResponse accountKitGraphResponse) {
                AppEventsLogger.this.handleResponse(sessionEventsStateKey2, accountKitGraphRequest2, accountKitGraphResponse, sessionEventsState2, flushStatistics2);
            }
        });
    }

    private void handleResponse(SessionEventsStateKey sessionEventsStateKey, AccountKitGraphRequest accountKitGraphRequest, @Nullable AccountKitGraphResponse accountKitGraphResponse, SessionEventsState sessionEventsState, FlushStatistics flushStatistics) {
        AccountKitRequestError error = accountKitGraphResponse == null ? null : accountKitGraphResponse.getError();
        String str = "Success";
        FlushResult flushResult = FlushResult.SUCCESS;
        boolean z = true;
        if (error != null) {
            if (error.getErrorCode() == -1) {
                str = "Failed: No Connectivity";
                flushResult = FlushResult.NO_CONNECTIVITY;
            } else {
                str = String.format("Failed:\n  Response: %s\n  Error %s", new Object[]{accountKitGraphResponse.toString(), error.toString()});
                flushResult = FlushResult.SERVER_ERROR;
            }
        }
        if (AccountKit.getLoggingBehaviors().isEnabled(LoggingBehavior.APP_EVENTS)) {
            String jSONArray;
            try {
                jSONArray = new JSONArray((String) accountKitGraphRequest.getTag()).toString(2);
            } catch (JSONException unused) {
                jSONArray = "<Can't encode events for debug logging>";
            }
            ConsoleLogger.log(LoggingBehavior.APP_EVENTS, TAG, "Flush completed\nParams: %s\n  Result: %s\n  Events JSON: %s", accountKitGraphRequest.getRequestObject().toString(), str, jSONArray);
        }
        if (error == null) {
            z = false;
        }
        sessionEventsState.clearInFlightAndStats(z);
        if (flushResult == FlushResult.NO_CONNECTIVITY) {
            PersistedEvents.persistEvents(this.applicationContext, sessionEventsStateKey, sessionEventsState);
        }
        if (flushResult != FlushResult.SUCCESS && flushStatistics.result != FlushResult.NO_CONNECTIVITY) {
            flushStatistics.result = flushResult;
        }
    }
}
