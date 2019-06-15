package com.helpshift.k;

import com.helpshift.q.d;
import com.helpshift.q.f;

public class b {
    public final a a;
    public final c b;

    private static final class a {
        static final b a = new b();
    }

    b() {
        d dVar = f.a().a;
        this.a = new a(dVar);
        this.b = new c(dVar);
    }

    public static b a() {
        return a.a;
    }
}
