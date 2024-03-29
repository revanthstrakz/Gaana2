package com.google.android.gms.analytics;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.List;

public class zzj<T extends zzj> {
    private final zzk zzsj;
    protected final zzg zzsk;
    private final List<zzh> zzsl = new ArrayList();

    @VisibleForTesting
    protected zzj(zzk zzk, Clock clock) {
        Preconditions.checkNotNull(zzk);
        this.zzsj = zzk;
        zzg zzg = new zzg(this, clock);
        zzg.zzab();
        this.zzsk = zzg;
    }

    /* Access modifiers changed, original: protected */
    public void zza(zzg zzg) {
    }

    public zzg zzm() {
        zzg zzs = this.zzsk.zzs();
        zzd(zzs);
        return zzs;
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzd(zzg zzg) {
        for (zzh zza : this.zzsl) {
            zza.zza(this, zzg);
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final zzk zzac() {
        return this.zzsj;
    }
}
