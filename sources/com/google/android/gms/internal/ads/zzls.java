package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C;
import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicInteger;

public final class zzls implements zzii {
    private final zzpx zzahz = new zzpx(32);
    private final zzot zzawq;
    private final int zzaym;
    private final zzlq zzayn = new zzlq();
    private final zzlr zzayo = new zzlr();
    private final AtomicInteger zzayp = new AtomicInteger();
    private zzlt zzayq = new zzlt(0, this.zzaym);
    private zzlt zzayr = this.zzayq;
    private zzfs zzays;
    private boolean zzayt;
    private zzfs zzayu;
    private long zzayv;
    private long zzayw;
    private int zzayx = this.zzaym;
    private zzlu zzayy;

    public zzls(zzot zzot) {
        this.zzawq = zzot;
        this.zzaym = zzot.zzgr();
    }

    public final void zzh(boolean z) {
        int andSet = this.zzayp.getAndSet(z ? 0 : 2);
        zzfi();
        this.zzayn.zzfj();
        if (andSet == 2) {
            this.zzays = null;
        }
    }

    public final int zzfk() {
        return this.zzayn.zzfk();
    }

    public final void zzar(int i) {
        this.zzayw = this.zzayn.zzaq(i);
        long j = this.zzayw;
        if (j == this.zzayq.zzayz) {
            zza(this.zzayq);
            this.zzayq = new zzlt(j, this.zzaym);
            this.zzayr = this.zzayq;
            return;
        }
        zzlt zzlt = this.zzayq;
        zzlt zzlt2 = this.zzayq.zzazc;
        while (true) {
            zzlt zzlt3 = zzlt2;
            zzlt2 = zzlt;
            zzlt = zzlt3;
            if (j > zzlt.zzayz) {
                zzlt2 = zzlt.zzazc;
            } else {
                zza(zzlt);
                this.zzayr = zzlt2;
                this.zzayr.zzazc = new zzlt(this.zzayr.zzaop, this.zzaym);
                this.zzayx = (int) (j - this.zzayr.zzayz);
                return;
            }
        }
    }

    public final void disable() {
        if (this.zzayp.getAndSet(2) == 0) {
            zzfi();
        }
    }

    public final boolean zzfm() {
        return this.zzayn.zzfm();
    }

    public final int zzfl() {
        return this.zzayn.zzfl();
    }

    public final zzfs zzfn() {
        return this.zzayn.zzfn();
    }

    public final long zzfc() {
        return this.zzayn.zzfc();
    }

    public final void zzfp() {
        long zzfo = this.zzayn.zzfo();
        if (zzfo != -1) {
            zzad(zzfo);
        }
    }

    public final boolean zze(long j, boolean z) {
        j = this.zzayn.zzd(j, z);
        if (j == -1) {
            return false;
        }
        zzad(j);
        return true;
    }

