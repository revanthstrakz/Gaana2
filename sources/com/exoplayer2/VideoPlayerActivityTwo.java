package com.exoplayer2;

import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest.Builder;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.constants.Constants;
import com.exoplayer2.ui.VideoPlayerControlView.b;
import com.exoplayer2.ui.VideoPlayerView;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.view.GaanaYourYearView.GAANA_VIDEO_SOURCE;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer.EventListener;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.PlaybackPreparer;
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
import com.google.android.exoplayer2.source.BehindLiveWindowException;
import com.google.android.exoplayer2.source.ConcatenatingMediaSource;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.MappingTrackSelector.MappedTrackInfo;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
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
import com.google.android.exoplayer2.util.Util;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.google.gson.internal.LinkedTreeMap;
import com.i.e;
import com.managers.u;
import com.utilities.d;
import java.io.File;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.UUID;

public class VideoPlayerActivityTwo extends AppCompatActivity implements OnClickListener, b, EventListener {
    private static final DefaultBandwidthMeter c = new DefaultBandwidthMeter();
    private static final CookieManager d = new CookieManager();
    private static SimpleCache u;
    File a;
    private Orientation b = Orientation.AUTO_START_WITH_PORTRAIT;
    private Handler e;
    private VideoPlayerView f;
    private Button g;
    private Factory h;
    private SimpleExoPlayer i;
    private DefaultTrackSelector j;
    private boolean k;
    private TrackGroupArray l;
    private boolean m;
    private int n;
    private long o;
    private ProgressBar p;
    private String q;
    private String r;
    private int s;
    private int t = GAANA_VIDEO_SOURCE.HOME_PAGE.ordinal();
    private boolean v = false;
    private final OnAudioFocusChangeListener w = new OnAudioFocusChangeListener() {
        public void onAudioFocusChange(int i) {
            switch (i) {
                case -3:
                    if (VideoPlayerActivityTwo.this.i != null) {
                        VideoPlayerActivityTwo.this.i.setVolume(0.1f);
                        return;
                    }
                    return;
                case -2:
                    VideoPlayerActivityTwo.this.b();
                    return;
                case -1:
                    VideoPlayerActivityTwo.this.setResult(0);
                    VideoPlayerActivityTwo.this.finish();
                    return;
                case 1:
                    VideoPlayerActivityTwo.this.c();
                    return;
                case 2:
                    VideoPlayerActivityTwo.this.c();
                    return;
                case 3:
                    if (VideoPlayerActivityTwo.this.i != null) {
                        VideoPlayerActivityTwo.this.i.setVolume(1.0f);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };

    public enum Orientation {
        AUTO_START_WITH_PORTRAIT,
        AUTO_START_WITH_LANDSCAPE,
        ONLY_LANDSCAPE,
        ONLY_PORTRAIT
    }

    private void j() {
    }

    private void k() {
    }

    public void a(int i) {
    }

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

    static {
        d.setCookiePolicy(CookiePolicy.ACCEPT_ORIGINAL_SERVER);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (Constants.l) {
            setTheme(R.style.VideoPlayerThemeWhite);
        }
        getWindow().addFlags(128);
        this.m = true;
        g();
        this.h = a(true);
        this.e = new Handler();
        if (CookieHandler.getDefault() != d) {
            CookieHandler.setDefault(d);
        }
        setContentView((int) R.layout.video_player_activity);
        this.p = (ProgressBar) findViewById(R.id.progressbar);
        this.p.setVisibility(0);
        findViewById(R.id.root).setOnClickListener(this);
        this.f = (VideoPlayerView) findViewById(R.id.player_view);
        this.f.setControllerVisibilityListener(this);
        this.f.requestFocus();
        this.f.setPlaybackPreparer(new PlaybackPreparer() {
            public void preparePlayback() {
                if (VideoPlayerActivityTwo.this.i != null) {
                    VideoPlayerActivityTwo.this.n = VideoPlayerActivityTwo.this.i.getCurrentWindowIndex();
                    VideoPlayerActivityTwo.this.o = VideoPlayerActivityTwo.this.i.getCurrentPosition();
                }
                VideoPlayerActivityTwo.this.e();
                VideoPlayerActivityTwo.this.d();
            }
        });
        String stringExtra = getIntent().getStringExtra("share_url");
        this.r = getIntent().getStringExtra("video_id");
        this.s = getIntent().getIntExtra("video_type", 0);
        this.b = (Orientation) getIntent().getSerializableExtra("orientation");
        if (this.b == null) {
            this.b = Orientation.AUTO_START_WITH_LANDSCAPE;
        }
        switch (this.s) {
            case 1:
                setRequestedOrientation(7);
                this.b = Orientation.AUTO_START_WITH_PORTRAIT;
                break;
            case 2:
                setRequestedOrientation(6);
                this.b = Orientation.AUTO_START_WITH_LANDSCAPE;
                break;
            default:
                setRequestedOrientation(6);
                this.b = Orientation.AUTO_START_WITH_LANDSCAPE;
                break;
        }
        this.t = getIntent().getIntExtra("from_page", 0);
        this.n = getIntent().getIntExtra("seek_index", -1);
        this.o = getIntent().getLongExtra("seek_pos", -1);
        this.f.setShareUrl(stringExtra);
    }

    private void b() {
        if (this.i != null) {
            this.i.setPlayWhenReady(false);
        }
    }

    private void c() {
        if (this.i != null) {
            this.i.setPlayWhenReady(true);
        }
    }

    public void onNewIntent(Intent intent) {
        e();
        this.m = true;
        g();
        setIntent(intent);
    }

    public void onStart() {
        super.onStart();
        if (Util.SDK_INT <= 23) {
            return;
        }
        if (this.i == null) {
            d();
        } else if (this.i.getPlayWhenReady()) {
            c();
        }
    }

    public void onResume() {
        super.onResume();
        this.v = true;
        if (!l()) {
            setResult(0);
            finish();
        }
        if (Util.SDK_INT <= 23) {
            if (this.i == null) {
                d();
            } else if (this.i.getPlayWhenReady()) {
                c();
            }
        }
        getWindow().addFlags(128);
    }

    public void onPause() {
        super.onPause();
        getWindow().clearFlags(128);
        ((AudioManager) getSystemService("audio")).abandonAudioFocus(this.w);
        if (Util.SDK_INT <= 23) {
            b();
        }
    }

    public void onStop() {
        super.onStop();
        long currentPosition = this.i != null ? this.i.getCurrentPosition() : 0;
        String str = "YIM_Video_ViewTime";
        if (this.t == GAANA_VIDEO_SOURCE.OCCASION_PAGE.ordinal()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("_OP");
            str = stringBuilder.toString();
        }
        u.a().a("YIM_Video", str, d.a(((int) currentPosition) / 1000));
        if (Util.SDK_INT > 23) {
            b();
        }
    }

    /* Access modifiers changed, original: protected */
    public void onDestroy() {
        super.onDestroy();
        ((AudioManager) getSystemService("audio")).abandonAudioFocus(this.w);
        if (u != null) {
            u.release();
        }
        e();
        getWindow().clearFlags(128);
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        if (iArr.length <= 0 || iArr[0] != 0) {
            b((int) R.string.storage_permission_toast);
            finish();
            return;
        }
        d();
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        this.f.a();
        return super.dispatchKeyEvent(keyEvent) || this.f.a(keyEvent);
    }

    public void onClick(View view) {
        if (view == this.g) {
            d();
        }
    }

    private void d() {
        Intent intent = getIntent();
        int i = this.i == null ? 1 : false;
        if (i != 0) {
            DrmSessionManager a;
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
            RenderersFactory defaultRenderersFactory = new DefaultRenderersFactory((Context) this, a, 2);
            this.j = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(c));
            this.l = null;
            this.i = ExoPlayerFactory.newSimpleInstance((Context) this, defaultRenderersFactory, this.j, new DefaultLoadControl());
            this.i.addListener(this);
            this.f.setPlayer(this.i);
            this.i.setPlayWhenReady(this.m);
        }
        if (i != 0 || this.k) {
            Uri[] uriArr;
            String[] strArr;
            int i2;
            String action = intent.getAction();
            if (TextUtils.isEmpty(this.q)) {
                this.q = getIntent().getStringExtra("video_url");
            }
            Uri parse = Uri.parse(this.q);
            if ("com.google.android.exoplayer.demo.action.VIEW".equals(action)) {
                uriArr = new Uri[]{parse};
                strArr = new String[]{intent.getStringExtra("extension")};
            } else if ("com.google.android.exoplayer.demo.action.VIEW_LIST".equals(action)) {
                String[] stringArrayExtra = intent.getStringArrayExtra("uri_list");
                Uri[] uriArr2 = new Uri[stringArrayExtra.length];
                for (i2 = 0; i2 < stringArrayExtra.length; i2++) {
                    uriArr2[i2] = Uri.parse(stringArrayExtra[i2]);
                }
                strArr = intent.getStringArrayExtra("extension_list");
                if (strArr == null) {
                    strArr = new String[stringArrayExtra.length];
                }
                uriArr = uriArr2;
            } else {
                return;
            }
            if (!Util.maybeRequestReadExternalStoragePermission(this, uriArr)) {
                MediaSource[] mediaSourceArr = new MediaSource[uriArr.length];
                for (i2 = 0; i2 < uriArr.length; i2++) {
                    mediaSourceArr[i2] = a(uriArr[i2], strArr[i2]);
                }
                MediaSource concatenatingMediaSource = mediaSourceArr.length == 1 ? mediaSourceArr[0] : new ConcatenatingMediaSource(mediaSourceArr);
                i = this.n != -1 ? 1 : 0;
                if (i != 0) {
                    this.i.seekTo(this.n, this.o);
                }
                this.i.prepare(concatenatingMediaSource, i ^ 1, false);
                this.k = false;
                j();
            }
        }
    }

    private MediaSource a(Uri uri, String str) {
        int inferContentType;
        StringBuilder stringBuilder;
        if (TextUtils.isEmpty(str)) {
            inferContentType = Util.inferContentType(uri);
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append(".");
            stringBuilder.append(str);
            inferContentType = Util.inferContentType(stringBuilder.toString());
        }
        switch (inferContentType) {
            case 2:
                return new HlsMediaSource.Factory(this.h).setAllowChunklessPreparation(true).createMediaSource(uri);
            case 3:
                return new ExtractorMediaSource(uri, this.h, new DefaultExtractorsFactory(), this.e, null);
            default:
                stringBuilder = new StringBuilder();
                stringBuilder.append("Unsupported type: ");
                stringBuilder.append(inferContentType);
                throw new IllegalStateException(stringBuilder.toString());
        }
    }

    private DrmSessionManager<FrameworkMediaCrypto> a(UUID uuid, String str, String[] strArr) throws UnsupportedDrmException {
        if (Util.SDK_INT < 18) {
            return null;
        }
        int i = 0;
        MediaDrmCallback httpMediaDrmCallback = new HttpMediaDrmCallback(str, b(false));
        if (strArr != null) {
            while (i < strArr.length - 1) {
                httpMediaDrmCallback.setKeyRequestProperty(strArr[i], strArr[i + 1]);
                i += 2;
            }
        }
        return new DefaultDrmSessionManager(uuid, FrameworkMediaDrm.newInstance(uuid), httpMediaDrmCallback, null, this.e, null);
    }

    private void e() {
        if (this.i != null) {
            this.m = this.i.getPlayWhenReady();
            f();
            this.i.release();
            this.i = null;
            this.j = null;
        }
    }

    private void f() {
        this.n = this.i.getCurrentWindowIndex();
        this.o = this.i.isCurrentWindowSeekable() ? Math.max(0, this.i.getCurrentPosition()) : C.TIME_UNSET;
    }

    private void g() {
        this.n = -1;
        this.o = C.TIME_UNSET;
    }

    private CacheDataSource h() {
        return new CacheDataSource(u, a(c).createDataSource(), new FileDataSource(), new CacheDataSink(u, 10485760), 3, new CacheDataSource.EventListener() {
            public void onCacheIgnored(int i) {
            }

            public void onCachedBytesRead(long j, long j2) {
            }
        });
    }

    private Factory a(boolean z) {
        i();
        if (u == null) {
            return b(z);
        }
        final CacheDataSource h = h();
        return new Factory() {
            public DataSource createDataSource() {
                return h;
            }
        };
    }

    private void i() {
        LeastRecentlyUsedCacheEvictor leastRecentlyUsedCacheEvictor = new LeastRecentlyUsedCacheEvictor(104857600);
        this.a = new File(ContextCompat.getExternalFilesDirs(getApplicationContext(), null)[0].getAbsolutePath(), "media_cache_fullscreen");
        this.a.mkdirs();
        try {
            u = new SimpleCache(this.a, leastRecentlyUsedCacheEvictor);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    private Factory a(DefaultBandwidthMeter defaultBandwidthMeter) {
        return new DefaultDataSourceFactory((Context) this, (TransferListener) defaultBandwidthMeter, ((GaanaApplication) getApplication()).buildDataSourceFactory(defaultBandwidthMeter, true));
    }

    private HttpDataSource.Factory b(boolean z) {
        return ((GaanaApplication) getApplication()).buildHttpDataSourceFactory(z ? c : null, false);
    }

    public void onPlayerStateChanged(boolean z, int i) {
        if (i == 4) {
            k();
            this.p.setVisibility(8);
        } else if (i == 2) {
            this.p.setVisibility(0);
        } else {
            this.p.setVisibility(8);
        }
        j();
    }

    public void onPlayerError(ExoPlaybackException exoPlaybackException) {
        if (exoPlaybackException.type == 1) {
            Exception rendererException = exoPlaybackException.getRendererException();
            if (rendererException instanceof DecoderInitializationException) {
                DecoderInitializationException decoderInitializationException = (DecoderInitializationException) rendererException;
            }
        } else if (exoPlaybackException.type == 0 && exoPlaybackException.getCause() != null && !TextUtils.isEmpty(exoPlaybackException.getCause().getMessage()) && exoPlaybackException.getCause().getMessage().contains("403")) {
            if (this.v) {
                String str;
                switch (this.s) {
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
                new com.player_framework.d(this).a(this.r, str, new e.b() {
                    public void onDataRetrieved(Object obj, int i, boolean z) {
                        int i2 = 0;
                        VideoPlayerActivityTwo.this.v = false;
                        if (obj instanceof LinkedTreeMap) {
                            VideoPlayerActivityTwo.this.e();
                            if (VideoPlayerActivityTwo.this.a == null) {
                                File file = ContextCompat.getExternalFilesDirs(VideoPlayerActivityTwo.this.getApplicationContext(), null)[0];
                                VideoPlayerActivityTwo.this.a = new File(file.getAbsolutePath(), "media_cache_fullscreen");
                                VideoPlayerActivityTwo.this.a.mkdirs();
                            }
                            if (VideoPlayerActivityTwo.this.a.isDirectory()) {
                                String[] list = VideoPlayerActivityTwo.this.a.list();
                                while (i2 < list.length) {
                                    new File(VideoPlayerActivityTwo.this.a, list[i2]).delete();
                                    i2++;
                                }
                            }
                            VideoPlayerActivityTwo.this.q = com.utilities.Util.k((String) ((LinkedTreeMap) obj).get("data"));
                            VideoPlayerActivityTwo.this.d();
                        }
                    }
                });
                return;
            }
            return;
        }
        this.k = true;
        if (a(exoPlaybackException)) {
            g();
            d();
        } else {
            f();
            j();
            k();
        }
    }

    public void onPositionDiscontinuity(int i) {
        if (this.k) {
            f();
        }
    }

    public void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
        j();
        if (trackGroupArray != this.l) {
            MappedTrackInfo currentMappedTrackInfo = this.j.getCurrentMappedTrackInfo();
            if (currentMappedTrackInfo != null) {
                currentMappedTrackInfo.getTrackTypeRendererSupport(2);
                currentMappedTrackInfo.getTrackTypeRendererSupport(1);
            }
            this.l = trackGroupArray;
        }
    }

    private void b(int i) {
        a(getString(i));
    }

    private void a(String str) {
        Toast.makeText(getApplicationContext(), str, 1).show();
    }

    private static boolean a(ExoPlaybackException exoPlaybackException) {
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

    public void a() {
        boolean playWhenReady = this.i.getPlayWhenReady();
        this.i.setPlayWhenReady(false);
        if (getResources().getConfiguration().orientation == 1) {
            setRequestedOrientation(0);
        } else {
            setRequestedOrientation(1);
        }
        if (playWhenReady) {
            this.i.setPlayWhenReady(true);
        }
    }

    public void onBackPressed() {
        if (this.i != null) {
            Constants.dd = this.i.getCurrentWindowIndex();
            Constants.de = this.i.getCurrentPosition();
        }
        finish();
        super.onBackPressed();
    }

    private boolean l() {
        int requestAudioFocus;
        AudioManager audioManager = (AudioManager) getSystemService("audio");
        if (d.d()) {
            requestAudioFocus = audioManager.requestAudioFocus(new Builder(1).setAudioAttributes(new AudioAttributes.Builder().setLegacyStreamType(3).build()).setOnAudioFocusChangeListener(this.w).build());
        } else {
            requestAudioFocus = audioManager.requestAudioFocus(this.w, 3, 1);
        }
        if (requestAudioFocus == 0) {
            return false;
        }
        return true;
    }
}
