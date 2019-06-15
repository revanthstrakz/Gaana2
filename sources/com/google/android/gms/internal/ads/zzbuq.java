package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbuq {
    private final byte[] buffer;
    private final int zzack;
    private int zzflq;
    private int zzflr = 64;
    private int zzfls = 67108864;
    private int zzflw;
    private int zzfly;
    private int zzflz = Integer.MAX_VALUE;
    private final int zzfwe;
    private int zzfwf;
    private int zzfwg;
    private zzbqf zzfwh;

    public static zzbuq zzq(byte[] bArr, int i, int i2) {
        return new zzbuq(bArr, 0, i2);
    }

    public final int zzaku() throws IOException {
        if (this.zzfwg == this.zzfwf) {
            this.zzfly = 0;
            return 0;
        }
        this.zzfly = zzalm();
        if (this.zzfly != 0) {
            return this.zzfly;
        }
        throw new zzbuy("Protocol message contained an invalid tag (zero).");
    }

    public final void zzeo(int i) throws zzbuy {
        if (this.zzfly != i) {
            throw new zzbuy("Protocol message end-group tag did not match expected tag.");
        }
    }

    public final boolean zzep(int i) throws IOException {
        switch (i & 7) {
            case 0:
                zzalm();
                return true;
            case 1:
                zzalr();
                zzalr();
                zzalr();
                zzalr();
                zzalr();
                zzalr();
                zzalr();
                zzalr();
                return true;
            case 2:
                zzet(zzalm());
                return true;
            case 3:
                break;
            case 4:
                return false;
            case 5:
                zzalr();
                zzalr();
                zzalr();
                zzalr();
                return true;
            default:
                throw new zzbuy("Protocol message tag had invalid wire type.");
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

    public final long zzakw() throws IOException {
        return zzaln();
    }

    public final int zzakx() throws IOException {
        return zzalm();
    }

    public final boolean zzala() throws IOException {
        return zzalm() != 0;
    }

    public final String readString() throws IOException {
        int zzalm = zzalm();
        if (zzalm < 0) {
            throw zzbuy.zzapp();
        } else if (zzalm > this.zzfwf - this.zzfwg) {
            throw zzbuy.zzapo();
        } else {
            String str = new String(this.buffer, this.zzfwg, zzalm, zzbux.UTF_8);
            this.zzfwg += zzalm;
            return str;
        }
    }

    public final void zza(zzbuz zzbuz) throws IOException {
        int zzalm = zzalm();
        if (this.zzflq >= this.zzflr) {
            throw new zzbuy("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
        }
        zzalm = zzer(zzalm);
        this.zzflq++;
        zzbuz.zza(this);
        zzeo(0);
        this.zzflq--;
        zzes(zzalm);
    }

    public final byte[] readBytes() throws IOException {
        int zzalm = zzalm();
        if (zzalm < 0) {
            throw zzbuy.zzapp();
        } else if (zzalm == 0) {
            return zzbvc.zzfxe;
        } else {
            if (zzalm > this.zzfwf - this.zzfwg) {
                throw zzbuy.zzapo();
            }
            byte[] bArr = new byte[zzalm];
            System.arraycopy(this.buffer, this.zzfwg, bArr, 0, zzalm);
            this.zzfwg += zzalm;
            return bArr;
        }
    }

    public final int zzalm() throws IOException {
        byte zzalr = zzalr();
        if (zzalr >= (byte) 0) {
            return zzalr;
        }
        int i = zzalr & 127;
        byte zzalr2 = zzalr();
        if (zzalr2 >= (byte) 0) {
            i |= zzalr2 << 7;
        } else {
            i |= (zzalr2 & 127) << 7;
            zzalr2 = zzalr();
            if (zzalr2 >= (byte) 0) {
                i |= zzalr2 << 14;
            } else {
                i |= (zzalr2 & 127) << 14;
                zzalr2 = zzalr();
                if (zzalr2 >= (byte) 0) {
                    i |= zzalr2 << 21;
                } else {
                    i |= (zzalr2 & 127) << 21;
                    zzalr2 = zzalr();
                    i |= zzalr2 << 28;
                    if (zzalr2 < (byte) 0) {
                        for (int i2 = 0; i2 < 5; i2++) {
                            if (zzalr() >= (byte) 0) {
                                return i;
                            }
                        }
                        throw zzbuy.zzapq();
                    }
                }
            }
        }
        return i;
    }

    public final long zzaln() throws IOException {
        int i = 0;
        long j = 0;
        while (i < 64) {
            byte zzalr = zzalr();
            long j2 = j | (((long) (zzalr & 127)) << i);
            if ((zzalr & 128) == 0) {
                return j2;
            }
            i += 7;
            j = j2;
        }
        throw zzbuy.zzapq();
    }

    private zzbuq(byte[] bArr, int i, int i2) {
        this.buffer = bArr;
        this.zzfwe = i;
        i2 += i;
        this.zzfwf = i2;
        this.zzack = i2;
        this.zzfwg = i;
    }

    public final <T extends zzbrd<T, ?>> T zza(zzbsw<T> zzbsw) throws IOException {
        try {
            if (this.zzfwh == null) {
                this.zzfwh = zzbqf.zzk(this.buffer, this.zzfwe, this.zzack);
            }
            int zzall = this.zzfwh.zzall();
            int i = this.zzfwg - this.zzfwe;
            if (zzall > i) {
                throw new IOException(String.format("CodedInputStream read ahead of CodedInputByteBufferNano: %s > %s", new Object[]{Integer.valueOf(zzall), Integer.valueOf(i)}));
            }
            this.zzfwh.zzet(i - zzall);
            this.zzfwh.zzeq(this.zzflr - this.zzflq);
            zzbrd zzbrd = (zzbrd) this.zzfwh.zza(zzbsw, zzbqq.zzame());
            zzep(this.zzfly);
            return zzbrd;
        } catch (zzbrl e) {
            throw new zzbuy("", e);
        }
    }

    public final int zzer(int i) throws zzbuy {
        if (i < 0) {
            throw zzbuy.zzapp();
        }
        i += this.zzfwg;
        int i2 = this.zzflz;
        if (i > i2) {
            throw zzbuy.zzapo();
        }
        this.zzflz = i;
        zzalq();
        return i2;
    }

    private final void zzalq() {
        this.zzfwf += this.zzflw;
        int i = this.zzfwf;
        if (i > this.zzflz) {
            this.zzflw = i - this.zzflz;
            this.zzfwf -= this.zzflw;
            return;
        }
        this.zzflw = 0;
    }

    public final void zzes(int i) {
        this.zzflz = i;
        zzalq();
    }

    public final int zzapl() {
        if (this.zzflz == Integer.MAX_VALUE) {
            return -1;
        }
        return this.zzflz - this.zzfwg;
    }

    public final int getPosition() {
        return this.zzfwg - this.zzfwe;
    }

    public final byte[] zzam(int i, int i2) {
        if (i2 == 0) {
            return zzbvc.zzfxe;
        }
        byte[] bArr = new byte[i2];
        System.arraycopy(this.buffer, this.zzfwe + i, bArr, 0, i2);
        return bArr;
    }

    public final void zzgc(int i) {
        zzan(i, this.zzfly);
    }

    /* Access modifiers changed, original: final */
    public final void zzan(int i, int i2) {
        if (i > this.zzfwg - this.zzfwe) {
            int i3 = this.zzfwg - this.zzfwe;
            StringBuilder stringBuilder = new StringBuilder(50);
            stringBuilder.append("Position ");
            stringBuilder.append(i);
            stringBuilder.append(" is beyond current ");
            stringBuilder.append(i3);
            throw new IllegalArgumentException(stringBuilder.toString());
        } else if (i < 0) {
            StringBuilder stringBuilder2 = new StringBuilder(24);
            stringBuilder2.append("Bad position ");
            stringBuilder2.append(i);
            throw new IllegalArgumentException(stringBuilder2.toString());
        } else {
            this.zzfwg = this.zzfwe + i;
            this.zzfly = i2;
        }
    }

    private final byte zzalr() throws IOException {
        if (this.zzfwg == this.zzfwf) {
            throw zzbuy.zzapo();
        }
        byte[] bArr = this.buffer;
        int i = this.zzfwg;
        this.zzfwg = i + 1;
        return bArr[i];
    }

    private final void zzet(int i) throws IOException {
        if (i < 0) {
            throw zzbuy.zzapp();
        } else if (this.zzfwg + i > this.zzflz) {
            zzet(this.zzflz - this.zzfwg);
            throw zzbuy.zzapo();
        } else if (i <= this.zzfwf - this.zzfwg) {
            this.zzfwg += i;
        } else {
            throw zzbuy.zzapo();
        }
    }
}
