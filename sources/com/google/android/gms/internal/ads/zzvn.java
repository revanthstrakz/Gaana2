package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzuw.zzn;
import java.io.IOException;

public final class zzvn extends zzbut<zzvn> {
    public String zzchc;
    private zzn zzchd;
    private Integer zzche;
    public zzvq zzchf;
    private Integer zzchg;
    private zzvc zzchh;
    private zzvc zzchi;
    private zzvc zzchj;

    public zzvn() {
        this.zzchc = null;
        this.zzchd = null;
        this.zzche = null;
        this.zzchf = null;
        this.zzchg = null;
        this.zzchh = null;
        this.zzchi = null;
        this.zzchj = null;
        this.zzfwk = null;
        this.zzfwt = -1;
    }

    public final void zza(zzbur zzbur) throws IOException {
        if (this.zzchc != null) {
            zzbur.zzf(1, this.zzchc);
        }
        if (this.zzchd != null) {
            zzbur.zze(2, this.zzchd);
        }
        if (this.zzche != null) {
            zzbur.zzv(3, this.zzche.intValue());
        }
        if (this.zzchf != null) {
            zzbur.zza(4, this.zzchf);
        }
        if (this.zzchg != null) {
            zzbur.zzv(5, this.zzchg.intValue());
        }
        if (!(this.zzchh == null || this.zzchh == null)) {
            zzbur.zzv(6, this.zzchh.zzom());
        }
        if (!(this.zzchi == null || this.zzchi == null)) {
            zzbur.zzv(7, this.zzchi.zzom());
        }
        if (!(this.zzchj == null || this.zzchj == null)) {
            zzbur.zzv(8, this.zzchj.zzom());
        }
        super.zza(zzbur);
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzt() {
        int zzt = super.zzt();
        if (this.zzchc != null) {
            zzt += zzbur.zzg(1, this.zzchc);
        }
        if (this.zzchd != null) {
            zzt += zzbqk.zzc(2, this.zzchd);
        }
        if (this.zzche != null) {
            zzt += zzbur.zzz(3, this.zzche.intValue());
        }
        if (this.zzchf != null) {
            zzt += zzbur.zzb(4, this.zzchf);
        }
        if (this.zzchg != null) {
            zzt += zzbur.zzz(5, this.zzchg.intValue());
        }
        if (!(this.zzchh == null || this.zzchh == null)) {
            zzt += zzbur.zzz(6, this.zzchh.zzom());
        }
        if (!(this.zzchi == null || this.zzchi == null)) {
            zzt += zzbur.zzz(7, this.zzchi.zzom());
        }
        return (this.zzchj == null || this.zzchj == null) ? zzt : zzt + zzbur.zzz(8, this.zzchj.zzom());
    }
}
