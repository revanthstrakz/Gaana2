package com.google.android.gms.internal.icing;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.Iterator;

public abstract class zzce implements Serializable, Iterable<Byte> {
    public static final zzce zzfx = new zzco(zzdl.zzkq);
    private static final zzck zzfy = (zzcb.zzal() ? new zzcp() : new zzci());
    private static final Comparator<zzce> zzga = new zzcg();
    private int zzfz = 0;

    zzce() {
    }

    private static int zza(byte b) {
        return b & 255;
    }

    public abstract boolean equals(Object obj);

    public abstract int size();

    public abstract int zza(int i, int i2, int i3);

    public abstract zzce zza(int i, int i2);

    public abstract String zza(Charset charset);

    public abstract void zza(zzcd zzcd) throws IOException;

    public abstract boolean zzap();

    public abstract byte zzk(int i);

    public abstract byte zzl(int i);

    public static zzce zzt(String str) {
        return new zzco(str.getBytes(zzdl.UTF_8));
    }

    public final String zzao() {
        return size() == 0 ? "" : zza(zzdl.UTF_8);
    }

    public final int hashCode() {
        int i = this.zzfz;
        if (i == 0) {
            i = size();
            i = zza(i, 0, i);
            if (i == 0) {
                i = 1;
            }
            this.zzfz = i;
        }
        return i;
    }

    static zzcm zzm(int i) {
        return new zzcm(i, null);
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzaq() {
        return this.zzfz;
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
        return new zzcf(this);
    }
}
