package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbvo extends zzbut<zzbvo> {
    public String zzegh;

    public zzbvo() {
        this.zzegh = null;
        this.zzfwk = null;
        this.zzfwt = -1;
    }

    public final void zza(zzbur zzbur) throws IOException {
        if (this.zzegh != null) {
            zzbur.zzf(1, this.zzegh);
        }
        super.zza(zzbur);
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzt() {
        int zzt = super.zzt();
        return this.zzegh != null ? zzt + zzbur.zzg(1, this.zzegh) : zzt;
    }
}
