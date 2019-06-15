package com.android.volley;

public class i<T> {
    public final T a;
    public final com.android.volley.a.a b;
    public final VolleyError c;
    public boolean d;
    public boolean e;

    public interface a {
        void onErrorResponse(VolleyError volleyError);
    }

    public interface b<T> {
        void onResponse(T t);
    }

    public interface c<T> {
        void a(T t, boolean z);
    }

    public static <T> i<T> a(T t, com.android.volley.a.a aVar) {
        return new i(t, aVar);
    }

    public static <T> i<T> a(VolleyError volleyError) {
        return new i(volleyError);
    }

    public boolean a() {
        return this.c == null;
    }

    private i(T t, com.android.volley.a.a aVar) {
        this.d = false;
        this.e = false;
        this.a = t;
        this.b = aVar;
        this.c = null;
    }

    private i(VolleyError volleyError) {
        this.d = false;
        this.e = false;
        this.a = null;
        this.b = null;
        this.c = volleyError;
    }
}
