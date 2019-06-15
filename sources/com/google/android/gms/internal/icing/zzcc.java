package com.google.android.gms.internal.icing;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzcc extends zzca<Boolean> implements zzdq<Boolean>, zzfb, RandomAccess {
    private static final zzcc zzfv;
    private int size;
    private boolean[] zzfw;

    public static zzcc zzan() {
        return zzfv;
    }

    zzcc() {
        this(new boolean[10], 0);
    }

    private zzcc(boolean[] zArr, int i) {
        this.zzfw = zArr;
        this.size = i;
    }

    /* Access modifiers changed, original: protected|final */
    public final void removeRange(int i, int i2) {
        zzak();
        if (i2 < i) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        System.arraycopy(this.zzfw, i2, this.zzfw, i, this.size - i2);
        this.size -= i2 - i;
        this.modCount++;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzcc)) {
            return super.equals(obj);
        }
        zzcc zzcc = (zzcc) obj;
        if (this.size != zzcc.size) {
            return false;
        }
        boolean[] zArr = zzcc.zzfw;
        for (int i = 0; i < this.size; i++) {
            if (this.zzfw[i] != zArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + zzdl.zzi(this.zzfw[i2]);
        }
        return i;
    }

    public final int size() {
        return this.size;
    }

    public final boolean addAll(Collection<? extends Boolean> collection) {
        zzak();
        zzdl.checkNotNull(collection);
        if (!(collection instanceof zzcc)) {
            return super.addAll(collection);
        }
        zzcc zzcc = (zzcc) collection;
        if (zzcc.size == 0) {
            return false;
        }
        if (Integer.MAX_VALUE - this.size < zzcc.size) {
            throw new OutOfMemoryError();
        }
        int i = this.size + zzcc.size;
        if (i > this.zzfw.length) {
            this.zzfw = Arrays.copyOf(this.zzfw, i);
        }
        System.arraycopy(zzcc.zzfw, 0, this.zzfw, this.size, zzcc.size);
        this.size = i;
        this.modCount++;
        return true;
    }

    public final boolean remove(Object obj) {
        zzak();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Boolean.valueOf(this.zzfw[i]))) {
                System.arraycopy(this.zzfw, i + 1, this.zzfw, i, this.size - i);
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
        boolean booleanValue = ((Boolean) obj).booleanValue();
        zzak();
        zzh(i);
        boolean z = this.zzfw[i];
        this.zzfw[i] = booleanValue;
        return Boolean.valueOf(z);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        zzak();
        if (i < 0 || i > this.size) {
            throw new IndexOutOfBoundsException(zzi(i));
        }
        if (this.size < this.zzfw.length) {
            System.arraycopy(this.zzfw, i, this.zzfw, i + 1, this.size - i);
        } else {
            boolean[] zArr = new boolean[(((this.size * 3) / 2) + 1)];
            System.arraycopy(this.zzfw, 0, zArr, 0, i);
            System.arraycopy(this.zzfw, i, zArr, i + 1, this.size - i);
            this.zzfw = zArr;
        }
        this.zzfw[i] = booleanValue;
        this.size++;
        this.modCount++;
    }

    public final /* synthetic */ zzdq zzj(int i) {
        if (i >= this.size) {
            return new zzcc(Arrays.copyOf(this.zzfw, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        zzh(i);
        return Boolean.valueOf(this.zzfw[i]);
    }

    static {
        zzcc zzcc = new zzcc();
        zzfv = zzcc;
        zzcc.zzaj();
    }
}
