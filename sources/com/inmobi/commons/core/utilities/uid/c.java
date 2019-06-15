package com.inmobi.commons.core.utilities.uid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.inmobi.commons.a.a;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.f;
import java.security.MessageDigest;

public class c {
    private static final String a = "c";
    private static final Object b = new Object();
    private static c c;
    private static a d;

    public static String d() {
        return "1";
    }

    public static c a() {
        c cVar = c;
        if (cVar == null) {
            synchronized (b) {
                cVar = c;
                if (cVar == null) {
                    cVar = new c();
                    c = cVar;
                }
            }
        }
        return cVar;
    }

    private c() {
    }

    public static void c() {
        try {
            String str;
            InternalLogLevel internalLogLevel;
            String str2;
            StringBuilder stringBuilder;
            if (f.a("root")) {
                a aVar = d;
                if (aVar != null) {
                    str = aVar.a;
                    if (str != null) {
                        internalLogLevel = InternalLogLevel.DEBUG;
                        str2 = a;
                        stringBuilder = new StringBuilder("Publisher device Id is ");
                        stringBuilder.append(str);
                        Logger.a(internalLogLevel, str2, stringBuilder.toString());
                    }
                }
                return;
            }
            str = e();
            internalLogLevel = InternalLogLevel.DEBUG;
            str2 = a;
            stringBuilder = new StringBuilder("Publisher device Id is ");
            stringBuilder.append(a(str, "SHA-1"));
            Logger.a(internalLogLevel, str2, stringBuilder.toString());
        } catch (Exception e) {
            new StringBuilder("SDK encountered an unexpected error attempting to print the publisher test ID; ").append(e.getMessage());
        }
    }

    static String a(String str, String str2) {
        if (str != null) {
            try {
                if (!"".equals(str.trim())) {
                    MessageDigest instance = MessageDigest.getInstance(str2);
                    instance.update(str.getBytes());
                    byte[] digest = instance.digest();
                    StringBuffer stringBuffer = new StringBuffer();
                    for (byte b : digest) {
                        stringBuffer.append(Integer.toString((b & 255) + 256, 16).substring(1));
                    }
                    return stringBuffer.toString();
                }
            } catch (Exception e) {
                new StringBuilder("SDK encountered an unexpected error attempting to get digested UID; ").append(e.getMessage());
                return null;
            }
        }
        return "TEST_EMULATOR";
    }

    @SuppressLint({"HardwareIds"})
    static String e() {
        String str = "";
        Context b = a.b();
        if (b == null) {
            return str;
        }
        try {
            str = Secure.getString(b.getContentResolver(), "android_id");
            return str == null ? System.getString(b.getContentResolver(), "android_id") : str;
        } catch (Exception unused) {
            return "";
        }
    }

    static a f() {
        return d;
    }

    private static boolean j() {
        try {
            AdvertisingIdClient.class.getName();
            return true;
        } catch (NoClassDefFoundError unused) {
            return false;
        }
    }

    @Nullable
    public static Boolean g() {
        a();
        a aVar = d;
        return aVar == null ? null : aVar.b;
    }

    public final void b() {
        try {
            final b bVar = new b();
            a aVar = new a();
            d = aVar;
            aVar.a = bVar.a.c("adv_id");
            d.b = bVar.a.a.contains("limit_ad_tracking") ? Boolean.valueOf(bVar.a.b("limit_ad_tracking", true)) : null;
            if (f.a("root") && j()) {
                new Thread(new Runnable() {
                    public final void run() {
                        try {
                            Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(a.b());
                            String id = advertisingIdInfo.getId();
                            boolean isLimitAdTrackingEnabled = advertisingIdInfo.isLimitAdTrackingEnabled();
                            c.d.a = id;
                            bVar.a.a("adv_id", id);
                            c.d.b = Boolean.valueOf(isLimitAdTrackingEnabled);
                            bVar.a.a("limit_ad_tracking", isLimitAdTrackingEnabled);
                        } catch (Exception e) {
                            c.a;
                            new StringBuilder("SDK encountered unexpected error in trying to set the advertising ID ").append(e.getMessage());
                        }
                    }
                }).start();
            }
        } catch (Exception e) {
            new StringBuilder("SDK encountered unexpected error in setting the advertising ID; ").append(e.getMessage());
        }
    }
}
