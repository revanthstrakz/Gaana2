package com.google.android.gms.internal.vision;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.List;

final class zzcx extends zzcu {
    private final zzcv zzlw = new zzcv();

    zzcx() {
    }

    public final void zza(Throwable th) {
        ThrowableExtension.printStackTrace(th);
        List<Throwable> zza = this.zzlw.zza(th, false);
        if (zza != null) {
            synchronized (zza) {
                for (Throwable th2 : zza) {
                    System.err.print("Suppressed: ");
                    ThrowableExtension.printStackTrace(th2);
                }
            }
        }
    }
}
