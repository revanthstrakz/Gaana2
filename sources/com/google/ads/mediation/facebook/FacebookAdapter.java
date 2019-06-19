package com.google.ads.mediation.facebook;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.facebook.ads.Ad;
import com.facebook.ads.AdChoicesView;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AdView;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.MediaView;
import com.facebook.ads.MediaViewListener;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdBase.Rating;
import com.facebook.ads.NativeAdListener;
import com.facebook.ads.NativeAdViewAttributes;
import com.facebook.ads.RewardedVideoAd;
import com.facebook.ads.RewardedVideoAdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdAssetNames;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.NativeAppInstallAdMapper;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@Keep
public final class FacebookAdapter implements MediationBannerAdapter, MediationInterstitialAdapter, MediationNativeAdapter, MediationRewardedVideoAdAdapter {
    public static final String KEY_AD_VIEW_ATTRIBUTES = "ad_view_attributes";
    public static final String KEY_AUTOPLAY = "autoplay";
    public static final String KEY_BACKGROUND_COLOR = "background_color";
    public static final String KEY_BUTTON_BORDER_COLOR = "button_border_color";
    public static final String KEY_BUTTON_COLOR = "button_color";
    public static final String KEY_BUTTON_TEXT_COLOR = "button_text_color";
    public static final String KEY_DESCRIPTION_TEXT_COLOR = "description_text_color";
    public static final String KEY_DESCRIPTION_TEXT_SIZE = "description_text_size";
    public static final String KEY_ID = "id";
    public static final String KEY_IS_BOLD = "is_bold";
    public static final String KEY_IS_ITALIC = "is_italic";
    public static final String KEY_SOCIAL_CONTEXT_ASSET = "social_context";
    public static final String KEY_STYLE = "style";
    public static final String KEY_SUBTITLE_ASSET = "subtitle";
    public static final String KEY_TITLE_TEXT_COLOR = "title_text_color";
    public static final String KEY_TITLE_TEXT_SIZE = "title_text_size";
    public static final String KEY_TYPEFACE = "typeface";
    private static final int MAX_STAR_RATING = 5;
    private static final String PLACEMENT_PARAMETER = "pubid";
    private static final String TAG = "FacebookAdapter";
    private AdView mAdView;
    private MediationBannerListener mBannerListener;
    private Context mContext;
    private InterstitialAd mInterstitialAd;
    private MediationInterstitialListener mInterstitialListener;
    private boolean mIsAdChoicesIconExpandable = true;
    private boolean mIsImpressionRecorded;
    private boolean mIsInitialized;
    private MediaView mMediaView;
    private NativeAd mNativeAd;
    private MediationNativeListener mNativeListener;
    private MediationRewardedVideoAdListener mRewardedListener;
    private RewardedVideoAd mRewardedVideoAd;
    private RelativeLayout mWrappedAdView;

    class AppInstallMapper extends NativeAppInstallAdMapper {
        private NativeAd mNativeAd;
        private NativeAdOptions mNativeAdOptions;

        public AppInstallMapper(NativeAd nativeAd, NativeAdOptions nativeAdOptions) {
            this.mNativeAd = nativeAd;
            this.mNativeAdOptions = nativeAdOptions;
        }

