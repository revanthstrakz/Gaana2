package com.google.android.exoplayer2;

import android.support.annotation.CheckResult;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.Timeline.Window;
import com.google.android.exoplayer2.source.MediaSource.MediaPeriodId;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectorResult;

final class PlaybackInfo {
    private static final MediaPeriodId DUMMY_MEDIA_PERIOD_ID = new MediaPeriodId(new Object());
    public volatile long bufferedPositionUs;
    public final long contentPositionUs;
    public final boolean isLoading;
    public final MediaPeriodId loadingMediaPeriodId;
    @Nullable
    public final Object manifest;
    public final MediaPeriodId periodId;
    public final int playbackState;
    public volatile long positionUs;
    public final long startPositionUs;
    public final Timeline timeline;
    public volatile long totalBufferedDurationUs;
    public final TrackGroupArray trackGroups;
    public final TrackSelectorResult trackSelectorResult;

    public static PlaybackInfo createDummy(long j, TrackSelectorResult trackSelectorResult) {
        return new PlaybackInfo(Timeline.EMPTY, null, DUMMY_MEDIA_PERIOD_ID, j, C.TIME_UNSET, 1, false, TrackGroupArray.EMPTY, trackSelectorResult, DUMMY_MEDIA_PERIOD_ID, j, 0, j);
    }

    public PlaybackInfo(Timeline timeline, @Nullable Object obj, MediaPeriodId mediaPeriodId, long j, long j2, int i, boolean z, TrackGroupArray trackGroupArray, TrackSelectorResult trackSelectorResult, MediaPeriodId mediaPeriodId2, long j3, long j4, long j5) {
        this.timeline = timeline;
        this.manifest = obj;
        this.periodId = mediaPeriodId;
        this.startPositionUs = j;
        this.contentPositionUs = j2;
        this.playbackState = i;
        this.isLoading = z;
        this.trackGroups = trackGroupArray;
        this.trackSelectorResult = trackSelectorResult;
        this.loadingMediaPeriodId = mediaPeriodId2;
        this.bufferedPositionUs = j3;
        this.totalBufferedDurationUs = j4;
        this.positionUs = j5;
    }

    public MediaPeriodId getDummyFirstMediaPeriodId(boolean z, Window window) {
        if (this.timeline.isEmpty()) {
            return DUMMY_MEDIA_PERIOD_ID;
        }
        return new MediaPeriodId(this.timeline.getUidOfPeriod(this.timeline.getWindow(this.timeline.getFirstWindowIndex(z), window).firstPeriodIndex));
    }

    @CheckResult
    public PlaybackInfo resetToNewPosition(MediaPeriodId mediaPeriodId, long j, long j2) {
        return new PlaybackInfo(this.timeline, this.manifest, mediaPeriodId, j, mediaPeriodId.isAd() ? j2 : C.TIME_UNSET, this.playbackState, this.isLoading, this.trackGroups, this.trackSelectorResult, mediaPeriodId, j, 0, j);
    }

    @CheckResult
    public PlaybackInfo copyWithNewPosition(MediaPeriodId mediaPeriodId, long j, long j2, long j3) {
        return new PlaybackInfo(this.timeline, this.manifest, mediaPeriodId, j, mediaPeriodId.isAd() ? j2 : C.TIME_UNSET, this.playbackState, this.isLoading, this.trackGroups, this.trackSelectorResult, this.loadingMediaPeriodId, this.bufferedPositionUs, j3, j);
    }

    @CheckResult
    public PlaybackInfo copyWithTimeline(Timeline timeline, Object obj) {
        MediaPeriodId mediaPeriodId = this.periodId;
        long j = this.startPositionUs;
        long j2 = this.contentPositionUs;
        int i = this.playbackState;
        boolean z = this.isLoading;
        TrackGroupArray trackGroupArray = this.trackGroups;
        TrackSelectorResult trackSelectorResult = this.trackSelectorResult;
        MediaPeriodId mediaPeriodId2 = this.loadingMediaPeriodId;
        long j3 = this.bufferedPositionUs;
        return new PlaybackInfo(timeline, obj, mediaPeriodId, j, j2, i, z, trackGroupArray, trackSelectorResult, mediaPeriodId2, j3, this.totalBufferedDurationUs, this.positionUs);
    }

    @CheckResult
    public PlaybackInfo copyWithPlaybackState(int i) {
        Timeline timeline = this.timeline;
        Object obj = this.manifest;
        MediaPeriodId mediaPeriodId = this.periodId;
        long j = this.startPositionUs;
        long j2 = this.contentPositionUs;
        boolean z = this.isLoading;
        TrackGroupArray trackGroupArray = this.trackGroups;
        TrackSelectorResult trackSelectorResult = this.trackSelectorResult;
        MediaPeriodId mediaPeriodId2 = this.loadingMediaPeriodId;
        long j3 = this.bufferedPositionUs;
        return new PlaybackInfo(timeline, obj, mediaPeriodId, j, j2, i, z, trackGroupArray, trackSelectorResult, mediaPeriodId2, j3, this.totalBufferedDurationUs, this.positionUs);
    }

    @CheckResult
    public PlaybackInfo copyWithIsLoading(boolean z) {
        Timeline timeline = this.timeline;
        Object obj = this.manifest;
        MediaPeriodId mediaPeriodId = this.periodId;
        long j = this.startPositionUs;
        long j2 = this.contentPositionUs;
        int i = this.playbackState;
        TrackGroupArray trackGroupArray = this.trackGroups;
        TrackSelectorResult trackSelectorResult = this.trackSelectorResult;
        MediaPeriodId mediaPeriodId2 = this.loadingMediaPeriodId;
        long j3 = this.bufferedPositionUs;
        return new PlaybackInfo(timeline, obj, mediaPeriodId, j, j2, i, z, trackGroupArray, trackSelectorResult, mediaPeriodId2, j3, this.totalBufferedDurationUs, this.positionUs);
    }

    @CheckResult
    public PlaybackInfo copyWithTrackInfo(TrackGroupArray trackGroupArray, TrackSelectorResult trackSelectorResult) {
        Timeline timeline = this.timeline;
        Object obj = this.manifest;
        MediaPeriodId mediaPeriodId = this.periodId;
        long j = this.startPositionUs;
        long j2 = this.contentPositionUs;
        int i = this.playbackState;
        boolean z = this.isLoading;
        MediaPeriodId mediaPeriodId2 = this.loadingMediaPeriodId;
        long j3 = this.bufferedPositionUs;
        return new PlaybackInfo(timeline, obj, mediaPeriodId, j, j2, i, z, trackGroupArray, trackSelectorResult, mediaPeriodId2, j3, this.totalBufferedDurationUs, this.positionUs);
    }

    @CheckResult
    public PlaybackInfo copyWithLoadingMediaPeriodId(MediaPeriodId mediaPeriodId) {
        Timeline timeline = this.timeline;
        Object obj = this.manifest;
        MediaPeriodId mediaPeriodId2 = this.periodId;
        long j = this.startPositionUs;
        long j2 = this.contentPositionUs;
        int i = this.playbackState;
        boolean z = this.isLoading;
        TrackGroupArray trackGroupArray = this.trackGroups;
        TrackSelectorResult trackSelectorResult = this.trackSelectorResult;
        long j3 = this.bufferedPositionUs;
        return new PlaybackInfo(timeline, obj, mediaPeriodId2, j, j2, i, z, trackGroupArray, trackSelectorResult, mediaPeriodId, j3, this.totalBufferedDurationUs, this.positionUs);
    }
}
