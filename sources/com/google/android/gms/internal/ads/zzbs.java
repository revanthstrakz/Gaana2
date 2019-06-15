package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbs extends zzbut<zzbs> {
    public Integer zzfy;
    private Integer zzfz;
    public byte[] zzhm;
    public byte[][] zzhr;

    public zzbs() {
        this.zzhr = zzbvc.zzfxd;
        this.zzhm = null;
        this.zzfwt = -1;
    }

    public final void zza(zzbur zzbur) throws IOException {
        if (this.zzhr != null && this.zzhr.length > 0) {
            for (byte[] bArr : this.zzhr) {
                if (bArr != null) {
                    zzbur.zza(1, bArr);
                }
            }
        }
        if (this.zzhm != null) {
            zzbur.zza(2, this.zzhm);
        }
        if (this.zzfz != null) {
            zzbur.zzv(3, this.zzfz.intValue());
        }
        if (this.zzfy != null) {
            zzbur.zzv(4, this.zzfy.intValue());
        }
        super.zza(zzbur);
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzt() {
        int zzt = super.zzt();
        if (this.zzhr != null && this.zzhr.length > 0) {
            int i = 0;
            int i2 = 0;
            int i3 = i2;
            while (i < this.zzhr.length) {
                byte[] bArr = this.zzhr[i];
                if (bArr != null) {
                    i3++;
                    i2 += zzbur.zzy(bArr);
                }
                i++;
            }
            zzt = (zzt + i2) + (1 * i3);
        }
        if (this.zzhm != null) {
            zzt += zzbur.zzb(2, this.zzhm);
        }
        if (this.zzfz != null) {
            zzt += zzbur.zzz(3, this.zzfz.intValue());
        }
        return this.zzfy != null ? zzt + zzbur.zzz(4, this.zzfy.intValue()) : zzt;
    }

    private final zzbs zzd(zzbuq zzbuq) throws IOException {
        while (true) {
            int zzaku = zzbuq.zzaku();
            if (zzaku == 0) {
                return this;
            }
            int length;
            if (zzaku == 10) {
                zzaku = zzbvc.zzb(zzbuq, 10);
                length = this.zzhr == null ? 0 : this.zzhr.length;
                byte[][] bArr = new byte[(zzaku + length)][];
                if (length != 0) {
                    System.arraycopy(this.zzhr, 0, bArr, 0, length);
                }
                while (length < bArr.length - 1) {
                    bArr[length] = zzbuq.readBytes();
                    zzbuq.zzaku();
                    length++;
                }
                bArr[length] = zzbuq.readBytes();
                this.zzhr = bArr;
            } else if (zzaku == 18) {
                this.zzhm = zzbuq.readBytes();
            } else if (zzaku == 24) {
                length = zzbuq.getPosition();
                try {
                    this.zzfz = Integer.valueOf(zzbk.zze(zzbuq.zzalm()));
                } catch (IllegalArgumentException unused) {
                    zzbuq.zzgc(length);
                    zza(zzbuq, zzaku);
                }
            } else if (zzaku == 32) {
                length = zzbuq.getPosition();
                try {
                    this.zzfy = Integer.valueOf(zzbk.zzf(zzbuq.zzalm()));
                } catch (IllegalArgumentException unused2) {
                    zzbuq.zzgc(length);
                    zza(zzbuq, zzaku);
                }
            } else if (!super.zza(zzbuq, zzaku)) {
                return this;
            }
        }
    }
}
