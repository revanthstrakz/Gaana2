package com.til.colombia.android.commons.a;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

final class e implements Closeable {
    private static final byte b = (byte) 13;
    private static final byte c = (byte) 10;
    final Charset a;
    private final InputStream d;
    private byte[] e;
    private int f;
    private int g;

    public e(InputStream inputStream, Charset charset) {
        this(inputStream, 8192, charset);
    }

    private e(InputStream inputStream, int i, Charset charset) {
        if (inputStream == null || charset == null) {
            throw new NullPointerException();
        } else if (charset.equals(g.a)) {
            this.d = inputStream;
            this.a = charset;
            this.e = new byte[8192];
        } else {
            throw new IllegalArgumentException("Unsupported encoding");
        }
    }

    public final void close() throws IOException {
        synchronized (this.d) {
            if (this.e != null) {
                this.e = null;
                this.d.close();
            }
        }
    }

    public final String a() throws IOException {
        synchronized (this.d) {
            if (this.e == null) {
                throw new IOException("LineReader is closed");
            }
            int i;
            if (this.f >= this.g) {
                b();
            }
            for (int i2 = this.f; i2 != this.g; i2++) {
                if (this.e[i2] == c) {
                    int i3;
                    String str;
                    if (i2 != this.f) {
                        i3 = i2 - 1;
                        if (this.e[i3] == b) {
                            str = new String(this.e, this.f, i3 - this.f, this.a.name());
                            this.f = i2 + 1;
                            return str;
                        }
                    }
                    i3 = i2;
                    str = new String(this.e, this.f, i3 - this.f, this.a.name());
                    this.f = i2 + 1;
                    return str;
                }
            }
            f fVar = new f(this, (this.g - this.f) + 80);
            loop1:
            while (true) {
                fVar.write(this.e, this.f, this.g - this.f);
                this.g = -1;
                b();
                i = this.f;
                while (i != this.g) {
                    if (this.e[i] == c) {
                        break loop1;
                    }
                    i++;
                }
            }
            if (i != this.f) {
                fVar.write(this.e, this.f, i - this.f);
            }
            this.f = i + 1;
            String byteArrayOutputStream = fVar.toString();
            return byteArrayOutputStream;
        }
    }

    private void b() throws IOException {
        int read = this.d.read(this.e, 0, this.e.length);
        if (read == -1) {
            throw new EOFException();
        }
        this.f = 0;
        this.g = read;
    }
}
