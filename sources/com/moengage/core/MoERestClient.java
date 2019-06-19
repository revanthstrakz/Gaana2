package com.moengage.core;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.facebook.internal.ServerProtocol;
import com.google.api.client.http.HttpMethods;
import com.moe.pushlibrary.exceptions.SDKNotInitializedException;
import com.moe.pushlibrary.utils.MoEHelperConstants;
import com.moengage.core.AdvertisingIdClient.AdInfo;
import com.moengage.push.PushManager;
import com.til.colombia.android.internal.e;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TimeZone;
import javax.net.ssl.HttpsURLConnection;

final class MoERestClient {
    private static String ANDROID_ID = null;
    private static final String ENCODING_CHARSET_UTF8 = "UTF-8";
    private static final String SCHEME_HTTPS = "https://";
    private static final String URL_PARAM_ASSIGNER = "=";
    private static final String URL_PARAM_SEPARATOR = "&";
    private static final String URL_QUERY_PARAM_SEPARATOR = "?";
    private static boolean androidIDRetrieved;
    private String errorResponse;
    private byte[] mByteArray = null;
    private String mStringBody = null;
    private HashMap<String, String> params;
    private String response;
    private int responseCode;
    private String url;

    public enum API_VERSION {
        V1,
        V2
    }

    public enum RequestMethod {
        GET,
        POST
    }

    public String getResponse() {
        return this.response;
    }

    /* Access modifiers changed, original: 0000 */
    public int getResponseCode() {
        return this.responseCode;
    }

    MoERestClient(String str, Context context, API_VERSION api_version) throws SDKNotInitializedException {
        this.url = str;
        this.params = new HashMap();
        if (!androidIDRetrieved) {
            androidIDRetrieved = true;
            ANDROID_ID = MoEUtils.getAndroidID(context);
        }
        if (API_VERSION.V1 == api_version) {
            initializeRestClientV1(context);
        } else {
            initializeRestClientV2(context);
        }
    }

    private void initializeRestClientV1(Context context) throws SDKNotInitializedException {
        this.params.put("os_value", "ANDROID");
        ConfigurationProvider instance = ConfigurationProvider.getInstance(context);
        String gCMToken = instance.getGCMToken();
        String appId = instance.getAppId();
        String currentUserId = instance.getCurrentUserId();
        appId = MoEUtils.addDebugIfRequired(context, appId);
        if (!TextUtils.isEmpty(gCMToken)) {
            addParam("gcmId", gCMToken);
        }
        if (TextUtils.isEmpty(appId)) {
            gCMToken = instance.getAppId();
            if (TextUtils.isEmpty(gCMToken)) {
                throw new SDKNotInitializedException("APP ID has not been set");
            }
            addParam("appId", gCMToken);
        } else {
            addParam("appId", appId);
        }
        if (!TextUtils.isEmpty(currentUserId)) {
            addParam("unique_id", currentUserId);
        }
        addParam(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, Integer.toString(instance.getAppVersion()));
        addParam("libVersion", Integer.toString(MoEHelperConstants.LIB_VERSION));
        String networkType = MoEUtils.getNetworkType(context);
        if (!TextUtils.isEmpty(networkType)) {
            addParam("networkType", networkType);
        }
        addBaiduParamIfRequired();
        addSegmentParamIfRequired(context);
    }

    private void initializeRestClientV2(Context context) throws SDKNotInitializedException {
        ConfigurationProvider instance = ConfigurationProvider.getInstance(context);
        String gCMToken = instance.getGCMToken();
        String appId = instance.getAppId();
        String currentUserId = instance.getCurrentUserId();
        String num = Integer.toString(instance.getAppVersion());
        if (!instance.isAdIdCollectionProhibitted()) {
            CharSequence storedGAID = instance.getStoredGAID();
            if (TextUtils.isEmpty(storedGAID)) {
                AdInfo advertisementInfo = MoEUtils.getAdvertisementInfo(context);
                if (advertisementInfo != null) {
                    storedGAID = advertisementInfo.getId();
                    instance.storeGAID(storedGAID);
                }
            }
            if (!TextUtils.isEmpty(storedGAID)) {
                addParam("moe_gaid", storedGAID);
            }
        }
        long currentTimeMillis = System.currentTimeMillis();
        appId = MoEUtils.addDebugIfRequired(context, appId);
        if (!TextUtils.isEmpty(gCMToken)) {
            addParam("push_id", gCMToken);
        }
        if (TextUtils.isEmpty(appId)) {
            gCMToken = instance.getAppId();
            if (TextUtils.isEmpty(gCMToken)) {
                throw new SDKNotInitializedException("APP ID has not been set");
            }
            addParam("app_id", gCMToken);
        } else {
            addParam("app_id", appId);
        }
        if (!TextUtils.isEmpty(currentUserId)) {
            addParam("unique_id", currentUserId);
        }
        if (!TextUtils.isEmpty(num)) {
            addParam("app_ver", num);
        }
        if (!TextUtils.isEmpty(ANDROID_ID)) {
            addParam("android_id", ANDROID_ID);
        }
        addParam(e.C, "ANDROID");
        addParam("sdk_ver", Integer.toString(MoEHelperConstants.LIB_VERSION));
        addParam("device_tz", TimeZone.getDefault().getID());
        addParam("device_tz_offset", String.valueOf(TimeZone.getDefault().getOffset(currentTimeMillis)));
        addParam("device_ts", String.valueOf(currentTimeMillis));
        addParam("os_ver", String.valueOf(VERSION.SDK_INT));
        addParam("model", Build.MODEL);
        gCMToken = MoEUtils.getNetworkType(context);
        if (!TextUtils.isEmpty(gCMToken)) {
            addParam("networkType", gCMToken);
        }
        addParam("app_version_name", instance.getAppVersionName());
        addPlatformIfRequired(instance);
        addBaiduParamIfRequired();
        addSegmentParamIfRequired(context);
    }

