package com.helpshift.websockets;

import java.util.Timer;
import java.util.TimerTask;

abstract class u {
    private final ae a;
    private final String b;
    private Timer c;
    private boolean d;
    private long e;
    private r f;

    private final class a extends TimerTask {
        a() {
        }

        public void run() {
            u.this.d();
        }
    }

    public abstract ah a(byte[] bArr);

    public u(ae aeVar, String str, r rVar) {
        this.a = aeVar;
        this.b = str;
        this.f = rVar;
    }

    public void a() {
        a(c());
    }

    public void b() {
        synchronized (this) {
            if (this.c == null) {
                return;
            }
            this.d = false;
            this.c.cancel();
        }
    }

    public long c() {
        long j;
        synchronized (this) {
            j = this.e;
        }
        return j;
    }

    public void a(long j) {
        if (j < 0) {
            j = 0;
        }
        synchronized (this) {
            this.e = j;
        }
        if (j != 0 && this.a.a()) {
            synchronized (this) {
                if (this.c == null) {
                    this.c = new Timer(this.b);
                }
                if (!this.d) {
                    this.d = a(this.c, new a(), j);
                }
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void d() {
        synchronized (this) {
            if (this.e != 0) {
                if (this.a.a()) {
                    this.a.a(e());
                    this.d = a(this.c, new a(), this.e);
                    return;
                }
            }
            this.d = false;
        }
    }

    private ah e() {
        return a(f());
    }

    private byte[] f() {
        if (this.f == null) {
            return null;
        }
        try {
            return this.f.a();
        } catch (Throwable unused) {
            return null;
        }
    }

    private static boolean a(Timer timer, a aVar, long j) {
        try {
            timer.schedule(aVar, j);
            return true;
        } catch (RuntimeException unused) {
            return false;
        }
    }
}
