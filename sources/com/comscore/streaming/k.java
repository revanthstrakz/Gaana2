package com.comscore.streaming;

import java.util.HashMap;
import java.util.TimerTask;

class k extends TimerTask {
    final /* synthetic */ long a;
    final /* synthetic */ HashMap b;
    final /* synthetic */ StreamSenseMediaPlayer c;

    k(StreamSenseMediaPlayer streamSenseMediaPlayer, long j, HashMap hashMap) {
        this.c = streamSenseMediaPlayer;
        this.a = j;
        this.b = hashMap;
    }

    public void run() {
        if (this.c.m() - this.a >= 500) {
            this.c.d();
            this.c.f(this.b);
            this.c.j();
            return;
        }
        this.c.d();
        this.c.a(this.b);
    }
}
