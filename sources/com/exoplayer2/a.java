package com.exoplayer2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.constants.Constants;
import com.exoplayer2.ui.VideoPlayerView;
import com.exoplayer2.upstream.EncryptedFileDataSource2;
import com.exoplayer2.upstream.FileDataSource;
import com.exoplayer2.upstream.cache.CacheDataSink;
import com.exoplayer2.upstream.cache.b;
import com.exoplayer2.upstream.cache.m;
import com.exoplayer2.upstream.cache.o;
import com.gaana.AudioAdActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.models.Tracks.Track;
import com.google.ads.interactivemedia.v3.api.AdEvent;
import com.google.ads.interactivemedia.v3.api.AdEvent.AdEventType;
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
import com.google.android.exoplayer2.audio.AudioAttributes;
import com.google.android.exoplayer2.audio.AudioListener;
import com.google.android.exoplayer2.audio.AudioListener$$CC;
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
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import com.logging.GaanaLogger.PLAYOUT_SOURCE;
import com.managers.PlayerManager;
import com.managers.PlayerManager.PlayerType;
import com.managers.ad;
import com.managers.ai;
import com.managers.aj;
import com.managers.am;
import com.managers.f;
import com.managers.q;
import com.models.PlayerTrack;
import com.til.colombia.android.internal.e;
import com.utilities.Util;
import com.utilities.d;
import com.utilities.h;
import com.utilities.i;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class a implements EventListener, AudioListener {
    public static final DefaultBandwidthMeter a = new DefaultBandwidthMeter();
    public static final m b = new m(314572800);
    private ClickListener A = new ClickListener() {
        public void onCompanionAdClick() {
            a.this.c(false);
            a.this.c();
        }
    };
    private final a B = new a() {
        public void a(int i) {
        }

        public void a(PLAYOUT_SOURCE playout_source, boolean z) {
        }

        public void a(Exception exception) {
        }

        public void a(boolean z, int i) {
        }

        public void a(AdEvent adEvent) {
            switch (AnonymousClass6.a[adEvent.getType().ordinal()]) {
                case 3:
                    if (!a.this.a()) {
                        f.v().g(false);
                        a.this.c(true);
                        if (!a.this.t) {
                            a.this.p();
                            f.v().e(true);
                            break;
                        }
                        f.v().f(true);
                        break;
                    }
                    break;
                case 4:
                    f.v().g(adEvent.getAd().isSkippable());
                    break;
                case 5:
                    a.this.c(false);
                    a.this.c();
                    break;
                case 6:
                case 7:
                    a.this.c(false);
                    f.v().f(false);
                    f.v().g(false);
                    break;
            }
            Iterator it = a.this.s.iterator();
            while (it.hasNext()) {
                ((a) it.next()).a(adEvent);
            }
        }
    };
    VideoPlayerView c = null;
    FrameLayout d = null;
    public ViewGroup e;
    Period f = new Period();
    private Factory g;
    private SimpleExoPlayer h;
    private b i;
    private DefaultTrackSelector j;
    private boolean k;
    private TrackGroupArray l;
    private int m;
    private long n;
    private int o;
    private boolean p;
    private Uri q;
    private Context r;
    private final CopyOnWriteArrayList<a> s;
    private boolean t;
    private com.exoplayer2.a.a.a u;
    private Uri v;
    private boolean w = false;
    private boolean x = false;
    private boolean y;
    private boolean z = false;

    public interface a {
        void a(int i);

        void a(AdEvent adEvent);

        void a(PLAYOUT_SOURCE playout_source, boolean z);

        void a(Exception exception);

        void a(boolean z, int i);
    }

    /* renamed from: com.exoplayer2.a$6 */
    static /* synthetic */ class AnonymousClass6 {
        static final /* synthetic */ int[] a = new int[AdEventType.values().length];

        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x004b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|16) */
        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|16) */
        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|(3:13|14|16)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|(3:13|14|16)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|(3:13|14|16)) */
        static {
            /*
            r0 = com.google.ads.interactivemedia.v3.api.AdEvent.AdEventType.values();
            r0 = r0.length;
            r0 = new int[r0];
            a = r0;
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r1 = com.google.ads.interactivemedia.v3.api.AdEvent.AdEventType.LOADED;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0014 }
            r2 = 1;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x0014 }
        L_0x0014:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x001f }
            r1 = com.google.ads.interactivemedia.v3.api.AdEvent.AdEventType.CONTENT_PAUSE_REQUESTED;	 Catch:{ NoSuchFieldError -> 0x001f }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x001f }
            r2 = 2;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x001f }
        L_0x001f:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x002a }
            r1 = com.google.ads.interactivemedia.v3.api.AdEvent.AdEventType.STARTED;	 Catch:{ NoSuchFieldError -> 0x002a }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x002a }
            r2 = 3;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x002a }
        L_0x002a:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x0035 }
            r1 = com.google.ads.interactivemedia.v3.api.AdEvent.AdEventType.MIDPOINT;	 Catch:{ NoSuchFieldError -> 0x0035 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0035 }
            r2 = 4;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x0035 }
        L_0x0035:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x0040 }
            r1 = com.google.ads.interactivemedia.v3.api.AdEvent.AdEventType.CLICKED;	 Catch:{ NoSuchFieldError -> 0x0040 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0040 }
            r2 = 5;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x0040 }
        L_0x0040:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x004b }
            r1 = com.google.ads.interactivemedia.v3.api.AdEvent.AdEventType.SKIPPED;	 Catch:{ NoSuchFieldError -> 0x004b }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x004b }
            r2 = 6;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x004b }
        L_0x004b:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x0056 }
            r1 = com.google.ads.interactivemedia.v3.api.AdEvent.AdEventType.CONTENT_RESUME_REQUESTED;	 Catch:{ NoSuchFieldError -> 0x0056 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0056 }
            r2 = 7;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x0056 }
        L_0x0056:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.exoplayer2.a$AnonymousClass6.<clinit>():void");
        }
    }

    public void onAudioAttributesChanged(AudioAttributes audioAttributes) {
        AudioListener$$CC.onAudioAttributesChanged(this, audioAttributes);
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

    public void onVolumeChanged(float f) {
        AudioListener$$CC.onVolumeChanged(this, f);
    }

    public boolean a() {
        if ((Util.c() || !m()) && (!Util.c() || m())) {
            return false;
        }
        c();
        return true;
    }

    private void c(boolean z) {
        if (ai.a() != null) {
            GaanaActivity gaanaActivity = (GaanaActivity) ai.a();
            if (z) {
                if (this.c != null) {
                    gaanaActivity.addOverlayFrameLayout(this.c);
                    this.c.setPlayer(this.h);
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

    public a(Context context, Uri uri) {
        this.r = context;
        this.s = new CopyOnWriteArrayList();
        a(context);
        b();
        this.q = uri;
    }

    public void b() {
        Constants.ej.a(new File(ContextCompat.getExternalFilesDirs(this.r, null)[0].getAbsolutePath(), "media_cache"));
    }

    public void a(a aVar) {
        this.s.add(aVar);
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
        return new com.exoplayer2.upstream.cache.a(oVar2, a(defaultBandwidthMeter, true).createDataSource(), o() ? new EncryptedFileDataSource2() : new FileDataSource(), new CacheDataSink(oVar2, 10485760), 3, z2, new com.exoplayer2.upstream.cache.a.a() {
            public void a(int i) {
            }

            public void a(long j, long j2) {
                if (!a.this.x) {
                    Iterator it = a.this.s.iterator();
                    while (it.hasNext()) {
                        ((a) it.next()).a(PLAYOUT_SOURCE.CACHE, a.this.z);
                    }
                    a.this.x = true;
                }
            }
        }, null);
    }

    private Factory a(boolean z, String str, boolean z2, String str2) {
        DefaultBandwidthMeter defaultBandwidthMeter = null;
        File file = ContextCompat.getExternalFilesDirs(this.r, null)[0];
        if (PlayerManager.a(this.r).m() == PlayerType.GAANA_RADIO || Constants.E == 0 || Constants.D == 1 || this.y || file == null) {
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

    private boolean o() {
        return d.b();
    }

    private Factory a(DefaultBandwidthMeter defaultBandwidthMeter, boolean z) {
        return new com.exoplayer2.upstream.b(this.r, defaultBandwidthMeter, ((GaanaApplication) this.r).buildDataSourceFactory(defaultBandwidthMeter, z));
    }

    private Factory a(DefaultBandwidthMeter defaultBandwidthMeter, boolean z, boolean z2) {
        return new com.exoplayer2.upstream.b(this.r, defaultBandwidthMeter, ((GaanaApplication) this.r).buildDataSourceFactory(defaultBandwidthMeter, z2), z);
    }

    private void a(Context context) {
        if ((this.h == null ? 1 : 0) != 0) {
            RenderersFactory defaultRenderersFactory = new DefaultRenderersFactory(context, null, 0);
            this.j = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(a));
            this.l = null;
            this.i = new com.exoplayer2.b.a().a((int) C.DEFAULT_VIDEO_BUFFER_SIZE).a(false).a();
            this.h = ExoPlayerFactory.newSimpleInstance(context, defaultRenderersFactory, this.j, this.i);
            this.h.addListener(this);
            this.h.addAudioListener(this);
            r();
        }
    }

    public void a(Uri[] uriArr, Object obj, int i, boolean z, boolean z2) {
        this.x = false;
        CharSequence charSequence = null;
        PlayerTrack playerTrack = (obj == null || !(obj instanceof PlayerTrack)) ? null : (PlayerTrack) obj;
        if (playerTrack == null) {
            this.g = a(true, "", z, "1");
        } else if (!this.y || playerTrack.b() == null) {
            this.g = a(true, playerTrack.h(), z, "1");
        } else {
            this.g = a(true, playerTrack.b().getVideoId(), z, Double.toString(playerTrack.b().getContentSource()));
        }
        uriArr[0] = this.q;
        MediaSource[] mediaSourceArr = new MediaSource[uriArr.length];
        for (int i2 = 0; i2 < uriArr.length; i2++) {
            mediaSourceArr[i2] = a(uriArr[i2], null);
        }
        MediaSource concatenatingMediaSource = mediaSourceArr.length == 1 ? mediaSourceArr[0] : new ConcatenatingMediaSource(mediaSourceArr);
        this.t = Util.c();
        if (f.v().a(this.t, z2, i)) {
            if (this.t) {
                this.w = true;
            } else {
                this.w = false;
            }
            f.v().i(false);
            charSequence = f.v().d(this.t);
            if (!TextUtils.isEmpty(charSequence)) {
                StringBuilder stringBuilder;
                StringBuilder stringBuilder2;
                StringBuilder stringBuilder3;
                StringBuilder stringBuilder4 = new StringBuilder();
                if (Constants.cj.get("ad_type") == null || !((Boolean) Constants.cj.get("ad_type")).booleanValue()) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("ad_type=");
                    stringBuilder.append(i);
                    stringBuilder4.append(stringBuilder.toString());
                }
                if (Constants.cj.get("entity_type") == null || !((Boolean) Constants.cj.get("entity_type")).booleanValue()) {
                    stringBuilder4.append("&entity_type=TR");
                }
                if (Constants.cj.get("track_id") == null || !((Boolean) Constants.cj.get("track_id")).booleanValue()) {
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("&track_id=");
                    stringBuilder2.append(playerTrack != null ? playerTrack.h() : "NA");
                    stringBuilder4.append(stringBuilder2.toString());
                }
                if (Constants.cj.get("current_network") == null || !((Boolean) Constants.cj.get("current_network")).booleanValue()) {
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("&current_network=");
                    stringBuilder2.append(Constants.dC);
                    stringBuilder4.append(stringBuilder2.toString());
                }
                if (!TextUtils.isEmpty(GaanaApplication.getInstance().getAdsEnv()) && (Constants.cj.get("demo") == null || !((Boolean) Constants.cj.get("demo")).booleanValue())) {
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("&demo=");
                    stringBuilder2.append(GaanaApplication.getInstance().getAdsEnv());
                    stringBuilder4.append(stringBuilder2.toString());
                }
                String a = f.v().a(playerTrack);
                if (!TextUtils.isEmpty(a)) {
                    stringBuilder4.append(a);
                }
                if (Constants.cj.get("source_name") == null || !((Boolean) Constants.cj.get("source_name")).booleanValue()) {
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("&source_name=");
                    stringBuilder2.append(playerTrack != null ? playerTrack.f() : "NA");
                    stringBuilder4.append(stringBuilder2.toString());
                }
                HashMap I = f.v().I();
                if (I != null) {
                    if (Constants.cj.get(e.K) == null || !((Boolean) Constants.cj.get(e.K)).booleanValue()) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("&age=");
                        stringBuilder.append(I.get(e.K));
                        stringBuilder4.append(stringBuilder.toString());
                    }
                    if (Constants.cj.get("gender") == null || !((Boolean) Constants.cj.get("gender")).booleanValue()) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("&gender=");
                        stringBuilder.append(I.get("gender"));
                        stringBuilder4.append(stringBuilder.toString());
                    }
                    if (Constants.cj.get("lan") == null || !((Boolean) Constants.cj.get("lan")).booleanValue()) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("&lan=");
                        stringBuilder.append(I.get("lan"));
                        stringBuilder4.append(stringBuilder.toString());
                    }
                }
                if (playerTrack != null) {
                    Track b = playerTrack.b();
                    if (b != null) {
                        if (Constants.cj.get("Artist_ID") == null || !((Boolean) Constants.cj.get("Artist_ID")).booleanValue()) {
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("&Artist_ID=");
                            stringBuilder.append(b.getTargetingArtistIds());
                            stringBuilder4.append(stringBuilder.toString());
                        }
                        if (Constants.cj.get("Album_ID") == null || !((Boolean) Constants.cj.get("Album_ID")).booleanValue()) {
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("&Album_ID=");
                            stringBuilder.append(b.getAlbumId());
                            stringBuilder4.append(stringBuilder.toString());
                        }
                        if (Constants.cj.get("Tag_ID") == null || !((Boolean) Constants.cj.get("Tag_ID")).booleanValue()) {
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("&Tag_ID=");
                            stringBuilder.append(b.getTagID());
                            stringBuilder4.append(stringBuilder.toString());
                        }
                        if (Constants.cj.get("Language_ID") == null || !((Boolean) Constants.cj.get("Language_ID")).booleanValue()) {
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("&Language_ID=");
                            stringBuilder.append(b.getLanguage());
                            stringBuilder4.append(stringBuilder.toString());
                        }
                    }
                }
                if (Constants.cj.get("app_version") == null || !((Boolean) Constants.cj.get("app_version")).booleanValue()) {
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("&app_version=");
                    stringBuilder2.append(am.a().b());
                    stringBuilder4.append(stringBuilder2.toString());
                }
                if (!((Constants.cj.get("section_name") != null && ((Boolean) Constants.cj.get("section_name")).booleanValue()) || playerTrack == null || playerTrack.g() == null)) {
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("&section_name=");
                    stringBuilder2.append(playerTrack.g().replaceAll("\\s+", "_"));
                    stringBuilder4.append(stringBuilder2.toString());
                }
                if (Constants.cj.get("voice") == null || !((Boolean) Constants.cj.get("voice")).booleanValue()) {
                    stringBuilder3 = new StringBuilder();
                    stringBuilder3.append("&voice=");
                    stringBuilder3.append(h.b(GaanaApplication.getContext()) ? "yes" : "no");
                    stringBuilder4.append(stringBuilder3.toString());
                }
                if (this.t || Constants.cj.get("wh") == null || !((Boolean) Constants.cj.get("wh")).booleanValue()) {
                    stringBuilder3 = new StringBuilder();
                    stringBuilder3.append("&wh=");
                    stringBuilder3.append(f.v().j());
                    stringBuilder3.append(AvidJSONUtil.KEY_X);
                    stringBuilder3.append(f.v().k());
                    stringBuilder4.append(stringBuilder3.toString());
                }
                if (Constants.cj.get("user_subtype") == null || !((Boolean) Constants.cj.get("user_subtype")).booleanValue()) {
                    stringBuilder3 = new StringBuilder();
                    stringBuilder3.append("&user_subtype=");
                    stringBuilder3.append(GaanaApplication.getInstance().getUserAccountStatus());
                    stringBuilder4.append(stringBuilder3.toString());
                }
                String e = q.a().e();
                if (!TextUtils.isEmpty(e) && (Constants.cj.get("sg") == null || !((Boolean) Constants.cj.get("sg")).booleanValue())) {
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("&sg=");
                    stringBuilder2.append(e);
                    stringBuilder4.append(stringBuilder2.toString());
                }
                try {
                    stringBuilder3 = new StringBuilder();
                    stringBuilder3.append("&cust_params=");
                    stringBuilder3.append(URLEncoder.encode(stringBuilder4.toString(), "UTF-8"));
                    stringBuilder3.append("&");
                    e = stringBuilder3.toString();
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(charSequence);
                    stringBuilder2.append(e);
                    charSequence = stringBuilder2.toString();
                } catch (UnsupportedEncodingException e2) {
                    ThrowableExtension.printStackTrace(e2);
                }
            }
        }
        if (TextUtils.isEmpty(charSequence)) {
            d();
        } else {
            Uri parse = Uri.parse(charSequence);
            if (!parse.equals(this.v)) {
                d();
                this.v = parse;
            }
            MediaSource a2 = a(concatenatingMediaSource, Uri.parse(charSequence));
            if (a2 != null) {
                concatenatingMediaSource = a2;
            } else {
                aj.a().a(this.r, "IMA not loaded");
            }
        }
        int i3 = this.m != -1 ? 1 : 0;
        if (i3 != 0) {
            this.h.seekTo(this.m, this.n);
        }
        this.h.prepare(concatenatingMediaSource, i3 ^ 1, false);
        r();
        this.k = false;
    }

    private boolean b(Context context) {
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        if (VERSION.SDK_INT >= 20) {
            return powerManager.isInteractive();
        }
        return powerManager.isScreenOn();
    }

    private void p() {
        if (b(this.r) && Util.c()) {
            Intent intent = new Intent(GaanaApplication.getContext(), AudioAdActivity.class);
            intent.putExtra("APP_OPEN", true);
            intent.setFlags(C.ENCODING_PCM_MU_LAW);
            this.r.startActivity(intent);
        }
    }

    @Nullable
    private MediaSource a(MediaSource mediaSource, Uri uri) {
        try {
            this.e = new FrameLayout(this.r);
            if (this.u == null) {
                Context a = ai.a();
                this.u = new com.exoplayer2.a.a.a(this.r, uri);
                this.u.a(this.B);
                if (this.t && a != null && (a instanceof GaanaActivity)) {
                    this.c = new VideoPlayerView(a);
                    this.c.setLayoutParams(new LayoutParams(-1, -1));
                    this.c.setBackgroundColor(a.getResources().getColor(R.color.black));
                    this.c.b();
                    this.c.setResizeMode(0);
                    this.d = new FrameLayout(a);
                    this.d.setLayoutParams(new LayoutParams(Util.a(a, f.v().j()), Util.a(a, f.v().k())));
                    this.c.getOverlayFrameLayout().addView(this.e);
                    Integer[][] numArr = new Integer[1][];
                    numArr[0] = new Integer[]{Integer.valueOf(f.v().j()), Integer.valueOf(f.v().k())};
                    Collection arrayList = new ArrayList();
                    for (int i = 0; i < numArr.length; i++) {
                        CompanionAdSlot createCompanionAdSlot = ImaSdkFactory.getInstance().createCompanionAdSlot();
                        createCompanionAdSlot.setContainer(this.d);
                        createCompanionAdSlot.addClickListener(this.A);
                        createCompanionAdSlot.setSize(numArr[i][0].intValue(), numArr[i][1].intValue());
                        arrayList.add(createCompanionAdSlot);
                    }
                    this.u.a(arrayList);
                }
            }
            return new AdsMediaSource(mediaSource, new MediaSourceFactory() {
                public MediaSource createMediaSource(Uri uri) {
                    return a.this.a(uri, null);
                }

                public int[] getSupportedTypes() {
                    return new int[]{0, 1, 2, 3};
                }
            }, this.u, this.e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void c() {
        if (this.u != null) {
            this.u.b();
        }
    }

    public void d() {
        if (this.u != null) {
            this.u.release();
            this.u = null;
            this.v = null;
            if (this.c != null) {
                this.c.getOverlayFrameLayout().removeAllViews();
            }
        }
    }

    public void onPlayerStateChanged(boolean z, int i) {
        r();
    }

    public void onPlayerError(ExoPlaybackException exoPlaybackException) {
        Iterator it = this.s.iterator();
        while (it.hasNext()) {
            ((a) it.next()).a((Exception) exoPlaybackException);
        }
    }

    public void onAudioSessionId(int i) {
        Iterator it = this.s.iterator();
        while (it.hasNext()) {
            ((a) it.next()).a(i);
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
                return new HlsMediaSource.Factory(this.g).setAllowChunklessPreparation(true).createMediaSource(uri);
            case 3:
                if (com.google.android.exoplayer2.util.Util.isLocalFileUri(uri) && uri.getPath().contains(i.a)) {
                    return new ExtractorMediaSource.Factory(a(null, true, false)).createMediaSource(uri);
                }
                return new ExtractorMediaSource.Factory(this.g).createMediaSource(uri);
            default:
                stringBuilder = new StringBuilder();
                stringBuilder.append("Unsupported type: ");
                stringBuilder.append(inferContentType);
                throw new IllegalStateException(stringBuilder.toString());
        }
    }

    public void a(boolean z) {
        this.i.a(z);
        this.z = z;
    }

    public void a(boolean z, boolean z2) {
        this.h.setPlayWhenReady(z);
        if (z2 && this.u != null) {
            d();
        }
        r();
    }

    private void q() {
        this.m = this.h.getCurrentWindowIndex();
        this.n = Math.max(0, this.h.getCurrentPosition());
    }

    public void a(long j) {
        this.h.seekTo(j);
        q();
    }

    public void e() {
        this.h.stop(true);
        this.h.release();
    }

    public int f() {
        return this.h.getPlaybackState();
    }

    public long g() {
        long currentPosition = this.h.getCurrentPosition();
        if (PlayerManager.a(this.r).m() != PlayerType.GAANA_RADIO || !ad.a(this.r).o().booleanValue()) {
            return currentPosition;
        }
        Timeline currentTimeline = this.h.getCurrentTimeline();
        return !currentTimeline.isEmpty() ? currentPosition - currentTimeline.getPeriod(this.h.getCurrentPeriodIndex(), this.f).getPositionInWindowMs() : currentPosition;
    }

    public long h() {
        return this.h.getDuration();
    }

    public int i() {
        return this.h.getBufferedPercentage();
    }

    public boolean j() {
        return this.h.getPlayWhenReady();
    }

    private void r() {
        boolean playWhenReady = this.h.getPlayWhenReady();
        int f = f();
        if (this.p != playWhenReady || this.o != f) {
            this.p = playWhenReady;
            this.o = f;
            Iterator it = this.s.iterator();
            while (it.hasNext()) {
                ((a) it.next()).a(playWhenReady, f);
            }
        }
    }

    public int k() {
        return this.h != null ? this.h.getAudioSessionId() : 0;
    }

    public void a(float f) {
        if (this.h != null) {
            this.h.setVolume(f);
        }
    }

    public com.exoplayer2.a.a.a l() {
        return this.u;
    }

    public boolean m() {
        return this.w;
    }

    public SimpleExoPlayer n() {
        return this.h;
    }

    public void b(boolean z) {
        this.y = z;
    }
}
