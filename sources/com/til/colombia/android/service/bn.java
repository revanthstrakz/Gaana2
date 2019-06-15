package com.til.colombia.android.service;

import com.til.colombia.android.commons.COLOMBIA_PLAYER_STATE;
import com.til.colombia.android.internal.a.a;

final class bn extends a {
    final /* synthetic */ InterstitialActivity b;

    bn(InterstitialActivity interstitialActivity) {
        this.b = interstitialActivity;
    }

    public final void a() {
        if (this.b.n != null) {
            this.b.n.pause();
            this.b.a = this.b.n.getCurrentPosition();
        }
        if (this.b.u != null && this.b.u.isPlaying() && this.b.v == COLOMBIA_PLAYER_STATE.STARTED) {
            this.b.u.pause();
            this.b.v = COLOMBIA_PLAYER_STATE.PAUSED;
        }
    }

    public final void b() {
        if (this.b.n != null) {
            this.b.n.resume();
        }
        if (this.b.u != null && !this.b.u.isPlaying() && this.b.v == COLOMBIA_PLAYER_STATE.PAUSED) {
            this.b.u.start();
            this.b.v = COLOMBIA_PLAYER_STATE.STARTED;
        }
    }
}
