package com.google.android.gms.internal.ads;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.exoplayer2.C;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

final class zzfl implements zzfg {
    private int repeatMode;
    private final zzfz[] zzwu;
    private final zzop zzwv;
    private final zzoo zzww;
    private final Handler zzwx;
    private final zzfn zzwy;
    private final CopyOnWriteArraySet<zzfh> zzwz;
    private final zzgf zzxa;
    private final zzge zzxb;
    private boolean zzxc;
    private boolean zzxd;
    private int zzxe;
    private int zzxf;
    private int zzxg;
    private boolean zzxh;
    private zzgc zzxi;
    private Object zzxj;
    private zzma zzxk;
    private zzoo zzxl;
    private zzfy zzxm;
    private zzfp zzxn;
    private int zzxo;
    private int zzxp;
    private long zzxq;

    @SuppressLint({"HandlerLeak"})
    public zzfl(zzfz[] zzfzArr, zzop zzop, zzfw zzfw) {
        String str = "Init ExoPlayerLib/2.4.2 [";
        String str2 = zzqe.zzbic;
        StringBuilder stringBuilder = new StringBuilder((String.valueOf(str).length() + 1) + String.valueOf(str2).length());
        stringBuilder.append(str);
        stringBuilder.append(str2);
        stringBuilder.append("]");
        Log.i("ExoPlayerImpl", stringBuilder.toString());
        zzpo.checkState(zzfzArr.length > 0);
        this.zzwu = (zzfz[]) zzpo.checkNotNull(zzfzArr);
        this.zzwv = (zzop) zzpo.checkNotNull(zzop);
        this.zzxd = false;
        this.repeatMode = 0;
        this.zzxe = 1;
        this.zzwz = new CopyOnWriteArraySet();
        this.zzww = new zzoo(new zzom[zzfzArr.length]);
        this.zzxi = zzgc.zzaal;
        this.zzxa = new zzgf();
        this.zzxb = new zzge();
        this.zzxk = zzma.zzazi;
        this.zzxl = this.zzww;
        this.zzxm = zzfy.zzaaf;
        this.zzwx = new zzfm(this, Looper.myLooper() != null ? Looper.myLooper() : Looper.getMainLooper());
        this.zzxn = new zzfp(0, 0);
        this.zzwy = new zzfn(zzfzArr, zzop, zzfw, this.zzxd, 0, this.zzwx, this.zzxn, this);
    }

    public final void zza(zzfh zzfh) {
        this.zzwz.add(zzfh);
    }

    public final void zzb(zzfh zzfh) {
        this.zzwz.remove(zzfh);
    }

    public final int getPlaybackState() {
        return this.zzxe;
    }

    public final void zza(zzlo zzlo) {
        Iterator it;
        if (!(this.zzxi.isEmpty() && this.zzxj == null)) {
            this.zzxi = zzgc.zzaal;
            this.zzxj = null;
            it = this.zzwz.iterator();
            while (it.hasNext()) {
                ((zzfh) it.next()).zza(this.zzxi, this.zzxj);
            }
        }
        if (this.zzxc) {
            this.zzxc = false;
            this.zzxk = zzma.zzazi;
            this.zzxl = this.zzww;
            this.zzwv.zzd(null);
            it = this.zzwz.iterator();
            while (it.hasNext()) {
                ((zzfh) it.next()).zza(this.zzxk, this.zzxl);
            }
        }
        this.zzxg++;
        this.zzwy.zza(zzlo, true);
    }

    public final void zzc(boolean z) {
        if (this.zzxd != z) {
            this.zzxd = z;
            this.zzwy.zzc(z);
            Iterator it = this.zzwz.iterator();
            while (it.hasNext()) {
                ((zzfh) it.next()).zza(z, this.zzxe);
            }
        }
    }

    public final boolean zzbp() {
        return this.zzxd;
    }

    public final void seekTo(long j) {
        int zzbt = zzbt();
        if (zzbt < 0 || (!this.zzxi.isEmpty() && zzbt >= this.zzxi.zzck())) {
            throw new zzfv(this.zzxi, zzbt, j);
        }
        this.zzxf++;
        this.zzxo = zzbt;
        if (this.zzxi.isEmpty()) {
            this.zzxp = 0;
        } else {
            long j2;
            this.zzxi.zza(zzbt, this.zzxa, false, 0);
            if (j == C.TIME_UNSET) {
                j2 = this.zzxa.zzaaw;
            } else {
                j2 = zzfe.zzg(j);
            }
            long j3 = this.zzxa.zzaax + j2;
            j2 = this.zzxi.zza(0, this.zzxb, false).zzaan;
            int i = 0;
            while (j2 != C.TIME_UNSET && j3 >= j2 && i < this.zzxa.zzaav) {
                long j4 = j3 - j2;
                i++;
                j2 = this.zzxi.zza(i, this.zzxb, false).zzaan;
                j3 = j4;
            }
            this.zzxp = i;
        }
        if (j == C.TIME_UNSET) {
            this.zzxq = 0;
            this.zzwy.zza(this.zzxi, zzbt, (long) C.TIME_UNSET);
            return;
        }
        this.zzxq = j;
        this.zzwy.zza(this.zzxi, zzbt, zzfe.zzg(j));
        Iterator it = this.zzwz.iterator();
        while (it.hasNext()) {
            ((zzfh) it.next()).zzbs();
        }
    }

