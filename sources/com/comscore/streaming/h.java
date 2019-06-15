package com.comscore.streaming;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnInfoListener;

class h implements OnInfoListener {
    final /* synthetic */ StreamSenseMediaPlayer a;

    h(StreamSenseMediaPlayer streamSenseMediaPlayer) {
        this.a = streamSenseMediaPlayer;
    }

    public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
        if (i == 701 && !this.a.n && !this.a.m) {
            this.a.a();
        } else if (i == 702 && !this.a.n && this.a.m) {
            this.a.b();
        }
        return this.a.r != null ? this.a.r.onInfo(mediaPlayer, i, i2) : true;
    }
}
