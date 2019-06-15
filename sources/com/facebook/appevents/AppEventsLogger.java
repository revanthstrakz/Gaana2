package com.facebook.appevents;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import bolts.c;
import com.facebook.AccessToken;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphRequest.Callback;
import com.facebook.HttpMethod;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.internal.ActivityLifecycleTracker;
import com.facebook.internal.AttributionIdentifiers;
import com.facebook.internal.BundleJSONConverter;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.Logger;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AppEventsLogger {
    public static final String ACTION_APP_EVENTS_FLUSHED = "com.facebook.sdk.APP_EVENTS_FLUSHED";
    public static final String APP_EVENTS_EXTRA_FLUSH_RESULT = "com.facebook.sdk.APP_EVENTS_FLUSH_RESULT";
    public static final String APP_EVENTS_EXTRA_NUM_EVENTS_FLUSHED = "com.facebook.sdk.APP_EVENTS_NUM_EVENTS_FLUSHED";
    private static final String APP_EVENT_NAME_PUSH_OPENED = "fb_mobile_push_opened";
    public static final String APP_EVENT_PREFERENCES = "com.facebook.sdk.appEventPreferences";
    private static final String APP_EVENT_PUSH_PARAMETER_ACTION = "fb_push_action";
    private static final String APP_EVENT_PUSH_PARAMETER_CAMPAIGN = "fb_push_campaign";
    private static final int APP_SUPPORTS_ATTRIBUTION_ID_RECHECK_PERIOD_IN_SECONDS = 86400;
    private static final int FLUSH_APP_SESSION_INFO_IN_SECONDS = 30;
    private static final String PUSH_PAYLOAD_CAMPAIGN_KEY = "campaign";
    private static final String PUSH_PAYLOAD_KEY = "fb_push_payload";
    private static final String SOURCE_APPLICATION_HAS_BEEN_SET_BY_THIS_INTENT = "_fbSourceApplicationHasBeenSet";
    private static final String TAG = AppEventsLogger.class.getCanonicalName();
    private static String anonymousAppDeviceGUID;
    private static ScheduledThreadPoolExecutor backgroundExecutor;
    private static String externalAnalyticsUserID;
    private static FlushBehavior flushBehavior = FlushBehavior.AUTO;
    private static boolean isActivateAppEventRequested;
    private static boolean isOpenedByApplink;
    private static String pushNotificationsRegistrationId;
    private static String sourceApplication;
    private static Object staticLock = new Object();
    private final AccessTokenAppIdPair accessTokenAppId;
    private final String contextName;

    public enum FlushBehavior {
        AUTO,
        EXPLICIT_ONLY
    }

    static class PersistedAppSessionInfo {
        private static final String PERSISTED_SESSION_INFO_FILENAME = "AppEventsLogger.persistedsessioninfo";
        private static final Runnable appSessionInfoFlushRunnable = new Runnable() {
            public void run() {
                PersistedAppSessionInfo.saveAppSessionInformation(FacebookSdk.getApplicationContext());
            }
        };
        private static Map<AccessTokenAppIdPair, FacebookTimeSpentData> appSessionInfoMap = null;
        private static boolean hasChanges = false;
        private static boolean isLoaded = false;
        private static final Object staticLock = new Object();

        PersistedAppSessionInfo() {
        }

        /* JADX WARNING: Removed duplicated region for block: B:39:0x00a2 A:{Catch:{ all -> 0x007c }} */
        /* JADX WARNING: Removed duplicated region for block: B:26:0x0072 A:{Catch:{ all -> 0x007c }} */
        /* JADX WARNING: Removed duplicated region for block: B:31:0x0089 A:{Catch:{ all -> 0x007c }} */
        private static void restoreAppSessionInformation(android.content.Context r9) {
            /*
            r0 = staticLock;
            monitor-enter(r0);
            r1 = isLoaded;	 Catch:{ all -> 0x00ae }
            if (r1 != 0) goto L_0x00ac;
        L_0x0007:
            r1 = 0;
            r2 = 1;
            r3 = 0;
            r4 = new java.io.ObjectInputStream;	 Catch:{ FileNotFoundException -> 0x0095, Exception -> 0x0046, all -> 0x0041 }
            r5 = "AppEventsLogger.persistedsessioninfo";
            r5 = r9.openFileInput(r5);	 Catch:{ FileNotFoundException -> 0x0095, Exception -> 0x0046, all -> 0x0041 }
            r4.<init>(r5);	 Catch:{ FileNotFoundException -> 0x0095, Exception -> 0x0046, all -> 0x0041 }
            r3 = r4.readObject();	 Catch:{ FileNotFoundException -> 0x0096, Exception -> 0x003f }
            r3 = (java.util.HashMap) r3;	 Catch:{ FileNotFoundException -> 0x0096, Exception -> 0x003f }
            appSessionInfoMap = r3;	 Catch:{ FileNotFoundException -> 0x0096, Exception -> 0x003f }
            r3 = com.facebook.LoggingBehavior.APP_EVENTS;	 Catch:{ FileNotFoundException -> 0x0096, Exception -> 0x003f }
            r5 = "AppEvents";
            r6 = "App session info loaded";
            com.facebook.internal.Logger.log(r3, r5, r6);	 Catch:{ FileNotFoundException -> 0x0096, Exception -> 0x003f }
            com.facebook.internal.Utility.closeQuietly(r4);	 Catch:{ all -> 0x00ae }
            r3 = "AppEventsLogger.persistedsessioninfo";
            r9.deleteFile(r3);	 Catch:{ all -> 0x00ae }
            r9 = appSessionInfoMap;	 Catch:{ all -> 0x00ae }
            if (r9 != 0) goto L_0x0039;
        L_0x0032:
            r9 = new java.util.HashMap;	 Catch:{ all -> 0x00ae }
            r9.<init>();	 Catch:{ all -> 0x00ae }
            appSessionInfoMap = r9;	 Catch:{ all -> 0x00ae }
        L_0x0039:
            isLoaded = r2;	 Catch:{ all -> 0x00ae }
        L_0x003b:
            hasChanges = r1;	 Catch:{ all -> 0x00ae }
            goto L_0x00ac;
        L_0x003f:
            r3 = move-exception;
            goto L_0x004a;
        L_0x0041:
            r4 = move-exception;
            r8 = r4;
            r4 = r3;
            r3 = r8;
            goto L_0x007d;
        L_0x0046:
            r4 = move-exception;
            r8 = r4;
            r4 = r3;
            r3 = r8;
        L_0x004a:
            r5 = com.facebook.appevents.AppEventsLogger.TAG;	 Catch:{ all -> 0x007c }
            r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x007c }
            r6.<init>();	 Catch:{ all -> 0x007c }
            r7 = "Got unexpected exception restoring app session info: ";
            r6.append(r7);	 Catch:{ all -> 0x007c }
            r3 = r3.toString();	 Catch:{ all -> 0x007c }
            r6.append(r3);	 Catch:{ all -> 0x007c }
            r3 = r6.toString();	 Catch:{ all -> 0x007c }
            android.util.Log.w(r5, r3);	 Catch:{ all -> 0x007c }
            com.facebook.internal.Utility.closeQuietly(r4);	 Catch:{ all -> 0x00ae }
            r3 = "AppEventsLogger.persistedsessioninfo";
            r9.deleteFile(r3);	 Catch:{ all -> 0x00ae }
            r9 = appSessionInfoMap;	 Catch:{ all -> 0x00ae }
            if (r9 != 0) goto L_0x0079;
        L_0x0072:
            r9 = new java.util.HashMap;	 Catch:{ all -> 0x00ae }
            r9.<init>();	 Catch:{ all -> 0x00ae }
            appSessionInfoMap = r9;	 Catch:{ all -> 0x00ae }
        L_0x0079:
            isLoaded = r2;	 Catch:{ all -> 0x00ae }
            goto L_0x003b;
        L_0x007c:
            r3 = move-exception;
        L_0x007d:
            com.facebook.internal.Utility.closeQuietly(r4);	 Catch:{ all -> 0x00ae }
            r4 = "AppEventsLogger.persistedsessioninfo";
            r9.deleteFile(r4);	 Catch:{ all -> 0x00ae }
            r9 = appSessionInfoMap;	 Catch:{ all -> 0x00ae }
            if (r9 != 0) goto L_0x0090;
        L_0x0089:
            r9 = new java.util.HashMap;	 Catch:{ all -> 0x00ae }
            r9.<init>();	 Catch:{ all -> 0x00ae }
            appSessionInfoMap = r9;	 Catch:{ all -> 0x00ae }
        L_0x0090:
            isLoaded = r2;	 Catch:{ all -> 0x00ae }
            hasChanges = r1;	 Catch:{ all -> 0x00ae }
            throw r3;	 Catch:{ all -> 0x00ae }
        L_0x0095:
            r4 = r3;
        L_0x0096:
            com.facebook.internal.Utility.closeQuietly(r4);	 Catch:{ all -> 0x00ae }
            r3 = "AppEventsLogger.persistedsessioninfo";
            r9.deleteFile(r3);	 Catch:{ all -> 0x00ae }
            r9 = appSessionInfoMap;	 Catch:{ all -> 0x00ae }
            if (r9 != 0) goto L_0x00a9;
        L_0x00a2:
            r9 = new java.util.HashMap;	 Catch:{ all -> 0x00ae }
            r9.<init>();	 Catch:{ all -> 0x00ae }
            appSessionInfoMap = r9;	 Catch:{ all -> 0x00ae }
        L_0x00a9:
            isLoaded = r2;	 Catch:{ all -> 0x00ae }
            goto L_0x003b;
        L_0x00ac:
            monitor-exit(r0);	 Catch:{ all -> 0x00ae }
            return;
        L_0x00ae:
            r9 = move-exception;
            monitor-exit(r0);	 Catch:{ all -> 0x00ae }
            throw r9;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.AppEventsLogger$PersistedAppSessionInfo.restoreAppSessionInformation(android.content.Context):void");
        }

        static void saveAppSessionInformation(Context context) {
            Exception e;
            String access$200;
            StringBuilder stringBuilder;
            Throwable th;
            synchronized (staticLock) {
                if (hasChanges) {
                    Closeable closeable = null;
                    try {
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(context.openFileOutput(PERSISTED_SESSION_INFO_FILENAME, 0)));
                        try {
                            objectOutputStream.writeObject(appSessionInfoMap);
                            hasChanges = false;
                            Logger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "App session info saved");
                            Utility.closeQuietly(objectOutputStream);
                        } catch (Exception e2) {
                            e = e2;
                            closeable = objectOutputStream;
                            try {
                                access$200 = AppEventsLogger.TAG;
                                stringBuilder = new StringBuilder();
                                stringBuilder.append("Got unexpected exception while writing app session info: ");
                                stringBuilder.append(e.toString());
                                Log.w(access$200, stringBuilder.toString());
                                Utility.closeQuietly(closeable);
                            } catch (Throwable th2) {
                                th = th2;
                                Utility.closeQuietly(closeable);
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            closeable = objectOutputStream;
                            Utility.closeQuietly(closeable);
                            throw th;
                        }
                    } catch (Exception e3) {
                        e = e3;
                        access$200 = AppEventsLogger.TAG;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Got unexpected exception while writing app session info: ");
                        stringBuilder.append(e.toString());
                        Log.w(access$200, stringBuilder.toString());
                        Utility.closeQuietly(closeable);
                    }
                }
            }
        }

        static void onResume(Context context, AccessTokenAppIdPair accessTokenAppIdPair, AppEventsLogger appEventsLogger, long j, String str) {
            synchronized (staticLock) {
                getTimeSpentData(context, accessTokenAppIdPair).onResume(appEventsLogger, j, str);
                onTimeSpentDataUpdate();
            }
        }

        static void onSuspend(Context context, AccessTokenAppIdPair accessTokenAppIdPair, AppEventsLogger appEventsLogger, long j) {
            synchronized (staticLock) {
                getTimeSpentData(context, accessTokenAppIdPair).onSuspend(appEventsLogger, j);
                onTimeSpentDataUpdate();
            }
        }

        private static FacebookTimeSpentData getTimeSpentData(Context context, AccessTokenAppIdPair accessTokenAppIdPair) {
            restoreAppSessionInformation(context);
            FacebookTimeSpentData facebookTimeSpentData = (FacebookTimeSpentData) appSessionInfoMap.get(accessTokenAppIdPair);
            if (facebookTimeSpentData != null) {
                return facebookTimeSpentData;
            }
            facebookTimeSpentData = new FacebookTimeSpentData();
            appSessionInfoMap.put(accessTokenAppIdPair, facebookTimeSpentData);
            return facebookTimeSpentData;
        }

        private static void onTimeSpentDataUpdate() {
            if (!hasChanges) {
                hasChanges = true;
                AppEventsLogger.backgroundExecutor.schedule(appSessionInfoFlushRunnable, 30, TimeUnit.SECONDS);
            }
        }
    }

    public static void activateApp(Application application) {
        activateApp(application, null);
    }

    public static void activateApp(Application application, String str) {
        if (FacebookSdk.isInitialized()) {
            AnalyticsUserIDStore.initStore();
            if (str == null) {
                str = FacebookSdk.getApplicationId();
            }
            FacebookSdk.publishInstallAsync(application, str);
            ActivityLifecycleTracker.startTracking(application, str);
            return;
        }
        throw new FacebookException("The Facebook sdk must be initialized before calling activateApp");
    }

    @Deprecated
    public static void activateApp(Context context) {
        if (ActivityLifecycleTracker.isTracking()) {
            Log.w(TAG, "activateApp events are being logged automatically. There's no need to call activateApp explicitly, this is safe to remove.");
            return;
        }
        FacebookSdk.sdkInitialize(context);
        activateApp(context, Utility.getMetadataApplicationId(context));
    }

    @Deprecated
    public static void activateApp(Context context, String str) {
        if (ActivityLifecycleTracker.isTracking()) {
            Log.w(TAG, "activateApp events are being logged automatically. There's no need to call activateApp explicitly, this is safe to remove.");
        } else if (context == null || str == null) {
            throw new IllegalArgumentException("Both context and applicationId must be non-null");
        } else {
            AnalyticsUserIDStore.initStore();
            if (context instanceof Activity) {
                setSourceApplication((Activity) context);
            } else {
                resetSourceApplication();
                Log.d(AppEventsLogger.class.getName(), "To set source application the context of activateApp must be an instance of Activity");
            }
            FacebookSdk.publishInstallAsync(context, str);
            AppEventsLogger appEventsLogger = new AppEventsLogger(context, str, null);
            final long currentTimeMillis = System.currentTimeMillis();
            final String sourceApplication = getSourceApplication();
            backgroundExecutor.execute(new Runnable(appEventsLogger) {
                final /* synthetic */ AppEventsLogger val$logger;

                public void run() {
                    this.val$logger.logAppSessionResumeEvent(currentTimeMillis, sourceApplication);
                }
            });
        }
    }

    @Deprecated
    public static void deactivateApp(Context context) {
        if (ActivityLifecycleTracker.isTracking()) {
            Log.w(TAG, "deactivateApp events are being logged automatically. There's no need to call deactivateApp, this is safe to remove.");
        } else {
            deactivateApp(context, Utility.getMetadataApplicationId(context));
        }
    }

    @Deprecated
    public static void deactivateApp(Context context, String str) {
        if (ActivityLifecycleTracker.isTracking()) {
            Log.w(TAG, "deactivateApp events are being logged automatically. There's no need to call deactivateApp, this is safe to remove.");
        } else if (context == null || str == null) {
            throw new IllegalArgumentException("Both context and applicationId must be non-null");
        } else {
            resetSourceApplication();
            AppEventsLogger appEventsLogger = new AppEventsLogger(context, str, null);
            final long currentTimeMillis = System.currentTimeMillis();
            backgroundExecutor.execute(new Runnable(appEventsLogger) {
                final /* synthetic */ AppEventsLogger val$logger;

                public void run() {
                    this.val$logger.logAppSessionSuspendEvent(currentTimeMillis);
                }
            });
        }
    }

    private void logAppSessionResumeEvent(long j, String str) {
        PersistedAppSessionInfo.onResume(FacebookSdk.getApplicationContext(), this.accessTokenAppId, this, j, str);
    }

    private void logAppSessionSuspendEvent(long j) {
        PersistedAppSessionInfo.onSuspend(FacebookSdk.getApplicationContext(), this.accessTokenAppId, this, j);
    }

    public static AppEventsLogger newLogger(Context context) {
        return new AppEventsLogger(context, null, null);
    }

    public static AppEventsLogger newLogger(Context context, AccessToken accessToken) {
        return new AppEventsLogger(context, null, accessToken);
    }

    public static AppEventsLogger newLogger(Context context, String str, AccessToken accessToken) {
        return new AppEventsLogger(context, str, accessToken);
    }

    public static AppEventsLogger newLogger(Context context, String str) {
        return new AppEventsLogger(context, str, null);
    }

    public static FlushBehavior getFlushBehavior() {
        FlushBehavior flushBehavior;
        synchronized (staticLock) {
            flushBehavior = flushBehavior;
        }
        return flushBehavior;
    }

    public static void setFlushBehavior(FlushBehavior flushBehavior) {
        synchronized (staticLock) {
            flushBehavior = flushBehavior;
        }
    }

    public void logEvent(String str) {
        logEvent(str, null);
    }

    public void logEvent(String str, double d) {
        logEvent(str, d, null);
    }

    public void logEvent(String str, Bundle bundle) {
        logEvent(str, null, bundle, false, ActivityLifecycleTracker.getCurrentSessionGuid());
    }

    public void logEvent(String str, double d, Bundle bundle) {
        logEvent(str, Double.valueOf(d), bundle, false, ActivityLifecycleTracker.getCurrentSessionGuid());
    }

    public void logPurchase(BigDecimal bigDecimal, Currency currency) {
        logPurchase(bigDecimal, currency, null);
    }

    public void logPurchase(BigDecimal bigDecimal, Currency currency, Bundle bundle) {
        if (bigDecimal == null) {
            notifyDeveloperError("purchaseAmount cannot be null");
        } else if (currency == null) {
            notifyDeveloperError("currency cannot be null");
        } else {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putString(AppEventsConstants.EVENT_PARAM_CURRENCY, currency.getCurrencyCode());
            logEvent(AppEventsConstants.EVENT_NAME_PURCHASED, bigDecimal.doubleValue(), bundle);
            eagerFlush();
        }
    }

    public void logPushNotificationOpen(Bundle bundle) {
        logPushNotificationOpen(bundle, null);
    }

    public void logPushNotificationOpen(Bundle bundle, String str) {
        String string;
        try {
            string = bundle.getString(PUSH_PAYLOAD_KEY);
            if (!Utility.isNullOrEmpty(string)) {
                string = new JSONObject(string).getString(PUSH_PAYLOAD_CAMPAIGN_KEY);
                if (string == null) {
                    Logger.log(LoggingBehavior.DEVELOPER_ERRORS, TAG, "Malformed payload specified for logging a push notification open.");
                    return;
                }
                Bundle bundle2 = new Bundle();
                bundle2.putString(APP_EVENT_PUSH_PARAMETER_CAMPAIGN, string);
                if (str != null) {
                    bundle2.putString(APP_EVENT_PUSH_PARAMETER_ACTION, str);
                }
                logEvent(APP_EVENT_NAME_PUSH_OPENED, bundle2);
            }
        } catch (JSONException unused) {
            string = null;
        }
    }

    public void flush() {
        AppEventQueue.flush(FlushReason.EXPLICIT);
    }

    public static void onContextStop() {
        AppEventQueue.persistToDisk();
    }

    public boolean isValidForAccessToken(AccessToken accessToken) {
        return this.accessTokenAppId.equals(new AccessTokenAppIdPair(accessToken));
    }

    public static void setPushNotificationsRegistrationId(String str) {
        synchronized (staticLock) {
            if (!Utility.stringsEqualOrEmpty(pushNotificationsRegistrationId, str)) {
                pushNotificationsRegistrationId = str;
                AppEventsLogger newLogger = newLogger(FacebookSdk.getApplicationContext());
                newLogger.logEvent(AppEventsConstants.EVENT_NAME_PUSH_TOKEN_OBTAINED);
                if (getFlushBehavior() != FlushBehavior.EXPLICIT_ONLY) {
                    newLogger.flush();
                }
            }
        }
    }

    static String getPushNotificationsRegistrationId() {
        String str;
        synchronized (staticLock) {
            str = pushNotificationsRegistrationId;
        }
        return str;
    }

    public static void setUserID(String str) {
        AnalyticsUserIDStore.setUserID(str);
    }

    public static String getUserID() {
        return AnalyticsUserIDStore.getUserID();
    }

    public static void clearUserID() {
        AnalyticsUserIDStore.setUserID(null);
    }

    public static void updateUserProperties(Bundle bundle, Callback callback) {
        updateUserProperties(bundle, FacebookSdk.getApplicationId(), callback);
    }

    public static void updateUserProperties(final Bundle bundle, final String str, final Callback callback) {
        final String userID = getUserID();
        if (userID == null || userID.isEmpty()) {
            Logger.log(LoggingBehavior.APP_EVENTS, TAG, "AppEventsLogger userID cannot be null or empty");
        } else {
            getAnalyticsExecutor().execute(new Runnable() {
                public void run() {
                    Bundle bundle = new Bundle();
                    bundle.putString("user_unique_id", userID);
                    bundle.putBundle("custom_data", bundle);
                    AttributionIdentifiers attributionIdentifiers = AttributionIdentifiers.getAttributionIdentifiers(FacebookSdk.getApplicationContext());
                    if (!(attributionIdentifiers == null || attributionIdentifiers.getAndroidAdvertiserId() == null)) {
                        bundle.putString("advertiser_id", attributionIdentifiers.getAndroidAdvertiserId());
                    }
                    Bundle bundle2 = new Bundle();
                    try {
                        JSONObject convertToJSON = BundleJSONConverter.convertToJSON(bundle);
                        JSONArray jSONArray = new JSONArray();
                        jSONArray.put(convertToJSON);
                        bundle2.putString("data", jSONArray.toString());
                        GraphRequest graphRequest = new GraphRequest(AccessToken.getCurrentAccessToken(), String.format(Locale.US, "%s/user_properties", new Object[]{str}), bundle2, HttpMethod.POST, callback);
                        graphRequest.setSkipClientToken(true);
                        graphRequest.executeAsync();
                    } catch (JSONException e) {
                        throw new FacebookException("Failed to construct request", e);
                    }
                }
            });
        }
    }

    public void logSdkEvent(String str, Double d, Bundle bundle) {
        logEvent(str, d, bundle, true, ActivityLifecycleTracker.getCurrentSessionGuid());
    }

    public String getApplicationId() {
        return this.accessTokenAppId.getApplicationId();
    }

    private AppEventsLogger(Context context, String str, AccessToken accessToken) {
        this(Utility.getActivityName(context), str, accessToken);
    }

    protected AppEventsLogger(String str, String str2, AccessToken accessToken) {
        Validate.sdkInitialized();
        this.contextName = str;
        if (accessToken == null) {
            accessToken = AccessToken.getCurrentAccessToken();
        }
        if (accessToken == null || !(str2 == null || str2.equals(accessToken.getApplicationId()))) {
            if (str2 == null) {
                str2 = Utility.getMetadataApplicationId(FacebookSdk.getApplicationContext());
            }
            this.accessTokenAppId = new AccessTokenAppIdPair(null, str2);
        } else {
            this.accessTokenAppId = new AccessTokenAppIdPair(accessToken);
        }
        initializeTimersIfNeeded();
    }

    private static void initializeTimersIfNeeded() {
        synchronized (staticLock) {
            if (backgroundExecutor != null) {
                return;
            }
            backgroundExecutor = new ScheduledThreadPoolExecutor(1);
            backgroundExecutor.scheduleAtFixedRate(new Runnable() {
                public void run() {
                    HashSet<String> hashSet = new HashSet();
                    for (AccessTokenAppIdPair applicationId : AppEventQueue.getKeySet()) {
                        hashSet.add(applicationId.getApplicationId());
                    }
                    for (String queryAppSettings : hashSet) {
                        FetchedAppSettingsManager.queryAppSettings(queryAppSettings, true);
                    }
                }
            }, 0, 86400, TimeUnit.SECONDS);
        }
    }

    private void logEvent(String str, Double d, Bundle bundle, boolean z, @Nullable UUID uuid) {
        try {
            logEvent(FacebookSdk.getApplicationContext(), new AppEvent(this.contextName, str, d, bundle, z, uuid), this.accessTokenAppId);
        } catch (JSONException e) {
            Logger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "JSON encoding for app event failed: '%s'", e.toString());
        } catch (FacebookException e2) {
            Logger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "Invalid app event: %s", e2.toString());
        }
    }

    private static void logEvent(Context context, AppEvent appEvent, AccessTokenAppIdPair accessTokenAppIdPair) {
        AppEventQueue.add(accessTokenAppIdPair, appEvent);
        if (!appEvent.getIsImplicit() && !isActivateAppEventRequested) {
            if (appEvent.getName() == AppEventsConstants.EVENT_NAME_ACTIVATED_APP) {
                isActivateAppEventRequested = true;
            } else {
                Logger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "Warning: Please call AppEventsLogger.activateApp(...)from the long-lived activity's onResume() methodbefore logging other app events.");
            }
        }
    }

    static void eagerFlush() {
        if (getFlushBehavior() != FlushBehavior.EXPLICIT_ONLY) {
            AppEventQueue.flush(FlushReason.EAGER_FLUSHING_EVENT);
        }
    }

    private static void notifyDeveloperError(String str) {
        Logger.log(LoggingBehavior.DEVELOPER_ERRORS, "AppEvents", str);
    }

    private static void setSourceApplication(Activity activity) {
        ComponentName callingActivity = activity.getCallingActivity();
        if (callingActivity != null) {
            String packageName = callingActivity.getPackageName();
            if (packageName.equals(activity.getPackageName())) {
                resetSourceApplication();
                return;
            }
            sourceApplication = packageName;
        }
        Intent intent = activity.getIntent();
        if (intent == null || intent.getBooleanExtra(SOURCE_APPLICATION_HAS_BEEN_SET_BY_THIS_INTENT, false)) {
            resetSourceApplication();
            return;
        }
        Bundle a = c.a(intent);
        if (a == null) {
            resetSourceApplication();
            return;
        }
        isOpenedByApplink = true;
        a = a.getBundle("referer_app_link");
        if (a == null) {
            sourceApplication = null;
            return;
        }
        sourceApplication = a.getString("package");
        intent.putExtra(SOURCE_APPLICATION_HAS_BEEN_SET_BY_THIS_INTENT, true);
    }

    static void setSourceApplication(String str, boolean z) {
        sourceApplication = str;
        isOpenedByApplink = z;
    }

    static String getSourceApplication() {
        String str = "Unclassified";
        if (isOpenedByApplink) {
            str = "Applink";
        }
        if (sourceApplication == null) {
            return str;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append("(");
        stringBuilder.append(sourceApplication);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    static void resetSourceApplication() {
        sourceApplication = null;
        isOpenedByApplink = false;
    }

    static Executor getAnalyticsExecutor() {
        if (backgroundExecutor == null) {
            initializeTimersIfNeeded();
        }
        return backgroundExecutor;
    }

    public static String getAnonymousAppDeviceGUID(Context context) {
        if (anonymousAppDeviceGUID == null) {
            synchronized (staticLock) {
                if (anonymousAppDeviceGUID == null) {
                    anonymousAppDeviceGUID = context.getSharedPreferences(APP_EVENT_PREFERENCES, 0).getString("anonymousAppDeviceGUID", null);
                    if (anonymousAppDeviceGUID == null) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("XZ");
                        stringBuilder.append(UUID.randomUUID().toString());
                        anonymousAppDeviceGUID = stringBuilder.toString();
                        context.getSharedPreferences(APP_EVENT_PREFERENCES, 0).edit().putString("anonymousAppDeviceGUID", anonymousAppDeviceGUID).apply();
                    }
                }
            }
        }
        return anonymousAppDeviceGUID;
    }
}
