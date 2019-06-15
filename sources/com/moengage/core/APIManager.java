package com.moengage.core;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.api.client.http.HttpStatusCodes;
import com.moe.pushlibrary.exceptions.SDKNotInitializedException;
import com.moe.pushlibrary.utils.MoEHelperUtils;
import com.moengage.core.MoERestClient.API_VERSION;
import com.moengage.core.MoERestClient.RequestMethod;
import com.moengage.inapp.InAppController;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.HashMap;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONObject;

public final class APIManager {
    private APIManager() {
    }

    static boolean addDevice(Context context) {
        Logger.v("APIManager:Sending GCM Client ID to server");
        try {
            if (!ConfigurationProvider.getInstance(context).isAppEnabled()) {
                return false;
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(MoEUtils.getAPIRoute(context));
            stringBuilder.append("/v2/device/add");
            MoERestClient moERestClient = new MoERestClient(stringBuilder.toString(), context, API_VERSION.V2);
            JSONObject deviceInfo = MoEUtils.deviceInfo(context);
            if (deviceInfo != null) {
                moERestClient.addBody(deviceInfo.toString());
            }
            moERestClient.execute(RequestMethod.POST);
            return MoEParser.isHttpStatusOk(moERestClient.getResponseCode());
        } catch (UnsupportedEncodingException e) {
            Logger.f("APIManager:registerDevice", e);
            return false;
        } catch (IOException e2) {
            Logger.f("APIManager:registerDevice", e2);
            return false;
        } catch (SDKNotInitializedException e3) {
            Logger.f("APIManager:registerDevice", e3);
            return false;
        } catch (Exception e4) {
            Logger.f("APIManager:registerDevice", e4);
            return false;
        }
    }

    static boolean sendInteractionReport(Context context, String str, String str2) {
        Logger.v("APIManager:Sending interaction report ");
        try {
            if (!ConfigurationProvider.getInstance(context).isAppEnabled()) {
                return false;
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(MoEUtils.getAPIRoute(context));
            stringBuilder.append(str2);
            MoERestClient moERestClient = new MoERestClient(stringBuilder.toString(), context, API_VERSION.V2);
            moERestClient.addBody(str);
            moERestClient.execute(RequestMethod.POST);
            return MoEParser.parseReportAddResponse(moERestClient.getResponseCode());
        } catch (UnsupportedEncodingException e) {
            Logger.f("APIManager: sendInteractionReport: UnsupportedEncodingException", e);
            return false;
        } catch (IOException e2) {
            Logger.f("APIManager: sendInteractionReport: IOException", e2);
            return false;
        } catch (SDKNotInitializedException e3) {
            Logger.f("APIManager: sendInteractionReport", e3);
            return false;
        } catch (Exception e4) {
            Logger.f("APIManager: sendInteractionReport", e4);
            return false;
        }
    }

    @Nullable
    static String getGeoFences(Context context, String str, HashMap<String, String> hashMap) {
        Logger.v("APIManager: getGeoFences: Get geo fences");
        if (MoEHelperUtils.hasPermission(context, "android.permission.ACCESS_FINE_LOCATION") || MoEHelperUtils.hasPermission(context, "android.permission.ACCESS_COARSE_LOCATION")) {
            try {
                if (ConfigurationProvider.getInstance(context).isAppEnabled()) {
                    if (ConfigurationProvider.getInstance(context).isGeoEnabled()) {
                        MoERestClient moERestClient = new MoERestClient(str, context, API_VERSION.V1);
                        moERestClient.addParam(hashMap);
                        moERestClient.execute(RequestMethod.GET);
                        if (MoEParser.isHttpStatusOk(moERestClient.getResponseCode()) && MoEParser.isValidAPIResponse(moERestClient.getResponse(), API_VERSION.V1)) {
                            return moERestClient.getResponse();
                        }
                    }
                }
                return null;
            } catch (UnsupportedEncodingException e) {
                Logger.f("APIManager: getGeoFences", e);
            } catch (Exception e2) {
                Logger.f("APIManager: getGeoFences", e2);
            }
        }
        return null;
    }

    static void geoFenceHit(Context context, String str, HashMap<String, String> hashMap) {
        Logger.v("APIManager:Registering a Geofence hit");
        try {
            if (ConfigurationProvider.getInstance(context).isAppEnabled()) {
                if (ConfigurationProvider.getInstance(context).isGeoEnabled()) {
                    MoERestClient moERestClient = new MoERestClient(str, context, API_VERSION.V1);
                    moERestClient.addParam(hashMap);
                    moERestClient.execute(RequestMethod.GET);
                }
            }
        } catch (UnsupportedEncodingException e) {
            Logger.f("APIManager: geoFenceHit", e);
        } catch (IOException e2) {
            Logger.f("APIManager: geoFenceHit", e2);
        } catch (SDKNotInitializedException e3) {
            Logger.f("APIManager: geoFenceHit", e3);
        } catch (Exception e4) {
            Logger.f("APIManager: geoFenceHit", e4);
        }
    }

    @Nullable
    static String fetchInAppCampaigns(Context context, String str, HashMap<String, String> hashMap, String str2) {
        try {
            if (ConfigurationProvider.getInstance(context).isAppEnabled()) {
                if (ConfigurationProvider.getInstance(context).isInAppEnabled()) {
                    MoERestClient moERestClient = new MoERestClient(str, context, API_VERSION.V2);
                    moERestClient.addParam(hashMap);
                    moERestClient.addBody(str2);
                    moERestClient.execute(RequestMethod.POST);
                    Logger.v("APIManager: Processing InApp Response - will parse and save data");
                    if (MoEParser.isHttpStatusOk(moERestClient.getResponseCode())) {
                        ConfigurationProvider.getInstance(context).setLastInappUpdateTime(System.currentTimeMillis());
                        if (!TextUtils.isEmpty(moERestClient.getResponse())) {
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("APIManager: fetchInAppCampaingn");
                            stringBuilder.append(moERestClient.getResponse());
                            Logger.v(stringBuilder.toString());
                            return moERestClient.getResponse();
                        }
                        return null;
                    }
                    InAppController.getInstance().trackAPIFailure(InAppController.SYNC_API_FAILURE);
                    return null;
                }
            }
            return null;
        } catch (Exception e) {
            Logger.f("APIManager: fetchInAppCampaigns", e);
            InAppController.getInstance().trackAPIFailure(InAppController.SYNC_API_FAILURE);
        }
    }

    @Nullable
    static String logASmartEvent(Context context, String str, HashMap<String, String> hashMap, String str2) {
        try {
            if (ConfigurationProvider.getInstance(context).isAppEnabled()) {
                if (ConfigurationProvider.getInstance(context).isInAppEnabled()) {
                    MoERestClient moERestClient = new MoERestClient(str, context, API_VERSION.V2);
                    moERestClient.addParam(hashMap);
                    moERestClient.addBody(str2);
                    moERestClient.execute(RequestMethod.POST);
                    Logger.v("APIManager: Processing Smart event response");
                    if (MoEParser.isHttpStatusOk(moERestClient.getResponseCode())) {
                        return moERestClient.getResponse();
                    }
                    InAppController.getInstance().trackAPIFailure(InAppController.SMART_API_FAILURE);
                    return null;
                }
            }
            return null;
        } catch (Exception e) {
            Logger.f("APIManager: logASmartEvent", e);
            InAppController.getInstance().trackAPIFailure(InAppController.SMART_API_FAILURE);
            return null;
        }
    }

    @Nullable
    static String fetchSingleInApp(Context context, String str, HashMap<String, String> hashMap) {
        try {
            if (ConfigurationProvider.getInstance(context).isAppEnabled()) {
                if (ConfigurationProvider.getInstance(context).isInAppEnabled()) {
                    MoERestClient moERestClient = new MoERestClient(str, context, API_VERSION.V2);
                    moERestClient.addParam(hashMap);
                    moERestClient.execute(RequestMethod.POST);
                    if (MoEParser.isHttpStatusOk(moERestClient.getResponseCode())) {
                        return moERestClient.getResponse();
                    }
                    InAppController.getInstance().trackAPIFailure(InAppController.SINGLE_API_FAILURE);
                    return null;
                }
            }
            return null;
        } catch (Exception e) {
            Logger.f("APIManager: fetchInAppCampaigns", e);
            InAppController.getInstance().trackAPIFailure(InAppController.SINGLE_API_FAILURE);
            return null;
        }
    }

    static boolean uploadLogsToLogEntries(Context context, String str, JSONObject jSONObject) {
        try {
            if (ConfigurationProvider.getInstance(context).isAppEnabled()) {
                if (ConfigurationProvider.getInstance(context).isLogEntryEnabled()) {
                    String jSONObject2 = jSONObject.toString();
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("API Manager : uploadLogsToLogEntries : URI ");
                    stringBuilder.append(str);
                    Logger.v(stringBuilder.toString());
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("API Manager : uploadLogsToLogEntries : request ");
                    stringBuilder.append(jSONObject2);
                    Logger.v(stringBuilder.toString());
                    if (TextUtils.isEmpty(jSONObject2)) {
                        return false;
                    }
                    HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(str).openConnection();
                    boolean z = true;
                    httpsURLConnection.setDoOutput(true);
                    httpsURLConnection.setRequestProperty("Accept-Charset", "UTF-8");
                    httpsURLConnection.setRequestProperty("Content-type", "application/json");
                    OutputStream outputStream = httpsURLConnection.getOutputStream();
                    outputStream.write(jSONObject2.getBytes("UTF-8"));
                    outputStream.close();
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("API Manager : uploadLogsToLogEntries : response");
                    stringBuilder2.append(httpsURLConnection.getResponseCode());
                    Logger.v(stringBuilder2.toString());
                    if (httpsURLConnection.getResponseCode() != HttpStatusCodes.STATUS_CODE_NO_CONTENT) {
                        z = false;
                    }
                    return z;
                }
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    @Nullable
    static String syncConfig(Context context, String str, String str2) {
        try {
            MoERestClient moERestClient = new MoERestClient(str, context, API_VERSION.V2);
            if (!TextUtils.isEmpty(str2)) {
                moERestClient.addBody(str2);
            }
            moERestClient.execute(RequestMethod.POST);
            if (!MoEParser.isHttpStatusOk(moERestClient.getResponseCode())) {
                return null;
            }
            ConfigurationProvider.getInstance(context).setLastConfigSyncTime(System.currentTimeMillis());
            return moERestClient.getResponse();
        } catch (Exception e) {
            Logger.f("API Manager : syncConfig exception", e);
            return null;
        }
    }

    @Nullable
    static String fetchMessages(Context context, String str, HashMap<String, String> hashMap, String str2) {
        try {
            if (ConfigurationProvider.getInstance(context).isAppEnabled()) {
                if (ConfigurationProvider.getInstance(context).isInboxEnabled()) {
                    MoERestClient moERestClient = new MoERestClient(str, context, API_VERSION.V2);
                    moERestClient.addParam(hashMap);
                    moERestClient.addBody(str2);
                    moERestClient.execute(RequestMethod.GET);
                    if (MoEParser.isHttpStatusOk(moERestClient.getResponseCode())) {
                        return moERestClient.getResponse();
                    }
                    return null;
                }
            }
            return null;
        } catch (Exception e) {
            Logger.f("API Manager : fetchMessages exception", e);
            return null;
        }
    }

    static boolean registerUnregisterDeviceForIntegrationVerification(Context context, String str, HashMap<String, String> hashMap) {
        try {
            if (!ConfigurationProvider.getInstance(context).isAppEnabled()) {
                return false;
            }
            MoERestClient moERestClient = new MoERestClient(str, context, API_VERSION.V2);
            if (hashMap != null) {
                moERestClient.addParam(hashMap);
            }
            moERestClient.execute(RequestMethod.GET);
            return MoEParser.isHttpStatusOk(moERestClient.getResponseCode());
        } catch (Exception e) {
            Logger.f("APIManager : registerUnregisterDeviceForIntegrationVerification :", e);
            return false;
        }
    }
}
