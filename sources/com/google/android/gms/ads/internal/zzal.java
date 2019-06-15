package com.google.android.gms.ads.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.internal.NativeProtocol;
import com.google.ads.mediation.AbstractAdViewAdapter;
import com.google.android.gms.ads.internal.gmsg.zzag;
import com.google.android.gms.ads.internal.gmsg.zzah;
import com.google.android.gms.ads.internal.gmsg.zzy;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.ads.internal.overlay.zzl;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzaan;
import com.google.android.gms.internal.ads.zzaba;
import com.google.android.gms.internal.ads.zzakq;
import com.google.android.gms.internal.ads.zzakr;
import com.google.android.gms.internal.ads.zzalg;
import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzasi;
import com.google.android.gms.internal.ads.zzasm;
import com.google.android.gms.internal.ads.zzaso;
import com.google.android.gms.internal.ads.zzatv;
import com.google.android.gms.internal.ads.zzauk;
import com.google.android.gms.internal.ads.zzawd;
import com.google.android.gms.internal.ads.zzawr;
import com.google.android.gms.internal.ads.zzawv;
import com.google.android.gms.internal.ads.zzaxf;
import com.google.android.gms.internal.ads.zzaxg;
import com.google.android.gms.internal.ads.zzayh;
import com.google.android.gms.internal.ads.zzayp;
import com.google.android.gms.internal.ads.zzbbd;
import com.google.android.gms.internal.ads.zzbbi;
import com.google.android.gms.internal.ads.zzbgg;
import com.google.android.gms.internal.ads.zzbgm;
import com.google.android.gms.internal.ads.zzbgq;
import com.google.android.gms.internal.ads.zzbhn;
import com.google.android.gms.internal.ads.zzbht;
import com.google.android.gms.internal.ads.zzsc;
import com.google.android.gms.internal.ads.zzwb;
import com.google.android.gms.internal.ads.zzwf;
import com.google.android.gms.internal.ads.zzwu;
import com.til.colombia.android.internal.e;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@zzark
public final class zzal extends zzh implements zzah, zzy {
    private transient boolean zzbom;
    private int zzbon = -1;
    private boolean zzboo;
    private float zzbop;
    private boolean zzboq;
    private zzawv zzbor;
    private String zzbos;
    private final String zzbot;
    private final zzauk zzbou;

    public zzal(Context context, zzwf zzwf, String str, zzalg zzalg, zzbbi zzbbi, zzv zzv) {
        zzauk zzauk;
        super(context, zzwf, str, zzalg, zzbbi, zzv);
        boolean z = false;
        this.zzbom = false;
        if (zzwf != null && "reward_mb".equals(zzwf.zzckk)) {
            z = true;
        }
        this.zzbot = z ? "/Rewarded" : "/Interstitial";
        if (z) {
            zzauk zzauk2 = new zzauk(this.zzbls, this.zzbma, new zzan(this), this, this);
        } else {
            zzauk = null;
        }
        this.zzbou = zzauk;
    }

    private final boolean zzn(boolean z) {
        return this.zzbou != null && z;
    }

    public final boolean zza(zzwb zzwb, zzaba zzaba) {
        if (this.zzbls.zzbsu != null) {
            zzbbd.zzeo("An interstitial is already loading. Aborting.");
            return false;
        }
        if (this.zzbor == null && zza.zza(zzwb) && zzbv.zzmf().zzv(this.zzbls.zzsp) && !TextUtils.isEmpty(this.zzbls.zzbsn)) {
            this.zzbor = new zzawv(this.zzbls.zzsp, this.zzbls.zzbsn);
        }
        return super.zza(zzwb, zzaba);
    }

