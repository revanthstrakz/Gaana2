package com.exoplayer2.ui;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnLayoutChangeListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.constants.Constants;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaanavideo.d;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ControlDispatcher;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer.EventListener;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.PlaybackPreparer;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Player.DefaultEventListener;
import com.google.android.exoplayer2.Player.TextComponent;
import com.google.android.exoplayer2.Player.VideoComponent;
import com.google.android.exoplayer2.RenderersFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.drm.DefaultDrmSessionManager;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.drm.FrameworkMediaCrypto;
import com.google.android.exoplayer2.drm.FrameworkMediaDrm;
import com.google.android.exoplayer2.drm.HttpMediaDrmCallback;
import com.google.android.exoplayer2.drm.MediaDrmCallback;
import com.google.android.exoplayer2.drm.UnsupportedDrmException;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.mediacodec.MediaCodecRenderer.DecoderInitializationException;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.Metadata.Entry;
import com.google.android.exoplayer2.metadata.id3.ApicFrame;
import com.google.android.exoplayer2.source.BehindLiveWindowException;
import com.google.android.exoplayer2.source.ConcatenatingMediaSource;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.TextOutput;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.MappingTrackSelector.MappedTrackInfo;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout.AspectRatioListener;
import com.google.android.exoplayer2.ui.SubtitleView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSource.Factory;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.FileDataSource;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.upstream.cache.CacheDataSink;
import com.google.android.exoplayer2.upstream.cache.CacheDataSource;
import com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor;
import com.google.android.exoplayer2.upstream.cache.SimpleCache;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ErrorMessageProvider;
import com.google.android.exoplayer2.video.VideoListener;
import com.google.android.exoplayer2.video.VideoListener$$CC;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.google.gson.internal.LinkedTreeMap;
import com.i.e.b;
import com.services.l.ay;
import com.utilities.Util;
import java.io.File;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;

public class VideoPlayerAutoPlayView extends FrameLayout {
    private static final DefaultBandwidthMeter D = new DefaultBandwidthMeter();
    private static final CookieManager E = new CookieManager();
    private static final d z = new d("media_cache_autoplay");
    private String A;
    private int B;
    private boolean C;
    private Factory F;
    private DefaultTrackSelector G;
    private TrackGroupArray H;
    private boolean I;
    private int J;
    private long K;
    private boolean L;
    private boolean M;
    private Handler N;
    private Context O;
    private String P;
    private boolean Q;
    private boolean R;
    private ay S;
    private Callable<Integer> T;
    private Callable<Integer> U;
    private Activity V;
    private int W;
    EventListener a;
    private boolean aa;
    private boolean ab;
    private File ac;
    private final AspectRatioFrameLayout b;
    private final View c;
    private final View d;
    private final ImageView e;
    private final SubtitleView f;
    @Nullable
    private final View g;
    @Nullable
    private final TextView h;
    private final VideoPlayerControlView i;
    private final a j;
    private final FrameLayout k;
    private SimpleExoPlayer l;
    private boolean m;
    private boolean n;
    private Bitmap o;
    private boolean p;
    private boolean q;
    @Nullable
    private ErrorMessageProvider<? super ExoPlaybackException> r;
    @Nullable
    private CharSequence s;
    private int t;
    private boolean u;
    private boolean v;
    private boolean w;
    private int x;
    private SimpleCache y;

    private final class a extends DefaultEventListener implements OnLayoutChangeListener, TextOutput, VideoListener {
        public void onSurfaceSizeChanged(int i, int i2) {
            VideoListener$$CC.onSurfaceSizeChanged(this, i, i2);
        }

        private a() {
        }

        /* synthetic */ a(VideoPlayerAutoPlayView videoPlayerAutoPlayView, AnonymousClass1 anonymousClass1) {
            this();
        }

        public void onCues(List<Cue> list) {
            if (VideoPlayerAutoPlayView.this.f != null) {
                VideoPlayerAutoPlayView.this.f.onCues(list);
            }
        }

        public void onVideoSizeChanged(int i, int i2, int i3, float f) {
            if (VideoPlayerAutoPlayView.this.b != null) {
                float f2 = (i2 == 0 || i == 0) ? 1.0f : (((float) i) * f) / ((float) i2);
                if (VideoPlayerAutoPlayView.this.d instanceof TextureView) {
                    if (i3 == 90 || i3 == 270) {
                        f2 = 1.0f / f2;
                    }
                    if (VideoPlayerAutoPlayView.this.x != 0) {
                        VideoPlayerAutoPlayView.this.d.removeOnLayoutChangeListener(this);
                    }
                    VideoPlayerAutoPlayView.this.x = i3;
                    if (VideoPlayerAutoPlayView.this.x != 0) {
                        VideoPlayerAutoPlayView.this.d.addOnLayoutChangeListener(this);
                    }
                    VideoPlayerAutoPlayView.b((TextureView) VideoPlayerAutoPlayView.this.d, VideoPlayerAutoPlayView.this.x);
                }
                VideoPlayerAutoPlayView.this.b.setAspectRatio(f2);
            }
        }

