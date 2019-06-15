package com.google.android.gms.internal.icing;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzcw extends zzca<Double> implements zzdq<Double>, zzfb, RandomAccess {
    private static final zzcw zzgp;
    private int size;
    private double[] zzgq;

    public static zzcw zzaz() {
        return zzgp;
    }

    zzcw() {
        this(new double[10], 0);
    }

    private zzcw(double[] dArr, int i) {
        this.zzgq = dArr;
        this.size = i;
    }

    /* Access modifiers changed, original: protected|final */
    public final void removeRange(int i, int i2) {
        zzak();
        if (i2 < i) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        System.arraycopy(this.zzgq, i2, this.zzgq, i, this.size - i2);
        this.size -= i2 - i;
        this.modCount++;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzcw)) {
            return super.equals(obj);
        }
        zzcw zzcw = (zzcw) obj;
        if (this.size != zzcw.size) {
            return false;
        }
        double[] dArr = zzcw.zzgq;
        for (int i = 0; i < this.size; i++) {
            if (Double.doubleToLongBits(this.zzgq[i]) != Double.doubleToLongBits(dArr[i])) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + zzdl.zzk(Double.doubleToLongBits(this.zzgq[i2]));
        }
        return i;
    }

    public final int size() {
        return this.size;
    }

    public final boolean addAll(Collection<? extends Double> collection) {
        zzak();
        zzdl.checkNotNull(collection);
        if (!(collection instanceof zzcw)) {
            return super.addAll(collection);
        }
        zzcw zzcw = (zzcw) collection;
        if (zzcw.size == 0) {
            return false;
        }
        if (Integer.MAX_VALUE - this.size < zzcw.size) {
            throw new OutOfMemoryError();
        }
        int i = this.size + zzcw.size;
        if (i > this.zzgq.length) {
            this.zzgq = Arrays.copyOf(this.zzgq, i);
        }
        System.arraycopy(zzcw.zzgq, 0, this.zzgq, this.size, zzcw.size);
        this.size = i;
        this.modCount++;
        return true;
    }

    public final boolean remove(Object obj) {
        zzak();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Double.valueOf(this.zzgq[i]))) {
                System.arraycopy(this.zzgq, i + 1, this.zzgq, i, this.size - i);
                this.size--;
                this.modCount++;
                return true;
            }
        }
        return false;
    }

    private final void zzh(int i) {
        if (i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException(zzi(i));
        }
    }

    private final String zzi(int i) {
        int i2 = this.size;
        StringBuilder stringBuilder = new StringBuilder(35);
        stringBuilder.append("Index:");
        stringBuilder.append(i);
        stringBuilder.append(", Size:");
        stringBuilder.append(i2);
        return stringBuilder.toString();
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        double doubleValue = ((Double) obj).doubleValue();
        zzak();
        zzh(i);
        double d = this.zzgq[i];
        this.zzgq[i] = doubleValue;
        return Double.valueOf(d);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        double doubleValue = ((Double) obj).doubleValue();
        zzak();
        if (i < 0 || i > this.size) {
            throw new IndexOutOfBoundsException(zzi(i));
        }
        if (this.size < this.zzgq.length) {
            System.arraycopy(this.zzgq, i, this.zzgq, i + 1, this.size - i);
        } else {
            double[] dArr = new double[(((this.size * 3) / 2) + 1)];
            System.arraycopy(this.zzgq, 0, dArr, 0, i);
            System.arraycopy(this.zzgq, i, dArr, i + 1, this.size - i);
            this.zzgq = dArr;
        }
        this.zzgq[i] = doubleValue;
        this.size++;
        this.modCount++;
    }

    public final /* synthetic */ zzdq zzj(int i) {
        if (i >= this.size) {
            return new zzcw(Arrays.copyOf(this.zzgq, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        zzh(i);
        return Double.valueOf(this.zzgq[i]);
    }

    static {
        zzcw zzcw = new zzcw();
        zzgp = zzcw;
        zzcw.zzaj();
    }
}
