package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import com.google.android.gms.internal.ads.zzuw.zza;
import com.google.android.gms.internal.ads.zzuw.zzb;
import com.google.android.gms.internal.ads.zzuw.zzc;
import com.google.android.gms.internal.ads.zzuw.zzf;
import com.google.android.gms.internal.ads.zzuw.zzg;
import com.google.android.gms.internal.ads.zzuw.zzi;
import com.google.android.gms.internal.ads.zzuw.zzj;
import java.io.IOException;

public final class zzvl extends zzbut<zzvl> {
    public Integer zzcgm;
    private zzvc zzcgn;
    private zzb zzcgo;
    public zzvm zzcgp;
    private zza[] zzcgq;
    private zzc zzcgr;
    private zzj zzcgs;
    private zzi zzcgt;
    private zzf zzcgu;
    private zzg zzcgv;
    private zzvr[] zzcgw;

    public zzvl() {
        this.zzcgm = null;
        this.zzcgn = null;
        this.zzcgo = null;
        this.zzcgp = null;
        this.zzcgq = new zza[0];
        this.zzcgr = null;
        this.zzcgs = null;
        this.zzcgt = null;
        this.zzcgu = null;
        this.zzcgv = null;
        this.zzcgw = zzvr.zzpl();
        this.zzfwk = null;
        this.zzfwt = -1;
    }

