package com.inmobi.ads;

import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.facebook.internal.AnalyticsEvents;
import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.ads.c.d;
import com.inmobi.ads.cache.AssetStore;
import com.inmobi.ads.cache.b;
import com.inmobi.ads.cache.f;
import com.inmobi.commons.core.utilities.b.e;
import com.inmobi.commons.core.utilities.c;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class bl implements com.inmobi.ads.e.a {
    private static final String c = "bl";
    boolean a = false;
    @NonNull
    d b;
    private final a d;
    private final d e;
    private f f;
    private long g = 0;
    private boolean h;
    private final f i = new f() {
        public final void a(b bVar) {
            String str;
            bl.c;
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
                    HashMap hashMap = new HashMap();
                    hashMap.put("url", aVar.d);
                    hashMap.put("latency", Long.valueOf(aVar.a));
                    hashMap.put("size", Long.valueOf(c.a(aVar.e)));
                    bl.this.d.a("VideoAssetDownloadFailed", hashMap);
                    for (a aVar2 : bl.this.e.b(aVar.d, bl.this.f == null ? null : bl.this.f.c)) {
                        if (!arrayList.contains(Long.valueOf(aVar2.d))) {
                            arrayList.add(Long.valueOf(aVar2.d));
                        }
                    }
                }
            }
            if (!arrayList.contains(Long.valueOf(bl.this.f.a))) {
                arrayList.add(Long.valueOf(bl.this.f.a));
            }
            for (Long longValue : arrayList) {
                bl.this.d.b(longValue.longValue(), new InMobiAdRequestStatus(StatusCode.AD_NO_LONGER_AVAILABLE));
            }
        }

        public final void b(b bVar) {
            String str;
            bl.c;
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
                    HashMap hashMap = new HashMap();
                    hashMap.put("url", aVar.d);
                    hashMap.put("latency", Long.valueOf(aVar.a));
                    hashMap.put("size", Long.valueOf(c.a(aVar.e)));
                    hashMap.put("clientRequestId", bVar.f);
                    if (aVar.j) {
                        bl.this.d.a("GotCachedVideoAsset", hashMap);
                    } else {
                        bl.this.d.a("VideoAssetDownloaded", hashMap);
                    }
                    List<a> a = bl.this.e.a(aVar.d, bl.this.f == null ? null : bl.this.f.c);
                    bl.c;
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
            if (!arrayList.contains(Long.valueOf(bl.this.f.a))) {
                arrayList.add(Long.valueOf(bl.this.f.a));
            }
            for (Long longValue : arrayList) {
                long longValue2 = longValue.longValue();
                bl.c;
                StringBuilder stringBuilder3 = new StringBuilder("Notifying ad unit with placement ID (");
                stringBuilder3.append(longValue2);
                stringBuilder3.append(")");
                bl.this.d.a(longValue2);
            }
        }
    };

    public interface a {
        void a(long j);

        void a(String str, Map<String, Object> map);

        void b(long j, InMobiAdRequestStatus inMobiAdRequestStatus);
    }

    public bl(a aVar, @NonNull d dVar) {
        this.d = aVar;
        this.e = d.a();
        this.b = dVar;
    }

    private boolean a(int i) {
        return SystemClock.elapsedRealtime() - this.g < ((long) (i * 1000));
    }

    public final void a(g gVar) {
        List c = c(gVar);
        HashMap hashMap;
        if (c == null) {
            new StringBuilder("Could not parse ad response:").append(gVar.a.b());
            if (!this.a) {
                this.d.b(this.f.a, new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR));
            }
        } else if (c.size() == 0) {
            new StringBuilder("Ad response received but no ad available:").append(gVar.a.b());
            hashMap = new HashMap();
            hashMap.put("latency", Long.valueOf(SystemClock.elapsedRealtime() - this.g));
            hashMap.put("isPreloaded", "1");
            hashMap.put("im-accid", com.inmobi.commons.a.a.e());
            this.d.a("ServerNoFill", hashMap);
            if (!this.a) {
                this.d.b(this.f.a, new InMobiAdRequestStatus(StatusCode.NO_FILL));
            }
        } else {
            hashMap = new HashMap();
            hashMap.put("numberOfAdsReturned", Integer.valueOf(c.size()));
            hashMap.put("latency", Long.valueOf(SystemClock.elapsedRealtime() - this.g));
            hashMap.put("isPreloaded", "1");
            hashMap.put("im-accid", com.inmobi.commons.a.a.e());
            this.d.a("ServerFill", hashMap);
            if ("HTML".equalsIgnoreCase(((a) c.get(0)).e()) && AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE.equals(this.f.e)) {
                if (!this.a) {
                    this.d.b(this.f.a, new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR));
                }
                return;
            }
            this.e.a(c, this.f.a, this.b.a, this.f.e, this.f.j, com.inmobi.ads.c.a.a(this.f.g), null);
            a(c);
            if (!(this.a || this.h)) {
                this.d.a(this.f.a);
            }
        }
    }

    public final void b(g gVar) {
        if (!this.a) {
            HashMap hashMap = new HashMap();
            hashMap.put("errorCode", String.valueOf(gVar.a.b.a.getValue()));
            hashMap.put("reason", gVar.a.b.b);
            hashMap.put("latency", Long.valueOf(SystemClock.elapsedRealtime() - this.g));
            hashMap.put("isPreloaded", "1");
            hashMap.put("im-accid", com.inmobi.commons.a.a.e());
            this.d.a("ServerError", hashMap);
            this.d.b(this.f.a, gVar.b);
        }
    }

    private List<a> c(g gVar) {
        List<a> arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONObject(gVar.a.b()).getJSONArray("ads");
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
            HashMap hashMap = new HashMap();
            hashMap.put("errorCode", "ParsingError");
            hashMap.put("reason", e.getLocalizedMessage());
            hashMap.put("latency", Long.valueOf(SystemClock.elapsedRealtime() - this.g));
            hashMap.put("isPreloaded", "1");
            hashMap.put("im-accid", com.inmobi.commons.a.a.e());
            this.d.a("ServerError", hashMap);
            arrayList = null;
        }
        return arrayList;
    }

    private void a(List<a> list) {
        if (list != null && list.size() > 0) {
            Set d;
            a aVar = (a) list.get(0);
            if (aVar != null) {
                d = aVar.d();
                if (d.size() == 0) {
                    this.d.a(this.f.a);
                    return;
                }
                AssetStore.a().a(new b(UUID.randomUUID().toString(), aVar.h, d, this.h ? this.i : null));
            }
            for (a aVar2 : list.subList(1, list.size())) {
                if (aVar2 != null && aVar2.e().equalsIgnoreCase("inmobiJson")) {
                    d = aVar2.d();
                    if (d.size() != 0) {
                        AssetStore.a().a(new b(UUID.randomUUID().toString(), aVar2.h, d, null));
                    }
                }
            }
        }
    }

    @Nullable
    public final String a(f fVar, boolean z, int i) throws com.inmobi.ads.a.a {
        if (e.b()) {
            d.c();
        }
        this.a = false;
        this.f = fVar;
        this.h = z;
        b.b();
        List c = this.e.c(this.f.a, this.f.c, this.f.j, com.inmobi.ads.c.a.a(this.f.g));
        int size = c.size();
        if (size == 0) {
            this.a = false;
            if (!a(i)) {
                return a(this.f);
            }
            throw new com.inmobi.ads.a.a("Ignoring request to fetch an ad from the network sooner than the minimum request interval");
        }
        String a;
        if (size < this.b.c) {
            this.a = true;
            if (!z) {
                this.d.a(this.f.a);
            }
            a(c);
            if (a(i)) {
                throw new com.inmobi.ads.a.a("Ignoring request to fetch an ad from the network sooner than the minimum request interval");
            }
            a = a(this.f);
        } else {
            this.a = true;
            String str = ((a) c.get(0)).h;
            if (!z) {
                this.d.a(this.f.a);
            }
            a(c);
            a = str;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("im-accid", com.inmobi.commons.a.a.e());
        hashMap.put("isPreloaded", "1");
        this.d.a("AdCacheAdRequested", hashMap);
        return a;
    }

    @NonNull
    private String a(f fVar) {
        if (fVar != null) {
            Map map = fVar.h;
            if (map == null) {
                map = new HashMap();
            }
            if (!map.containsKey("preload-request")) {
                map.put("preload-request", "1");
                fVar.h = map;
            }
        }
        this.g = SystemClock.elapsedRealtime();
        new e(fVar, this).a();
        HashMap hashMap = new HashMap();
        hashMap.put("isPreloaded", "1");
        hashMap.put("clientRequestId", fVar.i);
        hashMap.put("im-accid", com.inmobi.commons.a.a.e());
        this.d.a("ServerCallInitiated", hashMap);
        return fVar.i;
    }
}
