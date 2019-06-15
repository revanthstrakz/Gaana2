package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;

@zzark
public final class zzakd {
    @VisibleForTesting
    private static final zzazn<zzaii> zzdke = new zzake();
    @VisibleForTesting
    private static final zzazn<zzaii> zzdkf = new zzakf();
    private final zzait zzdkg;

    public zzakd(Context context, zzbbi zzbbi, String str) {
        this.zzdkg = new zzait(context, zzbbi, str, zzdke, zzdkf);
    }

    public final <I, O> zzajv<I, O> zza(String str, zzajy<I> zzajy, zzajx<O> zzajx) {
        return new zzakg(this.zzdkg, str, zzajy, zzajx);
    }
}
