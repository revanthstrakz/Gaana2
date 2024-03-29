package com.google.android.gms.ads.internal;

import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewParent;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.internal.gmsg.zzb;
import com.google.android.gms.ads.internal.gmsg.zzd;
import com.google.android.gms.ads.internal.overlay.zzt;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import com.google.android.gms.internal.ads.zzaan;
import com.google.android.gms.internal.ads.zzaay;
import com.google.android.gms.internal.ads.zzaba;
import com.google.android.gms.internal.ads.zzabg;
import com.google.android.gms.internal.ads.zzaow;
import com.google.android.gms.internal.ads.zzapc;
import com.google.android.gms.internal.ads.zzapm;
import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzarm;
import com.google.android.gms.internal.ads.zzaul;
import com.google.android.gms.internal.ads.zzauu;
import com.google.android.gms.internal.ads.zzavb;
import com.google.android.gms.internal.ads.zzawd;
import com.google.android.gms.internal.ads.zzawz;
import com.google.android.gms.internal.ads.zzaxf;
import com.google.android.gms.internal.ads.zzaxg;
import com.google.android.gms.internal.ads.zzaxh;
import com.google.android.gms.internal.ads.zzaxq;
import com.google.android.gms.internal.ads.zzaxs;
import com.google.android.gms.internal.ads.zzaxx;
import com.google.android.gms.internal.ads.zzaxz;
import com.google.android.gms.internal.ads.zzayh;
import com.google.android.gms.internal.ads.zzbat;
import com.google.android.gms.internal.ads.zzbbd;
import com.google.android.gms.internal.ads.zzbht;
import com.google.android.gms.internal.ads.zzrf;
import com.google.android.gms.internal.ads.zzvt;
import com.google.android.gms.internal.ads.zzwb;
import com.google.android.gms.internal.ads.zzwc;
import com.google.android.gms.internal.ads.zzwf;
import com.google.android.gms.internal.ads.zzwu;
import com.google.android.gms.internal.ads.zzwx;
import com.google.android.gms.internal.ads.zzxa;
import com.google.android.gms.internal.ads.zzxm;
import com.google.android.gms.internal.ads.zzxq;
import com.google.android.gms.internal.ads.zzxt;
import com.google.android.gms.internal.ads.zzxz;
import com.google.android.gms.internal.ads.zzyp;
import com.google.android.gms.internal.ads.zzyv;
import com.google.android.gms.internal.ads.zzzu;
import com.google.android.gms.internal.ads.zzzw;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

@zzark
public abstract class zza extends zzxm implements zzb, zzd, zzt, zzapm, zzarm, zzaxq, zzvt {
    protected zzaba zzbln;
    protected zzaay zzblo;
    private zzaay zzblp;
    protected boolean zzblq = false;
    protected final zzbl zzblr;
    protected final zzbw zzbls;
    @Nullable
    protected transient zzwb zzblt;
    protected final zzrf zzblu;
    private final Bundle zzblv = new Bundle();
    private boolean zzblw = false;
    @Nullable
    protected IObjectWrapper zzblx;
    protected final zzv zzbly;

    protected static boolean zza(zzwb zzwb) {
        Bundle bundle = zzwb.zzcjl.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
        return bundle == null || !bundle.containsKey("gw");
    }

    public zzyp getVideoController() {
        return null;
    }

    public abstract void zza(zzaxg zzaxg, zzaba zzaba);

    /* Access modifiers changed, original: 0000 */
    public boolean zza(zzaxf zzaxf) {
        return false;
    }

    public abstract boolean zza(@Nullable zzaxf zzaxf, zzaxf zzaxf2);

    public abstract boolean zza(zzwb zzwb, zzaba zzaba);

    public final zzv zzid() {
        return this.zzbly;
    }

    @VisibleForTesting
    zza(zzbw zzbw, @Nullable zzbl zzbl, zzv zzv) {
        this.zzbls = zzbw;
        this.zzblr = new zzbl(this);
        this.zzbly = zzv;
        zzbv.zzlf().zzai(this.zzbls.zzsp);
        zzbv.zzlf().zzaj(this.zzbls.zzsp);
        zzaxx.zzag(this.zzbls.zzsp);
        zzbv.zzlr().initialize(this.zzbls.zzsp);
        zzbv.zzlj().zzd(this.zzbls.zzsp, this.zzbls.zzbsp);
        zzbv.zzll().initialize(this.zzbls.zzsp);
        this.zzblu = zzbv.zzlj().zzym();
        zzbv.zzli().initialize(this.zzbls.zzsp);
        zzbv.zzmc().initialize(this.zzbls.zzsp);
    }

