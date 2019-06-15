package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbvt extends zzbut<zzbvt> {
    private static volatile zzbvt[] zzgbt;
    public String url;
    public Integer zzgbu;
    public zzbvq zzgbv;
    private zzbvr zzgbw;
    private Integer zzgbx;
    private int[] zzgby;
    private String zzgbz;
    public Integer zzgca;
    public String[] zzgcb;

    public static zzbvt[] zzaqe() {
        if (zzgbt == null) {
            synchronized (zzbux.zzfws) {
                if (zzgbt == null) {
                    zzgbt = new zzbvt[0];
                }
            }
        }
        return zzgbt;
    }

    public zzbvt() {
        this.zzgbu = null;
        this.url = null;
        this.zzgbv = null;
        this.zzgbw = null;
        this.zzgbx = null;
        this.zzgby = zzbvc.zzfsg;
        this.zzgbz = null;
        this.zzgca = null;
        this.zzgcb = zzbvc.zzfxc;
        this.zzfwk = null;
        this.zzfwt = -1;
    }

    public final void zza(zzbur zzbur) throws IOException {
        zzbur.zzv(1, this.zzgbu.intValue());
        if (this.url != null) {
            zzbur.zzf(2, this.url);
        }
        if (this.zzgbv != null) {
            zzbur.zza(3, this.zzgbv);
        }
        if (this.zzgbw != null) {
            zzbur.zza(4, this.zzgbw);
        }
        if (this.zzgbx != null) {
            zzbur.zzv(5, this.zzgbx.intValue());
        }
        int i = 0;
        if (this.zzgby != null && this.zzgby.length > 0) {
            for (int zzv : this.zzgby) {
                zzbur.zzv(6, zzv);
            }
        }
        if (this.zzgbz != null) {
            zzbur.zzf(7, this.zzgbz);
        }
        if (this.zzgca != null) {
            zzbur.zzv(8, this.zzgca.intValue());
        }
        if (this.zzgcb != null && this.zzgcb.length > 0) {
            while (i < this.zzgcb.length) {
                String str = this.zzgcb[i];
                if (str != null) {
                    zzbur.zzf(9, str);
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
        int zzt = super.zzt() + zzbur.zzz(1, this.zzgbu.intValue());
        if (this.url != null) {
            zzt += zzbur.zzg(2, this.url);
        }
        if (this.zzgbv != null) {
            zzt += zzbur.zzb(3, this.zzgbv);
        }
        if (this.zzgbw != null) {
            zzt += zzbur.zzb(4, this.zzgbw);
        }
        if (this.zzgbx != null) {
            zzt += zzbur.zzz(5, this.zzgbx.intValue());
        }
        int i3 = 0;
        if (this.zzgby != null && this.zzgby.length > 0) {
            i = 0;
            i2 = i;
            while (i < this.zzgby.length) {
                i2 += zzbur.zzfe(this.zzgby[i]);
                i++;
            }
            zzt = (zzt + i2) + (this.zzgby.length * 1);
        }
        if (this.zzgbz != null) {
            zzt += zzbur.zzg(7, this.zzgbz);
        }
        if (this.zzgca != null) {
            zzt += zzbur.zzz(8, this.zzgca.intValue());
        }
        if (this.zzgcb == null || this.zzgcb.length <= 0) {
            return zzt;
        }
        i = 0;
        i2 = i;
        while (i3 < this.zzgcb.length) {
            String str = this.zzgcb[i3];
            if (str != null) {
                i2++;
                i += zzbur.zzfy(str);
            }
            i3++;
        }
        return (zzt + i) + (1 * i2);
    }

    private final zzbvt zzh(zzbuq zzbuq) throws IOException {
        int length;
        StringBuilder stringBuilder;
        while (true) {
            int zzaku = zzbuq.zzaku();
            switch (zzaku) {
                case 0:
                    return this;
                case 8:
                    this.zzgbu = Integer.valueOf(zzbuq.zzakx());
                    break;
                case 18:
                    this.url = zzbuq.readString();
                    break;
                case 26:
                    if (this.zzgbv == null) {
                        this.zzgbv = new zzbvq();
                    }
                    zzbuq.zza(this.zzgbv);
                    break;
                case 34:
                    if (this.zzgbw == null) {
                        this.zzgbw = new zzbvr();
                    }
                    zzbuq.zza(this.zzgbw);
                    break;
                case 40:
                    this.zzgbx = Integer.valueOf(zzbuq.zzakx());
                    break;
                case 48:
                    zzaku = zzbvc.zzb(zzbuq, 48);
                    length = this.zzgby == null ? 0 : this.zzgby.length;
                    int[] iArr = new int[(zzaku + length)];
                    if (length != 0) {
                        System.arraycopy(this.zzgby, 0, iArr, 0, length);
                    }
                    while (length < iArr.length - 1) {
                        iArr[length] = zzbuq.zzakx();
                        zzbuq.zzaku();
                        length++;
                    }
                    iArr[length] = zzbuq.zzakx();
                    this.zzgby = iArr;
                    break;
                case 50:
                    zzaku = zzbuq.zzer(zzbuq.zzalm());
                    length = zzbuq.getPosition();
                    int i = 0;
                    while (zzbuq.zzapl() > 0) {
                        zzbuq.zzakx();
                        i++;
                    }
                    zzbuq.zzgc(length);
                    length = this.zzgby == null ? 0 : this.zzgby.length;
                    int[] iArr2 = new int[(i + length)];
                    if (length != 0) {
                        System.arraycopy(this.zzgby, 0, iArr2, 0, length);
                    }
                    while (length < iArr2.length) {
                        iArr2[length] = zzbuq.zzakx();
                        length++;
                    }
                    this.zzgby = iArr2;
                    zzbuq.zzes(zzaku);
                    break;
                case 58:
                    this.zzgbz = zzbuq.readString();
                    break;
                case 64:
                    try {
                        length = zzbuq.zzakx();
                        if (length >= 0 && length <= 3) {
                            this.zzgca = Integer.valueOf(length);
                            break;
                        }
                        stringBuilder = new StringBuilder(46);
                        stringBuilder.append(length);
                        stringBuilder.append(" is not a valid enum AdResourceType");
                        break;
                    } catch (IllegalArgumentException unused) {
                        zzbuq.zzgc(zzbuq.getPosition());
                        zza(zzbuq, zzaku);
                        break;
                    }
                case 74:
                    zzaku = zzbvc.zzb(zzbuq, 74);
                    length = this.zzgcb == null ? 0 : this.zzgcb.length;
                    String[] strArr = new String[(zzaku + length)];
                    if (length != 0) {
                        System.arraycopy(this.zzgcb, 0, strArr, 0, length);
                    }
                    while (length < strArr.length - 1) {
                        strArr[length] = zzbuq.readString();
                        zzbuq.zzaku();
                        length++;
                    }
                    strArr[length] = zzbuq.readString();
                    this.zzgcb = strArr;
                    break;
                default:
                    if (super.zza(zzbuq, zzaku)) {
                        break;
                    }
                    return this;
            }
        }
        stringBuilder = new StringBuilder(46);
        stringBuilder.append(length);
        stringBuilder.append(" is not a valid enum AdResourceType");
        throw new IllegalArgumentException(stringBuilder.toString());
    }
}
