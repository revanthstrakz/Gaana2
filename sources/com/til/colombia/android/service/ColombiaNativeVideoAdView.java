package com.til.colombia.android.service;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.til.colombia.android.R;
import com.til.colombia.android.internal.Log;
import com.til.colombia.android.internal.a.h;

public class ColombiaNativeVideoAdView extends FrameLayout {
    private static final String LOG_TAG = "MediaPlayerService";
    private View advertiserView;
    private View attributionTextView;
    private View bodyView;
    private View callToActionView;
    private ImageView colombiaView;
    private boolean commitFlag;
    private View displayUrlView;
    private View headlineView;
    private View iconView;
    private View imageView;
    private Item nativeAd;
    private ColombiaVideoView videoView;

    public ColombiaNativeVideoAdView(Context context) {
        super(context);
        setVisibility(8);
    }

    public ColombiaNativeVideoAdView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setVisibility(8);
    }

    public ColombiaNativeVideoAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setVisibility(8);
    }

    @TargetApi(21)
    public ColombiaNativeVideoAdView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        setVisibility(8);
    }

    public View setHeadlineView(View view) {
        this.headlineView = view;
        return this.headlineView;
    }

    public View setVideoView(ColombiaVideoView colombiaVideoView) {
        this.videoView = colombiaVideoView;
        return this.videoView;
    }

    public View setImageView(View view) {
        this.imageView = view;
        return this.imageView;
    }

    public View setBodyView(View view) {
        this.bodyView = view;
        return this.bodyView;
    }

    @Deprecated
    public View setCallToActionView(View view) {
        this.callToActionView = view;
        return this.callToActionView;
    }

    public View setIconView(View view) {
        this.iconView = view;
        return this.iconView;
    }

    public View setAdvertiserView(View view) {
        this.advertiserView = view;
        return this.advertiserView;
    }

    public View setAttributionTextView(View view) {
        this.attributionTextView = view;
        return this.attributionTextView;
    }

    public View setColombiaView(ImageView imageView) {
        this.colombiaView = imageView;
        return this.colombiaView;
    }

    public View setDisplayUrlView(View view) {
        this.displayUrlView = view;
        return this.displayUrlView;
    }

    public void setItem(Item item) {
        this.nativeAd = item;
    }

    public View getHeadlineView() {
        return this.headlineView;
    }

    public ColombiaVideoView getVideoView() {
        return this.videoView;
    }

    public View getImageView() {
        return this.imageView;
    }

    public View getBodyView() {
        return this.bodyView;
    }

    public View getCallToActionView() {
        return this.callToActionView;
    }

    public View getIconView() {
        return this.iconView;
    }

    public View getAdvertiserView() {
        return this.advertiserView;
    }

    public View getAttributionTextView() {
        return this.attributionTextView;
    }

    public ImageView getColombiaView() {
        return this.colombiaView;
    }

    public View getDisplayUrlView() {
        return this.displayUrlView;
    }

    public void clear() {
        getVideoView().clear();
    }

    public synchronized void commit() {
        if (!this.commitFlag) {
            getVideoView().setNativeAd(this.nativeAd, this);
            if (getColombiaView() != null) {
                getColombiaView().setBackgroundResource(R.drawable.colombia);
                getColombiaView().setVisibility(0);
            }
            if (h.a(this.nativeAd.getCtaText()) && getCallToActionView() != null) {
                getCallToActionView().setVisibility(8);
            }
            applyClickListeners();
            displayAvailableAssets();
            this.commitFlag = true;
        }
    }

    private void displayAvailableAssets() {
        if (h.a(this.nativeAd.getCtaText()) && getCallToActionView() != null) {
            getCallToActionView().setVisibility(8);
        }
    }

    private void applyClickListeners() {
        bh bhVar = new bh(this);
        if (getCallToActionView() != null) {
            getCallToActionView().setVisibility(8);
        }
        if (getDisplayUrlView() != null) {
            getDisplayUrlView().setOnClickListener(bhVar);
        }
        if (getIconView() != null) {
            getIconView().setOnClickListener(bhVar);
        }
        if (getHeadlineView() != null) {
            getHeadlineView().setOnClickListener(bhVar);
        }
        if (getBodyView() != null) {
            getBodyView().setOnClickListener(bhVar);
        }
        if (getAdvertiserView() != null) {
            getAdvertiserView().setOnClickListener(bhVar);
        }
    }

    public void onWindowFocusChanged(boolean z) {
        String str = LOG_TAG;
        StringBuilder stringBuilder = new StringBuilder("VideoView window focus:");
        stringBuilder.append(z);
        Log.a(str, stringBuilder.toString());
        if (getVideoView() != null) {
            if (z) {
                getVideoView().refresh();
            } else {
                getVideoView().autoPause();
            }
            super.onWindowFocusChanged(z);
        }
    }
}
