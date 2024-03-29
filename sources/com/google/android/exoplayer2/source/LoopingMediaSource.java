package com.google.android.exoplayer2.source;

import android.support.annotation.Nullable;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaSource.MediaPeriodId;
import com.google.android.exoplayer2.source.ShuffleOrder.UnshuffledShuffleOrder;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import java.util.HashMap;
import java.util.Map;

public final class LoopingMediaSource extends CompositeMediaSource<Void> {
    private final Map<MediaPeriodId, MediaPeriodId> childMediaPeriodIdToMediaPeriodId;
    private final MediaSource childSource;
    private final int loopCount;
    private final Map<MediaPeriod, MediaPeriodId> mediaPeriodToChildMediaPeriodId;

    private static final class InfinitelyLoopingTimeline extends ForwardingTimeline {
        public InfinitelyLoopingTimeline(Timeline timeline) {
            super(timeline);
        }

        public int getNextWindowIndex(int i, int i2, boolean z) {
            i = this.timeline.getNextWindowIndex(i, i2, z);
            return i == -1 ? getFirstWindowIndex(z) : i;
        }

        public int getPreviousWindowIndex(int i, int i2, boolean z) {
            i = this.timeline.getPreviousWindowIndex(i, i2, z);
            return i == -1 ? getLastWindowIndex(z) : i;
        }
    }

    private static final class LoopingTimeline extends AbstractConcatenatedTimeline {
        private final int childPeriodCount;
        private final Timeline childTimeline;
        private final int childWindowCount;
        private final int loopCount;

        public LoopingTimeline(Timeline timeline, int i) {
            boolean z = false;
            super(false, new UnshuffledShuffleOrder(i));
            this.childTimeline = timeline;
            this.childPeriodCount = timeline.getPeriodCount();
            this.childWindowCount = timeline.getWindowCount();
            this.loopCount = i;
            if (this.childPeriodCount > 0) {
                if (i <= Integer.MAX_VALUE / this.childPeriodCount) {
                    z = true;
                }
                Assertions.checkState(z, "LoopingMediaSource contains too many periods");
            }
        }

        public int getWindowCount() {
            return this.childWindowCount * this.loopCount;
        }

        public int getPeriodCount() {
            return this.childPeriodCount * this.loopCount;
        }

        /* Access modifiers changed, original: protected */
        public int getChildIndexByPeriodIndex(int i) {
            return i / this.childPeriodCount;
        }

        /* Access modifiers changed, original: protected */
        public int getChildIndexByWindowIndex(int i) {
            return i / this.childWindowCount;
        }

        /* Access modifiers changed, original: protected */
        public int getChildIndexByChildUid(Object obj) {
            if (obj instanceof Integer) {
                return ((Integer) obj).intValue();
            }
            return -1;
        }

        /* Access modifiers changed, original: protected */
        public Timeline getTimelineByChildIndex(int i) {
            return this.childTimeline;
        }

        /* Access modifiers changed, original: protected */
        public int getFirstPeriodIndexByChildIndex(int i) {
            return i * this.childPeriodCount;
        }

        /* Access modifiers changed, original: protected */
        public int getFirstWindowIndexByChildIndex(int i) {
            return i * this.childWindowCount;
        }

        /* Access modifiers changed, original: protected */
        public Object getChildUidByChildIndex(int i) {
            return Integer.valueOf(i);
        }
    }

    public LoopingMediaSource(MediaSource mediaSource) {
        this(mediaSource, Integer.MAX_VALUE);
    }

    public LoopingMediaSource(MediaSource mediaSource, int i) {
        Assertions.checkArgument(i > 0);
        this.childSource = mediaSource;
        this.loopCount = i;
        this.childMediaPeriodIdToMediaPeriodId = new HashMap();
        this.mediaPeriodToChildMediaPeriodId = new HashMap();
    }

    @Nullable
    public Object getTag() {
        return this.childSource.getTag();
    }

    public void prepareSourceInternal(ExoPlayer exoPlayer, boolean z, @Nullable TransferListener transferListener) {
        super.prepareSourceInternal(exoPlayer, z, transferListener);
        prepareChildSource(null, this.childSource);
    }

    public MediaPeriod createPeriod(MediaPeriodId mediaPeriodId, Allocator allocator) {
        if (this.loopCount == Integer.MAX_VALUE) {
            return this.childSource.createPeriod(mediaPeriodId, allocator);
        }
        MediaPeriodId copyWithPeriodUid = mediaPeriodId.copyWithPeriodUid(AbstractConcatenatedTimeline.getChildPeriodUidFromConcatenatedUid(mediaPeriodId.periodUid));
        this.childMediaPeriodIdToMediaPeriodId.put(copyWithPeriodUid, mediaPeriodId);
        MediaPeriod createPeriod = this.childSource.createPeriod(copyWithPeriodUid, allocator);
        this.mediaPeriodToChildMediaPeriodId.put(createPeriod, copyWithPeriodUid);
        return createPeriod;
    }

    public void releasePeriod(MediaPeriod mediaPeriod) {
        this.childSource.releasePeriod(mediaPeriod);
        MediaPeriodId mediaPeriodId = (MediaPeriodId) this.mediaPeriodToChildMediaPeriodId.remove(mediaPeriod);
        if (mediaPeriodId != null) {
            this.childMediaPeriodIdToMediaPeriodId.remove(mediaPeriodId);
        }
    }

    /* Access modifiers changed, original: protected */
    public void onChildSourceInfoRefreshed(Void voidR, MediaSource mediaSource, Timeline timeline, @Nullable Object obj) {
        refreshSourceInfo(this.loopCount != Integer.MAX_VALUE ? new LoopingTimeline(timeline, this.loopCount) : new InfinitelyLoopingTimeline(timeline), obj);
    }

    /* Access modifiers changed, original: protected */
    @Nullable
    public MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(Void voidR, MediaPeriodId mediaPeriodId) {
        return this.loopCount != Integer.MAX_VALUE ? (MediaPeriodId) this.childMediaPeriodIdToMediaPeriodId.get(mediaPeriodId) : mediaPeriodId;
    }
}
