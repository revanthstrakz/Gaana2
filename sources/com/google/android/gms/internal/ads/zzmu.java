package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import java.io.IOException;

public final class zzmu implements zzlo {
    private Handler handler;
    private zzov zzagy;
    private final int zzawn;
    private zzlp zzawp;
    private zzpf zzawt;
    private final zzkt zzbah;
    private final zzmr zzbax;
    private zznj zzbbd;
    private final boolean zzbbk;
    private final zzow zzbbl;
    private final long zzbbm;
    private final zzpm<? extends zznj> zzbbn;
    private final zzmz zzbbo;
    private final Object zzbbp;
    private final SparseArray<zzms> zzbbq;
    private final Runnable zzbbr;
    private final Runnable zzbbs;
    private zzpk zzbbt;
    private Uri zzbbu;
    private long zzbbv;
    private long zzbbw;
    private long zzbbx;
    private int zzbby;

    public zzmu(Uri uri, zzow zzow, zzmr zzmr, Handler handler, zzks zzks) {
        this(uri, zzow, zzmr, 3, -1, handler, null);
    }

    private zzmu(Uri uri, zzow zzow, zzmr zzmr, int i, long j, Handler handler, zzks zzks) {
        this(uri, zzow, new zznk(), zzmr, 3, -1, handler, null);
    }

    private zzmu(Uri uri, zzow zzow, zzpm<? extends zznj> zzpm, zzmr zzmr, int i, long j, Handler handler, zzks zzks) {
        this(null, uri, zzow, zzpm, zzmr, 3, -1, handler, zzks);
    }

    private zzmu(zznj zznj, Uri uri, zzow zzow, zzpm<? extends zznj> zzpm, zzmr zzmr, int i, long j, Handler handler, zzks zzks) {
        this.zzbbd = null;
        this.zzbbu = uri;
        this.zzbbl = zzow;
        this.zzbbn = zzpm;
        this.zzbax = zzmr;
        this.zzawn = i;
        this.zzbbm = j;
        this.zzbbk = false;
        this.zzbah = new zzkt(handler, zzks);
        this.zzbbp = new Object();
        this.zzbbq = new SparseArray();
        this.zzbbo = new zzmz(this, null);
        this.zzbbr = new zzmv(this);
        this.zzbbs = new zzmw(this);
    }

    public final void zza(zzfg zzfg, boolean z, zzlp zzlp) {
        this.zzawp = zzlp;
        this.zzagy = this.zzbbl.zzgs();
        this.zzawt = new zzpf("Loader:DashMediaSource");
        this.zzbbt = this.zzawt;
        this.handler = new Handler();
        zzgb();
    }

    public final void zzfg() throws IOException {
        this.zzbbt.zzev();
    }

    public final zzlm zza(int i, zzot zzot) {
        int i2 = i;
        zzms zzms = new zzms(this.zzbby + i, this.zzbbd, i2, this.zzbax, this.zzawn, this.zzbah.zzw(this.zzbbd.zzba(i).zzbdj), this.zzbbx, this.zzbbt, zzot);
        this.zzbbq.put(zzms.id, zzms);
        return zzms;
    }

    public final void zzb(zzlm zzlm) {
        zzms zzms = (zzms) zzlm;
        zzms.release();
        this.zzbbq.remove(zzms.id);
    }

    public final void zzfh() {
        this.zzagy = null;
        this.zzbbt = null;
        if (this.zzawt != null) {
            this.zzawt.zza(null);
            this.zzawt = null;
        }
        this.zzbbv = 0;
        this.zzbbw = 0;
        this.zzbbd = null;
        if (this.handler != null) {
            this.handler.removeCallbacksAndMessages(null);
            this.handler = null;
        }
        this.zzbbx = 0;
        this.zzbbq.clear();
    }

