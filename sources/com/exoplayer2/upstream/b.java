package com.exoplayer2.upstream;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.upstream.DataSource.Factory;
import com.google.android.exoplayer2.upstream.TransferListener;

public final class b implements Factory {
    private final Context a;
    @Nullable
    private final TransferListener b;
    private final Factory c;
    private final boolean d;

    public b(Context context, @Nullable TransferListener transferListener, Factory factory) {
        this(context, transferListener, factory, false);
    }

    public b(Context context, @Nullable TransferListener transferListener, Factory factory, boolean z) {
        this.a = context.getApplicationContext();
        this.b = transferListener;
        this.c = factory;
        this.d = z;
    }

    /* renamed from: a */
    public a createDataSource() {
        a aVar = new a(this.a, this.c.createDataSource(), this.d);
        if (this.b != null) {
            aVar.addTransferListener(this.b);
        }
        return aVar;
    }
}
