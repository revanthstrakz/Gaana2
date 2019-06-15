package com.til.colombia.android.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.ConnectivityManager;
import android.os.Build.VERSION;
import android.os.Looper;
import android.util.Base64;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.HttpStatusCodes;
import com.til.colombia.android.internal.HttpClient.b;
import com.til.colombia.android.internal.a.h;
import com.til.colombia.android.network.d;
import com.til.colombia.android.network.l;
import com.til.colombia.android.utils.c;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

public final class a {
    private static Context a = null;
    private static Integer b = null;
    private static volatile Intent c = null;
    private static final Class<?>[] d = new Class[]{String.class, ValueCallback.class};
    private static volatile Method e = null;
    private static final String f = "SettingPrefsFile";
    private static final String g = "clientId";
    private static final String h = "personaUrl";
    private static Map<String, com.til.colombia.android.adapters.a> i = new ConcurrentHashMap();
    private static b j = null;
    private static boolean k = false;
    private static boolean l = false;
    private static boolean m = false;
    private static boolean n = false;

    @TargetApi(17)
    static class a {
        a() {
        }

        static String a(Context context) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(WebSettings.getDefaultUserAgent(context));
            stringBuilder.append(i.f);
            return stringBuilder.toString();
        }
    }

    private static void d(String str) {
        c.a(a, f, h, str);
    }

    private static String L() {
        return c.b(a, f, h);
    }

    public static Context a() {
        return a;
    }

    public static void a(Context context) {
        if (a == null) {
            a = context;
            new Thread(new b()).start();
            com.til.colombia.android.commons.b.a.c();
            h.i().R = com.til.colombia.android.commons.b.a.b;
            h.i().S = com.til.colombia.android.commons.b.a.c;
            l.a().a(new c(), 5);
        }
    }

    public static b b() {
        if (j == null) {
            j = new b(a);
        }
        return j;
    }

    public static void a(String str) {
        h.i();
        h.g(str);
        try {
            h.i();
            if (h.j() == null) {
                if (VERSION.SDK_INT >= 17) {
                    h.i();
                    Context context = a;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(WebSettings.getDefaultUserAgent(context));
                    stringBuilder.append(i.f);
                    h.g(stringBuilder.toString());
                    return;
                }
                h.i();
                h.g(new WebView(a).getSettings().getUserAgentString());
            }
        } catch (Exception e) {
            Log.a(i.f, "Cannot get user agent", e);
        }
    }

    public static boolean b(Context context) {
        if (context == null) {
            Log.b(i.f, "Context is null. Can not check network.");
            return false;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager.getActiveNetworkInfo() == null || !connectivityManager.getActiveNetworkInfo().isConnected()) {
                return false;
            }
            return true;
        } catch (Exception e) {
            Log.a(i.f, "Cannot find network state", e);
            return false;
        }
    }

    /* JADX WARNING: Missing block: B:15:0x0034, code skipped:
            r0 = "3G";
     */
    /* JADX WARNING: Missing block: B:16:0x0037, code skipped:
            r0 = "4G";
     */
    /* JADX WARNING: Missing block: B:23:?, code skipped:
            return com.til.colombia.android.internal.e.w;
     */
    public static java.lang.String c() {
        /*
        r0 = 0;
        r1 = a;	 Catch:{ Exception -> 0x003d }
        r2 = "android.permission.ACCESS_NETWORK_STATE";
        r1 = r1.checkCallingOrSelfPermission(r2);	 Catch:{ Exception -> 0x003d }
        if (r1 != 0) goto L_0x0045;
    L_0x000b:
        r1 = a;	 Catch:{ Exception -> 0x003d }
        r2 = "connectivity";
        r1 = r1.getSystemService(r2);	 Catch:{ Exception -> 0x003d }
        r1 = (android.net.ConnectivityManager) r1;	 Catch:{ Exception -> 0x003d }
        if (r1 == 0) goto L_0x0045;
    L_0x0017:
        r1 = r1.getActiveNetworkInfo();	 Catch:{ Exception -> 0x003d }
        if (r1 == 0) goto L_0x0045;
    L_0x001d:
        r2 = r1.getType();	 Catch:{ Exception -> 0x003d }
        r1 = r1.getSubtype();	 Catch:{ Exception -> 0x003d }
        r3 = 1;
        if (r2 != r3) goto L_0x002b;
    L_0x0028:
        r1 = "wifi";
        goto L_0x0046;
    L_0x002b:
        if (r2 != 0) goto L_0x0045;
    L_0x002d:
        switch(r1) {
            case 1: goto L_0x0031;
            case 2: goto L_0x0031;
            case 3: goto L_0x0034;
            case 4: goto L_0x0031;
            case 5: goto L_0x0034;
            case 6: goto L_0x0034;
            case 7: goto L_0x0031;
            case 8: goto L_0x0034;
            case 9: goto L_0x0034;
            case 10: goto L_0x0034;
            case 11: goto L_0x0031;
            case 12: goto L_0x0034;
            case 13: goto L_0x0037;
            case 14: goto L_0x0034;
            case 15: goto L_0x0034;
            default: goto L_0x0030;
        };	 Catch:{ Exception -> 0x003d }
    L_0x0030:
        goto L_0x003a;
    L_0x0031:
        r1 = "2G";
        r0 = r1;
    L_0x0034:
        r1 = "3G";
        r0 = r1;
    L_0x0037:
        r1 = "4G";
        r0 = r1;
    L_0x003a:
        r1 = "carrier";
        goto L_0x0046;
    L_0x003d:
        r1 = move-exception;
        r2 = "Col:aos:4.0.0";
        r3 = "Error getting the network type";
        com.til.colombia.android.internal.Log.a(r2, r3, r1);
    L_0x0045:
        r1 = r0;
    L_0x0046:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.til.colombia.android.internal.a.c():java.lang.String");
    }

    public static void a(String str, Integer num) {
        h.i().T = str;
        c.a(a, f, e.u, str);
        h.i().U = num.intValue();
        c.a(a, f, e.v, Integer.valueOf(h.i().U).intValue());
    }

    private static String N() {
        if (h.i().T == null) {
            return c.b(a, f, e.u);
        }
        return h.i().T;
    }

    private static Integer O() {
        return Integer.valueOf(c.d(a, f, e.v));
    }

    public static String e() {
        double d = (double) a.getResources().getDisplayMetrics().density;
        if (d >= 4.0d) {
            return "xxxhdpi";
        }
        if (d >= 3.0d) {
            return "xxhdpi";
        }
        if (d >= 2.0d) {
            return "xhdpi";
        }
        if (d >= 1.5d) {
            return "hdpi";
        }
        return d >= 1.0d ? "mdpi" : "ldpi";
    }

    public static int f() {
        return a.getResources().getConfiguration().orientation;
    }

    private static Map<String, String> a(Map<String, ? extends Object> map) {
        HashMap hashMap = new HashMap();
        for (String str : map.keySet()) {
            try {
                hashMap.put(e(str), e(map.get(str).toString()));
            } catch (Exception e) {
                String str2 = i.f;
                StringBuilder stringBuilder = new StringBuilder("Exception Map encoding ");
                stringBuilder.append(map.toString());
                Log.a(str2, stringBuilder.toString(), e);
            }
        }
        return hashMap;
    }

    private static String e(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (Exception e) {
            String str2 = i.f;
            StringBuilder stringBuilder = new StringBuilder("Exception URL encoding ");
            stringBuilder.append(str);
            Log.a(str2, stringBuilder.toString(), e);
            return str;
        }
    }

    private static String a(String str, String str2) {
        String str3 = "";
        try {
            return URLDecoder.decode(str, str2);
        } catch (Exception e) {
            String str4 = i.f;
            StringBuilder stringBuilder = new StringBuilder("Exception URL decoding ");
            stringBuilder.append(str);
            Log.a(str4, stringBuilder.toString(), e);
            return str3;
        }
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x0008 */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:4|5|8) */
    /* JADX WARNING: Missing block: B:5:?, code skipped:
            r0 = com.til.colombia.android.internal.i.f;
            r1 = new java.lang.StringBuilder("JSON with property ");
            r1.append(r4);
            r1.append(" found but has bad datatype(");
            r1.append(r3.get(r4).getClass());
            r1.append("). Reverting to 432000");
            com.til.colombia.android.internal.Log.b(r0, r1.toString());
     */
    /* JADX WARNING: Missing block: B:6:0x0031, code skipped:
            r3 = move-exception;
     */
    /* JADX WARNING: Missing block: B:7:0x0032, code skipped:
            com.til.colombia.android.internal.Log.a(com.til.colombia.android.internal.i.f, "", r3);
     */
    /* JADX WARNING: Missing block: B:8:0x0039, code skipped:
            return com.til.colombia.android.internal.h.u;
     */
    private static long a(org.json.JSONObject r3, java.lang.String r4, long r5) {
        /*
        r5 = 432000; // 0x69780 float:6.05361E-40 double:2.134364E-318;
        r0 = r3.optLong(r4, r5);	 Catch:{ Exception -> 0x0008 }
        return r0;
    L_0x0008:
        r0 = "Col:aos:4.0.0";
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0031 }
        r2 = "JSON with property ";
        r1.<init>(r2);	 Catch:{ Exception -> 0x0031 }
        r1.append(r4);	 Catch:{ Exception -> 0x0031 }
        r2 = " found but has bad datatype(";
        r1.append(r2);	 Catch:{ Exception -> 0x0031 }
        r3 = r3.get(r4);	 Catch:{ Exception -> 0x0031 }
        r3 = r3.getClass();	 Catch:{ Exception -> 0x0031 }
        r1.append(r3);	 Catch:{ Exception -> 0x0031 }
        r3 = "). Reverting to 432000";
        r1.append(r3);	 Catch:{ Exception -> 0x0031 }
        r3 = r1.toString();	 Catch:{ Exception -> 0x0031 }
        com.til.colombia.android.internal.Log.b(r0, r3);	 Catch:{ Exception -> 0x0031 }
        goto L_0x0039;
    L_0x0031:
        r3 = move-exception;
        r4 = "Col:aos:4.0.0";
        r0 = "";
        com.til.colombia.android.internal.Log.a(r4, r0, r3);
    L_0x0039:
        return r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.til.colombia.android.internal.a.a(org.json.JSONObject, java.lang.String, long):long");
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0005 */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:3|4|7) */
    /* JADX WARNING: Missing block: B:4:?, code skipped:
            r0 = com.til.colombia.android.internal.i.f;
            r1 = new java.lang.StringBuilder("JSON with property ");
            r1.append(r4);
            r1.append(" found but has bad datatype(");
            r1.append(r3.get(r4).getClass());
            r1.append("). Reverting to ");
            r1.append(r5);
            com.til.colombia.android.internal.Log.b(r0, r1.toString());
     */
    /* JADX WARNING: Missing block: B:5:0x0031, code skipped:
            r3 = move-exception;
     */
    /* JADX WARNING: Missing block: B:6:0x0032, code skipped:
            com.til.colombia.android.internal.Log.a(com.til.colombia.android.internal.i.f, "", r3);
     */
    /* JADX WARNING: Missing block: B:7:0x0039, code skipped:
            return r5;
     */
    private static int a(org.json.JSONObject r3, java.lang.String r4, int r5) {
        /*
        r0 = r3.optInt(r4, r5);	 Catch:{ Exception -> 0x0005 }
        return r0;
    L_0x0005:
        r0 = "Col:aos:4.0.0";
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0031 }
        r2 = "JSON with property ";
        r1.<init>(r2);	 Catch:{ Exception -> 0x0031 }
        r1.append(r4);	 Catch:{ Exception -> 0x0031 }
        r2 = " found but has bad datatype(";
        r1.append(r2);	 Catch:{ Exception -> 0x0031 }
        r3 = r3.get(r4);	 Catch:{ Exception -> 0x0031 }
        r3 = r3.getClass();	 Catch:{ Exception -> 0x0031 }
        r1.append(r3);	 Catch:{ Exception -> 0x0031 }
        r3 = "). Reverting to ";
        r1.append(r3);	 Catch:{ Exception -> 0x0031 }
        r1.append(r5);	 Catch:{ Exception -> 0x0031 }
        r3 = r1.toString();	 Catch:{ Exception -> 0x0031 }
        com.til.colombia.android.internal.Log.b(r0, r3);	 Catch:{ Exception -> 0x0031 }
        goto L_0x0039;
    L_0x0031:
        r3 = move-exception;
        r4 = "Col:aos:4.0.0";
        r0 = "";
        com.til.colombia.android.internal.Log.a(r4, r0, r3);
    L_0x0039:
        return r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.til.colombia.android.internal.a.a(org.json.JSONObject, java.lang.String, int):int");
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0005 */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:3|4|7) */
    /* JADX WARNING: Missing block: B:4:?, code skipped:
            r0 = com.til.colombia.android.internal.i.f;
            r1 = new java.lang.StringBuilder("JSON with property ");
            r1.append(r4);
            r1.append(" found but has bad datatype(");
            r1.append(r3.get(r4).getClass());
            r1.append("). Reverting to ");
            r1.append(r5);
            com.til.colombia.android.internal.Log.b(r0, r1.toString());
     */
    /* JADX WARNING: Missing block: B:5:0x0031, code skipped:
            r3 = move-exception;
     */
    /* JADX WARNING: Missing block: B:6:0x0032, code skipped:
            com.til.colombia.android.internal.Log.a(com.til.colombia.android.internal.i.f, "", r3);
     */
    /* JADX WARNING: Missing block: B:7:0x0039, code skipped:
            return r5;
     */
    private static boolean a(org.json.JSONObject r3, java.lang.String r4, boolean r5) {
        /*
        r0 = r3.optBoolean(r4, r5);	 Catch:{ Exception -> 0x0005 }
        return r0;
    L_0x0005:
        r0 = "Col:aos:4.0.0";
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0031 }
        r2 = "JSON with property ";
        r1.<init>(r2);	 Catch:{ Exception -> 0x0031 }
        r1.append(r4);	 Catch:{ Exception -> 0x0031 }
        r2 = " found but has bad datatype(";
        r1.append(r2);	 Catch:{ Exception -> 0x0031 }
        r3 = r3.get(r4);	 Catch:{ Exception -> 0x0031 }
        r3 = r3.getClass();	 Catch:{ Exception -> 0x0031 }
        r1.append(r3);	 Catch:{ Exception -> 0x0031 }
        r3 = "). Reverting to ";
        r1.append(r3);	 Catch:{ Exception -> 0x0031 }
        r1.append(r5);	 Catch:{ Exception -> 0x0031 }
        r3 = r1.toString();	 Catch:{ Exception -> 0x0031 }
        com.til.colombia.android.internal.Log.b(r0, r3);	 Catch:{ Exception -> 0x0031 }
        goto L_0x0039;
    L_0x0031:
        r3 = move-exception;
        r4 = "Col:aos:4.0.0";
        r0 = "";
        com.til.colombia.android.internal.Log.a(r4, r0, r3);
    L_0x0039:
        return r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.til.colombia.android.internal.a.a(org.json.JSONObject, java.lang.String, boolean):boolean");
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0005 */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:3|4|7) */
    /* JADX WARNING: Missing block: B:4:?, code skipped:
            r0 = com.til.colombia.android.internal.i.f;
            r1 = new java.lang.StringBuilder("JSON with property ");
            r1.append(r4);
            r1.append(" found but has bad datatype(");
            r1.append(r3.get(r4).getClass());
            r1.append("). Reverting to ");
            r1.append(r5);
            com.til.colombia.android.internal.Log.b(r0, r1.toString());
     */
    /* JADX WARNING: Missing block: B:5:0x0031, code skipped:
            r3 = move-exception;
     */
    /* JADX WARNING: Missing block: B:6:0x0032, code skipped:
            com.til.colombia.android.internal.Log.a(com.til.colombia.android.internal.i.f, "", r3);
     */
    /* JADX WARNING: Missing block: B:7:0x0039, code skipped:
            return r5;
     */
    private static java.lang.String a(org.json.JSONObject r3, java.lang.String r4, java.lang.String r5) {
        /*
        r0 = r3.optString(r4, r5);	 Catch:{ Exception -> 0x0005 }
        return r0;
    L_0x0005:
        r0 = "Col:aos:4.0.0";
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0031 }
        r2 = "JSON with property ";
        r1.<init>(r2);	 Catch:{ Exception -> 0x0031 }
        r1.append(r4);	 Catch:{ Exception -> 0x0031 }
        r2 = " found but has bad datatype(";
        r1.append(r2);	 Catch:{ Exception -> 0x0031 }
        r3 = r3.get(r4);	 Catch:{ Exception -> 0x0031 }
        r3 = r3.getClass();	 Catch:{ Exception -> 0x0031 }
        r1.append(r3);	 Catch:{ Exception -> 0x0031 }
        r3 = "). Reverting to ";
        r1.append(r3);	 Catch:{ Exception -> 0x0031 }
        r1.append(r5);	 Catch:{ Exception -> 0x0031 }
        r3 = r1.toString();	 Catch:{ Exception -> 0x0031 }
        com.til.colombia.android.internal.Log.b(r0, r3);	 Catch:{ Exception -> 0x0031 }
        goto L_0x0039;
    L_0x0031:
        r3 = move-exception;
        r4 = "Col:aos:4.0.0";
        r0 = "";
        com.til.colombia.android.internal.Log.a(r4, r0, r3);
    L_0x0039:
        return r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.til.colombia.android.internal.a.a(org.json.JSONObject, java.lang.String, java.lang.String):java.lang.String");
    }

    private static String f(String str) {
        String str2;
        Throwable e;
        int i = 0;
        while (true) {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                str2 = e.c;
                h.i();
                httpURLConnection.setRequestProperty(str2, h.j());
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setRequestMethod(HttpMethods.GET);
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode < HttpStatusCodes.STATUS_CODE_MULTIPLE_CHOICES || responseCode >= 400) {
                    return str;
                }
                str2 = httpURLConnection.getHeaderField(e.e);
                if (str2 == null) {
                    return str;
                }
                try {
                    if (httpURLConnection.getResponseCode() == 200) {
                        break;
                    }
                    int i2 = i + 1;
                    if (i >= 5) {
                        break;
                    }
                    i = i2;
                    str = str2;
                } catch (Exception e2) {
                    e = e2;
                    str = str2;
                    Log.a(i.f, "Cannot get redirect url", e);
                    return str;
                }
            } catch (Exception e3) {
                e = e3;
                Log.a(i.f, "Cannot get redirect url", e);
                return str;
            }
        }
        return str2;
    }

    public static Map<String, com.til.colombia.android.adapters.a> g() {
        return i;
    }

    private static boolean P() {
        try {
            c = Q();
        } catch (Exception e) {
            Log.a(i.f, "Error getting headset status.", e);
        }
        return (c == null || c.getIntExtra("state", 0) == 0) ? false : true;
    }

    private static void a(WebView webView, String str) {
        if (e == null && VERSION.SDK_INT >= 19) {
            e = R();
            Log.a(i.f, "G+J EMS SDK AdView: Running in KITKAT mode with new Chromium webview!");
        }
        if (VERSION.SDK_INT < 19) {
            StringBuilder stringBuilder = new StringBuilder("javascript:");
            stringBuilder.append(str);
            webView.loadUrl(stringBuilder.toString());
            return;
        }
        try {
            e.invoke(webView, new Object[]{str, null});
        } catch (Exception e) {
            Log.a(i.f, "FATAL ERROR: Could not invoke Android 4.4 Chromium WebView method evaluateJavascript", e);
        }
    }

    private static synchronized Method R() {
        Method declaredMethod;
        synchronized (a.class) {
            try {
                declaredMethod = Class.forName("android.webkit.WebView").getDeclaredMethod("evaluateJavascript", d);
                e = declaredMethod;
                declaredMethod.setAccessible(true);
            } catch (Exception e) {
                Log.a(i.f, "FATAL ERROR: Could not invoke Android 4.4 Chromium WebView method evaluateJavascript", e);
            }
            declaredMethod = e;
        }
        return declaredMethod;
    }

    public static String h() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(System.currentTimeMillis());
        return stringBuilder.toString();
    }

    public static String a(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] toByteArray = byteArrayOutputStream.toByteArray();
        StringBuilder stringBuilder = new StringBuilder("data:text/javascript;base64,");
        stringBuilder.append(Base64.encodeToString(toByteArray, 0));
        return stringBuilder.toString();
    }

    public static void i() {
        Looper.prepare();
        if (h.a(G())) {
            k = true;
        }
        if (h.a(D())) {
            l = true;
        }
        Looper.loop();
    }

    private static void S() {
        try {
            com.til.colombia.android.adapters.a.getInstance(g.d).createCache();
        } catch (Throwable unused) {
            k = true;
        }
    }

    private static void T() {
        try {
            com.til.colombia.android.adapters.a.getInstance(g.c).createCache();
        } catch (Throwable unused) {
            l = true;
        }
    }

    public static void j() {
        k = true;
    }

    public static void k() {
        l = true;
    }

    public static String l() {
        StringBuilder stringBuilder = new StringBuilder();
        if (k) {
            stringBuilder.append(c.b(a, f, e.an));
        }
        if (!h.a(stringBuilder.toString())) {
            stringBuilder.append(",");
        }
        if (l) {
            stringBuilder.append(c.b(a, f, e.ap));
        }
        return stringBuilder.toString().trim();
    }

    public static void a(JSONObject jSONObject) {
        c.a(a, f, "expiry", a(jSONObject, "expiry", (long) h.u));
        c.a(a, f, e.V, a(jSONObject, e.V, 1));
        c.a(a, f, e.W, a(jSONObject, e.W, 3));
        c.a(a, f, e.X, a(jSONObject, e.X, 15000));
        c.a(a, f, e.Y, a(jSONObject, e.Y, 5));
        c.a(a, f, e.Z, a(jSONObject, e.Z, 1000));
        c.a(a, f, e.ag, a(jSONObject, e.ag, h.H));
        c.a(a, f, e.ah, a(jSONObject, e.ah, h.I));
        c.a(a, f, e.ai, a(jSONObject, e.ai, 5000));
        c.a(a, f, e.al, a(jSONObject, e.al, h.F));
        c.a(a, f, e.aj, a(jSONObject, e.aj, 10));
        c.a(a, f, e.ak, a(jSONObject, e.ak, ""));
        c.a(a, f, e.an, a(jSONObject, e.an, null));
        c.a(a, f, e.ap, a(jSONObject, e.ap, null));
        c.a(a, f, e.ar, a(jSONObject, e.ar, null));
        c.a(a, f, e.au, a(jSONObject, e.au, 3));
        c.a(a, f, e.av, a(jSONObject, e.av, null));
        c.a(a, f, e.ax, a(jSONObject, e.ax, null));
        c.a(a, f, e.ay, a(jSONObject, e.ay, null));
        c.a(a, f, e.aw, a(jSONObject, e.aw, 200));
    }

    public static void m() {
        c.a(a, f, "expiry", (long) h.u);
        c.a(a, f, e.V, 1);
        c.a(a, f, e.W, 3);
        c.a(a, f, e.X, 15000);
        c.a(a, f, e.Y, 5);
        c.a(a, f, e.Z, 1000);
        c.a(a, f, e.ag, h.H);
        c.a(a, f, e.ah, h.I);
        c.a(a, f, e.ai, 5000);
        c.a(a, f, e.al, h.F);
        c.a(a, f, e.ak, "");
        c.a(a, f, e.aj, 10);
        c.a(a, f, e.aw, 200);
    }

    private static void a(int i) {
        c.a(a, f, e.al, i);
    }

    public static int n() {
        return c.d(a, f, e.al);
    }

    private static void b(int i) {
        c.a(a, f, e.aj, i);
    }

    private static int U() {
        return c.d(a, f, e.aj);
    }

    private static void g(String str) {
        c.a(a, f, e.ak, str);
    }

    public static String o() {
        return c.b(a, f, e.ak);
    }

    private static void c(int i) {
        c.a(a, f, e.ai, i);
    }

    public static int p() {
        return c.d(a, f, e.ai);
    }

    private static void h(String str) {
        c.a(a, f, e.ax, str);
    }

    public static String q() {
        return c.b(a, f, e.ax);
    }

    private static void i(String str) {
        c.a(a, f, e.ay, str);
    }

    public static String r() {
        return c.b(a, f, e.ay);
    }

    private static void d(int i) {
        c.a(a, f, e.aw, i);
    }

    public static int s() {
        int d = c.d(a, f, e.aw);
        return d == 0 ? 200 : d;
    }

    private static void e(int i) {
        c.a(a, f, e.ag, i);
    }

    private static boolean V() {
        return c.d(a, f, e.ag) == 1;
    }

    private static void f(int i) {
        c.a(a, f, e.ah, i);
    }

    private static boolean W() {
        return c.d(a, f, e.ah) == 1;
    }

    private static void g(int i) {
        c.a(a, f, e.V, i);
    }

    public static boolean t() {
        return c.d(a, f, e.V) == 1;
    }

    public static boolean u() {
        Context context = a;
        String str = f;
        String str2 = e.V;
        if (context == null || str == null || str2 == null || "".equals(str.trim()) || "".equals(str2.trim())) {
            return false;
        }
        return context.getSharedPreferences(str, 0).contains(str2);
    }

    private static void a(long j) {
        c.a(a, f, "expiry", j);
    }

    private static long X() {
        return c.e(a, f, "expiry");
    }

    public static void v() {
        c.a(a, f, e.aa, System.currentTimeMillis() / 1000);
    }

    public static boolean w() {
        long e = c.e(a, f, e.aa);
        return e == 0 || System.currentTimeMillis() / 1000 >= e + X();
    }

    private static void h(int i) {
        c.a(a, f, e.W, i);
    }

    public static int x() {
        return c.d(a, f, e.W);
    }

    private static void i(int i) {
        c.a(a, f, e.X, i);
    }

    public static int y() {
        return c.d(a, f, e.X);
    }

    private static void j(int i) {
        c.a(a, f, e.Y, i);
    }

    public static int z() {
        return c.d(a, f, e.Y);
    }

    private static void k(int i) {
        c.a(a, f, e.Z, i);
    }

    public static int A() {
        return c.d(a, f, e.Z);
    }

    private static void j(String str) {
        c.a(a, f, e.ar, str);
    }

    public static String B() {
        return c.b(a, f, e.ar);
    }

    private static void k(String str) {
        c.a(a, f, e.av, str);
    }

    public static String C() {
        return c.b(a, f, e.av);
    }

    public static void b(String str) {
        c.a(a, f, e.ao, str);
    }

    public static String D() {
        return c.b(a, f, e.ao);
    }

    private static void l(String str) {
        c.a(a, f, e.ap, str);
    }

    private static String Y() {
        return c.b(a, f, e.ap);
    }

    public static void E() {
        c.a(a, f, e.aq, System.currentTimeMillis() / 1000);
    }

    public static boolean F() {
        long e = c.e(a, f, e.aq);
        return e == 0 || System.currentTimeMillis() / 1000 >= e + X();
    }

    public static void c(String str) {
        c.a(a, f, e.am, str);
    }

    public static String G() {
        return c.b(a, f, e.am);
    }

    private static void m(String str) {
        c.a(a, f, e.an, str);
    }

    private static String Z() {
        return c.b(a, f, e.an);
    }

    public static void a(boolean z) {
        m = true;
        d.b().d().b();
    }

    public static void b(boolean z) {
        n = true;
    }

    public static boolean H() {
        return m;
    }

    private static boolean aa() {
        return n;
    }

    private static void l(int i) {
        c.a(a, f, e.au, i);
    }

    public static int I() {
        int d = c.d(a, f, e.au);
        return d == 0 ? 3 : d;
    }

    public static boolean J() {
        try {
            return VERSION.SDK_INT >= 23 ? a.checkSelfPermission("android.permission.READ_PHONE_STATE") == 0 : a.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") == 0;
        } catch (Exception unused) {
            return true;
        }
    }

    private static void M() {
        try {
            Context context = a;
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null) {
                com.til.colombia.android.commons.b.a.b = applicationInfo.packageName;
                com.til.colombia.android.commons.b.a.a = applicationInfo.loadLabel(packageManager).toString();
            }
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 128);
            String str = null;
            if (packageInfo != null) {
                str = packageInfo.versionName;
                if (str == null || str.equals("")) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(packageInfo.versionCode);
                    str = stringBuilder.toString();
                }
            }
            if (!(str == null || str.equals(""))) {
                com.til.colombia.android.commons.b.a.c = str;
            }
        } catch (Exception e) {
            Log.a("Col:aos:4.0.0APP-INFO", "Failed to fill AppInfo", e);
        }
        h.i().R = com.til.colombia.android.commons.b.a.b;
        h.i().S = com.til.colombia.android.commons.b.a.c;
    }

    public static boolean d() {
        if (a != null) {
            return true;
        }
        Log.b(i.f, "Context is null.");
        return false;
    }

    private static synchronized Intent Q() {
        Intent registerReceiver;
        synchronized (a.class) {
            registerReceiver = a.registerReceiver(null, new IntentFilter("android.intent.action.HEADSET_PLUG"));
            c = registerReceiver;
        }
        return registerReceiver;
    }
}
