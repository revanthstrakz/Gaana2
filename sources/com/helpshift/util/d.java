package com.helpshift.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

public class d {
    private final int a;
    private final int b;
    private final Context c;

    public d(int i, int i2, Context context) {
        this.a = i;
        this.b = i2;
        this.c = context;
    }

    public int a() {
        int i = this.a;
        TelephonyManager telephonyManager = (TelephonyManager) this.c.getSystemService("phone");
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.c.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return i;
        }
        switch (activeNetworkInfo.getType()) {
            case 0:
                int networkType = telephonyManager.getNetworkType();
                if (networkType == 13 || networkType == 15) {
                    return this.a * 4;
                }
                switch (networkType) {
                    case 1:
                    case 2:
                        return this.a / 2;
                    default:
                        return i;
                }
            case 1:
                return this.b;
            default:
                return i;
        }
    }
}
