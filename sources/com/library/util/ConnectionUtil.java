package com.library.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.library.helpers.Enums.ConnectionType;

public class ConnectionUtil {
    public static boolean isConnected(Context context) {
        NetworkInfo networkInfo = getNetworkInfo(context);
        return networkInfo != null && networkInfo.isConnected();
    }

    public static ConnectionType getConnectionType(Context context) {
        if (!isConnected(context)) {
            return null;
        }
        int type = getNetworkInfo(context).getType();
        int subtype = getNetworkInfo(context).getSubtype();
        if (type == 1) {
            return ConnectionType.WIFI;
        }
        if (type != 0) {
            return null;
        }
        switch (subtype) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return ConnectionType.L_SPEED;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 13:
            case 14:
            case 15:
                return ConnectionType.H_SPEED;
            default:
                return ConnectionType.H_SPEED;
        }
    }

    private static NetworkInfo getNetworkInfo(Context context) {
        return ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
    }
}