    public final void zza(zzaay zzaay) {
        this.zzbln = new zzaba(((Boolean) zzwu.zzpz().zzd(zzaan.zzcpw)).booleanValue(), "load_ad", this.zzbls.zzbst.zzckk);
        this.zzblp = new zzaay(-1, null, null);
        if (zzaay == null) {
            this.zzblo = new zzaay(-1, null, null);
        } else {
            this.zzblo = new zzaay(zzaay.getTime(), zzaay.zzrd(), zzaay.zzre());
        }
    }

    public void destroy() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: destroy");
        this.zzblr.cancel();
        this.zzblu.zzi(this.zzbls.zzbsu);
        zzbw zzbw = this.zzbls;
        if (zzbw.zzbsq != null) {
            zzbw.zzbsq.zzmn();
        }
        zzbw.zzbsy = null;
        zzbw.zzbta = null;
        zzbw.zzbsz = null;
        zzbw.zzbto = null;
        zzbw.zzbtb = null;
        zzbw.zzr(false);
        if (zzbw.zzbsq != null) {
            zzbw.zzbsq.removeAllViews();
        }
        zzbw.zzmh();
        zzbw.zzmi();
        zzbw.zzbsu = null;
        this.zzblx = null;
    }

    public final IObjectWrapper zzie() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: getAdFrame");
        return ObjectWrapper.wrap(this.zzbls.zzbsq);
    }

    @Nullable
    public final zzwf zzif() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: getAdSize");
        return this.zzbls.zzbst == null ? null : new zzzu(this.zzbls.zzbst);
    }

    public final boolean isReady() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: isLoaded");
        return this.zzbls.zzbsr == null && this.zzbls.zzbss == null && this.zzbls.zzbsu != null;
    }

    public void setManualImpressionsEnabled(boolean z) {
        zzbbd.zzeo("Attempt to call setManualImpressionsEnabled for an unsupported ad type.");
    }

    public boolean zzb(zzwb zzwb) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: loadAd");
        zzbv.zzll().zzod();
        this.zzblv.clear();
        this.zzblw = false;
        zzwb = zzwb.zzpm();
        zzwb.extras.putInt("dv", DynamiteModule.getRemoteVersion(this.zzbls.zzsp, ModuleDescriptor.MODULE_ID));
        zzwb.extras.putBoolean(AdMobAdapter.NEW_BUNDLE, true);
        if (DeviceProperties.isSidewinder(this.zzbls.zzsp) && zzwb.zzcjj != null) {
            zzwb = new zzwc(zzwb).zza(null).zzpn();
        }
        if (this.zzbls.zzbsr == null && this.zzbls.zzbss == null) {
            zzbbd.zzen("Starting ad request.");
            String str = "SDK version: ";
            String valueOf = String.valueOf(this.zzbls.zzbsp.zzdp);
            zzbbd.zzen(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            zza(null);
            this.zzblo = this.zzbln.zzrg();
            if (zzwb.zzcje) {
                zzbbd.zzen("This request is sent from a test device.");
            } else {
                zzwu.zzpv();
                str = zzbat.zzbf(this.zzbls.zzsp);
                StringBuilder stringBuilder = new StringBuilder(71 + String.valueOf(str).length());
                stringBuilder.append("Use AdRequest.Builder.addTestDevice(\"");
                stringBuilder.append(str);
                stringBuilder.append("\") to get test ads on this device.");
                zzbbd.zzen(stringBuilder.toString());
            }
            this.zzblr.zzf(zzwb);
            this.zzblq = zza(zzwb, this.zzbln);
            return this.zzblq;
        }
        if (this.zzblt != null) {
            zzbbd.zzeo("Aborting last ad request since another ad request is already in progress. The current request object will still be cached for future refreshes.");
        } else {
            zzbbd.zzeo("Loading already in progress, saving this object for future refreshes.");
        }
        this.zzblt = zzwb;
        return false;
    }

    public final void zza(zzaxg zzaxg) {
        if (!(zzaxg.zzehy.zzdyh == -1 || TextUtils.isEmpty(zzaxg.zzehy.zzdyq))) {
            long zzaq = zzaq(zzaxg.zzehy.zzdyq);
            if (zzaq != -1) {
                zzaay zzao = this.zzbln.zzao(zzaxg.zzehy.zzdyh + zzaq);
                this.zzbln.zza(zzao, "stc");
            }
        }
        this.zzbln.zzbp(zzaxg.zzehy.zzdyq);
        this.zzbln.zza(this.zzblo, "arf");
        this.zzblp = this.zzbln.zzrg();
        this.zzbln.zzg("gqi", zzaxg.zzehy.zzcgx);
        this.zzbls.zzbsr = null;
        this.zzbls.zzbsv = zzaxg;
        zzaxg.zzehw.zza(new zzb(this, zzaxg));
        zzaxg.zzehw.zza(com.google.android.gms.internal.ads.zzuo.zza.zzb.AD_LOADED);
        zza(zzaxg, this.zzbln);
    }

    public void zzb(zzaxf zzaxf) {
        this.zzbln.zza(this.zzblp, "awr");
        this.zzbls.zzbss = null;
        if (!(zzaxf.errorCode == -2 || zzaxf.errorCode == 3 || this.zzbls.zzmg() == null)) {
            zzbv.zzlj().zzys().zzb(this.zzbls.zzmg());
        }
        if (zzaxf.errorCode == -1) {
            this.zzblq = false;
            return;
        }
        if (zza(zzaxf)) {
            zzbbd.zzdn("Ad refresh scheduled.");
        }
        if (zzaxf.errorCode != -2) {
            if (zzaxf.errorCode == 3) {
                zzaxf.zzehw.zza(com.google.android.gms.internal.ads.zzuo.zza.zzb.AD_FAILED_TO_LOAD_NO_FILL);
            } else {
                zzaxf.zzehw.zza(com.google.android.gms.internal.ads.zzuo.zza.zzb.AD_FAILED_TO_LOAD);
            }
            zzbr(zzaxf.errorCode);
            return;
        }
        if (this.zzbls.zzbtu == null) {
            this.zzbls.zzbtu = new zzaxs(this.zzbls.zzbsn);
        }
        if (this.zzbls.zzbsq != null) {
            this.zzbls.zzbsq.zzmm().zzeg(zzaxf.zzdyy);
        }
        this.zzblu.zzh(this.zzbls.zzbsu);
        if (zza(this.zzbls.zzbsu, zzaxf)) {
            this.zzbls.zzbsu = zzaxf;
            zzbw zzbw = this.zzbls;
            if (zzbw.zzbsw != null) {
                if (zzbw.zzbsu != null) {
                    zzbw.zzbsw.zzas(zzbw.zzbsu.zzehn);
                    zzbw.zzbsw.zzat(zzbw.zzbsu.zzeho);
                    zzbw.zzbsw.zzak(zzbw.zzbsu.zzdyd);
                }
                zzbw.zzbsw.zzaj(zzbw.zzbst.zzckl);
            }
            this.zzbln.zzg("is_mraid", this.zzbls.zzbsu.zzmu() ? "1" : "0");
            this.zzbln.zzg("is_mediation", this.zzbls.zzbsu.zzdyd ? "1" : "0");
            if (!(this.zzbls.zzbsu.zzdrv == null || this.zzbls.zzbsu.zzdrv.zzadl() == null)) {
                this.zzbln.zzg("is_delay_pl", this.zzbls.zzbsu.zzdrv.zzadl().zzaee() ? "1" : "0");
            }
            this.zzbln.zza(this.zzblo, "ttc");
            if (zzbv.zzlj().zzyh() != null) {
                zzbv.zzlj().zzyh().zza(this.zzbln);
            }
            zziq();
            if (this.zzbls.zzmj()) {
                zzil();
            }
        }
        if (zzaxf.zzdlu != null) {
            zzbv.zzlf().zza(this.zzbls.zzsp, zzaxf.zzdlu);
        }
    }

    public void onAdClicked() {
        if (this.zzbls.zzbsu == null) {
            zzbbd.zzeo("Ad state was null when trying to ping click URLs.");
            return;
        }
        zzbbd.zzdn("Pinging click URLs.");
        if (this.zzbls.zzbsw != null) {
            this.zzbls.zzbsw.zzxw();
        }
        if (this.zzbls.zzbsu.zzdlq != null) {
            zzbv.zzlf();
            zzayh.zza(this.zzbls.zzsp, this.zzbls.zzbsp.zzdp, zza(this.zzbls.zzbsu.zzdlq, this.zzbls.zzbsu.zzdzf));
        }
        if (this.zzbls.zzbsx != null) {
            try {
                this.zzbls.zzbsx.onAdClicked();
            } catch (RemoteException e) {
                zzbbd.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    public final void zzig() {
        zzij();
    }

    public final void zza(String str, Bundle bundle) {
        this.zzblv.putAll(bundle);
        if (this.zzblw && this.zzbls.zzbta != null) {
            try {
                this.zzbls.zzbta.onAdMetadataChanged();
            } catch (RemoteException e) {
                zzbbd.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    public final void onAppEvent(String str, @Nullable String str2) {
        if (this.zzbls.zzbsz != null) {
            try {
                this.zzbls.zzbsz.onAppEvent(str, str2);
            } catch (RemoteException e) {
                zzbbd.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    public final void zzih() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: recordManualImpression");
        if (this.zzbls.zzbsu == null) {
            zzbbd.zzeo("Ad state was null when trying to ping manual tracking URLs.");
            return;
        }
        zzbbd.zzdn("Pinging manual tracking URLs.");
        if (!this.zzbls.zzbsu.zzehu) {
            List arrayList = new ArrayList();
            if (this.zzbls.zzbsu.zzdyf != null) {
                arrayList.addAll(this.zzbls.zzbsu.zzdyf);
            }
            if (!(this.zzbls.zzbsu.zzdnb == null || this.zzbls.zzbsu.zzdnb.zzdlc == null)) {
                arrayList.addAll(this.zzbls.zzbsu.zzdnb.zzdlc);
            }
            if (!arrayList.isEmpty()) {
                zzbv.zzlf();
                zzayh.zza(this.zzbls.zzsp, this.zzbls.zzbsp.zzdp, arrayList);
                this.zzbls.zzbsu.zzehu = true;
            }
        }
    }

    public void pause() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: pause");
    }

    public void resume() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: resume");
    }

    /* Access modifiers changed, original: protected */
    public boolean zzc(zzwb zzwb) {
        if (this.zzbls.zzbsq == null) {
            return false;
        }
        ViewParent parent = this.zzbls.zzbsq.getParent();
        if (!(parent instanceof View)) {
            return false;
        }
        View view = (View) parent;
        return zzbv.zzlf().zza(view, view.getContext());
    }

    public final void zza(zzxa zzxa) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setAdListener");
        this.zzbls.zzbsy = zzxa;
    }

    public final void zza(zzavb zzavb) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setRewardedVideoAdListener");
        this.zzbls.zzbtp = zzavb;
    }

    public final void zza(zzauu zzauu) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setRewardedAdSkuListener");
        this.zzbls.zzbtq = zzauu;
    }

    public final void setUserId(String str) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setUserId");
        this.zzbls.zzbtr = str;
    }

    public final void zzap(String str) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setCustomData");
        this.zzbls.zzbts = str;
    }

    public final void zza(zzwx zzwx) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setAdClickListener");
        this.zzbls.zzbsx = zzwx;
    }

    public final void zza(zzwf zzwf) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setAdSize");
        this.zzbls.zzbst = zzwf;
        if (!(this.zzbls.zzbsu == null || this.zzbls.zzbsu.zzdrv == null || this.zzbls.zzbtw != 0)) {
            this.zzbls.zzbsu.zzdrv.zza(zzbht.zzb(zzwf));
        }
        if (this.zzbls.zzbsq != null) {
            if (this.zzbls.zzbsq.getChildCount() > 1) {
                this.zzbls.zzbsq.removeView(this.zzbls.zzbsq.getNextView());
            }
            this.zzbls.zzbsq.setMinimumWidth(zzwf.widthPixels);
            this.zzbls.zzbsq.setMinimumHeight(zzwf.heightPixels);
            this.zzbls.zzbsq.requestLayout();
        }
    }

    public final void zza(zzxt zzxt) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setAppEventListener");
        this.zzbls.zzbsz = zzxt;
    }

    public final void zza(zzxq zzxq) {
        this.zzbls.zzbta = zzxq;
    }

    public final Bundle getAdMetadata() {
        if (this.zzblw) {
            return this.zzblv;
        }
        return new Bundle();
    }

    public void zza(zzaow zzaow) {
        zzbbd.zzeo("#006 Unexpected call to a deprecated method.");
    }

    public void zza(zzabg zzabg) {
        throw new IllegalStateException("#005 Unexpected call to an abstract (unimplemented) method.");
    }

    public final void zza(zzapc zzapc, String str) {
        zzbbd.zzeo("#006 Unexpected call to a deprecated method.");
    }

    public final void zza(zzxz zzxz) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setCorrelationIdProvider");
        this.zzbls.zzbtb = zzxz;
    }

    public final void stopLoading() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: stopLoading");
        this.zzblq = false;
        this.zzbls.zzr(true);
    }

    public final boolean isLoading() {
        return this.zzblq;
    }

    public final void zza(@Nullable zzzw zzzw) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setVideoOptions");
        this.zzbls.zzbtj = zzzw;
    }

    public final void zza(@Nullable zzyv zzyv) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setIconAdOptions");
        this.zzbls.zzbtl = zzyv;
    }

    public void setImmersiveMode(boolean z) {
        throw new IllegalStateException("#005 Unexpected call to an abstract (unimplemented) method.");
    }

    @VisibleForTesting
    private static long zzaq(String str) {
        int indexOf = str.indexOf("ufe");
        int indexOf2 = str.indexOf(44, indexOf);
        if (indexOf2 == -1) {
            indexOf2 = str.length();
        }
        try {
            return Long.parseLong(str.substring(indexOf + 4, indexOf2));
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            zzbbd.zzb("", e);
            return -1;
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzg(View view) {
        zzbx zzbx = this.zzbls.zzbsq;
        if (zzbx != null) {
            zzbx.addView(view, zzbv.zzlh().zzzz());
        }
    }

    /* Access modifiers changed, original: protected */
    public void zzii() {
        zzaxz.v("Ad closing.");
        if (this.zzbls.zzbsy != null) {
            try {
                this.zzbls.zzbsy.onAdClosed();
            } catch (RemoteException e) {
                zzbbd.zzd("#007 Could not call remote method.", e);
            }
        }
        if (this.zzbls.zzbtp != null) {
            try {
                this.zzbls.zzbtp.onRewardedVideoAdClosed();
            } catch (RemoteException e2) {
                zzbbd.zzd("#007 Could not call remote method.", e2);
            }
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzij() {
        zzaxz.v("Ad leaving application.");
        if (this.zzbls.zzbsy != null) {
            try {
                this.zzbls.zzbsy.onAdLeftApplication();
            } catch (RemoteException e) {
                zzbbd.zzd("#007 Could not call remote method.", e);
            }
        }
        if (this.zzbls.zzbtp != null) {
            try {
                this.zzbls.zzbtp.onRewardedVideoAdLeftApplication();
            } catch (RemoteException e2) {
                zzbbd.zzd("#007 Could not call remote method.", e2);
            }
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzik() {
        zzaxz.v("Ad opening.");
        if (this.zzbls.zzbsy != null) {
            try {
                this.zzbls.zzbsy.onAdOpened();
            } catch (RemoteException e) {
                zzbbd.zzd("#007 Could not call remote method.", e);
            }
        }
        if (this.zzbls.zzbtp != null) {
            try {
                this.zzbls.zzbtp.onRewardedVideoAdOpened();
            } catch (RemoteException e2) {
                zzbbd.zzd("#007 Could not call remote method.", e2);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void zzil() {
        zzm(false);
    }

    /* Access modifiers changed, original: protected */
    public void zzm(boolean z) {
        zzaxz.v("Ad finished loading.");
        this.zzblq = z;
        this.zzblw = true;
        if (this.zzbls.zzbsy != null) {
            try {
                this.zzbls.zzbsy.onAdLoaded();
            } catch (RemoteException e) {
                zzbbd.zzd("#007 Could not call remote method.", e);
            }
        }
        if (this.zzbls.zzbtp != null) {
            try {
                this.zzbls.zzbtp.onRewardedVideoAdLoaded();
            } catch (RemoteException e2) {
                zzbbd.zzd("#007 Could not call remote method.", e2);
            }
        }
        if (this.zzbls.zzbta != null) {
            try {
                this.zzbls.zzbta.onAdMetadataChanged();
            } catch (RemoteException e22) {
                zzbbd.zzd("#007 Could not call remote method.", e22);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void zzbr(int i) {
        zzg(i, false);
    }

    /* Access modifiers changed, original: protected */
    public void zzg(int i, boolean z) {
        StringBuilder stringBuilder = new StringBuilder(30);
        stringBuilder.append("Failed to load ad: ");
        stringBuilder.append(i);
        zzbbd.zzeo(stringBuilder.toString());
        this.zzblq = z;
        if (this.zzbls.zzbsy != null) {
            try {
                this.zzbls.zzbsy.onAdFailedToLoad(i);
            } catch (RemoteException e) {
                zzbbd.zzd("#007 Could not call remote method.", e);
            }
        }
        if (this.zzbls.zzbtp != null) {
            try {
                this.zzbls.zzbtp.onRewardedVideoAdFailedToLoad(i);
            } catch (RemoteException e2) {
                zzbbd.zzd("#007 Could not call remote method.", e2);
            }
        }
        if (this.zzbls.zzbtf != null) {
            try {
                this.zzbls.zzbtf.zzcm(i);
            } catch (RemoteException e3) {
                zzbbd.zzd("#007 Could not call remote method.", e3);
            }
        }
    }

    public final void zzim() {
        zzbbd.zzen("Ad impression.");
        if (this.zzbls.zzbsy != null) {
            try {
                this.zzbls.zzbsy.onAdImpression();
            } catch (RemoteException e) {
                zzbbd.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    public final void zzin() {
        zzbbd.zzen("Ad clicked.");
        if (this.zzbls.zzbsy != null) {
            try {
                this.zzbls.zzbsy.onAdClicked();
            } catch (RemoteException e) {
                zzbbd.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzio() {
        if (this.zzbls.zzbtp != null) {
            try {
                this.zzbls.zzbtp.onRewardedVideoStarted();
            } catch (RemoteException e) {
                zzbbd.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final void zza(@Nullable zzawd zzawd) {
        if (this.zzbls.zzbtp != null) {
            try {
                String str = "";
                int i = 1;
                if (zzawd != null) {
                    str = zzawd.type;
                    i = zzawd.zzefo;
                }
                zzaul zzaul = new zzaul(str, i);
                this.zzbls.zzbtp.zza(zzaul);
                if (this.zzbls.zzbtq != null) {
                    this.zzbls.zzbtq.zza(zzaul, this.zzbls.zzbsv.zzeag.zzdws);
                }
            } catch (RemoteException e) {
                zzbbd.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzip() {
        if (this.zzbls.zzbtp != null) {
            try {
                this.zzbls.zzbtp.onRewardedVideoCompleted();
            } catch (RemoteException e) {
                zzbbd.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final List<String> zzc(List<String> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (String zzb : list) {
            arrayList.add(zzawz.zzb(zzb, this.zzbls.zzsp));
        }
        return arrayList;
    }

    public final void zziq() {
        zzaxf zzaxf = this.zzbls.zzbsu;
        if (zzaxf != null && !TextUtils.isEmpty(zzaxf.zzdyy) && !zzaxf.zzehv && zzbv.zzlp().zzaah()) {
            zzbbd.zzdn("Sending troubleshooting signals to the server.");
            zzbv.zzlp().zzb(this.zzbls.zzsp, this.zzbls.zzbsp.zzdp, zzaxf.zzdyy, this.zzbls.zzbsn);
            zzaxf.zzehv = true;
        }
    }

    public final void zza(HashSet<zzaxh> hashSet) {
        this.zzbls.zza(hashSet);
    }

    /* Access modifiers changed, original: protected|final */
    public final List<String> zza(List<String> list, boolean z) {
        ArrayList arrayList = new ArrayList(list.size());
        for (String zzb : list) {
            arrayList.add(zzawz.zzb(zzb, this.zzbls.zzsp, z));
        }
        return arrayList;
    }

    public String getAdUnitId() {
        return this.zzbls.zzbsn;
    }

    public final zzxt zzir() {
        return this.zzbls.zzbsz;
    }

    public final zzxa zzis() {
        return this.zzbls.zzbsy;
    }

    /* Access modifiers changed, original: protected|final */
    @Nullable
    public final String zzit() {
        zzaxg zzaxg = this.zzbls.zzbsv;
        if (zzaxg == null) {
            return "javascript";
        }
        if (zzaxg.zzehy == null) {
            return "javascript";
        }
        String str = zzaxg.zzehy.zzdzd;
        if (TextUtils.isEmpty(str)) {
            return "javascript";
        }
        try {
            return new JSONObject(str).optInt("media_type", -1) == 0 ? null : "javascript";
        } catch (JSONException e) {
            zzbbd.zzc("", e);
            return "javascript";
        }
    }
}
