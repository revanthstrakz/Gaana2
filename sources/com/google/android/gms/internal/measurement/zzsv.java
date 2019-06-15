package com.google.android.gms.internal.measurement;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.PrintStream;
import java.util.List;

final class zzsv extends zzss {
    private final zzst zzbsj = new zzst();

    zzsv() {
    }

    public final void zza(Throwable th, PrintStream printStream) {
        ThrowableExtension.printStackTrace(th, printStream);
        List<Throwable> zza = this.zzbsj.zza(th, false);
        if (zza != null) {
            synchronized (zza) {
                for (Throwable th2 : zza) {
                    printStream.print("Suppressed: ");
                    ThrowableExtension.printStackTrace(th2, printStream);
                }
            }
        }
    }
}
