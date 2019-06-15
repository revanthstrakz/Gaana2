package com.fasterxml.jackson.core.io;

import android.support.v4.internal.view.SupportMenu;
import java.io.CharConversionException;
import java.io.IOException;
import java.io.InputStream;

public class h extends a {
    protected final boolean g;
    protected char h = 0;
    protected int i = 0;
    protected int j = 0;
    protected final boolean k;

    public /* bridge */ /* synthetic */ void close() throws IOException {
        super.close();
    }

    public /* bridge */ /* synthetic */ int read() throws IOException {
        return super.read();
    }

    public h(c cVar, InputStream inputStream, byte[] bArr, int i, int i2, boolean z) {
        super(cVar, inputStream, bArr, i, i2);
        boolean z2 = false;
        this.g = z;
        if (inputStream != null) {
            z2 = true;
        }
        this.k = z2;
    }

    public int read(char[] cArr, int i, int i2) throws IOException {
        if (this.c == null) {
            return -1;
        }
        if (i2 < 1) {
            return i2;
        }
        int i3;
        int i4;
        if (i < 0 || i + i2 > cArr.length) {
            a(cArr, i, i2);
        }
        i2 += i;
        if (this.h != 0) {
            i3 = i + 1;
            cArr[i] = this.h;
            this.h = 0;
        } else {
            i3 = this.e - this.d;
            if (i3 < 4 && !a(i3)) {
                return -1;
            }
            i3 = i;
        }
        while (i3 < i2) {
            int i5 = this.d;
            if (this.g) {
                i5 = (this.c[i5 + 3] & 255) | (((this.c[i5] << 24) | ((this.c[i5 + 1] & 255) << 16)) | ((this.c[i5 + 2] & 255) << 8));
            } else {
                i5 = (this.c[i5 + 3] << 24) | (((this.c[i5] & 255) | ((this.c[i5 + 1] & 255) << 8)) | ((this.c[i5 + 2] & 255) << 16));
            }
            this.d += 4;
            if (i5 > SupportMenu.USER_MASK) {
                if (i5 > 1114111) {
                    int i6 = i3 - i;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("(above ");
                    stringBuilder.append(Integer.toHexString(1114111));
                    stringBuilder.append(") ");
                    a(i5, i6, stringBuilder.toString());
                }
                i5 -= 65536;
                i4 = i3 + 1;
                cArr[i3] = (char) (55296 + (i5 >> 10));
                i5 = (i5 & 1023) | 56320;
                if (i4 >= i2) {
                    this.h = (char) i5;
                    break;
                }
                i3 = i4;
            }
            i4 = i3 + 1;
            cArr[i3] = (char) i5;
            if (this.d >= this.e) {
                break;
            }
            i3 = i4;
        }
        i4 = i3;
        i4 -= i;
        this.i += i4;
        return i4;
    }

    private void a(int i, int i2) throws IOException {
        int i3 = this.j + i;
        int i4 = this.i;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unexpected EOF in the middle of a 4-byte UTF-32 char: got ");
        stringBuilder.append(i);
        stringBuilder.append(", needed ");
        stringBuilder.append(i2);
        stringBuilder.append(", at char #");
        stringBuilder.append(i4);
        stringBuilder.append(", byte #");
        stringBuilder.append(i3);
        stringBuilder.append(")");
        throw new CharConversionException(stringBuilder.toString());
    }

    private void a(int i, int i2, String str) throws IOException {
        int i3 = (this.j + this.d) - 1;
        int i4 = this.i + i2;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Invalid UTF-32 character 0x");
        stringBuilder.append(Integer.toHexString(i));
        stringBuilder.append(str);
        stringBuilder.append(" at char #");
        stringBuilder.append(i4);
        stringBuilder.append(", byte #");
        stringBuilder.append(i3);
        stringBuilder.append(")");
        throw new CharConversionException(stringBuilder.toString());
    }

    private boolean a(int i) throws IOException {
        this.j += this.e - i;
        if (i > 0) {
            if (this.d > 0) {
                for (int i2 = 0; i2 < i; i2++) {
                    this.c[i2] = this.c[this.d + i2];
                }
                this.d = 0;
            }
            this.e = i;
        } else {
            this.d = 0;
            i = this.b == null ? -1 : this.b.read(this.c);
            if (i < 1) {
                this.e = 0;
                if (i < 0) {
                    if (this.k) {
                        a();
                    }
                    return false;
                }
                b();
            }
            this.e = i;
        }
        while (this.e < 4) {
            i = this.b == null ? -1 : this.b.read(this.c, this.e, this.c.length - this.e);
            if (i < 1) {
                if (i < 0) {
                    if (this.k) {
                        a();
                    }
                    a(this.e, 4);
                }
                b();
            }
            this.e += i;
        }
        return true;
    }
}