        public void mapNativeAd(NativeAdMapperListener nativeAdMapperListener) {
            if (containsRequiredFieldsForNativeAppInstallAd(this.mNativeAd)) {
                setHeadline(this.mNativeAd.getAdHeadline());
                ArrayList arrayList = new ArrayList();
                arrayList.add(new FacebookAdapterNativeAdImage(Uri.parse(this.mNativeAd.getAdCoverImage().toString())));
                setImages(arrayList);
                setBody(this.mNativeAd.getAdBodyText());
                setIcon(new FacebookAdapterNativeAdImage(Uri.parse(this.mNativeAd.getAdIcon().toString())));
                setCallToAction(this.mNativeAd.getAdCallToAction());
                FacebookAdapter.this.mMediaView.setListener(new MediaViewListener() {
                    public void onEnterFullscreen(MediaView mediaView) {
                    }

                    public void onExitFullscreen(MediaView mediaView) {
                    }

                    public void onFullscreenBackground(MediaView mediaView) {
                    }

                    public void onFullscreenForeground(MediaView mediaView) {
                    }

                    public void onPause(MediaView mediaView) {
                    }

                    public void onPlay(MediaView mediaView) {
                    }

                    public void onVolumeChange(MediaView mediaView, float f) {
                    }

                    public void onComplete(MediaView mediaView) {
                        if (FacebookAdapter.this.mNativeListener != null) {
                            FacebookAdapter.this.mNativeListener.onVideoEnd(FacebookAdapter.this);
                        }
                    }
                });
                setMediaView(FacebookAdapter.this.mMediaView);
                setHasVideoContent(true);
                Double rating = getRating(this.mNativeAd.getAdStarRating());
                if (rating != null) {
                    setStarRating(rating.doubleValue());
                }
                Bundle bundle = new Bundle();
                bundle.putCharSequence("id", this.mNativeAd.getId());
                bundle.putCharSequence(FacebookAdapter.KEY_SOCIAL_CONTEXT_ASSET, this.mNativeAd.getAdSocialContext());
                NativeAdViewAttributes adViewAttributes = this.mNativeAd.getAdViewAttributes();
                if (adViewAttributes != null) {
                    Bundle bundle2 = new Bundle();
                    bundle2.putBoolean("autoplay", adViewAttributes.getAutoplay());
                    bundle2.putInt(FacebookAdapter.KEY_BACKGROUND_COLOR, adViewAttributes.getBackgroundColor());
                    bundle2.putInt(FacebookAdapter.KEY_BUTTON_BORDER_COLOR, adViewAttributes.getButtonBorderColor());
                    bundle2.putInt(FacebookAdapter.KEY_BUTTON_COLOR, adViewAttributes.getButtonColor());
                    bundle2.putInt(FacebookAdapter.KEY_BUTTON_TEXT_COLOR, adViewAttributes.getButtonTextColor());
                    bundle2.putInt(FacebookAdapter.KEY_DESCRIPTION_TEXT_COLOR, adViewAttributes.getDescriptionTextColor());
                    bundle2.putInt(FacebookAdapter.KEY_DESCRIPTION_TEXT_SIZE, adViewAttributes.getDescriptionTextSize());
                    bundle2.putInt(FacebookAdapter.KEY_TITLE_TEXT_COLOR, adViewAttributes.getTitleTextColor());
                    bundle2.putInt(FacebookAdapter.KEY_TITLE_TEXT_SIZE, adViewAttributes.getTitleTextSize());
                    Typeface typeface = adViewAttributes.getTypeface();
                    if (typeface != null) {
                        Bundle bundle3 = new Bundle();
                        bundle3.putBoolean(FacebookAdapter.KEY_IS_BOLD, typeface.isBold());
                        bundle3.putBoolean(FacebookAdapter.KEY_IS_ITALIC, typeface.isItalic());
                        bundle3.putInt("style", typeface.getStyle());
                        bundle2.putBundle(FacebookAdapter.KEY_TYPEFACE, bundle3);
                    }
                    bundle.putBundle(FacebookAdapter.KEY_AD_VIEW_ATTRIBUTES, bundle2);
                }
                setExtras(bundle);
                nativeAdMapperListener.onMappingSuccess();
                return;
            }
            Log.w(FacebookAdapter.TAG, "Ad from Facebook doesn't have all assets required for the app install format.");
            nativeAdMapperListener.onMappingFailed();
        }

