package com.til.colombia.android.service;

import android.content.Context;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.NativeContentAdView;
import com.til.colombia.android.adapters.GoogleNativeAd;

public class GoogleContentAdView extends FrameLayout {
    private View attrView;
    private View brandView;
    private NativeContentAdView contentAdView;
    private View ctaView;
    private View descView;
    private View iconView;
    private View imageView;
    private Item item;
    private Context mContext;
    private View priceView;
    private View rating;
    private View titleView;

    public GoogleContentAdView(Context context) {
        super(context);
        this.mContext = context;
    }

    public GoogleContentAdView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
    }

    public GoogleContentAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
    }

    @RequiresApi(api = 21)
    public GoogleContentAdView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mContext = context;
    }

    public void setGoogleView(NativeContentAdView nativeContentAdView) {
        this.contentAdView = nativeContentAdView;
    }

    @Deprecated
    public View getHeadlineView() {
        return getTitleView();
    }

    @Deprecated
    public View setHeadlineView(View view) {
        return setTitleView(view);
    }

    public View getTitleView() {
        return this.titleView;
    }

    public View setTitleView(View view) {
        this.titleView = view;
        return this.titleView;
    }

    @Deprecated
    public View getBodyView() {
        return getDescriptionView();
    }

    @Deprecated
    public View setBodyView(View view) {
        return setDescriptionView(view);
    }

    public View getDescriptionView() {
        return this.descView;
    }

    public View setDescriptionView(View view) {
        this.descView = view;
        return this.descView;
    }

    @Deprecated
    public View getAdvertiserView() {
        return getBrandView();
    }

    @Deprecated
    public View setAdvertiserView(View view) {
        return setBrandView(view);
    }

    public View getBrandView() {
        return this.brandView;
    }

    public View setBrandView(View view) {
        this.brandView = view;
        return this.brandView;
    }

    public View getPriceView() {
        return this.priceView;
    }

    public View setPriceView(View view) {
        this.priceView = view;
        return this.priceView;
    }

    public View getRating() {
        return this.rating;
    }

    public View setRating(View view) {
        this.rating = view;
        return this.rating;
    }

    public View getAttributionTextView() {
        return this.attrView;
    }

    public View setAttributionTextView(View view) {
        this.attrView = view;
        return this.attrView;
    }

    public View getIconView() {
        return this.iconView;
    }

    public View setIconView(View view) {
        this.iconView = view;
        return this.iconView;
    }

    public View getImageView() {
        return this.imageView;
    }

    public View setImageView(View view) {
        this.imageView = view;
        return this.imageView;
    }

    public View getCallToActionView() {
        return this.ctaView;
    }

    public View setCallToActionView(View view) {
        this.ctaView = view;
        return this.ctaView;
    }

    public void commitItem(Item item) {
        this.item = item;
        if (item.thirdPartyAd() instanceof NativeContentAd) {
            this.contentAdView.setNativeAd((NativeAd) item.thirdPartyAd());
            this.contentAdView.setAdvertiserView(getBrandView());
            this.contentAdView.setLogoView(getIconView());
            this.contentAdView.setBodyView(getDescriptionView());
            this.contentAdView.setImageView(getImageView());
            this.contentAdView.setHeadlineView(getTitleView());
            this.contentAdView.setCallToActionView(getCallToActionView());
        }
        bi.a().a(((GoogleNativeAd) item).getItemResponse(), item, this);
    }
}
