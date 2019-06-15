package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzbv;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@zzark
public class zzbcl<T> implements zzbcb<T> {
    private final Object mLock = new Object();
    private T mValue;
    private boolean zzdnk;
    private Throwable zzepv;
    private boolean zzepw;
    private final zzbcc zzepx = new zzbcc();

    public final void zza(Runnable runnable, Executor executor) {
        this.zzepx.zza(runnable, executor);
    }

    public final void set(@Nullable T t) {
        synchronized (this.mLock) {
            if (this.zzdnk) {
            } else if (zzaay()) {
                zzbv.zzlj().zzb(new IllegalStateException("Provided SettableFuture with multiple values."), "SettableFuture.set");
            } else {
                this.zzepw = true;
                this.mValue = t;
                this.mLock.notifyAll();
                this.zzepx.zzaaw();
            }
        }
    }

    public final void setException(Throwable th) {
        synchronized (this.mLock) {
            if (this.zzdnk) {
            } else if (zzaay()) {
                zzbv.zzlj().zzb(new IllegalStateException("Provided SettableFuture with multiple values."), "SettableFuture.setException");
            } else {
                this.zzepv = th;
                this.mLock.notifyAll();
                this.zzepx.zzaaw();
            }
        }
    }

    public T get() throws CancellationException, ExecutionException, InterruptedException {
        Object obj;
        synchronized (this.mLock) {
            if (!zzaay()) {
                try {
                    this.mLock.wait();
                } catch (InterruptedException e) {
                    throw e;
                }
            }
            if (this.zzepv != null) {
                throw new ExecutionException(this.zzepv);
            } else if (this.zzdnk) {
                throw new CancellationException("SettableFuture was cancelled.");
            } else {
                obj = this.mValue;
            }
        }
        return obj;
    }

    public T get(long j, TimeUnit timeUnit) throws CancellationException, ExecutionException, InterruptedException, TimeoutException {
        Object obj;
        synchronized (this.mLock) {
            if (!zzaay()) {
                try {
                    j = timeUnit.toMillis(j);
                    if (j != 0) {
                        this.mLock.wait(j);
                    }
                } catch (InterruptedException e) {
                    throw e;
                }
            }
            if (this.zzepv != null) {
                throw new ExecutionException(this.zzepv);
            } else if (!this.zzepw) {
                throw new TimeoutException("SettableFuture timed out.");
            } else if (this.zzdnk) {
                throw new CancellationException("SettableFuture was cancelled.");
            } else {
                obj = this.mValue;
            }
        }
        return obj;
    }

    public boolean cancel(boolean z) {
        if (!z) {
            return false;
        }
        synchronized (this.mLock) {
            if (zzaay()) {
                return false;
            }
            this.zzdnk = true;
            this.zzepw = true;
            this.mLock.notifyAll();
            this.zzepx.zzaaw();
            return true;
        }
    }

    public boolean isCancelled() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzdnk;
        }
        return z;
    }

    public boolean isDone() {
        boolean zzaay;
        synchronized (this.mLock) {
            zzaay = zzaay();
        }
        return zzaay;
    }

    private final boolean zzaay() {
        return this.zzepv != null || this.zzepw;
    }
}