        private boolean containsRequiredFieldsForNativeAppInstallAd(NativeAd nativeAd) {
            return (nativeAd.getAdHeadline() == null || nativeAd.getAdCoverImage() == null || nativeAd.getAdBodyText() == null || nativeAd.getAdIcon() == null || nativeAd.getAdCallToAction() == null || FacebookAdapter.this.mMediaView == null) ? false : true;
        }

        public void trackViews(View view, Map<String, View> map, Map<String, View> map2) {
            ViewGroup viewGroup = (ViewGroup) view;
            View childAt = viewGroup.getChildAt(viewGroup.getChildCount() - 1);
            if (childAt instanceof FrameLayout) {
                AdChoicesView adChoicesView = new AdChoicesView(view.getContext(), this.mNativeAd, FacebookAdapter.this.mIsAdChoicesIconExpandable);
                ((ViewGroup) childAt).addView(adChoicesView);
                LayoutParams layoutParams = (LayoutParams) adChoicesView.getLayoutParams();
                if (this.mNativeAdOptions != null) {
                    int adChoicesPlacement = this.mNativeAdOptions.getAdChoicesPlacement();
                    if (adChoicesPlacement != 0) {
                        switch (adChoicesPlacement) {
                            case 2:
                                layoutParams.gravity = 85;
                                break;
                            case 3:
                                layoutParams.gravity = 83;
                                break;
                            default:
                                layoutParams.gravity = 53;
                                break;
                        }
                    }
                    layoutParams.gravity = 51;
                } else {
                    layoutParams.gravity = 53;
                }
                viewGroup.requestLayout();
            } else {
                setAdChoicesContent(new AdChoicesView(view.getContext(), this.mNativeAd, FacebookAdapter.this.mIsAdChoicesIconExpandable));
            }
            setOverrideImpressionRecording(true);
            setOverrideClickHandling(true);
            ImageView imageView = null;
            List arrayList = new ArrayList();
            for (Entry entry : map.entrySet()) {
                arrayList.add(entry.getValue());
                if (((String) entry.getKey()).equals(NativeAppInstallAd.ASSET_ICON) || ((String) entry.getKey()).equals(UnifiedNativeAdAssetNames.ASSET_ICON)) {
                    imageView = (ImageView) entry.getValue();
                }
            }
            this.mNativeAd.registerViewForInteraction(view, FacebookAdapter.this.mMediaView, imageView, arrayList);
        }

        public void untrackView(View view) {
            super.untrackView(view);
            ViewGroup viewGroup = (ViewGroup) view;
            view = viewGroup.getChildAt(viewGroup.getChildCount() - 1);
            if (view instanceof FrameLayout) {
                ((FrameLayout) view).removeAllViews();
            }
            this.mNativeAd.unregisterView();
        }

        private Double getRating(Rating rating) {
            return rating == null ? null : Double.valueOf((5.0d * rating.getValue()) / rating.getScale());
        }
    }

    private class BannerListener implements AdListener {
        public void onLoggingImpression(Ad ad) {
        }

        private BannerListener() {
        }

        public void onAdClicked(Ad ad) {
            FacebookAdapter.this.mBannerListener.onAdClicked(FacebookAdapter.this);
            FacebookAdapter.this.mBannerListener.onAdOpened(FacebookAdapter.this);
            FacebookAdapter.this.mBannerListener.onAdLeftApplication(FacebookAdapter.this);
        }

        public void onAdLoaded(Ad ad) {
            FacebookAdapter.this.mBannerListener.onAdLoaded(FacebookAdapter.this);
        }

        public void onError(Ad ad, AdError adError) {
            String errorMessage = adError.getErrorMessage();
            if (!TextUtils.isEmpty(errorMessage)) {
                Log.w(FacebookAdapter.TAG, errorMessage);
            }
            FacebookAdapter.this.mBannerListener.onAdFailedToLoad(FacebookAdapter.this, FacebookAdapter.this.convertErrorCode(adError));
        }
    }

    private class FacebookAdapterNativeAdImage extends Image {
        private Drawable mDrawable;
        private Uri mUri;

