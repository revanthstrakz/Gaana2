package com.til.colombia.android.service;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import com.til.colombia.android.commons.COLOMBIA_PLAYER_STATE;

final class bt implements OnErrorListener {
    final /* synthetic */ InterstitialActivity a;

    bt(InterstitialActivity interstitialActivity) {
        this.a = interstitialActivity;
    }

    public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        this.a.v = COLOMBIA_PLAYER_STATE.ERROR;
        this.a.d();
        this.a.a(1000);
        return false;
    }
}
