package com.google.android.gms.internal.vision;

import java.io.IOException;
import java.util.Arrays;

final class zzfb extends zzez {
    private final byte[] buffer;
    private int limit;
    private int pos;
    private final boolean zzsk;
    private int zzsl;
    private int zzsm;
    private int zzsn;
    private int zzso;

    private zzfb(byte[] bArr, int i, int i2, boolean z) {
        super();
        this.zzso = Integer.MAX_VALUE;
        this.buffer = bArr;
        this.limit = i2 + i;
        this.pos = i;
        this.zzsm = this.pos;
        this.zzsk = z;
    }

    public final int zzdq() throws IOException {
        if (zzcm()) {
            this.zzsn = 0;
            return 0;
        }
        this.zzsn = zzdt();
        if ((this.zzsn >>> 3) != 0) {
            return this.zzsn;
        }
        throw zzgf.zzfk();
    }

    public final void zzak(int i) throws zzgf {
        if (this.zzsn != i) {
            throw zzgf.zzfl();
        }
    }

    public final boolean zzal(int i) throws IOException {
        int i2 = 0;
        switch (i & 7) {
            case 0:
                if (this.limit - this.pos >= 10) {
                    while (i2 < 10) {
                        byte[] bArr = this.buffer;
                        int i3 = this.pos;
                        this.pos = i3 + 1;
                        if (bArr[i3] < (byte) 0) {
                            i2++;
                        }
                    }
                    throw zzgf.zzfj();
                }
                while (i2 < 10) {
                    if (zzdy() < (byte) 0) {
                        i2++;
                    }
                }
                throw zzgf.zzfj();
                return true;
            case 1:
                zzap(8);
                return true;
            case 2:
                zzap(zzdt());
                return true;
            case 3:
                break;
            case 4:
                return false;
            case 5:
                zzap(4);
                return true;
            default:
                throw zzgf.zzfm();
        }
        int zzdq;
        do {
            zzdq = zzdq();
            if (zzdq != 0) {
            }
            zzak(((i >>> 3) << 3) | 4);
            return true;
        } while (zzal(zzdq));
        zzak(((i >>> 3) << 3) | 4);
        return true;
    }

    public final double readDouble() throws IOException {
        return Double.longBitsToDouble(zzdw());
    }

    public final float readFloat() throws IOException {
        return Float.intBitsToFloat(zzdv());
    }

    public final long zzcp() throws IOException {
        return zzdu();
    }

    public final long zzcq() throws IOException {
        return zzdu();
    }

    public final int zzcr() throws IOException {
        return zzdt();
    }

    public final long zzcs() throws IOException {
        return zzdw();
    }

    public final int zzct() throws IOException {
        return zzdv();
    }

    public final boolean zzcu() throws IOException {
        return zzdu() != 0;
    }

    public final String readString() throws IOException {
        int zzdt = zzdt();
        if (zzdt > 0 && zzdt <= this.limit - this.pos) {
            String str = new String(this.buffer, this.pos, zzdt, zzga.UTF_8);
            this.pos += zzdt;
            return str;
        } else if (zzdt == 0) {
            return "";
        } else {
            if (zzdt < 0) {
                throw zzgf.zzfi();
            }
            throw zzgf.zzfh();
        }
    }

    public final String zzcv() throws IOException {
        int zzdt = zzdt();
        if (zzdt > 0 && zzdt <= this.limit - this.pos) {
            String zzi = zziw.zzi(this.buffer, this.pos, zzdt);
            this.pos += zzdt;
            return zzi;
        } else if (zzdt == 0) {
            return "";
        } else {
            if (zzdt <= 0) {
                throw zzgf.zzfi();
            }
            throw zzgf.zzfh();
        }
    }

    public final <T extends zzhf> T zza(zzhq<T> zzhq, zzfk zzfk) throws IOException {
        int zzdt = zzdt();
        if (this.zzsf >= this.zzsg) {
            throw zzgf.zzfn();
        }
        zzdt = zzan(zzdt);
        this.zzsf++;
        zzhf zzhf = (zzhf) zzhq.zza(this, zzfk);
        zzak(0);
        this.zzsf--;
        zzao(zzdt);
        return zzhf;
    }

    public final zzeo zzcw() throws IOException {
        int zzdt = zzdt();
        if (zzdt > 0 && zzdt <= this.limit - this.pos) {
            zzeo zzb = zzeo.zzb(this.buffer, this.pos, zzdt);
            this.pos += zzdt;
            return zzb;
        } else if (zzdt == 0) {
            return zzeo.zzrx;
        } else {
            byte[] copyOfRange;
            if (zzdt > 0 && zzdt <= this.limit - this.pos) {
                int i = this.pos;
                this.pos += zzdt;
                copyOfRange = Arrays.copyOfRange(this.buffer, i, this.pos);
            } else if (zzdt > 0) {
                throw zzgf.zzfh();
            } else if (zzdt == 0) {
                copyOfRange = zzga.zzxn;
            } else {
                throw zzgf.zzfi();
            }
            return zzeo.zze(copyOfRange);
        }
    }

