package com.exoplayer2.upstream;

import android.support.annotation.Nullable;
import com.google.android.exoplayer2.upstream.HttpDataSource.BaseFactory;
import com.google.android.exoplayer2.upstream.HttpDataSource.RequestProperties;
import com.google.android.exoplayer2.upstream.TransferListener;

public final class d extends BaseFactory {
    private final String a;
    @Nullable
    private final TransferListener b;
    private final int c;
    private final int d;
    private final boolean e;
    private final boolean f;

    public d(String str, @Nullable TransferListener transferListener, boolean z) {
        this(str, transferListener, 8000, 8000, false, z);
    }

    public d(String str, @Nullable TransferListener transferListener, int i, int i2, boolean z, boolean z2) {
        this.a = str;
        this.b = transferListener;
        this.c = i;
        this.d = i2;
        this.e = z;
        this.f = z2;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public c createDataSourceInternal(RequestProperties requestProperties) {
        c cVar = new c(this.a, null, this.c, this.d, this.e, requestProperties, this.f);
        if (this.b != null) {
            cVar.addTransferListener(this.b);
        }
        return cVar;
    }
}
