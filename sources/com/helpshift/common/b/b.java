package com.helpshift.common.b;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class b {
    private long a;
    private int b;
    private final long c;
    private final long d;
    private final float e;
    private final float f;
    private final int g;
    private final Random h = new Random();

    public static class a {
        long a = TimeUnit.SECONDS.toMillis(10);
        long b = TimeUnit.SECONDS.toMillis(60);
        float c = 0.5f;
        float d = 2.0f;
        int e = Integer.MAX_VALUE;

        public a a(a aVar) {
            this.a = aVar.b.toMillis(aVar.a);
            return this;
        }

        public a b(a aVar) {
            this.b = aVar.b.toMillis(aVar.a);
            return this;
        }

        public a a(float f) {
            this.c = f;
            return this;
        }

        public a b(float f) {
            this.d = f;
            return this;
        }

        public a a(int i) {
            this.e = i;
            return this;
        }

        /* Access modifiers changed, original: 0000 */
        public void a() {
            if (this.a <= 0) {
                throw new IllegalArgumentException("Base interval can't be negative or zero");
            } else if (this.b <= 0) {
                throw new IllegalArgumentException("Max interval can't be negative or zero");
            } else if (this.b < this.a) {
                throw new IllegalArgumentException("Max interval can't be less than base interval");
            } else if (this.c < 0.0f || this.c > 1.0f) {
                throw new IllegalArgumentException("Randomness must be between 0 and 1 (both inclusive)");
            } else if (this.d < 1.0f) {
                throw new IllegalArgumentException("Multiplier can't be less than 1");
            } else if (this.e <= 0) {
                throw new IllegalArgumentException("Max attempts can't be negative or zero");
            }
        }
    }

    b(a aVar) {
        this.c = aVar.a;
        this.d = aVar.b;
        this.e = aVar.c;
        this.f = aVar.d;
        this.g = aVar.e;
        a();
    }

    public void a() {
        this.a = this.c;
        this.b = 0;
    }

    public long b() {
        if (this.b >= this.g) {
            return -100;
        }
        this.b++;
        float f = ((float) this.a) * (1.0f - this.e);
        float f2 = ((float) this.a) * (1.0f + this.e);
        if (this.a <= this.d) {
            this.a = Math.min((long) (((float) this.a) * this.f), this.d);
        }
        return (long) (f + (this.h.nextFloat() * (f2 - f)));
    }
}
