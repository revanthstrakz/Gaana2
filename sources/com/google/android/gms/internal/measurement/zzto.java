package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.nio.charset.Charset;

class zzto extends zztn {
    protected final byte[] zzbtx;

    zzto(byte[] bArr) {
        if (bArr == null) {
            throw new NullPointerException();
        }
        this.zzbtx = bArr;
    }

    /* Access modifiers changed, original: protected */
    public int zzug() {
        return 0;
    }

    public byte zzam(int i) {
        return this.zzbtx[i];
    }

    /* Access modifiers changed, original: 0000 */
    public byte zzan(int i) {
        return this.zzbtx[i];
    }

    public int size() {
        return this.zzbtx.length;
    }

    public final zzte zzb(int i, int i2) {
        i = zzte.zzb(0, i2, size());
        if (i == 0) {
            return zzte.zzbtq;
        }
        return new zztj(this.zzbtx, zzug(), i);
    }

    /* Access modifiers changed, original: final */
    public final void zza(zztd zztd) throws IOException {
        zztd.zza(this.zzbtx, zzug(), size());
    }

    /* Access modifiers changed, original: protected|final */
    public final String zza(Charset charset) {
        return new String(this.zzbtx, zzug(), size(), charset);
    }

    public final boolean zzue() {
        int zzug = zzug();
        return zzxl.zzf(this.zzbtx, zzug, size() + zzug);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzte) || size() != ((zzte) obj).size()) {
            return false;
        }
        if (size() == 0) {
            return true;
        }
        if (!(obj instanceof zzto)) {
            return obj.equals(this);
        }
        zzto zzto = (zzto) obj;
        int zzuf = zzuf();
        int zzuf2 = zzto.zzuf();
        if (zzuf == 0 || zzuf2 == 0 || zzuf == zzuf2) {
            return zza(zzto, 0, size());
        }
        return false;
    }

    /* Access modifiers changed, original: final */
    public final boolean zza(zzte zzte, int i, int i2) {
        StringBuilder stringBuilder;
        int size;
        if (i2 > zzte.size()) {
            i = size();
            stringBuilder = new StringBuilder(40);
            stringBuilder.append("Length too large: ");
            stringBuilder.append(i2);
            stringBuilder.append(i);
            throw new IllegalArgumentException(stringBuilder.toString());
        } else if (i2 > zzte.size()) {
            size = zzte.size();
            stringBuilder = new StringBuilder(59);
            stringBuilder.append("Ran off end of other: 0, ");
            stringBuilder.append(i2);
            stringBuilder.append(", ");
            stringBuilder.append(size);
            throw new IllegalArgumentException(stringBuilder.toString());
        } else if (!(zzte instanceof zzto)) {
            return zzte.zzb(0, i2).equals(zzb(0, i2));
        } else {
            zzto zzto = (zzto) zzte;
            byte[] bArr = this.zzbtx;
            byte[] bArr2 = zzto.zzbtx;
            int zzug = zzug() + i2;
            i2 = zzug();
            size = zzto.zzug();
            while (i2 < zzug) {
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
    public final int zza(int i, int i2, int i3) {
        return zzuq.zza(i, this.zzbtx, zzug(), i3);
    }
}