    public final void zza(zzbur zzbur) throws IOException {
        if (this.zzcgm != null) {
            zzbur.zzv(7, this.zzcgm.intValue());
        }
        if (!(this.zzcgn == null || this.zzcgn == null)) {
            zzbur.zzv(8, this.zzcgn.zzom());
        }
        if (this.zzcgo != null) {
            zzbur.zze(9, this.zzcgo);
        }
        if (this.zzcgp != null) {
            zzbur.zza(10, this.zzcgp);
        }
        int i = 0;
        if (this.zzcgq != null && this.zzcgq.length > 0) {
            for (zzbsl zzbsl : this.zzcgq) {
                if (zzbsl != null) {
                    zzbur.zze(11, zzbsl);
                }
            }
        }
        if (this.zzcgr != null) {
            zzbur.zze(12, this.zzcgr);
        }
        if (this.zzcgs != null) {
            zzbur.zze(13, this.zzcgs);
        }
        if (this.zzcgt != null) {
            zzbur.zze(14, this.zzcgt);
        }
        if (this.zzcgu != null) {
            zzbur.zze(15, this.zzcgu);
        }
        if (this.zzcgv != null) {
            zzbur.zze(16, this.zzcgv);
        }
        if (this.zzcgw != null && this.zzcgw.length > 0) {
            while (i < this.zzcgw.length) {
                zzbuz zzbuz = this.zzcgw[i];
                if (zzbuz != null) {
                    zzbur.zza(17, zzbuz);
                }
                i++;
            }
        }
        super.zza(zzbur);
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzt() {
        int zzt = super.zzt();
        if (this.zzcgm != null) {
            zzt += zzbur.zzz(7, this.zzcgm.intValue());
        }
        if (!(this.zzcgn == null || this.zzcgn == null)) {
            zzt += zzbur.zzz(8, this.zzcgn.zzom());
        }
        if (this.zzcgo != null) {
            zzt += zzbqk.zzc(9, this.zzcgo);
        }
        if (this.zzcgp != null) {
            zzt += zzbur.zzb(10, this.zzcgp);
        }
        int i = 0;
        if (this.zzcgq != null && this.zzcgq.length > 0) {
            int i2 = zzt;
            for (zzbsl zzbsl : this.zzcgq) {
                if (zzbsl != null) {
                    i2 += zzbqk.zzc(11, zzbsl);
                }
            }
            zzt = i2;
        }
        if (this.zzcgr != null) {
            zzt += zzbqk.zzc(12, this.zzcgr);
        }
        if (this.zzcgs != null) {
            zzt += zzbqk.zzc(13, this.zzcgs);
        }
        if (this.zzcgt != null) {
            zzt += zzbqk.zzc(14, this.zzcgt);
        }
        if (this.zzcgu != null) {
            zzt += zzbqk.zzc(15, this.zzcgu);
        }
        if (this.zzcgv != null) {
            zzt += zzbqk.zzc(16, this.zzcgv);
        }
        if (this.zzcgw != null && this.zzcgw.length > 0) {
            while (i < this.zzcgw.length) {
                zzbuz zzbuz = this.zzcgw[i];
                if (zzbuz != null) {
                    zzt += zzbur.zzb(17, zzbuz);
                }
                i++;
            }
        }
        return zzt;
    }

    private final zzvl zze(zzbuq zzbuq) throws IOException {
        int zzalm;
        StringBuilder stringBuilder;
        while (true) {
            int zzaku = zzbuq.zzaku();
            switch (zzaku) {
                case 0:
                    return this;
                case 56:
                    try {
                        zzalm = zzbuq.zzalm();
                        if (zzalm >= 0 && zzalm <= 9) {
                            this.zzcgm = Integer.valueOf(zzalm);
                            break;
                        }
                        stringBuilder = new StringBuilder(43);
                        stringBuilder.append(zzalm);
                        stringBuilder.append(" is not a valid enum AdInitiater");
                        break;
                    } catch (IllegalArgumentException unused) {
                        zzbuq.zzgc(zzbuq.getPosition());
                        zza(zzbuq, zzaku);
                        break;
                    }
                case 64:
                    int position = zzbuq.getPosition();
                    zzalm = zzbuq.zzalm();
                    if (zzalm != 1000) {
                        switch (zzalm) {
                            case 0:
                            case 1:
                                break;
                            default:
                                zzbuq.zzgc(position);
                                zza(zzbuq, zzaku);
                                continue;
                        }
                    }
                    this.zzcgn = zzvc.zzcd(zzalm);
                    break;
                case 74:
                    this.zzcgo = (zzb) zzbuq.zza(zzb.zzon());
                    break;
                case 82:
                    if (this.zzcgp == null) {
                        this.zzcgp = new zzvm();
                    }
                    zzbuq.zza(this.zzcgp);
                    break;
                case 90:
                    zzaku = zzbvc.zzb(zzbuq, 90);
                    zzalm = this.zzcgq == null ? 0 : this.zzcgq.length;
                    zza[] zzaArr = new zza[(zzaku + zzalm)];
                    if (zzalm != 0) {
                        System.arraycopy(this.zzcgq, 0, zzaArr, 0, zzalm);
                    }
                    while (zzalm < zzaArr.length - 1) {
                        zzaArr[zzalm] = (zza) zzbuq.zza(zza.zzon());
                        zzbuq.zzaku();
                        zzalm++;
                    }
                    zzaArr[zzalm] = (zza) zzbuq.zza(zza.zzon());
                    this.zzcgq = zzaArr;
                    break;
                case 98:
                    this.zzcgr = (zzc) zzbuq.zza(zzc.zzon());
                    break;
                case 106:
                    this.zzcgs = (zzj) zzbuq.zza(zzj.zzon());
                    break;
                case 114:
                    this.zzcgt = (zzi) zzbuq.zza(zzi.zzon());
                    break;
                case 122:
                    this.zzcgu = (zzf) zzbuq.zza(zzf.zzon());
                    break;
                case TsExtractor.TS_STREAM_TYPE_HDMV_DTS /*130*/:
                    this.zzcgv = (zzg) zzbuq.zza(zzg.zzon());
                    break;
                case TsExtractor.TS_STREAM_TYPE_DTS /*138*/:
                    zzaku = zzbvc.zzb(zzbuq, TsExtractor.TS_STREAM_TYPE_DTS);
                    zzalm = this.zzcgw == null ? 0 : this.zzcgw.length;
                    zzvr[] zzvrArr = new zzvr[(zzaku + zzalm)];
                    if (zzalm != 0) {
                        System.arraycopy(this.zzcgw, 0, zzvrArr, 0, zzalm);
                    }
                    while (zzalm < zzvrArr.length - 1) {
                        zzvrArr[zzalm] = new zzvr();
                        zzbuq.zza(zzvrArr[zzalm]);
                        zzbuq.zzaku();
                        zzalm++;
                    }
                    zzvrArr[zzalm] = new zzvr();
                    zzbuq.zza(zzvrArr[zzalm]);
                    this.zzcgw = zzvrArr;
                    break;
                default:
                    if (super.zza(zzbuq, zzaku)) {
                        break;
                    }
                    return this;
            }
        }
        stringBuilder = new StringBuilder(43);
        stringBuilder.append(zzalm);
        stringBuilder.append(" is not a valid enum AdInitiater");
        throw new IllegalArgumentException(stringBuilder.toString());
    }
}
