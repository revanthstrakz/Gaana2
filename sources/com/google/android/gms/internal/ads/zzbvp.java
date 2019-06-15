package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbvp extends zzbut<zzbvp> {
    private static volatile zzbvp[] zzgbi;
    public byte[] zzgbj;
    public byte[] zzgbk;

    public static zzbvp[] zzaqd() {
        if (zzgbi == null) {
            synchronized (zzbux.zzfws) {
                if (zzgbi == null) {
                    zzgbi = new zzbvp[0];
                }
            }
        }
        return zzgbi;
    }

    public zzbvp() {
        this.zzgbj = null;
        this.zzgbk = null;
        this.zzfwk = null;
        this.zzfwt = -1;
    }

    public final void zza(zzbur zzbur) throws IOException {
        zzbur.zza(1, this.zzgbj);
        if (this.zzgbk != null) {
            zzbur.zza(2, this.zzgbk);
        }
        super.zza(zzbur);
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzt() {
        int zzt = super.zzt() + zzbur.zzb(1, this.zzgbj);
        return this.zzgbk != null ? zzt + zzbur.zzb(2, this.zzgbk) : zzt;
    }
}
