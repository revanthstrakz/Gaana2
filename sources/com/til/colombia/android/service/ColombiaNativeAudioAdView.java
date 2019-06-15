package com.til.colombia.android.service;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.til.colombia.android.commons.USER_ACTION;
import com.til.colombia.android.internal.a.h;
import com.til.colombia.android.network.l;
import com.til.colombia.android.network.n;
import com.til.colombia.android.service.ColombiaAdManager.ITEM_TYPE;
import com.til.colombia.android.vast.VastCompanionAdConfig;
import com.til.colombia.android.vast.VastCompanionResource.CreativeType;
import com.til.colombia.android.vast.VastCompanionResource.Type;
import com.til.colombia.android.vast.VastTrackingEvent;
import com.til.colombia.android.vast.i;
import java.util.ArrayList;
import java.util.List;

public class ColombiaNativeAudioAdView extends FrameLayout {
    private static String LOG_TAG = ColombiaNativeAudioAdView.class.getCanonicalName();
    private final float CLICK_DISPLACEMENT = 8.0f;
    private View advertiserView;
    private View attributionTextView;
    ah audioPlayer;
    private View bodyView;
    private View callToActionView;
    private OnClickListener clickListener = new as(this);
    private ImageView colombiaView;
    private boolean commitFlag;
    private Context context;
    private View dismissView;
    float downX;
    float downY;
    private a handler;
    private View headlineView;
    private View iconView;
    private ViewGroup imageView;
    private Item nativeAd;
    private VastCompanionAdConfig vastCompanionAdConfig;
    View vastResourceView;

    class a extends Handler {
        a() {
        }

        public final void handleMessage(Message message) {
            ColombiaNativeAudioAdView.this.finish(USER_ACTION.AUTO_CLOSED);
        }
    }

    private class b implements OnTouchListener {
        private b() {
        }

        /* synthetic */ b(ColombiaNativeAudioAdView colombiaNativeAudioAdView, byte b) {
            this();
        }

