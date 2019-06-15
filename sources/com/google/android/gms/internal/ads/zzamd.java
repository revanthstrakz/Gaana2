package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.ads.mediation.NativeAppInstallAdMapper;
import com.google.android.gms.ads.mediation.NativeContentAdMapper;
import com.google.android.gms.ads.mediation.OnContextChangedListener;
import com.google.android.gms.ads.mediation.OnImmersiveModeUpdatedListener;
import com.google.android.gms.ads.mediation.UnifiedNativeAdMapper;
import com.google.android.gms.ads.mediation.zzb;
import com.google.android.gms.ads.reward.mediation.InitializableMediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.zzc;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONObject;

@zzark
public final class zzamd extends zzalk {
    private final MediationAdapter zzdnx;
    private zzame zzdny;

    public zzamd(@NonNull MediationAdapter mediationAdapter) {
        this.zzdnx = mediationAdapter;
    }

    public final IObjectWrapper zzut() throws RemoteException {
        if (this.zzdnx instanceof MediationBannerAdapter) {
            try {
                return ObjectWrapper.wrap(((MediationBannerAdapter) this.zzdnx).getBannerView());
            } catch (Throwable th) {
                zzbbd.zzb("", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str = "Not a MediationBannerAdapter: ";
            String valueOf = String.valueOf(this.zzdnx.getClass().getCanonicalName());
            zzbbd.zzeo(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            throw new RemoteException();
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzwf zzwf, zzwb zzwb, String str, zzalm zzalm) throws RemoteException {
        zza(iObjectWrapper, zzwf, zzwb, str, null, zzalm);
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzwf zzwf, zzwb zzwb, String str, String str2, zzalm zzalm) throws RemoteException {
        zzwf zzwf2 = zzwf;
        zzwb zzwb2 = zzwb;
        if (this.zzdnx instanceof MediationBannerAdapter) {
            zzbbd.zzdn("Requesting banner ad from adapter.");
            try {
                Date date;
                MediationBannerAdapter mediationBannerAdapter = (MediationBannerAdapter) this.zzdnx;
                Bundle bundle = null;
                Set hashSet = zzwb2.zzcjd != null ? new HashSet(zzwb2.zzcjd) : null;
                if (zzwb2.zzcjb == -1) {
                    date = null;
                } else {
                    date = new Date(zzwb2.zzcjb);
                }
                zzamc zzamc = new zzamc(date, zzwb2.zzcjc, hashSet, zzwb2.zzcjj, zzm(zzwb), zzwb2.zzcjf, zzwb2.zzcjq);
                if (zzwb2.zzcjl != null) {
                    bundle = zzwb2.zzcjl.getBundle(mediationBannerAdapter.getClass().getName());
                }
                mediationBannerAdapter.requestBannerAd((Context) ObjectWrapper.unwrap(iObjectWrapper), new zzame(zzalm), zza(str, zzwb2, str2), zzc.zza(zzwf2.width, zzwf2.height, zzwf2.zzckk), zzamc, bundle);
            } catch (Throwable th) {
                zzbbd.zzb("", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str3 = "Not a MediationBannerAdapter: ";
            String valueOf = String.valueOf(this.zzdnx.getClass().getCanonicalName());
            zzbbd.zzeo(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            throw new RemoteException();
        }
    }

    public final Bundle zzuw() {
        if (this.zzdnx instanceof zzbix) {
            return ((zzbix) this.zzdnx).zzuw();
        }
        String str = "Not a v2 MediationBannerAdapter: ";
        String valueOf = String.valueOf(this.zzdnx.getClass().getCanonicalName());
        zzbbd.zzeo(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        return new Bundle();
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzwb zzwb, String str, zzalm zzalm) throws RemoteException {
        zza(iObjectWrapper, zzwb, str, null, zzalm);
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzwb zzwb, String str, String str2, zzalm zzalm) throws RemoteException {
        zzwb zzwb2 = zzwb;
        if (this.zzdnx instanceof MediationInterstitialAdapter) {
            zzbbd.zzdn("Requesting interstitial ad from adapter.");
            try {
                Date date;
                MediationInterstitialAdapter mediationInterstitialAdapter = (MediationInterstitialAdapter) this.zzdnx;
                Bundle bundle = null;
                Set hashSet = zzwb2.zzcjd != null ? new HashSet(zzwb2.zzcjd) : null;
                if (zzwb2.zzcjb == -1) {
                    date = null;
                } else {
                    date = new Date(zzwb2.zzcjb);
                }
                zzamc zzamc = new zzamc(date, zzwb2.zzcjc, hashSet, zzwb2.zzcjj, zzm(zzwb), zzwb2.zzcjf, zzwb2.zzcjq);
                if (zzwb2.zzcjl != null) {
                    bundle = zzwb2.zzcjl.getBundle(mediationInterstitialAdapter.getClass().getName());
                }
                mediationInterstitialAdapter.requestInterstitialAd((Context) ObjectWrapper.unwrap(iObjectWrapper), new zzame(zzalm), zza(str, zzwb2, str2), zzamc, bundle);
            } catch (Throwable th) {
                zzbbd.zzb("", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str3 = "Not a MediationInterstitialAdapter: ";
            String valueOf = String.valueOf(this.zzdnx.getClass().getCanonicalName());
            zzbbd.zzeo(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            throw new RemoteException();
        }
    }

    public final Bundle getInterstitialAdapterInfo() {
        if (this.zzdnx instanceof zzbiy) {
            return ((zzbiy) this.zzdnx).getInterstitialAdapterInfo();
        }
        String str = "Not a v2 MediationInterstitialAdapter: ";
        String valueOf = String.valueOf(this.zzdnx.getClass().getCanonicalName());
        zzbbd.zzeo(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        return new Bundle();
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzwb zzwb, String str, String str2, zzalm zzalm, zzacp zzacp, List<String> list) throws RemoteException {
        zzwb zzwb2 = zzwb;
        if (this.zzdnx instanceof MediationNativeAdapter) {
            try {
                Date date;
                MediationNativeAdapter mediationNativeAdapter = (MediationNativeAdapter) this.zzdnx;
                Bundle bundle = null;
                Set hashSet = zzwb2.zzcjd != null ? new HashSet(zzwb2.zzcjd) : null;
                if (zzwb2.zzcjb == -1) {
                    date = null;
                } else {
                    date = new Date(zzwb2.zzcjb);
                }
                zzamh zzamh = new zzamh(date, zzwb2.zzcjc, hashSet, zzwb2.zzcjj, zzm(zzwb), zzwb2.zzcjf, zzacp, list, zzwb2.zzcjq);
                if (zzwb2.zzcjl != null) {
                    bundle = zzwb2.zzcjl.getBundle(mediationNativeAdapter.getClass().getName());
                }
                Bundle bundle2 = bundle;
                this.zzdny = new zzame(zzalm);
                mediationNativeAdapter.requestNativeAd((Context) ObjectWrapper.unwrap(iObjectWrapper), this.zzdny, zza(str, zzwb2, str2), zzamh, bundle2);
            } catch (Throwable th) {
                zzbbd.zzb("", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str3 = "Not a MediationNativeAdapter: ";
            String valueOf = String.valueOf(this.zzdnx.getClass().getCanonicalName());
            zzbbd.zzeo(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            throw new RemoteException();
        }
    }

    public final Bundle zzux() {
        return new Bundle();
    }

    public final zzals zzuu() {
        NativeAdMapper zzvd = this.zzdny.zzvd();
        return zzvd instanceof NativeAppInstallAdMapper ? new zzamf((NativeAppInstallAdMapper) zzvd) : null;
    }

    public final zzaly zzva() {
        UnifiedNativeAdMapper zzve = this.zzdny.zzve();
        return zzve != null ? new zzamx(zzve) : null;
    }

    public final zzalv zzuv() {
        NativeAdMapper zzvd = this.zzdny.zzvd();
        return zzvd instanceof NativeContentAdMapper ? new zzamg((NativeContentAdMapper) zzvd) : null;
    }

    public final zzadx zzuz() {
        NativeCustomTemplateAd zzvf = this.zzdny.zzvf();
        return zzvf instanceof zzaea ? ((zzaea) zzvf).zzsx() : null;
    }

    public final boolean zzuy() {
        return this.zzdnx instanceof InitializableMediationRewardedVideoAdAdapter;
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzwb zzwb, String str, zzavz zzavz, String str2) throws RemoteException {
        zzwb zzwb2 = zzwb;
        if (this.zzdnx instanceof MediationRewardedVideoAdAdapter) {
            zzbbd.zzdn("Initialize rewarded video adapter.");
            try {
                Bundle bundle;
                MediationAdRequest mediationAdRequest;
                MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter = (MediationRewardedVideoAdAdapter) this.zzdnx;
                Bundle zza = zza(str2, zzwb2, null);
                if (zzwb2 != null) {
                    Date date;
                    Set hashSet = zzwb2.zzcjd != null ? new HashSet(zzwb2.zzcjd) : null;
                    if (zzwb2.zzcjb == -1) {
                        date = null;
                    } else {
                        date = new Date(zzwb2.zzcjb);
                    }
                    zzamc zzamc = new zzamc(date, zzwb2.zzcjc, hashSet, zzwb2.zzcjj, zzm(zzwb), zzwb2.zzcjf, zzwb2.zzcjq);
                    bundle = zzwb2.zzcjl != null ? zzwb2.zzcjl.getBundle(mediationRewardedVideoAdAdapter.getClass().getName()) : null;
                    mediationAdRequest = zzamc;
                } else {
                    mediationAdRequest = null;
                    bundle = mediationAdRequest;
                }
                mediationRewardedVideoAdAdapter.initialize((Context) ObjectWrapper.unwrap(iObjectWrapper), mediationAdRequest, str, new zzawc(zzavz), zza, bundle);
            } catch (Throwable th) {
                zzbbd.zzb("", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str3 = "Not a MediationRewardedVideoAdAdapter: ";
            String valueOf = String.valueOf(this.zzdnx.getClass().getCanonicalName());
            zzbbd.zzeo(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            throw new RemoteException();
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzavz zzavz, List<String> list) throws RemoteException {
        if (this.zzdnx instanceof InitializableMediationRewardedVideoAdAdapter) {
            zzbbd.zzdn("Initialize rewarded video adapter.");
            try {
                InitializableMediationRewardedVideoAdAdapter initializableMediationRewardedVideoAdAdapter = (InitializableMediationRewardedVideoAdAdapter) this.zzdnx;
                ArrayList arrayList = new ArrayList();
                for (String zza : list) {
                    arrayList.add(zza(zza, null, null));
                }
                initializableMediationRewardedVideoAdAdapter.initialize((Context) ObjectWrapper.unwrap(iObjectWrapper), new zzawc(zzavz), arrayList);
            } catch (Throwable th) {
                zzbbd.zzc("Could not initialize rewarded video adapter.", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str = "Not an InitializableMediationRewardedVideoAdAdapter: ";
            String valueOf = String.valueOf(this.zzdnx.getClass().getCanonicalName());
            zzbbd.zzeo(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            throw new RemoteException();
        }
    }

    public final void zzc(zzwb zzwb, String str) throws RemoteException {
        zza(zzwb, str, null);
    }

    public final void zza(zzwb zzwb, String str, String str2) throws RemoteException {
        if (this.zzdnx instanceof MediationRewardedVideoAdAdapter) {
            zzbbd.zzdn("Requesting rewarded video ad from adapter.");
            try {
                Date date;
                MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter = (MediationRewardedVideoAdAdapter) this.zzdnx;
                Bundle bundle = null;
                Set hashSet = zzwb.zzcjd != null ? new HashSet(zzwb.zzcjd) : null;
                if (zzwb.zzcjb == -1) {
                    date = null;
                } else {
                    date = new Date(zzwb.zzcjb);
                }
                zzamc zzamc = new zzamc(date, zzwb.zzcjc, hashSet, zzwb.zzcjj, zzm(zzwb), zzwb.zzcjf, zzwb.zzcjq);
                if (zzwb.zzcjl != null) {
                    bundle = zzwb.zzcjl.getBundle(mediationRewardedVideoAdAdapter.getClass().getName());
                }
                mediationRewardedVideoAdAdapter.loadAd(zzamc, zza(str, zzwb, str2), bundle);
            } catch (Throwable th) {
                zzbbd.zzb("", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str3 = "Not a MediationRewardedVideoAdAdapter: ";
            str = String.valueOf(this.zzdnx.getClass().getCanonicalName());
            zzbbd.zzeo(str.length() != 0 ? str3.concat(str) : new String(str3));
            throw new RemoteException();
        }
    }

    public final void showVideo() throws RemoteException {
        if (this.zzdnx instanceof MediationRewardedVideoAdAdapter) {
            zzbbd.zzdn("Show rewarded video ad from adapter.");
            try {
                ((MediationRewardedVideoAdAdapter) this.zzdnx).showVideo();
            } catch (Throwable th) {
                zzbbd.zzb("", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str = "Not a MediationRewardedVideoAdAdapter: ";
            String valueOf = String.valueOf(this.zzdnx.getClass().getCanonicalName());
            zzbbd.zzeo(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            throw new RemoteException();
        }
    }

    public final boolean isInitialized() throws RemoteException {
        if (this.zzdnx instanceof MediationRewardedVideoAdAdapter) {
            zzbbd.zzdn("Check if adapter is initialized.");
            try {
                return ((MediationRewardedVideoAdAdapter) this.zzdnx).isInitialized();
            } catch (Throwable th) {
                zzbbd.zzb("", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str = "Not a MediationRewardedVideoAdAdapter: ";
            String valueOf = String.valueOf(this.zzdnx.getClass().getCanonicalName());
            zzbbd.zzeo(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            throw new RemoteException();
        }
    }

    public final void setImmersiveMode(boolean z) throws RemoteException {
        if (this.zzdnx instanceof OnImmersiveModeUpdatedListener) {
            try {
                ((OnImmersiveModeUpdatedListener) this.zzdnx).onImmersiveModeUpdated(z);
                return;
            } catch (Throwable th) {
                zzbbd.zzb("", th);
                return;
            }
        }
        String str = "Not an OnImmersiveModeUpdatedListener: ";
        String valueOf = String.valueOf(this.zzdnx.getClass().getCanonicalName());
        zzbbd.zzen(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }

    public final zzyp getVideoController() {
        if (!(this.zzdnx instanceof zzb)) {
            return null;
        }
        try {
            return ((zzb) this.zzdnx).getVideoController();
        } catch (Throwable th) {
            zzbbd.zzb("", th);
            return null;
        }
    }

    public final void showInterstitial() throws RemoteException {
        if (this.zzdnx instanceof MediationInterstitialAdapter) {
            zzbbd.zzdn("Showing interstitial from adapter.");
            try {
                ((MediationInterstitialAdapter) this.zzdnx).showInterstitial();
            } catch (Throwable th) {
                zzbbd.zzb("", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str = "Not a MediationInterstitialAdapter: ";
            String valueOf = String.valueOf(this.zzdnx.getClass().getCanonicalName());
            zzbbd.zzeo(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            throw new RemoteException();
        }
    }

    public final void destroy() throws RemoteException {
        try {
            this.zzdnx.onDestroy();
        } catch (Throwable th) {
            zzbbd.zzb("", th);
            RemoteException remoteException = new RemoteException();
        }
    }

    public final void pause() throws RemoteException {
        try {
            this.zzdnx.onPause();
        } catch (Throwable th) {
            zzbbd.zzb("", th);
            RemoteException remoteException = new RemoteException();
        }
    }

    public final void resume() throws RemoteException {
        try {
            this.zzdnx.onResume();
        } catch (Throwable th) {
            zzbbd.zzb("", th);
            RemoteException remoteException = new RemoteException();
        }
    }

    public final void zzj(IObjectWrapper iObjectWrapper) throws RemoteException {
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        if (this.zzdnx instanceof OnContextChangedListener) {
            ((OnContextChangedListener) this.zzdnx).onContextChanged(context);
        }
    }

    private final Bundle zza(String str, zzwb zzwb, String str2) throws RemoteException {
        String str3 = "Server parameters: ";
        String valueOf = String.valueOf(str);
        zzbbd.zzeo(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
        try {
            Bundle bundle;
            Bundle bundle2 = new Bundle();
            if (str != null) {
                JSONObject jSONObject = new JSONObject(str);
                bundle = new Bundle();
                Iterator keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String str4 = (String) keys.next();
                    bundle.putString(str4, jSONObject.getString(str4));
                }
            } else {
                bundle = bundle2;
            }
            if (this.zzdnx instanceof AdMobAdapter) {
                bundle.putString("adJson", str2);
                if (zzwb != null) {
                    bundle.putInt("tagForChildDirectedTreatment", zzwb.zzcjf);
                }
            }
            return bundle;
        } catch (Throwable th) {
            zzbbd.zzb("", th);
            RemoteException remoteException = new RemoteException();
        }
    }

    private static boolean zzm(zzwb zzwb) {
        if (!zzwb.zzcje) {
            zzwu.zzpv();
            if (!zzbat.zzaaq()) {
                return false;
            }
        }
        return true;
    }
}
