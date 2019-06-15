package com.facebook.ads.internal.r.b;

import android.util.Log;
import java.lang.Thread.State;
import java.util.concurrent.atomic.AtomicInteger;

class k {
    private final n a;
    private final a b;
    private final Object c = new Object();
    private final Object d = new Object();
    private final AtomicInteger e;
    private volatile Thread f;
    private volatile boolean g;
    private volatile int h = -1;

    private class a implements Runnable {
        private a() {
        }

        public void run() {
            k.this.e();
        }
    }

    public k(n nVar, a aVar) {
        this.a = (n) j.a(nVar);
        this.b = (a) j.a(aVar);
        this.e = new AtomicInteger();
    }

    private void b() {
        int i = this.e.get();
        if (i >= 1) {
            this.e.set(0);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Error reading source ");
            stringBuilder.append(i);
            stringBuilder.append(" times");
            throw new l(stringBuilder.toString());
        }
    }

    private void b(long j, long j2) {
        a(j, j2);
        synchronized (this.c) {
            this.c.notifyAll();
        }
    }

    private synchronized void c() {
        Object obj = (this.f == null || this.f.getState() == State.TERMINATED) ? null : 1;
        if (!(this.g || this.b.d() || obj != null)) {
            a aVar = new a();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Source reader for ");
            stringBuilder.append(this.a);
            this.f = new Thread(aVar, stringBuilder.toString());
            this.f.start();
        }
    }

    private void d() {
        synchronized (this.c) {
            try {
                this.c.wait(1000);
            } catch (InterruptedException e) {
                throw new l("Waiting source data is interrupted!", e);
            } catch (Throwable th) {
            }
        }
    }

