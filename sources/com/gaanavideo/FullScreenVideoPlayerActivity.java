package com.gaanavideo;

import android.app.Activity;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.view.GaanaYourYearView.GAANA_VIDEO_SOURCE;
import com.gaanavideo.VideoControllerView.a;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.managers.u;
import com.services.d;
import java.io.IOException;

public class FullScreenVideoPlayerActivity extends Activity implements OnPreparedListener, Callback, a {
    SurfaceView a;
    private String b = "FullscreenVideoActivity";
    private MediaPlayer c;
    private VideoControllerView d;
    private ProgressBar e;
    private String f;
    private String g;
    private boolean h = false;
    private int i = -1;
    private boolean j = false;
    private boolean k = false;
    private int l;
    private boolean m;
    private int n = GAANA_VIDEO_SOURCE.HOME_PAGE.ordinal();
    private final OnAudioFocusChangeListener o = new OnAudioFocusChangeListener() {
        public void onAudioFocusChange(int i) {
            if (i != 1) {
                switch (i) {
                    case -2:
                        if (FullScreenVideoPlayerActivity.this.c.isPlaying()) {
                            FullScreenVideoPlayerActivity.this.b();
                            FullScreenVideoPlayerActivity.this.m = true;
                            return;
                        }
                        return;
                    case -1:
                        if (FullScreenVideoPlayerActivity.this.c.isPlaying()) {
                            FullScreenVideoPlayerActivity.this.b();
                            FullScreenVideoPlayerActivity.this.m = true;
                            return;
                        }
                        return;
                    default:
                        return;
                }
            } else if (FullScreenVideoPlayerActivity.this.m) {
                FullScreenVideoPlayerActivity.this.m = false;
                FullScreenVideoPlayerActivity.this.a();
            }
        }
    };

    public boolean f() {
        return true;
    }

    public boolean g() {
        return true;
    }

