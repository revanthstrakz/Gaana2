package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.api.AdError;
import com.google.ads.interactivemedia.v3.api.BaseDisplayContainer;
import com.google.ads.interactivemedia.v3.impl.data.b;

public class il {
    private final jd a;
    private BaseDisplayContainer b;
    private jq c;
    private b d;

    public il(jd jdVar, BaseDisplayContainer baseDisplayContainer) throws AdError {
        this.a = jdVar;
        this.b = baseDisplayContainer;
    }

    public void a(b bVar) {
        if (this.d != null) {
            b();
        }
        if (bVar.isLinear()) {
            this.d = bVar;
            c();
        }
    }

    public void a() {
        if (this.c != null) {
            this.c.b();
        }
        this.d = null;
    }

    public void b() {
        a();
        if (this.c != null) {
            this.c.c();
        }
        this.c = null;
    }

    private void c() {
        this.c = new jq(this.a, this.b.getAdContainer());
        this.c.a();
    }
}
