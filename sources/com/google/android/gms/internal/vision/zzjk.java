package com.google.android.gms.internal.vision;

import java.io.IOException;

public final class zzjk {
    private final byte[] buffer;
    private final int zzacz;
    private final int zzada;
    private int zzadb;
    private int zzadc;
    private zzez zzadd;
    private int zzsf;
    private int zzsg = 64;
    private int zzsh = 67108864;
    private int zzsl;
    private int zzsn;
    private int zzso = Integer.MAX_VALUE;

    public static zzjk zzk(byte[] bArr, int i, int i2) {
        return new zzjk(bArr, 0, i2);
    }

    public final int zzdq() throws IOException {
        if (this.zzadc == this.zzadb) {
            this.zzsn = 0;
            return 0;
        }
        this.zzsn = zzdt();
        if (this.zzsn != 0) {
            return this.zzsn;
        }
        throw new zzjs("Protocol message contained an invalid tag (zero).");
    }

    public final void zzak(int i) throws zzjs {
        if (this.zzsn != i) {
            throw new zzjs("Protocol message end-group tag did not match expected tag.");
        }
    }

    public final boolean zzal(int i) throws IOException {
        switch (i & 7) {
            case 0:
                zzdt();
                return true;
            case 1:
                zzdy();
                zzdy();
                zzdy();
                zzdy();
                zzdy();
                zzdy();
                zzdy();
                zzdy();
                return true;
            case 2:
                zzap(zzdt());
                return true;
            case 3:
                break;
            case 4:
                return false;
            case 5:
                zzdv();
                return true;
            default:
                throw new zzjs("Protocol message tag had invalid wire type.");
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

    public final boolean zzcu() throws IOException {
        return zzdt() != 0;
    }

    public final String readString() throws IOException {
        int zzdt = zzdt();
        if (zzdt < 0) {
            throw zzjs.zzhu();
        } else if (zzdt > this.zzadb - this.zzadc) {
            throw zzjs.zzht();
        } else {
            String str = new String(this.buffer, this.zzadc, zzdt, zzjr.UTF_8);
            this.zzadc += zzdt;
            return str;
        }
    }

    public final void zza(zzjt zzjt) throws IOException {
        int zzdt = zzdt();
        if (this.zzsf >= this.zzsg) {
            throw new zzjs("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
        }
        zzdt = zzan(zzdt);
        this.zzsf++;
        zzjt.zza(this);
        zzak(0);
        this.zzsf--;
        zzao(zzdt);
    }

    public final int zzdt() throws IOException {
        byte zzdy = zzdy();
        if (zzdy >= (byte) 0) {
            return zzdy;
        }
        int i = zzdy & 127;
        byte zzdy2 = zzdy();
        if (zzdy2 >= (byte) 0) {
            i |= zzdy2 << 7;
        } else {
            i |= (zzdy2 & 127) << 7;
            zzdy2 = zzdy();
            if (zzdy2 >= (byte) 0) {
                i |= zzdy2 << 14;
            } else {
                i |= (zzdy2 & 127) << 14;
                zzdy2 = zzdy();
                if (zzdy2 >= (byte) 0) {
                    i |= zzdy2 << 21;
                } else {
                    i |= (zzdy2 & 127) << 21;
                    zzdy2 = zzdy();
                    i |= zzdy2 << 28;
                    if (zzdy2 < (byte) 0) {
                        for (int i2 = 0; i2 < 5; i2++) {
                            if (zzdy() >= (byte) 0) {
                                return i;
                            }
                        }
                        throw zzjs.zzhv();
                    }
                }
            }
        }
        return i;
    }

    public final long zzdu() throws IOException {
        int i = 0;
        long j = 0;
        while (i < 64) {
            byte zzdy = zzdy();
            long j2 = j | (((long) (zzdy & 127)) << i);
            if ((zzdy & 128) == 0) {
                return j2;
            }
            i += 7;
            j = j2;
        }
        throw zzjs.zzhv();
    }

    public final int zzdv() throws IOException {
        return (((zzdy() & 255) | ((zzdy() & 255) << 8)) | ((zzdy() & 255) << 16)) | ((zzdy() & 255) << 24);
    }

    private zzjk(byte[] bArr, int i, int i2) {
        this.buffer = bArr;
        this.zzacz = i;
        i2 += i;
        this.zzadb = i2;
        this.zzada = i2;
        this.zzadc = i;
    }

    public final <T extends zzfy<T, ?>> T zza(zzhq<T> zzhq) throws IOException {
        try {
            if (this.zzadd == null) {
                this.zzadd = zzez.zze(this.buffer, this.zzacz, this.zzada);
            }
            int zzds = this.zzadd.zzds();
            int i = this.zzadc - this.zzacz;
            if (zzds > i) {
                throw new IOException(String.format("CodedInputStream read ahead of CodedInputByteBufferNano: %s > %s", new Object[]{Integer.valueOf(zzds), Integer.valueOf(i)}));
            }
            this.zzadd.zzap(i - zzds);
            this.zzadd.zzam(this.zzsg - this.zzsf);
            zzfy zzfy = (zzfy) this.zzadd.zza(zzhq, zzfk.zzel());
            zzal(this.zzsn);
            return zzfy;
        } catch (zzgf e) {
            throw new zzjs("", e);
        }
    }

    public final int zzan(int i) throws zzjs {
        if (i < 0) {
            throw zzjs.zzhu();
        }
        i += this.zzadc;
        int i2 = this.zzso;
        if (i > i2) {
            throw zzjs.zzht();
        }
        this.zzso = i;
        zzdx();
        return i2;
    }

    private final void zzdx() {
        this.zzadb += this.zzsl;
        int i = this.zzadb;
        if (i > this.zzso) {
            this.zzsl = i - this.zzso;
            this.zzadb -= this.zzsl;
            return;
        }
        this.zzsl = 0;
    }

    public final void zzao(int i) {
        this.zzso = i;
        zzdx();
    }

    public final int zzhq() {
        if (this.zzso == Integer.MAX_VALUE) {
            return -1;
        }
        return this.zzso - this.zzadc;
    }

    public final int getPosition() {
        return this.zzadc - this.zzacz;
    }

    public final byte[] zzv(int i, int i2) {
        if (i2 == 0) {
            return zzjw.zzaea;
        }
        byte[] bArr = new byte[i2];
        System.arraycopy(this.buffer, this.zzacz + i, bArr, 0, i2);
        return bArr;
    }

    public final void zzbt(int i) {
        zzw(i, this.zzsn);
    }

    /* Access modifiers changed, original: final */
    public final void zzw(int i, int i2) {
        if (i > this.zzadc - this.zzacz) {
            int i3 = this.zzadc - this.zzacz;
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
            this.zzadc = this.zzacz + i;
            this.zzsn = i2;
        }
    }

    private final byte zzdy() throws IOException {
        if (this.zzadc == this.zzadb) {
            throw zzjs.zzht();
        }
        byte[] bArr = this.buffer;
        int i = this.zzadc;
        this.zzadc = i + 1;
        return bArr[i];
    }

    private final void zzap(int i) throws IOException {
        if (i < 0) {
            throw zzjs.zzhu();
        } else if (this.zzadc + i > this.zzso) {
            zzap(this.zzso - this.zzadc);
            throw zzjs.zzht();
        } else if (i <= this.zzadb - this.zzadc) {
            this.zzadc += i;
        } else {
            throw zzjs.zzht();
        }
    }
}
