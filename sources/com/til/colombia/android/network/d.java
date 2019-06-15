package com.til.colombia.android.network;

import com.android.volley.Request;
import com.android.volley.a;
import com.android.volley.e;
import com.android.volley.h;
import com.android.volley.i;
import com.android.volley.i.b;
import com.android.volley.toolbox.c;
import com.android.volley.toolbox.k;
import com.android.volley.toolbox.n;

public final class d {
    public static h a = null;
    public static h b = null;
    public static final String c = "feed";
    private static h d = null;
    private static final int e = 2097152;
    private static final int f = 1;
    private static final String g = "click";

    private static a d() {
        return new c(com.til.colombia.android.internal.a.a().getCacheDir(), 2097152);
    }

    public static a a() {
        return new k();
    }

    private static e e() {
        return new com.android.volley.toolbox.a(new e());
    }

    private static e f() {
        return new com.android.volley.toolbox.a(new f());
    }

    private static e g() {
        return new com.android.volley.toolbox.a(new g());
    }

    public static h b() {
        if (a == null) {
            if (com.til.colombia.android.internal.a.H()) {
                a = new h(new k(), e(), 1);
            } else {
                a = new h(new c(com.til.colombia.android.internal.a.a().getCacheDir(), 2097152), e(), 1);
            }
            a.a();
        }
        return a;
    }

    private static h h() {
        if (d == null) {
            h hVar = new h(new k(), new com.android.volley.toolbox.a(new f()), 1);
            d = hVar;
            hVar.a();
        }
        return d;
    }

    private static h i() {
        if (b == null) {
            h hVar = new h(new k(), new com.android.volley.toolbox.a(new g()), 1);
            b = hVar;
            hVar.a();
        }
        return b;
    }

    public static void a(String str, b<String> bVar, i.a aVar) {
        Request nVar = new n(0, str, bVar, aVar);
        nVar.setTag("click");
        if (b == null) {
            h hVar = new h(new k(), new com.android.volley.toolbox.a(new g()), 1);
            b = hVar;
            hVar.a();
        }
        b.a(nVar);
    }

    public static void c() {
        if (a != null) {
            a.a(c);
        }
        if (b != null) {
            b.a((Object) "click");
        }
    }
}