    public final void zza(zzaxg zzaxg, zzaba zzaba) {
        if (zzaxg.errorCode != -2) {
            super.zza(zzaxg, zzaba);
            return;
        }
        if (zzn(zzaxg.zzehj != null)) {
            this.zzbou.zzxd();
            return;
        }
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcrz)).booleanValue()) {
            int i = zzaxg.zzehy.zzdyd ^ 1;
            if (zza.zza(zzaxg.zzeag.zzdwg) && i != 0) {
                this.zzbls.zzbsv = zzb(zzaxg);
            }
            super.zza(this.zzbls.zzbsv, zzaba);
            return;
        }
        super.zza(zzaxg, zzaba);
    }

    /* Access modifiers changed, original: protected|final */
    public final zzbgg zza(zzaxg zzaxg, @Nullable zzw zzw, @Nullable zzawr zzawr) throws zzbgq {
        zzbv.zzlg();
        zzbgg zza = zzbgm.zza(this.zzbls.zzsp, zzbht.zzb(this.zzbls.zzbst), this.zzbls.zzbst.zzckk, false, false, this.zzbls.zzbso, this.zzbls.zzbsp, this.zzbln, this, this.zzbly, zzaxg.zzehw);
        zza.zzadl().zza(this, this, null, this, this, true, this, zzw, this, zzawr);
        zza(zza);
        zza.zzfb(zzaxg.zzeag.zzdws);
        zza.zza("/reward", new zzag(this));
        return zza;
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean zza(zzwb zzwb, zzaxf zzaxf, boolean z) {
        if (this.zzbls.zzmj() && zzaxf.zzdrv != null) {
            zzbv.zzlh();
            zzayp.zzi(zzaxf.zzdrv);
        }
        return this.zzblr.zzkv();
    }

    public final boolean zza(@Nullable zzaxf zzaxf, zzaxf zzaxf2) {
        if (zzn(zzaxf2.zzdyd)) {
            return zzauk.zza(zzaxf, zzaxf2);
        }
        if (!super.zza(zzaxf, zzaxf2)) {
            return false;
        }
        if (!(this.zzbls.zzmj() || this.zzbls.zzbtv == null || zzaxf2.zzehh == null)) {
            this.zzblu.zza(this.zzbls.zzbst, zzaxf2, this.zzbls.zzbtv);
        }
        zzb(zzaxf2, false);
        return true;
    }

    public final void zziw() {
        recordImpression();
        super.zziw();
        if (!(this.zzbls.zzbsu == null || this.zzbls.zzbsu.zzdrv == null)) {
            zzbhn zzadl = this.zzbls.zzbsu.zzdrv.zzadl();
            if (zzadl != null) {
                zzadl.zzaeg();
            }
        }
        if (!(!zzbv.zzmf().zzv(this.zzbls.zzsp) || this.zzbls.zzbsu == null || this.zzbls.zzbsu.zzdrv == null)) {
            zzbv.zzmf().zze(this.zzbls.zzbsu.zzdrv.getContext(), this.zzbos);
        }
        if (this.zzbor != null) {
            this.zzbor.zzai(true);
        }
        if (this.zzblx != null && this.zzbls.zzbsu != null && this.zzbls.zzbsu.zzdrv != null) {
            this.zzbls.zzbsu.zzdrv.zza("onSdkImpression", (Map) new HashMap());
        }
    }

    public final void zziv() {
        super.zziv();
        this.zzblu.zzh(this.zzbls.zzbsu);
        if (this.zzbor != null) {
            this.zzbor.zzai(false);
        }
        this.zzblx = null;
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzii() {
        zzke();
        super.zzii();
    }

    public final void zzo(boolean z) {
        this.zzbls.zzbpa = z;
    }

    public final void zza(boolean z, float f) {
        this.zzboo = z;
        this.zzbop = f;
    }

    public final void showInterstitial() {
        Preconditions.checkMainThread("showInterstitial must be called on the main UI thread.");
        boolean z = this.zzbls.zzbsu != null && this.zzbls.zzbsu.zzdyd;
        if (zzn(z)) {
            this.zzbou.zzah(this.zzboq);
            return;
        }
        String valueOf;
        if (zzbv.zzmf().zzv(this.zzbls.zzsp)) {
            this.zzbos = zzbv.zzmf().zzw(this.zzbls.zzsp);
            valueOf = String.valueOf(this.zzbos);
            String valueOf2 = String.valueOf(this.zzbot);
            this.zzbos = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
        }
        if (this.zzbls.zzbsu == null) {
            zzbbd.zzeo("The interstitial has not loaded.");
            return;
        }
        if (!this.zzbom) {
            if (!((Boolean) zzwu.zzpz().zzd(zzaan.zzcxp)).booleanValue()) {
                return;
            }
        }
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcra)).booleanValue()) {
            zzbv.zzlf();
            if (zzayh.zzap(this.zzbls.zzsp)) {
                zzbbd.zzeo("It is not recommended to show an interstitial when app is not in foreground.");
                return;
            }
        }
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcsw)).booleanValue()) {
            Bundle bundle;
            if (this.zzbls.zzsp.getApplicationContext() != null) {
                valueOf = this.zzbls.zzsp.getApplicationContext().getPackageName();
            } else {
                valueOf = this.zzbls.zzsp.getPackageName();
            }
            if (!this.zzbom) {
                zzbbd.zzeo("It is not recommended to show an interstitial before onAdLoaded completes.");
                bundle = new Bundle();
                bundle.putString(e.A, valueOf);
                bundle.putString(NativeProtocol.WEB_DIALOG_ACTION, "show_interstitial_before_load_finish");
                zzb(bundle);
            }
            zzbv.zzlf();
            if (!zzayh.zzao(this.zzbls.zzsp)) {
                zzbbd.zzeo("It is not recommended to show an interstitial when app is not in foreground.");
                bundle = new Bundle();
                bundle.putString(e.A, valueOf);
                bundle.putString(NativeProtocol.WEB_DIALOG_ACTION, "show_interstitial_app_not_in_foreground");
                zzb(bundle);
            }
        }
        if (!this.zzbls.zzmk()) {
            if (this.zzbls.zzbsu.zzdyd && this.zzbls.zzbsu.zzdnc != null) {
                try {
                    this.zzbls.zzbsu.zzdnc.setImmersiveMode(this.zzboq);
                    this.zzbls.zzbsu.zzdnc.showInterstitial();
                } catch (RemoteException e) {
                    zzbbd.zzc("Could not show interstitial.", e);
                    zzke();
                }
            } else if (this.zzbls.zzbsu.zzdrv == null) {
                zzbbd.zzeo("The interstitial failed to load.");
            } else if (this.zzbls.zzbsu.zzdrv.zzadq()) {
                zzbbd.zzeo("The interstitial is already showing.");
            } else {
                Bitmap zzar;
                this.zzbls.zzbsu.zzdrv.zzav(true);
                this.zzbls.zzj(this.zzbls.zzbsu.zzdrv.getView());
                if (this.zzbls.zzbsu.zzehh != null) {
                    this.zzblu.zza(this.zzbls.zzbst, this.zzbls.zzbsu);
                }
                if (PlatformVersion.isAtLeastIceCreamSandwich()) {
                    zzaxf zzaxf = this.zzbls.zzbsu;
                    if (zzaxf.zzmu()) {
                        new zzsc(this.zzbls.zzsp, zzaxf.zzdrv.getView()).zza(zzaxf.zzdrv);
                    } else {
                        zzaxf.zzdrv.zzadl().zza(new zzam(this, zzaxf));
                    }
                }
                if (this.zzbls.zzbpa) {
                    zzbv.zzlf();
                    zzar = zzayh.zzar(this.zzbls.zzsp);
                } else {
                    zzar = null;
                }
                this.zzbon = zzbv.zzma().zzb(zzar);
                if (zzar != null) {
                    new zzao(this, this.zzbon).zzyz();
                    return;
                }
                boolean z2 = this.zzbls.zzbpa;
                zzbv.zzlf();
                zzaq zzaq = new zzaq(z2, zzayh.zzay(this.zzbls.zzsp), false, 0.0f, -1, this.zzboq, this.zzbls.zzbsu.zzbph, this.zzbls.zzbsu.zzbpi);
                int requestedOrientation = this.zzbls.zzbsu.zzdrv.getRequestedOrientation();
                if (requestedOrientation == -1) {
                    requestedOrientation = this.zzbls.zzbsu.orientation;
                }
                AdOverlayInfoParcel adOverlayInfoParcel = new AdOverlayInfoParcel(this, this, this, this.zzbls.zzbsu.zzdrv, requestedOrientation, this.zzbls.zzbsp, this.zzbls.zzbsu.zzdyi, zzaq);
                zzbv.zzld();
                zzl.zza(this.zzbls.zzsp, adOverlayInfoParcel, true);
            }
        }
    }

    public final void setImmersiveMode(boolean z) {
        Preconditions.checkMainThread("setImmersiveMode must be called on the main UI thread.");
        this.zzboq = z;
    }

    private final void zzb(Bundle bundle) {
        zzbv.zzlf().zzb(this.zzbls.zzsp, this.zzbls.zzbsp.zzdp, "gmob-apps", bundle, false);
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzil() {
        zzbgg zzbgg = this.zzbls.zzbsu != null ? this.zzbls.zzbsu.zzdrv : null;
        zzaxg zzaxg = this.zzbls.zzbsv;
        if (!(zzaxg == null || zzaxg.zzehy == null || !zzaxg.zzehy.zzdzc || zzbgg == null || !zzbv.zzlw().zzk(this.zzbls.zzsp))) {
            int i = this.zzbls.zzbsp.zzeou;
            int i2 = this.zzbls.zzbsp.zzeov;
            StringBuilder stringBuilder = new StringBuilder(23);
            stringBuilder.append(i);
            stringBuilder.append(".");
            stringBuilder.append(i2);
            this.zzblx = zzbv.zzlw().zza(stringBuilder.toString(), zzbgg.getWebView(), "", "javascript", zzit());
            if (!(this.zzblx == null || zzbgg.getView() == null)) {
                zzbv.zzlw().zza(this.zzblx, zzbgg.getView());
                zzbgg.zzaa(this.zzblx);
                zzbv.zzlw().zzo(this.zzblx);
            }
        }
        super.zzil();
        this.zzbom = true;
    }

    public final void zzke() {
        zzbv.zzma().zzb(Integer.valueOf(this.zzbon));
        if (this.zzbls.zzmj()) {
            this.zzbls.zzmh();
            this.zzbls.zzbsu = null;
            this.zzbls.zzbpa = false;
            this.zzbom = false;
        }
    }

    public final void zzkf() {
        boolean z = this.zzbls.zzbsu != null && this.zzbls.zzbsu.zzdyd;
        if (zzn(z)) {
            this.zzbou.zzxe();
            zzio();
            return;
        }
        if (!(this.zzbls.zzbsu == null || this.zzbls.zzbsu.zzehm == null)) {
            zzbv.zzlf();
            zzayh.zza(this.zzbls.zzsp, this.zzbls.zzbsp.zzdp, this.zzbls.zzbsu.zzehm);
        }
        zzio();
    }

    public final void zzb(zzawd zzawd) {
        boolean z = this.zzbls.zzbsu != null && this.zzbls.zzbsu.zzdyd;
        if (zzn(z)) {
            zza(this.zzbou.zzd(zzawd));
            return;
        }
        if (this.zzbls.zzbsu != null) {
            if (this.zzbls.zzbsu.zzdyt != null) {
                zzbv.zzlf();
                zzayh.zza(this.zzbls.zzsp, this.zzbls.zzbsp.zzdp, this.zzbls.zzbsu.zzdyt);
            }
            if (this.zzbls.zzbsu.zzdyr != null) {
                zzawd = this.zzbls.zzbsu.zzdyr;
            }
        }
        zza(zzawd);
    }

    public final void zzkg() {
        boolean z = this.zzbls.zzbsu != null && this.zzbls.zzbsu.zzdyd;
        if (zzn(z)) {
            this.zzbou.zzxf();
        }
        zzip();
    }

    @VisibleForTesting
    private static zzaxg zzb(zzaxg zzaxg) {
        zzaxg zzaxg2 = zzaxg;
        zzaxg zzaxg3;
        try {
            String jSONObject = zzatv.zzb(zzaxg2.zzehy).toString();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(AbstractAdViewAdapter.AD_UNIT_ID_PARAMETER, zzaxg2.zzeag.zzbsn);
            zzakq zzakq = new zzakq(jSONObject, null, Collections.singletonList("com.google.ads.mediation.admob.AdMobAdapter"), null, null, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), jSONObject2.toString(), null, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), null, null, null, null, null, Collections.emptyList(), null, -1);
            zzasm zzasm = zzaxg2.zzehy;
            zzakr zzakr = new zzakr(Collections.singletonList(zzakq), ((Long) zzwu.zzpz().zzd(zzaan.zzctf)).longValue(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), zzasm.zzdlu, zzasm.zzdlv, "", -1, 0, 1, null, 0, -1, -1, false);
            zzasi zzasi = zzaxg2.zzeag;
            String str = zzasm.zzbde;
            String str2 = zzasm.zzdyb;
            List emptyList = Collections.emptyList();
            List emptyList2 = Collections.emptyList();
            long j = zzasm.zzdyc;
            long j2 = zzasm.zzdye;
            List emptyList3 = Collections.emptyList();
            long j3 = zzasm.zzdlx;
            int i = zzasm.orientation;
            String str3 = zzasm.zzdyg;
            long j4 = zzasm.zzdyh;
            String str4 = zzasm.zzdyi;
            boolean z = zzasm.zzdyj;
            String str5 = zzasm.zzdyk;
            boolean z2 = zzasm.zzdym;
            boolean z3 = zzasm.zzckn;
            boolean z4 = zzasm.zzdwn;
            boolean z5 = zzasm.zzdyn;
            boolean z6 = zzasm.zzdyo;
            String str6 = zzasm.zzcgx;
            boolean z7 = zzasm.zzcko;
            boolean z8 = zzasm.zzckp;
            List emptyList4 = Collections.emptyList();
            List emptyList5 = Collections.emptyList();
            boolean z9 = z8;
            boolean z10 = zzasm.zzdyu;
            zzaso zzaso = zzasm.zzdyv;
            boolean z11 = zzasm.zzdxb;
            String str7 = zzasm.zzdxc;
            List list = zzasm.zzdlu;
            boolean z12 = zzasm.zzdlv;
            String str8 = zzasm.zzdyw;
            String str9 = zzasm.zzdyy;
            boolean z13 = zzasm.zzdyz;
            boolean z14 = zzasm.zzdxn;
            boolean z15 = zzasm.zzbph;
            z8 = zzasm.zzdzc;
            boolean z16 = z8;
            zzasm zzasm2 = new zzasm(zzasi, str, str2, emptyList, emptyList2, j, true, j2, emptyList3, j3, i, str3, j4, str4, z, str5, null, z2, z3, z4, z5, z6, str6, z7, z9, null, emptyList4, emptyList5, z10, zzaso, z11, str7, list, z12, str8, null, str9, z13, z14, z15, 0, z16, Collections.emptyList(), zzasm.zzbpi, zzasm.zzdzd, zzasm.zzdze, zzasm.zzdzf);
            zzaxg3 = zzaxg;
            return new zzaxg(zzaxg3.zzeag, zzasm2, zzakr, zzaxg3.zzbst, zzaxg3.errorCode, zzaxg3.zzehn, zzaxg3.zzeho, null, zzaxg3.zzehw, null);
        } catch (JSONException e) {
            zzaxg3 = zzaxg2;
            zzbbd.zzb("Unable to generate ad state for an interstitial ad with pooling.", e);
            return zzaxg3;
        }
    }

    public final void zzjv() {
        zzd zzadh = this.zzbls.zzbsu.zzdrv.zzadh();
        if (zzadh != null) {
            zzadh.close();
        }
    }
}
