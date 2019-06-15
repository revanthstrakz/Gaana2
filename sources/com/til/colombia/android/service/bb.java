package com.til.colombia.android.service;

import com.til.colombia.android.commons.COLOMBIA_PLAYER_STATE;
import com.til.colombia.android.internal.a.a;

final class bb extends a {
    final /* synthetic */ ColombiaNativeSponsoredAdView b;

    bb(ColombiaNativeSponsoredAdView colombiaNativeSponsoredAdView) {
        this.b = colombiaNativeSponsoredAdView;
    }

    public final void a() {
        if (this.b.mediaPlayer != null) {
            if (this.b.mediaPlayer.isPlaying() && this.b.getMediaPlayerState() == COLOMBIA_PLAYER_STATE.STARTED) {
                this.b.mediaPlayer.pause();
                this.b.setMediaPlayerState(COLOMBIA_PLAYER_STATE.PAUSED);
            }
            if (!this.b.mediaPlayer.isPlaying() && this.b.getMediaPlayerState() == COLOMBIA_PLAYER_STATE.PREPARED) {
                this.b.mediaPlayer.pause();
                this.b.setMediaPlayerState(COLOMBIA_PLAYER_STATE.PAUSED);
            }
        }
    }

    public final void b() {
        if (this.b.mediaPlayer != null && !this.b.mediaPlayer.isPlaying()) {
            if (this.b.getMediaPlayerState() == COLOMBIA_PLAYER_STATE.PAUSED || this.b.getMediaPlayerState() == COLOMBIA_PLAYER_STATE.PREPARED) {
                this.b.mediaPlayer.start();
                this.b.setMediaPlayerState(COLOMBIA_PLAYER_STATE.STARTED);
            }
        }
    }
}
