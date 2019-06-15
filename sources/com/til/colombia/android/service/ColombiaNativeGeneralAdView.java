package com.til.colombia.android.service;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.til.colombia.android.R;
import com.til.colombia.android.internal.a.h;

public class ColombiaNativeGeneralAdView extends FrameLayout {
    private View advertiserView;
    private View attributionTextView;
    private ColombiaBannerView bannerView;
    private View bodyView;
    private View callToActionView;
    private ImageView colombiaView;
    private View headlineView;
    private View iconView;
    private View imageView;
    private Item nativeAd;
    private View priceView;

    public ColombiaNativeGeneralAdView(Context context) {
        super(context);
    }

    public ColombiaNativeGeneralAdView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ColombiaNativeGeneralAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @TargetApi(21)
    public ColombiaNativeGeneralAdView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public void setHeadlineView(View view) {
        this.headlineView = view;
    }

    public void setImageView(View view) {
        this.imageView = view;
    }

    public void setBannerView(ColombiaBannerView colombiaBannerView) {
        this.bannerView = colombiaBannerView;
    }

    public void setBodyView(View view) {
        this.bodyView = view;
    }

    public void setCallToActionView(View view) {
        this.callToActionView = view;
    }

    public void setIconView(View view) {
        this.iconView = view;
    }

    public void setAdvertiserView(View view) {
        this.advertiserView = view;
    }

    public void setPriceView(View view) {
        this.priceView = view;
    }

    public void setAttributionTextView(View view) {
        this.attributionTextView = view;
    }

    public void setColombiaView(ImageView imageView) {
        this.colombiaView = imageView;
    }

    public View getPriceView() {
        return this.priceView;
    }

    public View getHeadlineView() {
        return this.headlineView;
    }

    public View getBodyView() {
        return this.bodyView;
    }

    public View getCallToActionView() {
        return this.callToActionView;
    }

    public View getAdvertiserView() {
        return this.advertiserView;
    }

    public View getImageView() {
        return this.imageView;
    }

    public ColombiaBannerView getBannerView() {
        return this.bannerView;
    }

    public View getIconView() {
        return this.iconView;
    }

    public View getAttributionTextView() {
        return this.attributionTextView;
    }

    public View getColombiaView() {
        return this.colombiaView;
    }

    public void setItem(Item item) {
        if (item != null) {
            this.nativeAd = item;
        }
    }

    public synchronized void commit() {
        if (getColombiaView() != null) {
            getColombiaView().setBackgroundResource(R.drawable.colombia);
            getColombiaView().setVisibility(0);
        }
        if (h.a(this.nativeAd.getCtaText()) && getCallToActionView() != null) {
            getCallToActionView().setVisibility(8);
        }
        getBannerView().setNativeAd(this.nativeAd);
        az azVar = new az(this);
        setOnClickListener(azVar);
        if (getCallToActionView() != null) {
            getCallToActionView().setOnClickListener(azVar);
        }
        bi.a().a(((NativeItem) this.nativeAd).getItemResponse(), this.nativeAd, this);
    }
}
