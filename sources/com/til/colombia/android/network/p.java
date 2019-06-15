package com.til.colombia.android.network;

import android.support.annotation.NonNull;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class p extends ThreadPoolExecutor {
    BlockingQueue<Runnable> a;

    static class a<T> extends FutureTask<T> implements Comparable<a<T>> {
        volatile int a = 0;

        public final /* synthetic */ int compareTo(@NonNull Object obj) {
            return Integer.valueOf(this.a).compareTo(Integer.valueOf(((a) obj).a));
        }

        public a(Runnable runnable, T t, int i) {
            super(runnable, null);
            this.a = i;
        }

        private a(Callable<T> callable, int i) {
            super(callable);
            this.a = i;
        }

        private int a(@NonNull a<T> aVar) {
            return Integer.valueOf(this.a).compareTo(Integer.valueOf(aVar.a));
        }
    }

    private p(int i, int i2, long j, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue) {
        super(i, i2, 0, timeUnit, blockingQueue);
    }

    public static ExecutorService a(int i) {
        return new p(15, 15, 0, TimeUnit.MILLISECONDS, new PriorityBlockingQueue());
    }

    private Future<?> b(Runnable runnable, int i) {
        return super.submit(new a(runnable, null, i));
    }

    public final void a(Runnable runnable, int i) {
        super.execute(new a(runnable, null, i));
    }

    /* Access modifiers changed, original: protected|final */
    public final <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        return (RunnableFuture) callable;
    }

    /* Access modifiers changed, original: protected|final */
    public final <T> RunnableFuture<T> newTaskFor(Runnable runnable, T t) {
        return (RunnableFuture) runnable;
    }

    public final int a() {
        return this.a != null ? this.a.size() : 0;
    }
}
