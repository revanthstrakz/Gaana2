package com.google.android.gms.internal.ads;

final class zzna {
    public final boolean zzbcb;
    public final long zzbcc;
    public final long zzbcd;

    public static zzna zza(zznn zznn, long j) {
        zznn zznn2 = zznn;
        long j2 = j;
        int size = zznn2.zzbbe.size();
        int i = 0;
        int i2 = 0;
        long j3 = Long.MAX_VALUE;
        long j4 = 0;
        int i3 = i2;
        int i4 = i3;
        while (i3 < size) {
            zznd zzgj = ((zznp) ((zzni) zznn2.zzbbe.get(i3)).zzbcn.get(i)).zzgj();
            if (zzgj == null) {
                return new zzna(true, 0, j2);
            }
            int i5;
            i2 |= zzgj.zzge();
            int zzai = zzgj.zzai(j2);
            if (zzai == 0) {
                i5 = i3;
                i4 = 1;
                j4 = 0;
                j3 = 0;
            } else if (i4 == 0) {
                int zzgd = zzgj.zzgd();
                i5 = i3;
                long max = Math.max(j4, zzgj.zzaw(zzgd));
                if (zzai != -1) {
                    zzgd = (zzgd + zzai) - 1;
                    j3 = Math.min(j3, zzgj.zzaw(zzgd) + zzgj.zze(zzgd, j2));
                }
                j4 = max;
            } else {
                i5 = i3;
            }
            i3 = i5 + 1;
            i = 0;
        }
        return new zzna(i2, j4, j3);
    }

    private zzna(boolean z, long j, long j2) {
        this.zzbcb = z;
        this.zzbcc = j;
        this.zzbcd = j2;
    }
}
