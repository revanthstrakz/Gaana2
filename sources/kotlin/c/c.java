package kotlin.c;

public final class c extends a {
    public static final a b = new a();
    private static final c c = new c(1, 0);

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(a aVar) {
            this();
        }

        public final c a() {
            return c.c;
        }
    }

    public c(int i, int i2) {
        super(i, i2, 1);
    }

    public Integer f() {
        return Integer.valueOf(a());
    }

    public Integer g() {
        return Integer.valueOf(b());
    }

    public boolean e() {
        return a() > b();
    }

    /* JADX WARNING: Missing block: B:9:0x0027, code skipped:
            if (b() == r3.b()) goto L_0x0029;
     */
    public boolean equals(java.lang.Object r3) {
        /*
        r2 = this;
        r0 = r3 instanceof kotlin.c.c;
        if (r0 == 0) goto L_0x002b;
    L_0x0004:
        r0 = r2.e();
        if (r0 == 0) goto L_0x0013;
    L_0x000a:
        r0 = r3;
        r0 = (kotlin.c.c) r0;
        r0 = r0.e();
        if (r0 != 0) goto L_0x0029;
    L_0x0013:
        r0 = r2.a();
        r3 = (kotlin.c.c) r3;
        r1 = r3.a();
        if (r0 != r1) goto L_0x002b;
    L_0x001f:
        r0 = r2.b();
        r3 = r3.b();
        if (r0 != r3) goto L_0x002b;
    L_0x0029:
        r3 = 1;
        goto L_0x002c;
    L_0x002b:
        r3 = 0;
    L_0x002c:
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.c.c.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        return e() ? -1 : (31 * a()) + b();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(a());
        stringBuilder.append("..");
        stringBuilder.append(b());
        return stringBuilder.toString();
    }
}
