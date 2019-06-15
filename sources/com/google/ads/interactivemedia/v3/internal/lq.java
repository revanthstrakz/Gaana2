package com.google.ads.interactivemedia.v3.internal;

public class lq {
    private final byte[] a = new byte[256];
    private int b;
    private int c;

    public lq(byte[] bArr) {
        int i;
        for (i = 0; i < 256; i++) {
            this.a[i] = (byte) i;
        }
        i = 0;
        int i2 = i;
        while (i < 256) {
            i2 = ((i2 + this.a[i]) + bArr[i % bArr.length]) & 255;
            byte b = this.a[i];
            this.a[i] = this.a[i2];
            this.a[i2] = b;
            i++;
        }
        this.b = 0;
        this.c = 0;
    }

    public void a(byte[] bArr) {
        int i = this.b;
        int i2 = this.c;
        for (int i3 = 0; i3 < bArr.length; i3++) {
            i = (i + 1) & 255;
            i2 = (i2 + this.a[i]) & 255;
            byte b = this.a[i];
            this.a[i] = this.a[i2];
            this.a[i2] = b;
            bArr[i3] = (byte) (bArr[i3] ^ this.a[(this.a[i] + this.a[i2]) & 255]);
        }
        this.b = i;
        this.c = i2;
    }
}
