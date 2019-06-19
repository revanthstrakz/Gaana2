package com.facebook.ads.internal.settings;

import android.util.Log;
import java.util.Collection;
import java.util.HashSet;

public class AdInternalSettings {
    static volatile boolean a = false;
    private static final String b = "AdInternalSettings";
    private static final Collection<String> c = new HashSet();
    private static final Collection<String> d = new HashSet();
    private static boolean e;
    private static boolean f;
    private static String g;
    private static String h;
    private static String i;
    private static boolean j;
    private static boolean k;
    private static boolean l;

    static {
        d.add("sdk");
        d.add("google_sdk");
        d.add("vbox86p");
        d.add("vbox86tp");
    }

    private static void a(String str) {
        if (!a) {
            a = true;
            String str2 = b;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Test mode device hash: ");
            stringBuilder.append(str);
            Log.d(str2, stringBuilder.toString());
            str2 = b;
            stringBuilder = new StringBuilder();
            stringBuilder.append("When testing your app with Facebook's ad units you must specify the device hashed ID to ensure the delivery of test ads, add the following code before loading an ad: AdSettings.addTestDevice(\"");
            stringBuilder.append(str);
            stringBuilder.append("\");");
            Log.d(str2, stringBuilder.toString());
        }
    }

    public static void addTestDevice(String str) {
        c.add(str);
    }

    public static void addTestDevices(Collection<String> collection) {
        c.addAll(collection);
    }

    public static void clearTestDevices() {
        c.clear();
    }

    public static String getMediationService() {
        return h;
    }

    public static String getUrlPrefix() {
        return g;
    }

    public static boolean isDebugBuild() {
        return j;
    }

    public static boolean isExplicitTestMode() {
        return e;
    }

    /* JADX WARNING: Missing block: B:17:0x005c, code skipped:
            return true;
     */
    public static boolean isTestMode(android.content.Context r4) {
        /*
        r0 = j;
        r1 = 1;
        if (r0 != 0) goto L_0x005c;
    L_0x0005:
        r0 = isExplicitTestMode();
        if (r0 != 0) goto L_0x005c;
    L_0x000b:
        r0 = d;
        r2 = android.os.Build.PRODUCT;
        r0 = r0.contains(r2);
        if (r0 == 0) goto L_0x0016;
    L_0x0015:
        return r1;
    L_0x0016:
        r0 = i;
        r2 = 0;
        if (r0 != 0) goto L_0x004b;
    L_0x001b:
        r0 = "FBAdPrefs";
        r4 = r4.getSharedPreferences(r0, r2);
        r0 = "deviceIdHash";
        r3 = 0;
        r0 = r4.getString(r0, r3);
        i = r0;
        r0 = i;
        r0 = android.text.TextUtils.isEmpty(r0);
        if (r0 == 0) goto L_0x004b;
    L_0x0032:
        r0 = java.util.UUID.randomUUID();
        r0 = r0.toString();
        i = r0;
        r4 = r4.edit();
        r0 = "deviceIdHash";
        r3 = i;
        r4 = r4.putString(r0, r3);
        r4.apply();
    L_0x004b:
        r4 = c;
        r0 = i;
        r4 = r4.contains(r0);
        if (r4 == 0) goto L_0x0056;
    L_0x0055:
        return r1;
    L_0x0056:
        r4 = i;
        a(r4);
        return r2;
    L_0x005c:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.settings.AdInternalSettings.isTestMode(android.content.Context):boolean");
    }

    public static boolean isVideoAutoplay() {
        return k;
    }

    public static boolean isVideoAutoplayOnMobile() {
        return l;
    }

    public static boolean isVisibleAnimation() {
        return f;
    }

    public static void setDebugBuild(boolean z) {
        j = z;
    }

    public static void setMediationService(String str) {
        h = str;
    }

    public static void setTestMode(boolean z) {
        e = z;
    }

    public static void setUrlPrefix(String str) {
        g = str;
    }

    public static void setVideoAutoplay(boolean z) {
        k = z;
    }

    public static void setVideoAutoplayOnMobile(boolean z) {
        l = z;
    }

    public static void setVisibleAnimation(boolean z) {
        f = z;
    }
}
