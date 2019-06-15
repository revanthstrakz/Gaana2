package com.google.ads.interactivemedia.v3.internal;

import com.moe.pushlibrary.MoEWorker;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;

public class hx implements Closeable {
    private static final char[] b = ")]}'\n".toCharArray();
    int a = 0;
    private final Reader c;
    private boolean d = false;
    private final char[] e = new char[1024];
    private int f = 0;
    private int g = 0;
    private int h = 0;
    private int i = 0;
    private long j;
    private int k;
    private String l;
    private int[] m = new int[32];
    private int n = 0;
    private String[] o;
    private int[] p;

    public hx(Reader reader) {
        int[] iArr = this.m;
        int i = this.n;
        this.n = i + 1;
        iArr[i] = 6;
        this.o = new String[32];
        this.p = new int[32];
        if (reader == null) {
            throw new NullPointerException("in == null");
        }
        this.c = reader;
    }

    public final void a(boolean z) {
        this.d = z;
    }

    public final boolean q() {
        return this.d;
    }

    public void a() throws IOException {
        int i = this.a;
        if (i == 0) {
            i = r();
        }
        if (i == 3) {
            a(1);
            this.p[this.n - 1] = 0;
            this.a = 0;
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Expected BEGIN_ARRAY but was ");
        stringBuilder.append(f());
        stringBuilder.append(x());
        throw new IllegalStateException(stringBuilder.toString());
    }

    public void b() throws IOException {
        int i = this.a;
        if (i == 0) {
            i = r();
        }
        if (i == 4) {
            this.n--;
            int[] iArr = this.p;
            int i2 = this.n - 1;
            iArr[i2] = iArr[i2] + 1;
            this.a = 0;
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Expected END_ARRAY but was ");
        stringBuilder.append(f());
        stringBuilder.append(x());
        throw new IllegalStateException(stringBuilder.toString());
    }

    public void c() throws IOException {
        int i = this.a;
        if (i == 0) {
            i = r();
        }
        if (i == 1) {
            a(3);
            this.a = 0;
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Expected BEGIN_OBJECT but was ");
        stringBuilder.append(f());
        stringBuilder.append(x());
        throw new IllegalStateException(stringBuilder.toString());
    }

    public void d() throws IOException {
        int i = this.a;
        if (i == 0) {
            i = r();
        }
        if (i == 2) {
            this.n--;
            this.o[this.n] = null;
            int[] iArr = this.p;
            int i2 = this.n - 1;
            iArr[i2] = iArr[i2] + 1;
            this.a = 0;
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Expected END_OBJECT but was ");
        stringBuilder.append(f());
        stringBuilder.append(x());
        throw new IllegalStateException(stringBuilder.toString());
    }

    public boolean e() throws IOException {
        int i = this.a;
        if (i == 0) {
            i = r();
        }
        return (i == 2 || i == 4) ? false : true;
    }

    public hy f() throws IOException {
        int i = this.a;
        if (i == 0) {
            i = r();
        }
        switch (i) {
            case 1:
                return hy.BEGIN_OBJECT;
            case 2:
                return hy.END_OBJECT;
            case 3:
                return hy.BEGIN_ARRAY;
            case 4:
                return hy.END_ARRAY;
            case 5:
            case 6:
                return hy.BOOLEAN;
            case 7:
                return hy.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return hy.STRING;
            case 12:
            case 13:
            case 14:
                return hy.NAME;
            case 15:
            case 16:
                return hy.NUMBER;
            case 17:
                return hy.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public int r() throws IOException {
        int b;
        int i = this.m[this.n - 1];
        if (i == 1) {
            this.m[this.n - 1] = 2;
        } else if (i == 2) {
            b = b(true);
            if (b != 44) {
                if (b == 59) {
                    v();
                } else if (b != 93) {
                    throw b("Unterminated array");
                } else {
                    this.a = 4;
                    return 4;
                }
            }
        } else if (i == 3 || i == 5) {
            int b2;
            this.m[this.n - 1] = 4;
            if (i == 5) {
                b2 = b(true);
                if (b2 != 44) {
                    if (b2 == 59) {
                        v();
                    } else if (b2 != 125) {
                        throw b("Unterminated object");
                    } else {
                        this.a = 2;
                        return 2;
                    }
                }
            }
            b2 = b(true);
            if (b2 == 34) {
                this.a = 13;
                return 13;
            } else if (b2 == 39) {
                v();
                this.a = 12;
                return 12;
            } else if (b2 != 125) {
                v();
                this.f--;
                if (a((char) b2)) {
                    this.a = 14;
                    return 14;
                }
                throw b("Expected name");
            } else if (i != 5) {
                this.a = 2;
                return 2;
            } else {
                throw b("Expected name");
            }
        } else if (i == 4) {
            this.m[this.n - 1] = 5;
            b = b(true);
            if (b != 58) {
                if (b != 61) {
                    throw b("Expected ':'");
                }
                v();
                if ((this.f < this.g || b(1)) && this.e[this.f] == '>') {
                    this.f++;
                }
            }
        } else if (i == 6) {
            if (this.d) {
                z();
            }
            this.m[this.n - 1] = 7;
        } else if (i == 7) {
            if (b(false) == -1) {
                this.a = 17;
                return 17;
            }
            v();
            this.f--;
        } else if (i == 8) {
            throw new IllegalStateException("JsonReader is closed");
        }
        b = b(true);
        if (b == 34) {
            this.a = 9;
            return 9;
        } else if (b != 39) {
            if (!(b == 44 || b == 59)) {
                if (b == 91) {
                    this.a = 3;
                    return 3;
                } else if (b != 93) {
                    if (b != MoEWorker.REQ_CODE_SEND_DATA) {
                        this.f--;
                        i = o();
                        if (i != 0) {
                            return i;
                        }
                        i = s();
                        if (i != 0) {
                            return i;
                        }
                        if (a(this.e[this.f])) {
                            v();
                            this.a = 10;
                            return 10;
                        }
                        throw b("Expected value");
                    }
                    this.a = 1;
                    return 1;
                } else if (i == 1) {
                    this.a = 4;
                    return 4;
                }
            }
            if (i == 1 || i == 2) {
                v();
                this.f--;
                this.a = 7;
                return 7;
            }
            throw b("Unexpected value");
        } else {
            v();
            this.a = 8;
            return 8;
        }
    }

    private int o() throws IOException {
        String str;
        int i;
        char c = this.e[this.f];
        String str2;
        if (c == 't' || c == 'T') {
            str = "true";
            str2 = "TRUE";
            i = 5;
        } else if (c == 'f' || c == 'F') {
            str = InternalLogger.EVENT_PARAM_EXTRAS_FALSE;
            str2 = "FALSE";
            i = 6;
        } else if (c != 'n' && c != 'N') {
            return 0;
        } else {
            str = "null";
            str2 = "NULL";
            i = 7;
        }
        int length = str.length();
        int i2 = 1;
        while (i2 < length) {
            if (this.f + i2 >= this.g && !b(i2 + 1)) {
                return 0;
            }
            char c2 = this.e[this.f + i2];
            if (c2 != str.charAt(i2) && c2 != str2.charAt(i2)) {
                return 0;
            }
            i2++;
        }
        if ((this.f + length < this.g || b(length + 1)) && a(this.e[this.f + length])) {
            return 0;
        }
        this.f += length;
        this.a = i;
        return i;
    }

    private int s() throws java.io.IOException {
        /*
        r21 = this;
        r0 = r21;
        r1 = r0.e;
        r2 = r0.f;
        r3 = r0.g;
        r6 = 1;
        r7 = 0;
        r8 = r3;
        r10 = r6;
        r3 = r7;
        r9 = r3;
        r13 = r9;
        r11 = 0;
    L_0x0011:
        r14 = r2 + r3;
        r15 = 2;
        if (r14 != r8) goto L_0x0028;
    L_0x0016:
        r2 = r1.length;
        if (r3 != r2) goto L_0x001a;
    L_0x0019:
        return r7;
    L_0x001a:
        r2 = r3 + 1;
        r2 = r0.b(r2);
        if (r2 != 0) goto L_0x0024;
    L_0x0022:
        goto L_0x0099;
    L_0x0024:
        r2 = r0.f;
        r8 = r0.g;
    L_0x0028:
        r14 = r2 + r3;
        r14 = r1[r14];
        r7 = 43;
        r4 = 3;
        r5 = 5;
        if (r14 == r7) goto L_0x00e9;
    L_0x0032:
        r7 = 69;
        if (r14 == r7) goto L_0x00dd;
    L_0x0036:
        r7 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        if (r14 == r7) goto L_0x00dd;
    L_0x003a:
        switch(r14) {
            case 45: goto L_0x00d0;
            case 46: goto L_0x00c9;
            default: goto L_0x003d;
        };
    L_0x003d:
        r7 = 48;
        if (r14 < r7) goto L_0x0093;
    L_0x0041:
        r7 = 57;
        if (r14 <= r7) goto L_0x0046;
    L_0x0045:
        goto L_0x0093;
    L_0x0046:
        if (r9 == r6) goto L_0x0088;
    L_0x0048:
        if (r9 != 0) goto L_0x004b;
    L_0x004a:
        goto L_0x0088;
    L_0x004b:
        if (r9 != r15) goto L_0x0077;
    L_0x004d:
        r18 = 0;
        r4 = (r11 > r18 ? 1 : (r11 == r18 ? 0 : -1));
        if (r4 != 0) goto L_0x0055;
    L_0x0053:
        r4 = 0;
        return r4;
    L_0x0055:
        r4 = 10;
        r4 = r4 * r11;
        r14 = r14 + -48;
        r14 = (long) r14;
        r16 = r4 - r14;
        r4 = -922337203685477580; // 0xf333333333333334 float:4.1723254E-8 double:-8.390303882365713E246;
        r7 = (r11 > r4 ? 1 : (r11 == r4 ? 0 : -1));
        if (r7 > 0) goto L_0x0071;
    L_0x0066:
        r7 = (r11 > r4 ? 1 : (r11 == r4 ? 0 : -1));
        if (r7 != 0) goto L_0x006f;
    L_0x006a:
        r4 = (r16 > r11 ? 1 : (r16 == r11 ? 0 : -1));
        if (r4 >= 0) goto L_0x006f;
    L_0x006e:
        goto L_0x0071;
    L_0x006f:
        r4 = 0;
        goto L_0x0072;
    L_0x0071:
        r4 = r6;
    L_0x0072:
        r4 = r4 & r10;
        r10 = r4;
        r11 = r16;
        goto L_0x0090;
    L_0x0077:
        r18 = 0;
        if (r9 != r4) goto L_0x007f;
    L_0x007b:
        r7 = 0;
        r9 = 4;
        goto L_0x00f0;
    L_0x007f:
        if (r9 == r5) goto L_0x0084;
    L_0x0081:
        r4 = 6;
        if (r9 != r4) goto L_0x0090;
    L_0x0084:
        r7 = 0;
        r9 = 7;
        goto L_0x00f0;
    L_0x0088:
        r18 = 0;
        r14 = r14 + -48;
        r4 = -r14;
        r4 = (long) r4;
        r11 = r4;
        r9 = r15;
    L_0x0090:
        r7 = 0;
        goto L_0x00f0;
    L_0x0093:
        r1 = r0.a(r14);
        if (r1 != 0) goto L_0x00c7;
    L_0x0099:
        if (r9 != r15) goto L_0x00b5;
    L_0x009b:
        if (r10 == 0) goto L_0x00b5;
    L_0x009d:
        r1 = -9223372036854775808;
        r4 = (r11 > r1 ? 1 : (r11 == r1 ? 0 : -1));
        if (r4 != 0) goto L_0x00a5;
    L_0x00a3:
        if (r13 == 0) goto L_0x00b5;
    L_0x00a5:
        if (r13 == 0) goto L_0x00a8;
    L_0x00a7:
        goto L_0x00a9;
    L_0x00a8:
        r11 = -r11;
    L_0x00a9:
        r0.j = r11;
        r1 = r0.f;
        r1 = r1 + r3;
        r0.f = r1;
        r1 = 15;
        r0.a = r1;
        return r1;
    L_0x00b5:
        if (r9 == r15) goto L_0x00c0;
    L_0x00b7:
        r1 = 4;
        if (r9 == r1) goto L_0x00c0;
    L_0x00ba:
        r1 = 7;
        if (r9 != r1) goto L_0x00be;
    L_0x00bd:
        goto L_0x00c0;
    L_0x00be:
        r7 = 0;
        return r7;
    L_0x00c0:
        r0.k = r3;
        r1 = 16;
        r0.a = r1;
        return r1;
    L_0x00c7:
        r7 = 0;
        return r7;
    L_0x00c9:
        r7 = 0;
        r18 = 0;
        if (r9 != r15) goto L_0x00cf;
    L_0x00ce:
        goto L_0x00ef;
    L_0x00cf:
        return r7;
    L_0x00d0:
        r4 = 6;
        r7 = 0;
        r18 = 0;
        if (r9 != 0) goto L_0x00d9;
    L_0x00d6:
        r9 = r6;
        r13 = r9;
        goto L_0x00f0;
    L_0x00d9:
        if (r9 != r5) goto L_0x00dc;
    L_0x00db:
        goto L_0x00ef;
    L_0x00dc:
        return r7;
    L_0x00dd:
        r7 = 0;
        r18 = 0;
        if (r9 == r15) goto L_0x00e7;
    L_0x00e2:
        r4 = 4;
        if (r9 != r4) goto L_0x00e6;
    L_0x00e5:
        goto L_0x00e7;
    L_0x00e6:
        return r7;
    L_0x00e7:
        r9 = r5;
        goto L_0x00f0;
    L_0x00e9:
        r4 = 6;
        r7 = 0;
        r18 = 0;
        if (r9 != r5) goto L_0x00f4;
    L_0x00ef:
        r9 = r4;
    L_0x00f0:
        r3 = r3 + 1;
        goto L_0x0011;
    L_0x00f4:
        return r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.hx.s():int");
    }

    private boolean a(char c) throws IOException {
        switch (c) {
            case 9:
            case 10:
            case 12:
            case 13:
            case ' ':
            case ',':
            case ':':
            case '[':
            case ']':
            case MoEWorker.REQ_CODE_SEND_DATA /*123*/:
            case '}':
                break;
            case '#':
            case '/':
            case ';':
            case '=':
            case '\\':
                v();
                break;
            default:
                return true;
        }
        return false;
    }

    public String g() throws IOException {
        String t;
        int i = this.a;
        if (i == 0) {
            i = r();
        }
        if (i == 14) {
            t = t();
        } else if (i == 12) {
            t = b('\'');
        } else if (i == 13) {
            t = b('\"');
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Expected a name but was ");
            stringBuilder.append(f());
            stringBuilder.append(x());
            throw new IllegalStateException(stringBuilder.toString());
        }
        this.a = 0;
        this.o[this.n - 1] = t;
        return t;
    }

    public String h() throws IOException {
        String t;
        int i = this.a;
        if (i == 0) {
            i = r();
        }
        if (i == 10) {
            t = t();
        } else if (i == 8) {
            t = b('\'');
        } else if (i == 9) {
            t = b('\"');
        } else if (i == 11) {
            t = this.l;
            this.l = null;
        } else if (i == 15) {
            t = Long.toString(this.j);
        } else if (i == 16) {
            t = new String(this.e, this.f, this.k);
            this.f += this.k;
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Expected a string but was ");
            stringBuilder.append(f());
            stringBuilder.append(x());
            throw new IllegalStateException(stringBuilder.toString());
        }
        this.a = 0;
        int[] iArr = this.p;
        int i2 = this.n - 1;
        iArr[i2] = iArr[i2] + 1;
        return t;
    }

    public boolean i() throws IOException {
        int i = this.a;
        if (i == 0) {
            i = r();
        }
        int[] iArr;
        int i2;
        if (i == 5) {
            this.a = 0;
            iArr = this.p;
            i2 = this.n - 1;
            iArr[i2] = iArr[i2] + 1;
            return true;
        } else if (i == 6) {
            this.a = 0;
            iArr = this.p;
            i2 = this.n - 1;
            iArr[i2] = iArr[i2] + 1;
            return false;
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Expected a boolean but was ");
            stringBuilder.append(f());
            stringBuilder.append(x());
            throw new IllegalStateException(stringBuilder.toString());
        }
    }

    public void j() throws IOException {
        int i = this.a;
        if (i == 0) {
            i = r();
        }
        if (i == 7) {
            this.a = 0;
            int[] iArr = this.p;
            int i2 = this.n - 1;
            iArr[i2] = iArr[i2] + 1;
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Expected null but was ");
        stringBuilder.append(f());
        stringBuilder.append(x());
        throw new IllegalStateException(stringBuilder.toString());
    }

    public double k() throws IOException {
        int i = this.a;
        if (i == 0) {
            i = r();
        }
        if (i == 15) {
            this.a = 0;
            int[] iArr = this.p;
            int i2 = this.n - 1;
            iArr[i2] = iArr[i2] + 1;
            return (double) this.j;
        }
        if (i == 16) {
            this.l = new String(this.e, this.f, this.k);
            this.f += this.k;
        } else if (i == 8 || i == 9) {
            this.l = b(i == 8 ? '\'' : '\"');
        } else if (i == 10) {
            this.l = t();
        } else if (i != 11) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Expected a double but was ");
            stringBuilder.append(f());
            stringBuilder.append(x());
            throw new IllegalStateException(stringBuilder.toString());
        }
        this.a = 11;
        double parseDouble = Double.parseDouble(this.l);
        if (this.d || !(Double.isNaN(parseDouble) || Double.isInfinite(parseDouble))) {
            this.l = null;
            this.a = 0;
            int[] iArr2 = this.p;
            int i3 = this.n - 1;
            iArr2[i3] = iArr2[i3] + 1;
            return parseDouble;
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("JSON forbids NaN and infinities: ");
        stringBuilder2.append(parseDouble);
        stringBuilder2.append(x());
        throw new ia(stringBuilder2.toString());
    }

    public long l() throws IOException {
        int i = this.a;
        if (i == 0) {
            i = r();
        }
        int[] iArr;
        int i2;
        if (i == 15) {
            this.a = 0;
            iArr = this.p;
            i2 = this.n - 1;
            iArr[i2] = iArr[i2] + 1;
            return this.j;
        }
        StringBuilder stringBuilder;
        if (i == 16) {
            this.l = new String(this.e, this.f, this.k);
            this.f += this.k;
        } else if (i == 8 || i == 9 || i == 10) {
            if (i == 10) {
                this.l = t();
            } else {
                this.l = b(i == 8 ? '\'' : '\"');
            }
            try {
                long parseLong = Long.parseLong(this.l);
                this.a = 0;
                int[] iArr2 = this.p;
                int i3 = this.n - 1;
                iArr2[i3] = iArr2[i3] + 1;
                return parseLong;
            } catch (NumberFormatException unused) {
            }
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Expected a long but was ");
            stringBuilder.append(f());
            stringBuilder.append(x());
            throw new IllegalStateException(stringBuilder.toString());
        }
        this.a = 11;
        double parseDouble = Double.parseDouble(this.l);
        long j = (long) parseDouble;
        if (((double) j) != parseDouble) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Expected a long but was ");
            stringBuilder.append(this.l);
            stringBuilder.append(x());
            throw new NumberFormatException(stringBuilder.toString());
        }
        this.l = null;
        this.a = 0;
        iArr = this.p;
        i2 = this.n - 1;
        iArr[i2] = iArr[i2] + 1;
        return j;
    }

    private String b(char c) throws IOException {
        char[] cArr = this.e;
        StringBuilder stringBuilder = new StringBuilder();
        do {
            int i = this.f;
            int i2 = this.g;
            while (true) {
                int i3 = i;
                while (i < i2) {
                    int i4 = i + 1;
                    char c2 = cArr[i];
                    if (c2 == c) {
                        this.f = i4;
                        stringBuilder.append(cArr, i3, (i4 - i3) - 1);
                        return stringBuilder.toString();
                    } else if (c2 == '\\') {
                        this.f = i4;
                        stringBuilder.append(cArr, i3, (i4 - i3) - 1);
                        stringBuilder.append(y());
                        i = this.f;
                        i2 = this.g;
                    } else {
                        if (c2 == 10) {
                            this.h++;
                            this.i = i4;
                        }
                        i = i4;
                    }
                }
                stringBuilder.append(cArr, i3, i - i3);
                this.f = i;
            }
        } while (b(1));
        throw b("Unterminated string");
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0052  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0048  */
    /* JADX WARNING: Missing block: B:13:0x002a, code skipped:
            r0 = r1;
     */
    private java.lang.String t() throws java.io.IOException {
        /*
        r5 = this;
        r0 = 0;
        r1 = 0;
        r2 = r1;
    L_0x0003:
        r1 = r0;
    L_0x0004:
        r3 = r5.f;
        r3 = r3 + r1;
        r4 = r5.g;
        if (r3 >= r4) goto L_0x001c;
    L_0x000b:
        r3 = r5.e;
        r4 = r5.f;
        r4 = r4 + r1;
        r3 = r3[r4];
        switch(r3) {
            case 9: goto L_0x002a;
            case 10: goto L_0x002a;
            case 12: goto L_0x002a;
            case 13: goto L_0x002a;
            case 32: goto L_0x002a;
            case 35: goto L_0x0018;
            case 44: goto L_0x002a;
            case 47: goto L_0x0018;
            case 58: goto L_0x002a;
            case 59: goto L_0x0018;
            case 61: goto L_0x0018;
            case 91: goto L_0x002a;
            case 92: goto L_0x0018;
            case 93: goto L_0x002a;
            case 123: goto L_0x002a;
            case 125: goto L_0x002a;
            default: goto L_0x0015;
        };
    L_0x0015:
        r1 = r1 + 1;
        goto L_0x0004;
    L_0x0018:
        r5.v();
        goto L_0x002a;
    L_0x001c:
        r3 = r5.e;
        r3 = r3.length;
        if (r1 >= r3) goto L_0x002c;
    L_0x0021:
        r3 = r1 + 1;
        r3 = r5.b(r3);
        if (r3 == 0) goto L_0x002a;
    L_0x0029:
        goto L_0x0004;
    L_0x002a:
        r0 = r1;
        goto L_0x0046;
    L_0x002c:
        if (r2 != 0) goto L_0x0033;
    L_0x002e:
        r2 = new java.lang.StringBuilder;
        r2.<init>();
    L_0x0033:
        r3 = r5.e;
        r4 = r5.f;
        r2.append(r3, r4, r1);
        r3 = r5.f;
        r3 = r3 + r1;
        r5.f = r3;
        r1 = 1;
        r1 = r5.b(r1);
        if (r1 != 0) goto L_0x0003;
    L_0x0046:
        if (r2 != 0) goto L_0x0052;
    L_0x0048:
        r1 = new java.lang.String;
        r2 = r5.e;
        r3 = r5.f;
        r1.<init>(r2, r3, r0);
        goto L_0x005d;
    L_0x0052:
        r1 = r5.e;
        r3 = r5.f;
        r2.append(r1, r3, r0);
        r1 = r2.toString();
    L_0x005d:
        r2 = r5.f;
        r2 = r2 + r0;
        r5.f = r2;
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.hx.t():java.lang.String");
    }

    private void c(char c) throws IOException {
        char[] cArr = this.e;
        do {
            int i = this.f;
            int i2 = this.g;
            while (i < i2) {
                int i3 = i + 1;
                char c2 = cArr[i];
                if (c2 == c) {
                    this.f = i3;
                    return;
                } else if (c2 == '\\') {
                    this.f = i3;
                    y();
                    i = this.f;
                    i2 = this.g;
                } else {
                    if (c2 == 10) {
                        this.h++;
                        this.i = i3;
                    }
                    i = i3;
                }
            }
            this.f = i;
        } while (b(1));
        throw b("Unterminated string");
    }

    private void u() throws IOException {
        do {
            int i = 0;
            while (this.f + i < this.g) {
                switch (this.e[this.f + i]) {
                    case 9:
                    case 10:
                    case 12:
                    case 13:
                    case ' ':
                    case ',':
                    case ':':
                    case '[':
                    case ']':
                    case MoEWorker.REQ_CODE_SEND_DATA /*123*/:
                    case '}':
                        break;
                    case '#':
                    case '/':
                    case ';':
                    case '=':
                    case '\\':
                        v();
                        break;
                    default:
                        i++;
                }
                this.f += i;
                return;
            }
            this.f += i;
        } while (b(1));
    }

    public int m() throws IOException {
        int i = this.a;
        if (i == 0) {
            i = r();
        }
        StringBuilder stringBuilder;
        int[] iArr;
        if (i == 15) {
            i = (int) this.j;
            if (this.j != ((long) i)) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("Expected an int but was ");
                stringBuilder.append(this.j);
                stringBuilder.append(x());
                throw new NumberFormatException(stringBuilder.toString());
            }
            this.a = 0;
            iArr = this.p;
            int i2 = this.n - 1;
            iArr[i2] = iArr[i2] + 1;
            return i;
        }
        int i3;
        if (i == 16) {
            this.l = new String(this.e, this.f, this.k);
            this.f += this.k;
        } else if (i == 8 || i == 9 || i == 10) {
            if (i == 10) {
                this.l = t();
            } else {
                this.l = b(i == 8 ? '\'' : '\"');
            }
            try {
                i = Integer.parseInt(this.l);
                this.a = 0;
                iArr = this.p;
                i3 = this.n - 1;
                iArr[i3] = iArr[i3] + 1;
                return i;
            } catch (NumberFormatException unused) {
            }
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Expected an int but was ");
            stringBuilder.append(f());
            stringBuilder.append(x());
            throw new IllegalStateException(stringBuilder.toString());
        }
        this.a = 11;
        double parseDouble = Double.parseDouble(this.l);
        i3 = (int) parseDouble;
        if (((double) i3) != parseDouble) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Expected an int but was ");
            stringBuilder.append(this.l);
            stringBuilder.append(x());
            throw new NumberFormatException(stringBuilder.toString());
        }
        this.l = null;
        this.a = 0;
        int[] iArr2 = this.p;
        int i4 = this.n - 1;
        iArr2[i4] = iArr2[i4] + 1;
        return i3;
    }

    public void close() throws IOException {
        this.a = 0;
        this.m[0] = 8;
        this.n = 1;
        this.c.close();
    }

    public void n() throws IOException {
        int i = 0;
        do {
            int i2 = this.a;
            if (i2 == 0) {
                i2 = r();
            }
            if (i2 == 3) {
                a(1);
                i++;
            } else if (i2 == 1) {
                a(3);
                i++;
            } else if (i2 == 4) {
                this.n--;
                i--;
            } else if (i2 == 2) {
                this.n--;
                i--;
            } else if (i2 == 14 || i2 == 10) {
                u();
            } else if (i2 == 8 || i2 == 12) {
                c('\'');
            } else if (i2 == 9 || i2 == 13) {
                c('\"');
            } else if (i2 == 16) {
                this.f += this.k;
            }
            this.a = 0;
        } while (i != 0);
        int[] iArr = this.p;
        i = this.n - 1;
        iArr[i] = iArr[i] + 1;
        this.o[this.n - 1] = "null";
    }

    private void a(int i) {
        int[] iArr;
        if (this.n == this.m.length) {
            iArr = new int[(this.n * 2)];
            int[] iArr2 = new int[(this.n * 2)];
            String[] strArr = new String[(this.n * 2)];
            System.arraycopy(this.m, 0, iArr, 0, this.n);
            System.arraycopy(this.p, 0, iArr2, 0, this.n);
            System.arraycopy(this.o, 0, strArr, 0, this.n);
            this.m = iArr;
            this.p = iArr2;
            this.o = strArr;
        }
        iArr = this.m;
        int i2 = this.n;
        this.n = i2 + 1;
        iArr[i2] = i;
    }

    private boolean b(int i) throws IOException {
        char[] cArr = this.e;
        this.i -= this.f;
        if (this.g != this.f) {
            this.g -= this.f;
            System.arraycopy(cArr, this.f, cArr, 0, this.g);
        } else {
            this.g = 0;
        }
        this.f = 0;
        do {
            int read = this.c.read(cArr, this.g, cArr.length - this.g);
            if (read == -1) {
                return false;
            }
            this.g += read;
            if (this.h == 0 && this.i == 0 && this.g > 0 && cArr[0] == 65279) {
                this.f++;
                this.i++;
                i++;
            }
        } while (this.g < i);
        return true;
    }

    private int b(boolean z) throws IOException {
        char[] cArr = this.e;
        int i = this.f;
        int i2 = this.g;
        while (true) {
            if (i == i2) {
                this.f = i;
                if (b(1)) {
                    i = this.f;
                    i2 = this.g;
                } else if (!z) {
                    return -1;
                } else {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("End of input");
                    stringBuilder.append(x());
                    throw new EOFException(stringBuilder.toString());
                }
            }
            int i3 = i + 1;
            char c = cArr[i];
            if (c == 10) {
                this.h++;
                this.i = i3;
            } else if (!(c == ' ' || c == 13 || c == 9)) {
                if (c == '/') {
                    this.f = i3;
                    if (i3 == i2) {
                        this.f--;
                        boolean b = b(2);
                        this.f++;
                        if (!b) {
                            return c;
                        }
                    }
                    v();
                    char c2 = cArr[this.f];
                    if (c2 == '*') {
                        this.f++;
                        if (a("*/")) {
                            i = this.f + 2;
                            i2 = this.g;
                        } else {
                            throw b("Unterminated comment");
                        }
                    } else if (c2 != '/') {
                        return c;
                    } else {
                        this.f++;
                        w();
                        i = this.f;
                        i2 = this.g;
                    }
                } else if (c == '#') {
                    this.f = i3;
                    v();
                    w();
                    i = this.f;
                    i2 = this.g;
                } else {
                    this.f = i3;
                    return c;
                }
            }
            i = i3;
        }
    }

    private void v() throws IOException {
        if (!this.d) {
            throw b("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    private void w() throws IOException {
        char c;
        do {
            if (this.f < this.g || b(1)) {
                char[] cArr = this.e;
                int i = this.f;
                this.f = i + 1;
                c = cArr[i];
                if (c == 10) {
                    this.h++;
                    this.i = this.f;
                    return;
                }
            } else {
                return;
            }
        } while (c != 13);
    }

    private boolean a(String str) throws IOException {
        while (true) {
            int i = 0;
            if (this.f + str.length() > this.g && !b(str.length())) {
                return false;
            }
            if (this.e[this.f] == 10) {
                this.h++;
                this.i = this.f + 1;
            } else {
                while (i < str.length()) {
                    if (this.e[this.f + i] == str.charAt(i)) {
                        i++;
                    }
                }
                return true;
            }
            this.f++;
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName());
        stringBuilder.append(x());
        return stringBuilder.toString();
    }

    private String x() {
        int i = this.h + 1;
        int i2 = (this.f - this.i) + 1;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" at line ");
        stringBuilder.append(i);
        stringBuilder.append(" column ");
        stringBuilder.append(i2);
        stringBuilder.append(" path ");
        stringBuilder.append(p());
        return stringBuilder.toString();
    }

    public String p() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('$');
        int i = this.n;
        for (int i2 = 0; i2 < i; i2++) {
            switch (this.m[i2]) {
                case 1:
                case 2:
                    stringBuilder.append('[');
                    stringBuilder.append(this.p[i2]);
                    stringBuilder.append(']');
                    break;
                case 3:
                case 4:
                case 5:
                    stringBuilder.append('.');
                    if (this.o[i2] == null) {
                        break;
                    }
                    stringBuilder.append(this.o[i2]);
                    break;
                default:
                    break;
            }
        }
        return stringBuilder.toString();
    }

    private char y() throws IOException {
        if (this.f != this.g || b(1)) {
            char[] cArr = this.e;
            int i = this.f;
            this.f = i + 1;
            char c = cArr[i];
            if (c == 10) {
                this.h++;
                this.i = this.f;
            } else if (!(c == '\"' || c == '\'' || c == '/' || c == '\\')) {
                if (c == 'b') {
                    return 8;
                }
                if (c == 'f') {
                    return 12;
                }
                if (c == 'n') {
                    return 10;
                }
                if (c == 'r') {
                    return 13;
                }
                switch (c) {
                    case 't':
                        return 9;
                    case 'u':
                        if (this.f + 4 <= this.g || b(4)) {
                            int i2 = 0;
                            int i3 = this.f;
                            int i4 = i3 + 4;
                            while (i3 < i4) {
                                int i5;
                                char c2 = this.e[i3];
                                c = (char) (i2 << 4);
                                if (c2 >= '0' && c2 <= '9') {
                                    i5 = c2 - 48;
                                } else if (c2 >= 'a' && c2 <= 'f') {
                                    i5 = (c2 - 97) + 10;
                                } else if (c2 < 'A' || c2 > 'F') {
                                    StringBuilder stringBuilder = new StringBuilder();
                                    stringBuilder.append("\\u");
                                    stringBuilder.append(new String(this.e, this.f, 4));
                                    throw new NumberFormatException(stringBuilder.toString());
                                } else {
                                    i5 = (c2 - 65) + 10;
                                }
                                i2 = (char) (c + i5);
                                i3++;
                            }
                            this.f += 4;
                            return i2;
                        }
                        throw b("Unterminated escape sequence");
                    default:
                        throw b("Invalid escape sequence");
                }
            }
            return c;
        }
        throw b("Unterminated escape sequence");
    }

    private IOException b(String str) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(x());
        throw new ia(stringBuilder.toString());
    }

    private void z() throws IOException {
        b(true);
        this.f--;
        if (this.f + b.length <= this.g || b(b.length)) {
            int i = 0;
            while (i < b.length) {
                if (this.e[this.f + i] == b[i]) {
                    i++;
                } else {
                    return;
                }
            }
            this.f += b.length;
        }
    }

    static {
        ha.a = new ha() {
            public void a(hx hxVar) throws IOException {
                if (hxVar instanceof hl) {
                    ((hl) hxVar).o();
                    return;
                }
                int i = hxVar.a;
                if (i == 0) {
                    i = hxVar.r();
                }
                if (i == 13) {
                    hxVar.a = 9;
                } else if (i == 12) {
                    hxVar.a = 8;
                } else if (i == 14) {
                    hxVar.a = 10;
                } else {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Expected a name but was ");
                    stringBuilder.append(hxVar.f());
                    stringBuilder.append(hxVar.x());
                    throw new IllegalStateException(stringBuilder.toString());
                }
            }
        };
    }
}
