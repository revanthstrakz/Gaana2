package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.Arrays;

final class zzbqh extends zzbqf {
    private final byte[] buffer;
    private int limit;
    private int pos;
    private final boolean zzflv;
    private int zzflw;
    private int zzflx;
    private int zzfly;
    private int zzflz;

    private zzbqh(byte[] bArr, int i, int i2, boolean z) {
        super();
        this.zzflz = Integer.MAX_VALUE;
        this.buffer = bArr;
        this.limit = i2 + i;
        this.pos = i;
        this.zzflx = this.pos;
        this.zzflv = z;
    }

    public final int zzaku() throws IOException {
        if (zzalk()) {
            this.zzfly = 0;
            return 0;
        }
        this.zzfly = zzalm();
        if ((this.zzfly >>> 3) != 0) {
            return this.zzfly;
        }
        throw zzbrl.zzanf();
    }

    public final void zzeo(int i) throws zzbrl {
        if (this.zzfly != i) {
            throw zzbrl.zzang();
        }
    }

    public final boolean zzep(int i) throws IOException {
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
                    throw zzbrl.zzane();
                }
                while (i2 < 10) {
                    if (zzalr() < (byte) 0) {
                        i2++;
                    }
                }
                throw zzbrl.zzane();
                return true;
            case 1:
                zzet(8);
                return true;
            case 2:
                zzet(zzalm());
                return true;
            case 3:
                break;
            case 4:
                return false;
            case 5:
                zzet(4);
                return true;
            default:
                throw zzbrl.zzanh();
        }
        int zzaku;
        do {
            zzaku = zzaku();
            if (zzaku != 0) {
            }
            zzeo(((i >>> 3) << 3) | 4);
            return true;
        } while (zzep(zzaku));
        zzeo(((i >>> 3) << 3) | 4);
        return true;
    }

    public final double readDouble() throws IOException {
        return Double.longBitsToDouble(zzalp());
    }

    public final float readFloat() throws IOException {
        return Float.intBitsToFloat(zzalo());
    }

    public final long zzakv() throws IOException {
        return zzaln();
    }

    public final long zzakw() throws IOException {
        return zzaln();
    }

    public final int zzakx() throws IOException {
        return zzalm();
    }

    public final long zzaky() throws IOException {
        return zzalp();
    }

    public final int zzakz() throws IOException {
        return zzalo();
    }

    public final boolean zzala() throws IOException {
        return zzaln() != 0;
    }

    public final String readString() throws IOException {
        int zzalm = zzalm();
        if (zzalm > 0 && zzalm <= this.limit - this.pos) {
            String str = new String(this.buffer, this.pos, zzalm, zzbrf.UTF_8);
            this.pos += zzalm;
            return str;
        } else if (zzalm == 0) {
            return "";
        } else {
            if (zzalm < 0) {
                throw zzbrl.zzand();
            }
            throw zzbrl.zzanc();
        }
    }

    public final String zzalb() throws IOException {
        int zzalm = zzalm();
        if (zzalm > 0 && zzalm <= this.limit - this.pos) {
            String zzo = zzbuc.zzo(this.buffer, this.pos, zzalm);
            this.pos += zzalm;
            return zzo;
        } else if (zzalm == 0) {
            return "";
        } else {
            if (zzalm <= 0) {
                throw zzbrl.zzand();
            }
            throw zzbrl.zzanc();
        }
    }

    public final <T extends zzbsl> T zza(zzbsw<T> zzbsw, zzbqq zzbqq) throws IOException {
        int zzalm = zzalm();
        if (this.zzflq >= this.zzflr) {
            throw zzbrl.zzani();
        }
        zzalm = zzer(zzalm);
        this.zzflq++;
        zzbsl zzbsl = (zzbsl) zzbsw.zza(this, zzbqq);
        zzeo(0);
        this.zzflq--;
        zzes(zzalm);
        return zzbsl;
    }

    public final zzbpu zzalc() throws IOException {
        int zzalm = zzalm();
        if (zzalm > 0 && zzalm <= this.limit - this.pos) {
            zzbpu zzi = zzbpu.zzi(this.buffer, this.pos, zzalm);
            this.pos += zzalm;
            return zzi;
        } else if (zzalm == 0) {
            return zzbpu.zzfli;
        } else {
            byte[] copyOfRange;
            if (zzalm > 0 && zzalm <= this.limit - this.pos) {
                int i = this.pos;
                this.pos += zzalm;
                copyOfRange = Arrays.copyOfRange(this.buffer, i, this.pos);
            } else if (zzalm > 0) {
                throw zzbrl.zzanc();
            } else if (zzalm == 0) {
                copyOfRange = zzbrf.zzfqr;
            } else {
                throw zzbrl.zzand();
            }
            return zzbpu.zzs(copyOfRange);
        }
    }

    public final int zzald() throws IOException {
        return zzalm();
    }

    public final int zzale() throws IOException {
        return zzalm();
    }

    public final int zzalf() throws IOException {
        return zzalo();
    }

    public final long zzalg() throws IOException {
        return zzalp();
    }

    public final int zzalh() throws IOException {
        return zzbqf.zzeu(zzalm());
    }

    public final long zzali() throws IOException {
        return zzbqf.zzax(zzaln());
    }

    /* JADX WARNING: Missing block: B:29:0x0068, code skipped:
            if (r1[r2] >= (byte) 0) goto L_0x006a;
     */
    private final int zzalm() throws java.io.IOException {
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
        r0 = r5.zzalj();
        r0 = (int) r0;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbqh.zzalm():int");
    }

    private final long zzaln() throws IOException {
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
        return zzalj();
    }

    /* Access modifiers changed, original: final */
    public final long zzalj() throws IOException {
        long j = 0;
        int i = 0;
        while (i < 64) {
            byte zzalr = zzalr();
            long j2 = j | (((long) (zzalr & 127)) << i);
            if ((zzalr & 128) == 0) {
                return j2;
            }
            i += 7;
            j = j2;
        }
        throw zzbrl.zzane();
    }

    private final int zzalo() throws IOException {
        int i = this.pos;
        if (this.limit - i < 4) {
            throw zzbrl.zzanc();
        }
        byte[] bArr = this.buffer;
        this.pos = i + 4;
        return ((bArr[i + 3] & 255) << 24) | (((bArr[i] & 255) | ((bArr[i + 1] & 255) << 8)) | ((bArr[i + 2] & 255) << 16));
    }

    private final long zzalp() throws IOException {
        int i = this.pos;
        if (this.limit - i < 8) {
            throw zzbrl.zzanc();
        }
        byte[] bArr = this.buffer;
        this.pos = i + 8;
        return (((((((((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8)) | ((((long) bArr[i + 2]) & 255) << 16)) | ((((long) bArr[i + 3]) & 255) << 24)) | ((((long) bArr[i + 4]) & 255) << 32)) | ((((long) bArr[i + 5]) & 255) << 40)) | ((((long) bArr[i + 6]) & 255) << 48)) | ((((long) bArr[i + 7]) & 255) << 56);
    }

    public final int zzer(int i) throws zzbrl {
        if (i < 0) {
            throw zzbrl.zzand();
        }
        i += zzall();
        int i2 = this.zzflz;
        if (i > i2) {
            throw zzbrl.zzanc();
        }
        this.zzflz = i;
        zzalq();
        return i2;
    }

    private final void zzalq() {
        this.limit += this.zzflw;
        int i = this.limit - this.zzflx;
        if (i > this.zzflz) {
            this.zzflw = i - this.zzflz;
            this.limit -= this.zzflw;
            return;
        }
        this.zzflw = 0;
    }

    public final void zzes(int i) {
        this.zzflz = i;
        zzalq();
    }

    public final boolean zzalk() throws IOException {
        return this.pos == this.limit;
    }

    public final int zzall() {
        return this.pos - this.zzflx;
    }

    private final byte zzalr() throws IOException {
        if (this.pos == this.limit) {
            throw zzbrl.zzanc();
        }
        byte[] bArr = this.buffer;
        int i = this.pos;
        this.pos = i + 1;
        return bArr[i];
    }

    public final void zzet(int i) throws IOException {
        if (i >= 0 && i <= this.limit - this.pos) {
            this.pos += i;
        } else if (i < 0) {
            throw zzbrl.zzand();
        } else {
            throw zzbrl.zzanc();
        }
    }

    /* synthetic */ zzbqh(byte[] bArr, int i, int i2, boolean z, zzbqg zzbqg) {
        this(bArr, i, i2, z);
    }
}
