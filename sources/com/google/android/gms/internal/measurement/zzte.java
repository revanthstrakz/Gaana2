package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.Iterator;

public abstract class zzte implements Serializable, Iterable<Byte> {
    public static final zzte zzbtq = new zzto(zzuq.zzbza);
    private static final zztk zzbtr = (zztb.zzub() ? new zztp() : new zzti());
    private static final Comparator<zzte> zzbts = new zztg();
    private int zzbsi = 0;

    zzte() {
    }

    private static int zza(byte b) {
        return b & 255;
    }

    public abstract boolean equals(Object obj);

    public abstract int size();

    public abstract int zza(int i, int i2, int i3);

    public abstract String zza(Charset charset);

    public abstract void zza(zztd zztd) throws IOException;

    public abstract byte zzam(int i);

    public abstract byte zzan(int i);

    public abstract zzte zzb(int i, int i2);

    public abstract boolean zzue();

    public static zzte zzb(byte[] bArr, int i, int i2) {
        zzb(i, i + i2, bArr.length);
        return new zzto(zzbtr.zzc(bArr, i, i2));
    }

    static zzte zzi(byte[] bArr) {
        return new zzto(bArr);
    }

    public static zzte zzga(String str) {
        return new zzto(str.getBytes(zzuq.UTF_8));
    }

    public final String zzud() {
        return size() == 0 ? "" : zza(zzuq.UTF_8);
    }

    public final int hashCode() {
        int i = this.zzbsi;
        if (i == 0) {
            i = size();
            i = zza(i, 0, i);
            if (i == 0) {
                i = 1;
            }
            this.zzbsi = i;
        }
        return i;
    }

    static zztm zzao(int i) {
        return new zztm(i, null);
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzuf() {
        return this.zzbsi;
    }

    static int zzb(int i, int i2, int i3) {
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
        return new zztf(this);
    }
}
