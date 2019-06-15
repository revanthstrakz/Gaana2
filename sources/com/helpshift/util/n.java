package com.helpshift.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.facebook.internal.AnalyticsEvents;

public final class n {
    public static boolean a(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                return false;
            }
            return true;
        } catch (Exception e) {
            l.c("Helpshift_ConnectUtil", "Exception while getting system connectivity service", e);
            return false;
        }
    }

    public static String b(Context context) {
        String str = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.getTypeName();
            }
            return str;
        } catch (Exception e) {
            l.c("Helpshift_ConnectUtil", "Exception while getting system connectivity service", e);
            return str;
        }
    }
}
