package com.google.android.gms.ads.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzayh;
import com.google.android.gms.internal.ads.zzbbd;
import com.google.android.gms.internal.ads.zzwb;
import java.lang.ref.WeakReference;

@zzark
public final class zzbl {
    private final zzbn zzbqn;
    @Nullable
    private zzwb zzbqo;
    private boolean zzbqp;
    private boolean zzbqq;
    private long zzbqr;
    private final Runnable zzy;

    public zzbl(zza zza) {
        this(zza, new zzbn(zzayh.zzelc));
    }

    @VisibleForTesting
    private zzbl(zza zza, zzbn zzbn) {
        this.zzbqp = false;
        this.zzbqq = false;
        this.zzbqr = 0;
        this.zzbqn = zzbn;
        this.zzy = new zzbm(this, new WeakReference(zza));
    }

    public final void zzf(zzwb zzwb) {
        this.zzbqo = zzwb;
    }

    public final void cancel() {
        this.zzbqp = false;
        this.zzbqn.removeCallbacks(this.zzy);
    }

    public final void pause() {
        this.zzbqq = true;
        if (this.zzbqp) {
            this.zzbqn.removeCallbacks(this.zzy);
        }
    }

    public final void resume() {
        this.zzbqq = false;
        if (this.zzbqp) {
            this.zzbqp = false;
            zza(this.zzbqo, this.zzbqr);
        }
    }

    public final void zzku() {
        this.zzbqq = false;
        this.zzbqp = false;
        if (!(this.zzbqo == null || this.zzbqo.extras == null)) {
            this.zzbqo.extras.remove("_ad");
        }
        zza(this.zzbqo, 0);
    }

    public final boolean zzkv() {
        return this.zzbqp;
    }

    public final void zzg(zzwb zzwb) {
        zza(zzwb, 60000);
    }

    public final void zza(zzwb zzwb, long j) {
        if (this.zzbqp) {
            zzbbd.zzeo("An ad refresh is already scheduled.");
            return;
        }
        this.zzbqo = zzwb;
        this.zzbqp = true;
        this.zzbqr = j;
        if (!this.zzbqq) {
            StringBuilder stringBuilder = new StringBuilder(65);
            stringBuilder.append("Scheduling ad refresh ");
            stringBuilder.append(j);
            stringBuilder.append(" milliseconds from now.");
            zzbbd.zzen(stringBuilder.toString());
            this.zzbqn.postDelayed(this.zzy, j);
        }
    }
}
