package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.os.Handler;
import android.util.SparseArray;
import com.comscore.streaming.Constants;
import com.google.android.exoplayer2.C;
import java.io.IOException;

final class zzlc implements zzib, zzlm, zzlu, zzpg<zzlh> {
    private final Handler handler;
    private final Uri uri;
    private long zzaan;
    private final zzov zzagy;
    private final int zzawn;
    private final zzll zzawo;
    private final zzlp zzawp;
    private final zzot zzawq;
    private final String zzawr;
    private final long zzaws;
    private final zzpf zzawt = new zzpf("Loader:ExtractorMediaPeriod");
    private final zzli zzawu;
    private final zzpq zzawv;
    private final Runnable zzaww;
    private final Runnable zzawx;
    private final SparseArray<zzls> zzawy;
    private zzln zzawz;
    private zzig zzaxa;
    private boolean zzaxb;
    private boolean zzaxc;
    private boolean zzaxd;
    private int zzaxe;
    private zzma zzaxf;
    private boolean[] zzaxg;
    private boolean[] zzaxh;
    private boolean zzaxi;
    private long zzaxj;
    private long zzaxk;
    private int zzaxl;
    private boolean zzaxm;
    private long zzcc;
    private final Handler zzwx;
    private boolean zzyb;
    private boolean zzyu;

    public zzlc(Uri uri, zzov zzov, zzhz[] zzhzArr, int i, Handler handler, zzll zzll, zzlp zzlp, zzot zzot, String str, int i2) {
        this.uri = uri;
        this.zzagy = zzov;
        this.zzawn = i;
        this.zzwx = handler;
        this.zzawo = zzll;
        this.zzawp = zzlp;
        this.zzawq = zzot;
        this.zzawr = str;
        this.zzaws = (long) i2;
        this.zzawu = new zzli(zzhzArr, this);
        this.zzawv = new zzpq();
        this.zzaww = new zzld(this);
        this.zzawx = new zzle(this);
        this.handler = new Handler();
        this.zzaxk = C.TIME_UNSET;
        this.zzawy = new SparseArray();
        this.zzcc = -1;
    }

    public final void zzaa(long j) {
    }

    public final void release() {
        this.zzawt.zza(new zzlf(this, this.zzawu));
        this.handler.removeCallbacksAndMessages(null);
        this.zzyb = true;
    }

    public final void zza(zzln zzln, long j) {
        this.zzawz = zzln;
        this.zzawv.zzgz();
        startLoading();
    }

    public final void zzew() throws IOException {
        this.zzawt.zzbi(Integer.MIN_VALUE);
    }

    public final zzma zzex() {
        return this.zzaxf;
    }

    public final long zza(zzom[] zzomArr, boolean[] zArr, zzlv[] zzlvArr, boolean[] zArr2, long j) {
        zzpo.checkState(this.zzyu);
        int i = 0;
        int i2 = 0;
        while (i2 < zzomArr.length) {
            if (zzlvArr[i2] != null && (zzomArr[i2] == null || !zArr[i2])) {
                int zza = ((zzlj) zzlvArr[i2]).track;
                zzpo.checkState(this.zzaxg[zza]);
                this.zzaxe--;
                this.zzaxg[zza] = false;
                ((zzls) this.zzawy.valueAt(zza)).disable();
                zzlvArr[i2] = null;
            }
            i2++;
        }
        int i3 = 0;
        i2 = i3;
        while (i3 < zzomArr.length) {
            if (zzlvArr[i3] == null && zzomArr[i3] != null) {
                zzom zzom = zzomArr[i3];
                zzpo.checkState(zzom.length() == 1);
                zzpo.checkState(zzom.zzbd(0) == 0);
                i2 = this.zzaxf.zza(zzom.zzgk());
                zzpo.checkState(this.zzaxg[i2] ^ 1);
                this.zzaxe++;
                this.zzaxg[i2] = true;
                zzlvArr[i3] = new zzlj(this, i2);
                zArr2[i3] = true;
                i2 = 1;
            }
            i3++;
        }
        if (!this.zzaxc) {
            int size = this.zzawy.size();
            for (i3 = 0; i3 < size; i3++) {
                if (!this.zzaxg[i3]) {
                    ((zzls) this.zzawy.valueAt(i3)).disable();
                }
            }
        }
        if (this.zzaxe == 0) {
            this.zzaxd = false;
            if (this.zzawt.isLoading()) {
                this.zzawt.zzgy();
            }
        } else if (this.zzaxc ? r1 == 0 : j == 0) {
            j = zzab(j);
            while (i < zzlvArr.length) {
                if (zzlvArr[i] != null) {
                    zArr2[i] = true;
                }
                i++;
            }
        }
        this.zzaxc = true;
        return j;
    }

