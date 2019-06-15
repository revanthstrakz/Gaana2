package com.gaana.ads.interstitial;

import android.text.TextUtils;
import com.constants.Constants;
import com.gaana.ads.base.ILoadAdBehaviour;

public class LoadAlwaysInterstitialBehaviour implements ILoadAdBehaviour {
    public boolean whenToLoad() {
        return !TextUtils.isEmpty(Constants.cl);
    }
}
