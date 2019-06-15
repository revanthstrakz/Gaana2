package com.google.android.gms.internal.ads;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.PrintWriter;
import java.util.List;

final class zzbpi extends zzbpf {
    private final zzbpg zzfkt = new zzbpg();

    zzbpi() {
    }

    public final void zze(Throwable th) {
        ThrowableExtension.printStackTrace(th);
        List<Throwable> zza = this.zzfkt.zza(th, false);
        if (zza != null) {
            synchronized (zza) {
                for (Throwable th2 : zza) {
                    System.err.print("Suppressed: ");
                    ThrowableExtension.printStackTrace(th2);
                }
            }
        }
    }

    public final void zza(Throwable th, PrintWriter printWriter) {
        ThrowableExtension.printStackTrace(th, printWriter);
        List<Throwable> zza = this.zzfkt.zza(th, false);
        if (zza != null) {
            synchronized (zza) {
                for (Throwable th2 : zza) {
                    printWriter.print("Suppressed: ");
                    ThrowableExtension.printStackTrace(th2, printWriter);
                }
            }
        }
    }
}
