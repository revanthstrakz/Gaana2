package com.facebook.ads;

import android.content.Context;
import android.util.Log;
import com.facebook.ads.internal.DisplayAdController;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.internal.adapters.t;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.protocol.d;
import com.facebook.ads.internal.protocol.e;
import com.facebook.ads.internal.s.d.a;
import com.facebook.ads.internal.s.d.b;

public class RewardedVideoAd implements Ad {
    public static final int UNSET_VIDEO_DURATION = -1;
    private static final String a = "RewardedVideoAd";
    private final Context b;
    private final String c;
    private DisplayAdController d;
    private boolean e = false;
    private RewardedVideoAdListener f;
    private RewardData g;
    private int h = -1;
    private String i;

    public RewardedVideoAd(Context context, String str) {
        this.b = context;
        this.c = str;
    }

    private void a(String str, boolean z) {
        try {
            b(str, z);
        } catch (Exception e) {
            Log.e(a, "Error loading rewarded video ad", e);
            if (this.f != null) {
                a.a(this.b, "api", b.i, e);
                this.f.onError(this, AdError.internalError(2004));
            }
        }
    }

    private final void a(boolean z) {
        if (this.d != null) {
            this.d.b(z);
            this.d = null;
        }
    }

    private void b(String str, boolean z) {
        if (!(this.e || this.d == null)) {
            Log.w(a, "An ad load is already in progress. You should wait for adLoaded() to be called");
        }
        a(false);
        this.e = false;
        this.d = new DisplayAdController(this.b, this.c, e.REWARDED_VIDEO, AdPlacementType.REWARDED_VIDEO, d.INTERSTITIAL, 1, true);
        this.d.a(z);
        this.d.a(this.i);
        this.d.a(new com.facebook.ads.internal.adapters.a() {
            public void a() {
                if (RewardedVideoAd.this.f != null) {
                    RewardedVideoAd.this.f.onAdClicked(RewardedVideoAd.this);
                }
            }

            public void a(AdAdapter adAdapter) {
                t tVar = (t) adAdapter;
                if (RewardedVideoAd.this.g != null) {
                    tVar.a(RewardedVideoAd.this.g);
                }
                RewardedVideoAd.this.h = tVar.a();
                RewardedVideoAd.this.e = true;
                if (RewardedVideoAd.this.f != null) {
                    RewardedVideoAd.this.f.onAdLoaded(RewardedVideoAd.this);
                }
            }

            public void a(com.facebook.ads.internal.protocol.a aVar) {
                if (RewardedVideoAd.this.f != null) {
                    RewardedVideoAd.this.f.onError(RewardedVideoAd.this, AdError.getAdErrorFromWrapper(aVar));
                }
            }

            public void b() {
                if (RewardedVideoAd.this.f != null) {
                    RewardedVideoAd.this.f.onLoggingImpression(RewardedVideoAd.this);
                }
            }

            public void g() {
                RewardedVideoAd.this.f.onRewardedVideoCompleted();
            }

            public void h() {
                if (RewardedVideoAd.this.f != null) {
                    RewardedVideoAd.this.f.onRewardedVideoClosed();
                }
            }

            public void i() {
                if (RewardedVideoAd.this.f instanceof S2SRewardedVideoAdListener) {
                    ((S2SRewardedVideoAdListener) RewardedVideoAd.this.f).onRewardServerFailed();
                }
            }

            public void j() {
                if (RewardedVideoAd.this.f instanceof S2SRewardedVideoAdListener) {
                    ((S2SRewardedVideoAdListener) RewardedVideoAd.this.f).onRewardServerSuccess();
                }
            }

            public void k() {
                if (RewardedVideoAd.this.f instanceof RewardedVideoAdExtendedListener) {
                    ((RewardedVideoAdExtendedListener) RewardedVideoAd.this.f).onRewardedVideoActivityDestroyed();
                }
            }
        });
        this.d.b(str);
    }

    public void destroy() {
        a(true);
    }

    public String getPlacementId() {
        return this.c;
    }

    public int getVideoDuration() {
        return this.h;
    }

    public boolean isAdInvalidated() {
        return this.d == null || this.d.d();
    }

    public boolean isAdLoaded() {
        return this.e;
    }

    public void loadAd() {
        a(null, true);
    }

    public void loadAd(boolean z) {
        a(null, z);
    }

    public void loadAdFromBid(String str) {
        a(str, true);
    }

    public void loadAdFromBid(String str, boolean z) {
        a(str, z);
    }

    public void setAdListener(RewardedVideoAdListener rewardedVideoAdListener) {
        this.f = rewardedVideoAdListener;
    }

    public void setExtraHints(ExtraHints extraHints) {
        this.i = extraHints.getHints();
    }

    public void setRewardData(RewardData rewardData) {
        this.g = rewardData;
        if (this.e) {
            this.d.a(rewardData);
        }
    }

    public boolean show() {
        return show(-1);
    }

    public boolean show(int i) {
        if (this.e) {
            this.d.a(i);
            this.d.b();
            this.e = false;
            return true;
        }
        if (this.f != null) {
            this.f.onError(this, AdError.SHOW_CALLED_BEFORE_LOAD_ERROR);
        }
        return false;
    }
}
