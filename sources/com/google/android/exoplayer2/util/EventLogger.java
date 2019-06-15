package com.google.android.exoplayer2.util;

import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.Surface;
import com.comscore.android.id.IdHelperAndroid;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Timeline.Period;
import com.google.android.exoplayer2.Timeline.Window;
import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.analytics.AnalyticsListener$$CC;
import com.google.android.exoplayer2.analytics.AnalyticsListener.EventTime;
import com.google.android.exoplayer2.audio.AudioAttributes;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.source.MediaSourceEventListener.LoadEventInfo;
import com.google.android.exoplayer2.source.MediaSourceEventListener.MediaLoadData;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.MappingTrackSelector;
import com.google.android.exoplayer2.trackselection.MappingTrackSelector.MappedTrackInfo;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.payu.custombrowser.util.CBConstant;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

public class EventLogger implements AnalyticsListener {
    private static final String DEFAULT_TAG = "EventLogger";
    private static final int MAX_TIMELINE_ITEM_LINES = 3;
    private static final NumberFormat TIME_FORMAT = NumberFormat.getInstance(Locale.US);
    private final Period period;
    private final long startTimeMs;
    private final String tag;
    @Nullable
    private final MappingTrackSelector trackSelector;
    private final Window window;

    private static String getAdaptiveSupportString(int i, int i2) {
        return i < 2 ? "N/A" : i2 != 0 ? i2 != 8 ? i2 != 16 ? "?" : "YES" : "YES_NOT_SEAMLESS" : "NO";
    }

    private static String getDiscontinuityReasonString(int i) {
        switch (i) {
            case 0:
                return "PERIOD_TRANSITION";
            case 1:
                return "SEEK";
            case 2:
                return "SEEK_ADJUSTMENT";
            case 3:
                return "AD_INSERTION";
            case 4:
                return "INTERNAL";
            default:
                return "?";
        }
    }

    private static String getFormatSupportString(int i) {
        switch (i) {
            case 0:
                return "NO";
            case 1:
                return "NO_UNSUPPORTED_TYPE";
            case 2:
                return "NO_UNSUPPORTED_DRM";
            case 3:
                return "NO_EXCEEDS_CAPABILITIES";
            case 4:
                return "YES";
            default:
                return "?";
        }
    }

    private static String getRepeatModeString(int i) {
        switch (i) {
            case 0:
                return "OFF";
            case 1:
                return "ONE";
            case 2:
                return "ALL";
            default:
                return "?";
        }
    }

    private static String getStateString(int i) {
        switch (i) {
            case 1:
                return "IDLE";
            case 2:
                return "BUFFERING";
            case 3:
                return "READY";
            case 4:
                return "ENDED";
            default:
                return "?";
        }
    }

    private static String getTimelineChangeReasonString(int i) {
        switch (i) {
            case 0:
                return "PREPARED";
            case 1:
                return "RESET";
            case 2:
                return "DYNAMIC";
            default:
                return "?";
        }
    }

    private static String getTrackStatusString(boolean z) {
        return z ? "[X]" : "[ ]";
    }

    public void onAudioAttributesChanged(EventTime eventTime, AudioAttributes audioAttributes) {
        AnalyticsListener$$CC.onAudioAttributesChanged(this, eventTime, audioAttributes);
    }

    public void onBandwidthEstimate(EventTime eventTime, int i, long j, long j2) {
    }

    public void onLoadCanceled(EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
    }

    public void onLoadCompleted(EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
    }

    public void onLoadStarted(EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
    }

    public void onVolumeChanged(EventTime eventTime, float f) {
        AnalyticsListener$$CC.onVolumeChanged(this, eventTime, f);
    }

    static {
        TIME_FORMAT.setMinimumFractionDigits(2);
        TIME_FORMAT.setMaximumFractionDigits(2);
        TIME_FORMAT.setGroupingUsed(false);
    }

    public EventLogger(@Nullable MappingTrackSelector mappingTrackSelector) {
        this(mappingTrackSelector, DEFAULT_TAG);
    }

