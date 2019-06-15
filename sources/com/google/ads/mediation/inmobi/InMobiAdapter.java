package com.google.ads.mediation.inmobi;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.inmobi.ads.InMobiAdRequestStatus;
import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.ads.InMobiBanner;
import com.inmobi.ads.InMobiBanner.AnimationType;
import com.inmobi.ads.InMobiInterstitial;
import com.inmobi.ads.InMobiNative;
import com.inmobi.ads.listeners.BannerAdEventListener;
import com.inmobi.ads.listeners.InterstitialAdEventListener;
import com.inmobi.ads.listeners.NativeAdEventListener;
import com.inmobi.sdk.InMobiSdk;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class InMobiAdapter implements MediationBannerAdapter, MediationInterstitialAdapter, MediationNativeAdapter, MediationRewardedVideoAdAdapter {
    private static final String TAG = "InMobiAdapter";
    private static Boolean sDisableHardwareFlag = Boolean.valueOf(false);
    private static Boolean sIsAppInitialized = Boolean.valueOf(false);
    private InMobiInterstitial mAdInterstitial;
    private InMobiInterstitial mAdRewarded;
    private MediationBannerListener mBannerListener;
    private MediationInterstitialListener mInterstitialListener;
    private Boolean mIsOnlyUrl = Boolean.valueOf(false);
    private boolean mIsRewardedVideoAdAdapterInitialized;
    private String mKey = "";
    private MediationNativeListener mNativeListener;
    private NativeMediationAdRequest mNativeMedAdReq;
    private MediationRewardedVideoAdListener mRewardedVideoAdListener;
    private String mValue = "";
    private FrameLayout mWrappedAdView;

    public void onDestroy() {
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public static Boolean IsAppInitialized() {
        return sIsAppInitialized;
    }

    private void isTaggedForChildDirectedTreatment(MediationAdRequest mediationAdRequest, HashMap<String, String> hashMap) {
        if (mediationAdRequest.taggedForChildDirectedTreatment() == 1) {
            hashMap.put("coppa", "1");
        } else {
            hashMap.put("coppa", "0");
        }
    }

    private static int getAdRequestErrorCode(StatusCode statusCode) {
        switch (statusCode) {
            case INTERNAL_ERROR:
                return 0;
            case AD_ACTIVE:
            case REQUEST_INVALID:
            case REQUEST_PENDING:
            case EARLY_REFRESH_REQUEST:
            case MISSING_REQUIRED_DEPENDENCIES:
                return 1;
            case REQUEST_TIMED_OUT:
            case NETWORK_UNREACHABLE:
                return 2;
            default:
                return 3;
        }
    }

    public void requestBannerAd(Context context, MediationBannerListener mediationBannerListener, Bundle bundle, AdSize adSize, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        if (!(sIsAppInitialized.booleanValue() || bundle == null)) {
            Log.d(TAG, bundle.getString("accountid"));
            Log.d(TAG, bundle.getString("placementid"));
            InMobiSdk.init(context, bundle.getString("accountid"), InMobiConsent.getConsentObj());
            sIsAppInitialized = Boolean.valueOf(true);
        }
        this.mBannerListener = mediationBannerListener;
        LayoutParams layoutParams = new LayoutParams(adSize.getWidthInPixels(context), adSize.getHeightInPixels(context));
        if (bundle != null) {
            InMobiBanner inMobiBanner;
            if (context instanceof Activity) {
                inMobiBanner = new InMobiBanner((Activity) context, Long.parseLong(bundle.getString("placementid")));
            } else {
                inMobiBanner = new InMobiBanner(context, Long.parseLong(bundle.getString("placementid")));
            }
            inMobiBanner.setEnableAutoRefresh(false);
            inMobiBanner.setAnimationType(AnimationType.ANIMATION_OFF);
            if (mediationAdRequest.getKeywords() != null) {
                inMobiBanner.setKeywords(TextUtils.join(", ", mediationAdRequest.getKeywords()));
            }
            HashMap hashMap = new HashMap();
            hashMap.put("tp", "c_admob");
            isTaggedForChildDirectedTreatment(mediationAdRequest, hashMap);
            inMobiBanner.setExtras(hashMap);
            if (bundle2 == null) {
                bundle2 = new Bundle();
            }
            inMobiBanner.setListener(new BannerAdEventListener() {
                public void onUserLeftApplication(InMobiBanner inMobiBanner) {
                    Log.d(InMobiAdapter.TAG, "onUserLeftApplication");
                    InMobiAdapter.this.mBannerListener.onAdLeftApplication(InMobiAdapter.this);
                }

                public void onRewardsUnlocked(InMobiBanner inMobiBanner, Map<Object, Object> map) {
                    Log.d(InMobiAdapter.TAG, "InMobi Banner onRewardsUnlocked.");
                    if (map != null) {
                        for (Object obj : map.keySet()) {
                            String obj2 = obj.toString();
                            String obj3 = map.get(obj2).toString();
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append(obj2);
                            stringBuilder.append(":");
                            stringBuilder.append(obj3);
                            Log.d("Rewards: ", stringBuilder.toString());
                        }
                    }
                }

                public void onAdLoadSucceeded(InMobiBanner inMobiBanner) {
                    System.out.println("onLoadSucceeded");
                    Log.d(InMobiAdapter.TAG, "onAdLoadSucceeded");
                    InMobiAdapter.this.mBannerListener.onAdLoaded(InMobiAdapter.this);
                }

                public void onAdLoadFailed(InMobiBanner inMobiBanner, InMobiAdRequestStatus inMobiAdRequestStatus) {
                    InMobiAdapter.this.mBannerListener.onAdFailedToLoad(InMobiAdapter.this, InMobiAdapter.getAdRequestErrorCode(inMobiAdRequestStatus.getStatusCode()));
                    String access$000 = InMobiAdapter.TAG;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("onAdLoadFailed: ");
                    stringBuilder.append(inMobiAdRequestStatus.getMessage());
                    Log.d(access$000, stringBuilder.toString());
                }

                public void onAdDisplayed(InMobiBanner inMobiBanner) {
                    Log.d(InMobiAdapter.TAG, "onAdDismissed");
                    InMobiAdapter.this.mBannerListener.onAdOpened(InMobiAdapter.this);
                }

                public void onAdDismissed(InMobiBanner inMobiBanner) {
                    Log.d(InMobiAdapter.TAG, "onAdDismissed");
                    InMobiAdapter.this.mBannerListener.onAdClosed(InMobiAdapter.this);
                }

                public void onAdClicked(InMobiBanner inMobiBanner, Map<Object, Object> map) {
                    Log.d("onBannerClicked", "onBannerClick called");
                    InMobiAdapter.this.mBannerListener.onAdClicked(InMobiAdapter.this);
                }
            });
            if (sDisableHardwareFlag.booleanValue()) {
                inMobiBanner.disableHardwareAcceleration();
            }
            this.mWrappedAdView = new FrameLayout(context);
            this.mWrappedAdView.setLayoutParams(layoutParams);
            inMobiBanner.setLayoutParams(new LinearLayout.LayoutParams(adSize.getWidthInPixels(context), adSize.getHeightInPixels(context)));
            this.mWrappedAdView.addView(inMobiBanner);
            InMobiAdapterUtils.buildAdRequest(mediationAdRequest, bundle2);
            inMobiBanner.load();
            return;
        }
        mediationBannerListener.onAdFailedToLoad(this, 1);
    }

    public View getBannerView() {
        return this.mWrappedAdView;
    }

    public void requestInterstitialAd(Context context, MediationInterstitialListener mediationInterstitialListener, Bundle bundle, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        if (!sIsAppInitialized.booleanValue()) {
            InMobiSdk.init(context, bundle.getString("accountid"), InMobiConsent.getConsentObj());
            sIsAppInitialized = Boolean.valueOf(true);
        }
        this.mInterstitialListener = mediationInterstitialListener;
        this.mAdInterstitial = new InMobiInterstitial(context, Long.parseLong(bundle.getString("placementid")), new InterstitialAdEventListener() {
            public void onUserLeftApplication(InMobiInterstitial inMobiInterstitial) {
                Log.d(InMobiAdapter.TAG, "onUserLeftApplication");
                InMobiAdapter.this.mInterstitialListener.onAdLeftApplication(InMobiAdapter.this);
            }

            public void onRewardsUnlocked(InMobiInterstitial inMobiInterstitial, Map<Object, Object> map) {
                Log.d(InMobiAdapter.TAG, "InMobi Interstitial onRewardsUnlocked.");
                if (map != null) {
                    for (Object obj : map.keySet()) {
                        String obj2 = obj.toString();
                        String obj3 = map.get(obj2).toString();
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(obj2);
                        stringBuilder.append(":");
                        stringBuilder.append(obj3);
                        Log.d("Rewards: ", stringBuilder.toString());
                    }
                }
            }

            public void onAdDisplayFailed(InMobiInterstitial inMobiInterstitial) {
                Log.d(InMobiAdapter.TAG, "Ad Display failed.");
            }

            public void onAdWillDisplay(InMobiInterstitial inMobiInterstitial) {
                Log.d(InMobiAdapter.TAG, "Ad Will Display.");
            }

            public void onAdLoadSucceeded(InMobiInterstitial inMobiInterstitial) {
                Log.d(InMobiAdapter.TAG, "onAdLoadSucceeded");
                InMobiAdapter.this.mInterstitialListener.onAdLoaded(InMobiAdapter.this);
            }

            public void onAdLoadFailed(InMobiInterstitial inMobiInterstitial, InMobiAdRequestStatus inMobiAdRequestStatus) {
                InMobiAdapter.this.mInterstitialListener.onAdFailedToLoad(InMobiAdapter.this, InMobiAdapter.getAdRequestErrorCode(inMobiAdRequestStatus.getStatusCode()));
                String access$000 = InMobiAdapter.TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("onAdLoadFailed: ");
                stringBuilder.append(inMobiAdRequestStatus.getMessage());
                Log.d(access$000, stringBuilder.toString());
            }

            public void onAdReceived(InMobiInterstitial inMobiInterstitial) {
                Log.d(InMobiAdapter.TAG, "InMobi Ad server responded with an Ad.");
            }

            public void onAdDisplayed(InMobiInterstitial inMobiInterstitial) {
                Log.d(InMobiAdapter.TAG, "onAdDisplayed");
                InMobiAdapter.this.mInterstitialListener.onAdOpened(InMobiAdapter.this);
            }

            public void onAdDismissed(InMobiInterstitial inMobiInterstitial) {
                Log.d(InMobiAdapter.TAG, "onAdDismissed");
                InMobiAdapter.this.mInterstitialListener.onAdClosed(InMobiAdapter.this);
            }

            public void onAdClicked(InMobiInterstitial inMobiInterstitial, Map<Object, Object> map) {
                Log.d(InMobiAdapter.TAG, "InterstitialClicked");
                InMobiAdapter.this.mInterstitialListener.onAdClicked(InMobiAdapter.this);
            }
        });
        if (mediationAdRequest.getKeywords() != null) {
            this.mAdInterstitial.setKeywords(TextUtils.join(", ", mediationAdRequest.getKeywords()));
        }
        HashMap hashMap = new HashMap();
        hashMap.put("tp", "c_admob");
        isTaggedForChildDirectedTreatment(mediationAdRequest, hashMap);
        this.mAdInterstitial.setExtras(hashMap);
        if (sDisableHardwareFlag.booleanValue()) {
            this.mAdInterstitial.disableHardwareAcceleration();
        }
        InMobiAdapterUtils.buildAdRequest(mediationAdRequest, bundle2);
        this.mAdInterstitial.load();
    }

    public void showInterstitial() {
        if (this.mAdInterstitial.isReady()) {
            Log.d(TAG, "Ad is ready to show");
            this.mAdInterstitial.show();
        }
    }

    public void initialize(Context context, MediationAdRequest mediationAdRequest, String str, MediationRewardedVideoAdListener mediationRewardedVideoAdListener, Bundle bundle, Bundle bundle2) {
        Log.d(TAG, "initialize called from InMobiAdapter.");
        this.mRewardedVideoAdListener = mediationRewardedVideoAdListener;
        str = bundle.getString("accountid");
        if (!sIsAppInitialized.booleanValue()) {
            InMobiSdk.init(context, str, InMobiConsent.getConsentObj());
            sIsAppInitialized = Boolean.valueOf(true);
        }
        this.mAdRewarded = new InMobiInterstitial(context, Long.parseLong(bundle.getString("placementid")), new InterstitialAdEventListener() {
            public void onRewardsUnlocked(InMobiInterstitial inMobiInterstitial, Map<Object, Object> map) {
                Log.d(InMobiAdapter.TAG, "InMobi RewardedVideo onRewardsUnlocked.");
                if (map != null) {
                    for (Object obj : map.keySet()) {
                        InMobiAdapter.this.mKey = obj.toString();
                        InMobiAdapter.this.mValue = map.get(InMobiAdapter.this.mKey).toString();
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(InMobiAdapter.this.mKey);
                        stringBuilder.append(":");
                        stringBuilder.append(InMobiAdapter.this.mValue);
                        Log.d("Rewards: ", stringBuilder.toString());
                    }
                }
                InMobiAdapter.this.mRewardedVideoAdListener.onVideoCompleted(InMobiAdapter.this);
                InMobiAdapter.this.mRewardedVideoAdListener.onRewarded(InMobiAdapter.this, new RewardItem() {
                    public String getType() {
                        return InMobiAdapter.this.mKey;
                    }

                    public int getAmount() {
                        if (InMobiAdapter.this.mValue == null || "".equalsIgnoreCase(InMobiAdapter.this.mValue)) {
                            return 0;
                        }
                        try {
                            return Integer.parseInt(InMobiAdapter.this.mValue);
                        } catch (NumberFormatException e) {
                            String access$000 = InMobiAdapter.TAG;
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("Reward value should be of type integer:");
                            stringBuilder.append(e.getMessage());
                            Log.e(access$000, stringBuilder.toString());
                            ThrowableExtension.printStackTrace(e);
                            return 0;
                        }
                    }
                });
            }

            public void onAdDisplayFailed(InMobiInterstitial inMobiInterstitial) {
                Log.d(InMobiAdapter.TAG, "Ad Display failed.");
            }

            public void onAdWillDisplay(InMobiInterstitial inMobiInterstitial) {
                Log.d(InMobiAdapter.TAG, "Ad Will Display.");
            }

            public void onAdDisplayed(InMobiInterstitial inMobiInterstitial) {
                Log.d(InMobiAdapter.TAG, "onAdDisplayed");
                InMobiAdapter.this.mRewardedVideoAdListener.onAdOpened(InMobiAdapter.this);
                InMobiAdapter.this.mRewardedVideoAdListener.onVideoStarted(InMobiAdapter.this);
            }

            public void onAdDismissed(InMobiInterstitial inMobiInterstitial) {
                Log.d(InMobiAdapter.TAG, "onAdDismissed");
                InMobiAdapter.this.mRewardedVideoAdListener.onAdClosed(InMobiAdapter.this);
            }

            public void onAdClicked(InMobiInterstitial inMobiInterstitial, Map<Object, Object> map) {
                Log.d(InMobiAdapter.TAG, "onInterstitialClicked called");
                InMobiAdapter.this.mRewardedVideoAdListener.onAdClicked(InMobiAdapter.this);
            }

            public void onAdLoadSucceeded(InMobiInterstitial inMobiInterstitial) {
                Log.d(InMobiAdapter.TAG, "onAdLoadSucceeded");
                InMobiAdapter.this.mRewardedVideoAdListener.onAdLoaded(InMobiAdapter.this);
            }

            public void onAdLoadFailed(InMobiInterstitial inMobiInterstitial, InMobiAdRequestStatus inMobiAdRequestStatus) {
                InMobiAdapter.this.mRewardedVideoAdListener.onAdFailedToLoad(InMobiAdapter.this, InMobiAdapter.getAdRequestErrorCode(inMobiAdRequestStatus.getStatusCode()));
                String access$000 = InMobiAdapter.TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("onAdLoadFailed: ");
                stringBuilder.append(inMobiAdRequestStatus.getMessage());
                Log.d(access$000, stringBuilder.toString());
            }

            public void onAdReceived(InMobiInterstitial inMobiInterstitial) {
                Log.d(InMobiAdapter.TAG, "InMobi Ad server responded with an Ad.");
            }

            public void onUserLeftApplication(InMobiInterstitial inMobiInterstitial) {
                Log.d(InMobiAdapter.TAG, "onUserLeftApplication");
                InMobiAdapter.this.mRewardedVideoAdListener.onAdLeftApplication(InMobiAdapter.this);
            }
        });
        this.mIsRewardedVideoAdAdapterInitialized = true;
        this.mRewardedVideoAdListener.onInitializationSucceeded(this);
        if (mediationAdRequest.getKeywords() != null) {
            String str2 = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("keyword is present:");
            stringBuilder.append(mediationAdRequest.getKeywords().toString());
            Log.d(str2, stringBuilder.toString());
            this.mAdRewarded.setKeywords(TextUtils.join(", ", mediationAdRequest.getKeywords()));
        }
        HashMap hashMap = new HashMap();
        hashMap.put("tp", "c_admob");
        isTaggedForChildDirectedTreatment(mediationAdRequest, hashMap);
        this.mAdRewarded.setExtras(hashMap);
        if (sDisableHardwareFlag.booleanValue()) {
            this.mAdRewarded.disableHardwareAcceleration();
        }
        InMobiAdapterUtils.buildAdRequest(mediationAdRequest, bundle2);
    }

    public void loadAd(MediationAdRequest mediationAdRequest, Bundle bundle, Bundle bundle2) {
        if (this.mAdRewarded != null) {
            this.mAdRewarded.load();
        }
    }

    public void showVideo() {
        if (this.mAdRewarded.isReady()) {
            this.mAdRewarded.show();
        }
    }

    public boolean isInitialized() {
        return this.mIsRewardedVideoAdAdapterInitialized && sIsAppInitialized.booleanValue();
    }

    public void requestNativeAd(final Context context, MediationNativeListener mediationNativeListener, Bundle bundle, NativeMediationAdRequest nativeMediationAdRequest, Bundle bundle2) {
        this.mNativeMedAdReq = nativeMediationAdRequest;
        if (!(sIsAppInitialized.booleanValue() || bundle == null)) {
            InMobiSdk.init(context, bundle.getString("accountid"), InMobiConsent.getConsentObj());
            sIsAppInitialized = Boolean.valueOf(true);
        }
        this.mNativeListener = mediationNativeListener;
        boolean z = nativeMediationAdRequest.isAppInstallAdRequested() && nativeMediationAdRequest.isContentAdRequested();
        if (Boolean.valueOf(z).booleanValue()) {
            InMobiNative inMobiNative = new InMobiNative(context, Long.parseLong(bundle.getString("placementid")), new NativeAdEventListener() {
                public void onAdFullScreenWillDisplay(InMobiNative inMobiNative) {
                }

                public void onAdStatusChanged(@NonNull InMobiNative inMobiNative) {
                }

                public void onAdLoadSucceeded(InMobiNative inMobiNative) {
                    System.out.println(" [ InMobi Native Ad ] : onAdLoadSucceeded ");
                    Log.d(InMobiAdapter.TAG, "onAdLoadSucceeded");
                    if (inMobiNative != null) {
                        NativeAdOptions nativeAdOptions = InMobiAdapter.this.mNativeMedAdReq.getNativeAdOptions();
                        if (nativeAdOptions != null) {
                            InMobiAdapter.this.mIsOnlyUrl = Boolean.valueOf(nativeAdOptions.shouldReturnUrlsForImageAssets());
                        }
                        new InMobiAppInstallNativeAdMapper(InMobiAdapter.this, inMobiNative, InMobiAdapter.this.mIsOnlyUrl, InMobiAdapter.this.mNativeListener).mapAppInstallAd(context);
                    }
                }

                public void onAdLoadFailed(InMobiNative inMobiNative, InMobiAdRequestStatus inMobiAdRequestStatus) {
                    InMobiAdapter.this.mNativeListener.onAdFailedToLoad(InMobiAdapter.this, InMobiAdapter.getAdRequestErrorCode(inMobiAdRequestStatus.getStatusCode()));
                    String access$000 = InMobiAdapter.TAG;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("onAdLoadFailed: ");
                    stringBuilder.append(inMobiAdRequestStatus.getMessage());
                    Log.d(access$000, stringBuilder.toString());
                }

                public void onAdFullScreenDismissed(InMobiNative inMobiNative) {
                    Log.d(InMobiAdapter.TAG, "onAdDismissed");
                    InMobiAdapter.this.mNativeListener.onAdClosed(InMobiAdapter.this);
                }

                public void onAdFullScreenDisplayed(InMobiNative inMobiNative) {
                    InMobiAdapter.this.mNativeListener.onAdOpened(InMobiAdapter.this);
                }

                public void onUserWillLeaveApplication(InMobiNative inMobiNative) {
                    Log.d(InMobiAdapter.TAG, "onUserLeftApplication");
                    InMobiAdapter.this.mNativeListener.onAdLeftApplication(InMobiAdapter.this);
                }

                public void onAdImpressed(@NonNull InMobiNative inMobiNative) {
                    Log.d(InMobiAdapter.TAG, "InMobi impression recorded successfully");
                    InMobiAdapter.this.mNativeListener.onAdImpression(InMobiAdapter.this);
                }

                public void onAdClicked(@NonNull InMobiNative inMobiNative) {
                    InMobiAdapter.this.mNativeListener.onAdClicked(InMobiAdapter.this);
                }
            });
            Set keywords = nativeMediationAdRequest.getKeywords();
            if (keywords != null) {
                inMobiNative.setKeywords(TextUtils.join(", ", keywords));
            }
            HashMap hashMap = new HashMap();
            hashMap.put("tp", "c_admob");
            isTaggedForChildDirectedTreatment(nativeMediationAdRequest, hashMap);
            inMobiNative.setExtras(hashMap);
            InMobiAdapterUtils.buildAdRequest(nativeMediationAdRequest, bundle2);
            inMobiNative.load();
            return;
        }
        this.mNativeListener.onAdFailedToLoad(this, 1);
    }
}
