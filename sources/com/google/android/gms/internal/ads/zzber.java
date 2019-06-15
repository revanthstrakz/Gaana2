package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.gms.common.util.VisibleForTesting;

@zzark
public final class zzber implements zzfw {
    private int zzbfz;
    private final zzpa zzevj;
    private long zzevk;
    private long zzevl;
    private long zzevm;
    private long zzevn;
    private boolean zzevo;

    zzber() {
        this(15000, 30000, 2500, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
    }

    private zzber(int i, int i2, long j, long j2) {
        this.zzevj = new zzpa(true, 65536);
        this.zzevk = 15000000;
        this.zzevl = 30000000;
        this.zzevm = 2500000;
        this.zzevn = 5000000;
    }

    public final void zzcg() {
        zzh(false);
    }

    public final void zza(zzfz[] zzfzArr, zzma zzma, zzoo zzoo) {
        int i = 0;
        this.zzbfz = 0;
        while (i < zzfzArr.length) {
            if (zzoo.zzbe(i) != null) {
                this.zzbfz += zzqe.zzbq(zzfzArr[i].getTrackType());
            }
            i++;
        }
        this.zzevj.zzbh(this.zzbfz);
    }

    public final void onStopped() {
        zzh(true);
    }

    public final void zzch() {
        zzh(true);
    }

    public final zzot zzci() {
        return this.zzevj;
    }

    public final synchronized boolean zzc(long j, boolean z) {
        boolean z2;
        long j2;
        if (z) {
            try {
                j2 = this.zzevn;
            } catch (Throwable th) {
            }
        } else {
            j2 = this.zzevm;
        }
        if (j2 <= 0 || j >= j2) {
            z2 = true;
        } else {
            z2 = false;
        }
        return z2;
    }

    public final synchronized boolean zzk(long j) {
        boolean z = false;
        boolean z2 = j > this.zzevl ? false : j < this.zzevk ? true : true;
        boolean z3 = this.zzevj.zzgv() >= this.zzbfz;
        if (z2 || (z2 && this.zzevo && !z3)) {
            z = true;
        }
        this.zzevo = z;
        return this.zzevo;
    }

    public final synchronized void zzdf(int i) {
        this.zzevk = ((long) i) * 1000;
    }

    public final synchronized void zzdg(int i) {
        this.zzevl = ((long) i) * 1000;
    }

    public final synchronized void zzdb(int i) {
        this.zzevm = ((long) i) * 1000;
    }

    public final synchronized void zzdc(int i) {
        this.zzevn = ((long) i) * 1000;
    }

    @VisibleForTesting
    private final void zzh(boolean z) {
        this.zzbfz = 0;
        this.zzevo = false;
        if (z) {
            this.zzevj.reset();
        }
    }
}