    public EventLogger(@Nullable MappingTrackSelector mappingTrackSelector, String str) {
        this.trackSelector = mappingTrackSelector;
        this.tag = str;
        this.window = new Window();
        this.period = new Period();
        this.startTimeMs = SystemClock.elapsedRealtime();
    }

    public void onLoadingChanged(EventTime eventTime, boolean z) {
        logd(eventTime, CBConstant.LOADING, Boolean.toString(z));
    }

    public void onPlayerStateChanged(EventTime eventTime, boolean z, int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(z);
        stringBuilder.append(", ");
        stringBuilder.append(getStateString(i));
        logd(eventTime, "state", stringBuilder.toString());
    }

    public void onRepeatModeChanged(EventTime eventTime, int i) {
        logd(eventTime, "repeatMode", getRepeatModeString(i));
    }

    public void onShuffleModeChanged(EventTime eventTime, boolean z) {
        logd(eventTime, "shuffleModeEnabled", Boolean.toString(z));
    }

    public void onPositionDiscontinuity(EventTime eventTime, int i) {
        logd(eventTime, "positionDiscontinuity", getDiscontinuityReasonString(i));
    }

    public void onSeekStarted(EventTime eventTime) {
        logd(eventTime, "seekStarted");
    }

    public void onPlaybackParametersChanged(EventTime eventTime, PlaybackParameters playbackParameters) {
        logd(eventTime, "playbackParameters", Util.formatInvariant("speed=%.2f, pitch=%.2f, skipSilence=%s", Float.valueOf(playbackParameters.speed), Float.valueOf(playbackParameters.pitch), Boolean.valueOf(playbackParameters.skipSilence)));
    }

    public void onTimelineChanged(EventTime eventTime, int i) {
        int periodCount = eventTime.timeline.getPeriodCount();
        int windowCount = eventTime.timeline.getWindowCount();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("timelineChanged [");
        stringBuilder.append(getEventTimeString(eventTime));
        stringBuilder.append(", periodCount=");
        stringBuilder.append(periodCount);
        stringBuilder.append(", windowCount=");
        stringBuilder.append(windowCount);
        stringBuilder.append(", reason=");
        stringBuilder.append(getTimelineChangeReasonString(i));
        logd(stringBuilder.toString());
        i = 0;
        for (int i2 = 0; i2 < Math.min(periodCount, 3); i2++) {
            eventTime.timeline.getPeriod(i2, this.period);
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("  period [");
            stringBuilder2.append(getTimeString(this.period.getDurationMs()));
            stringBuilder2.append("]");
            logd(stringBuilder2.toString());
        }
        if (periodCount > 3) {
            logd("  ...");
        }
        while (i < Math.min(windowCount, 3)) {
            eventTime.timeline.getWindow(i, this.window);
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("  window [");
            stringBuilder3.append(getTimeString(this.window.getDurationMs()));
            stringBuilder3.append(", ");
            stringBuilder3.append(this.window.isSeekable);
            stringBuilder3.append(", ");
            stringBuilder3.append(this.window.isDynamic);
            stringBuilder3.append("]");
            logd(stringBuilder3.toString());
            i++;
        }
        if (windowCount > 3) {
            logd("  ...");
        }
        logd("]");
    }

    public void onPlayerError(EventTime eventTime, ExoPlaybackException exoPlaybackException) {
        loge(eventTime, "playerFailed", exoPlaybackException);
    }

