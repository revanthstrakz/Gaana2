package com.inmobi.ads.c;

import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.facebook.internal.AnalyticsEvents;
import com.google.android.gms.cast.HlsSegmentFormat;
import com.inmobi.ads.InMobiAdRequestStatus;
import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.ads.aj;
import com.inmobi.ads.bi;
import com.inmobi.ads.bj;
import com.inmobi.ads.f;
import com.inmobi.ads.i;
import com.inmobi.ads.i.d;
import com.inmobi.ads.p;
import com.inmobi.commons.core.configs.b;
import com.inmobi.commons.core.configs.b.c;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class a implements c {
    public static ConcurrentHashMap<bi, i> a = new ConcurrentHashMap(8, 0.9f, 3);
    public static com.inmobi.ads.c b = null;
    private static final String d = "a";
    private static volatile a e;
    private static volatile a f;
    private static volatile a g;
    private static final Object h = new Object();
    private static final Object i = new Object();
    private static final Object j = new Object();
    public String c;

    /* renamed from: com.inmobi.ads.c.a$2 */
    class AnonymousClass2 implements Runnable {
        final /* synthetic */ f a;
        private d c;

        public AnonymousClass2(f fVar) {
            this.a = fVar;
        }

        public final void run() {
            try {
                Context b = com.inmobi.commons.a.a.b();
                if (b != null) {
                    bi a = bi.a(this.a.a, this.a.g, this.a.e, this.a.f);
                    a.f = this.a.j;
                    a.d;
                    StringBuilder stringBuilder = new StringBuilder("preFetchAdUnit. pid:");
                    stringBuilder.append(a.a);
                    stringBuilder.append(" tp:");
                    stringBuilder.append(a.b);
                    if (a.c == null && a.b != null) {
                        HashMap hashMap = new HashMap();
                        hashMap.put("tp", a.b);
                        a.c = hashMap;
                    }
                    this.c = new a(a);
                    i a2 = a.b(a.this.c, b, a);
                    if (a2 != null) {
                        a2.e = a.d;
                        a2.f = a.c;
                        a2.n = true;
                        a2.q = this.c;
                        if (a.this.c.equalsIgnoreCase("banner")) {
                            ((p) a2).A = this.a.c;
                            ((p) a2).y = true;
                        }
                        a2.a(true);
                    }
                }
            } catch (Exception e) {
                a.d;
                new StringBuilder("SDK encountered an unexpected error preloading ad units; ").append(e.getMessage());
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            }
        }
    }

    /* renamed from: com.inmobi.ads.c.a$3 */
    class AnonymousClass3 implements com.inmobi.ads.bl.a {
        final /* synthetic */ f a;

        public AnonymousClass3(f fVar) {
            this.a = fVar;
        }

        public final void a(long j) {
            a.d;
        }

        public final void b(long j, InMobiAdRequestStatus inMobiAdRequestStatus) {
            a.d;
            new StringBuilder("Interstitial Prefetch failed with the message - ").append(inMobiAdRequestStatus.getMessage());
        }

        public final void a(String str, Map<String, Object> map) {
            a.a(str, (Map) map, this.a);
        }
    }

    static class a implements d {
        private bi a;

        a(bi biVar) {
            this.a = biVar;
        }

        public final void a(@NonNull i iVar) {
            a.d;
            a.a.remove(this.a);
        }

        public final void a(@NonNull i iVar, @NonNull InMobiAdRequestStatus inMobiAdRequestStatus) {
            a.d;
            new StringBuilder("onAdLoadFailed called. Status:").append(inMobiAdRequestStatus.getMessage());
            a.a.remove(this.a);
            if (StatusCode.NO_FILL.equals(inMobiAdRequestStatus.getStatusCode())) {
                iVar.d("PreLoadServerNoFill");
            }
        }
    }

    private static a d() {
        a aVar = e;
        if (aVar == null) {
            synchronized (h) {
                aVar = e;
                if (aVar == null) {
                    aVar = new a("banner");
                    e = aVar;
                }
            }
        }
        return aVar;
    }

    private static a e() {
        a aVar = f;
        if (aVar == null) {
            synchronized (i) {
                aVar = f;
                if (aVar == null) {
                    aVar = new a("int");
                    f = aVar;
                }
            }
        }
        return aVar;
    }

    private static a f() {
        a aVar = g;
        if (aVar == null) {
            synchronized (j) {
                aVar = g;
                if (aVar == null) {
                    aVar = new a(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE);
                    g = aVar;
                }
            }
        }
        return aVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x003e  */
    @android.support.annotation.NonNull
    public static com.inmobi.ads.c.a a(java.lang.String r2) {
        /*
        r0 = r2.hashCode();
        r1 = -1396342996; // 0xffffffffacc57f2c float:-5.6131957E-12 double:NaN;
        if (r0 == r1) goto L_0x0028;
    L_0x0009:
        r1 = -1052618729; // 0xffffffffc1425017 float:-12.144553 double:NaN;
        if (r0 == r1) goto L_0x001e;
    L_0x000e:
        r1 = 104431; // 0x197ef float:1.46339E-40 double:5.1596E-319;
        if (r0 == r1) goto L_0x0014;
    L_0x0013:
        goto L_0x0032;
    L_0x0014:
        r0 = "int";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x0032;
    L_0x001c:
        r2 = 2;
        goto L_0x0033;
    L_0x001e:
        r0 = "native";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x0032;
    L_0x0026:
        r2 = 1;
        goto L_0x0033;
    L_0x0028:
        r0 = "banner";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x0032;
    L_0x0030:
        r2 = 0;
        goto L_0x0033;
    L_0x0032:
        r2 = -1;
    L_0x0033:
        switch(r2) {
            case 0: goto L_0x0048;
            case 1: goto L_0x0043;
            case 2: goto L_0x003e;
            default: goto L_0x0036;
        };
    L_0x0036:
        r2 = new java.lang.IllegalArgumentException;
        r0 = "Unknown adType passed";
        r2.<init>(r0);
        throw r2;
    L_0x003e:
        r2 = e();
        return r2;
    L_0x0043:
        r2 = f();
        return r2;
    L_0x0048:
        r2 = d();
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.c.a.a(java.lang.String):com.inmobi.ads.c.a");
    }

    @Nullable
    private static i b(String str, @NonNull Context context, @NonNull bi biVar) {
        int i = -1;
        try {
            int hashCode = str.hashCode();
            if (hashCode != -1396342996) {
                if (hashCode != -1052618729) {
                    if (hashCode == 104431) {
                        if (str.equals("int")) {
                            i = 1;
                        }
                    }
                } else if (str.equals(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE)) {
                    i = 2;
                }
            } else if (str.equals("banner")) {
                i = 0;
            }
            switch (i) {
                case 0:
                    return p.a(context, biVar, null, 1);
                case 1:
                    return com.inmobi.ads.ac.a.a(com.inmobi.commons.a.a.b(), biVar, null);
                case 2:
                    return aj.a(context, biVar, null, 1);
                default:
                    break;
            }
        } catch (IllegalStateException e) {
            e.getMessage();
        }
        return null;
    }

    a(String str) {
        this.c = str;
        b = new com.inmobi.ads.c();
        b.a().a(b, (c) this);
        com.inmobi.commons.core.e.b.a().a("ads", b.l);
    }

    public final void a(com.inmobi.commons.core.configs.a aVar) {
        b = (com.inmobi.ads.c) aVar;
        com.inmobi.commons.core.e.b.a().a("ads", b.l);
    }

    private void g() {
        Iterator it = a.entrySet().iterator();
        while (it.hasNext()) {
            try {
                Entry entry = (Entry) it.next();
                final i iVar = (i) entry.getValue();
                if (iVar.h()) {
                    StringBuilder stringBuilder = new StringBuilder("cleanUpExpiredCachedAdUnits. pid:");
                    stringBuilder.append(((bi) entry.getKey()).a);
                    stringBuilder.append(" tp:");
                    stringBuilder.append(((bi) entry.getKey()).b);
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public final void run() {
                            try {
                                iVar.v();
                            } catch (Exception e) {
                                a.d;
                                new StringBuilder("Encountered an unexpected error clearing the ad unit: ").append(e.getMessage());
                                Logger.a(InternalLogLevel.DEBUG, "InMobi", "SDK encountered an unexpected error clearing an old ad");
                                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                            }
                        }
                    });
                    it.remove();
                }
            } catch (Exception e) {
                new StringBuilder("SDK encountered an unexpected error in expiring ad units; ").append(e.getMessage());
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                return;
            }
        }
    }

    public final void b() {
        h();
        g();
    }

    public final void a(final bi biVar) {
        if (b.c(this.c).a) {
            new Thread() {
                public final void run() {
                    List arrayList = new ArrayList();
                    arrayList.add(biVar);
                    int a = bj.a().a(arrayList, a.b.c(a.this.c).c);
                    if (a > 0) {
                        HashMap hashMap = new HashMap();
                        hashMap.put("count", Integer.valueOf(a));
                        hashMap.put("type", a.this.c);
                        hashMap.put("plId", Long.valueOf(biVar.a));
                        com.inmobi.commons.core.e.b.a();
                        com.inmobi.commons.core.e.b.a("ads", "PreLoadPidOverflow", hashMap);
                    }
                }
            }.start();
        }
    }

    private void h() {
        if (b.c(this.c).a) {
            bj.a();
            int a = bj.a(b.c(this.c).b, this.c);
            if (a > 0) {
                try {
                    HashMap hashMap = new HashMap();
                    hashMap.put("type", this.c);
                    hashMap.put("count", Integer.valueOf(a));
                    com.inmobi.commons.core.e.b.a();
                    com.inmobi.commons.core.e.b.a("ads", "PreLoadPidExpiry", hashMap);
                } catch (Exception e) {
                    StringBuilder stringBuilder = new StringBuilder("Error in submitting telemetry event : (");
                    stringBuilder.append(e.getMessage());
                    stringBuilder.append(")");
                }
            }
        }
    }

    @NonNull
    public static String a(Map<String, String> map) {
        if (map == null) {
            return "";
        }
        if (((String) map.get("tp")) == null) {
            return "";
        }
        return (String) map.get("tp");
    }

    public static void a(String str, Map<String, Object> map, f fVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("type", fVar.e);
        hashMap.put("plId", Long.valueOf(fVar.a));
        hashMap.put("isPreloaded", Integer.valueOf(1));
        hashMap.put("networkType", Integer.valueOf(com.inmobi.commons.core.utilities.b.b.a()));
        hashMap.put(HlsSegmentFormat.TS, Long.valueOf(System.currentTimeMillis()));
        if (map.get("clientRequestId") == null) {
            hashMap.put("clientRequestId", fVar.i);
        }
        for (Entry entry : map.entrySet()) {
            hashMap.put(entry.getKey(), entry.getValue());
        }
        try {
            com.inmobi.commons.core.e.b.a();
            com.inmobi.commons.core.e.b.a("ads", str, hashMap);
        } catch (Exception e) {
            StringBuilder stringBuilder = new StringBuilder("Error in submitting telemetry event : (");
            stringBuilder.append(e.getMessage());
            stringBuilder.append(")");
        }
    }

    public final void a() {
        Application application = (Application) com.inmobi.commons.a.a.b();
        if (application != null) {
            application.registerComponentCallbacks(new ComponentCallbacks2() {
                public final void onConfigurationChanged(Configuration configuration) {
                }

                public final void onLowMemory() {
                }

                public final void onTrimMemory(int i) {
                    if (i == 15) {
                        a.a(a.this);
                    }
                }
            });
        }
        h();
        g();
        if (b.c(this.c).a) {
            bj.a();
            ArrayList arrayList = (ArrayList) bj.a(this.c);
            for (int i = 0; i < arrayList.size(); i++) {
                final bi biVar = (bi) arrayList.get(i);
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    private d c;

                    public final void run() {
                        try {
                            Context b = com.inmobi.commons.a.a.b();
                            if (b != null) {
                                a.d;
                                StringBuilder stringBuilder = new StringBuilder("preFetchAdUnit. pid:");
                                stringBuilder.append(biVar.a);
                                stringBuilder.append(" tp:");
                                stringBuilder.append(biVar.b);
                                if (biVar.c == null && biVar.b != null) {
                                    HashMap hashMap = new HashMap();
                                    hashMap.put("tp", biVar.b);
                                    biVar.c = hashMap;
                                }
                                this.c = new a(biVar);
                                i a = a.b(a.this.c, b, biVar);
                                if (a != null) {
                                    a.e = biVar.d;
                                    a.f = biVar.c;
                                    a.n = true;
                                    a.q = this.c;
                                    a.a(true);
                                }
                            }
                        } catch (Exception e) {
                            a.d;
                            new StringBuilder("SDK encountered an unexpected error preloading ad units; ").append(e.getMessage());
                            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                        }
                    }
                });
            }
        }
    }
}
