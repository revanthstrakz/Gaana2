package com.facebook.ads.internal.r.a;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;

public abstract class b {
    public abstract void a(n nVar);

    public void a(Exception exception) {
        ThrowableExtension.printStackTrace(exception);
    }
}
