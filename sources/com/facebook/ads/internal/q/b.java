package com.facebook.ads.internal.q;

import android.content.Context;
import com.facebook.ads.internal.c.a;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.protocol.c;
import com.facebook.ads.internal.protocol.e;
import com.facebook.ads.internal.protocol.g;
import com.facebook.ads.internal.s.a.aa;
import com.facebook.ads.internal.s.a.m;
import com.facebook.ads.internal.s.a.v;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class b {
    protected String a;
    public Context b;
    public e c;
    private c d;
    private final AdPlacementType e = this.d.a();
    private final String f;
    private boolean g;
    private boolean h;
    private int i;
    private m j;
    private final Map<String, String> k;
    private final g l;
    private String m;
    private String n;

    public b(Context context, com.facebook.ads.internal.k.c cVar, String str, m mVar, e eVar, String str2, int i, boolean z, boolean z2, g gVar, String str3, String str4) {
        this.b = context;
        this.k = cVar.b();
        this.a = str;
        this.j = mVar;
        this.c = eVar;
        this.f = str2;
        this.i = i;
        this.g = z;
        this.h = z2;
        this.l = gVar;
        this.d = c.a(eVar);
        this.m = str3;
        this.n = str4;
    }

    private void a(Map<String, String> map, String str, String str2) {
        map.put(str, str2);
    }

    public String a() {
        return this.a;
    }

    public c b() {
        return this.d;
    }

    public m c() {
        return this.j;
    }

    public int d() {
        return this.i;
    }

    public g e() {
        return this.l;
    }

    public Map<String, String> f() {
        HashMap hashMap = new HashMap(this.k);
        a(hashMap, "IDFA", com.facebook.ads.internal.d.b.b);
        a(hashMap, "IDFA_FLAG", com.facebook.ads.internal.d.b.c ? "0" : "1");
        a(hashMap, "COPPA", String.valueOf(this.h));
        a(hashMap, "PLACEMENT_ID", this.a);
        if (this.e != AdPlacementType.UNKNOWN) {
            a(hashMap, "PLACEMENT_TYPE", this.e.toString().toLowerCase());
        }
        if (this.j != null) {
            a(hashMap, "WIDTH", String.valueOf(this.j.b()));
            a(hashMap, "HEIGHT", String.valueOf(this.j.a()));
        }
        if (this.c != null) {
            a(hashMap, "TEMPLATE_ID", String.valueOf(this.c.a()));
        }
        if (this.g) {
            a(hashMap, "TEST_MODE", "1");
        }
        if (this.f != null) {
            a(hashMap, "DEMO_AD_ID", this.f);
        }
        if (this.i != 0) {
            a(hashMap, "NUM_ADS_REQUESTED", String.valueOf(this.i));
        }
        a(hashMap, "CLIENT_EVENTS", com.facebook.ads.internal.l.b.a());
        a(hashMap, "KG_RESTRICTED", String.valueOf(aa.a(this.b)));
        a(hashMap, "REQUEST_TIME", v.b(System.currentTimeMillis()));
        if (this.l.c()) {
            a(hashMap, "BID_ID", this.l.d());
        }
        if (this.m != null) {
            a(hashMap, "STACK_TRACE", this.m);
        }
        a(hashMap, "CLIENT_REQUEST_ID", UUID.randomUUID().toString());
        a(hashMap, "AD_REPORTING_CONFIG_LAST_UPDATE_TIME", v.a(a.a(this.b)));
        if (this.n != null) {
            a(hashMap, "EXTRA_HINTS", this.n);
        }
        return hashMap;
    }
}
