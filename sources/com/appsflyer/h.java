package com.appsflyer;

import android.app.Activity;
import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Looper;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.appsflyer.y.AnonymousClass3;
import com.google.android.gms.common.GoogleApiAvailability;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class h implements s {
    static final String a = "4.8.13".substring(0, "4.8.13".indexOf("."));
    static final String b;
    static g c;
    private static final String f;
    private static String g;
    private static String h;
    private static String i;
    private static final List<String> l = Arrays.asList(new String[]{"is_cache"});
    private static final List<String> m = Arrays.asList(new String[]{"googleplay", "playstore", "googleplaystore"});
    private static f o;
    private static h w = new h();
    private boolean A = false;
    private boolean B = false;
    private String C;
    private boolean D;
    private boolean E;
    private p F = new p();
    private boolean G = false;
    private boolean H = false;
    String d;
    String e;
    private long j = -1;
    private long k = -1;
    private long n = TimeUnit.SECONDS.toMillis(5);
    private u p = null;
    private Map<String, String> q;
    private boolean r = false;
    private long s;
    private ScheduledExecutorService t = null;
    private long u;
    private long v;
    private b x;
    private Uri y = null;
    private long z;

    class a implements Runnable {
        private final Intent a;
        private WeakReference<Context> b;
        private String c;
        private String d;
        private String e;
        private String f;
        private ExecutorService g;
        private boolean h;
        private boolean i;

        /* synthetic */ a(h hVar, WeakReference weakReference, String str, String str2, String str3, String str4, ExecutorService executorService, boolean z, Intent intent, byte b) {
            this(weakReference, str, str2, str3, str4, executorService, z, intent);
        }

        private a(WeakReference<Context> weakReference, String str, String str2, String str3, String str4, boolean z, boolean z2, Intent intent) {
            this.b = weakReference;
            this.c = str;
            this.d = str2;
            this.e = str3;
            this.f = str4;
            this.h = true;
            this.g = z;
            this.i = z2;
            this.a = intent;
        }

        public final void run() {
            h.a(h.this, (Context) this.b.get(), this.c, this.d, this.e, this.f, this.h, this.i, this.a);
        }
    }

    abstract class b implements Runnable {
        WeakReference<Context> a = null;
        private String b;
        private ScheduledExecutorService c;
        private AtomicInteger d = new AtomicInteger(0);

        public abstract String a();

        public abstract void a(String str, int i);

        public abstract void a(Map<String, String> map);

        b(Context context, String str, ScheduledExecutorService scheduledExecutorService) {
            this.a = new WeakReference(context);
            this.b = str;
            if (scheduledExecutorService == null) {
                this.c = a.a().c();
            } else {
                this.c = scheduledExecutorService;
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:81:0x0257  */
        /* JADX WARNING: Removed duplicated region for block: B:72:0x0232 A:{Catch:{ all -> 0x0228 }} */
        /* JADX WARNING: Removed duplicated region for block: B:76:0x0247  */
        /* JADX WARNING: Missing block: B:83:0x025b, code skipped:
            return;
     */
        public void run() {
            /*
            r13 = this;
            r0 = r13.b;
            if (r0 == 0) goto L_0x025b;
        L_0x0004:
            r0 = r13.b;
            r0 = r0.length();
            if (r0 != 0) goto L_0x000e;
        L_0x000c:
            goto L_0x025b;
        L_0x000e:
            r0 = com.appsflyer.h.this;
            r0 = r0.d();
            if (r0 == 0) goto L_0x0017;
        L_0x0016:
            return;
        L_0x0017:
            r0 = r13.d;
            r0.incrementAndGet();
            r0 = 0;
            r1 = 0;
            r2 = r13.a;	 Catch:{ Throwable -> 0x022b }
            r2 = r2.get();	 Catch:{ Throwable -> 0x022b }
            r2 = (android.content.Context) r2;	 Catch:{ Throwable -> 0x022b }
            if (r2 != 0) goto L_0x002e;
        L_0x0028:
            r0 = r13.d;
            r0.decrementAndGet();
            return;
        L_0x002e:
            r3 = java.lang.System.currentTimeMillis();	 Catch:{ Throwable -> 0x022b }
            r5 = new java.lang.ref.WeakReference;	 Catch:{ Throwable -> 0x022b }
            r5.<init>(r2);	 Catch:{ Throwable -> 0x022b }
            r5 = com.appsflyer.h.b(r5);	 Catch:{ Throwable -> 0x022b }
            r5 = com.appsflyer.h.f(r2, r5);	 Catch:{ Throwable -> 0x022b }
            r6 = "";
            r7 = 1;
            if (r5 == 0) goto L_0x006a;
        L_0x0044:
            r8 = com.appsflyer.h.m;	 Catch:{ Throwable -> 0x022b }
            r9 = r5.toLowerCase();	 Catch:{ Throwable -> 0x022b }
            r8 = r8.contains(r9);	 Catch:{ Throwable -> 0x022b }
            if (r8 != 0) goto L_0x005d;
        L_0x0052:
            r6 = "-";
            r5 = java.lang.String.valueOf(r5);	 Catch:{ Throwable -> 0x022b }
            r6 = r6.concat(r5);	 Catch:{ Throwable -> 0x022b }
            goto L_0x006a;
        L_0x005d:
            r8 = "AF detected using redundant Google-Play channel for attribution - %s. Using without channel postfix.";
            r9 = new java.lang.Object[r7];	 Catch:{ Throwable -> 0x022b }
            r9[r1] = r5;	 Catch:{ Throwable -> 0x022b }
            r5 = java.lang.String.format(r8, r9);	 Catch:{ Throwable -> 0x022b }
            com.appsflyer.AFLogger.e(r5);	 Catch:{ Throwable -> 0x022b }
        L_0x006a:
            r5 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x022b }
            r5.<init>();	 Catch:{ Throwable -> 0x022b }
            r8 = r13.a();	 Catch:{ Throwable -> 0x022b }
            r5.append(r8);	 Catch:{ Throwable -> 0x022b }
            r8 = r2.getPackageName();	 Catch:{ Throwable -> 0x022b }
            r5.append(r8);	 Catch:{ Throwable -> 0x022b }
            r5.append(r6);	 Catch:{ Throwable -> 0x022b }
            r6 = "?devkey=";
            r5.append(r6);	 Catch:{ Throwable -> 0x022b }
            r6 = r13.b;	 Catch:{ Throwable -> 0x022b }
            r5.append(r6);	 Catch:{ Throwable -> 0x022b }
            r6 = "&device_id=";
            r5.append(r6);	 Catch:{ Throwable -> 0x022b }
            r6 = new java.lang.ref.WeakReference;	 Catch:{ Throwable -> 0x022b }
            r6.<init>(r2);	 Catch:{ Throwable -> 0x022b }
            r6 = com.appsflyer.ac.a(r6);	 Catch:{ Throwable -> 0x022b }
            r5.append(r6);	 Catch:{ Throwable -> 0x022b }
            r6 = com.appsflyer.ah.a();	 Catch:{ Throwable -> 0x022b }
            r8 = r5.toString();	 Catch:{ Throwable -> 0x022b }
            r9 = "";
            r6.a(r8, r9);	 Catch:{ Throwable -> 0x022b }
            r6 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x022b }
            r8 = "Calling server for attribution url: ";
            r6.<init>(r8);	 Catch:{ Throwable -> 0x022b }
            r8 = r5.toString();	 Catch:{ Throwable -> 0x022b }
            r6.append(r8);	 Catch:{ Throwable -> 0x022b }
            r6 = r6.toString();	 Catch:{ Throwable -> 0x022b }
            com.appsflyer.y.AnonymousClass3.b(r6);	 Catch:{ Throwable -> 0x022b }
            r6 = new java.net.URL;	 Catch:{ Throwable -> 0x022b }
            r8 = r5.toString();	 Catch:{ Throwable -> 0x022b }
            r6.<init>(r8);	 Catch:{ Throwable -> 0x022b }
            r6 = r6.openConnection();	 Catch:{ Throwable -> 0x022b }
            r6 = (java.net.HttpURLConnection) r6;	 Catch:{ Throwable -> 0x022b }
            r0 = "GET";
            r6.setRequestMethod(r0);	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            r0 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
            r6.setConnectTimeout(r0);	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            r0 = "Connection";
            r8 = "close";
            r6.setRequestProperty(r0, r8);	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            r6.connect();	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            r0 = r6.getResponseCode();	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            r8 = com.appsflyer.h.a(r6);	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            r9 = com.appsflyer.ah.a();	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            r10 = r5.toString();	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            r9.a(r10, r0, r8);	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            r9 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
            if (r0 != r9) goto L_0x01ec;
        L_0x00f7:
            r9 = java.lang.System.currentTimeMillis();	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            r0 = "appsflyerGetConversionDataTiming";
            r11 = r9 - r3;
            r3 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
            r11 = r11 / r3;
            com.appsflyer.h.b(r2, r0, r11);	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            r0 = "Attribution data: ";
            r3 = java.lang.String.valueOf(r8);	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            r0 = r0.concat(r3);	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            com.appsflyer.y.AnonymousClass3.b(r0);	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            r0 = r8.length();	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            if (r0 <= 0) goto L_0x0218;
        L_0x0118:
            if (r2 == 0) goto L_0x0218;
        L_0x011a:
            r0 = com.appsflyer.h.e(r8);	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            r3 = "iscache";
            r3 = r0.get(r3);	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            r3 = (java.lang.String) r3;	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            if (r3 == 0) goto L_0x013b;
        L_0x0128:
            r4 = java.lang.Boolean.toString(r1);	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            r4 = r4.equals(r3);	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            if (r4 == 0) goto L_0x013b;
        L_0x0132:
            r4 = "appsflyerConversionDataCacheExpiration";
            r9 = java.lang.System.currentTimeMillis();	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            com.appsflyer.h.b(r2, r4, r9);	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
        L_0x013b:
            r4 = "af_siteid";
            r4 = r0.containsKey(r4);	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            if (r4 == 0) goto L_0x0178;
        L_0x0143:
            r4 = "af_channel";
            r4 = r0.containsKey(r4);	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            if (r4 == 0) goto L_0x0165;
        L_0x014b:
            r4 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            r5 = "[Invite] Detected App-Invite via channel: ";
            r4.<init>(r5);	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            r5 = "af_channel";
            r5 = r0.get(r5);	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            r5 = (java.lang.String) r5;	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            r4.append(r5);	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            r4 = r4.toString();	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            com.appsflyer.AFLogger.c(r4);	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            goto L_0x0178;
        L_0x0165:
            r4 = "[CrossPromotion] App was installed via %s's Cross Promotion";
            r5 = new java.lang.Object[r7];	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            r9 = "af_siteid";
            r9 = r0.get(r9);	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            r5[r1] = r9;	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            r4 = java.lang.String.format(r4, r5);	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            com.appsflyer.AFLogger.c(r4);	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
        L_0x0178:
            r4 = "af_siteid";
            r4 = r0.containsKey(r4);	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            if (r4 == 0) goto L_0x0199;
        L_0x0180:
            r4 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            r5 = "[Invite] Detected App-Invite via channel: ";
            r4.<init>(r5);	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            r5 = "af_channel";
            r5 = r0.get(r5);	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            r5 = (java.lang.String) r5;	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            r4.append(r5);	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            r4 = r4.toString();	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            com.appsflyer.AFLogger.c(r4);	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
        L_0x0199:
            r4 = "is_first_launch";
            r5 = java.lang.Boolean.toString(r1);	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            r0.put(r4, r5);	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            r4 = new org.json.JSONObject;	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            r4.<init>(r0);	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            r4 = r4.toString();	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            if (r4 == 0) goto L_0x01b3;
        L_0x01ad:
            r5 = "attributionId";
            com.appsflyer.h.b(r2, r5, r4);	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            goto L_0x01b8;
        L_0x01b3:
            r4 = "attributionId";
            com.appsflyer.h.b(r2, r4, r8);	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
        L_0x01b8:
            r4 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            r5 = "iscache=";
            r4.<init>(r5);	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            r4.append(r3);	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            r3 = " caching conversion data";
            r4.append(r3);	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            r3 = r4.toString();	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            com.appsflyer.AFLogger.c(r3);	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            r3 = com.appsflyer.h.o;	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            if (r3 == 0) goto L_0x0218;
        L_0x01d4:
            r3 = r13.d;	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            r3 = r3.intValue();	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            if (r3 > r7) goto L_0x0218;
        L_0x01dc:
            r2 = com.appsflyer.h.e(r2);	 Catch:{ l -> 0x01e2 }
            r0 = r2;
            goto L_0x01e8;
        L_0x01e2:
            r2 = move-exception;
            r3 = "Exception while trying to fetch attribution data. ";
            com.appsflyer.AFLogger.a(r3, r2);	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
        L_0x01e8:
            r13.a(r0);	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            goto L_0x0218;
        L_0x01ec:
            r2 = com.appsflyer.h.o;	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            if (r2 == 0) goto L_0x01ff;
        L_0x01f2:
            r2 = "Error connection to server: ";
            r3 = java.lang.String.valueOf(r0);	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            r2 = r2.concat(r3);	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            r13.a(r2, r0);	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
        L_0x01ff:
            r2 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            r3 = "AttributionIdFetcher response code: ";
            r2.<init>(r3);	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            r2.append(r0);	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            r0 = "  url: ";
            r2.append(r0);	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            r2.append(r5);	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            r0 = r2.toString();	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
            com.appsflyer.y.AnonymousClass3.b(r0);	 Catch:{ Throwable -> 0x0225, all -> 0x0223 }
        L_0x0218:
            r0 = r13.d;
            r0.decrementAndGet();
            if (r6 == 0) goto L_0x024a;
        L_0x021f:
            r6.disconnect();
            goto L_0x024a;
        L_0x0223:
            r1 = move-exception;
            goto L_0x0250;
        L_0x0225:
            r2 = move-exception;
            r0 = r6;
            goto L_0x022c;
        L_0x0228:
            r1 = move-exception;
            r6 = r0;
            goto L_0x0250;
        L_0x022b:
            r2 = move-exception;
        L_0x022c:
            r3 = com.appsflyer.h.o;	 Catch:{ all -> 0x0228 }
            if (r3 == 0) goto L_0x0239;
        L_0x0232:
            r3 = r2.getMessage();	 Catch:{ all -> 0x0228 }
            r13.a(r3, r1);	 Catch:{ all -> 0x0228 }
        L_0x0239:
            r1 = r2.getMessage();	 Catch:{ all -> 0x0228 }
            com.appsflyer.AFLogger.a(r1, r2);	 Catch:{ all -> 0x0228 }
            r1 = r13.d;
            r1.decrementAndGet();
            if (r0 == 0) goto L_0x024a;
        L_0x0247:
            r0.disconnect();
        L_0x024a:
            r0 = r13.c;
            r0.shutdown();
            return;
        L_0x0250:
            r0 = r13.d;
            r0.decrementAndGet();
            if (r6 == 0) goto L_0x025a;
        L_0x0257:
            r6.disconnect();
        L_0x025a:
            throw r1;
        L_0x025b:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.h$b.run():void");
        }
    }

    class c implements Runnable {
        private WeakReference<Context> a = null;

        public c(Context context) {
            this.a = new WeakReference(context);
        }

        public final void run() {
            if (!h.this.r) {
                h.this.s = System.currentTimeMillis();
                if (this.a != null) {
                    h.this.r = true;
                    try {
                        String d = i.a().a("AppsFlyerKey");
                        synchronized (this.a) {
                            for (com.appsflyer.a.b bVar : com.appsflyer.a.a.a().b((Context) this.a.get())) {
                                StringBuilder stringBuilder = new StringBuilder("resending request: ");
                                stringBuilder.append(bVar.c());
                                AFLogger.d(stringBuilder.toString());
                                try {
                                    long currentTimeMillis = System.currentTimeMillis();
                                    long parseLong = Long.parseLong(bVar.d(), 10);
                                    h hVar = h.this;
                                    StringBuilder stringBuilder2 = new StringBuilder();
                                    stringBuilder2.append(bVar.c());
                                    stringBuilder2.append("&isCachedRequest=true&timeincache=");
                                    stringBuilder2.append(Long.toString((currentTimeMillis - parseLong) / 1000));
                                    h.a(hVar, stringBuilder2.toString(), bVar.b(), d, this.a, bVar.d(), false);
                                } catch (Exception e) {
                                    AFLogger.a("Failed to resend cached request", e);
                                }
                            }
                        }
                    } catch (Exception e2) {
                        try {
                            AFLogger.a("failed to check cache. ", e2);
                        } catch (Throwable th) {
                            h.this.r = false;
                        }
                    }
                    h.this.r = false;
                    h.this.t.shutdown();
                    h.this.t = null;
                }
            }
        }
    }

    class d extends b {
        public d(Context context, String str, ScheduledExecutorService scheduledExecutorService) {
            super(context, str, scheduledExecutorService);
        }

        public final String a() {
            return q.b("https://api.%s/install_data/v3/");
        }

        /* Access modifiers changed, original: protected|final */
        public final void a(Map<String, String> map) {
            map.put("is_first_launch", Boolean.toString(true));
            h.o.onInstallConversionDataLoaded(map);
            h.b((Context) this.a.get(), "appsflyerConversionDataRequestRetries", 0);
        }

        /* Access modifiers changed, original: protected|final */
        public final void a(String str, int i) {
            h.o.onInstallConversionFailure(str);
            if (i >= 400 && i < 500) {
                h.b((Context) this.a.get(), "appsflyerConversionDataRequestRetries", h.b((Context) this.a.get()).getInt("appsflyerConversionDataRequestRetries", 0) + 1);
            }
        }
    }

    class e implements Runnable {
        private String a;
        private WeakReference<Context> b;
        private Map<String, Object> c;
        private boolean d;
        private int e;

        /* synthetic */ e(h hVar, String str, Map map, Context context, boolean z, int i, byte b) {
            this(str, map, context, z, i);
        }

        private e(String str, Map<String, Object> map, Context context, boolean z, int i) {
            this.b = null;
            this.a = str;
            this.c = map;
            this.b = new WeakReference(context);
            this.d = z;
            this.e = i;
        }

        /* JADX WARNING: Removed duplicated region for block: B:17:0x0050 A:{ExcHandler: Throwable (r0_5 'th' java.lang.Throwable), Splitter:B:10:0x0028} */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing block: B:15:0x004b, code skipped:
            r0 = move-exception;
     */
        /* JADX WARNING: Missing block: B:16:0x004c, code skipped:
            r9 = r1;
            r1 = r0;
            r0 = r9;
     */
        /* JADX WARNING: Missing block: B:17:0x0050, code skipped:
            r0 = move-exception;
     */
        /* JADX WARNING: Missing block: B:18:0x0051, code skipped:
            com.appsflyer.AFLogger.a(r0.getMessage(), r0);
     */
        /* JADX WARNING: Missing block: B:19:0x0058, code skipped:
            return;
     */
        public final void run() {
            /*
            r10 = this;
            r0 = com.appsflyer.h.this;
            r0 = r0.d();
            if (r0 == 0) goto L_0x0009;
        L_0x0008:
            return;
        L_0x0009:
            r0 = 0;
            r1 = r10.d;
            if (r1 == 0) goto L_0x0028;
        L_0x000e:
            r1 = r10.e;
            r2 = 2;
            if (r1 > r2) goto L_0x0028;
        L_0x0013:
            r1 = com.appsflyer.h.this;
            r1 = com.appsflyer.h.a(r1);
            if (r1 == 0) goto L_0x0028;
        L_0x001b:
            r1 = r10.c;
            r2 = "rfr";
            r3 = com.appsflyer.h.this;
            r3 = r3.q;
            r1.put(r2, r3);
        L_0x0028:
            r1 = r10.c;	 Catch:{ IOException -> 0x0059, Throwable -> 0x0050 }
            r2 = "appsflyerKey";
            r1 = r1.get(r2);	 Catch:{ IOException -> 0x0059, Throwable -> 0x0050 }
            r5 = r1;
            r5 = (java.lang.String) r5;	 Catch:{ IOException -> 0x0059, Throwable -> 0x0050 }
            r1 = r10.c;	 Catch:{ IOException -> 0x0059, Throwable -> 0x0050 }
            r1 = com.appsflyer.b.a(r1);	 Catch:{ IOException -> 0x0059, Throwable -> 0x0050 }
            r1 = r1.toString();	 Catch:{ IOException -> 0x0059, Throwable -> 0x0050 }
            r2 = com.appsflyer.h.this;	 Catch:{ IOException -> 0x004b, Throwable -> 0x0050 }
            r3 = r10.a;	 Catch:{ IOException -> 0x004b, Throwable -> 0x0050 }
            r6 = r10.b;	 Catch:{ IOException -> 0x004b, Throwable -> 0x0050 }
            r7 = 0;
            r8 = r10.d;	 Catch:{ IOException -> 0x004b, Throwable -> 0x0050 }
            r4 = r1;
            com.appsflyer.h.a(r2, r3, r4, r5, r6, r7, r8);	 Catch:{ IOException -> 0x004b, Throwable -> 0x0050 }
            return;
        L_0x004b:
            r0 = move-exception;
            r9 = r1;
            r1 = r0;
            r0 = r9;
            goto L_0x005a;
        L_0x0050:
            r0 = move-exception;
            r1 = r0.getMessage();
            com.appsflyer.AFLogger.a(r1, r0);
            return;
        L_0x0059:
            r1 = move-exception;
        L_0x005a:
            r2 = "Exception while sending request to server. ";
            com.appsflyer.AFLogger.a(r2, r1);
            if (r0 == 0) goto L_0x008e;
        L_0x0061:
            r2 = r10.b;
            if (r2 == 0) goto L_0x008e;
        L_0x0065:
            r2 = r10.a;
            r3 = "&isCachedRequest=true&timeincache=";
            r2 = r2.contains(r3);
            if (r2 != 0) goto L_0x008e;
        L_0x006f:
            r2 = com.appsflyer.a.a.a();
            r3 = new com.appsflyer.a.b;
            r4 = r10.a;
            r5 = "4.8.13";
            r3.<init>(r4, r0, r5);
            r0 = r10.b;
            r0 = r0.get();
            r0 = (android.content.Context) r0;
            r2.a(r3, r0);
            r0 = r1.getMessage();
            com.appsflyer.AFLogger.a(r0, r1);
        L_0x008e:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.h$e.run():void");
        }
    }

    static /* synthetic */ void a(h hVar, String str, String str2, String str3, WeakReference weakReference, String str4, boolean z) throws IOException {
        URL url = new URL(str);
        StringBuilder stringBuilder = new StringBuilder("url: ");
        stringBuilder.append(url.toString());
        AFLogger.d(stringBuilder.toString());
        AnonymousClass3.b("data: ".concat(String.valueOf(str2)));
        a((Context) weakReference.get(), "AppsFlyer_4.8.13", "EVENT_DATA", str2);
        try {
            hVar.a(url, str2, str3, weakReference, str4, z);
        } catch (IOException e) {
            AFLogger.a("Exception in sendRequestToServer. ", e);
            if (i.a().b("useHttpFallback", false)) {
                hVar.a(new URL(str.replace("https:", "http:")), str2, str3, weakReference, str4, z);
                return;
            }
            StringBuilder stringBuilder2 = new StringBuilder("failed to send requeset to server. ");
            stringBuilder2.append(e.getLocalizedMessage());
            AFLogger.d(stringBuilder2.toString());
            a((Context) weakReference.get(), "AppsFlyer_4.8.13", "ERROR", e.getLocalizedMessage());
            throw e;
        }
    }

    static {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(a);
        stringBuilder.append("/androidevent?buildnumber=4.8.13&app_id=");
        f = stringBuilder.toString();
        stringBuilder = new StringBuilder("https://attr.%s/api/v");
        stringBuilder.append(f);
        g = stringBuilder.toString();
        stringBuilder = new StringBuilder("https://t.%s/api/v");
        stringBuilder.append(f);
        h = stringBuilder.toString();
        stringBuilder = new StringBuilder("https://events.%s/api/v");
        stringBuilder.append(f);
        i = stringBuilder.toString();
        stringBuilder = new StringBuilder("https://register.%s/api/v");
        stringBuilder.append(f);
        b = stringBuilder.toString();
    }

    /* Access modifiers changed, original: final */
    public final void a() {
        this.u = System.currentTimeMillis();
    }

    /* Access modifiers changed, original: final */
    public final void b() {
        this.v = System.currentTimeMillis();
    }

    /* Access modifiers changed, original: final */
    public final void a(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra("shouldMonitor");
        if (stringExtra != null) {
            AFLogger.d("Turning on monitoring.");
            i.a().a("shouldMonitor", stringExtra.equals("true"));
            a(context, null, "START_TRACKING", context.getPackageName());
            return;
        }
        AFLogger.d("****** onReceive called *******");
        i.a().b();
        String stringExtra2 = intent.getStringExtra("referrer");
        AFLogger.d("Play store referrer: ".concat(String.valueOf(stringExtra2)));
        if (stringExtra2 != null) {
            if ("AppsFlyer_Test".equals(intent.getStringExtra("TestIntegrationMode"))) {
                Editor edit = context.getSharedPreferences("appsflyer-data", 0).edit();
                edit.clear();
                if (VERSION.SDK_INT >= 9) {
                    edit.apply();
                } else {
                    edit.commit();
                }
                i.a().a(false);
                AFLogger.d("Test mode started..");
                this.z = System.currentTimeMillis();
            }
            Editor edit2 = context.getSharedPreferences("appsflyer-data", 0).edit();
            edit2.putString("referrer", stringExtra2);
            if (VERSION.SDK_INT >= 9) {
                edit2.apply();
            } else {
                edit2.commit();
            }
            i.a().b(stringExtra2);
            if (i.a().c()) {
                AFLogger.d("onReceive: isLaunchCalled");
                if (stringExtra2 != null && stringExtra2.length() > 5) {
                    ScheduledExecutorService c = a.a().c();
                    a(c, new a(this, new WeakReference(context.getApplicationContext()), null, null, null, stringExtra2, c, true, intent, (byte) 0), 5, TimeUnit.MILLISECONDS);
                }
            }
        }
    }

    private static void a(JSONObject jSONObject) {
        String str;
        ArrayList arrayList = new ArrayList();
        Iterator keys = jSONObject.keys();
        while (true) {
            int i = 0;
            if (!keys.hasNext()) {
                break;
            }
            try {
                JSONArray jSONArray = new JSONArray((String) jSONObject.get((String) keys.next()));
                while (i < jSONArray.length()) {
                    arrayList.add(Long.valueOf(jSONArray.getLong(i)));
                    i++;
                }
            } catch (JSONException unused) {
            }
        }
        Collections.sort(arrayList);
        keys = jSONObject.keys();
        loop2:
        while (true) {
            str = null;
            while (keys.hasNext() && str == null) {
                String str2 = (String) keys.next();
                try {
                    JSONArray jSONArray2 = new JSONArray((String) jSONObject.get(str2));
                    String str3 = str;
                    int i2 = 0;
                    while (i2 < jSONArray2.length()) {
                        try {
                            if (!(jSONArray2.getLong(i2) == ((Long) arrayList.get(0)).longValue() || jSONArray2.getLong(i2) == ((Long) arrayList.get(1)).longValue())) {
                                if (jSONArray2.getLong(i2) != ((Long) arrayList.get(arrayList.size() - 1)).longValue()) {
                                    i2++;
                                    str3 = str2;
                                }
                            }
                        } catch (JSONException unused2) {
                        }
                    }
                    str = str3;
                } catch (JSONException unused3) {
                }
            }
        }
        if (str != null) {
            jSONObject.remove(str);
        }
    }

    static void a(Context context, String str) {
        AFLogger.c("received a new (extra) referrer: ".concat(String.valueOf(str)));
        try {
            JSONObject jSONObject;
            JSONArray jSONArray;
            long currentTimeMillis = System.currentTimeMillis();
            String string = context.getSharedPreferences("appsflyer-data", 0).getString("extraReferrers", null);
            if (string == null) {
                JSONObject jSONObject2 = new JSONObject();
                JSONArray jSONArray2 = new JSONArray();
                jSONObject = jSONObject2;
                jSONArray = jSONArray2;
            } else {
                jSONObject = new JSONObject(string);
                if (jSONObject.has(str)) {
                    jSONArray = new JSONArray((String) jSONObject.get(str));
                } else {
                    jSONArray = new JSONArray();
                }
            }
            if (((long) jSONArray.length()) < 5) {
                jSONArray.put(currentTimeMillis);
            }
            if (((long) jSONObject.length()) >= 4) {
                a(jSONObject);
            }
            jSONObject.put(str, jSONArray.toString());
            String jSONObject3 = jSONObject.toString();
            Editor edit = context.getSharedPreferences("appsflyer-data", 0).edit();
            edit.putString("extraReferrers", jSONObject3);
            if (VERSION.SDK_INT >= 9) {
                edit.apply();
            } else {
                edit.commit();
            }
        } catch (JSONException unused) {
        } catch (Throwable th) {
            StringBuilder stringBuilder = new StringBuilder("Couldn't save referrer - ");
            stringBuilder.append(str);
            stringBuilder.append(": ");
            AFLogger.a(stringBuilder.toString(), th);
        }
    }

    private h() {
        e.a();
    }

    public static h c() {
        return w;
    }

    private void b(Application application) {
        i.a().b(application.getApplicationContext());
        if (VERSION.SDK_INT < 14) {
            AFLogger.d("SDK<14 call trackEvent manually");
            AFLogger.d("onBecameForeground");
            c().u = System.currentTimeMillis();
            c().b((Context) application, null, null);
            AFLogger.a();
        } else if (VERSION.SDK_INT >= 14 && this.x == null) {
            ae.a();
            this.x = new b() {
                public final void a(Activity activity) {
                    if (2 > h.a(h.b((Context) activity))) {
                        y a = y.a(activity);
                        a.a.post(a.f);
                        a.a.post(a.e);
                    }
                    AFLogger.d("onBecameForeground");
                    h.c().a();
                    h.c().b((Context) activity, null, null);
                    AFLogger.a();
                }

                public final void a(WeakReference<Context> weakReference) {
                    r.a((Context) weakReference.get());
                    y a = y.a((Context) weakReference.get());
                    a.a.post(a.f);
                }
            };
            ae.b().a(application, this.x);
        }
    }

    public void a(String str) {
        ah.a().a("enableUninstallTracking", str);
        i.a().a("gcmProjectNumber", str);
    }

    public void b(Context context, String str) {
        if (str != null) {
            ag.a(context, new r(str));
        }
    }

    public void b(String str) {
        ah.a().a("setCustomerUserId", str);
        AFLogger.d("setCustomerUserId = ".concat(String.valueOf(str)));
        i.a().a("AppUserId", str);
    }

    public void a(Activity activity) {
        if (activity != null && activity.getIntent() != null) {
            String[] strArr = new String[2];
            strArr[0] = activity.getLocalClassName();
            StringBuilder stringBuilder = new StringBuilder("activity_intent_");
            stringBuilder.append(activity.getIntent().toString());
            strArr[1] = stringBuilder.toString();
            ah.a().a("sendDeepLinkData", strArr);
        } else if (activity != null) {
            ah.a().a("sendDeepLinkData", activity.getLocalClassName(), "activity_intent_null");
        } else {
            ah.a().a("sendDeepLinkData", "activity_null");
        }
        StringBuilder stringBuilder2 = new StringBuilder("getDeepLinkData with activity ");
        stringBuilder2.append(activity.getIntent().getDataString());
        AFLogger.d(stringBuilder2.toString());
        b(activity.getApplication());
    }

    public h a(String str, f fVar) {
        ah a = ah.a();
        String str2 = "init";
        String[] strArr = new String[2];
        strArr[0] = str;
        strArr[1] = fVar == null ? "null" : "conversionDataListener";
        a.a(str2, strArr);
        AFLogger.b(String.format("Initializing AppsFlyer SDK: (v%s.%s)", new Object[]{"4.8.13", "388"}));
        this.E = true;
        i.a().a("AppsFlyerKey", str);
        AnonymousClass3.a(str);
        o = fVar;
        return this;
    }

    public h a(String str, f fVar, Context context) {
        if (context != null && d(context)) {
            if (this.p == null) {
                this.p = new u();
                this.p.a(context, this);
            } else {
                AFLogger.e("AFInstallReferrer instance already created");
            }
        }
        return a(str, fVar);
    }

    private static boolean d(@NonNull Context context) {
        if (a(context.getSharedPreferences("appsflyer-data", 0), "appsFlyerCount", false) > 2) {
            AFLogger.a("Install referrer will not load, the counter > 2, ");
            return false;
        }
        try {
            Class.forName("com.android.b.a.a");
            if (b.a(context, "com.google.android.finsky.permission.BIND_GET_INSTALL_REFERRER_SERVICE")) {
                AFLogger.c("Install referrer is allowed");
                return true;
            }
            AFLogger.c("Install referrer is not allowed");
            return false;
        } catch (ClassNotFoundException unused) {
            AFLogger.a("Class com.android.installreferrer.api.InstallReferrerClient not found");
            return false;
        } catch (Throwable th) {
            AFLogger.a("An error occurred while trying to verify manifest : com.android.installreferrer.api.InstallReferrerClient", th);
            return false;
        }
    }

    public void a(Application application) {
        if (this.E) {
            a(application, null);
        } else {
            AFLogger.e("ERROR: AppsFlyer SDK is not initialized! The API call 'startTracking(Application)' must be called after the 'init(String, AppsFlyerConversionListener)' API method, which should be called on the Application's onCreate.");
        }
    }

    public void a(Application application, String str) {
        ah.a().a("startTracking", str);
        AFLogger.d(String.format("Starting AppsFlyer Tracking: (v%s.%s)", new Object[]{"4.8.13", "388"}));
        AFLogger.d("Build Number: 388");
        i.a().b(application.getApplicationContext());
        if (TextUtils.isEmpty(str)) {
            if (TextUtils.isEmpty(i.a().a("AppsFlyerKey"))) {
                AFLogger.e("ERROR: AppsFlyer SDK is not initialized! You must provide AppsFlyer Dev-Key either in the 'init' API method (should be called on Application's onCreate),or in the startTracking API method (should be called on Activity's onCreate).");
                return;
            }
        }
        i.a().a("AppsFlyerKey", str);
        AnonymousClass3.a(str);
        b(application);
    }

    /* Access modifiers changed, original: final */
    public final void a(WeakReference<Context> weakReference) {
        if (weakReference.get() != null) {
            AFLogger.d("app went to background");
            SharedPreferences sharedPreferences = ((Context) weakReference.get()).getSharedPreferences("appsflyer-data", 0);
            i.a().a(sharedPreferences);
            long j = this.v - this.u;
            HashMap hashMap = new HashMap();
            String a = i.a().a("AppsFlyerKey");
            if (a == null) {
                AFLogger.e("[callStats] AppsFlyer's SDK cannot send any event without providing DevKey.");
                return;
            }
            Object a2 = i.a().a("KSAppsFlyerId");
            if (i.a().b("deviceTrackingDisabled", false)) {
                hashMap.put("deviceTrackingDisabled", "true");
            }
            m a3 = n.a(((Context) weakReference.get()).getContentResolver());
            if (a3 != null) {
                hashMap.put("amazon_aid", a3.a());
                hashMap.put("amazon_aid_limit", String.valueOf(a3.b()));
            }
            String a4 = i.a().a("advertiserId");
            if (a4 != null) {
                hashMap.put("advertiserId", a4);
            }
            hashMap.put("app_id", ((Context) weakReference.get()).getPackageName());
            hashMap.put("devkey", a);
            hashMap.put("uid", ac.a((WeakReference) weakReference));
            hashMap.put("time_in_app", String.valueOf(j / 1000));
            hashMap.put("statType", "user_closed_app");
            hashMap.put("platform", InternalLogger.EVENT_PARAM_SDK_ANDROID);
            hashMap.put("launch_counter", Integer.toString(a(sharedPreferences, "appsFlyerCount", false)));
            hashMap.put("gcd_conversion_data_timing", Long.toString(sharedPreferences.getLong("appsflyerGetConversionDataTiming", 0)));
            String str = "channel";
            Object a5 = i.a().a("channel");
            if (a5 == null) {
                a5 = a((WeakReference) weakReference, "CHANNEL");
            }
            hashMap.put(str, a5);
            String str2 = "originalAppsflyerId";
            if (a2 == null) {
                a2 = "";
            }
            hashMap.put(str2, a2);
            if (this.G) {
                try {
                    ab abVar = new ab(null, d());
                    abVar.a = hashMap;
                    if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                        AFLogger.c("Main thread detected. Running callStats task in a new thread.");
                        abVar.execute(new String[]{q.b("https://stats.%s/stats")});
                        return;
                    }
                    StringBuilder stringBuilder = new StringBuilder("Running callStats task (on current thread: ");
                    stringBuilder.append(Thread.currentThread().toString());
                    stringBuilder.append(" )");
                    AFLogger.c(stringBuilder.toString());
                    abVar.onPreExecute();
                    abVar.onPostExecute(abVar.doInBackground(q.b("https://stats.%s/stats")));
                    return;
                } catch (Throwable th) {
                    AFLogger.a("Could not send callStats request", th);
                    return;
                }
            }
            AFLogger.c("Stats call is disabled, ignore ...");
        }
    }

    public void a(Context context, String str, Map<String, Object> map) {
        JSONObject jSONObject = new JSONObject(map == null ? new HashMap() : map);
        ah.a().a("trackEvent", str, jSONObject.toString());
        b(context, str, (Map) map);
    }

    /* Access modifiers changed, original: final */
    public final void b(Context context, String str, Map<String, Object> map) {
        Intent intent = context instanceof Activity ? ((Activity) context).getIntent() : null;
        if (i.a().a("AppsFlyerKey") == null) {
            AFLogger.e("[TrackEvent/Launch] AppsFlyer's SDK cannot send any event without providing DevKey.");
            return;
        }
        Map map2;
        if (map2 == null) {
            map2 = new HashMap();
        }
        JSONObject jSONObject = new JSONObject(map2);
        String a = i.a().a(context);
        String jSONObject2 = jSONObject.toString();
        if (a == null) {
            a = "";
        }
        a(context, null, str, jSONObject2, a, intent);
    }

    private static void a(Context context, String str, String str2, String str3) {
        if (i.a().b("shouldMonitor", false)) {
            Intent intent = new Intent("com.appsflyer.MonitorBroadcast");
            intent.setPackage("com.appsflyer.nightvision");
            intent.putExtra("message", str2);
            intent.putExtra("value", str3);
            intent.putExtra("packageName", "true");
            intent.putExtra("pid", new Integer(Process.myPid()));
            intent.putExtra("eventIdentifier", str);
            intent.putExtra("sdk", "4.8.13");
            context.sendBroadcast(intent);
        }
    }

    /* Access modifiers changed, original: final */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0025  */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x001f  */
    public final void c(android.content.Context r11, java.lang.String r12) {
        /*
        r10 = this;
        r0 = "waitForCustomerId";
        r1 = com.appsflyer.i.a();
        r2 = 0;
        r0 = r1.b(r0, r2);
        r1 = 1;
        if (r0 == 0) goto L_0x001c;
    L_0x000e:
        r0 = "AppUserId";
        r3 = com.appsflyer.i.a();
        r0 = r3.a(r0);
        if (r0 != 0) goto L_0x001c;
    L_0x001a:
        r0 = r1;
        goto L_0x001d;
    L_0x001c:
        r0 = r2;
    L_0x001d:
        if (r0 == 0) goto L_0x0025;
    L_0x001f:
        r11 = "CustomerUserId not set, Tracking is disabled";
        com.appsflyer.AFLogger.a(r11, r1);
        return;
    L_0x0025:
        r0 = new java.util.HashMap;
        r0.<init>();
        r3 = "AppsFlyerKey";
        r4 = com.appsflyer.i.a();
        r3 = r4.a(r3);
        if (r3 != 0) goto L_0x003c;
    L_0x0036:
        r11 = "[registerUninstall] AppsFlyer's SDK cannot send any event without providing DevKey.";
        com.appsflyer.AFLogger.e(r11);
        return;
    L_0x003c:
        r4 = r11.getPackageManager();
        r5 = r11.getPackageName();
        r6 = r4.getPackageInfo(r5, r2);	 Catch:{ Throwable -> 0x008c }
        r7 = "app_version_code";
        r8 = r6.versionCode;	 Catch:{ Throwable -> 0x008c }
        r8 = java.lang.Integer.toString(r8);	 Catch:{ Throwable -> 0x008c }
        r0.put(r7, r8);	 Catch:{ Throwable -> 0x008c }
        r7 = "app_version_name";
        r8 = r6.versionName;	 Catch:{ Throwable -> 0x008c }
        r0.put(r7, r8);	 Catch:{ Throwable -> 0x008c }
        r7 = r6.applicationInfo;	 Catch:{ Throwable -> 0x008c }
        r4 = r4.getApplicationLabel(r7);	 Catch:{ Throwable -> 0x008c }
        r4 = r4.toString();	 Catch:{ Throwable -> 0x008c }
        r7 = "app_name";
        r0.put(r7, r4);	 Catch:{ Throwable -> 0x008c }
        r6 = r6.firstInstallTime;	 Catch:{ Throwable -> 0x008c }
        r4 = "yyyy-MM-dd_HHmmssZ";
        r8 = new java.text.SimpleDateFormat;	 Catch:{ Throwable -> 0x008c }
        r9 = java.util.Locale.US;	 Catch:{ Throwable -> 0x008c }
        r8.<init>(r4, r9);	 Catch:{ Throwable -> 0x008c }
        r4 = "installDate";
        r9 = "UTC";
        r9 = java.util.TimeZone.getTimeZone(r9);	 Catch:{ Throwable -> 0x008c }
        r8.setTimeZone(r9);	 Catch:{ Throwable -> 0x008c }
        r9 = new java.util.Date;	 Catch:{ Throwable -> 0x008c }
        r9.<init>(r6);	 Catch:{ Throwable -> 0x008c }
        r6 = r8.format(r9);	 Catch:{ Throwable -> 0x008c }
        r0.put(r4, r6);	 Catch:{ Throwable -> 0x008c }
        goto L_0x0092;
    L_0x008c:
        r4 = move-exception;
        r6 = "Exception while collecting application version info.";
        com.appsflyer.AFLogger.a(r6, r4);
    L_0x0092:
        a(r11, r0);
        r4 = "AppUserId";
        r6 = com.appsflyer.i.a();
        r4 = r6.a(r4);
        if (r4 == 0) goto L_0x00a6;
    L_0x00a1:
        r6 = "appUserId";
        r0.put(r6, r4);
    L_0x00a6:
        r4 = "model";
        r6 = android.os.Build.MODEL;	 Catch:{ Throwable -> 0x00b5 }
        r0.put(r4, r6);	 Catch:{ Throwable -> 0x00b5 }
        r4 = "brand";
        r6 = android.os.Build.BRAND;	 Catch:{ Throwable -> 0x00b5 }
        r0.put(r4, r6);	 Catch:{ Throwable -> 0x00b5 }
        goto L_0x00bb;
    L_0x00b5:
        r4 = move-exception;
        r6 = "Exception while collecting device brand and model.";
        com.appsflyer.AFLogger.a(r6, r4);
    L_0x00bb:
        r4 = com.appsflyer.i.a();
        r6 = "deviceTrackingDisabled";
        r4 = r4.b(r6, r2);
        if (r4 == 0) goto L_0x00ce;
    L_0x00c7:
        r4 = "deviceTrackingDisabled";
        r6 = "true";
        r0.put(r4, r6);
    L_0x00ce:
        r4 = r11.getContentResolver();
        r4 = com.appsflyer.n.a(r4);
        if (r4 == 0) goto L_0x00ee;
    L_0x00d8:
        r6 = "amazon_aid";
        r7 = r4.a();
        r0.put(r6, r7);
        r6 = "amazon_aid_limit";
        r4 = r4.b();
        r4 = java.lang.String.valueOf(r4);
        r0.put(r6, r4);
    L_0x00ee:
        r4 = com.appsflyer.i.a();
        r6 = "advertiserId";
        r4 = r4.a(r6);
        if (r4 == 0) goto L_0x00ff;
    L_0x00fa:
        r6 = "advertiserId";
        r0.put(r6, r4);
    L_0x00ff:
        r4 = "devkey";
        r0.put(r4, r3);
        r3 = "uid";
        r4 = new java.lang.ref.WeakReference;
        r4.<init>(r11);
        r4 = com.appsflyer.ac.a(r4);
        r0.put(r3, r4);
        r3 = "af_gcm_token";
        r0.put(r3, r12);
        r12 = "appsflyer-data";
        r12 = r11.getSharedPreferences(r12, r2);
        r3 = "appsFlyerCount";
        r12 = a(r12, r3, r2);
        r3 = "launch_counter";
        r12 = java.lang.Integer.toString(r12);
        r0.put(r3, r12);
        r12 = "sdk";
        r3 = android.os.Build.VERSION.SDK_INT;
        r3 = java.lang.Integer.toString(r3);
        r0.put(r12, r3);
        r12 = new java.lang.ref.WeakReference;
        r12.<init>(r11);
        r3 = com.appsflyer.i.a();
        r4 = "channel";
        r3 = r3.a(r4);
        if (r3 != 0) goto L_0x014e;
    L_0x0148:
        r3 = "CHANNEL";
        r3 = a(r12, r3);
    L_0x014e:
        if (r3 == 0) goto L_0x0155;
    L_0x0150:
        r12 = "channel";
        r0.put(r12, r3);
    L_0x0155:
        r12 = new com.appsflyer.ab;	 Catch:{ Throwable -> 0x017d }
        r3 = r10.d();	 Catch:{ Throwable -> 0x017d }
        r12.<init>(r11, r3);	 Catch:{ Throwable -> 0x017d }
        r12.a = r0;	 Catch:{ Throwable -> 0x017d }
        r11 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x017d }
        r11.<init>();	 Catch:{ Throwable -> 0x017d }
        r0 = b;	 Catch:{ Throwable -> 0x017d }
        r0 = com.appsflyer.q.b(r0);	 Catch:{ Throwable -> 0x017d }
        r11.append(r0);	 Catch:{ Throwable -> 0x017d }
        r11.append(r5);	 Catch:{ Throwable -> 0x017d }
        r11 = r11.toString();	 Catch:{ Throwable -> 0x017d }
        r0 = new java.lang.String[r1];	 Catch:{ Throwable -> 0x017d }
        r0[r2] = r11;	 Catch:{ Throwable -> 0x017d }
        r12.execute(r0);	 Catch:{ Throwable -> 0x017d }
        return;
    L_0x017d:
        r11 = move-exception;
        r12 = r11.getMessage();
        com.appsflyer.AFLogger.a(r12, r11);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.h.c(android.content.Context, java.lang.String):void");
    }

    private static Map<String, String> e(Context context) throws l {
        String string = context.getSharedPreferences("appsflyer-data", 0).getString("attributionId", null);
        if (string != null && string.length() > 0) {
            return e(string);
        }
        throw new l();
    }

    private static Map<String, String> e(Context context, String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        String[] split = str.split("&");
        int length = split.length;
        int i = 0;
        int i2 = i;
        while (i < length) {
            Object substring;
            String str2 = split[i];
            int indexOf = str2.indexOf("=");
            Object substring2 = indexOf > 0 ? str2.substring(0, indexOf) : str2;
            if (!linkedHashMap.containsKey(substring2)) {
                if (substring2.equals("c")) {
                    substring2 = "campaign";
                } else if (substring2.equals("pid")) {
                    substring2 = "media_source";
                } else if (substring2.equals("af_prt")) {
                    substring2 = "agency";
                    i2 = 1;
                }
                linkedHashMap.put(substring2, "");
            }
            if (indexOf > 0) {
                indexOf++;
                if (str2.length() > indexOf) {
                    substring = str2.substring(indexOf);
                    linkedHashMap.put(substring2, substring);
                    i++;
                }
            }
            substring = null;
            linkedHashMap.put(substring2, substring);
            i++;
        }
        try {
            if (!linkedHashMap.containsKey("install_time")) {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
                long j = packageInfo.firstInstallTime;
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                linkedHashMap.put("install_time", simpleDateFormat.format(new Date(j)));
            }
        } catch (Exception e) {
            AFLogger.a("Could not fetch install time. ", e);
        }
        if (!linkedHashMap.containsKey("af_status")) {
            linkedHashMap.put("af_status", "Non-organic");
        }
        if (i2 != 0) {
            linkedHashMap.remove("media_source");
        }
        return linkedHashMap;
    }

    private static Map<String, String> e(String str) {
        HashMap hashMap = new HashMap();
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str2 = (String) keys.next();
                if (!l.contains(str2)) {
                    String string = jSONObject.getString(str2);
                    if (!(TextUtils.isEmpty(string) || "null".equals(string))) {
                        hashMap.put(str2, string);
                    }
                }
            }
            return hashMap;
        } catch (JSONException e) {
            AFLogger.a(e.getMessage(), e);
            return null;
        }
    }

    private void a(Context context, String str, String str2, String str3, String str4, Intent intent) {
        Context applicationContext = context.getApplicationContext();
        boolean z = false;
        boolean z2 = str2 == null;
        if (i.a().b("waitForCustomerId", false)) {
            if (i.a().a("AppUserId") == null) {
                z = true;
            }
        }
        if (z) {
            AFLogger.a("CustomerUserId not set, Tracking is disabled", true);
            return;
        }
        h hVar;
        if (z2) {
            if (!i.a().b("launchProtectEnabled", true)) {
                AFLogger.d("Allowing multiple launches within a 5 second time window.");
            } else if (h()) {
                return;
            }
            hVar = this;
            hVar.j = System.currentTimeMillis();
        } else {
            hVar = this;
        }
        ScheduledExecutorService c = a.a().c();
        a(c, new a(hVar, new WeakReference(applicationContext), str, str2, str3, str4, c, false, intent, (byte) 0), 150, TimeUnit.MILLISECONDS);
    }

    private boolean h() {
        if (this.j > 0) {
            long currentTimeMillis = System.currentTimeMillis() - this.j;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS Z", Locale.US);
            long j = this.j;
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            String format = simpleDateFormat.format(new Date(j));
            j = this.k;
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            String format2 = simpleDateFormat.format(new Date(j));
            if (currentTimeMillis < this.n && !d()) {
                AFLogger.d(String.format(Locale.US, "Last Launch attempt: %s;\nLast successful Launch event: %s;\nThis launch is blocked: %s ms < %s ms", new Object[]{format, format2, Long.valueOf(currentTimeMillis), Long.valueOf(this.n)}));
                return true;
            } else if (!d()) {
                AFLogger.d(String.format(Locale.US, "Last Launch attempt: %s;\nLast successful Launch event: %s;\nSending launch (+%s ms)", new Object[]{format, format2, Long.valueOf(currentTimeMillis)}));
            }
        } else if (!d()) {
            AFLogger.d("Sending first launch for this session!");
        }
        return false;
    }

    /* Access modifiers changed, original: final */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x047e A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x047d A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x046e A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x047d A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x047e A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x045a A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x0462 A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x046e A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x047e A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x047d A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x0446 A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x044e A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x045a A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x0462 A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x046e A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x047d A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x047e A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x0426 A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x043a A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x0446 A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x044e A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x045a A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x0462 A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x046e A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x047e A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x047d A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:279:0x06f2 A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:278:0x06e7 A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:278:0x06e7 A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:279:0x06f2 A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:252:0x067c A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:251:0x0671 A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:275:0x06e1 A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:279:0x06f2 A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:278:0x06e7 A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:251:0x0671 A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:252:0x067c A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:255:0x0695 A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:275:0x06e1 A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:278:0x06e7 A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:279:0x06f2 A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:340:0x0872 A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:343:0x0882 A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:346:0x0893 A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:349:0x08bd A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:352:0x08c8 A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:357:0x08e8 A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:377:0x094d A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:386:0x0996 A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:389:0x09cf A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:395:0x09f7 A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:406:0x0a5d A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:405:0x0a5b A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:413:0x0a84 A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:416:0x0acc A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:434:0x0c2b A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:340:0x0872 A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:343:0x0882 A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:346:0x0893 A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:349:0x08bd A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:352:0x08c8 A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:357:0x08e8 A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:377:0x094d A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:380:0x0987 A:{SKIP, Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:386:0x0996 A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:389:0x09cf A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:395:0x09f7 A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:405:0x0a5b A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:406:0x0a5d A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:413:0x0a84 A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:416:0x0acc A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:434:0x0c2b A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:217:0x05bf A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:221:0x05da A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:220:0x05d1 A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:283:0x0702 A:{Catch:{ Exception -> 0x0708 }} */
    /* JADX WARNING: Removed duplicated region for block: B:309:0x077b A:{SYNTHETIC, Splitter:B:309:0x077b} */
    /* JADX WARNING: Removed duplicated region for block: B:321:0x07c2 A:{Catch:{ Throwable -> 0x0861 }} */
    /* JADX WARNING: Removed duplicated region for block: B:324:0x07e6 A:{Catch:{ Throwable -> 0x0861 }} */
    /* JADX WARNING: Removed duplicated region for block: B:340:0x0872 A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:343:0x0882 A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:346:0x0893 A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:349:0x08bd A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:352:0x08c8 A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:357:0x08e8 A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:377:0x094d A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:380:0x0987 A:{SKIP, Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:386:0x0996 A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:389:0x09cf A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:395:0x09f7 A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:406:0x0a5d A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:405:0x0a5b A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:413:0x0a84 A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:416:0x0acc A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    /* JADX WARNING: Removed duplicated region for block: B:434:0x0c2b A:{Catch:{ Exception -> 0x00ac, Throwable -> 0x0c4f }} */
    public final java.util.Map<java.lang.String, java.lang.Object> a(android.content.Context r19, java.lang.String r20, java.lang.String r21, java.lang.String r22, java.lang.String r23, boolean r24, android.content.SharedPreferences r25, boolean r26, android.content.Intent r27) {
        /*
        r18 = this;
        r1 = r18;
        r2 = r19;
        r3 = r20;
        r4 = r21;
        r5 = r22;
        r6 = r25;
        r7 = r26;
        r8 = new java.util.HashMap;
        r8.<init>();
        com.appsflyer.n.a(r2, r8);
        r9 = new java.util.Date;
        r9.<init>();
        r9 = r9.getTime();
        r11 = "af_timestamp";
        r12 = java.lang.Long.toString(r9);
        r8.put(r11, r12);
        r9 = com.appsflyer.t.a(r2, r9);
        if (r9 == 0) goto L_0x0033;
    L_0x002e:
        r10 = "cksm_v1";
        r8.put(r10, r9);
    L_0x0033:
        r9 = r18.d();	 Catch:{ Throwable -> 0x0c4f }
        if (r9 != 0) goto L_0x0051;
    L_0x0039:
        r9 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0c4f }
        r10 = "******* sendTrackingWithEvent: ";
        r9.<init>(r10);	 Catch:{ Throwable -> 0x0c4f }
        if (r7 == 0) goto L_0x0045;
    L_0x0042:
        r10 = "Launch";
        goto L_0x0046;
    L_0x0045:
        r10 = r4;
    L_0x0046:
        r9.append(r10);	 Catch:{ Throwable -> 0x0c4f }
        r9 = r9.toString();	 Catch:{ Throwable -> 0x0c4f }
        com.appsflyer.AFLogger.d(r9);	 Catch:{ Throwable -> 0x0c4f }
        goto L_0x0056;
    L_0x0051:
        r9 = "SDK tracking has been stopped";
        com.appsflyer.AFLogger.d(r9);	 Catch:{ Throwable -> 0x0c4f }
    L_0x0056:
        r9 = "AppsFlyer_4.8.13";
        r10 = "EVENT_CREATED_WITH_NAME";
        if (r7 == 0) goto L_0x005f;
    L_0x005c:
        r11 = "Launch";
        goto L_0x0060;
    L_0x005f:
        r11 = r4;
    L_0x0060:
        a(r2, r9, r10, r11);	 Catch:{ Throwable -> 0x0c4f }
        r9 = com.appsflyer.a.a.a();	 Catch:{ Throwable -> 0x0c4f }
        r9.a(r2);	 Catch:{ Throwable -> 0x0c4f }
        r9 = 0;
        r10 = r19.getPackageManager();	 Catch:{ Exception -> 0x00ac }
        r11 = r19.getPackageName();	 Catch:{ Exception -> 0x00ac }
        r12 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
        r10 = r10.getPackageInfo(r11, r12);	 Catch:{ Exception -> 0x00ac }
        r10 = r10.requestedPermissions;	 Catch:{ Exception -> 0x00ac }
        r10 = java.util.Arrays.asList(r10);	 Catch:{ Exception -> 0x00ac }
        r11 = "android.permission.INTERNET";
        r11 = r10.contains(r11);	 Catch:{ Exception -> 0x00ac }
        if (r11 != 0) goto L_0x0091;
    L_0x0087:
        r11 = "Permission android.permission.INTERNET is missing in the AndroidManifest.xml";
        com.appsflyer.AFLogger.e(r11);	 Catch:{ Exception -> 0x00ac }
        r11 = "PERMISSION_INTERNET_MISSING";
        a(r2, r9, r11, r9);	 Catch:{ Exception -> 0x00ac }
    L_0x0091:
        r11 = "android.permission.ACCESS_NETWORK_STATE";
        r11 = r10.contains(r11);	 Catch:{ Exception -> 0x00ac }
        if (r11 != 0) goto L_0x009e;
    L_0x0099:
        r11 = "Permission android.permission.ACCESS_NETWORK_STATE is missing in the AndroidManifest.xml";
        com.appsflyer.AFLogger.e(r11);	 Catch:{ Exception -> 0x00ac }
    L_0x009e:
        r11 = "android.permission.ACCESS_WIFI_STATE";
        r10 = r10.contains(r11);	 Catch:{ Exception -> 0x00ac }
        if (r10 != 0) goto L_0x00b3;
    L_0x00a6:
        r10 = "Permission android.permission.ACCESS_WIFI_STATE is missing in the AndroidManifest.xml";
        com.appsflyer.AFLogger.e(r10);	 Catch:{ Exception -> 0x00ac }
        goto L_0x00b3;
    L_0x00ac:
        r0 = move-exception;
        r10 = r0;
        r11 = "Exception while validation permissions. ";
        com.appsflyer.AFLogger.a(r11, r10);	 Catch:{ Throwable -> 0x0c4f }
    L_0x00b3:
        if (r24 == 0) goto L_0x00bc;
    L_0x00b5:
        r10 = "af_events_api";
        r11 = "1";
        r8.put(r10, r11);	 Catch:{ Throwable -> 0x0c4f }
    L_0x00bc:
        r10 = "brand";
        r11 = android.os.Build.BRAND;	 Catch:{ Throwable -> 0x0c4f }
        r8.put(r10, r11);	 Catch:{ Throwable -> 0x0c4f }
        r10 = "device";
        r11 = android.os.Build.DEVICE;	 Catch:{ Throwable -> 0x0c4f }
        r8.put(r10, r11);	 Catch:{ Throwable -> 0x0c4f }
        r10 = "product";
        r11 = android.os.Build.PRODUCT;	 Catch:{ Throwable -> 0x0c4f }
        r8.put(r10, r11);	 Catch:{ Throwable -> 0x0c4f }
        r10 = "sdk";
        r11 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Throwable -> 0x0c4f }
        r11 = java.lang.Integer.toString(r11);	 Catch:{ Throwable -> 0x0c4f }
        r8.put(r10, r11);	 Catch:{ Throwable -> 0x0c4f }
        r10 = "model";
        r11 = android.os.Build.MODEL;	 Catch:{ Throwable -> 0x0c4f }
        r8.put(r10, r11);	 Catch:{ Throwable -> 0x0c4f }
        r10 = "deviceType";
        r11 = android.os.Build.TYPE;	 Catch:{ Throwable -> 0x0c4f }
        r8.put(r10, r11);	 Catch:{ Throwable -> 0x0c4f }
        r13 = 0;
        r15 = 1;
        r12 = 0;
        if (r7 == 0) goto L_0x027c;
    L_0x00f0:
        r10 = "appsflyer-data";
        r10 = r2.getSharedPreferences(r10, r12);	 Catch:{ Throwable -> 0x0c4f }
        r11 = "appsFlyerCount";
        r10 = r10.contains(r11);	 Catch:{ Throwable -> 0x0c4f }
        r10 = r10 ^ r15;
        if (r10 == 0) goto L_0x022e;
    L_0x00ff:
        r10 = com.appsflyer.i.a();	 Catch:{ Throwable -> 0x0c4f }
        r10 = r10.f();	 Catch:{ Throwable -> 0x0c4f }
        if (r10 != 0) goto L_0x0192;
    L_0x0109:
        r10 = "af_sdks";
        r11 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0c4f }
        r11.<init>();	 Catch:{ Throwable -> 0x0c4f }
        r15 = "com.tune.Tune";
        r9 = r1.F;	 Catch:{ Throwable -> 0x0c4f }
        r9 = r9.a(r15);	 Catch:{ Throwable -> 0x0c4f }
        r11.append(r9);	 Catch:{ Throwable -> 0x0c4f }
        r9 = "com.adjust.sdk.Adjust";
        r15 = r1.F;	 Catch:{ Throwable -> 0x0c4f }
        r9 = r15.a(r9);	 Catch:{ Throwable -> 0x0c4f }
        r11.append(r9);	 Catch:{ Throwable -> 0x0c4f }
        r9 = "com.kochava.android.tracker.Feature";
        r15 = r1.F;	 Catch:{ Throwable -> 0x0c4f }
        r9 = r15.a(r9);	 Catch:{ Throwable -> 0x0c4f }
        r11.append(r9);	 Catch:{ Throwable -> 0x0c4f }
        r9 = "io.branch.referral.Branch";
        r15 = r1.F;	 Catch:{ Throwable -> 0x0c4f }
        r9 = r15.a(r9);	 Catch:{ Throwable -> 0x0c4f }
        r11.append(r9);	 Catch:{ Throwable -> 0x0c4f }
        r9 = "com.apsalar.sdk.Apsalar";
        r15 = r1.F;	 Catch:{ Throwable -> 0x0c4f }
        r9 = r15.a(r9);	 Catch:{ Throwable -> 0x0c4f }
        r11.append(r9);	 Catch:{ Throwable -> 0x0c4f }
        r9 = "com.localytics.android.Localytics";
        r15 = r1.F;	 Catch:{ Throwable -> 0x0c4f }
        r9 = r15.a(r9);	 Catch:{ Throwable -> 0x0c4f }
        r11.append(r9);	 Catch:{ Throwable -> 0x0c4f }
        r9 = "com.tenjin.android.TenjinSDK";
        r15 = r1.F;	 Catch:{ Throwable -> 0x0c4f }
        r9 = r15.a(r9);	 Catch:{ Throwable -> 0x0c4f }
        r11.append(r9);	 Catch:{ Throwable -> 0x0c4f }
        r9 = "place holder for TD";
        r15 = r1.F;	 Catch:{ Throwable -> 0x0c4f }
        r9 = r15.a(r9);	 Catch:{ Throwable -> 0x0c4f }
        r11.append(r9);	 Catch:{ Throwable -> 0x0c4f }
        r9 = "it.partytrack.sdk.Track";
        r15 = r1.F;	 Catch:{ Throwable -> 0x0c4f }
        r9 = r15.a(r9);	 Catch:{ Throwable -> 0x0c4f }
        r11.append(r9);	 Catch:{ Throwable -> 0x0c4f }
        r9 = "jp.appAdForce.android.LtvManager";
        r15 = r1.F;	 Catch:{ Throwable -> 0x0c4f }
        r9 = r15.a(r9);	 Catch:{ Throwable -> 0x0c4f }
        r11.append(r9);	 Catch:{ Throwable -> 0x0c4f }
        r9 = r11.toString();	 Catch:{ Throwable -> 0x0c4f }
        r8.put(r10, r9);	 Catch:{ Throwable -> 0x0c4f }
        r9 = h(r19);	 Catch:{ Throwable -> 0x0c4f }
        r10 = "batteryLevel";
        r9 = java.lang.String.valueOf(r9);	 Catch:{ Throwable -> 0x0c4f }
        r8.put(r10, r9);	 Catch:{ Throwable -> 0x0c4f }
    L_0x0192:
        r9 = 18;
        r10 = "OPPO";
        r11 = android.os.Build.BRAND;	 Catch:{ Throwable -> 0x0c4f }
        r10 = r10.equals(r11);	 Catch:{ Throwable -> 0x0c4f }
        if (r10 == 0) goto L_0x01a5;
    L_0x019e:
        r9 = 23;
        r10 = "OPPO device found";
        com.appsflyer.AFLogger.a(r10);	 Catch:{ Throwable -> 0x0c4f }
    L_0x01a5:
        r10 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Throwable -> 0x0c4f }
        if (r10 < r9) goto L_0x0216;
    L_0x01a9:
        r9 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0c4f }
        r10 = "OS SDK is=";
        r9.<init>(r10);	 Catch:{ Throwable -> 0x0c4f }
        r10 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Throwable -> 0x0c4f }
        r9.append(r10);	 Catch:{ Throwable -> 0x0c4f }
        r10 = "; use KeyStore";
        r9.append(r10);	 Catch:{ Throwable -> 0x0c4f }
        r9 = r9.toString();	 Catch:{ Throwable -> 0x0c4f }
        com.appsflyer.AFLogger.a(r9);	 Catch:{ Throwable -> 0x0c4f }
        r9 = new com.appsflyer.c;	 Catch:{ Throwable -> 0x0c4f }
        r9.<init>(r2);	 Catch:{ Throwable -> 0x0c4f }
        r10 = r9.b();	 Catch:{ Throwable -> 0x0c4f }
        if (r10 != 0) goto L_0x01f7;
    L_0x01cc:
        r10 = new java.lang.ref.WeakReference;	 Catch:{ Throwable -> 0x0c4f }
        r10.<init>(r2);	 Catch:{ Throwable -> 0x0c4f }
        r10 = com.appsflyer.ac.a(r10);	 Catch:{ Throwable -> 0x0c4f }
        r9.a(r10);	 Catch:{ Throwable -> 0x0c4f }
        r10 = "KSAppsFlyerId";
        r11 = r9.c();	 Catch:{ Throwable -> 0x0c4f }
        r15 = com.appsflyer.i.a();	 Catch:{ Throwable -> 0x0c4f }
        r15.a(r10, r11);	 Catch:{ Throwable -> 0x0c4f }
        r10 = "KSAppsFlyerRICounter";
        r9 = r9.d();	 Catch:{ Throwable -> 0x0c4f }
        r9 = java.lang.String.valueOf(r9);	 Catch:{ Throwable -> 0x0c4f }
        r11 = com.appsflyer.i.a();	 Catch:{ Throwable -> 0x0c4f }
    L_0x01f3:
        r11.a(r10, r9);	 Catch:{ Throwable -> 0x0c4f }
        goto L_0x022e;
    L_0x01f7:
        r9.a();	 Catch:{ Throwable -> 0x0c4f }
        r10 = "KSAppsFlyerId";
        r11 = r9.c();	 Catch:{ Throwable -> 0x0c4f }
        r15 = com.appsflyer.i.a();	 Catch:{ Throwable -> 0x0c4f }
        r15.a(r10, r11);	 Catch:{ Throwable -> 0x0c4f }
        r10 = "KSAppsFlyerRICounter";
        r9 = r9.d();	 Catch:{ Throwable -> 0x0c4f }
        r9 = java.lang.String.valueOf(r9);	 Catch:{ Throwable -> 0x0c4f }
        r11 = com.appsflyer.i.a();	 Catch:{ Throwable -> 0x0c4f }
        goto L_0x01f3;
    L_0x0216:
        r9 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0c4f }
        r10 = "OS SDK is=";
        r9.<init>(r10);	 Catch:{ Throwable -> 0x0c4f }
        r10 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Throwable -> 0x0c4f }
        r9.append(r10);	 Catch:{ Throwable -> 0x0c4f }
        r10 = "; no KeyStore usage";
        r9.append(r10);	 Catch:{ Throwable -> 0x0c4f }
        r9 = r9.toString();	 Catch:{ Throwable -> 0x0c4f }
        com.appsflyer.AFLogger.a(r9);	 Catch:{ Throwable -> 0x0c4f }
    L_0x022e:
        r9 = "timepassedsincelastlaunch";
        r10 = "appsflyer-data";
        r10 = r2.getSharedPreferences(r10, r12);	 Catch:{ Throwable -> 0x0c4f }
        r11 = "AppsFlyerTimePassedSincePrevLaunch";
        r10 = r10.getLong(r11, r13);	 Catch:{ Throwable -> 0x0c4f }
        r12 = java.lang.System.currentTimeMillis();	 Catch:{ Throwable -> 0x0c4f }
        r14 = "AppsFlyerTimePassedSincePrevLaunch";
        b(r2, r14, r12);	 Catch:{ Throwable -> 0x0c4f }
        r14 = 0;
        r16 = (r10 > r14 ? 1 : (r10 == r14 ? 0 : -1));
        if (r16 <= 0) goto L_0x0252;
    L_0x024b:
        r14 = r12 - r10;
        r10 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r10 = r14 / r10;
        goto L_0x0254;
    L_0x0252:
        r10 = -1;
    L_0x0254:
        r10 = java.lang.Long.toString(r10);	 Catch:{ Throwable -> 0x0c4f }
        r8.put(r9, r10);	 Catch:{ Throwable -> 0x0c4f }
        r9 = com.appsflyer.i.a();	 Catch:{ Throwable -> 0x0c4f }
        r10 = "oneLinkSlug";
        r9 = r9.a(r10);	 Catch:{ Throwable -> 0x0c4f }
        if (r9 == 0) goto L_0x02f0;
    L_0x0267:
        r10 = "onelink_id";
        r8.put(r10, r9);	 Catch:{ Throwable -> 0x0c4f }
        r9 = "ol_ver";
        r10 = com.appsflyer.i.a();	 Catch:{ Throwable -> 0x0c4f }
        r11 = "onelinkVersion";
        r10 = r10.a(r11);	 Catch:{ Throwable -> 0x0c4f }
        r8.put(r9, r10);	 Catch:{ Throwable -> 0x0c4f }
        goto L_0x02f0;
    L_0x027c:
        r9 = "appsflyer-data";
        r10 = 0;
        r9 = r2.getSharedPreferences(r9, r10);	 Catch:{ Throwable -> 0x0c4f }
        r10 = r9.edit();	 Catch:{ Throwable -> 0x0c4f }
        r11 = "prev_event_name";
        r12 = 0;
        r11 = r9.getString(r11, r12);	 Catch:{ Exception -> 0x02e9 }
        if (r11 == 0) goto L_0x02c8;
    L_0x0290:
        r12 = new org.json.JSONObject;	 Catch:{ Exception -> 0x02e9 }
        r12.<init>();	 Catch:{ Exception -> 0x02e9 }
        r13 = "prev_event_timestamp";
        r14 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x02e9 }
        r14.<init>();	 Catch:{ Exception -> 0x02e9 }
        r15 = "prev_event_timestamp";
        r6 = -1;
        r6 = r9.getLong(r15, r6);	 Catch:{ Exception -> 0x02e9 }
        r14.append(r6);	 Catch:{ Exception -> 0x02e9 }
        r6 = r14.toString();	 Catch:{ Exception -> 0x02e9 }
        r12.put(r13, r6);	 Catch:{ Exception -> 0x02e9 }
        r6 = "prev_event_value";
        r7 = "prev_event_value";
        r13 = 0;
        r7 = r9.getString(r7, r13);	 Catch:{ Exception -> 0x02e9 }
        r12.put(r6, r7);	 Catch:{ Exception -> 0x02e9 }
        r6 = "prev_event_name";
        r12.put(r6, r11);	 Catch:{ Exception -> 0x02e9 }
        r6 = "prev_event";
        r7 = r12.toString();	 Catch:{ Exception -> 0x02e9 }
        r8.put(r6, r7);	 Catch:{ Exception -> 0x02e9 }
    L_0x02c8:
        r6 = "prev_event_name";
        r10.putString(r6, r4);	 Catch:{ Exception -> 0x02e9 }
        r6 = "prev_event_value";
        r10.putString(r6, r5);	 Catch:{ Exception -> 0x02e9 }
        r6 = "prev_event_timestamp";
        r11 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x02e9 }
        r10.putLong(r6, r11);	 Catch:{ Exception -> 0x02e9 }
        r6 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x02e9 }
        r7 = 9;
        if (r6 < r7) goto L_0x02e5;
    L_0x02e1:
        r10.apply();	 Catch:{ Exception -> 0x02e9 }
        goto L_0x02f0;
    L_0x02e5:
        r10.commit();	 Catch:{ Exception -> 0x02e9 }
        goto L_0x02f0;
    L_0x02e9:
        r0 = move-exception;
        r6 = r0;
        r7 = "Error while processing previous event.";
        com.appsflyer.AFLogger.a(r7, r6);	 Catch:{ Throwable -> 0x0c4f }
    L_0x02f0:
        r6 = "KSAppsFlyerId";
        r7 = com.appsflyer.i.a();	 Catch:{ Throwable -> 0x0c4f }
        r6 = r7.a(r6);	 Catch:{ Throwable -> 0x0c4f }
        r7 = "KSAppsFlyerRICounter";
        r9 = com.appsflyer.i.a();	 Catch:{ Throwable -> 0x0c4f }
        r7 = r9.a(r7);	 Catch:{ Throwable -> 0x0c4f }
        if (r6 == 0) goto L_0x031c;
    L_0x0306:
        if (r7 == 0) goto L_0x031c;
    L_0x0308:
        r9 = java.lang.Integer.valueOf(r7);	 Catch:{ Throwable -> 0x0c4f }
        r9 = r9.intValue();	 Catch:{ Throwable -> 0x0c4f }
        if (r9 <= 0) goto L_0x031c;
    L_0x0312:
        r9 = "reinstallCounter";
        r8.put(r9, r7);	 Catch:{ Throwable -> 0x0c4f }
        r7 = "originalAppsflyerId";
        r8.put(r7, r6);	 Catch:{ Throwable -> 0x0c4f }
    L_0x031c:
        r6 = "additionalCustomData";
        r7 = com.appsflyer.i.a();	 Catch:{ Throwable -> 0x0c4f }
        r6 = r7.a(r6);	 Catch:{ Throwable -> 0x0c4f }
        if (r6 == 0) goto L_0x032d;
    L_0x0328:
        r7 = "customData";
        r8.put(r7, r6);	 Catch:{ Throwable -> 0x0c4f }
    L_0x032d:
        r6 = r19.getPackageManager();	 Catch:{ Exception -> 0x0341 }
        r7 = r19.getPackageName();	 Catch:{ Exception -> 0x0341 }
        r6 = r6.getInstallerPackageName(r7);	 Catch:{ Exception -> 0x0341 }
        if (r6 == 0) goto L_0x0348;
    L_0x033b:
        r7 = "installer_package";
        r8.put(r7, r6);	 Catch:{ Exception -> 0x0341 }
        goto L_0x0348;
    L_0x0341:
        r0 = move-exception;
        r6 = r0;
        r7 = "Exception while getting the app's installer package. ";
        com.appsflyer.AFLogger.a(r7, r6);	 Catch:{ Throwable -> 0x0c4f }
    L_0x0348:
        r6 = com.appsflyer.i.a();	 Catch:{ Throwable -> 0x0c4f }
        r7 = "sdkExtension";
        r6 = r6.a(r7);	 Catch:{ Throwable -> 0x0c4f }
        if (r6 == 0) goto L_0x035f;
    L_0x0354:
        r7 = r6.length();	 Catch:{ Throwable -> 0x0c4f }
        if (r7 <= 0) goto L_0x035f;
    L_0x035a:
        r7 = "sdkExtension";
        r8.put(r7, r6);	 Catch:{ Throwable -> 0x0c4f }
    L_0x035f:
        r6 = new java.lang.ref.WeakReference;	 Catch:{ Throwable -> 0x0c4f }
        r6.<init>(r2);	 Catch:{ Throwable -> 0x0c4f }
        r7 = com.appsflyer.i.a();	 Catch:{ Throwable -> 0x0c4f }
        r9 = "channel";
        r7 = r7.a(r9);	 Catch:{ Throwable -> 0x0c4f }
        if (r7 != 0) goto L_0x0376;
    L_0x0370:
        r7 = "CHANNEL";
        r7 = a(r6, r7);	 Catch:{ Throwable -> 0x0c4f }
    L_0x0376:
        r6 = f(r2, r7);	 Catch:{ Throwable -> 0x0c4f }
        if (r6 == 0) goto L_0x0381;
    L_0x037c:
        r9 = "channel";
        r8.put(r9, r6);	 Catch:{ Throwable -> 0x0c4f }
    L_0x0381:
        if (r6 == 0) goto L_0x0389;
    L_0x0383:
        r9 = r6.equals(r7);	 Catch:{ Throwable -> 0x0c4f }
        if (r9 == 0) goto L_0x038d;
    L_0x0389:
        if (r6 != 0) goto L_0x0392;
    L_0x038b:
        if (r7 == 0) goto L_0x0392;
    L_0x038d:
        r6 = "af_latestchannel";
        r8.put(r6, r7);	 Catch:{ Throwable -> 0x0c4f }
    L_0x0392:
        r6 = "appsflyer-data";
        r7 = 0;
        r6 = r2.getSharedPreferences(r6, r7);	 Catch:{ Throwable -> 0x0c4f }
        r7 = "INSTALL_STORE";
        r7 = r6.contains(r7);	 Catch:{ Throwable -> 0x0c4f }
        if (r7 == 0) goto L_0x03a9;
    L_0x03a1:
        r7 = "INSTALL_STORE";
        r9 = 0;
        r6 = r6.getString(r7, r9);	 Catch:{ Throwable -> 0x0c4f }
        goto L_0x03cd;
    L_0x03a9:
        r6 = "appsflyer-data";
        r7 = 0;
        r6 = r2.getSharedPreferences(r6, r7);	 Catch:{ Throwable -> 0x0c4f }
        r7 = "appsFlyerCount";
        r6 = r6.contains(r7);	 Catch:{ Throwable -> 0x0c4f }
        r7 = 1;
        r6 = r6 ^ r7;
        if (r6 == 0) goto L_0x03c7;
    L_0x03ba:
        r6 = new java.lang.ref.WeakReference;	 Catch:{ Throwable -> 0x0c4f }
        r6.<init>(r2);	 Catch:{ Throwable -> 0x0c4f }
        r7 = "AF_STORE";
        r9 = a(r6, r7);	 Catch:{ Throwable -> 0x0c4f }
        r6 = r9;
        goto L_0x03c8;
    L_0x03c7:
        r6 = 0;
    L_0x03c8:
        r7 = "INSTALL_STORE";
        b(r2, r7, r6);	 Catch:{ Throwable -> 0x0c4f }
    L_0x03cd:
        if (r6 == 0) goto L_0x03d8;
    L_0x03cf:
        r7 = "af_installstore";
        r6 = r6.toLowerCase();	 Catch:{ Throwable -> 0x0c4f }
        r8.put(r7, r6);	 Catch:{ Throwable -> 0x0c4f }
    L_0x03d8:
        r6 = "appsflyer-data";
        r7 = 0;
        r6 = r2.getSharedPreferences(r6, r7);	 Catch:{ Throwable -> 0x0c4f }
        r7 = "preInstallName";
        r9 = com.appsflyer.i.a();	 Catch:{ Throwable -> 0x0c4f }
        r7 = r9.a(r7);	 Catch:{ Throwable -> 0x0c4f }
        if (r7 != 0) goto L_0x049b;
    L_0x03eb:
        r9 = "preInstallName";
        r9 = r6.contains(r9);	 Catch:{ Throwable -> 0x0c4f }
        if (r9 == 0) goto L_0x03fd;
    L_0x03f3:
        r7 = "preInstallName";
        r9 = 0;
        r6 = r6.getString(r7, r9);	 Catch:{ Throwable -> 0x0c4f }
        r7 = r6;
        goto L_0x0490;
    L_0x03fd:
        r6 = "appsflyer-data";
        r9 = 0;
        r6 = r2.getSharedPreferences(r6, r9);	 Catch:{ Throwable -> 0x0c4f }
        r9 = "appsFlyerCount";
        r6 = r6.contains(r9);	 Catch:{ Throwable -> 0x0c4f }
        r9 = 1;
        r6 = r6 ^ r9;
        if (r6 == 0) goto L_0x0489;
    L_0x040e:
        r6 = "ro.appsflyer.preinstall.path";
        r6 = f(r6);	 Catch:{ Throwable -> 0x0c4f }
        r6 = g(r6);	 Catch:{ Throwable -> 0x0c4f }
        if (r6 == 0) goto L_0x0423;
    L_0x041a:
        r7 = r6.exists();	 Catch:{ Throwable -> 0x0c4f }
        if (r7 != 0) goto L_0x0421;
    L_0x0420:
        goto L_0x0423;
    L_0x0421:
        r7 = 0;
        goto L_0x0424;
    L_0x0423:
        r7 = 1;
    L_0x0424:
        if (r7 == 0) goto L_0x0438;
    L_0x0426:
        r6 = "AF_PRE_INSTALL_PATH";
        r7 = r19.getPackageManager();	 Catch:{ Throwable -> 0x0c4f }
        r9 = r19.getPackageName();	 Catch:{ Throwable -> 0x0c4f }
        r6 = a(r6, r7, r9);	 Catch:{ Throwable -> 0x0c4f }
        r6 = g(r6);	 Catch:{ Throwable -> 0x0c4f }
    L_0x0438:
        if (r6 == 0) goto L_0x0443;
    L_0x043a:
        r7 = r6.exists();	 Catch:{ Throwable -> 0x0c4f }
        if (r7 != 0) goto L_0x0441;
    L_0x0440:
        goto L_0x0443;
    L_0x0441:
        r7 = 0;
        goto L_0x0444;
    L_0x0443:
        r7 = 1;
    L_0x0444:
        if (r7 == 0) goto L_0x044c;
    L_0x0446:
        r6 = "/data/local/tmp/pre_install.appsflyer";
        r6 = g(r6);	 Catch:{ Throwable -> 0x0c4f }
    L_0x044c:
        if (r6 == 0) goto L_0x0457;
    L_0x044e:
        r7 = r6.exists();	 Catch:{ Throwable -> 0x0c4f }
        if (r7 != 0) goto L_0x0455;
    L_0x0454:
        goto L_0x0457;
    L_0x0455:
        r7 = 0;
        goto L_0x0458;
    L_0x0457:
        r7 = 1;
    L_0x0458:
        if (r7 == 0) goto L_0x0460;
    L_0x045a:
        r6 = "/etc/pre_install.appsflyer";
        r6 = g(r6);	 Catch:{ Throwable -> 0x0c4f }
    L_0x0460:
        if (r6 == 0) goto L_0x046b;
    L_0x0462:
        r7 = r6.exists();	 Catch:{ Throwable -> 0x0c4f }
        if (r7 != 0) goto L_0x0469;
    L_0x0468:
        goto L_0x046b;
    L_0x0469:
        r7 = 0;
        goto L_0x046c;
    L_0x046b:
        r7 = 1;
    L_0x046c:
        if (r7 != 0) goto L_0x047a;
    L_0x046e:
        r7 = r19.getPackageName();	 Catch:{ Throwable -> 0x0c4f }
        r9 = a(r6, r7);	 Catch:{ Throwable -> 0x0c4f }
        if (r9 == 0) goto L_0x047a;
    L_0x0478:
        r7 = r9;
        goto L_0x047b;
    L_0x047a:
        r7 = 0;
    L_0x047b:
        if (r7 == 0) goto L_0x047e;
    L_0x047d:
        goto L_0x0489;
    L_0x047e:
        r6 = new java.lang.ref.WeakReference;	 Catch:{ Throwable -> 0x0c4f }
        r6.<init>(r2);	 Catch:{ Throwable -> 0x0c4f }
        r7 = "AF_PRE_INSTALL_NAME";
        r7 = a(r6, r7);	 Catch:{ Throwable -> 0x0c4f }
    L_0x0489:
        if (r7 == 0) goto L_0x0490;
    L_0x048b:
        r6 = "preInstallName";
        b(r2, r6, r7);	 Catch:{ Throwable -> 0x0c4f }
    L_0x0490:
        if (r7 == 0) goto L_0x049b;
    L_0x0492:
        r6 = "preInstallName";
        r9 = com.appsflyer.i.a();	 Catch:{ Throwable -> 0x0c4f }
        r9.a(r6, r7);	 Catch:{ Throwable -> 0x0c4f }
    L_0x049b:
        if (r7 == 0) goto L_0x04a6;
    L_0x049d:
        r6 = "af_preinstall_name";
        r7 = r7.toLowerCase();	 Catch:{ Throwable -> 0x0c4f }
        r8.put(r6, r7);	 Catch:{ Throwable -> 0x0c4f }
    L_0x04a6:
        r6 = new java.lang.ref.WeakReference;	 Catch:{ Throwable -> 0x0c4f }
        r6.<init>(r2);	 Catch:{ Throwable -> 0x0c4f }
        r7 = "AF_STORE";
        r6 = a(r6, r7);	 Catch:{ Throwable -> 0x0c4f }
        if (r6 == 0) goto L_0x04bc;
    L_0x04b3:
        r7 = "af_currentstore";
        r6 = r6.toLowerCase();	 Catch:{ Throwable -> 0x0c4f }
        r8.put(r7, r6);	 Catch:{ Throwable -> 0x0c4f }
    L_0x04bc:
        if (r3 == 0) goto L_0x04ca;
    L_0x04be:
        r6 = r20.length();	 Catch:{ Throwable -> 0x0c4f }
        if (r6 < 0) goto L_0x04ca;
    L_0x04c4:
        r6 = "appsflyerKey";
        r8.put(r6, r3);	 Catch:{ Throwable -> 0x0c4f }
        goto L_0x04e1;
    L_0x04ca:
        r3 = "AppsFlyerKey";
        r6 = com.appsflyer.i.a();	 Catch:{ Throwable -> 0x0c4f }
        r3 = r6.a(r3);	 Catch:{ Throwable -> 0x0c4f }
        if (r3 == 0) goto L_0x0c3c;
    L_0x04d6:
        r6 = r3.length();	 Catch:{ Throwable -> 0x0c4f }
        if (r6 < 0) goto L_0x0c3c;
    L_0x04dc:
        r6 = "appsflyerKey";
        r8.put(r6, r3);	 Catch:{ Throwable -> 0x0c4f }
    L_0x04e1:
        r3 = "AppUserId";
        r6 = com.appsflyer.i.a();	 Catch:{ Throwable -> 0x0c4f }
        r3 = r6.a(r3);	 Catch:{ Throwable -> 0x0c4f }
        if (r3 == 0) goto L_0x04f2;
    L_0x04ed:
        r6 = "appUserId";
        r8.put(r6, r3);	 Catch:{ Throwable -> 0x0c4f }
    L_0x04f2:
        r3 = com.appsflyer.i.a();	 Catch:{ Throwable -> 0x0c4f }
        r6 = "userEmails";
        r3 = r3.a(r6);	 Catch:{ Throwable -> 0x0c4f }
        if (r3 == 0) goto L_0x0504;
    L_0x04fe:
        r6 = "user_emails";
        r8.put(r6, r3);	 Catch:{ Throwable -> 0x0c4f }
        goto L_0x0519;
    L_0x0504:
        r3 = "userEmail";
        r6 = com.appsflyer.i.a();	 Catch:{ Throwable -> 0x0c4f }
        r3 = r6.a(r3);	 Catch:{ Throwable -> 0x0c4f }
        if (r3 == 0) goto L_0x0519;
    L_0x0510:
        r6 = "sha1_el";
        r3 = com.appsflyer.ad.a(r3);	 Catch:{ Throwable -> 0x0c4f }
        r8.put(r6, r3);	 Catch:{ Throwable -> 0x0c4f }
    L_0x0519:
        if (r4 == 0) goto L_0x0527;
    L_0x051b:
        r3 = "eventName";
        r8.put(r3, r4);	 Catch:{ Throwable -> 0x0c4f }
        if (r5 == 0) goto L_0x0527;
    L_0x0522:
        r3 = "eventValue";
        r8.put(r3, r5);	 Catch:{ Throwable -> 0x0c4f }
    L_0x0527:
        r3 = "appid";
        r5 = com.appsflyer.i.a();	 Catch:{ Throwable -> 0x0c4f }
        r3 = r5.a(r3);	 Catch:{ Throwable -> 0x0c4f }
        if (r3 == 0) goto L_0x0542;
    L_0x0533:
        r3 = "appid";
        r5 = "appid";
        r6 = com.appsflyer.i.a();	 Catch:{ Throwable -> 0x0c4f }
        r5 = r6.a(r5);	 Catch:{ Throwable -> 0x0c4f }
        r8.put(r3, r5);	 Catch:{ Throwable -> 0x0c4f }
    L_0x0542:
        r3 = "currencyCode";
        r5 = com.appsflyer.i.a();	 Catch:{ Throwable -> 0x0c4f }
        r3 = r5.a(r3);	 Catch:{ Throwable -> 0x0c4f }
        r5 = 3;
        if (r3 == 0) goto L_0x0570;
    L_0x054f:
        r6 = r3.length();	 Catch:{ Throwable -> 0x0c4f }
        if (r6 == r5) goto L_0x056b;
    L_0x0555:
        r6 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0c4f }
        r7 = "WARNING: currency code should be 3 characters!!! '";
        r6.<init>(r7);	 Catch:{ Throwable -> 0x0c4f }
        r6.append(r3);	 Catch:{ Throwable -> 0x0c4f }
        r7 = "' is not a legal value.";
        r6.append(r7);	 Catch:{ Throwable -> 0x0c4f }
        r6 = r6.toString();	 Catch:{ Throwable -> 0x0c4f }
        com.appsflyer.AFLogger.e(r6);	 Catch:{ Throwable -> 0x0c4f }
    L_0x056b:
        r6 = "currency";
        r8.put(r6, r3);	 Catch:{ Throwable -> 0x0c4f }
    L_0x0570:
        r3 = "IS_UPDATE";
        r6 = com.appsflyer.i.a();	 Catch:{ Throwable -> 0x0c4f }
        r3 = r6.a(r3);	 Catch:{ Throwable -> 0x0c4f }
        if (r3 == 0) goto L_0x0581;
    L_0x057c:
        r6 = "isUpdate";
        r8.put(r6, r3);	 Catch:{ Throwable -> 0x0c4f }
    L_0x0581:
        r3 = r18.a(r19);	 Catch:{ Throwable -> 0x0c4f }
        r6 = "af_preinstalled";
        r3 = java.lang.Boolean.toString(r3);	 Catch:{ Throwable -> 0x0c4f }
        r8.put(r6, r3);	 Catch:{ Throwable -> 0x0c4f }
        r3 = com.appsflyer.i.a();	 Catch:{ Throwable -> 0x0c4f }
        r6 = "collectFacebookAttrId";
        r7 = 1;
        r3 = r3.b(r6, r7);	 Catch:{ Throwable -> 0x0c4f }
        if (r3 == 0) goto L_0x05c4;
    L_0x059b:
        r3 = r19.getPackageManager();	 Catch:{ NameNotFoundException -> 0x05b7, Throwable -> 0x05ae }
        r6 = "com.facebook.katana";
        r7 = 0;
        r3.getApplicationInfo(r6, r7);	 Catch:{ NameNotFoundException -> 0x05b7, Throwable -> 0x05ae }
        r3 = r19.getContentResolver();	 Catch:{ NameNotFoundException -> 0x05b7, Throwable -> 0x05ae }
        r9 = r1.a(r3);	 Catch:{ NameNotFoundException -> 0x05b7, Throwable -> 0x05ae }
        goto L_0x05bd;
    L_0x05ae:
        r0 = move-exception;
        r3 = r0;
        r6 = "Exception while collecting facebook's attribution ID. ";
        com.appsflyer.AFLogger.a(r6, r3);	 Catch:{ Throwable -> 0x0c4f }
    L_0x05b5:
        r9 = 0;
        goto L_0x05bd;
    L_0x05b7:
        r3 = "Exception while collecting facebook's attribution ID. ";
        com.appsflyer.AFLogger.e(r3);	 Catch:{ Throwable -> 0x0c4f }
        goto L_0x05b5;
    L_0x05bd:
        if (r9 == 0) goto L_0x05c4;
    L_0x05bf:
        r3 = "fb";
        r8.put(r3, r9);	 Catch:{ Throwable -> 0x0c4f }
    L_0x05c4:
        r3 = com.appsflyer.i.a();	 Catch:{ Throwable -> 0x0c4f }
        r6 = "deviceTrackingDisabled";
        r7 = 0;
        r3 = r3.b(r6, r7);	 Catch:{ Throwable -> 0x0c4f }
        if (r3 == 0) goto L_0x05da;
    L_0x05d1:
        r3 = "deviceTrackingDisabled";
        r6 = "true";
        r8.put(r3, r6);	 Catch:{ Throwable -> 0x0c4f }
        goto L_0x06f7;
    L_0x05da:
        r3 = "appsflyer-data";
        r6 = 0;
        r3 = r2.getSharedPreferences(r3, r6);	 Catch:{ Throwable -> 0x0c4f }
        r6 = com.appsflyer.i.a();	 Catch:{ Throwable -> 0x0c4f }
        r7 = "collectIMEI";
        r9 = 1;
        r6 = r6.b(r7, r9);	 Catch:{ Throwable -> 0x0c4f }
        r7 = "imeiCached";
        r9 = 0;
        r7 = r3.getString(r7, r9);	 Catch:{ Throwable -> 0x0c4f }
        if (r6 == 0) goto L_0x0667;
    L_0x05f5:
        r6 = r1.d;	 Catch:{ Throwable -> 0x0c4f }
        r6 = android.text.TextUtils.isEmpty(r6);	 Catch:{ Throwable -> 0x0c4f }
        if (r6 == 0) goto L_0x0667;
    L_0x05fd:
        r6 = g(r19);	 Catch:{ Throwable -> 0x0c4f }
        if (r6 == 0) goto L_0x066e;
    L_0x0603:
        r6 = "phone";
        r6 = r2.getSystemService(r6);	 Catch:{ InvocationTargetException -> 0x064f, Exception -> 0x0635 }
        r6 = (android.telephony.TelephonyManager) r6;	 Catch:{ InvocationTargetException -> 0x064f, Exception -> 0x0635 }
        r9 = r6.getClass();	 Catch:{ InvocationTargetException -> 0x064f, Exception -> 0x0635 }
        r10 = "getDeviceId";
        r11 = 0;
        r12 = new java.lang.Class[r11];	 Catch:{ InvocationTargetException -> 0x064f, Exception -> 0x0635 }
        r9 = r9.getMethod(r10, r12);	 Catch:{ InvocationTargetException -> 0x064f, Exception -> 0x0635 }
        r10 = new java.lang.Object[r11];	 Catch:{ InvocationTargetException -> 0x064f, Exception -> 0x0635 }
        r6 = r9.invoke(r6, r10);	 Catch:{ InvocationTargetException -> 0x064f, Exception -> 0x0635 }
        r9 = r6;
        r9 = (java.lang.String) r9;	 Catch:{ InvocationTargetException -> 0x064f, Exception -> 0x0635 }
        if (r9 == 0) goto L_0x0624;
    L_0x0623:
        goto L_0x066f;
    L_0x0624:
        if (r7 == 0) goto L_0x066e;
    L_0x0626:
        r6 = "use cached IMEI: ";
        r9 = java.lang.String.valueOf(r7);	 Catch:{ InvocationTargetException -> 0x064f, Exception -> 0x0635 }
        r6 = r6.concat(r9);	 Catch:{ InvocationTargetException -> 0x064f, Exception -> 0x0635 }
        com.appsflyer.AFLogger.c(r6);	 Catch:{ InvocationTargetException -> 0x064f, Exception -> 0x0635 }
        r9 = r7;
        goto L_0x066f;
    L_0x0635:
        r0 = move-exception;
        r6 = r0;
        if (r7 == 0) goto L_0x0648;
    L_0x0639:
        r9 = "use cached IMEI: ";
        r10 = java.lang.String.valueOf(r7);	 Catch:{ Throwable -> 0x0c4f }
        r9 = r9.concat(r10);	 Catch:{ Throwable -> 0x0c4f }
        com.appsflyer.AFLogger.c(r9);	 Catch:{ Throwable -> 0x0c4f }
        r9 = r7;
        goto L_0x0649;
    L_0x0648:
        r9 = 0;
    L_0x0649:
        r7 = "WARNING: other reason: ";
        com.appsflyer.AFLogger.a(r7, r6);	 Catch:{ Throwable -> 0x0c4f }
        goto L_0x066f;
    L_0x064f:
        if (r7 == 0) goto L_0x0660;
    L_0x0651:
        r6 = "use cached IMEI: ";
        r9 = java.lang.String.valueOf(r7);	 Catch:{ Throwable -> 0x0c4f }
        r6 = r6.concat(r9);	 Catch:{ Throwable -> 0x0c4f }
        com.appsflyer.AFLogger.c(r6);	 Catch:{ Throwable -> 0x0c4f }
        r9 = r7;
        goto L_0x0661;
    L_0x0660:
        r9 = 0;
    L_0x0661:
        r6 = "WARNING: READ_PHONE_STATE is missing.";
        com.appsflyer.AFLogger.e(r6);	 Catch:{ Throwable -> 0x0c4f }
        goto L_0x066f;
    L_0x0667:
        r6 = r1.d;	 Catch:{ Throwable -> 0x0c4f }
        if (r6 == 0) goto L_0x066e;
    L_0x066b:
        r9 = r1.d;	 Catch:{ Throwable -> 0x0c4f }
        goto L_0x066f;
    L_0x066e:
        r9 = 0;
    L_0x066f:
        if (r9 == 0) goto L_0x067c;
    L_0x0671:
        r6 = "imeiCached";
        b(r2, r6, r9);	 Catch:{ Throwable -> 0x0c4f }
        r6 = "imei";
        r8.put(r6, r9);	 Catch:{ Throwable -> 0x0c4f }
        goto L_0x0681;
    L_0x067c:
        r6 = "IMEI was not collected.";
        com.appsflyer.AFLogger.d(r6);	 Catch:{ Throwable -> 0x0c4f }
    L_0x0681:
        r6 = com.appsflyer.i.a();	 Catch:{ Throwable -> 0x0c4f }
        r7 = "collectAndroidId";
        r9 = 1;
        r6 = r6.b(r7, r9);	 Catch:{ Throwable -> 0x0c4f }
        r7 = "androidIdCached";
        r9 = 0;
        r3 = r3.getString(r7, r9);	 Catch:{ Throwable -> 0x0c4f }
        if (r6 == 0) goto L_0x06dd;
    L_0x0695:
        r6 = r1.e;	 Catch:{ Throwable -> 0x0c4f }
        r6 = android.text.TextUtils.isEmpty(r6);	 Catch:{ Throwable -> 0x0c4f }
        if (r6 == 0) goto L_0x06dd;
    L_0x069d:
        r6 = g(r19);	 Catch:{ Throwable -> 0x0c4f }
        if (r6 == 0) goto L_0x06e4;
    L_0x06a3:
        r6 = r19.getContentResolver();	 Catch:{ Exception -> 0x06c1 }
        r7 = "android_id";
        r9 = android.provider.Settings.Secure.getString(r6, r7);	 Catch:{ Exception -> 0x06c1 }
        if (r9 == 0) goto L_0x06b0;
    L_0x06af:
        goto L_0x06e5;
    L_0x06b0:
        if (r3 == 0) goto L_0x06e4;
    L_0x06b2:
        r6 = "use cached AndroidId: ";
        r7 = java.lang.String.valueOf(r3);	 Catch:{ Exception -> 0x06c1 }
        r6 = r6.concat(r7);	 Catch:{ Exception -> 0x06c1 }
        com.appsflyer.AFLogger.c(r6);	 Catch:{ Exception -> 0x06c1 }
        r9 = r3;
        goto L_0x06e5;
    L_0x06c1:
        r0 = move-exception;
        r6 = r0;
        if (r3 == 0) goto L_0x06d4;
    L_0x06c5:
        r7 = "use cached AndroidId: ";
        r9 = java.lang.String.valueOf(r3);	 Catch:{ Throwable -> 0x0c4f }
        r7 = r7.concat(r9);	 Catch:{ Throwable -> 0x0c4f }
        com.appsflyer.AFLogger.c(r7);	 Catch:{ Throwable -> 0x0c4f }
        r9 = r3;
        goto L_0x06d5;
    L_0x06d4:
        r9 = 0;
    L_0x06d5:
        r3 = r6.getMessage();	 Catch:{ Throwable -> 0x0c4f }
        com.appsflyer.AFLogger.a(r3, r6);	 Catch:{ Throwable -> 0x0c4f }
        goto L_0x06e5;
    L_0x06dd:
        r3 = r1.e;	 Catch:{ Throwable -> 0x0c4f }
        if (r3 == 0) goto L_0x06e4;
    L_0x06e1:
        r9 = r1.e;	 Catch:{ Throwable -> 0x0c4f }
        goto L_0x06e5;
    L_0x06e4:
        r9 = 0;
    L_0x06e5:
        if (r9 == 0) goto L_0x06f2;
    L_0x06e7:
        r3 = "androidIdCached";
        b(r2, r3, r9);	 Catch:{ Throwable -> 0x0c4f }
        r3 = "android_id";
        r8.put(r3, r9);	 Catch:{ Throwable -> 0x0c4f }
        goto L_0x06f7;
    L_0x06f2:
        r3 = "Android ID was not collected.";
        com.appsflyer.AFLogger.d(r3);	 Catch:{ Throwable -> 0x0c4f }
    L_0x06f7:
        r3 = new java.lang.ref.WeakReference;	 Catch:{ Exception -> 0x0708 }
        r3.<init>(r2);	 Catch:{ Exception -> 0x0708 }
        r3 = com.appsflyer.ac.a(r3);	 Catch:{ Exception -> 0x0708 }
        if (r3 == 0) goto L_0x071f;
    L_0x0702:
        r6 = "uid";
        r8.put(r6, r3);	 Catch:{ Exception -> 0x0708 }
        goto L_0x071f;
    L_0x0708:
        r0 = move-exception;
        r3 = r0;
        r6 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0c4f }
        r7 = "ERROR: could not get uid ";
        r6.<init>(r7);	 Catch:{ Throwable -> 0x0c4f }
        r7 = r3.getMessage();	 Catch:{ Throwable -> 0x0c4f }
        r6.append(r7);	 Catch:{ Throwable -> 0x0c4f }
        r6 = r6.toString();	 Catch:{ Throwable -> 0x0c4f }
        com.appsflyer.AFLogger.a(r6, r3);	 Catch:{ Throwable -> 0x0c4f }
    L_0x071f:
        r3 = "lang";
        r6 = java.util.Locale.getDefault();	 Catch:{ Exception -> 0x072d }
        r6 = r6.getDisplayLanguage();	 Catch:{ Exception -> 0x072d }
        r8.put(r3, r6);	 Catch:{ Exception -> 0x072d }
        goto L_0x0734;
    L_0x072d:
        r0 = move-exception;
        r3 = r0;
        r6 = "Exception while collecting display language name. ";
        com.appsflyer.AFLogger.a(r6, r3);	 Catch:{ Throwable -> 0x0c4f }
    L_0x0734:
        r3 = "lang_code";
        r6 = java.util.Locale.getDefault();	 Catch:{ Exception -> 0x0742 }
        r6 = r6.getLanguage();	 Catch:{ Exception -> 0x0742 }
        r8.put(r3, r6);	 Catch:{ Exception -> 0x0742 }
        goto L_0x0749;
    L_0x0742:
        r0 = move-exception;
        r3 = r0;
        r6 = "Exception while collecting display language code. ";
        com.appsflyer.AFLogger.a(r6, r3);	 Catch:{ Throwable -> 0x0c4f }
    L_0x0749:
        r3 = "country";
        r6 = java.util.Locale.getDefault();	 Catch:{ Exception -> 0x0757 }
        r6 = r6.getCountry();	 Catch:{ Exception -> 0x0757 }
        r8.put(r3, r6);	 Catch:{ Exception -> 0x0757 }
        goto L_0x075e;
    L_0x0757:
        r0 = move-exception;
        r3 = r0;
        r6 = "Exception while collecting country name. ";
        com.appsflyer.AFLogger.a(r6, r3);	 Catch:{ Throwable -> 0x0c4f }
    L_0x075e:
        r3 = "platformextension";
        r6 = r1.F;	 Catch:{ Throwable -> 0x0c4f }
        r6 = r6.a();	 Catch:{ Throwable -> 0x0c4f }
        r8.put(r3, r6);	 Catch:{ Throwable -> 0x0c4f }
        a(r2, r8);	 Catch:{ Throwable -> 0x0c4f }
        r3 = "yyyy-MM-dd_HHmmssZ";
        r6 = new java.text.SimpleDateFormat;	 Catch:{ Throwable -> 0x0c4f }
        r7 = java.util.Locale.US;	 Catch:{ Throwable -> 0x0c4f }
        r6.<init>(r3, r7);	 Catch:{ Throwable -> 0x0c4f }
        r3 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Throwable -> 0x0c4f }
        r7 = 9;
        if (r3 < r7) goto L_0x07a9;
    L_0x077b:
        r3 = r19.getPackageManager();	 Catch:{ Exception -> 0x07a2 }
        r7 = r19.getPackageName();	 Catch:{ Exception -> 0x07a2 }
        r9 = 0;
        r3 = r3.getPackageInfo(r7, r9);	 Catch:{ Exception -> 0x07a2 }
        r9 = r3.firstInstallTime;	 Catch:{ Exception -> 0x07a2 }
        r3 = "installDate";
        r7 = "UTC";
        r7 = java.util.TimeZone.getTimeZone(r7);	 Catch:{ Exception -> 0x07a2 }
        r6.setTimeZone(r7);	 Catch:{ Exception -> 0x07a2 }
        r7 = new java.util.Date;	 Catch:{ Exception -> 0x07a2 }
        r7.<init>(r9);	 Catch:{ Exception -> 0x07a2 }
        r7 = r6.format(r7);	 Catch:{ Exception -> 0x07a2 }
        r8.put(r3, r7);	 Catch:{ Exception -> 0x07a2 }
        goto L_0x07a9;
    L_0x07a2:
        r0 = move-exception;
        r3 = r0;
        r7 = "Exception while collecting install date. ";
        com.appsflyer.AFLogger.a(r7, r3);	 Catch:{ Throwable -> 0x0c4f }
    L_0x07a9:
        r3 = r19.getPackageManager();	 Catch:{ Throwable -> 0x0863 }
        r7 = r19.getPackageName();	 Catch:{ Throwable -> 0x0863 }
        r9 = 0;
        r3 = r3.getPackageInfo(r7, r9);	 Catch:{ Throwable -> 0x0863 }
        r7 = "versionCode";
        r10 = r25;
        r7 = r10.getInt(r7, r9);	 Catch:{ Throwable -> 0x0861 }
        r11 = r3.versionCode;	 Catch:{ Throwable -> 0x0861 }
        if (r11 <= r7) goto L_0x07ce;
    L_0x07c2:
        r7 = "appsflyerConversionDataRequestRetries";
        b(r2, r7, r9);	 Catch:{ Throwable -> 0x0861 }
        r7 = "versionCode";
        r9 = r3.versionCode;	 Catch:{ Throwable -> 0x0861 }
        b(r2, r7, r9);	 Catch:{ Throwable -> 0x0861 }
    L_0x07ce:
        r7 = "app_version_code";
        r9 = r3.versionCode;	 Catch:{ Throwable -> 0x0861 }
        r9 = java.lang.Integer.toString(r9);	 Catch:{ Throwable -> 0x0861 }
        r8.put(r7, r9);	 Catch:{ Throwable -> 0x0861 }
        r7 = "app_version_name";
        r9 = r3.versionName;	 Catch:{ Throwable -> 0x0861 }
        r8.put(r7, r9);	 Catch:{ Throwable -> 0x0861 }
        r7 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Throwable -> 0x0861 }
        r9 = 9;
        if (r7 < r9) goto L_0x086c;
    L_0x07e6:
        r11 = r3.firstInstallTime;	 Catch:{ Throwable -> 0x0861 }
        r13 = r3.lastUpdateTime;	 Catch:{ Throwable -> 0x0861 }
        r3 = "date1";
        r7 = "UTC";
        r7 = java.util.TimeZone.getTimeZone(r7);	 Catch:{ Throwable -> 0x0861 }
        r6.setTimeZone(r7);	 Catch:{ Throwable -> 0x0861 }
        r7 = new java.util.Date;	 Catch:{ Throwable -> 0x0861 }
        r7.<init>(r11);	 Catch:{ Throwable -> 0x0861 }
        r7 = r6.format(r7);	 Catch:{ Throwable -> 0x0861 }
        r8.put(r3, r7);	 Catch:{ Throwable -> 0x0861 }
        r3 = "date2";
        r7 = "UTC";
        r7 = java.util.TimeZone.getTimeZone(r7);	 Catch:{ Throwable -> 0x0861 }
        r6.setTimeZone(r7);	 Catch:{ Throwable -> 0x0861 }
        r7 = new java.util.Date;	 Catch:{ Throwable -> 0x0861 }
        r7.<init>(r13);	 Catch:{ Throwable -> 0x0861 }
        r7 = r6.format(r7);	 Catch:{ Throwable -> 0x0861 }
        r8.put(r3, r7);	 Catch:{ Throwable -> 0x0861 }
        r3 = "appsflyer-data";
        r7 = 0;
        r3 = r2.getSharedPreferences(r3, r7);	 Catch:{ Throwable -> 0x0861 }
        r9 = "appsFlyerFirstInstall";
        r11 = 0;
        r3 = r3.getString(r9, r11);	 Catch:{ Throwable -> 0x0861 }
        if (r3 != 0) goto L_0x084e;
    L_0x0828:
        r3 = "appsflyer-data";
        r3 = r2.getSharedPreferences(r3, r7);	 Catch:{ Throwable -> 0x0861 }
        r7 = "appsFlyerCount";
        r3 = r3.contains(r7);	 Catch:{ Throwable -> 0x0861 }
        r7 = 1;
        r3 = r3 ^ r7;
        if (r3 == 0) goto L_0x0847;
    L_0x0838:
        r3 = "AppsFlyer: first launch detected";
        com.appsflyer.AFLogger.c(r3);	 Catch:{ Throwable -> 0x0861 }
        r3 = new java.util.Date;	 Catch:{ Throwable -> 0x0861 }
        r3.<init>();	 Catch:{ Throwable -> 0x0861 }
        r3 = r6.format(r3);	 Catch:{ Throwable -> 0x0861 }
        goto L_0x0849;
    L_0x0847:
        r3 = "";
    L_0x0849:
        r6 = "appsFlyerFirstInstall";
        b(r2, r6, r3);	 Catch:{ Throwable -> 0x0861 }
    L_0x084e:
        r6 = "AppsFlyer: first launch date: ";
        r7 = java.lang.String.valueOf(r3);	 Catch:{ Throwable -> 0x0861 }
        r6 = r6.concat(r7);	 Catch:{ Throwable -> 0x0861 }
        com.appsflyer.AFLogger.d(r6);	 Catch:{ Throwable -> 0x0861 }
        r6 = "firstLaunchDate";
        r8.put(r6, r3);	 Catch:{ Throwable -> 0x0861 }
        goto L_0x086c;
    L_0x0861:
        r0 = move-exception;
        goto L_0x0866;
    L_0x0863:
        r0 = move-exception;
        r10 = r25;
    L_0x0866:
        r3 = r0;
        r6 = "Exception while collecting app version data ";
        com.appsflyer.AFLogger.a(r6, r3);	 Catch:{ Throwable -> 0x0c4f }
    L_0x086c:
        r3 = r23.length();	 Catch:{ Throwable -> 0x0c4f }
        if (r3 <= 0) goto L_0x0879;
    L_0x0872:
        r3 = "referrer";
        r6 = r23;
        r8.put(r3, r6);	 Catch:{ Throwable -> 0x0c4f }
    L_0x0879:
        r3 = "extraReferrers";
        r6 = 0;
        r3 = r10.getString(r3, r6);	 Catch:{ Throwable -> 0x0c4f }
        if (r3 == 0) goto L_0x0887;
    L_0x0882:
        r6 = "extraReferrers";
        r8.put(r6, r3);	 Catch:{ Throwable -> 0x0c4f }
    L_0x0887:
        r3 = "afUninstallToken";
        r6 = com.appsflyer.i.a();	 Catch:{ Throwable -> 0x0c4f }
        r3 = r6.a(r3);	 Catch:{ Throwable -> 0x0c4f }
        if (r3 == 0) goto L_0x08a0;
    L_0x0893:
        r3 = com.appsflyer.r.a(r3);	 Catch:{ Throwable -> 0x0c4f }
        r6 = "af_gcm_token";
        r3 = r3.a();	 Catch:{ Throwable -> 0x0c4f }
        r8.put(r6, r3);	 Catch:{ Throwable -> 0x0c4f }
    L_0x08a0:
        r3 = com.appsflyer.ag.a(r19);	 Catch:{ Throwable -> 0x0c4f }
        r1.D = r3;	 Catch:{ Throwable -> 0x0c4f }
        r3 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0c4f }
        r6 = "didConfigureTokenRefreshService=";
        r3.<init>(r6);	 Catch:{ Throwable -> 0x0c4f }
        r6 = r1.D;	 Catch:{ Throwable -> 0x0c4f }
        r3.append(r6);	 Catch:{ Throwable -> 0x0c4f }
        r3 = r3.toString();	 Catch:{ Throwable -> 0x0c4f }
        com.appsflyer.AFLogger.c(r3);	 Catch:{ Throwable -> 0x0c4f }
        r3 = r1.D;	 Catch:{ Throwable -> 0x0c4f }
        if (r3 != 0) goto L_0x08c4;
    L_0x08bd:
        r3 = "tokenRefreshConfigured";
        r6 = java.lang.Boolean.FALSE;	 Catch:{ Throwable -> 0x0c4f }
        r8.put(r3, r6);	 Catch:{ Throwable -> 0x0c4f }
    L_0x08c4:
        r3 = r26;
        if (r3 == 0) goto L_0x08e6;
    L_0x08c8:
        r6 = r1.C;	 Catch:{ Throwable -> 0x0c4f }
        if (r6 == 0) goto L_0x08e3;
    L_0x08cc:
        r6 = new org.json.JSONObject;	 Catch:{ Throwable -> 0x0c4f }
        r7 = r1.C;	 Catch:{ Throwable -> 0x0c4f }
        r6.<init>(r7);	 Catch:{ Throwable -> 0x0c4f }
        r7 = "isPush";
        r9 = "true";
        r6.put(r7, r9);	 Catch:{ Throwable -> 0x0c4f }
        r7 = "af_deeplink";
        r6 = r6.toString();	 Catch:{ Throwable -> 0x0c4f }
        r8.put(r7, r6);	 Catch:{ Throwable -> 0x0c4f }
    L_0x08e3:
        r6 = 0;
        r1.C = r6;	 Catch:{ Throwable -> 0x0c4f }
    L_0x08e6:
        if (r3 == 0) goto L_0x0949;
    L_0x08e8:
        if (r27 == 0) goto L_0x08fb;
    L_0x08ea:
        r7 = "android.intent.action.VIEW";
        r9 = r27.getAction();	 Catch:{ Throwable -> 0x0c4f }
        r7 = r7.equals(r9);	 Catch:{ Throwable -> 0x0c4f }
        if (r7 == 0) goto L_0x08fb;
    L_0x08f6:
        r9 = r27.getData();	 Catch:{ Throwable -> 0x0c4f }
        goto L_0x08fc;
    L_0x08fb:
        r9 = 0;
    L_0x08fc:
        if (r9 == 0) goto L_0x0940;
    L_0x08fe:
        r6 = a(r9);	 Catch:{ Throwable -> 0x0c4f }
        if (r6 == 0) goto L_0x093c;
    L_0x0904:
        r6 = com.appsflyer.i.a();	 Catch:{ Throwable -> 0x0c4f }
        r7 = "consumeAfDeepLink";
        r11 = 0;
        r6 = r6.b(r7, r11);	 Catch:{ Throwable -> 0x0c4f }
        if (r6 == 0) goto L_0x093c;
    L_0x0911:
        r6 = com.appsflyer.i.a();	 Catch:{ Throwable -> 0x0c4f }
        r7 = "prevDPURI";
        r6 = r6.a(r7);	 Catch:{ Throwable -> 0x0c4f }
        r7 = r9.toString();	 Catch:{ Throwable -> 0x0c4f }
        r6 = r7.equals(r6);	 Catch:{ Throwable -> 0x0c4f }
        if (r6 == 0) goto L_0x092b;
    L_0x0925:
        r6 = "Skipping execution of previously consumed AppsFlyer deep link";
        com.appsflyer.AFLogger.d(r6);	 Catch:{ Throwable -> 0x0c4f }
        goto L_0x0949;
    L_0x092b:
        r6 = com.appsflyer.i.a();	 Catch:{ Throwable -> 0x0c4f }
        r7 = "prevDPURI";
        r11 = r9.toString();	 Catch:{ Throwable -> 0x0c4f }
        r6.a(r7, r11);	 Catch:{ Throwable -> 0x0c4f }
        r1.a(r2, r8, r9);	 Catch:{ Throwable -> 0x0c4f }
        goto L_0x0949;
    L_0x093c:
        r1.a(r2, r8, r9);	 Catch:{ Throwable -> 0x0c4f }
        goto L_0x0949;
    L_0x0940:
        r6 = r1.y;	 Catch:{ Throwable -> 0x0c4f }
        if (r6 == 0) goto L_0x0949;
    L_0x0944:
        r6 = r1.y;	 Catch:{ Throwable -> 0x0c4f }
        r1.a(r2, r8, r6);	 Catch:{ Throwable -> 0x0c4f }
    L_0x0949:
        r6 = r1.B;	 Catch:{ Throwable -> 0x0c4f }
        if (r6 == 0) goto L_0x0971;
    L_0x094d:
        r6 = "testAppMode_retargeting";
        r7 = "true";
        r8.put(r6, r7);	 Catch:{ Throwable -> 0x0c4f }
        r6 = new org.json.JSONObject;	 Catch:{ Throwable -> 0x0c4f }
        r6.<init>(r8);	 Catch:{ Throwable -> 0x0c4f }
        r6 = r6.toString();	 Catch:{ Throwable -> 0x0c4f }
        r7 = new android.content.Intent;	 Catch:{ Throwable -> 0x0c4f }
        r9 = "com.appsflyer.testIntgrationBroadcast";
        r7.<init>(r9);	 Catch:{ Throwable -> 0x0c4f }
        r9 = "params";
        r7.putExtra(r9, r6);	 Catch:{ Throwable -> 0x0c4f }
        r2.sendBroadcast(r7);	 Catch:{ Throwable -> 0x0c4f }
        r6 = "Sent retargeting params to test app";
        com.appsflyer.AFLogger.d(r6);	 Catch:{ Throwable -> 0x0c4f }
    L_0x0971:
        r6 = java.lang.System.currentTimeMillis();	 Catch:{ Throwable -> 0x0c4f }
        r11 = r1.z;	 Catch:{ Throwable -> 0x0c4f }
        r13 = r6 - r11;
        r6 = com.appsflyer.i.a();	 Catch:{ Throwable -> 0x0c4f }
        r6 = r6.a(r2);	 Catch:{ Throwable -> 0x0c4f }
        r11 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
        r7 = (r13 > r11 ? 1 : (r13 == r11 ? 0 : -1));
        if (r7 > 0) goto L_0x0993;
    L_0x0987:
        if (r6 == 0) goto L_0x0993;
    L_0x0989:
        r7 = "AppsFlyer_Test";
        r6 = r6.contains(r7);	 Catch:{ Throwable -> 0x0c4f }
        if (r6 == 0) goto L_0x0993;
    L_0x0991:
        r6 = 1;
        goto L_0x0994;
    L_0x0993:
        r6 = 0;
    L_0x0994:
        if (r6 == 0) goto L_0x09c3;
    L_0x0996:
        r6 = "testAppMode";
        r7 = "true";
        r8.put(r6, r7);	 Catch:{ Throwable -> 0x0c4f }
        r6 = new org.json.JSONObject;	 Catch:{ Throwable -> 0x0c4f }
        r6.<init>(r8);	 Catch:{ Throwable -> 0x0c4f }
        r6 = r6.toString();	 Catch:{ Throwable -> 0x0c4f }
        r7 = new android.content.Intent;	 Catch:{ Throwable -> 0x0c4f }
        r9 = "com.appsflyer.testIntgrationBroadcast";
        r7.<init>(r9);	 Catch:{ Throwable -> 0x0c4f }
        r9 = "params";
        r7.putExtra(r9, r6);	 Catch:{ Throwable -> 0x0c4f }
        r2.sendBroadcast(r7);	 Catch:{ Throwable -> 0x0c4f }
        r6 = "Sent params to test app";
        com.appsflyer.AFLogger.d(r6);	 Catch:{ Throwable -> 0x0c4f }
        r6 = "Test mode ended!";
        com.appsflyer.AFLogger.d(r6);	 Catch:{ Throwable -> 0x0c4f }
        r6 = 0;
        r1.z = r6;	 Catch:{ Throwable -> 0x0c4f }
    L_0x09c3:
        r6 = "advertiserId";
        r7 = com.appsflyer.i.a();	 Catch:{ Throwable -> 0x0c4f }
        r6 = r7.a(r6);	 Catch:{ Throwable -> 0x0c4f }
        if (r6 != 0) goto L_0x09ed;
    L_0x09cf:
        com.appsflyer.n.a(r2, r8);	 Catch:{ Throwable -> 0x0c4f }
        r6 = "advertiserId";
        r7 = com.appsflyer.i.a();	 Catch:{ Throwable -> 0x0c4f }
        r6 = r7.a(r6);	 Catch:{ Throwable -> 0x0c4f }
        if (r6 == 0) goto L_0x09e6;
    L_0x09de:
        r6 = "GAID_retry";
        r7 = "true";
        r8.put(r6, r7);	 Catch:{ Throwable -> 0x0c4f }
        goto L_0x09ed;
    L_0x09e6:
        r6 = "GAID_retry";
        r7 = "false";
        r8.put(r6, r7);	 Catch:{ Throwable -> 0x0c4f }
    L_0x09ed:
        r6 = r19.getContentResolver();	 Catch:{ Throwable -> 0x0c4f }
        r6 = com.appsflyer.n.a(r6);	 Catch:{ Throwable -> 0x0c4f }
        if (r6 == 0) goto L_0x0a0d;
    L_0x09f7:
        r7 = "amazon_aid";
        r9 = r6.a();	 Catch:{ Throwable -> 0x0c4f }
        r8.put(r7, r9);	 Catch:{ Throwable -> 0x0c4f }
        r7 = "amazon_aid_limit";
        r6 = r6.b();	 Catch:{ Throwable -> 0x0c4f }
        r6 = java.lang.String.valueOf(r6);	 Catch:{ Throwable -> 0x0c4f }
        r8.put(r7, r6);	 Catch:{ Throwable -> 0x0c4f }
    L_0x0a0d:
        r6 = com.appsflyer.i.a();	 Catch:{ Throwable -> 0x0c4f }
        r6 = r6.a(r2);	 Catch:{ Throwable -> 0x0c4f }
        if (r6 == 0) goto L_0x0a2a;
    L_0x0a17:
        r7 = r6.length();	 Catch:{ Throwable -> 0x0c4f }
        if (r7 <= 0) goto L_0x0a2a;
    L_0x0a1d:
        r7 = "referrer";
        r7 = r8.get(r7);	 Catch:{ Throwable -> 0x0c4f }
        if (r7 != 0) goto L_0x0a2a;
    L_0x0a25:
        r7 = "referrer";
        r8.put(r7, r6);	 Catch:{ Throwable -> 0x0c4f }
    L_0x0a2a:
        r6 = "true";
        r7 = "sentSuccessfully";
        r9 = "";
        r7 = r10.getString(r7, r9);	 Catch:{ Throwable -> 0x0c4f }
        r6 = r6.equals(r7);	 Catch:{ Throwable -> 0x0c4f }
        r7 = "sentRegisterRequestToAF";
        r9 = 0;
        r7 = r10.getBoolean(r7, r9);	 Catch:{ Throwable -> 0x0c4f }
        r9 = "registeredUninstall";
        r7 = java.lang.Boolean.valueOf(r7);	 Catch:{ Throwable -> 0x0c4f }
        r8.put(r9, r7);	 Catch:{ Throwable -> 0x0c4f }
        r7 = "appsFlyerCount";
        r7 = a(r10, r7, r3);	 Catch:{ Throwable -> 0x0c4f }
        r9 = "counter";
        r11 = java.lang.Integer.toString(r7);	 Catch:{ Throwable -> 0x0c4f }
        r8.put(r9, r11);	 Catch:{ Throwable -> 0x0c4f }
        r9 = "iaecounter";
        if (r4 == 0) goto L_0x0a5d;
    L_0x0a5b:
        r4 = 1;
        goto L_0x0a5e;
    L_0x0a5d:
        r4 = 0;
    L_0x0a5e:
        r11 = "appsFlyerInAppEventCount";
        r4 = a(r10, r11, r4);	 Catch:{ Throwable -> 0x0c4f }
        r4 = java.lang.Integer.toString(r4);	 Catch:{ Throwable -> 0x0c4f }
        r8.put(r9, r4);	 Catch:{ Throwable -> 0x0c4f }
        if (r3 == 0) goto L_0x0a8e;
    L_0x0a6d:
        r4 = 1;
        if (r7 != r4) goto L_0x0a8e;
    L_0x0a70:
        r4 = com.appsflyer.i.a();	 Catch:{ Throwable -> 0x0c4f }
        r4.d();	 Catch:{ Throwable -> 0x0c4f }
        r4 = "waitForCustomerId";
        r9 = com.appsflyer.i.a();	 Catch:{ Throwable -> 0x0c4f }
        r11 = 0;
        r4 = r9.b(r4, r11);	 Catch:{ Throwable -> 0x0c4f }
        if (r4 == 0) goto L_0x0a8e;
    L_0x0a84:
        r4 = "wait_cid";
        r9 = 1;
        r11 = java.lang.Boolean.toString(r9);	 Catch:{ Throwable -> 0x0c4f }
        r8.put(r4, r11);	 Catch:{ Throwable -> 0x0c4f }
    L_0x0a8e:
        r4 = "isFirstCall";
        r9 = 1;
        r6 = r6 ^ r9;
        r6 = java.lang.Boolean.toString(r6);	 Catch:{ Throwable -> 0x0c4f }
        r8.put(r4, r6);	 Catch:{ Throwable -> 0x0c4f }
        r4 = new java.util.HashMap;	 Catch:{ Throwable -> 0x0c4f }
        r4.<init>();	 Catch:{ Throwable -> 0x0c4f }
        r6 = "cpu_abi";
        r9 = "ro.product.cpu.abi";
        r9 = f(r9);	 Catch:{ Throwable -> 0x0c4f }
        r4.put(r6, r9);	 Catch:{ Throwable -> 0x0c4f }
        r6 = "cpu_abi2";
        r9 = "ro.product.cpu.abi2";
        r9 = f(r9);	 Catch:{ Throwable -> 0x0c4f }
        r4.put(r6, r9);	 Catch:{ Throwable -> 0x0c4f }
        r6 = "arch";
        r9 = "os.arch";
        r9 = f(r9);	 Catch:{ Throwable -> 0x0c4f }
        r4.put(r6, r9);	 Catch:{ Throwable -> 0x0c4f }
        r6 = "build_display_id";
        r9 = "ro.build.display.id";
        r9 = f(r9);	 Catch:{ Throwable -> 0x0c4f }
        r4.put(r6, r9);	 Catch:{ Throwable -> 0x0c4f }
        if (r3 == 0) goto L_0x0b47;
    L_0x0acc:
        r3 = r1.A;	 Catch:{ Throwable -> 0x0c4f }
        if (r3 == 0) goto L_0x0b0f;
    L_0x0ad0:
        r3 = com.appsflyer.z.a.a;	 Catch:{ Throwable -> 0x0c4f }
        r3 = com.appsflyer.z.a(r19);	 Catch:{ Throwable -> 0x0c4f }
        r6 = new java.util.HashMap;	 Catch:{ Throwable -> 0x0c4f }
        r6.<init>(r5);	 Catch:{ Throwable -> 0x0c4f }
        if (r3 == 0) goto L_0x0b04;
    L_0x0add:
        r5 = "lat";
        r11 = r3.getLatitude();	 Catch:{ Throwable -> 0x0c4f }
        r9 = java.lang.String.valueOf(r11);	 Catch:{ Throwable -> 0x0c4f }
        r6.put(r5, r9);	 Catch:{ Throwable -> 0x0c4f }
        r5 = "lon";
        r11 = r3.getLongitude();	 Catch:{ Throwable -> 0x0c4f }
        r9 = java.lang.String.valueOf(r11);	 Catch:{ Throwable -> 0x0c4f }
        r6.put(r5, r9);	 Catch:{ Throwable -> 0x0c4f }
        r5 = "ts";
        r11 = r3.getTime();	 Catch:{ Throwable -> 0x0c4f }
        r3 = java.lang.String.valueOf(r11);	 Catch:{ Throwable -> 0x0c4f }
        r6.put(r5, r3);	 Catch:{ Throwable -> 0x0c4f }
    L_0x0b04:
        r3 = r6.isEmpty();	 Catch:{ Throwable -> 0x0c4f }
        if (r3 != 0) goto L_0x0b0f;
    L_0x0b0a:
        r3 = "loc";
        r4.put(r3, r6);	 Catch:{ Throwable -> 0x0c4f }
    L_0x0b0f:
        r3 = com.appsflyer.v.a.a;	 Catch:{ Throwable -> 0x0c4f }
        r3 = r3.a(r2);	 Catch:{ Throwable -> 0x0c4f }
        r5 = "btl";
        r6 = r3.a();	 Catch:{ Throwable -> 0x0c4f }
        r6 = java.lang.Float.toString(r6);	 Catch:{ Throwable -> 0x0c4f }
        r4.put(r5, r6);	 Catch:{ Throwable -> 0x0c4f }
        r5 = r3.b();	 Catch:{ Throwable -> 0x0c4f }
        if (r5 == 0) goto L_0x0b31;
    L_0x0b28:
        r5 = "btch";
        r3 = r3.b();	 Catch:{ Throwable -> 0x0c4f }
        r4.put(r5, r3);	 Catch:{ Throwable -> 0x0c4f }
    L_0x0b31:
        r3 = 2;
        if (r3 < r7) goto L_0x0b47;
    L_0x0b34:
        r3 = com.appsflyer.y.a(r19);	 Catch:{ Throwable -> 0x0c4f }
        r3 = r3.c();	 Catch:{ Throwable -> 0x0c4f }
        r5 = r3.isEmpty();	 Catch:{ Throwable -> 0x0c4f }
        if (r5 != 0) goto L_0x0b47;
    L_0x0b42:
        r5 = "sensors";
        r4.put(r5, r3);	 Catch:{ Throwable -> 0x0c4f }
    L_0x0b47:
        r2 = com.appsflyer.d.a(r19);	 Catch:{ Throwable -> 0x0c4f }
        r3 = "dim";
        r4.put(r3, r2);	 Catch:{ Throwable -> 0x0c4f }
        r2 = "deviceData";
        r8.put(r2, r4);	 Catch:{ Throwable -> 0x0c4f }
        r2 = new com.appsflyer.ad;	 Catch:{ Throwable -> 0x0c4f }
        r2.<init>();	 Catch:{ Throwable -> 0x0c4f }
        r2 = "appsflyerKey";
        r2 = r8.get(r2);	 Catch:{ Throwable -> 0x0c4f }
        r2 = (java.lang.String) r2;	 Catch:{ Throwable -> 0x0c4f }
        r3 = "af_timestamp";
        r3 = r8.get(r3);	 Catch:{ Throwable -> 0x0c4f }
        r3 = (java.lang.String) r3;	 Catch:{ Throwable -> 0x0c4f }
        r4 = "uid";
        r4 = r8.get(r4);	 Catch:{ Throwable -> 0x0c4f }
        r4 = (java.lang.String) r4;	 Catch:{ Throwable -> 0x0c4f }
        r5 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0c4f }
        r5.<init>();	 Catch:{ Throwable -> 0x0c4f }
        r6 = 7;
        r7 = 0;
        r2 = r2.substring(r7, r6);	 Catch:{ Throwable -> 0x0c4f }
        r5.append(r2);	 Catch:{ Throwable -> 0x0c4f }
        r2 = r4.substring(r7, r6);	 Catch:{ Throwable -> 0x0c4f }
        r5.append(r2);	 Catch:{ Throwable -> 0x0c4f }
        r2 = r3.length();	 Catch:{ Throwable -> 0x0c4f }
        r2 = r2 - r6;
        r2 = r3.substring(r2);	 Catch:{ Throwable -> 0x0c4f }
        r5.append(r2);	 Catch:{ Throwable -> 0x0c4f }
        r2 = r5.toString();	 Catch:{ Throwable -> 0x0c4f }
        r2 = com.appsflyer.ad.a(r2);	 Catch:{ Throwable -> 0x0c4f }
        r3 = "af_v";
        r8.put(r3, r2);	 Catch:{ Throwable -> 0x0c4f }
        r2 = new com.appsflyer.ad;	 Catch:{ Throwable -> 0x0c4f }
        r2.<init>();	 Catch:{ Throwable -> 0x0c4f }
        r2 = "appsflyerKey";
        r2 = r8.get(r2);	 Catch:{ Throwable -> 0x0c4f }
        r2 = (java.lang.String) r2;	 Catch:{ Throwable -> 0x0c4f }
        r3 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0c4f }
        r3.<init>();	 Catch:{ Throwable -> 0x0c4f }
        r3.append(r2);	 Catch:{ Throwable -> 0x0c4f }
        r2 = "af_timestamp";
        r2 = r8.get(r2);	 Catch:{ Throwable -> 0x0c4f }
        r3.append(r2);	 Catch:{ Throwable -> 0x0c4f }
        r2 = r3.toString();	 Catch:{ Throwable -> 0x0c4f }
        r3 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0c4f }
        r3.<init>();	 Catch:{ Throwable -> 0x0c4f }
        r3.append(r2);	 Catch:{ Throwable -> 0x0c4f }
        r2 = "uid";
        r2 = r8.get(r2);	 Catch:{ Throwable -> 0x0c4f }
        r3.append(r2);	 Catch:{ Throwable -> 0x0c4f }
        r2 = r3.toString();	 Catch:{ Throwable -> 0x0c4f }
        r3 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0c4f }
        r3.<init>();	 Catch:{ Throwable -> 0x0c4f }
        r3.append(r2);	 Catch:{ Throwable -> 0x0c4f }
        r2 = "installDate";
        r2 = r8.get(r2);	 Catch:{ Throwable -> 0x0c4f }
        r3.append(r2);	 Catch:{ Throwable -> 0x0c4f }
        r2 = r3.toString();	 Catch:{ Throwable -> 0x0c4f }
        r3 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0c4f }
        r3.<init>();	 Catch:{ Throwable -> 0x0c4f }
        r3.append(r2);	 Catch:{ Throwable -> 0x0c4f }
        r2 = "counter";
        r2 = r8.get(r2);	 Catch:{ Throwable -> 0x0c4f }
        r3.append(r2);	 Catch:{ Throwable -> 0x0c4f }
        r2 = r3.toString();	 Catch:{ Throwable -> 0x0c4f }
        r3 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0c4f }
        r3.<init>();	 Catch:{ Throwable -> 0x0c4f }
        r3.append(r2);	 Catch:{ Throwable -> 0x0c4f }
        r2 = "iaecounter";
        r2 = r8.get(r2);	 Catch:{ Throwable -> 0x0c4f }
        r3.append(r2);	 Catch:{ Throwable -> 0x0c4f }
        r2 = r3.toString();	 Catch:{ Throwable -> 0x0c4f }
        r2 = com.appsflyer.ad.b(r2);	 Catch:{ Throwable -> 0x0c4f }
        r2 = com.appsflyer.ad.a(r2);	 Catch:{ Throwable -> 0x0c4f }
        r3 = "af_v2";
        r8.put(r3, r2);	 Catch:{ Throwable -> 0x0c4f }
        r2 = "is_stop_tracking_used";
        r2 = r10.contains(r2);	 Catch:{ Throwable -> 0x0c4f }
        if (r2 == 0) goto L_0x0c58;
    L_0x0c2b:
        r2 = "istu";
        r3 = "is_stop_tracking_used";
        r4 = 0;
        r3 = r10.getBoolean(r3, r4);	 Catch:{ Throwable -> 0x0c4f }
        r3 = java.lang.String.valueOf(r3);	 Catch:{ Throwable -> 0x0c4f }
        r8.put(r2, r3);	 Catch:{ Throwable -> 0x0c4f }
        goto L_0x0c58;
    L_0x0c3c:
        r3 = "AppsFlyer dev key is missing!!! Please use  AppsFlyerLib.getInstance().setAppsFlyerKey(...) to set it. ";
        com.appsflyer.AFLogger.d(r3);	 Catch:{ Throwable -> 0x0c4f }
        r3 = "AppsFlyer_4.8.13";
        r4 = "DEV_KEY_MISSING";
        r5 = 0;
        a(r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0c4f }
        r2 = "AppsFlyer will not track this event.";
        com.appsflyer.AFLogger.d(r2);	 Catch:{ Throwable -> 0x0c4f }
        return r5;
    L_0x0c4f:
        r0 = move-exception;
        r2 = r0;
        r3 = r2.getLocalizedMessage();
        com.appsflyer.AFLogger.a(r3, r2);
    L_0x0c58:
        return r8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.h.a(android.content.Context, java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, android.content.SharedPreferences, boolean, android.content.Intent):java.util.Map");
    }

    private static void a(Context context, Map<String, ? super String> map) {
        w wVar = a.a;
        b a = w.a(context);
        map.put("network", a.a());
        if (a.c() != null) {
            map.put("operator", a.c());
        }
        if (a.b() != null) {
            map.put(com.til.colombia.android.internal.e.w, a.b());
        }
    }

    private void a(Context context, Map<String, Object> map, Uri uri) {
        Map e;
        map.put("af_deeplink", uri.toString());
        if (uri.getQueryParameter("af_deeplink") != null) {
            boolean z = "AppsFlyer_Test".equals(uri.getQueryParameter("media_source")) && Boolean.parseBoolean(uri.getQueryParameter("is_retargeting"));
            this.B = z;
            e = e(context, uri.getQuery());
            String str = "path";
            String path = uri.getPath();
            if (path != null) {
                e.put(str, path);
            }
            str = "scheme";
            path = uri.getScheme();
            if (path != null) {
                e.put(str, path);
            }
            str = "host";
            path = uri.getHost();
            if (path != null) {
                e.put(str, path);
            }
        } else {
            e = new HashMap();
            e.put("link", uri.toString());
        }
        final WeakReference weakReference = new WeakReference(context);
        af afVar = new af(uri, this);
        afVar.a(new com.appsflyer.o.a());
        if (afVar.c()) {
            afVar.a(new a() {
                public final void a(String str) {
                    if (h.o != null) {
                        b(e);
                        h.o.onAttributionFailure(str);
                    }
                }

                private void b(Map<String, String> map) {
                    if (weakReference.get() != null) {
                        h.b((Context) weakReference.get(), "deeplinkAttribution", new JSONObject(map).toString());
                    }
                }

                public final void a(Map<String, String> map) {
                    for (String str : map.keySet()) {
                        e.put(str, map.get(str));
                    }
                    b(e);
                    h.b(e);
                }
            });
            a.a().b().execute(afVar);
            return;
        }
        if (o != null) {
            try {
                o.onAppOpenAttribution(e);
            } catch (Throwable th) {
                AFLogger.a(th.getLocalizedMessage(), th);
            }
        }
    }

    private static boolean f(Context context) {
        try {
            if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == 0) {
                return true;
            }
        } catch (Throwable th) {
            AFLogger.a("WARNING:  Google play services is unavailable. ", th);
        }
        try {
            context.getPackageManager().getPackageInfo("com.google.android.gms", 0);
            return true;
        } catch (NameNotFoundException e) {
            AFLogger.a("WARNING:  Google Play Services is unavailable. ", e);
            return false;
        }
    }

    private static boolean g(Context context) {
        boolean z = i.a().b("collectAndroidIdForceByUser", false) || i.a().b("collectIMEIForceByUser", false);
        if (z || !f(context)) {
            return true;
        }
        return false;
    }

    private static String f(String str) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class}).invoke(null, new Object[]{str});
        } catch (Throwable th) {
            AFLogger.a(th.getMessage(), th);
            return null;
        }
    }

    @Nullable
    private static String a(WeakReference<Context> weakReference, String str) {
        if (weakReference.get() == null) {
            return null;
        }
        return a(str, ((Context) weakReference.get()).getPackageManager(), ((Context) weakReference.get()).getPackageName());
    }

    @Nullable
    private static String a(String str, PackageManager packageManager, String str2) {
        try {
            Bundle bundle = packageManager.getApplicationInfo(str2, 128).metaData;
            if (bundle == null) {
                return null;
            }
            Object obj = bundle.get(str);
            if (obj != null) {
                return obj.toString();
            }
            return null;
        } catch (Throwable th) {
            StringBuilder stringBuilder = new StringBuilder("Could not find ");
            stringBuilder.append(str);
            stringBuilder.append(" value in the manifest");
            AFLogger.a(stringBuilder.toString(), th);
            return null;
        }
    }

    private static boolean a(Uri uri) {
        String toLowerCase = uri.toString().toLowerCase();
        for (CharSequence contains : com.appsflyer.b.a.a) {
            if (toLowerCase.contains(contains)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:24:0x0042=Splitter:B:24:0x0042, B:15:0x002b=Splitter:B:15:0x002b} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:24:0x0042 */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0034 A:{SYNTHETIC, Splitter:B:18:0x0034} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0060 A:{SYNTHETIC, Splitter:B:32:0x0060} */
    private static java.lang.String a(java.io.File r4, java.lang.String r5) {
        /*
        r0 = 0;
        r1 = new java.util.Properties;	 Catch:{ FileNotFoundException -> 0x0041, Throwable -> 0x0029, all -> 0x0026 }
        r1.<init>();	 Catch:{ FileNotFoundException -> 0x0041, Throwable -> 0x0029, all -> 0x0026 }
        r2 = new java.io.FileReader;	 Catch:{ FileNotFoundException -> 0x0041, Throwable -> 0x0029, all -> 0x0026 }
        r2.<init>(r4);	 Catch:{ FileNotFoundException -> 0x0041, Throwable -> 0x0029, all -> 0x0026 }
        r1.load(r2);	 Catch:{ FileNotFoundException -> 0x0042, Throwable -> 0x0024 }
        r3 = "Found PreInstall property!";
        com.appsflyer.AFLogger.d(r3);	 Catch:{ FileNotFoundException -> 0x0042, Throwable -> 0x0024 }
        r5 = r1.getProperty(r5);	 Catch:{ FileNotFoundException -> 0x0042, Throwable -> 0x0024 }
        r2.close();	 Catch:{ Throwable -> 0x001b }
        goto L_0x0023;
    L_0x001b:
        r4 = move-exception;
        r0 = r4.getMessage();
        com.appsflyer.AFLogger.a(r0, r4);
    L_0x0023:
        return r5;
    L_0x0024:
        r4 = move-exception;
        goto L_0x002b;
    L_0x0026:
        r4 = move-exception;
        r2 = r0;
        goto L_0x005e;
    L_0x0029:
        r4 = move-exception;
        r2 = r0;
    L_0x002b:
        r5 = r4.getMessage();	 Catch:{ all -> 0x005d }
        com.appsflyer.AFLogger.a(r5, r4);	 Catch:{ all -> 0x005d }
        if (r2 == 0) goto L_0x005c;
    L_0x0034:
        r2.close();	 Catch:{ Throwable -> 0x0038 }
        goto L_0x005c;
    L_0x0038:
        r4 = move-exception;
        r5 = r4.getMessage();
        com.appsflyer.AFLogger.a(r5, r4);
        goto L_0x005c;
    L_0x0041:
        r2 = r0;
    L_0x0042:
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x005d }
        r1 = "PreInstall file wasn't found: ";
        r5.<init>(r1);	 Catch:{ all -> 0x005d }
        r4 = r4.getAbsolutePath();	 Catch:{ all -> 0x005d }
        r5.append(r4);	 Catch:{ all -> 0x005d }
        r4 = r5.toString();	 Catch:{ all -> 0x005d }
        com.appsflyer.AFLogger.c(r4);	 Catch:{ all -> 0x005d }
        if (r2 == 0) goto L_0x005c;
    L_0x0059:
        r2.close();	 Catch:{ Throwable -> 0x0038 }
    L_0x005c:
        return r0;
    L_0x005d:
        r4 = move-exception;
    L_0x005e:
        if (r2 == 0) goto L_0x006c;
    L_0x0060:
        r2.close();	 Catch:{ Throwable -> 0x0064 }
        goto L_0x006c;
    L_0x0064:
        r5 = move-exception;
        r0 = r5.getMessage();
        com.appsflyer.AFLogger.a(r0, r5);
    L_0x006c:
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.h.a(java.io.File, java.lang.String):java.lang.String");
    }

    private static File g(String str) {
        if (str != null) {
            try {
                if (str.trim().length() > 0) {
                    return new File(str.trim());
                }
            } catch (Throwable th) {
                AFLogger.a(th.getMessage(), th);
            }
        }
        return null;
    }

    public boolean a(Context context) {
        try {
            if ((context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).flags & 1) != 0) {
                return true;
            }
            return false;
        } catch (NameNotFoundException e) {
            AFLogger.a("Could not check if app is pre installed", e);
        }
    }

    private static String f(Context context, String str) throws NameNotFoundException {
        SharedPreferences sharedPreferences = context.getSharedPreferences("appsflyer-data", 0);
        if (sharedPreferences.contains("CACHED_CHANNEL")) {
            return sharedPreferences.getString("CACHED_CHANNEL", null);
        }
        b(context, "CACHED_CHANNEL", str);
        return str;
    }

    public String a(ContentResolver contentResolver) {
        ContentResolver contentResolver2 = contentResolver;
        Cursor query = contentResolver2.query(Uri.parse("content://com.facebook.katana.provider.AttributionIdProvider"), new String[]{"aid"}, null, null, null);
        String str = null;
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    String string = query.getString(query.getColumnIndex("aid"));
                    if (query != null) {
                        try {
                            query.close();
                        } catch (Exception e) {
                            AFLogger.a(e.getMessage(), e);
                        }
                    }
                    str = string;
                    return str;
                }
            } catch (Exception e2) {
                AFLogger.a("Could not collect cursor attribution. ", e2);
                if (query != null) {
                    try {
                        query.close();
                    } catch (Exception e3) {
                        AFLogger.a(e3.getMessage(), e3);
                    }
                }
            } catch (Throwable th) {
                if (query != null) {
                    try {
                        query.close();
                    } catch (Exception e32) {
                        AFLogger.a(e32.getMessage(), e32);
                    }
                }
            }
        }
        if (query != null) {
            try {
                query.close();
            } catch (Exception e322) {
                AFLogger.a(e322.getMessage(), e322);
            }
        }
        return null;
    }

    static SharedPreferences b(Context context) {
        return context.getSharedPreferences("appsflyer-data", 0);
    }

    static int a(SharedPreferences sharedPreferences) {
        return a(sharedPreferences, "appsFlyerCount", false);
    }

    private static int a(SharedPreferences sharedPreferences, String str, boolean z) {
        int i = sharedPreferences.getInt(str, 0);
        if (z) {
            i++;
            Editor edit = sharedPreferences.edit();
            edit.putInt(str, i);
            if (VERSION.SDK_INT >= 9) {
                edit.apply();
            } else {
                edit.commit();
            }
        }
        if (ah.a().f()) {
            ah.a().a(String.valueOf(i));
        }
        return i;
    }

    /* JADX WARNING: Removed duplicated region for block: B:103:0x0211  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x0205 A:{SYNTHETIC, Splitter:B:95:0x0205} */
    private void a(java.net.URL r21, java.lang.String r22, java.lang.String r23, java.lang.ref.WeakReference<android.content.Context> r24, java.lang.String r25, boolean r26) throws java.io.IOException {
        /*
        r20 = this;
        r1 = r20;
        r2 = r22;
        r3 = r23;
        r4 = r25;
        r6 = r24.get();
        r6 = (android.content.Context) r6;
        r7 = 1;
        r8 = 0;
        if (r26 == 0) goto L_0x0018;
    L_0x0012:
        r9 = o;
        if (r9 == 0) goto L_0x0018;
    L_0x0016:
        r9 = r7;
        goto L_0x0019;
    L_0x0018:
        r9 = r8;
    L_0x0019:
        r10 = 0;
        r11 = com.appsflyer.ah.a();	 Catch:{ all -> 0x020c }
        r12 = r21.toString();	 Catch:{ all -> 0x020c }
        r11.a(r12, r2);	 Catch:{ all -> 0x020c }
        r11 = r21.openConnection();	 Catch:{ all -> 0x020c }
        r11 = (java.net.HttpURLConnection) r11;	 Catch:{ all -> 0x020c }
        r12 = "POST";
        r11.setRequestMethod(r12);	 Catch:{ all -> 0x0209 }
        r12 = r22.getBytes();	 Catch:{ all -> 0x0209 }
        r12 = r12.length;	 Catch:{ all -> 0x0209 }
        r13 = "Content-Length";
        r12 = java.lang.String.valueOf(r12);	 Catch:{ all -> 0x0209 }
        r11.setRequestProperty(r13, r12);	 Catch:{ all -> 0x0209 }
        r12 = "Content-Type";
        r13 = "application/json";
        r11.setRequestProperty(r12, r13);	 Catch:{ all -> 0x0209 }
        r12 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r11.setConnectTimeout(r12);	 Catch:{ all -> 0x0209 }
        r11.setDoOutput(r7);	 Catch:{ all -> 0x0209 }
        r12 = new java.io.OutputStreamWriter;	 Catch:{ all -> 0x0201 }
        r13 = r11.getOutputStream();	 Catch:{ all -> 0x0201 }
        r14 = "UTF-8";
        r12.<init>(r13, r14);	 Catch:{ all -> 0x0201 }
        r12.write(r2);	 Catch:{ all -> 0x01fd }
        r12.close();	 Catch:{ all -> 0x0209 }
        r2 = r11.getResponseCode();	 Catch:{ all -> 0x0209 }
        r12 = a(r11);	 Catch:{ all -> 0x0209 }
        r13 = com.appsflyer.ah.a();	 Catch:{ all -> 0x0209 }
        r14 = r21.toString();	 Catch:{ all -> 0x0209 }
        r13.a(r14, r2, r12);	 Catch:{ all -> 0x0209 }
        r13 = "response code: ";
        r14 = java.lang.String.valueOf(r2);	 Catch:{ all -> 0x0209 }
        r13 = r13.concat(r14);	 Catch:{ all -> 0x0209 }
        com.appsflyer.AFLogger.d(r13);	 Catch:{ all -> 0x0209 }
        r13 = "AppsFlyer_4.8.13";
        r14 = "SERVER_RESPONSE_CODE";
        r15 = java.lang.Integer.toString(r2);	 Catch:{ all -> 0x0209 }
        a(r6, r13, r14, r15);	 Catch:{ all -> 0x0209 }
        r13 = "appsflyer-data";
        r13 = r6.getSharedPreferences(r13, r8);	 Catch:{ all -> 0x0209 }
        r14 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r2 != r14) goto L_0x0151;
    L_0x0093:
        r2 = r24.get();	 Catch:{ all -> 0x0209 }
        if (r2 == 0) goto L_0x00a1;
    L_0x0099:
        if (r26 == 0) goto L_0x00a1;
    L_0x009b:
        r14 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x0209 }
        r1.k = r14;	 Catch:{ all -> 0x0209 }
    L_0x00a1:
        r2 = "afUninstallToken";
        r5 = com.appsflyer.i.a();	 Catch:{ all -> 0x0209 }
        r2 = r5.a(r2);	 Catch:{ all -> 0x0209 }
        if (r2 == 0) goto L_0x00d8;
    L_0x00ad:
        r5 = "Uninstall Token exists: ";
        r14 = java.lang.String.valueOf(r2);	 Catch:{ all -> 0x0209 }
        r5 = r5.concat(r14);	 Catch:{ all -> 0x0209 }
        com.appsflyer.AFLogger.c(r5);	 Catch:{ all -> 0x0209 }
        r5 = "sentRegisterRequestToAF";
        r5 = r13.getBoolean(r5, r8);	 Catch:{ all -> 0x0209 }
        if (r5 != 0) goto L_0x00f8;
    L_0x00c2:
        r5 = "Resending Uninstall token to AF servers: ";
        r14 = java.lang.String.valueOf(r2);	 Catch:{ all -> 0x0209 }
        r5 = r5.concat(r14);	 Catch:{ all -> 0x0209 }
        com.appsflyer.AFLogger.c(r5);	 Catch:{ all -> 0x0209 }
        r5 = new com.appsflyer.r;	 Catch:{ all -> 0x0209 }
        r5.<init>(r2);	 Catch:{ all -> 0x0209 }
        com.appsflyer.ag.a(r6, r5);	 Catch:{ all -> 0x0209 }
        goto L_0x00f8;
    L_0x00d8:
        r2 = "gcmProjectNumber";
        r5 = com.appsflyer.i.a();	 Catch:{ all -> 0x0209 }
        r2 = r5.a(r2);	 Catch:{ all -> 0x0209 }
        if (r2 == 0) goto L_0x00f8;
    L_0x00e4:
        r2 = "GCM Project number exists. Fetching token and sending to AF servers";
        com.appsflyer.AFLogger.c(r2);	 Catch:{ all -> 0x0209 }
        r2 = new java.lang.ref.WeakReference;	 Catch:{ all -> 0x0209 }
        r2.<init>(r6);	 Catch:{ all -> 0x0209 }
        r5 = new com.appsflyer.ag$a;	 Catch:{ all -> 0x0209 }
        r5.<init>(r2);	 Catch:{ all -> 0x0209 }
        r2 = new java.lang.Void[r8];	 Catch:{ all -> 0x0209 }
        r5.execute(r2);	 Catch:{ all -> 0x0209 }
    L_0x00f8:
        r2 = r1.y;	 Catch:{ all -> 0x0209 }
        if (r2 == 0) goto L_0x00fe;
    L_0x00fc:
        r1.y = r10;	 Catch:{ all -> 0x0209 }
    L_0x00fe:
        if (r4 == 0) goto L_0x0107;
    L_0x0100:
        r2 = com.appsflyer.a.a.a();	 Catch:{ all -> 0x0209 }
        r2.a(r4, r6);	 Catch:{ all -> 0x0209 }
    L_0x0107:
        r2 = r24.get();	 Catch:{ all -> 0x0209 }
        if (r2 == 0) goto L_0x0145;
    L_0x010d:
        if (r4 != 0) goto L_0x0145;
    L_0x010f:
        r2 = "sentSuccessfully";
        r4 = "true";
        b(r6, r2, r4);	 Catch:{ all -> 0x0209 }
        r2 = r1.r;	 Catch:{ all -> 0x0209 }
        if (r2 != 0) goto L_0x0145;
    L_0x011a:
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x0209 }
        r14 = r1.s;	 Catch:{ all -> 0x0209 }
        r16 = r4 - r14;
        r4 = 15000; // 0x3a98 float:2.102E-41 double:7.411E-320;
        r2 = (r16 > r4 ? 1 : (r16 == r4 ? 0 : -1));
        if (r2 >= 0) goto L_0x0129;
    L_0x0128:
        goto L_0x0145;
    L_0x0129:
        r2 = r1.t;	 Catch:{ all -> 0x0209 }
        if (r2 != 0) goto L_0x0145;
    L_0x012d:
        r2 = com.appsflyer.a.a();	 Catch:{ all -> 0x0209 }
        r2 = r2.c();	 Catch:{ all -> 0x0209 }
        r1.t = r2;	 Catch:{ all -> 0x0209 }
        r2 = new com.appsflyer.h$c;	 Catch:{ all -> 0x0209 }
        r2.<init>(r6);	 Catch:{ all -> 0x0209 }
        r4 = r1.t;	 Catch:{ all -> 0x0209 }
        r14 = 1;
        r5 = java.util.concurrent.TimeUnit.SECONDS;	 Catch:{ all -> 0x0209 }
        a(r4, r2, r14, r5);	 Catch:{ all -> 0x0209 }
    L_0x0145:
        r2 = com.appsflyer.q.a(r12);	 Catch:{ all -> 0x0209 }
        r4 = "send_background";
        r2 = r2.optBoolean(r4, r8);	 Catch:{ all -> 0x0209 }
        r1.G = r2;	 Catch:{ all -> 0x0209 }
    L_0x0151:
        r2 = "appsflyerConversionDataRequestRetries";
        r2 = r13.getInt(r2, r8);	 Catch:{ all -> 0x0209 }
        r4 = "appsflyerConversionDataCacheExpiration";
        r14 = 0;
        r4 = r13.getLong(r4, r14);	 Catch:{ all -> 0x0209 }
        r12 = (r4 > r14 ? 1 : (r4 == r14 ? 0 : -1));
        if (r12 == 0) goto L_0x017c;
    L_0x0163:
        r16 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x0209 }
        r18 = r16 - r4;
        r4 = 5184000000; // 0x134fd9000 float:4.7229696E-7 double:2.561236308E-314;
        r12 = (r18 > r4 ? 1 : (r18 == r4 ? 0 : -1));
        if (r12 <= 0) goto L_0x017c;
    L_0x0172:
        r4 = "attributionId";
        b(r6, r4, r10);	 Catch:{ all -> 0x0209 }
        r4 = "appsflyerConversionDataCacheExpiration";
        b(r6, r4, r14);	 Catch:{ all -> 0x0209 }
    L_0x017c:
        r4 = "attributionId";
        r4 = r13.getString(r4, r10);	 Catch:{ all -> 0x0209 }
        if (r4 != 0) goto L_0x01a8;
    L_0x0184:
        if (r3 == 0) goto L_0x01a8;
    L_0x0186:
        if (r9 == 0) goto L_0x01a8;
    L_0x0188:
        r4 = o;	 Catch:{ all -> 0x0209 }
        if (r4 == 0) goto L_0x01a8;
    L_0x018c:
        r4 = 5;
        if (r2 > r4) goto L_0x01a8;
    L_0x018f:
        r2 = com.appsflyer.a.a();	 Catch:{ all -> 0x0209 }
        r2 = r2.c();	 Catch:{ all -> 0x0209 }
        r4 = new com.appsflyer.h$d;	 Catch:{ all -> 0x0209 }
        r5 = r6.getApplicationContext();	 Catch:{ all -> 0x0209 }
        r4.<init>(r5, r3, r2);	 Catch:{ all -> 0x0209 }
        r5 = 10;
        r3 = java.util.concurrent.TimeUnit.MILLISECONDS;	 Catch:{ all -> 0x0209 }
        a(r2, r4, r5, r3);	 Catch:{ all -> 0x0209 }
        goto L_0x01f6;
    L_0x01a8:
        if (r3 != 0) goto L_0x01b0;
    L_0x01aa:
        r2 = "AppsFlyer dev key is missing.";
        com.appsflyer.AFLogger.e(r2);	 Catch:{ all -> 0x0209 }
        goto L_0x01f6;
    L_0x01b0:
        if (r9 == 0) goto L_0x01f6;
    L_0x01b2:
        r2 = o;	 Catch:{ all -> 0x0209 }
        if (r2 == 0) goto L_0x01f6;
    L_0x01b6:
        r2 = "attributionId";
        r2 = r13.getString(r2, r10);	 Catch:{ all -> 0x0209 }
        if (r2 == 0) goto L_0x01f6;
    L_0x01be:
        r2 = "appsFlyerCount";
        r2 = a(r13, r2, r8);	 Catch:{ all -> 0x0209 }
        if (r2 <= r7) goto L_0x01f6;
    L_0x01c6:
        r2 = e(r6);	 Catch:{ l -> 0x01ed }
        if (r2 == 0) goto L_0x01f6;
    L_0x01cc:
        r3 = "is_first_launch";
        r3 = r2.containsKey(r3);	 Catch:{ Throwable -> 0x01e3 }
        if (r3 != 0) goto L_0x01dd;
    L_0x01d4:
        r3 = "is_first_launch";
        r4 = java.lang.Boolean.toString(r8);	 Catch:{ Throwable -> 0x01e3 }
        r2.put(r3, r4);	 Catch:{ Throwable -> 0x01e3 }
    L_0x01dd:
        r3 = o;	 Catch:{ Throwable -> 0x01e3 }
        r3.onInstallConversionDataLoaded(r2);	 Catch:{ Throwable -> 0x01e3 }
        goto L_0x01f6;
    L_0x01e3:
        r0 = move-exception;
        r2 = r0;
        r3 = r2.getLocalizedMessage();	 Catch:{ l -> 0x01ed }
        com.appsflyer.AFLogger.a(r3, r2);	 Catch:{ l -> 0x01ed }
        goto L_0x01f6;
    L_0x01ed:
        r0 = move-exception;
        r2 = r0;
        r3 = r2.getMessage();	 Catch:{ all -> 0x0209 }
        com.appsflyer.AFLogger.a(r3, r2);	 Catch:{ all -> 0x0209 }
    L_0x01f6:
        if (r11 == 0) goto L_0x01fc;
    L_0x01f8:
        r11.disconnect();
        return;
    L_0x01fc:
        return;
    L_0x01fd:
        r0 = move-exception;
        r2 = r0;
        r10 = r12;
        goto L_0x0203;
    L_0x0201:
        r0 = move-exception;
        r2 = r0;
    L_0x0203:
        if (r10 == 0) goto L_0x0208;
    L_0x0205:
        r10.close();	 Catch:{ all -> 0x0209 }
    L_0x0208:
        throw r2;	 Catch:{ all -> 0x0209 }
    L_0x0209:
        r0 = move-exception;
        r2 = r0;
        goto L_0x020f;
    L_0x020c:
        r0 = move-exception;
        r2 = r0;
        r11 = r10;
    L_0x020f:
        if (r11 == 0) goto L_0x0214;
    L_0x0211:
        r11.disconnect();
    L_0x0214:
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.h.a(java.net.URL, java.lang.String, java.lang.String, java.lang.ref.WeakReference, java.lang.String, boolean):void");
    }

    private static void a(ScheduledExecutorService scheduledExecutorService, Runnable runnable, long j, TimeUnit timeUnit) {
        if (scheduledExecutorService != null) {
            try {
                if (!(scheduledExecutorService.isShutdown() || scheduledExecutorService.isTerminated())) {
                    scheduledExecutorService.schedule(runnable, j, timeUnit);
                    return;
                }
            } catch (RejectedExecutionException e) {
                AFLogger.a("scheduleJob failed with RejectedExecutionException Exception", e);
                return;
            } catch (Throwable e2) {
                AFLogger.a("scheduleJob failed with Exception", e2);
                return;
            }
        }
        AFLogger.e("scheduler is null, shut downed or terminated");
    }

    public void a(Map<String, String> map) {
        this.q = map;
    }

    public boolean d() {
        return this.H;
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x005a A:{SYNTHETIC, Splitter:B:28:0x005a} */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0086 A:{SYNTHETIC, Splitter:B:46:0x0086} */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x008b A:{Catch:{ Throwable -> 0x008e }} */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0086 A:{SYNTHETIC, Splitter:B:46:0x0086} */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x008b A:{Catch:{ Throwable -> 0x008e }} */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x005a A:{SYNTHETIC, Splitter:B:28:0x005a} */
    /* JADX WARNING: Missing block: B:30:0x005d, code skipped:
            if (r3 != null) goto L_0x002c;
     */
    @android.support.annotation.NonNull
    static java.lang.String a(java.net.HttpURLConnection r7) {
        /*
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = 0;
        r2 = r7.getErrorStream();	 Catch:{ Throwable -> 0x003d, all -> 0x003a }
        if (r2 != 0) goto L_0x0010;
    L_0x000c:
        r2 = r7.getInputStream();	 Catch:{ Throwable -> 0x003d, all -> 0x003a }
    L_0x0010:
        r3 = new java.io.InputStreamReader;	 Catch:{ Throwable -> 0x003d, all -> 0x003a }
        r3.<init>(r2);	 Catch:{ Throwable -> 0x003d, all -> 0x003a }
        r2 = new java.io.BufferedReader;	 Catch:{ Throwable -> 0x0038 }
        r2.<init>(r3);	 Catch:{ Throwable -> 0x0038 }
    L_0x001a:
        r1 = r2.readLine();	 Catch:{ Throwable -> 0x0033, all -> 0x0030 }
        if (r1 == 0) goto L_0x0029;
    L_0x0020:
        r0.append(r1);	 Catch:{ Throwable -> 0x0033, all -> 0x0030 }
        r1 = 10;
        r0.append(r1);	 Catch:{ Throwable -> 0x0033, all -> 0x0030 }
        goto L_0x001a;
    L_0x0029:
        r2.close();	 Catch:{ Throwable -> 0x0060 }
    L_0x002c:
        r3.close();	 Catch:{ Throwable -> 0x0060 }
        goto L_0x0060;
    L_0x0030:
        r7 = move-exception;
        r1 = r2;
        goto L_0x0084;
    L_0x0033:
        r1 = move-exception;
        r6 = r2;
        r2 = r1;
        r1 = r6;
        goto L_0x003f;
    L_0x0038:
        r2 = move-exception;
        goto L_0x003f;
    L_0x003a:
        r7 = move-exception;
        r3 = r1;
        goto L_0x0084;
    L_0x003d:
        r2 = move-exception;
        r3 = r1;
    L_0x003f:
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0083 }
        r5 = "Could not read connection response from: ";
        r4.<init>(r5);	 Catch:{ all -> 0x0083 }
        r7 = r7.getURL();	 Catch:{ all -> 0x0083 }
        r7 = r7.toString();	 Catch:{ all -> 0x0083 }
        r4.append(r7);	 Catch:{ all -> 0x0083 }
        r7 = r4.toString();	 Catch:{ all -> 0x0083 }
        com.appsflyer.AFLogger.a(r7, r2);	 Catch:{ all -> 0x0083 }
        if (r1 == 0) goto L_0x005d;
    L_0x005a:
        r1.close();	 Catch:{ Throwable -> 0x0060 }
    L_0x005d:
        if (r3 == 0) goto L_0x0060;
    L_0x005f:
        goto L_0x002c;
    L_0x0060:
        r7 = r0.toString();
        r0 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x006a }
        r0.<init>(r7);	 Catch:{ JSONException -> 0x006a }
        return r7;
    L_0x006a:
        r0 = new org.json.JSONObject;
        r0.<init>();
        r1 = "string_response";
        r0.put(r1, r7);	 Catch:{ JSONException -> 0x0079 }
        r7 = r0.toString();	 Catch:{ JSONException -> 0x0079 }
        return r7;
    L_0x0079:
        r7 = new org.json.JSONObject;
        r7.<init>();
        r7 = r7.toString();
        return r7;
    L_0x0083:
        r7 = move-exception;
    L_0x0084:
        if (r1 == 0) goto L_0x0089;
    L_0x0086:
        r1.close();	 Catch:{ Throwable -> 0x008e }
    L_0x0089:
        if (r3 == 0) goto L_0x008e;
    L_0x008b:
        r3.close();	 Catch:{ Throwable -> 0x008e }
    L_0x008e:
        throw r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.h.a(java.net.HttpURLConnection):java.lang.String");
    }

    private static float h(Context context) {
        float f;
        try {
            Intent registerReceiver = context.getApplicationContext().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            int intExtra = registerReceiver.getIntExtra("level", -1);
            int intExtra2 = registerReceiver.getIntExtra("scale", -1);
            if (intExtra == -1 || intExtra2 == -1) {
                return 50.0f;
            }
            f = (((float) intExtra) / ((float) intExtra2)) * 100.0f;
            return f;
        } catch (Throwable th) {
            AFLogger.a(th.getMessage(), th);
            f = 1.0f;
        }
    }

    public String e() {
        String a = i.a().a("custom_host");
        return a != null ? a : "appsflyer.com";
    }

    private static void b(Context context, String str, String str2) {
        Editor edit = context.getSharedPreferences("appsflyer-data", 0).edit();
        edit.putString(str, str2);
        if (VERSION.SDK_INT >= 9) {
            edit.apply();
        } else {
            edit.commit();
        }
    }

    private static void b(Context context, String str, int i) {
        Editor edit = context.getSharedPreferences("appsflyer-data", 0).edit();
        edit.putInt(str, i);
        if (VERSION.SDK_INT >= 9) {
            edit.apply();
        } else {
            edit.commit();
        }
    }

    private static void b(Context context, String str, long j) {
        Editor edit = context.getSharedPreferences("appsflyer-data", 0).edit();
        edit.putLong(str, j);
        if (VERSION.SDK_INT >= 9) {
            edit.apply();
        } else {
            edit.commit();
        }
    }
}