        public void onRenderedFirstFrame() {
            if (VideoPlayerAutoPlayView.this.c != null) {
                VideoPlayerAutoPlayView.this.c.setVisibility(4);
            }
        }

        public void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
            VideoPlayerAutoPlayView.this.c(false);
        }

        public void onPlayerStateChanged(boolean z, int i) {
            VideoPlayerAutoPlayView.this.r();
            VideoPlayerAutoPlayView.this.s();
            if (VideoPlayerAutoPlayView.this.o() && VideoPlayerAutoPlayView.this.v) {
                VideoPlayerAutoPlayView.this.b();
            } else {
                VideoPlayerAutoPlayView.this.a(false);
            }
        }

        public void onPositionDiscontinuity(int i) {
            if (VideoPlayerAutoPlayView.this.o() && VideoPlayerAutoPlayView.this.v) {
                VideoPlayerAutoPlayView.this.b();
            }
        }

        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            VideoPlayerAutoPlayView.b((TextureView) view, VideoPlayerAutoPlayView.this.x);
        }
    }

    @SuppressLint({"InlinedApi"})
    private boolean a(int i) {
        return i == 19 || i == 270 || i == 22 || i == 271 || i == 20 || i == 269 || i == 21 || i == 268 || i == 23;
    }

    private void w() {
    }

    private void x() {
    }

    static {
        E.setCookiePolicy(CookiePolicy.ACCEPT_ORIGINAL_SERVER);
    }

    public VideoPlayerAutoPlayView(Context context) {
        this(context, null);
    }

    public VideoPlayerAutoPlayView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VideoPlayerAutoPlayView(Context context, AttributeSet attributeSet, int i) {
        Context context2 = context;
        AttributeSet attributeSet2 = attributeSet;
        super(context, attributeSet, i);
        this.C = true;
        this.L = true;
        this.M = true;
        this.Q = false;
        this.R = false;
        this.T = null;
        this.U = null;
        this.W = 0;
        this.aa = false;
        this.ab = true;
        this.a = new EventListener() {
            public void onLoadingChanged(boolean z) {
            }

            public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
            }

            public void onRepeatModeChanged(int i) {
            }

            public void onSeekProcessed() {
            }

            public void onShuffleModeEnabledChanged(boolean z) {
            }

            public void onTimelineChanged(Timeline timeline, Object obj, int i) {
            }

            public void onPlayerStateChanged(boolean z, int i) {
                if (i == 4) {
                    VideoPlayerAutoPlayView.this.x();
                } else if (i == 2) {
                    if (VideoPlayerAutoPlayView.this.l != null && VideoPlayerAutoPlayView.this.l.getPlayWhenReady()) {
                        VideoPlayerAutoPlayView.this.S.videoStateChanged(2);
                    }
                } else if (i == 3) {
                    try {
                        if (VideoPlayerAutoPlayView.this.l != null && VideoPlayerAutoPlayView.this.l.getPlayWhenReady()) {
                            VideoPlayerAutoPlayView.this.S.videoStateChanged(1);
                        }
                    } catch (Exception e) {
                        ThrowableExtension.printStackTrace(e);
                    }
                }
                VideoPlayerAutoPlayView.this.w();
            }

            public void onPlayerError(ExoPlaybackException exoPlaybackException) {
                if (exoPlaybackException.type == 1) {
                    Exception rendererException = exoPlaybackException.getRendererException();
                    if (rendererException instanceof DecoderInitializationException) {
                        DecoderInitializationException decoderInitializationException = (DecoderInitializationException) rendererException;
                    }
                    VideoPlayerAutoPlayView.this.i();
                } else if (VideoPlayerAutoPlayView.this.C) {
                    a();
                    return;
                }
                VideoPlayerAutoPlayView.this.I = true;
                if (VideoPlayerAutoPlayView.b(exoPlaybackException)) {
                    VideoPlayerAutoPlayView.this.u();
                    VideoPlayerAutoPlayView.this.h();
                } else if (VideoPlayerAutoPlayView.this.l != null) {
                    VideoPlayerAutoPlayView.this.t();
                    VideoPlayerAutoPlayView.this.w();
                    VideoPlayerAutoPlayView.this.x();
                }
            }

            private void a() {
                if (TextUtils.isEmpty(VideoPlayerAutoPlayView.this.P)) {
                    String str;
                    switch (VideoPlayerAutoPlayView.this.B) {
                        case 0:
                            str = "youtube";
                            break;
                        case 1:
                            str = "vert";
                            break;
                        case 2:
                            str = "horz";
                            break;
                        default:
                            str = "youtube";
                            break;
                    }
                    new com.player_framework.d(VideoPlayerAutoPlayView.this.O).a(VideoPlayerAutoPlayView.this.A, str, new b() {
                        public void onDataRetrieved(Object obj, int i, boolean z) {
                            VideoPlayerAutoPlayView.this.C = false;
                            if (obj instanceof LinkedTreeMap) {
                                VideoPlayerAutoPlayView.this.i();
                                if (VideoPlayerAutoPlayView.z != null) {
                                    VideoPlayerAutoPlayView.z.a(VideoPlayerAutoPlayView.this.A);
                                }
                                String str = (String) ((LinkedTreeMap) obj).get("data");
                                if (str.contains("http") || str.contains("https")) {
                                    VideoPlayerAutoPlayView.this.setSource(str);
                                    VideoPlayerAutoPlayView.this.g();
                                    return;
                                }
                                str = Util.k(str);
                                if (str.contains("http") || str.contains("https")) {
                                    VideoPlayerAutoPlayView.this.setSource(str);
                                    VideoPlayerAutoPlayView.this.g();
                                }
                            }
                        }
                    });
                    return;
                }
                VideoPlayerAutoPlayView.this.C = false;
                if (VideoPlayerAutoPlayView.this.P.contains("http") || VideoPlayerAutoPlayView.this.P.contains("https")) {
                    VideoPlayerAutoPlayView.this.setSource(VideoPlayerAutoPlayView.this.P);
                    VideoPlayerAutoPlayView.this.g();
                    return;
                }
                VideoPlayerAutoPlayView.this.P = Util.k(VideoPlayerAutoPlayView.this.P);
                if (VideoPlayerAutoPlayView.this.P.contains("http") || VideoPlayerAutoPlayView.this.P.contains("https")) {
                    VideoPlayerAutoPlayView.this.setSource(VideoPlayerAutoPlayView.this.P);
                    VideoPlayerAutoPlayView.this.g();
                }
            }

            public void onPositionDiscontinuity(int i) {
                if (VideoPlayerAutoPlayView.this.I) {
                    VideoPlayerAutoPlayView.this.t();
                }
            }

            public void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
                VideoPlayerAutoPlayView.this.w();
                if (trackGroupArray != VideoPlayerAutoPlayView.this.H) {
                    MappedTrackInfo currentMappedTrackInfo = VideoPlayerAutoPlayView.this.G.getCurrentMappedTrackInfo();
                    if (currentMappedTrackInfo != null) {
                        currentMappedTrackInfo.getTrackTypeRendererSupport(2);
                        currentMappedTrackInfo.getTrackTypeRendererSupport(1);
                    }
                    VideoPlayerAutoPlayView.this.H = trackGroupArray;
                }
            }
        };
        setup(context);
        if (isInEditMode()) {
            this.b = null;
            this.c = null;
            this.d = null;
            this.e = null;
            this.f = null;
            this.g = null;
            this.h = null;
            this.i = null;
            this.j = null;
            this.k = null;
            ImageView imageView = new ImageView(context2);
            if (com.google.android.exoplayer2.util.Util.SDK_INT >= 23) {
                a(getResources(), imageView);
            } else {
                b(getResources(), imageView);
            }
            addView(imageView);
            return;
        }
        int color;
        boolean z;
        int resourceId;
        boolean z2;
        int i2;
        boolean z3;
        int i3;
        boolean z4;
        boolean z5;
        boolean z6;
        boolean z7;
        int i4 = R.layout.exo_player_view;
        int i5 = 3;
        boolean hasValue;
        if (attributeSet2 != null) {
            TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet2, com.google.android.exoplayer2.ui.R.styleable.PlayerView, 0, 0);
            try {
                hasValue = obtainStyledAttributes.hasValue(14);
                color = obtainStyledAttributes.getColor(14, 0);
                i4 = obtainStyledAttributes.getResourceId(7, R.layout.exo_player_view);
                z = obtainStyledAttributes.getBoolean(16, true);
                resourceId = obtainStyledAttributes.getResourceId(2, 0);
                z2 = obtainStyledAttributes.getBoolean(17, false);
                i2 = obtainStyledAttributes.getInt(15, 1);
                i5 = obtainStyledAttributes.getInt(9, 3);
                int i6 = obtainStyledAttributes.getInt(13, 5000);
                z3 = obtainStyledAttributes.getBoolean(5, true);
                i3 = i6;
                z4 = obtainStyledAttributes.getBoolean(0, true);
                boolean z8 = obtainStyledAttributes.getBoolean(11, false);
                this.q = obtainStyledAttributes.getBoolean(6, this.q);
                z5 = obtainStyledAttributes.getBoolean(4, true);
                obtainStyledAttributes.recycle();
                z6 = z5;
                z5 = z8;
                boolean z9 = z4;
                z4 = z2;
                z2 = z9;
            } catch (Throwable th) {
                Throwable th2 = th;
                obtainStyledAttributes.recycle();
            }
        } else {
            i2 = true;
            z2 = i2;
            z6 = z2;
            z = z6;
            i3 = 5000;
            z5 = false;
            color = 0;
            hasValue = false;
            resourceId = 0;
            z4 = false;
            z3 = z;
        }
        LayoutInflater.from(context).inflate(i4, this);
        this.j = new a(this, null);
        setDescendantFocusability(262144);
        this.b = (AspectRatioFrameLayout) findViewById(R.id.exo_content_frame);
        if (this.b != null) {
            a(this.b, i5);
        }
        this.c = findViewById(R.id.exo_shutter);
        if (this.c != null && hasValue) {
            this.c.setBackgroundColor(color);
        }
        if (this.b == null || i2 == 0) {
            this.d = null;
        } else {
            LayoutParams layoutParams = new LayoutParams(-1, -1);
            if (i2 == 2) {
                this.d = new TextureView(context2);
            } else {
                this.d = new SurfaceView(context2);
                ((SurfaceView) this.d).getHolder().addCallback(new Callback() {
                    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
                    }

                    public void surfaceCreated(SurfaceHolder surfaceHolder) {
                        if (VideoPlayerAutoPlayView.this.O instanceof GaanaActivity) {
                            VideoPlayerAutoPlayView.this.C = true;
                            VideoPlayerAutoPlayView.this.g();
                        }
                    }

                    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                        VideoPlayerAutoPlayView.this.m();
                    }
                });
            }
            this.d.setLayoutParams(layoutParams);
            this.b.addView(this.d, 0);
        }
        this.k = (FrameLayout) findViewById(R.id.exo_overlay);
        this.e = (ImageView) findViewById(R.id.exo_artwork);
        boolean z10 = z && this.e != null;
        this.n = z10;
        if (resourceId != 0) {
            this.o = BitmapFactory.decodeResource(context.getResources(), resourceId);
        }
        this.f = (SubtitleView) findViewById(R.id.exo_subtitles);
        if (this.f != null) {
            this.f.setUserDefaultStyle();
            this.f.setUserDefaultTextSize();
        }
        this.g = findViewById(R.id.exo_buffering);
        if (this.g != null) {
            this.g.setVisibility(8);
        }
        this.p = z5;
        this.h = (TextView) findViewById(R.id.exo_error_message);
        if (this.h != null) {
            this.h.setVisibility(8);
        }
        VideoPlayerControlView videoPlayerControlView = (VideoPlayerControlView) findViewById(R.id.exo_controller);
        View findViewById = findViewById(R.id.exo_controller_placeholder);
        if (videoPlayerControlView != null) {
            this.i = videoPlayerControlView;
            z7 = false;
        } else if (findViewById != null) {
            z7 = false;
            this.i = new VideoPlayerControlView(context2, null, 0, attributeSet2);
            this.i.setLayoutParams(findViewById.getLayoutParams());
            ViewGroup viewGroup = (ViewGroup) findViewById.getParent();
            int indexOfChild = viewGroup.indexOfChild(findViewById);
            viewGroup.removeView(findViewById);
            viewGroup.addView(this.i, indexOfChild);
        } else {
            z7 = false;
            this.i = null;
        }
        this.t = this.i != null ? i3 : z7;
        this.w = z3;
        this.u = z2;
        this.v = z6;
        if (z4 && this.i != null) {
            z7 = true;
        }
        this.m = z7;
        b();
    }

    public void setShareUrl(String str) {
        this.i.setShareUrl(str);
    }

    /* Access modifiers changed, original: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        m();
    }

    /* Access modifiers changed, original: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        g();
    }

    private void m() {
        if (this.l != null) {
            i();
        }
    }

    public Player getPlayer() {
        return this.l;
    }

    public void setPlayer(Player player) {
        if (this.l != player) {
            VideoComponent videoComponent;
            TextComponent textComponent;
            if (this.l != null) {
                this.l.removeListener(this.j);
                videoComponent = this.l.getVideoComponent();
                if (videoComponent != null) {
                    videoComponent.removeVideoListener(this.j);
                    if (this.d instanceof TextureView) {
                        videoComponent.clearVideoTextureView((TextureView) this.d);
                    } else if (this.d instanceof SurfaceView) {
                        videoComponent.clearVideoSurfaceView((SurfaceView) this.d);
                    }
                }
                textComponent = this.l.getTextComponent();
                if (textComponent != null) {
                    textComponent.removeTextOutput(this.j);
                }
            }
            this.l = (SimpleExoPlayer) player;
            if (this.m) {
                this.i.setPlayer(player);
            }
            if (this.f != null) {
                this.f.setCues(null);
            }
            r();
            s();
            c(true);
            if (player != null) {
                videoComponent = player.getVideoComponent();
                if (videoComponent != null) {
                    if (this.d instanceof TextureView) {
                        videoComponent.setVideoTextureView((TextureView) this.d);
                    } else if (this.d instanceof SurfaceView) {
                        videoComponent.setVideoSurfaceView((SurfaceView) this.d);
                    }
                    videoComponent.addVideoListener(this.j);
                }
                textComponent = player.getTextComponent();
                if (textComponent != null) {
                    textComponent.addTextOutput(this.j);
                }
                player.addListener(this.j);
                a(false);
            } else {
                b();
            }
        }
    }

    public void setVisibility(int i) {
        super.setVisibility(i);
        if (this.d instanceof SurfaceView) {
            this.d.setVisibility(i);
        }
    }

    public void setResizeMode(int i) {
        Assertions.checkState(this.b != null);
        this.b.setResizeMode(i);
    }

    public int getResizeMode() {
        Assertions.checkState(this.b != null);
        return this.b.getResizeMode();
    }

    public boolean getUseArtwork() {
        return this.n;
    }

    public void setUseArtwork(boolean z) {
        boolean z2 = (z && this.e == null) ? false : true;
        Assertions.checkState(z2);
        if (this.n != z) {
            this.n = z;
            c(false);
        }
    }

    public Bitmap getDefaultArtwork() {
        return this.o;
    }

    public void setDefaultArtwork(Bitmap bitmap) {
        if (this.o != bitmap) {
            this.o = bitmap;
            c(false);
        }
    }

    public boolean getUseController() {
        return this.m;
    }

    public void setUseController(boolean z) {
        boolean z2 = (z && this.i == null) ? false : true;
        Assertions.checkState(z2);
        if (this.m != z) {
            this.m = z;
            if (z) {
                this.i.setPlayer(this.l);
            } else if (this.i != null) {
                this.i.b();
                this.i.setPlayer(null);
            }
        }
    }

    public void setShutterBackgroundColor(int i) {
        if (this.c != null) {
            this.c.setBackgroundColor(i);
        }
    }

    public void setKeepContentOnPlayerReset(boolean z) {
        if (this.q != z) {
            this.q = z;
            c(false);
        }
    }

    public void setShowBuffering(boolean z) {
        if (this.p != z) {
            this.p = z;
            r();
        }
    }

    public void setErrorMessageProvider(@Nullable ErrorMessageProvider<? super ExoPlaybackException> errorMessageProvider) {
        if (this.r != errorMessageProvider) {
            this.r = errorMessageProvider;
            s();
        }
    }

    public void setCustomErrorMessage(@Nullable CharSequence charSequence) {
        Assertions.checkState(this.h != null);
        this.s = charSequence;
        s();
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (this.l == null || !this.l.isPlayingAd()) {
            boolean z = false;
            boolean z2 = a(keyEvent.getKeyCode()) && this.m && !this.i.c();
            a(true);
            if (z2 || a(keyEvent) || super.dispatchKeyEvent(keyEvent)) {
                z = true;
            }
            return z;
        }
        this.k.requestFocus();
        return super.dispatchKeyEvent(keyEvent);
    }

    public boolean a(KeyEvent keyEvent) {
        return this.m && this.i.a(keyEvent);
    }

    public void a() {
        b(n());
    }

    public void b() {
        if (this.i != null) {
            this.i.b();
        }
    }

    public int getControllerShowTimeoutMs() {
        return this.t;
    }

    public void setControllerShowTimeoutMs(int i) {
        Assertions.checkState(this.i != null);
        this.t = i;
        if (this.i.c()) {
            a();
        }
    }

    public boolean getControllerHideOnTouch() {
        return this.w;
    }

    public void setControllerHideOnTouch(boolean z) {
        Assertions.checkState(this.i != null);
        this.w = z;
    }

    public boolean getControllerAutoShow() {
        return this.u;
    }

    public void setControllerAutoShow(boolean z) {
        this.u = z;
    }

    public void setControllerHideDuringAds(boolean z) {
        this.v = z;
    }

    public void setControllerVisibilityListener(VideoPlayerControlView.b bVar) {
        Assertions.checkState(this.i != null);
        this.i.setVisibilityListener(bVar);
    }

    public void setPlaybackPreparer(@Nullable PlaybackPreparer playbackPreparer) {
        Assertions.checkState(this.i != null);
        this.i.setPlaybackPreparer(playbackPreparer);
    }

    public void setControlDispatcher(@Nullable ControlDispatcher controlDispatcher) {
        Assertions.checkState(this.i != null);
        this.i.setControlDispatcher(controlDispatcher);
    }

    public void setRewindIncrementMs(int i) {
        Assertions.checkState(this.i != null);
        this.i.setRewindIncrementMs(i);
    }

    public void setFastForwardIncrementMs(int i) {
        Assertions.checkState(this.i != null);
        this.i.setFastForwardIncrementMs(i);
    }

    public void setRepeatToggleModes(int i) {
        Assertions.checkState(this.i != null);
        this.i.setRepeatToggleModes(i);
    }

    public void setShowShuffleButton(boolean z) {
        Assertions.checkState(this.i != null);
        this.i.setShowShuffleButton(z);
    }

    public void setShowMultiWindowTimeBar(boolean z) {
        Assertions.checkState(this.i != null);
        this.i.setShowMultiWindowTimeBar(z);
    }

    public void setExtraAdGroupMarkers(@Nullable long[] jArr, @Nullable boolean[] zArr) {
        Assertions.checkState(this.i != null);
        this.i.setExtraAdGroupMarkers(jArr, zArr);
    }

    public void setAspectRatioListener(AspectRatioListener aspectRatioListener) {
        Assertions.checkState(this.b != null);
        this.b.setAspectRatioListener(aspectRatioListener);
    }

    public View getVideoSurfaceView() {
        return this.d;
    }

    public FrameLayout getOverlayFrameLayout() {
        return this.k;
    }

    public SubtitleView getSubtitleView() {
        return this.f;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.m || this.l == null || motionEvent.getActionMasked() != 0) {
            return false;
        }
        if (!this.i.c()) {
            a(true);
        } else if (this.w) {
            this.i.b();
        }
        return true;
    }

    public boolean onTrackballEvent(MotionEvent motionEvent) {
        if (!this.m || this.l == null) {
            return false;
        }
        a(true);
        return true;
    }

    private void a(boolean z) {
        if (!(o() && this.v) && this.m) {
            Object obj = (!this.i.c() || this.i.getShowTimeoutMs() > 0) ? null : 1;
            boolean n = n();
            if (z || obj != null || n) {
                b(n);
            }
        }
    }

    private boolean n() {
        boolean z = true;
        if (this.l == null) {
            return true;
        }
        int playbackState = this.l.getPlaybackState();
        if (!(this.u && (playbackState == 1 || playbackState == 4 || !this.l.getPlayWhenReady()))) {
            z = false;
        }
        return z;
    }

    private void b(boolean z) {
        if (this.m) {
            this.i.setShowTimeoutMs(z ? 0 : this.t);
            this.i.a();
        }
    }

    private boolean o() {
        return this.l != null && this.l.isPlayingAd() && this.l.getPlayWhenReady();
    }

    private void c(boolean z) {
        if (this.l == null || this.l.getCurrentTrackGroups().isEmpty()) {
            if (!this.q) {
                p();
                q();
            }
            return;
        }
        if (z && !this.q) {
            q();
        }
        TrackSelectionArray currentTrackSelections = this.l.getCurrentTrackSelections();
        int i = 0;
        while (i < currentTrackSelections.length) {
            if (this.l.getRendererType(i) != 2 || currentTrackSelections.get(i) == null) {
                i++;
            } else {
                p();
                return;
            }
        }
        q();
        if (this.n) {
            for (i = 0; i < currentTrackSelections.length; i++) {
                TrackSelection trackSelection = currentTrackSelections.get(i);
                if (trackSelection != null) {
                    int i2 = 0;
                    while (i2 < trackSelection.length()) {
                        Metadata metadata = trackSelection.getFormat(i2).metadata;
                        if (metadata == null || !a(metadata)) {
                            i2++;
                        } else {
                            return;
                        }
                    }
                    continue;
                }
            }
            if (a(this.o)) {
                return;
            }
        }
        p();
    }

    private boolean a(Metadata metadata) {
        for (int i = 0; i < metadata.length(); i++) {
            Entry entry = metadata.get(i);
            if (entry instanceof ApicFrame) {
                byte[] bArr = ((ApicFrame) entry).pictureData;
                return a(BitmapFactory.decodeByteArray(bArr, 0, bArr.length));
            }
        }
        return false;
    }

    private boolean a(Bitmap bitmap) {
        if (bitmap != null) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            if (width > 0 && height > 0) {
                if (this.b != null) {
                    this.b.setAspectRatio(((float) width) / ((float) height));
                }
                this.e.setImageBitmap(bitmap);
                this.e.setVisibility(0);
                return true;
            }
        }
        return false;
    }

    private void p() {
        if (this.e != null) {
            this.e.setImageResource(17170445);
            this.e.setVisibility(4);
        }
    }

    private void q() {
        if (this.c != null) {
            this.c.setVisibility(0);
        }
    }

    private void r() {
        if (this.g != null) {
            int i = 0;
            int i2 = (this.p && this.l != null && this.l.getPlaybackState() == 2 && this.l.getPlayWhenReady()) ? 1 : 0;
            View view = this.g;
            if (i2 == 0) {
                i = 8;
            }
            view.setVisibility(i);
        }
    }

    private void s() {
        if (this.h != null) {
            if (this.s != null) {
                this.h.setText(this.s);
                this.h.setVisibility(0);
                return;
            }
            Throwable th = null;
            if (!(this.l == null || this.l.getPlaybackState() != 1 || this.r == null)) {
                th = this.l.getPlaybackError();
            }
            if (th != null) {
                this.h.setText((CharSequence) this.r.getErrorMessage(th).second);
                this.h.setVisibility(0);
            } else {
                this.h.setVisibility(8);
            }
        }
    }

    @TargetApi(23)
    private static void a(Resources resources, ImageView imageView) {
        imageView.setImageDrawable(resources.getDrawable(R.drawable.exo_edit_mode_logo, null));
        imageView.setBackgroundColor(resources.getColor(R.color.exo_edit_mode_background_color, null));
    }

    private static void b(Resources resources, ImageView imageView) {
        imageView.setImageDrawable(resources.getDrawable(R.drawable.exo_edit_mode_logo));
        imageView.setBackgroundColor(resources.getColor(R.color.exo_edit_mode_background_color));
    }

    private static void a(AspectRatioFrameLayout aspectRatioFrameLayout, int i) {
        aspectRatioFrameLayout.setResizeMode(i);
    }

    private static void b(TextureView textureView, int i) {
        float width = (float) textureView.getWidth();
        float height = (float) textureView.getHeight();
        if (width == 0.0f || height == 0.0f || i == 0) {
            textureView.setTransform(null);
            return;
        }
        Matrix matrix = new Matrix();
        float f = width / 2.0f;
        float f2 = height / 2.0f;
        matrix.postRotate((float) i, f, f2);
        RectF rectF = new RectF(0.0f, 0.0f, width, height);
        RectF rectF2 = new RectF();
        matrix.mapRect(rectF2, rectF);
        matrix.postScale(width / rectF2.width(), height / rectF2.height(), f, f2);
        textureView.setTransform(matrix);
    }

    private void a(Context context) {
        if (this.y == null) {
            LeastRecentlyUsedCacheEvictor leastRecentlyUsedCacheEvictor = new LeastRecentlyUsedCacheEvictor(262144000);
            File file = ContextCompat.getExternalFilesDirs(context.getApplicationContext(), null)[0];
            String absolutePath = file.getAbsolutePath();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("media_cache_autoplay/");
            stringBuilder.append(this.A);
            this.ac = new File(absolutePath, stringBuilder.toString());
            this.ac.mkdirs();
            File file2 = new File(file.getAbsolutePath(), "media_cache");
            file2.mkdirs();
            Constants.ej.a(file2);
            try {
                this.y = new SimpleCache(this.ac, leastRecentlyUsedCacheEvictor);
                z.a(this.A, this.ac.getAbsolutePath());
            } catch (IllegalStateException e) {
                ThrowableExtension.printStackTrace(e);
                this.y = null;
            }
        }
    }

    public void setup(Context context) {
        this.O = context;
        this.L = true;
        u();
        this.N = new Handler(context.getMainLooper());
        if (CookieHandler.getDefault() != E) {
            CookieHandler.setDefault(E);
        }
    }

    public void setPaused(boolean z) {
        this.R = z;
    }

    public void setSource(String str) {
        if (str.contains("http") || str.contains("https")) {
            this.P = str;
        } else {
            this.P = Util.k(str);
        }
    }

    public void setVideoParams(String str, int i) {
        this.A = str;
        this.B = i;
    }

    public void set_act(Activity activity) {
        this.V = activity;
    }

    public void setVideoStateChangeListener(ay ayVar) {
        this.S = ayVar;
    }

    public boolean c() {
        return this.M;
    }

    public void d() {
        if (this.l != null) {
            this.l.setVolume(0.0f);
        }
    }

    public void setSurfaceReleased(boolean z) {
        this.aa = z;
        j();
    }

    public void e() {
        if (this.l != null) {
            this.M = false;
            this.l.setPlayWhenReady(false);
            this.S.videoStateChanged(0);
        }
    }

    public void f() {
        if (this.l != null) {
            this.M = true;
            this.l.setPlayWhenReady(true);
            d();
        }
    }

    public void setVideoFetchFirstTry(boolean z) {
        this.C = z;
    }

    public void g() {
        if (!TextUtils.isEmpty(this.A)) {
            this.F = d(true);
            if (this.l == null) {
                h();
            } else {
                f();
            }
        }
    }

    public void setVideoScalingMode(boolean z) {
        if (this.l == null) {
            this.ab = z;
        } else if (z) {
            this.l.setVideoScalingMode(2);
        } else {
            this.l.setVideoScalingMode(1);
        }
    }

    public void h() {
        Intent intent = new Intent();
        int i = this.l == null ? 1 : false;
        if (i == 0 && !this.I) {
            f();
        }
        if (i != 0) {
            DrmSessionManager a;
            if (this.S != null) {
                this.S.videoStateChanged(2);
            }
            UUID fromString = intent.hasExtra("drm_scheme_uuid") ? UUID.fromString(intent.getStringExtra("drm_scheme_uuid")) : null;
            if (fromString != null) {
                try {
                    a = a(fromString, intent.getStringExtra("drm_license_url"), intent.getStringArrayExtra("drm_key_request_properties"));
                } catch (UnsupportedDrmException unused) {
                    return;
                }
            }
            a = null;
            intent.getBooleanExtra("prefer_extension_decoders", false);
            RenderersFactory defaultRenderersFactory = new DefaultRenderersFactory(this.O, a, 2);
            this.G = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(D));
            this.H = null;
            SimpleExoPlayer newSimpleInstance = ExoPlayerFactory.newSimpleInstance(this.O, defaultRenderersFactory, this.G, new DefaultLoadControl());
            newSimpleInstance.addListener(this.a);
            setPlayer(newSimpleInstance);
            f();
        }
        if (i != 0 || this.I) {
            intent.getAction();
            if (!TextUtils.isEmpty(this.P)) {
                Uri[] uriArr = new Uri[]{Uri.parse(this.P)};
                String[] strArr = new String[]{intent.getStringExtra("extension")};
                if (!com.google.android.exoplayer2.util.Util.maybeRequestReadExternalStoragePermission((Activity) this.O, uriArr)) {
                    MediaSource[] mediaSourceArr = new MediaSource[uriArr.length];
                    for (int i2 = 0; i2 < uriArr.length; i2++) {
                        mediaSourceArr[i2] = a(uriArr[i2], strArr[i2]);
                    }
                    MediaSource concatenatingMediaSource = mediaSourceArr.length == 1 ? mediaSourceArr[0] : new ConcatenatingMediaSource(mediaSourceArr);
                    i = this.J != -1 ? 1 : 0;
                    if (i != 0) {
                        this.l.seekTo(this.J, this.K);
                    }
                    if (this.ab) {
                        this.l.setVideoScalingMode(2);
                    } else {
                        this.l.setVideoScalingMode(1);
                    }
                    d();
                    this.l.prepare(concatenatingMediaSource, i ^ 1, false);
                    this.I = false;
                }
            }
        }
    }

    private MediaSource a(Uri uri, String str) {
        int inferContentType;
        StringBuilder stringBuilder;
        if (TextUtils.isEmpty(str)) {
            inferContentType = com.google.android.exoplayer2.util.Util.inferContentType(uri);
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append(".");
            stringBuilder.append(str);
            inferContentType = com.google.android.exoplayer2.util.Util.inferContentType(stringBuilder.toString());
        }
        switch (inferContentType) {
            case 2:
                return new HlsMediaSource(uri, this.F, this.N, null);
            case 3:
                return new ExtractorMediaSource(uri, this.F, new DefaultExtractorsFactory(), this.N, null);
            default:
                stringBuilder = new StringBuilder();
                stringBuilder.append("Unsupported type: ");
                stringBuilder.append(inferContentType);
                throw new IllegalStateException(stringBuilder.toString());
        }
    }

    private DrmSessionManager<FrameworkMediaCrypto> a(UUID uuid, String str, String[] strArr) throws UnsupportedDrmException {
        if (com.google.android.exoplayer2.util.Util.SDK_INT < 18) {
            return null;
        }
        int i = 0;
        MediaDrmCallback httpMediaDrmCallback = new HttpMediaDrmCallback(str, e(false));
        if (strArr != null) {
            while (i < strArr.length - 1) {
                httpMediaDrmCallback.setKeyRequestProperty(strArr[i], strArr[i + 1]);
                i += 2;
            }
        }
        return new DefaultDrmSessionManager(uuid, FrameworkMediaDrm.newInstance(uuid), httpMediaDrmCallback, null, this.N, null);
    }

    public void i() {
        if (this.l != null) {
            this.L = this.l.getPlayWhenReady();
            t();
            this.l.stop(true);
            this.l.setVolume(0.0f);
            this.l.release();
            this.l = null;
            this.G = null;
        }
        j();
    }

    public void j() {
        if (this.y != null) {
            this.y.release();
            this.y = null;
        }
    }

    public void k() {
        if (Constants.dd != -1 && Constants.de != -1) {
            this.J = Constants.dd;
            this.K = Constants.de;
            Constants.dd = -1;
            Constants.de = -1;
        }
    }

    private void t() {
        this.J = this.l.getCurrentWindowIndex();
        this.K = this.l.isCurrentWindowSeekable() ? Math.max(0, this.l.getCurrentPosition()) : C.TIME_UNSET;
    }

    private void u() {
        this.J = -1;
        this.K = C.TIME_UNSET;
    }

    public int getResumeWindow() {
        if (this.l != null) {
            this.J = this.l.getCurrentWindowIndex();
        }
        return this.J;
    }

    public long getResumePosition() {
        if (this.l != null) {
            this.K = this.l.isCurrentWindowSeekable() ? Math.max(0, this.l.getCurrentPosition()) : C.TIME_UNSET;
        }
        return this.K;
    }

    private CacheDataSource v() {
        return new CacheDataSource(this.y, a(D).createDataSource(), new FileDataSource(), new CacheDataSink(this.y, 10485760), 3, new CacheDataSource.EventListener() {
            public void onCacheIgnored(int i) {
            }

            public void onCachedBytesRead(long j, long j2) {
            }
        });
    }

    private Factory d(boolean z) {
        a(this.O);
        if (this.y == null) {
            return e(z);
        }
        final CacheDataSource v = v();
        return new Factory() {
            public DataSource createDataSource() {
                return v;
            }
        };
    }

    private Factory a(DefaultBandwidthMeter defaultBandwidthMeter) {
        return new DefaultDataSourceFactory(this.O, (TransferListener) defaultBandwidthMeter, ((GaanaApplication) ((Activity) this.O).getApplication()).buildDataSourceFactory(defaultBandwidthMeter, true));
    }

    private HttpDataSource.Factory e(boolean z) {
        return ((GaanaApplication) ((Activity) this.O).getApplication()).buildHttpDataSourceFactory(z ? D : null, false);
    }

    public void setLooping(boolean z) {
        if (z) {
            this.l.setRepeatMode(1);
        } else {
            this.l.setRepeatMode(0);
        }
    }

    public String getVideoId() {
        return this.A;
    }

    public int getVideoType() {
        return this.B;
    }

    private void a(String str) {
        Toast.makeText(this.O.getApplicationContext(), str, 1).show();
    }

    private static boolean b(ExoPlaybackException exoPlaybackException) {
        if (exoPlaybackException.type != 0) {
            return false;
        }
        for (Throwable sourceException = exoPlaybackException.getSourceException(); sourceException != null; sourceException = sourceException.getCause()) {
            if (sourceException instanceof BehindLiveWindowException) {
                return true;
            }
        }
        return false;
    }
}
