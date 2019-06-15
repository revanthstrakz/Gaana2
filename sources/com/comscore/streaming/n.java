package com.comscore.streaming;

import java.util.TimerTask;

class n extends TimerTask {
    final /* synthetic */ long a;
    final /* synthetic */ StreamSenseVideoView b;

    n(StreamSenseVideoView streamSenseVideoView, long j) {
        this.b = streamSenseVideoView;
        this.a = j;
    }

    public void run() {
        if (this.b.m != null) {
            Object obj = this.a == this.b.getCurrentPlayerSafePosition() ? 1 : null;
            if (!this.b.a() || obj == null || this.b.b()) {
                this.b.f();
                this.b.g();
            } else {
                this.b.f();
                this.b.c();
            }
        }
    }
}
