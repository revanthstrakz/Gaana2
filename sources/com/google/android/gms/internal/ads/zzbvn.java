package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import com.google.android.gms.internal.ads.zzbvd.zza.zzc;
import java.io.IOException;

public final class zzbvn extends zzbut<zzbvn> {
    public String url;
    public Integer zzgar;
    private zzc zzgas;
    public String zzgat;
    private String zzgau;
    public zzbvo zzgav;
    public zzbvt[] zzgaw;
    public String zzgax;
    public zzbvs zzgay;
    private Boolean zzgaz;
    private String[] zzgba;
    private String zzgbb;
    private Boolean zzgbc;
    private Boolean zzgbd;
    private byte[] zzgbe;
    public zzbvu zzgbf;
    public String[] zzgbg;
    public String[] zzgbh;

    public zzbvn() {
        this.zzgar = null;
        this.zzgas = null;
        this.url = null;
        this.zzgat = null;
        this.zzgau = null;
        this.zzgav = null;
        this.zzgaw = zzbvt.zzaqe();
        this.zzgax = null;
        this.zzgay = null;
        this.zzgaz = null;
        this.zzgba = zzbvc.zzfxc;
        this.zzgbb = null;
        this.zzgbc = null;
        this.zzgbd = null;
        this.zzgbe = null;
        this.zzgbf = null;
        this.zzgbg = zzbvc.zzfxc;
        this.zzgbh = zzbvc.zzfxc;
        this.zzfwk = null;
        this.zzfwt = -1;
    }

