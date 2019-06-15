package com.fasterxml.jackson.core.a;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.io.c;
import com.fasterxml.jackson.core.io.f;
import com.fasterxml.jackson.core.util.a;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class b extends c {
    static final BigInteger s = BigInteger.valueOf(-2147483648L);
    static final BigInteger t = BigInteger.valueOf(2147483647L);
    static final BigInteger u = BigInteger.valueOf(Long.MIN_VALUE);
    static final BigInteger v = BigInteger.valueOf(Long.MAX_VALUE);
    static final BigDecimal w = new BigDecimal(u);
    static final BigDecimal x = new BigDecimal(v);
    static final BigDecimal y = new BigDecimal(s);
    static final BigDecimal z = new BigDecimal(t);
    protected int A = 0;
    protected int B;
    protected long C;
    protected double D;
    protected BigInteger E;
    protected BigDecimal F;
    protected boolean G;
    protected int H;
    protected int I;
    protected int J;
    protected final c b;
    protected boolean c;
    protected int d = 0;
    protected int e = 0;
    protected long f = 0;
    protected int g = 1;
    protected int h = 0;
    protected long i = 0;
    protected int j = 1;
    protected int k = 0;
    protected com.fasterxml.jackson.core.b.c l;
    protected JsonToken m;
    protected final com.fasterxml.jackson.core.util.b n;
    protected char[] o = null;
    protected boolean p = false;
    protected a q = null;
    protected byte[] r;

    public abstract boolean p() throws IOException;

    public abstract void q() throws IOException, JsonParseException;

    public abstract void r() throws IOException;

    protected b(c cVar, int i) {
        this.a = i;
        this.b = cVar;
        this.n = cVar.d();
        this.l = com.fasterxml.jackson.core.b.c.g();
    }

    public String d() throws IOException, JsonParseException {
        if (this.K == JsonToken.START_OBJECT || this.K == JsonToken.START_ARRAY) {
            return this.l.i().h();
        }
        return this.l.h();
    }

    public void close() throws IOException {
        if (!this.c) {
            this.c = true;
            try {
                r();
            } finally {
                s();
            }
        }
    }

    public JsonLocation e() {
        int i = (this.d - this.h) + 1;
        return new JsonLocation(this.b.a(), (this.f + ((long) this.d)) - 1, this.g, i);
    }

    /* Access modifiers changed, original: protected|final */
    public final void o() throws IOException {
        if (!p()) {
            C();
        }
    }

    /* Access modifiers changed, original: protected */
    public void s() throws IOException {
        this.n.a();
        char[] cArr = this.o;
        if (cArr != null) {
            this.o = null;
            this.b.c(cArr);
        }
    }

    /* Access modifiers changed, original: protected */
    public void t() throws JsonParseException {
        if (!this.l.b()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(": expected close marker for ");
            stringBuilder.append(this.l.d());
            stringBuilder.append(" (from ");
            stringBuilder.append(this.l.a(this.b.a()));
            stringBuilder.append(")");
            c(stringBuilder.toString());
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(int i, char c) throws JsonParseException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("");
        stringBuilder.append(this.l.a(this.b.a()));
        String stringBuilder2 = stringBuilder.toString();
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append("Unexpected close marker '");
        stringBuilder3.append((char) i);
        stringBuilder3.append("': expected '");
        stringBuilder3.append(c);
        stringBuilder3.append("' (for ");
        stringBuilder3.append(this.l.d());
        stringBuilder3.append(" starting at ");
        stringBuilder3.append(stringBuilder2);
        stringBuilder3.append(")");
        d(stringBuilder3.toString());
    }

    /* Access modifiers changed, original: protected|final */
    public final JsonToken a(boolean z, int i, int i2, int i3) {
        if (i2 >= 1 || i3 >= 1) {
            return b(z, i, i2, i3);
        }
        return a(z, i);
    }

    /* Access modifiers changed, original: protected|final */
    public final JsonToken a(boolean z, int i) {
        this.G = z;
        this.H = i;
        this.I = 0;
        this.J = 0;
        this.A = 0;
        return JsonToken.VALUE_NUMBER_INT;
    }

    /* Access modifiers changed, original: protected|final */
    public final JsonToken b(boolean z, int i, int i2, int i3) {
        this.G = z;
        this.H = i;
        this.I = i2;
        this.J = i3;
        this.A = 0;
        return JsonToken.VALUE_NUMBER_FLOAT;
    }

    /* Access modifiers changed, original: protected|final */
    public final JsonToken a(String str, double d) {
        this.n.a(str);
        this.D = d;
        this.A = 8;
        return JsonToken.VALUE_NUMBER_FLOAT;
    }

    public int i() throws IOException, JsonParseException {
        if ((this.A & 1) == 0) {
            if (this.A == 0) {
                a(1);
            }
            if ((this.A & 1) == 0) {
                u();
            }
        }
        return this.B;
    }

    public long j() throws IOException, JsonParseException {
        if ((this.A & 2) == 0) {
            if (this.A == 0) {
                a(2);
            }
            if ((this.A & 2) == 0) {
                v();
            }
        }
        return this.C;
    }

    public BigInteger k() throws IOException, JsonParseException {
        if ((this.A & 4) == 0) {
            if (this.A == 0) {
                a(4);
            }
            if ((this.A & 4) == 0) {
                w();
            }
        }
        return this.E;
    }

    public float l() throws IOException, JsonParseException {
        return (float) m();
    }

    public double m() throws IOException, JsonParseException {
        if ((this.A & 8) == 0) {
            if (this.A == 0) {
                a(8);
            }
            if ((this.A & 8) == 0) {
                x();
            }
        }
        return this.D;
    }

    public BigDecimal n() throws IOException, JsonParseException {
        if ((this.A & 16) == 0) {
            if (this.A == 0) {
                a(16);
            }
            if ((this.A & 16) == 0) {
                y();
            }
        }
        return this.F;
    }

    /* Access modifiers changed, original: protected */
    public void a(int i) throws IOException, JsonParseException {
        if (this.K == JsonToken.VALUE_NUMBER_INT) {
            char[] e = this.n.e();
            int d = this.n.d();
            int i2 = this.H;
            if (this.G) {
                d++;
            }
            if (i2 <= 9) {
                i = f.a(e, d, i2);
                if (this.G) {
                    i = -i;
                }
                this.B = i;
                this.A = 1;
            } else if (i2 <= 18) {
                long b = f.b(e, d, i2);
                if (this.G) {
                    b = -b;
                }
                if (i2 == 10) {
                    if (this.G) {
                        if (b >= -2147483648L) {
                            this.B = (int) b;
                            this.A = 1;
                            return;
                        }
                    } else if (b <= 2147483647L) {
                        this.B = (int) b;
                        this.A = 1;
                        return;
                    }
                }
                this.C = b;
                this.A = 2;
            } else {
                a(i, e, d, i2);
            }
        } else if (this.K == JsonToken.VALUE_NUMBER_FLOAT) {
            d(i);
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Current token (");
            stringBuilder.append(this.K);
            stringBuilder.append(") not numeric, can not use numeric value accessors");
            d(stringBuilder.toString());
        }
    }

    private void d(int i) throws IOException, JsonParseException {
        if (i == 16) {
            try {
                this.F = this.n.h();
                this.A = 16;
                return;
            } catch (NumberFormatException e) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Malformed numeric value '");
                stringBuilder.append(this.n.f());
                stringBuilder.append("'");
                a(stringBuilder.toString(), e);
                return;
            }
        }
        this.D = this.n.i();
        this.A = 8;
    }

    private void a(int i, char[] cArr, int i2, int i3) throws IOException, JsonParseException {
        String f = this.n.f();
        try {
            if (f.a(cArr, i2, i3, this.G)) {
                this.C = Long.parseLong(f);
                this.A = 2;
                return;
            }
            this.E = new BigInteger(f);
            this.A = 4;
        } catch (NumberFormatException e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Malformed numeric value '");
            stringBuilder.append(f);
            stringBuilder.append("'");
            a(stringBuilder.toString(), e);
        }
    }

    /* Access modifiers changed, original: protected */
    public void u() throws IOException, JsonParseException {
        if ((this.A & 2) != 0) {
            int i = (int) this.C;
            if (((long) i) != this.C) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Numeric value (");
                stringBuilder.append(f());
                stringBuilder.append(") out of range of int");
                d(stringBuilder.toString());
            }
            this.B = i;
        } else if ((this.A & 4) != 0) {
            if (s.compareTo(this.E) > 0 || t.compareTo(this.E) < 0) {
                z();
            }
            this.B = this.E.intValue();
        } else if ((this.A & 8) != 0) {
            if (this.D < -2.147483648E9d || this.D > 2.147483647E9d) {
                z();
            }
            this.B = (int) this.D;
        } else if ((this.A & 16) != 0) {
            if (y.compareTo(this.F) > 0 || z.compareTo(this.F) < 0) {
                z();
            }
            this.B = this.F.intValue();
        } else {
            E();
        }
        this.A |= 1;
    }

    /* Access modifiers changed, original: protected */
    public void v() throws IOException, JsonParseException {
        if ((this.A & 1) != 0) {
            this.C = (long) this.B;
        } else if ((this.A & 4) != 0) {
            if (u.compareTo(this.E) > 0 || v.compareTo(this.E) < 0) {
                A();
            }
            this.C = this.E.longValue();
        } else if ((this.A & 8) != 0) {
            if (this.D < -9.223372036854776E18d || this.D > 9.223372036854776E18d) {
                A();
            }
            this.C = (long) this.D;
        } else if ((this.A & 16) != 0) {
            if (w.compareTo(this.F) > 0 || x.compareTo(this.F) < 0) {
                A();
            }
            this.C = this.F.longValue();
        } else {
            E();
        }
        this.A |= 2;
    }

    /* Access modifiers changed, original: protected */
    public void w() throws IOException, JsonParseException {
        if ((this.A & 16) != 0) {
            this.E = this.F.toBigInteger();
        } else if ((this.A & 2) != 0) {
            this.E = BigInteger.valueOf(this.C);
        } else if ((this.A & 1) != 0) {
            this.E = BigInteger.valueOf((long) this.B);
        } else if ((this.A & 8) != 0) {
            this.E = BigDecimal.valueOf(this.D).toBigInteger();
        } else {
            E();
        }
        this.A |= 4;
    }

    /* Access modifiers changed, original: protected */
    public void x() throws IOException, JsonParseException {
        if ((this.A & 16) != 0) {
            this.D = this.F.doubleValue();
        } else if ((this.A & 4) != 0) {
            this.D = this.E.doubleValue();
        } else if ((this.A & 2) != 0) {
            this.D = (double) this.C;
        } else if ((this.A & 1) != 0) {
            this.D = (double) this.B;
        } else {
            E();
        }
        this.A |= 8;
    }

    /* Access modifiers changed, original: protected */
    public void y() throws IOException, JsonParseException {
        if ((this.A & 8) != 0) {
            this.F = new BigDecimal(f());
        } else if ((this.A & 4) != 0) {
            this.F = new BigDecimal(this.E);
        } else if ((this.A & 2) != 0) {
            this.F = BigDecimal.valueOf(this.C);
        } else if ((this.A & 1) != 0) {
            this.F = BigDecimal.valueOf((long) this.B);
        } else {
            E();
        }
        this.A |= 16;
    }

    /* Access modifiers changed, original: protected */
    public void a(int i, String str) throws JsonParseException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unexpected character (");
        stringBuilder.append(c.c(i));
        stringBuilder.append(") in numeric value");
        String stringBuilder2 = stringBuilder.toString();
        if (str != null) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(stringBuilder2);
            stringBuilder.append(": ");
            stringBuilder.append(str);
            stringBuilder2 = stringBuilder.toString();
        }
        d(stringBuilder2);
    }

    /* Access modifiers changed, original: protected */
    public void b(String str) throws JsonParseException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Invalid numeric value: ");
        stringBuilder.append(str);
        d(stringBuilder.toString());
    }

    /* Access modifiers changed, original: protected */
    public void z() throws IOException, JsonParseException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Numeric value (");
        stringBuilder.append(f());
        stringBuilder.append(") out of range of int (");
        stringBuilder.append(Integer.MIN_VALUE);
        stringBuilder.append(" - ");
        stringBuilder.append(Integer.MAX_VALUE);
        stringBuilder.append(")");
        d(stringBuilder.toString());
    }

    /* Access modifiers changed, original: protected */
    public void A() throws IOException, JsonParseException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Numeric value (");
        stringBuilder.append(f());
        stringBuilder.append(") out of range of long (");
        stringBuilder.append(Long.MIN_VALUE);
        stringBuilder.append(" - ");
        stringBuilder.append(Long.MAX_VALUE);
        stringBuilder.append(")");
        d(stringBuilder.toString());
    }

    /* Access modifiers changed, original: protected */
    public char B() throws IOException, JsonParseException {
        throw new UnsupportedOperationException();
    }
}
