package com.gaana.ads.rewarded;

import android.location.Location;
import com.google.android.gms.ads.reward.RewardedVideoAd;

public class RewardedVideoAdRequest implements RewardedVideosAdBuilder {
    public RewardedVideoAdType rewardedVideoAdType = new RewardedVideoAdType();

    public RewardedVideosAdBuilder buildRewardedVideoAd(RewardedVideoAd rewardedVideoAd) {
        this.rewardedVideoAdType.setRewardedVideoAd(rewardedVideoAd);
        return this;
    }

    public RewardedVideosAdBuilder buildAdRequestCallBack(IRewardedVideoAdRequestCallBack iRewardedVideoAdRequestCallBack) {
        this.rewardedVideoAdType.setAdRequestCallBack(iRewardedVideoAdRequestCallBack);
        return this;
    }

    public RewardedVideosAdBuilder buildLocation(Location location) {
        this.rewardedVideoAdType.setLocation(location);
        return this;
    }

    public IRewardAdType build() {
        return this.rewardedVideoAdType;
    }
}
