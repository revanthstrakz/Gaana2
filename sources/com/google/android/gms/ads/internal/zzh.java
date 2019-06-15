package com.google.android.gms.ads.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzaan;
import com.google.android.gms.internal.ads.zzaba;
import com.google.android.gms.internal.ads.zzabg;
import com.google.android.gms.internal.ads.zzalg;
import com.google.android.gms.internal.ads.zzaol;
import com.google.android.gms.internal.ads.zzapl;
import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzawr;
import com.google.android.gms.internal.ads.zzaxf;
import com.google.android.gms.internal.ads.zzaxg;
import com.google.android.gms.internal.ads.zzayh;
import com.google.android.gms.internal.ads.zzbbd;
import com.google.android.gms.internal.ads.zzbbi;
import com.google.android.gms.internal.ads.zzbgg;
import com.google.android.gms.internal.ads.zzbgm;
import com.google.android.gms.internal.ads.zzbgq;
import com.google.android.gms.internal.ads.zzbht;
import com.google.android.gms.internal.ads.zzwf;
import com.google.android.gms.internal.ads.zzwu;

@zzark
public abstract class zzh extends zzc implements zzaf, zzaol {
    private boolean zzbme;

    public zzh(Context context, zzwf zzwf, String str, zzalg zzalg, zzbbi zzbbi, zzv zzv) {
        super(context, zzwf, str, zzalg, zzbbi, zzv);
    }

    /* Access modifiers changed, original: protected */
    public zzbgg zza(zzaxg zzaxg, @Nullable zzw zzw, @Nullable zzawr zzawr) throws zzbgq {
        zzaxg zzaxg2 = zzaxg;
        View nextView = this.zzbls.zzbsq.getNextView();
        if (nextView instanceof zzbgg) {
            ((zzbgg) nextView).destroy();
        }
        if (nextView != null) {
            this.zzbls.zzbsq.removeView(nextView);
        }
        zzbv.zzlg();
        zzbgg zza = zzbgm.zza(this.zzbls.zzsp, zzbht.zzb(this.zzbls.zzbst), this.zzbls.zzbst.zzckk, false, false, this.zzbls.zzbso, this.zzbls.zzbsp, this.zzbln, this, this.zzbly, zzaxg2.zzehw);
        if (this.zzbls.zzbst.zzckm == null) {
            zzg(zza.getView());
        }
        zza.zzadl().zza(this, this, this, this, this, false, null, zzw, this, zzawr);
        zza(zza);
        zza.zzfb(zzaxg2.zzeag.zzdws);
        return zza;
    }

    /* Access modifiers changed, original: protected|final */
    public final void zza(zzbgg zzbgg) {
        zzbgg.zza("/trackActiveViewUnit", new zzi(this));
    }

