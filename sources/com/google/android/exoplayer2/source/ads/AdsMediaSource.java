package com.google.android.exoplayer2.source.ads;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.Timeline.Period;
import com.google.android.exoplayer2.source.CompositeMediaSource;
import com.google.android.exoplayer2.source.DeferredMediaPeriod;
import com.google.android.exoplayer2.source.DeferredMediaPeriod.PrepareErrorListener;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSource.MediaPeriodId;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DataSource.Factory;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class AdsMediaSource extends CompositeMediaSource<MediaPeriodId> {
    private static final MediaPeriodId DUMMY_CONTENT_MEDIA_PERIOD_ID = new MediaPeriodId(new Object());
    private MediaSource[][] adGroupMediaSources;
    private Timeline[][] adGroupTimelines;
    private final MediaSourceFactory adMediaSourceFactory;
    private AdPlaybackState adPlaybackState;
    private final ViewGroup adUiViewGroup;
    private final AdsLoader adsLoader;
    private ComponentListener componentListener;
    private Object contentManifest;
    private final MediaSource contentMediaSource;
    private Timeline contentTimeline;
    private final Map<MediaSource, List<DeferredMediaPeriod>> deferredMediaPeriodByAdMediaSource;
    @Nullable
    private final Handler eventHandler;
    @Nullable
    private final EventListener eventListener;
    private final Handler mainHandler;
    private final Period period;

    public interface MediaSourceFactory {
        MediaSource createMediaSource(Uri uri);

        int[] getSupportedTypes();
    }

    public static final class AdLoadException extends IOException {
        public static final int TYPE_AD = 0;
        public static final int TYPE_AD_GROUP = 1;
        public static final int TYPE_ALL_ADS = 2;
        public static final int TYPE_UNEXPECTED = 3;
        public final int type;

        @Documented
        @Retention(RetentionPolicy.SOURCE)
        public @interface Type {
        }

        public static AdLoadException createForAd(Exception exception) {
            return new AdLoadException(0, exception);
        }

        public static AdLoadException createForAdGroup(Exception exception, int i) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Failed to load ad group ");
            stringBuilder.append(i);
            return new AdLoadException(1, new IOException(stringBuilder.toString(), exception));
        }

        public static AdLoadException createForAllAds(Exception exception) {
            return new AdLoadException(2, exception);
        }

        public static AdLoadException createForUnexpected(RuntimeException runtimeException) {
            return new AdLoadException(3, runtimeException);
        }

        private AdLoadException(int i, Exception exception) {
            super(exception);
            this.type = i;
        }

        public RuntimeException getRuntimeExceptionForUnexpected() {
            Assertions.checkState(this.type == 3);
            return (RuntimeException) getCause();
        }
    }

    private final class AdPrepareErrorListener implements PrepareErrorListener {
        private final int adGroupIndex;
        private final int adIndexInAdGroup;
        private final Uri adUri;

        public AdPrepareErrorListener(Uri uri, int i, int i2) {
            this.adUri = uri;
            this.adGroupIndex = i;
            this.adIndexInAdGroup = i2;
        }

        public void onPrepareError(MediaPeriodId mediaPeriodId, IOException iOException) {
            AdsMediaSource.this.createEventDispatcher(mediaPeriodId).loadError(new DataSpec(this.adUri), this.adUri, Collections.emptyMap(), 6, -1, 0, 0, AdLoadException.createForAd(iOException), true);
            AdsMediaSource.this.mainHandler.post(new AdsMediaSource$AdPrepareErrorListener$$Lambda$0(this, iOException));
        }

        /* Access modifiers changed, original: final|synthetic */
        public final /* synthetic */ void lambda$onPrepareError$0$AdsMediaSource$AdPrepareErrorListener(IOException iOException) {
            AdsMediaSource.this.adsLoader.handlePrepareError(this.adGroupIndex, this.adIndexInAdGroup, iOException);
        }
    }

    private final class ComponentListener implements com.google.android.exoplayer2.source.ads.AdsLoader.EventListener {
        private final Handler playerHandler = new Handler();
        private volatile boolean released;

        public void release() {
            this.released = true;
            this.playerHandler.removeCallbacksAndMessages(null);
        }

        public void onAdPlaybackState(AdPlaybackState adPlaybackState) {
            if (!this.released) {
                this.playerHandler.post(new AdsMediaSource$ComponentListener$$Lambda$0(this, adPlaybackState));
            }
        }

        /* Access modifiers changed, original: final|synthetic */
        public final /* synthetic */ void lambda$onAdPlaybackState$0$AdsMediaSource$ComponentListener(AdPlaybackState adPlaybackState) {
            if (!this.released) {
                AdsMediaSource.this.onAdPlaybackState(adPlaybackState);
            }
        }

        public void onAdClicked() {
            if (!(this.released || AdsMediaSource.this.eventHandler == null || AdsMediaSource.this.eventListener == null)) {
                AdsMediaSource.this.eventHandler.post(new AdsMediaSource$ComponentListener$$Lambda$1(this));
            }
        }

        /* Access modifiers changed, original: final|synthetic */
        public final /* synthetic */ void lambda$onAdClicked$1$AdsMediaSource$ComponentListener() {
            if (!this.released) {
                AdsMediaSource.this.eventListener.onAdClicked();
            }
        }

        public void onAdTapped() {
            if (!(this.released || AdsMediaSource.this.eventHandler == null || AdsMediaSource.this.eventListener == null)) {
                AdsMediaSource.this.eventHandler.post(new AdsMediaSource$ComponentListener$$Lambda$2(this));
            }
        }

        /* Access modifiers changed, original: final|synthetic */
        public final /* synthetic */ void lambda$onAdTapped$2$AdsMediaSource$ComponentListener() {
            if (!this.released) {
                AdsMediaSource.this.eventListener.onAdTapped();
            }
        }

        public void onAdLoadError(AdLoadException adLoadException, DataSpec dataSpec) {
            if (!this.released) {
                DataSpec dataSpec2 = dataSpec;
                AdsMediaSource.this.createEventDispatcher(null).loadError(dataSpec2, dataSpec2.uri, Collections.emptyMap(), 6, -1, 0, 0, adLoadException, true);
                if (!(AdsMediaSource.this.eventHandler == null || AdsMediaSource.this.eventListener == null)) {
                    AdsMediaSource.this.eventHandler.post(new AdsMediaSource$ComponentListener$$Lambda$3(this, adLoadException));
                }
            }
        }

        /* Access modifiers changed, original: final|synthetic */
        public final /* synthetic */ void lambda$onAdLoadError$3$AdsMediaSource$ComponentListener(AdLoadException adLoadException) {
            if (!this.released) {
                if (adLoadException.type == 3) {
                    AdsMediaSource.this.eventListener.onInternalAdLoadError(adLoadException.getRuntimeExceptionForUnexpected());
                } else {
                    AdsMediaSource.this.eventListener.onAdLoadError(adLoadException);
                }
            }
        }
    }

    @Deprecated
    public interface EventListener {
        void onAdClicked();

        void onAdLoadError(IOException iOException);

        void onAdTapped();

        void onInternalAdLoadError(RuntimeException runtimeException);
    }

    public AdsMediaSource(MediaSource mediaSource, Factory factory, AdsLoader adsLoader, ViewGroup viewGroup) {
        this(mediaSource, new ExtractorMediaSource.Factory(factory), adsLoader, viewGroup, null, null);
    }

    public AdsMediaSource(MediaSource mediaSource, MediaSourceFactory mediaSourceFactory, AdsLoader adsLoader, ViewGroup viewGroup) {
        this(mediaSource, mediaSourceFactory, adsLoader, viewGroup, null, null);
    }

    @Deprecated
    public AdsMediaSource(MediaSource mediaSource, Factory factory, AdsLoader adsLoader, ViewGroup viewGroup, @Nullable Handler handler, @Nullable EventListener eventListener) {
        this(mediaSource, new ExtractorMediaSource.Factory(factory), adsLoader, viewGroup, handler, eventListener);
    }

    @Deprecated
    public AdsMediaSource(MediaSource mediaSource, MediaSourceFactory mediaSourceFactory, AdsLoader adsLoader, ViewGroup viewGroup, @Nullable Handler handler, @Nullable EventListener eventListener) {
        this.contentMediaSource = mediaSource;
        this.adMediaSourceFactory = mediaSourceFactory;
        this.adsLoader = adsLoader;
        this.adUiViewGroup = viewGroup;
        this.eventHandler = handler;
        this.eventListener = eventListener;
        this.mainHandler = new Handler(Looper.getMainLooper());
        this.deferredMediaPeriodByAdMediaSource = new HashMap();
        this.period = new Period();
        this.adGroupMediaSources = new MediaSource[0][];
        this.adGroupTimelines = new Timeline[0][];
        adsLoader.setSupportedContentTypes(mediaSourceFactory.getSupportedTypes());
    }

    @Nullable
    public Object getTag() {
        return this.contentMediaSource.getTag();
    }

    public void prepareSourceInternal(ExoPlayer exoPlayer, boolean z, @Nullable TransferListener transferListener) {
        super.prepareSourceInternal(exoPlayer, z, transferListener);
        Assertions.checkArgument(z, "AdsMediaSource must be the top-level source used to prepare the player.");
        ComponentListener componentListener = new ComponentListener();
        this.componentListener = componentListener;
        prepareChildSource(DUMMY_CONTENT_MEDIA_PERIOD_ID, this.contentMediaSource);
        this.mainHandler.post(new AdsMediaSource$$Lambda$0(this, exoPlayer, componentListener));
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void lambda$prepareSourceInternal$0$AdsMediaSource(ExoPlayer exoPlayer, ComponentListener componentListener) {
        this.adsLoader.attachPlayer(exoPlayer, componentListener, this.adUiViewGroup);
    }

    public MediaPeriod createPeriod(MediaPeriodId mediaPeriodId, Allocator allocator) {
        if (this.adPlaybackState.adGroupCount <= 0 || !mediaPeriodId.isAd()) {
            DeferredMediaPeriod deferredMediaPeriod = new DeferredMediaPeriod(this.contentMediaSource, mediaPeriodId, allocator);
            deferredMediaPeriod.createPeriod(mediaPeriodId);
            return deferredMediaPeriod;
        }
        MediaSource createMediaSource;
        int i = mediaPeriodId.adGroupIndex;
        int i2 = mediaPeriodId.adIndexInAdGroup;
        Uri uri = this.adPlaybackState.adGroups[i].uris[i2];
        if (this.adGroupMediaSources[i].length <= i2) {
            createMediaSource = this.adMediaSourceFactory.createMediaSource(uri);
            if (i2 >= this.adGroupMediaSources[i].length) {
                int i3 = i2 + 1;
                this.adGroupMediaSources[i] = (MediaSource[]) Arrays.copyOf(this.adGroupMediaSources[i], i3);
                this.adGroupTimelines[i] = (Timeline[]) Arrays.copyOf(this.adGroupTimelines[i], i3);
            }
            this.adGroupMediaSources[i][i2] = createMediaSource;
            this.deferredMediaPeriodByAdMediaSource.put(createMediaSource, new ArrayList());
            prepareChildSource(mediaPeriodId, createMediaSource);
        }
        createMediaSource = this.adGroupMediaSources[i][i2];
        DeferredMediaPeriod deferredMediaPeriod2 = new DeferredMediaPeriod(createMediaSource, mediaPeriodId, allocator);
        deferredMediaPeriod2.setPrepareErrorListener(new AdPrepareErrorListener(uri, i, i2));
        List list = (List) this.deferredMediaPeriodByAdMediaSource.get(createMediaSource);
        if (list == null) {
            deferredMediaPeriod2.createPeriod(new MediaPeriodId(this.adGroupTimelines[i][i2].getUidOfPeriod(0), mediaPeriodId.windowSequenceNumber));
        } else {
            list.add(deferredMediaPeriod2);
        }
        return deferredMediaPeriod2;
    }

    public void releasePeriod(MediaPeriod mediaPeriod) {
        DeferredMediaPeriod deferredMediaPeriod = (DeferredMediaPeriod) mediaPeriod;
        List list = (List) this.deferredMediaPeriodByAdMediaSource.get(deferredMediaPeriod.mediaSource);
        if (list != null) {
            list.remove(deferredMediaPeriod);
        }
        deferredMediaPeriod.releasePeriod();
    }

    public void releaseSourceInternal() {
        super.releaseSourceInternal();
        this.componentListener.release();
        this.componentListener = null;
        this.deferredMediaPeriodByAdMediaSource.clear();
        this.contentTimeline = null;
        this.contentManifest = null;
        this.adPlaybackState = null;
        this.adGroupMediaSources = new MediaSource[0][];
        this.adGroupTimelines = new Timeline[0][];
        Handler handler = this.mainHandler;
        AdsLoader adsLoader = this.adsLoader;
        adsLoader.getClass();
        handler.post(AdsMediaSource$$Lambda$1.get$Lambda(adsLoader));
    }

    /* Access modifiers changed, original: protected */
    public void onChildSourceInfoRefreshed(MediaPeriodId mediaPeriodId, MediaSource mediaSource, Timeline timeline, @Nullable Object obj) {
        if (mediaPeriodId.isAd()) {
            onAdSourceInfoRefreshed(mediaSource, mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup, timeline);
        } else {
            onContentSourceInfoRefreshed(timeline, obj);
        }
    }

    /* Access modifiers changed, original: protected */
    @Nullable
    public MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(MediaPeriodId mediaPeriodId, MediaPeriodId mediaPeriodId2) {
        return mediaPeriodId.isAd() ? mediaPeriodId : mediaPeriodId2;
    }

    private void onAdPlaybackState(AdPlaybackState adPlaybackState) {
        if (this.adPlaybackState == null) {
            this.adGroupMediaSources = new MediaSource[adPlaybackState.adGroupCount][];
            Arrays.fill(this.adGroupMediaSources, new MediaSource[0]);
            this.adGroupTimelines = new Timeline[adPlaybackState.adGroupCount][];
            Arrays.fill(this.adGroupTimelines, new Timeline[0]);
        }
        this.adPlaybackState = adPlaybackState;
        maybeUpdateSourceInfo();
    }

    private void onContentSourceInfoRefreshed(Timeline timeline, Object obj) {
        this.contentTimeline = timeline;
        this.contentManifest = obj;
        maybeUpdateSourceInfo();
    }

    private void onAdSourceInfoRefreshed(MediaSource mediaSource, int i, int i2, Timeline timeline) {
        int i3 = 0;
        boolean z = true;
        if (timeline.getPeriodCount() != 1) {
            z = false;
        }
        Assertions.checkArgument(z);
        this.adGroupTimelines[i][i2] = timeline;
        List list = (List) this.deferredMediaPeriodByAdMediaSource.remove(mediaSource);
        if (list != null) {
            Object uidOfPeriod = timeline.getUidOfPeriod(0);
            while (i3 < list.size()) {
                DeferredMediaPeriod deferredMediaPeriod = (DeferredMediaPeriod) list.get(i3);
                deferredMediaPeriod.createPeriod(new MediaPeriodId(uidOfPeriod, deferredMediaPeriod.id.windowSequenceNumber));
                i3++;
            }
        }
        maybeUpdateSourceInfo();
    }

    private void maybeUpdateSourceInfo() {
        if (this.adPlaybackState != null && this.contentTimeline != null) {
            this.adPlaybackState = this.adPlaybackState.withAdDurationsUs(getAdDurations(this.adGroupTimelines, this.period));
            refreshSourceInfo(this.adPlaybackState.adGroupCount == 0 ? this.contentTimeline : new SinglePeriodAdTimeline(this.contentTimeline, this.adPlaybackState), this.contentManifest);
        }
    }

    private static long[][] getAdDurations(Timeline[][] timelineArr, Period period) {
        long[][] jArr = new long[timelineArr.length][];
        for (int i = 0; i < timelineArr.length; i++) {
            jArr[i] = new long[timelineArr[i].length];
            for (int i2 = 0; i2 < timelineArr[i].length; i2++) {
                long j;
                long[] jArr2 = jArr[i];
                if (timelineArr[i][i2] == null) {
                    j = C.TIME_UNSET;
                } else {
                    j = timelineArr[i][i2].getPeriod(0, period).getDurationUs();
                }
                jArr2[i2] = j;
            }
        }
        return jArr;
    }
}
