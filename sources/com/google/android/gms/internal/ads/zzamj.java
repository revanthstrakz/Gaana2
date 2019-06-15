package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationBannerListener;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationInterstitialListener;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;

@zzark
public final class zzamj<NETWORK_EXTRAS extends NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> implements MediationBannerListener, MediationInterstitialListener {
    private final zzalm zzdnz;

    public zzamj(zzalm zzalm) {
        this.zzdnz = zzalm;
    }

    public final void onClick(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzbbd.zzdn("Adapter called onClick.");
        zzwu.zzpv();
        if (zzbat.zzaar()) {
            try {
                this.zzdnz.onAdClicked();
                return;
            } catch (RemoteException e) {
                zzbbd.zzd("#007 Could not call remote method.", e);
                return;
            }
        }
        zzbbd.zzd("#008 Must be called on the main UI thread.", null);
        zzbat.zztu.post(new zzamk(this));
    }

    public final void onDismissScreen(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzbbd.zzdn("Adapter called onDismissScreen.");
        zzwu.zzpv();
        if (zzbat.zzaar()) {
            try {
                this.zzdnz.onAdClosed();
                return;
            } catch (RemoteException e) {
                zzbbd.zzd("#007 Could not call remote method.", e);
                return;
            }
        }
        zzbbd.zzeo("#008 Must be called on the main UI thread.");
        zzbat.zztu.post(new zzamn(this));
    }

    public final void onFailedToReceiveAd(MediationBannerAdapter<?, ?> mediationBannerAdapter, ErrorCode errorCode) {
        String valueOf = String.valueOf(errorCode);
        StringBuilder stringBuilder = new StringBuilder(47 + String.valueOf(valueOf).length());
        stringBuilder.append("Adapter called onFailedToReceiveAd with error. ");
        stringBuilder.append(valueOf);
        zzbbd.zzdn(stringBuilder.toString());
        zzwu.zzpv();
        if (zzbat.zzaar()) {
            try {
                this.zzdnz.onAdFailedToLoad(zzamv.zza(errorCode));
                return;
            } catch (RemoteException e) {
                zzbbd.zzd("#007 Could not call remote method.", e);
                return;
            }
        }
        zzbbd.zzd("#008 Must be called on the main UI thread.", null);
        zzbat.zztu.post(new zzamo(this, errorCode));
    }

    public final void onLeaveApplication(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzbbd.zzdn("Adapter called onLeaveApplication.");
        zzwu.zzpv();
        if (zzbat.zzaar()) {
            try {
                this.zzdnz.onAdLeftApplication();
                return;
            } catch (RemoteException e) {
                zzbbd.zzd("#007 Could not call remote method.", e);
                return;
            }
        }
        zzbbd.zzd("#008 Must be called on the main UI thread.", null);
        zzbat.zztu.post(new zzamp(this));
    }

    public final void onPresentScreen(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzbbd.zzdn("Adapter called onPresentScreen.");
        zzwu.zzpv();
        if (zzbat.zzaar()) {
            try {
                this.zzdnz.onAdOpened();
                return;
            } catch (RemoteException e) {
                zzbbd.zzd("#007 Could not call remote method.", e);
                return;
            }
        }
        zzbbd.zzd("#008 Must be called on the main UI thread.", null);
        zzbat.zztu.post(new zzamq(this));
    }

    public final void onReceivedAd(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzbbd.zzdn("Adapter called onReceivedAd.");
        zzwu.zzpv();
        if (zzbat.zzaar()) {
            try {
                this.zzdnz.onAdLoaded();
                return;
            } catch (RemoteException e) {
                zzbbd.zzd("#007 Could not call remote method.", e);
                return;
            }
        }
        zzbbd.zzd("#008 Must be called on the main UI thread.", null);
        zzbat.zztu.post(new zzamr(this));
    }

    public final void onDismissScreen(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzbbd.zzdn("Adapter called onDismissScreen.");
        zzwu.zzpv();
        if (zzbat.zzaar()) {
            try {
                this.zzdnz.onAdClosed();
                return;
            } catch (RemoteException e) {
                zzbbd.zzd("#007 Could not call remote method.", e);
                return;
            }
        }
        zzbbd.zzd("#008 Must be called on the main UI thread.", null);
        zzbat.zztu.post(new zzams(this));
    }

    public final void onFailedToReceiveAd(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter, ErrorCode errorCode) {
        String valueOf = String.valueOf(errorCode);
        StringBuilder stringBuilder = new StringBuilder(47 + String.valueOf(valueOf).length());
        stringBuilder.append("Adapter called onFailedToReceiveAd with error ");
        stringBuilder.append(valueOf);
        stringBuilder.append(".");
        zzbbd.zzdn(stringBuilder.toString());
        zzwu.zzpv();
        if (zzbat.zzaar()) {
            try {
                this.zzdnz.onAdFailedToLoad(zzamv.zza(errorCode));
                return;
            } catch (RemoteException e) {
                zzbbd.zzd("#007 Could not call remote method.", e);
                return;
            }
        }
        zzbbd.zzd("#008 Must be called on the main UI thread.", null);
        zzbat.zztu.post(new zzamt(this, errorCode));
    }

    public final void onLeaveApplication(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzbbd.zzdn("Adapter called onLeaveApplication.");
        zzwu.zzpv();
        if (zzbat.zzaar()) {
            try {
                this.zzdnz.onAdLeftApplication();
                return;
            } catch (RemoteException e) {
                zzbbd.zzd("#007 Could not call remote method.", e);
                return;
            }
        }
        zzbbd.zzd("#008 Must be called on the main UI thread.", null);
        zzbat.zztu.post(new zzamu(this));
    }

    public final void onPresentScreen(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzbbd.zzdn("Adapter called onPresentScreen.");
        zzwu.zzpv();
        if (zzbat.zzaar()) {
            try {
                this.zzdnz.onAdOpened();
                return;
            } catch (RemoteException e) {
                zzbbd.zzd("#007 Could not call remote method.", e);
                return;
            }
        }
        zzbbd.zzd("#008 Must be called on the main UI thread.", null);
        zzbat.zztu.post(new zzaml(this));
    }

    public final void onReceivedAd(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzbbd.zzdn("Adapter called onReceivedAd.");
        zzwu.zzpv();
        if (zzbat.zzaar()) {
            try {
                this.zzdnz.onAdLoaded();
                return;
            } catch (RemoteException e) {
                zzbbd.zzd("#007 Could not call remote method.", e);
                return;
            }
        }
        zzbbd.zzd("#008 Must be called on the main UI thread.", null);
        zzbat.zztu.post(new zzamm(this));
    }
}
