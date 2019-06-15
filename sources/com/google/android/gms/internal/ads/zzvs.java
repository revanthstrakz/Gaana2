package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzuw.zzn;
import com.google.android.gms.internal.ads.zzuw.zzq;
import java.io.IOException;

public final class zzvs extends zzbut<zzvs> {
    private zzvq zzcis;
    private zzvc zzcit;
    private zzq zzciu;
    private zzn zzciv;

    public zzvs() {
        this.zzcis = null;
        this.zzcit = null;
        this.zzciu = null;
        this.zzciv = null;
        this.zzfwk = null;
        this.zzfwt = -1;
    }

    public final void zza(zzbur zzbur) throws IOException {
        if (this.zzcis != null) {
            zzbur.zza(1, this.zzcis);
        }
        if (!(this.zzcit == null || this.zzcit == null)) {
            zzbur.zzv(2, this.zzcit.zzom());
        }
        if (this.zzciu != null) {
            zzbur.zze(3, this.zzciu);
        }
        if (this.zzciv != null) {
            zzbur.zze(4, this.zzciv);
        }
        super.zza(zzbur);
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzt() {
        int zzt = super.zzt();
        if (this.zzcis != null) {
            zzt += zzbur.zzb(1, this.zzcis);
        }
        if (!(this.zzcit == null || this.zzcit == null)) {
            zzt += zzbur.zzz(2, this.zzcit.zzom());
        }
        if (this.zzciu != null) {
            zzt += zzbqk.zzc(3, this.zzciu);
        }
        return this.zzciv != null ? zzt + zzbqk.zzc(4, this.zzciv) : zzt;
    }
}
