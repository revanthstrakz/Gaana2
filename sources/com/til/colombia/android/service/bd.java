package com.til.colombia.android.service;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import com.til.colombia.android.commons.COLOMBIA_PLAYER_STATE;
import com.til.colombia.android.internal.Log;

final class bd implements OnCompletionListener {
    final /* synthetic */ ColombiaNativeSponsoredAdView a;

    bd(ColombiaNativeSponsoredAdView colombiaNativeSponsoredAdView) {
        this.a = colombiaNativeSponsoredAdView;
    }

    public final void onCompletion(MediaPlayer mediaPlayer) {
        Log.a(ColombiaNativeSponsoredAdView.LOG_TAG, "on complete");
        this.a.setMediaPlayerState(COLOMBIA_PLAYER_STATE.COMPLETED);
        this.a.releaseMediaPlayer();
        this.a.handler.sendEmptyMessageDelayed(0, 3000);
    }
}
