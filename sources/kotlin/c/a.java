package kotlin.c;

import kotlin.a.r;

public class a implements Iterable<Integer> {
    public static final a a = new a();
    private final int b;
    private final int c;
    private final int d;

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(a aVar) {
            this();
        }

        public final a a(int i, int i2, int i3) {
            return new a(i, i2, i3);
        }
    }

    public a(int i, int i2, int i3) {
        if (i3 == 0) {
            throw new IllegalArgumentException("Step must be non-zero.");
        } else if (i3 == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.");
        } else {
            this.b = i;
            this.c = kotlin.b.a.a(i, i2, i3);
            this.d = i3;
        }
    }

    public final int a() {
        return this.b;
    }

    public final int b() {
        return this.c;
    }

    public final int c() {
        return this.d;
    }

    /* renamed from: d */
    public r iterator() {
        return new b(this.b, this.c, this.d);
    }

    public boolean e() {
        if (this.d > 0) {
            if (this.b <= this.c) {
                return false;
            }
        } else if (this.b >= this.c) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Missing block: B:11:0x0025, code skipped:
            if (r2.d == r3.d) goto L_0x0027;
     */
    public boolean equals(java.lang.Object r3) {
        /*
        r2 = this;
        r0 = r3 instanceof kotlin.c.a;
        if (r0 == 0) goto L_0x0029;
    L_0x0004:
        r0 = r2.e();
        if (r0 == 0) goto L_0x0013;
    L_0x000a:
        r0 = r3;
        r0 = (kotlin.c.a) r0;
        r0 = r0.e();
        if (r0 != 0) goto L_0x0027;
    L_0x0013:
        r0 = r2.b;
        r3 = (kotlin.c.a) r3;
        r1 = r3.b;
        if (r0 != r1) goto L_0x0029;
    L_0x001b:
        r0 = r2.c;
        r1 = r3.c;
        if (r0 != r1) goto L_0x0029;
    L_0x0021:
        r0 = r2.d;
        r3 = r3.d;
        if (r0 != r3) goto L_0x0029;
    L_0x0027:
        r3 = 1;
        goto L_0x002a;
    L_0x0029:
        r3 = 0;
    L_0x002a:
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.c.a.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        if (e()) {
            return -1;
        }
        return this.d + (31 * ((this.b * 31) + this.c));
    }

    public String toString() {
        StringBuilder stringBuilder;
        int i;
        if (this.d > 0) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(this.b);
            stringBuilder.append("..");
            stringBuilder.append(this.c);
            stringBuilder.append(" step ");
            i = this.d;
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append(this.b);
            stringBuilder.append(" downTo ");
            stringBuilder.append(this.c);
            stringBuilder.append(" step ");
            i = -this.d;
        }
        stringBuilder.append(i);
        return stringBuilder.toString();
    }
}