    public final void stop() {
        this.zzwy.stop();
    }

    public final void release() {
        this.zzwy.release();
        this.zzwx.removeCallbacksAndMessages(null);
    }

    public final void zza(zzfj... zzfjArr) {
        this.zzwy.zza(zzfjArr);
    }

    public final void zzb(zzfj... zzfjArr) {
        this.zzwy.zzb(zzfjArr);
    }

    private final int zzbt() {
        if (this.zzxi.isEmpty() || this.zzxf > 0) {
            return this.zzxo;
        }
        this.zzxi.zza(this.zzxn.zzyr, this.zzxb, false);
        return 0;
    }

    public final long getDuration() {
        if (this.zzxi.isEmpty()) {
            return C.TIME_UNSET;
        }
        return zzfe.zzf(this.zzxi.zza(zzbt(), this.zzxa, false, 0).zzaan);
    }

    public final long zzbr() {
        if (this.zzxi.isEmpty() || this.zzxf > 0) {
            return this.zzxq;
        }
        this.zzxi.zza(this.zzxn.zzyr, this.zzxb, false);
        return this.zzxb.zzcm() + zzfe.zzf(this.zzxn.zzyz);
    }

    public final long getBufferedPosition() {
        if (this.zzxi.isEmpty() || this.zzxf > 0) {
            return this.zzxq;
        }
        this.zzxi.zza(this.zzxn.zzyr, this.zzxb, false);
        return this.zzxb.zzcm() + zzfe.zzf(this.zzxn.zzza);
    }

    public final int zzbq() {
        return this.zzwu.length;
    }

    /* Access modifiers changed, original: final */
    public final void zza(Message message) {
        boolean z = true;
        Iterator it;
        Iterator it2;
        switch (message.what) {
            case 0:
                this.zzxg--;
                return;
            case 1:
                this.zzxe = message.arg1;
                it = this.zzwz.iterator();
                while (it.hasNext()) {
                    ((zzfh) it.next()).zza(this.zzxd, this.zzxe);
                }
                return;
            case 2:
                if (message.arg1 == 0) {
                    z = false;
                }
                this.zzxh = z;
                it = this.zzwz.iterator();
                while (it.hasNext()) {
                    ((zzfh) it.next()).zzd(this.zzxh);
                }
                return;
            case 3:
                if (this.zzxg == 0) {
                    zzor zzor = (zzor) message.obj;
                    this.zzxc = true;
                    this.zzxk = zzor.zzbfk;
                    this.zzxl = zzor.zzbfl;
                    this.zzwv.zzd(zzor.zzbfm);
                    it = this.zzwz.iterator();
                    while (it.hasNext()) {
                        ((zzfh) it.next()).zza(this.zzxk, this.zzxl);
                    }
                    return;
                }
                break;
            case 4:
                int i = this.zzxf - 1;
                this.zzxf = i;
                if (i == 0) {
                    this.zzxn = (zzfp) message.obj;
                    if (message.arg1 != 0) {
                        it = this.zzwz.iterator();
                        while (it.hasNext()) {
                            ((zzfh) it.next()).zzbs();
                        }
                        return;
                    }
                }
                break;
            case 5:
                if (this.zzxf == 0) {
                    this.zzxn = (zzfp) message.obj;
                    it = this.zzwz.iterator();
                    while (it.hasNext()) {
                        ((zzfh) it.next()).zzbs();
                    }
                    return;
                }
                break;
            case 6:
                zzfr zzfr = (zzfr) message.obj;
                this.zzxf -= zzfr.zzzd;
                if (this.zzxg == 0) {
                    this.zzxi = zzfr.zzxi;
                    this.zzxj = zzfr.zzxj;
                    this.zzxn = zzfr.zzxn;
                    it = this.zzwz.iterator();
                    while (it.hasNext()) {
                        ((zzfh) it.next()).zza(this.zzxi, this.zzxj);
                    }
                    return;
                }
                break;
            case 7:
                zzfy zzfy = (zzfy) message.obj;
                if (!this.zzxm.equals(zzfy)) {
                    this.zzxm = zzfy;
                    it2 = this.zzwz.iterator();
                    while (it2.hasNext()) {
                        ((zzfh) it2.next()).zza(zzfy);
                    }
                    return;
                }
                break;
            case 8:
                zzff zzff = (zzff) message.obj;
                it2 = this.zzwz.iterator();
                while (it2.hasNext()) {
                    ((zzfh) it2.next()).zza(zzff);
                }
                return;
            default:
                throw new IllegalStateException();
        }
    }
}
