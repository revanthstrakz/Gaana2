package com.google.android.exoplayer2.source;

import android.support.annotation.Nullable;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.Timeline.Period;
import com.google.android.exoplayer2.Timeline.Window;
import com.google.android.exoplayer2.source.MediaSource.MediaPeriodId;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

public final class ClippingMediaSource extends CompositeMediaSource<Void> {
    private final boolean allowDynamicClippingUpdates;
    private IllegalClippingException clippingError;
    private ClippingTimeline clippingTimeline;
    private final boolean enableInitialDiscontinuity;
    private final long endUs;
    @Nullable
    private Object manifest;
    private final ArrayList<ClippingMediaPeriod> mediaPeriods;
    private final MediaSource mediaSource;
    private long periodEndUs;
    private long periodStartUs;
    private final boolean relativeToDefaultPosition;
    private final long startUs;
    private final Window window;

    private static final class ClippingTimeline extends ForwardingTimeline {
        private final long durationUs;
        private final long endUs;
        private final boolean isDynamic;
        private final long startUs;

        public ClippingTimeline(Timeline timeline, long j, long j2) throws IllegalClippingException {
            super(timeline);
            boolean z = false;
            if (timeline.getPeriodCount() != 1) {
                throw new IllegalClippingException(0);
            }
            Window window = timeline.getWindow(0, new Window());
            j = Math.max(0, j);
            j2 = j2 == Long.MIN_VALUE ? window.durationUs : Math.max(0, j2);
            if (window.durationUs != C.TIME_UNSET) {
                if (j2 > window.durationUs) {
                    j2 = window.durationUs;
                }
                if (j != 0 && !window.isSeekable) {
                    throw new IllegalClippingException(1);
                } else if (j > j2) {
                    throw new IllegalClippingException(2);
                }
            }
            this.startUs = j;
            this.endUs = j2;
            this.durationUs = j2 == C.TIME_UNSET ? C.TIME_UNSET : j2 - j;
            if (window.isDynamic && (j2 == C.TIME_UNSET || (window.durationUs != C.TIME_UNSET && j2 == window.durationUs))) {
                z = true;
            }
            this.isDynamic = z;
        }

        public Window getWindow(int i, Window window, boolean z, long j) {
            long j2;
            this.timeline.getWindow(0, window, z, 0);
            window.positionInFirstPeriodUs += this.startUs;
            window.durationUs = this.durationUs;
            window.isDynamic = this.isDynamic;
            if (window.defaultPositionUs != C.TIME_UNSET) {
                window.defaultPositionUs = Math.max(window.defaultPositionUs, this.startUs);
                if (this.endUs == C.TIME_UNSET) {
                    j2 = window.defaultPositionUs;
                } else {
                    j2 = Math.min(window.defaultPositionUs, this.endUs);
                }
                window.defaultPositionUs = j2;
                window.defaultPositionUs -= this.startUs;
            }
            j2 = C.usToMs(this.startUs);
            if (window.presentationStartTimeMs != C.TIME_UNSET) {
                window.presentationStartTimeMs += j2;
            }
            if (window.windowStartTimeMs != C.TIME_UNSET) {
                window.windowStartTimeMs += j2;
            }
            return window;
        }

        public Period getPeriod(int i, Period period, boolean z) {
            this.timeline.getPeriod(0, period, z);
            long positionInWindowUs = period.getPositionInWindowUs() - this.startUs;
            long j = this.durationUs;
            long j2 = C.TIME_UNSET;
            if (j != C.TIME_UNSET) {
                j2 = this.durationUs - positionInWindowUs;
            }
            return period.set(period.id, period.uid, 0, j2, positionInWindowUs);
        }
    }

    public static final class IllegalClippingException extends IOException {
        public static final int REASON_INVALID_PERIOD_COUNT = 0;
        public static final int REASON_NOT_SEEKABLE_TO_START = 1;
        public static final int REASON_START_EXCEEDS_END = 2;
        public final int reason;

        @Documented
        @Retention(RetentionPolicy.SOURCE)
        public @interface Reason {
        }

        private static String getReasonDescription(int i) {
            switch (i) {
                case 0:
                    return "invalid period count";
                case 1:
                    return "not seekable to start";
                case 2:
                    return "start exceeds end";
                default:
                    return "unknown";
            }
        }

