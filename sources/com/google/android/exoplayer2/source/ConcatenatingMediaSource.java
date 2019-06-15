package com.google.android.exoplayer2.source;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.PlayerMessage.Target;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.Timeline.Period;
import com.google.android.exoplayer2.Timeline.Window;
import com.google.android.exoplayer2.source.MediaSource.MediaPeriodId;
import com.google.android.exoplayer2.source.ShuffleOrder.DefaultShuffleOrder;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

public class ConcatenatingMediaSource extends CompositeMediaSource<MediaSourceHolder> implements Target {
    private static final int MSG_ADD = 0;
    private static final int MSG_MOVE = 2;
    private static final int MSG_NOTIFY_LISTENER = 4;
    private static final int MSG_ON_COMPLETION = 5;
    private static final int MSG_REMOVE = 1;
    private static final int MSG_SET_SHUFFLE_ORDER = 3;
    private final boolean isAtomic;
    private boolean listenerNotificationScheduled;
    private final Map<MediaPeriod, MediaSourceHolder> mediaSourceByMediaPeriod;
    private final Map<Object, MediaSourceHolder> mediaSourceByUid;
    private final List<MediaSourceHolder> mediaSourceHolders;
    private final List<MediaSourceHolder> mediaSourcesPublic;
    private final List<Runnable> pendingOnCompletionActions;
    private final Period period;
    private int periodCount;
    @Nullable
    private ExoPlayer player;
    @Nullable
    private Handler playerApplicationHandler;
    private ShuffleOrder shuffleOrder;
    private final boolean useLazyPreparation;
    private final Window window;
    private int windowCount;

    static final class MediaSourceHolder implements Comparable<MediaSourceHolder> {
        public List<DeferredMediaPeriod> activeMediaPeriods = new ArrayList();
        public int childIndex;
        public int firstPeriodIndexInChild;
        public int firstWindowIndexInChild;
        public boolean hasStartedPreparing;
        public boolean isPrepared;
        public boolean isRemoved;
        public final MediaSource mediaSource;
        public DeferredTimeline timeline;
        public final Object uid = new Object();

        public MediaSourceHolder(MediaSource mediaSource) {
            this.mediaSource = mediaSource;
            this.timeline = DeferredTimeline.createWithDummyTimeline(mediaSource.getTag());
        }

        public void reset(int i, int i2, int i3) {
            this.childIndex = i;
            this.firstWindowIndexInChild = i2;
            this.firstPeriodIndexInChild = i3;
            this.hasStartedPreparing = false;
            this.isPrepared = false;
            this.isRemoved = false;
            this.activeMediaPeriods.clear();
        }

        public int compareTo(@NonNull MediaSourceHolder mediaSourceHolder) {
            return this.firstPeriodIndexInChild - mediaSourceHolder.firstPeriodIndexInChild;
        }
    }

    private static final class MessageData<T> {
        @Nullable
        public final Runnable actionOnCompletion;
        public final T customData;
        public final int index;

        public MessageData(int i, T t, @Nullable Runnable runnable) {
            this.index = i;
            this.actionOnCompletion = runnable;
            this.customData = t;
        }
    }

    private static final class DummyTimeline extends Timeline {
        @Nullable
        private final Object tag;

        public int getPeriodCount() {
            return 1;
        }

        public int getWindowCount() {
            return 1;
        }

        public DummyTimeline(@Nullable Object obj) {
            this.tag = obj;
        }

        public Window getWindow(int i, Window window, boolean z, long j) {
            return window.set(this.tag, C.TIME_UNSET, C.TIME_UNSET, false, true, 0, C.TIME_UNSET, 0, 0, 0);
        }

        public Period getPeriod(int i, Period period, boolean z) {
            return period.set(Integer.valueOf(0), DeferredTimeline.DUMMY_ID, 0, C.TIME_UNSET, 0);
        }

        public int getIndexOfPeriod(Object obj) {
            return obj == DeferredTimeline.DUMMY_ID ? 0 : -1;
        }

        public Object getUidOfPeriod(int i) {
            return DeferredTimeline.DUMMY_ID;
        }
    }

    private static final class ConcatenatedTimeline extends AbstractConcatenatedTimeline {
        private final HashMap<Object, Integer> childIndexByUid = new HashMap();
        private final int[] firstPeriodInChildIndices;
        private final int[] firstWindowInChildIndices;
        private final int periodCount;
        private final Timeline[] timelines;
        private final Object[] uids;
        private final int windowCount;

