package com.inmobi.ads;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.cast.HlsSegmentFormat;
import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.ads.c.d;
import com.inmobi.ads.cache.AssetStore;
import com.inmobi.ads.cache.b;
import com.inmobi.ads.cache.f;
import com.inmobi.commons.core.utilities.b.e;
import com.inmobi.commons.core.utilities.c;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class h implements com.inmobi.ads.e.a {
    private static final String f = "h";
    @NonNull
    final a a;
    @NonNull
    public final d b;
    @NonNull
    f c;
    @NonNull
    public d d;
    long e = 0;
    private final f g = new f() {
        public final void a(b bVar) {
            String str;
            h.f;
            StringBuilder stringBuilder = new StringBuilder("onAssetsFetchFailure of batch ");
            if (bVar == null) {
                str = null;
            } else {
                str = bVar.toString();
            }
            stringBuilder.append(str);
            ArrayList<Long> arrayList = new ArrayList();
            if (bVar != null) {
                for (com.inmobi.ads.cache.a aVar : bVar.a) {
                    Map hashMap = new HashMap();
                    hashMap.put("url", aVar.d);
                    hashMap.put("latency", Long.valueOf(aVar.a));
                    hashMap.put("size", Long.valueOf(c.a(aVar.e)));
                    h.this.a.a("VideoAssetDownloadFailed", hashMap);
                    for (a aVar2 : h.this.b.b(aVar.d, h.this.c.c)) {
                        if (!arrayList.contains(Long.valueOf(aVar2.d))) {
                            arrayList.add(Long.valueOf(aVar2.d));
                        }
                    }
                }
            }
            if (!arrayList.contains(Long.valueOf(h.this.c.a))) {
                arrayList.add(Long.valueOf(h.this.c.a));
            }
            for (Long longValue : arrayList) {
                h.this.a.a(longValue.longValue(), false);
            }
        }

        public final void b(b bVar) {
            String str;
            h.f;
            StringBuilder stringBuilder = new StringBuilder("onAssetsFetchSuccess of batch ");
            if (bVar == null) {
                str = null;
            } else {
                str = bVar.toString();
            }
            stringBuilder.append(str);
            ArrayList<Long> arrayList = new ArrayList();
            if (bVar != null) {
                for (com.inmobi.ads.cache.a aVar : bVar.a) {
                    Map hashMap = new HashMap();
                    hashMap.put("url", aVar.d);
                    hashMap.put("latency", Long.valueOf(aVar.a));
                    hashMap.put("size", Long.valueOf(c.a(aVar.e)));
                    hashMap.put("clientRequestId", bVar.f);
                    if (aVar.j) {
                        h.this.a.a("GotCachedVideoAsset", hashMap);
                    } else {
                        h.this.a.a("VideoAssetDownloaded", hashMap);
                    }
                    List<a> a = h.this.b.a(aVar.d, h.this.c.c);
                    h.f;
                    StringBuilder stringBuilder2 = new StringBuilder("Found ");
                    stringBuilder2.append(a.size());
                    stringBuilder2.append(" ads mapping to this asset");
                    for (a aVar2 : a) {
                        if (!arrayList.contains(Long.valueOf(aVar2.d))) {
                            arrayList.add(Long.valueOf(aVar2.d));
                        }
                    }
                }
            }
            if (!arrayList.contains(Long.valueOf(h.this.c.a))) {
                arrayList.add(Long.valueOf(h.this.c.a));
            }
            for (Long longValue : arrayList) {
                long longValue2 = longValue.longValue();
                h.f;
                StringBuilder stringBuilder3 = new StringBuilder("Notifying ad unit with placement ID (");
                stringBuilder3.append(longValue2);
                stringBuilder3.append(")");
                h.this.a.a(longValue2, true);
            }
        }
    };

    public interface a {
        void a(long j, InMobiAdRequestStatus inMobiAdRequestStatus);

        void a(long j, @NonNull a aVar);

        void a(long j, boolean z);

        void a(String str, Map<String, Object> map);

        void b(long j, a aVar);
    }

    public h(@NonNull a aVar, @NonNull d dVar, @NonNull f fVar) {
        this.a = aVar;
        this.b = d.a();
        this.d = dVar;
        this.c = fVar;
    }

    public static void a() {
        if (e.b()) {
            d.c();
        }
    }

    public static void c() {
        b.b();
    }

    /* Access modifiers changed, original: final */
    public final void a(final a aVar) {
        new Thread() {
            public final void run() {
                h.this.b;
                d.a(aVar);
            }
        }.start();
    }

    /* Access modifiers changed, original: final */
    public final void a(@NonNull final String str) {
        new Thread() {
            public final void run() {
                h.this.b;
                d.a(str);
            }
        }.start();
    }

    /* Access modifiers changed, original: final */
    @NonNull
    public final String a(f fVar, boolean z) {
        b(fVar, z);
        this.e = SystemClock.elapsedRealtime();
        new e(fVar, this).a();
        Map hashMap = new HashMap();
        hashMap.put("isPreloaded", fVar.c());
        hashMap.put("clientRequestId", fVar.i);
        hashMap.put("im-accid", com.inmobi.commons.a.a.e());
        this.a.a("ServerCallInitiated", hashMap);
        return fVar.i;
    }

    private void a(List<a> list, @NonNull String str, @Nullable String str2) {
        this.b.a(list, this.c.a, this.d.a, this.c.e, this.c.j, str, str2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x003a A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0092  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x003a A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0092  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x003b  */
    private void a(java.util.List<com.inmobi.ads.a> r10, java.lang.String r11) {
        /*
        r9 = this;
        r0 = 0;
        r1 = r10.get(r0);
        r1 = (com.inmobi.ads.a) r1;
        r2 = r1.e();
        r3 = java.util.Locale.ENGLISH;
        r2 = r2.toUpperCase(r3);
        r3 = r2.hashCode();
        r4 = -598127114; // 0xffffffffdc594df6 float:-2.44663156E17 double:NaN;
        r5 = 1;
        if (r3 == r4) goto L_0x002b;
    L_0x001b:
        r4 = 2228139; // 0x21ffab float:3.122288E-39 double:1.100847E-317;
        if (r3 == r4) goto L_0x0021;
    L_0x0020:
        goto L_0x0035;
    L_0x0021:
        r3 = "HTML";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0035;
    L_0x0029:
        r2 = r0;
        goto L_0x0036;
    L_0x002b:
        r3 = "INMOBIJSON";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0035;
    L_0x0033:
        r2 = r5;
        goto L_0x0036;
    L_0x0035:
        r2 = -1;
    L_0x0036:
        r3 = 0;
        switch(r2) {
            case 0: goto L_0x0092;
            case 1: goto L_0x003b;
            default: goto L_0x003a;
        };
    L_0x003a:
        return;
    L_0x003b:
        r9.a(r10, r11, r3);
        r2 = "int";
        r3 = r9.c;
        r3 = r3.e;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0054;
    L_0x004a:
        r11 = r9.a;
        r0 = r9.c;
        r2 = r0.a;
        r11.b(r2, r1);
        goto L_0x008e;
    L_0x0054:
        r2 = "native";
        r3 = r9.c;
        r3 = r3.e;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x008e;
    L_0x0060:
        r3 = r9.b;
        r2 = r9.c;
        r4 = r2.a;
        r2 = r9.c;
        r6 = r2.c;
        r2 = r9.c;
        r7 = r2.j;
        r8 = r11;
        r11 = r3.b(r4, r6, r7, r8);
        if (r11 == 0) goto L_0x007f;
    L_0x0075:
        r1 = r1.a(r11);
        if (r1 != 0) goto L_0x0080;
    L_0x007b:
        r10.add(r0, r11);
        goto L_0x0080;
    L_0x007f:
        r11 = r1;
    L_0x0080:
        r0 = r9.a;
        r1 = r9.c;
        r1 = r1.a;
        r0.a(r1, r11);
        r11 = r9.c;
        r9.a(r11);
    L_0x008e:
        r9.a(r10);
        return;
    L_0x0092:
        r0 = "native";
        r2 = r9.c;
        r2 = r2.e;
        r0 = r0.equals(r2);
        if (r0 == 0) goto L_0x00af;
    L_0x009e:
        r10 = r9.a;
        r11 = r9.c;
        r0 = r11.a;
        r11 = new com.inmobi.ads.InMobiAdRequestStatus;
        r2 = com.inmobi.ads.InMobiAdRequestStatus.StatusCode.INTERNAL_ERROR;
        r11.<init>(r2);
        r10.a(r0, r11);
        return;
    L_0x00af:
        r0 = r10.size();
        r10 = r10.subList(r5, r0);
        r9.a(r10, r11, r3);
        r10 = r9.a;
        r11 = r9.c;
        r2 = r11.a;
        r10.a(r2, r1);
        r10 = r9.c;
        r9.a(r10);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.h.a(java.util.List, java.lang.String):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x00b5  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0055 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00bc  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0056  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0055 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00bc  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0056  */
    private void b(java.util.List<com.inmobi.ads.a> r7, java.lang.String r8, @android.support.annotation.NonNull java.lang.String r9) {
        /*
        r6 = this;
        r6.a(r7, r8, r9);
        r8 = r6.c;
        r8 = r8.e;
        com.inmobi.ads.b.b();
        r8 = com.inmobi.ads.d.c(r9);
        if (r8 != 0) goto L_0x0021;
    L_0x0010:
        r7 = r6.a;
        r8 = r6.c;
        r8 = r8.a;
        r0 = new com.inmobi.ads.InMobiAdRequestStatus;
        r1 = com.inmobi.ads.InMobiAdRequestStatus.StatusCode.INTERNAL_ERROR;
        r0.<init>(r1);
        r7.a(r8, r0);
        return;
    L_0x0021:
        r0 = r8.e();
        r1 = java.util.Locale.ENGLISH;
        r0 = r0.toUpperCase(r1);
        r1 = r0.hashCode();
        r2 = -598127114; // 0xffffffffdc594df6 float:-2.44663156E17 double:NaN;
        r3 = -1;
        r4 = 1;
        r5 = 0;
        if (r1 == r2) goto L_0x0047;
    L_0x0037:
        r2 = 2228139; // 0x21ffab float:3.122288E-39 double:1.100847E-317;
        if (r1 == r2) goto L_0x003d;
    L_0x003c:
        goto L_0x0051;
    L_0x003d:
        r1 = "HTML";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x0051;
    L_0x0045:
        r0 = r5;
        goto L_0x0052;
    L_0x0047:
        r1 = "INMOBIJSON";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x0051;
    L_0x004f:
        r0 = r4;
        goto L_0x0052;
    L_0x0051:
        r0 = r3;
    L_0x0052:
        switch(r0) {
            case 0: goto L_0x00bc;
            case 1: goto L_0x0056;
            default: goto L_0x0055;
        };
    L_0x0055:
        return;
    L_0x0056:
        r0 = r6.c;
        r0 = r0.e;
        r1 = r0.hashCode();
        r2 = -1052618729; // 0xffffffffc1425017 float:-12.144553 double:NaN;
        if (r1 == r2) goto L_0x0073;
    L_0x0063:
        r2 = 104431; // 0x197ef float:1.46339E-40 double:5.1596E-319;
        if (r1 == r2) goto L_0x0069;
    L_0x0068:
        goto L_0x007c;
    L_0x0069:
        r1 = "int";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x007c;
    L_0x0071:
        r3 = r5;
        goto L_0x007c;
    L_0x0073:
        r1 = "native";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x007c;
    L_0x007b:
        r3 = r4;
    L_0x007c:
        switch(r3) {
            case 0: goto L_0x0092;
            case 1: goto L_0x0080;
            default: goto L_0x007f;
        };
    L_0x007f:
        goto L_0x009b;
    L_0x0080:
        com.inmobi.ads.d.a(r9);
        r9 = r6.a;
        r0 = r6.c;
        r0 = r0.a;
        r9.a(r0, r8);
        r9 = r6.c;
        r6.a(r9);
        goto L_0x009b;
    L_0x0092:
        r9 = r6.a;
        r0 = r6.c;
        r0 = r0.a;
        r9.b(r0, r8);
    L_0x009b:
        r9 = r7.iterator();
    L_0x009f:
        r0 = r9.hasNext();
        if (r0 == 0) goto L_0x00b2;
    L_0x00a5:
        r0 = r9.next();
        r0 = (com.inmobi.ads.a) r0;
        r0 = r8.a(r0);
        if (r0 == 0) goto L_0x009f;
    L_0x00b1:
        goto L_0x00b3;
    L_0x00b2:
        r4 = r5;
    L_0x00b3:
        if (r4 != 0) goto L_0x00b8;
    L_0x00b5:
        r7.add(r8);
    L_0x00b8:
        r6.a(r7);
        return;
    L_0x00bc:
        com.inmobi.ads.d.a(r9);
        r7 = r6.a;
        r9 = r6.c;
        r0 = r9.a;
        r7.a(r0, r8);
        r7 = r6.c;
        r6.a(r7);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.h.b(java.util.List, java.lang.String, java.lang.String):void");
    }

    public final void a(g gVar) {
        StringBuilder stringBuilder = new StringBuilder();
        List<a> a = a(gVar, stringBuilder);
        String stringBuilder2 = stringBuilder.toString();
        boolean isEmpty = TextUtils.isEmpty(stringBuilder2);
        Map hashMap;
        if (a == null) {
            new StringBuilder("Could not parse ad response:").append(gVar.a.b());
            this.a.a(this.c.a, new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR));
        } else if (a.size() == 0 && isEmpty) {
            new StringBuilder("Ad response received but no ad available:").append(gVar.a.b());
            hashMap = new HashMap();
            hashMap.put("latency", Long.valueOf(SystemClock.elapsedRealtime() - this.e));
            hashMap.put("isPreloaded", this.c.c());
            hashMap.put("im-accid", com.inmobi.commons.a.a.e());
            this.a.a("ServerNoFill", hashMap);
            this.a.a(this.c.a, new InMobiAdRequestStatus(StatusCode.NO_FILL));
        } else {
            hashMap = new HashMap();
            hashMap.put("numberOfAdsReturned", Integer.valueOf(a.size()));
            hashMap.put("latency", Long.valueOf(SystemClock.elapsedRealtime() - this.e));
            hashMap.put("isPreloaded", this.c.c());
            hashMap.put("im-accid", com.inmobi.commons.a.a.e());
            this.a.a("ServerFill", hashMap);
            for (a aVar : a) {
                Map hashMap2 = new HashMap();
                hashMap2.put(HlsSegmentFormat.TS, Long.valueOf(System.currentTimeMillis()));
                hashMap2.put("impId", aVar.g);
                hashMap2.put("plId", Long.valueOf(aVar.d));
                this.a.a("AdCacheImpressionInserted", hashMap2);
            }
            String a2 = com.inmobi.ads.c.a.a(this.c.g);
            if (isEmpty) {
                a((List) a, a2);
            } else {
                b(a, a2, stringBuilder2);
            }
        }
    }

    /* Access modifiers changed, original: final */
    public final void a(List<a> list) {
        if (list != null && list.size() > 0) {
            a aVar = (a) list.get(0);
            if (aVar != null) {
                Set d = aVar.d();
                if (d.size() == 0) {
                    this.a.a(this.c.a, true);
                    return;
                } else {
                    AssetStore.a().a(new b(UUID.randomUUID().toString(), aVar.h, d, this.g));
                }
            }
            for (a aVar2 : list.subList(1, list.size())) {
                if (aVar2 != null) {
                    Set d2 = aVar2.d();
                    if (d2.size() != 0) {
                        AssetStore.a().a(new b(UUID.randomUUID().toString(), aVar2.h, d2, null));
                    }
                }
            }
        }
    }

    @Nullable
    private List<a> a(g gVar, @Nullable StringBuilder stringBuilder) {
        List<a> arrayList = new ArrayList();
        try {
            JSONObject jSONObject = new JSONObject(gVar.a.b());
            stringBuilder.append(jSONObject.optString("winnerImpressionId").trim());
            JSONArray jSONArray = jSONObject.getJSONArray("ads");
            if (jSONArray != null) {
                int min = Math.min(gVar.c.d, jSONArray.length());
                for (int i = 0; i < min; i++) {
                    a a = a.a(jSONArray.getJSONObject(i), gVar.c.a, gVar.c.e, gVar.c.c, gVar.c.i, gVar.c.j, gVar.c.k);
                    if (a != null) {
                        arrayList.add(a);
                    }
                }
                if (min <= 0 || !arrayList.isEmpty()) {
                    return arrayList;
                }
                return null;
            }
        } catch (JSONException e) {
            Map hashMap = new HashMap();
            hashMap.put("errorCode", "ParsingError");
            hashMap.put("reason", e.getLocalizedMessage());
            hashMap.put("latency", Long.valueOf(SystemClock.elapsedRealtime() - this.e));
            hashMap.put("im-accid", com.inmobi.commons.a.a.e());
            this.a.a("ServerError", hashMap);
            arrayList = null;
        }
        return arrayList;
    }

    public final void b(g gVar) {
        Map hashMap = new HashMap();
        hashMap.put("errorCode", String.valueOf(gVar.a.b.a.getValue()));
        hashMap.put("reason", gVar.a.b.b);
        hashMap.put("latency", Long.valueOf(SystemClock.elapsedRealtime() - this.e));
        hashMap.put("im-accid", com.inmobi.commons.a.a.e());
        this.a.a("ServerError", hashMap);
        this.a.a(this.c.a, gVar.b);
    }

    /* Access modifiers changed, original: final */
    public final String b() {
        String a = com.inmobi.ads.c.a.a(this.c.g);
        b.b();
        a aVar = null;
        if (d.a(this.c.a, this.c.c, this.c.j, a) != 0) {
            a b = this.b.b(this.c.a, this.c.c, this.c.j, a);
            if (b != null) {
                Map hashMap = new HashMap();
                hashMap.put("clientRequestId", b.h);
                hashMap.put("im-accid", com.inmobi.commons.a.a.e());
                hashMap.put("isPreloaded", this.c.c());
                this.a.a("AdCacheHit", hashMap);
                a(this.c);
                aVar = b;
            }
        }
        if (aVar != null) {
            String str = aVar.h;
            this.a.a(this.c.a, aVar);
            if (!"INMOBIJSON".equalsIgnoreCase(aVar.e())) {
                return str;
            }
            a(new ArrayList(Collections.singletonList(aVar)));
            return str;
        } else if (this.c.c().equals("1")) {
            return a(this.c, true);
        } else {
            return a(this.c, false);
        }
    }

    private static void b(f fVar, boolean z) {
        if (fVar != null) {
            Map map = fVar.h;
            if (map == null) {
                map = new HashMap();
            }
            map.put("preload-request", String.valueOf(z));
            fVar.h = map;
        }
    }

    public final void a(@NonNull f fVar) {
        b.b();
        int a = d.a(fVar.a, fVar.c, fVar.j, com.inmobi.ads.c.a.a(fVar.g));
        boolean equals = "int".equals(fVar.e);
        if (a < this.d.c) {
            new StringBuilder("Cached ad count below threshold, firing ad request for Placement : ").append(fVar.a);
            com.inmobi.ads.c.a a2 = com.inmobi.ads.c.a.a(fVar.e);
            if (equals) {
                b(fVar, true);
                try {
                    new bl(new com.inmobi.ads.c.a.AnonymousClass3(fVar), this.d).a(fVar, true, com.inmobi.ads.c.a.b.c);
                    return;
                } catch (com.inmobi.ads.a.a e) {
                    e.getMessage();
                    return;
                }
            }
            new Handler(Looper.getMainLooper()).post(new com.inmobi.ads.c.a.AnonymousClass2(fVar));
        }
    }
}
