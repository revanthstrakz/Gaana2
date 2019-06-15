package com.google.android.gms.internal.measurement;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.PrintStream;

final class zzsw extends zzss {
    zzsw() {
    }

    public final void zza(Throwable th, PrintStream printStream) {
        ThrowableExtension.printStackTrace(th, printStream);
    }
}
