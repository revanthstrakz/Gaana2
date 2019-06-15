package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.Iterator;

public abstract class zzbpu implements Serializable, Iterable<Byte> {
    public static final zzbpu zzfli = new zzbqd(zzbrf.zzfqr);
    private static final zzbpz zzflj = (zzbpp.zzakl() ? new zzbqe() : new zzbpx());
    private static final Comparator<zzbpu> zzflk = new zzbpw();
    private int zzfks = 0;

    zzbpu() {
    }

    private static int zzb(byte b) {
        return b & 255;
    }

    public abstract boolean equals(Object obj);

    public abstract int size();

    public abstract String zza(Charset charset);

    public abstract void zza(zzbpt zzbpt) throws IOException;

    public abstract void zza(byte[] bArr, int i, int i2, int i3);

    public abstract boolean zzako();

    public abstract zzbqf zzakp();

    public abstract byte zzem(int i);

    public abstract int zzf(int i, int i2, int i3);

    public abstract zzbpu zzt(int i, int i2);

    public static zzbpu zzi(byte[] bArr, int i, int i2) {
        zzg(i, i + i2, bArr.length);
        return new zzbqd(zzflj.zzj(bArr, i, i2));
    }

    public static zzbpu zzr(byte[] bArr) {
        return zzi(bArr, 0, bArr.length);
    }

    static zzbpu zzs(byte[] bArr) {
        return new zzbqd(bArr);
    }

    public static zzbpu zzfw(String str) {
        return new zzbqd(str.getBytes(zzbrf.UTF_8));
    }

    public final byte[] toByteArray() {
        int size = size();
        if (size == 0) {
            return zzbrf.zzfqr;
        }
        byte[] bArr = new byte[size];
        zza(bArr, 0, 0, size);
        return bArr;
    }

    public final String zzakn() {
        return size() == 0 ? "" : zza(zzbrf.UTF_8);
    }

    public final int hashCode() {
        int i = this.zzfks;
        if (i == 0) {
            i = size();
            i = zzf(i, 0, i);
            if (i == 0) {
                i = 1;
            }
            this.zzfks = i;
        }
        return i;
    }

    static zzbqb zzen(int i) {
        return new zzbqb(i, null);
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzakq() {
        return this.zzfks;
    }

    static int zzg(int i, int i2, int i3) {
        int i4 = i2 - i;
        if ((((i | i2) | i4) | (i3 - i2)) >= 0) {
            return i4;
        }
        StringBuilder stringBuilder;
        if (i < 0) {
            StringBuilder stringBuilder2 = new StringBuilder(32);
            stringBuilder2.append("Beginning index: ");
            stringBuilder2.append(i);
            stringBuilder2.append(" < 0");
            throw new IndexOutOfBoundsException(stringBuilder2.toString());
        } else if (i2 < i) {
            stringBuilder = new StringBuilder(66);
            stringBuilder.append("Beginning index larger than ending index: ");
            stringBuilder.append(i);
            stringBuilder.append(", ");
            stringBuilder.append(i2);
            throw new IndexOutOfBoundsException(stringBuilder.toString());
        } else {
            stringBuilder = new StringBuilder(37);
            stringBuilder.append("End index: ");
            stringBuilder.append(i2);
            stringBuilder.append(" >= ");
            stringBuilder.append(i3);
            throw new IndexOutOfBoundsException(stringBuilder.toString());
        }
    }

    public final String toString() {
        return String.format("<ByteString@%s size=%d>", new Object[]{Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(size())});
    }

    public /* synthetic */ Iterator iterator() {
        return new zzbpv(this);
    }
}
