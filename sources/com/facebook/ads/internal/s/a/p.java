package com.facebook.ads.internal.s.a;

import android.os.AsyncTask;
import android.support.annotation.VisibleForTesting;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class p implements Executor {
    public static final Executor a = new p("ASYNC_TASK", 32);
    public static final Executor b = new p("DB", 0);
    private ThreadPoolExecutor c;
    private Executor d = AsyncTask.THREAD_POOL_EXECUTOR;
    private final int e;

    @VisibleForTesting
    p(final String str, int i) {
        this.e = i;
        i = Runtime.getRuntime().availableProcessors();
        this.c = new ThreadPoolExecutor(Math.max(2, Math.min(i - 1, 4)), (i * 2) + 1, 30, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ThreadFactory() {
            private final AtomicInteger c = new AtomicInteger(1);

            public Thread newThread(Runnable runnable) {
                return new Thread(runnable, String.format(Locale.US, "FAN:%s #%d", new Object[]{str, Integer.valueOf(this.c.getAndIncrement())}));
            }
        });
        this.c.allowCoreThreadTimeOut(true);
    }

    public void execute(Runnable runnable) {
        if (!(this.d instanceof ThreadPoolExecutor) || ((ThreadPoolExecutor) this.d).getQueue().size() >= this.e) {
            this.c.execute(runnable);
        } else {
            this.d.execute(runnable);
        }
    }
}
