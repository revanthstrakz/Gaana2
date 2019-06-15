package com.google.android.gms.internal.ads;

import android.util.Log;

final class zzfo {
    public final int index;
    private final zzfz[] zzwu;
    private final zzop zzwv;
    private final zzga[] zzxs;
    private final zzfw zzxt;
    private final zzlo zzxz;
    public final zzlm zzym;
    public final Object zzyn;
    public final zzlv[] zzyo;
    private final boolean[] zzyp;
    public final long zzyq;
    public int zzyr;
    public long zzys;
    public boolean zzyt;
    public boolean zzyu;
    public boolean zzyv;
    public zzfo zzyw;
    public zzor zzyx;
    private zzor zzyy;

    public zzfo(zzfz[] zzfzArr, zzga[] zzgaArr, long j, zzop zzop, zzfw zzfw, zzlo zzlo, Object obj, int i, int i2, boolean z, long j2) {
        this.zzwu = zzfzArr;
        this.zzxs = zzgaArr;
        this.zzyq = j;
        this.zzwv = zzop;
        this.zzxt = zzfw;
        this.zzxz = zzlo;
        this.zzyn = zzpo.checkNotNull(obj);
        this.index = i;
        this.zzyr = i2;
        this.zzyt = z;
        this.zzys = j2;
        this.zzyo = new zzlv[zzfzArr.length];
        this.zzyp = new boolean[zzfzArr.length];
        this.zzym = zzlo.zza(i2, zzfw.zzci());
    }

    public final long zzcb() {
        return this.zzyq - this.zzys;
    }

    public final void zzc(int i, boolean z) {
        this.zzyr = i;
        this.zzyt = z;
    }

    public final boolean zzcc() {
        return this.zzyu && (!this.zzyv || this.zzym.zzez() == Long.MIN_VALUE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002b  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x002a A:{RETURN} */
    public final boolean zzcd() throws com.google.android.gms.internal.ads.zzff {
        /*
        r6 = this;
        r0 = r6.zzwv;
        r1 = r6.zzxs;
        r2 = r6.zzym;
        r2 = r2.zzex();
        r0 = r0.zza(r1, r2);
        r1 = r6.zzyy;
        r2 = 1;
        r3 = 0;
        if (r1 != 0) goto L_0x0016;
    L_0x0014:
        r1 = r3;
        goto L_0x0028;
    L_0x0016:
        r4 = r3;
    L_0x0017:
        r5 = r0.zzbfl;
        r5 = r5.length;
        if (r4 >= r5) goto L_0x0027;
    L_0x001d:
        r5 = r0.zza(r1, r4);
        if (r5 != 0) goto L_0x0024;
    L_0x0023:
        goto L_0x0014;
    L_0x0024:
        r4 = r4 + 1;
        goto L_0x0017;
    L_0x0027:
        r1 = r2;
    L_0x0028:
        if (r1 == 0) goto L_0x002b;
    L_0x002a:
        return r3;
    L_0x002b:
        r6.zzyx = r0;
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzfo.zzcd():boolean");
    }

    public final long zzb(long j, boolean z) {
        return zza(j, false, new boolean[this.zzwu.length]);
    }

    public final long zza(long j, boolean z, boolean[] zArr) {
        zzoo zzoo = this.zzyx.zzbfl;
        int i = 0;
        while (true) {
            boolean z2 = true;
            if (i >= zzoo.length) {
                break;
            }
            boolean[] zArr2 = this.zzyp;
            if (z || !this.zzyx.zza(this.zzyy, i)) {
                z2 = false;
            }
            zArr2[i] = z2;
            i++;
        }
        long zza = this.zzym.zza(zzoo.zzgp(), this.zzyp, this.zzyo, zArr, j);
        this.zzyy = this.zzyx;
        this.zzyv = false;
        for (int i2 = 0; i2 < this.zzyo.length; i2++) {
            if (this.zzyo[i2] != null) {
                zzpo.checkState(zzoo.zzbe(i2) != null);
                this.zzyv = true;
            } else {
                zzpo.checkState(zzoo.zzbe(i2) == null);
            }
        }
        this.zzxt.zza(this.zzwu, this.zzyx.zzbfk, zzoo);
        return zza;
    }

    public final void release() {
        try {
            this.zzxz.zzb(this.zzym);
        } catch (RuntimeException e) {
            Log.e("ExoPlayerImplInternal", "Period release failed.", e);
        }
    }
}
