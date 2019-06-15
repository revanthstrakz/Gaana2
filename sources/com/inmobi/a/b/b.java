package com.inmobi.a.b;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import com.inmobi.a.o;
import com.inmobi.commons.a.a;
import com.til.colombia.android.internal.e;

public final class b {
    static boolean a(int i, int i2) {
        return (i & i2) == i2;
    }

    static boolean a(int i) {
        return !a(i, 2);
    }

    private static a a(boolean z, boolean z2) {
        Throwable e;
        Context b = a.b();
        String str = null;
        if (b == null) {
            return null;
        }
        a aVar;
        try {
            WifiInfo connectionInfo = ((WifiManager) b.getSystemService(e.ad)).getConnectionInfo();
            if (connectionInfo != null) {
                String bssid = connectionInfo.getBSSID();
                String ssid = connectionInfo.getSSID();
                if (!(bssid == null || a(z, ssid))) {
                    aVar = new a();
                    try {
                        aVar.a = a(bssid);
                        bssid = (ssid != null && ssid.startsWith("\"") && ssid.endsWith("\"")) ? ssid.substring(1, ssid.length() - 1) : ssid;
                        if (!z2) {
                            str = bssid;
                        }
                        aVar.b = str;
                        aVar.c = connectionInfo.getRssi();
                        aVar.d = connectionInfo.getIpAddress();
                    } catch (Exception e2) {
                        e = e2;
                        com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                        return aVar;
                    }
                    return aVar;
                }
            }
            aVar = null;
        } catch (Exception e3) {
            e = e3;
            aVar = null;
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            return aVar;
        }
        return aVar;
    }

    static boolean a(boolean z, String str) {
        return z && str != null && str.endsWith("_nomap");
    }

    static long a(String str) {
        String[] split = str.split("\\:");
        byte[] bArr = new byte[6];
        int i = 0;
        while (i < 6) {
            try {
                bArr[i] = (byte) Integer.parseInt(split[i], 16);
                i++;
            } catch (NumberFormatException unused) {
                return 0;
            }
        }
        return (((((((long) bArr[5]) & 255) | ((((long) bArr[4]) & 255) << 8)) | ((((long) bArr[3]) & 255) << 16)) | ((((long) bArr[2]) & 255) << 24)) | ((((long) bArr[1]) & 255) << 32)) | ((((long) bArr[0]) & 255) << 40);
    }

    public static a a() {
        int i = 0;
        int i2 = (a.a() && com.inmobi.commons.core.utilities.e.a(a.b(), "signals", "android.permission.ACCESS_WIFI_STATE")) ? 1 : 0;
        if (i2 != 0) {
            com.inmobi.a.p.b bVar = o.a().a.a;
            if (bVar.l && bVar.a) {
                i = 1;
            }
            if (i != 0) {
                i2 = o.a().a.a.j;
                return a(a(i2), a(i2, 1));
            }
        }
        return null;
    }
}
