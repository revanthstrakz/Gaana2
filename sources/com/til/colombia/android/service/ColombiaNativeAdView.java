package com.til.colombia.android.service;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeAppInstallAdView;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.NativeContentAdView;

public class ColombiaNativeAdView extends FrameLayout {
    private View advertiserView;
    private View attributionTextView;
    private AttributeSet attrs;
    private ColombiaBannerView bannerView;
    private View bodyView;
    private View callToActionView;
    private ImageView colombiaView;
    private boolean commitFlag;
    private Context context;
    private int defStyleAttr;
    private int defStyleRes;
    private View headlineView;
    private View iconView;
    private View imageView;
    private ViewGroup mediatorView;
    private Item nativeAd;
    private View offerPriceView;
    private View offerTextView;
    private View priceView;
    private View rating;
    private View reviews;

    public ColombiaNativeAdView(Context context) {
        super(context);
        this.context = context;
    }

    public ColombiaNativeAdView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.context = context;
        this.attrs = attributeSet;
    }

    public ColombiaNativeAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.context = context;
        this.attrs = attributeSet;
        this.defStyleAttr = i;
    }

    @TargetApi(21)
    public ColombiaNativeAdView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.context = context;
        this.attrs = attributeSet;
        this.defStyleAttr = i;
        this.defStyleRes = i2;
    }

    public void setItem(Item item) {
        this.nativeAd = item;
        fillThirdPartyView();
    }

    public View getHeadlineView() {
        return this.headlineView;
    }

    public void setHeadlineView(View view) {
        this.headlineView = view;
    }

    public View getImageView() {
        return this.imageView;
    }

    public void setImageView(View view) {
        this.imageView = view;
    }

    public View getBodyView() {
        return this.bodyView;
    }

    public void setBodyView(View view) {
        this.bodyView = view;
    }

    public View getCallToActionView() {
        return this.callToActionView;
    }

    public void setCallToActionView(View view) {
        this.callToActionView = view;
    }

    public View getIconView() {
        return this.iconView;
    }

    public void setIconView(View view) {
        this.iconView = view;
    }

    public View getAdvertiserView() {
        return this.advertiserView;
    }

    public void setAdvertiserView(View view) {
        this.advertiserView = view;
    }

    public View getRating() {
        return this.rating;
    }

    public void setRating(View view) {
        this.rating = view;
    }

    public View getPriceView() {
        return this.priceView;
    }

    public void setPriceView(View view) {
        this.priceView = view;
    }

    public View getReviews() {
        return this.reviews;
    }

    public void setReviews(View view) {
        this.reviews = view;
    }

    public View getAttributionTextView() {
        return this.attributionTextView;
    }

    public void setAttributionTextView(View view) {
        this.attributionTextView = view;
    }

    public ImageView getColombiaView() {
        return this.colombiaView;
    }

    public void setColombiaView(ImageView imageView) {
        this.colombiaView = imageView;
    }

    public View getOfferPriceView() {
        return this.offerPriceView;
    }

    public void setOfferPriceView(View view) {
        this.offerPriceView = view;
    }

    public View getOfferTextView() {
        return this.offerTextView;
    }

    public void setOfferTextView(View view) {
        this.offerTextView = view;
    }

    public ColombiaBannerView getBannerView() {
        return this.bannerView;
    }

    public void setBannerView(ColombiaBannerView colombiaBannerView) {
        this.bannerView = colombiaBannerView;
    }

    private void fillThirdPartyView() {
        if (this.nativeAd.thirdPartyAd() != null) {
            if (this.nativeAd.thirdPartyAd() instanceof NativeAppInstallAd) {
                if (this.mediatorView == null) {
                    if (this.context != null && this.attrs == null) {
                        this.mediatorView = new NativeAppInstallAdView(this.context);
                    } else if (this.context != null && this.defStyleAttr == 0) {
                        this.mediatorView = new NativeAppInstallAdView(this.context, this.attrs);
                    } else if (this.context != null && this.defStyleAttr > 0) {
                        this.mediatorView = new NativeAppInstallAdView(this.context, this.attrs, this.defStyleAttr);
                    } else if (this.context == null || this.defStyleAttr <= 0 || this.defStyleRes <= 0) {
                        this.mediatorView = new NativeAppInstallAdView(this.context);
                    } else {
                        this.mediatorView = new NativeAppInstallAdView(this.context, this.attrs, this.defStyleAttr, this.defStyleRes);
                    }
                }
                ((NativeAppInstallAdView) this.mediatorView).setNativeAd((NativeAd) this.nativeAd.thirdPartyAd());
            }
            if (this.nativeAd.thirdPartyAd() instanceof NativeContentAd) {
                if (this.mediatorView == null) {
                    if (this.context != null && this.attrs == null) {
                        this.mediatorView = new NativeContentAdView(this.context);
                    } else if (this.context != null && this.attrs != null && this.defStyleAttr == 0) {
                        this.mediatorView = new NativeContentAdView(this.context, this.attrs);
                    } else if (this.context != null && this.attrs != null && this.defStyleAttr > 0) {
                        this.mediatorView = new NativeContentAdView(this.context, this.attrs, this.defStyleAttr);
                    } else if (this.context == null || this.attrs == null || this.defStyleAttr <= 0 || this.defStyleRes <= 0) {
                        this.mediatorView = new NativeContentAdView(this.context);
                    } else {
                        this.mediatorView = new NativeContentAdView(this.context, this.attrs, this.defStyleAttr, this.defStyleRes);
                    }
                }
                ((NativeContentAdView) this.mediatorView).setNativeAd((NativeContentAd) this.nativeAd.thirdPartyAd());
            }
        }
    }

    /* JADX WARNING: Missing block: B:107:0x0345, code skipped:
            return;
     */
    public synchronized void commit() {
        /*
        r9 = this;
        monitor-enter(r9);
        r0 = r9.commitFlag;	 Catch:{ all -> 0x0346 }
        if (r0 == 0) goto L_0x0007;
    L_0x0005:
        monitor-exit(r9);
        return;
    L_0x0007:
        r0 = 1;
        r9.commitFlag = r0;	 Catch:{ all -> 0x0346 }
        r1 = r9.nativeAd;	 Catch:{ all -> 0x0346 }
        r1 = r1.thirdPartyAd();	 Catch:{ all -> 0x0346 }
        r2 = 0;
        r3 = 8;
        if (r1 == 0) goto L_0x00e6;
    L_0x0015:
        r1 = r9.mediatorView;	 Catch:{ all -> 0x0346 }
        if (r1 == 0) goto L_0x00e6;
    L_0x0019:
        r1 = r9.nativeAd;	 Catch:{ all -> 0x0346 }
        r1 = r1.thirdPartyAd();	 Catch:{ all -> 0x0346 }
        r1 = r1 instanceof com.google.android.gms.ads.formats.NativeAppInstallAd;	 Catch:{ all -> 0x0346 }
        if (r1 == 0) goto L_0x00e6;
    L_0x0023:
        r0 = r9.mediatorView;	 Catch:{ all -> 0x0346 }
        r0 = (com.google.android.gms.ads.formats.NativeAppInstallAdView) r0;	 Catch:{ all -> 0x0346 }
        r1 = r9.getHeadlineView();	 Catch:{ all -> 0x0346 }
        r0.setHeadlineView(r1);	 Catch:{ all -> 0x0346 }
        r0 = r9.mediatorView;	 Catch:{ all -> 0x0346 }
        r0 = (com.google.android.gms.ads.formats.NativeAppInstallAdView) r0;	 Catch:{ all -> 0x0346 }
        r1 = r9.getImageView();	 Catch:{ all -> 0x0346 }
        r0.setImageView(r1);	 Catch:{ all -> 0x0346 }
        r0 = r9.mediatorView;	 Catch:{ all -> 0x0346 }
        r0 = (com.google.android.gms.ads.formats.NativeAppInstallAdView) r0;	 Catch:{ all -> 0x0346 }
        r1 = r9.getBodyView();	 Catch:{ all -> 0x0346 }
        r0.setBodyView(r1);	 Catch:{ all -> 0x0346 }
        r0 = r9.mediatorView;	 Catch:{ all -> 0x0346 }
        r0 = (com.google.android.gms.ads.formats.NativeAppInstallAdView) r0;	 Catch:{ all -> 0x0346 }
        r1 = r9.getCallToActionView();	 Catch:{ all -> 0x0346 }
        r0.setCallToActionView(r1);	 Catch:{ all -> 0x0346 }
        r0 = r9.mediatorView;	 Catch:{ all -> 0x0346 }
        r0 = (com.google.android.gms.ads.formats.NativeAppInstallAdView) r0;	 Catch:{ all -> 0x0346 }
        r1 = r9.getIconView();	 Catch:{ all -> 0x0346 }
        r0.setIconView(r1);	 Catch:{ all -> 0x0346 }
        r0 = r9.mediatorView;	 Catch:{ all -> 0x0346 }
        r0 = (com.google.android.gms.ads.formats.NativeAppInstallAdView) r0;	 Catch:{ all -> 0x0346 }
        r1 = r9.getAdvertiserView();	 Catch:{ all -> 0x0346 }
        r0.setStoreView(r1);	 Catch:{ all -> 0x0346 }
        r0 = r9.mediatorView;	 Catch:{ all -> 0x0346 }
        r0 = (com.google.android.gms.ads.formats.NativeAppInstallAdView) r0;	 Catch:{ all -> 0x0346 }
        r1 = r9.getPriceView();	 Catch:{ all -> 0x0346 }
        r0.setPriceView(r1);	 Catch:{ all -> 0x0346 }
        r0 = r9.mediatorView;	 Catch:{ all -> 0x0346 }
        r0 = (com.google.android.gms.ads.formats.NativeAppInstallAdView) r0;	 Catch:{ all -> 0x0346 }
        r1 = r9.getRating();	 Catch:{ all -> 0x0346 }
        r0.setStarRatingView(r1);	 Catch:{ all -> 0x0346 }
        r0 = r2;
    L_0x007c:
        r1 = r9.getChildCount();	 Catch:{ all -> 0x0346 }
        if (r0 >= r1) goto L_0x0099;
    L_0x0082:
        r1 = r9.getChildAt(r0);	 Catch:{ all -> 0x0346 }
        r9.removeViewAt(r0);	 Catch:{ all -> 0x0346 }
        r1.setVisibility(r2);	 Catch:{ all -> 0x0346 }
        r4 = r9.mediatorView;	 Catch:{ all -> 0x0346 }
        r4.addView(r1);	 Catch:{ all -> 0x0346 }
        r1 = r9.mediatorView;	 Catch:{ all -> 0x0346 }
        r1.setVisibility(r2);	 Catch:{ all -> 0x0346 }
        r0 = r0 + 1;
        goto L_0x007c;
    L_0x0099:
        r9.removeAllViews();	 Catch:{ all -> 0x0346 }
        r0 = r9.mediatorView;	 Catch:{ all -> 0x0346 }
        r9.addView(r0);	 Catch:{ all -> 0x0346 }
        r0 = r9.mediatorView;	 Catch:{ all -> 0x0346 }
        r9.bringChildToFront(r0);	 Catch:{ all -> 0x0346 }
        r9.setVisibility(r2);	 Catch:{ all -> 0x0346 }
        r0 = r9.getColombiaView();	 Catch:{ all -> 0x0346 }
        if (r0 == 0) goto L_0x00b6;
    L_0x00af:
        r0 = r9.getColombiaView();	 Catch:{ all -> 0x0346 }
        r0.setVisibility(r3);	 Catch:{ all -> 0x0346 }
    L_0x00b6:
        r0 = r9.nativeAd;	 Catch:{ all -> 0x0346 }
        r0 = r0.getStarRating();	 Catch:{ all -> 0x0346 }
        if (r0 != 0) goto L_0x00d3;
    L_0x00be:
        r0 = r9.mediatorView;	 Catch:{ all -> 0x0346 }
        r0 = (com.google.android.gms.ads.formats.NativeAppInstallAdView) r0;	 Catch:{ all -> 0x0346 }
        r0 = r0.getStarRatingView();	 Catch:{ all -> 0x0346 }
        if (r0 == 0) goto L_0x00d3;
    L_0x00c8:
        r0 = r9.mediatorView;	 Catch:{ all -> 0x0346 }
        r0 = (com.google.android.gms.ads.formats.NativeAppInstallAdView) r0;	 Catch:{ all -> 0x0346 }
        r0 = r0.getStarRatingView();	 Catch:{ all -> 0x0346 }
        r0.setVisibility(r3);	 Catch:{ all -> 0x0346 }
    L_0x00d3:
        r0 = com.til.colombia.android.service.bi.a();	 Catch:{ all -> 0x0346 }
        r1 = r9.nativeAd;	 Catch:{ all -> 0x0346 }
        r1 = (com.til.colombia.android.adapters.GoogleNativeAd) r1;	 Catch:{ all -> 0x0346 }
        r1 = r1.getItemResponse();	 Catch:{ all -> 0x0346 }
        r2 = r9.nativeAd;	 Catch:{ all -> 0x0346 }
        r0.a(r1, r2, r9);	 Catch:{ all -> 0x0346 }
        monitor-exit(r9);
        return;
    L_0x00e6:
        r1 = r9.nativeAd;	 Catch:{ all -> 0x0346 }
        r1 = r1.thirdPartyAd();	 Catch:{ all -> 0x0346 }
        if (r1 == 0) goto L_0x0189;
    L_0x00ee:
        r1 = r9.mediatorView;	 Catch:{ all -> 0x0346 }
        if (r1 == 0) goto L_0x0189;
    L_0x00f2:
        r1 = r9.nativeAd;	 Catch:{ all -> 0x0346 }
        r1 = r1.thirdPartyAd();	 Catch:{ all -> 0x0346 }
        r1 = r1 instanceof com.google.android.gms.ads.formats.NativeContentAd;	 Catch:{ all -> 0x0346 }
        if (r1 == 0) goto L_0x0189;
    L_0x00fc:
        r0 = r9.mediatorView;	 Catch:{ all -> 0x0346 }
        r0 = (com.google.android.gms.ads.formats.NativeContentAdView) r0;	 Catch:{ all -> 0x0346 }
        r1 = r9.getAdvertiserView();	 Catch:{ all -> 0x0346 }
        r0.setAdvertiserView(r1);	 Catch:{ all -> 0x0346 }
        r0 = r9.mediatorView;	 Catch:{ all -> 0x0346 }
        r0 = (com.google.android.gms.ads.formats.NativeContentAdView) r0;	 Catch:{ all -> 0x0346 }
        r1 = r9.getIconView();	 Catch:{ all -> 0x0346 }
        r0.setLogoView(r1);	 Catch:{ all -> 0x0346 }
        r0 = r9.mediatorView;	 Catch:{ all -> 0x0346 }
        r0 = (com.google.android.gms.ads.formats.NativeContentAdView) r0;	 Catch:{ all -> 0x0346 }
        r1 = r9.getBodyView();	 Catch:{ all -> 0x0346 }
        r0.setBodyView(r1);	 Catch:{ all -> 0x0346 }
        r0 = r9.mediatorView;	 Catch:{ all -> 0x0346 }
        r0 = (com.google.android.gms.ads.formats.NativeContentAdView) r0;	 Catch:{ all -> 0x0346 }
        r1 = r9.getImageView();	 Catch:{ all -> 0x0346 }
        r0.setImageView(r1);	 Catch:{ all -> 0x0346 }
        r0 = r9.mediatorView;	 Catch:{ all -> 0x0346 }
        r0 = (com.google.android.gms.ads.formats.NativeContentAdView) r0;	 Catch:{ all -> 0x0346 }
        r1 = r9.getHeadlineView();	 Catch:{ all -> 0x0346 }
        r0.setHeadlineView(r1);	 Catch:{ all -> 0x0346 }
        r0 = r9.mediatorView;	 Catch:{ all -> 0x0346 }
        r0 = (com.google.android.gms.ads.formats.NativeContentAdView) r0;	 Catch:{ all -> 0x0346 }
        r1 = r9.getCallToActionView();	 Catch:{ all -> 0x0346 }
        r0.setCallToActionView(r1);	 Catch:{ all -> 0x0346 }
        r0 = r2;
    L_0x013f:
        r1 = r9.getChildCount();	 Catch:{ all -> 0x0346 }
        if (r0 >= r1) goto L_0x0159;
    L_0x0145:
        r1 = r9.getChildAt(r0);	 Catch:{ all -> 0x0346 }
        r9.removeViewAt(r0);	 Catch:{ all -> 0x0346 }
        r4 = r9.mediatorView;	 Catch:{ all -> 0x0346 }
        r4.addView(r1);	 Catch:{ all -> 0x0346 }
        r1 = r9.mediatorView;	 Catch:{ all -> 0x0346 }
        r1.setVisibility(r2);	 Catch:{ all -> 0x0346 }
        r0 = r0 + 1;
        goto L_0x013f;
    L_0x0159:
        r9.removeAllViews();	 Catch:{ all -> 0x0346 }
        r0 = r9.mediatorView;	 Catch:{ all -> 0x0346 }
        r9.addView(r0);	 Catch:{ all -> 0x0346 }
        r0 = r9.mediatorView;	 Catch:{ all -> 0x0346 }
        r9.bringChildToFront(r0);	 Catch:{ all -> 0x0346 }
        r9.setVisibility(r2);	 Catch:{ all -> 0x0346 }
        r0 = r9.getColombiaView();	 Catch:{ all -> 0x0346 }
        if (r0 == 0) goto L_0x0176;
    L_0x016f:
        r0 = r9.getColombiaView();	 Catch:{ all -> 0x0346 }
        r0.setVisibility(r3);	 Catch:{ all -> 0x0346 }
    L_0x0176:
        r0 = com.til.colombia.android.service.bi.a();	 Catch:{ all -> 0x0346 }
        r1 = r9.nativeAd;	 Catch:{ all -> 0x0346 }
        r1 = (com.til.colombia.android.adapters.GoogleNativeAd) r1;	 Catch:{ all -> 0x0346 }
        r1 = r1.getItemResponse();	 Catch:{ all -> 0x0346 }
        r2 = r9.nativeAd;	 Catch:{ all -> 0x0346 }
        r0.a(r1, r2, r9);	 Catch:{ all -> 0x0346 }
        monitor-exit(r9);
        return;
    L_0x0189:
        r1 = r9.nativeAd;	 Catch:{ all -> 0x0346 }
        r1 = r1.thirdPartyAd();	 Catch:{ all -> 0x0346 }
        r4 = 53;
        r5 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r6 = 1097859072; // 0x41700000 float:15.0 double:5.424144515E-315;
        if (r1 == 0) goto L_0x01f4;
    L_0x0197:
        r1 = r9.nativeAd;	 Catch:{ all -> 0x0346 }
        r1 = r1.thirdPartyAd();	 Catch:{ all -> 0x0346 }
        r1 = r1 instanceof com.facebook.ads.NativeAd;	 Catch:{ all -> 0x0346 }
        if (r1 == 0) goto L_0x01f4;
    L_0x01a1:
        r1 = new android.widget.FrameLayout$LayoutParams;	 Catch:{ all -> 0x0346 }
        r2 = r9.context;	 Catch:{ all -> 0x0346 }
        r2 = com.til.colombia.android.commons.CommonUtil.b(r6, r2);	 Catch:{ all -> 0x0346 }
        r3 = r9.context;	 Catch:{ all -> 0x0346 }
        r3 = com.til.colombia.android.commons.CommonUtil.b(r6, r3);	 Catch:{ all -> 0x0346 }
        r1.<init>(r2, r3);	 Catch:{ all -> 0x0346 }
        r1.gravity = r4;	 Catch:{ all -> 0x0346 }
        r2 = r9.context;	 Catch:{ all -> 0x0346 }
        r2 = com.til.colombia.android.commons.CommonUtil.b(r5, r2);	 Catch:{ all -> 0x0346 }
        r1.topMargin = r2;	 Catch:{ all -> 0x0346 }
        r2 = r9.context;	 Catch:{ all -> 0x0346 }
        r2 = com.til.colombia.android.commons.CommonUtil.b(r5, r2);	 Catch:{ all -> 0x0346 }
        r1.rightMargin = r2;	 Catch:{ all -> 0x0346 }
        r2 = new com.facebook.ads.AdChoicesView;	 Catch:{ all -> 0x0346 }
        r3 = r9.context;	 Catch:{ all -> 0x0346 }
        r4 = r9.nativeAd;	 Catch:{ all -> 0x0346 }
        r4 = r4.thirdPartyAd();	 Catch:{ all -> 0x0346 }
        r4 = (com.facebook.ads.NativeAd) r4;	 Catch:{ all -> 0x0346 }
        r2.<init>(r3, r4, r0);	 Catch:{ all -> 0x0346 }
        r9.addView(r2, r1);	 Catch:{ all -> 0x0346 }
        r0 = r9.nativeAd;	 Catch:{ all -> 0x0346 }
        r0 = r0.thirdPartyAd();	 Catch:{ all -> 0x0346 }
        r0 = (com.facebook.ads.NativeAd) r0;	 Catch:{ all -> 0x0346 }
        r0.registerViewForInteraction(r9);	 Catch:{ all -> 0x0346 }
        r0 = com.til.colombia.android.service.bi.a();	 Catch:{ all -> 0x0346 }
        r1 = r9.nativeAd;	 Catch:{ all -> 0x0346 }
        r1 = (com.til.colombia.android.adapters.FbNativeAd) r1;	 Catch:{ all -> 0x0346 }
        r1 = r1.getItemResponse();	 Catch:{ all -> 0x0346 }
        r2 = r9.nativeAd;	 Catch:{ all -> 0x0346 }
        r0.a(r1, r2, r9);	 Catch:{ all -> 0x0346 }
        monitor-exit(r9);
        return;
    L_0x01f4:
        r0 = r9.nativeAd;	 Catch:{ all -> 0x0346 }
        r0 = r0.thirdPartyAd();	 Catch:{ all -> 0x0346 }
        if (r0 != 0) goto L_0x0344;
    L_0x01fc:
        r0 = r9.nativeAd;	 Catch:{ all -> 0x0346 }
        r0 = (com.til.colombia.android.service.NativeItem) r0;	 Catch:{ all -> 0x0346 }
        r0 = r0.getNetworkId();	 Catch:{ all -> 0x0346 }
        r1 = "3700";
        r0 = r0.equalsIgnoreCase(r1);	 Catch:{ all -> 0x0346 }
        if (r0 == 0) goto L_0x0263;
    L_0x020c:
        r0 = new android.widget.ImageView;	 Catch:{ all -> 0x0346 }
        r1 = r9.context;	 Catch:{ all -> 0x0346 }
        r0.<init>(r1);	 Catch:{ all -> 0x0346 }
        r1 = new android.widget.FrameLayout$LayoutParams;	 Catch:{ all -> 0x0346 }
        r7 = r9.context;	 Catch:{ all -> 0x0346 }
        r7 = com.til.colombia.android.commons.CommonUtil.b(r6, r7);	 Catch:{ all -> 0x0346 }
        r8 = r9.context;	 Catch:{ all -> 0x0346 }
        r6 = com.til.colombia.android.commons.CommonUtil.b(r6, r8);	 Catch:{ all -> 0x0346 }
        r1.<init>(r7, r6);	 Catch:{ all -> 0x0346 }
        r1.gravity = r4;	 Catch:{ all -> 0x0346 }
        r4 = r9.context;	 Catch:{ all -> 0x0346 }
        r4 = com.til.colombia.android.commons.CommonUtil.b(r5, r4);	 Catch:{ all -> 0x0346 }
        r1.topMargin = r4;	 Catch:{ all -> 0x0346 }
        r4 = r9.context;	 Catch:{ all -> 0x0346 }
        r4 = com.til.colombia.android.commons.CommonUtil.b(r5, r4);	 Catch:{ all -> 0x0346 }
        r1.rightMargin = r4;	 Catch:{ all -> 0x0346 }
        r9.addView(r0, r1);	 Catch:{ all -> 0x0346 }
        r1 = r9.nativeAd;	 Catch:{ all -> 0x0346 }
        r1 = (com.til.colombia.android.service.NativeItem) r1;	 Catch:{ all -> 0x0346 }
        r1 = r1.getAdChoiceImageUrl();	 Catch:{ all -> 0x0346 }
        r1 = com.til.colombia.android.commons.a.h.a(r1);	 Catch:{ all -> 0x0346 }
        if (r1 == 0) goto L_0x024b;
    L_0x0247:
        r0.setImageBitmap(r1);	 Catch:{ all -> 0x0346 }
        goto L_0x025b;
    L_0x024b:
        r1 = new com.til.colombia.android.utils.b;	 Catch:{ all -> 0x0346 }
        r1.<init>();	 Catch:{ all -> 0x0346 }
        r4 = r9.nativeAd;	 Catch:{ all -> 0x0346 }
        r4 = (com.til.colombia.android.service.NativeItem) r4;	 Catch:{ all -> 0x0346 }
        r4 = r4.getAdChoiceImageUrl();	 Catch:{ all -> 0x0346 }
        r1.a(r0, r4);	 Catch:{ all -> 0x0346 }
    L_0x025b:
        r1 = new com.til.colombia.android.service.ap;	 Catch:{ all -> 0x0346 }
        r1.<init>(r9);	 Catch:{ all -> 0x0346 }
        r0.setOnClickListener(r1);	 Catch:{ all -> 0x0346 }
    L_0x0263:
        r0 = r9.getColombiaView();	 Catch:{ all -> 0x0346 }
        if (r0 == 0) goto L_0x0279;
    L_0x0269:
        r0 = r9.getColombiaView();	 Catch:{ all -> 0x0346 }
        r1 = com.til.colombia.android.R.drawable.colombia;	 Catch:{ all -> 0x0346 }
        r0.setBackgroundResource(r1);	 Catch:{ all -> 0x0346 }
        r0 = r9.getColombiaView();	 Catch:{ all -> 0x0346 }
        r0.setVisibility(r2);	 Catch:{ all -> 0x0346 }
    L_0x0279:
        r0 = r9.nativeAd;	 Catch:{ all -> 0x0346 }
        r0 = r0.getCtaText();	 Catch:{ all -> 0x0346 }
        r0 = com.til.colombia.android.internal.a.h.a(r0);	 Catch:{ all -> 0x0346 }
        if (r0 == 0) goto L_0x0292;
    L_0x0285:
        r0 = r9.getCallToActionView();	 Catch:{ all -> 0x0346 }
        if (r0 == 0) goto L_0x0292;
    L_0x028b:
        r0 = r9.getCallToActionView();	 Catch:{ all -> 0x0346 }
        r0.setVisibility(r3);	 Catch:{ all -> 0x0346 }
    L_0x0292:
        r0 = r9.nativeAd;	 Catch:{ all -> 0x0346 }
        r0 = r0.getItemType();	 Catch:{ all -> 0x0346 }
        r1 = com.til.colombia.android.service.ColombiaAdManager.ITEM_TYPE.CONTENT;	 Catch:{ all -> 0x0346 }
        if (r0 == r1) goto L_0x02ba;
    L_0x029c:
        r0 = r9.nativeAd;	 Catch:{ all -> 0x0346 }
        r0 = r0.getItemType();	 Catch:{ all -> 0x0346 }
        r1 = com.til.colombia.android.service.ColombiaAdManager.ITEM_TYPE.LEADGEN;	 Catch:{ all -> 0x0346 }
        if (r0 == r1) goto L_0x02ba;
    L_0x02a6:
        r0 = r9.nativeAd;	 Catch:{ all -> 0x0346 }
        r0 = r0.getItemType();	 Catch:{ all -> 0x0346 }
        r1 = com.til.colombia.android.service.ColombiaAdManager.ITEM_TYPE.GENERAL;	 Catch:{ all -> 0x0346 }
        if (r0 == r1) goto L_0x02ba;
    L_0x02b0:
        r0 = r9.nativeAd;	 Catch:{ all -> 0x0346 }
        r0 = r0.getItemType();	 Catch:{ all -> 0x0346 }
        r1 = com.til.colombia.android.service.ColombiaAdManager.ITEM_TYPE.PRODUCT;	 Catch:{ all -> 0x0346 }
        if (r0 != r1) goto L_0x0305;
    L_0x02ba:
        r0 = r9.getReviews();	 Catch:{ all -> 0x0346 }
        if (r0 == 0) goto L_0x02c7;
    L_0x02c0:
        r0 = r9.getReviews();	 Catch:{ all -> 0x0346 }
        r0.setVisibility(r3);	 Catch:{ all -> 0x0346 }
    L_0x02c7:
        r0 = r9.getRating();	 Catch:{ all -> 0x0346 }
        if (r0 == 0) goto L_0x02d4;
    L_0x02cd:
        r0 = r9.getRating();	 Catch:{ all -> 0x0346 }
        r0.setVisibility(r3);	 Catch:{ all -> 0x0346 }
    L_0x02d4:
        r0 = r9.nativeAd;	 Catch:{ all -> 0x0346 }
        r0 = r0.getItemType();	 Catch:{ all -> 0x0346 }
        r1 = com.til.colombia.android.service.ColombiaAdManager.ITEM_TYPE.PRODUCT;	 Catch:{ all -> 0x0346 }
        if (r0 == r1) goto L_0x0305;
    L_0x02de:
        r0 = r9.getPriceView();	 Catch:{ all -> 0x0346 }
        if (r0 == 0) goto L_0x02eb;
    L_0x02e4:
        r0 = r9.getPriceView();	 Catch:{ all -> 0x0346 }
        r0.setVisibility(r3);	 Catch:{ all -> 0x0346 }
    L_0x02eb:
        r0 = r9.getOfferPriceView();	 Catch:{ all -> 0x0346 }
        if (r0 == 0) goto L_0x02f8;
    L_0x02f1:
        r0 = r9.getOfferPriceView();	 Catch:{ all -> 0x0346 }
        r0.setVisibility(r3);	 Catch:{ all -> 0x0346 }
    L_0x02f8:
        r0 = r9.getOfferTextView();	 Catch:{ all -> 0x0346 }
        if (r0 == 0) goto L_0x0305;
    L_0x02fe:
        r0 = r9.getOfferTextView();	 Catch:{ all -> 0x0346 }
        r0.setVisibility(r3);	 Catch:{ all -> 0x0346 }
    L_0x0305:
        r0 = r9.nativeAd;	 Catch:{ all -> 0x0346 }
        r0 = r0.getItemType();	 Catch:{ all -> 0x0346 }
        r1 = com.til.colombia.android.service.ColombiaAdManager.ITEM_TYPE.GENERAL;	 Catch:{ all -> 0x0346 }
        if (r0 != r1) goto L_0x031e;
    L_0x030f:
        r0 = r9.getBannerView();	 Catch:{ all -> 0x0346 }
        if (r0 == 0) goto L_0x031e;
    L_0x0315:
        r0 = r9.getBannerView();	 Catch:{ all -> 0x0346 }
        r1 = r9.nativeAd;	 Catch:{ all -> 0x0346 }
        r0.setNativeAd(r1);	 Catch:{ all -> 0x0346 }
    L_0x031e:
        r0 = new com.til.colombia.android.service.aq;	 Catch:{ all -> 0x0346 }
        r0.<init>(r9);	 Catch:{ all -> 0x0346 }
        r9.setOnClickListener(r0);	 Catch:{ all -> 0x0346 }
        r1 = r9.getCallToActionView();	 Catch:{ all -> 0x0346 }
        if (r1 == 0) goto L_0x0333;
    L_0x032c:
        r1 = r9.getCallToActionView();	 Catch:{ all -> 0x0346 }
        r1.setOnClickListener(r0);	 Catch:{ all -> 0x0346 }
    L_0x0333:
        r0 = com.til.colombia.android.service.bi.a();	 Catch:{ all -> 0x0346 }
        r1 = r9.nativeAd;	 Catch:{ all -> 0x0346 }
        r1 = (com.til.colombia.android.service.NativeItem) r1;	 Catch:{ all -> 0x0346 }
        r1 = r1.getItemResponse();	 Catch:{ all -> 0x0346 }
        r2 = r9.nativeAd;	 Catch:{ all -> 0x0346 }
        r0.a(r1, r2, r9);	 Catch:{ all -> 0x0346 }
    L_0x0344:
        monitor-exit(r9);
        return;
    L_0x0346:
        r0 = move-exception;
        monitor-exit(r9);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.til.colombia.android.service.ColombiaNativeAdView.commit():void");
    }
}