    /* Access modifiers changed, original: final */
    public final void zza(zzpl<zznj> zzpl, long j, long j2) {
        this.zzbah.zza(zzpl.zzazo, zzpl.type, j, j2, zzpl.zzfv());
        zznj zznj = (zznj) zzpl.getResult();
        int i = 0;
        int zzcl = this.zzbbd == null ? 0 : this.zzbbd.zzcl();
        long j3 = zznj.zzba(0).zzbdj;
        while (i < zzcl && this.zzbbd.zzba(i).zzbdj < j3) {
            i++;
        }
        if (zzcl - i > zznj.zzcl()) {
            Log.w("DashMediaSource", "Out of sync manifest");
            zzgc();
            return;
        }
        this.zzbbd = zznj;
        this.zzbbv = j - j2;
        this.zzbbw = j;
        if (this.zzbbd.zzbcx != null) {
            synchronized (this.zzbbp) {
                if (zzpl.zzazo.uri == this.zzbbu) {
                    this.zzbbu = this.zzbbd.zzbcx;
                }
            }
        }
        if (zzcl != 0) {
            this.zzbby += i;
            zzi(true);
        } else if (this.zzbbd.zzbcw != null) {
            zzob zzob = this.zzbbd.zzbcw;
            Object obj = zzob.zzbdi;
            if (zzqe.zza(obj, (Object) "urn:mpeg:dash:utc:direct:2014") || zzqe.zza(obj, (Object) "urn:mpeg:dash:utc:direct:2012")) {
                try {
                    zzah(zzqe.zzal(zzob.value) - this.zzbbw);
                } catch (zzfx e) {
                    zzc(e);
                }
            } else if (zzqe.zza(obj, (Object) "urn:mpeg:dash:utc:http-iso:2014") || zzqe.zza(obj, (Object) "urn:mpeg:dash:utc:http-iso:2012")) {
                zza(zzob, new zzmy());
            } else if (zzqe.zza(obj, (Object) "urn:mpeg:dash:utc:http-xsdate:2014") || zzqe.zza(obj, (Object) "urn:mpeg:dash:utc:http-xsdate:2012")) {
                zza(zzob, new zznc());
            } else {
                zzc(new IOException("Unsupported UTC timing scheme"));
            }
        } else {
            zzi(true);
        }
    }

    /* Access modifiers changed, original: final */
    public final int zza(zzpl<zznj> zzpl, long j, long j2, IOException iOException) {
        zzpl<zznj> zzpl2 = zzpl;
        IOException iOException2 = iOException;
        boolean z = iOException2 instanceof zzfx;
        this.zzbah.zza(zzpl2.zzazo, zzpl2.type, j, j2, zzpl2.zzfv(), iOException2, z);
        return z ? 3 : 0;
    }

    /* Access modifiers changed, original: final */
    public final void zzb(zzpl<Long> zzpl, long j, long j2) {
        this.zzbah.zza(zzpl.zzazo, zzpl.type, j, j2, zzpl.zzfv());
        zzah(((Long) zzpl.getResult()).longValue() - j);
    }

    /* Access modifiers changed, original: final */
    public final int zzb(zzpl<Long> zzpl, long j, long j2, IOException iOException) {
        zzpl<Long> zzpl2 = zzpl;
        this.zzbah.zza(zzpl2.zzazo, zzpl2.type, j, j2, zzpl2.zzfv(), iOException, true);
        zzc(iOException);
        return 2;
    }

    /* Access modifiers changed, original: final */
    public final void zzc(zzpl<?> zzpl, long j, long j2) {
        this.zzbah.zzb(zzpl.zzazo, zzpl.type, j, j2, zzpl.zzfv());
    }

    private final void zzgb() {
        Uri uri;
        synchronized (this.zzbbp) {
            uri = this.zzbbu;
        }
        zza(new zzpl(this.zzagy, uri, 4, this.zzbbn), this.zzbbo, this.zzawn);
    }

