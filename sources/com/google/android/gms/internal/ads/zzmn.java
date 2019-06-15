package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C;
import java.io.IOException;

public final class zzmn extends zzme {
    private volatile boolean zzaxq;
    private final zzmf zzbat;
    private volatile int zzbau;

    public zzmn(zzov zzov, zzoz zzoz, zzfs zzfs, int i, Object obj, zzmf zzmf) {
        super(zzov, zzoz, 2, zzfs, i, obj, C.TIME_UNSET, C.TIME_UNSET);
        this.zzbat = zzmf;
    }

    public final long zzfv() {
        return (long) this.zzbau;
    }

    public final void cancelLoad() {
        this.zzaxq = true;
    }

    public final boolean zzfe() {
        return this.zzaxq;
    }

    public final void zzff() throws IOException, InterruptedException {
        zzoz zza = zzqe.zza(this.zzazo, this.zzbau);
        zzhx zzhx;
        try {
            zzhx = new zzhx(this.zzagy, zza.zzbfu, this.zzagy.zza(zza));
            if (this.zzbau == 0) {
                this.zzbat.zza(null);
            }
            zzhz zzhz = this.zzbat.zzaxu;
            int i = 0;
            while (i == 0 && !this.zzaxq) {
                i = zzhz.zza(zzhx, null);
            }
            boolean z = true;
            if (i == 1) {
                z = false;
            }
            zzpo.checkState(z);
            this.zzbau = (int) (zzhx.getPosition() - this.zzazo.zzbfu);
            zzqe.zza(this.zzagy);
        } catch (Throwable th) {
            zzqe.zza(this.zzagy);
        }
    }
}