        public IllegalClippingException(int i) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Illegal clipping: ");
            stringBuilder.append(getReasonDescription(i));
            super(stringBuilder.toString());
            this.reason = i;
        }
    }

    public ClippingMediaSource(MediaSource mediaSource, long j, long j2) {
        this(mediaSource, j, j2, true, false, false);
    }

    @Deprecated
    public ClippingMediaSource(MediaSource mediaSource, long j, long j2, boolean z) {
        this(mediaSource, j, j2, z, false, false);
    }

    public ClippingMediaSource(MediaSource mediaSource, long j) {
        this(mediaSource, 0, j, true, false, true);
    }

    public ClippingMediaSource(MediaSource mediaSource, long j, long j2, boolean z, boolean z2, boolean z3) {
        Assertions.checkArgument(j >= 0);
        this.mediaSource = (MediaSource) Assertions.checkNotNull(mediaSource);
        this.startUs = j;
        this.endUs = j2;
        this.enableInitialDiscontinuity = z;
        this.allowDynamicClippingUpdates = z2;
        this.relativeToDefaultPosition = z3;
        this.mediaPeriods = new ArrayList();
        this.window = new Window();
    }

    @Nullable
    public Object getTag() {
        return this.mediaSource.getTag();
    }

    public void prepareSourceInternal(ExoPlayer exoPlayer, boolean z, @Nullable TransferListener transferListener) {
        super.prepareSourceInternal(exoPlayer, z, transferListener);
        prepareChildSource(null, this.mediaSource);
    }

    public void maybeThrowSourceInfoRefreshError() throws IOException {
        if (this.clippingError != null) {
            throw this.clippingError;
        }
        super.maybeThrowSourceInfoRefreshError();
    }

    public MediaPeriod createPeriod(MediaPeriodId mediaPeriodId, Allocator allocator) {
        ClippingMediaPeriod clippingMediaPeriod = new ClippingMediaPeriod(this.mediaSource.createPeriod(mediaPeriodId, allocator), this.enableInitialDiscontinuity, this.periodStartUs, this.periodEndUs);
        this.mediaPeriods.add(clippingMediaPeriod);
        return clippingMediaPeriod;
    }

    public void releasePeriod(MediaPeriod mediaPeriod) {
        Assertions.checkState(this.mediaPeriods.remove(mediaPeriod));
        this.mediaSource.releasePeriod(((ClippingMediaPeriod) mediaPeriod).mediaPeriod);
        if (this.mediaPeriods.isEmpty() && !this.allowDynamicClippingUpdates) {
            refreshClippedTimeline(this.clippingTimeline.timeline);
        }
    }

    public void releaseSourceInternal() {
        super.releaseSourceInternal();
        this.clippingError = null;
        this.clippingTimeline = null;
    }

    /* Access modifiers changed, original: protected */
    public void onChildSourceInfoRefreshed(Void voidR, MediaSource mediaSource, Timeline timeline, @Nullable Object obj) {
        if (this.clippingError == null) {
            this.manifest = obj;
            refreshClippedTimeline(timeline);
        }
    }

    private void refreshClippedTimeline(Timeline timeline) {
        int i = 0;
        Timeline timeline2 = timeline;
        timeline2.getWindow(0, this.window);
        long positionInFirstPeriodUs = this.window.getPositionInFirstPeriodUs();
        long j = Long.MIN_VALUE;
        long j2;
        if (this.clippingTimeline == null || this.mediaPeriods.isEmpty() || this.allowDynamicClippingUpdates) {
            long j3;
            j2 = this.startUs;
            long j4 = this.endUs;
            if (this.relativeToDefaultPosition) {
                long defaultPositionUs = this.window.getDefaultPositionUs();
                j3 = j2 + defaultPositionUs;
                j4 += defaultPositionUs;
            } else {
                j3 = j2;
            }
            this.periodStartUs = positionInFirstPeriodUs + j3;
            if (this.endUs != Long.MIN_VALUE) {
                j = positionInFirstPeriodUs + j4;
            }
            this.periodEndUs = j;
            int size = this.mediaPeriods.size();
            while (i < size) {
                ((ClippingMediaPeriod) this.mediaPeriods.get(i)).updateClipping(this.periodStartUs, this.periodEndUs);
                i++;
            }
            j = j4;
            positionInFirstPeriodUs = j3;
        } else {
            j2 = this.periodStartUs - positionInFirstPeriodUs;
            if (this.endUs != Long.MIN_VALUE) {
                j = this.periodEndUs - positionInFirstPeriodUs;
            }
            positionInFirstPeriodUs = j2;
        }
        try {
            this.clippingTimeline = new ClippingTimeline(timeline2, positionInFirstPeriodUs, j);
            refreshSourceInfo(this.clippingTimeline, this.manifest);
        } catch (IllegalClippingException e) {
            this.clippingError = e;
        }
    }

    /* Access modifiers changed, original: protected */
    public long getMediaTimeForChildMediaTime(Void voidR, long j) {
        if (j == C.TIME_UNSET) {
            return C.TIME_UNSET;
        }
        long usToMs = C.usToMs(this.startUs);
        long max = Math.max(0, j - usToMs);
        if (this.endUs != Long.MIN_VALUE) {
            max = Math.min(C.usToMs(this.endUs) - usToMs, max);
        }
        return max;
    }
}
