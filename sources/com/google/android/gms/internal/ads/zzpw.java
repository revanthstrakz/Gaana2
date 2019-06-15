package com.google.android.gms.internal.ads;

public final class zzpw {
    private byte[] data;
    private int zzbhx;
    private int zzbhy;
    private int zzbhz;

    public zzpw(byte[] bArr) {
        this(bArr, bArr.length);
    }

    private zzpw(byte[] bArr, int i) {
        this.data = bArr;
        this.zzbhz = i;
    }

    public final int zzbj(int i) {
        boolean z = false;
        if (i == 0) {
            return 0;
        }
        int i2 = i / 8;
        int i3 = i;
        i = 0;
        int i4 = i;
        while (i < i2) {
            int i5;
            if (this.zzbhy != 0) {
                i5 = ((this.data[this.zzbhx + 1] & 255) >>> (8 - this.zzbhy)) | ((this.data[this.zzbhx] & 255) << this.zzbhy);
            } else {
                i5 = this.data[this.zzbhx];
            }
            i3 -= 8;
            i4 |= (255 & i5) << i3;
            this.zzbhx++;
            i++;
        }
        if (i3 > 0) {
            i = this.zzbhy + i3;
            byte b = (byte) (255 >> (8 - i3));
            if (i > 8) {
                i2 = (b & (((this.data[this.zzbhx] & 255) << (i - 8)) | ((255 & this.data[this.zzbhx + 1]) >> (16 - i)))) | i4;
                this.zzbhx++;
            } else {
                i2 = (b & ((this.data[this.zzbhx] & 255) >> (8 - i))) | i4;
                if (i == 8) {
                    this.zzbhx++;
                }
            }
            i4 = i2;
            this.zzbhy = i % 8;
        }
        if (this.zzbhx >= 0 && this.zzbhy >= 0 && this.zzbhy < 8 && (this.zzbhx < this.zzbhz || (this.zzbhx == this.zzbhz && this.zzbhy == 0))) {
            z = true;
        }
        zzpo.checkState(z);
        return i4;
    }
}
