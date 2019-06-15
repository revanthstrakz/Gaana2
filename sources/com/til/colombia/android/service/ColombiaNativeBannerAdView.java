package com.til.colombia.android.service;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.til.colombia.android.R;

public class ColombiaNativeBannerAdView extends FrameLayout {
    private View advertiserView;
    private View attributionTextView;
    private ImageView colombiaView;
    private boolean commitFlag;
    private Context context;
    private View imageView;
    private Item nativeAd;

    public ColombiaNativeBannerAdView(Context context) {
        super(context);
        this.context = context;
    }

    public ColombiaNativeBannerAdView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ColombiaNativeBannerAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.context = context;
    }

    @TargetApi(21)
    public ColombiaNativeBannerAdView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.context = context;
    }

    public void setItem(Item item) {
        if (item != null) {
            this.nativeAd = item;
        }
    }

    public View getImageView() {
        return this.imageView;
    }

    public void setImageView(View view) {
        this.imageView = view;
    }

    public View getAdvertiserView() {
        return this.advertiserView;
    }

    public void setAdvertiserView(View view) {
        this.advertiserView = view;
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

    public synchronized void commit() {
        if (getColombiaView() != null) {
            getColombiaView().setBackgroundResource(R.drawable.colombia);
            getColombiaView().setVisibility(0);
        }
        setOnClickListener(new ay(this));
        bi.a().a(((NativeItem) this.nativeAd).getItemResponse(), this.nativeAd, this);
    }
}
