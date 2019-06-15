package com.comscore.streaming;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

class g implements OnCompletionListener {
    final /* synthetic */ StreamSenseMediaPlayer a;

    g(StreamSenseMediaPlayer streamSenseMediaPlayer) {
        this.a = streamSenseMediaPlayer;
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        this.a.a(mediaPlayer);
        if (this.a.p != null) {
            this.a.p.onCompletion(mediaPlayer);
        }
    }
}
