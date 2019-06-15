package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzcz.zzd.zzb;
import java.io.IOException;

public final class zzdm extends zzjn<zzdm> {
    private static volatile zzdm[] zzoo;
    public String name;
    private String zzop;
    private String[] zzoq;
    private zzb zzor;
    public String zzos;
    public Long zzot;
    public Long zzou;
    public zzdt[] zzov;

    public static zzdm[] zzcb() {
        if (zzoo == null) {
            synchronized (zzjr.zzado) {
                if (zzoo == null) {
                    zzoo = new zzdm[0];
                }
            }
        }
        return zzoo;
    }

    public zzdm() {
        this.name = null;
        this.zzop = null;
        this.zzoq = zzjw.zzady;
        this.zzos = null;
        this.zzot = null;
        this.zzou = null;
        this.zzov = zzdt.zzcd();
        this.zzadp = -1;
    }

    public final void zza(zzjl zzjl) throws IOException {
        if (this.name != null) {
            zzjl.zza(1, this.name);
        }
        if (this.zzop != null) {
            zzjl.zza(2, this.zzop);
        }
        int i = 0;
        if (this.zzoq != null && this.zzoq.length > 0) {
            for (String str : this.zzoq) {
                if (str != null) {
                    zzjl.zza(3, str);
                }
            }
        }
        if (!(this.zzor == null || this.zzor == null)) {
            zzjl.zze(4, this.zzor.zzr());
        }
        if (this.zzos != null) {
            zzjl.zza(5, this.zzos);
        }
        if (this.zzot != null) {
            zzjl.zzi(6, this.zzot.longValue());
        }
        if (this.zzou != null) {
            zzjl.zzi(7, this.zzou.longValue());
        }
        if (this.zzov != null && this.zzov.length > 0) {
            while (i < this.zzov.length) {
                zzjt zzjt = this.zzov[i];
                if (zzjt != null) {
                    zzjl.zza(8, zzjt);
                }
                i++;
            }
        }
        super.zza(zzjl);
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzt() {
        int zzt = super.zzt();
        if (this.name != null) {
            zzt += zzjl.zzb(1, this.name);
        }
        if (this.zzop != null) {
            zzt += zzjl.zzb(2, this.zzop);
        }
        int i = 0;
        if (this.zzoq != null && this.zzoq.length > 0) {
            int i2 = 0;
            int i3 = i2;
            int i4 = i3;
            while (i2 < this.zzoq.length) {
                String str = this.zzoq[i2];
                if (str != null) {
                    i4++;
                    i3 += zzjl.zzn(str);
                }
                i2++;
            }
            zzt = (zzt + i3) + (1 * i4);
        }
        if (!(this.zzor == null || this.zzor == null)) {
            zzt += zzjl.zzi(4, this.zzor.zzr());
        }
        if (this.zzos != null) {
            zzt += zzjl.zzb(5, this.zzos);
        }
        if (this.zzot != null) {
            zzt += zzjl.zzd(6, this.zzot.longValue());
        }
        if (this.zzou != null) {
            zzt += zzjl.zzd(7, this.zzou.longValue());
        }
        if (this.zzov != null && this.zzov.length > 0) {
            while (i < this.zzov.length) {
                zzjt zzjt = this.zzov[i];
                if (zzjt != null) {
                    zzt += zzjl.zzb(8, zzjt);
                }
                i++;
            }
        }
        return zzt;
    }
}
