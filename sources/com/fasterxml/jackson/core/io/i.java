package com.fasterxml.jackson.core.io;

import android.support.v4.internal.view.SupportMenu;
import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

public final class i extends Writer {
    protected final c a;
    OutputStream b;
    byte[] c;
    final int d;
    int e;
    int f = 0;

    public i(c cVar, OutputStream outputStream) {
        this.a = cVar;
        this.b = outputStream;
        this.c = cVar.f();
        this.d = this.c.length - 4;
        this.e = 0;
    }

    public Writer append(char c) throws IOException {
        write((int) c);
        return this;
    }

    public void close() throws IOException {
        if (this.b != null) {
            if (this.e > 0) {
                this.b.write(this.c, 0, this.e);
                this.e = 0;
            }
            OutputStream outputStream = this.b;
            this.b = null;
            byte[] bArr = this.c;
            if (bArr != null) {
                this.c = null;
                this.a.b(bArr);
            }
            outputStream.close();
            int i = this.f;
            this.f = 0;
            if (i > 0) {
                b(i);
            }
        }
    }

    public void flush() throws IOException {
        if (this.b != null) {
            if (this.e > 0) {
                this.b.write(this.c, 0, this.e);
                this.e = 0;
            }
            this.b.flush();
        }
    }

    public void write(char[] cArr) throws IOException {
        write(cArr, 0, cArr.length);
    }

    public void write(char[] cArr, int i, int i2) throws IOException {
        if (i2 < 2) {
            if (i2 == 1) {
                write(cArr[i]);
            }
            return;
        }
        int i3;
        if (this.f > 0) {
            i3 = i + 1;
            i2--;
            write(a(cArr[i]));
            i = i3;
        }
        i3 = this.e;
        byte[] bArr = this.c;
        int i4 = this.d;
        i2 += i;
        while (i < i2) {
            int i5;
            if (i3 >= i4) {
                this.b.write(bArr, 0, i3);
                i3 = 0;
            }
            int i6 = i + 1;
            i = cArr[i];
            if (i < 128) {
                i5 = i3 + 1;
                bArr[i3] = (byte) i;
                i = i2 - i6;
                i3 = i4 - i5;
                if (i > i3) {
                    i = i3;
                }
                int i7 = i + i6;
                while (true) {
                    i = i6;
                    i3 = i5;
                    if (i >= i7) {
                        break;
                    }
                    i6 = i + 1;
                    i = cArr[i];
                    if (i >= 128) {
                        break;
                    }
                    i5 = i3 + 1;
                    bArr[i3] = (byte) i;
                }
            }
            if (i < 2048) {
                i5 = i3 + 1;
                bArr[i3] = (byte) (PsExtractor.AUDIO_STREAM | (i >> 6));
                i3 = i5 + 1;
                bArr[i5] = (byte) ((i & 63) | 128);
                i = i6;
            } else if (i < 55296 || i > 57343) {
                i5 = i3 + 1;
                bArr[i3] = (byte) (224 | (i >> 12));
                i3 = i5 + 1;
                bArr[i5] = (byte) (((i >> 6) & 63) | 128);
                i5 = i3 + 1;
                bArr[i3] = (byte) ((i & 63) | 128);
                i = i6;
                i3 = i5;
            } else {
                if (i > 56319) {
                    this.e = i3;
                    b(i);
                }
                this.f = i;
                if (i6 >= i2) {
                    break;
                }
                i = i6 + 1;
                i6 = a(cArr[i6]);
                if (i6 > 1114111) {
                    this.e = i3;
                    b(i6);
                }
                i5 = i3 + 1;
                bArr[i3] = (byte) (PsExtractor.VIDEO_STREAM_MASK | (i6 >> 18));
                i3 = i5 + 1;
                bArr[i5] = (byte) (((i6 >> 12) & 63) | 128);
                i5 = i3 + 1;
                bArr[i3] = (byte) (((i6 >> 6) & 63) | 128);
                i3 = i5 + 1;
                bArr[i5] = (byte) ((i6 & 63) | 128);
            }
        }
        this.e = i3;
    }

    public void write(int i) throws IOException {
        if (this.f > 0) {
            i = a(i);
        } else if (i >= 55296 && i <= 57343) {
            if (i > 56319) {
                b(i);
            }
            this.f = i;
            return;
        }
        if (this.e >= this.d) {
            this.b.write(this.c, 0, this.e);
            this.e = 0;
        }
        int i2;
        if (i < 128) {
            byte[] bArr = this.c;
            i2 = this.e;
            this.e = i2 + 1;
            bArr[i2] = (byte) i;
        } else {
            int i3;
            i2 = this.e;
            int i4;
            if (i < 2048) {
                i4 = i2 + 1;
                this.c[i2] = (byte) (PsExtractor.AUDIO_STREAM | (i >> 6));
                i3 = i4 + 1;
                this.c[i4] = (byte) ((i & 63) | 128);
            } else if (i <= SupportMenu.USER_MASK) {
                i4 = i2 + 1;
                this.c[i2] = (byte) (224 | (i >> 12));
                i3 = i4 + 1;
                this.c[i4] = (byte) (((i >> 6) & 63) | 128);
                i4 = i3 + 1;
                this.c[i3] = (byte) ((i & 63) | 128);
                i3 = i4;
            } else {
                if (i > 1114111) {
                    b(i);
                }
                i4 = i2 + 1;
                this.c[i2] = (byte) (PsExtractor.VIDEO_STREAM_MASK | (i >> 18));
                i3 = i4 + 1;
                this.c[i4] = (byte) (((i >> 12) & 63) | 128);
                i4 = i3 + 1;
                this.c[i3] = (byte) (((i >> 6) & 63) | 128);
                i3 = i4 + 1;
                this.c[i4] = (byte) ((i & 63) | 128);
            }
            this.e = i3;
        }
    }

