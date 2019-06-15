package com.google.android.gms.internal.icing;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzdk extends zzca<Integer> implements zzdq<Integer>, zzfb, RandomAccess {
    private static final zzdk zzko;
    private int size;
    private int[] zzkp;

    zzdk() {
        this(new int[10], 0);
    }

    private zzdk(int[] iArr, int i) {
        this.zzkp = iArr;
        this.size = i;
    }

    /* Access modifiers changed, original: protected|final */
    public final void removeRange(int i, int i2) {
        zzak();
        if (i2 < i) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        System.arraycopy(this.zzkp, i2, this.zzkp, i, this.size - i2);
        this.size -= i2 - i;
        this.modCount++;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzdk)) {
            return super.equals(obj);
        }
        zzdk zzdk = (zzdk) obj;
        if (this.size != zzdk.size) {
            return false;
        }
        int[] iArr = zzdk.zzkp;
        for (int i = 0; i < this.size; i++) {
            if (this.zzkp[i] != iArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + this.zzkp[i2];
        }
        return i;
    }

    public final int getInt(int i) {
        zzh(i);
        return this.zzkp[i];
    }

    public final int size() {
        return this.size;
    }

    public final boolean addAll(Collection<? extends Integer> collection) {
        zzak();
        zzdl.checkNotNull(collection);
        if (!(collection instanceof zzdk)) {
            return super.addAll(collection);
        }
        zzdk zzdk = (zzdk) collection;
        if (zzdk.size == 0) {
            return false;
        }
        if (Integer.MAX_VALUE - this.size < zzdk.size) {
            throw new OutOfMemoryError();
        }
        int i = this.size + zzdk.size;
        if (i > this.zzkp.length) {
            this.zzkp = Arrays.copyOf(this.zzkp, i);
        }
        System.arraycopy(zzdk.zzkp, 0, this.zzkp, this.size, zzdk.size);
        this.size = i;
        this.modCount++;
        return true;
    }

    public final boolean remove(Object obj) {
        zzak();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Integer.valueOf(this.zzkp[i]))) {
                System.arraycopy(this.zzkp, i + 1, this.zzkp, i, this.size - i);
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
        int intValue = ((Integer) obj).intValue();
        zzak();
        zzh(i);
        int i2 = this.zzkp[i];
        this.zzkp[i] = intValue;
        return Integer.valueOf(i2);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        int intValue = ((Integer) obj).intValue();
        zzak();
        if (i < 0 || i > this.size) {
            throw new IndexOutOfBoundsException(zzi(i));
        }
        if (this.size < this.zzkp.length) {
            System.arraycopy(this.zzkp, i, this.zzkp, i + 1, this.size - i);
        } else {
            int[] iArr = new int[(((this.size * 3) / 2) + 1)];
            System.arraycopy(this.zzkp, 0, iArr, 0, i);
            System.arraycopy(this.zzkp, i, iArr, i + 1, this.size - i);
            this.zzkp = iArr;
        }
        this.zzkp[i] = intValue;
        this.size++;
        this.modCount++;
    }

    public final /* synthetic */ zzdq zzj(int i) {
        if (i >= this.size) {
            return new zzdk(Arrays.copyOf(this.zzkp, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        return Integer.valueOf(getInt(i));
    }

    static {
        zzdk zzdk = new zzdk();
        zzko = zzdk;
        zzdk.zzaj();
    }
}