        public ConcatenatedTimeline(Collection<MediaSourceHolder> collection, int i, int i2, ShuffleOrder shuffleOrder, boolean z) {
            super(z, shuffleOrder);
            this.windowCount = i;
            this.periodCount = i2;
            i = collection.size();
            this.firstPeriodInChildIndices = new int[i];
            this.firstWindowInChildIndices = new int[i];
            this.timelines = new Timeline[i];
            this.uids = new Object[i];
            i = 0;
            for (MediaSourceHolder mediaSourceHolder : collection) {
                this.timelines[i] = mediaSourceHolder.timeline;
                this.firstPeriodInChildIndices[i] = mediaSourceHolder.firstPeriodIndexInChild;
                this.firstWindowInChildIndices[i] = mediaSourceHolder.firstWindowIndexInChild;
                this.uids[i] = mediaSourceHolder.uid;
                int i3 = i + 1;
                this.childIndexByUid.put(this.uids[i], Integer.valueOf(i));
                i = i3;
            }
        }

        /* Access modifiers changed, original: protected */
        public int getChildIndexByPeriodIndex(int i) {
            return Util.binarySearchFloor(this.firstPeriodInChildIndices, i + 1, false, false);
        }

        /* Access modifiers changed, original: protected */
        public int getChildIndexByWindowIndex(int i) {
            return Util.binarySearchFloor(this.firstWindowInChildIndices, i + 1, false, false);
        }

        /* Access modifiers changed, original: protected */
        public int getChildIndexByChildUid(Object obj) {
            Integer num = (Integer) this.childIndexByUid.get(obj);
            if (num == null) {
                return -1;
            }
            return num.intValue();
        }

        /* Access modifiers changed, original: protected */
        public Timeline getTimelineByChildIndex(int i) {
            return this.timelines[i];
        }

        /* Access modifiers changed, original: protected */
        public int getFirstPeriodIndexByChildIndex(int i) {
            return this.firstPeriodInChildIndices[i];
        }

        /* Access modifiers changed, original: protected */
        public int getFirstWindowIndexByChildIndex(int i) {
            return this.firstWindowInChildIndices[i];
        }

        /* Access modifiers changed, original: protected */
        public Object getChildUidByChildIndex(int i) {
            return this.uids[i];
        }

        public int getWindowCount() {
            return this.windowCount;
        }

        public int getPeriodCount() {
            return this.periodCount;
        }
    }

    private static final class DeferredTimeline extends ForwardingTimeline {
        private static final Object DUMMY_ID = new Object();
        private final Object replacedId;

        public static DeferredTimeline createWithDummyTimeline(@Nullable Object obj) {
            return new DeferredTimeline(new DummyTimeline(obj), DUMMY_ID);
        }

        public static DeferredTimeline createWithRealTimeline(Timeline timeline, Object obj) {
            return new DeferredTimeline(timeline, obj);
        }

        private DeferredTimeline(Timeline timeline, Object obj) {
            super(timeline);
            this.replacedId = obj;
        }

        public DeferredTimeline cloneWithUpdatedTimeline(Timeline timeline) {
            return new DeferredTimeline(timeline, this.replacedId);
        }

        public Timeline getTimeline() {
            return this.timeline;
        }

        public Period getPeriod(int i, Period period, boolean z) {
            this.timeline.getPeriod(i, period, z);
            if (Util.areEqual(period.uid, this.replacedId)) {
                period.uid = DUMMY_ID;
            }
            return period;
        }

        public int getIndexOfPeriod(Object obj) {
            Timeline timeline = this.timeline;
            if (DUMMY_ID.equals(obj)) {
                obj = this.replacedId;
            }
            return timeline.getIndexOfPeriod(obj);
        }

        public Object getUidOfPeriod(int i) {
            Object uidOfPeriod = this.timeline.getUidOfPeriod(i);
            return Util.areEqual(uidOfPeriod, this.replacedId) ? DUMMY_ID : uidOfPeriod;
        }
    }

    private static final class DummyMediaSource extends BaseMediaSource {
        @Nullable
        public Object getTag() {
            return null;
        }

        public void maybeThrowSourceInfoRefreshError() throws IOException {
        }

        /* Access modifiers changed, original: protected */
        public void prepareSourceInternal(ExoPlayer exoPlayer, boolean z, @Nullable TransferListener transferListener) {
        }

