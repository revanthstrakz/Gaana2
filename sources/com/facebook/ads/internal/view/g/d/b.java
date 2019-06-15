package com.facebook.ads.internal.view.g.d;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.media.MediaPlayer.TrackInfo;
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
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.ads.internal.view.g.a.a;

@TargetApi(14)
public class b extends TextureView implements OnBufferingUpdateListener, OnCompletionListener, OnErrorListener, OnInfoListener, OnPreparedListener, OnSeekCompleteListener, OnVideoSizeChangedListener, SurfaceTextureListener, c {
    private static final String t = "b";
    private Uri a;
    private e b;
    private Surface c;
    @Nullable
    private MediaPlayer d;
    private MediaController e;
    private d f = d.IDLE;
    private d g = d.IDLE;
    private d h = d.IDLE;
    private boolean i = false;
    private View j;
    private int k = 0;
    private long l;
    private int m = 0;
    private int n = 0;
    private float o = 1.0f;
    private boolean p = false;
    private int q = 3;
    private boolean r = false;
    private boolean s = false;
    private int u = 0;
    private boolean v = false;
    private a w = a.NOT_STARTED;
    private final MediaPlayerControl x = new MediaPlayerControl() {
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
            return b.this.d != null ? b.this.d.getAudioSessionId() : 0;
        }

        public int getBufferPercentage() {
            return 0;
        }

        public int getCurrentPosition() {
            return b.this.getCurrentPosition();
        }

        public int getDuration() {
            return b.this.getDuration();
        }

        public boolean isPlaying() {
            return b.this.d != null && b.this.d.isPlaying();
        }

        public void pause() {
            b.this.a(true);
        }

        public void seekTo(int i) {
            b.this.a(i);
        }

