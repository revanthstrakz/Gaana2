package com.gaana.ads.interstitial;

import com.constants.Constants;
import com.gaana.ads.base.IShowAdBehaviour;
import com.managers.f;

public class ShowConditionalInterstitialBehaviour implements IShowAdBehaviour {
    public boolean whenToShow() {
        long currentTimeMillis = System.currentTimeMillis();
        if (f.v().g() + Constants.cn > currentTimeMillis || f.v().h() >= Constants.cm) {
            return false;
        }
        long i = f.v().i();
        if (i == 0 || i + Constants.co <= currentTimeMillis) {
            return true;
        }
        return false;
    }
}
