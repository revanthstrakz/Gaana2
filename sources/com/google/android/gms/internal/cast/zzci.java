package com.google.android.gms.internal.cast;

import com.google.android.gms.common.api.Status;

final class zzci implements zzec {
    private final /* synthetic */ zzcb zzwo;
    private final /* synthetic */ zzch zzwp;

    zzci(zzch zzch, zzcb zzcb) {
        this.zzwp = zzch;
        this.zzwo = zzcb;
    }

    public final void zzd(long j) {
        this.zzwp.setResult(zzch.zzb(new Status(2103)));
    }

    public final void zza(long j, int i, Object obj) {
        if (obj == null) {
            try {
                this.zzwp.setResult(new zzcn(new Status(i, null, null), null, j, null));
                return;
            } catch (ClassCastException unused) {
                this.zzwp.setResult(zzch.zzb(new Status(13)));
                return;
            }
        }
        zzcp zzcp = (zzcp) obj;
        String str = zzcp.zzxh;
        if (i == 0 && str != null) {
            this.zzwp.zzwi.zzwh = str;
        }
        this.zzwp.setResult(new zzcn(new Status(i, zzcp.zzxa, null), str, zzcp.zzxi, zzcp.zzxb));
    }
}
