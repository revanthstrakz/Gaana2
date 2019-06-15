package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.util.BufferRecycler.ByteBufferType;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.LinkedList;

public final class a extends OutputStream {
    private static final byte[] a = new byte[0];
    private final BufferRecycler b;
    private final LinkedList<byte[]> c;
    private int d;
    private byte[] e;
    private int f;

    public void close() {
    }

    public void flush() {
    }

    public a() {
        this(null);
    }

    public a(BufferRecycler bufferRecycler) {
        this(bufferRecycler, 500);
    }

    public a(BufferRecycler bufferRecycler, int i) {
        this.c = new LinkedList();
        this.b = bufferRecycler;
        if (bufferRecycler == null) {
            this.e = new byte[i];
        } else {
            this.e = bufferRecycler.a(ByteBufferType.WRITE_CONCAT_BUFFER);
        }
    }

    public void a() {
        this.d = 0;
        this.f = 0;
        if (!this.c.isEmpty()) {
            this.c.clear();
        }
    }

    public void a(int i) {
        if (this.f >= this.e.length) {
            e();
        }
        byte[] bArr = this.e;
        int i2 = this.f;
        this.f = i2 + 1;
        bArr[i2] = (byte) i;
    }

    public byte[] b() {
        int i = this.d + this.f;
        if (i == 0) {
            return a;
        }
        byte[] bArr = new byte[i];
        Iterator it = this.c.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            byte[] bArr2 = (byte[]) it.next();
            int length = bArr2.length;
            System.arraycopy(bArr2, 0, bArr, i2, length);
            i2 += length;
        }
        System.arraycopy(this.e, 0, bArr, i2, this.f);
        i2 += this.f;
        if (i2 != i) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Internal error: total len assumed to be ");
            stringBuilder.append(i);
            stringBuilder.append(", copied ");
            stringBuilder.append(i2);
            stringBuilder.append(" bytes");
            throw new RuntimeException(stringBuilder.toString());
        }
        if (!this.c.isEmpty()) {
            a();
        }
        return bArr;
    }

    public byte[] c() {
        a();
        return this.e;
    }

    public byte[] d() {
        e();
        return this.e;
    }

    public byte[] b(int i) {
        this.f = i;
        return b();
    }

    public void write(byte[] bArr) {
        write(bArr, 0, bArr.length);
    }

    public void write(byte[] bArr, int i, int i2) {
        while (true) {
            int min = Math.min(this.e.length - this.f, i2);
            if (min > 0) {
                System.arraycopy(bArr, i, this.e, this.f, min);
                i += min;
                this.f += min;
                i2 -= min;
            }
            if (i2 > 0) {
                e();
            } else {
                return;
            }
        }
    }

    public void write(int i) {
        a(i);
    }

    private void e() {
        this.d += this.e.length;
        int max = Math.max(this.d >> 1, 1000);
        if (max > 262144) {
            max = 262144;
        }
        this.c.add(this.e);
        this.e = new byte[max];
        this.f = 0;
    }
}