    /* Access modifiers changed, original: protected */
    public void zzil() {
        super.zzil();
        if (this.zzbme) {
            if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcuk)).booleanValue()) {
                zzb(this.zzbls.zzbsu.zzdrv);
            }
        }
    }

    /* Access modifiers changed, original: final */
    @VisibleForTesting
    public final void zzb(zzbgg zzbgg) {
        if (this.zzbls.zzbsu != null) {
            this.zzblu.zza(this.zzbls.zzbst, this.zzbls.zzbsu, zzbgg.getView(), zzbgg);
            this.zzbme = false;
            return;
        }
        this.zzbme = true;
        zzbbd.zzeo("Request to enable ActiveView before adState is available.");
    }

    /* Access modifiers changed, original: protected */
    public void zza(zzaxg zzaxg, zzaba zzaba) {
        if (zzaxg.errorCode != -2) {
            zzayh.zzelc.post(new zzj(this, zzaxg));
            return;
        }
        if (zzaxg.zzbst != null) {
            this.zzbls.zzbst = zzaxg.zzbst;
        }
        if (!zzaxg.zzehy.zzdyd || zzaxg.zzehy.zzckp) {
            zzayh.zzelc.post(new zzk(this, zzaxg, this.zzbly.zzbmu.zza(this.zzbls.zzsp, this.zzbls.zzbsp, zzaxg.zzehy), zzaba));
            return;
        }
        this.zzbls.zzbtw = 0;
        zzbw zzbw = this.zzbls;
        zzbv.zzle();
        zzbw.zzbss = zzapl.zza(this.zzbls.zzsp, this, zzaxg, this.zzbls.zzbso, null, this.zzbma, this, zzaba);
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x0039 */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    public boolean zza(@android.support.annotation.Nullable com.google.android.gms.internal.ads.zzaxf r3, com.google.android.gms.internal.ads.zzaxf r4) {
        /*
        r2 = this;
        r0 = r2.zzbls;
        r0 = r0.zzmj();
        if (r0 == 0) goto L_0x001b;
    L_0x0008:
        r0 = r2.zzbls;
        r0 = r0.zzbsq;
        if (r0 == 0) goto L_0x001b;
    L_0x000e:
        r0 = r2.zzbls;
        r0 = r0.zzbsq;
        r0 = r0.zzmm();
        r1 = r4.zzdyi;
        r0.zzef(r1);
    L_0x001b:
        r0 = r4.zzdrv;	 Catch:{ RuntimeException -> 0x003f }
        if (r0 == 0) goto L_0x0044;
    L_0x001f:
        r0 = r4.zzdyd;	 Catch:{ RuntimeException -> 0x003f }
        if (r0 != 0) goto L_0x0044;
    L_0x0023:
        r0 = r4.zzehx;	 Catch:{ RuntimeException -> 0x003f }
        if (r0 == 0) goto L_0x0044;
    L_0x0027:
        r0 = r4.zzdwg;	 Catch:{ RuntimeException -> 0x003f }
        r0 = r0.extras;	 Catch:{ RuntimeException -> 0x003f }
        r1 = "sdk_less_server_data";
        r0 = r0.containsKey(r1);	 Catch:{ RuntimeException -> 0x003f }
        if (r0 != 0) goto L_0x0044;
    L_0x0033:
        r0 = r4.zzdrv;	 Catch:{ Throwable -> 0x0039 }
        r0.zzadz();	 Catch:{ Throwable -> 0x0039 }
        goto L_0x0044;
    L_0x0039:
        r0 = "Could not render test Ad label.";
        com.google.android.gms.internal.ads.zzaxz.v(r0);	 Catch:{ RuntimeException -> 0x003f }
        goto L_0x0044;
    L_0x003f:
        r0 = "Could not render test AdLabel.";
        com.google.android.gms.internal.ads.zzaxz.v(r0);
    L_0x0044:
        if (r4 == 0) goto L_0x0051;
    L_0x0046:
        r0 = r4.zzdrv;
        if (r0 == 0) goto L_0x0051;
    L_0x004a:
        r0 = r4.zzdrv;
        r1 = r4.zzdzf;
        r0.zzay(r1);
    L_0x0051:
        r3 = super.zza(r3, r4);
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.zzh.zza(com.google.android.gms.internal.ads.zzaxf, com.google.android.gms.internal.ads.zzaxf):boolean");
    }

    public final void zza(zzabg zzabg) {
        Preconditions.checkMainThread("setOnCustomRenderedAdLoadedListener must be called on the main UI thread.");
        this.zzbls.zzbto = zzabg;
    }

    public final void zzh(View view) {
        this.zzbls.zzbtv = view;
        zzb(new zzaxf(this.zzbls.zzbsv, null, null, null, null, null, null, null));
    }

    public final void zzjh() {
        onAdClicked();
    }

    public final void zzji() {
        recordImpression();
        zzih();
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean zzjj() {
        return (this.zzbls.zzbsv == null || this.zzbls.zzbsv.zzehy == null || !this.zzbls.zzbsv.zzehy.zzdzc) ? false : true;
    }

    public final void zza(int i, int i2, int i3, int i4) {
        zzik();
    }

    public final void zzjk() {
        zzii();
    }
}
