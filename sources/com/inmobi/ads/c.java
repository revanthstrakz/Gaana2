package com.inmobi.ads;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import com.facebook.internal.AnalyticsEvents;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class c extends com.inmobi.commons.core.configs.a {
    private static boolean q = true;
    private static boolean r = false;
    private static boolean s = true;
    private static boolean t = true;
    private static final String u = "c";
    private static final Object v = new Object();
    private g A = new g();
    String a = "https://i.w.inmobi.com/showad.asm";
    public String b = "https://sdktm.w.inmobi.com/sdkpubreq/v2";
    public int c = 20;
    int d = 60;
    int e = 60;
    a f;
    Map<String, a> g;
    public e h = new e();
    public h i = new h();
    public f j = new f();
    public k k = new k();
    public JSONObject l;
    public j m = new j();
    public b n = new b();
    boolean o = false;
    private List<String> w = new LinkedList();
    private d x;
    private Map<String, d> y;
    private Map<String, g> z = new HashMap();

    public static final class a {
        public int a;
        public long b;
        public int c;
        public long d;
        public long e;
        public i f;
        public i g;
        public boolean h;

        /* JADX WARNING: Missing block: B:33:0x006e, code skipped:
            return false;
     */
        public final boolean a() {
            /*
            r8 = this;
            r0 = r8.e;
            r2 = r8.d;
            r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
            r0 = 0;
            if (r4 < 0) goto L_0x006e;
        L_0x0009:
            r1 = r8.e;
            r3 = r8.b;
            r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1));
            if (r5 > 0) goto L_0x006e;
        L_0x0011:
            r1 = r8.b;
            r3 = r8.d;
            r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1));
            if (r5 >= 0) goto L_0x001a;
        L_0x0019:
            goto L_0x006e;
        L_0x001a:
            r1 = r8.f;
            r1 = r1.a();
            if (r1 == 0) goto L_0x006d;
        L_0x0022:
            r1 = r8.g;
            r1 = r1.a();
            if (r1 != 0) goto L_0x002b;
        L_0x002a:
            goto L_0x006d;
        L_0x002b:
            r1 = r8.a;
            if (r1 < 0) goto L_0x006c;
        L_0x002f:
            r1 = r8.a;
            r2 = 3;
            if (r1 > r2) goto L_0x006c;
        L_0x0034:
            r1 = r8.b;
            r3 = 0;
            r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1));
            if (r5 <= 0) goto L_0x006c;
        L_0x003c:
            r1 = r8.b;
            r5 = 86400; // 0x15180 float:1.21072E-40 double:4.26873E-319;
            r7 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1));
            if (r7 > 0) goto L_0x006c;
        L_0x0045:
            r1 = r8.c;
            if (r1 <= 0) goto L_0x006c;
        L_0x0049:
            r1 = r8.c;
            r2 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
            if (r1 > r2) goto L_0x006c;
        L_0x004f:
            r1 = r8.e;
            r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1));
            if (r5 <= 0) goto L_0x006c;
        L_0x0055:
            r1 = r8.e;
            r5 = 180; // 0xb4 float:2.52E-43 double:8.9E-322;
            r7 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1));
            if (r7 > 0) goto L_0x006c;
        L_0x005d:
            r1 = r8.d;
            r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1));
            if (r5 < 0) goto L_0x006c;
        L_0x0063:
            r1 = r8.d;
            r3 = 60;
            r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1));
            if (r5 > 0) goto L_0x006c;
        L_0x006b:
            r0 = 1;
        L_0x006c:
            return r0;
        L_0x006d:
            return r0;
        L_0x006e:
            return r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.c$a.a():boolean");
        }
    }

    public static final class b {
        public int a = 3;
        public int b = 1;
        int c = 10;
        public long d = 104857600;
        public long e = 259200;
    }

    public static final class c {
        boolean a = false;
        int b = 2000;
    }

    public static final class d {
        int a = 1;
        int b;
        int c;
        long d;
        public boolean e;

        public final boolean a() {
            return this.b > 0 && this.a >= 0 && this.c >= 0 && this.d >= 0;
        }
    }

    public static final class e {
        public int a = 3;
        public int b = 60;
        public int c = 120;
        public int d = 500;
        public int e = 10;
        public long f = 10800;
    }

    public static final class f {
        public long a = com.til.colombia.android.internal.h.u;
        public int b = 3;
        public int c = 60;
        public String d = "https://i.l.inmobicdn.net/sdk/sdk/500/android/mraid.js";
    }

    public static final class g {
        public boolean a = false;
        public long b = 259200;
        public int c = 5;

        public final boolean a() {
            return this.b >= 0 && this.c > 0;
        }
    }

    public static final class h {
        int a = 60;
        int b = ModuleDescriptor.MODULE_VERSION;
        int c = 480;
        int d = 100;
        String e = "#00000000";
        public int f = Color.parseColor("#00000000");
        public boolean g = true;
        int h = 5;
        int i = 20;
        long j = 5242880;
        ArrayList<String> k = new ArrayList(Arrays.asList(new String[]{MimeTypes.VIDEO_MP4}));
        public boolean l = false;
        public boolean m = false;
    }

    public static final class i {
        public long a;
        public int b;

        public final boolean a() {
            return this.a > 0 && this.a <= 60 && this.b > 0 && this.b <= 97;
        }
    }

    public static final class j {
        int a = 3;
        long b = 3145728;
        public long c = 31457280;
        c d = new c();
        public ArrayList<String> e = new ArrayList(Arrays.asList(new String[]{MimeTypes.VIDEO_MP4, "image/jpeg", "image/jpg", "image/gif", "image/png"}));
    }

    public static final class k {
        int a = 50;
        int b = 1000;
        int c = 100;
        int d = Callback.DEFAULT_SWIPE_ANIMATION_DURATION;
        int e = 67;
        int f = 50;
        public int g = 2000;
        int h = 50;
        boolean i = true;
        boolean j = true;
    }

    public final String a() {
        return "ads";
    }

    private static JSONObject e() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("retryInterval", 3);
        jSONObject2.put("maxBatchSize", 10);
        jSONObject.put(com.til.colombia.android.internal.e.ad, jSONObject2);
        jSONObject2 = new JSONObject();
        jSONObject2.put("retryInterval", 3);
        jSONObject2.put("maxBatchSize", 5);
        jSONObject.put("others", jSONObject2);
        return jSONObject;
    }

    public c() {
        this.w.add("bannerDict");
        this.w.add("intDict");
        this.w.add("nativeDict");
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("maxCacheSize", 1);
            jSONObject2.put("fetchLimit", 1);
            jSONObject2.put("minThreshold", 0);
            jSONObject2.put("timeToLive", 3300);
            jSONObject2.put("sortByBid", false);
            jSONObject.put("base", jSONObject2);
            jSONObject2 = new JSONObject();
            jSONObject2.put("maxCacheSize", 1);
            jSONObject2.put("fetchLimit", 1);
            jSONObject2.put("minThreshold", 1);
            jSONObject2.put("timeToLive", 3300);
            jSONObject.put("banner", jSONObject2);
            jSONObject2 = new JSONObject();
            jSONObject2.put("maxCacheSize", 1);
            jSONObject2.put("fetchLimit", 1);
            jSONObject2.put("minThreshold", 1);
            jSONObject2.put("timeToLive", 3300);
            jSONObject.put("int", jSONObject2);
            jSONObject2 = new JSONObject();
            jSONObject2.put("maxCacheSize", 100);
            jSONObject2.put("fetchLimit", 1);
            jSONObject2.put("minThreshold", 1);
            jSONObject2.put("timeToLive", 3300);
            jSONObject.put(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, jSONObject2);
            c(jSONObject);
            jSONObject = new JSONObject();
            jSONObject.put("enabled", true);
            jSONObject.put("samplingFactor", 0);
            this.l = jSONObject;
            JSONObject jSONObject3 = new JSONObject();
            jSONObject = new JSONObject();
            jSONObject.put("enabled", q);
            jSONObject.put("maxRetryCount", 1);
            jSONObject.put("eventTTL", 14400);
            jSONObject.put("maxEventsToPersist", 1000);
            jSONObject.put("txLatency", 60);
            jSONObject.put("processingInterval", 0);
            jSONObject.put("networkType", e());
            jSONObject2 = new JSONObject();
            jSONObject2.put("enabled", r);
            jSONObject2.put("maxRetryCount", 1);
            jSONObject2.put("eventTTL", 14400);
            jSONObject2.put("maxEventsToPersist", 1000);
            jSONObject2.put("txLatency", 60);
            jSONObject2.put("processingInterval", 0);
            jSONObject2.put("networkType", e());
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put("enabled", s);
            jSONObject4.put("maxRetryCount", 1);
            jSONObject4.put("eventTTL", 14400);
            jSONObject4.put("maxEventsToPersist", 1000);
            jSONObject4.put("txLatency", 60);
            jSONObject4.put("processingInterval", 0);
            jSONObject4.put("networkType", e());
            JSONObject jSONObject5 = new JSONObject();
            jSONObject5.put("enabled", t);
            jSONObject5.put("maxRetryCount", 1);
            jSONObject5.put("eventTTL", 14400);
            jSONObject5.put("maxEventsToPersist", 1000);
            jSONObject5.put("txLatency", 60);
            jSONObject5.put("processingInterval", 0);
            jSONObject5.put("networkType", e());
            jSONObject3.put("baseDict", jSONObject);
            jSONObject3.put("bannerDict", jSONObject2);
            jSONObject3.put("intDict", jSONObject4);
            jSONObject3.put("nativeDict", jSONObject5);
            b(jSONObject3);
        } catch (JSONException unused) {
        }
    }

    public final void a(JSONObject jSONObject) throws JSONException {
        super.a(jSONObject);
        if (jSONObject.has("url")) {
            this.a = jSONObject.getString("url");
        }
        if (jSONObject.has("trueRequestUrl")) {
            this.b = jSONObject.getString("trueRequestUrl");
        }
        this.c = jSONObject.getInt("minimumRefreshInterval");
        this.d = jSONObject.getInt("defaultRefreshInterval");
        this.e = jSONObject.getInt("fetchTimeout");
        this.o = jSONObject.getBoolean("flushCacheOnStart");
        c(jSONObject.getJSONObject("cache"));
        b(jSONObject.getJSONObject("trcFlagDict"));
        JSONObject jSONObject2 = jSONObject.getJSONObject("preload");
        JSONObject jSONObject3 = jSONObject2.getJSONObject("base");
        this.A = new g();
        this.A.a = jSONObject3.getBoolean("enabled");
        this.A.b = jSONObject3.getLong("placementExpiry");
        this.A.c = jSONObject3.getInt("maxPreloadedAds");
        jSONObject2.remove("base");
        Iterator keys = jSONObject2.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            JSONObject jSONObject4 = jSONObject2.getJSONObject(str);
            g gVar = new g();
            gVar.a = jSONObject4.optBoolean("enabled", this.A.a);
            gVar.b = jSONObject4.optLong("placementExpiry", this.A.b);
            gVar.c = jSONObject4.optInt("maxPreloadedAds", this.A.c);
            this.z.put(str, gVar);
        }
        jSONObject2 = jSONObject.getJSONObject("imai");
        this.h.a = jSONObject2.getInt("maxRetries");
        this.h.b = jSONObject2.getInt("pingInterval");
        this.h.c = jSONObject2.getInt("pingTimeout");
        this.h.d = jSONObject2.getInt("maxDbEvents");
        this.h.e = jSONObject2.getInt("maxEventBatch");
        this.h.f = jSONObject2.getLong("pingCacheExpiry");
        jSONObject2 = jSONObject.getJSONObject("rendering");
        this.i.a = jSONObject2.getInt("renderTimeout");
        this.i.c = jSONObject2.getInt("picHeight");
        this.i.b = jSONObject2.getInt("picWidth");
        this.i.d = jSONObject2.getInt("picQuality");
        this.i.e = jSONObject2.getString("webviewBackground");
        this.i.g = jSONObject2.getBoolean("autoRedirectionEnforcement");
        this.i.h = jSONObject2.getInt("maxVibrationDuration");
        this.i.i = jSONObject2.getInt("maxVibrationPatternLength");
        int i = 0;
        this.i.m = jSONObject2.optBoolean("enablePubMuteControl", false);
        this.i.j = (long) jSONObject2.getJSONObject("savecontent").getInt("maxSaveSize");
        synchronized (v) {
            this.i.k.clear();
            JSONArray jSONArray = jSONObject2.getJSONObject("savecontent").getJSONArray("allowedContentType");
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                this.i.k.add(jSONArray.getString(i2));
            }
        }
        this.i.l = jSONObject2.getBoolean("shouldRenderPopup");
        jSONObject2 = jSONObject.getJSONObject("mraid");
        this.j.a = jSONObject2.getLong("expiry");
        this.j.b = jSONObject2.getInt("maxRetries");
        this.j.c = jSONObject2.getInt("retryInterval");
        this.j.d = jSONObject2.getString("url");
        if (jSONObject.has("telemetry")) {
            this.l = jSONObject.getJSONObject("telemetry");
        }
        jSONObject2 = jSONObject.getJSONObject("viewability");
        this.k.a = jSONObject2.getInt("impressionMinPercentageViewed");
        this.k.b = jSONObject2.getInt("impressionMinTimeViewed");
        this.k.e = jSONObject2.optInt("displayMinPercentageAnimate", 67);
        this.k.c = jSONObject2.optInt("visibilityThrottleMillis", 100);
        this.k.d = jSONObject2.optInt("impressionPollIntervalMillis", Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
        this.k.i = jSONObject2.optBoolean("moatEnabled", false);
        this.k.j = jSONObject2.optBoolean("iasEnabled", false);
        jSONObject2 = jSONObject2.getJSONObject("video");
        this.k.f = jSONObject2.getInt("impressionMinPercentageViewed");
        this.k.g = jSONObject2.getInt("impressionMinTimeViewed");
        this.k.h = jSONObject2.optInt("videoMinPercentagePlay", 50);
        jSONObject2 = jSONObject.getJSONObject("vastVideo");
        this.m.a = jSONObject2.getInt("maxWrapperLimit");
        this.m.b = jSONObject2.getLong("optimalVastVideoSize");
        this.m.c = jSONObject2.getLong("vastMaxAssetSize");
        synchronized (v) {
            this.m.e.clear();
            JSONArray jSONArray2 = jSONObject2.getJSONArray("allowedContentType");
            while (i < jSONArray2.length()) {
                this.m.e.add(jSONArray2.getString(i));
                i++;
            }
        }
        c cVar = this.m.d;
        jSONObject2 = jSONObject2.getJSONObject("bitRate");
        cVar.a = jSONObject2.getBoolean("bitrate_mandatory");
        cVar.b = jSONObject2.getInt("headerTimeout");
        jSONObject = jSONObject.getJSONObject("assetCache");
        this.n.b = jSONObject.getInt("retryInterval");
        this.n.a = jSONObject.getInt("maxRetries");
        this.n.c = jSONObject.getInt("maxCachedAssets");
        this.n.d = (long) jSONObject.getInt("maxCacheSize");
        this.n.e = jSONObject.getLong("timeToLive");
    }

    private void b(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = jSONObject.getJSONObject("baseDict");
        this.f = new a();
        this.f.h = jSONObject2.getBoolean("enabled");
        this.f.a = jSONObject2.getInt("maxRetryCount");
        this.f.b = jSONObject2.getLong("eventTTL");
        this.f.c = jSONObject2.getInt("maxEventsToPersist");
        this.f.d = jSONObject2.getLong("processingInterval");
        this.f.e = jSONObject2.getLong("txLatency");
        a(jSONObject2.getJSONObject("networkType"), this.f);
        jSONObject.remove("baseDict");
        this.g = new HashMap();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            if (!(str == null || this.w == null || !this.w.contains(str))) {
                JSONObject jSONObject3 = jSONObject.getJSONObject(str);
                a aVar = new a();
                aVar.h = jSONObject3.optBoolean("enabled", this.f.h);
                aVar.a = jSONObject3.optInt("maxRetryCount", this.f.a);
                aVar.b = jSONObject3.optLong("eventTTL", this.f.b);
                aVar.c = jSONObject3.optInt("maxEventsToPersist", this.f.c);
                aVar.d = jSONObject3.optLong("processingInterval", this.f.d);
                aVar.e = jSONObject3.optLong("txLatency", this.f.e);
                a(jSONObject3.getJSONObject("networkType"), aVar);
                this.g.put(str, aVar);
            }
        }
    }

    private static void a(JSONObject jSONObject, a aVar) throws JSONException {
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            JSONObject jSONObject2 = jSONObject.getJSONObject(str);
            i iVar = new i();
            iVar.a = jSONObject2.getLong("retryInterval");
            iVar.b = jSONObject2.getInt("maxBatchSize");
            Object obj = -1;
            int hashCode = str.hashCode();
            if (hashCode != -1068855134) {
                if (hashCode != -1006804125) {
                    if (hashCode == 3649301 && str.equals(com.til.colombia.android.internal.e.ad)) {
                        obj = null;
                    }
                } else if (str.equals("others")) {
                    obj = 2;
                }
            } else if (str.equals("mobile")) {
                obj = 1;
            }
            if (obj != null) {
                aVar.f = iVar;
            } else {
                aVar.g = iVar;
            }
        }
    }

    private void c(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = jSONObject.getJSONObject("base");
        this.x = new d();
        this.x.a = jSONObject2.getInt("maxCacheSize");
        this.x.b = jSONObject2.getInt("fetchLimit");
        this.x.c = jSONObject2.getInt("minThreshold");
        this.x.d = jSONObject2.getLong("timeToLive");
        this.x.e = jSONObject2.getBoolean("sortByBid");
        jSONObject.remove("base");
        this.y = new HashMap();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            JSONObject jSONObject3 = jSONObject.getJSONObject(str);
            d dVar = new d();
            dVar.a = jSONObject3.optInt("maxCacheSize", this.x.a);
            dVar.b = jSONObject3.optInt("fetchLimit", this.x.b);
            dVar.c = jSONObject3.optInt("minThreshold", this.x.c);
            dVar.d = jSONObject3.optLong("timeToLive", this.x.d);
            dVar.e = jSONObject3.optBoolean("sortByBid", this.x.e);
            this.y.put(str, dVar);
        }
    }

    public final JSONObject b() throws JSONException {
        JSONObject b = super.b();
        b.put("url", this.a);
        b.put("trueRequestUrl", this.b);
        b.put("minimumRefreshInterval", this.c);
        b.put("defaultRefreshInterval", this.d);
        b.put("fetchTimeout", this.e);
        b.put("flushCacheOnStart", this.o);
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("maxCacheSize", this.x.a);
        jSONObject2.put("fetchLimit", this.x.b);
        jSONObject2.put("minThreshold", this.x.c);
        jSONObject2.put("timeToLive", this.x.d);
        jSONObject.put("base", jSONObject2);
        for (Entry entry : this.y.entrySet()) {
            JSONObject jSONObject3 = new JSONObject();
            d dVar = (d) entry.getValue();
            jSONObject3.put("maxCacheSize", dVar.a);
            jSONObject3.put("fetchLimit", dVar.b);
            jSONObject3.put("minThreshold", dVar.c);
            jSONObject3.put("timeToLive", dVar.d);
            jSONObject.put((String) entry.getKey(), jSONObject3);
        }
        b.put("cache", jSONObject);
        b.put("trcFlagDict", g());
        jSONObject = new JSONObject();
        jSONObject.put("maxRetries", this.h.a);
        jSONObject.put("pingInterval", this.h.b);
        jSONObject.put("pingTimeout", this.h.c);
        jSONObject.put("maxDbEvents", this.h.d);
        jSONObject.put("maxEventBatch", this.h.e);
        jSONObject.put("pingCacheExpiry", this.h.f);
        b.put("imai", jSONObject);
        jSONObject = new JSONObject();
        jSONObject.put("renderTimeout", this.i.a);
        jSONObject.put("picWidth", this.i.b);
        jSONObject.put("picHeight", this.i.c);
        jSONObject.put("picQuality", this.i.d);
        jSONObject.put("webviewBackground", this.i.e);
        jSONObject.put("autoRedirectionEnforcement", this.i.g);
        jSONObject.put("maxVibrationDuration", this.i.h);
        jSONObject.put("maxVibrationPatternLength", this.i.i);
        jSONObject.put("enablePubMuteControl", this.i.m);
        jSONObject2 = new JSONObject();
        jSONObject2.put("maxSaveSize", this.i.j);
        jSONObject2.put("allowedContentType", new JSONArray(this.i.k));
        jSONObject.put("savecontent", jSONObject2);
        jSONObject.put("shouldRenderPopup", this.i.l);
        b.put("rendering", jSONObject);
        jSONObject = new JSONObject();
        jSONObject.put("expiry", this.j.a);
        jSONObject.put("maxRetries", this.j.b);
        jSONObject.put("retryInterval", this.j.c);
        jSONObject.put("url", this.j.d);
        b.put("mraid", jSONObject);
        jSONObject = new JSONObject();
        jSONObject.put("impressionMinPercentageViewed", this.k.a);
        jSONObject.put("impressionMinTimeViewed", this.k.b);
        jSONObject.put("displayMinPercentageAnimate", this.k.e);
        jSONObject.put("visibilityThrottleMillis", this.k.c);
        jSONObject.put("impressionPollIntervalMillis", this.k.d);
        jSONObject.put("moatEnabled", this.k.i);
        jSONObject.put("iasEnabled", this.k.j);
        jSONObject2 = new JSONObject();
        jSONObject2.put("impressionMinPercentageViewed", this.k.f);
        jSONObject2.put("impressionMinTimeViewed", this.k.g);
        jSONObject2.put("videoMinPercentagePlay", this.k.h);
        jSONObject.put("video", jSONObject2);
        b.put("viewability", jSONObject);
        b.put("preload", f());
        jSONObject = new JSONObject();
        jSONObject.put("maxWrapperLimit", this.m.a);
        jSONObject.put("optimalVastVideoSize", this.m.b);
        jSONObject.put("vastMaxAssetSize", this.m.c);
        jSONObject.put("allowedContentType", new JSONArray(this.m.e));
        c cVar = this.m.d;
        JSONObject jSONObject4 = new JSONObject();
        jSONObject4.put("headerTimeout", cVar.b);
        jSONObject4.put("bitrate_mandatory", cVar.a);
        jSONObject.put("bitRate", jSONObject4);
        b.put("vastVideo", jSONObject);
        jSONObject = new JSONObject();
        jSONObject.put("retryInterval", this.n.b);
        jSONObject.put("maxRetries", this.n.a);
        jSONObject.put("maxCachedAssets", this.n.c);
        jSONObject.put("maxCacheSize", this.n.d);
        jSONObject.put("timeToLive", this.n.e);
        b.put("assetCache", jSONObject);
        if (this.l != null) {
            b.put("telemetry", this.l);
        }
        return b;
    }

    private JSONObject f() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("enabled", this.A.a);
        jSONObject2.put("placementExpiry", this.A.b);
        jSONObject2.put("maxPreloadedAds", this.A.c);
        jSONObject.put("base", jSONObject2);
        for (Entry entry : this.z.entrySet()) {
            JSONObject jSONObject3 = new JSONObject();
            g gVar = (g) entry.getValue();
            jSONObject3.put("enabled", gVar.a);
            jSONObject3.put("placementExpiry", gVar.b);
            jSONObject3.put("maxPreloadedAds", gVar.c);
            jSONObject.put((String) entry.getKey(), jSONObject3);
        }
        return jSONObject;
    }

    private JSONObject g() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("enabled", this.f.h);
        jSONObject2.put("maxRetryCount", this.f.a);
        jSONObject2.put("eventTTL", this.f.b);
        jSONObject2.put("maxEventsToPersist", this.f.c);
        jSONObject2.put("processingInterval", this.f.d);
        jSONObject2.put("txLatency", this.f.e);
        jSONObject2.put("networkType", a(this.f));
        jSONObject.put("baseDict", jSONObject2);
        for (Entry entry : this.g.entrySet()) {
            JSONObject jSONObject3 = new JSONObject();
            a aVar = (a) entry.getValue();
            jSONObject3.put("enabled", aVar.h);
            jSONObject3.put("maxRetryCount", aVar.a);
            jSONObject3.put("eventTTL", aVar.b);
            jSONObject3.put("maxEventsToPersist", aVar.c);
            jSONObject3.put("processingInterval", aVar.d);
            jSONObject3.put("txLatency", aVar.e);
            jSONObject3.put("networkType", a(aVar));
            jSONObject.put((String) entry.getKey(), jSONObject3);
        }
        return jSONObject;
    }

    private static JSONObject a(a aVar) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        i iVar = aVar.g;
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("retryInterval", iVar.a);
        jSONObject2.put("maxBatchSize", iVar.b);
        jSONObject.put(com.til.colombia.android.internal.e.ad, jSONObject2);
        i iVar2 = aVar.f;
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("retryInterval", iVar2.a);
        jSONObject3.put("maxBatchSize", iVar2.b);
        jSONObject.put("others", jSONObject3);
        return jSONObject;
    }

    /* JADX WARNING: Missing block: B:147:0x024f, code skipped:
            return false;
     */
    /* JADX WARNING: Missing block: B:149:0x0251, code skipped:
            return false;
     */
    /* JADX WARNING: Missing block: B:150:0x0252, code skipped:
            return false;
     */
    /* JADX WARNING: Missing block: B:154:0x0255, code skipped:
            return false;
     */
    /* JADX WARNING: Missing block: B:155:0x0256, code skipped:
            return false;
     */
    /* JADX WARNING: Missing block: B:158:0x0259, code skipped:
            return false;
     */
    public final boolean c() {
        /*
        r8 = this;
        r0 = r8.a;
        r1 = "http://";
        r0 = r0.startsWith(r1);
        r1 = 0;
        if (r0 != 0) goto L_0x0015;
    L_0x000b:
        r0 = r8.a;
        r2 = "https://";
        r0 = r0.startsWith(r2);
        if (r0 == 0) goto L_0x0259;
    L_0x0015:
        r0 = r8.b;
        r2 = "http://";
        r0 = r0.startsWith(r2);
        if (r0 != 0) goto L_0x0029;
    L_0x001f:
        r0 = r8.b;
        r2 = "https://";
        r0 = r0.startsWith(r2);
        if (r0 == 0) goto L_0x0259;
    L_0x0029:
        r0 = r8.c;
        if (r0 < 0) goto L_0x0259;
    L_0x002d:
        r0 = r8.d;
        if (r0 < 0) goto L_0x0259;
    L_0x0031:
        r0 = r8.e;
        if (r0 > 0) goto L_0x0037;
    L_0x0035:
        goto L_0x0259;
    L_0x0037:
        r0 = r8.x;
        if (r0 == 0) goto L_0x0258;
    L_0x003b:
        r0 = r8.x;
        r0 = r0.a();
        if (r0 != 0) goto L_0x0045;
    L_0x0043:
        goto L_0x0258;
    L_0x0045:
        r0 = r8.y;
        r0 = r0.entrySet();
        r0 = r0.iterator();
    L_0x004f:
        r2 = r0.hasNext();
        if (r2 == 0) goto L_0x0068;
    L_0x0055:
        r2 = r0.next();
        r2 = (java.util.Map.Entry) r2;
        r2 = r2.getValue();
        r2 = (com.inmobi.ads.c.d) r2;
        r2 = r2.a();
        if (r2 != 0) goto L_0x004f;
    L_0x0067:
        return r1;
    L_0x0068:
        r0 = r8.f;
        if (r0 == 0) goto L_0x0257;
    L_0x006c:
        r0 = r8.f;
        r0 = r0.a();
        if (r0 != 0) goto L_0x0076;
    L_0x0074:
        goto L_0x0257;
    L_0x0076:
        r0 = r8.g;
        r0 = r0.entrySet();
        r0 = r0.iterator();
    L_0x0080:
        r2 = r0.hasNext();
        if (r2 == 0) goto L_0x0099;
    L_0x0086:
        r2 = r0.next();
        r2 = (java.util.Map.Entry) r2;
        r2 = r2.getValue();
        r2 = (com.inmobi.ads.c.a) r2;
        r2 = r2.a();
        if (r2 != 0) goto L_0x0080;
    L_0x0098:
        return r1;
    L_0x0099:
        r0 = r8.h;
        r0 = r0.d;
        if (r0 < 0) goto L_0x0256;
    L_0x009f:
        r0 = r8.h;
        r0 = r0.e;
        if (r0 < 0) goto L_0x0256;
    L_0x00a5:
        r0 = r8.h;
        r0 = r0.a;
        if (r0 < 0) goto L_0x0256;
    L_0x00ab:
        r0 = r8.h;
        r0 = r0.b;
        if (r0 < 0) goto L_0x0256;
    L_0x00b1:
        r0 = r8.h;
        r0 = r0.c;
        if (r0 <= 0) goto L_0x0256;
    L_0x00b7:
        r0 = r8.h;
        r2 = r0.f;
        r4 = 0;
        r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r0 > 0) goto L_0x00c3;
    L_0x00c1:
        goto L_0x0256;
    L_0x00c3:
        r0 = r8.j;
        r2 = r0.a;
        r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r0 < 0) goto L_0x0255;
    L_0x00cb:
        r0 = r8.j;
        r0 = r0.c;
        if (r0 < 0) goto L_0x0255;
    L_0x00d1:
        r0 = r8.j;
        r0 = r0.b;
        if (r0 < 0) goto L_0x0255;
    L_0x00d7:
        r0 = r8.j;
        r0 = r0.d;
        r2 = "http://";
        r0 = r0.startsWith(r2);
        if (r0 != 0) goto L_0x00f1;
    L_0x00e3:
        r0 = r8.j;
        r0 = r0.d;
        r2 = "https://";
        r0 = r0.startsWith(r2);
        if (r0 != 0) goto L_0x00f1;
    L_0x00ef:
        goto L_0x0255;
    L_0x00f1:
        r0 = r8.i;
        r0 = r0.a;
        if (r0 < 0) goto L_0x0254;
    L_0x00f7:
        r0 = r8.i;
        r0 = r0.c;
        if (r0 < 0) goto L_0x0254;
    L_0x00fd:
        r0 = r8.i;
        r0 = r0.b;
        if (r0 < 0) goto L_0x0254;
    L_0x0103:
        r0 = r8.i;
        r0 = r0.d;
        if (r0 < 0) goto L_0x0254;
    L_0x0109:
        r0 = r8.i;
        r0 = r0.h;
        if (r0 < 0) goto L_0x0254;
    L_0x010f:
        r0 = r8.i;
        r0 = r0.i;
        if (r0 < 0) goto L_0x0254;
    L_0x0115:
        r0 = r8.i;
        r2 = r0.j;
        r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r0 < 0) goto L_0x0254;
    L_0x011d:
        r0 = r8.i;
        r0 = r0.e;
        if (r0 == 0) goto L_0x0254;
    L_0x0123:
        r0 = r8.i;
        r0 = r0.e;
        r0 = r0.trim();
        r0 = r0.length();
        if (r0 != 0) goto L_0x0133;
    L_0x0131:
        goto L_0x0254;
    L_0x0133:
        r0 = r8.i;	 Catch:{ IllegalArgumentException -> 0x0253 }
        r2 = r8.i;	 Catch:{ IllegalArgumentException -> 0x0253 }
        r2 = r2.e;	 Catch:{ IllegalArgumentException -> 0x0253 }
        r2 = android.graphics.Color.parseColor(r2);	 Catch:{ IllegalArgumentException -> 0x0253 }
        r0.f = r2;	 Catch:{ IllegalArgumentException -> 0x0253 }
        r0 = r8.j;
        r0 = r0.b;
        if (r0 < 0) goto L_0x0252;
    L_0x0145:
        r0 = r8.j;
        r0 = r0.c;
        if (r0 < 0) goto L_0x0252;
    L_0x014b:
        r0 = r8.j;
        r0 = r0.d;
        if (r0 == 0) goto L_0x0252;
    L_0x0151:
        r0 = r8.j;
        r0 = r0.d;
        r0 = r0.trim();
        r0 = r0.length();
        if (r0 != 0) goto L_0x0161;
    L_0x015f:
        goto L_0x0252;
    L_0x0161:
        r0 = r8.k;
        r0 = r0.a;
        if (r0 <= 0) goto L_0x0251;
    L_0x0167:
        r0 = r8.k;
        r0 = r0.a;
        r2 = 100;
        if (r0 > r2) goto L_0x0251;
    L_0x016f:
        r0 = r8.k;
        r0 = r0.b;
        if (r0 < 0) goto L_0x0251;
    L_0x0175:
        r0 = r8.k;
        r0 = r0.e;
        if (r0 <= 0) goto L_0x0251;
    L_0x017b:
        r0 = r8.k;
        r0 = r0.e;
        if (r0 > r2) goto L_0x0251;
    L_0x0181:
        r0 = r8.k;
        r0 = r0.f;
        if (r0 <= 0) goto L_0x0251;
    L_0x0187:
        r0 = r8.k;
        r0 = r0.f;
        if (r0 > r2) goto L_0x0251;
    L_0x018d:
        r0 = r8.k;
        r0 = r0.g;
        if (r0 < 0) goto L_0x0251;
    L_0x0193:
        r0 = r8.k;
        r0 = r0.h;
        if (r0 <= 0) goto L_0x0251;
    L_0x0199:
        r0 = r8.k;
        r0 = r0.h;
        if (r0 > r2) goto L_0x0251;
    L_0x019f:
        r0 = r8.k;
        r0 = r0.c;
        r2 = 50;
        if (r0 < r2) goto L_0x0251;
    L_0x01a7:
        r0 = r8.k;
        r0 = r0.c;
        r0 = r0 * 5;
        r3 = r8.k;
        r3 = r3.b;
        if (r0 > r3) goto L_0x0251;
    L_0x01b3:
        r0 = r8.k;
        r0 = r0.d;
        if (r0 < r2) goto L_0x0251;
    L_0x01b9:
        r0 = r8.k;
        r0 = r0.d;
        r0 = r0 * 4;
        r2 = r8.k;
        r2 = r2.b;
        if (r0 <= r2) goto L_0x01c7;
    L_0x01c5:
        goto L_0x0251;
    L_0x01c7:
        r0 = r8.A;
        if (r0 == 0) goto L_0x0250;
    L_0x01cb:
        r0 = r8.A;
        r0 = r0.a();
        if (r0 != 0) goto L_0x01d5;
    L_0x01d3:
        goto L_0x0250;
    L_0x01d5:
        r0 = r8.z;
        r0 = r0.entrySet();
        r0 = r0.iterator();
    L_0x01df:
        r2 = r0.hasNext();
        if (r2 == 0) goto L_0x01f8;
    L_0x01e5:
        r2 = r0.next();
        r2 = (java.util.Map.Entry) r2;
        r2 = r2.getValue();
        r2 = (com.inmobi.ads.c.g) r2;
        r2 = r2.a();
        if (r2 != 0) goto L_0x01df;
    L_0x01f7:
        return r1;
    L_0x01f8:
        r0 = r8.m;
        r2 = r0.b;
        r6 = 31457280; // 0x1e00000 float:8.2284605E-38 double:1.55419614E-316;
        r0 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
        if (r0 > 0) goto L_0x024f;
    L_0x0203:
        r0 = r8.m;
        r2 = r0.b;
        r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r0 <= 0) goto L_0x024f;
    L_0x020b:
        r0 = r8.m;
        r0 = r0.a;
        if (r0 < 0) goto L_0x024f;
    L_0x0211:
        r0 = r8.m;
        r2 = r0.c;
        r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r0 <= 0) goto L_0x024f;
    L_0x0219:
        r0 = r8.m;
        r2 = r0.c;
        r0 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
        if (r0 <= 0) goto L_0x0222;
    L_0x0221:
        goto L_0x024f;
    L_0x0222:
        r0 = r8.n;
        r0 = r0.b;
        if (r0 < 0) goto L_0x024e;
    L_0x0228:
        r0 = r8.n;
        r0 = r0.c;
        r2 = 20;
        if (r0 > r2) goto L_0x024e;
    L_0x0230:
        r0 = r8.n;
        r0 = r0.c;
        if (r0 < 0) goto L_0x024e;
    L_0x0236:
        r0 = r8.n;
        r2 = r0.e;
        r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r0 < 0) goto L_0x024e;
    L_0x023e:
        r0 = r8.n;
        r2 = r0.d;
        r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r0 < 0) goto L_0x024e;
    L_0x0246:
        r0 = r8.n;
        r0 = r0.a;
        if (r0 < 0) goto L_0x024e;
    L_0x024c:
        r0 = 1;
        return r0;
    L_0x024e:
        return r1;
    L_0x024f:
        return r1;
    L_0x0250:
        return r1;
    L_0x0251:
        return r1;
    L_0x0252:
        return r1;
    L_0x0253:
        return r1;
    L_0x0254:
        return r1;
    L_0x0255:
        return r1;
    L_0x0256:
        return r1;
    L_0x0257:
        return r1;
    L_0x0258:
        return r1;
    L_0x0259:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.c.c():boolean");
    }

    public final com.inmobi.commons.core.configs.a d() {
        return new c();
    }

    public final d a(String str) {
        d dVar = (d) this.y.get(str);
        return dVar == null ? this.x : dVar;
    }

    @NonNull
    public final a b(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append("Dict");
        a aVar = (a) this.g.get(stringBuilder.toString());
        return aVar == null ? this.f : aVar;
    }

    public final g c(String str) {
        g gVar = (g) this.z.get(str);
        return gVar == null ? this.A : gVar;
    }
}
