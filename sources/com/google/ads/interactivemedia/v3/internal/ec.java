package com.google.ads.interactivemedia.v3.internal;

public final class ec {
    private final long a;
    private long b;
    private volatile long c = Long.MIN_VALUE;

    public ec(long j) {
        this.a = j;
    }

    public void a() {
        this.c = Long.MIN_VALUE;
    }

    public long a(long j) {
        if (this.c != Long.MIN_VALUE) {
            long j2 = (this.c + 4294967296L) / 8589934592L;
            long j3 = j + ((j2 - 1) * 8589934592L);
            j2 = j + (8589934592L * j2);
            j = Math.abs(j3 - this.c) < Math.abs(j2 - this.c) ? j3 : j2;
        }
        long b = b(j);
        if (this.a != Long.MAX_VALUE && this.c == Long.MIN_VALUE) {
            this.b = this.a - b;
        }
        this.c = j;
        return b + this.b;
    }

    public static long b(long j) {
        return (j * 1000000) / 90000;
    }
}