    public final int zzcx() throws IOException {
        return zzdt();
    }

    public final int zzcy() throws IOException {
        return zzdt();
    }

    public final int zzcz() throws IOException {
        return zzdv();
    }

    public final long zzda() throws IOException {
        return zzdw();
    }

    public final int zzdb() throws IOException {
        return zzez.zzaq(zzdt());
    }

    public final long zzdc() throws IOException {
        return zzez.zzd(zzdu());
    }

    /* JADX WARNING: Missing block: B:29:0x0068, code skipped:
            if (r1[r2] >= (byte) 0) goto L_0x006a;
     */
    private final int zzdt() throws java.io.IOException {
        /*
        r5 = this;
        r0 = r5.pos;
        r1 = r5.limit;
        if (r1 == r0) goto L_0x006d;
    L_0x0006:
        r1 = r5.buffer;
        r2 = r0 + 1;
        r0 = r1[r0];
        if (r0 < 0) goto L_0x0011;
    L_0x000e:
        r5.pos = r2;
        return r0;
    L_0x0011:
        r3 = r5.limit;
        r3 = r3 - r2;
        r4 = 9;
        if (r3 < r4) goto L_0x006d;
    L_0x0018:
        r3 = r2 + 1;
        r2 = r1[r2];
        r2 = r2 << 7;
        r0 = r0 ^ r2;
        if (r0 >= 0) goto L_0x0024;
    L_0x0021:
        r0 = r0 ^ -128;
        goto L_0x006a;
    L_0x0024:
        r2 = r3 + 1;
        r3 = r1[r3];
        r3 = r3 << 14;
        r0 = r0 ^ r3;
        if (r0 < 0) goto L_0x0031;
    L_0x002d:
        r0 = r0 ^ 16256;
    L_0x002f:
        r3 = r2;
        goto L_0x006a;
    L_0x0031:
        r3 = r2 + 1;
        r2 = r1[r2];
        r2 = r2 << 21;
        r0 = r0 ^ r2;
        if (r0 >= 0) goto L_0x003f;
    L_0x003a:
        r1 = -2080896; // 0xffffffffffe03f80 float:NaN double:NaN;
        r0 = r0 ^ r1;
        goto L_0x006a;
    L_0x003f:
        r2 = r3 + 1;
        r3 = r1[r3];
        r4 = r3 << 28;
        r0 = r0 ^ r4;
        r4 = 266354560; // 0xfe03f80 float:2.2112565E-29 double:1.315966377E-315;
        r0 = r0 ^ r4;
        if (r3 >= 0) goto L_0x002f;
    L_0x004c:
        r3 = r2 + 1;
        r2 = r1[r2];
        if (r2 >= 0) goto L_0x006a;
    L_0x0052:
        r2 = r3 + 1;
        r3 = r1[r3];
        if (r3 >= 0) goto L_0x002f;
    L_0x0058:
        r3 = r2 + 1;
        r2 = r1[r2];
        if (r2 >= 0) goto L_0x006a;
    L_0x005e:
        r2 = r3 + 1;
        r3 = r1[r3];
        if (r3 >= 0) goto L_0x002f;
    L_0x0064:
        r3 = r2 + 1;
        r1 = r1[r2];
        if (r1 < 0) goto L_0x006d;
    L_0x006a:
        r5.pos = r3;
        return r0;
    L_0x006d:
        r0 = r5.zzdr();
        r0 = (int) r0;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzfb.zzdt():int");
    }

    private final long zzdu() throws IOException {
        int i = this.pos;
        if (this.limit != i) {
            byte[] bArr = this.buffer;
            int i2 = i + 1;
            byte b = bArr[i];
            if (b >= (byte) 0) {
                this.pos = i2;
                return (long) b;
            } else if (this.limit - i2 >= 9) {
                long j;
                long j2;
                long j3;
                int i3 = i2 + 1;
                i = b ^ (bArr[i2] << 7);
                if (i < 0) {
                    j = (long) (i ^ -128);
                } else {
                    i2 = i3 + 1;
                    i ^= bArr[i3] << 14;
                    if (i >= 0) {
                        j2 = (long) (i ^ 16256);
                        i = i2;
                        j3 = j2;
                        this.pos = i;
                        return j3;
                    }
                    i3 = i2 + 1;
                    i ^= bArr[i2] << 21;
                    if (i < 0) {
                        j = (long) (i ^ -2080896);
                    } else {
                        long j4;
                        long j5 = (long) i;
                        i = i3 + 1;
                        long j6 = j5 ^ (((long) bArr[i3]) << 28);
                        if (j6 >= 0) {
                            j4 = j6 ^ 266354560;
                        } else {
                            int i4 = i + 1;
                            long j7 = j6 ^ (((long) bArr[i]) << 35);
                            if (j7 < 0) {
                                j3 = j7 ^ -34093383808L;
                            } else {
                                i = i4 + 1;
                                j6 = j7 ^ (((long) bArr[i4]) << 42);
                                if (j6 >= 0) {
                                    j4 = j6 ^ 4363953127296L;
                                } else {
                                    i4 = i + 1;
                                    j7 = j6 ^ (((long) bArr[i]) << 49);
                                    if (j7 < 0) {
                                        j3 = j7 ^ -558586000294016L;
                                    } else {
                                        i = i4 + 1;
                                        long j8 = (j7 ^ (((long) bArr[i4]) << 56)) ^ 71499008037633920L;
                                        if (j8 < 0) {
                                            i4 = i + 1;
                                            if (((long) bArr[i]) >= 0) {
                                                i = i4;
                                            }
                                        }
                                        j3 = j8;
                                        this.pos = i;
                                        return j3;
                                    }
                                }
                            }
                            i = i4;
                            this.pos = i;
                            return j3;
                        }
                        j3 = j4;
                        this.pos = i;
                        return j3;
                    }
                }
                j2 = j;
                i = i3;
                j3 = j2;
                this.pos = i;
                return j3;
            }
        }
        return zzdr();
    }

    /* Access modifiers changed, original: final */
    public final long zzdr() throws IOException {
        long j = 0;
        int i = 0;
        while (i < 64) {
            byte zzdy = zzdy();
            long j2 = j | (((long) (zzdy & 127)) << i);
            if ((zzdy & 128) == 0) {
                return j2;
            }
            i += 7;
            j = j2;
        }
        throw zzgf.zzfj();
    }

    private final int zzdv() throws IOException {
        int i = this.pos;
        if (this.limit - i < 4) {
            throw zzgf.zzfh();
        }
        byte[] bArr = this.buffer;
        this.pos = i + 4;
        return ((bArr[i + 3] & 255) << 24) | (((bArr[i] & 255) | ((bArr[i + 1] & 255) << 8)) | ((bArr[i + 2] & 255) << 16));
    }

    private final long zzdw() throws IOException {
        int i = this.pos;
        if (this.limit - i < 8) {
            throw zzgf.zzfh();
        }
        byte[] bArr = this.buffer;
        this.pos = i + 8;
        return (((((((((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8)) | ((((long) bArr[i + 2]) & 255) << 16)) | ((((long) bArr[i + 3]) & 255) << 24)) | ((((long) bArr[i + 4]) & 255) << 32)) | ((((long) bArr[i + 5]) & 255) << 40)) | ((((long) bArr[i + 6]) & 255) << 48)) | ((((long) bArr[i + 7]) & 255) << 56);
    }

    public final int zzan(int i) throws zzgf {
        if (i < 0) {
            throw zzgf.zzfi();
        }
        i += zzds();
        int i2 = this.zzso;
        if (i > i2) {
            throw zzgf.zzfh();
        }
        this.zzso = i;
        zzdx();
        return i2;
    }

    private final void zzdx() {
        this.limit += this.zzsl;
        int i = this.limit - this.zzsm;
        if (i > this.zzso) {
            this.zzsl = i - this.zzso;
            this.limit -= this.zzsl;
            return;
        }
        this.zzsl = 0;
    }

    public final void zzao(int i) {
        this.zzso = i;
        zzdx();
    }

    public final boolean zzcm() throws IOException {
        return this.pos == this.limit;
    }

    public final int zzds() {
        return this.pos - this.zzsm;
    }

    private final byte zzdy() throws IOException {
        if (this.pos == this.limit) {
            throw zzgf.zzfh();
        }
        byte[] bArr = this.buffer;
        int i = this.pos;
        this.pos = i + 1;
        return bArr[i];
    }

    public final void zzap(int i) throws IOException {
        if (i >= 0 && i <= this.limit - this.pos) {
            this.pos += i;
        } else if (i < 0) {
            throw zzgf.zzfi();
        } else {
            throw zzgf.zzfh();
        }
    }

    /* synthetic */ zzfb(byte[] bArr, int i, int i2, boolean z, zzfa zzfa) {
        this(bArr, i, i2, z);
    }
}