    public final boolean zzy(long j) {
        if (this.zzaxm || (this.zzyu && this.zzaxe == 0)) {
            return false;
        }
        boolean zzgz = this.zzawv.zzgz();
        if (!this.zzawt.isLoading()) {
            startLoading();
            zzgz = true;
        }
        return zzgz;
    }

    public final long zzeu() {
        return this.zzaxe == 0 ? Long.MIN_VALUE : zzez();
    }

    public final long zzey() {
        if (!this.zzaxd) {
            return C.TIME_UNSET;
        }
        this.zzaxd = false;
        return this.zzaxj;
    }

    public final long zzez() {
        if (this.zzaxm) {
            return Long.MIN_VALUE;
        }
        if (zzfd()) {
            return this.zzaxk;
        }
        long j;
        if (this.zzaxi) {
            j = Long.MAX_VALUE;
            int size = this.zzawy.size();
            for (int i = 0; i < size; i++) {
                if (this.zzaxh[i]) {
                    j = Math.min(j, ((zzls) this.zzawy.valueAt(i)).zzfc());
                }
            }
        } else {
            j = zzfc();
        }
        return j == Long.MIN_VALUE ? this.zzaxj : j;
    }

    public final long zzab(long j) {
        if (!this.zzaxa.zzdw()) {
            j = 0;
        }
        this.zzaxj = j;
        int size = this.zzawy.size();
        int zzfd = zzfd() ^ 1;
        int i = 0;
        while (zzfd != 0 && i < size) {
            if (this.zzaxg[i]) {
                zzfd = ((zzls) this.zzawy.valueAt(i)).zze(j, false);
            }
            i++;
        }
        if (zzfd == 0) {
            this.zzaxk = j;
            this.zzaxm = false;
            if (this.zzawt.isLoading()) {
                this.zzawt.zzgy();
            } else {
                for (zzfd = 0; zzfd < size; zzfd++) {
                    ((zzls) this.zzawy.valueAt(zzfd)).zzh(this.zzaxg[zzfd]);
                }
            }
        }
        this.zzaxd = false;
        return j;
    }

    /* Access modifiers changed, original: final */
    public final boolean zzap(int i) {
        return this.zzaxm || (!zzfd() && ((zzls) this.zzawy.valueAt(i)).zzfm());
    }

    /* Access modifiers changed, original: final */
    public final void zzev() throws IOException {
        this.zzawt.zzbi(Integer.MIN_VALUE);
    }

    /* Access modifiers changed, original: final */
    public final int zza(int i, zzfu zzfu, zzho zzho, boolean z) {
        if (this.zzaxd || zzfd()) {
            return -3;
        }
        return ((zzls) this.zzawy.valueAt(i)).zza(zzfu, zzho, z, this.zzaxm, this.zzaxj);
    }

    /* Access modifiers changed, original: final */
    public final void zzd(int i, long j) {
        zzls zzls = (zzls) this.zzawy.valueAt(i);
        if (!this.zzaxm || j <= zzls.zzfc()) {
            zzls.zze(j, true);
        } else {
            zzls.zzfp();
        }
    }

