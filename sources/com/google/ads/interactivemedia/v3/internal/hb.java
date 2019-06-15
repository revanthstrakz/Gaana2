package com.google.ads.interactivemedia.v3.internal;

import java.math.BigDecimal;

public final class hb extends Number {
    private final String a;

    public hb(String str) {
        this.a = str;
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0007 */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:6:0x000e, code skipped:
            return (int) java.lang.Long.parseLong(r2.a);
     */
    /* JADX WARNING: Missing block: B:9:0x001a, code skipped:
            return new java.math.BigDecimal(r2.a).intValue();
     */
    public int intValue() {
        /*
        r2 = this;
        r0 = r2.a;	 Catch:{ NumberFormatException -> 0x0007 }
        r0 = java.lang.Integer.parseInt(r0);	 Catch:{ NumberFormatException -> 0x0007 }
        return r0;
    L_0x0007:
        r0 = r2.a;	 Catch:{ NumberFormatException -> 0x000f }
        r0 = java.lang.Long.parseLong(r0);	 Catch:{ NumberFormatException -> 0x000f }
        r0 = (int) r0;
        return r0;
    L_0x000f:
        r0 = new java.math.BigDecimal;
        r1 = r2.a;
        r0.<init>(r1);
        r0 = r0.intValue();
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.hb.intValue():int");
    }

    public long longValue() {
        try {
            return Long.parseLong(this.a);
        } catch (NumberFormatException unused) {
            return new BigDecimal(this.a).longValue();
        }
    }

    public float floatValue() {
        return Float.parseFloat(this.a);
    }

    public double doubleValue() {
        return Double.parseDouble(this.a);
    }

    public String toString() {
        return this.a;
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof hb)) {
            return false;
        }
        hb hbVar = (hb) obj;
        if (!(this.a == hbVar.a || this.a.equals(hbVar.a))) {
            z = false;
        }
        return z;
    }
}