    public final int zza(zzfu zzfu, zzho zzho, boolean z, boolean z2, long j) {
        zzho zzho2 = zzho;
        switch (this.zzayn.zza(zzfu, zzho2, z, z2, this.zzays, this.zzayo)) {
            case C.RESULT_FORMAT_READ /*-5*/:
                this.zzays = zzfu.zzaad;
                return -5;
            case -4:
                if (!zzho.zzdp()) {
                    int i;
                    int i2;
                    if (zzho2.zzago < j) {
                        zzho2.zzq(Integer.MIN_VALUE);
                    }
                    if (zzho.zzdt()) {
                        zzlr zzlr = this.zzayo;
                        long j2 = zzlr.zzapb;
                        i = 1;
                        this.zzahz.reset(1);
                        zza(j2, this.zzahz.data, 1);
                        long j3 = j2 + 1;
                        int i3 = 0;
                        byte b = this.zzahz.data[0];
                        i2 = (b & 128) != 0 ? 1 : 0;
                        int i4 = b & 127;
                        if (zzho2.zzagn.iv == null) {
                            zzho2.zzagn.iv = new byte[16];
                        }
                        zza(j3, zzho2.zzagn.iv, i4);
                        long j4 = j3 + ((long) i4);
                        if (i2 != 0) {
                            this.zzahz.reset(2);
                            zza(j4, this.zzahz.data, 2);
                            j3 = j4 + 2;
                            i = this.zzahz.readUnsignedShort();
                        } else {
                            j3 = j4;
                        }
                        int i5 = i;
                        int[] iArr = zzho2.zzagn.numBytesOfClearData;
                        if (iArr == null || iArr.length < i5) {
                            iArr = new int[i5];
                        }
                        int[] iArr2 = iArr;
                        iArr = zzho2.zzagn.numBytesOfEncryptedData;
                        if (iArr == null || iArr.length < i5) {
                            iArr = new int[i5];
                        }
                        int[] iArr3 = iArr;
                        if (i2 != 0) {
                            i4 = i5 * 6;
                            this.zzahz.reset(i4);
                            zza(j3, this.zzahz.data, i4);
                            long j5 = j3 + ((long) i4);
                            this.zzahz.setPosition(0);
                            while (i3 < i5) {
                                iArr2[i3] = this.zzahz.readUnsignedShort();
                                iArr3[i3] = this.zzahz.zzhg();
                                i3++;
                            }
                            j3 = j5;
                        } else {
                            iArr2[0] = 0;
                            iArr3[0] = zzlr.size - ((int) (j3 - zzlr.zzapb));
                        }
                        zzij zzij = zzlr.zzajw;
                        zzho2.zzagn.set(i5, iArr2, iArr3, zzij.zzahh, zzho2.zzagn.iv, zzij.zzahg);
                        i4 = (int) (j3 - zzlr.zzapb);
                        zzlr.zzapb += (long) i4;
                        zzlr.size -= i4;
                    }
                    zzho2.zzs(this.zzayo.size);
                    long j6 = this.zzayo.zzapb;
                    ByteBuffer byteBuffer = zzho2.zzdd;
                    i2 = this.zzayo.size;
                    zzad(j6);
                    while (i2 > 0) {
                        i = (int) (j6 - this.zzayq.zzayz);
                        int min = Math.min(i2, this.zzaym - i);
                        zzos zzos = this.zzayq.zzazb;
                        byteBuffer.put(zzos.data, zzos.zzbf(i), min);
                        long j7 = j6 + ((long) min);
                        i2 -= min;
                        if (j7 == this.zzayq.zzaop) {
                            this.zzawq.zza(zzos);
                            this.zzayq = this.zzayq.zzfs();
                        }
                        j6 = j7;
                    }
                    zzad(this.zzayo.zzayl);
                }
                return -4;
            case -3:
                return -3;
            default:
                throw new IllegalStateException();
        }
    }

    private final void zza(long j, byte[] bArr, int i) {
        zzad(j);
        int i2 = 0;
        while (i2 < i) {
            int i3 = (int) (j - this.zzayq.zzayz);
            int min = Math.min(i - i2, this.zzaym - i3);
            zzos zzos = this.zzayq.zzazb;
            System.arraycopy(zzos.data, zzos.zzbf(i3), bArr, i2, min);
            long j2 = j + ((long) min);
            i2 += min;
            if (j2 == this.zzayq.zzaop) {
                this.zzawq.zza(zzos);
                this.zzayq = this.zzayq.zzfs();
            }
            j = j2;
        }
    }

    private final void zzad(long j) {
        while (j >= this.zzayq.zzaop) {
            this.zzawq.zza(this.zzayq.zzazb);
            this.zzayq = this.zzayq.zzfs();
        }
    }

    public final void zza(zzlu zzlu) {
        this.zzayy = zzlu;
    }

    public final void zzae(long j) {
        if (this.zzayv != j) {
            this.zzayv = j;
            this.zzayt = true;
        }
    }

