package com.til.colombia.android.service;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.util.Log;
import com.til.colombia.android.commons.COLOMBIA_PLAYER_STATE;

final class be implements OnErrorListener {
    final /* synthetic */ ColombiaNativeSponsoredAdView a;

    be(ColombiaNativeSponsoredAdView colombiaNativeSponsoredAdView) {
        this.a = colombiaNativeSponsoredAdView;
    }

    public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        String access$300 = ColombiaNativeSponsoredAdView.LOG_TAG;
        StringBuilder stringBuilder = new StringBuilder("on error : what");
        stringBuilder.append(i);
        stringBuilder.append(", extra:");
        stringBuilder.append(i2);
        Log.e(access$300, stringBuilder.toString());
        this.a.setMediaPlayerState(COLOMBIA_PLAYER_STATE.ERROR);
        this.a.releaseMediaPlayer();
        return false;
    }
}
