package com.gaana.ads.interstitial;

import com.gaana.ads.base.IShowAdBehaviour;

public class ShowAlwaysInterstitialBehaviour implements IShowAdBehaviour {
    public boolean whenToShow() {
        return true;
    }
}