    private final void zza(zzob zzob, zzpm<Long> zzpm) {
        zza(new zzpl(this.zzagy, Uri.parse(zzob.value), 5, zzpm), new zznb(this, null), 1);
    }

    private final void zzah(long j) {
        this.zzbbx = j;
        zzi(true);
    }

    private final void zzc(IOException iOException) {
        Log.e("DashMediaSource", "Failed to resolve UtcTiming element.", iOException);
        zzi(true);
    }

    private final void zzi(boolean z) {
        int i;
        long j;
        long zzg;
        long j2;
        for (i = 0; i < this.zzbbq.size(); i++) {
            int keyAt = this.zzbbq.keyAt(i);
            if (keyAt >= this.zzbby) {
                ((zzms) this.zzbbq.valueAt(i)).zza(this.zzbbd, keyAt - this.zzbby);
            }
        }
        i = this.zzbbd.zzcl() - 1;
        zzna zza = zzna.zza(this.zzbbd.zzba(0), this.zzbbd.zzbb(0));
        zzna zza2 = zzna.zza(this.zzbbd.zzba(i), this.zzbbd.zzbb(i));
        long j3 = zza.zzbcc;
        long j4 = zza2.zzbcd;
        if (!this.zzbbd.zzbcs || zza2.zzbcb) {
            j = 0;
            i = 0;
        } else {
            if (this.zzbbx != 0) {
                j = zzfe.zzg(SystemClock.elapsedRealtime() + this.zzbbx);
            } else {
                j = zzfe.zzg(System.currentTimeMillis());
            }
            j4 = Math.min((j - zzfe.zzg(this.zzbbd.zzbcq)) - zzfe.zzg(this.zzbbd.zzba(i).zzbdj), j4);
            if (this.zzbbd.zzbcu != C.TIME_UNSET) {
                zzg = j4 - zzfe.zzg(this.zzbbd.zzbcu);
                j = 0;
                while (zzg < 0 && i > 0) {
                    i--;
                    zzg += this.zzbbd.zzbb(i);
                }
                if (i == 0) {
                    j3 = Math.max(j3, zzg);
                } else {
                    j3 = this.zzbbd.zzbb(0);
                }
            } else {
                j = 0;
            }
            i = 1;
        }
        zzg = j4 - j3;
        int i2 = 0;
        long j5 = zzg;
        while (i2 < this.zzbbd.zzcl() - 1) {
            i2++;
            j5 += this.zzbbd.zzbb(i2);
        }
        if (this.zzbbd.zzbcs) {
            long j6 = this.zzbbm;
            if (j6 == -1) {
                j6 = this.zzbbd.zzbcv != C.TIME_UNSET ? this.zzbbd.zzbcv : 30000;
            }
            zzg = j5 - zzfe.zzg(j6);
            if (zzg < 5000000) {
                zzg = Math.min(5000000, j5 / 2);
            }
            j2 = zzg;
        } else {
            j2 = j;
        }
        this.zzawp.zzb(new zzmx(this.zzbbd.zzbcq, (this.zzbbd.zzbcq + this.zzbbd.zzba(0).zzbdj) + zzfe.zzf(j3), this.zzbby, j3, j5, j2, this.zzbbd), this.zzbbd);
        this.handler.removeCallbacks(this.zzbbs);
        if (i != 0) {
            this.handler.postDelayed(this.zzbbs, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
        }
        if (z) {
            zzgc();
        }
    }

    private final void zzgc() {
        if (this.zzbbd.zzbcs) {
            long j = this.zzbbd.zzbct;
            if (j == 0) {
                j = DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS;
            }
            this.handler.postDelayed(this.zzbbr, Math.max(0, (this.zzbbv + j) - SystemClock.elapsedRealtime()));
        }
    }

    private final <T> void zza(zzpl<T> zzpl, zzpg<zzpl<T>> zzpg, int i) {
        this.zzbah.zza(zzpl.zzazo, zzpl.type, this.zzawt.zza(zzpl, zzpg, i));
    }
}
