package com.comscore.utils.task;

import com.comscore.analytics.Core;
import com.comscore.utils.CSLog;
import com.comscore.utils.Constants;
import java.util.concurrent.atomic.AtomicBoolean;

class a implements Runnable {
    private AtomicBoolean a;
    private AtomicBoolean b;
    private Runnable c;
    private Core d;
    private long e;
    private long f;
    private long g;
    private boolean h;
    private boolean i;

    a(Runnable runnable, Core core) {
        this(runnable, core, 0);
    }

    a(Runnable runnable, Core core, long j) {
        this(runnable, core, j, false, 0);
    }

    a(Runnable runnable, Core core, long j, boolean z, long j2) {
        this.c = runnable;
        this.d = core;
        this.e = System.currentTimeMillis() + (j > 0 ? j : 0);
        this.i = j > 0;
        this.f = System.currentTimeMillis();
        this.h = z;
        this.g = j2;
        this.a = new AtomicBoolean();
        this.b = new AtomicBoolean();
        this.b.set(false);
        this.a.set(false);
    }

    /* Access modifiers changed, original: 0000 */
    public long a() {
        long currentTimeMillis = this.e - System.currentTimeMillis();
        return currentTimeMillis > 0 ? currentTimeMillis : 0;
    }

    /* Access modifiers changed, original: 0000 */
    public long b() {
        return this.f;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean c() {
        return this.a.get();
    }

    /* Access modifiers changed, original: 0000 */
    public boolean d() {
        return this.i;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean e() {
        return this.b.get();
    }

    /* Access modifiers changed, original: 0000 */
    public long f() {
        return this.e;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean g() {
        return this.h;
    }

    /* Access modifiers changed, original: 0000 */
    public long h() {
        return this.g;
    }

    /* Access modifiers changed, original: 0000 */
    public Runnable i() {
        return this.c;
    }

    public void run() {
        this.a.set(true);
        try {
            this.c.run();
        } catch (Exception e) {
            CSLog.e(getClass(), "Unexpected error running asynchronous task: ");
            CSLog.printStackTrace(e);
            this.d.getStorage().add(Constants.EXCEPTION_OCURRENCES_KEY, 1);
            this.d.setEnabled(false);
        }
        this.a.set(false);
        this.b.set(true);
    }
}
