package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ga {
    private gz a = gz.a;
    private go b = go.DEFAULT;
    private fy c = fx.IDENTITY;
    private final Map<Type, gb<?>> d = new HashMap();
    private final List<gq> e = new ArrayList();
    private final List<gq> f = new ArrayList();
    private boolean g = false;
    private String h;
    private int i = 2;
    private int j = 2;
    private boolean k = false;
    private boolean l = false;
    private boolean m = true;
    private boolean n = false;
    private boolean o = false;
    private boolean p = false;

    public ga a(fv fvVar) {
        this.a = this.a.a(fvVar, true, false);
        return this;
    }

    public ga a(Type type, Object obj) {
        boolean z = obj instanceof gm;
        boolean z2 = z || (obj instanceof ge) || (obj instanceof gb) || (obj instanceof gp);
        gw.a(z2);
        if (obj instanceof gb) {
            this.d.put(type, (gb) obj);
        }
        if (z || (obj instanceof ge)) {
            this.e.add(hs.b(hw.a(type), obj));
        }
        if (obj instanceof gp) {
            this.e.add(hu.a(hw.a(type), (gp) obj));
        }
        return this;
    }

    public ga a(gq gqVar) {
        this.e.add(gqVar);
        return this;
    }

    public fz a() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.e);
        Collections.reverse(arrayList);
        arrayList.addAll(this.f);
        a(this.h, this.i, this.j, arrayList);
        return new fz(this.a, this.c, this.d, this.g, this.k, this.o, this.m, this.n, this.p, this.l, this.b, arrayList);
    }

    private void a(String str, int i, int i2, List<gq> list) {
        Object fuVar;
        if (str != null && !"".equals(str.trim())) {
            fuVar = new fu(str);
        } else if (i != 2 && i2 != 2) {
            fuVar = new fu(i, i2);
        } else {
            return;
        }
        list.add(hs.a(hw.b(Date.class), fuVar));
        list.add(hs.a(hw.b(Timestamp.class), fuVar));
        list.add(hs.a(hw.b(java.sql.Date.class), fuVar));
    }
}
