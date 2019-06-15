package com.google.android.gms.internal.icing;

import java.io.IOException;
import java.nio.charset.Charset;

class zzco extends zzcn {
    protected final byte[] zzgf;

    zzco(byte[] bArr) {
        if (bArr == null) {
            throw new NullPointerException();
        }
        this.zzgf = bArr;
    }

    /* Access modifiers changed, original: protected */
    public int zzar() {
        return 0;
    }

    public byte zzk(int i) {
        return this.zzgf[i];
    }

    /* Access modifiers changed, original: 0000 */
    public byte zzl(int i) {
        return this.zzgf[i];
    }

    public int size() {
        return this.zzgf.length;
    }

    public final zzce zza(int i, int i2) {
        i = zzce.zzb(0, i2, size());
        if (i == 0) {
            return zzce.zzfx;
        }
        return new zzcj(this.zzgf, zzar(), i);
    }

    /* Access modifiers changed, original: final */
    public final void zza(zzcd zzcd) throws IOException {
        zzcd.zza(this.zzgf, zzar(), size());
    }

    /* Access modifiers changed, original: protected|final */
    public final String zza(Charset charset) {
        return new String(this.zzgf, zzar(), size(), charset);
    }

    public final boolean zzap() {
        int zzar = zzar();
        return zzgf.zzc(this.zzgf, zzar, size() + zzar);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzce) || size() != ((zzce) obj).size()) {
            return false;
        }
        if (size() == 0) {
            return true;
        }
        if (!(obj instanceof zzco)) {
            return obj.equals(this);
        }
        zzco zzco = (zzco) obj;
        int zzaq = zzaq();
        int zzaq2 = zzco.zzaq();
        if (zzaq == 0 || zzaq2 == 0 || zzaq == zzaq2) {
            return zza(zzco, 0, size());
        }
        return false;
    }

    /* Access modifiers changed, original: final */
    public final boolean zza(zzce zzce, int i, int i2) {
        StringBuilder stringBuilder;
        int size;
        if (i2 > zzce.size()) {
            i = size();
            stringBuilder = new StringBuilder(40);
            stringBuilder.append("Length too large: ");
            stringBuilder.append(i2);
            stringBuilder.append(i);
            throw new IllegalArgumentException(stringBuilder.toString());
        } else if (i2 > zzce.size()) {
            size = zzce.size();
            stringBuilder = new StringBuilder(59);
            stringBuilder.append("Ran off end of other: 0, ");
            stringBuilder.append(i2);
            stringBuilder.append(", ");
            stringBuilder.append(size);
            throw new IllegalArgumentException(stringBuilder.toString());
        } else if (!(zzce instanceof zzco)) {
            return zzce.zza(0, i2).equals(zza(0, i2));
        } else {
            zzco zzco = (zzco) zzce;
            byte[] bArr = this.zzgf;
            byte[] bArr2 = zzco.zzgf;
            int zzar = zzar() + i2;
            i2 = zzar();
            size = zzco.zzar();
            while (i2 < zzar) {
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
        return zzdl.zza(i, this.zzgf, zzar(), i3);
    }
}
