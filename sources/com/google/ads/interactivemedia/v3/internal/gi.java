package com.google.ads.interactivemedia.v3.internal;

import java.util.Map.Entry;
import java.util.Set;

public final class gi extends gf {
    private final hc<String, gf> a = new hc();

    public void a(String str, gf gfVar) {
        Object gfVar2;
        if (gfVar2 == null) {
            gfVar2 = gh.a;
        }
        this.a.put(str, gfVar2);
    }

    public Set<Entry<String, gf>> o() {
        return this.a.entrySet();
    }

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof gi) && ((gi) obj).a.equals(this.a));
    }

    public int hashCode() {
        return this.a.hashCode();
    }
}
