package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.nio.charset.Charset;

class zzbqd extends zzbqc {
    protected final byte[] zzflp;

    zzbqd(byte[] bArr) {
        if (bArr == null) {
            throw new NullPointerException();
        }
        this.zzflp = bArr;
    }

    /* Access modifiers changed, original: protected */
    public int zzakr() {
        return 0;
    }

    public byte zzem(int i) {
        return this.zzflp[i];
    }

    public int size() {
        return this.zzflp.length;
    }

    public final zzbpu zzt(int i, int i2) {
        i = zzbpu.zzg(0, i2, size());
        if (i == 0) {
            return zzbpu.zzfli;
        }
        return new zzbpy(this.zzflp, zzakr(), i);
    }

    /* Access modifiers changed, original: protected */
    public void zza(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.zzflp, 0, bArr, 0, i3);
    }

    /* Access modifiers changed, original: final */
    public final void zza(zzbpt zzbpt) throws IOException {
        zzbpt.zzh(this.zzflp, zzakr(), size());
    }

    /* Access modifiers changed, original: protected|final */
    public final String zza(Charset charset) {
        return new String(this.zzflp, zzakr(), size(), charset);
    }

    public final boolean zzako() {
        int zzakr = zzakr();
        return zzbuc.zzm(this.zzflp, zzakr, size() + zzakr);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbpu) || size() != ((zzbpu) obj).size()) {
            return false;
        }
        if (size() == 0) {
            return true;
        }
        if (!(obj instanceof zzbqd)) {
            return obj.equals(this);
        }
        zzbqd zzbqd = (zzbqd) obj;
        int zzakq = zzakq();
        int zzakq2 = zzbqd.zzakq();
        if (zzakq == 0 || zzakq2 == 0 || zzakq == zzakq2) {
            return zza(zzbqd, 0, size());
        }
        return false;
    }

    /* Access modifiers changed, original: final */
    public final boolean zza(zzbpu zzbpu, int i, int i2) {
        StringBuilder stringBuilder;
        int size;
        if (i2 > zzbpu.size()) {
            i = size();
            stringBuilder = new StringBuilder(40);
            stringBuilder.append("Length too large: ");
            stringBuilder.append(i2);
            stringBuilder.append(i);
            throw new IllegalArgumentException(stringBuilder.toString());
        } else if (i2 > zzbpu.size()) {
            size = zzbpu.size();
            stringBuilder = new StringBuilder(59);
            stringBuilder.append("Ran off end of other: 0, ");
            stringBuilder.append(i2);
            stringBuilder.append(", ");
            stringBuilder.append(size);
            throw new IllegalArgumentException(stringBuilder.toString());
        } else if (!(zzbpu instanceof zzbqd)) {
            return zzbpu.zzt(0, i2).equals(zzt(0, i2));
        } else {
            zzbqd zzbqd = (zzbqd) zzbpu;
            byte[] bArr = this.zzflp;
            byte[] bArr2 = zzbqd.zzflp;
            int zzakr = zzakr() + i2;
            i2 = zzakr();
            size = zzbqd.zzakr();
            while (i2 < zzakr) {
                if (bArr[i2] != bArr2[size]) {
                    return false;
                }
                i2++;
                size++;
            }
            return true;
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzf(int i, int i2, int i3) {
        return zzbrf.zza(i, this.zzflp, zzakr(), i3);
    }

    public final zzbqf zzakp() {
        return zzbqf.zzb(this.zzflp, zzakr(), size(), true);
    }
}
