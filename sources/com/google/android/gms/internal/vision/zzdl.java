package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzcz.zzc.zzb;
import java.io.IOException;

public final class zzdl extends zzjn<zzdl> {
    private String url;
    private Boolean zzoi;
    private zzb zzoj;
    private Long zzok;
    private Long zzol;
    private Long zzom;
    private String zzon;

    public zzdl() {
        this.url = null;
        this.zzoi = null;
        this.zzok = null;
        this.zzol = null;
        this.zzom = null;
        this.zzon = null;
        this.zzadp = -1;
    }

    public final void zza(zzjl zzjl) throws IOException {
        if (this.url != null) {
            zzjl.zza(1, this.url);
        }
        if (this.zzoi != null) {
            zzjl.zzb(2, this.zzoi.booleanValue());
        }
        if (!(this.zzoj == null || this.zzoj == null)) {
            zzjl.zze(3, this.zzoj.zzr());
        }
        if (this.zzok != null) {
            zzjl.zzi(4, this.zzok.longValue());
        }
        if (this.zzol != null) {
            zzjl.zzi(5, this.zzol.longValue());
        }
        if (this.zzom != null) {
            zzjl.zzi(6, this.zzom.longValue());
        }
        if (this.zzon != null) {
            zzjl.zza(7, this.zzon);
        }
        super.zza(zzjl);
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzt() {
        int zzt = super.zzt();
        if (this.url != null) {
            zzt += zzjl.zzb(1, this.url);
        }
        if (this.zzoi != null) {
            this.zzoi.booleanValue();
            zzt += zzjl.zzav(2) + 1;
        }
        if (!(this.zzoj == null || this.zzoj == null)) {
            zzt += zzjl.zzi(3, this.zzoj.zzr());
        }
        if (this.zzok != null) {
            zzt += zzjl.zzd(4, this.zzok.longValue());
        }
        if (this.zzol != null) {
            zzt += zzjl.zzd(5, this.zzol.longValue());
        }
        if (this.zzom != null) {
            zzt += zzjl.zzd(6, this.zzom.longValue());
        }
        return this.zzon != null ? zzt + zzjl.zzb(7, this.zzon) : zzt;
    }
}
