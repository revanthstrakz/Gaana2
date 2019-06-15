package com.gaana.ads.interstitial;

import android.location.Location;
import com.gaana.ads.base.IAdRequestCallBack;
import com.gaana.ads.base.ILoadAdBehaviour;
import com.gaana.ads.base.IShowAdBehaviour;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;

public interface IAdType {
    void loadAd();

    void loadAndShow();

    void setAdRequestCallBack(IAdRequestCallBack iAdRequestCallBack);

    void setAudioFollowUpCampaign(String str);

    void setLoadAdBehaviour(ILoadAdBehaviour iLoadAdBehaviour);

    void setLocation(Location location);

    void setPublisherInterstitialAd(PublisherInterstitialAd publisherInterstitialAd);

    void setShowAdBehaviour(IShowAdBehaviour iShowAdBehaviour);

    void showAd();
}
