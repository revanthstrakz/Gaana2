package com.google.android.gms.internal.vision;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzfh extends zzef<Double> implements zzge<Double>, zzhr, RandomAccess {
    private static final zzfh zztc;
    private int size;
    private double[] zztd;

    zzfh() {
        this(new double[10], 0);
    }

    private zzfh(double[] dArr, int i) {
        this.zztd = dArr;
        this.size = i;
    }

    /* Access modifiers changed, original: protected|final */
    public final void removeRange(int i, int i2) {
        zzcj();
        if (i2 < i) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        System.arraycopy(this.zztd, i2, this.zztd, i, this.size - i2);
        this.size -= i2 - i;
        this.modCount++;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzfh)) {
            return super.equals(obj);
        }
        zzfh zzfh = (zzfh) obj;
        if (this.size != zzfh.size) {
            return false;
        }
        double[] dArr = zzfh.zztd;
        for (int i = 0; i < this.size; i++) {
            if (Double.doubleToLongBits(this.zztd[i]) != Double.doubleToLongBits(dArr[i])) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + zzga.zzo(Double.doubleToLongBits(this.zztd[i2]));
        }
        return i;
    }

    public final int size() {
        return this.size;
    }

    public final void zzc(double d) {
        zzc(this.size, d);
    }

    private final void zzc(int i, double d) {
        zzcj();
        if (i < 0 || i > this.size) {
            throw new IndexOutOfBoundsException(zzag(i));
        }
        if (this.size < this.zztd.length) {
            System.arraycopy(this.zztd, i, this.zztd, i + 1, this.size - i);
        } else {
            double[] dArr = new double[(((this.size * 3) / 2) + 1)];
            System.arraycopy(this.zztd, 0, dArr, 0, i);
            System.arraycopy(this.zztd, i, dArr, i + 1, this.size - i);
            this.zztd = dArr;
        }
        this.zztd[i] = d;
        this.size++;
        this.modCount++;
    }

    public final boolean addAll(Collection<? extends Double> collection) {
        zzcj();
        zzga.checkNotNull(collection);
        if (!(collection instanceof zzfh)) {
            return super.addAll(collection);
        }
        zzfh zzfh = (zzfh) collection;
        if (zzfh.size == 0) {
            return false;
        }
        if (Integer.MAX_VALUE - this.size < zzfh.size) {
            throw new OutOfMemoryError();
        }
        int i = this.size + zzfh.size;
        if (i > this.zztd.length) {
            this.zztd = Arrays.copyOf(this.zztd, i);
        }
        System.arraycopy(zzfh.zztd, 0, this.zztd, this.size, zzfh.size);
        this.size = i;
        this.modCount++;
        return true;
    }

    public final boolean remove(Object obj) {
        zzcj();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Double.valueOf(this.zztd[i]))) {
                System.arraycopy(this.zztd, i + 1, this.zztd, i, this.size - i);
                this.size--;
                this.modCount++;
                return true;
            }
        }
        return false;
    }

    private final void zzaf(int i) {
        if (i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException(zzag(i));
        }
    }

    private final String zzag(int i) {
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
        zzcj();
        zzaf(i);
        double d = this.zztd[i];
        this.zztd[i] = doubleValue;
        return Double.valueOf(d);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        zzc(i, ((Double) obj).doubleValue());
    }

    public final /* synthetic */ zzge zzah(int i) {
        if (i >= this.size) {
            return new zzfh(Arrays.copyOf(this.zztd, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        zzaf(i);
        return Double.valueOf(this.zztd[i]);
    }

    static {
        zzfh zzfh = new zzfh();
        zztc = zzfh;
        zzfh.zzci();
    }
}
