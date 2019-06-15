package com.google.android.gms.internal.ads;

import java.util.Arrays;

public final class zzpr {
    private int size;
    private long[] zzbhl;

    public zzpr() {
        this(32);
    }

    private zzpr(int i) {
        this.zzbhl = new long[32];
    }

    public final void add(long j) {
        if (this.size == this.zzbhl.length) {
            this.zzbhl = Arrays.copyOf(this.zzbhl, this.size << 1);
        }
        long[] jArr = this.zzbhl;
        int i = this.size;
        this.size = i + 1;
        jArr[i] = j;
    }

    public final long get(int i) {
        if (i >= 0 && i < this.size) {
            return this.zzbhl[i];
        }
        int i2 = this.size;
        StringBuilder stringBuilder = new StringBuilder(46);
        stringBuilder.append("Invalid index ");
        stringBuilder.append(i);
        stringBuilder.append(", size is ");
        stringBuilder.append(i2);
        throw new IndexOutOfBoundsException(stringBuilder.toString());
    }

    public final int size() {
        return this.size;
    }
}
