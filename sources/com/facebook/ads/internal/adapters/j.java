package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import com.facebook.ads.internal.a.d;
import com.facebook.ads.internal.a.e.a;
import com.facebook.ads.internal.l.b;
import com.facebook.ads.internal.o.c;
import com.facebook.ads.internal.p.e;
import com.facebook.ads.internal.p.g;
import com.facebook.ads.internal.p.i;
import com.facebook.ads.internal.p.l;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.s.a.aa;
import com.facebook.ads.internal.s.a.k;
import com.facebook.ads.internal.s.a.s;
import com.google.android.gms.analytics.ecommerce.Promotion;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class j implements a, AdAdapter {
    private static final String a = "j";
    private int A;
    private String B;
    private String C;
    private l D;
    private int E = 200;
    private String F;
    private g G;
    private String H;
    private String I;
    private com.facebook.ads.internal.p.j J;
    private List<e> K;
    private int L = -1;
    private int M;
    private String N;
    private boolean O;
    private boolean P;
    private boolean Q;
    private boolean R;
    private boolean S;
    private long T = 0;
    private com.facebook.ads.internal.l.a.a U = null;
    @Nullable
    private c V;
    private e.c W;
    private Context b;
    private r c;
    private Uri d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private String n;
    private String o;
    private g p;
    private g q;
    private i r;
    private String s;
    private d t;
    private Collection<String> u;
    private boolean v;
    private boolean w;
    private int x;
    private int y;
    private int z;

    private boolean N() {
        return (!(this.O || TextUtils.isEmpty(this.e)) || (!TextUtils.isEmpty(this.f) && this.O)) && ((this.p != null || this.O) && (this.q != null || getPlacementType() == AdPlacementType.NATIVE_BANNER));
    }

    private void O() {
        if (!this.S) {
            if (this.V != null) {
                this.V.a(this.s);
            }
            this.S = true;
        }
    }

    private void a(Context context, JSONObject jSONObject, c cVar, String str, int i, int i2) {
        this.O = true;
        this.b = context;
        this.V = cVar;
        this.L = i;
        this.M = i2;
        a(jSONObject, str);
    }

    private void a(Map<String, String> map, final Map<String, String> map2) {
        try {
            final Map c = c(map);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    if (!TextUtils.isEmpty(j.this.N)) {
                        HashMap hashMap = new HashMap();
                        hashMap.putAll(map2);
                        hashMap.putAll(c);
                        if (j.this.V != null) {
                            j.this.V.f(j.this.N, hashMap);
                        }
                    }
                }
            }, (long) (this.x * 1000));
        } catch (Exception unused) {
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0112  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0168  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0163  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0197 A:{LOOP_END, LOOP:0: B:46:0x0195->B:47:0x0197, Catch:{ JSONException -> 0x01bd }} */
    private void a(org.json.JSONObject r13, java.lang.String r14) {
        /*
        r12 = this;
        r0 = r12.P;
        if (r0 == 0) goto L_0x000c;
    L_0x0004:
        r13 = new java.lang.IllegalStateException;
        r14 = "Adapter already loaded data";
        r13.<init>(r14);
        throw r13;
    L_0x000c:
        if (r13 != 0) goto L_0x000f;
    L_0x000e:
        return;
    L_0x000f:
        r0 = r12.b;
        r1 = "Audience Network Loaded";
        com.facebook.ads.internal.s.a.d.a(r0, r1);
        r12.N = r14;
        r0 = "fbad_command";
        r0 = com.facebook.ads.internal.s.a.k.a(r13, r0);
        r1 = android.text.TextUtils.isEmpty(r0);
        r2 = 0;
        if (r1 == 0) goto L_0x0027;
    L_0x0025:
        r0 = r2;
        goto L_0x002b;
    L_0x0027:
        r0 = android.net.Uri.parse(r0);
    L_0x002b:
        r12.d = r0;
        r0 = "advertiser_name";
        r0 = com.facebook.ads.internal.s.a.k.a(r13, r0);
        r12.e = r0;
        r0 = "title";
        r0 = com.facebook.ads.internal.s.a.k.a(r13, r0);
        r12.f = r0;
        r0 = "subtitle";
        r0 = com.facebook.ads.internal.s.a.k.a(r13, r0);
        r12.g = r0;
        r0 = "headline";
        r0 = com.facebook.ads.internal.s.a.k.a(r13, r0);
        r12.h = r0;
        r0 = "body";
        r0 = com.facebook.ads.internal.s.a.k.a(r13, r0);
        r12.i = r0;
        r0 = "call_to_action";
        r0 = com.facebook.ads.internal.s.a.k.a(r13, r0);
        r12.j = r0;
        r0 = r12.j;
        r0 = android.text.TextUtils.isEmpty(r0);
        if (r0 == 0) goto L_0x0067;
    L_0x0065:
        r12.j = r2;
    L_0x0067:
        r0 = "social_context";
        r0 = com.facebook.ads.internal.s.a.k.a(r13, r0);
        r12.k = r0;
        r0 = "link_description";
        r0 = com.facebook.ads.internal.s.a.k.a(r13, r0);
        r12.l = r0;
        r0 = "sponsored_translation";
        r0 = com.facebook.ads.internal.s.a.k.a(r13, r0);
        r12.m = r0;
        r0 = "ad_translation";
        r0 = com.facebook.ads.internal.s.a.k.a(r13, r0);
        r12.n = r0;
        r0 = "promoted_translation";
        r0 = com.facebook.ads.internal.s.a.k.a(r13, r0);
        r12.o = r0;
        r0 = "icon";
        r0 = r13.optJSONObject(r0);
        r0 = com.facebook.ads.internal.p.g.a(r0);
        r12.p = r0;
        r0 = "image";
        r0 = r13.optJSONObject(r0);
        r0 = com.facebook.ads.internal.p.g.a(r0);
        r12.q = r0;
        r0 = "star_rating";
        r0 = r13.optJSONObject(r0);
        r0 = com.facebook.ads.internal.p.i.a(r0);
        r12.r = r0;
        r0 = "used_report_url";
        r0 = com.facebook.ads.internal.s.a.k.a(r13, r0);
        r12.s = r0;
        r0 = "enable_view_log";
        r0 = r13.optBoolean(r0);
        r12.v = r0;
        r0 = "enable_snapshot_log";
        r0 = r13.optBoolean(r0);
        r12.w = r0;
        r0 = "snapshot_log_delay_second";
        r1 = 4;
        r0 = r13.optInt(r0, r1);
        r12.x = r0;
        r0 = "snapshot_compress_quality";
        r1 = 0;
        r0 = r13.optInt(r0, r1);
        r12.y = r0;
        r0 = "viewability_check_initial_delay";
        r0 = r13.optInt(r0, r1);
        r12.z = r0;
        r0 = "viewability_check_interval";
        r3 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r0 = r13.optInt(r0, r3);
        r12.A = r0;
        r0 = "ad_choices_icon";
        r0 = r13.optJSONObject(r0);
        r3 = "native_ui_config";
        r3 = r13.optJSONObject(r3);
        if (r3 == 0) goto L_0x010a;
    L_0x00fd:
        r4 = r3.length();	 Catch:{ JSONException -> 0x010e }
        if (r4 != 0) goto L_0x0104;
    L_0x0103:
        goto L_0x010a;
    L_0x0104:
        r4 = new com.facebook.ads.internal.p.j;	 Catch:{ JSONException -> 0x010e }
        r4.<init>(r3);	 Catch:{ JSONException -> 0x010e }
        goto L_0x010b;
    L_0x010a:
        r4 = r2;
    L_0x010b:
        r12.J = r4;	 Catch:{ JSONException -> 0x010e }
        goto L_0x0110;
    L_0x010e:
        r12.J = r2;
    L_0x0110:
        if (r0 == 0) goto L_0x0118;
    L_0x0112:
        r0 = com.facebook.ads.internal.p.g.a(r0);
        r12.G = r0;
    L_0x0118:
        r0 = "ad_choices_link_url";
        r0 = com.facebook.ads.internal.s.a.k.a(r13, r0);
        r12.H = r0;
        r0 = "request_id";
        r0 = com.facebook.ads.internal.s.a.k.a(r13, r0);
        r12.I = r0;
        r0 = "invalidation_behavior";
        r0 = r13.optString(r0);
        r0 = com.facebook.ads.internal.a.d.a(r0);
        r12.t = r0;
        r0 = new org.json.JSONArray;	 Catch:{ JSONException -> 0x0140 }
        r3 = "detection_strings";
        r3 = r13.optString(r3);	 Catch:{ JSONException -> 0x0140 }
        r0.<init>(r3);	 Catch:{ JSONException -> 0x0140 }
        goto L_0x0145;
    L_0x0140:
        r0 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);
        r0 = r2;
    L_0x0145:
        r0 = com.facebook.ads.internal.a.e.a(r0);
        r12.u = r0;
        r0 = "video_url";
        r0 = com.facebook.ads.internal.s.a.k.a(r13, r0);
        r12.B = r0;
        r0 = "video_mpd";
        r0 = com.facebook.ads.internal.s.a.k.a(r13, r0);
        r12.C = r0;
        r0 = "video_autoplay_enabled";
        r0 = r13.has(r0);
        if (r0 != 0) goto L_0x0168;
    L_0x0163:
        r0 = com.facebook.ads.internal.p.l.DEFAULT;
    L_0x0165:
        r12.D = r0;
        goto L_0x0176;
    L_0x0168:
        r0 = "video_autoplay_enabled";
        r0 = r13.optBoolean(r0);
        if (r0 == 0) goto L_0x0173;
    L_0x0170:
        r0 = com.facebook.ads.internal.p.l.ON;
        goto L_0x0165;
    L_0x0173:
        r0 = com.facebook.ads.internal.p.l.OFF;
        goto L_0x0165;
    L_0x0176:
        r0 = "video_report_url";
        r0 = com.facebook.ads.internal.s.a.k.a(r13, r0);
        r12.F = r0;
        r0 = "carousel";
        r13 = r13.optJSONArray(r0);	 Catch:{ JSONException -> 0x01bd }
        if (r13 == 0) goto L_0x01c5;
    L_0x0186:
        r0 = r13.length();	 Catch:{ JSONException -> 0x01bd }
        if (r0 <= 0) goto L_0x01c5;
    L_0x018c:
        r0 = r13.length();	 Catch:{ JSONException -> 0x01bd }
        r10 = new java.util.ArrayList;	 Catch:{ JSONException -> 0x01bd }
        r10.<init>(r0);	 Catch:{ JSONException -> 0x01bd }
    L_0x0195:
        if (r1 >= r0) goto L_0x01ba;
    L_0x0197:
        r11 = new com.facebook.ads.internal.adapters.j;	 Catch:{ JSONException -> 0x01bd }
        r11.<init>();	 Catch:{ JSONException -> 0x01bd }
        r4 = r12.b;	 Catch:{ JSONException -> 0x01bd }
        r5 = r13.getJSONObject(r1);	 Catch:{ JSONException -> 0x01bd }
        r6 = r12.V;	 Catch:{ JSONException -> 0x01bd }
        r3 = r11;
        r7 = r14;
        r8 = r1;
        r9 = r0;
        r3.a(r4, r5, r6, r7, r8, r9);	 Catch:{ JSONException -> 0x01bd }
        r3 = new com.facebook.ads.internal.p.e;	 Catch:{ JSONException -> 0x01bd }
        r4 = r12.b;	 Catch:{ JSONException -> 0x01bd }
        r5 = r12.W;	 Catch:{ JSONException -> 0x01bd }
        r3.<init>(r4, r11, r2, r5);	 Catch:{ JSONException -> 0x01bd }
        r10.add(r3);	 Catch:{ JSONException -> 0x01bd }
        r1 = r1 + 1;
        goto L_0x0195;
    L_0x01ba:
        r12.K = r10;	 Catch:{ JSONException -> 0x01bd }
        goto L_0x01c5;
    L_0x01bd:
        r13 = move-exception;
        r14 = a;
        r0 = "Unable to parse carousel data.";
        android.util.Log.e(r14, r0, r13);
    L_0x01c5:
        r13 = 1;
        r12.P = r13;
        r13 = r12.N();
        r12.Q = r13;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.adapters.j.a(org.json.JSONObject, java.lang.String):void");
    }

    private Map<String, String> c(Map<String, String> map) {
        HashMap hashMap = new HashMap();
        if (map.containsKey(Promotion.ACTION_VIEW)) {
            hashMap.put(Promotion.ACTION_VIEW, map.get(Promotion.ACTION_VIEW));
        }
        if (map.containsKey("snapshot")) {
            hashMap.put("snapshot", map.get("snapshot"));
        }
        return hashMap;
    }

    public g A() {
        return !K() ? null : this.G;
    }

    public String B() {
        return !K() ? null : this.H;
    }

    public String C() {
        return !K() ? null : "AdChoices";
    }

    public String D() {
        return !K() ? null : this.B;
    }

    public String E() {
        return !K() ? null : this.C;
    }

    public l F() {
        return !K() ? l.DEFAULT : this.D;
    }

    public int G() {
        return this.E;
    }

    public List<e> H() {
        return !K() ? null : this.K;
    }

    public int I() {
        return this.L;
    }

    public int J() {
        return this.M;
    }

    public boolean K() {
        return this.P && this.Q;
    }

    public String L() {
        return this.F;
    }

    public boolean M() {
        return this.O;
    }

    public d a() {
        return this.t;
    }

    public void a(int i) {
        if (K() && i == 0 && this.T > 0 && this.U != null) {
            b.a(com.facebook.ads.internal.l.a.a(this.T, this.U, this.I));
            this.T = 0;
            this.U = null;
        }
    }

    public void a(Context context, r rVar, c cVar, Map<String, Object> map, e.c cVar2) {
        this.b = context;
        this.c = rVar;
        this.V = cVar;
        this.W = cVar2;
        JSONObject jSONObject = (JSONObject) map.get("data");
        com.facebook.ads.internal.j.d dVar = (com.facebook.ads.internal.j.d) map.get("definition");
        this.E = dVar != null ? dVar.k() : 200;
        a(jSONObject, k.a(jSONObject, com.til.colombia.android.internal.e.P));
        if (com.facebook.ads.internal.a.e.a(context, this, cVar)) {
            rVar.a(this, new com.facebook.ads.internal.protocol.a(AdErrorType.NO_FILL, "No Fill"));
            return;
        }
        if (rVar != null) {
            rVar.a(this);
        }
        com.facebook.ads.internal.l.a.a = this.I;
    }

    public void a(View view, List<View> list) {
    }

    public void a(r rVar) {
        this.c = rVar;
    }

    public void a(Map<String, String> map) {
        if (K() && !this.R) {
            if (this.c != null) {
                this.c.b(this);
            }
            Map hashMap = new HashMap();
            if (map != null) {
                hashMap.putAll(map);
            }
            if (this.O) {
                hashMap.put("cardind", String.valueOf(this.L));
                hashMap.put("cardcnt", String.valueOf(this.M));
            }
            if (!(TextUtils.isEmpty(c()) || this.V == null)) {
                this.V.a(c(), hashMap);
            }
            if (e() || i()) {
                a((Map) map, hashMap);
            }
            this.R = true;
        }
    }

    public Collection<String> b() {
        return this.u;
    }

    public void b(Map<String, String> map) {
        if (!K()) {
            return;
        }
        if (com.facebook.ads.internal.n.a.c(this.b) && aa.a((Map) map)) {
            Log.e(a, "Click happened on lockscreen ad");
            return;
        }
        HashMap hashMap = new HashMap();
        if (map != null) {
            hashMap.putAll(map);
        }
        com.facebook.ads.internal.s.a.d.a(this.b, "Click logged");
        if (this.c != null) {
            this.c.c(this);
        }
        if (this.O) {
            hashMap.put("cardind", String.valueOf(this.L));
            hashMap.put("cardcnt", String.valueOf(this.M));
        }
        com.facebook.ads.internal.a.b a = com.facebook.ads.internal.a.c.a(this.b, this.V, this.N, this.d, hashMap);
        if (a != null) {
            try {
                this.T = System.currentTimeMillis();
                this.U = a.a();
                a.b();
            } catch (Exception e) {
                Log.e(a, "Error executing action", e);
            }
        }
    }

    public String c() {
        return this.N;
    }

    public void d() {
        if (this.K != null && !this.K.isEmpty()) {
            for (e I : this.K) {
                I.I();
            }
        }
    }

    public boolean e() {
        return K() && this.v;
    }

    public boolean f() {
        return K() && this.J != null;
    }

    public boolean g() {
        return K() && this.d != null;
    }

    public AdPlacementType getPlacementType() {
        return AdPlacementType.NATIVE;
    }

    public boolean h() {
        return true;
    }

    public boolean i() {
        return K() && this.w;
    }

    public int j() {
        return (this.y < 0 || this.y > 100) ? 0 : this.y;
    }

    public int k() {
        return this.z;
    }

    public int l() {
        return this.A;
    }

    public g m() {
        return !K() ? null : this.p;
    }

    public g n() {
        return !K() ? null : this.q;
    }

    public com.facebook.ads.internal.p.j o() {
        return !K() ? null : this.J;
    }

    public void onDestroy() {
    }

    public String p() {
        if (!K()) {
            return null;
        }
        O();
        return this.e;
    }

    public String q() {
        if (!K()) {
            return null;
        }
        O();
        return this.h;
    }

    public String r() {
        if (!K()) {
            return null;
        }
        O();
        return s.a(this.i);
    }

    public String s() {
        if (!K()) {
            return null;
        }
        O();
        return this.i;
    }

    public String t() {
        if (!K()) {
            return null;
        }
        O();
        return this.j;
    }

    public String u() {
        if (!K()) {
            return null;
        }
        O();
        return this.k;
    }

    public String v() {
        if (!K()) {
            return null;
        }
        O();
        return this.l;
    }

    public String w() {
        if (!K()) {
            return null;
        }
        O();
        return this.m;
    }

    public String x() {
        if (!K()) {
            return null;
        }
        O();
        return this.n;
    }

    public String y() {
        if (!K()) {
            return null;
        }
        O();
        return this.o;
    }

    public i z() {
        if (!K()) {
            return null;
        }
        O();
        return this.r;
    }
}
