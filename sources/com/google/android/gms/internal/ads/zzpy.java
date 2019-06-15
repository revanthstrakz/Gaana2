package com.google.android.gms.internal.ads;

public final class zzpy {
    private byte[] data;
    private int zzbhx;
    private int zzbhy = 0;
    private int zzbhz;

    public zzpy(byte[] bArr, int i, int i2) {
        this.data = bArr;
        this.zzbhx = i;
        this.zzbhz = i2;
        zzhn();
    }

    public final void zzbn(int i) {
        int i2 = this.zzbhx;
        this.zzbhx += i / 8;
        this.zzbhy += i % 8;
        if (this.zzbhy > 7) {
            this.zzbhx++;
            this.zzbhy -= 8;
        }
        while (true) {
            i2++;
            if (i2 > this.zzbhx) {
                zzhn();
                return;
            } else if (zzbo(i2)) {
                this.zzbhx++;
                i2 += 2;
            }
        }
    }

    public final boolean zzhj() {
        return zzbj(1) == 1;
    }

    public final int zzbj(int i) {
        int i2 = 0;
        if (i == 0) {
            return 0;
        }
        int i3 = i / 8;
        int i4 = 0;
        while (i2 < i3) {
            int i5;
            int i6 = zzbo(this.zzbhx + 1) ? this.zzbhx + 2 : this.zzbhx + 1;
            if (this.zzbhy != 0) {
                i5 = ((this.data[i6] & 255) >>> (8 - this.zzbhy)) | ((this.data[this.zzbhx] & 255) << this.zzbhy);
            } else {
                i5 = this.data[this.zzbhx];
            }
            i -= 8;
            i4 |= (255 & i5) << i;
            this.zzbhx = i6;
            i2++;
        }
        if (i > 0) {
            i2 = this.zzbhy + i;
            byte b = (byte) (255 >> (8 - i));
            i3 = zzbo(this.zzbhx + 1) ? this.zzbhx + 2 : this.zzbhx + 1;
            if (i2 > 8) {
                i = (b & (((255 & this.data[i3]) >> (16 - i2)) | ((this.data[this.zzbhx] & 255) << (i2 - 8)))) | i4;
                this.zzbhx = i3;
            } else {
                i = (b & ((255 & this.data[this.zzbhx]) >> (8 - i2))) | i4;
                if (i2 == 8) {
                    this.zzbhx = i3;
                }
            }
            i4 = i;
            this.zzbhy = i2 % 8;
        }
        zzhn();
        return i4;
    }

    public final int zzhk() {
        return zzhm();
    }

    public final int zzhl() {
        int zzhm = zzhm();
        return (zzhm % 2 == 0 ? -1 : 1) * ((zzhm + 1) / 2);
    }

    private final int zzhm() {
        int i = 0;
        int i2 = 0;
        while (!zzhj()) {
            i2++;
        }
        int i3 = (1 << i2) - 1;
        if (i2 > 0) {
            i = zzbj(i2);
        }
        return i3 + i;
    }

    private final boolean zzbo(int i) {
        return 2 <= i && i < this.zzbhz && this.data[i] == (byte) 3 && this.data[i - 2] == (byte) 0 && this.data[i - 1] == (byte) 0;
    }

    private final void zzhn() {
        boolean z = this.zzbhx >= 0 && this.zzbhy >= 0 && this.zzbhy < 8 && (this.zzbhx < this.zzbhz || (this.zzbhx == this.zzbhz && this.zzbhy == 0));
        zzpo.checkState(z);
    }
}