    public final void zzf(zzfs zzfs) {
        long j = this.zzayv;
        zzfs zzj = zzfs == null ? null : (j == 0 || zzfs.zzzy == Long.MAX_VALUE) ? zzfs : zzfs.zzj(zzfs.zzzy + j);
        boolean zzh = this.zzayn.zzh(zzj);
        this.zzayu = zzfs;
        this.zzayt = false;
        if (this.zzayy != null && zzh) {
            this.zzayy.zzg(zzj);
        }
    }

    public final int zza(zzia zzia, int i, boolean z) throws IOException, InterruptedException {
        int read;
        if (zzfq()) {
            try {
                i = zzas(i);
                zzos zzos = this.zzayr.zzazb;
                read = zzia.read(zzos.data, zzos.zzbf(this.zzayx), i);
                if (read != -1) {
                    this.zzayx += read;
                    this.zzayw += (long) read;
                    zzfr();
                    return read;
                } else if (z) {
                    return -1;
                } else {
                    throw new EOFException();
                }
            } finally {
                zzfr();
            }
        } else {
            read = zzia.zzv(i);
            if (read != -1) {
                return read;
            }
            if (z) {
                return -1;
            }
            throw new EOFException();
        }
    }

    public final void zza(zzpx zzpx, int i) {
        if (zzfq()) {
            while (i > 0) {
                int zzas = zzas(i);
                zzos zzos = this.zzayr.zzazb;
                zzpx.zze(zzos.data, zzos.zzbf(this.zzayx), zzas);
                this.zzayx += zzas;
                this.zzayw += (long) zzas;
                i -= zzas;
            }
            zzfr();
            return;
        }
        zzpx.zzbl(i);
    }

    public final void zza(long j, int i, int i2, int i3, zzij zzij) {
        long j2 = j;
        if (this.zzayt) {
            zzf(this.zzayu);
        }
        if (zzfq()) {
            try {
                int i4 = i2;
                this.zzayn.zza(j2 + this.zzayv, i, (this.zzayw - ((long) i4)) - ((long) i3), i4, zzij);
                zzfr();
            } catch (Throwable th) {
                Throwable th2 = th;
                zzfr();
            }
        } else {
            this.zzayn.zzac(j2);
        }
    }

    private final boolean zzfq() {
        return this.zzayp.compareAndSet(0, 1);
    }

    private final void zzfr() {
        if (!this.zzayp.compareAndSet(1, 0)) {
            zzfi();
        }
    }

    private final void zzfi() {
        this.zzayn.zzfi();
        zza(this.zzayq);
        this.zzayq = new zzlt(0, this.zzaym);
        this.zzayr = this.zzayq;
        this.zzayw = 0;
        this.zzayx = this.zzaym;
        this.zzawq.zzo();
    }

    private final void zza(zzlt zzlt) {
        if (zzlt.zzaza) {
            zzos[] zzosArr = new zzos[(this.zzayr.zzaza + (((int) (this.zzayr.zzayz - zzlt.zzayz)) / this.zzaym))];
            for (int i = 0; i < zzosArr.length; i++) {
                zzosArr[i] = zzlt.zzazb;
                zzlt = zzlt.zzfs();
            }
            this.zzawq.zza(zzosArr);
        }
    }

    private final int zzas(int i) {
        if (this.zzayx == this.zzaym) {
            this.zzayx = 0;
            if (this.zzayr.zzaza) {
                this.zzayr = this.zzayr.zzazc;
            }
            zzlt zzlt = this.zzayr;
            zzos zzgq = this.zzawq.zzgq();
            zzlt zzlt2 = new zzlt(this.zzayr.zzaop, this.zzaym);
            zzlt.zzazb = zzgq;
            zzlt.zzazc = zzlt2;
            zzlt.zzaza = true;
        }
        return Math.min(i, this.zzaym - this.zzayx);
    }
}