    public final void zza(zzbur zzbur) throws IOException {
        if (this.url != null) {
            zzbur.zzf(1, this.url);
        }
        if (this.zzgat != null) {
            zzbur.zzf(2, this.zzgat);
        }
        if (this.zzgau != null) {
            zzbur.zzf(3, this.zzgau);
        }
        int i = 0;
        if (this.zzgaw != null && this.zzgaw.length > 0) {
            for (zzbuz zzbuz : this.zzgaw) {
                if (zzbuz != null) {
                    zzbur.zza(4, zzbuz);
                }
            }
        }
        if (this.zzgaz != null) {
            zzbur.zzj(5, this.zzgaz.booleanValue());
        }
        if (this.zzgba != null && this.zzgba.length > 0) {
            for (String str : this.zzgba) {
                if (str != null) {
                    zzbur.zzf(6, str);
                }
            }
        }
        if (this.zzgbb != null) {
            zzbur.zzf(7, this.zzgbb);
        }
        if (this.zzgbc != null) {
            zzbur.zzj(8, this.zzgbc.booleanValue());
        }
        if (this.zzgbd != null) {
            zzbur.zzj(9, this.zzgbd.booleanValue());
        }
        if (this.zzgar != null) {
            zzbur.zzv(10, this.zzgar.intValue());
        }
        if (!(this.zzgas == null || this.zzgas == null)) {
            zzbur.zzv(11, this.zzgas.zzom());
        }
        if (this.zzgav != null) {
            zzbur.zza(12, this.zzgav);
        }
        if (this.zzgax != null) {
            zzbur.zzf(13, this.zzgax);
        }
        if (this.zzgay != null) {
            zzbur.zza(14, this.zzgay);
        }
        if (this.zzgbe != null) {
            zzbur.zza(15, this.zzgbe);
        }
        if (this.zzgbf != null) {
            zzbur.zza(17, this.zzgbf);
        }
        if (this.zzgbg != null && this.zzgbg.length > 0) {
            for (String str2 : this.zzgbg) {
                if (str2 != null) {
                    zzbur.zzf(20, str2);
                }
            }
        }
        if (this.zzgbh != null && this.zzgbh.length > 0) {
            while (i < this.zzgbh.length) {
                String str3 = this.zzgbh[i];
                if (str3 != null) {
                    zzbur.zzf(21, str3);
                }
                i++;
            }
        }
        super.zza(zzbur);
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzt() {
        int i;
        int i2;
        int i3;
        int zzt = super.zzt();
        if (this.url != null) {
            zzt += zzbur.zzg(1, this.url);
        }
        if (this.zzgat != null) {
            zzt += zzbur.zzg(2, this.zzgat);
        }
        if (this.zzgau != null) {
            zzt += zzbur.zzg(3, this.zzgau);
        }
        int i4 = 0;
        if (this.zzgaw != null && this.zzgaw.length > 0) {
            i = zzt;
            for (zzbuz zzbuz : this.zzgaw) {
                if (zzbuz != null) {
                    i += zzbur.zzb(4, zzbuz);
                }
            }
            zzt = i;
        }
        if (this.zzgaz != null) {
            this.zzgaz.booleanValue();
            zzt += zzbur.zzfd(5) + 1;
        }
        if (this.zzgba != null && this.zzgba.length > 0) {
            i = 0;
            i2 = i;
            int i5 = i2;
            while (i < this.zzgba.length) {
                String str = this.zzgba[i];
                if (str != null) {
                    i5++;
                    i2 += zzbur.zzfy(str);
                }
                i++;
            }
            zzt = (zzt + i2) + (i5 * 1);
        }
        if (this.zzgbb != null) {
            zzt += zzbur.zzg(7, this.zzgbb);
        }
        if (this.zzgbc != null) {
            this.zzgbc.booleanValue();
            zzt += zzbur.zzfd(8) + 1;
        }
        if (this.zzgbd != null) {
            this.zzgbd.booleanValue();
            zzt += zzbur.zzfd(9) + 1;
        }
        if (this.zzgar != null) {
            zzt += zzbur.zzz(10, this.zzgar.intValue());
        }
        if (!(this.zzgas == null || this.zzgas == null)) {
            zzt += zzbur.zzz(11, this.zzgas.zzom());
        }
        if (this.zzgav != null) {
            zzt += zzbur.zzb(12, this.zzgav);
        }
        if (this.zzgax != null) {
            zzt += zzbur.zzg(13, this.zzgax);
        }
        if (this.zzgay != null) {
            zzt += zzbur.zzb(14, this.zzgay);
        }
        if (this.zzgbe != null) {
            zzt += zzbur.zzb(15, this.zzgbe);
        }
        if (this.zzgbf != null) {
            zzt += zzbur.zzb(17, this.zzgbf);
        }
        if (this.zzgbg != null && this.zzgbg.length > 0) {
            i = 0;
            i3 = i;
            i2 = i3;
            while (i < this.zzgbg.length) {
                String str2 = this.zzgbg[i];
                if (str2 != null) {
                    i2++;
                    i3 += zzbur.zzfy(str2);
                }
                i++;
            }
            zzt = (zzt + i3) + (i2 * 2);
        }
        if (this.zzgbh == null || this.zzgbh.length <= 0) {
            return zzt;
        }
        i = 0;
        i3 = i;
        while (i4 < this.zzgbh.length) {
            String str3 = this.zzgbh[i4];
            if (str3 != null) {
                i3++;
                i += zzbur.zzfy(str3);
            }
            i4++;
        }
        return (zzt + i) + (2 * i3);
    }

    private final zzbvn zzf(zzbuq zzbuq) throws IOException {
        int length;
        StringBuilder stringBuilder;
        while (true) {
            int zzaku = zzbuq.zzaku();
            String[] strArr;
            switch (zzaku) {
                case 0:
                    return this;
                case 10:
                    this.url = zzbuq.readString();
                    break;
                case 18:
                    this.zzgat = zzbuq.readString();
                    break;
                case 26:
                    this.zzgau = zzbuq.readString();
                    break;
                case 34:
                    zzaku = zzbvc.zzb(zzbuq, 34);
                    length = this.zzgaw == null ? 0 : this.zzgaw.length;
                    zzbvt[] zzbvtArr = new zzbvt[(zzaku + length)];
                    if (length != 0) {
                        System.arraycopy(this.zzgaw, 0, zzbvtArr, 0, length);
                    }
                    while (length < zzbvtArr.length - 1) {
                        zzbvtArr[length] = new zzbvt();
                        zzbuq.zza(zzbvtArr[length]);
                        zzbuq.zzaku();
                        length++;
                    }
                    zzbvtArr[length] = new zzbvt();
                    zzbuq.zza(zzbvtArr[length]);
                    this.zzgaw = zzbvtArr;
                    break;
                case 40:
                    this.zzgaz = Boolean.valueOf(zzbuq.zzala());
                    break;
                case 50:
                    zzaku = zzbvc.zzb(zzbuq, 50);
                    length = this.zzgba == null ? 0 : this.zzgba.length;
                    strArr = new String[(zzaku + length)];
                    if (length != 0) {
                        System.arraycopy(this.zzgba, 0, strArr, 0, length);
                    }
                    while (length < strArr.length - 1) {
                        strArr[length] = zzbuq.readString();
                        zzbuq.zzaku();
                        length++;
                    }
                    strArr[length] = zzbuq.readString();
                    this.zzgba = strArr;
                    break;
                case 58:
                    this.zzgbb = zzbuq.readString();
                    break;
                case 64:
                    this.zzgbc = Boolean.valueOf(zzbuq.zzala());
                    break;
                case 72:
                    this.zzgbd = Boolean.valueOf(zzbuq.zzala());
                    break;
                case 80:
                    try {
                        length = zzbuq.zzakx();
                        if (length >= 0 && length <= 9) {
                            this.zzgar = Integer.valueOf(length);
                            break;
                        }
                        stringBuilder = new StringBuilder(42);
                        stringBuilder.append(length);
                        stringBuilder.append(" is not a valid enum ReportType");
                        break;
                    } catch (IllegalArgumentException unused) {
                        zzbuq.zzgc(zzbuq.getPosition());
                        zza(zzbuq, zzaku);
                        break;
                    }
                case 88:
                    int position = zzbuq.getPosition();
                    length = zzbuq.zzakx();
                    switch (length) {
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                            this.zzgas = zzc.zzgi(length);
                            break;
                        default:
                            zzbuq.zzgc(position);
                            zza(zzbuq, zzaku);
                            break;
                    }
                case 98:
                    if (this.zzgav == null) {
                        this.zzgav = new zzbvo();
                    }
                    zzbuq.zza(this.zzgav);
                    break;
                case 106:
                    this.zzgax = zzbuq.readString();
                    break;
                case 114:
                    if (this.zzgay == null) {
                        this.zzgay = new zzbvs();
                    }
                    zzbuq.zza(this.zzgay);
                    break;
                case 122:
                    this.zzgbe = zzbuq.readBytes();
                    break;
                case TsExtractor.TS_STREAM_TYPE_DTS /*138*/:
                    if (this.zzgbf == null) {
                        this.zzgbf = new zzbvu();
                    }
                    zzbuq.zza(this.zzgbf);
                    break;
                case 162:
                    zzaku = zzbvc.zzb(zzbuq, 162);
                    length = this.zzgbg == null ? 0 : this.zzgbg.length;
                    strArr = new String[(zzaku + length)];
                    if (length != 0) {
                        System.arraycopy(this.zzgbg, 0, strArr, 0, length);
                    }
                    while (length < strArr.length - 1) {
                        strArr[length] = zzbuq.readString();
                        zzbuq.zzaku();
                        length++;
                    }
                    strArr[length] = zzbuq.readString();
                    this.zzgbg = strArr;
                    break;
                case 170:
                    zzaku = zzbvc.zzb(zzbuq, 170);
                    length = this.zzgbh == null ? 0 : this.zzgbh.length;
                    strArr = new String[(zzaku + length)];
                    if (length != 0) {
                        System.arraycopy(this.zzgbh, 0, strArr, 0, length);
                    }
                    while (length < strArr.length - 1) {
                        strArr[length] = zzbuq.readString();
                        zzbuq.zzaku();
                        length++;
                    }
                    strArr[length] = zzbuq.readString();
                    this.zzgbh = strArr;
                    break;
                default:
                    if (super.zza(zzbuq, zzaku)) {
                        break;
                    }
                    return this;
            }
        }
        stringBuilder = new StringBuilder(42);
        stringBuilder.append(length);
        stringBuilder.append(" is not a valid enum ReportType");
        throw new IllegalArgumentException(stringBuilder.toString());
    }
}
