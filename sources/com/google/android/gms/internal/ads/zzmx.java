package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C;

final class zzmx extends zzgc {
    private final long zzaaq;
    private final long zzaar;
    private final long zzazf;
    private final long zzazh;
    private final zznj zzbbd;
    private final int zzbby;
    private final long zzbca;

    public zzmx(long j, long j2, int i, long j3, long j4, long j5, zznj zznj) {
        this.zzaaq = j;
        this.zzaar = j2;
        this.zzbby = i;
        this.zzbca = j3;
        this.zzazf = j4;
        this.zzazh = j5;
        this.zzbbd = zznj;
    }

    public final int zzck() {
        return 1;
    }

    public final int zzcl() {
        return this.zzbbd.zzcl();
    }

    public final zzge zza(int i, zzge zzge, boolean z) {
        zzpo.zzc(i, 0, this.zzbbd.zzcl());
        Integer num = null;
        Object obj = z ? this.zzbbd.zzba(i).zzze : null;
        if (z) {
            num = Integer.valueOf(this.zzbby + zzpo.zzc(i, 0, this.zzbbd.zzcl()));
        }
        return zzge.zza(obj, num, 0, this.zzbbd.zzbb(i), zzfe.zzg(this.zzbbd.zzba(i).zzbdj - this.zzbbd.zzba(0).zzbdj) - this.zzbca, false);
    }

    public final zzgf zza(int i, zzgf zzgf, boolean z, long j) {
        zzpo.zzc(i, 0, 1);
        long j2 = this.zzazh;
        if (this.zzbbd.zzbcs) {
            long j3;
            if (j > 0) {
                j3 = j2 + j;
                j2 = j3 > this.zzazf ? C.TIME_UNSET : j3;
            }
            j3 = this.zzbca + j2;
            long zzbb = this.zzbbd.zzbb(0);
            int i2 = 0;
            while (i2 < this.zzbbd.zzcl() - 1 && j3 >= zzbb) {
                long j4 = j3 - zzbb;
                i2++;
                zzbb = this.zzbbd.zzbb(i2);
                j3 = j4;
            }
            zznn zzba = this.zzbbd.zzba(i2);
            int size = zzba.zzbbe.size();
            int i3 = 0;
            while (i3 < size) {
                if (((zzni) zzba.zzbbe.get(i3)).type == 2) {
                    break;
                }
                i3++;
            }
            i3 = -1;
            if (i3 != -1) {
                zznd zzgj = ((zznp) ((zzni) zzba.zzbbe.get(i3)).zzbcn.get(0)).zzgj();
                if (!(zzgj == null || zzgj.zzai(zzbb) == 0)) {
                    j2 = (j2 + zzgj.zzaw(zzgj.zzf(j3, zzbb))) - j3;
                }
            }
        }
        return zzgf.zza(null, this.zzaaq, this.zzaar, true, this.zzbbd.zzbcs, j2, this.zzazf, 0, this.zzbbd.zzcl() - 1, this.zzbca);
    }

    public final int zzc(Object obj) {
        if (!(obj instanceof Integer)) {
            return -1;
        }
        int intValue = ((Integer) obj).intValue();
        if (intValue < this.zzbby || intValue >= this.zzbby + zzcl()) {
            return -1;
        }
        return intValue - this.zzbby;
    }
}