    private void addParam(String str, String str2) {
        this.params.put(str, str2);
    }

    /* Access modifiers changed, original: 0000 */
    public void addParam(HashMap<String, String> hashMap) {
        for (Entry entry : hashMap.entrySet()) {
            this.params.put(entry.getKey(), entry.getValue());
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void addBody(String str) {
        this.mStringBody = str;
    }

    private String convertStreamToString(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    stringBuilder.append(readLine);
                } else {
                    try {
                        break;
                    } catch (IOException e) {
                        Logger.f("MoERestClient:executeRequest: IOException", e);
                    } catch (Exception e2) {
                        Logger.f("MoERestClient:executeRequest: Exception", e2);
                    }
                }
            } catch (IOException e3) {
                Logger.f("MoERestClient:executeRequest: IOException", e3);
                inputStream.close();
            } catch (Exception e4) {
                Logger.f("MoERestClient:executeRequest: Exception", e4);
                inputStream.close();
            } catch (Throwable th) {
                try {
                    inputStream.close();
                } catch (IOException e5) {
                    Logger.f("MoERestClient:executeRequest: IOException", e5);
                } catch (Exception e22) {
                    Logger.f("MoERestClient:executeRequest: Exception", e22);
                }
                throw th;
            }
        }
        inputStream.close();
        return stringBuilder.toString();
    }

    private String getFinalURI(String str) {
        StringBuilder stringBuilder = new StringBuilder(str);
        if (!this.params.isEmpty()) {
            stringBuilder.append(URL_QUERY_PARAM_SEPARATOR);
            int size = this.params.size();
            int i = 0;
            for (Entry entry : this.params.entrySet()) {
                try {
                    stringBuilder.append((String) entry.getKey());
                    stringBuilder.append(URL_PARAM_ASSIGNER);
                    stringBuilder.append(URLEncoder.encode((String) entry.getValue(), "UTF-8"));
                    if (i <= size - 2) {
                        stringBuilder.append(URL_PARAM_SEPARATOR);
                    }
                    i++;
                } catch (Exception e) {
                    Logger.f("MoERestClient: getFinalURI ", e);
                }
            }
        }
        return stringBuilder.toString();
    }

    public void execute(RequestMethod requestMethod) throws IOException {
        HttpURLConnection httpURLConnection;
        URL url = new URL(getFinalURI(this.url));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("MoERestClient: executing API: ");
        stringBuilder.append(url.toString());
        Logger.v(stringBuilder.toString());
        if (this.url.startsWith(SCHEME_HTTPS)) {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) null;
            httpURLConnection = (HttpsURLConnection) url.openConnection();
        } else {
            httpURLConnection = (HttpURLConnection) url.openConnection();
        }
        if (requestMethod == RequestMethod.POST) {
            addBody(httpURLConnection);
        } else {
            httpURLConnection.setRequestMethod(HttpMethods.GET);
        }
        this.responseCode = httpURLConnection.getResponseCode();
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("MoERestClient: ResponseCode: ");
        stringBuilder2.append(this.responseCode);
        Logger.v(stringBuilder2.toString());
        if (200 != this.responseCode) {
            this.errorResponse = convertStreamToString(httpURLConnection.getErrorStream());
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("MoERestClient: Response: API Failed: ");
            stringBuilder2.append(this.url);
            stringBuilder2.append(" response code :");
            stringBuilder2.append(this.responseCode);
            stringBuilder2.append("reason : ");
            stringBuilder2.append(this.errorResponse);
            Logger.f(stringBuilder2.toString());
            if (!TextUtils.isEmpty(this.errorResponse)) {
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("MoERestClient: with reason: ");
                stringBuilder2.append(this.errorResponse);
                Logger.f(stringBuilder2.toString());
            }
            return;
        }
        this.response = convertStreamToString(httpURLConnection.getInputStream());
        httpURLConnection.disconnect();
        if (!TextUtils.isEmpty(this.response)) {
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("MoERestClient: Response: ");
            stringBuilder2.append(this.response);
            Logger.v(stringBuilder2.toString());
        }
    }

    private void addBody(HttpURLConnection httpURLConnection) throws IOException {
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestProperty("Accept-Charset", "UTF-8");
        httpURLConnection.setRequestProperty("Content-type", "application/json");
        OutputStream outputStream = httpURLConnection.getOutputStream();
        StringBuilder stringBuilder;
        if (this.mStringBody != null) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("MoERestClient: addBody: string: ");
            stringBuilder.append(this.mStringBody);
            Logger.d(stringBuilder.toString());
            outputStream.write(this.mStringBody.getBytes("UTF-8"));
        } else if (this.mByteArray != null) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("MoERestClient: addBody: bytes: ");
            stringBuilder.append(this.mByteArray.toString());
            Logger.d(stringBuilder.toString());
            outputStream.write(this.mByteArray);
        }
        outputStream.close();
    }

    private void addPlatformIfRequired(ConfigurationProvider configurationProvider) {
        String unityVersion = configurationProvider.getUnityVersion();
        if (!TextUtils.isEmpty(unityVersion)) {
            addParam("unity_ver", unityVersion);
        }
    }

    private void addBaiduParamIfRequired() {
        if (PushManager.getInstance().isIsBaiduEnabled()) {
            addParam("moe_push_ser", "baidu");
        } else {
            addParam("moe_push_ser", "android");
        }
    }

    private void addSegmentParamIfRequired(Context context) {
        if (ConfigurationProvider.getInstance(context).isSegmentEnabled()) {
            addParam("integration_type", "segment");
        }
    }
}