        public double getScale() {
            return 1.0d;
        }

        public FacebookAdapterNativeAdImage(Uri uri) {
            this.mUri = uri;
        }

        /* Access modifiers changed, original: protected */
        public void setDrawable(Drawable drawable) {
            this.mDrawable = drawable;
        }

        public Drawable getDrawable() {
            return this.mDrawable;
        }

        public Uri getUri() {
            return this.mUri;
        }
    }

    public static class FacebookExtrasBundleBuilder {
        private static final String KEY_EXPANDABLE_ICON = "expandable_icon";
        private boolean mIsExpandableIcon;

        public FacebookExtrasBundleBuilder setNativeAdChoicesIconExpandable(boolean z) {
            this.mIsExpandableIcon = z;
            return this;
        }

        public Bundle build() {
            Bundle bundle = new Bundle();
            bundle.putBoolean(KEY_EXPANDABLE_ICON, this.mIsExpandableIcon);
            return bundle;
        }
    }

    private class FacebookReward implements RewardItem {
        public int getAmount() {
            return 1;
        }

        public String getType() {
            return "";
        }

        private FacebookReward() {
        }
    }

    private class InterstitialListener implements InterstitialAdListener {
        public void onLoggingImpression(Ad ad) {
        }

        private InterstitialListener() {
        }

        public void onAdClicked(Ad ad) {
            FacebookAdapter.this.mInterstitialListener.onAdClicked(FacebookAdapter.this);
            FacebookAdapter.this.mInterstitialListener.onAdLeftApplication(FacebookAdapter.this);
        }

        public void onAdLoaded(Ad ad) {
            FacebookAdapter.this.mInterstitialListener.onAdLoaded(FacebookAdapter.this);
        }

        public void onError(Ad ad, AdError adError) {
            String errorMessage = adError.getErrorMessage();
            if (!TextUtils.isEmpty(errorMessage)) {
                Log.w(FacebookAdapter.TAG, errorMessage);
            }
            FacebookAdapter.this.mInterstitialListener.onAdFailedToLoad(FacebookAdapter.this, FacebookAdapter.this.convertErrorCode(adError));
        }

        public void onInterstitialDismissed(Ad ad) {
            FacebookAdapter.this.mInterstitialListener.onAdClosed(FacebookAdapter.this);
        }

        public void onInterstitialDisplayed(Ad ad) {
            FacebookAdapter.this.mInterstitialListener.onAdOpened(FacebookAdapter.this);
        }
    }

    private interface NativeAdMapperListener {
        void onMappingFailed();

        void onMappingSuccess();
    }

    private class NativeListener implements AdListener, NativeAdListener {
        private NativeMediationAdRequest mMediationAdRequest;
        private NativeAd mNativeAd;

        private NativeListener(NativeAd nativeAd, NativeMediationAdRequest nativeMediationAdRequest) {
            this.mNativeAd = nativeAd;
            this.mMediationAdRequest = nativeMediationAdRequest;
        }

        public void onAdClicked(Ad ad) {
            FacebookAdapter.this.mNativeListener.onAdClicked(FacebookAdapter.this);
            FacebookAdapter.this.mNativeListener.onAdOpened(FacebookAdapter.this);
            FacebookAdapter.this.mNativeListener.onAdLeftApplication(FacebookAdapter.this);
        }

        public void onLoggingImpression(Ad ad) {
            if (FacebookAdapter.this.mIsImpressionRecorded) {
                Log.d(FacebookAdapter.TAG, "Received onLoggingImpression callback for a native whose impression is already recorded. Ignoring the duplicate callback.");
                return;
            }
            FacebookAdapter.this.mNativeListener.onAdImpression(FacebookAdapter.this);
            FacebookAdapter.this.mIsImpressionRecorded = true;
        }

