package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

class kg implements ke {
    private lr a;
    private byte[] b;
    private final int c;

    public kg(int i) {
        this.c = i;
        a();
    }

    public void a() {
        this.b = new byte[this.c];
        this.a = lr.a(this.b);
    }

    public void a(int i, long j) throws IOException {
        this.a.a(i, j);
    }

    public void a(int i, String str) throws IOException {
        this.a.a(i, str);
    }

    public byte[] b() throws IOException {
        int a = this.a.a();
        if (a < 0) {
            throw new IOException();
        } else if (a == 0) {
            return this.b;
        } else {
            byte[] bArr = new byte[(this.b.length - a)];
            System.arraycopy(this.b, 0, bArr, 0, bArr.length);
            return bArr;
        }
    }
}
