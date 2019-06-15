package com.helpshift.websockets;

import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import javax.security.auth.x500.X500Principal;

final class h {
    private final String a;
    private final int b = this.a.length();
    private int c;
    private int d;
    private int e;
    private int f;
    private char[] g;

    public h(X500Principal x500Principal) {
        this.a = x500Principal.getName("RFC2253");
    }

    private String a() {
        while (this.c < this.b && this.g[this.c] == ' ') {
            this.c++;
        }
        if (this.c == this.b) {
            return null;
        }
        this.d = this.c;
        this.c++;
        while (this.c < this.b && this.g[this.c] != '=' && this.g[this.c] != ' ') {
            this.c++;
        }
        StringBuilder stringBuilder;
        if (this.c >= this.b) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Unexpected end of DN: ");
            stringBuilder.append(this.a);
            throw new IllegalStateException(stringBuilder.toString());
        }
        this.e = this.c;
        if (this.g[this.c] == ' ') {
            while (this.c < this.b && this.g[this.c] != '=' && this.g[this.c] == ' ') {
                this.c++;
            }
            if (this.g[this.c] != '=' || this.c == this.b) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("Unexpected end of DN: ");
                stringBuilder.append(this.a);
                throw new IllegalStateException(stringBuilder.toString());
            }
        }
        this.c++;
        while (this.c < this.b && this.g[this.c] == ' ') {
            this.c++;
        }
        if (this.e - this.d > 4 && this.g[this.d + 3] == '.' && ((this.g[this.d] == 'O' || this.g[this.d] == 'o') && ((this.g[this.d + 1] == 'I' || this.g[this.d + 1] == 'i') && (this.g[this.d + 2] == 'D' || this.g[this.d + 2] == 'd')))) {
            this.d += 4;
        }
        return new String(this.g, this.d, this.e - this.d);
    }

    private String b() {
        this.c++;
        this.d = this.c;
        this.e = this.d;
        while (this.c != this.b) {
            if (this.g[this.c] == '\"') {
                this.c++;
                while (this.c < this.b && this.g[this.c] == ' ') {
                    this.c++;
                }
                return new String(this.g, this.d, this.e - this.d);
            }
            if (this.g[this.c] == '\\') {
                this.g[this.e] = e();
            } else {
                this.g[this.e] = this.g[this.c];
            }
            this.c++;
            this.e++;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unexpected end of DN: ");
        stringBuilder.append(this.a);
        throw new IllegalStateException(stringBuilder.toString());
    }

    private String c() {
        StringBuilder stringBuilder;
        if (this.c + 4 >= this.b) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Unexpected end of DN: ");
            stringBuilder.append(this.a);
            throw new IllegalStateException(stringBuilder.toString());
        }
        int i;
        this.d = this.c;
        this.c++;
        while (this.c != this.b && this.g[this.c] != '+' && this.g[this.c] != ',' && this.g[this.c] != ';') {
            int i2;
            if (this.g[this.c] == ' ') {
                this.e = this.c;
                this.c++;
                while (this.c < this.b && this.g[this.c] == ' ') {
                    this.c++;
                }
                i = this.e - this.d;
                if (i >= 5 || (i & 1) == 0) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Unexpected end of DN: ");
                    stringBuilder.append(this.a);
                    throw new IllegalStateException(stringBuilder.toString());
                }
                byte[] bArr = new byte[(i / 2)];
                int i3 = this.d + 1;
                for (i2 = 0; i2 < bArr.length; i2++) {
                    bArr[i2] = (byte) a(i3);
                    i3 += 2;
                }
                return new String(this.g, this.d, i);
            }
            if (this.g[this.c] >= 'A' && this.g[this.c] <= 'F') {
                char[] cArr = this.g;
                i2 = this.c;
                cArr[i2] = (char) (cArr[i2] + 32);
            }
            this.c++;
        }
        this.e = this.c;
        i = this.e - this.d;
        if (i >= 5) {
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append("Unexpected end of DN: ");
        stringBuilder.append(this.a);
        throw new IllegalStateException(stringBuilder.toString());
    }

    private String d() {
        this.d = this.c;
        this.e = this.c;
        while (this.c < this.b) {
            char c = this.g[this.c];
            char[] cArr;
            if (c != ' ') {
                if (c != ';') {
                    int i;
                    if (c != '\\') {
                        switch (c) {
                            case '+':
                            case ',':
                                break;
                            default:
                                cArr = this.g;
                                i = this.e;
                                this.e = i + 1;
                                cArr[i] = this.g[this.c];
                                this.c++;
                                continue;
                        }
                    } else {
                        cArr = this.g;
                        i = this.e;
                        this.e = i + 1;
                        cArr[i] = e();
                        this.c++;
                    }
                }
                return new String(this.g, this.d, this.e - this.d);
            }
            this.f = this.e;
            this.c++;
            cArr = this.g;
            int i2 = this.e;
            this.e = i2 + 1;
            cArr[i2] = ' ';
            while (this.c < this.b && this.g[this.c] == ' ') {
                cArr = this.g;
                i2 = this.e;
                this.e = i2 + 1;
                cArr[i2] = ' ';
                this.c++;
            }
            if (this.c == this.b || this.g[this.c] == ',' || this.g[this.c] == '+' || this.g[this.c] == ';') {
                return new String(this.g, this.d, this.f - this.d);
            }
        }
        return new String(this.g, this.d, this.e - this.d);
    }

    private char e() {
        this.c++;
        if (this.c == this.b) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Unexpected end of DN: ");
            stringBuilder.append(this.a);
            throw new IllegalStateException(stringBuilder.toString());
        }
        char c = this.g[this.c];
        if (!(c == ' ' || c == '%' || c == '\\' || c == '_')) {
            switch (c) {
                case '\"':
                case '#':
                    break;
                default:
                    switch (c) {
                        case '*':
                        case '+':
                        case ',':
                            break;
                        default:
                            switch (c) {
                                case ';':
                                case '<':
                                case '=':
                                case '>':
                                    break;
                                default:
                                    return f();
                            }
                    }
            }
        }
        return this.g[this.c];
    }

    private char f() {
        int a = a(this.c);
        this.c++;
        if (a < 128) {
            return (char) a;
        }
        if (a < PsExtractor.AUDIO_STREAM || a > 247) {
            return '?';
        }
        int i;
        if (a <= 223) {
            a &= 31;
            i = 1;
        } else if (a <= 239) {
            i = 2;
            a &= 15;
        } else {
            i = 3;
            a &= 7;
        }
        for (int i2 = 0; i2 < i; i2++) {
            this.c++;
            if (this.c == this.b || this.g[this.c] != '\\') {
                return '?';
            }
            this.c++;
            int a2 = a(this.c);
            this.c++;
            if ((a2 & PsExtractor.AUDIO_STREAM) != 128) {
                return '?';
            }
            a = (a << 6) + (a2 & 63);
        }
        return (char) a;
    }

    private int a(int i) {
        int i2 = i + 1;
        StringBuilder stringBuilder;
        if (i2 >= this.b) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Malformed DN: ");
            stringBuilder.append(this.a);
            throw new IllegalStateException(stringBuilder.toString());
        }
        char c = this.g[i];
        if (c >= '0' && c <= '9') {
            i = c - 48;
        } else if (c >= 'a' && c <= 'f') {
            i = c - 87;
        } else if (c < 'A' || c > 'F') {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Malformed DN: ");
            stringBuilder.append(this.a);
            throw new IllegalStateException(stringBuilder.toString());
        } else {
            i = c - 55;
        }
        char c2 = this.g[i2];
        if (c2 >= '0' && c2 <= '9') {
            i2 = c2 - 48;
        } else if (c2 >= 'a' && c2 <= 'f') {
            i2 = c2 - 87;
        } else if (c2 < 'A' || c2 > 'F') {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Malformed DN: ");
            stringBuilder.append(this.a);
            throw new IllegalStateException(stringBuilder.toString());
        } else {
            i2 = c2 - 55;
        }
        return (i << 4) + i2;
    }

    public String a(String str) {
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = this.a.toCharArray();
        String a = a();
        if (a == null) {
            return null;
        }
        StringBuilder stringBuilder;
        do {
            String str2 = "";
            if (this.c == this.b) {
                return null;
            }
            switch (this.g[this.c]) {
                case '\"':
                    str2 = b();
                    break;
                case '#':
                    str2 = c();
                    break;
                case '+':
                case ',':
                case ';':
                    break;
                default:
                    str2 = d();
                    break;
            }
            if (str.equalsIgnoreCase(a)) {
                return str2;
            }
            if (this.c >= this.b) {
                return null;
            }
            if (this.g[this.c] == ',' || this.g[this.c] == ';' || this.g[this.c] == '+') {
                this.c++;
                a = a();
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append("Malformed DN: ");
                stringBuilder.append(this.a);
                throw new IllegalStateException(stringBuilder.toString());
            }
        } while (a != null);
        stringBuilder = new StringBuilder();
        stringBuilder.append("Malformed DN: ");
        stringBuilder.append(this.a);
        throw new IllegalStateException(stringBuilder.toString());
    }
}
