package com.facebook.ads.internal.view.g.d;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.MediaController;
import android.widget.MediaController.MediaPlayerControl;
import com.facebook.ads.internal.l.b;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer.EventListener;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player$EventListener$$CC;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.SimpleExoPlayer.VideoListener;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection.Factory;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoListener$$CC;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;

@TargetApi(14)
public class a extends TextureView implements SurfaceTextureListener, c, EventListener, VideoListener {
    private static final String a = "a";
    private Uri b;
    @Nullable
    private String c;
    private e d;
    private Surface e;
    @Nullable
    private SimpleExoPlayer f;
    private MediaController g;
    private d h = d.IDLE;
    private d i = d.IDLE;
    private d j = d.IDLE;
    private boolean k = false;
    private View l;
    private boolean m = false;
    private boolean n = false;
    private long o;
    private long p;
    private long q;
    private int r;
    private int s;
    private float t = 1.0f;
    private int u = -1;
    private boolean v = false;
    private boolean w = false;
    private com.facebook.ads.internal.view.g.a.a x = com.facebook.ads.internal.view.g.a.a.NOT_STARTED;
    private boolean y = false;

    public a(Context context) {
        super(context);
    }

    public a(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public a(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @TargetApi(21)
    public a(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    private void f() {
        TransferListener defaultBandwidthMeter = new DefaultBandwidthMeter();
        this.f = ExoPlayerFactory.newSimpleInstance(getContext(), new DefaultTrackSelector(new Factory(defaultBandwidthMeter)), new DefaultLoadControl());
        this.f.setVideoListener(this);
        this.f.addListener(this);
        this.f.setPlayWhenReady(false);
        if (this.n && !this.v) {
            this.g = new MediaController(getContext());
            this.g.setAnchorView(this.l == null ? this : this.l);
            this.g.setMediaPlayer(new MediaPlayerControl() {
                public boolean canPause() {
                    return true;
                }

                public boolean canSeekBackward() {
                    return true;
                }

                public boolean canSeekForward() {
                    return true;
                }

                public int getAudioSessionId() {
                    return a.this.f != null ? a.this.f.getAudioSessionId() : 0;
                }

                public int getBufferPercentage() {
                    return a.this.f != null ? a.this.f.getBufferedPercentage() : 0;
                }

                public int getCurrentPosition() {
                    return a.this.getCurrentPosition();
                }

                public int getDuration() {
                    return a.this.getDuration();
                }

                public boolean isPlaying() {
                    return a.this.f != null && a.this.f.getPlayWhenReady();
                }

                public void pause() {
                    a.this.a(true);
                }

                public void seekTo(int i) {
                    a.this.a(i);
                }

                public void start() {
                    a.this.a(com.facebook.ads.internal.view.g.a.a.USER_STARTED);
                }
            });
            this.g.setEnabled(true);
        }
        if (this.c == null || this.c.length() == 0 || this.y) {
            this.f.prepare(new ExtractorMediaSource(this.b, new DefaultDataSourceFactory(getContext(), Util.getUserAgent(getContext(), "ads"), defaultBandwidthMeter), new DefaultExtractorsFactory(), null, null));
        }
        setVideoState(d.PREPARING);
        if (isAvailable()) {
            onSurfaceTextureAvailable(getSurfaceTexture(), 0, 0);
        }
    }

    private void g() {
        if (this.e != null) {
            this.e.release();
            this.e = null;
        }
        if (this.f != null) {
            this.f.release();
            this.f = null;
        }
        this.g = null;
        this.m = false;
        setVideoState(d.IDLE);
    }

    private void setVideoState(d dVar) {
        if (dVar != this.h) {
            this.h = dVar;
            if (this.h == d.STARTED) {
                this.m = true;
            }
            if (this.d != null) {
                this.d.a(dVar);
            }
        }
    }

    public void a() {
        if (!this.w) {
            a(false);
        }
    }

    public void a(int i) {
        if (this.f != null) {
            this.u = getCurrentPosition();
            this.f.seekTo((long) i);
            return;
        }
        this.q = (long) i;
    }

    public void a(com.facebook.ads.internal.view.g.a.a aVar) {
        this.i = d.STARTED;
        this.x = aVar;
        if (this.f == null) {
            setup(this.b);
            return;
        }
        if (this.h == d.PREPARED || this.h == d.PAUSED || this.h == d.PLAYBACK_COMPLETED) {
            this.f.setPlayWhenReady(true);
            setVideoState(d.STARTED);
        }
    }

    public void a(boolean z) {
        if (this.f != null) {
            this.f.setPlayWhenReady(false);
        } else {
            setVideoState(d.IDLE);
        }
    }

    public void b() {
        setVideoState(d.PLAYBACK_COMPLETED);
        c();
        this.q = 0;
    }

    public void c() {
        this.i = d.IDLE;
        if (this.f != null) {
            this.f.stop();
            this.f.release();
            this.f = null;
        }
        setVideoState(d.IDLE);
    }

    public boolean d() {
        return (this.f == null || this.f.getAudioFormat() == null) ? false : true;
    }

    public void e() {
        g();
    }

    public int getCurrentPosition() {
        return this.f != null ? (int) this.f.getCurrentPosition() : 0;
    }

    public int getDuration() {
        return this.f == null ? 0 : (int) this.f.getDuration();
    }

    public long getInitialBufferTime() {
        return this.p;
    }

    public com.facebook.ads.internal.view.g.a.a getStartReason() {
        return this.x;
    }

    public d getState() {
        return this.h;
    }

    public d getTargetState() {
        return this.i;
    }

    public int getVideoHeight() {
        return this.s;
    }

    public int getVideoWidth() {
        return this.r;
    }

    public View getView() {
        return this;
    }

    public float getVolume() {
        return this.t;
    }

    public void onLoadingChanged(boolean z) {
    }

    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
    }

    public void onPlayerError(ExoPlaybackException exoPlaybackException) {
        setVideoState(d.ERROR);
        ThrowableExtension.printStackTrace(exoPlaybackException);
        b.a(com.facebook.ads.internal.l.a.a(exoPlaybackException, "[ExoPlayer] Error during playback of ExoPlayer"));
    }

    public void onPlayerStateChanged(boolean r9, int r10) {
        /*
        r8 = this;
        switch(r10) {
            case 1: goto L_0x0094;
            case 2: goto L_0x0081;
            case 3: goto L_0x001f;
            case 4: goto L_0x0004;
            default: goto L_0x0003;
        };
    L_0x0003:
        return;
    L_0x0004:
        if (r9 == 0) goto L_0x000b;
    L_0x0006:
        r10 = com.facebook.ads.internal.view.g.d.d.PLAYBACK_COMPLETED;
        r8.setVideoState(r10);
    L_0x000b:
        r10 = r8.f;
        r0 = 0;
        if (r10 == 0) goto L_0x001c;
    L_0x0010:
        r10 = r8.f;
        r10.setPlayWhenReady(r0);
        if (r9 != 0) goto L_0x001c;
    L_0x0017:
        r9 = r8.f;
        r9.seekToDefaultPosition();
    L_0x001c:
        r8.m = r0;
        return;
    L_0x001f:
        r0 = r8.o;
        r2 = 0;
        r10 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r10 == 0) goto L_0x0031;
    L_0x0027:
        r0 = java.lang.System.currentTimeMillis();
        r4 = r8.o;
        r6 = r0 - r4;
        r8.p = r6;
    L_0x0031:
        r10 = r8.t;
        r8.setRequestedVolume(r10);
        r0 = r8.q;
        r10 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r10 <= 0) goto L_0x0051;
    L_0x003c:
        r0 = r8.q;
        r10 = r8.f;
        r4 = r10.getDuration();
        r10 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
        if (r10 >= 0) goto L_0x0051;
    L_0x0048:
        r10 = r8.f;
        r0 = r8.q;
        r10.seekTo(r0);
        r8.q = r2;
    L_0x0051:
        r10 = r8.f;
        r0 = r10.getCurrentPosition();
        r10 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r10 == 0) goto L_0x0064;
    L_0x005b:
        if (r9 != 0) goto L_0x0064;
    L_0x005d:
        r10 = r8.m;
        if (r10 == 0) goto L_0x0064;
    L_0x0061:
        r9 = com.facebook.ads.internal.view.g.d.d.PAUSED;
        goto L_0x0096;
    L_0x0064:
        if (r9 != 0) goto L_0x0099;
    L_0x0066:
        r9 = r8.h;
        r10 = com.facebook.ads.internal.view.g.d.d.PLAYBACK_COMPLETED;
        if (r9 == r10) goto L_0x0099;
    L_0x006c:
        r9 = com.facebook.ads.internal.view.g.d.d.PREPARED;
        r8.setVideoState(r9);
        r9 = r8.i;
        r10 = com.facebook.ads.internal.view.g.d.d.STARTED;
        if (r9 != r10) goto L_0x0099;
    L_0x0077:
        r9 = r8.x;
        r8.a(r9);
        r9 = com.facebook.ads.internal.view.g.d.d.IDLE;
        r8.i = r9;
        return;
    L_0x0081:
        r9 = r8.u;
        if (r9 < 0) goto L_0x0099;
    L_0x0085:
        r9 = r8.u;
        r10 = -1;
        r8.u = r10;
        r10 = r8.d;
        r0 = r8.getCurrentPosition();
        r10.a(r9, r0);
        return;
    L_0x0094:
        r9 = com.facebook.ads.internal.view.g.d.d.IDLE;
    L_0x0096:
        r8.setVideoState(r9);
    L_0x0099:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.view.g.d.a.onPlayerStateChanged(boolean, int):void");
    }

    public void onPositionDiscontinuity() {
    }

    public void onPositionDiscontinuity(int i) {
        Player$EventListener$$CC.onPositionDiscontinuity(this, i);
    }

    public void onRenderedFirstFrame() {
    }

    public void onRepeatModeChanged(int i) {
        Player$EventListener$$CC.onRepeatModeChanged(this, i);
    }

    public void onSeekProcessed() {
        Player$EventListener$$CC.onSeekProcessed(this);
    }

    public void onShuffleModeEnabledChanged(boolean z) {
        Player$EventListener$$CC.onShuffleModeEnabledChanged(this, z);
    }

    public void onSurfaceSizeChanged(int i, int i2) {
        VideoListener$$CC.onSurfaceSizeChanged(this, i, i2);
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        if (this.e != null) {
            this.e.release();
        }
        this.e = new Surface(surfaceTexture);
        if (this.f != null) {
            this.f.setVideoSurface(this.e);
            this.k = false;
            if (this.h == d.PAUSED && this.j != d.PAUSED) {
                a(this.x);
            }
        }
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        if (this.e != null) {
            this.e.release();
            this.e = null;
            if (this.f != null) {
                this.f.setVideoSurface(null);
            }
        }
        if (!this.k) {
            this.j = this.n ? d.STARTED : this.h;
            this.k = true;
        }
        if (this.h != d.PAUSED) {
            a(false);
        }
        return true;
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public void onTimelineChanged(Timeline timeline, Object obj) {
    }

    public void onTimelineChanged(Timeline timeline, Object obj, int i) {
        Player$EventListener$$CC.onTimelineChanged(this, timeline, obj, i);
    }

    public void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
    }

    public void onVideoSizeChanged(int i, int i2, int i3, float f) {
        this.r = i;
        this.s = i2;
        if (this.r != 0 && this.s != 0) {
            requestLayout();
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (this.f != null) {
            if (this.g == null || !this.g.isShowing()) {
                if (z) {
                    this.k = false;
                    if (this.h == d.PAUSED && this.j != d.PAUSED) {
                        a(this.x);
                    }
                } else {
                    if (!this.k) {
                        this.j = this.n ? d.STARTED : this.h;
                        this.k = true;
                    }
                    if (this.h != d.PAUSED) {
                        a();
                    }
                }
            }
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        if (VERSION.SDK_INT < 24) {
            super.setBackgroundDrawable(drawable);
            return;
        }
        if (AdInternalSettings.isDebugBuild()) {
            Log.w(a, "Google always throw an exception with setBackgroundDrawable on Nougat above. so we silently ignore it.");
        }
    }

    public void setBackgroundPlaybackEnabled(boolean z) {
        this.w = z;
    }

    public void setControlsAnchorView(View view) {
        this.l = view;
        view.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (a.this.g != null && motionEvent.getAction() == 1) {
                    if (a.this.g.isShowing()) {
                        a.this.g.hide();
                        return true;
                    }
                    a.this.g.show();
                }
                return true;
            }
        });
    }

    public void setForeground(Drawable drawable) {
        if (VERSION.SDK_INT < 24) {
            super.setForeground(drawable);
            return;
        }
        if (AdInternalSettings.isDebugBuild()) {
            Log.w(a, "Google always throw an exception with setForeground on Nougat above. so we silently ignore it.");
        }
    }

    public void setFullScreen(boolean z) {
        this.n = z;
        if (z && !this.v) {
            setOnTouchListener(new OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (a.this.g != null && motionEvent.getAction() == 1) {
                        if (a.this.g.isShowing()) {
                            a.this.g.hide();
                            return true;
                        }
                        a.this.g.show();
                    }
                    return true;
                }
            });
        }
    }

    public void setRequestedVolume(float f) {
        this.t = f;
        if (this.f != null && this.h != d.PREPARING && this.h != d.IDLE) {
            this.f.setVolume(f);
        }
    }

    public void setTestMode(boolean z) {
        this.y = z;
    }

    public void setVideoMPD(@Nullable String str) {
        this.c = str;
    }

    public void setVideoStateChangeListener(e eVar) {
        this.d = eVar;
    }

    public void setup(Uri uri) {
        if (this.f != null) {
            g();
        }
        this.b = uri;
        setSurfaceTextureListener(this);
        f();
    }
}
