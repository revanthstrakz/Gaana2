package com.comscore.streaming;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnSeekCompleteListener;

class i implements OnSeekCompleteListener {
    final /* synthetic */ StreamSenseMediaPlayer a;

    i(StreamSenseMediaPlayer streamSenseMediaPlayer) {
        this.a = streamSenseMediaPlayer;
    }

    public void onSeekComplete(MediaPlayer mediaPlayer) {
        if (this.a.n) {
            this.a.n = false;
            this.a.b(mediaPlayer);
        }
        if (this.a.t != null) {
            this.a.t.onSeekComplete(mediaPlayer);
        }
    }
}
