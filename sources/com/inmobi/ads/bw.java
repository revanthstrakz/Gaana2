package com.inmobi.ads;

import android.support.annotation.NonNull;
import com.google.api.client.http.HttpMethods;
import com.inmobi.a.n;
import com.inmobi.commons.core.network.c;
import com.inmobi.commons.core.network.d;
import java.lang.ref.WeakReference;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

final class bw {
    public static final Executor d;
    private static final String e = "bw";
    private static final int g = Runtime.getRuntime().availableProcessors();
    private static final int h = Math.max(2, Math.min(g - 1, 4));
    private static final int i = ((g * 2) + 1);
    private static final ThreadFactory j = new ThreadFactory() {
        private final AtomicInteger a = new AtomicInteger(1);

        public final Thread newThread(@NonNull Runnable runnable) {
            StringBuilder stringBuilder = new StringBuilder("VastNetworkTask #");
            stringBuilder.append(this.a.getAndIncrement());
            return new Thread(runnable, stringBuilder.toString());
        }
    };
    private static final BlockingQueue<Runnable> k = new LinkedBlockingQueue(128);
    c a;
    WeakReference<bv> b;
    long c = 0;
    private final CountDownLatch f;

    static {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(h, i, 30, TimeUnit.SECONDS, k, j);
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        d = threadPoolExecutor;
    }

    public bw(bv bvVar, int i, CountDownLatch countDownLatch) {
        this.a = new c(HttpMethods.GET, bvVar.a);
        this.a.r = i;
        this.b = new WeakReference(bvVar);
        this.f = countDownLatch;
    }

    /* Access modifiers changed, original: final */
    public final void a() {
        if (this.f != null) {
            this.f.countDown();
        }
    }

    public final void a(d dVar) {
        new StringBuilder("Vast Media Header Request fetch failed:").append(dVar.b.b);
        try {
            n.a().a(this.a.g());
            n.a().b(dVar.c());
        } catch (Exception e) {
            new StringBuilder("Handling Vast Media Header Request fetch failed encountered an unexpected error: ").append(e.getMessage());
        } finally {
            a();
        }
    }
}