        public void onAdLoaded(Ad ad) {
            if (ad != this.mNativeAd) {
                Log.w(FacebookAdapter.TAG, "Ad loaded is not a native ad.");
                FacebookAdapter.this.mNativeListener.onAdFailedToLoad(FacebookAdapter.this, 0);
                return;
            }
            final AppInstallMapper appInstallMapper = new AppInstallMapper(this.mNativeAd, this.mMediationAdRequest.getNativeAdOptions());
            appInstallMapper.mapNativeAd(new NativeAdMapperListener() {
                public void onMappingSuccess() {
                    FacebookAdapter.this.mNativeListener.onAdLoaded(FacebookAdapter.this, appInstallMapper);
                }

                public void onMappingFailed() {
                    FacebookAdapter.this.mNativeListener.onAdFailedToLoad(FacebookAdapter.this, 3);
                }
            });
        }

        public void onError(Ad ad, AdError adError) {
            String errorMessage = adError.getErrorMessage();
            if (!TextUtils.isEmpty(errorMessage)) {
                Log.w(FacebookAdapter.TAG, errorMessage);
            }
            FacebookAdapter.this.mNativeListener.onAdFailedToLoad(FacebookAdapter.this, FacebookAdapter.this.convertErrorCode(adError));
        }

        public void onMediaDownloaded(Ad ad) {
            Log.d(FacebookAdapter.TAG, "onMediaDownloaded");
        }
    }

    private class RewardedVideoListener implements RewardedVideoAdListener {
        public void onLoggingImpression(Ad ad) {
        }

        private RewardedVideoListener() {
        }

        public void onRewardedVideoCompleted() {
            FacebookAdapter.this.mRewardedListener.onVideoCompleted(FacebookAdapter.this);
            FacebookAdapter.this.mRewardedListener.onRewarded(FacebookAdapter.this, new FacebookReward());
        }

        public void onError(Ad ad, AdError adError) {
            String errorMessage = adError.getErrorMessage();
            if (!TextUtils.isEmpty(errorMessage)) {
                Log.w(FacebookAdapter.TAG, errorMessage);
            }
            FacebookAdapter.this.mRewardedListener.onAdFailedToLoad(FacebookAdapter.this, FacebookAdapter.this.convertErrorCode(adError));
        }

        public void onAdLoaded(Ad ad) {
            FacebookAdapter.this.mRewardedListener.onAdLoaded(FacebookAdapter.this);
        }

        public void onAdClicked(Ad ad) {
            FacebookAdapter.this.mRewardedListener.onAdClicked(FacebookAdapter.this);
            FacebookAdapter.this.mRewardedListener.onAdLeftApplication(FacebookAdapter.this);
        }

        public void onRewardedVideoClosed() {
            FacebookAdapter.this.mRewardedListener.onAdClosed(FacebookAdapter.this);
        }
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public void onDestroy() {
        if (this.mAdView != null) {
            this.mAdView.destroy();
        }
        if (this.mInterstitialAd != null) {
            this.mInterstitialAd.destroy();
        }
        if (this.mNativeAd != null) {
            this.mNativeAd.unregisterView();
            this.mNativeAd.destroy();
        }
        if (this.mMediaView != null) {
            this.mMediaView.destroy();
        }
        if (this.mRewardedVideoAd != null) {
            this.mRewardedVideoAd.destroy();
        }
    }

