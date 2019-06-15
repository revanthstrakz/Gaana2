package com.inmobi.ads;

import android.annotation.TargetApi;
import android.app.KeyguardManager;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.AudioAttributes.Builder;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.view.Surface;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View.MeasureSpec;
import android.widget.ImageView;
import android.widget.MediaController.MediaPlayerControl;
import android.widget.ProgressBar;
import com.inmobi.ads.AdContainer.RenderingProperties.PlacementType;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

@TargetApi(15)
public class NativeVideoView extends TextureView implements MediaPlayerControl {
    private static final String m = "NativeVideoView";
    private OnCompletionListener A = new OnCompletionListener() {
        public final void onCompletion(MediaPlayer mediaPlayer) {
            try {
                NativeVideoView.f(NativeVideoView.this);
            } catch (Exception e) {
                NativeVideoView.m;
                new StringBuilder("SDK encountered unexpected error in handling the media playback complete event; ").append(e.getMessage());
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            }
        }
    };
    private OnInfoListener B = new OnInfoListener() {
        @TargetApi(17)
        public final boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
            if (VERSION.SDK_INT >= 17 && 3 == i) {
                NativeVideoView.this.a(8, 8);
            }
            return true;
        }
    };
    private OnBufferingUpdateListener C = new OnBufferingUpdateListener() {
        public final void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
            NativeVideoView.this.w = i;
        }
    };
    private OnErrorListener D = new OnErrorListener() {
        public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
            NativeVideoView.m;
            StringBuilder stringBuilder = new StringBuilder("Media Play Error ");
            stringBuilder.append(i);
            stringBuilder.append(",");
            stringBuilder.append(i2);
            if (NativeVideoView.this.t != null) {
                NativeVideoView.this.t.a(i);
            }
            if (NativeVideoView.this.c != null) {
                NativeVideoView.this.c.a = -1;
                NativeVideoView.this.c.b = -1;
            }
            if (NativeVideoView.this.v != null) {
                NativeVideoView.this.v.b();
            }
            NativeVideoView.h(NativeVideoView.this);
            return true;
        }
    };
    Uri a;
    Map<String, String> b;
    av c = null;
    int d;
    int e;
    int f;
    d g;
    @Nullable
    Handler h;
    boolean i;
    OnVideoSizeChangedListener j = new OnVideoSizeChangedListener() {
        public final void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
            NativeVideoView.this.e = mediaPlayer.getVideoWidth();
            NativeVideoView.this.f = mediaPlayer.getVideoHeight();
            if (NativeVideoView.this.e != 0 && NativeVideoView.this.f != 0) {
                NativeVideoView.this.requestLayout();
            }
        }
    };
    OnPreparedListener k = new OnPreparedListener() {
        public final void onPrepared(MediaPlayer mediaPlayer) {
            if (NativeVideoView.this.c != null) {
                NativeVideoView.this.c.a = 2;
                NativeVideoView.this.x = NativeVideoView.this.y = NativeVideoView.this.z = true;
                if (NativeVideoView.this.v != null) {
                    NativeVideoView.this.v.setEnabled(true);
                }
                NativeVideoView.this.e = mediaPlayer.getVideoWidth();
                NativeVideoView.this.f = mediaPlayer.getVideoHeight();
                be beVar = (be) NativeVideoView.this.getTag();
                int i = 0;
                if (beVar != null && ((Boolean) beVar.v.get("didCompleteQ4")).booleanValue()) {
                    NativeVideoView.this.a(8, 0);
                    if (((PlacementType) beVar.v.get("placementType")) == PlacementType.PLACEMENT_TYPE_FULLSCREEN) {
                        return;
                    }
                }
                if (NativeVideoView.this.getPlaybackEventListener() != null) {
                    NativeVideoView.this.getPlaybackEventListener().a(0);
                }
                if (!(beVar == null || ((Boolean) beVar.v.get("didCompleteQ4")).booleanValue())) {
                    i = ((Integer) beVar.v.get("seekPosition")).intValue();
                }
                if (NativeVideoView.this.e == 0 || NativeVideoView.this.f == 0) {
                    if (3 == NativeVideoView.this.c.b && beVar != null && ((Boolean) beVar.v.get("isFullScreen")).booleanValue()) {
                        NativeVideoView.this.start();
                    }
                } else if (3 == NativeVideoView.this.c.b) {
                    if (beVar != null && ((Boolean) beVar.v.get("isFullScreen")).booleanValue()) {
                        NativeVideoView.this.start();
                    }
                    if (NativeVideoView.this.v != null) {
                        NativeVideoView.this.v.a();
                    }
                } else if (!NativeVideoView.this.isPlaying() && ((i != 0 || NativeVideoView.this.getCurrentPosition() > 0) && NativeVideoView.this.v != null)) {
                    NativeVideoView.this.v.a();
                }
            }
        }
    };
    final SurfaceTextureListener l = new SurfaceTextureListener() {
        public final void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        }

        @TargetApi(16)
        public final void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
            NativeVideoView.this.n = new Surface(surfaceTexture);
            NativeVideoView.this.g();
        }

        public final void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
            Object obj = null;
            Object obj2 = (NativeVideoView.this.c == null || NativeVideoView.this.c.b != 3) ? null : 1;
            if (i > 0 && i2 > 0) {
                obj = 1;
            }
            if (NativeVideoView.this.c != null && obj2 != null && obj != null) {
                if (NativeVideoView.this.getTag() != null) {
                    int intValue = ((Integer) ((be) NativeVideoView.this.getTag()).v.get("seekPosition")).intValue();
                    if (intValue != 0) {
                        NativeVideoView.this.a(intValue);
                    }
                }
                NativeVideoView.this.start();
            }
        }

        public final boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            if (NativeVideoView.this.n != null) {
                NativeVideoView.this.n.release();
                NativeVideoView.this.n = null;
            }
            if (NativeVideoView.this.v != null) {
                NativeVideoView.this.v.b();
            }
            NativeVideoView.this.c();
            return true;
        }
    };
    private Surface n = null;
    private int o;
    private int p = Integer.MIN_VALUE;
    private int q = 0;
    private c r;
    private b s;
    private a t;
    private boolean u;
    private NativeVideoController v;
    private int w;
    private boolean x;
    private boolean y;
    private boolean z;

    interface a {
        void a(int i);
    }

    interface b {
        void a(int i);
    }

    interface c {
        void a(int i);
    }

    static final class d extends Handler {
        private final WeakReference<NativeVideoView> a;

        d(NativeVideoView nativeVideoView) {
            this.a = new WeakReference(nativeVideoView);
        }

        public final void handleMessage(Message message) {
            NativeVideoView nativeVideoView = (NativeVideoView) this.a.get();
            if (nativeVideoView != null && message.what == 1) {
                int duration = nativeVideoView.getDuration();
                int currentPosition = nativeVideoView.getCurrentPosition();
                if (!(duration == -1 || currentPosition == 0)) {
                    be beVar = (be) nativeVideoView.getTag();
                    if (!((Boolean) beVar.v.get("didCompleteQ1")).booleanValue() && (4 * currentPosition) - duration >= 0) {
                        beVar.v.put("didCompleteQ1", Boolean.valueOf(true));
                        nativeVideoView.getQuartileCompletedListener().a(0);
                    }
                    if (!((Boolean) beVar.v.get("didCompleteQ2")).booleanValue() && (2 * currentPosition) - duration >= 0) {
                        beVar.v.put("didCompleteQ2", Boolean.valueOf(true));
                        nativeVideoView.getQuartileCompletedListener().a(1);
                    }
                    if (!((Boolean) beVar.v.get("didCompleteQ3")).booleanValue() && (4 * currentPosition) - (3 * duration) >= 0) {
                        beVar.v.put("didCompleteQ3", Boolean.valueOf(true));
                        nativeVideoView.getQuartileCompletedListener().a(2);
                    }
                    boolean booleanValue = ((Boolean) beVar.v.get("didQ4Fire")).booleanValue();
                    if ((((float) currentPosition) / ((float) duration)) * 100.0f > ((float) beVar.E) && !booleanValue) {
                        nativeVideoView.getPlaybackEventListener().a(5);
                    }
                }
                sendEmptyMessageDelayed(1, 1000);
            }
            super.handleMessage(message);
        }
    }

    public void seekTo(int i) {
    }

    public NativeVideoView(Context context) {
        super(context);
        requestLayout();
        invalidate();
    }

    public av getMediaPlayer() {
        return this.c;
    }

    public final void a() {
        if (this.n != null) {
            this.n.release();
            this.n = null;
        }
        c();
    }

    public int getState() {
        return this.c != null ? this.c.a : 0;
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int i, int i2) {
        try {
            int defaultSize = getDefaultSize(this.e, i);
            int defaultSize2 = getDefaultSize(this.f, i2);
            if (this.e <= 0 || this.f <= 0) {
                i = defaultSize;
            } else {
                defaultSize = MeasureSpec.getMode(i);
                i = MeasureSpec.getSize(i);
                defaultSize2 = MeasureSpec.getMode(i2);
                i2 = MeasureSpec.getSize(i2);
                if (defaultSize != 1073741824 || defaultSize2 != 1073741824) {
                    if (defaultSize == 1073741824) {
                        defaultSize = (this.f * i) / this.e;
                        if (defaultSize2 != Integer.MIN_VALUE || defaultSize <= i2) {
                            i2 = defaultSize;
                        }
                    } else {
                        if (defaultSize2 == 1073741824) {
                            defaultSize2 = (this.e * i2) / this.f;
                            if (defaultSize == Integer.MIN_VALUE && defaultSize2 > i) {
                            }
                        } else {
                            int i3 = this.e;
                            int i4 = this.f;
                            if (defaultSize2 != Integer.MIN_VALUE || i4 <= i2) {
                                defaultSize2 = i3;
                                i2 = i4;
                            } else {
                                defaultSize2 = (this.e * i2) / this.f;
                            }
                            if (defaultSize == Integer.MIN_VALUE && r1 > i) {
                                defaultSize2 = (this.f * i) / this.e;
                            }
                        }
                        i = defaultSize2;
                    }
                    setMeasuredDimension(i, i2);
                } else if (this.e * i2 < this.f * i) {
                    defaultSize2 = (this.f * i) / this.e;
                } else {
                    if (this.e * i2 > this.f * i) {
                        i = (this.e * i2) / this.f;
                    }
                    setMeasuredDimension(i, i2);
                }
            }
            i2 = defaultSize2;
            setMeasuredDimension(i, i2);
        } catch (Exception e) {
            new StringBuilder("SDK encountered unexpected error in handling the onMeasure event; ").append(e.getMessage());
        }
    }

    /* Access modifiers changed, original: 0000 */
    @Nullable
    public NativeVideoController getMediaController() {
        return this.v;
    }

    /* Access modifiers changed, original: final */
    public final boolean b() {
        return (this.c == null || this.c.a == -1 || this.c.a == 0 || this.c.a == 1) ? false : true;
    }

    public void setIsLockScreen(boolean z) {
        this.u = z;
    }

    @TargetApi(20)
    public void start() {
        PowerManager powerManager = (PowerManager) getContext().getSystemService("power");
        boolean inKeyguardRestrictedInputMode = ((KeyguardManager) getContext().getSystemService("keyguard")).inKeyguardRestrictedInputMode();
        boolean isScreenOn;
        if (VERSION.SDK_INT < 20) {
            isScreenOn = powerManager.isScreenOn();
        } else {
            isScreenOn = powerManager.isInteractive();
        }
        boolean b = b();
        be beVar = (be) getTag();
        int i = (beVar == null || ((Boolean) beVar.v.get("shouldAutoPlay")).booleanValue()) ? 1 : 0;
        if (b && i == 0) {
            a(8, 0);
        }
        if (b && isScreenOn && !this.c.isPlaying() && i != 0 && (this.u || !inKeyguardRestrictedInputMode)) {
            int intValue = (beVar == null || ((Boolean) beVar.v.get("didCompleteQ4")).booleanValue()) ? 0 : ((Integer) beVar.v.get("seekPosition")).intValue();
            d();
            a(intValue);
            this.c.start();
            this.c.a = 3;
            a(8, 8);
            if (beVar != null) {
                beVar.v.put("didCompleteQ4", Boolean.valueOf(false));
                if (beVar.a()) {
                    e();
                }
                if (((Boolean) beVar.v.get("didPause")).booleanValue()) {
                    getPlaybackEventListener().a(3);
                    beVar.v.put("didPause", Boolean.valueOf(false));
                } else {
                    getPlaybackEventListener().a(1);
                }
                if (!(this.g == null || this.g.hasMessages(1))) {
                    this.g.sendEmptyMessage(1);
                }
            }
            if (this.v != null) {
                this.v.a();
            }
        }
        if (this.c != null) {
            this.c.b = 3;
        }
    }

    public void pause() {
        boolean z = b() && this.c.isPlaying();
        if (z) {
            this.c.pause();
            this.c.a = 4;
            if (getTag() != null) {
                be beVar = (be) getTag();
                beVar.v.put("didPause", Boolean.valueOf(true));
                beVar.v.put("seekPosition", Integer.valueOf(getCurrentPosition()));
            }
            getPlaybackEventListener().a(2);
        }
        if (this.c != null) {
            this.c.b = 4;
        }
        this.i = false;
    }

    public int getDuration() {
        return b() ? this.c.getDuration() : -1;
    }

    public int getCurrentPosition() {
        return b() ? this.c.getCurrentPosition() : 0;
    }

    /* Access modifiers changed, original: final */
    public final void a(int i) {
        if (b()) {
            this.c.seekTo(i);
        }
    }

    public boolean isPlaying() {
        return b() && this.c.isPlaying();
    }

    public int getBufferPercentage() {
        return this.c != null ? this.w : 0;
    }

    public boolean canPause() {
        return this.x;
    }

    public boolean canSeekBackward() {
        return this.y;
    }

    public boolean canSeekForward() {
        return this.z;
    }

    public int getAudioSessionId() {
        if (this.d == 0) {
            MediaPlayer mediaPlayer = new MediaPlayer();
            this.d = mediaPlayer.getAudioSessionId();
            mediaPlayer.release();
        }
        return this.d;
    }

    public void setVideoPath(String str) {
        setVideoURI(Uri.parse(str));
    }

    public void setVideoURI(Uri uri) {
        setVideoURI(uri, null);
    }

    public void setVideoURI(Uri uri, Map<String, String> map) {
        this.a = uri;
        this.b = map;
        g();
        requestLayout();
        invalidate();
    }

    private void g() {
        if (this.a != null && this.n != null) {
            if (this.c == null) {
                av avVar;
                be beVar = (be) getTag();
                PlacementType placementType = PlacementType.PLACEMENT_TYPE_FULLSCREEN;
                if (beVar != null) {
                    placementType = (PlacementType) beVar.v.get("placementType");
                }
                if (PlacementType.PLACEMENT_TYPE_FULLSCREEN == placementType) {
                    avVar = new av();
                } else {
                    avVar = av.a();
                }
                this.c = avVar;
                if (this.d != 0) {
                    this.c.setAudioSessionId(this.d);
                } else {
                    this.d = this.c.getAudioSessionId();
                }
                try {
                    this.c.setDataSource(getContext().getApplicationContext(), this.a, this.b);
                } catch (IOException unused) {
                    this.c.a = -1;
                    this.c.b = -1;
                    return;
                }
            }
            try {
                be beVar2 = (be) getTag();
                this.c.setOnPreparedListener(this.k);
                this.c.setOnVideoSizeChangedListener(this.j);
                this.c.setOnCompletionListener(this.A);
                this.c.setOnErrorListener(this.D);
                this.c.setOnInfoListener(this.B);
                this.c.setOnBufferingUpdateListener(this.C);
                this.c.setSurface(this.n);
                if (VERSION.SDK_INT >= 26) {
                    this.c.setAudioAttributes(new Builder().setUsage(1).setContentType(2).setLegacyStreamType(3).build());
                } else {
                    this.c.setAudioStreamType(3);
                }
                this.c.prepareAsync();
                this.w = 0;
                this.c.a = 1;
                h();
                if (beVar2 != null) {
                    if (((Boolean) beVar2.v.get("shouldAutoPlay")).booleanValue()) {
                        this.c.b = 3;
                    }
                    if (((Boolean) beVar2.v.get("didCompleteQ4")).booleanValue()) {
                        a(8, 0);
                        return;
                    }
                }
                a(0, 0);
            } catch (Exception e) {
                this.c.a = -1;
                this.c.b = -1;
                this.D.onError(this.c, 1, 0);
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            }
        }
    }

    /* Access modifiers changed, original: final */
    public final void c() {
        if (this.c != null) {
            if (this.g != null) {
                this.g.removeMessages(1);
            }
            if (getTag() != null) {
                ((be) getTag()).v.put("seekPosition", Integer.valueOf(getCurrentPosition()));
            }
            this.c.a = 0;
            this.c.b = 0;
            this.c.reset();
            this.c.setOnPreparedListener(null);
            this.c.setOnVideoSizeChangedListener(null);
            this.c.setOnCompletionListener(null);
            this.c.setOnErrorListener(null);
            this.c.setOnInfoListener(null);
            this.c.setOnBufferingUpdateListener(null);
            if (getTag() != null) {
                if (PlacementType.PLACEMENT_TYPE_INLINE == ((be) getTag()).v.get("placementType")) {
                    this.c.b();
                }
            } else {
                this.c.b();
            }
            AudioManager audioManager = (AudioManager) getContext().getSystemService("audio");
            if (audioManager != null) {
                audioManager.abandonAudioFocus(null);
            }
            this.c = null;
        }
    }

    public final void d() {
        if (this.c != null) {
            this.o = 0;
            this.c.setVolume(0.0f, 0.0f);
            if (getTag() != null) {
                ((be) getTag()).v.put("currentMediaVolume", Integer.valueOf(0));
            }
        }
    }

    public final void e() {
        if (this.c != null) {
            this.o = 1;
            this.c.setVolume(1.0f, 1.0f);
            if (getTag() != null) {
                ((be) getTag()).v.put("currentMediaVolume", Integer.valueOf(15));
            }
        }
    }

    public int getVolume() {
        return b() ? this.o : -1;
    }

    public int getVideoVolume() {
        return isPlaying() ? this.o : -1;
    }

    public int getLastVolume() {
        return this.p;
    }

    public void setLastVolume(int i) {
        this.p = i;
    }

    private void h() {
        if (this.c != null && this.v != null) {
            this.v.setMediaPlayer(this);
            this.v.setEnabled(b());
            this.v.a();
        }
    }

    /* Access modifiers changed, original: final */
    public final void a(int i, int i2) {
        if (this.c != null) {
            ProgressBar progressBar = ((NativeVideoWrapper) getParent()).getProgressBar();
            ImageView poster = ((NativeVideoWrapper) getParent()).getPoster();
            progressBar.setVisibility(i);
            poster.setVisibility(i2);
        }
    }

    public void setMediaController(NativeVideoController nativeVideoController) {
        if (nativeVideoController != null) {
            this.v = nativeVideoController;
            h();
        }
    }

    public c getQuartileCompletedListener() {
        return this.r;
    }

    public void setQuartileCompletedListener(c cVar) {
        this.r = cVar;
    }

    public b getPlaybackEventListener() {
        return this.s;
    }

    public void setPlaybackEventListener(b bVar) {
        this.s = bVar;
    }

    public void setMediaErrorListener(a aVar) {
        this.t = aVar;
    }

    static /* synthetic */ void f(NativeVideoView nativeVideoView) {
        if (nativeVideoView.c != null) {
            nativeVideoView.c.a = 5;
            nativeVideoView.c.b = 5;
        }
        if (nativeVideoView.v != null) {
            nativeVideoView.v.b();
        }
        if (nativeVideoView.g != null) {
            nativeVideoView.g.removeMessages(1);
        }
        if (nativeVideoView.getTag() != null) {
            be beVar = (be) nativeVideoView.getTag();
            if (!((Boolean) beVar.v.get("didCompleteQ4")).booleanValue()) {
                beVar.v.put("didCompleteQ4", Boolean.valueOf(true));
                if (nativeVideoView.getQuartileCompletedListener() != null) {
                    nativeVideoView.getQuartileCompletedListener().a(3);
                }
            }
            beVar.v.put("didSignalVideoCompleted", Boolean.valueOf(true));
            if (beVar != null) {
                beVar.v.put("didCompleteQ1", Boolean.valueOf(false));
                beVar.v.put("didCompleteQ2", Boolean.valueOf(false));
                beVar.v.put("didCompleteQ3", Boolean.valueOf(false));
                beVar.v.put("didPause", Boolean.valueOf(false));
                beVar.v.put("didStartPlaying", Boolean.valueOf(false));
                beVar.v.put("didQ4Fire", Boolean.valueOf(false));
            }
            if (beVar.C) {
                nativeVideoView.start();
            } else if (((Boolean) beVar.v.get("isFullScreen")).booleanValue()) {
                nativeVideoView.a(8, 0);
            }
        }
    }

    static /* synthetic */ void h(NativeVideoView nativeVideoView) {
        try {
            if (nativeVideoView.a != null) {
                String uri = nativeVideoView.a.toString();
                com.inmobi.ads.cache.d.a();
                com.inmobi.commons.core.d.b a = com.inmobi.commons.core.d.b.a();
                List a2 = a.a("asset", com.inmobi.ads.cache.d.a, "disk_uri=? ", new String[]{uri}, null, null, "created_ts DESC ", "1");
                a.b();
                com.inmobi.ads.cache.a a3 = a2.isEmpty() ? null : com.inmobi.ads.cache.d.a((ContentValues) a2.get(0));
                com.inmobi.ads.cache.a.a aVar = new com.inmobi.ads.cache.a.a();
                if (a3 != null) {
                    a3 = aVar.a(a3.d, 0, 0).a();
                    com.inmobi.ads.cache.d.a();
                    com.inmobi.ads.cache.d.b(a3);
                }
            }
        } catch (Exception unused) {
        }
    }
}
