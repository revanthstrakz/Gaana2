package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.VisibleForTesting;

@zzark
final class zzaht {
    private static final zzahq zzdhy = zzahq.zzto();
    private static final float zzdhz = ((Float) zzwu.zzpz().zzd(zzaan.zzcsl)).floatValue();
    private static final long zzdia = ((Long) zzwu.zzpz().zzd(zzaan.zzcsj)).longValue();
    private static final float zzdib = ((Float) zzwu.zzpz().zzd(zzaan.zzcsm)).floatValue();
    private static final long zzdic = ((Long) zzwu.zzpz().zzd(zzaan.zzcsk)).longValue();

    static boolean zztz() {
        int zztv = zzdhy.zztv();
        int zztw = zzdhy.zztw();
        int zztu = zzdhy.zztu() + zzdhy.zztt();
        int i = Integer.MAX_VALUE;
        int zzc = (zztv >= 16 || zzdic == 0) ? zzdib != 0.0f ? ((int) (zzdib * ((float) zztv))) + 1 : Integer.MAX_VALUE : zzc(zzdic, zztv);
        if (zztw <= zzc) {
            if (zztv < 16 && zzdia != 0) {
                i = zzc(zzdia, zztv);
            } else if (zzdhz != 0.0f) {
                i = (int) (zzdhz * ((float) zztv));
            }
            if (zztu <= i) {
                return true;
            }
        }
        return false;
    }

    @VisibleForTesting
    private static int zzc(long j, int i) {
        return (int) ((j >>> (4 * (i % 16))) & 15);
    }
}
