package com.facebook.ads.internal.a;

import android.content.Context;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.l.a.a;
import com.facebook.ads.internal.o.c;

public abstract class b {
    protected final Context a;
    protected final c b;
    protected final String c;

    public b(Context context, c cVar, String str) {
        this.a = context;
        this.b = cVar;
        this.c = str;
    }

    @Deprecated
    public abstract a a();

    public abstract void b();

    @Nullable
    public a c() {
        return null;
    }
}
