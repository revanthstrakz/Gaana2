package com.fasterxml.jackson.core.b;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.a.b;
import com.fasterxml.jackson.core.io.c;
import com.moe.pushlibrary.MoEWorker;
import java.io.IOException;
import java.io.Reader;

public final class e extends b {
    protected Reader L;
    protected char[] M;
    protected com.fasterxml.jackson.core.b N;
    protected final com.fasterxml.jackson.core.c.b O;
    protected final int P;
    protected boolean Q = false;

    public e(c cVar, int i, Reader reader, com.fasterxml.jackson.core.b bVar, com.fasterxml.jackson.core.c.b bVar2) {
        super(cVar, i);
        this.L = reader;
        this.M = cVar.g();
        this.N = bVar;
        this.O = bVar2;
        this.P = bVar2.e();
    }

    /* Access modifiers changed, original: protected */
    public boolean p() throws IOException {
        this.f += (long) this.e;
        this.h -= this.e;
        if (this.L != null) {
            int read = this.L.read(this.M, 0, this.M.length);
            if (read > 0) {
                this.d = 0;
                this.e = read;
                return true;
            }
            r();
            if (read == 0) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Reader returned 0 characters when trying to read ");
                stringBuilder.append(this.e);
                throw new IOException(stringBuilder.toString());
            }
        }
        return false;
    }

    /* Access modifiers changed, original: protected */
    public char e(String str) throws IOException, JsonParseException {
        if (this.d >= this.e && !p()) {
            c(str);
        }
        char[] cArr = this.M;
        int i = this.d;
        this.d = i + 1;
        return cArr[i];
    }

    /* Access modifiers changed, original: protected */
    public void r() throws IOException {
        if (this.L != null) {
            if (this.b.c() || a(Feature.AUTO_CLOSE_SOURCE)) {
                this.L.close();
            }
            this.L = null;
        }
    }

    /* Access modifiers changed, original: protected */
    public void s() throws IOException {
        super.s();
        char[] cArr = this.M;
        if (cArr != null) {
            this.M = null;
            this.b.a(cArr);
        }
    }

    public String f() throws IOException, JsonParseException {
        JsonToken jsonToken = this.K;
        if (jsonToken != JsonToken.VALUE_STRING) {
            return a(jsonToken);
        }
        if (this.Q) {
            this.Q = false;
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
        if (this.Q) {
            I();
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
            boolean c = this.l.c();
            if (c) {
                this.l.a(e(O));
                this.K = JsonToken.FIELD_NAME;
                O = N();
                if (O != 58) {
                    b(O, "was expecting a colon to separate field name and value");
                }
                O = N();
            }
            if (O != 34) {
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
                                                    jsonToken = g(O);
                                                    break;
                                            }
                                        }
                                    }
                                    if (!c) {
                                        this.l = this.l.b(this.j, this.k);
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
                        if (!c) {
                            this.l = this.l.a(this.j, this.k);
                        }
                        jsonToken = JsonToken.START_ARRAY;
                    }
                }
                jsonToken = d(O);
            } else {
                this.Q = true;
                jsonToken = JsonToken.VALUE_STRING;
            }
            if (c) {
                this.m = jsonToken;
                return this.K;
            }
            this.K = jsonToken;
            return jsonToken;
        }
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
        this.O.b();
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00ac  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x009a  */
    public com.fasterxml.jackson.core.JsonToken d(int r13) throws java.io.IOException, com.fasterxml.jackson.core.JsonParseException {
        /*
        r12 = this;
        r0 = 45;
        r1 = 0;
        r2 = 1;
        if (r13 != r0) goto L_0x0008;
    L_0x0006:
        r3 = r2;
        goto L_0x0009;
    L_0x0008:
        r3 = r1;
    L_0x0009:
        r4 = r12.d;
        r5 = r4 + -1;
        r6 = r12.e;
        r7 = 57;
        r8 = 48;
        if (r3 == 0) goto L_0x002f;
    L_0x0015:
        r13 = r12.e;
        if (r4 < r13) goto L_0x001b;
    L_0x0019:
        goto L_0x0098;
    L_0x001b:
        r13 = r12.M;
        r9 = r4 + 1;
        r13 = r13[r4];
        if (r13 > r7) goto L_0x0028;
    L_0x0023:
        if (r13 >= r8) goto L_0x0026;
    L_0x0025:
        goto L_0x0028;
    L_0x0026:
        r4 = r9;
        goto L_0x002f;
    L_0x0028:
        r12.d = r9;
        r13 = r12.a(r13, r2);
        return r13;
    L_0x002f:
        if (r13 != r8) goto L_0x0033;
    L_0x0031:
        goto L_0x0098;
    L_0x0033:
        r13 = r12.e;
        if (r4 < r13) goto L_0x0039;
    L_0x0037:
        goto L_0x0098;
    L_0x0039:
        r13 = r12.M;
        r9 = r4 + 1;
        r13 = r13[r4];
        if (r13 < r8) goto L_0x0048;
    L_0x0041:
        if (r13 <= r7) goto L_0x0044;
    L_0x0043:
        goto L_0x0048;
    L_0x0044:
        r2 = r2 + 1;
        r4 = r9;
        goto L_0x0033;
    L_0x0048:
        r4 = 46;
        if (r13 != r4) goto L_0x006b;
    L_0x004c:
        r13 = r1;
    L_0x004d:
        if (r9 < r6) goto L_0x0050;
    L_0x004f:
        goto L_0x0098;
    L_0x0050:
        r4 = r12.M;
        r10 = r9 + 1;
        r4 = r4[r9];
        if (r4 < r8) goto L_0x005f;
    L_0x0058:
        if (r4 <= r7) goto L_0x005b;
    L_0x005a:
        goto L_0x005f;
    L_0x005b:
        r13 = r13 + 1;
        r9 = r10;
        goto L_0x004d;
    L_0x005f:
        if (r13 != 0) goto L_0x0066;
    L_0x0061:
        r9 = "Decimal point not followed by a digit";
        r12.a(r4, r9);
    L_0x0066:
        r9 = r10;
        r11 = r4;
        r4 = r13;
        r13 = r11;
        goto L_0x006c;
    L_0x006b:
        r4 = r1;
    L_0x006c:
        r10 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        if (r13 == r10) goto L_0x0074;
    L_0x0070:
        r10 = 69;
        if (r13 != r10) goto L_0x00b1;
    L_0x0074:
        if (r9 < r6) goto L_0x0077;
    L_0x0076:
        goto L_0x0098;
    L_0x0077:
        r13 = r12.M;
        r10 = r9 + 1;
        r13 = r13[r9];
        if (r13 == r0) goto L_0x0086;
    L_0x007f:
        r0 = 43;
        if (r13 != r0) goto L_0x0084;
    L_0x0083:
        goto L_0x0086;
    L_0x0084:
        r9 = r10;
        goto L_0x0090;
    L_0x0086:
        if (r10 < r6) goto L_0x0089;
    L_0x0088:
        goto L_0x0098;
    L_0x0089:
        r13 = r12.M;
        r0 = r10 + 1;
        r13 = r13[r10];
    L_0x008f:
        r9 = r0;
    L_0x0090:
        if (r13 > r7) goto L_0x00aa;
    L_0x0092:
        if (r13 < r8) goto L_0x00aa;
    L_0x0094:
        r1 = r1 + 1;
        if (r9 < r6) goto L_0x00a3;
    L_0x0098:
        if (r3 == 0) goto L_0x009c;
    L_0x009a:
        r5 = r5 + 1;
    L_0x009c:
        r12.d = r5;
        r13 = r12.a(r3);
        return r13;
    L_0x00a3:
        r13 = r12.M;
        r0 = r9 + 1;
        r13 = r13[r9];
        goto L_0x008f;
    L_0x00aa:
        if (r1 != 0) goto L_0x00b1;
    L_0x00ac:
        r0 = "Exponent indicator not followed by a digit";
        r12.a(r13, r0);
    L_0x00b1:
        r9 = r9 + -1;
        r12.d = r9;
        r9 = r9 - r5;
        r13 = r12.n;
        r0 = r12.M;
        r13.a(r0, r5, r9);
        r13 = r12.a(r3, r2, r4, r1);
        return r13;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.b.e.d(int):com.fasterxml.jackson.core.JsonToken");
    }

    private JsonToken a(boolean z) throws IOException, JsonParseException {
        int i;
        char[] cArr;
        char c;
        int i2;
        char[] cArr2;
        int i3;
        int i4;
        int i5;
        char[] k = this.n.k();
        int i6 = 0;
        if (z) {
            k[0] = '-';
            i = 1;
        } else {
            i = 0;
        }
        if (this.d < this.e) {
            cArr = this.M;
            int i7 = this.d;
            this.d = i7 + 1;
            c = cArr[i7];
        } else {
            c = e("No digit following minus sign");
        }
        if (c == '0') {
            c = M();
        }
        char[] cArr3 = k;
        int i8 = 0;
        while (c >= '0' && c <= '9') {
            i8++;
            if (i >= cArr3.length) {
                cArr3 = this.n.m();
                i = 0;
            }
            i2 = i + 1;
            cArr3[i] = c;
            if (this.d >= this.e && !p()) {
                c = 0;
                i = i2;
                i2 = 1;
                break;
            }
            cArr2 = this.M;
            i3 = this.d;
            this.d = i3 + 1;
            c = cArr2[i3];
            i = i2;
        }
        i2 = 0;
        if (i8 == 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Missing integer part (next char ");
            stringBuilder.append(com.fasterxml.jackson.core.a.c.c((int) c));
            stringBuilder.append(")");
            b(stringBuilder.toString());
        }
        if (c == '.') {
            i4 = i + 1;
            cArr3[i] = c;
            i = 0;
            while (true) {
                if (this.d >= this.e && !p()) {
                    i2 = 1;
                    break;
                }
                cArr = this.M;
                i5 = this.d;
                this.d = i5 + 1;
                c = cArr[i5];
                if (c < '0' || c > '9') {
                    break;
                }
                i++;
                if (i4 >= cArr3.length) {
                    cArr3 = this.n.m();
                    i4 = 0;
                }
                i5 = i4 + 1;
                cArr3[i4] = c;
                i4 = i5;
            }
            if (i == 0) {
                a((int) c, "Decimal point not followed by a digit");
            }
            int i9 = i2;
            i2 = i;
            i = i4;
            i4 = i9;
        } else {
            i4 = i2;
            i2 = 0;
        }
        if (c == 'e' || c == 'E') {
            char c2;
            int i10;
            if (i >= cArr3.length) {
                cArr3 = this.n.m();
                i = 0;
            }
            i5 = i + 1;
            cArr3[i] = c;
            if (this.d < this.e) {
                cArr2 = this.M;
                i3 = this.d;
                this.d = i3 + 1;
                c2 = cArr2[i3];
            } else {
                c2 = e("expected a digit for number exponent");
            }
            if (c2 == '-' || c2 == '+') {
                if (i5 >= cArr3.length) {
                    cArr3 = this.n.m();
                    i5 = 0;
                }
                i10 = i5 + 1;
                cArr3[i5] = c2;
                if (this.d < this.e) {
                    cArr2 = this.M;
                    i3 = this.d;
                    this.d = i3 + 1;
                    c2 = cArr2[i3];
                } else {
                    c2 = e("expected a digit for number exponent");
                }
                i5 = i10;
            }
            i10 = 0;
            while (c2 <= '9' && c2 >= '0') {
                i10++;
                if (i5 >= cArr3.length) {
                    i5 = 0;
                    cArr3 = this.n.m();
                }
                i3 = i5 + 1;
                cArr3[i5] = c2;
                if (this.d >= this.e && !p()) {
                    i6 = i10;
                    i4 = 1;
                    break;
                }
                cArr2 = this.M;
                i5 = this.d;
                this.d = i5 + 1;
                c2 = cArr2[i5];
                i5 = i3;
            }
            i6 = i10;
            i3 = i5;
            if (i6 == 0) {
                a((int) c2, "Exponent indicator not followed by a digit");
            }
            i = i3;
        }
        if (i4 == 0) {
            this.d--;
        }
        this.n.a(i);
        return a(z, i8, i2, i6);
    }

    private char M() throws IOException, JsonParseException {
        if (this.d >= this.e && !p()) {
            return '0';
        }
        char c = this.M[this.d];
        if (c < '0' || c > '9') {
            return '0';
        }
        if (!a(Feature.ALLOW_NUMERIC_LEADING_ZEROS)) {
            b("Leading zeroes not allowed");
        }
        this.d++;
        if (c == '0') {
            do {
                if (this.d >= this.e && !p()) {
                    break;
                }
                c = this.M[this.d];
                if (c < '0' || c > '9') {
                    return '0';
                }
                this.d++;
            } while (c == '0');
        }
        return c;
    }

    /* Access modifiers changed, original: protected */
    public JsonToken a(int i, boolean z) throws IOException, JsonParseException {
        if (i == 73) {
            if (this.d >= this.e && !p()) {
                D();
            }
            char[] cArr = this.M;
            int i2 = this.d;
            this.d = i2 + 1;
            i = cArr[i2];
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
    public String e(int i) throws IOException, JsonParseException {
        if (i != 34) {
            return f(i);
        }
        i = this.d;
        int i2 = this.P;
        int i3 = this.e;
        if (i < i3) {
            int[] a = com.fasterxml.jackson.core.io.b.a();
            char length = a.length;
            do {
                char c = this.M[i];
                if (c >= length || a[c] == 0) {
                    i2 = (i2 * 33) + c;
                    i++;
                } else if (c == '\"') {
                    int i4 = this.d;
                    this.d = i + 1;
                    return this.O.a(this.M, i4, i - i4, i2);
                }
            } while (i < i3);
        }
        i3 = this.d;
        this.d = i;
        return a(i3, i2, 34);
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x008b  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0083  */
    private java.lang.String a(int r5, int r6, int r7) throws java.io.IOException, com.fasterxml.jackson.core.JsonParseException {
        /*
        r4 = this;
        r0 = r4.n;
        r1 = r4.M;
        r2 = r4.d;
        r2 = r2 - r5;
        r0.a(r1, r5, r2);
        r5 = r4.n;
        r5 = r5.j();
        r0 = r4.n;
        r0 = r0.l();
    L_0x0016:
        r1 = r4.d;
        r2 = r4.e;
        if (r1 < r2) goto L_0x003c;
    L_0x001c:
        r1 = r4.p();
        if (r1 != 0) goto L_0x003c;
    L_0x0022:
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = ": was expecting closing '";
        r1.append(r2);
        r2 = (char) r7;
        r1.append(r2);
        r2 = "' for name";
        r1.append(r2);
        r1 = r1.toString();
        r4.c(r1);
    L_0x003c:
        r1 = r4.M;
        r2 = r4.d;
        r3 = r2 + 1;
        r4.d = r3;
        r1 = r1[r2];
        r2 = 92;
        if (r1 > r2) goto L_0x0078;
    L_0x004a:
        if (r1 != r2) goto L_0x0051;
    L_0x004c:
        r2 = r4.B();
        goto L_0x0079;
    L_0x0051:
        if (r1 > r7) goto L_0x0078;
    L_0x0053:
        if (r1 != r7) goto L_0x006f;
    L_0x0055:
        r5 = r4.n;
        r5.a(r0);
        r5 = r4.n;
        r7 = r5.e();
        r0 = r5.d();
        r5 = r5.c();
        r1 = r4.O;
        r5 = r1.a(r7, r0, r5, r6);
        return r5;
    L_0x006f:
        r2 = 32;
        if (r1 >= r2) goto L_0x0078;
    L_0x0073:
        r2 = "name";
        r4.c(r1, r2);
    L_0x0078:
        r2 = r1;
    L_0x0079:
        r6 = r6 * 33;
        r6 = r6 + r1;
        r1 = r0 + 1;
        r5[r0] = r2;
        r0 = r5.length;
        if (r1 < r0) goto L_0x008b;
    L_0x0083:
        r5 = r4.n;
        r5 = r5.m();
        r0 = 0;
        goto L_0x0016;
    L_0x008b:
        r0 = r1;
        goto L_0x0016;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.b.e.a(int, int, int):java.lang.String");
    }

    /* Access modifiers changed, original: protected */
    public String f(int i) throws IOException, JsonParseException {
        if (i == 39 && a(Feature.ALLOW_SINGLE_QUOTES)) {
            return F();
        }
        if (!a(Feature.ALLOW_UNQUOTED_FIELD_NAMES)) {
            b(i, "was expecting double-quote to start field name");
        }
        int[] c = com.fasterxml.jackson.core.io.b.c();
        char length = c.length;
        boolean isJavaIdentifierPart = i < length ? c[i] == 0 && (i < 48 || i > 57) : Character.isJavaIdentifierPart((char) i);
        if (!isJavaIdentifierPart) {
            b(i, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name");
        }
        i = this.d;
        int i2 = this.P;
        int i3 = this.e;
        if (i < i3) {
            do {
                char c2 = this.M[i];
                int i4;
                if (c2 < length) {
                    if (c[c2] != 0) {
                        i4 = this.d - 1;
                        this.d = i;
                        return this.O.a(this.M, i4, i - i4, i2);
                    }
                } else if (!Character.isJavaIdentifierPart((char) c2)) {
                    i4 = this.d - 1;
                    this.d = i;
                    return this.O.a(this.M, i4, i - i4, i2);
                }
                i2 = (i2 * 33) + c2;
                i++;
            } while (i < i3);
        }
        int i5 = this.d - 1;
        this.d = i;
        return a(i5, i2, c);
    }

    /* Access modifiers changed, original: protected */
    public String F() throws IOException, JsonParseException {
        int i = this.d;
        int i2 = this.P;
        int i3 = this.e;
        if (i < i3) {
            int[] a = com.fasterxml.jackson.core.io.b.a();
            char length = a.length;
            do {
                char c = this.M[i];
                if (c != '\'') {
                    if (c < length && a[c] != 0) {
                        break;
                    }
                    i2 = (i2 * 33) + c;
                    i++;
                } else {
                    i3 = this.d;
                    this.d = i + 1;
                    return this.O.a(this.M, i3, i - i3, i2);
                }
            } while (i < i3);
        }
        i3 = this.d;
        this.d = i;
        return a(i3, i2, 39);
    }

    /* Access modifiers changed, original: protected */
    public JsonToken g(int i) throws IOException, JsonParseException {
        if (i != 39) {
            if (i == 43) {
                if (this.d >= this.e && !p()) {
                    D();
                }
                char[] cArr = this.M;
                int i2 = this.d;
                this.d = i2 + 1;
                return a(cArr[i2], false);
            } else if (i == 78) {
                a("NaN", 1);
                if (a(Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
                    return a("NaN", Double.NaN);
                }
                d("Non-standard token 'NaN': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
            }
        } else if (a(Feature.ALLOW_SINGLE_QUOTES)) {
            return G();
        }
        b(i, "expected a valid value (number, String, array, object, 'true', 'false' or 'null')");
        return null;
    }

    /* Access modifiers changed, original: protected */
    public JsonToken G() throws IOException, JsonParseException {
        char[] k = this.n.k();
        int l = this.n.l();
        while (true) {
            if (this.d >= this.e && !p()) {
                c(": was expecting closing quote for a string value");
            }
            char[] cArr = this.M;
            int i = this.d;
            this.d = i + 1;
            char c = cArr[i];
            if (c <= '\\') {
                if (c == '\\') {
                    c = B();
                } else if (c <= '\'') {
                    if (c == '\'') {
                        this.n.a(l);
                        return JsonToken.VALUE_STRING;
                    } else if (c < ' ') {
                        c(c, "string value");
                    }
                }
            }
            if (l >= k.length) {
                k = this.n.m();
                l = 0;
            }
            i = l + 1;
            k[l] = c;
            l = i;
        }
    }

    private String a(int i, int i2, int[] iArr) throws IOException, JsonParseException {
        this.n.a(this.M, i, this.d - i);
        char[] j = this.n.j();
        int l = this.n.l();
        char length = iArr.length;
        while (true) {
            if (this.d >= this.e && !p()) {
                break;
            }
            char c = this.M[this.d];
            if (c > length) {
                if (!Character.isJavaIdentifierPart(c)) {
                    break;
                }
            } else if (iArr[c] != 0) {
                break;
            }
            this.d++;
            i2 = (i2 * 33) + c;
            int i3 = l + 1;
            j[l] = c;
            if (i3 >= j.length) {
                j = this.n.m();
                l = 0;
            } else {
                l = i3;
            }
        }
        this.n.a(l);
        com.fasterxml.jackson.core.util.b bVar = this.n;
        return this.O.a(bVar.e(), bVar.d(), bVar.c(), i2);
    }

    /* Access modifiers changed, original: protected */
    public void q() throws IOException, JsonParseException {
        int i = this.d;
        int i2 = this.e;
        if (i < i2) {
            int[] a = com.fasterxml.jackson.core.io.b.a();
            char length = a.length;
            do {
                char c = this.M[i];
                if (c >= length || a[c] == 0) {
                    i++;
                } else if (c == '\"') {
                    this.n.a(this.M, this.d, i - this.d);
                    this.d = i + 1;
                    return;
                }
            } while (i < i2);
        }
        this.n.b(this.M, this.d, i - this.d);
        this.d = i;
        H();
    }

    /* Access modifiers changed, original: protected */
    public void H() throws IOException, JsonParseException {
        char[] j = this.n.j();
        int l = this.n.l();
        while (true) {
            if (this.d >= this.e && !p()) {
                c(": was expecting closing quote for a string value");
            }
            char[] cArr = this.M;
            int i = this.d;
            this.d = i + 1;
            char c = cArr[i];
            if (c <= '\\') {
                if (c == '\\') {
                    c = B();
                } else if (c <= '\"') {
                    if (c == '\"') {
                        this.n.a(l);
                        return;
                    } else if (c < ' ') {
                        c(c, "string value");
                    }
                }
            }
            if (l >= j.length) {
                j = this.n.m();
                l = 0;
            }
            i = l + 1;
            j[l] = c;
            l = i;
        }
    }

    /* Access modifiers changed, original: protected */
    public void I() throws IOException, JsonParseException {
        this.Q = false;
        int i = this.d;
        int i2 = this.e;
        char[] cArr = this.M;
        while (true) {
            if (i >= i2) {
                this.d = i;
                if (!p()) {
                    c(": was expecting closing quote for a string value");
                }
                i = this.d;
                i2 = this.e;
            }
            int i3 = i + 1;
            char c = cArr[i];
            if (c <= '\\') {
                if (c == '\\') {
                    this.d = i3;
                    B();
                    i = this.d;
                    i2 = this.e;
                } else if (c <= '\"') {
                    if (c == '\"') {
                        this.d = i3;
                        return;
                    } else if (c < ' ') {
                        this.d = i3;
                        c(c, "string value");
                    }
                }
            }
            i = i3;
        }
    }

    /* Access modifiers changed, original: protected */
    public void J() throws IOException {
        if ((this.d < this.e || p()) && this.M[this.d] == 10) {
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

    private int N() throws IOException, JsonParseException {
        while (true) {
            if (this.d < this.e || p()) {
                char[] cArr = this.M;
                int i = this.d;
                this.d = i + 1;
                char c = cArr[i];
                if (c > ' ') {
                    if (c != '/') {
                        return c;
                    }
                    P();
                } else if (c != ' ') {
                    if (c == 10) {
                        K();
                    } else if (c == 13) {
                        J();
                    } else if (c != 9) {
                        b(c);
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
                char[] cArr = this.M;
                int i = this.d;
                this.d = i + 1;
                char c = cArr[i];
                if (c > ' ') {
                    if (c != '/') {
                        return c;
                    }
                    P();
                } else if (c != ' ') {
                    if (c == 10) {
                        K();
                    } else if (c == 13) {
                        J();
                    } else if (c != 9) {
                        b(c);
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
        char[] cArr = this.M;
        int i = this.d;
        this.d = i + 1;
        char c = cArr[i];
        if (c == '/') {
            R();
        } else if (c == '*') {
            Q();
        } else {
            b((int) c, "was expecting either '*' or '/' for a comment");
        }
    }

    private void Q() throws IOException, JsonParseException {
        while (true) {
            if (this.d >= this.e && !p()) {
                break;
            }
            char[] cArr = this.M;
            int i = this.d;
            this.d = i + 1;
            char c = cArr[i];
            if (c <= '*') {
                if (c == '*') {
                    if (this.d >= this.e && !p()) {
                        break;
                    } else if (this.M[this.d] == '/') {
                        this.d++;
                        return;
                    }
                } else if (c < ' ') {
                    if (c == 10) {
                        K();
                    } else if (c == 13) {
                        J();
                    } else if (c != 9) {
                        b(c);
                    }
                }
            }
        }
        c(" in a comment");
    }

    private void R() throws IOException, JsonParseException {
        while (true) {
            if (this.d < this.e || p()) {
                char[] cArr = this.M;
                int i = this.d;
                this.d = i + 1;
                char c = cArr[i];
                if (c < ' ') {
                    if (c == 10) {
                        K();
                        return;
                    } else if (c == 13) {
                        J();
                        return;
                    } else if (c != 9) {
                        b(c);
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
        char[] cArr = this.M;
        int i = this.d;
        this.d = i + 1;
        char c = cArr[i];
        if (c == '\"' || c == '/' || c == '\\') {
            return c;
        }
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
                int i2 = 0;
                i = 0;
                while (i2 < 4) {
                    if (this.d >= this.e && !p()) {
                        c(" in character escape sequence");
                    }
                    char[] cArr2 = this.M;
                    int i3 = this.d;
                    this.d = i3 + 1;
                    char c2 = cArr2[i3];
                    i3 = com.fasterxml.jackson.core.io.b.a(c2);
                    if (i3 < 0) {
                        b((int) c2, "expected a hex-digit for character escape sequence");
                    }
                    i = (i << 4) | i3;
                    i2++;
                }
                return (char) i;
            default:
                return a(c);
        }
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Missing block: B:26:0x0063, code skipped:
            return;
     */
    public void a(java.lang.String r5, int r6) throws java.io.IOException, com.fasterxml.jackson.core.JsonParseException {
        /*
        r4 = this;
        r0 = r5.length();
    L_0x0004:
        r1 = r4.d;
        r2 = r4.e;
        if (r1 < r2) goto L_0x0013;
    L_0x000a:
        r1 = r4.p();
        if (r1 != 0) goto L_0x0013;
    L_0x0010:
        r4.D();
    L_0x0013:
        r1 = r4.M;
        r2 = r4.d;
        r1 = r1[r2];
        r2 = r5.charAt(r6);
        r3 = 0;
        if (r1 == r2) goto L_0x0029;
    L_0x0020:
        r1 = r5.substring(r3, r6);
        r2 = "'null', 'true', 'false' or NaN";
        r4.a(r1, r2);
    L_0x0029:
        r1 = r4.d;
        r1 = r1 + 1;
        r4.d = r1;
        r6 = r6 + 1;
        if (r6 < r0) goto L_0x0004;
    L_0x0033:
        r0 = r4.d;
        r1 = r4.e;
        if (r0 < r1) goto L_0x0040;
    L_0x0039:
        r0 = r4.p();
        if (r0 != 0) goto L_0x0040;
    L_0x003f:
        return;
    L_0x0040:
        r0 = r4.M;
        r1 = r4.d;
        r0 = r0[r1];
        r1 = 48;
        if (r0 < r1) goto L_0x0063;
    L_0x004a:
        r1 = 93;
        if (r0 == r1) goto L_0x0063;
    L_0x004e:
        r1 = 125; // 0x7d float:1.75E-43 double:6.2E-322;
        if (r0 != r1) goto L_0x0053;
    L_0x0052:
        goto L_0x0063;
    L_0x0053:
        r0 = java.lang.Character.isJavaIdentifierPart(r0);
        if (r0 == 0) goto L_0x0062;
    L_0x0059:
        r5 = r5.substring(r3, r6);
        r6 = "'null', 'true', 'false' or NaN";
        r4.a(r5, r6);
    L_0x0062:
        return;
    L_0x0063:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.b.e.a(java.lang.String, int):void");
    }

    /* Access modifiers changed, original: protected */
    public void a(String str, String str2) throws IOException, JsonParseException {
        StringBuilder stringBuilder = new StringBuilder(str);
        while (true) {
            if (this.d >= this.e && !p()) {
                break;
            }
            char c = this.M[this.d];
            if (!Character.isJavaIdentifierPart(c)) {
                break;
            }
            this.d++;
            stringBuilder.append(c);
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Unrecognized token '");
        stringBuilder2.append(stringBuilder.toString());
        stringBuilder2.append("': was expecting ");
        d(stringBuilder2.toString());
    }
}
