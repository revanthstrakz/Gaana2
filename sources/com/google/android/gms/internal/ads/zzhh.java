package com.google.android.gms.internal.ads;

import com.gaana.login.sso.SsoErrorCodes;
import java.nio.ShortBuffer;
import java.util.Arrays;

final class zzhh {
    private float zzaag;
    private float zzaah;
    private final int zzafc;
    private final int zzafd;
    private final int zzafe;
    private final int zzaff = (2 * this.zzafe);
    private final short[] zzafg = new short[this.zzaff];
    private int zzafh = this.zzaff;
    private short[] zzafi;
    private int zzafj;
    private short[] zzafk;
    private int zzafl;
    private short[] zzafm;
    private int zzafn;
    private int zzafo;
    private int zzafp;
    private int zzafq;
    private int zzafr;
    private int zzafs;
    private int zzaft;
    private int zzafu;
    private int zzafv;
    private int zzafw;
    private final int zzzu;

    public zzhh(int i, int i2) {
        this.zzzu = i;
        this.zzafc = i2;
        this.zzafd = i / 400;
        this.zzafe = i / 65;
        this.zzafi = new short[(this.zzaff * i2)];
        this.zzafj = this.zzaff;
        this.zzafk = new short[(this.zzaff * i2)];
        this.zzafl = this.zzaff;
        this.zzafm = new short[(this.zzaff * i2)];
        this.zzafn = 0;
        this.zzafo = 0;
        this.zzaft = 0;
        this.zzaag = 1.0f;
        this.zzaah = 1.0f;
    }

    public final void setSpeed(float f) {
        this.zzaag = f;
    }

    public final void zza(float f) {
        this.zzaah = f;
    }

    public final void zza(ShortBuffer shortBuffer) {
        int remaining = shortBuffer.remaining() / this.zzafc;
        int i = (this.zzafc * remaining) << 1;
        zzp(remaining);
        shortBuffer.get(this.zzafi, this.zzafp * this.zzafc, i / 2);
        this.zzafp += remaining;
        zzdl();
    }

    public final void zzb(ShortBuffer shortBuffer) {
        int min = Math.min(shortBuffer.remaining() / this.zzafc, this.zzafq);
        shortBuffer.put(this.zzafk, 0, this.zzafc * min);
        this.zzafq -= min;
        System.arraycopy(this.zzafk, min * this.zzafc, this.zzafk, 0, this.zzafq * this.zzafc);
    }

    public final void zzcq() {
        int i = this.zzafp;
        int i2 = this.zzafq + ((int) ((((((float) i) / (this.zzaag / this.zzaah)) + ((float) this.zzafr)) / this.zzaah) + 0.5f));
        zzp((this.zzaff * 2) + i);
        for (int i3 = 0; i3 < (this.zzaff * 2) * this.zzafc; i3++) {
            this.zzafi[(this.zzafc * i) + i3] = (short) 0;
        }
        this.zzafp += 2 * this.zzaff;
        zzdl();
        if (this.zzafq > i2) {
            this.zzafq = i2;
        }
        this.zzafp = 0;
        this.zzafs = 0;
        this.zzafr = 0;
    }

    public final int zzdk() {
        return this.zzafq;
    }

    private final void zzo(int i) {
        if (this.zzafq + i > this.zzafj) {
            this.zzafj += (this.zzafj / 2) + i;
            this.zzafk = Arrays.copyOf(this.zzafk, this.zzafj * this.zzafc);
        }
    }

    private final void zzp(int i) {
        if (this.zzafp + i > this.zzafh) {
            this.zzafh += (this.zzafh / 2) + i;
            this.zzafi = Arrays.copyOf(this.zzafi, this.zzafh * this.zzafc);
        }
    }

    private final void zza(short[] sArr, int i, int i2) {
        zzo(i2);
        System.arraycopy(sArr, i * this.zzafc, this.zzafk, this.zzafq * this.zzafc, this.zzafc * i2);
        this.zzafq += i2;
    }