        public final boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case 0:
                    return false;
                case 1:
                    if (ColombiaNativeAudioAdView.this.vastCompanionAdConfig.getClickTrackers() != null) {
                        for (VastTrackingEvent url : ColombiaNativeAudioAdView.this.vastCompanionAdConfig.getClickTrackers()) {
                            l.a(url.getUrl(), 5, null);
                        }
                    }
                    bi.a();
                    bi.a(ColombiaNativeAudioAdView.this.nativeAd);
                    ((NativeItem) ColombiaNativeAudioAdView.this.nativeAd).getAdListener().onMediaItemClicked(ColombiaNativeAudioAdView.this.nativeAd);
                    if (ColombiaNativeAudioAdView.this.handler != null) {
                        ColombiaNativeAudioAdView.this.handler.sendEmptyMessageDelayed(0, 500);
                        break;
                    }
                    break;
                case 2:
                    return true;
            }
            return false;
        }
    }

    public ColombiaNativeAudioAdView(Context context) {
        super(context);
        this.context = context;
    }

    public ColombiaNativeAudioAdView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.context = context;
    }

    public ColombiaNativeAudioAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.context = context;
    }

    @TargetApi(21)
    public ColombiaNativeAudioAdView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.context = context;
    }

    public void setItem(Item item) {
        if (item != null) {
            this.nativeAd = item;
        }
    }

    public View getHeadlineView() {
        return this.headlineView;
    }

    public void setHeadlineView(View view) {
        this.headlineView = view;
    }

    public ViewGroup getImageViewGroup() {
        return this.imageView;
    }

    public void setImageViewGroup(ViewGroup viewGroup) {
        this.imageView = viewGroup;
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

    public View getDismissView() {
        return this.dismissView;
    }

    public void setDismissView(View view) {
        if (view != null) {
            this.dismissView = view;
            this.dismissView.setOnClickListener(new ar(this));
            this.dismissView.setVisibility(8);
        }
    }

    public void commitResource() {
        if (this.context != null && (this.context instanceof Activity)) {
            ((Activity) this.context).setRequestedOrientation(1);
        }
        if (h.a(this.nativeAd.getCtaText()) && getCallToActionView() != null) {
            getCallToActionView().setVisibility(8);
        }
        this.vastCompanionAdConfig = ((NativeItem) this.nativeAd).getVastHelper().getBestCompanionAdConfig(this.imageView.getLayoutParams().width, this.imageView.getLayoutParams().height);
        if (this.vastCompanionAdConfig != null && this.vastCompanionAdConfig.getVastResource() != null) {
            if (this.vastCompanionAdConfig.getVastResource().getType() == Type.STATIC_RESOURCE && this.vastCompanionAdConfig.getVastResource().getCreativeType() == CreativeType.IMAGE) {
                this.vastResourceView = new ImageView(this.context);
            } else {
                this.vastResourceView = new i(this.context);
            }
            this.imageView.addView(this.vastResourceView, this.imageView.getLayoutParams());
            this.vastCompanionAdConfig.getVastResource().initializeVastResourceView(this.vastResourceView);
            initVastDisplayView();
            List<VastTrackingEvent> creativeViewTrackers = this.vastCompanionAdConfig.getCreativeViewTrackers();
            if (creativeViewTrackers != null) {
                ArrayList arrayList = new ArrayList(creativeViewTrackers.size());
                for (VastTrackingEvent url : creativeViewTrackers) {
                    arrayList.add(url.getUrl());
                }
                n.a(arrayList, 5, "companion creative view tracked.");
            }
            if (this.vastResourceView == null || !(this.vastResourceView instanceof i)) {
                this.imageView.setOnClickListener(this.clickListener);
            }
        } else if (this.nativeAd.getItemType() == ITEM_TYPE.AUDIO) {
            this.vastResourceView = new ImageView(this.context);
            this.imageView.addView(this.vastResourceView, this.imageView.getLayoutParams());
            setItemImage();
            if (((NativeItem) this.nativeAd).getVastHelper().isClickable()) {
                this.imageView.setOnClickListener(this.clickListener);
            }
        }
        if (getCallToActionView() != null) {
            getCallToActionView().setOnClickListener(this.clickListener);
        }
        if (this.audioPlayer != null && this.audioPlayer.b) {
            this.dismissView.setVisibility(0);
        }
    }

    private void setItemImage() {
        ((ImageView) this.vastResourceView).setAdjustViewBounds(true);
        com.til.colombia.android.commons.a.a.a(this.nativeAd.getImageUrl(), new at(this));
    }

    public synchronized void commit() {
        if (!this.commitFlag) {
            this.handler = new a();
            this.audioPlayer = new ah(this.context, this.nativeAd, ((NativeItem) this.nativeAd).getAdListener());
            this.audioPlayer.a = this.dismissView;
            ((NativeItem) this.nativeAd).getItemResponse().recordItemResponseImpression(null);
            this.commitFlag = true;
        }
    }

    private void click() {
        if (this.vastCompanionAdConfig != null) {
            if (this.vastCompanionAdConfig.getVastResource().getType() == Type.STATIC_RESOURCE && this.vastCompanionAdConfig.getVastResource().getCreativeType() == CreativeType.IMAGE) {
                this.vastCompanionAdConfig.handleClick(com.til.colombia.android.internal.a.a(), this.vastCompanionAdConfig.getClickThroughUrl());
            }
            if (this.vastCompanionAdConfig.getClickTrackers() != null) {
                for (VastTrackingEvent url : this.vastCompanionAdConfig.getClickTrackers()) {
                    l.a(url.getUrl(), 5, null);
                }
            }
            bi.a();
            bi.a(this.nativeAd);
        } else {
            bi.a();
            bi.a(this.nativeAd, false);
        }
        ((NativeItem) this.nativeAd).getAdListener().onMediaItemClicked(this.nativeAd);
        if (this.handler != null) {
            this.handler.sendEmptyMessageDelayed(0, 500);
        }
    }

    public synchronized void initVastDisplayView() {
        if (this.vastResourceView == null || !(this.vastResourceView instanceof i)) {
            this.vastResourceView.setOnTouchListener(new ax(this));
        } else {
            ((i) this.vastResourceView).setOnTouchListener(new b(this, (byte) 0));
        }
    }

    private void clearWebView() {
        if (this.vastResourceView != null && (this.vastResourceView instanceof i)) {
            ((i) this.vastResourceView).clearHistory();
            ((i) this.vastResourceView).clearCache(true);
            ((i) this.vastResourceView).destroy();
        }
        this.vastResourceView = null;
    }

    public void onDetachedFromWindow() {
        if (this.audioPlayer != null) {
            this.audioPlayer.d();
        }
        super.onDetachedFromWindow();
    }

    /* Access modifiers changed, original: 0000 */
    public void finish(USER_ACTION user_action) {
        clearWebView();
        if (this.audioPlayer != null) {
            this.audioPlayer.d();
        }
        ((NativeItem) this.nativeAd).getAdListener().onMediaItemClosed(this.nativeAd, user_action);
    }

    public synchronized void destroy() {
        if (this.handler != null) {
            this.handler.removeMessages(0);
            this.handler = null;
        }
        clearWebView();
        if (this.audioPlayer != null) {
            this.audioPlayer.b();
            this.audioPlayer.d();
        }
    }
}
