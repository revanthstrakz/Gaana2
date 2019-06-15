package com.playercache;

import android.content.Context;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.widget.FrameLayout;
import com.constants.Constants;
import com.exoplayer2.TrackSpan;
import com.exoplayer2.ui.VideoPlayerView;
import com.exoplayer2.upstream.EncryptedFileDataSource2;
import com.exoplayer2.upstream.FileDataSource;
import com.exoplayer2.upstream.cache.CacheDataSink;
import com.exoplayer2.upstream.cache.b;
import com.exoplayer2.upstream.cache.m;
import com.exoplayer2.upstream.cache.o;
import com.gaana.application.GaanaApplication;
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
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSource.Factory;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.util.Util;
import com.managers.PlayerManager;
import com.managers.PlayerManager.PlayerType;
import com.managers.ad;
import com.models.PlayerTrack;
import com.playercache.TrackCacheQueueManager.CACHING_BEHAVIOUR;
import com.utilities.d;
import com.utilities.i;
import java.io.File;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class a implements EventListener {
    public static final DefaultBandwidthMeter a = new DefaultBandwidthMeter();
    public static final m b = new m(314572800);
    VideoPlayerView c = null;
    FrameLayout d = null;
    Period e = new Period();
    private Factory f;
    private SimpleExoPlayer g;
    private b h;
    private DefaultTrackSelector i;
    private boolean j;
    private TrackGroupArray k;
    private int l;
    private long m;
    private int n;
    private boolean o;
    private Uri p;
    private Context q;
    private final CopyOnWriteArrayList<a> r;
    private boolean s = false;
    private boolean t = false;
    private boolean u;
    private boolean v = false;
    private boolean w = false;
    private final a x = new a() {
        public void a(Exception exception) {
        }

        public void a(boolean z, int i) {
        }
    };

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

    public a(Context context, Uri uri) {
        this.q = context;
        this.r = new CopyOnWriteArrayList();
        a(context);
        a();
        this.p = uri;
    }

    public void a() {
        Constants.ej.a(new File(ContextCompat.getExternalFilesDirs(this.q, null)[0].getAbsolutePath(), "media_cache"));
    }

    public void a(a aVar) {
        this.r.add(aVar);
    }

    private com.exoplayer2.upstream.cache.a a(File file, String str, boolean z, boolean z2) {
        File file2;
        o oVar;
        File file3 = new File(file.getAbsolutePath(), "media_cache");
        if (TextUtils.isEmpty(str)) {
            file2 = new File(file.getAbsolutePath(), "media_cache");
            file2.mkdirs();
        } else {
            String absolutePath = file.getAbsolutePath();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("media_cache/");
            stringBuilder.append(str);
            file2 = new File(absolutePath, stringBuilder.toString());
            file2.mkdirs();
            b.a(file3);
            b.a(new TrackSpan(str, System.currentTimeMillis()));
            Constants.ej.a(file3);
        }
        DefaultBandwidthMeter defaultBandwidthMeter = null;
        if (d.b()) {
            b bVar = b;
            GaanaApplication.getInstance();
            oVar = new o(file2, bVar, GaanaApplication.getExoEncryptionKey(0));
        } else {
            oVar = new o(file2, b, null);
        }
        o oVar2 = oVar;
        if (z) {
            defaultBandwidthMeter = a;
        }
        return new com.exoplayer2.upstream.cache.a(oVar2, a(defaultBandwidthMeter, true).createDataSource(), i() ? new EncryptedFileDataSource2() : new FileDataSource(), new CacheDataSink(oVar2, 10485760), 3, z2, new com.exoplayer2.upstream.cache.a.a() {
            public void a(int i) {
            }

            public void a(long j, long j2) {
            }
        }, null);
    }

    private Factory a(boolean z, String str, boolean z2, String str2) {
        DefaultBandwidthMeter defaultBandwidthMeter = null;
        File file = ContextCompat.getExternalFilesDirs(this.q, null)[0];
        if (PlayerManager.a(this.q).m() == PlayerType.GAANA_RADIO || Constants.E == 0 || this.u || file == null) {
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

    private boolean i() {
        return d.b();
    }

    private Factory a(DefaultBandwidthMeter defaultBandwidthMeter, boolean z) {
        return new com.exoplayer2.upstream.b(this.q, defaultBandwidthMeter, ((GaanaApplication) this.q).buildDataSourceFactory(defaultBandwidthMeter, z));
    }

    private Factory a(DefaultBandwidthMeter defaultBandwidthMeter, boolean z, boolean z2) {
        return new com.exoplayer2.upstream.b(this.q, defaultBandwidthMeter, ((GaanaApplication) this.q).buildDataSourceFactory(defaultBandwidthMeter, z2), z);
    }

    private void a(Context context) {
        if ((this.g == null ? 1 : 0) != 0) {
            RenderersFactory defaultRenderersFactory = new DefaultRenderersFactory(context, null, 0);
            this.i = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(a));
            this.k = null;
            this.h = new com.playercache.b.a().a((int) C.DEFAULT_VIDEO_BUFFER_SIZE).a(false).a();
            this.g = ExoPlayerFactory.newSimpleInstance(context, defaultRenderersFactory, this.i, this.h);
            this.g.addListener(this);
            k();
        }
    }

    public void a(Uri[] uriArr, Object obj, int i, boolean z, boolean z2) {
        this.t = false;
        PlayerTrack playerTrack = (obj == null || !(obj instanceof PlayerTrack)) ? null : (PlayerTrack) obj;
        if (playerTrack != null) {
            if (!this.u || playerTrack.b() == null) {
                this.f = a(true, playerTrack.h(), z, "1");
            } else {
                this.f = a(true, playerTrack.b().getVideoId(), z, Double.toString(playerTrack.b().getContentSource()));
            }
            if (this.v) {
                this.h.a(playerTrack.b().getCachingBehaviour() == CACHING_BEHAVIOUR.FULL_CACHE.ordinal());
            }
        } else {
            this.f = a(true, "", z, "1");
        }
        uriArr[0] = this.p;
        MediaSource[] mediaSourceArr = new MediaSource[uriArr.length];
        for (int i2 = 0; i2 < uriArr.length; i2++) {
            mediaSourceArr[i2] = a(uriArr[i2], null);
        }
        MediaSource concatenatingMediaSource = mediaSourceArr.length == 1 ? mediaSourceArr[0] : new ConcatenatingMediaSource(mediaSourceArr);
        int i3 = this.l != -1 ? 1 : 0;
        if (i3 != 0) {
            this.g.seekTo(this.l, this.m);
        }
        this.g.prepare(concatenatingMediaSource, i3 ^ 1, false);
        k();
        this.j = false;
    }

    public void onPlayerStateChanged(boolean z, int i) {
        k();
    }

    public void onPlayerError(ExoPlaybackException exoPlaybackException) {
        if (!this.v) {
            Iterator it = this.r.iterator();
            while (it.hasNext()) {
                ((a) it.next()).a(exoPlaybackException);
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
                return new HlsMediaSource.Factory(this.f).setAllowChunklessPreparation(true).createMediaSource(uri);
            case 3:
                if (Util.isLocalFileUri(uri) && uri.getPath().contains(i.a)) {
                    return new ExtractorMediaSource.Factory(a(null, true, false)).createMediaSource(uri);
                }
                return new ExtractorMediaSource.Factory(this.f).createMediaSource(uri);
            default:
                stringBuilder = new StringBuilder();
                stringBuilder.append("Unsupported type: ");
                stringBuilder.append(inferContentType);
                throw new IllegalStateException(stringBuilder.toString());
        }
    }

    public void a(boolean z) {
        this.w = z;
        if (!this.v) {
            this.h.a(z);
        }
    }

    public void b(boolean z) {
        this.v = z;
    }

    public void a(boolean z, boolean z2) {
        this.g.setPlayWhenReady(z);
        k();
    }

    private void j() {
        this.l = this.g.getCurrentWindowIndex();
        this.m = Math.max(0, this.g.getCurrentPosition());
    }

    public void a(long j) {
        this.g.seekTo(j);
        j();
    }

    public void b() {
        this.g.stop(true);
        this.g.release();
    }

    public int c() {
        return this.g.getPlaybackState();
    }

    public long d() {
        long currentPosition = this.g.getCurrentPosition();
        if (PlayerManager.a(this.q).m() != PlayerType.GAANA_RADIO || !ad.a(this.q).o().booleanValue()) {
            return currentPosition;
        }
        Timeline currentTimeline = this.g.getCurrentTimeline();
        return !currentTimeline.isEmpty() ? currentPosition - currentTimeline.getPeriod(this.g.getCurrentPeriodIndex(), this.e).getPositionInWindowMs() : currentPosition;
    }

    public long e() {
        return this.g.getDuration();
    }

    public int f() {
        return this.g.getBufferedPercentage();
    }

    public boolean g() {
        return this.g.getPlayWhenReady();
    }

    private void k() {
        boolean playWhenReady = this.g.getPlayWhenReady();
        int c = c();
        if (this.o != playWhenReady || this.n != c) {
            this.o = playWhenReady;
            this.n = c;
            Iterator it = this.r.iterator();
            while (it.hasNext()) {
                ((a) it.next()).a(playWhenReady, c);
            }
        }
    }

    public int h() {
        return this.g != null ? this.g.getAudioSessionId() : 0;
    }

    public void a(float f) {
        if (this.g != null) {
            this.g.setVolume(f);
        }
    }

    public void c(boolean z) {
        this.u = z;
    }
}
