package com.til.colombia.android.service;

import android.view.View;
import android.view.View.OnClickListener;
import com.google.android.exoplayer2.DefaultRenderersFactory;

final class bf implements OnClickListener {
    final /* synthetic */ ColombiaNativeSponsoredAdView a;

    bf(ColombiaNativeSponsoredAdView colombiaNativeSponsoredAdView) {
        this.a = colombiaNativeSponsoredAdView;
    }

    public final void onClick(View view) {
        if (System.currentTimeMillis() >= this.a.clickTime + DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS) {
            if (this.a.handler != null) {
                this.a.handler.removeMessages(0);
            }
            if (this.a.mediaPlayer != null && this.a.playAudio) {
                this.a.releaseMediaPlayer();
            }
            this.a.clickTime = System.currentTimeMillis();
            bi.a();
            bi.a(this.a.nativeAd, false);
        }
    }
}
