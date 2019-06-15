package com.facebook.applinks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSessionContext;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AppLinkData {
    private static final String APPLINK_BRIDGE_ARGS_KEY = "bridge_args";
    private static final String APPLINK_METHOD_ARGS_KEY = "method_args";
    private static final String APPLINK_VERSION_KEY = "version";
    public static final String ARGUMENTS_EXTRAS_KEY = "extras";
    public static final String ARGUMENTS_NATIVE_CLASS_KEY = "com.facebook.platform.APPLINK_NATIVE_CLASS";
    public static final String ARGUMENTS_NATIVE_URL = "com.facebook.platform.APPLINK_NATIVE_URL";
    public static final String ARGUMENTS_REFERER_DATA_KEY = "referer_data";
    public static final String ARGUMENTS_TAPTIME_KEY = "com.facebook.platform.APPLINK_TAP_TIME_UTC";
    private static final String BRIDGE_ARGS_METHOD_KEY = "method";
    private static final String BUNDLE_AL_APPLINK_DATA_KEY = "al_applink_data";
    static final String BUNDLE_APPLINK_ARGS_KEY = "com.facebook.platform.APPLINK_ARGS";
    private static final String DEFERRED_APP_LINK_ARGS_FIELD = "applink_args";
    private static final String DEFERRED_APP_LINK_CLASS_FIELD = "applink_class";
    private static final String DEFERRED_APP_LINK_CLICK_TIME_FIELD = "click_time";
    private static final String DEFERRED_APP_LINK_EVENT = "DEFERRED_APP_LINK";
    private static final String DEFERRED_APP_LINK_PATH = "%s/activities";
    private static final String DEFERRED_APP_LINK_URL_FIELD = "applink_url";
    private static final String EXTRAS_DEEPLINK_CONTEXT_KEY = "deeplink_context";
    private static final String METHOD_ARGS_REF_KEY = "ref";
    private static final String METHOD_ARGS_TARGET_URL_KEY = "target_url";
    private static final String PROMOTION_CODE_KEY = "promo_code";
    private static final String REFERER_DATA_REF_KEY = "fb_ref";
    private static final String TAG = AppLinkData.class.getCanonicalName();
    private Bundle argumentBundle;
    private JSONObject arguments;
    private String promotionCode;
    private String ref;
    private Uri targetUri;

    public interface CompletionHandler {
        void onDeferredAppLinkDataFetched(AppLinkData appLinkData);
    }

    public static void fetchDeferredAppLinkData(Context context, CompletionHandler completionHandler) {
        fetchDeferredAppLinkData(context, null, completionHandler);
    }

    public static void fetchDeferredAppLinkData(Context context, String str, final CompletionHandler completionHandler) {
        Object str2;
        Validate.notNull(context, "context");
        Validate.notNull(completionHandler, "completionHandler");
        if (str2 == null) {
            str2 = Utility.getMetadataApplicationId(context);
        }
        Validate.notNull(str2, "applicationId");
        context = context.getApplicationContext();
        FacebookSdk.getExecutor().execute(new Runnable() {
            public void run() {
                AppLinkData.fetchDeferredAppLinkFromServer(context, str2, completionHandler);
            }
        });
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x008b */
    /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x00ab */
    /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x00cb */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Can't wrap try/catch for region: R(2:21|22) */
    /* JADX WARNING: Missing block: B:22:?, code skipped:
            android.util.Log.d(TAG, "Unable to put tap time in AppLinkData.arguments");
     */
    /* JADX WARNING: Missing block: B:32:?, code skipped:
            android.util.Log.d(TAG, "Unable to put tap time in AppLinkData.arguments");
     */
    /* JADX WARNING: Missing block: B:42:?, code skipped:
            android.util.Log.d(TAG, "Unable to put tap time in AppLinkData.arguments");
     */
    private static void fetchDeferredAppLinkFromServer(android.content.Context r7, java.lang.String r8, com.facebook.applinks.AppLinkData.CompletionHandler r9) {
        /*
        r0 = new org.json.JSONObject;
        r0.<init>();
        r1 = "event";
        r2 = "DEFERRED_APP_LINK";
        r0.put(r1, r2);	 Catch:{ JSONException -> 0x00df }
        r1 = com.facebook.internal.AttributionIdentifiers.getAttributionIdentifiers(r7);	 Catch:{ JSONException -> 0x00df }
        r2 = com.facebook.appevents.AppEventsLogger.getAnonymousAppDeviceGUID(r7);	 Catch:{ JSONException -> 0x00df }
        r3 = com.facebook.FacebookSdk.getLimitEventAndDataUsage(r7);	 Catch:{ JSONException -> 0x00df }
        com.facebook.internal.Utility.setAppEventAttributionParameters(r0, r1, r2, r3);	 Catch:{ JSONException -> 0x00df }
        r1 = com.facebook.FacebookSdk.getApplicationContext();	 Catch:{ JSONException -> 0x00df }
        com.facebook.internal.Utility.setAppEventExtendedDeviceInfoParameters(r0, r1);	 Catch:{ JSONException -> 0x00df }
        r1 = "application_package_name";
        r7 = r7.getPackageName();	 Catch:{ JSONException -> 0x00df }
        r0.put(r1, r7);	 Catch:{ JSONException -> 0x00df }
        r7 = "%s/activities";
        r1 = 1;
        r1 = new java.lang.Object[r1];
        r2 = 0;
        r1[r2] = r8;
        r7 = java.lang.String.format(r7, r1);
        r8 = 0;
        r7 = com.facebook.GraphRequest.newPostRequest(r8, r7, r0, r8);	 Catch:{ Exception -> 0x00d4 }
        r7 = r7.executeAndWait();	 Catch:{ Exception -> 0x00d4 }
        r7 = r7.getJSONObject();	 Catch:{ Exception -> 0x00d4 }
        if (r7 == 0) goto L_0x00db;
    L_0x0046:
        r0 = "applink_args";
        r0 = r7.optString(r0);	 Catch:{ Exception -> 0x00d4 }
        r1 = "click_time";
        r2 = -1;
        r4 = r7.optLong(r1, r2);	 Catch:{ Exception -> 0x00d4 }
        r1 = "applink_class";
        r1 = r7.optString(r1);	 Catch:{ Exception -> 0x00d4 }
        r6 = "applink_url";
        r7 = r7.optString(r6);	 Catch:{ Exception -> 0x00d4 }
        r6 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x00d4 }
        if (r6 != 0) goto L_0x00db;
    L_0x0066:
        r0 = createFromJson(r0);	 Catch:{ Exception -> 0x00d4 }
        r8 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1));
        if (r8 == 0) goto L_0x0092;
    L_0x006e:
        r8 = r0.arguments;	 Catch:{ JSONException -> 0x008b }
        if (r8 == 0) goto L_0x0079;
    L_0x0072:
        r8 = r0.arguments;	 Catch:{ JSONException -> 0x008b }
        r2 = "com.facebook.platform.APPLINK_TAP_TIME_UTC";
        r8.put(r2, r4);	 Catch:{ JSONException -> 0x008b }
    L_0x0079:
        r8 = r0.argumentBundle;	 Catch:{ JSONException -> 0x008b }
        if (r8 == 0) goto L_0x0092;
    L_0x007d:
        r8 = r0.argumentBundle;	 Catch:{ JSONException -> 0x008b }
        r2 = "com.facebook.platform.APPLINK_TAP_TIME_UTC";
        r3 = java.lang.Long.toString(r4);	 Catch:{ JSONException -> 0x008b }
        r8.putString(r2, r3);	 Catch:{ JSONException -> 0x008b }
        goto L_0x0092;
    L_0x0089:
        r8 = r0;
        goto L_0x00d4;
    L_0x008b:
        r8 = TAG;	 Catch:{ Exception -> 0x0089 }
        r2 = "Unable to put tap time in AppLinkData.arguments";
        android.util.Log.d(r8, r2);	 Catch:{ Exception -> 0x0089 }
    L_0x0092:
        if (r1 == 0) goto L_0x00b2;
    L_0x0094:
        r8 = r0.arguments;	 Catch:{ JSONException -> 0x00ab }
        if (r8 == 0) goto L_0x009f;
    L_0x0098:
        r8 = r0.arguments;	 Catch:{ JSONException -> 0x00ab }
        r2 = "com.facebook.platform.APPLINK_NATIVE_CLASS";
        r8.put(r2, r1);	 Catch:{ JSONException -> 0x00ab }
    L_0x009f:
        r8 = r0.argumentBundle;	 Catch:{ JSONException -> 0x00ab }
        if (r8 == 0) goto L_0x00b2;
    L_0x00a3:
        r8 = r0.argumentBundle;	 Catch:{ JSONException -> 0x00ab }
        r2 = "com.facebook.platform.APPLINK_NATIVE_CLASS";
        r8.putString(r2, r1);	 Catch:{ JSONException -> 0x00ab }
        goto L_0x00b2;
    L_0x00ab:
        r8 = TAG;	 Catch:{ Exception -> 0x0089 }
        r1 = "Unable to put tap time in AppLinkData.arguments";
        android.util.Log.d(r8, r1);	 Catch:{ Exception -> 0x0089 }
    L_0x00b2:
        if (r7 == 0) goto L_0x00d2;
    L_0x00b4:
        r8 = r0.arguments;	 Catch:{ JSONException -> 0x00cb }
        if (r8 == 0) goto L_0x00bf;
    L_0x00b8:
        r8 = r0.arguments;	 Catch:{ JSONException -> 0x00cb }
        r1 = "com.facebook.platform.APPLINK_NATIVE_URL";
        r8.put(r1, r7);	 Catch:{ JSONException -> 0x00cb }
    L_0x00bf:
        r8 = r0.argumentBundle;	 Catch:{ JSONException -> 0x00cb }
        if (r8 == 0) goto L_0x00d2;
    L_0x00c3:
        r8 = r0.argumentBundle;	 Catch:{ JSONException -> 0x00cb }
        r1 = "com.facebook.platform.APPLINK_NATIVE_URL";
        r8.putString(r1, r7);	 Catch:{ JSONException -> 0x00cb }
        goto L_0x00d2;
    L_0x00cb:
        r7 = TAG;	 Catch:{ Exception -> 0x0089 }
        r8 = "Unable to put tap time in AppLinkData.arguments";
        android.util.Log.d(r7, r8);	 Catch:{ Exception -> 0x0089 }
    L_0x00d2:
        r8 = r0;
        goto L_0x00db;
    L_0x00d4:
        r7 = TAG;
        r0 = "Unable to fetch deferred applink from server";
        com.facebook.internal.Utility.logd(r7, r0);
    L_0x00db:
        r9.onDeferredAppLinkDataFetched(r8);
        return;
    L_0x00df:
        r7 = move-exception;
        r8 = new com.facebook.FacebookException;
        r9 = "An error occurred while preparing deferred app link";
        r8.<init>(r9, r7);
        throw r8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.applinks.AppLinkData.fetchDeferredAppLinkFromServer(android.content.Context, java.lang.String, com.facebook.applinks.AppLinkData$CompletionHandler):void");
    }

    public static AppLinkData createFromActivity(Activity activity) {
        Validate.notNull(activity, "activity");
        Intent intent = activity.getIntent();
        if (intent == null) {
            return null;
        }
        AppLinkData createFromAlApplinkData = createFromAlApplinkData(intent);
        if (createFromAlApplinkData == null) {
            createFromAlApplinkData = createFromJson(intent.getStringExtra(BUNDLE_APPLINK_ARGS_KEY));
        }
        if (createFromAlApplinkData == null) {
            createFromAlApplinkData = createFromUri(intent.getData());
        }
        return createFromAlApplinkData;
    }

    public static AppLinkData createFromAlApplinkData(Intent intent) {
        if (intent == null) {
            return null;
        }
        Bundle bundleExtra = intent.getBundleExtra(BUNDLE_AL_APPLINK_DATA_KEY);
        if (bundleExtra == null) {
            return null;
        }
        String string;
        AppLinkData appLinkData = new AppLinkData();
        appLinkData.targetUri = intent.getData();
        if (appLinkData.targetUri == null) {
            string = bundleExtra.getString(METHOD_ARGS_TARGET_URL_KEY);
            if (string != null) {
                appLinkData.targetUri = Uri.parse(string);
            }
        }
        appLinkData.argumentBundle = bundleExtra;
        appLinkData.arguments = null;
        Bundle bundle = bundleExtra.getBundle(ARGUMENTS_REFERER_DATA_KEY);
        if (bundle != null) {
            appLinkData.ref = bundle.getString(REFERER_DATA_REF_KEY);
        }
        bundle = bundleExtra.getBundle("extras");
        if (bundle != null) {
            string = bundle.getString("deeplink_context");
            if (string != null) {
                try {
                    JSONObject jSONObject = new JSONObject(string);
                    if (jSONObject.has("promo_code")) {
                        appLinkData.promotionCode = jSONObject.getString("promo_code");
                    }
                } catch (JSONException e) {
                    Log.d(TAG, "Unable to parse deeplink_context JSON", e);
                }
            }
        }
        return appLinkData;
    }

    private static AppLinkData createFromJson(String str) {
        if (str == null) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            str = jSONObject.getString("version");
            if (jSONObject.getJSONObject("bridge_args").getString(BRIDGE_ARGS_METHOD_KEY).equals("applink") && str.equals(InternalAvidAdSessionContext.AVID_API_LEVEL)) {
                AppLinkData appLinkData = new AppLinkData();
                appLinkData.arguments = jSONObject.getJSONObject("method_args");
                if (appLinkData.arguments.has(METHOD_ARGS_REF_KEY)) {
                    appLinkData.ref = appLinkData.arguments.getString(METHOD_ARGS_REF_KEY);
                } else if (appLinkData.arguments.has(ARGUMENTS_REFERER_DATA_KEY)) {
                    jSONObject = appLinkData.arguments.getJSONObject(ARGUMENTS_REFERER_DATA_KEY);
                    if (jSONObject.has(REFERER_DATA_REF_KEY)) {
                        appLinkData.ref = jSONObject.getString(REFERER_DATA_REF_KEY);
                    }
                }
                if (appLinkData.arguments.has(METHOD_ARGS_TARGET_URL_KEY)) {
                    appLinkData.targetUri = Uri.parse(appLinkData.arguments.getString(METHOD_ARGS_TARGET_URL_KEY));
                }
                if (appLinkData.arguments.has("extras")) {
                    jSONObject = appLinkData.arguments.getJSONObject("extras");
                    if (jSONObject.has("deeplink_context")) {
                        jSONObject = jSONObject.getJSONObject("deeplink_context");
                        if (jSONObject.has("promo_code")) {
                            appLinkData.promotionCode = jSONObject.getString("promo_code");
                        }
                    }
                }
                appLinkData.argumentBundle = toBundle(appLinkData.arguments);
                return appLinkData;
            }
        } catch (JSONException e) {
            Log.d(TAG, "Unable to parse AppLink JSON", e);
        } catch (FacebookException e2) {
            Log.d(TAG, "Unable to parse AppLink JSON", e2);
        }
        return null;
    }

    private static AppLinkData createFromUri(Uri uri) {
        if (uri == null) {
            return null;
        }
        AppLinkData appLinkData = new AppLinkData();
        appLinkData.targetUri = uri;
        return appLinkData;
    }

    private static Bundle toBundle(JSONObject jSONObject) throws JSONException {
        Bundle bundle = new Bundle();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            Object obj = jSONObject.get(str);
            if (obj instanceof JSONObject) {
                bundle.putBundle(str, toBundle((JSONObject) obj));
            } else if (obj instanceof JSONArray) {
                JSONArray jSONArray = (JSONArray) obj;
                int i = 0;
                if (jSONArray.length() == 0) {
                    bundle.putStringArray(str, new String[0]);
                } else {
                    Object obj2 = jSONArray.get(0);
                    if (obj2 instanceof JSONObject) {
                        Bundle[] bundleArr = new Bundle[jSONArray.length()];
                        while (i < jSONArray.length()) {
                            bundleArr[i] = toBundle(jSONArray.getJSONObject(i));
                            i++;
                        }
                        bundle.putParcelableArray(str, bundleArr);
                    } else if (obj2 instanceof JSONArray) {
                        throw new FacebookException("Nested arrays are not supported.");
                    } else {
                        String[] strArr = new String[jSONArray.length()];
                        while (i < jSONArray.length()) {
                            strArr[i] = jSONArray.get(i).toString();
                            i++;
                        }
                        bundle.putStringArray(str, strArr);
                    }
                }
            } else {
                bundle.putString(str, obj.toString());
            }
        }
        return bundle;
    }

    private AppLinkData() {
    }

    public Uri getTargetUri() {
        return this.targetUri;
    }

    public String getRef() {
        return this.ref;
    }

    public String getPromotionCode() {
        return this.promotionCode;
    }

    public Bundle getArgumentBundle() {
        return this.argumentBundle;
    }

    public Bundle getRefererData() {
        return this.argumentBundle != null ? this.argumentBundle.getBundle(ARGUMENTS_REFERER_DATA_KEY) : null;
    }
}
