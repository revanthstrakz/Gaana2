package com.facebook.ads.internal.k;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import com.facebook.ads.internal.h.e;
import com.facebook.ads.internal.i.b;
import com.facebook.ads.internal.s.a.g;
import com.facebook.ads.internal.s.a.g.a;
import com.facebook.ads.internal.s.a.h;
import com.facebook.ads.internal.s.a.i;
import com.facebook.ads.internal.s.a.k;
import com.facebook.ads.internal.s.a.o;
import com.facebook.ads.internal.s.a.u;
import com.facebook.ads.internal.s.a.v;
import com.facebook.ads.internal.s.a.y;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.gaana.login.LoginManager;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

public class c {
    public static int a = 1303;
    private static final AtomicInteger b = new AtomicInteger(0);
    private static String c;
    private static final a d = g.a();
    private final Context e;
    private final b f;

    public c(Context context, boolean z) {
        this.e = context;
        this.f = new b(context);
        a(context, z);
    }

    @WorkerThread
    public static void a(Context context) {
        if (context != null) {
            context = context.getApplicationContext();
            com.facebook.ads.internal.r.a.a.a(new com.facebook.ads.internal.r.a.a.a() {
                public Map<String, String> a() {
                    HashMap hashMap = new HashMap();
                    if (!com.facebook.ads.internal.d.b.c) {
                        hashMap.put("X-FB-Pool-Routing-Token", new c(context, true).a());
                    }
                    return hashMap;
                }
            });
        }
    }

    private static void a(final Context context, boolean z) {
        if (b.compareAndSet(0, 1)) {
            try {
                o.a();
                final SharedPreferences sharedPreferences = context.getSharedPreferences("FBAdPrefs", 0);
                b bVar = new b(context);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("AFP;");
                stringBuilder.append(bVar.g());
                final String stringBuilder2 = stringBuilder.toString();
                c = sharedPreferences.getString(stringBuilder2, null);
                FutureTask futureTask = new FutureTask(new Callable<Boolean>() {
                    /* renamed from: a */
                    public Boolean call() {
                        c.c = c.b(context, context.getPackageName());
                        sharedPreferences.edit().putString(stringBuilder2, c.c).apply();
                        c.b.set(2);
                        return Boolean.valueOf(true);
                    }
                });
                Executors.newSingleThreadExecutor().submit(futureTask);
                if (z) {
                    futureTask.get();
                }
            } catch (Exception unused) {
                b.set(0);
            }
        }
    }

