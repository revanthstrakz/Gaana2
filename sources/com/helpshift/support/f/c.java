package com.helpshift.support.f;

import java.util.List;

public class c implements g {
    private final int a;
    private final String b;
    private final List<g> c;
    private com.helpshift.support.d.c d;

    public void a(com.helpshift.support.d.c cVar) {
        this.d = cVar;
    }

    public int a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public void c() {
        if (this.a != 0) {
            this.d.a(this.a, this.c, true);
        } else {
            this.d.a(this.b, this.c, true);
        }
    }
}