    public void requestBannerAd(Context context, MediationBannerListener mediationBannerListener, Bundle bundle, AdSize adSize, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        this.mBannerListener = mediationBannerListener;
        if (!isValidRequestParameters(context, bundle)) {
            this.mBannerListener.onAdFailedToLoad(this, 1);
        } else if (adSize == null) {
            Log.w(TAG, "Fail to request banner ad, adSize is null");
            this.mBannerListener.onAdFailedToLoad(this, 1);
        } else {
            String string = bundle.getString("pubid");
            com.facebook.ads.AdSize adSize2 = getAdSize(context, adSize);
            if (adSize2 == null) {
                String str = TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("The input ad size ");
                stringBuilder.append(adSize.toString());
                stringBuilder.append(" is not supported at this moment.");
                Log.w(str, stringBuilder.toString());
                this.mBannerListener.onAdFailedToLoad(this, 3);
                return;
            }
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("ADMOB_");
            stringBuilder2.append(getGMSVersionCode(context));
            AdSettings.setMediationService(stringBuilder2.toString());
            this.mAdView = new AdView(context, string, adSize2);
            this.mAdView.setAdListener(new BannerListener());
            buildAdRequest(mediationAdRequest);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(adSize.getWidthInPixels(context), adSize.getHeightInPixels(context));
            this.mWrappedAdView = new RelativeLayout(context);
            this.mAdView.setLayoutParams(layoutParams);
            this.mWrappedAdView.addView(this.mAdView);
            this.mAdView.loadAd();
        }
    }

    public View getBannerView() {
        return this.mWrappedAdView;
    }

    public void requestInterstitialAd(Context context, MediationInterstitialListener mediationInterstitialListener, Bundle bundle, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        this.mInterstitialListener = mediationInterstitialListener;
        if (isValidRequestParameters(context, bundle)) {
            String string = bundle.getString("pubid");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ADMOB_");
            stringBuilder.append(getGMSVersionCode(context));
            AdSettings.setMediationService(stringBuilder.toString());
            this.mInterstitialAd = new InterstitialAd(context, string);
            this.mInterstitialAd.setAdListener(new InterstitialListener());
            buildAdRequest(mediationAdRequest);
            this.mInterstitialAd.loadAd();
            return;
        }
        this.mInterstitialListener.onAdFailedToLoad(this, 1);
    }

    public void showInterstitial() {
        if (this.mInterstitialAd.isAdLoaded()) {
            this.mInterstitialAd.show();
        }
    }

    public void initialize(Context context, MediationAdRequest mediationAdRequest, String str, MediationRewardedVideoAdListener mediationRewardedVideoAdListener, Bundle bundle, Bundle bundle2) {
        this.mContext = context;
        this.mRewardedListener = mediationRewardedVideoAdListener;
        if (isValidRequestParameters(context, bundle)) {
            this.mRewardedVideoAd = new RewardedVideoAd(context, bundle.getString("pubid"));
            this.mRewardedVideoAd.setAdListener(new RewardedVideoListener());
            this.mIsInitialized = true;
            this.mRewardedListener.onInitializationSucceeded(this);
            return;
        }
        this.mRewardedListener.onAdFailedToLoad(this, 1);
    }

    public void loadAd(MediationAdRequest mediationAdRequest, Bundle bundle, Bundle bundle2) {
        if (this.mRewardedVideoAd == null) {
            Log.w(TAG, "Failed to request rewarded video ad, adapter has not been initialized.");
            this.mIsInitialized = false;
            if (this.mRewardedListener != null) {
                this.mRewardedListener.onAdFailedToLoad(this, 0);
            }
        } else if (this.mRewardedVideoAd.isAdLoaded()) {
            this.mRewardedListener.onAdLoaded(this);
        } else {
            buildAdRequest(mediationAdRequest);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ADMOB_");
            stringBuilder.append(getGMSVersionCode(this.mContext));
            AdSettings.setMediationService(stringBuilder.toString());
            this.mRewardedVideoAd.loadAd(true);
        }
    }

    public void showVideo() {
        if (this.mRewardedVideoAd == null || !this.mRewardedVideoAd.isAdLoaded()) {
            Log.w(TAG, "No ads to show.");
            if (this.mRewardedListener != null) {
                this.mRewardedListener.onAdOpened(this);
                this.mRewardedListener.onAdClosed(this);
                return;
            }
            return;
        }
        this.mRewardedVideoAd.show();
        this.mRewardedListener.onAdOpened(this);
        this.mRewardedListener.onVideoStarted(this);
    }