        public void releasePeriod(MediaPeriod mediaPeriod) {
        }

        /* Access modifiers changed, original: protected */
        public void releaseSourceInternal() {
        }

        private DummyMediaSource() {
        }

        public MediaPeriod createPeriod(MediaPeriodId mediaPeriodId, Allocator allocator) {
            throw new UnsupportedOperationException();
        }
    }

    @Nullable
    public Object getTag() {
        return null;
    }

    public void maybeThrowSourceInfoRefreshError() throws IOException {
    }

    public ConcatenatingMediaSource(MediaSource... mediaSourceArr) {
        this(false, mediaSourceArr);
    }

    public ConcatenatingMediaSource(boolean z, MediaSource... mediaSourceArr) {
        this(z, new DefaultShuffleOrder(0), mediaSourceArr);
    }

    public ConcatenatingMediaSource(boolean z, ShuffleOrder shuffleOrder, MediaSource... mediaSourceArr) {
        this(z, false, shuffleOrder, mediaSourceArr);
    }

    public ConcatenatingMediaSource(boolean z, boolean z2, ShuffleOrder shuffleOrder, MediaSource... mediaSourceArr) {
        for (Object checkNotNull : mediaSourceArr) {
            Assertions.checkNotNull(checkNotNull);
        }
        if (shuffleOrder.getLength() > 0) {
            shuffleOrder = shuffleOrder.cloneAndClear();
        }
        this.shuffleOrder = shuffleOrder;
        this.mediaSourceByMediaPeriod = new IdentityHashMap();
        this.mediaSourceByUid = new HashMap();
        this.mediaSourcesPublic = new ArrayList();
        this.mediaSourceHolders = new ArrayList();
        this.pendingOnCompletionActions = new ArrayList();
        this.isAtomic = z;
        this.useLazyPreparation = z2;
        this.window = new Window();
        this.period = new Period();
        addMediaSources(Arrays.asList(mediaSourceArr));
    }

    public final synchronized void addMediaSource(MediaSource mediaSource) {
        addMediaSource(this.mediaSourcesPublic.size(), mediaSource, null);
    }

    public final synchronized void addMediaSource(MediaSource mediaSource, @Nullable Runnable runnable) {
        addMediaSource(this.mediaSourcesPublic.size(), mediaSource, runnable);
    }

    public final synchronized void addMediaSource(int i, MediaSource mediaSource) {
        addMediaSource(i, mediaSource, null);
    }

    public final synchronized void addMediaSource(int i, MediaSource mediaSource, @Nullable Runnable runnable) {
        addMediaSources(i, Collections.singletonList(mediaSource), runnable);
    }

    public final synchronized void addMediaSources(Collection<MediaSource> collection) {
        addMediaSources(this.mediaSourcesPublic.size(), collection, null);
    }

    public final synchronized void addMediaSources(Collection<MediaSource> collection, @Nullable Runnable runnable) {
        addMediaSources(this.mediaSourcesPublic.size(), collection, runnable);
    }

    public final synchronized void addMediaSources(int i, Collection<MediaSource> collection) {
        addMediaSources(i, collection, null);
    }

    public final synchronized void addMediaSources(int i, Collection<MediaSource> collection, @Nullable Runnable runnable) {
        for (MediaSource checkNotNull : collection) {
            Assertions.checkNotNull(checkNotNull);
        }
        ArrayList arrayList = new ArrayList(collection.size());
        for (MediaSource mediaSourceHolder : collection) {
            arrayList.add(new MediaSourceHolder(mediaSourceHolder));
        }
        this.mediaSourcesPublic.addAll(i, arrayList);
        if (this.player != null && !collection.isEmpty()) {
            this.player.createMessage(this).setType(0).setPayload(new MessageData(i, arrayList, runnable)).send();
        } else if (runnable != null) {
            runnable.run();
        }
    }

    public final synchronized void removeMediaSource(int i) {
        removeMediaSource(i, null);
    }

    public final synchronized void removeMediaSource(int i, @Nullable Runnable runnable) {
        removeMediaSourceRange(i, i + 1, runnable);
    }

    public final synchronized void removeMediaSourceRange(int i, int i2) {
        removeMediaSourceRange(i, i2, null);
    }

