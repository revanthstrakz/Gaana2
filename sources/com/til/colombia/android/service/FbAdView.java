package com.til.colombia.android.service;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import com.facebook.ads.AdChoicesView;
import com.facebook.ads.NativeAd;
import com.til.colombia.android.adapters.FbNativeAd;
import com.til.colombia.android.commons.CommonUtil;

public class FbAdView extends FrameLayout {
    private View attrView;
    private View bannerImgView;
    private ColombiaBannerView bannerView;
    private View brandView;
    private ImageView colombiaView;
    private Context context;
    private ImageView criteoIconView;
    private View ctaView;
    private View descView;
    private AdChoicesView fbIconView;
    private View iconView;
    private View imageView;
    private Item item;
    private View priceView;
    private View rating;
    private View titleView;

    public FbAdView(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public FbAdView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.context = context;
    }

    public FbAdView(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i) {
        super(context, attributeSet, i);
        this.context = context;
    }

    @RequiresApi(api = 21)
    public FbAdView(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i, @StyleRes int i2) {
        super(context, attributeSet, i, i2);
        this.context = context;
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
        if (!(this.item == null || this.item.thirdPartyAd() == null || !(this.item.thirdPartyAd() instanceof NativeAd))) {
            hideFbIcon();
            ((NativeAd) this.item.thirdPartyAd()).unregisterView();
        }
        this.item = item;
        if (this.item.thirdPartyAd() != null && (this.item.thirdPartyAd() instanceof NativeAd)) {
            hideColombiaIcon();
            showFbIcon();
            ((NativeAd) this.item.thirdPartyAd()).registerViewForInteraction(this);
            bi.a().a(((FbNativeAd) this.item).getItemResponse(), this.item, this);
        }
    }

    private void showFbIcon() {
        if (this.fbIconView == null) {
            LayoutParams layoutParams = new LayoutParams(CommonUtil.b(15.0f, this.context), CommonUtil.b(15.0f, this.context));
            layoutParams.gravity = 53;
            layoutParams.topMargin = CommonUtil.b(2.0f, this.context);
            layoutParams.rightMargin = CommonUtil.b(2.0f, this.context);
            this.fbIconView = new AdChoicesView(this.context, (NativeAd) this.item.thirdPartyAd(), true);
            addView(this.fbIconView, layoutParams);
        }
    }

    private void hideFbIcon() {
        try {
            if (this.fbIconView != null) {
                this.fbIconView.setVisibility(8);
                removeView(this.fbIconView);
                this.fbIconView = null;
            }
        } catch (Exception unused) {
        }
    }

    private void hideColombiaIcon() {
        if (this.colombiaView != null) {
            this.colombiaView.setVisibility(8);
        }
    }
}
