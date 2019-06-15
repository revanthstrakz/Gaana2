package com.comscore.streaming;

import java.util.TimerTask;

class l extends TimerTask {
    final /* synthetic */ long a;
    final /* synthetic */ StreamSenseMediaPlayer b;

    l(StreamSenseMediaPlayer streamSenseMediaPlayer, long j) {
        this.b = streamSenseMediaPlayer;
        this.a = j;
    }

    public void run() {
        if (this.b.y != null) {
            Object obj = this.a == this.b.m() ? 1 : null;
            if (this.b.n() && !this.b.isPlayerPausedForSeeking() && obj != null && !this.b.isPlayerPausedForBuffering()) {
                this.b.a();
            } else if (this.b.n() && !this.b.isPlayerPausedForSeeking() && obj == null && this.b.isPlayerPausedForBuffering()) {
                this.b.b();
            }
            this.b.i();
            this.b.j();
        }
    }
}
