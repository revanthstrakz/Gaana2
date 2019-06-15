package com.google.android.gms.internal.ads;

import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RunnableFuture;

@zzark
public abstract class zzbbk extends AbstractExecutorService implements zzbcf {
    /* Access modifiers changed, original: protected|final */
    public final <T> RunnableFuture<T> newTaskFor(Runnable runnable, T t) {
        return new zzbce(runnable, t);
    }

    /* Access modifiers changed, original: protected|final */
    public final <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        return new zzbce(callable);
    }

    /* renamed from: zze */
    public final zzbcb<?> submit(Runnable runnable) throws RejectedExecutionException {
        return (zzbcb) super.submit(runnable);
    }

    /* renamed from: zza */
    public final <T> zzbcb<T> submit(Callable<T> callable) throws RejectedExecutionException {
        return (zzbcb) super.submit(callable);
    }

    public /* synthetic */ Future submit(Runnable runnable, Object obj) {
        return (zzbcb) super.submit(runnable, obj);
    }
}
