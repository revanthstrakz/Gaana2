package com.google.android.gms.internal.ads;

import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;

public final class zzhx implements zzia {
    private static final byte[] zzagx = new byte[4096];
    private final zzov zzagy;
    private final long zzagz;
    private long zzaha;
    private byte[] zzahb = new byte[65536];
    private int zzahc;
    private int zzahd;

    public zzhx(zzov zzov, long j, long j2) {
        this.zzagy = zzov;
        this.zzaha = j;
        this.zzagz = j2;
    }

    public final int read(byte[] bArr, int i, int i2) throws IOException, InterruptedException {
        int zzb = zzb(bArr, i, i2);
        if (zzb == 0) {
            zzb = zza(bArr, i, i2, 0, true);
        }
        zzaa(zzb);
        return zzb;
    }

    public final boolean zza(byte[] bArr, int i, int i2, boolean z) throws IOException, InterruptedException {
        int zzb = zzb(bArr, i, i2);
        while (zzb < i2 && zzb != -1) {
            zzb = zza(bArr, i, i2, zzb, z);
        }
        zzaa(zzb);
        return zzb != -1;
    }

    public final void readFully(byte[] bArr, int i, int i2) throws IOException, InterruptedException {
        zza(bArr, i, i2, false);
    }

    public final int zzv(int i) throws IOException, InterruptedException {
        int zzy = zzy(i);
        if (zzy == 0) {
            zzy = zza(zzagx, 0, Math.min(i, zzagx.length), 0, true);
        }
        zzaa(zzy);
        return zzy;
    }

    public final void zzw(int i) throws IOException, InterruptedException {
        int zzy = zzy(i);
        while (zzy < i && zzy != -1) {
            zzy = zza(zzagx, -zzy, Math.min(i, zzagx.length + zzy), zzy, false);
        }
        zzaa(zzy);
    }

    public final void zza(byte[] bArr, int i, int i2) throws IOException, InterruptedException {
        if (zzd(i2, false)) {
            System.arraycopy(this.zzahb, this.zzahc - i2, bArr, i, i2);
        }
    }

    private final boolean zzd(int i, boolean z) throws IOException, InterruptedException {
        int i2 = this.zzahc + i;
        if (i2 > this.zzahb.length) {
            this.zzahb = Arrays.copyOf(this.zzahb, zzqe.zzd(this.zzahb.length << 1, 65536 + i2, i2 + 524288));
        }
        int min = Math.min(this.zzahd - this.zzahc, i);
        while (min < i) {
            min = zza(this.zzahb, this.zzahc, i, min, false);
            if (min == -1) {
                return false;
            }
        }
        this.zzahc += i;
        this.zzahd = Math.max(this.zzahd, this.zzahc);
        return true;
    }

    public final void zzx(int i) throws IOException, InterruptedException {
        zzd(i, false);
    }

    public final void zzdx() {
        this.zzahc = 0;
    }

    public final long getPosition() {
        return this.zzaha;
    }

    public final long getLength() {
        return this.zzagz;
    }

    private final int zzy(int i) {
        i = Math.min(this.zzahd, i);
        zzz(i);
        return i;
    }

    private final int zzb(byte[] bArr, int i, int i2) {
        if (this.zzahd == 0) {
            return 0;
        }
        i2 = Math.min(this.zzahd, i2);
        System.arraycopy(this.zzahb, 0, bArr, i, i2);
        zzz(i2);
        return i2;
    }

    private final void zzz(int i) {
        this.zzahd -= i;
        this.zzahc = 0;
        Object obj = this.zzahb;
        if (this.zzahd < this.zzahb.length - 524288) {
            obj = new byte[(this.zzahd + 65536)];
        }
        System.arraycopy(this.zzahb, i, obj, 0, this.zzahd);
        this.zzahb = obj;
    }

    private final int zza(byte[] bArr, int i, int i2, int i3, boolean z) throws InterruptedException, IOException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        int read = this.zzagy.read(bArr, i + i3, i2 - i3);
        if (read != -1) {
            return i3 + read;
        }
        if (i3 == 0 && z) {
            return -1;
        }
        throw new EOFException();
    }

    private final void zzaa(int i) {
        if (i != -1) {
            this.zzaha += (long) i;
        }
    }
}
