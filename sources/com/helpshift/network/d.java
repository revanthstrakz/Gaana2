package com.helpshift.network;

import java.util.ArrayList;
import java.util.List;

public class d {
    private List<b> a;
    private j b;
    private c c;
    private com.helpshift.a.a.a.d d;

    public d(j jVar) {
        if (jVar == null) {
            throw new IllegalArgumentException("Status line may not be null.");
        }
        this.b = jVar;
        this.a = new ArrayList(16);
    }

    public void a(c cVar) {
        this.c = cVar;
    }

    public c a() {
        return this.c;
    }

    public j b() {
        return this.b;
    }

    public void a(b bVar) {
        if (bVar != null) {
            this.a.add(bVar);
        }
    }

    public b[] c() {
        return (b[]) this.a.toArray(new b[this.a.size()]);
    }

    public com.helpshift.a.a.a.d d() {
        return this.d;
    }

    public void a(com.helpshift.a.a.a.d dVar) {
        this.d = dVar;
    }
}
