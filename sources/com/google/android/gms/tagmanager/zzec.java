package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
final class zzec implements zzfw {
    private final /* synthetic */ zzeb zzbdv;

    zzec(zzeb zzeb) {
        this.zzbdv = zzeb;
    }

    public final void zza(zzbw zzbw) {
        this.zzbdv.zze(zzbw.zzov());
    }

    public final void zzb(zzbw zzbw) {
        this.zzbdv.zze(zzbw.zzov());
        long zzov = zzbw.zzov();
        StringBuilder stringBuilder = new StringBuilder(57);
        stringBuilder.append("Permanent failure dispatching hitId: ");
        stringBuilder.append(zzov);
        zzdi.v(stringBuilder.toString());
    }

    public final void zzc(zzbw zzbw) {
        long zzow = zzbw.zzow();
        if (zzow == 0) {
            this.zzbdv.zze(zzbw.zzov(), this.zzbdv.zzrz.currentTimeMillis());
            return;
        }
        if (zzow + 14400000 < this.zzbdv.zzrz.currentTimeMillis()) {
            this.zzbdv.zze(zzbw.zzov());
            zzow = zzbw.zzov();
            StringBuilder stringBuilder = new StringBuilder(47);
            stringBuilder.append("Giving up on failed hitId: ");
            stringBuilder.append(zzow);
            zzdi.v(stringBuilder.toString());
        }
    }
}
