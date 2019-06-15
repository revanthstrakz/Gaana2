package com.gaana.ads.rewarded;

public interface IRewardedVideoAdRequestCallBack {
    void destroyRewardVideoAd();

    void onRewardedVideoAdOpened();

    void playSong(boolean z);
}
