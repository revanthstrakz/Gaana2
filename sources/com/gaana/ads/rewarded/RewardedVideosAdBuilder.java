package com.gaana.ads.rewarded;

import android.location.Location;
import com.google.android.gms.ads.reward.RewardedVideoAd;

public interface RewardedVideosAdBuilder {
    IRewardAdType build();

    RewardedVideosAdBuilder buildAdRequestCallBack(IRewardedVideoAdRequestCallBack iRewardedVideoAdRequestCallBack);

    RewardedVideosAdBuilder buildLocation(Location location);

    RewardedVideosAdBuilder buildRewardedVideoAd(RewardedVideoAd rewardedVideoAd);
}
