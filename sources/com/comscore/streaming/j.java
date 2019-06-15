package com.comscore.streaming;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;

class j implements OnPreparedListener {
    final /* synthetic */ StreamSenseMediaPlayer a;

    j(StreamSenseMediaPlayer streamSenseMediaPlayer) {
        this.a = streamSenseMediaPlayer;
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        this.a.k = String.valueOf(this.a.getCurrentPosition());
        if (this.a.v != null) {
            this.a.v.onPrepared(mediaPlayer);
        }
    }
}
