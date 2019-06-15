package com.fasterxml.jackson.core.io;

import java.io.IOException;
import java.io.InputStream;

public final class e extends InputStream {
    protected final c a;
    final InputStream b;
    byte[] c;
    int d;
    final int e;

    public e(c cVar, InputStream inputStream, byte[] bArr, int i, int i2) {
        this.a = cVar;
        this.b = inputStream;
        this.c = bArr;
        this.d = i;
        this.e = i2;
    }

    public int available() throws IOException {
        if (this.c != null) {
            return this.e - this.d;
        }
        return this.b.available();
    }

    public void close() throws IOException {
        a();
        this.b.close();
    }

    public void mark(int i) {
        if (this.c == null) {
            this.b.mark(i);
        }
    }

    public boolean markSupported() {
        return this.c == null && this.b.markSupported();
    }

    public int read() throws IOException {
        if (this.c == null) {
            return this.b.read();
        }
        byte[] bArr = this.c;
        int i = this.d;
        this.d = i + 1;
        int i2 = bArr[i] & 255;
        if (this.d >= this.e) {
            a();
        }
        return i2;
    }

    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.c == null) {
            return this.b.read(bArr, i, i2);
        }
        int i3 = this.e - this.d;
        if (i2 > i3) {
            i2 = i3;
        }
        System.arraycopy(this.c, this.d, bArr, i, i2);
        this.d += i2;
        if (this.d >= this.e) {
            a();
        }
        return i2;
    }

    public void reset() throws IOException {
        if (this.c == null) {
            this.b.reset();
        }
    }

    public long skip(long j) throws IOException {
        long j2;
        if (this.c != null) {
            long j3 = (long) (this.e - this.d);
            if (j3 > j) {
                this.d += (int) j;
                return j;
            }
            a();
            j2 = 0 + j3;
            j -= j3;
        } else {
            j2 = 0;
        }
        return j > 0 ? j2 + this.b.skip(j) : j2;
    }

    private void a() {
        byte[] bArr = this.c;
        if (bArr != null) {
            this.c = null;
            if (this.a != null) {
                this.a.a(bArr);
            }
        }
    }
}
