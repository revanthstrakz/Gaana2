package com.google.android.exoplayer2.analytics;

import android.support.annotation.Nullable;
import android.view.Surface;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.analytics.AnalyticsListener.EventTime;
import com.google.android.exoplayer2.audio.AudioAttributes;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.source.MediaSourceEventListener.LoadEventInfo;
import com.google.android.exoplayer2.source.MediaSourceEventListener.MediaLoadData;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import java.io.IOException;

public abstract /* synthetic */ class AnalyticsListener$$CC {
    public static void onAudioAttributesChanged(AnalyticsListener analyticsListener, EventTime eventTime, AudioAttributes audioAttributes) {
    }

    public static void onAudioSessionId(AnalyticsListener analyticsListener, EventTime eventTime, int i) {
    }

    public static void onAudioUnderrun(AnalyticsListener analyticsListener, EventTime eventTime, int i, long j, long j2) {
    }

    public static void onBandwidthEstimate(AnalyticsListener analyticsListener, EventTime eventTime, int i, long j, long j2) {
    }

    public static void onDecoderDisabled(AnalyticsListener analyticsListener, EventTime eventTime, int i, DecoderCounters decoderCounters) {
    }

    public static void onDecoderEnabled(AnalyticsListener analyticsListener, EventTime eventTime, int i, DecoderCounters decoderCounters) {
    }

    public static void onDecoderInitialized(AnalyticsListener analyticsListener, EventTime eventTime, int i, String str, long j) {
    }

    public static void onDecoderInputFormatChanged(AnalyticsListener analyticsListener, EventTime eventTime, int i, Format format) {
    }

    public static void onDownstreamFormatChanged(AnalyticsListener analyticsListener, EventTime eventTime, MediaLoadData mediaLoadData) {
    }

    public static void onDrmKeysLoaded(AnalyticsListener analyticsListener, EventTime eventTime) {
    }

    public static void onDrmKeysRemoved(AnalyticsListener analyticsListener, EventTime eventTime) {
    }

    public static void onDrmKeysRestored(AnalyticsListener analyticsListener, EventTime eventTime) {
    }

    public static void onDrmSessionAcquired(AnalyticsListener analyticsListener, EventTime eventTime) {
    }

    public static void onDrmSessionManagerError(AnalyticsListener analyticsListener, EventTime eventTime, Exception exception) {
    }

    public static void onDrmSessionReleased(AnalyticsListener analyticsListener, EventTime eventTime) {
    }

    public static void onDroppedVideoFrames(AnalyticsListener analyticsListener, EventTime eventTime, int i, long j) {
    }

    public static void onLoadCanceled(AnalyticsListener analyticsListener, EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
    }

    public static void onLoadCompleted(AnalyticsListener analyticsListener, EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
    }

    public static void onLoadError(AnalyticsListener analyticsListener, EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z) {
    }

    public static void onLoadStarted(AnalyticsListener analyticsListener, EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
    }

    public static void onLoadingChanged(AnalyticsListener analyticsListener, EventTime eventTime, boolean z) {
    }

    public static void onMediaPeriodCreated(AnalyticsListener analyticsListener, EventTime eventTime) {
    }

    public static void onMediaPeriodReleased(AnalyticsListener analyticsListener, EventTime eventTime) {
    }

    public static void onMetadata(AnalyticsListener analyticsListener, EventTime eventTime, Metadata metadata) {
    }

    public static void onPlaybackParametersChanged(AnalyticsListener analyticsListener, EventTime eventTime, PlaybackParameters playbackParameters) {
    }

    public static void onPlayerError(AnalyticsListener analyticsListener, EventTime eventTime, ExoPlaybackException exoPlaybackException) {
    }

    public static void onPlayerStateChanged(AnalyticsListener analyticsListener, EventTime eventTime, boolean z, int i) {
    }

    public static void onPositionDiscontinuity(AnalyticsListener analyticsListener, EventTime eventTime, int i) {
    }

    public static void onReadingStarted(AnalyticsListener analyticsListener, EventTime eventTime) {
    }

    public static void onRenderedFirstFrame(AnalyticsListener analyticsListener, @Nullable EventTime eventTime, Surface surface) {
    }

    public static void onRepeatModeChanged(AnalyticsListener analyticsListener, EventTime eventTime, int i) {
    }

    public static void onSeekProcessed(AnalyticsListener analyticsListener, EventTime eventTime) {
    }

    public static void onSeekStarted(AnalyticsListener analyticsListener, EventTime eventTime) {
    }

    public static void onShuffleModeChanged(AnalyticsListener analyticsListener, EventTime eventTime, boolean z) {
    }

    public static void onSurfaceSizeChanged(AnalyticsListener analyticsListener, EventTime eventTime, int i, int i2) {
    }

    public static void onTimelineChanged(AnalyticsListener analyticsListener, EventTime eventTime, int i) {
    }

    public static void onTracksChanged(AnalyticsListener analyticsListener, EventTime eventTime, TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
    }

    public static void onUpstreamDiscarded(AnalyticsListener analyticsListener, EventTime eventTime, MediaLoadData mediaLoadData) {
    }

    public static void onVideoSizeChanged(AnalyticsListener analyticsListener, EventTime eventTime, int i, int i2, int i3, float f) {
    }

    public static void onVolumeChanged(AnalyticsListener analyticsListener, EventTime eventTime, float f) {
    }
}
