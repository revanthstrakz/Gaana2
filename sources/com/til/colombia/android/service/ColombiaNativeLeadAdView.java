package com.til.colombia.android.service;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.til.colombia.android.R;
import com.til.colombia.android.internal.a.h;

@Deprecated
public class ColombiaNativeLeadAdView extends FrameLayout {
    private View advertiserView;
    private View attributionTextView;
    private View bodyView;
    private ImageView colombiaView;
    private View ctaView;
    private View headlineView;
    private View imageView;
    private View logoView;
    private Item nativeAd;

    public ColombiaNativeLeadAdView(Context context) {
        super(context);
    }

    public ColombiaNativeLeadAdView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ColombiaNativeLeadAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @TargetApi(21)
    public ColombiaNativeLeadAdView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public void setHeadlineView(View view) {
        this.headlineView = view;
    }

    public void setImageView(View view) {
        this.imageView = view;
    }

    public void setBodyView(View view) {
        this.bodyView = view;
    }

    public void setCallToActionView(View view) {
        this.ctaView = view;
    }

    public void setLogoView(View view) {
        this.logoView = view;
    }

    public void setAdvertiserView(View view) {
        this.advertiserView = view;
    }

    public void setAttributionTextView(View view) {
        this.attributionTextView = view;
    }

    public void setColombiaView(ImageView imageView) {
        this.colombiaView = imageView;
    }

    public View getHeadlineView() {
        return this.headlineView;
    }

    public View getBodyView() {
        return this.bodyView;
    }

    public View getCallToActionView() {
        return this.ctaView;
    }

    public View getAdvertiserView() {
        return this.advertiserView;
    }

    public View getImageView() {
        return this.imageView;
    }

    public View getLogoView() {
        return this.logoView;
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
        ba baVar = new ba(this);
        setOnClickListener(baVar);
        if (getCallToActionView() != null) {
            getCallToActionView().setOnClickListener(baVar);
        }
        if (h.a(this.nativeAd.getCtaText()) && getCallToActionView() != null) {
            getCallToActionView().setVisibility(8);
        }
        bi.a().a(((NativeItem) this.nativeAd).getItemResponse(), this.nativeAd, this);
    }
}
