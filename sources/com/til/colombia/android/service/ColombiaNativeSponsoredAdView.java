package com.til.colombia.android.service;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.til.colombia.android.R;
import com.til.colombia.android.commons.COLOMBIA_PLAYER_STATE;
import com.til.colombia.android.commons.USER_ACTION;
import com.til.colombia.android.commons.a.g;
import com.til.colombia.android.internal.Log;
import com.til.colombia.android.internal.a.h;
import com.til.colombia.android.internal.i;
import com.til.colombia.android.vast.VastSponsoredAdConfig;
import java.io.IOException;

public class ColombiaNativeSponsoredAdView extends FrameLayout {
    private static String LOG_TAG = ColombiaNativeSponsoredAdView.class.getCanonicalName();
    public static final String PREF_SPONSORED = "com.til.colombia.android.sponsoredPref";
    private View advertiserView;
    private View attributionTextView;
    private ColombiaBannerView bannerView;
    private View bodyView;
    private View callToActionView;
    private long clickTime = 0;
    private ImageView colombiaView;
    private boolean commitFlag;
    private Context context;
    private View dismissView;
    private a handler;
    private View headlineView;
    private View iconView;
    private View imageView;
    private AudioManager mAudioManager;
    private com.til.colombia.android.internal.a.a mPhoneCallManager;
    private MediaPlayer mediaPlayer = null;
    private COLOMBIA_PLAYER_STATE mediaPlayerState = COLOMBIA_PLAYER_STATE.NULL;
    private Item nativeAd;
    private View offerPriceView;
    private View offerTextView;
    private boolean playAudio;
    private View priceView;
    private View rating;
    private View reviews;

    class a extends Handler {
        a() {
        }

        public final void handleMessage(Message message) {
            ColombiaNativeSponsoredAdView.this.finish(USER_ACTION.AUTO_CLOSED);
        }
    }

    public ColombiaNativeSponsoredAdView(Context context) {
        super(context);
        this.context = context;
    }

