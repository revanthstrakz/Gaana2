package com.google.android.gms.tagmanager;

import android.content.Context;
import android.os.Looper;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BasePendingResult;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzl;
import com.google.android.gms.internal.measurement.zzo;
import com.google.android.gms.internal.measurement.zzre;
import com.google.android.gms.internal.measurement.zzrf;
import com.google.android.gms.internal.measurement.zzrk;

@ShowFirstParty
public final class zzy extends BasePendingResult<ContainerHolder> {
    private final String zzazo;
    private long zzazt;
    private final Looper zzazw;
    private final TagManager zzbad;
    private final zzaf zzbag;
    private final zzej zzbah;
    private final int zzbai;
    private final zzai zzbaj;
    private zzah zzbak;
    private zzrf zzbal;
    private volatile zzv zzbam;
    private volatile boolean zzban;
    private zzo zzbao;
    private String zzbap;
    private zzag zzbaq;
    private zzac zzbar;
    private final Context zzri;
    private final Clock zzrz;

    public zzy(Context context, TagManager tagManager, Looper looper, String str, int i, zzal zzal) {
        Context context2 = context;
        String str2 = str;
        this(context2, tagManager, looper, str2, i, new zzex(context2, str2), new zzes(context2, str2, zzal), new zzrf(context2), DefaultClock.getInstance(), new zzdg(1, 5, 900000, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS, "refreshing", DefaultClock.getInstance()), new zzai(context2, str2));
        this.zzbal.zzfh(zzal.zzoe());
    }

    @VisibleForTesting
    private zzy(Context context, TagManager tagManager, Looper looper, String str, int i, zzah zzah, zzag zzag, zzrf zzrf, Clock clock, zzej zzej, zzai zzai) {
        super(looper == null ? Looper.getMainLooper() : looper);
        this.zzri = context;
        this.zzbad = tagManager;
        if (looper == null) {
            looper = Looper.getMainLooper();
        }
        this.zzazw = looper;
        this.zzazo = str;
        this.zzbai = i;
        this.zzbak = zzah;
        this.zzbaq = zzag;
        this.zzbal = zzrf;
        this.zzbag = new zzaf(this, null);
        this.zzbao = new zzo();
        this.zzrz = clock;
        this.zzbah = zzej;
        this.zzbaj = zzai;
        if (zznw()) {
            zzdf(zzeh.zzpm().zzpo());
        }
    }

    public final void zznt() {
        zzrk zzv = this.zzbak.zzv(this.zzbai);
        if (zzv != null) {
            setResult(new zzv(this.zzbad, this.zzazw, new Container(this.zzri, this.zzbad.getDataLayer(), this.zzazo, 0, zzv), new zzaa(this)));
        } else {
            String str = "Default was requested, but no default container was found";
            zzdi.e(str);
            setResult(createFailedResult(new Status(10, str, null)));
        }
        this.zzbaq = null;
        this.zzbak = null;
    }

    public final void zznu() {
        zzn(false);
    }

    public final void zznv() {
        zzn(true);
    }

    private final void zzn(boolean z) {
        this.zzbak.zza(new zzad(this, null));
        this.zzbaq.zza(new zzae(this, null));
        zzrk zzv = this.zzbak.zzv(this.zzbai);
        if (zzv != null) {
            this.zzbam = new zzv(this.zzbad, this.zzazw, new Container(this.zzri, this.zzbad.getDataLayer(), this.zzazo, 0, zzv), this.zzbag);
        }
        this.zzbar = new zzab(this, z);
        if (zznw()) {
            this.zzbaq.zza(0, "");
        } else {
            this.zzbak.zzny();
        }
    }

