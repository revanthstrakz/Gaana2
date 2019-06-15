package com.google.android.youtube.player;

import android.app.Activity;
import android.os.Bundle;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;

public class YouTubeBaseActivity extends Activity {
    private a a;
    private YouTubePlayerView b;
    private int c;
    private Bundle d;

    private final class a implements b {
        private a() {
        }

        /* synthetic */ a(YouTubeBaseActivity youTubeBaseActivity, byte b) {
            this();
        }

        public final void a(YouTubePlayerView youTubePlayerView) {
            if (!(YouTubeBaseActivity.this.b == null || YouTubeBaseActivity.this.b == youTubePlayerView)) {
                YouTubeBaseActivity.this.b.c(true);
            }
            YouTubeBaseActivity.this.b = youTubePlayerView;
            if (YouTubeBaseActivity.this.c > 0) {
                youTubePlayerView.a();
            }
            if (YouTubeBaseActivity.this.c >= 2) {
                youTubePlayerView.b();
            }
        }

        public final void a(YouTubePlayerView youTubePlayerView, String str, OnInitializedListener onInitializedListener) {
            youTubePlayerView.a(YouTubeBaseActivity.this, youTubePlayerView, str, onInitializedListener, YouTubeBaseActivity.this.d);
            YouTubeBaseActivity.this.d = null;
        }
    }

    /* Access modifiers changed, original: final */
    public final b a() {
        return this.a;
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.a = new a(this, (byte) 0);
        this.d = bundle != null ? bundle.getBundle("YouTubeBaseActivity.KEY_PLAYER_VIEW_STATE") : null;
    }

    /* Access modifiers changed, original: protected */
    public void onDestroy() {
        if (this.b != null) {
            this.b.b(isFinishing());
        }
        super.onDestroy();
    }

    /* Access modifiers changed, original: protected */
    public void onPause() {
        this.c = 1;
        if (this.b != null) {
            this.b.c();
        }
        super.onPause();
    }

    /* Access modifiers changed, original: protected */
    public void onResume() {
        super.onResume();
        this.c = 2;
        if (this.b != null) {
            this.b.b();
        }
    }

    /* Access modifiers changed, original: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBundle("YouTubeBaseActivity.KEY_PLAYER_VIEW_STATE", this.b != null ? this.b.e() : this.d);
    }

    /* Access modifiers changed, original: protected */
    public void onStart() {
        super.onStart();
        this.c = 1;
        if (this.b != null) {
            this.b.a();
        }
    }

    /* Access modifiers changed, original: protected */
    public void onStop() {
        this.c = 0;
        if (this.b != null) {
            this.b.d();
        }
        super.onStop();
    }
}