    @Nullable
    private static String b(Context context, String str) {
        try {
            return i.a(context.getPackageManager().getApplicationInfo(str, 0).sourceDir);
        } catch (Exception e) {
            Map b = new c(context, false).b();
            b.put(LoginManager.TAG_SUBTYPE, "generic");
            b.put("subtype_code", String.valueOf(a));
            e.a(e, context, b);
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x008f A:{SYNTHETIC, Splitter:B:39:0x008f} */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0094 A:{Catch:{ IOException -> 0x009c }} */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0099 A:{Catch:{ IOException -> 0x009c }} */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x008f A:{SYNTHETIC, Splitter:B:39:0x008f} */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0094 A:{Catch:{ IOException -> 0x009c }} */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0099 A:{Catch:{ IOException -> 0x009c }} */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x008f A:{SYNTHETIC, Splitter:B:39:0x008f} */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0094 A:{Catch:{ IOException -> 0x009c }} */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0099 A:{Catch:{ IOException -> 0x009c }} */
    @android.support.annotation.WorkerThread
    public java.lang.String a() {
        /*
        r7 = this;
        r0 = r7.e;
        r1 = 1;
        a(r0, r1);
        r0 = 0;
        r1 = new java.io.ByteArrayOutputStream;	 Catch:{ IOException -> 0x007f, all -> 0x0079 }
        r1.<init>();	 Catch:{ IOException -> 0x007f, all -> 0x0079 }
        r2 = new android.util.Base64OutputStream;	 Catch:{ IOException -> 0x0074, all -> 0x006f }
        r3 = 0;
        r2.<init>(r1, r3);	 Catch:{ IOException -> 0x0074, all -> 0x006f }
        r3 = new java.util.zip.DeflaterOutputStream;	 Catch:{ IOException -> 0x006a, all -> 0x0065 }
        r3.<init>(r2);	 Catch:{ IOException -> 0x006a, all -> 0x0065 }
        r0 = r7.b();	 Catch:{ IOException -> 0x0063 }
        r4 = com.facebook.ads.internal.d.b.b;	 Catch:{ IOException -> 0x0063 }
        r4 = android.text.TextUtils.isEmpty(r4);	 Catch:{ IOException -> 0x0063 }
        if (r4 == 0) goto L_0x002d;
    L_0x0023:
        r4 = r7.e;	 Catch:{ IOException -> 0x0063 }
        com.facebook.ads.internal.d.b.a(r4);	 Catch:{ IOException -> 0x0063 }
        r4 = r7.e;	 Catch:{ IOException -> 0x0063 }
        a(r4);	 Catch:{ IOException -> 0x0063 }
    L_0x002d:
        r4 = "IDFA";
        r5 = com.facebook.ads.internal.d.b.b;	 Catch:{ IOException -> 0x0063 }
        r0.put(r4, r5);	 Catch:{ IOException -> 0x0063 }
        r4 = new org.json.JSONObject;	 Catch:{ IOException -> 0x0063 }
        r4.<init>(r0);	 Catch:{ IOException -> 0x0063 }
        r0 = r4.toString();	 Catch:{ IOException -> 0x0063 }
        r0 = r0.getBytes();	 Catch:{ IOException -> 0x0063 }
        r3.write(r0);	 Catch:{ IOException -> 0x0063 }
        r3.close();	 Catch:{ IOException -> 0x0063 }
        r0 = r1.toString();	 Catch:{ IOException -> 0x0063 }
        r4 = "\n";
        r5 = "";
        r0 = r0.replaceAll(r4, r5);	 Catch:{ IOException -> 0x0063 }
        if (r3 == 0) goto L_0x0058;
    L_0x0055:
        r3.close();	 Catch:{ IOException -> 0x0062 }
    L_0x0058:
        if (r2 == 0) goto L_0x005d;
    L_0x005a:
        r2.close();	 Catch:{ IOException -> 0x0062 }
    L_0x005d:
        if (r1 == 0) goto L_0x0062;
    L_0x005f:
        r1.close();	 Catch:{ IOException -> 0x0062 }
    L_0x0062:
        return r0;
    L_0x0063:
        r0 = move-exception;
        goto L_0x0084;
    L_0x0065:
        r3 = move-exception;
        r6 = r3;
        r3 = r0;
        r0 = r6;
        goto L_0x008d;
    L_0x006a:
        r3 = move-exception;
        r6 = r3;
        r3 = r0;
        r0 = r6;
        goto L_0x0084;
    L_0x006f:
        r2 = move-exception;
        r3 = r0;
        r0 = r2;
        r2 = r3;
        goto L_0x008d;
    L_0x0074:
        r2 = move-exception;
        r3 = r0;
        r0 = r2;
        r2 = r3;
        goto L_0x0084;
    L_0x0079:
        r1 = move-exception;
        r2 = r0;
        r3 = r2;
        r0 = r1;
        r1 = r3;
        goto L_0x008d;
    L_0x007f:
        r1 = move-exception;
        r2 = r0;
        r3 = r2;
        r0 = r1;
        r1 = r3;
    L_0x0084:
        r4 = new java.lang.RuntimeException;	 Catch:{ all -> 0x008c }
        r5 = "Failed to build user token";
        r4.<init>(r5, r0);	 Catch:{ all -> 0x008c }
        throw r4;	 Catch:{ all -> 0x008c }
    L_0x008c:
        r0 = move-exception;
    L_0x008d:
        if (r3 == 0) goto L_0x0092;
    L_0x008f:
        r3.close();	 Catch:{ IOException -> 0x009c }
    L_0x0092:
        if (r2 == 0) goto L_0x0097;
    L_0x0094:
        r2.close();	 Catch:{ IOException -> 0x009c }
    L_0x0097:
        if (r1 == 0) goto L_0x009c;
    L_0x0099:
        r1.close();	 Catch:{ IOException -> 0x009c }
    L_0x009c:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.k.c.a():java.lang.String");
    }

    public Map<String, String> b() {
        a(this.e, false);
        com.facebook.ads.internal.i.a.a(this.e);
        HashMap hashMap = new HashMap();
        hashMap.put("SDK", "android");
        hashMap.put("SDK_VERSION", "5.0.0");
        hashMap.put("LOCALE", Locale.getDefault().toString());
        float f = y.b;
        int i = this.e.getResources().getDisplayMetrics().widthPixels;
        int i2 = this.e.getResources().getDisplayMetrics().heightPixels;
        hashMap.put("DENSITY", String.valueOf(f));
        hashMap.put("SCREEN_WIDTH", String.valueOf((int) (((float) i) / f)));
        hashMap.put("SCREEN_HEIGHT", String.valueOf((int) (((float) i2) / f)));
        hashMap.put("ATTRIBUTION_ID", com.facebook.ads.internal.d.b.a);
        hashMap.put("ID_SOURCE", com.facebook.ads.internal.d.b.d);
        hashMap.put("OS", InternalLogger.EVENT_PARAM_SDK_ANDROID);
        hashMap.put("OSVERS", b.a);
        hashMap.put("BUNDLE", this.f.f());
        hashMap.put("APPNAME", this.f.d());
        hashMap.put("APPVERS", this.f.g());
        hashMap.put("APPBUILD", String.valueOf(this.f.h()));
        hashMap.put("CARRIER", this.f.c());
        hashMap.put("MAKE", this.f.a());
        hashMap.put("MODEL", this.f.b());
        hashMap.put("ROOTED", String.valueOf(d.d));
        hashMap.put("INSTALLER", this.f.e());
        hashMap.put("SDK_CAPABILITY", com.facebook.ads.internal.s.a.c.b());
        hashMap.put("NETWORK_TYPE", String.valueOf(u.a(this.e).g));
        hashMap.put("SESSION_TIME", v.a(o.b()));
        hashMap.put("SESSION_ID", o.c());
        if (c != null) {
            hashMap.put("AFP", c);
        }
        String a = g.a(this.e);
        if (a != null) {
            hashMap.put("ASHAS", a);
        }
        hashMap.put("UNITY", String.valueOf(h.a(this.e)));
        a = AdInternalSettings.getMediationService();
        if (a != null) {
            hashMap.put("MEDIATION_SERVICE", a);
        }
        hashMap.put("ACCESSIBILITY_ENABLED", String.valueOf(this.f.i()));
        if (this.f.j() != -1) {
            hashMap.put("APP_MIN_SDK_VERSION", String.valueOf(this.f.j()));
        }
        hashMap.put("VALPARAMS", b.a(this.e));
        hashMap.put("ANALOG", k.a(com.facebook.ads.internal.i.a.a()));
        return hashMap;
    }
}