    public void write(String str) throws IOException {
        write(str, 0, str.length());
    }

    public void write(String str, int i, int i2) throws IOException {
        if (i2 < 2) {
            if (i2 == 1) {
                write(str.charAt(i));
            }
            return;
        }
        int i3;
        if (this.f > 0) {
            i3 = i + 1;
            i2--;
            write(a(str.charAt(i)));
            i = i3;
        }
        i3 = this.e;
        byte[] bArr = this.c;
        int i4 = this.d;
        i2 += i;
        while (i < i2) {
            int i5;
            if (i3 >= i4) {
                this.b.write(bArr, 0, i3);
                i3 = 0;
            }
            int i6 = i + 1;
            i = str.charAt(i);
            if (i < 128) {
                i5 = i3 + 1;
                bArr[i3] = (byte) i;
                i = i2 - i6;
                i3 = i4 - i5;
                if (i > i3) {
                    i = i3;
                }
                int i7 = i + i6;
                while (true) {
                    i = i6;
                    i3 = i5;
                    if (i >= i7) {
                        break;
                    }
                    i6 = i + 1;
                    i = str.charAt(i);
                    if (i >= 128) {
                        break;
                    }
                    i5 = i3 + 1;
                    bArr[i3] = (byte) i;
                }
            }
            if (i < 2048) {
                i5 = i3 + 1;
                bArr[i3] = (byte) (PsExtractor.AUDIO_STREAM | (i >> 6));
                i3 = i5 + 1;
                bArr[i5] = (byte) ((i & 63) | 128);
                i = i6;
            } else if (i < 55296 || i > 57343) {
                i5 = i3 + 1;
                bArr[i3] = (byte) (224 | (i >> 12));
                i3 = i5 + 1;
                bArr[i5] = (byte) (((i >> 6) & 63) | 128);
                i5 = i3 + 1;
                bArr[i3] = (byte) ((i & 63) | 128);
                i = i6;
                i3 = i5;
            } else {
                if (i > 56319) {
                    this.e = i3;
                    b(i);
                }
                this.f = i;
                if (i6 >= i2) {
                    break;
                }
                i = i6 + 1;
                i6 = a(str.charAt(i6));
                if (i6 > 1114111) {
                    this.e = i3;
                    b(i6);
                }
                i5 = i3 + 1;
                bArr[i3] = (byte) (PsExtractor.VIDEO_STREAM_MASK | (i6 >> 18));
                i3 = i5 + 1;
                bArr[i5] = (byte) (((i6 >> 12) & 63) | 128);
                i5 = i3 + 1;
                bArr[i3] = (byte) (((i6 >> 6) & 63) | 128);
                i3 = i5 + 1;
                bArr[i5] = (byte) ((i6 & 63) | 128);
            }
        }
        this.e = i3;
    }

    private int a(int i) throws IOException {
        int i2 = this.f;
        this.f = 0;
        if (i >= 56320 && i <= 57343) {
            return (65536 + ((i2 - 55296) << 10)) + (i - 56320);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Broken surrogate pair: first char 0x");
        stringBuilder.append(Integer.toHexString(i2));
        stringBuilder.append(", second 0x");
        stringBuilder.append(Integer.toHexString(i));
        stringBuilder.append("; illegal combination");
        throw new IOException(stringBuilder.toString());
    }

    private void b(int i) throws IOException {
        StringBuilder stringBuilder;
        if (i > 1114111) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Illegal character point (0x");
            stringBuilder.append(Integer.toHexString(i));
            stringBuilder.append(") to output; max is 0x10FFFF as per RFC 4627");
            throw new IOException(stringBuilder.toString());
        } else if (i < 55296) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Illegal character point (0x");
            stringBuilder.append(Integer.toHexString(i));
            stringBuilder.append(") to output");
            throw new IOException(stringBuilder.toString());
        } else if (i <= 56319) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Unmatched first part of surrogate pair (0x");
            stringBuilder.append(Integer.toHexString(i));
            stringBuilder.append(")");
            throw new IOException(stringBuilder.toString());
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Unmatched second part of surrogate pair (0x");
            stringBuilder.append(Integer.toHexString(i));
            stringBuilder.append(")");
            throw new IOException(stringBuilder.toString());
        }
    }
}
