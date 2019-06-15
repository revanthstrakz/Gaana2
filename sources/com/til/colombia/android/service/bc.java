package com.til.colombia.android.service;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import com.til.colombia.android.commons.COLOMBIA_PLAYER_STATE;
import com.til.colombia.android.internal.Log;

final class bc implements OnPreparedListener {
    final /* synthetic */ MediaPlayer a;
    final /* synthetic */ ColombiaNativeSponsoredAdView b;

    bc(ColombiaNativeSponsoredAdView colombiaNativeSponsoredAdView, MediaPlayer mediaPlayer) {
        this.b = colombiaNativeSponsoredAdView;
        this.a = mediaPlayer;
    }

    public final void onPrepared(MediaPlayer mediaPlayer) {
        Log.a(ColombiaNativeSponsoredAdView.LOG_TAG, "on prepared");
        this.b.setMediaPlayerState(COLOMBIA_PLAYER_STATE.PREPARED);
        this.a.setVolume(1.0f, 1.0f);
        this.a.start();
        this.b.setMediaPlayerState(COLOMBIA_PLAYER_STATE.STARTED);
        this.b.handler.removeMessages(0);
    }
}