    private final void zzb(short[] sArr, int i, int i2) {
        int i3 = this.zzaff / i2;
        int i4 = this.zzafc * i2;
        i *= this.zzafc;
        for (int i5 = 0; i5 < i3; i5++) {
            int i6 = 0;
            int i7 = i6;
            while (i6 < i4) {
                i7 += sArr[((i5 * i4) + i) + i6];
                i6++;
            }
            this.zzafg[i5] = (short) (i7 / i4);
        }
    }

    private final int zza(short[] sArr, int i, int i2, int i3) {
        i *= this.zzafc;
        int i4 = 1;
        int i5 = 0;
        int i6 = 255;
        int i7 = i5;
        while (i2 <= i3) {
            int i8 = 0;
            int i9 = i8;
            while (i8 < i2) {
                short s = sArr[i + i8];
                short s2 = sArr[(i + i2) + i8];
                i9 += s >= s2 ? s - s2 : s2 - s;
                i8++;
            }
            if (i9 * i7 < i4 * i2) {
                i7 = i2;
                i4 = i9;
            }
            if (i9 * i6 > i5 * i2) {
                i6 = i2;
                i5 = i9;
            }
            i2++;
        }
        this.zzafv = i4 / i7;
        this.zzafw = i5 / i6;
        return i7;
    }

