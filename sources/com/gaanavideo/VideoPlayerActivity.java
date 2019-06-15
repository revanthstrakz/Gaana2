package com.gaanavideo;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.MediaController;
import android.widget.VideoView;
import com.gaana.R;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;

public class VideoPlayerActivity extends Activity {
    private VideoView a;
    private MediaController b;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.gaana_video_player_view);
        this.a = (VideoView) findViewById(R.id.full_screen_video_player);
        setRequestedOrientation(0);
        a();
    }

    public void onCreate(Bundle bundle, PersistableBundle persistableBundle) {
        super.onCreate(bundle);
        setContentView(R.layout.gaana_video_player_view);
        this.a = (VideoView) findViewById(R.id.full_screen_video_player);
        setRequestedOrientation(0);
        a();
    }

    private void a() {
        if (this.b == null) {
            this.b = new MediaController(this);
        }
        try {
            this.a.setMediaController(this.b);
            this.a.setVideoURI(Uri.parse("http://streams.gaana.com/video/gaana.mp4?streamauth=1486300261_9c409c1239c61b2951f325b840b3e69d"));
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        this.a.requestFocus();
        this.a.setOnPreparedListener(new OnPreparedListener() {
            public void onPrepared(MediaPlayer mediaPlayer) {
                VideoPlayerActivity.this.a.start();
            }
        });
    }
}
