package com.til.colombia.android.service;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import com.til.colombia.android.commons.COLOMBIA_PLAYER_STATE;

final class bs implements OnCompletionListener {
    final /* synthetic */ InterstitialActivity a;

    bs(InterstitialActivity interstitialActivity) {
        this.a = interstitialActivity;
    }

    public final void onCompletion(MediaPlayer mediaPlayer) {
        this.a.v = COLOMBIA_PLAYER_STATE.COMPLETED;
        this.a.d();
        this.a.w.sendEmptyMessageDelayed(0, 1000);
    }
}
