package com.google.android.gms.internal.icing;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzef extends zzca<Long> implements zzdq<Long>, zzfb, RandomAccess {
    private static final zzef zzlt;
    private int size;
    private long[] zzlu;

    public static zzef zzck() {
        return zzlt;
    }

    zzef() {
        this(new long[10], 0);
    }

    private zzef(long[] jArr, int i) {
        this.zzlu = jArr;
        this.size = i;
    }

    /* Access modifiers changed, original: protected|final */
    public final void removeRange(int i, int i2) {
        zzak();
        if (i2 < i) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        System.arraycopy(this.zzlu, i2, this.zzlu, i, this.size - i2);
        this.size -= i2 - i;
        this.modCount++;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzef)) {
            return super.equals(obj);
        }
        zzef zzef = (zzef) obj;
        if (this.size != zzef.size) {
            return false;
        }
        long[] jArr = zzef.zzlu;
        for (int i = 0; i < this.size; i++) {
            if (this.zzlu[i] != jArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + zzdl.zzk(this.zzlu[i2]);
        }
        return i;
    }

    public final long getLong(int i) {
        zzh(i);
        return this.zzlu[i];
    }

    public final int size() {
        return this.size;
    }

    public final boolean addAll(Collection<? extends Long> collection) {
        zzak();
        zzdl.checkNotNull(collection);
        if (!(collection instanceof zzef)) {
            return super.addAll(collection);
        }
        zzef zzef = (zzef) collection;
        if (zzef.size == 0) {
            return false;
        }
        if (Integer.MAX_VALUE - this.size < zzef.size) {
            throw new OutOfMemoryError();
        }
        int i = this.size + zzef.size;
        if (i > this.zzlu.length) {
            this.zzlu = Arrays.copyOf(this.zzlu, i);
        }
        System.arraycopy(zzef.zzlu, 0, this.zzlu, this.size, zzef.size);
        this.size = i;
        this.modCount++;
        return true;
    }

    public final boolean remove(Object obj) {
        zzak();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Long.valueOf(this.zzlu[i]))) {
                System.arraycopy(this.zzlu, i + 1, this.zzlu, i, this.size - i);
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
        long longValue = ((Long) obj).longValue();
        zzak();
        zzh(i);
        long j = this.zzlu[i];
        this.zzlu[i] = longValue;
        return Long.valueOf(j);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        long longValue = ((Long) obj).longValue();
        zzak();
        if (i < 0 || i > this.size) {
            throw new IndexOutOfBoundsException(zzi(i));
        }
        if (this.size < this.zzlu.length) {
            System.arraycopy(this.zzlu, i, this.zzlu, i + 1, this.size - i);
        } else {
            long[] jArr = new long[(((this.size * 3) / 2) + 1)];
            System.arraycopy(this.zzlu, 0, jArr, 0, i);
            System.arraycopy(this.zzlu, i, jArr, i + 1, this.size - i);
            this.zzlu = jArr;
        }
        this.zzlu[i] = longValue;
        this.size++;
        this.modCount++;
    }

    public final /* synthetic */ zzdq zzj(int i) {
        if (i >= this.size) {
            return new zzef(Arrays.copyOf(this.zzlu, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        return Long.valueOf(getLong(i));
    }

    static {
        zzef zzef = new zzef();
        zzlt = zzef;
        zzef.zzaj();
    }
}
