package com.google.ads.interactivemedia.v3.internal;

abstract class co {
    protected final ck a;
    private long b = -1;

    public static final class a extends bl {
        public a(String str) {
            super(str);
        }
    }

    protected co(ck ckVar) {
        this.a = ckVar;
    }

    public abstract void a(fp fpVar, long j) throws bl;

    public abstract boolean a(fp fpVar) throws bl;

    public final void a(long j) {
        this.b = j;
    }

    public final long a() {
        return this.b;
    }

    public final void b(fp fpVar, long j) throws bl {
        if (a(fpVar)) {
            a(fpVar, j);
        }
    }
}