    public void onTracksChanged(EventTime eventTime, TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
        MappedTrackInfo currentMappedTrackInfo = this.trackSelector != null ? this.trackSelector.getCurrentMappedTrackInfo() : null;
        if (currentMappedTrackInfo == null) {
            logd(eventTime, "tracksChanged", "[]");
            return;
        }
        int i;
        StringBuilder stringBuilder;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("tracksChanged [");
        stringBuilder2.append(getEventTimeString(eventTime));
        stringBuilder2.append(", ");
        logd(stringBuilder2.toString());
        int rendererCount = currentMappedTrackInfo.getRendererCount();
        for (i = 0; i < rendererCount; i++) {
            TrackGroupArray trackGroups = currentMappedTrackInfo.getTrackGroups(i);
            TrackSelection trackSelection = trackSelectionArray.get(i);
            if (trackGroups.length > 0) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("  Renderer:");
                stringBuilder.append(i);
                stringBuilder.append(" [");
                logd(stringBuilder.toString());
                for (int i2 = 0; i2 < trackGroups.length; i2++) {
                    TrackGroup trackGroup = trackGroups.get(i2);
                    String adaptiveSupportString = getAdaptiveSupportString(trackGroup.length, currentMappedTrackInfo.getAdaptiveSupport(i, i2, false));
                    StringBuilder stringBuilder3 = new StringBuilder();
                    stringBuilder3.append("    Group:");
                    stringBuilder3.append(i2);
                    stringBuilder3.append(", adaptive_supported=");
                    stringBuilder3.append(adaptiveSupportString);
                    stringBuilder3.append(" [");
                    logd(stringBuilder3.toString());
                    for (int i3 = 0; i3 < trackGroup.length; i3++) {
                        String trackStatusString = getTrackStatusString(trackSelection, trackGroup, i3);
                        String formatSupportString = getFormatSupportString(currentMappedTrackInfo.getTrackSupport(i, i2, i3));
                        StringBuilder stringBuilder4 = new StringBuilder();
                        stringBuilder4.append("      ");
                        stringBuilder4.append(trackStatusString);
                        stringBuilder4.append(" Track:");
                        stringBuilder4.append(i3);
                        stringBuilder4.append(", ");
                        stringBuilder4.append(Format.toLogString(trackGroup.getFormat(i3)));
                        stringBuilder4.append(", supported=");
                        stringBuilder4.append(formatSupportString);
                        logd(stringBuilder4.toString());
                    }
                    logd("    ]");
                }
                if (trackSelection != null) {
                    for (int i4 = 0; i4 < trackSelection.length(); i4++) {
                        Metadata metadata = trackSelection.getFormat(i4).metadata;
                        if (metadata != null) {
                            logd("    Metadata [");
                            printMetadata(metadata, "      ");
                            logd("    ]");
                            break;
                        }
                    }
                }
                logd("  ]");
            }
        }
        TrackGroupArray unmappedTrackGroups = currentMappedTrackInfo.getUnmappedTrackGroups();
        if (unmappedTrackGroups.length > 0) {
            logd("  Renderer:None [");
            for (int i5 = 0; i5 < unmappedTrackGroups.length; i5++) {
                StringBuilder stringBuilder5 = new StringBuilder();
                stringBuilder5.append("    Group:");
                stringBuilder5.append(i5);
                stringBuilder5.append(" [");
                logd(stringBuilder5.toString());
                TrackGroup trackGroup2 = unmappedTrackGroups.get(i5);
                for (i = 0; i < trackGroup2.length; i++) {
                    String trackStatusString2 = getTrackStatusString(false);
                    String formatSupportString2 = getFormatSupportString(0);
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("      ");
                    stringBuilder.append(trackStatusString2);
                    stringBuilder.append(" Track:");
                    stringBuilder.append(i);
                    stringBuilder.append(", ");
                    stringBuilder.append(Format.toLogString(trackGroup2.getFormat(i)));
                    stringBuilder.append(", supported=");
                    stringBuilder.append(formatSupportString2);
                    logd(stringBuilder.toString());
                }
                logd("    ]");
            }
            logd("  ]");
        }
        logd("]");
    }

    public void onSeekProcessed(EventTime eventTime) {
        logd(eventTime, "seekProcessed");
    }

    public void onMetadata(EventTime eventTime, Metadata metadata) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("metadata [");
        stringBuilder.append(getEventTimeString(eventTime));
        stringBuilder.append(", ");
        logd(stringBuilder.toString());
        printMetadata(metadata, "  ");
        logd("]");
    }

    public void onDecoderEnabled(EventTime eventTime, int i, DecoderCounters decoderCounters) {
        logd(eventTime, "decoderEnabled", getTrackTypeString(i));
    }

    public void onAudioSessionId(EventTime eventTime, int i) {
        logd(eventTime, "audioSessionId", Integer.toString(i));
    }

    public void onDecoderInitialized(EventTime eventTime, int i, String str, long j) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getTrackTypeString(i));
        stringBuilder.append(", ");
        stringBuilder.append(str);
        logd(eventTime, "decoderInitialized", stringBuilder.toString());
    }

    public void onDecoderInputFormatChanged(EventTime eventTime, int i, Format format) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getTrackTypeString(i));
        stringBuilder.append(", ");
        stringBuilder.append(Format.toLogString(format));
        logd(eventTime, "decoderInputFormatChanged", stringBuilder.toString());
    }

    public void onDecoderDisabled(EventTime eventTime, int i, DecoderCounters decoderCounters) {
        logd(eventTime, "decoderDisabled", getTrackTypeString(i));
    }

    public void onAudioUnderrun(EventTime eventTime, int i, long j, long j2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(i);
        stringBuilder.append(", ");
        stringBuilder.append(j);
        stringBuilder.append(", ");
        stringBuilder.append(j2);
        stringBuilder.append("]");
        loge(eventTime, "audioTrackUnderrun", stringBuilder.toString(), null);
    }

    public void onDroppedVideoFrames(EventTime eventTime, int i, long j) {
        logd(eventTime, "droppedFrames", Integer.toString(i));
    }

    public void onVideoSizeChanged(EventTime eventTime, int i, int i2, int i3, float f) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(i);
        stringBuilder.append(", ");
        stringBuilder.append(i2);
        logd(eventTime, "videoSizeChanged", stringBuilder.toString());
    }

    public void onRenderedFirstFrame(EventTime eventTime, @Nullable Surface surface) {
        logd(eventTime, "renderedFirstFrame", String.valueOf(surface));
    }

    public void onMediaPeriodCreated(EventTime eventTime) {
        logd(eventTime, "mediaPeriodCreated");
    }

    public void onMediaPeriodReleased(EventTime eventTime) {
        logd(eventTime, "mediaPeriodReleased");
    }

    public void onLoadError(EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z) {
        printInternalError(eventTime, "loadError", iOException);
    }

    public void onReadingStarted(EventTime eventTime) {
        logd(eventTime, "mediaPeriodReadingStarted");
    }

    public void onSurfaceSizeChanged(EventTime eventTime, int i, int i2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(i);
        stringBuilder.append(", ");
        stringBuilder.append(i2);
        logd(eventTime, "surfaceSizeChanged", stringBuilder.toString());
    }

    public void onUpstreamDiscarded(EventTime eventTime, MediaLoadData mediaLoadData) {
        logd(eventTime, "upstreamDiscarded", Format.toLogString(mediaLoadData.trackFormat));
    }

    public void onDownstreamFormatChanged(EventTime eventTime, MediaLoadData mediaLoadData) {
        logd(eventTime, "downstreamFormatChanged", Format.toLogString(mediaLoadData.trackFormat));
    }

    public void onDrmSessionAcquired(EventTime eventTime) {
        logd(eventTime, "drmSessionAcquired");
    }

    public void onDrmSessionManagerError(EventTime eventTime, Exception exception) {
        printInternalError(eventTime, "drmSessionManagerError", exception);
    }

    public void onDrmKeysRestored(EventTime eventTime) {
        logd(eventTime, "drmKeysRestored");
    }

    public void onDrmKeysRemoved(EventTime eventTime) {
        logd(eventTime, "drmKeysRemoved");
    }

    public void onDrmKeysLoaded(EventTime eventTime) {
        logd(eventTime, "drmKeysLoaded");
    }

    public void onDrmSessionReleased(EventTime eventTime) {
        logd(eventTime, "drmSessionReleased");
    }

    /* Access modifiers changed, original: protected */
    public void logd(String str) {
        Log.d(this.tag, str);
    }

    /* Access modifiers changed, original: protected */
    public void loge(String str, @Nullable Throwable th) {
        Log.e(this.tag, str, th);
    }

    private void logd(EventTime eventTime, String str) {
        logd(getEventString(eventTime, str));
    }

    private void logd(EventTime eventTime, String str, String str2) {
        logd(getEventString(eventTime, str, str2));
    }

    private void loge(EventTime eventTime, String str, @Nullable Throwable th) {
        loge(getEventString(eventTime, str), th);
    }

    private void loge(EventTime eventTime, String str, String str2, @Nullable Throwable th) {
        loge(getEventString(eventTime, str, str2), th);
    }

    private void printInternalError(EventTime eventTime, String str, Exception exception) {
        loge(eventTime, "internalError", str, exception);
    }

    private void printMetadata(Metadata metadata, String str) {
        for (int i = 0; i < metadata.length(); i++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(metadata.get(i));
            logd(stringBuilder.toString());
        }
    }

    private String getEventString(EventTime eventTime, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(" [");
        stringBuilder.append(getEventTimeString(eventTime));
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    private String getEventString(EventTime eventTime, String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(" [");
        stringBuilder.append(getEventTimeString(eventTime));
        stringBuilder.append(", ");
        stringBuilder.append(str2);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    private String getEventTimeString(EventTime eventTime) {
        StringBuilder stringBuilder;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("window=");
        stringBuilder2.append(eventTime.windowIndex);
        String stringBuilder3 = stringBuilder2.toString();
        if (eventTime.mediaPeriodId != null) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(stringBuilder3);
            stringBuilder.append(", period=");
            stringBuilder.append(eventTime.timeline.getIndexOfPeriod(eventTime.mediaPeriodId.periodUid));
            stringBuilder3 = stringBuilder.toString();
            if (eventTime.mediaPeriodId.isAd()) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(stringBuilder3);
                stringBuilder.append(", adGroup=");
                stringBuilder.append(eventTime.mediaPeriodId.adGroupIndex);
                stringBuilder3 = stringBuilder.toString();
                stringBuilder = new StringBuilder();
                stringBuilder.append(stringBuilder3);
                stringBuilder.append(", ad=");
                stringBuilder.append(eventTime.mediaPeriodId.adIndexInAdGroup);
                stringBuilder3 = stringBuilder.toString();
            }
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append(getTimeString(eventTime.realtimeMs - this.startTimeMs));
        stringBuilder.append(", ");
        stringBuilder.append(getTimeString(eventTime.currentPlaybackPositionMs));
        stringBuilder.append(", ");
        stringBuilder.append(stringBuilder3);
        return stringBuilder.toString();
    }

    private static String getTimeString(long j) {
        return j == C.TIME_UNSET ? "?" : TIME_FORMAT.format((double) (((float) j) / 1000.0f));
    }

    private static String getTrackStatusString(@Nullable TrackSelection trackSelection, TrackGroup trackGroup, int i) {
        boolean z = (trackSelection == null || trackSelection.getTrackGroup() != trackGroup || trackSelection.indexOf(i) == -1) ? false : true;
        return getTrackStatusString(z);
    }

    private static String getTrackTypeString(int i) {
        switch (i) {
            case 0:
                return CBConstant.DEFAULT_VALUE;
            case 1:
                return "audio";
            case 2:
                return "video";
            case 3:
                return MimeTypes.BASE_TYPE_TEXT;
            case 4:
                return TtmlNode.TAG_METADATA;
            case 5:
                return "camera motion";
            case 6:
                return IdHelperAndroid.NO_ID_AVAILABLE;
            default:
                String stringBuilder;
                if (i >= 10000) {
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("custom (");
                    stringBuilder2.append(i);
                    stringBuilder2.append(")");
                    stringBuilder = stringBuilder2.toString();
                } else {
                    stringBuilder = "?";
                }
                return stringBuilder;
        }
    }
}