    private final void zzdl() {
        int i;
        int min;
        int i2;
        int i3;
        int i4 = this.zzafq;
        float f = this.zzaag / this.zzaah;
        double d = (double) f;
        int i5 = 1;
        if (d <= 1.00001d && d >= 0.99999d) {
            zza(this.zzafi, 0, this.zzafp);
            this.zzafp = 0;
        } else if (this.zzafp >= this.zzaff) {
            i = this.zzafp;
            int i6 = 0;
            while (true) {
                if (this.zzafs > 0) {
                    min = Math.min(this.zzaff, this.zzafs);
                    zza(this.zzafi, i6, min);
                    this.zzafs -= min;
                    i6 += min;
                } else {
                    short[] sArr = this.zzafi;
                    int i7 = this.zzzu > SsoErrorCodes.SDK_NOT_INITIALIZED ? this.zzzu / SsoErrorCodes.SDK_NOT_INITIALIZED : i5;
                    if (this.zzafc == i5 && i7 == i5) {
                        min = zza(sArr, i6, this.zzafd, this.zzafe);
                    } else {
                        zzb(sArr, i6, i7);
                        int zza = zza(this.zzafg, 0, this.zzafd / i7, this.zzafe / i7);
                        if (i7 != i5) {
                            zza *= i7;
                            i7 <<= 2;
                            i2 = zza - i7;
                            zza += i7;
                            if (i2 < this.zzafd) {
                                i2 = this.zzafd;
                            }
                            if (zza > this.zzafe) {
                                zza = this.zzafe;
                            }
                            if (this.zzafc == i5) {
                                min = zza(sArr, i6, i2, zza);
                            } else {
                                zzb(sArr, i6, i5);
                                min = zza(this.zzafg, 0, i2, zza);
                            }
                        } else {
                            min = zza;
                        }
                    }
                    i7 = this.zzafv;
                    i7 = (i7 == 0 || this.zzaft == 0 || this.zzafw > i7 * 3 || (i7 << 1) <= this.zzafu * 3) ? 0 : i5;
                    int i8 = i7 != 0 ? this.zzaft : min;
                    this.zzafu = this.zzafv;
                    this.zzaft = min;
                    int i9;
                    int i10;
                    if (d > 1.0d) {
                        short[] sArr2 = this.zzafi;
                        if (f >= 2.0f) {
                            i2 = (int) (((float) i8) / (f - 1.0f));
                        } else {
                            this.zzafs = (int) ((((float) i8) * (2.0f - f)) / (f - 1.0f));
                            i2 = i8;
                        }
                        zzo(i2);
                        i9 = i2;
                        i5 = i8;
                        i10 = i6;
                        zza(i2, this.zzafc, this.zzafk, this.zzafq, sArr2, i6, sArr2, i6 + i8);
                        this.zzafq += i9;
                        i6 = i10 + (i5 + i9);
                    } else {
                        int i11;
                        i5 = i8;
                        i10 = i6;
                        short[] sArr3 = this.zzafi;
                        if (f < 0.5f) {
                            i11 = (int) ((((float) i5) * f) / (1.0f - f));
                        } else {
                            this.zzafs = (int) ((((float) i5) * ((2.0f * f) - 1.0f)) / (1.0f - f));
                            i11 = i5;
                        }
                        i8 = i5 + i11;
                        zzo(i8);
                        System.arraycopy(sArr3, this.zzafc * i10, this.zzafk, this.zzafq * this.zzafc, this.zzafc * i5);
                        i9 = i8;
                        zza(i11, this.zzafc, this.zzafk, this.zzafq + i5, sArr3, i10 + i5, sArr3, i10);
                        this.zzafq += i9;
                        i6 = i10 + i11;
                    }
                }
                if (this.zzaff + i6 > i) {
                    break;
                }
                i5 = 1;
            }
            i3 = this.zzafp - i6;
            System.arraycopy(this.zzafi, i6 * this.zzafc, this.zzafi, 0, this.zzafc * i3);
            this.zzafp = i3;
        }
        if (this.zzaah != 1.0f) {
            f = this.zzaah;
            if (this.zzafq != i4) {
                i3 = (int) (((float) this.zzzu) / f);
                int i12 = this.zzzu;
                while (true) {
                    if (i3 <= 16384 && i12 <= 16384) {
                        break;
                    }
                    i3 /= 2;
                    i12 /= 2;
                }
                int i13 = this.zzafq - i4;
                if (this.zzafr + i13 > this.zzafl) {
                    this.zzafl += (this.zzafl / 2) + i13;
                    this.zzafm = Arrays.copyOf(this.zzafm, this.zzafl * this.zzafc);
                }
                System.arraycopy(this.zzafk, this.zzafc * i4, this.zzafm, this.zzafr * this.zzafc, this.zzafc * i13);
                this.zzafq = i4;
                this.zzafr += i13;
                i4 = 0;
                while (true) {
                    int i14 = 1;
                    if (i4 >= this.zzafr - 1) {
                        break;
                    }
                    while ((this.zzafn + i14) * i3 > this.zzafo * i12) {
                        zzo(i14);
                        for (i13 = 0; i13 < this.zzafc; i13++) {
                            short[] sArr4 = this.zzafk;
                            i = (this.zzafq * this.zzafc) + i13;
                            short[] sArr5 = this.zzafm;
                            min = (this.zzafc * i4) + i13;
                            short s = sArr5[min];
                            short s2 = sArr5[min + this.zzafc];
                            i2 = (this.zzafn + 1) * i3;
                            min = i2 - (this.zzafo * i12);
                            i2 -= this.zzafn * i3;
                            sArr4[i] = (short) (((s * min) + ((i2 - min) * s2)) / i2);
                        }
                        i14 = 1;
                        this.zzafo++;
                        this.zzafq++;
                    }
                    this.zzafn += i14;
                    if (this.zzafn == i12) {
                        this.zzafn = 0;
                        zzpo.checkState(this.zzafo == i3);
                        this.zzafo = 0;
                    }
                    i4++;
                }
                i4 = this.zzafr - 1;
                if (i4 != 0) {
                    System.arraycopy(this.zzafm, this.zzafc * i4, this.zzafm, 0, (this.zzafr - i4) * this.zzafc);
                    this.zzafr -= i4;
                }
            }
        }
    }

    private static void zza(int i, int i2, short[] sArr, int i3, short[] sArr2, int i4, short[] sArr3, int i5) {
        for (int i6 = 0; i6 < i2; i6++) {
            int i7 = (i4 * i2) + i6;
            int i8 = (i5 * i2) + i6;
            int i9 = (i3 * i2) + i6;
            for (int i10 = 0; i10 < i; i10++) {
                sArr[i9] = (short) (((sArr2[i7] * (i - i10)) + (sArr3[i8] * i10)) / i);
                i9 += i2;
                i7 += i2;
                i8 += i2;
            }
        }
    }
}
