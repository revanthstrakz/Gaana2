package com.gaana.ads.interstitial;

import android.location.Location;
import android.text.TextUtils;
import com.constants.Constants;
import com.gaana.ads.base.IAdRequestCallBack;
import com.gaana.ads.base.ILoadAdBehaviour;
import com.gaana.ads.base.IShowAdBehaviour;
import com.gaana.application.GaanaApplication;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest.Builder;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.managers.f;
import com.utilities.Util;

public class InterstitialAdType implements IAdType {
    private IAdRequestCallBack adRequestCallBack;
    private String audioFollowUpCampaign;
    private ILoadAdBehaviour loadAdBehaviour;
    private Location location;
    private PublisherInterstitialAd publisherInterstitialAd;
    private IShowAdBehaviour showAdBehaviour;

    public void setPublisherInterstitialAd(PublisherInterstitialAd publisherInterstitialAd) {
        this.publisherInterstitialAd = publisherInterstitialAd;
        this.publisherInterstitialAd.setAdUnitId(Constants.cl);
    }

    public void setAdRequestCallBack(IAdRequestCallBack iAdRequestCallBack) {
        this.adRequestCallBack = iAdRequestCallBack;
    }

    public void setShowAdBehaviour(IShowAdBehaviour iShowAdBehaviour) {
        this.showAdBehaviour = iShowAdBehaviour;
    }

    public void setLoadAdBehaviour(ILoadAdBehaviour iLoadAdBehaviour) {
        this.loadAdBehaviour = iLoadAdBehaviour;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setAudioFollowUpCampaign(String str) {
        this.audioFollowUpCampaign = str;
    }

    public void loadAd() {
        if (this.publisherInterstitialAd != null) {
            Builder builder = new Builder();
            if (this.location != null) {
                Location location = new Location("");
                location.setLatitude(this.location.getLatitude());
                location.setLongitude(this.location.getLongitude());
                builder.setLocation(location);
            }
            if (GaanaApplication.getInstance().getNetworkExtrasBundle() != null) {
                builder.addNetworkExtras(GaanaApplication.getInstance().getNetworkExtrasBundle());
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(Util.l(GaanaApplication.getContext()));
            stringBuilder.append("Gaana ");
            builder.setPublisherProvidedId(Util.c(stringBuilder.toString()));
            this.publisherInterstitialAd.loadAd(builder.build());
            this.publisherInterstitialAd.setAdListener(new AdListener() {
                public void onAdClosed() {
                    f.v().c(System.currentTimeMillis());
                    f.v().b(f.v().h() + 1);
                    if (InterstitialAdType.this.loadAdBehaviour != null && InterstitialAdType.this.loadAdBehaviour.whenToLoad()) {
                        InterstitialAdType.this.loadAd();
                    }
                }

                public void onAdLoaded() {
                    super.onAdLoaded();
                    if (InterstitialAdType.this.adRequestCallBack != null) {
                        InterstitialAdType.this.adRequestCallBack.onAdLoaded();
                    }
                }
            });
        }
    }

    public void showAd() {
        if (this.showAdBehaviour == null) {
            throw new IllegalStateException("To show interstitial ads, one must define show behaviour while initiating");
        } else if (this.showAdBehaviour.whenToShow() && this.publisherInterstitialAd != null && this.publisherInterstitialAd.isLoaded()) {
            this.publisherInterstitialAd.show();
        }
    }

    public void loadAndShow() {
        if (this.publisherInterstitialAd != null) {
            Builder builder = new Builder();
            if (this.location != null) {
                Location location = new Location("");
                location.setLatitude(this.location.getLatitude());
                location.setLongitude(this.location.getLongitude());
                builder.setLocation(location);
            }
            if (!TextUtils.isEmpty(this.audioFollowUpCampaign)) {
                GaanaApplication.getInstance().setNetworkExtrasBundle("followup", this.audioFollowUpCampaign);
            }
            if (GaanaApplication.getInstance().getNetworkExtrasBundle() != null) {
                builder.addNetworkExtras(GaanaApplication.getInstance().getNetworkExtrasBundle());
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(Util.l(GaanaApplication.getContext()));
            stringBuilder.append("Gaana ");
            builder.setPublisherProvidedId(Util.c(stringBuilder.toString()));
            this.publisherInterstitialAd.loadAd(builder.build());
            GaanaApplication.getInstance().setNetworkExtrasBundle("followup", "");
            this.publisherInterstitialAd.setAdListener(new AdListener() {
                public void onAdClosed() {
                    f.v().c(System.currentTimeMillis());
                }

                public void onAdLoaded() {
                    InterstitialAdType.this.showAd();
                    f.v().b("");
                }
            });
        }
    }
}