    public boolean isInitialized() {
        return this.mIsInitialized;
    }

    public void requestNativeAd(Context context, MediationNativeListener mediationNativeListener, Bundle bundle, NativeMediationAdRequest nativeMediationAdRequest, Bundle bundle2) {
        this.mNativeListener = mediationNativeListener;
        if (!isValidRequestParameters(context, bundle)) {
            this.mNativeListener.onAdFailedToLoad(this, 1);
        } else if (nativeMediationAdRequest.isAppInstallAdRequested() && nativeMediationAdRequest.isContentAdRequested()) {
            String string = bundle.getString("pubid");
            if (bundle2 != null) {
                this.mIsAdChoicesIconExpandable = bundle2.getBoolean("expandable_icon", true);
            }
            this.mMediaView = new MediaView(context);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ADMOB_");
            stringBuilder.append(getGMSVersionCode(context));
            AdSettings.setMediationService(stringBuilder.toString());
            this.mNativeAd = new NativeAd(context, string);
            this.mNativeAd.setAdListener(new NativeListener(this.mNativeAd, nativeMediationAdRequest));
            buildAdRequest(nativeMediationAdRequest);
            this.mNativeAd.loadAd();
        } else {
            Log.w(TAG, "Failed to request native ad. Both app install and content ad should be requested");
            this.mNativeListener.onAdFailedToLoad(this, 1);
        }
    }

    private static boolean isValidRequestParameters(Context context, Bundle bundle) {
        if (context == null) {
            Log.w(TAG, "Failed to request ad, Context is null.");
            return false;
        } else if (bundle == null) {
            Log.w(TAG, "Failed to request ad, serverParameters is null.");
            return false;
        } else if (!TextUtils.isEmpty(bundle.getString("pubid"))) {
            return true;
        } else {
            Log.w(TAG, "Failed to request ad, placementId is null or empty.");
            return false;
        }
    }

    private int convertErrorCode(AdError adError) {
        if (adError == null) {
            return 0;
        }
        int errorCode = adError.getErrorCode();
        if (errorCode != 2000) {
            switch (errorCode) {
                case 1000:
                    break;
                case 1001:
                    return 3;
                case 1002:
                    return 1;
                default:
                    return 0;
            }
        }
        return 2;
    }

    private void buildAdRequest(MediationAdRequest mediationAdRequest) {
        if (mediationAdRequest != null) {
            boolean z = true;
            if (mediationAdRequest.taggedForChildDirectedTreatment() != 1) {
                z = false;
            }
            AdSettings.setIsChildDirected(z);
        }
    }

    private com.facebook.ads.AdSize getAdSize(Context context, AdSize adSize) {
        if (adSize.getWidth() == com.facebook.ads.AdSize.BANNER_320_50.getWidth() && adSize.getHeight() == com.facebook.ads.AdSize.BANNER_320_50.getHeight()) {
            return com.facebook.ads.AdSize.BANNER_320_50;
        }
        int pixelToDip = pixelToDip(adSize.getHeightInPixels(context));
        if (pixelToDip == com.facebook.ads.AdSize.BANNER_HEIGHT_50.getHeight()) {
            return com.facebook.ads.AdSize.BANNER_HEIGHT_50;
        }
        if (pixelToDip == com.facebook.ads.AdSize.BANNER_HEIGHT_90.getHeight()) {
            return com.facebook.ads.AdSize.BANNER_HEIGHT_90;
        }
        return pixelToDip == com.facebook.ads.AdSize.RECTANGLE_HEIGHT_250.getHeight() ? com.facebook.ads.AdSize.RECTANGLE_HEIGHT_250 : null;
    }

    private int pixelToDip(int i) {
        return Math.round(((float) i) / Resources.getSystem().getDisplayMetrics().density);
    }

    private static int getGMSVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo("com.google.android.gms", 0).versionCode;
        } catch (NameNotFoundException unused) {
            return 0;
        }
    }
}