    public final zzii zzb(int i, int i2) {
        zzls zzls = (zzls) this.zzawy.get(i);
        if (zzls != null) {
            return zzls;
        }
        zzls = new zzls(this.zzawq);
        zzls.zza((zzlu) this);
        this.zzawy.put(i, zzls);
        return zzls;
    }

    public final void zzdy() {
        this.zzaxb = true;
        this.handler.post(this.zzaww);
    }

    public final void zza(zzig zzig) {
        this.zzaxa = zzig;
        this.handler.post(this.zzaww);
    }

    public final void zzg(zzfs zzfs) {
        this.handler.post(this.zzaww);
    }

    private final void zzfa() {
        if (!this.zzyb && !this.zzyu && this.zzaxa != null && this.zzaxb) {
            int size = this.zzawy.size();
            int i = 0;
            while (i < size) {
                if (((zzls) this.zzawy.valueAt(i)).zzfn() != null) {
                    i++;
                } else {
                    return;
                }
            }
            this.zzawv.zzha();
            zzlz[] zzlzArr = new zzlz[size];
            this.zzaxh = new boolean[size];
            this.zzaxg = new boolean[size];
            this.zzaan = this.zzaxa.getDurationUs();
            int i2 = 0;
            while (true) {
                boolean z = true;
                if (i2 < size) {
                    zzlzArr[i2] = new zzlz(((zzls) this.zzawy.valueAt(i2)).zzfn());
                    String str = r5.zzzj;
                    if (!(zzpt.zzac(str) || zzpt.zzab(str))) {
                        z = false;
                    }
                    this.zzaxh[i2] = z;
                    this.zzaxi = z | this.zzaxi;
                    i2++;
                } else {
                    this.zzaxf = new zzma(zzlzArr);
                    this.zzyu = true;
                    this.zzawp.zzb(new zzly(this.zzaan, this.zzaxa.zzdw()), null);
                    this.zzawz.zza(this);
                    return;
                }
            }
        }
    }

    private final void zza(zzlh zzlh) {
        if (this.zzcc == -1) {
            this.zzcc = zzlh.zzcc;
        }
    }

    private final void startLoading() {
        zzlh zzlh = new zzlh(this, this.uri, this.zzagy, this.zzawu, this.zzawv);
        if (this.zzyu) {
            zzpo.checkState(zzfd());
            if (this.zzaan == C.TIME_UNSET || this.zzaxk < this.zzaan) {
                zzlh.zze(this.zzaxa.zzr(this.zzaxk), this.zzaxk);
                this.zzaxk = C.TIME_UNSET;
            } else {
                this.zzaxm = true;
                this.zzaxk = C.TIME_UNSET;
                return;
            }
        }
        this.zzaxl = zzfb();
        int i = this.zzawn;
        if (i == -1) {
            i = (this.zzyu && this.zzcc == -1 && (this.zzaxa == null || this.zzaxa.getDurationUs() == C.TIME_UNSET)) ? 6 : 3;
        }
        this.zzawt.zza(zzlh, this, i);
    }

    private final int zzfb() {
        int i = 0;
        int i2 = 0;
        while (i < this.zzawy.size()) {
            i2 += ((zzls) this.zzawy.valueAt(i)).zzfk();
            i++;
        }
        return i2;
    }

    private final long zzfc() {
        int size = this.zzawy.size();
        long j = Long.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            j = Math.max(j, ((zzls) this.zzawy.valueAt(i)).zzfc());
        }
        return j;
    }

    private final boolean zzfd() {
        return this.zzaxk != C.TIME_UNSET;
    }

    public final /* synthetic */ void zza(zzpi zzpi, long j, long j2) {
        zza((zzlh) zzpi);
        this.zzaxm = true;
        if (this.zzaan == C.TIME_UNSET) {
            long zzfc = zzfc();
            this.zzaan = zzfc == Long.MIN_VALUE ? 0 : zzfc + Constants.HEARTBEAT_STAGE_ONE_INTERVAL;
            this.zzawp.zzb(new zzly(this.zzaan, this.zzaxa.zzdw()), null);
        }
        this.zzawz.zza(this);
    }
}
