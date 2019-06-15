package com.gaanavideo;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.constants.Constants;
import com.exoplayer2.TrackSpan;
import com.exoplayer2.c;
import com.exoplayer2.ui.VideoPlayerView;
import com.exoplayer2.upstream.EncryptedFileDataSource2;
import com.exoplayer2.upstream.FileDataSource;
import com.exoplayer2.upstream.cache.CacheDataSink;
import com.exoplayer2.upstream.cache.m;
import com.exoplayer2.upstream.cache.o;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.google.ads.interactivemedia.v3.api.CompanionAdSlot;
import com.google.ads.interactivemedia.v3.api.CompanionAdSlot.ClickListener;
import com.google.ads.interactivemedia.v3.api.ImaSdkFactory;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer.EventListener;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.RenderersFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.Timeline.Period;
import com.google.android.exoplayer2.source.ConcatenatingMediaSource;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.ads.AdsMediaSource;
import com.google.android.exoplayer2.source.ads.AdsMediaSource.MediaSourceFactory;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSource.Factory;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import com.managers.PlayerManager;
import com.managers.PlayerManager.PlayerType;
import com.managers.ad;
import com.managers.ai;
import com.managers.aj;
import com.utilities.Util;
import com.utilities.d;
import com.utilities.i;
import com.youtube.YouTubeVideos.YouTubeVideo;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class b implements EventListener {
    public static final DefaultBandwidthMeter a = new DefaultBandwidthMeter();
    public static final m b = new m(104857600);
    private ClickListener A = new ClickListener() {
        public void onCompanionAdClick() {
            b.this.c(false);
            b.this.b();
        }
    };
    private final a B = new a() {
        public void a(Exception exception) {
        }

        public void a(boolean z, int i) {
        }
    };
    VideoPlayerView c = null;
    FrameLayout d = null;
    public ViewGroup e;
    Period f = new Period();
    private Handler g;
    private Factory h;
    private SimpleExoPlayer i;
    private c j;
    private DefaultTrackSelector k;
    private boolean l;
    private TrackGroupArray m;
    private int n;
    private long o;
    private int p;
    private boolean q;
    private Uri r;
    private Context s;
    private final CopyOnWriteArrayList<a> t;
    private boolean u;
    private com.exoplayer2.a.a.a v;
    private Uri w;
    private boolean x = false;
    private boolean y = false;
    private boolean z = false;

    public interface a {
        void a(Exception exception);

        void a(boolean z, int i);
    }

    public void onLoadingChanged(boolean z) {
    }

    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
    }

    public void onPositionDiscontinuity(int i) {
    }

    public void onRepeatModeChanged(int i) {
    }

    public void onSeekProcessed() {
    }

    public void onShuffleModeEnabledChanged(boolean z) {
    }

    public void onTimelineChanged(Timeline timeline, Object obj, int i) {
    }

    public void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
    }

    private void c(boolean z) {
        if (ai.a() != null) {
            GaanaActivity gaanaActivity = (GaanaActivity) ai.a();
            if (z) {
                if (this.c != null) {
                    gaanaActivity.addOverlayFrameLayout(this.c);
                    this.c.setPlayer(this.i);
                    this.c.setVisibility(0);
                }
                if (!(this.d == null || ai.a() == null)) {
                    gaanaActivity.addCompanionAdSlot(this.d);
                }
            } else {
                if (this.c != null) {
                    gaanaActivity.removeVideoView(this.c);
                }
                if (this.d != null) {
                    gaanaActivity.removeCompanionAdSlot(this.d);
                }
                if (gaanaActivity.getBackgroundAdSlot() != null) {
                    gaanaActivity.getBackgroundAdSlot().setVisibility(8);
                }
            }
        }
    }

    public b(Context context, Uri uri) {
        this.s = context;
        this.g = new Handler();
        this.t = new CopyOnWriteArrayList();
        a(context);
        a();
        this.r = uri;
    }

    public void a() {
        Constants.ej.a(new File(ContextCompat.getExternalFilesDirs(this.s, null)[0].getAbsolutePath(), "media_cache"));
    }

    public void a(a aVar) {
        this.t.add(aVar);
    }

    private com.exoplayer2.upstream.cache.a a(File file, String str, boolean z, boolean z2) {
        File file2;
        o oVar;
        File file3 = new File(file.getAbsolutePath(), "media_cache_video");
        if (TextUtils.isEmpty(str)) {
            file2 = new File(file.getAbsolutePath(), "media_cache_video");
            file2.mkdirs();
        } else {
            String absolutePath = file.getAbsolutePath();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("media_cache_video/");
            stringBuilder.append(str);
            file2 = new File(absolutePath, stringBuilder.toString());
            file2.mkdirs();
            b.a(file3);
            b.a(new TrackSpan(str, System.currentTimeMillis()));
        }
        DefaultBandwidthMeter defaultBandwidthMeter = null;
        if (d.b()) {
            com.exoplayer2.upstream.cache.b bVar = b;
            GaanaApplication.getInstance();
            oVar = new o(file2, bVar, GaanaApplication.getExoEncryptionKey(0));
        } else {
            oVar = new o(file2, b, null);
        }
        o oVar2 = oVar;
        if (z) {
            defaultBandwidthMeter = a;
        }
        return new com.exoplayer2.upstream.cache.a(oVar2, a(defaultBandwidthMeter, true).createDataSource(), l() ? new EncryptedFileDataSource2() : new FileDataSource(), new CacheDataSink(oVar2, 10485760), 3, z2, new com.exoplayer2.upstream.cache.a.a() {
            public void a(int i) {
            }

            public void a(long j, long j2) {
            }
        }, null);
    }

    private Factory a(boolean z, String str, boolean z2) {
        DefaultBandwidthMeter defaultBandwidthMeter = null;
        File file = ContextCompat.getExternalFilesDirs(this.s, null)[0];
        Constants.E = 0;
        if (PlayerManager.a(this.s).m() == PlayerType.GAANA_RADIO || this.y || Constants.E == 0 || Constants.D == 1 || file == null) {
            if (z) {
                defaultBandwidthMeter = a;
            }
            return a(defaultBandwidthMeter, false);
        }
        final com.exoplayer2.upstream.cache.a a = a(file, str, z, z2);
        return new Factory() {
            public DataSource createDataSource() {
                return a;
            }
        };
    }

    private boolean l() {
        return d.b();
    }

    private Factory a(DefaultBandwidthMeter defaultBandwidthMeter, boolean z) {
        return new com.exoplayer2.upstream.b(this.s, defaultBandwidthMeter, ((GaanaApplication) this.s).buildDataSourceFactory(defaultBandwidthMeter, z));
    }

    private Factory a(DefaultBandwidthMeter defaultBandwidthMeter, boolean z, boolean z2) {
        return new com.exoplayer2.upstream.b(this.s, defaultBandwidthMeter, ((GaanaApplication) this.s).buildDataSourceFactory(defaultBandwidthMeter, z2), z);
    }

    public void a(boolean z) {
        this.z = z;
        if (this.z && this.i != null) {
            this.i.setVideoScalingMode(2);
        }
    }

    private void a(Context context) {
        if ((this.i == null ? 1 : 0) != 0) {
            RenderersFactory defaultRenderersFactory = new DefaultRenderersFactory(context, null, 0);
            this.k = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(a));
            this.m = null;
            this.j = new com.exoplayer2.c.a().a((int) C.DEFAULT_VIDEO_BUFFER_SIZE).a(false).a();
            this.i = ExoPlayerFactory.newSimpleInstance(context, defaultRenderersFactory, this.k, this.j);
            this.i.addListener(this);
            if (this.z) {
                this.i.setVideoScalingMode(2);
            }
            n();
        }
    }

    public void a(Uri[] uriArr, Object obj, int i, boolean z, boolean z2) {
        YouTubeVideo youTubeVideo = (obj == null || !(obj instanceof YouTubeVideo)) ? null : (YouTubeVideo) obj;
        if (youTubeVideo != null) {
            this.h = a(true, youTubeVideo.getBusinessObjId(), z);
        } else {
            this.h = a(true, "", z);
        }
        uriArr[0] = this.r;
        MediaSource[] mediaSourceArr = new MediaSource[uriArr.length];
        for (int i2 = 0; i2 < uriArr.length; i2++) {
            mediaSourceArr[i2] = a(uriArr[i2], null);
        }
        MediaSource concatenatingMediaSource = mediaSourceArr.length == 1 ? mediaSourceArr[0] : new ConcatenatingMediaSource(mediaSourceArr);
        this.u = Util.c();
        if (TextUtils.isEmpty(null)) {
            c();
        } else {
            Uri parse = Uri.parse(null);
            if (!parse.equals(this.w)) {
                c();
                this.w = parse;
            }
            MediaSource a = a(concatenatingMediaSource, Uri.parse(null));
            if (a != null) {
                concatenatingMediaSource = a;
            } else {
                aj.a().a(this.s, "IMA not loaded");
            }
        }
        int i3 = this.n != -1 ? 1 : 0;
        if (i3 != 0) {
            this.i.seekTo(this.n, this.o);
        }
        this.i.prepare(concatenatingMediaSource, i3 ^ 1, false);
        n();
        this.l = false;
    }

    @Nullable
    private MediaSource a(MediaSource mediaSource, Uri uri) {
        try {
            this.e = new FrameLayout(this.s);
            if (this.v == null) {
                Context a = ai.a();
                this.v = new com.exoplayer2.a.a.a(this.s, uri);
                if (this.u && a != null && (a instanceof GaanaActivity)) {
                    this.c = new VideoPlayerView(a);
                    this.c.setLayoutParams(new LayoutParams(-1, -1));
                    this.c.setBackgroundColor(a.getResources().getColor(R.color.black));
                    this.d = new FrameLayout(a);
                    this.d.setLayoutParams(new LayoutParams(Util.a(a, (int) ModuleDescriptor.MODULE_VERSION), Util.a(a, 480)));
                    this.c.getOverlayFrameLayout().addView(this.e);
                    Integer[][] numArr = new Integer[1][];
                    numArr[0] = new Integer[]{Integer.valueOf(ModuleDescriptor.MODULE_VERSION), Integer.valueOf(480)};
                    Collection arrayList = new ArrayList();
                    for (int i = 0; i < numArr.length; i++) {
                        CompanionAdSlot createCompanionAdSlot = ImaSdkFactory.getInstance().createCompanionAdSlot();
                        createCompanionAdSlot.setContainer(this.d);
                        createCompanionAdSlot.addClickListener(this.A);
                        createCompanionAdSlot.setSize(numArr[i][0].intValue(), numArr[i][1].intValue());
                        arrayList.add(createCompanionAdSlot);
                    }
                    this.v.a(arrayList);
                }
            }
            return new AdsMediaSource(mediaSource, new MediaSourceFactory() {
                public MediaSource createMediaSource(Uri uri) {
                    return b.this.a(uri, null);
                }

                public int[] getSupportedTypes() {
                    return new int[]{0, 1, 2, 3};
                }
            }, this.v, this.e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void b() {
        if (this.v != null) {
            this.v.b();
        }
    }

    public void c() {
        if (this.v != null) {
            this.v.release();
            this.v = null;
            this.w = null;
            if (this.c != null) {
                this.c.getOverlayFrameLayout().removeAllViews();
            }
        }
    }

    public void onPlayerStateChanged(boolean z, int i) {
        n();
    }

    public void onPlayerError(ExoPlaybackException exoPlaybackException) {
        Iterator it = this.t.iterator();
        while (it.hasNext()) {
            ((a) it.next()).a(exoPlaybackException);
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
                return new HlsMediaSource.Factory(this.h).setAllowChunklessPreparation(true).createMediaSource(uri);
            case 3:
                if (com.google.android.exoplayer2.util.Util.isLocalFileUri(uri) && uri.getPath().contains(i.a)) {
                    return new ExtractorMediaSource.Factory(a(null, true, false)).createMediaSource(uri);
                }
                return new ExtractorMediaSource.Factory(this.h).createMediaSource(uri);
            default:
                stringBuilder = new StringBuilder();
                stringBuilder.append("Unsupported type: ");
                stringBuilder.append(inferContentType);
                throw new IllegalStateException(stringBuilder.toString());
        }
    }

    public void b(boolean z) {
        this.j.a(z);
    }

    public void a(boolean z, boolean z2) {
        this.i.setPlayWhenReady(z);
        n();
    }

    private void m() {
        this.n = this.i.getCurrentWindowIndex();
        this.o = Math.max(0, this.i.getCurrentPosition());
    }

    public void a(long j) {
        this.i.seekTo(j);
        m();
    }

    public void d() {
        this.i.stop(true);
        this.i.release();
    }

    public int e() {
        return this.i.getPlaybackState();
    }

    public long f() {
        long currentPosition = this.i.getCurrentPosition();
        if (PlayerManager.a(this.s).m() != PlayerType.GAANA_RADIO || !ad.a(this.s).o().booleanValue()) {
            return currentPosition;
        }
        Timeline currentTimeline = this.i.getCurrentTimeline();
        return !currentTimeline.isEmpty() ? currentPosition - currentTimeline.getPeriod(this.i.getCurrentPeriodIndex(), this.f).getPositionInWindowMs() : currentPosition;
    }

    public long g() {
        return this.i.getDuration();
    }

    public int h() {
        return this.i.getBufferedPercentage();
    }

    public boolean i() {
        return this.i.getPlayWhenReady();
    }

    private void n() {
        boolean playWhenReady = this.i.getPlayWhenReady();
        int e = e();
        if (this.q != playWhenReady || this.p != e) {
            this.q = playWhenReady;
            this.p = e;
            Iterator it = this.t.iterator();
            while (it.hasNext()) {
                ((a) it.next()).a(playWhenReady, e);
            }
        }
    }

    public int j() {
        return this.i != null ? this.i.getAudioSessionId() : 0;
    }

    public void a(float f) {
        if (this.i != null) {
            this.i.setVolume(f);
        }
    }

    public SimpleExoPlayer k() {
        return this.i;
    }
}