        public void start() {
            b.this.a(a.USER_STARTED);
        }
    };

    public b(Context context) {
        super(context);
    }

    public b(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public b(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @TargetApi(21)
    public b(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    private boolean a(@Nullable Surface surface) {
        if (this.d == null) {
            return false;
        }
        try {
            this.d.setSurface(surface);
            return true;
        } catch (IllegalStateException e) {
            com.facebook.ads.internal.s.d.a.a(getContext(), "player", com.facebook.ads.internal.s.d.b.w, e);
            Log.d(t, "The MediaPlayer failed", e);
            return false;
        }
    }

    private boolean f() {
        return this.f == d.PREPARED || this.f == d.STARTED || this.f == d.PAUSED || this.f == d.PLAYBACK_COMPLETED;
    }

    private boolean g() {
        if (this.d == null) {
            return false;
        }
        try {
            this.d.reset();
            return true;
        } catch (IllegalStateException e) {
            com.facebook.ads.internal.s.d.a.a(getContext(), "player", com.facebook.ads.internal.s.d.b.x, e);
            Log.d(t, "The MediaPlayer failed", e);
            return false;
        }
    }

    private boolean h() {
        return (this.f == d.PREPARING || this.f == d.PREPARED) ? false : true;
    }

    private boolean i() {
        return (this.f == d.PREPARING || this.f == d.PREPARED) ? false : true;
    }

    private void setVideoState(d dVar) {
        if (dVar != this.f) {
            this.f = dVar;
            if (this.b != null) {
                this.b.a(dVar);
            }
        }
    }

    public void a() {
        if (!this.r) {
            a(false);
        }
    }

    public void a(int i) {
        if (this.d == null || !f()) {
            this.k = i;
        } else if (i < getDuration() && i > 0) {
            this.u = getCurrentPosition();
            this.k = i;
            this.d.seekTo(i);
        }
    }

    public void a(a aVar) {
        this.g = d.STARTED;
        this.w = aVar;
        if (this.f == d.STARTED || this.f == d.PREPARED || this.f == d.IDLE || this.f == d.PAUSED || this.f == d.PLAYBACK_COMPLETED) {
            if (this.d == null) {
                setup(this.a);
            } else {
                if (this.k > 0) {
                    this.d.seekTo(this.k);
                }
                this.d.start();
                if (this.f != d.PREPARED || this.s) {
                    setVideoState(d.STARTED);
                }
            }
        }
        if (isAvailable()) {
            onSurfaceTextureAvailable(getSurfaceTexture(), 0, 0);
        }
    }

    public void a(boolean z) {
        d dVar;
        this.g = d.PAUSED;
        if (this.d == null) {
            dVar = d.IDLE;
        } else if (i()) {
            if (z) {
                this.h = d.PAUSED;
                this.i = true;
            }
            this.d.pause();
            if (this.f != d.PLAYBACK_COMPLETED) {
                dVar = d.PAUSED;
            }
        } else {
            return;
        }
        setVideoState(dVar);
    }

    public void b() {
        setVideoState(d.PLAYBACK_COMPLETED);
        c();
        this.k = 0;
    }

    public void c() {
        this.g = d.IDLE;
        if (this.d != null) {
            int currentPosition = this.d.getCurrentPosition();
            if (currentPosition > 0) {
                this.k = currentPosition;
            }
            this.d.stop();
            g();
            this.d.release();
            this.d = null;
            if (this.e != null) {
                this.e.hide();
                this.e.setEnabled(false);
            }
        }
        setVideoState(d.IDLE);
    }

    @SuppressLint({"NewApi"})
    public boolean d() {
        if (this.d == null || VERSION.SDK_INT < 16) {
            return false;
        }
        try {
            for (TrackInfo trackType : this.d.getTrackInfo()) {
                if (trackType.getTrackType() == 2) {
                    return true;
                }
            }
            return false;
        } catch (RuntimeException e) {
            Log.e(t, "Couldn't retrieve video information", e);
            return true;
        }
    }

    public void e() {
        if (this.d != null) {
            a(null);
            this.d.setOnBufferingUpdateListener(null);
            this.d.setOnCompletionListener(null);
            this.d.setOnErrorListener(null);
            this.d.setOnInfoListener(null);
            this.d.setOnPreparedListener(null);
            this.d.setOnVideoSizeChangedListener(null);
            this.d.setOnSeekCompleteListener(null);
            g();
            this.d = null;
            setVideoState(d.IDLE);
        }
    }

    public int getCurrentPosition() {
        return (this.d == null || !f()) ? 0 : this.d.getCurrentPosition();
    }

    public int getDuration() {
        return (this.d == null || !f()) ? 0 : this.d.getDuration();
    }

    public long getInitialBufferTime() {
        return this.l;
    }

    public a getStartReason() {
        return this.w;
    }

    public d getState() {
        return this.f;
    }

    public d getTargetState() {
        return this.g;
    }

    public int getVideoHeight() {
        return this.n;
    }

    public int getVideoWidth() {
        return this.m;
    }

    public View getView() {
        return this;
    }

    public float getVolume() {
        return this.o;
    }

    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        if (this.d != null) {
            this.d.pause();
        }
        setVideoState(d.PLAYBACK_COMPLETED);
        a(0);
        this.k = 0;
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        if (this.q <= 0 || getState() != d.STARTED) {
            setVideoState(d.ERROR);
            c();
            return true;
        }
        this.q--;
        c();
        a(this.w);
        return true;
    }

    public boolean onInfo(android.media.MediaPlayer r1, int r2, int r3) {
        /*
        r0 = this;
        r1 = 3;
        if (r2 == r1) goto L_0x0017;
    L_0x0003:
        switch(r2) {
            case 701: goto L_0x0010;
            case 702: goto L_0x0007;
            default: goto L_0x0006;
        };
    L_0x0006:
        goto L_0x0015;
    L_0x0007:
        r1 = r0.h();
        if (r1 == 0) goto L_0x0015;
    L_0x000d:
        r1 = com.facebook.ads.internal.view.g.d.d.STARTED;
        goto L_0x0012;
    L_0x0010:
        r1 = com.facebook.ads.internal.view.g.d.d.BUFFERING;
    L_0x0012:
        r0.setVideoState(r1);
    L_0x0015:
        r1 = 0;
        return r1;
    L_0x0017:
        r1 = 1;
        r0.s = r1;
        r2 = r0.g;
        r3 = com.facebook.ads.internal.view.g.d.d.STARTED;
        if (r2 != r3) goto L_0x0025;
    L_0x0020:
        r2 = com.facebook.ads.internal.view.g.d.d.STARTED;
        r0.setVideoState(r2);
    L_0x0025:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.view.g.d.b.onInfo(android.media.MediaPlayer, int, int):boolean");
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        setVideoState(d.PREPARED);
        if (this.p && !this.v) {
            this.e = new MediaController(getContext());
            this.e.setAnchorView(this.j == null ? this : this.j);
            this.e.setMediaPlayer(this.x);
            this.e.setEnabled(true);
        }
        setRequestedVolume(this.o);
        this.m = mediaPlayer.getVideoWidth();
        this.n = mediaPlayer.getVideoHeight();
        if (this.k > 0) {
            if (this.k >= this.d.getDuration()) {
                this.k = 0;
            }
            this.d.seekTo(this.k);
            this.k = 0;
        }
        if (this.g == d.STARTED) {
            a(this.w);
        }
    }

    public void onSeekComplete(MediaPlayer mediaPlayer) {
        if (this.b != null) {
            this.b.a(this.u, this.k);
            this.k = 0;
        }
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        if (this.c == null) {
            this.c = new Surface(surfaceTexture);
        }
        if (a(this.c)) {
            this.i = false;
            if (this.f == d.PAUSED && this.h != d.PAUSED) {
                a(this.w);
            }
            return;
        }
        setVideoState(d.ERROR);
        e();
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        a(null);
        if (this.c != null) {
            this.c.release();
            this.c = null;
        }
        if (!this.i) {
            this.h = this.p ? d.STARTED : this.f;
            this.i = true;
        }
        if (this.f != d.PAUSED) {
            a(false);
        }
        return true;
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        this.m = mediaPlayer.getVideoWidth();
        this.n = mediaPlayer.getVideoHeight();
        if (this.m != 0 && this.n != 0) {
            requestLayout();
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (this.d != null) {
            if (this.e == null || !this.e.isShowing()) {
                if (z) {
                    this.i = false;
                    if (this.f == d.PAUSED && this.h != d.PAUSED) {
                        a(this.w);
                    }
                } else {
                    if (!this.i) {
                        this.h = this.p ? d.STARTED : this.f;
                        this.i = true;
                    }
                    if (this.f != d.PAUSED) {
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
            Log.w(t, "Google always throw an exception with setBackgroundDrawable on Nougat above. so we silently ignore it.");
        }
    }

    public void setBackgroundPlaybackEnabled(boolean z) {
        this.r = z;
    }

    public void setControlsAnchorView(View view) {
        this.j = view;
        view.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (!(b.this.v || b.this.e == null || motionEvent.getAction() != 1)) {
                    if (b.this.e.isShowing()) {
                        b.this.e.hide();
                        return true;
                    }
                    b.this.e.show();
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
            Log.w(t, "Google always throw an exception with setForeground on Nougat above. so we silently ignore it.");
        }
    }

    public void setFullScreen(boolean z) {
        this.p = z;
        if (this.p && !this.v) {
            setOnTouchListener(new OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (!(b.this.v || b.this.e == null || motionEvent.getAction() != 1)) {
                        if (b.this.e.isShowing()) {
                            b.this.e.hide();
                            return true;
                        }
                        b.this.e.show();
                    }
                    return true;
                }
            });
        }
    }

    public void setRequestedVolume(float f) {
        this.o = f;
        if (this.d != null && this.f != d.PREPARING && this.f != d.IDLE) {
            this.d.setVolume(f, f);
        }
    }

    public void setVideoMPD(@Nullable String str) {
    }

    public void setVideoStateChangeListener(e eVar) {
        this.b = eVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:47:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0121  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0121  */
    /* JADX WARNING: Removed duplicated region for block: B:47:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:47:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0121  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0098 A:{SYNTHETIC, Splitter:B:27:0x0098} */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0121  */
    /* JADX WARNING: Removed duplicated region for block: B:47:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00b3 A:{SYNTHETIC, Splitter:B:33:0x00b3} */
    public void setup(android.net.Uri r11) {
        /*
        r10 = this;
        r0 = 0;
        r10.s = r0;
        r10.a = r11;
        r1 = r10.d;
        r2 = 0;
        if (r1 == 0) goto L_0x0018;
    L_0x000a:
        r10.g();
        r10.a(r2);
        r1 = r10.d;
        r3 = com.facebook.ads.internal.view.g.d.d.IDLE;
        r10.setVideoState(r3);
        goto L_0x001d;
    L_0x0018:
        r1 = new android.media.MediaPlayer;
        r1.<init>();
    L_0x001d:
        r3 = r11.getScheme();	 Catch:{ Exception -> 0x00f9 }
        r4 = "asset";
        r3 = r3.equals(r4);	 Catch:{ Exception -> 0x00f9 }
        if (r3 == 0) goto L_0x00cf;
    L_0x0029:
        r3 = r10.getContext();	 Catch:{ IOException | SecurityException -> 0x007a, IOException | SecurityException -> 0x007a }
        r3 = r3.getAssets();	 Catch:{ IOException | SecurityException -> 0x007a, IOException | SecurityException -> 0x007a }
        r11 = r11.getPath();	 Catch:{ IOException | SecurityException -> 0x007a, IOException | SecurityException -> 0x007a }
        r4 = 1;
        r11 = r11.substring(r4);	 Catch:{ IOException | SecurityException -> 0x007a, IOException | SecurityException -> 0x007a }
        r11 = r3.openFd(r11);	 Catch:{ IOException | SecurityException -> 0x007a, IOException | SecurityException -> 0x007a }
        r5 = r11.getStartOffset();	 Catch:{ IOException | SecurityException -> 0x0073, IOException | SecurityException -> 0x0073, all -> 0x006e }
        r7 = r11.getLength();	 Catch:{ IOException | SecurityException -> 0x0073, IOException | SecurityException -> 0x0073, all -> 0x006e }
        r4 = r11.getFileDescriptor();	 Catch:{ IOException | SecurityException -> 0x0073, IOException | SecurityException -> 0x0073, all -> 0x006e }
        r3 = r1;
        r3.setDataSource(r4, r5, r7);	 Catch:{ IOException | SecurityException -> 0x0073, IOException | SecurityException -> 0x0073, all -> 0x006e }
        if (r11 == 0) goto L_0x00d6;
    L_0x0050:
        r11.close();	 Catch:{ IOException -> 0x0055 }
        goto L_0x00d6;
    L_0x0055:
        r11 = move-exception;
        r2 = t;	 Catch:{ Exception -> 0x00f9 }
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00f9 }
        r3.<init>();	 Catch:{ Exception -> 0x00f9 }
        r4 = "Unable to close";
        r3.append(r4);	 Catch:{ Exception -> 0x00f9 }
        r3.append(r11);	 Catch:{ Exception -> 0x00f9 }
        r11 = r3.toString();	 Catch:{ Exception -> 0x00f9 }
    L_0x0069:
        android.util.Log.w(r2, r11);	 Catch:{ Exception -> 0x00f9 }
        goto L_0x00d6;
    L_0x006e:
        r2 = move-exception;
        r9 = r2;
        r2 = r11;
        r11 = r9;
        goto L_0x00b1;
    L_0x0073:
        r2 = move-exception;
        r9 = r2;
        r2 = r11;
        r11 = r9;
        goto L_0x007b;
    L_0x0078:
        r11 = move-exception;
        goto L_0x00b1;
    L_0x007a:
        r11 = move-exception;
    L_0x007b:
        r3 = t;	 Catch:{ all -> 0x0078 }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0078 }
        r4.<init>();	 Catch:{ all -> 0x0078 }
        r5 = "Failed to open assets ";
        r4.append(r5);	 Catch:{ all -> 0x0078 }
        r4.append(r11);	 Catch:{ all -> 0x0078 }
        r11 = r4.toString();	 Catch:{ all -> 0x0078 }
        android.util.Log.w(r3, r11);	 Catch:{ all -> 0x0078 }
        r11 = com.facebook.ads.internal.view.g.d.d.ERROR;	 Catch:{ all -> 0x0078 }
        r10.setVideoState(r11);	 Catch:{ all -> 0x0078 }
        if (r2 == 0) goto L_0x00d6;
    L_0x0098:
        r2.close();	 Catch:{ IOException -> 0x009c }
        goto L_0x00d6;
    L_0x009c:
        r11 = move-exception;
        r2 = t;	 Catch:{ Exception -> 0x00f9 }
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00f9 }
        r3.<init>();	 Catch:{ Exception -> 0x00f9 }
        r4 = "Unable to close";
        r3.append(r4);	 Catch:{ Exception -> 0x00f9 }
        r3.append(r11);	 Catch:{ Exception -> 0x00f9 }
        r11 = r3.toString();	 Catch:{ Exception -> 0x00f9 }
        goto L_0x0069;
    L_0x00b1:
        if (r2 == 0) goto L_0x00ce;
    L_0x00b3:
        r2.close();	 Catch:{ IOException -> 0x00b7 }
        goto L_0x00ce;
    L_0x00b7:
        r2 = move-exception;
        r3 = t;	 Catch:{ Exception -> 0x00f9 }
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00f9 }
        r4.<init>();	 Catch:{ Exception -> 0x00f9 }
        r5 = "Unable to close";
        r4.append(r5);	 Catch:{ Exception -> 0x00f9 }
        r4.append(r2);	 Catch:{ Exception -> 0x00f9 }
        r2 = r4.toString();	 Catch:{ Exception -> 0x00f9 }
        android.util.Log.w(r3, r2);	 Catch:{ Exception -> 0x00f9 }
    L_0x00ce:
        throw r11;	 Catch:{ Exception -> 0x00f9 }
    L_0x00cf:
        r11 = r11.toString();	 Catch:{ Exception -> 0x00f9 }
        r1.setDataSource(r11);	 Catch:{ Exception -> 0x00f9 }
    L_0x00d6:
        r1.setLooping(r0);	 Catch:{ Exception -> 0x00f9 }
        r1.setOnBufferingUpdateListener(r10);	 Catch:{ Exception -> 0x00f9 }
        r1.setOnCompletionListener(r10);	 Catch:{ Exception -> 0x00f9 }
        r1.setOnErrorListener(r10);	 Catch:{ Exception -> 0x00f9 }
        r1.setOnInfoListener(r10);	 Catch:{ Exception -> 0x00f9 }
        r1.setOnPreparedListener(r10);	 Catch:{ Exception -> 0x00f9 }
        r1.setOnVideoSizeChangedListener(r10);	 Catch:{ Exception -> 0x00f9 }
        r1.setOnSeekCompleteListener(r10);	 Catch:{ Exception -> 0x00f9 }
        r1.prepareAsync();	 Catch:{ Exception -> 0x00f9 }
        r10.d = r1;	 Catch:{ Exception -> 0x00f9 }
        r11 = com.facebook.ads.internal.view.g.d.d.PREPARING;	 Catch:{ Exception -> 0x00f9 }
        r10.setVideoState(r11);	 Catch:{ Exception -> 0x00f9 }
        goto L_0x0118;
    L_0x00f9:
        r11 = move-exception;
        r2 = com.facebook.ads.internal.view.g.d.d.ERROR;
        r10.setVideoState(r2);
        r1.release();
        r1 = t;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "Cannot prepare media player with SurfaceTexture: ";
        r2.append(r3);
        r2.append(r11);
        r11 = r2.toString();
        android.util.Log.e(r1, r11);
    L_0x0118:
        r10.setSurfaceTextureListener(r10);
        r11 = r10.isAvailable();
        if (r11 == 0) goto L_0x0128;
    L_0x0121:
        r11 = r10.getSurfaceTexture();
        r10.onSurfaceTextureAvailable(r11, r0, r0);
    L_0x0128:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.view.g.d.b.setup(android.net.Uri):void");
    }
}
