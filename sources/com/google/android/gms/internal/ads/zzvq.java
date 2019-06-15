package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzvq extends zzbut<zzvq> {
    public Integer zzchy;
    public Integer zzchz;
    public Integer zzcia;

    public zzvq() {
        this.zzchy = null;
        this.zzchz = null;
        this.zzcia = null;
        this.zzfwk = null;
        this.zzfwt = -1;
    }

    public final void zza(zzbur zzbur) throws IOException {
        if (this.zzchy != null) {
            zzbur.zzv(1, this.zzchy.intValue());
        }
        if (this.zzchz != null) {
            zzbur.zzv(2, this.zzchz.intValue());
        }
        if (this.zzcia != null) {
            zzbur.zzv(3, this.zzcia.intValue());
        }
        super.zza(zzbur);
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzt() {
        int zzt = super.zzt();
        if (this.zzchy != null) {
            zzt += zzbur.zzz(1, this.zzchy.intValue());
        }
        if (this.zzchz != null) {
            zzt += zzbur.zzz(2, this.zzchz.intValue());
        }
        return this.zzcia != null ? zzt + zzbur.zzz(3, this.zzcia.intValue()) : zzt;
    }
}
