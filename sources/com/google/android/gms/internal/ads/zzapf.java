package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.internal.Preconditions;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

@zzark
public abstract class zzapf implements zzazb<Void>, zzbho {
    protected final Context mContext;
    protected final zzbgg zzdin;
    private final zzapm zzdsj;
    private final zzaxg zzdsk;
    protected zzasm zzdsl;
    private Runnable zzdsm;
    private final Object zzdsn = new Object();
    private AtomicBoolean zzdso = new AtomicBoolean(true);

    protected zzapf(Context context, zzaxg zzaxg, zzbgg zzbgg, zzapm zzapm) {
        this.mContext = context;
        this.zzdsk = zzaxg;
        this.zzdsl = this.zzdsk.zzehy;
        this.zzdin = zzbgg;
        this.zzdsj = zzapm;
    }

    public abstract void zzvz();

    public final void zzp(boolean z) {
        zzbbd.zzdn("WebView finished loading.");
        int i = 0;
        if (this.zzdso.getAndSet(false)) {
            if (z) {
                i = -2;
            }
            zzcq(i);
            zzayh.zzelc.removeCallbacks(this.zzdsm);
        }
    }

    public void cancel() {
        if (this.zzdso.getAndSet(false)) {
            this.zzdin.stopLoading();
            zzbv.zzlh();
            zzayp.zzi(this.zzdin);
            zzcq(-1);
            zzayh.zzelc.removeCallbacks(this.zzdsm);
        }
    }

    /* Access modifiers changed, original: protected */
    public void zzcq(int i) {
        int i2 = i;
        if (i2 != -2) {
            this.zzdsl = new zzasm(i2, this.zzdsl.zzdlx);
        }
        this.zzdin.zzadf();
        zzapm zzapm = this.zzdsj;
        zzasi zzasi = this.zzdsk.zzeag;
        zzwb zzwb = zzasi.zzdwg;
        zzbgg zzbgg = this.zzdin;
        List list = this.zzdsl.zzdlq;
        List list2 = this.zzdsl.zzdlr;
        List list3 = this.zzdsl.zzdyf;
        int i3 = this.zzdsl.orientation;
        long j = this.zzdsl.zzdlx;
        String str = zzasi.zzdwj;
        boolean z = this.zzdsl.zzdyd;
        long j2 = this.zzdsl.zzdye;
        zzwf zzwf = this.zzdsk.zzbst;
        long j3 = j2;
        zzapm zzapm2 = zzapm;
        long j4 = this.zzdsl.zzdyc;
        long j5 = this.zzdsk.zzehn;
        long j6 = this.zzdsl.zzdyh;
        String str2 = this.zzdsl.zzdyi;
        JSONObject jSONObject = this.zzdsk.zzehh;
        zzawd zzawd = this.zzdsl.zzdyr;
        List list4 = this.zzdsl.zzdys;
        List list5 = this.zzdsl.zzdyt;
        boolean z2 = this.zzdsl.zzdyu;
        zzaso zzaso = this.zzdsl.zzdyv;
        List list6 = this.zzdsl.zzdlu;
        String str3 = this.zzdsl.zzdyy;
        zzum zzum = this.zzdsk.zzehw;
        boolean z3 = this.zzdsk.zzehy.zzbph;
        boolean z4 = this.zzdsk.zzehx;
        boolean z5 = this.zzdsk.zzehy.zzdzc;
        List list7 = this.zzdsl.zzdls;
        boolean z6 = this.zzdsk.zzehy.zzbpi;
        String str4 = str2;
        zzalj zzalj = null;
        zzaxf zzaxf = r46;
        zzapm zzapm3 = zzapm2;
        String str5 = null;
        zzakr zzakr = null;
        zzakt zzakt = null;
        long j7 = j3;
        long j8 = j4;
        long j9 = j5;
        long j10 = j6;
        zzaxf zzaxf2 = new zzaxf(zzwb, zzbgg, list, i2, list2, list3, i3, j, str, z, null, zzalj, str5, zzakr, zzakt, j7, zzwf, j8, j9, j10, str4, jSONObject, null, zzawd, list4, list5, z2, zzaso, null, list6, str3, zzum, z3, z4, z5, list7, z6, this.zzdsk.zzehy.zzdzd, this.zzdsk.zzehy.zzdzf);
        zzapm3.zzb(zzaxf);
    }

    public final /* synthetic */ Object zzwa() {
        Preconditions.checkMainThread("Webview render task needs to be called on UI thread.");
        this.zzdsm = new zzapg(this);
        zzayh.zzelc.postDelayed(this.zzdsm, ((Long) zzwu.zzpz().zzd(zzaan.zzctf)).longValue());
        zzvz();
        return null;
    }
}
