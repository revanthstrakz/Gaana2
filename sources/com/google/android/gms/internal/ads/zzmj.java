package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public final class zzmj<T extends zzml> implements zzlv, zzlw, zzpg<zzme> {
    private final int zzawn;
    private final zzpf zzawt = new zzpf("Loader:ChunkSampleStream");
    long zzaxj;
    private long zzaxk;
    boolean zzaxm;
    private final int zzbac;
    private final int[] zzbad;
    private final boolean[] zzbae;
    private final T zzbaf;
    private final zzlx<zzmj<T>> zzbag;
    private final zzkt zzbah;
    private final zzmi zzbai = new zzmi();
    private final LinkedList<zzmc> zzbaj = new LinkedList();
    private final List<zzmc> zzbak = Collections.unmodifiableList(this.zzbaj);
    private final zzls zzbal;
    private final zzls[] zzbam;
    private final zzmd zzban;
    private zzfs zzbao;

    public zzmj(int i, int[] iArr, T t, zzlx<zzmj<T>> zzlx, zzot zzot, long j, int i2, zzkt zzkt) {
        int i3;
        this.zzbac = i;
        this.zzbad = iArr;
        this.zzbaf = t;
        this.zzbag = zzlx;
        this.zzbah = zzkt;
        this.zzawn = i2;
        int i4 = 0;
        if (iArr == null) {
            i3 = 0;
        } else {
            i3 = iArr.length;
        }
        this.zzbam = new zzls[i3];
        this.zzbae = new boolean[i3];
        i2 = i3 + 1;
        int[] iArr2 = new int[i2];
        zzls[] zzlsArr = new zzls[i2];
        this.zzbal = new zzls(zzot);
        iArr2[0] = i;
        zzlsArr[0] = this.zzbal;
        while (i4 < i3) {
            zzls zzls = new zzls(zzot);
            this.zzbam[i4] = zzls;
            int i5 = i4 + 1;
            zzlsArr[i5] = zzls;
            iArr2[i5] = iArr[i4];
            i4 = i5;
        }
        this.zzban = new zzmd(iArr2, zzlsArr);
        this.zzaxk = j;
        this.zzaxj = j;
    }

    public final void zzaf(long j) {
        for (int i = 0; i < this.zzbam.length; i++) {
            if (!this.zzbae[i]) {
                this.zzbam[i].zze(j, true);
            }
        }
    }

    public final zzmk zza(long j, int i) {
        for (int i2 = 0; i2 < this.zzbam.length; i2++) {
            if (this.zzbad[i2] == i) {
                zzpo.checkState(this.zzbae[i2] ^ 1);
                this.zzbae[i2] = true;
                this.zzbam[i2].zze(j, true);
                return new zzmk(this, this, this.zzbam[i2], i2);
            }
        }
        throw new IllegalStateException();
    }

    public final T zzfy() {
        return this.zzbaf;
    }

    public final long zzez() {
        if (this.zzaxm) {
            return Long.MIN_VALUE;
        }
        if (zzfd()) {
            return this.zzaxk;
        }
        long j = this.zzaxj;
        zzmc zzmc = (zzmc) this.zzbaj.getLast();
        if (!zzmc.zzga()) {
            zzmc = this.zzbaj.size() > 1 ? (zzmc) this.zzbaj.get(this.zzbaj.size() - 2) : null;
        }
        if (zzmc != null) {
            j = Math.max(j, zzmc.zzazt);
        }
        return Math.max(j, this.zzbal.zzfc());
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0052  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0022 A:{LOOP_START, LOOP:0: B:11:0x0022->B:15:0x003e} */
    public final void zzag(long r7) {
        /*
        r6 = this;
        r6.zzaxj = r7;
        r0 = r6.zzfd();
        r1 = 0;
        r2 = 1;
        if (r0 != 0) goto L_0x001f;
    L_0x000a:
        r0 = r6.zzbal;
        r3 = r6.zzeu();
        r5 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1));
        if (r5 >= 0) goto L_0x0016;
    L_0x0014:
        r3 = r2;
        goto L_0x0017;
    L_0x0016:
        r3 = r1;
    L_0x0017:
        r0 = r0.zze(r7, r3);
        if (r0 == 0) goto L_0x001f;
    L_0x001d:
        r0 = r2;
        goto L_0x0020;
    L_0x001f:
        r0 = r1;
    L_0x0020:
        if (r0 == 0) goto L_0x0052;
    L_0x0022:
        r0 = r6.zzbaj;
        r0 = r0.size();
        if (r0 <= r2) goto L_0x0044;
    L_0x002a:
        r0 = r6.zzbaj;
        r0 = r0.get(r2);
        r0 = (com.google.android.gms.internal.ads.zzmc) r0;
        r0 = r0.zzav(r1);
        r3 = r6.zzbal;
        r3 = r3.zzfl();
        if (r0 > r3) goto L_0x0044;
    L_0x003e:
        r0 = r6.zzbaj;
        r0.removeFirst();
        goto L_0x0022;
    L_0x0044:
        r0 = r6.zzbam;
        r3 = r0.length;
    L_0x0047:
        if (r1 >= r3) goto L_0x0051;
    L_0x0049:
        r4 = r0[r1];
        r4.zze(r7, r2);
        r1 = r1 + 1;
        goto L_0x0047;
    L_0x0051:
        return;
    L_0x0052:
        r6.zzaxk = r7;
        r6.zzaxm = r1;
        r7 = r6.zzbaj;
        r7.clear();
        r7 = r6.zzawt;
        r7 = r7.isLoading();
        if (r7 == 0) goto L_0x0069;
    L_0x0063:
        r7 = r6.zzawt;
        r7.zzgy();
        return;
    L_0x0069:
        r7 = r6.zzbal;
        r7.zzh(r2);
        r7 = r6.zzbam;
        r8 = r7.length;
    L_0x0071:
        if (r1 >= r8) goto L_0x007b;
    L_0x0073:
        r0 = r7[r1];
        r0.zzh(r2);
        r1 = r1 + 1;
        goto L_0x0071;
    L_0x007b:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzmj.zzag(long):void");
    }

    public final void release() {
        this.zzbal.disable();
        for (zzls disable : this.zzbam) {
            disable.disable();
        }
        this.zzawt.zza(null);
    }

    public final boolean isReady() {
        return this.zzaxm || (!zzfd() && this.zzbal.zzfm());
    }

    public final void zzev() throws IOException {
        this.zzawt.zzbi(Integer.MIN_VALUE);
        if (!this.zzawt.isLoading()) {
            this.zzbaf.zzev();
        }
    }

    public final int zzb(zzfu zzfu, zzho zzho, boolean z) {
        if (zzfd()) {
            return -3;
        }
        int zzfl = this.zzbal.zzfl();
        if (!this.zzbaj.isEmpty()) {
            while (this.zzbaj.size() > 1 && ((zzmc) this.zzbaj.get(1)).zzav(0) <= zzfl) {
                this.zzbaj.removeFirst();
            }
            zzmc zzmc = (zzmc) this.zzbaj.getFirst();
            zzfs zzfs = zzmc.zzazp;
            if (!zzfs.equals(this.zzbao)) {
                this.zzbah.zzb(this.zzbac, zzfs, zzmc.zzazq, zzmc.zzazr, zzmc.zzazs);
            }
            this.zzbao = zzfs;
        }
        return this.zzbal.zza(zzfu, zzho, z, this.zzaxm, this.zzaxj);
    }

    public final void zzz(long j) {
        if (!this.zzaxm || j <= this.zzbal.zzfc()) {
            this.zzbal.zze(j, true);
        } else {
            this.zzbal.zzfp();
        }
    }

    public final boolean zzy(long j) {
        if (this.zzaxm || this.zzawt.isLoading()) {
            return false;
        }
        this.zzbaf.zza(this.zzbaj.isEmpty() ? null : (zzmo) this.zzbaj.getLast(), this.zzaxk != C.TIME_UNSET ? this.zzaxk : j, this.zzbai);
        boolean z = this.zzbai.zzbab;
        zzme zzme = this.zzbai.zzbaa;
        zzmi zzmi = this.zzbai;
        zzmi.zzbaa = null;
        zzmi.zzbab = false;
        if (z) {
            this.zzaxk = C.TIME_UNSET;
            this.zzaxm = true;
            return true;
        } else if (zzme == null) {
            return false;
        } else {
            if (zzme instanceof zzmc) {
                this.zzaxk = C.TIME_UNSET;
                zzmc zzmc = (zzmc) zzme;
                zzmc.zza(this.zzban);
                this.zzbaj.add(zzmc);
            }
            this.zzbah.zzb(zzme.zzazo, zzme.type, this.zzbac, zzme.zzazp, zzme.zzazq, zzme.zzazr, zzme.zzazs, zzme.zzazt, this.zzawt.zza(zzme, this, this.zzawn));
            return true;
        }
    }

    public final long zzeu() {
        if (zzfd()) {
            return this.zzaxk;
        }
        return this.zzaxm ? Long.MIN_VALUE : ((zzmc) this.zzbaj.getLast()).zzazt;
    }

    /* Access modifiers changed, original: final */
    public final boolean zzfd() {
        return this.zzaxk != C.TIME_UNSET;
    }

    public final /* synthetic */ void zza(zzpi zzpi, long j, long j2) {
        zzme zzme = (zzme) zzpi;
        this.zzbaf.zza(zzme);
        this.zzbah.zzc(zzme.zzazo, zzme.type, this.zzbac, zzme.zzazp, zzme.zzazq, zzme.zzazr, zzme.zzazs, zzme.zzazt, j, j2, zzme.zzfv());
        this.zzbag.zza(this);
    }
}
