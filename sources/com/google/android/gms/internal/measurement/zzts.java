package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Arrays;

final class zzts extends zztq {
    private final byte[] buffer;
    private int limit;
    private int pos;
    private final boolean zzbud;
    private int zzbue;
    private int zzbuf;
    private int zzbug;
    private int zzbuh;

    private zzts(byte[] bArr, int i, int i2, boolean z) {
        super();
        this.zzbuh = Integer.MAX_VALUE;
        this.buffer = bArr;
        this.limit = i2 + i;
        this.pos = i;
        this.zzbuf = this.pos;
        this.zzbud = z;
    }

    public final int zzuj() throws IOException {
        if (zzuz()) {
            this.zzbug = 0;
            return 0;
        }
        this.zzbug = zzvb();
        if ((this.zzbug >>> 3) != 0) {
            return this.zzbug;
        }
        throw new zzuv("Protocol message contained an invalid tag (zero).");
    }

    public final void zzap(int i) throws zzuv {
        if (this.zzbug != i) {
            throw zzuv.zzwt();
        }
    }

    public final boolean zzaq(int i) throws IOException {
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
                    throw zzuv.zzws();
                }
                while (i2 < 10) {
                    if (zzvg() < (byte) 0) {
                        i2++;
                    }
                }
                throw zzuv.zzws();
                return true;
            case 1:
                zzau(8);
                return true;
            case 2:
                zzau(zzvb());
                return true;
            case 3:
                break;
            case 4:
                return false;
            case 5:
                zzau(4);
                return true;
            default:
                throw zzuv.zzwu();
        }
        int zzuj;
        do {
            zzuj = zzuj();
            if (zzuj != 0) {
            }
            zzap(((i >>> 3) << 3) | 4);
            return true;
        } while (zzaq(zzuj));
        zzap(((i >>> 3) << 3) | 4);
        return true;
    }

    public final double readDouble() throws IOException {
        return Double.longBitsToDouble(zzve());
    }

    public final float readFloat() throws IOException {
        return Float.intBitsToFloat(zzvd());
    }

    public final long zzuk() throws IOException {
        return zzvc();
    }

    public final long zzul() throws IOException {
        return zzvc();
    }

    public final int zzum() throws IOException {
        return zzvb();
    }

    public final long zzun() throws IOException {
        return zzve();
    }

    public final int zzuo() throws IOException {
        return zzvd();
    }

    public final boolean zzup() throws IOException {
        return zzvc() != 0;
    }

    public final String readString() throws IOException {
        int zzvb = zzvb();
        if (zzvb > 0 && zzvb <= this.limit - this.pos) {
            String str = new String(this.buffer, this.pos, zzvb, zzuq.UTF_8);
            this.pos += zzvb;
            return str;
        } else if (zzvb == 0) {
            return "";
        } else {
            if (zzvb < 0) {
                throw zzuv.zzwr();
            }
            throw zzuv.zzwq();
        }
    }

    public final String zzuq() throws IOException {
        int zzvb = zzvb();
        if (zzvb > 0 && zzvb <= this.limit - this.pos) {
            String zzh = zzxl.zzh(this.buffer, this.pos, zzvb);
            this.pos += zzvb;
            return zzh;
        } else if (zzvb == 0) {
            return "";
        } else {
            if (zzvb <= 0) {
                throw zzuv.zzwr();
            }
            throw zzuv.zzwq();
        }
    }

    public final <T extends zzvv> T zza(zzwf<T> zzwf, zzub zzub) throws IOException {
        int zzvb = zzvb();
        if (this.zzbty >= this.zzbtz) {
            throw zzuv.zzwv();
        }
        zzvb = zzas(zzvb);
        this.zzbty++;
        zzvv zzvv = (zzvv) zzwf.zza(this, zzub);
        zzap(0);
        this.zzbty--;
        zzat(zzvb);
        return zzvv;
    }

    public final zzte zzur() throws IOException {
        int zzvb = zzvb();
        if (zzvb > 0 && zzvb <= this.limit - this.pos) {
            zzte zzb = zzte.zzb(this.buffer, this.pos, zzvb);
            this.pos += zzvb;
            return zzb;
        } else if (zzvb == 0) {
            return zzte.zzbtq;
        } else {
            byte[] copyOfRange;
            if (zzvb > 0 && zzvb <= this.limit - this.pos) {
                int i = this.pos;
                this.pos += zzvb;
                copyOfRange = Arrays.copyOfRange(this.buffer, i, this.pos);
            } else if (zzvb > 0) {
                throw zzuv.zzwq();
            } else if (zzvb == 0) {
                copyOfRange = zzuq.zzbza;
            } else {
                throw zzuv.zzwr();
            }
            return zzte.zzi(copyOfRange);
        }
    }

    public final int zzus() throws IOException {
        return zzvb();
    }

    public final int zzut() throws IOException {
        return zzvb();
    }

    public final int zzuu() throws IOException {
        return zzvd();
    }

    public final long zzuv() throws IOException {
        return zzve();
    }

    public final int zzuw() throws IOException {
        int zzvb = zzvb();
        return (-(zzvb & 1)) ^ (zzvb >>> 1);
    }

    public final long zzux() throws IOException {
        long zzvc = zzvc();
        return (zzvc >>> 1) ^ (-(zzvc & 1));
    }

    /* JADX WARNING: Missing block: B:29:0x0068, code skipped:
            if (r1[r2] >= (byte) 0) goto L_0x006a;
     */
    private final int zzvb() throws java.io.IOException {
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
        r0 = r5.zzuy();
        r0 = (int) r0;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzts.zzvb():int");
    }

    private final long zzvc() throws IOException {
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
        return zzuy();
    }

    /* Access modifiers changed, original: final */
    public final long zzuy() throws IOException {
        long j = 0;
        int i = 0;
        while (i < 64) {
            byte zzvg = zzvg();
            long j2 = j | (((long) (zzvg & 127)) << i);
            if ((zzvg & 128) == 0) {
                return j2;
            }
            i += 7;
            j = j2;
        }
        throw zzuv.zzws();
    }

    private final int zzvd() throws IOException {
        int i = this.pos;
        if (this.limit - i < 4) {
            throw zzuv.zzwq();
        }
        byte[] bArr = this.buffer;
        this.pos = i + 4;
        return ((bArr[i + 3] & 255) << 24) | (((bArr[i] & 255) | ((bArr[i + 1] & 255) << 8)) | ((bArr[i + 2] & 255) << 16));
    }

    private final long zzve() throws IOException {
        int i = this.pos;
        if (this.limit - i < 8) {
            throw zzuv.zzwq();
        }
        byte[] bArr = this.buffer;
        this.pos = i + 8;
        return (((((((((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8)) | ((((long) bArr[i + 2]) & 255) << 16)) | ((((long) bArr[i + 3]) & 255) << 24)) | ((((long) bArr[i + 4]) & 255) << 32)) | ((((long) bArr[i + 5]) & 255) << 40)) | ((((long) bArr[i + 6]) & 255) << 48)) | ((((long) bArr[i + 7]) & 255) << 56);
    }

    public final int zzas(int i) throws zzuv {
        if (i < 0) {
            throw zzuv.zzwr();
        }
        i += zzva();
        int i2 = this.zzbuh;
        if (i > i2) {
            throw zzuv.zzwq();
        }
        this.zzbuh = i;
        zzvf();
        return i2;
    }

    private final void zzvf() {
        this.limit += this.zzbue;
        int i = this.limit - this.zzbuf;
        if (i > this.zzbuh) {
            this.zzbue = i - this.zzbuh;
            this.limit -= this.zzbue;
            return;
        }
        this.zzbue = 0;
    }

    public final void zzat(int i) {
        this.zzbuh = i;
        zzvf();
    }

    public final boolean zzuz() throws IOException {
        return this.pos == this.limit;
    }

    public final int zzva() {
        return this.pos - this.zzbuf;
    }

    private final byte zzvg() throws IOException {
        if (this.pos == this.limit) {
            throw zzuv.zzwq();
        }
        byte[] bArr = this.buffer;
        int i = this.pos;
        this.pos = i + 1;
        return bArr[i];
    }

    public final void zzau(int i) throws IOException {
        if (i >= 0 && i <= this.limit - this.pos) {
            this.pos += i;
        } else if (i < 0) {
            throw zzuv.zzwr();
        } else {
            throw zzuv.zzwq();
        }
    }

    /* synthetic */ zzts(byte[] bArr, int i, int i2, boolean z, zztr zztr) {
        this(bArr, i, i2, z);
    }
}