    /* JADX WARNING: Missing block: B:23:0x0074, code skipped:
            return;
     */
    private final synchronized void zza(com.google.android.gms.internal.measurement.zzo r11, long r12, boolean r14) {
        /*
        r10 = this;
        monitor-enter(r10);
        if (r14 == 0) goto L_0x0008;
    L_0x0003:
        r14 = r10.zzban;	 Catch:{ all -> 0x0006 }
        goto L_0x0008;
    L_0x0006:
        r11 = move-exception;
        goto L_0x0075;
    L_0x0008:
        r14 = r10.isReady();	 Catch:{ all -> 0x0006 }
        if (r14 == 0) goto L_0x0014;
    L_0x000e:
        r14 = r10.zzbam;	 Catch:{ all -> 0x0006 }
        if (r14 != 0) goto L_0x0014;
    L_0x0012:
        monitor-exit(r10);
        return;
    L_0x0014:
        r10.zzbao = r11;	 Catch:{ all -> 0x0006 }
        r10.zzazt = r12;	 Catch:{ all -> 0x0006 }
        r14 = r10.zzbaj;	 Catch:{ all -> 0x0006 }
        r0 = r14.zznz();	 Catch:{ all -> 0x0006 }
        r2 = 0;
        r4 = r10.zzazt;	 Catch:{ all -> 0x0006 }
        r6 = r4 + r0;
        r14 = r10.zzrz;	 Catch:{ all -> 0x0006 }
        r4 = r14.currentTimeMillis();	 Catch:{ all -> 0x0006 }
        r8 = r6 - r4;
        r0 = java.lang.Math.min(r0, r8);	 Catch:{ all -> 0x0006 }
        r0 = java.lang.Math.max(r2, r0);	 Catch:{ all -> 0x0006 }
        r10.zzao(r0);	 Catch:{ all -> 0x0006 }
        r14 = new com.google.android.gms.tagmanager.Container;	 Catch:{ all -> 0x0006 }
        r3 = r10.zzri;	 Catch:{ all -> 0x0006 }
        r0 = r10.zzbad;	 Catch:{ all -> 0x0006 }
        r4 = r0.getDataLayer();	 Catch:{ all -> 0x0006 }
        r5 = r10.zzazo;	 Catch:{ all -> 0x0006 }
        r2 = r14;
        r6 = r12;
        r8 = r11;
        r2.<init>(r3, r4, r5, r6, r8);	 Catch:{ all -> 0x0006 }
        r11 = r10.zzbam;	 Catch:{ all -> 0x0006 }
        if (r11 != 0) goto L_0x005b;
    L_0x004d:
        r11 = new com.google.android.gms.tagmanager.zzv;	 Catch:{ all -> 0x0006 }
        r12 = r10.zzbad;	 Catch:{ all -> 0x0006 }
        r13 = r10.zzazw;	 Catch:{ all -> 0x0006 }
        r0 = r10.zzbag;	 Catch:{ all -> 0x0006 }
        r11.<init>(r12, r13, r14, r0);	 Catch:{ all -> 0x0006 }
        r10.zzbam = r11;	 Catch:{ all -> 0x0006 }
        goto L_0x0060;
    L_0x005b:
        r11 = r10.zzbam;	 Catch:{ all -> 0x0006 }
        r11.zza(r14);	 Catch:{ all -> 0x0006 }
    L_0x0060:
        r11 = r10.isReady();	 Catch:{ all -> 0x0006 }
        if (r11 != 0) goto L_0x0073;
    L_0x0066:
        r11 = r10.zzbar;	 Catch:{ all -> 0x0006 }
        r11 = r11.zzb(r14);	 Catch:{ all -> 0x0006 }
        if (r11 == 0) goto L_0x0073;
    L_0x006e:
        r11 = r10.zzbam;	 Catch:{ all -> 0x0006 }
        r10.setResult(r11);	 Catch:{ all -> 0x0006 }
    L_0x0073:
        monitor-exit(r10);
        return;
    L_0x0075:
        monitor-exit(r10);
        throw r11;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzy.zza(com.google.android.gms.internal.measurement.zzo, long, boolean):void");
    }

    /* Access modifiers changed, original: final|declared_synchronized */
    @VisibleForTesting
    public final synchronized void zzdf(String str) {
        this.zzbap = str;
        if (this.zzbaq != null) {
            this.zzbaq.zzdg(str);
        }
    }

    /* Access modifiers changed, original: final|declared_synchronized */
    public final synchronized String zznq() {
        return this.zzbap;
    }

    private final synchronized void zzao(long j) {
        if (this.zzbaq == null) {
            zzdi.zzab("Refresh requested, but no network load scheduler.");
        } else {
            this.zzbaq.zza(j, this.zzbao.zzqh);
        }
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: zza */
    public final ContainerHolder createFailedResult(Status status) {
        if (this.zzbam != null) {
            return this.zzbam;
        }
        if (status == Status.RESULT_TIMEOUT) {
            zzdi.e("timer expired: setting result to failure");
        }
        return new zzv(status);
    }

    private final boolean zznw() {
        zzeh zzpm = zzeh.zzpm();
        return (zzpm.zzpn() == zza.CONTAINER || zzpm.zzpn() == zza.CONTAINER_DEBUG) && this.zzazo.equals(zzpm.getContainerId());
    }

    private final synchronized void zza(zzo zzo) {
        if (this.zzbak != null) {
            zzre zzre = new zzre();
            zzre.zzbqc = this.zzazt;
            zzre.zzqg = new zzl();
            zzre.zzbqd = zzo;
            this.zzbak.zza(zzre);
        }
    }
}
