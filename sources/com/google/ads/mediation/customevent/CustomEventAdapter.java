package com.google.ads.mediation.customevent;

import android.app.Activity;
import android.view.View;
import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.AdSize;
import com.google.ads.mediation.MediationAdRequest;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationBannerListener;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.customevent.CustomEventExtras;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzbbd;

@KeepName
public final class CustomEventAdapter implements MediationBannerAdapter<CustomEventExtras, CustomEventServerParameters>, MediationInterstitialAdapter<CustomEventExtras, CustomEventServerParameters> {
    private View zzim;
    @VisibleForTesting
    private CustomEventBanner zzin;
    @VisibleForTesting
    private CustomEventInterstitial zzio;

    @VisibleForTesting
    static final class zza implements CustomEventBannerListener {
        private final CustomEventAdapter zzip;
        private final MediationBannerListener zziq;

        public zza(CustomEventAdapter customEventAdapter, MediationBannerListener mediationBannerListener) {
            this.zzip = customEventAdapter;
            this.zziq = mediationBannerListener;
        }

        public final void onReceivedAd(View view) {
            zzbbd.zzdn("Custom event adapter called onReceivedAd.");
            this.zzip.zza(view);
            this.zziq.onReceivedAd(this.zzip);
        }

        public final void onFailedToReceiveAd() {
            zzbbd.zzdn("Custom event adapter called onFailedToReceiveAd.");
            this.zziq.onFailedToReceiveAd(this.zzip, ErrorCode.NO_FILL);
        }

        public final void onClick() {
            zzbbd.zzdn("Custom event adapter called onFailedToReceiveAd.");
            this.zziq.onClick(this.zzip);
        }

        public final void onPresentScreen() {
            zzbbd.zzdn("Custom event adapter called onFailedToReceiveAd.");
            this.zziq.onPresentScreen(this.zzip);
        }

        public final void onDismissScreen() {
            zzbbd.zzdn("Custom event adapter called onFailedToReceiveAd.");
            this.zziq.onDismissScreen(this.zzip);
        }

        public final void onLeaveApplication() {
            zzbbd.zzdn("Custom event adapter called onFailedToReceiveAd.");
            this.zziq.onLeaveApplication(this.zzip);
        }
    }

    @VisibleForTesting
    class zzb implements CustomEventInterstitialListener {
        private final CustomEventAdapter zzip;
        private final MediationInterstitialListener zzir;

        public zzb(CustomEventAdapter customEventAdapter, MediationInterstitialListener mediationInterstitialListener) {
            this.zzip = customEventAdapter;
            this.zzir = mediationInterstitialListener;
        }

        public final void onReceivedAd() {
            zzbbd.zzdn("Custom event adapter called onReceivedAd.");
            this.zzir.onReceivedAd(CustomEventAdapter.this);
        }

        public final void onFailedToReceiveAd() {
            zzbbd.zzdn("Custom event adapter called onFailedToReceiveAd.");
            this.zzir.onFailedToReceiveAd(this.zzip, ErrorCode.NO_FILL);
        }

        public final void onPresentScreen() {
            zzbbd.zzdn("Custom event adapter called onPresentScreen.");
            this.zzir.onPresentScreen(this.zzip);
        }

        public final void onDismissScreen() {
            zzbbd.zzdn("Custom event adapter called onDismissScreen.");
            this.zzir.onDismissScreen(this.zzip);
        }

        public final void onLeaveApplication() {
            zzbbd.zzdn("Custom event adapter called onLeaveApplication.");
            this.zzir.onLeaveApplication(this.zzip);
        }
    }

    private static <T> T zzi(String str) {
        try {
            return Class.forName(str).newInstance();
        } catch (Throwable th) {
            String message = th.getMessage();
            StringBuilder stringBuilder = new StringBuilder((46 + String.valueOf(str).length()) + String.valueOf(message).length());
            stringBuilder.append("Could not instantiate custom event adapter: ");
            stringBuilder.append(str);
            stringBuilder.append(". ");
            stringBuilder.append(message);
            zzbbd.zzeo(stringBuilder.toString());
            return null;
        }
    }

    public final void destroy() {
        if (this.zzin != null) {
            this.zzin.destroy();
        }
        if (this.zzio != null) {
            this.zzio.destroy();
        }
    }

    public final Class<CustomEventExtras> getAdditionalParametersType() {
        return CustomEventExtras.class;
    }

    public final View getBannerView() {
        return this.zzim;
    }

    public final Class<CustomEventServerParameters> getServerParametersType() {
        return CustomEventServerParameters.class;
    }

    public final void requestBannerAd(MediationBannerListener mediationBannerListener, Activity activity, CustomEventServerParameters customEventServerParameters, AdSize adSize, MediationAdRequest mediationAdRequest, CustomEventExtras customEventExtras) {
        this.zzin = (CustomEventBanner) zzi(customEventServerParameters.className);
        if (this.zzin == null) {
            mediationBannerListener.onFailedToReceiveAd(this, ErrorCode.INTERNAL_ERROR);
            return;
        }
        Object obj;
        if (customEventExtras == null) {
            obj = null;
        } else {
            obj = customEventExtras.getExtra(customEventServerParameters.label);
        }
        this.zzin.requestBannerAd(new zza(this, mediationBannerListener), activity, customEventServerParameters.label, customEventServerParameters.parameter, adSize, mediationAdRequest, obj);
    }

    public final void requestInterstitialAd(MediationInterstitialListener mediationInterstitialListener, Activity activity, CustomEventServerParameters customEventServerParameters, MediationAdRequest mediationAdRequest, CustomEventExtras customEventExtras) {
        this.zzio = (CustomEventInterstitial) zzi(customEventServerParameters.className);
        if (this.zzio == null) {
            mediationInterstitialListener.onFailedToReceiveAd(this, ErrorCode.INTERNAL_ERROR);
            return;
        }
        Object obj;
        if (customEventExtras == null) {
            obj = null;
        } else {
            obj = customEventExtras.getExtra(customEventServerParameters.label);
        }
        this.zzio.requestInterstitialAd(new zzb(this, mediationInterstitialListener), activity, customEventServerParameters.label, customEventServerParameters.parameter, mediationAdRequest, obj);
    }

    public final void showInterstitial() {
        this.zzio.showInterstitial();
    }

    private final void zza(View view) {
        this.zzim = view;
    }
}
