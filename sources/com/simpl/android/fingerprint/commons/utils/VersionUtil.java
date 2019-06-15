package com.simpl.android.fingerprint.commons.utils;

import android.util.Log;
import com.simpl.android.fingerprint.BuildConfig;

public class VersionUtil {
    private static final String FINGERPRINT_BUILD_CONFIG_CLASS = "com.simpl.android.fingerprint.BuildConfig";
    private static final String ONECLICK_BUILD_CONFIG_CLASS = "com.simpl.android.sdk.BuildConfig";
    private static final String TAG = "VersionUtil";
    private static final String ZEROCLICK_BUILD_CONFIG_CLASS = "com.simpl.android.zeroClickSdk.BuildConfig";
    private static final String[] buildConfigClasses = new String[]{ONECLICK_BUILD_CONFIG_CLASS, ZEROCLICK_BUILD_CONFIG_CLASS, FINGERPRINT_BUILD_CONFIG_CLASS};

    private static String getField(String str, String str2) {
        try {
            return (String) Class.forName(str).getField(str2).get(null);
        } catch (ClassNotFoundException e) {
            str2 = TAG;
            str = e.getLocalizedMessage();
            Log.e(str2, str);
            return null;
        } catch (NoSuchFieldException e2) {
            str2 = TAG;
            str = e2.getLocalizedMessage();
            Log.e(str2, str);
            return null;
        } catch (IllegalAccessException e3) {
            str2 = TAG;
            str = e3.getLocalizedMessage();
            Log.e(str2, str);
            return null;
        }
    }

    public static String getSdkType() {
        for (String sdkType : buildConfigClasses) {
            String sdkType2 = getSdkType(sdkType2);
            if (sdkType2 != null) {
                return sdkType2;
            }
        }
        return "";
    }

    private static String getSdkType(String str) {
        str = getField(str, "APPLICATION_ID");
        if (str == null) {
            return null;
        }
        str = str.toLowerCase();
        Object obj = -1;
        int hashCode = str.hashCode();
        if (hashCode != 495968257) {
            if (hashCode != 1068422323) {
                if (hashCode == 1869248605 && str.equals(BuildConfig.APPLICATION_ID)) {
                    obj = 2;
                }
            } else if (str.equals("com.simpl.android.sdk")) {
                obj = null;
            }
        } else if (str.equals("com.simpl.android.zeroclicksdk")) {
            obj = 1;
        }
        switch (obj) {
            case null:
                return "oc";
            case 1:
                return "zc";
            case 2:
                return "fp";
            default:
                return null;
        }
    }

    public static String getSdkVersion() {
        for (String versionName : buildConfigClasses) {
            String versionName2 = getVersionName(versionName2);
            if (versionName2 != null) {
                return versionName2;
            }
        }
        return "";
    }

    private static String getVersionName(String str) {
        return getField(str, "VERSION_NAME");
    }
}
