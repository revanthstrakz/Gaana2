package com.payu.magicretry.a;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class c {
    public static boolean a(Context context) {
        NetworkInfo[] allNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getAllNetworkInfo();
        int length = allNetworkInfo.length;
        int i = 0;
        int i2 = i;
        int i3 = i2;
        while (i < length) {
            NetworkInfo networkInfo = allNetworkInfo[i];
            if (networkInfo.getTypeName().equalsIgnoreCase("WIFI") && networkInfo.isConnected()) {
                i2 = 1;
            }
            if (networkInfo.getTypeName().equalsIgnoreCase("MOBILE") && networkInfo.isConnected()) {
                i3 = 1;
            }
            i++;
        }
        if (i2 == 0 && i3 == 0) {
            return false;
        }
        return true;
    }

    public static void b(Context context) {
        Toast.makeText(context, "Not connected to internet", 0).show();
    }
}
