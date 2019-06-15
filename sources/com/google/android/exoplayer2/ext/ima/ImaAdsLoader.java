package com.google.android.exoplayer2.ext.ima;

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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class ImaAdsLoader implements AdErrorListener, AdEventListener, AdsLoadedListener, ContentProgressProvider, VideoAdPlayer, EventListener, AdsLoader {
    private static final int BITRATE_UNSET = -1;
    private static final boolean DEBUG = false;
    private static final boolean ENABLE_PRELOADING = true;
    private static final long END_OF_CONTENT_POSITION_THRESHOLD_MS = 5000;
    private static final int IMA_AD_STATE_NONE = 0;
    private static final int IMA_AD_STATE_PAUSED = 2;
    private static final int IMA_AD_STATE_PLAYING = 1;
    private static final long IMA_DURATION_UNSET = -1;
    private static final String IMA_SDK_SETTINGS_PLAYER_TYPE = "google/exo.ext.ima";
    private static final String IMA_SDK_SETTINGS_PLAYER_VERSION = "2.9.3";
    private static final long MAXIMUM_PRELOAD_DURATION_MS = 8000;
    private static final String TAG = "ImaAdsLoader";
    private static final int TIMEOUT_UNSET = -1;
    private final List<VideoAdPlayerCallback> adCallbacks;
    private final AdDisplayContainer adDisplayContainer;
    @Nullable
    private final AdEventListener adEventListener;
    private int adGroupIndex;
    private AdPlaybackState adPlaybackState;
    @Nullable
    private final Uri adTagUri;
    @Nullable
    private final Set<UiElement> adUiElements;
    private final com.google.ads.interactivemedia.v3.api.AdsLoader adsLoader;
    private AdsManager adsManager;
    @Nullable
    private final String adsResponse;
    private long contentDurationMs;
    private AdsLoader.EventListener eventListener;
    private int expectedAdGroupIndex;
    private long fakeContentProgressElapsedRealtimeMs;
    private long fakeContentProgressOffsetMs;
    private final boolean focusSkipButtonWhenAvailable;
    private int imaAdState;
    private final ImaFactory imaFactory;
    private boolean imaPausedContent;
    private VideoProgressUpdate lastAdProgress;
    private VideoProgressUpdate lastContentProgress;
    private int lastVolumePercentage;
    private final int mediaBitrate;
    private final int mediaLoadTimeoutMs;
    private AdLoadException pendingAdLoadError;
    private Object pendingAdRequestContext;
    private long pendingContentPositionMs;
    private final Period period;
    private Player player;
    private boolean playingAd;
    private int playingAdIndexInAdGroup;
    private int podIndexOffset;
    private boolean sentContentComplete;
    private boolean sentPendingContentPositionMs;
    private boolean shouldNotifyAdPrepareError;
    private List<String> supportedMimeTypes;
    private Timeline timeline;
    private final int vastLoadTimeoutMs;

    public static final class Builder {
        @Nullable
        private AdEventListener adEventListener;
        @Nullable
        private Set<UiElement> adUiElements;
        private final Context context;
        private boolean focusSkipButtonWhenAvailable = true;
        private ImaFactory imaFactory = new DefaultImaFactory();
        @Nullable
        private ImaSdkSettings imaSdkSettings;
        private int mediaBitrate = -1;
        private int mediaLoadTimeoutMs = -1;
        private int vastLoadTimeoutMs = -1;

        public Builder(Context context) {
            this.context = (Context) Assertions.checkNotNull(context);
        }

        public Builder setImaSdkSettings(ImaSdkSettings imaSdkSettings) {
            this.imaSdkSettings = (ImaSdkSettings) Assertions.checkNotNull(imaSdkSettings);
            return this;
        }

        public Builder setAdEventListener(AdEventListener adEventListener) {
            this.adEventListener = (AdEventListener) Assertions.checkNotNull(adEventListener);
            return this;
        }

        public Builder setAdUiElements(Set<UiElement> set) {
            this.adUiElements = new HashSet((Collection) Assertions.checkNotNull(set));
            return this;
        }

        public Builder setVastLoadTimeoutMs(int i) {
            Assertions.checkArgument(i > 0);
            this.vastLoadTimeoutMs = i;
            return this;
        }

        public Builder setMediaLoadTimeoutMs(int i) {
            Assertions.checkArgument(i > 0);
            this.mediaLoadTimeoutMs = i;
            return this;
        }

        public Builder setMaxMediaBitrate(int i) {
            Assertions.checkArgument(i > 0);
            this.mediaBitrate = i;
            return this;
        }

        public Builder setFocusSkipButtonWhenAvailable(boolean z) {
            this.focusSkipButtonWhenAvailable = z;
            return this;
        }

        /* Access modifiers changed, original: 0000 */
        public Builder setImaFactory(ImaFactory imaFactory) {
            this.imaFactory = (ImaFactory) Assertions.checkNotNull(imaFactory);
            return this;
        }

        public ImaAdsLoader buildForAdTag(Uri uri) {
            return new ImaAdsLoader(this.context, uri, this.imaSdkSettings, null, this.vastLoadTimeoutMs, this.mediaLoadTimeoutMs, this.mediaBitrate, this.focusSkipButtonWhenAvailable, this.adUiElements, this.adEventListener, this.imaFactory);
        }

        public ImaAdsLoader buildForAdsResponse(String str) {
            return new ImaAdsLoader(this.context, null, this.imaSdkSettings, str, this.vastLoadTimeoutMs, this.mediaLoadTimeoutMs, this.mediaBitrate, this.focusSkipButtonWhenAvailable, this.adUiElements, this.adEventListener, this.imaFactory);
        }
    }

    interface ImaFactory {
        AdDisplayContainer createAdDisplayContainer();

        com.google.ads.interactivemedia.v3.api.AdsLoader createAdsLoader(Context context, ImaSdkSettings imaSdkSettings);

        AdsRenderingSettings createAdsRenderingSettings();

        AdsRequest createAdsRequest();

        ImaSdkSettings createImaSdkSettings();
    }

    private static final class DefaultImaFactory implements ImaFactory {
        private DefaultImaFactory() {
        }

        public ImaSdkSettings createImaSdkSettings() {
            return ImaSdkFactory.getInstance().createImaSdkSettings();
        }

        public AdsRenderingSettings createAdsRenderingSettings() {
            return ImaSdkFactory.getInstance().createAdsRenderingSettings();
        }

        public AdDisplayContainer createAdDisplayContainer() {
            return ImaSdkFactory.getInstance().createAdDisplayContainer();
        }

        public AdsRequest createAdsRequest() {
            return ImaSdkFactory.getInstance().createAdsRequest();
        }

        public com.google.ads.interactivemedia.v3.api.AdsLoader createAdsLoader(Context context, ImaSdkSettings imaSdkSettings) {
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

    public ImaAdsLoader(Context context, Uri uri) {
        this(context, uri, null, null, -1, -1, -1, true, null, null, new DefaultImaFactory());
    }

    @Deprecated
    public ImaAdsLoader(Context context, Uri uri, ImaSdkSettings imaSdkSettings) {
        this(context, uri, imaSdkSettings, null, -1, -1, -1, true, null, null, new DefaultImaFactory());
    }

    private ImaAdsLoader(Context context, @Nullable Uri uri, @Nullable ImaSdkSettings imaSdkSettings, @Nullable String str, int i, int i2, int i3, boolean z, @Nullable Set<UiElement> set, @Nullable AdEventListener adEventListener, ImaFactory imaFactory) {
        boolean z2 = (uri == null && str == null) ? false : true;
        Assertions.checkArgument(z2);
        this.adTagUri = uri;
        this.adsResponse = str;
        this.vastLoadTimeoutMs = i;
        this.mediaLoadTimeoutMs = i2;
        this.mediaBitrate = i3;
        this.focusSkipButtonWhenAvailable = z;
        this.adUiElements = set;
        this.adEventListener = adEventListener;
        this.imaFactory = imaFactory;
        if (imaSdkSettings == null) {
            imaSdkSettings = imaFactory.createImaSdkSettings();
        }
        imaSdkSettings.setPlayerType(IMA_SDK_SETTINGS_PLAYER_TYPE);
        imaSdkSettings.setPlayerVersion("2.9.3");
        this.adsLoader = imaFactory.createAdsLoader(context, imaSdkSettings);
        this.period = new Period();
        this.adCallbacks = new ArrayList(1);
        this.adDisplayContainer = imaFactory.createAdDisplayContainer();
        this.adDisplayContainer.setPlayer(this);
        this.adsLoader.addAdErrorListener(this);
        this.adsLoader.addAdsLoadedListener(this);
        this.fakeContentProgressElapsedRealtimeMs = C.TIME_UNSET;
        this.fakeContentProgressOffsetMs = C.TIME_UNSET;
        this.pendingContentPositionMs = C.TIME_UNSET;
        this.adGroupIndex = -1;
        this.contentDurationMs = C.TIME_UNSET;
    }

    public com.google.ads.interactivemedia.v3.api.AdsLoader getAdsLoader() {
        return this.adsLoader;
    }

    public void setCompanionSlots(Collection<CompanionAdSlot> collection) {
        this.adDisplayContainer.setCompanionSlots(collection);
    }

    public void requestAds(ViewGroup viewGroup) {
        if (this.adPlaybackState == null && this.adsManager == null && this.pendingAdRequestContext == null) {
            this.adDisplayContainer.setAdContainer(viewGroup);
            this.pendingAdRequestContext = new Object();
            AdsRequest createAdsRequest = this.imaFactory.createAdsRequest();
            if (this.adTagUri != null) {
                createAdsRequest.setAdTagUrl(this.adTagUri.toString());
            } else {
                createAdsRequest.setAdsResponse(this.adsResponse);
            }
            if (this.vastLoadTimeoutMs != -1) {
                createAdsRequest.setVastLoadTimeout((float) this.vastLoadTimeoutMs);
            }
            createAdsRequest.setAdDisplayContainer(this.adDisplayContainer);
            createAdsRequest.setContentProgressProvider(this);
            createAdsRequest.setUserRequestContext(this.pendingAdRequestContext);
            this.adsLoader.requestAds(createAdsRequest);
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
        this.supportedMimeTypes = Collections.unmodifiableList(arrayList);
    }

    public void attachPlayer(ExoPlayer exoPlayer, AdsLoader.EventListener eventListener, ViewGroup viewGroup) {
        Assertions.checkArgument(exoPlayer.getApplicationLooper() == Looper.getMainLooper());
        this.player = exoPlayer;
        this.eventListener = eventListener;
        this.lastVolumePercentage = 0;
        this.lastAdProgress = null;
        this.lastContentProgress = null;
        this.adDisplayContainer.setAdContainer(viewGroup);
        exoPlayer.addListener(this);
        maybeNotifyPendingAdLoadError();
        if (this.adPlaybackState != null) {
            eventListener.onAdPlaybackState(this.adPlaybackState);
            if (this.imaPausedContent && exoPlayer.getPlayWhenReady()) {
                this.adsManager.resume();
            }
        } else if (this.adsManager != null) {
            startAdPlayback();
        } else {
            requestAds(viewGroup);
        }
    }

    public void detachPlayer() {
        if (this.adsManager != null && this.imaPausedContent) {
            this.adPlaybackState = this.adPlaybackState.withAdResumePositionUs(this.playingAd ? C.msToUs(this.player.getCurrentPosition()) : 0);
            this.adsManager.pause();
        }
        this.lastVolumePercentage = getVolume();
        this.lastAdProgress = getAdProgress();
        this.lastContentProgress = getContentProgress();
        this.player.removeListener(this);
        this.player = null;
        this.eventListener = null;
    }

    public void release() {
        this.pendingAdRequestContext = null;
        if (this.adsManager != null) {
            this.adsManager.destroy();
            this.adsManager = null;
        }
        this.imaPausedContent = false;
        this.imaAdState = 0;
        this.pendingAdLoadError = null;
        this.adPlaybackState = AdPlaybackState.NONE;
        updateAdPlaybackState();
    }

    public void handlePrepareError(int i, int i2, IOException iOException) {
        if (this.player != null) {
            try {
                handleAdPrepareError(i, i2, iOException);
            } catch (Exception e) {
                maybeNotifyInternalError("handlePrepareError", e);
            }
        }
    }

    public void onAdsManagerLoaded(AdsManagerLoadedEvent adsManagerLoadedEvent) {
        AdsManager adsManager = adsManagerLoadedEvent.getAdsManager();
        if (Util.areEqual(this.pendingAdRequestContext, adsManagerLoadedEvent.getUserRequestContext())) {
            this.pendingAdRequestContext = null;
            this.adsManager = adsManager;
            adsManager.addAdErrorListener(this);
            adsManager.addAdEventListener(this);
            if (this.adEventListener != null) {
                adsManager.addAdEventListener(this.adEventListener);
            }
            if (this.player != null) {
                try {
                    startAdPlayback();
                } catch (Exception e) {
                    maybeNotifyInternalError("onAdsManagerLoaded", e);
                }
            }
            return;
        }
        adsManager.destroy();
    }

    public void onAdEvent(AdEvent adEvent) {
        adEvent.getType();
        if (this.adsManager == null) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Ignoring AdEvent after release: ");
            stringBuilder.append(adEvent);
            Log.w(str, stringBuilder.toString());
            return;
        }
        try {
            handleAdEvent(adEvent);
        } catch (Exception e) {
            maybeNotifyInternalError("onAdEvent", e);
        }
    }

    public void onAdError(AdErrorEvent adErrorEvent) {
        AdError error = adErrorEvent.getError();
        if (this.adsManager == null) {
            this.pendingAdRequestContext = null;
            this.adPlaybackState = new AdPlaybackState(new long[0]);
            updateAdPlaybackState();
        } else if (isAdGroupLoadError(error)) {
            try {
                handleAdGroupLoadError(error);
            } catch (Exception e) {
                maybeNotifyInternalError("onAdError", e);
            }
        }
        if (this.pendingAdLoadError == null) {
            this.pendingAdLoadError = AdLoadException.createForAllAds(error);
        }
        maybeNotifyPendingAdLoadError();
    }

    public VideoProgressUpdate getContentProgress() {
        if (this.player == null) {
            return this.lastContentProgress;
        }
        long j;
        boolean z = this.contentDurationMs != C.TIME_UNSET;
        long elapsedRealtime;
        if (this.pendingContentPositionMs != C.TIME_UNSET) {
            this.sentPendingContentPositionMs = true;
            j = this.pendingContentPositionMs;
            this.expectedAdGroupIndex = this.adPlaybackState.getAdGroupIndexForPositionUs(C.msToUs(j));
        } else if (this.fakeContentProgressElapsedRealtimeMs != C.TIME_UNSET) {
            elapsedRealtime = this.fakeContentProgressOffsetMs + (SystemClock.elapsedRealtime() - this.fakeContentProgressElapsedRealtimeMs);
            this.expectedAdGroupIndex = this.adPlaybackState.getAdGroupIndexForPositionUs(C.msToUs(elapsedRealtime));
            j = elapsedRealtime;
        } else if (this.imaAdState != 0 || this.playingAd || !z) {
            return VideoProgressUpdate.VIDEO_TIME_NOT_READY;
        } else {
            j = this.player.getCurrentPosition();
            int adGroupIndexAfterPositionUs = this.adPlaybackState.getAdGroupIndexAfterPositionUs(C.msToUs(j));
            if (!(adGroupIndexAfterPositionUs == this.expectedAdGroupIndex || adGroupIndexAfterPositionUs == -1)) {
                elapsedRealtime = C.usToMs(this.adPlaybackState.adGroupTimesUs[adGroupIndexAfterPositionUs]);
                if (elapsedRealtime == Long.MIN_VALUE) {
                    elapsedRealtime = this.contentDurationMs;
                }
                if (elapsedRealtime - j < MAXIMUM_PRELOAD_DURATION_MS) {
                    this.expectedAdGroupIndex = adGroupIndexAfterPositionUs;
                }
            }
        }
        return new VideoProgressUpdate(j, z ? this.contentDurationMs : -1);
    }

    public VideoProgressUpdate getAdProgress() {
        if (this.player == null) {
            return this.lastAdProgress;
        }
        if (this.imaAdState == 0 || !this.playingAd) {
            return VideoProgressUpdate.VIDEO_TIME_NOT_READY;
        }
        VideoProgressUpdate videoProgressUpdate;
        long duration = this.player.getDuration();
        if (duration == C.TIME_UNSET) {
            videoProgressUpdate = VideoProgressUpdate.VIDEO_TIME_NOT_READY;
        } else {
            videoProgressUpdate = new VideoProgressUpdate(this.player.getCurrentPosition(), duration);
        }
        return videoProgressUpdate;
    }

    public int getVolume() {
        if (this.player == null) {
            return this.lastVolumePercentage;
        }
        AudioComponent audioComponent = this.player.getAudioComponent();
        if (audioComponent != null) {
            return (int) (audioComponent.getVolume() * 100.0f);
        }
        TrackSelectionArray currentTrackSelections = this.player.getCurrentTrackSelections();
        int i = 0;
        while (i < this.player.getRendererCount() && i < currentTrackSelections.length) {
            if (this.player.getRendererType(i) == 1 && currentTrackSelections.get(i) != null) {
                return 100;
            }
            i++;
        }
        return 0;
    }

    public void loadAd(String str) {
        try {
            if (this.adsManager == null) {
                Log.w(TAG, "Ignoring loadAd after release");
                return;
            }
            if (this.adGroupIndex == -1) {
                String str2 = TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Unexpected loadAd without LOADED event; assuming ad group index is actually ");
                stringBuilder.append(this.expectedAdGroupIndex);
                Log.w(str2, stringBuilder.toString());
                this.adGroupIndex = this.expectedAdGroupIndex;
                this.adsManager.start();
            }
            int adIndexInAdGroupToLoad = getAdIndexInAdGroupToLoad(this.adGroupIndex);
            if (adIndexInAdGroupToLoad == -1) {
                Log.w(TAG, "Unexpected loadAd in an ad group with no remaining unavailable ads");
                return;
            }
            this.adPlaybackState = this.adPlaybackState.withAdUri(this.adGroupIndex, adIndexInAdGroupToLoad, Uri.parse(str));
            updateAdPlaybackState();
        } catch (Exception e) {
            maybeNotifyInternalError("loadAd", e);
        }
    }

    public void addCallback(VideoAdPlayerCallback videoAdPlayerCallback) {
        this.adCallbacks.add(videoAdPlayerCallback);
    }

    public void removeCallback(VideoAdPlayerCallback videoAdPlayerCallback) {
        this.adCallbacks.remove(videoAdPlayerCallback);
    }

    public void playAd() {
        if (this.adsManager == null) {
            Log.w(TAG, "Ignoring playAd after release");
            return;
        }
        int i = 0;
        switch (this.imaAdState) {
            case 0:
                this.fakeContentProgressElapsedRealtimeMs = C.TIME_UNSET;
                this.fakeContentProgressOffsetMs = C.TIME_UNSET;
                this.imaAdState = 1;
                for (int i2 = 0; i2 < this.adCallbacks.size(); i2++) {
                    ((VideoAdPlayerCallback) this.adCallbacks.get(i2)).onPlay();
                }
                if (this.shouldNotifyAdPrepareError) {
                    this.shouldNotifyAdPrepareError = false;
                    while (i < this.adCallbacks.size()) {
                        ((VideoAdPlayerCallback) this.adCallbacks.get(i)).onError();
                        i++;
                    }
                    break;
                }
                break;
            case 1:
                Log.w(TAG, "Unexpected playAd without stopAd");
                break;
            case 2:
                this.imaAdState = 1;
                while (i < this.adCallbacks.size()) {
                    ((VideoAdPlayerCallback) this.adCallbacks.get(i)).onResume();
                    i++;
                }
                break;
            default:
                throw new IllegalStateException();
        }
        if (this.player == null) {
            Log.w(TAG, "Unexpected playAd while detached");
        } else if (!this.player.getPlayWhenReady()) {
            this.adsManager.pause();
        }
    }

    public void stopAd() {
        if (this.adsManager == null) {
            Log.w(TAG, "Ignoring stopAd after release");
            return;
        }
        if (this.player == null) {
            Log.w(TAG, "Unexpected stopAd while detached");
        }
        if (this.imaAdState == 0) {
            Log.w(TAG, "Unexpected stopAd");
            return;
        }
        try {
            stopAdInternal();
        } catch (Exception e) {
            maybeNotifyInternalError("stopAd", e);
        }
    }

    public void pauseAd() {
        if (this.imaAdState != 0) {
            this.imaAdState = 2;
            for (int i = 0; i < this.adCallbacks.size(); i++) {
                ((VideoAdPlayerCallback) this.adCallbacks.get(i)).onPause();
            }
        }
    }

    public void resumeAd() {
        maybeNotifyInternalError("resumeAd", new IllegalStateException("Unexpected call to resumeAd"));
    }

    public void onTimelineChanged(Timeline timeline, @Nullable Object obj, int i) {
        boolean z = true;
        if (i != 1) {
            if (timeline.getPeriodCount() != 1) {
                z = false;
            }
            Assertions.checkArgument(z);
            this.timeline = timeline;
            long j = timeline.getPeriod(0, this.period).durationUs;
            this.contentDurationMs = C.usToMs(j);
            if (j != C.TIME_UNSET) {
                this.adPlaybackState = this.adPlaybackState.withContentDurationUs(j);
            }
            updateImaStateForPlayerState();
        }
    }

    public void onPlayerStateChanged(boolean z, int i) {
        if (this.adsManager != null) {
            if (this.imaAdState == 1 && !z) {
                this.adsManager.pause();
            } else if (this.imaAdState == 2 && z) {
                this.adsManager.resume();
            } else {
                if (this.imaAdState == 0 && i == 2 && z) {
                    checkForContentComplete();
                } else if (this.imaAdState != 0 && i == 4) {
                    for (int i2 = 0; i2 < this.adCallbacks.size(); i2++) {
                        ((VideoAdPlayerCallback) this.adCallbacks.get(i2)).onEnded();
                    }
                }
            }
        }
    }

    public void onPlayerError(ExoPlaybackException exoPlaybackException) {
        if (this.imaAdState != 0) {
            for (int i = 0; i < this.adCallbacks.size(); i++) {
                ((VideoAdPlayerCallback) this.adCallbacks.get(i)).onError();
            }
        }
    }

    public void onPositionDiscontinuity(int i) {
        if (this.adsManager != null) {
            if (this.playingAd || this.player.isPlayingAd()) {
                updateImaStateForPlayerState();
            } else {
                checkForContentComplete();
                int i2 = 0;
                if (this.sentContentComplete) {
                    while (i2 < this.adPlaybackState.adGroupCount) {
                        if (this.adPlaybackState.adGroupTimesUs[i2] != Long.MIN_VALUE) {
                            this.adPlaybackState = this.adPlaybackState.withSkippedAdGroup(i2);
                        }
                        i2++;
                    }
                    updateAdPlaybackState();
                } else {
                    long currentPosition = this.player.getCurrentPosition();
                    this.timeline.getPeriod(0, this.period);
                    i = this.period.getAdGroupIndexForPositionUs(C.msToUs(currentPosition));
                    if (i != -1) {
                        this.sentPendingContentPositionMs = false;
                        this.pendingContentPositionMs = currentPosition;
                        if (i != this.adGroupIndex) {
                            this.shouldNotifyAdPrepareError = false;
                        }
                    }
                }
            }
        }
    }

    private void startAdPlayback() {
        AdsRenderingSettings createAdsRenderingSettings = this.imaFactory.createAdsRenderingSettings();
        createAdsRenderingSettings.setEnablePreloading(true);
        createAdsRenderingSettings.setMimeTypes(this.supportedMimeTypes);
        if (this.mediaLoadTimeoutMs != -1) {
            createAdsRenderingSettings.setLoadVideoTimeout(this.mediaLoadTimeoutMs);
        }
        if (this.mediaBitrate != -1) {
            createAdsRenderingSettings.setBitrateKbps(this.mediaBitrate / 1000);
        }
        createAdsRenderingSettings.setFocusSkipButtonWhenAvailable(this.focusSkipButtonWhenAvailable);
        if (this.adUiElements != null) {
            createAdsRenderingSettings.setUiElements(this.adUiElements);
        }
        long[] adGroupTimesUs = getAdGroupTimesUs(this.adsManager.getAdCuePoints());
        this.adPlaybackState = new AdPlaybackState(adGroupTimesUs);
        long currentPosition = this.player.getCurrentPosition();
        int adGroupIndexForPositionUs = this.adPlaybackState.getAdGroupIndexForPositionUs(C.msToUs(currentPosition));
        int i = 0;
        if (adGroupIndexForPositionUs == 0) {
            this.podIndexOffset = 0;
        } else if (adGroupIndexForPositionUs == -1) {
            this.podIndexOffset = -1;
        } else {
            while (i < adGroupIndexForPositionUs) {
                this.adPlaybackState = this.adPlaybackState.withSkippedAdGroup(i);
                i++;
            }
            int i2 = adGroupIndexForPositionUs - 1;
            createAdsRenderingSettings.setPlayAdsAfterTime((((double) (adGroupTimesUs[adGroupIndexForPositionUs] + adGroupTimesUs[i2])) / 2.0d) / 1000000.0d);
            this.podIndexOffset = i2;
        }
        if (adGroupIndexForPositionUs != -1 && hasMidrollAdGroups(adGroupTimesUs)) {
            this.pendingContentPositionMs = currentPosition;
        }
        this.adsManager.init(createAdsRenderingSettings);
        updateAdPlaybackState();
    }

    private void handleAdEvent(AdEvent adEvent) {
        Ad ad = adEvent.getAd();
        StringBuilder stringBuilder;
        switch (adEvent.getType()) {
            case LOADED:
                AdPodInfo adPodInfo = ad.getAdPodInfo();
                int podIndex = adPodInfo.getPodIndex();
                this.adGroupIndex = podIndex == -1 ? this.adPlaybackState.adGroupCount - 1 : podIndex + this.podIndexOffset;
                adPodInfo.getAdPosition();
                int totalAds = adPodInfo.getTotalAds();
                this.adsManager.start();
                podIndex = this.adPlaybackState.adGroups[this.adGroupIndex].count;
                if (totalAds != podIndex) {
                    if (podIndex == -1) {
                        this.adPlaybackState = this.adPlaybackState.withAdCount(this.adGroupIndex, totalAds);
                        updateAdPlaybackState();
                    } else {
                        String str = TAG;
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append("Unexpected ad count in LOADED, ");
                        stringBuilder2.append(totalAds);
                        stringBuilder2.append(", expected ");
                        stringBuilder2.append(podIndex);
                        Log.w(str, stringBuilder2.toString());
                    }
                }
                if (this.adGroupIndex != this.expectedAdGroupIndex) {
                    String str2 = TAG;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Expected ad group index ");
                    stringBuilder.append(this.expectedAdGroupIndex);
                    stringBuilder.append(", actual ad group index ");
                    stringBuilder.append(this.adGroupIndex);
                    Log.w(str2, stringBuilder.toString());
                    this.expectedAdGroupIndex = this.adGroupIndex;
                    return;
                }
                return;
            case CONTENT_PAUSE_REQUESTED:
                this.imaPausedContent = true;
                pauseContentInternal();
                return;
            case TAPPED:
                if (this.eventListener != null) {
                    this.eventListener.onAdTapped();
                    return;
                }
                return;
            case CLICKED:
                if (this.eventListener != null) {
                    this.eventListener.onAdClicked();
                    return;
                }
                return;
            case CONTENT_RESUME_REQUESTED:
                this.imaPausedContent = false;
                resumeContentInternal();
                return;
            case LOG:
                Map adData = adEvent.getAdData();
                stringBuilder = new StringBuilder();
                stringBuilder.append("AdEvent: ");
                stringBuilder.append(adData);
                String stringBuilder3 = stringBuilder.toString();
                Log.i(TAG, stringBuilder3);
                if ("adLoadError".equals(adData.get("type"))) {
                    handleAdGroupLoadError(new IOException(stringBuilder3));
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void updateImaStateForPlayerState() {
        boolean z = this.playingAd;
        int i = this.playingAdIndexInAdGroup;
        this.playingAd = this.player.isPlayingAd();
        this.playingAdIndexInAdGroup = this.playingAd ? this.player.getCurrentAdIndexInAdGroup() : -1;
        int i2 = 0;
        i = (!z || this.playingAdIndexInAdGroup == i) ? 0 : 1;
        if (i != 0) {
            while (i2 < this.adCallbacks.size()) {
                ((VideoAdPlayerCallback) this.adCallbacks.get(i2)).onEnded();
                i2++;
            }
        }
        if (!this.sentContentComplete && !z && this.playingAd && this.imaAdState == 0) {
            int currentAdGroupIndex = this.player.getCurrentAdGroupIndex();
            this.fakeContentProgressElapsedRealtimeMs = SystemClock.elapsedRealtime();
            this.fakeContentProgressOffsetMs = C.usToMs(this.adPlaybackState.adGroupTimesUs[currentAdGroupIndex]);
            if (this.fakeContentProgressOffsetMs == Long.MIN_VALUE) {
                this.fakeContentProgressOffsetMs = this.contentDurationMs;
            }
        }
    }

    private void resumeContentInternal() {
        if (this.imaAdState != 0) {
            this.imaAdState = 0;
        }
        if (this.adGroupIndex != -1) {
            this.adPlaybackState = this.adPlaybackState.withSkippedAdGroup(this.adGroupIndex);
            this.adGroupIndex = -1;
            updateAdPlaybackState();
        }
    }

    private void pauseContentInternal() {
        this.imaAdState = 0;
        if (this.sentPendingContentPositionMs) {
            this.pendingContentPositionMs = C.TIME_UNSET;
            this.sentPendingContentPositionMs = false;
        }
    }

    private void stopAdInternal() {
        this.imaAdState = 0;
        this.adPlaybackState = this.adPlaybackState.withPlayedAd(this.adGroupIndex, this.adPlaybackState.adGroups[this.adGroupIndex].getFirstAdIndexToPlay()).withAdResumePositionUs(0);
        updateAdPlaybackState();
        if (!this.playingAd) {
            this.adGroupIndex = -1;
        }
    }

    private void handleAdGroupLoadError(Exception exception) {
        int i = this.adGroupIndex == -1 ? this.expectedAdGroupIndex : this.adGroupIndex;
        if (i != -1) {
            AdGroup adGroup = this.adPlaybackState.adGroups[i];
            if (adGroup.count == -1) {
                this.adPlaybackState = this.adPlaybackState.withAdCount(i, Math.max(1, adGroup.states.length));
                adGroup = this.adPlaybackState.adGroups[i];
            }
            for (int i2 = 0; i2 < adGroup.count; i2++) {
                if (adGroup.states[i2] == 0) {
                    this.adPlaybackState = this.adPlaybackState.withAdLoadError(i, i2);
                }
            }
            updateAdPlaybackState();
            if (this.pendingAdLoadError == null) {
                this.pendingAdLoadError = AdLoadException.createForAdGroup(exception, i);
            }
            this.pendingContentPositionMs = C.TIME_UNSET;
            this.fakeContentProgressElapsedRealtimeMs = C.TIME_UNSET;
        }
    }

    private void handleAdPrepareError(int i, int i2, Exception exception) {
        if (this.adsManager == null) {
            Log.w(TAG, "Ignoring ad prepare error after release");
            return;
        }
        if (this.imaAdState == 0) {
            this.fakeContentProgressElapsedRealtimeMs = SystemClock.elapsedRealtime();
            this.fakeContentProgressOffsetMs = C.usToMs(this.adPlaybackState.adGroupTimesUs[i]);
            if (this.fakeContentProgressOffsetMs == Long.MIN_VALUE) {
                this.fakeContentProgressOffsetMs = this.contentDurationMs;
            }
            this.shouldNotifyAdPrepareError = true;
        } else {
            int i3 = 0;
            if (i2 > this.playingAdIndexInAdGroup) {
                for (int i4 = 0; i4 < this.adCallbacks.size(); i4++) {
                    ((VideoAdPlayerCallback) this.adCallbacks.get(i4)).onEnded();
                }
            }
            this.playingAdIndexInAdGroup = this.adPlaybackState.adGroups[i].getFirstAdIndexToPlay();
            while (i3 < this.adCallbacks.size()) {
                ((VideoAdPlayerCallback) this.adCallbacks.get(i3)).onError();
                i3++;
            }
        }
        this.adPlaybackState = this.adPlaybackState.withAdLoadError(i, i2);
        updateAdPlaybackState();
    }

    private void checkForContentComplete() {
        if (this.contentDurationMs != C.TIME_UNSET && this.pendingContentPositionMs == C.TIME_UNSET && this.player.getContentPosition() + 5000 >= this.contentDurationMs && !this.sentContentComplete) {
            this.adsLoader.contentComplete();
            this.sentContentComplete = true;
            this.expectedAdGroupIndex = this.adPlaybackState.getAdGroupIndexForPositionUs(C.msToUs(this.contentDurationMs));
        }
    }

    private void updateAdPlaybackState() {
        if (this.eventListener != null) {
            this.eventListener.onAdPlaybackState(this.adPlaybackState);
        }
    }

    private int getAdIndexInAdGroupToLoad(int i) {
        int[] iArr = this.adPlaybackState.adGroups[i].states;
        int i2 = 0;
        while (i2 < iArr.length && iArr[i2] != 0) {
            i2++;
        }
        return i2 == iArr.length ? -1 : i2;
    }

    private void maybeNotifyPendingAdLoadError() {
        if (this.pendingAdLoadError != null && this.eventListener != null) {
            this.eventListener.onAdLoadError(this.pendingAdLoadError, new DataSpec(this.adTagUri));
            this.pendingAdLoadError = null;
        }
    }

    private void maybeNotifyInternalError(String str, Exception exception) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Internal error in ");
        stringBuilder.append(str);
        str = stringBuilder.toString();
        Log.e(TAG, str, exception);
        if (this.adPlaybackState == null) {
            this.adPlaybackState = AdPlaybackState.NONE;
        } else {
            for (int i = 0; i < this.adPlaybackState.adGroupCount; i++) {
                this.adPlaybackState = this.adPlaybackState.withSkippedAdGroup(i);
            }
        }
        updateAdPlaybackState();
        if (this.eventListener != null) {
            this.eventListener.onAdLoadError(AdLoadException.createForUnexpected(new RuntimeException(str, exception)), new DataSpec(this.adTagUri));
        }
    }

    private static long[] getAdGroupTimesUs(List<Float> list) {
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

    private static boolean isAdGroupLoadError(AdError adError) {
        return adError.getErrorCode() == AdErrorCode.VAST_LINEAR_ASSET_MISMATCH;
    }

    private static boolean hasMidrollAdGroups(long[] jArr) {
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
