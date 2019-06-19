package com.helpshift.util.a;

import com.helpshift.util.l;

public class e {

    public static abstract class b<T> implements Runnable {
        public T a;
    }

    public static class a implements Runnable {
        private final Runnable a;
        private boolean b;
        private final Object c = new Object();

        a(Runnable runnable) {
            this.a = runnable;
        }

        public void a() {
            synchronized (this.c) {
                try {
                    if (!this.b) {
                        this.c.wait();
                    }
                } catch (InterruptedException e) {
                    l.a("Helpshift_NotiRunnable", "Exception in NotifyingRunnable", e);
                }
            }
        }

        public void run() {
            synchronized (this.c) {
                try {
                    this.a.run();
                    this.b = true;
                    this.c.notifyAll();
                } catch (Throwable th) {
                    this.c.notifyAll();
                }
            }
        }
    }
}
