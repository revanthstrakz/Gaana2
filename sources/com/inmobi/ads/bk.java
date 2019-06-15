package com.inmobi.ads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.inmobi.ads.c.k;

class bk extends ce {
    private static final String d = "bk";
    @Nullable
    private k e;

    bk(@NonNull a aVar, @Nullable k kVar) {
        super(aVar);
        this.e = kVar;
    }

    /* Access modifiers changed, original: protected|final */
    public final int a() {
        return this.e == null ? 100 : this.e.c;
    }

    /* Access modifiers changed, original: protected|final */
    public final void b() {
        g();
    }
}
