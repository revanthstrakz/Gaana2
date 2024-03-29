package com.google.android.gms.common.internal;

import android.support.annotation.NonNull;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;

public final class zzh {
    @NonNull
    private final String mPackageName;
    private final int zzdt = TsExtractor.TS_STREAM_TYPE_AC3;
    @NonNull
    private final String zzej;
    private final boolean zzek;

    public zzh(@NonNull String str, @NonNull String str2, boolean z, int i) {
        this.mPackageName = str;
        this.zzej = str2;
        this.zzek = z;
    }

    /* Access modifiers changed, original: final */
    @NonNull
    public final String zzu() {
        return this.zzej;
    }

    /* Access modifiers changed, original: final */
    @NonNull
    public final String getPackageName() {
        return this.mPackageName;
    }

    /* Access modifiers changed, original: final */
    public final int zzq() {
        return this.zzdt;
    }
}
