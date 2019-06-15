package com.comscore.streaming;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

class o implements OnCompletionListener {
    final /* synthetic */ StreamSenseVideoView a;

    o(StreamSenseVideoView streamSenseVideoView) {
        this.a = streamSenseVideoView;
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        this.a.a(mediaPlayer);
        if (this.a.n != null) {
            this.a.n.onCompletion(mediaPlayer);
        }
    }
}
