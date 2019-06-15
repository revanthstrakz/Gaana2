package com.gaana.ads.rewarded;

import android.arch.lifecycle.Lifecycle.Event;
import android.arch.lifecycle.m;
import android.location.Location;
import android.os.CountDownTimer;
import android.text.TextUtils;
import com.constants.Constants;
import com.gaana.application.GaanaApplication;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest.Builder;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.managers.u;
import com.player_framework.GaanaMusicService;
import com.services.d;
import com.utilities.Util;
import java.util.HashMap;

public class RewardedVideoAdType implements IRewardAdType {
    private CountDownTimer countDownTimer = null;
    private boolean isRewardVideoRequestSent = false;
    private boolean isVideoAdRewarded = false;
    private Location location;
    private RewardedVideoAd rewardedVideoAd;
    private IRewardedVideoAdRequestCallBack rewardedVideoAdRequestCallBack;

    public void setRewardedVideoAd(RewardedVideoAd rewardedVideoAd) {
        this.rewardedVideoAd = rewardedVideoAd;
    }

    public void setAdRequestCallBack(IRewardedVideoAdRequestCallBack iRewardedVideoAdRequestCallBack) {
        this.rewardedVideoAdRequestCallBack = iRewardedVideoAdRequestCallBack;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void loadAndShow() {
        if (this.rewardedVideoAd != null) {
            Builder builder = new Builder();
            if (this.location != null) {
                Location location = new Location("");
                location.setLatitude(this.location.getLatitude());
                location.setLongitude(this.location.getLongitude());
                builder.setLocation(location);
            }
            if (GaanaApplication.getInstance().getNetworkExtrasBundle() != null) {
                builder.addNetworkExtras(GaanaApplication.getInstance().getNetworkExtrasBundle());
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(Util.l(GaanaApplication.getContext()));
            stringBuilder.append("Gaana ");
            builder.setPublisherProvidedId(Util.c(stringBuilder.toString()));
            this.rewardedVideoAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {
                public void onRewardedVideoAdLeftApplication() {
                }

                public void onRewardedVideoCompleted() {
                }

                public void onRewardedVideoStarted() {
                }

                public void onRewardedVideoAdLoaded() {
                    if (RewardedVideoAdType.this.countDownTimer != null) {
                        RewardedVideoAdType.this.countDownTimer.cancel();
                    }
                    RewardedVideoAdType.this.isRewardVideoRequestSent = true;
                    if (RewardedVideoAdType.this.rewardedVideoAd != null) {
                        RewardedVideoAdType.this.rewardedVideoAd.show();
                    }
                }

                public void onRewardedVideoAdOpened() {
                    if (RewardedVideoAdType.this.countDownTimer != null) {
                        RewardedVideoAdType.this.countDownTimer.cancel();
                    }
                    GaanaMusicService.i();
                    if (RewardedVideoAdType.this.rewardedVideoAdRequestCallBack != null) {
                        RewardedVideoAdType.this.rewardedVideoAdRequestCallBack.onRewardedVideoAdOpened();
                    }
                }

                public void onRewardedVideoAdClosed() {
                    if (!RewardedVideoAdType.this.isVideoAdRewarded) {
                        u.a().a("Premium pop-up", "Watch Ad", "Failure");
                    } else if (RewardedVideoAdType.this.rewardedVideoAdRequestCallBack != null) {
                        RewardedVideoAdType.this.rewardedVideoAdRequestCallBack.playSong(false);
                    }
                    if (RewardedVideoAdType.this.rewardedVideoAdRequestCallBack != null) {
                        RewardedVideoAdType.this.rewardedVideoAdRequestCallBack.destroyRewardVideoAd();
                    }
                    RewardedVideoAdType.this.isVideoAdRewarded = false;
                    RewardedVideoAdType.this.isRewardVideoRequestSent = false;
                }

                public void onRewarded(RewardItem rewardItem) {
                    RewardedVideoAdType.this.isVideoAdRewarded = true;
                    RewardedVideoAdType.this.provideManualReward(rewardItem);
                }

                public void onRewardedVideoAdFailedToLoad(int i) {
                    if (RewardedVideoAdType.this.countDownTimer != null) {
                        RewardedVideoAdType.this.countDownTimer.cancel();
                    }
                    RewardedVideoAdType.this.unlockPremiumSongForFree();
                }
            });
            this.countDownTimer = new CountDownTimer((long) Constants.ci, (long) Constants.ci) {
                public void onTick(long j) {
                }

                public void onFinish() {
                    if (RewardedVideoAdType.this.countDownTimer != null) {
                        RewardedVideoAdType.this.countDownTimer.cancel();
                    }
                    RewardedVideoAdType.this.unlockPremiumSongForFree();
                }
            };
            this.countDownTimer.start();
            this.rewardedVideoAd.loadAd(Constants.ch, builder.build());
        }
    }

    private void provideManualReward(RewardItem rewardItem) {
        Object c;
        if (rewardItem == null) {
            rewardItem = new RewardItem() {
                public int getAmount() {
                    return 1;
                }

                public String getType() {
                    return Constants.cU;
                }
            };
        }
        String c2 = d.a().c("PREFERENCE_SESSION_REWARD_TYPE", false);
        HashMap a = d.a().a("premium_content_track_data", false);
        if (a == null) {
            a = new HashMap();
        }
        if (!(TextUtils.isEmpty(c2) || c2.equalsIgnoreCase(rewardItem.getType()))) {
            a.clear();
        }
        if (Constants.cR.equalsIgnoreCase(Constants.cQ)) {
            c = d.a().c("premium_content_track_id", false);
        } else {
            c = Constants.cS;
        }
        if (rewardItem.getType().equalsIgnoreCase(Constants.cU)) {
            long longValue = Long.valueOf((long) ((rewardItem.getAmount() + d.a().b("PREFERENCE_SESSION_HISTORY_COUNT", 0, false)) - 1)).longValue();
            a.put(c, Long.valueOf(longValue));
            d.a().a(longValue, "PREFERENCE_SESSION_REWARD_COUNT", false);
        } else {
            a.put(c, Long.valueOf(System.currentTimeMillis() + ((long) (rewardItem.getAmount() * 1000))));
        }
        d.a().a(a, "premium_content_track_data", false);
        d.a().a("PREFERENCE_SESSION_REWARD_TYPE", rewardItem.getType(), false);
        u.a().a("Premium pop-up", "Watch Ad", "Success");
    }

    @m(a = Event.ON_PAUSE)
    private void onPause() {
        if (this.countDownTimer != null) {
            this.countDownTimer.cancel();
        }
        if (!this.isRewardVideoRequestSent) {
            unlockPremiumSongForFree();
        }
    }

    private void unlockPremiumSongForFree() {
        if (this.rewardedVideoAdRequestCallBack != null) {
            this.rewardedVideoAdRequestCallBack.playSong(true);
        }
        provideManualReward(null);
        if (this.rewardedVideoAdRequestCallBack != null) {
            this.rewardedVideoAdRequestCallBack.destroyRewardVideoAd();
        }
    }
}
