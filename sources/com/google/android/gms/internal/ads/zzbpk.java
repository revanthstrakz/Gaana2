package com.google.android.gms.internal.ads;

public final class zzbpk {
    private final byte[] zzfku = new byte[256];
    private int zzfkv;
    private int zzfkw;

    public zzbpk(byte[] bArr) {
        int i;
        for (i = 0; i < 256; i++) {
            this.zzfku[i] = (byte) i;
        }
        i = 0;
        int i2 = i;
        while (i < 256) {
            i2 = ((i2 + this.zzfku[i]) + bArr[i % bArr.length]) & 255;
            byte b = this.zzfku[i];
            this.zzfku[i] = this.zzfku[i2];
            this.zzfku[i2] = b;
            i++;
        }
        this.zzfkv = 0;
        this.zzfkw = 0;
    }

    public final void zzq(byte[] bArr) {
        int i = this.zzfkv;
        int i2 = this.zzfkw;
        for (int i3 = 0; i3 < bArr.length; i3++) {
            i = (i + 1) & 255;
            i2 = (i2 + this.zzfku[i]) & 255;
            byte b = this.zzfku[i];
            this.zzfku[i] = this.zzfku[i2];
            this.zzfku[i2] = b;
            bArr[i3] = (byte) (bArr[i3] ^ this.zzfku[(this.zzfku[i] + this.zzfku[i2]) & 255]);
        }
        this.zzfkv = i;
        this.zzfkw = i2;
    }
}
