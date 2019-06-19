package com.facebook;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.AsyncTask;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Base64;
import android.util.Log;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.internal.LockOnGetVariable;
import com.facebook.internal.ServerProtocol;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import com.gaana.login.LoginManager;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public final class FacebookSdk {
    public static final String APPLICATION_ID_PROPERTY = "com.facebook.sdk.ApplicationId";
    public static final String APPLICATION_NAME_PROPERTY = "com.facebook.sdk.ApplicationName";
    private static final String ATTRIBUTION_PREFERENCES = "com.facebook.sdk.attributionTracking";
    public static final String AUTO_LOG_APP_EVENTS_ENABLED_PROPERTY = "com.facebook.sdk.AutoLogAppEventsEnabled";
    static final String CALLBACK_OFFSET_CHANGED_AFTER_INIT = "The callback request code offset can't be updated once the SDK is initialized. Call FacebookSdk.setCallbackRequestCodeOffset inside your Application.onCreate method";
    static final String CALLBACK_OFFSET_NEGATIVE = "The callback request code offset can't be negative.";
    public static final String CALLBACK_OFFSET_PROPERTY = "com.facebook.sdk.CallbackOffset";
    public static final String CLIENT_TOKEN_PROPERTY = "com.facebook.sdk.ClientToken";
    private static final int DEFAULT_CALLBACK_REQUEST_CODE_OFFSET = 64206;
    private static final int DEFAULT_CORE_POOL_SIZE = 5;
    private static final int DEFAULT_KEEP_ALIVE = 1;
    private static final int DEFAULT_MAXIMUM_POOL_SIZE = 128;
    private static final int DEFAULT_THEME = R.style.com_facebook_activity_theme;
    private static final ThreadFactory DEFAULT_THREAD_FACTORY = new ThreadFactory() {
        private final AtomicInteger counter = new AtomicInteger(0);

        public Thread newThread(Runnable runnable) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("FacebookSdk #");
            stringBuilder.append(this.counter.incrementAndGet());
            return new Thread(runnable, stringBuilder.toString());
        }
    };
    private static final BlockingQueue<Runnable> DEFAULT_WORK_QUEUE = new LinkedBlockingQueue(10);
    private static final String FACEBOOK_COM = "facebook.com";
    private static final Object LOCK = new Object();
    private static final int MAX_REQUEST_CODE_RANGE = 100;
    private static final String PUBLISH_ACTIVITY_PATH = "%s/activities";
    private static final String TAG = FacebookSdk.class.getCanonicalName();
    public static final String WEB_DIALOG_THEME = "com.facebook.sdk.WebDialogTheme";
    private static volatile String appClientToken = null;
    private static Context applicationContext = null;
    private static volatile String applicationId = null;
    private static volatile String applicationName = null;
    private static volatile Boolean autoLogAppEventsEnabled = null;
    private static LockOnGetVariable<File> cacheDir = null;
    private static int callbackRequestCodeOffset = 64206;
    private static volatile Executor executor = null;
    private static volatile String facebookDomain = "facebook.com";
    private static String graphApiVersion = ServerProtocol.getDefaultAPIVersion();
    private static volatile boolean isDebugEnabled;
    private static boolean isLegacyTokenUpgradeSupported;
    private static final HashSet<LoggingBehavior> loggingBehaviors = new HashSet(Arrays.asList(new LoggingBehavior[]{LoggingBehavior.DEVELOPER_ERRORS}));
    private static AtomicLong onProgressThreshold = new AtomicLong(PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH);
    private static Boolean sdkInitialized = Boolean.valueOf(false);
    private static volatile int webDialogTheme;

    public interface InitializeCallback {
        void onInitialized();
    }

    public static String getSdkVersion() {
        return "4.22.0";
    }

    @Deprecated
    public static synchronized void sdkInitialize(Context context, int i) {
        synchronized (FacebookSdk.class) {
            sdkInitialize(context, i, null);
        }
    }

    @Deprecated
    public static synchronized void sdkInitialize(Context context, int i, InitializeCallback initializeCallback) {
        synchronized (FacebookSdk.class) {
            if (sdkInitialized.booleanValue() && i != callbackRequestCodeOffset) {
                throw new FacebookException(CALLBACK_OFFSET_CHANGED_AFTER_INIT);
            } else if (i < 0) {
                throw new FacebookException(CALLBACK_OFFSET_NEGATIVE);
            } else {
                callbackRequestCodeOffset = i;
                sdkInitialize(context, initializeCallback);
            }
        }
    }

    @Deprecated
    public static synchronized void sdkInitialize(Context context) {
        synchronized (FacebookSdk.class) {
            sdkInitialize(context, null);
        }
    }

    /* JADX WARNING: Missing block: B:8:0x0011, code skipped:
            return;
     */
    @java.lang.Deprecated
    public static synchronized void sdkInitialize(final android.content.Context r3, final com.facebook.FacebookSdk.InitializeCallback r4) {
        /*
        r0 = com.facebook.FacebookSdk.class;
        monitor-enter(r0);
        r1 = sdkInitialized;	 Catch:{ all -> 0x006a }
        r1 = r1.booleanValue();	 Catch:{ all -> 0x006a }
        if (r1 == 0) goto L_0x0012;
    L_0x000b:
        if (r4 == 0) goto L_0x0010;
    L_0x000d:
        r4.onInitialized();	 Catch:{ all -> 0x006a }
    L_0x0010:
        monitor-exit(r0);
        return;
    L_0x0012:
        r1 = "applicationContext";
        com.facebook.internal.Validate.notNull(r3, r1);	 Catch:{ all -> 0x006a }
        r1 = 0;
        com.facebook.internal.Validate.hasFacebookActivity(r3, r1);	 Catch:{ all -> 0x006a }
        com.facebook.internal.Validate.hasInternetPermissions(r3, r1);	 Catch:{ all -> 0x006a }
        r1 = r3.getApplicationContext();	 Catch:{ all -> 0x006a }
        applicationContext = r1;	 Catch:{ all -> 0x006a }
        r1 = applicationContext;	 Catch:{ all -> 0x006a }
        loadDefaultsFromMetadata(r1);	 Catch:{ all -> 0x006a }
        r1 = applicationId;	 Catch:{ all -> 0x006a }
        r1 = com.facebook.internal.Utility.isNullOrEmpty(r1);	 Catch:{ all -> 0x006a }
        if (r1 == 0) goto L_0x0039;
    L_0x0031:
        r3 = new com.facebook.FacebookException;	 Catch:{ all -> 0x006a }
        r4 = "A valid Facebook app id must be set in the AndroidManifest.xml or set by calling FacebookSdk.setApplicationId before initializing the sdk.";
        r3.<init>(r4);	 Catch:{ all -> 0x006a }
        throw r3;	 Catch:{ all -> 0x006a }
    L_0x0039:
        r1 = 1;
        r1 = java.lang.Boolean.valueOf(r1);	 Catch:{ all -> 0x006a }
        sdkInitialized = r1;	 Catch:{ all -> 0x006a }
        com.facebook.internal.FetchedAppSettingsManager.loadAppSettingsAsync();	 Catch:{ all -> 0x006a }
        com.facebook.internal.NativeProtocol.updateAllAvailableProtocolVersionsAsync();	 Catch:{ all -> 0x006a }
        r1 = applicationContext;	 Catch:{ all -> 0x006a }
        com.facebook.internal.BoltsMeasurementEventListener.getInstance(r1);	 Catch:{ all -> 0x006a }
        r1 = new com.facebook.internal.LockOnGetVariable;	 Catch:{ all -> 0x006a }
        r2 = new com.facebook.FacebookSdk$2;	 Catch:{ all -> 0x006a }
        r2.<init>();	 Catch:{ all -> 0x006a }
        r1.<init>(r2);	 Catch:{ all -> 0x006a }
        cacheDir = r1;	 Catch:{ all -> 0x006a }
        r1 = new java.util.concurrent.FutureTask;	 Catch:{ all -> 0x006a }
        r2 = new com.facebook.FacebookSdk$3;	 Catch:{ all -> 0x006a }
        r2.<init>(r4, r3);	 Catch:{ all -> 0x006a }
        r1.<init>(r2);	 Catch:{ all -> 0x006a }
        r3 = getExecutor();	 Catch:{ all -> 0x006a }
        r3.execute(r1);	 Catch:{ all -> 0x006a }
        monitor-exit(r0);
        return;
    L_0x006a:
        r3 = move-exception;
        monitor-exit(r0);
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.FacebookSdk.sdkInitialize(android.content.Context, com.facebook.FacebookSdk$InitializeCallback):void");
    }

    public static synchronized boolean isInitialized() {
        boolean booleanValue;
        synchronized (FacebookSdk.class) {
            booleanValue = sdkInitialized.booleanValue();
        }
        return booleanValue;
    }

    public static Set<LoggingBehavior> getLoggingBehaviors() {
        Set unmodifiableSet;
        synchronized (loggingBehaviors) {
            unmodifiableSet = Collections.unmodifiableSet(new HashSet(loggingBehaviors));
        }
        return unmodifiableSet;
    }

    public static void addLoggingBehavior(LoggingBehavior loggingBehavior) {
        synchronized (loggingBehaviors) {
            loggingBehaviors.add(loggingBehavior);
            updateGraphDebugBehavior();
        }
    }

    public static void removeLoggingBehavior(LoggingBehavior loggingBehavior) {
        synchronized (loggingBehaviors) {
            loggingBehaviors.remove(loggingBehavior);
        }
    }

    public static void clearLoggingBehaviors() {
        synchronized (loggingBehaviors) {
            loggingBehaviors.clear();
        }
    }

    public static boolean isLoggingBehaviorEnabled(LoggingBehavior loggingBehavior) {
        boolean z;
        synchronized (loggingBehaviors) {
            z = isDebugEnabled() && loggingBehaviors.contains(loggingBehavior);
        }
        return z;
    }

    public static boolean isDebugEnabled() {
        return isDebugEnabled;
    }

    public static void setIsDebugEnabled(boolean z) {
        isDebugEnabled = z;
    }

    public static boolean isLegacyTokenUpgradeSupported() {
        return isLegacyTokenUpgradeSupported;
    }

    private static void updateGraphDebugBehavior() {
        if (loggingBehaviors.contains(LoggingBehavior.GRAPH_API_DEBUG_INFO) && !loggingBehaviors.contains(LoggingBehavior.GRAPH_API_DEBUG_WARNING)) {
            loggingBehaviors.add(LoggingBehavior.GRAPH_API_DEBUG_WARNING);
        }
    }

    public static void setLegacyTokenUpgradeSupported(boolean z) {
        isLegacyTokenUpgradeSupported = z;
    }

    public static Executor getExecutor() {
        synchronized (LOCK) {
            if (executor == null) {
                executor = AsyncTask.THREAD_POOL_EXECUTOR;
            }
        }
        return executor;
    }

    public static void setExecutor(Executor executor) {
        Validate.notNull(executor, "executor");
        synchronized (LOCK) {
            executor = executor;
        }
    }

    public static String getFacebookDomain() {
        return facebookDomain;
    }

    public static void setFacebookDomain(String str) {
        Log.w(TAG, "WARNING: Calling setFacebookDomain from non-DEBUG code.");
        facebookDomain = str;
    }

    public static Context getApplicationContext() {
        Validate.sdkInitialized();
        return applicationContext;
    }

    public static void setGraphApiVersion(String str) {
        if (!Utility.isNullOrEmpty(str) && !graphApiVersion.equals(str)) {
            graphApiVersion = str;
        }
    }

    public static String getGraphApiVersion() {
        return graphApiVersion;
    }

    public static void publishInstallAsync(Context context, final String str) {
        context = context.getApplicationContext();
        getExecutor().execute(new Runnable() {
            public void run() {
                FacebookSdk.publishInstallAndWaitForResponse(context, str);
            }
        });
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0080 A:{Catch:{ JSONException -> 0x00aa, Exception -> 0x00bb }} */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x006a A:{SYNTHETIC, Splitter:B:17:0x006a} */
    static com.facebook.GraphResponse publishInstallAndWaitForResponse(android.content.Context r14, java.lang.String r15) {
        /*
        r0 = 0;
        if (r14 == 0) goto L_0x00b3;
    L_0x0003:
        if (r15 != 0) goto L_0x0007;
    L_0x0005:
        goto L_0x00b3;
    L_0x0007:
        r1 = com.facebook.internal.AttributionIdentifiers.getAttributionIdentifiers(r14);	 Catch:{ Exception -> 0x00bb }
        r2 = "com.facebook.sdk.attributionTracking";
        r3 = 0;
        r2 = r14.getSharedPreferences(r2, r3);	 Catch:{ Exception -> 0x00bb }
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00bb }
        r4.<init>();	 Catch:{ Exception -> 0x00bb }
        r4.append(r15);	 Catch:{ Exception -> 0x00bb }
        r5 = "ping";
        r4.append(r5);	 Catch:{ Exception -> 0x00bb }
        r4 = r4.toString();	 Catch:{ Exception -> 0x00bb }
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00bb }
        r5.<init>();	 Catch:{ Exception -> 0x00bb }
        r5.append(r15);	 Catch:{ Exception -> 0x00bb }
        r6 = "json";
        r5.append(r6);	 Catch:{ Exception -> 0x00bb }
        r5 = r5.toString();	 Catch:{ Exception -> 0x00bb }
        r6 = 0;
        r8 = r2.getLong(r4, r6);	 Catch:{ Exception -> 0x00bb }
        r10 = r2.getString(r5, r0);	 Catch:{ Exception -> 0x00bb }
        r11 = com.facebook.internal.AppEventsLoggerUtility.GraphAPIActivityType.MOBILE_INSTALL_EVENT;	 Catch:{ JSONException -> 0x00aa }
        r12 = com.facebook.appevents.AppEventsLogger.getAnonymousAppDeviceGUID(r14);	 Catch:{ JSONException -> 0x00aa }
        r13 = getLimitEventAndDataUsage(r14);	 Catch:{ JSONException -> 0x00aa }
        r14 = com.facebook.internal.AppEventsLoggerUtility.getJSONObjectForGraphAPICall(r11, r1, r12, r13, r14);	 Catch:{ JSONException -> 0x00aa }
        r1 = "%s/activities";
        r11 = 1;
        r12 = new java.lang.Object[r11];	 Catch:{ Exception -> 0x00bb }
        r12[r3] = r15;	 Catch:{ Exception -> 0x00bb }
        r15 = java.lang.String.format(r1, r12);	 Catch:{ Exception -> 0x00bb }
        r14 = com.facebook.GraphRequest.newPostRequest(r0, r15, r14, r0);	 Catch:{ Exception -> 0x00bb }
        r15 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1));
        if (r15 == 0) goto L_0x0086;
    L_0x005f:
        if (r10 == 0) goto L_0x0067;
    L_0x0061:
        r15 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0067 }
        r15.<init>(r10);	 Catch:{ JSONException -> 0x0067 }
        goto L_0x0068;
    L_0x0067:
        r15 = r0;
    L_0x0068:
        if (r15 != 0) goto L_0x0080;
    L_0x006a:
        r15 = "true";
        r1 = new com.facebook.GraphRequestBatch;	 Catch:{ Exception -> 0x00bb }
        r2 = new com.facebook.GraphRequest[r11];	 Catch:{ Exception -> 0x00bb }
        r2[r3] = r14;	 Catch:{ Exception -> 0x00bb }
        r1.<init>(r2);	 Catch:{ Exception -> 0x00bb }
        r14 = com.facebook.GraphResponse.createResponsesFromString(r15, r0, r1);	 Catch:{ Exception -> 0x00bb }
        r14 = r14.get(r3);	 Catch:{ Exception -> 0x00bb }
        r14 = (com.facebook.GraphResponse) r14;	 Catch:{ Exception -> 0x00bb }
        return r14;
    L_0x0080:
        r14 = new com.facebook.GraphResponse;	 Catch:{ Exception -> 0x00bb }
        r14.<init>(r0, r0, r0, r15);	 Catch:{ Exception -> 0x00bb }
        return r14;
    L_0x0086:
        r14 = r14.executeAndWait();	 Catch:{ Exception -> 0x00bb }
        r15 = r2.edit();	 Catch:{ Exception -> 0x00bb }
        r1 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x00bb }
        r15.putLong(r4, r1);	 Catch:{ Exception -> 0x00bb }
        r1 = r14.getJSONObject();	 Catch:{ Exception -> 0x00bb }
        if (r1 == 0) goto L_0x00a6;
    L_0x009b:
        r1 = r14.getJSONObject();	 Catch:{ Exception -> 0x00bb }
        r1 = r1.toString();	 Catch:{ Exception -> 0x00bb }
        r15.putString(r5, r1);	 Catch:{ Exception -> 0x00bb }
    L_0x00a6:
        r15.apply();	 Catch:{ Exception -> 0x00bb }
        return r14;
    L_0x00aa:
        r14 = move-exception;
        r15 = new com.facebook.FacebookException;	 Catch:{ Exception -> 0x00bb }
        r1 = "An error occurred while publishing install.";
        r15.<init>(r1, r14);	 Catch:{ Exception -> 0x00bb }
        throw r15;	 Catch:{ Exception -> 0x00bb }
    L_0x00b3:
        r14 = new java.lang.IllegalArgumentException;	 Catch:{ Exception -> 0x00bb }
        r15 = "Both context and applicationId must be non-null";
        r14.<init>(r15);	 Catch:{ Exception -> 0x00bb }
        throw r14;	 Catch:{ Exception -> 0x00bb }
    L_0x00bb:
        r14 = move-exception;
        r15 = "Facebook-publish";
        com.facebook.internal.Utility.logd(r15, r14);
        r15 = new com.facebook.GraphResponse;
        r1 = new com.facebook.FacebookRequestError;
        r1.<init>(r0, r14);
        r15.<init>(r0, r0, r1);
        return r15;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.FacebookSdk.publishInstallAndWaitForResponse(android.content.Context, java.lang.String):com.facebook.GraphResponse");
    }

    public static boolean getLimitEventAndDataUsage(Context context) {
        Validate.sdkInitialized();
        return context.getSharedPreferences(AppEventsLogger.APP_EVENT_PREFERENCES, 0).getBoolean("limitEventUsage", false);
    }

    public static void setLimitEventAndDataUsage(Context context, boolean z) {
        context.getSharedPreferences(AppEventsLogger.APP_EVENT_PREFERENCES, 0).edit().putBoolean("limitEventUsage", z).apply();
    }

    public static long getOnProgressThreshold() {
        Validate.sdkInitialized();
        return onProgressThreshold.get();
    }

    public static void setOnProgressThreshold(long j) {
        onProgressThreshold.set(j);
    }

    static void loadDefaultsFromMetadata(Context context) {
        if (context != null) {
            try {
                ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
                if (applicationInfo != null && applicationInfo.metaData != null) {
                    if (applicationId == null) {
                        Object obj = applicationInfo.metaData.get("com.facebook.sdk.ApplicationId");
                        if (obj instanceof String) {
                            String str = (String) obj;
                            if (str.toLowerCase(Locale.ROOT).startsWith(LoginManager.TAG_SUBTYPE_FB)) {
                                applicationId = str.substring(2);
                            } else {
                                applicationId = str;
                            }
                        } else if (obj instanceof Integer) {
                            throw new FacebookException("App Ids cannot be directly placed in the manifest.They must be prefixed by 'fb' or be placed in the string resource file.");
                        }
                    }
                    if (applicationName == null) {
                        applicationName = applicationInfo.metaData.getString(APPLICATION_NAME_PROPERTY);
                    }
                    if (appClientToken == null) {
                        appClientToken = applicationInfo.metaData.getString(CLIENT_TOKEN_PROPERTY);
                    }
                    if (webDialogTheme == 0) {
                        setWebDialogTheme(applicationInfo.metaData.getInt(WEB_DIALOG_THEME));
                    }
                    if (callbackRequestCodeOffset == DEFAULT_CALLBACK_REQUEST_CODE_OFFSET) {
                        callbackRequestCodeOffset = applicationInfo.metaData.getInt(CALLBACK_OFFSET_PROPERTY, DEFAULT_CALLBACK_REQUEST_CODE_OFFSET);
                    }
                    if (autoLogAppEventsEnabled == null) {
                        autoLogAppEventsEnabled = Boolean.valueOf(applicationInfo.metaData.getBoolean(AUTO_LOG_APP_EVENTS_ENABLED_PROPERTY, true));
                    }
                }
            } catch (NameNotFoundException unused) {
            }
        }
    }

    public static String getApplicationSignature(Context context) {
        Validate.sdkInitialized();
        if (context == null) {
            return null;
        }
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return null;
        }
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 64);
            Signature[] signatureArr = packageInfo.signatures;
            if (signatureArr == null || signatureArr.length == 0) {
                return null;
            }
            try {
                MessageDigest instance = MessageDigest.getInstance("SHA-1");
                instance.update(packageInfo.signatures[0].toByteArray());
                return Base64.encodeToString(instance.digest(), 9);
            } catch (NoSuchAlgorithmException unused) {
                return null;
            }
        } catch (NameNotFoundException unused2) {
            return null;
        }
    }

    public static String getApplicationId() {
        Validate.sdkInitialized();
        return applicationId;
    }

    public static void setApplicationId(String str) {
        applicationId = str;
    }

    public static String getApplicationName() {
        Validate.sdkInitialized();
        return applicationName;
    }

    public static void setApplicationName(String str) {
        applicationName = str;
    }

    public static String getClientToken() {
        Validate.sdkInitialized();
        return appClientToken;
    }

    public static void setClientToken(String str) {
        appClientToken = str;
    }

    public static int getWebDialogTheme() {
        Validate.sdkInitialized();
        return webDialogTheme;
    }

    public static void setWebDialogTheme(int i) {
        if (i == 0) {
            i = DEFAULT_THEME;
        }
        webDialogTheme = i;
    }

    public static boolean getAutoLogAppEventsEnabled() {
        Validate.sdkInitialized();
        return autoLogAppEventsEnabled.booleanValue();
    }

    public static void setAutoLogAppEventsEnabled(boolean z) {
        autoLogAppEventsEnabled = Boolean.valueOf(z);
    }

    public static File getCacheDir() {
        Validate.sdkInitialized();
        return (File) cacheDir.getValue();
    }

    public static void setCacheDir(File file) {
        cacheDir = new LockOnGetVariable((Object) file);
    }

    public static int getCallbackRequestCodeOffset() {
        Validate.sdkInitialized();
        return callbackRequestCodeOffset;
    }

    public static boolean isFacebookRequestCode(int i) {
        return i >= callbackRequestCodeOffset && i < callbackRequestCodeOffset + 100;
    }
}
