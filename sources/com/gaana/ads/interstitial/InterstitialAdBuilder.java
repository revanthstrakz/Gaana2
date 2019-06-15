package com.gaana.ads.interstitial;

import android.location.Location;
import com.gaana.ads.base.IAdRequestCallBack;
import com.gaana.ads.base.ILoadAdBehaviour;
import com.gaana.ads.base.IShowAdBehaviour;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;

public interface InterstitialAdBuilder {
    IAdType build();

    InterstitialAdBuilder buildAdRequestCallBack(IAdRequestCallBack iAdRequestCallBack);

    InterstitialAdBuilder buildAudioFollowUpCampaign(String str);

    InterstitialAdBuilder buildInterstitialLoadBehaviour(ILoadAdBehaviour iLoadAdBehaviour);

    InterstitialAdBuilder buildInterstitialShowBehaviour(IShowAdBehaviour iShowAdBehaviour);

    InterstitialAdBuilder buildLocation(Location location);

    InterstitialAdBuilder buildPublisherInterstitialAd(PublisherInterstitialAd publisherInterstitialAd);
}
