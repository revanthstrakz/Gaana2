package com.facebook.ads.internal.j;

import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class c {
    private List<a> a = new ArrayList();
    private int b = 0;
    private d c;
    @Nullable
    private String d;
    @Nullable
    private String e;

    public c(d dVar, @Nullable String str, @Nullable String str2) {
        this.c = dVar;
        this.d = str;
        this.e = str2;
    }

    public d a() {
        return this.c;
    }

    public void a(a aVar) {
        this.a.add(aVar);
    }

    @Nullable
    public String b() {
        return this.d;
    }

    @Nullable
    public String c() {
        return this.e;
    }

    public int d() {
        return this.a.size();
    }

    public a e() {
        if (this.b >= this.a.size()) {
            return null;
        }
        this.b++;
        return (a) this.a.get(this.b - 1);
    }

    public boolean f() {
        return this.c == null || System.currentTimeMillis() > this.c.a() + ((long) this.c.l());
    }
}