    public boolean h() {
        return true;
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_fullscreen_video);
        getWindow().addFlags(128);
        this.a = (SurfaceView) findViewById(R.id.videoSurface);
        a(true);
        this.e = (ProgressBar) findViewById(R.id.progressbar);
        this.e.setVisibility(0);
        this.a.getHolder().addCallback(this);
        this.f = getIntent().getStringExtra("video_url");
        this.n = getIntent().getIntExtra("from_page", 0);
        this.g = getIntent().getStringExtra("share_url");
        this.d = new VideoControllerView(this);
        k();
    }

    private void k() {
        this.c = new MediaPlayer();
        this.d = new VideoControllerView(this);
        try {
            this.c.setAudioStreamType(3);
            this.c.setDataSource(this, Uri.parse(this.f));
            this.c.setOnPreparedListener(this);
        } catch (IOException | IllegalArgumentException | IllegalStateException | SecurityException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        this.h = false;
        this.k = true;
        this.m = false;
        this.d.setMediaPlayerPreparing(this.h);
        this.d.setMediaPlayer(this);
        this.d.setShareUrl(this.g);
        this.e.setVisibility(8);
        this.d.setAnchorView((FrameLayout) findViewById(R.id.videoSurfaceContainer));
        if (((AudioManager) getSystemService("audio")).requestAudioFocus(this.o, 3, 1) == 0) {
            Toast.makeText(this, getString(R.string.error_ongoing_call_during_music_play), 1).show();
        }
        this.c.setOnBufferingUpdateListener(new OnBufferingUpdateListener() {
            public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
                FullScreenVideoPlayerActivity.this.d.setSeekbarBufferProgress(i);
            }
        });
        this.c.seekTo(this.i);
        this.c.start();
        if (!d.a().b("PREFERENCE_YEAR_VIDEO_SESSION_LAUNCH_SUCCEED", false, false)) {
            d.a().a("PREFERENCE_YEAR_VIDEO_SESSION_LAUNCH_SUCCEED", true, false);
            d.a().a("PREFERENCE_YEAR_VIDEO_SESSION_LAUNCH", GaanaApplication.sessionHistoryCount, false);
        }
        this.c.setOnInfoListener(new OnInfoListener() {
            public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
                switch (i) {
                    case 701:
                        FullScreenVideoPlayerActivity.this.e.setVisibility(0);
                        ((ImageButton) FullScreenVideoPlayerActivity.this.d.findViewById(R.id.pause)).setVisibility(4);
                        break;
                    case 702:
                        FullScreenVideoPlayerActivity.this.e.setVisibility(8);
                        ((ImageButton) FullScreenVideoPlayerActivity.this.d.findViewById(R.id.pause)).setVisibility(0);
                        break;
                }
                return false;
            }
        });
        this.c.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer mediaPlayer) {
                FullScreenVideoPlayerActivity.this.d.setMediaCompletionStatus(true);
                FullScreenVideoPlayerActivity.this.d.b();
            }
        });
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.c.setDisplay(surfaceHolder);
        if (!this.h && !this.k) {
            this.c.prepareAsync();
            this.h = true;
            this.d.setMediaPlayerPreparing(this.h);
        } else if (this.k) {
            this.c.start();
        }
    }

    /* Access modifiers changed, original: protected */
    public void onStart() {
        super.onStart();
    }

    /* Access modifiers changed, original: protected */
    public void onPause() {
        super.onPause();
        this.c.pause();
        this.j = true;
    }

    /* Access modifiers changed, original: protected */
    public void onResume() {
        super.onResume();
        if (this.j) {
            this.c.start();
        }
    }

    /* Access modifiers changed, original: protected */
    public void onStop() {
        super.onStop();
        this.c.pause();
        this.j = false;
        this.l = this.c.getDuration();
        this.i = this.c.getCurrentPosition();
        String str = "YIM_Video_ViewTime";
        if (this.n == GAANA_VIDEO_SOURCE.OCCASION_PAGE.ordinal()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("_OP");
            str = stringBuilder.toString();
        }
        u.a().a("YIM_Video", str, com.utilities.d.a(this.c.getCurrentPosition() / 1000));
    }

    /* Access modifiers changed, original: protected */
    public void onDestroy() {
        super.onDestroy();
        this.c.stop();
        this.c.reset();
        this.c.release();
        ((AudioManager) getSystemService("audio")).abandonAudioFocus(this.o);
        this.k = false;
        this.j = false;
    }

    public void a() {
        if (!this.h) {
            this.c.start();
            this.d.setMediaCompletionStatus(false);
        }
    }

    public void b() {
        this.c.pause();
    }

    public int c() {
        return this.c.getDuration();
    }

    public int d() {
        return this.c.getCurrentPosition();
    }

    public void a(int i) {
        this.c.seekTo(i);
        if (((long) i) * 1000 != ((long) c())) {
            this.d.setMediaCompletionStatus(false);
        }
    }

    public boolean e() {
        return this.c.isPlaying();
    }

    public boolean i() {
        return getResources().getConfiguration().orientation == 2;
    }

    private void a(boolean z) {
        DisplayMetrics displayMetrics;
        int i;
        LayoutParams layoutParams;
        if (z) {
            setRequestedOrientation(0);
            displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            i = displayMetrics.heightPixels;
            layoutParams = (LayoutParams) this.a.getLayoutParams();
            layoutParams.width = displayMetrics.widthPixels;
            layoutParams.height = i;
            layoutParams.setMargins(0, 0, 0, 0);
            return;
        }
        setRequestedOrientation(1);
        displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        i = Float.valueOf(((float) displayMetrics.heightPixels) * 0.4f).intValue();
        layoutParams = (LayoutParams) this.a.getLayoutParams();
        layoutParams.width = displayMetrics.widthPixels;
        layoutParams.height = i;
        layoutParams.gravity = 17;
        layoutParams.setMargins(0, 0, 0, 0);
    }

    public void j() {
        boolean isPlaying = this.c.isPlaying();
        this.c.pause();
        DisplayMetrics displayMetrics;
        int i;
        LayoutParams layoutParams;
        if (getResources().getConfiguration().orientation == 1) {
            setRequestedOrientation(0);
            displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            i = displayMetrics.heightPixels;
            layoutParams = (LayoutParams) this.a.getLayoutParams();
            layoutParams.width = displayMetrics.widthPixels;
            layoutParams.height = i;
            layoutParams.setMargins(0, 0, 0, 0);
        } else {
            setRequestedOrientation(1);
            displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            FrameLayout frameLayout = (FrameLayout) findViewById(R.id.videoSurfaceContainer);
            i = Float.valueOf(((float) displayMetrics.heightPixels) * 0.4f).intValue();
            layoutParams = (LayoutParams) this.a.getLayoutParams();
            layoutParams.width = displayMetrics.widthPixels;
            layoutParams.height = i;
            layoutParams.gravity = 17;
            layoutParams.setMargins(0, 0, 0, 0);
        }
        if (isPlaying) {
            this.c.start();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.d.e();
        }
        return false;
    }
}