    public ColombiaNativeSponsoredAdView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.context = context;
    }

    public ColombiaNativeSponsoredAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.context = context;
    }

    @TargetApi(21)
    public ColombiaNativeSponsoredAdView(Context context, AttributeSet attributeSet, int i, int i2) {
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

    public View getDismissView() {
        return this.dismissView;
    }

    public void setDismissView(View view) {
        this.dismissView = view;
    }

    public ColombiaBannerView getBannerView() {
        return this.bannerView;
    }

    public void setBannerView(ColombiaBannerView colombiaBannerView) {
        this.bannerView = colombiaBannerView;
    }

    private void notifyInterstitialListener(String str, Bundle bundle) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(":");
        stringBuilder.append(this.nativeAd.getUID());
        Intent intent = new Intent(stringBuilder.toString());
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        LocalBroadcastManager.getInstance(this.context).sendBroadcastSync(intent);
    }

    /* Access modifiers changed, original: 0000 */
    public void finish(USER_ACTION user_action) {
        Bundle bundle = new Bundle();
        bundle.putString("USER_ACTION", user_action.toString());
        notifyInterstitialListener(ck.c, bundle);
    }

    private void configureAudioAndPhoneManager() {
        this.mPhoneCallManager = new bb(this);
        this.mPhoneCallManager.a(this.context);
        this.mAudioManager = (AudioManager) this.context.getSystemService("audio");
        try {
            this.mAudioManager.requestAudioFocus(null, 3, 1);
        } catch (Exception e) {
            Log.a(i.f, "", e);
        }
    }

    private COLOMBIA_PLAYER_STATE getMediaPlayerState() {
        return this.mediaPlayerState;
    }

    private void prepareAudioPlayer(String str) {
        try {
            this.mediaPlayer = new MediaPlayer();
            setMediaPlayerState(COLOMBIA_PLAYER_STATE.IDLE);
            configureMediaPlayer(this.mediaPlayer);
            this.mediaPlayer.setDataSource(this.context, g.a(str));
            setMediaPlayerState(COLOMBIA_PLAYER_STATE.INITIALIZED);
            this.mediaPlayer.prepareAsync();
            setMediaPlayerState(COLOMBIA_PLAYER_STATE.PREPARING);
        } catch (IOException e) {
            Log.a(i.f, "", e);
        }
    }

    private void autoClose(long j) {
        if (j < ((long) com.til.colombia.android.internal.a.p())) {
            j = (long) com.til.colombia.android.internal.a.p();
        }
        if (this.handler != null) {
            this.handler.sendEmptyMessageDelayed(0, j);
        }
    }

    private void configureMediaPlayer(MediaPlayer mediaPlayer) {
        try {
            mediaPlayer.setAudioStreamType(3);
            mediaPlayer.setOnPreparedListener(new bc(this, mediaPlayer));
            mediaPlayer.setOnCompletionListener(new bd(this));
            mediaPlayer.setOnErrorListener(new be(this));
        } catch (Exception e) {
            Log.a(i.f, "", e);
        }
    }

    private void setMediaPlayerState(COLOMBIA_PLAYER_STATE colombia_player_state) {
        this.mediaPlayerState = colombia_player_state;
    }

    private void releaseMediaPlayer() {
        try {
            if (!(this.mediaPlayer == null || getMediaPlayerState() == COLOMBIA_PLAYER_STATE.NULL)) {
                if ((getMediaPlayerState() == COLOMBIA_PLAYER_STATE.STARTED || getMediaPlayerState() == COLOMBIA_PLAYER_STATE.COMPLETED || getMediaPlayerState() == COLOMBIA_PLAYER_STATE.PREPARED) && this.mediaPlayer.isPlaying()) {
                    this.mediaPlayer.stop();
                    setMediaPlayerState(COLOMBIA_PLAYER_STATE.STOPPED);
                }
                this.mediaPlayer.reset();
                setMediaPlayerState(COLOMBIA_PLAYER_STATE.IDLE);
                this.mediaPlayer.release();
                setMediaPlayerState(COLOMBIA_PLAYER_STATE.END);
                this.mediaPlayer = null;
                setMediaPlayerState(COLOMBIA_PLAYER_STATE.NULL);
            }
        } catch (Exception e) {
            Log.a(i.f, "", e);
        }
    }

    public synchronized void commit() {
        if (!this.commitFlag) {
            if (this.context != null && (this.context instanceof Activity)) {
                ((Activity) this.context).setRequestedOrientation(1);
            }
            if (getColombiaView() != null) {
                getColombiaView().setBackgroundResource(R.drawable.colombia);
                getColombiaView().setVisibility(0);
            }
            if (getBannerView() != null) {
                getBannerView().setNativeAd(this.nativeAd);
            }
            if (h.a(this.nativeAd.getCtaText()) && getCallToActionView() != null) {
                getCallToActionView().setVisibility(8);
            }
            this.handler = new a();
            bf bfVar = new bf(this);
            ((NativeItem) this.nativeAd).registerReceiver();
            configureAudioAndPhoneManager();
            if (getCallToActionView() != null) {
                getCallToActionView().setOnClickListener(bfVar);
            }
            if (this.playAudio) {
                VastSponsoredAdConfig sponsoredAdConfig = ((NativeItem) this.nativeAd).getVastHelper().getSponsoredAdConfig();
                if (sponsoredAdConfig == null || sponsoredAdConfig.getPreAudioCompanion() == null) {
                    autoClose((long) com.til.colombia.android.internal.a.p());
                } else {
                    String audioSrc = sponsoredAdConfig.getPreAudioCompanion().getAudioSrc();
                    if (h.a(audioSrc)) {
                        autoClose((long) com.til.colombia.android.internal.a.p());
                    } else {
                        prepareAudioPlayer(audioSrc);
                    }
                }
            }
            if (getDismissView() != null) {
                getDismissView().setOnTouchListener(new bg(this));
            }
            bi.a().a(((NativeItem) this.nativeAd).getItemResponse(), this.nativeAd, this);
            this.commitFlag = true;
        }
    }

    public void setPlayAudio(boolean z) {
        this.playAudio = z;
    }

    public void onWindowFocusChanged(boolean z) {
        if (z) {
            if (this.mediaPlayer != null && !this.mediaPlayer.isPlaying() && getMediaPlayerState() == COLOMBIA_PLAYER_STATE.PAUSED) {
                this.mediaPlayer.start();
                setMediaPlayerState(COLOMBIA_PLAYER_STATE.STARTED);
            } else if (!(this.mediaPlayer == null || this.mediaPlayer.isPlaying() || getMediaPlayerState() != COLOMBIA_PLAYER_STATE.PREPARED)) {
                this.mediaPlayer.start();
                setMediaPlayerState(COLOMBIA_PLAYER_STATE.STARTED);
            }
        } else if (this.mediaPlayer != null && this.mediaPlayer.isPlaying() && getMediaPlayerState() == COLOMBIA_PLAYER_STATE.STARTED) {
            this.mediaPlayer.pause();
            setMediaPlayerState(COLOMBIA_PLAYER_STATE.PAUSED);
        }
        super.onWindowFocusChanged(z);
    }

    public void onDetachedFromWindow() {
        destroy();
        super.onDetachedFromWindow();
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.playAudio) {
            autoClose((long) com.til.colombia.android.internal.a.p());
        }
    }

    public synchronized void destroy() {
        if (this.handler != null) {
            this.handler.removeMessages(0);
            this.handler = null;
        }
        releaseMediaPlayer();
        if (this.mPhoneCallManager != null) {
            this.mPhoneCallManager.b(this.context);
        }
        try {
            if (this.mAudioManager != null) {
                this.mAudioManager.abandonAudioFocus(null);
            }
            this.mAudioManager = null;
        } catch (Exception e) {
            try {
                Log.a(i.f, "", e);
            } finally {
                this.mAudioManager = null;
            }
        }
    }
}
