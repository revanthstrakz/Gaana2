package com.til.colombia.android.service;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import com.til.colombia.android.commons.COLOMBIA_PLAYER_STATE;

final class br implements OnPreparedListener {
    final /* synthetic */ MediaPlayer a;
    final /* synthetic */ InterstitialActivity b;

    br(InterstitialActivity interstitialActivity, MediaPlayer mediaPlayer) {
        this.b = interstitialActivity;
        this.a = mediaPlayer;
    }

    public final void onPrepared(MediaPlayer mediaPlayer) {
        this.b.v = COLOMBIA_PLAYER_STATE.PREPARED;
        this.a.setVolume(1.0f, 1.0f);
        this.a.start();
        this.b.v = COLOMBIA_PLAYER_STATE.STARTED;
    }
}
