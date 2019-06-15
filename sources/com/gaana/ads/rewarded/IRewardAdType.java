package com.gaana.ads.rewarded;

import android.arch.lifecycle.d;
import android.location.Location;
import com.google.android.gms.ads.reward.RewardedVideoAd;

public interface IRewardAdType extends d {
    void loadAndShow();

    void setAdRequestCallBack(IRewardedVideoAdRequestCallBack iRewardedVideoAdRequestCallBack);

    void setLocation(Location location);

    void setRewardedVideoAd(RewardedVideoAd rewardedVideoAd);
}
