package com.fasterxml.jackson.core.b;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.a.b;
import com.fasterxml.jackson.core.c.a;
import com.fasterxml.jackson.core.io.c;
import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.moe.pushlibrary.MoEWorker;
import java.io.IOException;
import java.io.InputStream;

public final class g extends b {
    private static final int[] S = com.fasterxml.jackson.core.io.b.b();
    private static final int[] T = com.fasterxml.jackson.core.io.b.a();
    protected com.fasterxml.jackson.core.b L;
    protected final a M;
    protected int[] N = new int[16];
    protected boolean O = false;
    protected InputStream P;
    protected byte[] Q;
    protected boolean R;
    private int U;

    public g(c cVar, int i, InputStream inputStream, com.fasterxml.jackson.core.b bVar, a aVar, byte[] bArr, int i2, int i3, boolean z) {
        super(cVar, i);
        this.P = inputStream;
        this.L = bVar;
        this.M = aVar;
        this.Q = bArr;
        this.d = i2;
        this.e = i3;
        this.R = z;
    }

    /* Access modifiers changed, original: protected */
    public boolean p() throws IOException {
        this.f += (long) this.e;
        this.h -= this.e;
        if (this.P != null) {
            int read = this.P.read(this.Q, 0, this.Q.length);
            if (read > 0) {
                this.d = 0;
                this.e = read;
                return true;
            }
            r();
            if (read == 0) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("InputStream.read() returned 0 characters when trying to read ");
                stringBuilder.append(this.Q.length);
                stringBuilder.append(" bytes");
                throw new IOException(stringBuilder.toString());
            }
        }
        return false;
    }

    /* Access modifiers changed, original: protected */
    public void r() throws IOException {
        if (this.P != null) {
            if (this.b.c() || a(Feature.AUTO_CLOSE_SOURCE)) {
                this.P.close();
            }
            this.P = null;
        }
    }

    /* Access modifiers changed, original: protected */
    public void s() throws IOException {
        super.s();
        if (this.R) {
            byte[] bArr = this.Q;
            if (bArr != null) {
                this.Q = null;
                this.b.a(bArr);
            }
        }
    }

    public String f() throws IOException, JsonParseException {
        if (this.K != JsonToken.VALUE_STRING) {
            return a(this.K);
        }
        if (this.O) {
            this.O = false;
            q();
        }
        return this.n.f();
    }

    /* Access modifiers changed, original: protected */
    public String a(JsonToken jsonToken) {
        if (jsonToken == null) {
            return null;
        }
        switch (jsonToken) {
            case FIELD_NAME:
                return this.l.h();
            case VALUE_STRING:
            case VALUE_NUMBER_INT:
            case VALUE_NUMBER_FLOAT:
                return this.n.f();
            default:
                return jsonToken.asString();
        }
    }

    public JsonToken a() throws IOException, JsonParseException {
        this.A = 0;
        if (this.K == JsonToken.FIELD_NAME) {
            return L();
        }
        if (this.O) {
            H();
        }
        int O = O();
        if (O < 0) {
            close();
            this.K = null;
            return null;
        }
        this.i = (this.f + ((long) this.d)) - 1;
        this.j = this.g;
        this.k = (this.d - this.h) - 1;
        this.r = null;
        JsonToken jsonToken;
        if (O == 93) {
            if (!this.l.a()) {
                a(O, '}');
            }
            this.l = this.l.i();
            jsonToken = JsonToken.END_ARRAY;
            this.K = jsonToken;
            return jsonToken;
        } else if (O == 125) {
            if (!this.l.c()) {
                a(O, ']');
            }
            this.l = this.l.i();
            jsonToken = JsonToken.END_OBJECT;
            this.K = jsonToken;
            return jsonToken;
        } else {
            if (this.l.j()) {
                if (O != 44) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("was expecting comma to separate ");
                    stringBuilder.append(this.l.d());
                    stringBuilder.append(" entries");
                    b(O, stringBuilder.toString());
                }
                O = N();
            }
            if (!this.l.c()) {
                return m(O);
            }
            this.l.a(e(O).a());
            this.K = JsonToken.FIELD_NAME;
            O = N();
            if (O != 58) {
                b(O, "was expecting a colon to separate field name and value");
            }
            O = N();
            if (O == 34) {
                this.O = true;
                this.m = JsonToken.VALUE_STRING;
                return this.K;
            }
            if (O != 45) {
                if (O != 91) {
                    if (O != 93) {
                        if (O == 102) {
                            a(InternalLogger.EVENT_PARAM_EXTRAS_FALSE, 1);
                            jsonToken = JsonToken.VALUE_FALSE;
                        } else if (O != 110) {
                            if (O != 116) {
                                if (O != MoEWorker.REQ_CODE_SEND_DATA) {
                                    if (O != 125) {
                                        switch (O) {
                                            case 48:
                                            case 49:
                                            case 50:
                                            case 51:
                                            case 52:
                                            case 53:
                                            case 54:
                                            case 55:
                                            case 56:
                                            case 57:
                                                break;
                                            default:
                                                jsonToken = h(O);
                                                break;
                                        }
                                    }
                                }
                                jsonToken = JsonToken.START_OBJECT;
                            }
                            a("true", 1);
                            jsonToken = JsonToken.VALUE_TRUE;
                        } else {
                            a("null", 1);
                            jsonToken = JsonToken.VALUE_NULL;
                        }
                    }
                    b(O, "expected a value");
                    a("true", 1);
                    jsonToken = JsonToken.VALUE_TRUE;
                } else {
                    jsonToken = JsonToken.START_ARRAY;
                }
                this.m = jsonToken;
                return this.K;
            }
            jsonToken = d(O);
            this.m = jsonToken;
            return this.K;
        }
    }

    private JsonToken m(int i) throws IOException, JsonParseException {
        JsonToken jsonToken;
        if (i == 34) {
            this.O = true;
            jsonToken = JsonToken.VALUE_STRING;
            this.K = jsonToken;
            return jsonToken;
        }
        if (i != 45) {
            if (i != 91) {
                if (i != 93) {
                    if (i == 102) {
                        a(InternalLogger.EVENT_PARAM_EXTRAS_FALSE, 1);
                        jsonToken = JsonToken.VALUE_FALSE;
                        this.K = jsonToken;
                        return jsonToken;
                    } else if (i != 110) {
                        if (i != 116) {
                            if (i == MoEWorker.REQ_CODE_SEND_DATA) {
                                this.l = this.l.b(this.j, this.k);
                                jsonToken = JsonToken.START_OBJECT;
                                this.K = jsonToken;
                                return jsonToken;
                            } else if (i != 125) {
                                switch (i) {
                                    case 48:
                                    case 49:
                                    case 50:
                                    case 51:
                                    case 52:
                                    case 53:
                                    case 54:
                                    case 55:
                                    case 56:
                                    case 57:
                                        break;
                                    default:
                                        jsonToken = h(i);
                                        this.K = jsonToken;
                                        return jsonToken;
                                }
                            }
                        }
                        a("true", 1);
                        jsonToken = JsonToken.VALUE_TRUE;
                        this.K = jsonToken;
                        return jsonToken;
                    } else {
                        a("null", 1);
                        jsonToken = JsonToken.VALUE_NULL;
                        this.K = jsonToken;
                        return jsonToken;
                    }
                }
                b(i, "expected a value");
                a("true", 1);
                jsonToken = JsonToken.VALUE_TRUE;
                this.K = jsonToken;
                return jsonToken;
            }
            this.l = this.l.a(this.j, this.k);
            jsonToken = JsonToken.START_ARRAY;
            this.K = jsonToken;
            return jsonToken;
        }
        jsonToken = d(i);
        this.K = jsonToken;
        return jsonToken;
    }

    private JsonToken L() {
        this.p = false;
        JsonToken jsonToken = this.m;
        this.m = null;
        if (jsonToken == JsonToken.START_ARRAY) {
            this.l = this.l.a(this.j, this.k);
        } else if (jsonToken == JsonToken.START_OBJECT) {
            this.l = this.l.b(this.j, this.k);
        }
        this.K = jsonToken;
        return jsonToken;
    }

    public void close() throws IOException {
        super.close();
        this.M.b();
    }

    /* Access modifiers changed, original: protected */
    public JsonToken d(int i) throws IOException, JsonParseException {
        int i2;
        char[] k = this.n.k();
        int i3 = 0;
        boolean z = i == 45;
        if (z) {
            k[0] = '-';
            if (this.d >= this.e) {
                o();
            }
            byte[] bArr = this.Q;
            i2 = this.d;
            this.d = i2 + 1;
            i = bArr[i2] & 255;
            if (i < 48 || i > 57) {
                return a(i, true);
            }
            i3 = 1;
        }
        if (i == 48) {
            i = M();
        }
        i2 = i3 + 1;
        k[i3] = (char) i;
        i = this.d + k.length;
        if (i > this.e) {
            i = this.e;
        }
        int i4 = 1;
        while (this.d < i) {
            byte[] bArr2 = this.Q;
            int i5 = this.d;
            this.d = i5 + 1;
            i5 = bArr2[i5] & 255;
            if (i5 >= 48 && i5 <= 57) {
                i4++;
                i3 = i2 + 1;
                k[i2] = (char) i5;
                i2 = i3;
            } else if (i5 == 46 || i5 == 101 || i5 == 69) {
                return a(k, i2, i5, z, i4);
            } else {
                this.d--;
                this.n.a(i2);
                return a(z, i4);
            }
        }
        return a(k, i2, z, i4);
    }

    private JsonToken a(char[] cArr, int i, boolean z, int i2) throws IOException, JsonParseException {
        int i3;
        char[] cArr2 = cArr;
        int i4 = i;
        int i5 = i2;
        while (true) {
            if (this.d < this.e || p()) {
                byte[] bArr = this.Q;
                i = this.d;
                this.d = i + 1;
                i3 = bArr[i] & 255;
                if (i3 <= 57 && i3 >= 48) {
                    if (i4 >= cArr2.length) {
                        i4 = 0;
                        cArr2 = this.n.m();
                    }
                    int i6 = i4 + 1;
                    cArr2[i4] = (char) i3;
                    i5++;
                    i4 = i6;
                }
            } else {
                this.n.a(i4);
                return a(z, i5);
            }
        }
        if (i3 == 46 || i3 == 101 || i3 == 69) {
            return a(cArr2, i4, i3, z, i5);
        }
        this.d--;
        this.n.a(i4);
        return a(z, i5);
    }

    private int M() throws IOException, JsonParseException {
        if (this.d >= this.e && !p()) {
            return 48;
        }
        int i = this.Q[this.d] & 255;
        if (i < 48 || i > 57) {
            return 48;
        }
        if (!a(Feature.ALLOW_NUMERIC_LEADING_ZEROS)) {
            b("Leading zeroes not allowed");
        }
        this.d++;
        if (i == 48) {
            do {
                if (this.d >= this.e && !p()) {
                    break;
                }
                i = this.Q[this.d] & 255;
                if (i < 48 || i > 57) {
                    return 48;
                }
                this.d++;
            } while (i == 48);
        }
        return i;
    }

    private JsonToken a(char[] cArr, int i, int i2, boolean z, int i3) throws IOException, JsonParseException {
        int i4;
        int i5;
        int i6;
        int i7 = 0;
        if (i2 == 46) {
            i4 = i + 1;
            cArr[i] = (char) i2;
            i = i4;
            i4 = i2;
            char[] cArr2 = cArr;
            i5 = 0;
            while (true) {
                if (this.d >= this.e && !p()) {
                    i6 = 1;
                    break;
                }
                byte[] bArr = this.Q;
                i6 = this.d;
                this.d = i6 + 1;
                i4 = bArr[i6] & 255;
                if (i4 < 48 || i4 > 57) {
                    i6 = 0;
                } else {
                    i5++;
                    if (i >= cArr2.length) {
                        cArr2 = this.n.m();
                        i = 0;
                    }
                    i6 = i + 1;
                    cArr2[i] = (char) i4;
                    i = i6;
                }
            }
            if (i5 == 0) {
                a(i4, "Decimal point not followed by a digit");
            }
            int i8 = i4;
            i4 = i5;
            cArr = cArr2;
            i2 = i8;
        } else {
            i4 = 0;
            i6 = i4;
        }
        if (i2 == 101 || i2 == 69) {
            if (i >= cArr.length) {
                cArr = this.n.m();
                i = 0;
            }
            int i9 = i + 1;
            cArr[i] = (char) i2;
            if (this.d >= this.e) {
                o();
            }
            byte[] bArr2 = this.Q;
            i2 = this.d;
            this.d = i2 + 1;
            i = bArr2[i2] & 255;
            if (i == 45 || i == 43) {
                if (i9 >= cArr.length) {
                    cArr = this.n.m();
                    i9 = 0;
                }
                i2 = i9 + 1;
                cArr[i9] = (char) i;
                if (this.d >= this.e) {
                    o();
                }
                bArr2 = this.Q;
                i9 = this.d;
                this.d = i9 + 1;
                i = bArr2[i9] & 255;
            } else {
                i2 = i9;
            }
            char[] cArr3 = cArr;
            i5 = 0;
            while (i <= 57 && i >= 48) {
                i5++;
                if (i2 >= cArr3.length) {
                    cArr3 = this.n.m();
                    i2 = 0;
                }
                int i10 = i2 + 1;
                cArr3[i2] = (char) i;
                if (this.d >= this.e && !p()) {
                    i7 = i5;
                    i6 = 1;
                    i2 = i10;
                    break;
                }
                bArr2 = this.Q;
                i2 = this.d;
                this.d = i2 + 1;
                i = bArr2[i2] & 255;
                i2 = i10;
            }
            i7 = i5;
            if (i7 == 0) {
                a(i, "Exponent indicator not followed by a digit");
            }
            i = i2;
        }
        if (i6 == 0) {
            this.d--;
        }
        this.n.a(i);
        return b(z, i3, i4, i7);
    }

    /* Access modifiers changed, original: protected */
    public com.fasterxml.jackson.core.c.c e(int i) throws IOException, JsonParseException {
        if (i != 34) {
            return g(i);
        }
        if (this.d + 9 > this.e) {
            return F();
        }
        byte[] bArr = this.Q;
        int[] iArr = T;
        int i2 = this.d;
        this.d = i2 + 1;
        i2 = bArr[i2] & 255;
        if (iArr[i2] == 0) {
            int i3 = this.d;
            this.d = i3 + 1;
            i3 = bArr[i3] & 255;
            if (iArr[i3] == 0) {
                i2 = (i2 << 8) | i3;
                i3 = this.d;
                this.d = i3 + 1;
                i3 = bArr[i3] & 255;
                if (iArr[i3] == 0) {
                    i2 = (i2 << 8) | i3;
                    i3 = this.d;
                    this.d = i3 + 1;
                    i3 = bArr[i3] & 255;
                    if (iArr[i3] == 0) {
                        i2 = (i2 << 8) | i3;
                        i3 = this.d;
                        this.d = i3 + 1;
                        i = bArr[i3] & 255;
                        if (iArr[i] == 0) {
                            this.U = i2;
                            return a(i, iArr);
                        } else if (i == 34) {
                            return b(i2, 4);
                        } else {
                            return a(i2, i, 4);
                        }
                    } else if (i3 == 34) {
                        return b(i2, 3);
                    } else {
                        return a(i2, i3, 3);
                    }
                } else if (i3 == 34) {
                    return b(i2, 2);
                } else {
                    return a(i2, i3, 2);
                }
            } else if (i3 == 34) {
                return b(i2, 1);
            } else {
                return a(i2, i3, 1);
            }
        } else if (i2 == 34) {
            return a.d();
        } else {
            return a(0, i2, 0);
        }
    }

    /* Access modifiers changed, original: protected */
    public com.fasterxml.jackson.core.c.c a(int i, int[] iArr) throws IOException, JsonParseException {
        byte[] bArr = this.Q;
        int i2 = this.d;
        this.d = i2 + 1;
        int i3 = bArr[i2] & 255;
        if (iArr[i3] == 0) {
            i = (i << 8) | i3;
            bArr = this.Q;
            i2 = this.d;
            this.d = i2 + 1;
            i3 = bArr[i2] & 255;
            if (iArr[i3] == 0) {
                i = (i << 8) | i3;
                bArr = this.Q;
                i2 = this.d;
                this.d = i2 + 1;
                i3 = bArr[i2] & 255;
                if (iArr[i3] == 0) {
                    i = (i << 8) | i3;
                    bArr = this.Q;
                    i2 = this.d;
                    this.d = i2 + 1;
                    i3 = bArr[i2] & 255;
                    if (iArr[i3] == 0) {
                        this.N[0] = this.U;
                        this.N[1] = i;
                        return f(i3);
                    } else if (i3 == 34) {
                        return b(this.U, i, 4);
                    } else {
                        return a(this.U, i, i3, 4);
                    }
                } else if (i3 == 34) {
                    return b(this.U, i, 3);
                } else {
                    return a(this.U, i, i3, 3);
                }
            } else if (i3 == 34) {
                return b(this.U, i, 2);
            } else {
                return a(this.U, i, i3, 2);
            }
        } else if (i3 == 34) {
            return b(this.U, i, 1);
        } else {
            return a(this.U, i, i3, 1);
        }
    }

    /* Access modifiers changed, original: protected */
    public com.fasterxml.jackson.core.c.c f(int i) throws IOException, JsonParseException {
        int[] iArr = T;
        int i2 = 2;
        while (this.e - this.d >= 4) {
            byte[] bArr = this.Q;
            int i3 = this.d;
            this.d = i3 + 1;
            int i4 = bArr[i3] & 255;
            if (iArr[i4] == 0) {
                i = (i << 8) | i4;
                bArr = this.Q;
                i4 = this.d;
                this.d = i4 + 1;
                i4 = bArr[i4] & 255;
                if (iArr[i4] == 0) {
                    i = (i << 8) | i4;
                    bArr = this.Q;
                    i4 = this.d;
                    this.d = i4 + 1;
                    i4 = bArr[i4] & 255;
                    if (iArr[i4] == 0) {
                        i = (i << 8) | i4;
                        bArr = this.Q;
                        i4 = this.d;
                        this.d = i4 + 1;
                        i4 = bArr[i4] & 255;
                        if (iArr[i4] == 0) {
                            if (i2 >= this.N.length) {
                                this.N = a(this.N, i2);
                            }
                            int i5 = i2 + 1;
                            this.N[i2] = i;
                            i2 = i5;
                            i = i4;
                        } else if (i4 == 34) {
                            return a(this.N, i2, i, 4);
                        } else {
                            return a(this.N, i2, i, i4, 4);
                        }
                    } else if (i4 == 34) {
                        return a(this.N, i2, i, 3);
                    } else {
                        return a(this.N, i2, i, i4, 3);
                    }
                } else if (i4 == 34) {
                    return a(this.N, i2, i, 2);
                } else {
                    return a(this.N, i2, i, i4, 2);
                }
            } else if (i4 == 34) {
                return a(this.N, i2, i, 1);
            } else {
                return a(this.N, i2, i, i4, 1);
            }
        }
        return a(this.N, i2, 0, i, 0);
    }

    /* Access modifiers changed, original: protected */
    public com.fasterxml.jackson.core.c.c F() throws IOException, JsonParseException {
        if (this.d >= this.e && !p()) {
            c(": was expecting closing '\"' for name");
        }
        byte[] bArr = this.Q;
        int i = this.d;
        this.d = i + 1;
        int i2 = bArr[i] & 255;
        if (i2 == 34) {
            return a.d();
        }
        return a(this.N, 0, 0, i2, 0);
    }

    private com.fasterxml.jackson.core.c.c a(int i, int i2, int i3) throws IOException, JsonParseException {
        return a(this.N, 0, i, i2, i3);
    }

    private com.fasterxml.jackson.core.c.c a(int i, int i2, int i3, int i4) throws IOException, JsonParseException {
        this.N[0] = i;
        return a(this.N, 1, i2, i3, i4);
    }

    /* Access modifiers changed, original: protected */
    public com.fasterxml.jackson.core.c.c a(int[] iArr, int i, int i2, int i3, int i4) throws IOException, JsonParseException {
        int[] iArr2 = T;
        while (true) {
            if (iArr2[i3] != 0) {
                if (i3 == 34) {
                    break;
                }
                if (i3 != 92) {
                    c(i3, "name");
                } else {
                    i3 = B();
                }
                if (i3 > 127) {
                    if (i4 >= 4) {
                        if (i >= iArr.length) {
                            iArr = a(iArr, iArr.length);
                            this.N = iArr;
                        }
                        i4 = i + 1;
                        iArr[i] = i2;
                        i = i4;
                        i2 = 0;
                        i4 = i2;
                    }
                    if (i3 < 2048) {
                        i2 = (i2 << 8) | (PsExtractor.AUDIO_STREAM | (i3 >> 6));
                        i4++;
                    } else {
                        i2 = (i2 << 8) | (224 | (i3 >> 12));
                        i4++;
                        if (i4 >= 4) {
                            if (i >= iArr.length) {
                                iArr = a(iArr, iArr.length);
                                this.N = iArr;
                            }
                            i4 = i + 1;
                            iArr[i] = i2;
                            i = i4;
                            i2 = 0;
                            i4 = i2;
                        }
                        i2 = (i2 << 8) | (((i3 >> 6) & 63) | 128);
                        i4++;
                    }
                    i3 = (i3 & 63) | 128;
                }
            }
            if (i4 < 4) {
                i4++;
                i2 = (i2 << 8) | i3;
            } else {
                if (i >= iArr.length) {
                    iArr = a(iArr, iArr.length);
                    this.N = iArr;
                }
                i4 = i + 1;
                iArr[i] = i2;
                i2 = i3;
                i = i4;
                i4 = 1;
            }
            if (this.d >= this.e && !p()) {
                c(" in field name");
            }
            byte[] bArr = this.Q;
            int i5 = this.d;
            this.d = i5 + 1;
            i3 = bArr[i5] & 255;
        }
        if (i4 > 0) {
            if (i >= iArr.length) {
                iArr = a(iArr, iArr.length);
                this.N = iArr;
            }
            i3 = i + 1;
            iArr[i] = i2;
            i = i3;
        }
        com.fasterxml.jackson.core.c.c a = this.M.a(iArr, i);
        if (a == null) {
            return a(iArr, i, i4);
        }
        return a;
    }

    /* Access modifiers changed, original: protected */
    public com.fasterxml.jackson.core.c.c g(int i) throws IOException, JsonParseException {
        if (i == 39 && a(Feature.ALLOW_SINGLE_QUOTES)) {
            return G();
        }
        int i2;
        if (!a(Feature.ALLOW_UNQUOTED_FIELD_NAMES)) {
            b(i, "was expecting double-quote to start field name");
        }
        int[] d = com.fasterxml.jackson.core.io.b.d();
        if (d[i] != 0) {
            b(i, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name");
        }
        int i3 = 0;
        int[] iArr = this.N;
        int i4 = 0;
        int i5 = i;
        i = i4;
        while (true) {
            if (i3 < 4) {
                i3++;
                i = (i << 8) | i5;
            } else {
                if (i4 >= iArr.length) {
                    iArr = a(iArr, iArr.length);
                    this.N = iArr;
                }
                i3 = i4 + 1;
                iArr[i4] = i;
                i = i5;
                i4 = i3;
                i3 = 1;
            }
            if (this.d >= this.e && !p()) {
                c(" in field name");
            }
            i5 = this.Q[this.d] & 255;
            if (d[i5] != 0) {
                break;
            }
            this.d++;
        }
        if (i3 > 0) {
            if (i4 >= iArr.length) {
                iArr = a(iArr, iArr.length);
                this.N = iArr;
            }
            i2 = i4 + 1;
            iArr[i4] = i;
        } else {
            i2 = i4;
        }
        com.fasterxml.jackson.core.c.c a = this.M.a(iArr, i2);
        if (a == null) {
            a = a(iArr, i2, i3);
        }
        return a;
    }

    /* Access modifiers changed, original: protected */
    public com.fasterxml.jackson.core.c.c G() throws IOException, JsonParseException {
        if (this.d >= this.e && !p()) {
            c(": was expecting closing ''' for name");
        }
        byte[] bArr = this.Q;
        int i = this.d;
        this.d = i + 1;
        int i2 = bArr[i] & 255;
        if (i2 == 39) {
            return a.d();
        }
        int[] iArr = this.N;
        int[] iArr2 = T;
        int[] iArr3 = iArr;
        int i3 = 0;
        int i4 = i3;
        int i5 = i4;
        while (i2 != 39) {
            if (!(i2 == 34 || iArr2[i2] == 0)) {
                if (i2 != 92) {
                    c(i2, "name");
                } else {
                    i2 = B();
                }
                if (i2 > 127) {
                    if (i3 >= 4) {
                        if (i4 >= iArr3.length) {
                            iArr3 = a(iArr3, iArr3.length);
                            this.N = iArr3;
                        }
                        i3 = i4 + 1;
                        iArr3[i4] = i5;
                        i4 = i3;
                        i3 = 0;
                        i5 = i3;
                    }
                    if (i2 < 2048) {
                        i5 = (i5 << 8) | (PsExtractor.AUDIO_STREAM | (i2 >> 6));
                        i3++;
                    } else {
                        i5 = (i5 << 8) | (224 | (i2 >> 12));
                        i3++;
                        if (i3 >= 4) {
                            if (i4 >= iArr3.length) {
                                iArr3 = a(iArr3, iArr3.length);
                                this.N = iArr3;
                            }
                            i3 = i4 + 1;
                            iArr3[i4] = i5;
                            i4 = i3;
                            i3 = 0;
                            i5 = i3;
                        }
                        i5 = (i5 << 8) | (((i2 >> 6) & 63) | 128);
                        i3++;
                    }
                    i2 = (i2 & 63) | 128;
                }
            }
            if (i3 < 4) {
                i3++;
                i5 = i2 | (i5 << 8);
            } else {
                if (i4 >= iArr3.length) {
                    iArr3 = a(iArr3, iArr3.length);
                    this.N = iArr3;
                }
                i3 = i4 + 1;
                iArr3[i4] = i5;
                i5 = i2;
                i4 = i3;
                i3 = 1;
            }
            if (this.d >= this.e && !p()) {
                c(" in field name");
            }
            bArr = this.Q;
            int i6 = this.d;
            this.d = i6 + 1;
            i2 = bArr[i6] & 255;
        }
        if (i3 > 0) {
            if (i4 >= iArr3.length) {
                iArr3 = a(iArr3, iArr3.length);
                this.N = iArr3;
            }
            i2 = i4 + 1;
            iArr3[i4] = i5;
        } else {
            i2 = i4;
        }
        com.fasterxml.jackson.core.c.c a = this.M.a(iArr3, i2);
        if (a == null) {
            a = a(iArr3, i2, i3);
        }
        return a;
    }

    private com.fasterxml.jackson.core.c.c b(int i, int i2) throws JsonParseException {
        com.fasterxml.jackson.core.c.c b = this.M.b(i);
        if (b != null) {
            return b;
        }
        this.N[0] = i;
        return a(this.N, 1, i2);
    }

    private com.fasterxml.jackson.core.c.c b(int i, int i2, int i3) throws JsonParseException {
        com.fasterxml.jackson.core.c.c a = this.M.a(i, i2);
        if (a != null) {
            return a;
        }
        this.N[0] = i;
        this.N[1] = i2;
        return a(this.N, 2, i3);
    }

    private com.fasterxml.jackson.core.c.c a(int[] iArr, int i, int i2, int i3) throws JsonParseException {
        if (i >= iArr.length) {
            iArr = a(iArr, iArr.length);
            this.N = iArr;
        }
        int i4 = i + 1;
        iArr[i] = i2;
        com.fasterxml.jackson.core.c.c a = this.M.a(iArr, i4);
        return a == null ? a(iArr, i4, i3) : a;
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x00e3  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00c2  */
    private com.fasterxml.jackson.core.c.c a(int[] r19, int r20, int r21) throws com.fasterxml.jackson.core.JsonParseException {
        /*
        r18 = this;
        r0 = r18;
        r1 = r19;
        r2 = r20;
        r3 = r21;
        r4 = r2 << 2;
        r5 = 4;
        r4 = r4 - r5;
        r4 = r4 + r3;
        r7 = 3;
        if (r3 >= r5) goto L_0x001c;
    L_0x0010:
        r8 = r2 + -1;
        r9 = r1[r8];
        r10 = 4 - r3;
        r10 = r10 << r7;
        r10 = r9 << r10;
        r1[r8] = r10;
        goto L_0x001d;
    L_0x001c:
        r9 = 0;
    L_0x001d:
        r8 = r0.n;
        r8 = r8.k();
        r10 = r8;
        r8 = 0;
        r11 = 0;
    L_0x0026:
        if (r8 >= r4) goto L_0x00f7;
    L_0x0028:
        r12 = r8 >> 2;
        r12 = r1[r12];
        r13 = r8 & 3;
        r13 = 3 - r13;
        r13 = r13 << r7;
        r12 = r12 >> r13;
        r12 = r12 & 255;
        r8 = r8 + 1;
        r13 = 127; // 0x7f float:1.78E-43 double:6.27E-322;
        if (r12 <= r13) goto L_0x00e4;
    L_0x003a:
        r13 = r12 & 224;
        r14 = 192; // 0xc0 float:2.69E-43 double:9.5E-322;
        r5 = 1;
        if (r13 != r14) goto L_0x0046;
    L_0x0041:
        r12 = r12 & 31;
        r13 = r12;
        r12 = r5;
        goto L_0x0061;
    L_0x0046:
        r13 = r12 & 240;
        r14 = 224; // 0xe0 float:3.14E-43 double:1.107E-321;
        if (r13 != r14) goto L_0x0051;
    L_0x004c:
        r12 = r12 & 15;
        r13 = r12;
        r12 = 2;
        goto L_0x0061;
    L_0x0051:
        r13 = r12 & 248;
        r14 = 240; // 0xf0 float:3.36E-43 double:1.186E-321;
        if (r13 != r14) goto L_0x005c;
    L_0x0057:
        r12 = r12 & 7;
        r13 = r12;
        r12 = r7;
        goto L_0x0061;
    L_0x005c:
        r0.k(r12);
        r12 = r5;
        r13 = r12;
    L_0x0061:
        r14 = r8 + r12;
        if (r14 <= r4) goto L_0x006a;
    L_0x0065:
        r14 = " in field name";
        r0.c(r14);
    L_0x006a:
        r14 = r8 >> 2;
        r14 = r1[r14];
        r16 = r8 & 3;
        r16 = 3 - r16;
        r16 = r16 << 3;
        r14 = r14 >> r16;
        r8 = r8 + 1;
        r6 = r14 & 192;
        r15 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        if (r6 == r15) goto L_0x0081;
    L_0x007e:
        r0.l(r14);
    L_0x0081:
        r6 = r13 << 6;
        r13 = r14 & 63;
        r6 = r6 | r13;
        if (r12 <= r5) goto L_0x00be;
    L_0x0088:
        r5 = r8 >> 2;
        r5 = r1[r5];
        r13 = r8 & 3;
        r13 = 3 - r13;
        r13 = r13 << r7;
        r5 = r5 >> r13;
        r8 = r8 + 1;
        r13 = r5 & 192;
        if (r13 == r15) goto L_0x009b;
    L_0x0098:
        r0.l(r5);
    L_0x009b:
        r6 = r6 << 6;
        r5 = r5 & 63;
        r5 = r5 | r6;
        r6 = 2;
        if (r12 <= r6) goto L_0x00c0;
    L_0x00a3:
        r6 = r8 >> 2;
        r6 = r1[r6];
        r13 = r8 & 3;
        r13 = 3 - r13;
        r13 = r13 << r7;
        r6 = r6 >> r13;
        r8 = r8 + 1;
        r13 = r6 & 192;
        if (r13 == r15) goto L_0x00b8;
    L_0x00b3:
        r13 = r6 & 255;
        r0.l(r13);
    L_0x00b8:
        r5 = r5 << 6;
        r6 = r6 & 63;
        r5 = r5 | r6;
        goto L_0x00bf;
    L_0x00be:
        r5 = r6;
    L_0x00bf:
        r6 = 2;
    L_0x00c0:
        if (r12 <= r6) goto L_0x00e3;
    L_0x00c2:
        r6 = 65536; // 0x10000 float:9.18355E-41 double:3.2379E-319;
        r5 = r5 - r6;
        r6 = r10.length;
        if (r11 < r6) goto L_0x00cf;
    L_0x00c8:
        r6 = r0.n;
        r6 = r6.n();
        r10 = r6;
    L_0x00cf:
        r6 = r11 + 1;
        r12 = 55296; // 0xd800 float:7.7486E-41 double:2.732E-319;
        r13 = r5 >> 10;
        r12 = r12 + r13;
        r12 = (char) r12;
        r10[r11] = r12;
        r11 = 56320; // 0xdc00 float:7.8921E-41 double:2.7826E-319;
        r5 = r5 & 1023;
        r12 = r11 | r5;
        r11 = r6;
        goto L_0x00e4;
    L_0x00e3:
        r12 = r5;
    L_0x00e4:
        r5 = r10.length;
        if (r11 < r5) goto L_0x00ee;
    L_0x00e7:
        r5 = r0.n;
        r5 = r5.n();
        r10 = r5;
    L_0x00ee:
        r5 = r11 + 1;
        r6 = (char) r12;
        r10[r11] = r6;
        r11 = r5;
        r5 = 4;
        goto L_0x0026;
    L_0x00f7:
        r4 = new java.lang.String;
        r5 = 0;
        r4.<init>(r10, r5, r11);
        r5 = 4;
        if (r3 >= r5) goto L_0x0104;
    L_0x0100:
        r3 = r2 + -1;
        r1[r3] = r9;
    L_0x0104:
        r3 = r0.M;
        r1 = r3.a(r4, r1, r2);
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.b.g.a(int[], int, int):com.fasterxml.jackson.core.c.c");
    }

    /* Access modifiers changed, original: protected */
    public void q() throws IOException, JsonParseException {
        int i = this.d;
        if (i >= this.e) {
            o();
            i = this.d;
        }
        int i2 = 0;
        char[] k = this.n.k();
        int[] iArr = S;
        int min = Math.min(this.e, k.length + i);
        byte[] bArr = this.Q;
        while (i < min) {
            int i3 = bArr[i] & 255;
            if (iArr[i3] != 0) {
                if (i3 == 34) {
                    this.d = i + 1;
                    this.n.a(i2);
                    return;
                }
                this.d = i;
                a(k, i2);
            }
            i++;
            int i4 = i2 + 1;
            k[i2] = (char) i3;
            i2 = i4;
        }
        this.d = i;
        a(k, i2);
    }

    private void a(char[] cArr, int i) throws IOException, JsonParseException {
        int[] iArr = S;
        byte[] bArr = this.Q;
        while (true) {
            int i2 = this.d;
            if (i2 >= this.e) {
                o();
                i2 = this.d;
            }
            if (i >= cArr.length) {
                cArr = this.n.m();
                i = 0;
            }
            int min = Math.min(this.e, (cArr.length - i) + i2);
            while (i2 < min) {
                int i3 = i2 + 1;
                i2 = bArr[i2] & 255;
                if (iArr[i2] != 0) {
                    this.d = i3;
                    if (i2 == 34) {
                        this.n.a(i);
                        return;
                    }
                    switch (iArr[i2]) {
                        case 1:
                            i2 = B();
                            break;
                        case 2:
                            i2 = n(i2);
                            break;
                        case 3:
                            if (this.e - this.d < 2) {
                                i2 = o(i2);
                                break;
                            } else {
                                i2 = p(i2);
                                break;
                            }
                        case 4:
                            i2 = q(i2);
                            min = i + 1;
                            cArr[i] = (char) (55296 | (i2 >> 10));
                            if (min >= cArr.length) {
                                cArr = this.n.m();
                                min = 0;
                            }
                            i2 = (i2 & 1023) | 56320;
                            i = min;
                            break;
                        default:
                            if (i2 >= 32) {
                                j(i2);
                                break;
                            } else {
                                c(i2, "string value");
                                break;
                            }
                    }
                    if (i >= cArr.length) {
                        cArr = this.n.m();
                        i = 0;
                    }
                    int i4 = i + 1;
                    cArr[i] = (char) i2;
                    i = i4;
                } else {
                    int i5 = i + 1;
                    cArr[i] = (char) i2;
                    i2 = i3;
                    i = i5;
                }
            }
            this.d = i2;
        }
    }

    /* Access modifiers changed, original: protected */
    public void H() throws IOException, JsonParseException {
        this.O = false;
        int[] iArr = S;
        byte[] bArr = this.Q;
        while (true) {
            int i = this.d;
            int i2 = this.e;
            if (i >= i2) {
                o();
                i = this.d;
                i2 = this.e;
            }
            while (i < i2) {
                int i3 = i + 1;
                i = bArr[i] & 255;
                if (iArr[i] != 0) {
                    this.d = i3;
                    if (i != 34) {
                        switch (iArr[i]) {
                            case 1:
                                B();
                                break;
                            case 2:
                                r(i);
                                break;
                            case 3:
                                s(i);
                                break;
                            case 4:
                                t(i);
                                break;
                            default:
                                if (i >= 32) {
                                    j(i);
                                    break;
                                } else {
                                    c(i, "string value");
                                    break;
                                }
                        }
                    }
                    return;
                }
                i = i3;
            }
            this.d = i;
        }
    }

    /* Access modifiers changed, original: protected */
    public JsonToken h(int i) throws IOException, JsonParseException {
        if (i != 39) {
            if (i == 43) {
                if (this.d >= this.e && !p()) {
                    D();
                }
                byte[] bArr = this.Q;
                int i2 = this.d;
                this.d = i2 + 1;
                return a(bArr[i2] & 255, false);
            } else if (i == 78) {
                a("NaN", 1);
                if (a(Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
                    return a("NaN", Double.NaN);
                }
                d("Non-standard token 'NaN': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
            }
        } else if (a(Feature.ALLOW_SINGLE_QUOTES)) {
            return I();
        }
        b(i, "expected a valid value (number, String, array, object, 'true', 'false' or 'null')");
        return null;
    }

    /* Access modifiers changed, original: protected */
    public JsonToken I() throws IOException, JsonParseException {
        char[] k = this.n.k();
        int[] iArr = S;
        byte[] bArr = this.Q;
        char[] cArr = k;
        int i = 0;
        while (true) {
            if (this.d >= this.e) {
                o();
            }
            if (i >= cArr.length) {
                cArr = this.n.m();
                i = 0;
            }
            int i2 = this.e;
            int length = this.d + (cArr.length - i);
            if (length < i2) {
                i2 = length;
            }
            while (this.d < i2) {
                length = this.d;
                this.d = length + 1;
                length = bArr[length] & 255;
                if (length != 39 && iArr[length] == 0) {
                    int i3 = i + 1;
                    cArr[i] = (char) length;
                    i = i3;
                } else if (length == 39) {
                    this.n.a(i);
                    return JsonToken.VALUE_STRING;
                } else {
                    switch (iArr[length]) {
                        case 1:
                            if (length != 34) {
                                length = B();
                                break;
                            }
                            break;
                        case 2:
                            length = n(length);
                            break;
                        case 3:
                            if (this.e - this.d < 2) {
                                length = o(length);
                                break;
                            }
                            length = p(length);
                            break;
                        case 4:
                            i2 = q(length);
                            length = i + 1;
                            cArr[i] = (char) (55296 | (i2 >> 10));
                            if (length >= cArr.length) {
                                cArr = this.n.m();
                                length = 0;
                            }
                            int i4 = length;
                            length = 56320 | (i2 & 1023);
                            i = i4;
                            break;
                        default:
                            if (length < 32) {
                                c(length, "string value");
                            }
                            j(length);
                            break;
                    }
                    if (i >= cArr.length) {
                        cArr = this.n.m();
                        i = 0;
                    }
                    i2 = i + 1;
                    cArr[i] = (char) length;
                    i = i2;
                }
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public JsonToken a(int i, boolean z) throws IOException, JsonParseException {
        if (i == 73) {
            if (this.d >= this.e && !p()) {
                D();
            }
            byte[] bArr = this.Q;
            int i2 = this.d;
            this.d = i2 + 1;
            i = bArr[i2];
            double d = Double.POSITIVE_INFINITY;
            String str;
            StringBuilder stringBuilder;
            if (i == 78) {
                str = z ? "-INF" : "+INF";
                a(str, 3);
                if (a(Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
                    if (z) {
                        d = Double.NEGATIVE_INFINITY;
                    }
                    return a(str, d);
                }
                stringBuilder = new StringBuilder();
                stringBuilder.append("Non-standard token '");
                stringBuilder.append(str);
                stringBuilder.append("': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
                d(stringBuilder.toString());
            } else if (i == 110) {
                str = z ? "-Infinity" : "+Infinity";
                a(str, 3);
                if (a(Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
                    if (z) {
                        d = Double.NEGATIVE_INFINITY;
                    }
                    return a(str, d);
                }
                stringBuilder = new StringBuilder();
                stringBuilder.append("Non-standard token '");
                stringBuilder.append(str);
                stringBuilder.append("': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
                d(stringBuilder.toString());
            }
        }
        a(i, "expected digit (0-9) to follow minus sign, for valid numeric value");
        return null;
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Missing block: B:26:0x0072, code skipped:
            return;
     */
    public void a(java.lang.String r5, int r6) throws java.io.IOException, com.fasterxml.jackson.core.JsonParseException {
        /*
        r4 = this;
        r0 = r5.length();
    L_0x0004:
        r1 = r4.d;
        r2 = r4.e;
        if (r1 < r2) goto L_0x0015;
    L_0x000a:
        r1 = r4.p();
        if (r1 != 0) goto L_0x0015;
    L_0x0010:
        r1 = " in a value";
        r4.c(r1);
    L_0x0015:
        r1 = r4.Q;
        r2 = r4.d;
        r1 = r1[r2];
        r2 = r5.charAt(r6);
        r3 = 0;
        if (r1 == r2) goto L_0x002b;
    L_0x0022:
        r1 = r5.substring(r3, r6);
        r2 = "'null', 'true', 'false' or NaN";
        r4.a(r1, r2);
    L_0x002b:
        r1 = r4.d;
        r1 = r1 + 1;
        r4.d = r1;
        r6 = r6 + 1;
        if (r6 < r0) goto L_0x0004;
    L_0x0035:
        r0 = r4.d;
        r1 = r4.e;
        if (r0 < r1) goto L_0x0042;
    L_0x003b:
        r0 = r4.p();
        if (r0 != 0) goto L_0x0042;
    L_0x0041:
        return;
    L_0x0042:
        r0 = r4.Q;
        r1 = r4.d;
        r0 = r0[r1];
        r0 = r0 & 255;
        r1 = 48;
        if (r0 < r1) goto L_0x0072;
    L_0x004e:
        r1 = 93;
        if (r0 == r1) goto L_0x0072;
    L_0x0052:
        r1 = 125; // 0x7d float:1.75E-43 double:6.2E-322;
        if (r0 != r1) goto L_0x0057;
    L_0x0056:
        goto L_0x0072;
    L_0x0057:
        r0 = r4.i(r0);
        r0 = (char) r0;
        r0 = java.lang.Character.isJavaIdentifierPart(r0);
        if (r0 == 0) goto L_0x0071;
    L_0x0062:
        r0 = r4.d;
        r0 = r0 + 1;
        r4.d = r0;
        r5 = r5.substring(r3, r6);
        r6 = "'null', 'true', 'false' or NaN";
        r4.a(r5, r6);
    L_0x0071:
        return;
    L_0x0072:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.b.g.a(java.lang.String, int):void");
    }

    /* Access modifiers changed, original: protected */
    public void a(String str, String str2) throws IOException, JsonParseException {
        StringBuilder stringBuilder = new StringBuilder(str);
        while (true) {
            if (this.d >= this.e && !p()) {
                break;
            }
            byte[] bArr = this.Q;
            int i = this.d;
            this.d = i + 1;
            char i2 = (char) i(bArr[i]);
            if (!Character.isJavaIdentifierPart(i2)) {
                break;
            }
            stringBuilder.append(i2);
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Unrecognized token '");
        stringBuilder2.append(stringBuilder.toString());
        stringBuilder2.append("': was expecting ");
        stringBuilder2.append(str2);
        d(stringBuilder2.toString());
    }

    private int N() throws IOException, JsonParseException {
        while (true) {
            if (this.d < this.e || p()) {
                byte[] bArr = this.Q;
                int i = this.d;
                this.d = i + 1;
                int i2 = bArr[i] & 255;
                if (i2 > 32) {
                    if (i2 != 47) {
                        return i2;
                    }
                    P();
                } else if (i2 != 32) {
                    if (i2 == 10) {
                        K();
                    } else if (i2 == 13) {
                        J();
                    } else if (i2 != 9) {
                        b(i2);
                    }
                }
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Unexpected end-of-input within/between ");
                stringBuilder.append(this.l.d());
                stringBuilder.append(" entries");
                throw a(stringBuilder.toString());
            }
        }
    }

    private int O() throws IOException, JsonParseException {
        while (true) {
            if (this.d < this.e || p()) {
                byte[] bArr = this.Q;
                int i = this.d;
                this.d = i + 1;
                int i2 = bArr[i] & 255;
                if (i2 > 32) {
                    if (i2 != 47) {
                        return i2;
                    }
                    P();
                } else if (i2 != 32) {
                    if (i2 == 10) {
                        K();
                    } else if (i2 == 13) {
                        J();
                    } else if (i2 != 9) {
                        b(i2);
                    }
                }
            } else {
                t();
                return -1;
            }
        }
    }

    private void P() throws IOException, JsonParseException {
        if (!a(Feature.ALLOW_COMMENTS)) {
            b(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
        }
        if (this.d >= this.e && !p()) {
            c(" in a comment");
        }
        byte[] bArr = this.Q;
        int i = this.d;
        this.d = i + 1;
        int i2 = bArr[i] & 255;
        if (i2 == 47) {
            R();
        } else if (i2 == 42) {
            Q();
        } else {
            b(i2, "was expecting either '*' or '/' for a comment");
        }
    }

    private void Q() throws IOException, JsonParseException {
        int[] e = com.fasterxml.jackson.core.io.b.e();
        while (true) {
            if (this.d < this.e || p()) {
                byte[] bArr = this.Q;
                int i = this.d;
                this.d = i + 1;
                int i2 = bArr[i] & 255;
                i = e[i2];
                if (i != 0) {
                    if (i == 10) {
                        K();
                    } else if (i == 13) {
                        J();
                    } else if (i != 42) {
                        switch (i) {
                            case 2:
                                r(i2);
                                break;
                            case 3:
                                s(i2);
                                break;
                            case 4:
                                t(i2);
                                break;
                            default:
                                j(i2);
                                break;
                        }
                    } else if (this.d < this.e || p()) {
                        if (this.Q[this.d] == (byte) 47) {
                            this.d++;
                            return;
                        }
                    }
                }
            }
        }
        c(" in a comment");
    }

    private void R() throws IOException, JsonParseException {
        int[] e = com.fasterxml.jackson.core.io.b.e();
        while (true) {
            if (this.d < this.e || p()) {
                byte[] bArr = this.Q;
                int i = this.d;
                this.d = i + 1;
                int i2 = bArr[i] & 255;
                i = e[i2];
                if (i != 0) {
                    if (i == 10) {
                        K();
                        return;
                    } else if (i == 13) {
                        J();
                        return;
                    } else if (i != 42) {
                        switch (i) {
                            case 2:
                                r(i2);
                                break;
                            case 3:
                                s(i2);
                                break;
                            case 4:
                                t(i2);
                                break;
                            default:
                                j(i2);
                                break;
                        }
                    }
                }
            } else {
                return;
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public char B() throws IOException, JsonParseException {
        if (this.d >= this.e && !p()) {
            c(" in character escape sequence");
        }
        byte[] bArr = this.Q;
        int i = this.d;
        this.d = i + 1;
        byte b = bArr[i];
        if (b == (byte) 34 || b == (byte) 47 || b == (byte) 92) {
            return (char) b;
        }
        if (b == (byte) 98) {
            return 8;
        }
        if (b == (byte) 102) {
            return 12;
        }
        if (b == (byte) 110) {
            return 10;
        }
        if (b == (byte) 114) {
            return 13;
        }
        switch (b) {
            case (byte) 116:
                return 9;
            case (byte) 117:
                int i2 = 0;
                i = 0;
                while (i2 < 4) {
                    if (this.d >= this.e && !p()) {
                        c(" in character escape sequence");
                    }
                    byte[] bArr2 = this.Q;
                    int i3 = this.d;
                    this.d = i3 + 1;
                    byte b2 = bArr2[i3];
                    i3 = com.fasterxml.jackson.core.io.b.a(b2);
                    if (i3 < 0) {
                        b((int) b2, "expected a hex-digit for character escape sequence");
                    }
                    i = (i << 4) | i3;
                    i2++;
                }
                return (char) i;
            default:
                return a((char) i(b));
        }
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:28:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x003e  */
    public int i(int r7) throws java.io.IOException, com.fasterxml.jackson.core.JsonParseException {
        /*
        r6 = this;
        if (r7 >= 0) goto L_0x0064;
    L_0x0002:
        r0 = r7 & 224;
        r1 = 2;
        r2 = 1;
        r3 = 192; // 0xc0 float:2.69E-43 double:9.5E-322;
        if (r0 != r3) goto L_0x000e;
    L_0x000a:
        r7 = r7 & 31;
    L_0x000c:
        r0 = r2;
        goto L_0x0028;
    L_0x000e:
        r0 = r7 & 240;
        r3 = 224; // 0xe0 float:3.14E-43 double:1.107E-321;
        if (r0 != r3) goto L_0x0018;
    L_0x0014:
        r7 = r7 & 15;
        r0 = r1;
        goto L_0x0028;
    L_0x0018:
        r0 = r7 & 248;
        r3 = 240; // 0xf0 float:3.36E-43 double:1.186E-321;
        if (r0 != r3) goto L_0x0022;
    L_0x001e:
        r7 = r7 & 7;
        r0 = 3;
        goto L_0x0028;
    L_0x0022:
        r0 = r7 & 255;
        r6.k(r0);
        goto L_0x000c;
    L_0x0028:
        r3 = r6.S();
        r4 = r3 & 192;
        r5 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        if (r4 == r5) goto L_0x0037;
    L_0x0032:
        r4 = r3 & 255;
        r6.l(r4);
    L_0x0037:
        r7 = r7 << 6;
        r3 = r3 & 63;
        r7 = r7 | r3;
        if (r0 <= r2) goto L_0x0064;
    L_0x003e:
        r2 = r6.S();
        r3 = r2 & 192;
        if (r3 == r5) goto L_0x004b;
    L_0x0046:
        r3 = r2 & 255;
        r6.l(r3);
    L_0x004b:
        r7 = r7 << 6;
        r2 = r2 & 63;
        r7 = r7 | r2;
        if (r0 <= r1) goto L_0x0064;
    L_0x0052:
        r0 = r6.S();
        r1 = r0 & 192;
        if (r1 == r5) goto L_0x005f;
    L_0x005a:
        r1 = r0 & 255;
        r6.l(r1);
    L_0x005f:
        r7 = r7 << 6;
        r0 = r0 & 63;
        r7 = r7 | r0;
    L_0x0064:
        return r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.b.g.i(int):int");
    }

    private int n(int i) throws IOException, JsonParseException {
        if (this.d >= this.e) {
            o();
        }
        byte[] bArr = this.Q;
        int i2 = this.d;
        this.d = i2 + 1;
        byte b = bArr[i2];
        if ((b & PsExtractor.AUDIO_STREAM) != 128) {
            a(b & 255, this.d);
        }
        return ((i & 31) << 6) | (b & 63);
    }

    private int o(int i) throws IOException, JsonParseException {
        if (this.d >= this.e) {
            o();
        }
        i &= 15;
        byte[] bArr = this.Q;
        int i2 = this.d;
        this.d = i2 + 1;
        byte b = bArr[i2];
        if ((b & PsExtractor.AUDIO_STREAM) != 128) {
            a(b & 255, this.d);
        }
        i = (i << 6) | (b & 63);
        if (this.d >= this.e) {
            o();
        }
        bArr = this.Q;
        i2 = this.d;
        this.d = i2 + 1;
        b = bArr[i2];
        if ((b & PsExtractor.AUDIO_STREAM) != 128) {
            a(b & 255, this.d);
        }
        return (i << 6) | (b & 63);
    }

    private int p(int i) throws IOException, JsonParseException {
        i &= 15;
        byte[] bArr = this.Q;
        int i2 = this.d;
        this.d = i2 + 1;
        byte b = bArr[i2];
        if ((b & PsExtractor.AUDIO_STREAM) != 128) {
            a(b & 255, this.d);
        }
        i = (i << 6) | (b & 63);
        bArr = this.Q;
        i2 = this.d;
        this.d = i2 + 1;
        b = bArr[i2];
        if ((b & PsExtractor.AUDIO_STREAM) != 128) {
            a(b & 255, this.d);
        }
        return (i << 6) | (b & 63);
    }

    private int q(int i) throws IOException, JsonParseException {
        if (this.d >= this.e) {
            o();
        }
        byte[] bArr = this.Q;
        int i2 = this.d;
        this.d = i2 + 1;
        byte b = bArr[i2];
        if ((b & PsExtractor.AUDIO_STREAM) != 128) {
            a(b & 255, this.d);
        }
        i = ((i & 7) << 6) | (b & 63);
        if (this.d >= this.e) {
            o();
        }
        bArr = this.Q;
        i2 = this.d;
        this.d = i2 + 1;
        b = bArr[i2];
        if ((b & PsExtractor.AUDIO_STREAM) != 128) {
            a(b & 255, this.d);
        }
        i = (i << 6) | (b & 63);
        if (this.d >= this.e) {
            o();
        }
        bArr = this.Q;
        i2 = this.d;
        this.d = i2 + 1;
        b = bArr[i2];
        if ((b & PsExtractor.AUDIO_STREAM) != 128) {
            a(b & 255, this.d);
        }
        return ((i << 6) | (b & 63)) - 65536;
    }

    private void r(int i) throws IOException, JsonParseException {
        if (this.d >= this.e) {
            o();
        }
        byte[] bArr = this.Q;
        int i2 = this.d;
        this.d = i2 + 1;
        byte b = bArr[i2];
        if ((b & PsExtractor.AUDIO_STREAM) != 128) {
            a(b & 255, this.d);
        }
    }

    private void s(int i) throws IOException, JsonParseException {
        if (this.d >= this.e) {
            o();
        }
        byte[] bArr = this.Q;
        int i2 = this.d;
        this.d = i2 + 1;
        byte b = bArr[i2];
        if ((b & PsExtractor.AUDIO_STREAM) != 128) {
            a(b & 255, this.d);
        }
        if (this.d >= this.e) {
            o();
        }
        bArr = this.Q;
        i2 = this.d;
        this.d = i2 + 1;
        b = bArr[i2];
        if ((b & PsExtractor.AUDIO_STREAM) != 128) {
            a(b & 255, this.d);
        }
    }

    private void t(int i) throws IOException, JsonParseException {
        if (this.d >= this.e) {
            o();
        }
        byte[] bArr = this.Q;
        int i2 = this.d;
        this.d = i2 + 1;
        byte b = bArr[i2];
        if ((b & PsExtractor.AUDIO_STREAM) != 128) {
            a(b & 255, this.d);
        }
        if (this.d >= this.e) {
            o();
        }
        bArr = this.Q;
        i2 = this.d;
        this.d = i2 + 1;
        b = bArr[i2];
        if ((b & PsExtractor.AUDIO_STREAM) != 128) {
            a(b & 255, this.d);
        }
        if (this.d >= this.e) {
            o();
        }
        bArr = this.Q;
        i2 = this.d;
        this.d = i2 + 1;
        b = bArr[i2];
        if ((b & PsExtractor.AUDIO_STREAM) != 128) {
            a(b & 255, this.d);
        }
    }

    /* Access modifiers changed, original: protected */
    public void J() throws IOException {
        if ((this.d < this.e || p()) && this.Q[this.d] == (byte) 10) {
            this.d++;
        }
        this.g++;
        this.h = this.d;
    }

    /* Access modifiers changed, original: protected */
    public void K() throws IOException {
        this.g++;
        this.h = this.d;
    }

    private int S() throws IOException, JsonParseException {
        if (this.d >= this.e) {
            o();
        }
        byte[] bArr = this.Q;
        int i = this.d;
        this.d = i + 1;
        return bArr[i] & 255;
    }

    /* Access modifiers changed, original: protected */
    public void j(int i) throws JsonParseException {
        if (i < 32) {
            b(i);
        }
        k(i);
    }

    /* Access modifiers changed, original: protected */
    public void k(int i) throws JsonParseException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Invalid UTF-8 start byte 0x");
        stringBuilder.append(Integer.toHexString(i));
        d(stringBuilder.toString());
    }

    /* Access modifiers changed, original: protected */
    public void l(int i) throws JsonParseException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Invalid UTF-8 middle byte 0x");
        stringBuilder.append(Integer.toHexString(i));
        d(stringBuilder.toString());
    }

    /* Access modifiers changed, original: protected */
    public void a(int i, int i2) throws JsonParseException {
        this.d = i2;
        l(i);
    }

    public static int[] a(int[] iArr, int i) {
        if (iArr == null) {
            return new int[i];
        }
        int length = iArr.length;
        int[] iArr2 = new int[(i + length)];
        System.arraycopy(iArr, 0, iArr2, 0, length);
        return iArr2;
    }
}
