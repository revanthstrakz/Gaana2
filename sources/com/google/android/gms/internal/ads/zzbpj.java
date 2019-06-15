package com.google.android.gms.internal.ads;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.PrintWriter;

final class zzbpj extends zzbpf {
    zzbpj() {
    }

    public final void zze(Throwable th) {
        ThrowableExtension.printStackTrace(th);
    }

    public final void zza(Throwable th, PrintWriter printWriter) {
        ThrowableExtension.printStackTrace(th, printWriter);
    }
}
