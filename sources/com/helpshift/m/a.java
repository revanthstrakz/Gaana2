package com.helpshift.m;

import android.support.annotation.Nullable;
import com.helpshift.network.errors.NetworkError;
import com.helpshift.util.l;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class a<V> implements Runnable {
    private final Callable<V> a;
    private final ExecutorService b;
    private final ScheduledExecutorService c;
    private boolean d;

    @Nullable
    public abstract com.helpshift.common.b.a a(Exception exception);

    @Nullable
    public abstract com.helpshift.common.b.a a(V v);

    public a(Callable<V> callable, ExecutorService executorService, ScheduledExecutorService scheduledExecutorService) {
        this.a = callable;
        this.b = executorService;
        this.c = scheduledExecutorService;
    }

    public void a() {
        this.d = false;
        this.c.shutdownNow();
        this.b.shutdownNow();
    }

    public void b() {
        if (!this.d) {
            this.d = true;
            try {
                this.b.execute(this);
            } catch (RejectedExecutionException e) {
                l.c("Helpshift_Poller", "Rejected execution : ", e);
            }
        }
    }

    public void run() {
        a(0, TimeUnit.SECONDS);
    }

    /* Access modifiers changed, original: 0000 */
    public void a(long j, TimeUnit timeUnit) {
        try {
            if (this.d && !this.c.isShutdown()) {
                com.helpshift.common.b.a a;
                try {
                    a = a(this.c.schedule(this.a, j, timeUnit).get());
                } catch (Exception e) {
                    if (e.getCause() instanceof NetworkError) {
                        a = a((NetworkError) e.getCause());
                    } else {
                        a = a(e);
                    }
                }
                if (a != null) {
                    if (!this.b.isShutdown()) {
                        this.b.execute(new Runnable() {
                            public void run() {
                                a.this.a(a.a, a.b);
                            }
                        });
                        return;
                    }
                }
                this.d = false;
            }
        } catch (RejectedExecutionException e2) {
            l.c("Helpshift_Poller", "Rejected execution of run delayed : ", e2);
        }
    }
}
