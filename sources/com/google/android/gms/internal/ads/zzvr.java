package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzuw.zzn;
import com.google.android.gms.internal.ads.zzuw.zzp;
import com.google.android.gms.internal.ads.zzuw.zzr;
import com.google.android.gms.internal.ads.zzuw.zzs;
import com.google.android.gms.internal.ads.zzuw.zzt;
import com.google.android.gms.internal.ads.zzuw.zzu;
import com.google.android.gms.internal.ads.zzuw.zzv;
import java.io.IOException;

public final class zzvr extends zzbut<zzvr> {
    private static volatile zzvr[] zzcib;
    private zzr zzcic;
    private zzt zzcid;
    private zzu zzcie;
    private zzv zzcif;
    private zzp zzcig;
    private zzs zzcih;
    private zzvs zzcii;
    private Integer zzcij;
    private Integer zzcik;
    private zzn zzcil;
    private Integer zzcim;
    private Integer zzcin;
    private Integer zzcio;
    private Integer zzcip;
    private Integer zzciq;
    private Long zzcir;

    public static zzvr[] zzpl() {
        if (zzcib == null) {
            synchronized (zzbux.zzfws) {
                if (zzcib == null) {
                    zzcib = new zzvr[0];
                }
            }
        }
        return zzcib;
    }

    public zzvr() {
        this.zzcic = null;
        this.zzcid = null;
        this.zzcie = null;
        this.zzcif = null;
        this.zzcig = null;
        this.zzcih = null;
        this.zzcii = null;
        this.zzcij = null;
        this.zzcik = null;
        this.zzcil = null;
        this.zzcim = null;
        this.zzcin = null;
        this.zzcio = null;
        this.zzcip = null;
        this.zzciq = null;
        this.zzcir = null;
        this.zzfwk = null;
        this.zzfwt = -1;
    }

    public final void zza(zzbur zzbur) throws IOException {
        if (this.zzcic != null) {
            zzbur.zze(5, this.zzcic);
        }
        if (this.zzcid != null) {
            zzbur.zze(6, this.zzcid);
        }
        if (this.zzcie != null) {
            zzbur.zze(7, this.zzcie);
        }
        if (this.zzcif != null) {
            zzbur.zze(8, this.zzcif);
        }
        if (this.zzcig != null) {
            zzbur.zze(9, this.zzcig);
        }
        if (this.zzcih != null) {
            zzbur.zze(10, this.zzcih);
        }
        if (this.zzcii != null) {
            zzbur.zza(11, this.zzcii);
        }
        if (this.zzcij != null) {
            zzbur.zzv(12, this.zzcij.intValue());
        }
        if (this.zzcik != null) {
            zzbur.zzv(13, this.zzcik.intValue());
        }
        if (this.zzcil != null) {
            zzbur.zze(14, this.zzcil);
        }
        if (this.zzcim != null) {
            zzbur.zzv(15, this.zzcim.intValue());
        }
        if (this.zzcin != null) {
            zzbur.zzv(16, this.zzcin.intValue());
        }
        if (this.zzcio != null) {
            zzbur.zzv(17, this.zzcio.intValue());
        }
        if (this.zzcip != null) {
            zzbur.zzv(18, this.zzcip.intValue());
        }
        if (this.zzciq != null) {
            zzbur.zzv(19, this.zzciq.intValue());
        }
        if (this.zzcir != null) {
            zzbur.zzj(20, this.zzcir.longValue());
        }
        super.zza(zzbur);
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzt() {
        int zzt = super.zzt();
        if (this.zzcic != null) {
            zzt += zzbqk.zzc(5, this.zzcic);
        }
        if (this.zzcid != null) {
            zzt += zzbqk.zzc(6, this.zzcid);
        }
        if (this.zzcie != null) {
            zzt += zzbqk.zzc(7, this.zzcie);
        }
        if (this.zzcif != null) {
            zzt += zzbqk.zzc(8, this.zzcif);
        }
        if (this.zzcig != null) {
            zzt += zzbqk.zzc(9, this.zzcig);
        }
        if (this.zzcih != null) {
            zzt += zzbqk.zzc(10, this.zzcih);
        }
        if (this.zzcii != null) {
            zzt += zzbur.zzb(11, this.zzcii);
        }
        if (this.zzcij != null) {
            zzt += zzbur.zzz(12, this.zzcij.intValue());
        }
        if (this.zzcik != null) {
            zzt += zzbur.zzz(13, this.zzcik.intValue());
        }
        if (this.zzcil != null) {
            zzt += zzbqk.zzc(14, this.zzcil);
        }
        if (this.zzcim != null) {
            zzt += zzbur.zzz(15, this.zzcim.intValue());
        }
        if (this.zzcin != null) {
            zzt += zzbur.zzz(16, this.zzcin.intValue());
        }
        if (this.zzcio != null) {
            zzt += zzbur.zzz(17, this.zzcio.intValue());
        }
        if (this.zzcip != null) {
            zzt += zzbur.zzz(18, this.zzcip.intValue());
        }
        if (this.zzciq != null) {
            zzt += zzbur.zzz(19, this.zzciq.intValue());
        }
        return this.zzcir != null ? zzt + zzbur.zzn(20, this.zzcir.longValue()) : zzt;
    }
}
