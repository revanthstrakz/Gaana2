package com.moengage.core;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.comscore.utils.Constants;
import com.moe.pushlibrary.MoEHelper;
import com.moe.pushlibrary.models.Event;
import com.moe.pushlibrary.models.UserAttribute;
import com.moe.pushlibrary.utils.MoEHelperUtils;
import com.moe.pushlibrary.utils.ReflectionUtils;
import com.moengage.core.AdvertisingIdClient.AdInfo;
import com.til.colombia.android.internal.e;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class MoEUtils {
    private static final String PREF_NAME = "pref_moe";

    @Nullable
    static JSONObject deviceInfo(Context context) {
        if (ConfigurationProvider.getInstance(context).isDeviceAttributesCollectionProhibited()) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("OS_VERSION", VERSION.RELEASE);
            jSONObject.put("OS_API_LEVEL", VERSION.SDK_INT);
            jSONObject.put("DEVICE", Build.DEVICE);
            jSONObject.put("MODEL", Build.MODEL);
            jSONObject.put("PRODUCT", Build.PRODUCT);
            jSONObject.put("MANUFACTURER", Build.MANUFACTURER);
            if (!ConfigurationProvider.getInstance(context).isAdIdCollectionProhibitted()) {
                AdInfo advertisementInfo = getAdvertisementInfo(context);
                if (advertisementInfo != null) {
                    jSONObject.put(MoEConstants.ATTR_MOE_GAID, advertisementInfo.getId());
                    jSONObject.put("MOE_ISLAT", advertisementInfo.isLimitAdTrackingEnabled());
                }
            }
            try {
                jSONObject.put("GOOGLE_PLAY_SERVICES_VERSION", context.getPackageManager().getPackageInfo("com.google.android.gms", 0).versionCode);
            } catch (NameNotFoundException e) {
                Logger.f("Google Play services version: ", e);
            } catch (Exception e2) {
                Logger.f("Google Play services version: ", e2);
            }
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            jSONObject.put("DENSITYDPI", displayMetrics.densityDpi);
            jSONObject.put("WIDTH", displayMetrics.widthPixels);
            jSONObject.put("HEIGHT", displayMetrics.heightPixels);
            String imei = getIMEI(context);
            if (!TextUtils.isEmpty(imei)) {
                jSONObject.put("IMEI", imei);
            }
            imei = getAndroidID(context);
            if (!TextUtils.isEmpty(imei)) {
                jSONObject.put("DEVICE_ID", imei);
            }
            String operatorName = getOperatorName(context);
            if (!TextUtils.isEmpty(operatorName)) {
                jSONObject.put("CARRIER", operatorName);
            }
        } catch (Exception e3) {
            Logger.f("MoEUtils: deviceInfo", e3);
        }
        return jSONObject;
    }

    @Nullable
    private static String getOperatorName(Context context) {
        try {
            if (!ConfigurationProvider.getInstance(context).isOperatorNameCollectionProhibited() && MoEHelperUtils.hasPermission(context, "android.permission.READ_PHONE_STATE") && hasFeature(context, "android.hardware.telephony")) {
                return ((TelephonyManager) context.getSystemService("phone")).getSimOperatorName();
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public static String convertBundletoJSONString(Bundle bundle) {
        Set<String> keySet = bundle.keySet();
        JSONObject jSONObject = new JSONObject();
        for (String str : keySet) {
            try {
                jSONObject.put(str, bundle.get(str));
            } catch (Exception e) {
                Logger.f("MoEUtils:convertBundletoJSONString", e);
            }
        }
        return jSONObject.toString();
    }

    public static void showNormalDialogWithOk(String str, Context context) {
        if (context != null) {
            Builder builder = new Builder(context);
            builder.setMessage(str).setPositiveButton(Constants.RESPONSE_MASK, new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            builder.create().show();
        }
    }

    public static void showCouponDialog(String str, final String str2, final Context context) {
        if (context != null) {
            Builder builder = new Builder(context);
            builder.setMessage(str).setPositiveButton("Copy Code", new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    MoEHelperUtils.copyCouponCodeToClipboard(context, str2);
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("coupon_code", str2);
                    } catch (Exception e) {
                        Logger.f("showCouponDialog", e);
                    }
                    MoEHelper.getInstance(context).trackEvent("EVENT_ACTION_COUPON_CODE_COPY", jSONObject);
                }
            });
            builder.create().show();
        }
    }

    @Nullable
    public static AdInfo getAdvertisementInfo(Context context) {
        try {
            return AdvertisingIdClient.getAdvertisingIdInfo(context);
        } catch (Exception unused) {
            try {
                Object invokeStatic = ReflectionUtils.invokeStatic("com.google.android.gms.ads.identifier.AdvertisingIdClient", "getAdvertisingIdInfo", new Class[]{Context.class}, new Object[]{context});
                if (invokeStatic != null) {
                    String str = (String) ReflectionUtils.invokeInstance(invokeStatic, "getId", null, null);
                    boolean booleanValue = ((Boolean) ReflectionUtils.invokeInstance(invokeStatic, "isLimitAdTrackingEnabled", null, null)).booleanValue();
                    if (TextUtils.isEmpty(str)) {
                        str = null;
                    }
                    return new AdInfo(str, booleanValue);
                }
                Logger.v("It is advised that you add ----> com.google.android.gms:play-services-ads:7.5.0");
                return null;
            } catch (Exception e) {
                Logger.f("MoEUtils:getAdvertisementInfo", e);
            }
        }
    }

    public static void saveCurrentExponentialCounter(Context context, int i) {
        if (context != null) {
            getSharedPrefs(context).edit().putInt("PREF_EXPONENTIAL_BACK_OFF", i).apply();
        }
    }

    public static int getCurrentExponentialCounter(Context context) {
        if (context == null) {
            return 1;
        }
        return getSharedPrefs(context).getInt("PREF_EXPONENTIAL_BACK_OFF", 1);
    }

    public static void setInstallRegistered(Context context) {
        if (context != null) {
            getSharedPrefs(context).edit().putBoolean("pref_installed", true).apply();
        }
    }

    public static boolean isInstallRegistered(Context context) {
        if (context == null) {
            return false;
        }
        return getSharedPrefs(context).getBoolean("pref_installed", false);
    }

    private static SharedPreferences getSharedPrefs(Context context) {
        return context == null ? null : context.getSharedPreferences(PREF_NAME, 0);
    }

    public static boolean isRegistrationScheduled(Context context) {
        return getSharedPrefs(context).getBoolean("PREF_DEVICE_ADD_SCHEDULED", false);
    }

    public static void setRegistrationScheduled(Context context, boolean z) {
        getSharedPrefs(context).edit().putBoolean("PREF_DEVICE_ADD_SCHEDULED", z).apply();
    }

    public static boolean hasFeature(Context context, String str) {
        return context.getPackageManager().hasSystemFeature(str);
    }

    public static boolean isNullOrEmpty(CharSequence charSequence) {
        return TextUtils.isEmpty(charSequence) || TextUtils.getTrimmedLength(charSequence) == 0;
    }

    public static boolean isNullOrEmpty(Collection collection) {
        return collection == null || collection.size() == 0;
    }

    public static boolean isNullOrEmpty(Map map) {
        return map == null || map.size() == 0;
    }

    public static <T> T getSystemService(Context context, String str) {
        return context.getSystemService(str);
    }

    public static String getAndroidID(Context context) {
        if (!ConfigurationProvider.getInstance(context).isAndroidIdCollectionProhibited()) {
            CharSequence string = Secure.getString(context.getContentResolver(), "android_id");
            if (!(isNullOrEmpty(string) || "9774d56d682e549c".equals(string) || "unknown".equals(string) || "000000000000000".equals(string))) {
                return string;
            }
        }
        return null;
    }

    public static String getIMEI(Context context) {
        try {
            if (!ConfigurationProvider.getInstance(context).isIMEICollectionProhibited() && MoEHelperUtils.hasPermission(context, "android.permission.READ_PHONE_STATE") && hasFeature(context, "android.hardware.telephony")) {
                return ((TelephonyManager) getSystemService(context, "phone")).getDeviceId();
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public static String getNetworkType(Context context) {
        try {
            if (MoEHelperUtils.hasPermission(context, "android.permission.ACCESS_WIFI_STATE")) {
                NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(1);
                if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
                    return e.ad;
                }
            }
            if (MoEHelperUtils.hasPermission(context, "android.permission.READ_PHONE_STATE") && hasFeature(context, "android.hardware.telephony")) {
                switch (((TelephonyManager) getSystemService(context, "phone")).getNetworkType()) {
                    case 1:
                    case 2:
                    case 4:
                    case 7:
                    case 11:
                        return "2G";
                    case 3:
                    case 5:
                    case 6:
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 14:
                    case 15:
                        return "3G";
                    case 13:
                        return "4G";
                    default:
                        return "CouldNotDetermine";
                }
            }
        } catch (Exception e) {
            Logger.f("MoEUtils: getNetworkType", e);
        }
        return null;
    }

    @WorkerThread
    static void trackActivityStates(String str, String str2, Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ACTIVITY_NAME", str2);
            trackEventInternal(str, jSONObject, context);
        } catch (Exception e) {
            Logger.f("MoEUtils :trackActivityStates", e);
        }
    }

    static void trackEventInternal(String str, JSONObject jSONObject, Context context) {
        MoEDAO.getInstance(context).addEvent(new Event(str, jSONObject), context);
    }

    public static void setUserAttributeInternal(Context context, String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(str, str2);
            trackEventInternal("EVENT_ACTION_USER_ATTRIBUTE", jSONObject, context);
        } catch (Exception e) {
            Logger.f("MoEutils : setUserAttribute", e);
        }
    }

    public static String getAPIRoute(Context context) {
        int dataRegion = ConfigurationProvider.getInstance(context).getDataRegion();
        if (dataRegion == -999) {
            return ConfigurationProvider.getInstance(context).shouldRouteTraffic() ? "https://apiv2mumbai.moengage.com" : LocationConstants.API_GEO_FENCE;
        } else {
            switch (dataRegion) {
                case 1001:
                    return "https://apiv2mumbai.moengage.com";
                case 1002:
                    return "https://apiv2eu.moengage.com";
                case 1003:
                    return LocationConstants.API_GEO_FENCE;
                default:
                    return LocationConstants.API_GEO_FENCE;
            }
        }
    }

    public static String addDebugIfRequired(Context context, String str) {
        if (((context.getApplicationInfo().flags & 2) != 0 ? 1 : null) == null) {
            return str;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append("_DEBUG");
        return stringBuilder.toString();
    }

    @Nullable
    public static Bundle convertMapToBundle(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        try {
            for (Entry entry : map.entrySet()) {
                bundle.putString((String) entry.getKey(), (String) entry.getValue());
            }
        } catch (Exception e) {
            Logger.f("MoEUtils#convertMapToBundle : Exception", e);
        }
        return bundle;
    }

    @Nullable
    public static Bundle jsonToBundle(JSONObject jSONObject) {
        try {
            Bundle bundle = new Bundle();
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                bundle.putString(str, jSONObject.getString(str));
            }
            return bundle;
        } catch (JSONException e) {
            Logger.f("MoEUtils : jsonToBundle", e);
            return null;
        }
    }

    public static void updateTestDeviceState(Context context) {
        if (ConfigurationProvider.getInstance(context).getVerificationRegistrationTime() + 3600000 < System.currentTimeMillis()) {
            ConfigurationProvider.getInstance(context).setVerificationRegistration(false);
        }
    }

    @Nullable
    static String convertJSONArrayToString(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        while (i < jSONArray.length()) {
            try {
                stringBuilder.append((String) jSONArray.get(i));
                if (i != jSONArray.length() - 1) {
                    stringBuilder.append(";");
                }
                i++;
            } catch (Exception e) {
                Logger.f("MoEUtils: convertJSONArrayToString", e);
                return null;
            }
        }
        return stringBuilder.toString();
    }

    static String getAPIRouteV3(Context context) {
        int dataRegion = ConfigurationProvider.getInstance(context).getDataRegion();
        if (dataRegion == -999) {
            return ConfigurationProvider.getInstance(context).shouldRouteTraffic() ? "https://apiv3india.moengage.com" : "https://apiv3.moengage.com";
        } else {
            switch (dataRegion) {
                case 1001:
                    return "https://apiv3india.moengage.com";
                case 1002:
                    return "https://apiv3eu.moengage.com";
                case 1003:
                    return "https://apiv3.moengage.com";
                default:
                    return "https://apiv3.moengage.com";
            }
        }
    }

    static JSONArray convertStringToJSONArray(String str) {
        if (TextUtils.isEmpty(str)) {
            return new JSONArray();
        }
        JSONArray jSONArray = new JSONArray();
        for (Object put : str.split(";")) {
            jSONArray.put(put);
        }
        return jSONArray;
    }

    @Nullable
    static UserAttribute getUserAttributePoJo(JSONObject jSONObject) {
        Throwable e;
        UserAttribute userAttribute = null;
        try {
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                UserAttribute userAttribute2 = new UserAttribute();
                try {
                    userAttribute2.userAttributeName = (String) keys.next();
                    userAttribute2.userAttributeValue = jSONObject.getString(userAttribute2.userAttributeName);
                    userAttribute = userAttribute2;
                } catch (Exception e2) {
                    e = e2;
                    userAttribute = userAttribute2;
                    Logger.f("MoEDispatcher : getUserAttributePoJo", e);
                    return userAttribute;
                }
            }
        } catch (Exception e3) {
            e = e3;
            Logger.f("MoEDispatcher : getUserAttributePoJo", e);
            return userAttribute;
        }
        return userAttribute;
    }

    static boolean shouldSendUserAttribute(UserAttribute userAttribute, UserAttribute userAttribute2) {
        return userAttribute == null || userAttribute2 == null || !userAttribute2.equals(userAttribute);
    }

    @Nullable
    static UserAttribute getSavedUserAttribute(Context context, String str) {
        return MoEDAO.getInstance(context).getUserAttributesForKey(str);
    }
}
