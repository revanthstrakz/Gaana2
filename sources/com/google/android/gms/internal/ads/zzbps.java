package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzbps extends zzbpo<Boolean> implements zzbrk<Boolean>, zzbsx, RandomAccess {
    private static final zzbps zzflg;
    private int size;
    private boolean[] zzflh;

    zzbps() {
        this(new boolean[10], 0);
    }

    private zzbps(boolean[] zArr, int i) {
        this.zzflh = zArr;
        this.size = i;
    }

    /* Access modifiers changed, original: protected|final */
    public final void removeRange(int i, int i2) {
        zzakk();
        if (i2 < i) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        System.arraycopy(this.zzflh, i2, this.zzflh, i, this.size - i2);
        this.size -= i2 - i;
        this.modCount++;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzbps)) {
            return super.equals(obj);
        }
        zzbps zzbps = (zzbps) obj;
        if (this.size != zzbps.size) {
            return false;
        }
        boolean[] zArr = zzbps.zzflh;
        for (int i = 0; i < this.size; i++) {
            if (this.zzflh[i] != zArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + zzbrf.zzbf(this.zzflh[i2]);
        }
        return i;
    }

    public final int size() {
        return this.size;
    }

    public final void addBoolean(boolean z) {
        zzi(this.size, z);
    }

    private final void zzi(int i, boolean z) {
        zzakk();
        if (i < 0 || i > this.size) {
            throw new IndexOutOfBoundsException(zzek(i));
        }
        if (this.size < this.zzflh.length) {
            System.arraycopy(this.zzflh, i, this.zzflh, i + 1, this.size - i);
        } else {
            boolean[] zArr = new boolean[(((this.size * 3) / 2) + 1)];
            System.arraycopy(this.zzflh, 0, zArr, 0, i);
            System.arraycopy(this.zzflh, i, zArr, i + 1, this.size - i);
            this.zzflh = zArr;
        }
        this.zzflh[i] = z;
        this.size++;
        this.modCount++;
    }

    public final boolean addAll(Collection<? extends Boolean> collection) {
        zzakk();
        zzbrf.checkNotNull(collection);
        if (!(collection instanceof zzbps)) {
            return super.addAll(collection);
        }
        zzbps zzbps = (zzbps) collection;
        if (zzbps.size == 0) {
            return false;
        }
        if (Integer.MAX_VALUE - this.size < zzbps.size) {
            throw new OutOfMemoryError();
        }
        int i = this.size + zzbps.size;
        if (i > this.zzflh.length) {
            this.zzflh = Arrays.copyOf(this.zzflh, i);
        }
        System.arraycopy(zzbps.zzflh, 0, this.zzflh, this.size, zzbps.size);
        this.size = i;
        this.modCount++;
        return true;
    }

    public final boolean remove(Object obj) {
        zzakk();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Boolean.valueOf(this.zzflh[i]))) {
                System.arraycopy(this.zzflh, i + 1, this.zzflh, i, this.size - i);
                this.size--;
                this.modCount++;
                return true;
            }
        }
        return false;
    }

    private final void zzej(int i) {
        if (i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException(zzek(i));
        }
    }

    private final String zzek(int i) {
        int i2 = this.size;
        StringBuilder stringBuilder = new StringBuilder(35);
        stringBuilder.append("Index:");
        stringBuilder.append(i);
        stringBuilder.append(", Size:");
        stringBuilder.append(i2);
        return stringBuilder.toString();
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        zzakk();
        zzej(i);
        boolean z = this.zzflh[i];
        this.zzflh[i] = booleanValue;
        return Boolean.valueOf(z);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        zzi(i, ((Boolean) obj).booleanValue());
    }

    public final /* synthetic */ zzbrk zzel(int i) {
        if (i >= this.size) {
            return new zzbps(Arrays.copyOf(this.zzflh, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        zzej(i);
        return Boolean.valueOf(this.zzflh[i]);
    }

    static {
        zzbps zzbps = new zzbps();
        zzflg = zzbps;
        zzbps.zzakj();
    }
}
