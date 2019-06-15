package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;

@zzark
final class zzbce<V> extends FutureTask<V> implements zzbcb<V> {
    private final zzbcc zzepj = new zzbcc();

    zzbce(Callable<V> callable) {
        super(callable);
    }

    zzbce(Runnable runnable, V v) {
        super(runnable, v);
    }

    public final void zza(Runnable runnable, Executor executor) {
        this.zzepj.zza(runnable, executor);
    }

    /* Access modifiers changed, original: protected|final */
    public final void done() {
        this.zzepj.zzaaw();
    }
}
