package com.comscore.utils.task;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;

public class TaskThread extends Thread {
    private boolean a = false;
    private Object b;
    private TaskExecutor c;

    TaskThread(TaskExecutor taskExecutor) {
        this.c = taskExecutor;
        this.b = new Object();
    }

    private void a(long j) {
        synchronized (this.b) {
            try {
                this.b.wait(j);
            } catch (InterruptedException e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean a() {
        return this.a;
    }

    /* Access modifiers changed, original: 0000 */
    public void b() {
        this.a = true;
    }

    /* Access modifiers changed, original: 0000 */
    public void c() {
        synchronized (this.b) {
            this.b.notify();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void d() {
        long a = this.c.a();
        if (a > 0) {
            a(a);
        }
    }

    public void run() {
        while (!a()) {
            a b = this.c.b();
            if (b != null) {
                b.run();
                this.c.a(b);
                if (b.g()) {
                    this.c.execute(b.i(), b.h(), b.g(), b.h());
                }
            } else {
                d();
            }
        }
    }
}
