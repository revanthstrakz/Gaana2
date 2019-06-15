package com.youtube;

import android.content.Intent;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.FrameLayout.LayoutParams;
import android.widget.Toast;
import com.gaana.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.ErrorReason;
import com.google.android.youtube.player.YouTubePlayer.OnFullscreenListener;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayer.PlayerStateChangeListener;
import com.google.android.youtube.player.YouTubePlayer.PlayerStyle;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YouTubePlayerActivity extends YouTubeBaseActivity implements OnFullscreenListener, OnInitializedListener, PlayerStateChangeListener {
    private String a;
    private String b;
    private PlayerStyle c;
    private boolean d;
    private boolean e;
    private YouTubePlayerView f;
    private YouTubePlayer g;
    private Orientation h;
    private AudioManager i;
    private String j;

    public enum Orientation {
        AUTO_START_WITH_PORTRAIT,
        AUTO_START_WITH_LANDSCAPE,
        ONLY_LANDSCAPE,
        ONLY_PORTRAIT
    }

    public void onAdStarted() {
    }

    public void onLoading() {
    }

    public void onVideoEnded() {
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        c();
        this.f = new YouTubePlayerView(this);
        this.f.initialize(this.a, this);
        addContentView(this.f, new LayoutParams(-1, -1));
        this.f.setBackgroundResource(17170444);
        b();
    }

    public void b() {
        if (VERSION.SDK_INT < 16) {
            getWindow().setFlags(1024, 1024);
        } else {
            getWindow().getDecorView().setSystemUiVisibility(4);
        }
    }

    private void c() {
        this.a = getResources().getString(R.string.youtube_api_key);
        this.b = getIntent().getStringExtra("video_id");
        this.c = (PlayerStyle) getIntent().getSerializableExtra("player_style");
        if (this.c == null) {
            this.c = PlayerStyle.DEFAULT;
        }
        this.h = (Orientation) getIntent().getSerializableExtra("orientation");
        if (this.h == null) {
            this.h = Orientation.AUTO_START_WITH_LANDSCAPE;
        }
        this.d = getIntent().getBooleanExtra("show_audio_ui", true);
        this.e = getIntent().getBooleanExtra("handle_error", true);
        this.j = getIntent().getStringExtra("browser_url");
        if (TextUtils.isEmpty(this.b) && !TextUtils.isEmpty(this.j)) {
            this.b = a(this.j);
        }
        if (this.b == null) {
            finish();
        }
    }

    public static String a(String str) {
        Matcher matcher = Pattern.compile("(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%‌​2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*").matcher(str);
        return matcher.find() ? matcher.group() : null;
    }

    public void onInitializationSuccess(Provider provider, YouTubePlayer youTubePlayer, boolean z) {
        this.g = youTubePlayer;
        youTubePlayer.setOnFullscreenListener(this);
        youTubePlayer.setPlayerStateChangeListener(this);
        switch (this.h) {
            case AUTO_START_WITH_PORTRAIT:
                youTubePlayer.setFullscreenControlFlags(15);
                break;
            case AUTO_START_WITH_LANDSCAPE:
                youTubePlayer.setFullscreenControlFlags(15);
                youTubePlayer.setFullscreen(true);
                break;
            case ONLY_LANDSCAPE:
                setRequestedOrientation(0);
                youTubePlayer.setFullscreenControlFlags(10);
                youTubePlayer.setFullscreen(true);
                break;
            case ONLY_PORTRAIT:
                setRequestedOrientation(1);
                youTubePlayer.setFullscreenControlFlags(10);
                youTubePlayer.setFullscreen(true);
                break;
        }
        switch (this.c) {
            case CHROMELESS:
                youTubePlayer.setPlayerStyle(PlayerStyle.CHROMELESS);
                break;
            case MINIMAL:
                youTubePlayer.setPlayerStyle(PlayerStyle.MINIMAL);
                break;
            default:
                youTubePlayer.setPlayerStyle(PlayerStyle.DEFAULT);
                break;
        }
        if (!z) {
            youTubePlayer.cueVideo(this.b);
        }
    }

    public void onInitializationFailure(Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(this, 1).show();
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getString(R.string.error_youtube_vid));
        stringBuilder.append(" (%1$s)");
        Toast.makeText(this, String.format(stringBuilder.toString(), new Object[]{youTubeInitializationResult.toString()}), 1).show();
    }

    /* Access modifiers changed, original: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1) {
            this.f.initialize(this.a, this);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        switch (this.h) {
            case AUTO_START_WITH_PORTRAIT:
            case AUTO_START_WITH_LANDSCAPE:
                if (configuration.orientation == 2 && this.g != null) {
                    this.g.setFullscreen(true);
                    return;
                } else if (configuration.orientation == 1 && this.g != null) {
                    this.g.setFullscreen(false);
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }

    public void onFullscreen(boolean z) {
        switch (this.h) {
            case AUTO_START_WITH_PORTRAIT:
            case AUTO_START_WITH_LANDSCAPE:
                if (z) {
                    setRequestedOrientation(6);
                    return;
                } else {
                    setRequestedOrientation(7);
                    return;
                }
            default:
                return;
        }
    }

    public void onError(ErrorReason errorReason) {
        if (this.e && ErrorReason.NOT_PLAYABLE.equals(errorReason)) {
            b(this.b);
        }
    }

    public void b(@NonNull String str) {
        Uri parse = Uri.parse(c(str));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("vnd.youtube:");
        stringBuilder.append(str);
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(stringBuilder.toString()));
        if (getPackageManager().queryIntentActivities(intent, 65536).isEmpty()) {
            intent = new Intent("android.intent.action.VIEW", parse);
        }
        startActivity(intent);
    }

    public void onLoaded(String str) {
        if (!TextUtils.isEmpty(this.b) && this.b.equals(str) && !isFinishing() && this.g != null) {
            this.g.play();
        }
    }

    public void onVideoStarted() {
        b();
    }

    public boolean onKeyDown(int i, @NonNull KeyEvent keyEvent) {
        if (i == 24) {
            a(true, this.d);
            b();
            return true;
        } else if (i != 25) {
            return super.onKeyDown(i, keyEvent);
        } else {
            a(false, this.d);
            b();
            return true;
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public String c(@NonNull String str) {
        if (!TextUtils.isEmpty(this.j)) {
            return this.j;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("http://youtu.be/");
        stringBuilder.append(str);
        return stringBuilder.toString();
    }

    public void a(boolean z, boolean z2) {
        if (this.i == null) {
            this.i = (AudioManager) getSystemService("audio");
        }
        this.i.adjustStreamVolume(3, z ? 1 : -1, z2 | 8);
    }
}
