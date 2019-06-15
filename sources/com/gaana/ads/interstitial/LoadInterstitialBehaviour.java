package com.gaana.ads.interstitial;

import android.text.TextUtils;
import com.constants.Constants;
import com.gaana.ads.base.ILoadAdBehaviour;
import com.managers.f;

public class LoadInterstitialBehaviour implements ILoadAdBehaviour {
    public boolean whenToLoad() {
        if (!TextUtils.isEmpty(Constants.cl) && f.v().h() < Constants.cm) {
            return true;
        }
        return false;
    }
}
