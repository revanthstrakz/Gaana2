package com.exoplayer2.a.a;

import android.content.Context;
import android.net.Uri;
import android.os.Looper;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import com.google.ads.interactivemedia.v3.api.Ad;
import com.google.ads.interactivemedia.v3.api.AdDisplayContainer;
import com.google.ads.interactivemedia.v3.api.AdError;
import com.google.ads.interactivemedia.v3.api.AdError.AdErrorCode;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent.AdErrorListener;
import com.google.ads.interactivemedia.v3.api.AdEvent;
import com.google.ads.interactivemedia.v3.api.AdEvent.AdEventListener;
import com.google.ads.interactivemedia.v3.api.AdEvent.AdEventType;
import com.google.ads.interactivemedia.v3.api.AdPodInfo;
import com.google.ads.interactivemedia.v3.api.AdsLoader.AdsLoadedListener;
import com.google.ads.interactivemedia.v3.api.AdsManager;
import com.google.ads.interactivemedia.v3.api.AdsManagerLoadedEvent;
import com.google.ads.interactivemedia.v3.api.AdsRenderingSettings;
import com.google.ads.interactivemedia.v3.api.AdsRequest;
import com.google.ads.interactivemedia.v3.api.CompanionAdSlot;
import com.google.ads.interactivemedia.v3.api.ImaSdkFactory;
import com.google.ads.interactivemedia.v3.api.ImaSdkSettings;
import com.google.ads.interactivemedia.v3.api.UiElement;
import com.google.ads.interactivemedia.v3.api.player.ContentProgressProvider;
import com.google.ads.interactivemedia.v3.api.player.VideoAdPlayer;
import com.google.ads.interactivemedia.v3.api.player.VideoAdPlayer.VideoAdPlayerCallback;
import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerLibraryInfo;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Player$EventListener$$CC;
import com.google.android.exoplayer2.Player.AudioComponent;
import com.google.android.exoplayer2.Player.EventListener;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.Timeline.Period;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.ads.AdPlaybackState;
import com.google.android.exoplayer2.source.ads.AdPlaybackState.AdGroup;
import com.google.android.exoplayer2.source.ads.AdsLoader;
import com.google.android.exoplayer2.source.ads.AdsMediaSource.AdLoadException;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class a implements AdErrorListener, AdEventListener, AdsLoadedListener, ContentProgressProvider, VideoAdPlayer, EventListener, AdsLoader {
    private int A;
    private int B;
    private boolean C;
    private int D;
    private boolean E;
    private boolean F;
    private int G;
    private boolean H;
    private long I;
    private long J;
    private long K;
    private boolean L;
    private com.exoplayer2.a.a M;
    @Nullable
    private final Uri a;
    @Nullable
    private final String b;
    private final int c;
    private final int d;
    private final boolean e;
    private final int f;
    @Nullable
    private final Set<UiElement> g;
    @Nullable
    private final AdEventListener h;
    private final b i;
    private final Period j;
    private final List<VideoAdPlayerCallback> k;
    private final AdDisplayContainer l;
    private final com.google.ads.interactivemedia.v3.api.AdsLoader m;
    private Object n;
    private List<String> o;
    private AdsLoader.EventListener p;
    private Player q;
    private VideoProgressUpdate r;
    private VideoProgressUpdate s;
    private int t;
    private AdsManager u;
    private AdLoadException v;
    private Timeline w;
    private long x;
    private int y;
    private AdPlaybackState z;

    interface b {
        com.google.ads.interactivemedia.v3.api.AdsLoader a(Context context, ImaSdkSettings imaSdkSettings);

        ImaSdkSettings a();

        AdsRenderingSettings b();

        AdDisplayContainer c();

        AdsRequest d();
    }

    private static final class a implements b {
        private a() {
        }

        /* synthetic */ a(AnonymousClass1 anonymousClass1) {
            this();
        }

        public ImaSdkSettings a() {
            return ImaSdkFactory.getInstance().createImaSdkSettings();
        }

        public AdsRenderingSettings b() {
            return ImaSdkFactory.getInstance().createAdsRenderingSettings();
        }

        public AdDisplayContainer c() {
            return ImaSdkFactory.getInstance().createAdDisplayContainer();
        }

        public AdsRequest d() {
            return ImaSdkFactory.getInstance().createAdsRequest();
        }

        public com.google.ads.interactivemedia.v3.api.AdsLoader a(Context context, ImaSdkSettings imaSdkSettings) {
            return ImaSdkFactory.getInstance().createAdsLoader(context, imaSdkSettings);
        }
    }

    public void onLoadingChanged(boolean z) {
        Player$EventListener$$CC.onLoadingChanged(this, z);
    }

    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
        Player$EventListener$$CC.onPlaybackParametersChanged(this, playbackParameters);
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

    public void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
        Player$EventListener$$CC.onTracksChanged(this, trackGroupArray, trackSelectionArray);
    }

    static {
        ExoPlayerLibraryInfo.registerModule("goog.exo.ima");
    }

    public a(Context context, Uri uri) {
        this(context, uri, null, null, -1, -1, -1, true, null, null, new a());
    }

    private a(Context context, @Nullable Uri uri, @Nullable ImaSdkSettings imaSdkSettings, @Nullable String str, int i, int i2, int i3, boolean z, @Nullable Set<UiElement> set, @Nullable AdEventListener adEventListener, b bVar) {
        boolean z2 = (uri == null && str == null) ? false : true;
        Assertions.checkArgument(z2);
        this.a = uri;
        this.b = str;
        this.c = i;
        this.d = i2;
        this.f = i3;
        this.e = z;
        this.g = set;
        this.h = adEventListener;
        this.i = bVar;
        if (imaSdkSettings == null) {
            imaSdkSettings = bVar.a();
        }
        imaSdkSettings.setPlayerType("google/exo.ext.ima");
        imaSdkSettings.setPlayerVersion(ExoPlayerLibraryInfo.VERSION);
        this.m = bVar.a(context, imaSdkSettings);
        this.j = new Period();
        this.k = new ArrayList(1);
        this.l = bVar.c();
        this.l.setPlayer(this);
        this.m.addAdErrorListener(this);
        this.m.addAdsLoadedListener(this);
        this.I = C.TIME_UNSET;
        this.J = C.TIME_UNSET;
        this.K = C.TIME_UNSET;
        this.B = -1;
        this.x = C.TIME_UNSET;
    }

    public void a(com.exoplayer2.a.a aVar) {
        this.M = aVar;
    }

    public void a(Collection<CompanionAdSlot> collection) {
        this.l.setCompanionSlots(collection);
    }

    public void a(ViewGroup viewGroup) {
        if (this.z == null && this.u == null && this.n == null) {
            this.l.setAdContainer(viewGroup);
            this.n = new Object();
            AdsRequest d = this.i.d();
            if (this.a != null) {
                d.setAdTagUrl(this.a.toString());
            } else {
                d.setAdsResponse(this.b);
            }
            if (this.c != -1) {
                d.setVastLoadTimeout((float) this.c);
            }
            d.setAdDisplayContainer(this.l);
            d.setContentProgressProvider(this);
            d.setUserRequestContext(this.n);
            this.m.requestAds(d);
        }
    }

    public void setSupportedContentTypes(int... iArr) {
        ArrayList arrayList = new ArrayList();
        for (int i : iArr) {
            if (i == 0) {
                arrayList.add(MimeTypes.APPLICATION_MPD);
            } else if (i == 2) {
                arrayList.add(MimeTypes.APPLICATION_M3U8);
            } else if (i == 3) {
                arrayList.addAll(Arrays.asList(new String[]{MimeTypes.VIDEO_MP4, MimeTypes.VIDEO_WEBM, MimeTypes.VIDEO_H263, MimeTypes.AUDIO_MP4, MimeTypes.AUDIO_MPEG}));
            }
        }
        this.o = Collections.unmodifiableList(arrayList);
    }

    public void a() {
        if (this.C && this.q.getPlayWhenReady()) {
            this.u.resume();
        }
    }

    public void attachPlayer(ExoPlayer exoPlayer, AdsLoader.EventListener eventListener, ViewGroup viewGroup) {
        Assertions.checkArgument(exoPlayer.getApplicationLooper() == Looper.getMainLooper());
        this.q = exoPlayer;
        this.p = eventListener;
        this.t = 0;
        this.s = null;
        this.r = null;
        this.l.setAdContainer(viewGroup);
        exoPlayer.addListener(this);
        j();
        if (this.z != null) {
            eventListener.onAdPlaybackState(this.z);
            if (this.C && exoPlayer.getPlayWhenReady()) {
                this.u.resume();
            }
        } else if (this.u != null) {
            c();
        } else {
            a(viewGroup);
        }
    }

    public void detachPlayer() {
        if (this.u != null && this.C) {
            this.z = this.z.withAdResumePositionUs(this.F ? C.msToUs(this.q.getCurrentPosition()) : 0);
            this.u.pause();
        }
        this.t = getVolume();
        this.s = getAdProgress();
        this.r = getContentProgress();
        this.q.removeListener(this);
        this.q = null;
        this.p = null;
    }

    public void release() {
        this.n = null;
        if (this.u != null) {
            this.u.destroy();
            this.u = null;
        }
        this.C = false;
        this.D = 0;
        this.v = null;
        this.z = AdPlaybackState.NONE;
        i();
    }

    public void handlePrepareError(int i, int i2, IOException iOException) {
        if (this.q != null) {
            try {
                a(i, i2, iOException);
            } catch (Exception e) {
                a("handlePrepareError", e);
            }
        }
    }

    public void onAdsManagerLoaded(AdsManagerLoadedEvent adsManagerLoadedEvent) {
        AdsManager adsManager = adsManagerLoadedEvent.getAdsManager();
        if (Util.areEqual(this.n, adsManagerLoadedEvent.getUserRequestContext())) {
            this.n = null;
            this.u = adsManager;
            adsManager.addAdErrorListener(this);
            adsManager.addAdEventListener(this);
            if (this.h != null) {
                adsManager.addAdEventListener(this.h);
            }
            if (this.q != null) {
                try {
                    c();
                } catch (Exception e) {
                    a("onAdsManagerLoaded", e);
                }
            }
            return;
        }
        adsManager.destroy();
    }

    public void onAdEvent(AdEvent adEvent) {
        adEvent.getType();
        if (this.u == null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Ignoring AdEvent after release: ");
            stringBuilder.append(adEvent);
            Log.w("ImaAdsLoader", stringBuilder.toString());
            return;
        }
        try {
            a(adEvent);
        } catch (Exception e) {
            a("onAdEvent", e);
        }
    }

    public void onAdError(AdErrorEvent adErrorEvent) {
        Exception error = adErrorEvent.getError();
        if (this.u == null) {
            this.n = null;
            this.z = new AdPlaybackState(new long[0]);
            i();
        } else if (a((AdError) error)) {
            try {
                a(error);
            } catch (Exception e) {
                a("onAdError", e);
            }
        }
        if (this.v == null) {
            this.v = AdLoadException.createForAllAds(error);
        }
        j();
    }

    public VideoProgressUpdate getContentProgress() {
        if (this.q == null) {
            return this.r;
        }
        long j;
        boolean z = this.x != C.TIME_UNSET;
        long elapsedRealtime;
        if (this.K != C.TIME_UNSET) {
            this.L = true;
            j = this.K;
            this.A = this.z.getAdGroupIndexForPositionUs(C.msToUs(j));
        } else if (this.I != C.TIME_UNSET) {
            elapsedRealtime = this.J + (SystemClock.elapsedRealtime() - this.I);
            this.A = this.z.getAdGroupIndexForPositionUs(C.msToUs(elapsedRealtime));
            j = elapsedRealtime;
        } else if (this.D != 0 || this.F || !z) {
            return VideoProgressUpdate.VIDEO_TIME_NOT_READY;
        } else {
            j = this.q.getCurrentPosition();
            int adGroupIndexAfterPositionUs = this.z.getAdGroupIndexAfterPositionUs(C.msToUs(j));
            if (!(adGroupIndexAfterPositionUs == this.A || adGroupIndexAfterPositionUs == -1)) {
                elapsedRealtime = C.usToMs(this.z.adGroupTimesUs[adGroupIndexAfterPositionUs]);
                if (elapsedRealtime == Long.MIN_VALUE) {
                    elapsedRealtime = this.x;
                }
                if (elapsedRealtime - j < 8000) {
                    this.A = adGroupIndexAfterPositionUs;
                }
            }
        }
        return new VideoProgressUpdate(j, z ? this.x : -1);
    }

    public VideoProgressUpdate getAdProgress() {
        if (this.q == null) {
            return this.s;
        }
        if (this.D == 0 || !this.F) {
            return VideoProgressUpdate.VIDEO_TIME_NOT_READY;
        }
        VideoProgressUpdate videoProgressUpdate;
        long duration = this.q.getDuration();
        if (duration == C.TIME_UNSET) {
            videoProgressUpdate = VideoProgressUpdate.VIDEO_TIME_NOT_READY;
        } else {
            videoProgressUpdate = new VideoProgressUpdate(this.q.getCurrentPosition(), duration);
        }
        return videoProgressUpdate;
    }

    public int getVolume() {
        if (this.q == null) {
            return this.t;
        }
        AudioComponent audioComponent = this.q.getAudioComponent();
        if (audioComponent != null) {
            return (int) (audioComponent.getVolume() * 100.0f);
        }
        TrackSelectionArray currentTrackSelections = this.q.getCurrentTrackSelections();
        int i = 0;
        while (i < this.q.getRendererCount() && i < currentTrackSelections.length) {
            if (this.q.getRendererType(i) == 1 && currentTrackSelections.get(i) != null) {
                return 100;
            }
            i++;
        }
        return 0;
    }

    public void loadAd(String str) {
        try {
            if (this.u == null) {
                Log.w("ImaAdsLoader", "Ignoring loadAd after release");
                return;
            }
            if (this.B == -1) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Unexpected loadAd without LOADED event; assuming ad group index is actually ");
                stringBuilder.append(this.A);
                Log.w("ImaAdsLoader", stringBuilder.toString());
                this.B = this.A;
                this.u.start();
            }
            int a = a(this.B);
            if (a == -1) {
                Log.w("ImaAdsLoader", "Unexpected loadAd in an ad group with no remaining unavailable ads");
                return;
            }
            this.z = this.z.withAdUri(this.B, a, Uri.parse(str));
            i();
        } catch (Exception e) {
            a("loadAd", e);
        }
    }

    public void addCallback(VideoAdPlayerCallback videoAdPlayerCallback) {
        this.k.add(videoAdPlayerCallback);
    }

    public void removeCallback(VideoAdPlayerCallback videoAdPlayerCallback) {
        this.k.remove(videoAdPlayerCallback);
    }

    public void playAd() {
        if (this.u == null) {
            Log.w("ImaAdsLoader", "Ignoring playAd after release");
            return;
        }
        int i = 0;
        switch (this.D) {
            case 0:
                this.I = C.TIME_UNSET;
                this.J = C.TIME_UNSET;
                this.D = 1;
                for (int i2 = 0; i2 < this.k.size(); i2++) {
                    ((VideoAdPlayerCallback) this.k.get(i2)).onPlay();
                }
                if (this.H) {
                    this.H = false;
                    while (i < this.k.size()) {
                        ((VideoAdPlayerCallback) this.k.get(i)).onError();
                        i++;
                    }
                    break;
                }
                break;
            case 1:
                Log.w("ImaAdsLoader", "Unexpected playAd without stopAd");
                break;
            case 2:
                this.D = 1;
                while (i < this.k.size()) {
                    ((VideoAdPlayerCallback) this.k.get(i)).onResume();
                    i++;
                }
                break;
            default:
                throw new IllegalStateException();
        }
        if (this.q == null) {
            Log.w("ImaAdsLoader", "Unexpected playAd while detached");
        } else if (!this.q.getPlayWhenReady()) {
            this.u.pause();
        }
    }

    public void stopAd() {
        if (this.u == null) {
            Log.w("ImaAdsLoader", "Ignoring stopAd after release");
            return;
        }
        if (this.q == null) {
            Log.w("ImaAdsLoader", "Unexpected stopAd while detached");
        }
        if (this.D == 0) {
            Log.w("ImaAdsLoader", "Unexpected stopAd");
            return;
        }
        try {
            g();
        } catch (Exception e) {
            a("stopAd", e);
        }
    }

    public void pauseAd() {
        if (this.D != 0) {
            this.D = 2;
            for (int i = 0; i < this.k.size(); i++) {
                ((VideoAdPlayerCallback) this.k.get(i)).onPause();
            }
        }
    }

    public void resumeAd() {
        a("resumeAd", new IllegalStateException("Unexpected call to resumeAd"));
    }

    public void onTimelineChanged(Timeline timeline, @Nullable Object obj, int i) {
        boolean z = true;
        if (i != 1) {
            if (timeline.getPeriodCount() != 1) {
                z = false;
            }
            Assertions.checkArgument(z);
            this.w = timeline;
            long j = timeline.getPeriod(0, this.j).durationUs;
            this.x = C.usToMs(j);
            if (j != C.TIME_UNSET) {
                this.z = this.z.withContentDurationUs(j);
            }
            d();
        }
    }

    public void onPlayerStateChanged(boolean z, int i) {
        if (this.u != null) {
            if (this.D == 1 && !z) {
                this.u.pause();
            } else if (this.D == 2 && z) {
                this.u.resume();
            } else {
                if (this.D == 0 && i == 2 && z) {
                    h();
                } else if (this.D != 0 && i == 4) {
                    for (int i2 = 0; i2 < this.k.size(); i2++) {
                        ((VideoAdPlayerCallback) this.k.get(i2)).onEnded();
                    }
                }
            }
        }
    }

    public void onPlayerError(ExoPlaybackException exoPlaybackException) {
        if (this.D != 0) {
            for (int i = 0; i < this.k.size(); i++) {
                ((VideoAdPlayerCallback) this.k.get(i)).onError();
            }
        }
    }

    public void onPositionDiscontinuity(int i) {
        if (this.u != null) {
            if (this.F || this.q.isPlayingAd()) {
                d();
            } else {
                h();
                int i2 = 0;
                if (this.E) {
                    while (i2 < this.z.adGroupCount) {
                        if (this.z.adGroupTimesUs[i2] != Long.MIN_VALUE) {
                            this.z = this.z.withSkippedAdGroup(i2);
                        }
                        i2++;
                    }
                    i();
                } else {
                    long currentPosition = this.q.getCurrentPosition();
                    this.w.getPeriod(0, this.j);
                    i = this.j.getAdGroupIndexForPositionUs(C.msToUs(currentPosition));
                    if (i != -1) {
                        this.L = false;
                        this.K = currentPosition;
                        if (i != this.B) {
                            this.H = false;
                        }
                    }
                }
            }
        }
    }

    private void c() {
        AdsRenderingSettings b = this.i.b();
        b.setEnablePreloading(true);
        b.setMimeTypes(this.o);
        if (this.d != -1) {
            b.setLoadVideoTimeout(this.d);
        }
        if (this.f != -1) {
            b.setBitrateKbps(this.f / 1000);
        }
        b.setFocusSkipButtonWhenAvailable(this.e);
        if (this.g != null) {
            b.setUiElements(this.g);
        }
        long[] a = a(this.u.getAdCuePoints());
        this.z = new AdPlaybackState(a);
        long currentPosition = this.q.getCurrentPosition();
        int adGroupIndexForPositionUs = this.z.getAdGroupIndexForPositionUs(C.msToUs(currentPosition));
        int i = 0;
        if (adGroupIndexForPositionUs == 0) {
            this.y = 0;
        } else if (adGroupIndexForPositionUs == -1) {
            this.y = -1;
        } else {
            while (i < adGroupIndexForPositionUs) {
                this.z = this.z.withSkippedAdGroup(i);
                i++;
            }
            int i2 = adGroupIndexForPositionUs - 1;
            b.setPlayAdsAfterTime((((double) (a[adGroupIndexForPositionUs] + a[i2])) / 2.0d) / 1000000.0d);
            this.y = i2;
        }
        if (adGroupIndexForPositionUs != -1 && a(a)) {
            this.K = currentPosition;
        }
        this.u.init(b);
        i();
    }

    private void a(AdEvent adEvent) {
        Ad ad = adEvent.getAd();
        StringBuilder stringBuilder;
        switch (adEvent.getType()) {
            case LOADED:
                AdPodInfo adPodInfo = ad.getAdPodInfo();
                int podIndex = adPodInfo.getPodIndex();
                this.B = podIndex == -1 ? this.z.adGroupCount - 1 : podIndex + this.y;
                adPodInfo.getAdPosition();
                int totalAds = adPodInfo.getTotalAds();
                this.u.start();
                podIndex = this.z.adGroups[this.B].count;
                if (totalAds != podIndex) {
                    if (podIndex == -1) {
                        this.z = this.z.withAdCount(this.B, totalAds);
                        i();
                    } else {
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append("Unexpected ad count in LOADED, ");
                        stringBuilder2.append(totalAds);
                        stringBuilder2.append(", expected ");
                        stringBuilder2.append(podIndex);
                        Log.w("ImaAdsLoader", stringBuilder2.toString());
                    }
                }
                if (this.B != this.A) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Expected ad group index ");
                    stringBuilder.append(this.A);
                    stringBuilder.append(", actual ad group index ");
                    stringBuilder.append(this.B);
                    Log.w("ImaAdsLoader", stringBuilder.toString());
                    this.A = this.B;
                    break;
                }
                break;
            case CONTENT_PAUSE_REQUESTED:
                this.C = true;
                f();
                break;
            case TAPPED:
                if (this.p != null) {
                    this.p.onAdTapped();
                    break;
                }
                break;
            case CLICKED:
                if (this.p != null) {
                    this.p.onAdClicked();
                    break;
                }
                break;
            case CONTENT_RESUME_REQUESTED:
                this.C = false;
                e();
                break;
            case LOG:
                Map adData = adEvent.getAdData();
                stringBuilder = new StringBuilder();
                stringBuilder.append("AdEvent: ");
                stringBuilder.append(adData);
                String stringBuilder3 = stringBuilder.toString();
                Log.i("ImaAdsLoader", stringBuilder3);
                if ("adLoadError".equals(adData.get("type"))) {
                    a(new IOException(stringBuilder3));
                    break;
                }
                break;
        }
        if (this.M != null) {
            this.M.a(adEvent);
        }
    }

    private void d() {
        boolean z = this.F;
        int i = this.G;
        this.F = this.q.isPlayingAd();
        this.G = this.F ? this.q.getCurrentAdIndexInAdGroup() : -1;
        int i2 = 0;
        i = (!z || this.G == i) ? 0 : 1;
        if (i != 0) {
            while (i2 < this.k.size()) {
                ((VideoAdPlayerCallback) this.k.get(i2)).onEnded();
                i2++;
            }
        }
        if (!this.E && !z && this.F && this.D == 0) {
            int currentAdGroupIndex = this.q.getCurrentAdGroupIndex();
            this.I = SystemClock.elapsedRealtime();
            this.J = C.usToMs(this.z.adGroupTimesUs[currentAdGroupIndex]);
            if (this.J == Long.MIN_VALUE) {
                this.J = this.x;
            }
        }
    }

    private void e() {
        if (this.D != 0) {
            this.D = 0;
        }
        if (this.B != -1) {
            this.z = this.z.withSkippedAdGroup(this.B);
            this.B = -1;
            i();
        }
    }

    private void f() {
        this.D = 0;
        if (this.L) {
            this.K = C.TIME_UNSET;
            this.L = false;
        }
    }

    private void g() {
        this.D = 0;
        this.z = this.z.withPlayedAd(this.B, this.z.adGroups[this.B].getFirstAdIndexToPlay()).withAdResumePositionUs(0);
        i();
        if (!this.F) {
            this.B = -1;
        }
    }

    private void a(Exception exception) {
        int i = this.B == -1 ? this.A : this.B;
        if (i != -1) {
            AdGroup adGroup = this.z.adGroups[i];
            if (adGroup.count == -1) {
                this.z = this.z.withAdCount(i, Math.max(1, adGroup.states.length));
                adGroup = this.z.adGroups[i];
            }
            for (int i2 = 0; i2 < adGroup.count; i2++) {
                if (adGroup.states[i2] == 0) {
                    this.z = this.z.withAdLoadError(i, i2);
                }
            }
            i();
            if (this.v == null) {
                this.v = AdLoadException.createForAdGroup(exception, i);
            }
            this.K = C.TIME_UNSET;
            this.I = C.TIME_UNSET;
        }
    }

    private void a(int i, int i2, Exception exception) {
        if (this.u == null) {
            Log.w("ImaAdsLoader", "Ignoring ad prepare error after release");
            return;
        }
        if (this.D == 0) {
            this.I = SystemClock.elapsedRealtime();
            this.J = C.usToMs(this.z.adGroupTimesUs[i]);
            if (this.J == Long.MIN_VALUE) {
                this.J = this.x;
            }
            this.H = true;
        } else {
            int i3 = 0;
            if (i2 > this.G) {
                for (int i4 = 0; i4 < this.k.size(); i4++) {
                    ((VideoAdPlayerCallback) this.k.get(i4)).onEnded();
                }
            }
            this.G = this.z.adGroups[i].getFirstAdIndexToPlay();
            while (i3 < this.k.size()) {
                ((VideoAdPlayerCallback) this.k.get(i3)).onError();
                i3++;
            }
        }
        this.z = this.z.withAdLoadError(i, i2);
        i();
    }

    private void h() {
        if (this.x != C.TIME_UNSET && this.K == C.TIME_UNSET && this.q.getContentPosition() + DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS >= this.x && !this.E) {
            this.m.contentComplete();
            this.E = true;
            this.A = this.z.getAdGroupIndexForPositionUs(C.msToUs(this.x));
        }
    }

    public void b() {
        stopAd();
        AdEvent anonymousClass1 = new AdEvent() {
            public Ad getAd() {
                return null;
            }

            public Map<String, String> getAdData() {
                return null;
            }

            public AdEventType getType() {
                return AdEventType.CONTENT_RESUME_REQUESTED;
            }
        };
        if (this.M != null) {
            this.M.a(anonymousClass1);
        }
        release();
    }

    private void i() {
        if (this.p != null) {
            this.p.onAdPlaybackState(this.z);
        }
    }

    private int a(int i) {
        int[] iArr = this.z.adGroups[i].states;
        int i2 = 0;
        while (i2 < iArr.length && iArr[i2] != 0) {
            i2++;
        }
        return i2 == iArr.length ? -1 : i2;
    }

    private void j() {
        if (this.v != null && this.p != null) {
            this.p.onAdLoadError(this.v, new DataSpec(this.a));
            this.v = null;
        }
    }

    private void a(String str, Exception exception) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Internal error in ");
        stringBuilder.append(str);
        str = stringBuilder.toString();
        Log.e("ImaAdsLoader", str, exception);
        if (this.z == null) {
            this.z = AdPlaybackState.NONE;
        } else {
            for (int i = 0; i < this.z.adGroupCount; i++) {
                this.z = this.z.withSkippedAdGroup(i);
            }
        }
        i();
        if (this.p != null) {
            this.p.onAdLoadError(AdLoadException.createForUnexpected(new RuntimeException(str, exception)), new DataSpec(this.a));
        }
    }

    private static long[] a(List<Float> list) {
        if (list.isEmpty()) {
            return new long[]{0};
        }
        int size = list.size();
        long[] jArr = new long[size];
        int i = 0;
        int i2 = i;
        while (i < size) {
            double floatValue = (double) ((Float) list.get(i)).floatValue();
            if (floatValue == -1.0d) {
                jArr[size - 1] = Long.MIN_VALUE;
            } else {
                int i3 = i2 + 1;
                jArr[i2] = (long) (1000000.0d * floatValue);
                i2 = i3;
            }
            i++;
        }
        Arrays.sort(jArr, 0, i2);
        return jArr;
    }

    private static boolean a(AdError adError) {
        return adError.getErrorCode() == AdErrorCode.VAST_LINEAR_ASSET_MISMATCH;
    }

    private static boolean a(long[] jArr) {
        boolean z = false;
        int length = jArr.length;
        if (length == 1) {
            if (!(jArr[0] == 0 || jArr[0] == Long.MIN_VALUE)) {
                z = true;
            }
            return z;
        } else if (length != 2) {
            return true;
        } else {
            if (!(jArr[0] == 0 && jArr[1] == Long.MIN_VALUE)) {
                z = true;
            }
            return z;
        }
    }
}
