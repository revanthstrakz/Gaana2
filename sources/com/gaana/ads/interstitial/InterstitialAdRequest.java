package com.gaana.ads.interstitial;

import android.location.Location;
import com.gaana.ads.base.IAdRequestCallBack;
import com.gaana.ads.base.ILoadAdBehaviour;
import com.gaana.ads.base.IShowAdBehaviour;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;

public class InterstitialAdRequest implements InterstitialAdBuilder {
    public InterstitialAdType interstitialAdType = new InterstitialAdType();

    public InterstitialAdBuilder buildPublisherInterstitialAd(PublisherInterstitialAd publisherInterstitialAd) {
        this.interstitialAdType.setPublisherInterstitialAd(publisherInterstitialAd);
        return this;
    }

    public InterstitialAdBuilder buildAdRequestCallBack(IAdRequestCallBack iAdRequestCallBack) {
        this.interstitialAdType.setAdRequestCallBack(iAdRequestCallBack);
        return this;
    }

    public InterstitialAdBuilder buildInterstitialShowBehaviour(IShowAdBehaviour iShowAdBehaviour) {
        this.interstitialAdType.setShowAdBehaviour(iShowAdBehaviour);
        return this;
    }

    public InterstitialAdBuilder buildInterstitialLoadBehaviour(ILoadAdBehaviour iLoadAdBehaviour) {
        this.interstitialAdType.setLoadAdBehaviour(iLoadAdBehaviour);
        return this;
    }

    public InterstitialAdBuilder buildLocation(Location location) {
        this.interstitialAdType.setLocation(location);
        return this;
    }

    public InterstitialAdBuilder buildAudioFollowUpCampaign(String str) {
        this.interstitialAdType.setAudioFollowUpCampaign(str);
        return this;
    }

    public IAdType build() {
        return this.interstitialAdType;
    }
}