    /* JADX WARNING: Missing block: B:7:0x000e, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:15:0x0035, code skipped:
            return;
     */
    public final synchronized void removeMediaSourceRange(int r3, int r4, @android.support.annotation.Nullable java.lang.Runnable r5) {
        /*
        r2 = this;
        monitor-enter(r2);
        r0 = r2.mediaSourcesPublic;	 Catch:{ all -> 0x0036 }
        com.google.android.exoplayer2.util.Util.removeRange(r0, r3, r4);	 Catch:{ all -> 0x0036 }
        if (r3 != r4) goto L_0x000f;
    L_0x0008:
        if (r5 == 0) goto L_0x000d;
    L_0x000a:
        r5.run();	 Catch:{ all -> 0x0036 }
    L_0x000d:
        monitor-exit(r2);
        return;
    L_0x000f:
        r0 = r2.player;	 Catch:{ all -> 0x0036 }
        if (r0 == 0) goto L_0x002f;
    L_0x0013:
        r0 = r2.player;	 Catch:{ all -> 0x0036 }
        r0 = r0.createMessage(r2);	 Catch:{ all -> 0x0036 }
        r1 = 1;
        r0 = r0.setType(r1);	 Catch:{ all -> 0x0036 }
        r1 = new com.google.android.exoplayer2.source.ConcatenatingMediaSource$MessageData;	 Catch:{ all -> 0x0036 }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ all -> 0x0036 }
        r1.<init>(r3, r4, r5);	 Catch:{ all -> 0x0036 }
        r3 = r0.setPayload(r1);	 Catch:{ all -> 0x0036 }
        r3.send();	 Catch:{ all -> 0x0036 }
        goto L_0x0034;
    L_0x002f:
        if (r5 == 0) goto L_0x0034;
    L_0x0031:
        r5.run();	 Catch:{ all -> 0x0036 }
    L_0x0034:
        monitor-exit(r2);
        return;
    L_0x0036:
        r3 = move-exception;
        monitor-exit(r2);
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.ConcatenatingMediaSource.removeMediaSourceRange(int, int, java.lang.Runnable):void");
    }

    public final synchronized void moveMediaSource(int i, int i2) {
        moveMediaSource(i, i2, null);
    }

    /* JADX WARNING: Missing block: B:6:0x0009, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:14:0x003b, code skipped:
            return;
     */
    public final synchronized void moveMediaSource(int r3, int r4, @android.support.annotation.Nullable java.lang.Runnable r5) {
        /*
        r2 = this;
        monitor-enter(r2);
        if (r3 != r4) goto L_0x000a;
    L_0x0003:
        if (r5 == 0) goto L_0x0008;
    L_0x0005:
        r5.run();	 Catch:{ all -> 0x003c }
    L_0x0008:
        monitor-exit(r2);
        return;
    L_0x000a:
        r0 = r2.mediaSourcesPublic;	 Catch:{ all -> 0x003c }
        r1 = r2.mediaSourcesPublic;	 Catch:{ all -> 0x003c }
        r1 = r1.remove(r3);	 Catch:{ all -> 0x003c }
        r0.add(r4, r1);	 Catch:{ all -> 0x003c }
        r0 = r2.player;	 Catch:{ all -> 0x003c }
        if (r0 == 0) goto L_0x0035;
    L_0x0019:
        r0 = r2.player;	 Catch:{ all -> 0x003c }
        r0 = r0.createMessage(r2);	 Catch:{ all -> 0x003c }
        r1 = 2;
        r0 = r0.setType(r1);	 Catch:{ all -> 0x003c }
        r1 = new com.google.android.exoplayer2.source.ConcatenatingMediaSource$MessageData;	 Catch:{ all -> 0x003c }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ all -> 0x003c }
        r1.<init>(r3, r4, r5);	 Catch:{ all -> 0x003c }
        r3 = r0.setPayload(r1);	 Catch:{ all -> 0x003c }
        r3.send();	 Catch:{ all -> 0x003c }
        goto L_0x003a;
    L_0x0035:
        if (r5 == 0) goto L_0x003a;
    L_0x0037:
        r5.run();	 Catch:{ all -> 0x003c }
    L_0x003a:
        monitor-exit(r2);
        return;
    L_0x003c:
        r3 = move-exception;
        monitor-exit(r2);
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.ConcatenatingMediaSource.moveMediaSource(int, int, java.lang.Runnable):void");
    }

    public final synchronized void clear() {
        clear(null);
    }

    public final synchronized void clear(@Nullable Runnable runnable) {
        removeMediaSourceRange(0, getSize(), runnable);
    }

    public final synchronized int getSize() {
        return this.mediaSourcesPublic.size();
    }

    public final synchronized MediaSource getMediaSource(int i) {
        return ((MediaSourceHolder) this.mediaSourcesPublic.get(i)).mediaSource;
    }

    public final synchronized void setShuffleOrder(ShuffleOrder shuffleOrder) {
        setShuffleOrder(shuffleOrder, null);
    }

    public final synchronized void setShuffleOrder(ShuffleOrder shuffleOrder, @Nullable Runnable runnable) {
        ExoPlayer exoPlayer = this.player;
        if (exoPlayer != null) {
            Object cloneAndInsert;
            int size = getSize();
            if (shuffleOrder.getLength() != size) {
                cloneAndInsert = shuffleOrder.cloneAndClear().cloneAndInsert(0, size);
            }
            exoPlayer.createMessage(this).setType(3).setPayload(new MessageData(0, cloneAndInsert, runnable)).send();
        } else {
            if (shuffleOrder.getLength() > 0) {
                shuffleOrder = shuffleOrder.cloneAndClear();
            }
            this.shuffleOrder = shuffleOrder;
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    public final synchronized void prepareSourceInternal(ExoPlayer exoPlayer, boolean z, @Nullable TransferListener transferListener) {
        super.prepareSourceInternal(exoPlayer, z, transferListener);
        this.player = exoPlayer;
        this.playerApplicationHandler = new Handler(exoPlayer.getApplicationLooper());
        if (this.mediaSourcesPublic.isEmpty()) {
            notifyListener();
        } else {
            this.shuffleOrder = this.shuffleOrder.cloneAndInsert(0, this.mediaSourcesPublic.size());
            addMediaSourcesInternal(0, this.mediaSourcesPublic);
            scheduleListenerNotification(null);
        }
    }

    public final MediaPeriod createPeriod(MediaPeriodId mediaPeriodId, Allocator allocator) {
        MediaSourceHolder mediaSourceHolder = (MediaSourceHolder) this.mediaSourceByUid.get(getMediaSourceHolderUid(mediaPeriodId.periodUid));
        if (mediaSourceHolder == null) {
            mediaSourceHolder = new MediaSourceHolder(new DummyMediaSource());
            mediaSourceHolder.hasStartedPreparing = true;
        }
        DeferredMediaPeriod deferredMediaPeriod = new DeferredMediaPeriod(mediaSourceHolder.mediaSource, mediaPeriodId, allocator);
        this.mediaSourceByMediaPeriod.put(deferredMediaPeriod, mediaSourceHolder);
        mediaSourceHolder.activeMediaPeriods.add(deferredMediaPeriod);
        if (!mediaSourceHolder.hasStartedPreparing) {
            mediaSourceHolder.hasStartedPreparing = true;
            prepareChildSource(mediaSourceHolder, mediaSourceHolder.mediaSource);
        } else if (mediaSourceHolder.isPrepared) {
            deferredMediaPeriod.createPeriod(mediaPeriodId.copyWithPeriodUid(getChildPeriodUid(mediaSourceHolder, mediaPeriodId.periodUid)));
        }
        return deferredMediaPeriod;
    }

    public final void releasePeriod(MediaPeriod mediaPeriod) {
        MediaSourceHolder mediaSourceHolder = (MediaSourceHolder) Assertions.checkNotNull(this.mediaSourceByMediaPeriod.remove(mediaPeriod));
        ((DeferredMediaPeriod) mediaPeriod).releasePeriod();
        mediaSourceHolder.activeMediaPeriods.remove(mediaPeriod);
        maybeReleaseChildSource(mediaSourceHolder);
    }

    public final void releaseSourceInternal() {
        super.releaseSourceInternal();
        this.mediaSourceHolders.clear();
        this.mediaSourceByUid.clear();
        this.player = null;
        this.playerApplicationHandler = null;
        this.shuffleOrder = this.shuffleOrder.cloneAndClear();
        this.windowCount = 0;
        this.periodCount = 0;
    }

    /* Access modifiers changed, original: protected|final */
    public final void onChildSourceInfoRefreshed(MediaSourceHolder mediaSourceHolder, MediaSource mediaSource, Timeline timeline, @Nullable Object obj) {
        updateMediaSourceInternal(mediaSourceHolder, timeline);
    }

    /* Access modifiers changed, original: protected */
    @Nullable
    public MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(MediaSourceHolder mediaSourceHolder, MediaPeriodId mediaPeriodId) {
        for (int i = 0; i < mediaSourceHolder.activeMediaPeriods.size(); i++) {
            if (((DeferredMediaPeriod) mediaSourceHolder.activeMediaPeriods.get(i)).id.windowSequenceNumber == mediaPeriodId.windowSequenceNumber) {
                return mediaPeriodId.copyWithPeriodUid(getPeriodUid(mediaSourceHolder, mediaPeriodId.periodUid));
            }
        }
        return null;
    }

    /* Access modifiers changed, original: protected */
    public int getWindowIndexForChildWindowIndex(MediaSourceHolder mediaSourceHolder, int i) {
        return i + mediaSourceHolder.firstWindowIndexInChild;
    }

    public final void handleMessage(int i, @Nullable Object obj) throws ExoPlaybackException {
        if (this.player != null) {
            MessageData messageData;
            switch (i) {
                case 0:
                    messageData = (MessageData) Util.castNonNull(obj);
                    this.shuffleOrder = this.shuffleOrder.cloneAndInsert(messageData.index, ((Collection) messageData.customData).size());
                    addMediaSourcesInternal(messageData.index, (Collection) messageData.customData);
                    scheduleListenerNotification(messageData.actionOnCompletion);
                    break;
                case 1:
                    messageData = (MessageData) Util.castNonNull(obj);
                    int i2 = messageData.index;
                    int intValue = ((Integer) messageData.customData).intValue();
                    if (i2 == 0 && intValue == this.shuffleOrder.getLength()) {
                        this.shuffleOrder = this.shuffleOrder.cloneAndClear();
                    } else {
                        this.shuffleOrder = this.shuffleOrder.cloneAndRemove(i2, intValue);
                    }
                    for (intValue--; intValue >= i2; intValue--) {
                        removeMediaSourceInternal(intValue);
                    }
                    scheduleListenerNotification(messageData.actionOnCompletion);
                    break;
                case 2:
                    messageData = (MessageData) Util.castNonNull(obj);
                    this.shuffleOrder = this.shuffleOrder.cloneAndRemove(messageData.index, messageData.index + 1);
                    this.shuffleOrder = this.shuffleOrder.cloneAndInsert(((Integer) messageData.customData).intValue(), 1);
                    moveMediaSourceInternal(messageData.index, ((Integer) messageData.customData).intValue());
                    scheduleListenerNotification(messageData.actionOnCompletion);
                    break;
                case 3:
                    messageData = (MessageData) Util.castNonNull(obj);
                    this.shuffleOrder = (ShuffleOrder) messageData.customData;
                    scheduleListenerNotification(messageData.actionOnCompletion);
                    break;
                case 4:
                    notifyListener();
                    break;
                case 5:
                    List list = (List) Util.castNonNull(obj);
                    Handler handler = (Handler) Assertions.checkNotNull(this.playerApplicationHandler);
                    for (int i3 = 0; i3 < list.size(); i3++) {
                        handler.post((Runnable) list.get(i3));
                    }
                    break;
                default:
                    throw new IllegalStateException();
            }
        }
    }

    private void scheduleListenerNotification(@Nullable Runnable runnable) {
        if (!this.listenerNotificationScheduled) {
            ((ExoPlayer) Assertions.checkNotNull(this.player)).createMessage(this).setType(4).send();
            this.listenerNotificationScheduled = true;
        }
        if (runnable != null) {
            this.pendingOnCompletionActions.add(runnable);
        }
    }

    private void notifyListener() {
        this.listenerNotificationScheduled = false;
        List emptyList = this.pendingOnCompletionActions.isEmpty() ? Collections.emptyList() : new ArrayList(this.pendingOnCompletionActions);
        this.pendingOnCompletionActions.clear();
        refreshSourceInfo(new ConcatenatedTimeline(this.mediaSourceHolders, this.windowCount, this.periodCount, this.shuffleOrder, this.isAtomic), null);
        if (!emptyList.isEmpty()) {
            ((ExoPlayer) Assertions.checkNotNull(this.player)).createMessage(this).setType(5).setPayload(emptyList).send();
        }
    }

    private void addMediaSourcesInternal(int i, Collection<MediaSourceHolder> collection) {
        for (MediaSourceHolder addMediaSourceInternal : collection) {
            int i2 = i + 1;
            addMediaSourceInternal(i, addMediaSourceInternal);
            i = i2;
        }
    }

    private void addMediaSourceInternal(int i, MediaSourceHolder mediaSourceHolder) {
        if (i > 0) {
            MediaSourceHolder mediaSourceHolder2 = (MediaSourceHolder) this.mediaSourceHolders.get(i - 1);
            mediaSourceHolder.reset(i, mediaSourceHolder2.firstWindowIndexInChild + mediaSourceHolder2.timeline.getWindowCount(), mediaSourceHolder2.firstPeriodIndexInChild + mediaSourceHolder2.timeline.getPeriodCount());
        } else {
            mediaSourceHolder.reset(i, 0, 0);
        }
        correctOffsets(i, 1, mediaSourceHolder.timeline.getWindowCount(), mediaSourceHolder.timeline.getPeriodCount());
        this.mediaSourceHolders.add(i, mediaSourceHolder);
        this.mediaSourceByUid.put(mediaSourceHolder.uid, mediaSourceHolder);
        if (!this.useLazyPreparation) {
            mediaSourceHolder.hasStartedPreparing = true;
            prepareChildSource(mediaSourceHolder, mediaSourceHolder.mediaSource);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x00a0  */
    private void updateMediaSourceInternal(com.google.android.exoplayer2.source.ConcatenatingMediaSource.MediaSourceHolder r12, com.google.android.exoplayer2.Timeline r13) {
        /*
        r11 = this;
        if (r12 != 0) goto L_0x0008;
    L_0x0002:
        r0 = new java.lang.IllegalArgumentException;
        r0.<init>();
        throw r0;
    L_0x0008:
        r1 = r12.timeline;
        r2 = r1.getTimeline();
        if (r2 != r13) goto L_0x0011;
    L_0x0010:
        return;
    L_0x0011:
        r2 = r13.getWindowCount();
        r3 = r1.getWindowCount();
        r2 = r2 - r3;
        r3 = r13.getPeriodCount();
        r4 = r1.getPeriodCount();
        r3 = r3 - r4;
        r4 = 0;
        r7 = 1;
        if (r2 != 0) goto L_0x0029;
    L_0x0027:
        if (r3 == 0) goto L_0x002f;
    L_0x0029:
        r5 = r12.childIndex;
        r5 = r5 + r7;
        r11.correctOffsets(r5, r4, r2, r3);
    L_0x002f:
        r2 = r12.isPrepared;
        r8 = 0;
        if (r2 == 0) goto L_0x003c;
    L_0x0034:
        r1 = r1.cloneWithUpdatedTimeline(r13);
        r12.timeline = r1;
        goto L_0x00b4;
    L_0x003c:
        r1 = r13.isEmpty();
        if (r1 == 0) goto L_0x004e;
    L_0x0042:
        r1 = com.google.android.exoplayer2.source.ConcatenatingMediaSource.DeferredTimeline.DUMMY_ID;
        r1 = com.google.android.exoplayer2.source.ConcatenatingMediaSource.DeferredTimeline.createWithRealTimeline(r13, r1);
        r12.timeline = r1;
        goto L_0x00b4;
    L_0x004e:
        r1 = r12.activeMediaPeriods;
        r1 = r1.size();
        if (r1 > r7) goto L_0x0058;
    L_0x0056:
        r1 = r7;
        goto L_0x0059;
    L_0x0058:
        r1 = r4;
    L_0x0059:
        com.google.android.exoplayer2.util.Assertions.checkState(r1);
        r1 = r12.activeMediaPeriods;
        r1 = r1.isEmpty();
        if (r1 == 0) goto L_0x0066;
    L_0x0064:
        r9 = r8;
        goto L_0x006f;
    L_0x0066:
        r1 = r12.activeMediaPeriods;
        r1 = r1.get(r4);
        r1 = (com.google.android.exoplayer2.source.DeferredMediaPeriod) r1;
        r9 = r1;
    L_0x006f:
        r1 = r11.window;
        r1 = r1.getDefaultPositionUs();
        if (r9 == 0) goto L_0x0083;
    L_0x0077:
        r3 = r9.getPreparePositionUs();
        r5 = 0;
        r10 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1));
        if (r10 == 0) goto L_0x0083;
    L_0x0081:
        r5 = r3;
        goto L_0x0084;
    L_0x0083:
        r5 = r1;
    L_0x0084:
        r2 = r11.window;
        r3 = r11.period;
        r4 = 0;
        r1 = r13;
        r1 = r1.getPeriodPosition(r2, r3, r4, r5);
        r2 = r1.first;
        r1 = r1.second;
        r1 = (java.lang.Long) r1;
        r3 = r1.longValue();
        r1 = com.google.android.exoplayer2.source.ConcatenatingMediaSource.DeferredTimeline.createWithRealTimeline(r13, r2);
        r12.timeline = r1;
        if (r9 == 0) goto L_0x00b4;
    L_0x00a0:
        r9.overridePreparePositionUs(r3);
        r1 = r9.id;
        r2 = r9.id;
        r2 = r2.periodUid;
        r2 = getChildPeriodUid(r12, r2);
        r1 = r1.copyWithPeriodUid(r2);
        r9.createPeriod(r1);
    L_0x00b4:
        r12.isPrepared = r7;
        r11.scheduleListenerNotification(r8);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.ConcatenatingMediaSource.updateMediaSourceInternal(com.google.android.exoplayer2.source.ConcatenatingMediaSource$MediaSourceHolder, com.google.android.exoplayer2.Timeline):void");
    }

    private void removeMediaSourceInternal(int i) {
        MediaSourceHolder mediaSourceHolder = (MediaSourceHolder) this.mediaSourceHolders.remove(i);
        this.mediaSourceByUid.remove(mediaSourceHolder.uid);
        DeferredTimeline deferredTimeline = mediaSourceHolder.timeline;
        correctOffsets(i, -1, -deferredTimeline.getWindowCount(), -deferredTimeline.getPeriodCount());
        mediaSourceHolder.isRemoved = true;
        maybeReleaseChildSource(mediaSourceHolder);
    }

    private void moveMediaSourceInternal(int i, int i2) {
        int min = Math.min(i, i2);
        int max = Math.max(i, i2);
        int i3 = ((MediaSourceHolder) this.mediaSourceHolders.get(min)).firstWindowIndexInChild;
        int i4 = ((MediaSourceHolder) this.mediaSourceHolders.get(min)).firstPeriodIndexInChild;
        this.mediaSourceHolders.add(i2, this.mediaSourceHolders.remove(i));
        while (min <= max) {
            MediaSourceHolder mediaSourceHolder = (MediaSourceHolder) this.mediaSourceHolders.get(min);
            mediaSourceHolder.firstWindowIndexInChild = i3;
            mediaSourceHolder.firstPeriodIndexInChild = i4;
            i3 += mediaSourceHolder.timeline.getWindowCount();
            i4 += mediaSourceHolder.timeline.getPeriodCount();
            min++;
        }
    }

    private void correctOffsets(int i, int i2, int i3, int i4) {
        this.windowCount += i3;
        this.periodCount += i4;
        while (i < this.mediaSourceHolders.size()) {
            MediaSourceHolder mediaSourceHolder = (MediaSourceHolder) this.mediaSourceHolders.get(i);
            mediaSourceHolder.childIndex += i2;
            mediaSourceHolder = (MediaSourceHolder) this.mediaSourceHolders.get(i);
            mediaSourceHolder.firstWindowIndexInChild += i3;
            mediaSourceHolder = (MediaSourceHolder) this.mediaSourceHolders.get(i);
            mediaSourceHolder.firstPeriodIndexInChild += i4;
            i++;
        }
    }

    private void maybeReleaseChildSource(MediaSourceHolder mediaSourceHolder) {
        if (mediaSourceHolder.isRemoved && mediaSourceHolder.hasStartedPreparing && mediaSourceHolder.activeMediaPeriods.isEmpty()) {
            releaseChildSource(mediaSourceHolder);
        }
    }

    private static Object getMediaSourceHolderUid(Object obj) {
        return AbstractConcatenatedTimeline.getChildTimelineUidFromConcatenatedUid(obj);
    }

    private static Object getChildPeriodUid(MediaSourceHolder mediaSourceHolder, Object obj) {
        obj = AbstractConcatenatedTimeline.getChildPeriodUidFromConcatenatedUid(obj);
        return obj.equals(DeferredTimeline.DUMMY_ID) ? mediaSourceHolder.timeline.replacedId : obj;
    }

    private static Object getPeriodUid(MediaSourceHolder mediaSourceHolder, Object obj) {
        if (mediaSourceHolder.timeline.replacedId.equals(obj)) {
            obj = DeferredTimeline.DUMMY_ID;
        }
        return AbstractConcatenatedTimeline.getConcatenatedUid(mediaSourceHolder.uid, obj);
    }
}