    /* JADX WARNING: Missing block: B:21:0x0038, code skipped:
            r2 = r2 + r4;
     */
    /* JADX WARNING: Missing block: B:23:?, code skipped:
            b((long) r2, (long) r1);
     */
    private void e() {
        /*
        r9 = this;
        r0 = -1;
        r1 = 0;
        r2 = r9.b;	 Catch:{ Throwable -> 0x0065, all -> 0x005f }
        r2 = r2.a();	 Catch:{ Throwable -> 0x0065, all -> 0x005f }
        r1 = r9.a;	 Catch:{ Throwable -> 0x0059, all -> 0x0054 }
        r1.a(r2);	 Catch:{ Throwable -> 0x0059, all -> 0x0054 }
        r1 = r9.a;	 Catch:{ Throwable -> 0x0059, all -> 0x0054 }
        r1 = r1.a();	 Catch:{ Throwable -> 0x0059, all -> 0x0054 }
        r3 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r3 = new byte[r3];	 Catch:{ Throwable -> 0x0050, all -> 0x004e }
    L_0x0017:
        r4 = r9.a;	 Catch:{ Throwable -> 0x0050, all -> 0x004e }
        r4 = r4.a(r3);	 Catch:{ Throwable -> 0x0050, all -> 0x004e }
        if (r4 == r0) goto L_0x0042;
    L_0x001f:
        r5 = r9.d;	 Catch:{ Throwable -> 0x0050, all -> 0x004e }
        monitor-enter(r5);	 Catch:{ Throwable -> 0x0050, all -> 0x004e }
        r6 = r9.g();	 Catch:{ all -> 0x003f }
        if (r6 == 0) goto L_0x0032;
    L_0x0028:
        monitor-exit(r5);	 Catch:{ all -> 0x003f }
        r9.h();
        r2 = (long) r2;
        r0 = (long) r1;
        r9.b(r2, r0);
        return;
    L_0x0032:
        r6 = r9.b;	 Catch:{ all -> 0x003f }
        r6.a(r3, r4);	 Catch:{ all -> 0x003f }
        monitor-exit(r5);	 Catch:{ all -> 0x003f }
        r2 = r2 + r4;
        r4 = (long) r2;
        r6 = (long) r1;
        r9.b(r4, r6);	 Catch:{ Throwable -> 0x0050, all -> 0x004e }
        goto L_0x0017;
    L_0x003f:
        r0 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x003f }
        throw r0;	 Catch:{ Throwable -> 0x0050, all -> 0x004e }
    L_0x0042:
        r9.f();	 Catch:{ Throwable -> 0x0050, all -> 0x004e }
        r9.h();
        r2 = (long) r2;
        r0 = (long) r1;
        r9.b(r2, r0);
        return;
    L_0x004e:
        r0 = move-exception;
        goto L_0x007e;
    L_0x0050:
        r0 = move-exception;
        r8 = r2;
        r2 = r1;
        goto L_0x005d;
    L_0x0054:
        r1 = move-exception;
        r8 = r1;
        r1 = r0;
        r0 = r8;
        goto L_0x007e;
    L_0x0059:
        r1 = move-exception;
        r8 = r2;
        r2 = r0;
        r0 = r1;
    L_0x005d:
        r1 = r8;
        goto L_0x0069;
    L_0x005f:
        r2 = move-exception;
        r8 = r1;
        r1 = r0;
        r0 = r2;
        r2 = r8;
        goto L_0x007e;
    L_0x0065:
        r2 = move-exception;
        r8 = r2;
        r2 = r0;
        r0 = r8;
    L_0x0069:
        r3 = r9.e;	 Catch:{ all -> 0x007a }
        r3.incrementAndGet();	 Catch:{ all -> 0x007a }
        r9.a(r0);	 Catch:{ all -> 0x007a }
        r9.h();
        r0 = (long) r1;
        r2 = (long) r2;
        r9.b(r0, r2);
        return;
    L_0x007a:
        r0 = move-exception;
        r8 = r2;
        r2 = r1;
        r1 = r8;
    L_0x007e:
        r9.h();
        r2 = (long) r2;
        r4 = (long) r1;
        r9.b(r2, r4);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.r.b.k.e():void");
    }

    private void f() {
        synchronized (this.d) {
            if (!g() && this.b.a() == this.a.a()) {
                this.b.c();
            }
        }
    }

    private boolean g() {
        return Thread.currentThread().isInterrupted() || this.g;
    }

    private void h() {
        try {
            this.a.b();
        } catch (l e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Error closing source ");
            stringBuilder.append(this.a);
            a(new l(stringBuilder.toString(), e));
        }
    }

    public int a(byte[] bArr, long j, int i) {
        m.a(bArr, j, i);
        while (!this.b.d() && ((long) this.b.a()) < j + ((long) i) && !this.g) {
            c();
            d();
            b();
        }
        int a = this.b.a(bArr, j, i);
        if (this.b.d() && this.h != 100) {
            this.h = 100;
            a(100);
        }
        return a;
    }

    public void a() {
        synchronized (this.d) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Shutdown proxy for ");
            stringBuilder.append(this.a);
            Log.d("ProxyCache", stringBuilder.toString());
            try {
                this.g = true;
                if (this.f != null) {
                    this.f.interrupt();
                }
                this.b.b();
            } catch (l e) {
                a(e);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(int i) {
    }

    /* Access modifiers changed, original: protected */
    public void a(long j, long j2) {
        Object obj = null;
        int i = ((j2 > 0 ? 1 : (j2 == 0 ? 0 : -1)) == 0 ? 1 : null) != null ? 100 : (int) ((j * 100) / j2);
        Object obj2 = i != this.h ? 1 : null;
        if (j2 >= 0) {
            obj = 1;
        }
        if (!(obj == null || obj2 == null)) {
            a(i);
        }
        this.h = i;
    }

    /* Access modifiers changed, original: protected|final */
    public final void a(Throwable th) {
        if (th instanceof i) {
            Log.d("ProxyCache", "ProxyCache is interrupted");
        } else {
            Log.e("ProxyCache", "ProxyCache error", th);
        }
    }
}
