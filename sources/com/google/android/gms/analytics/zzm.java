package com.google.android.gms.analytics;

import android.util.Log;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.FutureTask;

final class zzm extends FutureTask<T> {
    private final /* synthetic */ zza zzsu;

    zzm(zza zza, Runnable runnable, Object obj) {
        this.zzsu = zza;
        super(runnable, obj);
    }

    /* Access modifiers changed, original: protected|final */
    public final void setException(Throwable th) {
        UncaughtExceptionHandler zzb = zzk.this.zzsr;
        if (zzb != null) {
            zzb.uncaughtException(Thread.currentThread(), th);
        } else if (Log.isLoggable("GAv4", 6)) {
            String valueOf = String.valueOf(th);
            StringBuilder stringBuilder = new StringBuilder(37 + String.valueOf(valueOf).length());
            stringBuilder.append("MeasurementExecutor: job failed with ");
            stringBuilder.append(valueOf);
            Log.e("GAv4", stringBuilder.toString());
        }
        super.setException(th);
    }
}
