package com.fasterxml.jackson.core.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

abstract class a extends Reader {
    protected final c a;
    protected InputStream b;
    protected byte[] c;
    protected int d;
    protected int e;
    protected char[] f = null;

    protected a(c cVar, InputStream inputStream, byte[] bArr, int i, int i2) {
        this.a = cVar;
        this.b = inputStream;
        this.c = bArr;
        this.d = i;
        this.e = i2;
    }

    public void close() throws IOException {
        InputStream inputStream = this.b;
        if (inputStream != null) {
            this.b = null;
            a();
            inputStream.close();
        }
    }

    public int read() throws IOException {
        if (this.f == null) {
            this.f = new char[1];
        }
        if (read(this.f, 0, 1) < 1) {
            return -1;
        }
        return this.f[0];
    }

    public final void a() {
        byte[] bArr = this.c;
        if (bArr != null) {
            this.c = null;
            this.a.a(bArr);
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(char[] cArr, int i, int i2) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("read(buf,");
        stringBuilder.append(i);
        stringBuilder.append(",");
        stringBuilder.append(i2);
        stringBuilder.append("), cbuf[");
        stringBuilder.append(cArr.length);
        stringBuilder.append("]");
        throw new ArrayIndexOutOfBoundsException(stringBuilder.toString());
    }

    /* Access modifiers changed, original: protected */
    public void b() throws IOException {
        throw new IOException("Strange I/O stream, returned 0 bytes on read");
    }
}
