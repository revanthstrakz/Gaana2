package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbp extends zzbut<zzbp> {
    public byte[] data;
    public byte[] zzhm;
    public byte[] zzhn;
    public byte[] zzho;

    public zzbp() {
        this.data = null;
        this.zzhm = null;
        this.zzhn = null;
        this.zzho = null;
        this.zzfwt = -1;
    }

    public final void zza(zzbur zzbur) throws IOException {
        if (this.data != null) {
            zzbur.zza(1, this.data);
        }
        if (this.zzhm != null) {
            zzbur.zza(2, this.zzhm);
        }
        if (this.zzhn != null) {
            zzbur.zza(3, this.zzhn);
        }
        if (this.zzho != null) {
            zzbur.zza(4, this.zzho);
        }
        super.zza(zzbur);
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzt() {
        int zzt = super.zzt();
        if (this.data != null) {
            zzt += zzbur.zzb(1, this.data);
        }
        if (this.zzhm != null) {
            zzt += zzbur.zzb(2, this.zzhm);
        }
        if (this.zzhn != null) {
            zzt += zzbur.zzb(3, this.zzhn);
        }
        return this.zzho != null ? zzt + zzbur.zzb(4, this.zzho) : zzt;
    }
}
