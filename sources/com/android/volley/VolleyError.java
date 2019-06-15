package com.android.volley;

public class VolleyError extends Exception {
    public final g a;
    private long b;

    public VolleyError() {
        this.a = null;
    }

    public VolleyError(g gVar) {
        this.a = gVar;
    }

    public VolleyError(String str) {
        super(str);
        this.a = null;
    }

    public VolleyError(Throwable th) {
        super(th);
        this.a = null;
    }

    /* Access modifiers changed, original: 0000 */
    public void a(long j) {
        this.b = j;
    }
}
