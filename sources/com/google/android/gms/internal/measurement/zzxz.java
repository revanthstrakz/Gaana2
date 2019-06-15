package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzxz {
    private final byte[] buffer;
    private int zzbty;
    private int zzbtz = 64;
    private int zzbua = 67108864;
    private int zzbue;
    private int zzbug;
    private int zzbuh = Integer.MAX_VALUE;
    private final int zzcem;
    private final int zzcen;
    private int zzceo;
    private int zzcep;
    private zztq zzceq;

    public static zzxz zzn(byte[] bArr) {
        return zzj(bArr, 0, bArr.length);
    }

    public static zzxz zzj(byte[] bArr, int i, int i2) {
        return new zzxz(bArr, 0, i2);
    }

    public final int zzuj() throws IOException {
        if (this.zzcep == this.zzceo) {
            this.zzbug = 0;
            return 0;
        }
        this.zzbug = zzvb();
        if (this.zzbug != 0) {
            return this.zzbug;
        }
        throw new zzyh("Protocol message contained an invalid tag (zero).");
    }

    public final void zzap(int i) throws zzyh {
        if (this.zzbug != i) {
            throw new zzyh("Protocol message end-group tag did not match expected tag.");
        }
    }

    public final boolean zzaq(int i) throws IOException {
        switch (i & 7) {
            case 0:
                zzvb();
                return true;
            case 1:
                zzve();
                return true;
            case 2:
                zzau(zzvb());
                return true;
            case 3:
                break;
            case 4:
                return false;
            case 5:
                zzvd();
                return true;
            default:
                throw new zzyh("Protocol message tag had invalid wire type.");
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

    public final boolean zzup() throws IOException {
        return zzvb() != 0;
    }

    public final String readString() throws IOException {
        int zzvb = zzvb();
        if (zzvb < 0) {
            throw zzyh.zzze();
        } else if (zzvb > this.zzceo - this.zzcep) {
            throw zzyh.zzzd();
        } else {
            String str = new String(this.buffer, this.zzcep, zzvb, zzyg.UTF_8);
            this.zzcep += zzvb;
            return str;
        }
    }

    public final void zza(zzyi zzyi, int i) throws IOException {
        if (this.zzbty >= this.zzbtz) {
            throw zzyh.zzzg();
        }
        this.zzbty++;
        zzyi.zza(this);
        zzap((i << 3) | 4);
        this.zzbty--;
    }

    public final void zza(zzyi zzyi) throws IOException {
        int zzvb = zzvb();
        if (this.zzbty >= this.zzbtz) {
            throw zzyh.zzzg();
        }
        zzvb = zzas(zzvb);
        this.zzbty++;
        zzyi.zza(this);
        zzap(0);
        this.zzbty--;
        zzat(zzvb);
    }

    public final int zzvb() throws IOException {
        byte zzvg = zzvg();
        if (zzvg >= (byte) 0) {
            return zzvg;
        }
        int i = zzvg & 127;
        byte zzvg2 = zzvg();
        if (zzvg2 >= (byte) 0) {
            i |= zzvg2 << 7;
        } else {
            i |= (zzvg2 & 127) << 7;
            zzvg2 = zzvg();
            if (zzvg2 >= (byte) 0) {
                i |= zzvg2 << 14;
            } else {
                i |= (zzvg2 & 127) << 14;
                zzvg2 = zzvg();
                if (zzvg2 >= (byte) 0) {
                    i |= zzvg2 << 21;
                } else {
                    i |= (zzvg2 & 127) << 21;
                    zzvg2 = zzvg();
                    i |= zzvg2 << 28;
                    if (zzvg2 < (byte) 0) {
                        for (int i2 = 0; i2 < 5; i2++) {
                            if (zzvg() >= (byte) 0) {
                                return i;
                            }
                        }
                        throw zzyh.zzzf();
                    }
                }
            }
        }
        return i;
    }

    public final long zzvc() throws IOException {
        int i = 0;
        long j = 0;
        while (i < 64) {
            byte zzvg = zzvg();
            long j2 = j | (((long) (zzvg & 127)) << i);
            if ((zzvg & 128) == 0) {
                return j2;
            }
            i += 7;
            j = j2;
        }
        throw zzyh.zzzf();
    }

    public final int zzvd() throws IOException {
        return (((zzvg() & 255) | ((zzvg() & 255) << 8)) | ((zzvg() & 255) << 16)) | ((zzvg() & 255) << 24);
    }

    public final long zzve() throws IOException {
        return (((((((((long) zzvg()) & 255) | ((((long) zzvg()) & 255) << 8)) | ((((long) zzvg()) & 255) << 16)) | ((((long) zzvg()) & 255) << 24)) | ((((long) zzvg()) & 255) << 32)) | ((((long) zzvg()) & 255) << 40)) | ((((long) zzvg()) & 255) << 48)) | ((((long) zzvg()) & 255) << 56);
    }

    private zzxz(byte[] bArr, int i, int i2) {
        this.buffer = bArr;
        this.zzcem = i;
        i2 += i;
        this.zzceo = i2;
        this.zzcen = i2;
        this.zzcep = i;
    }

    private final zztq zzyx() throws IOException {
        if (this.zzceq == null) {
            this.zzceq = zztq.zzd(this.buffer, this.zzcem, this.zzcen);
        }
        int zzva = this.zzceq.zzva();
        int i = this.zzcep - this.zzcem;
        if (zzva > i) {
            throw new IOException(String.format("CodedInputStream read ahead of CodedInputByteBufferNano: %s > %s", new Object[]{Integer.valueOf(zzva), Integer.valueOf(i)}));
        }
        this.zzceq.zzau(i - zzva);
        this.zzceq.zzar(this.zzbtz - this.zzbty);
        return this.zzceq;
    }

    public final <T extends zzuo<T, ?>> T zza(zzwf<T> zzwf) throws IOException {
        try {
            zzuo zzuo = (zzuo) zzyx().zza(zzwf, zzub.zzvs());
            zzaq(this.zzbug);
            return zzuo;
        } catch (zzuv e) {
            throw new zzyh("", e);
        }
    }

    public final int zzas(int i) throws zzyh {
        if (i < 0) {
            throw zzyh.zzze();
        }
        i += this.zzcep;
        int i2 = this.zzbuh;
        if (i > i2) {
            throw zzyh.zzzd();
        }
        this.zzbuh = i;
        zzvf();
        return i2;
    }

    private final void zzvf() {
        this.zzceo += this.zzbue;
        int i = this.zzceo;
        if (i > this.zzbuh) {
            this.zzbue = i - this.zzbuh;
            this.zzceo -= this.zzbue;
            return;
        }
        this.zzbue = 0;
    }

    public final void zzat(int i) {
        this.zzbuh = i;
        zzvf();
    }

    public final int zzyy() {
        if (this.zzbuh == Integer.MAX_VALUE) {
            return -1;
        }
        return this.zzbuh - this.zzcep;
    }

    public final int getPosition() {
        return this.zzcep - this.zzcem;
    }

    public final byte[] zzs(int i, int i2) {
        if (i2 == 0) {
            return zzyl.zzcfo;
        }
        byte[] bArr = new byte[i2];
        System.arraycopy(this.buffer, this.zzcem + i, bArr, 0, i2);
        return bArr;
    }

    public final void zzcb(int i) {
        zzt(i, this.zzbug);
    }

    /* Access modifiers changed, original: final */
    public final void zzt(int i, int i2) {
        if (i > this.zzcep - this.zzcem) {
            int i3 = this.zzcep - this.zzcem;
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
            this.zzcep = this.zzcem + i;
            this.zzbug = i2;
        }
    }

    private final byte zzvg() throws IOException {
        if (this.zzcep == this.zzceo) {
            throw zzyh.zzzd();
        }
        byte[] bArr = this.buffer;
        int i = this.zzcep;
        this.zzcep = i + 1;
        return bArr[i];
    }

    private final void zzau(int i) throws IOException {
        if (i < 0) {
            throw zzyh.zzze();
        } else if (this.zzcep + i > this.zzbuh) {
            zzau(this.zzbuh - this.zzcep);
            throw zzyh.zzzd();
        } else if (i <= this.zzceo - this.zzcep) {
            this.zzcep += i;
        } else {
            throw zzyh.zzzd();
        }
    }
}
