package com.google.android.exoplayer2.source;

import android.os.Handler;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaSource.MediaPeriodId;
import com.google.android.exoplayer2.source.MediaSource.SourceInfoRefreshListener;
import com.google.android.exoplayer2.source.MediaSourceEventListener.EventDispatcher;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class BaseMediaSource implements MediaSource {
    private final EventDispatcher eventDispatcher = new EventDispatcher();
    @Nullable
    private Object manifest;
    @Nullable
    private ExoPlayer player;
    private final ArrayList<SourceInfoRefreshListener> sourceInfoListeners = new ArrayList(1);
    @Nullable
    private Timeline timeline;

    public Object getTag() {
        return MediaSource$$CC.getTag(this);
    }

    public abstract void prepareSourceInternal(ExoPlayer exoPlayer, boolean z, @Nullable TransferListener transferListener);

    public abstract void releaseSourceInternal();

    /* Access modifiers changed, original: protected|final */
    public final void refreshSourceInfo(Timeline timeline, @Nullable Object obj) {
        this.timeline = timeline;
        this.manifest = obj;
        Iterator it = this.sourceInfoListeners.iterator();
        while (it.hasNext()) {
            ((SourceInfoRefreshListener) it.next()).onSourceInfoRefreshed(this, timeline, obj);
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final EventDispatcher createEventDispatcher(@Nullable MediaPeriodId mediaPeriodId) {
        return this.eventDispatcher.withParameters(0, mediaPeriodId, 0);
    }

    /* Access modifiers changed, original: protected|final */
    public final EventDispatcher createEventDispatcher(MediaPeriodId mediaPeriodId, long j) {
        Assertions.checkArgument(mediaPeriodId != null);
        return this.eventDispatcher.withParameters(0, mediaPeriodId, j);
    }

    /* Access modifiers changed, original: protected|final */
    public final EventDispatcher createEventDispatcher(int i, @Nullable MediaPeriodId mediaPeriodId, long j) {
        return this.eventDispatcher.withParameters(i, mediaPeriodId, j);
    }

    public final void addEventListener(Handler handler, MediaSourceEventListener mediaSourceEventListener) {
        this.eventDispatcher.addEventListener(handler, mediaSourceEventListener);
    }

    public final void removeEventListener(MediaSourceEventListener mediaSourceEventListener) {
        this.eventDispatcher.removeEventListener(mediaSourceEventListener);
    }

    public final void prepareSource(ExoPlayer exoPlayer, boolean z, SourceInfoRefreshListener sourceInfoRefreshListener) {
        prepareSource(exoPlayer, z, sourceInfoRefreshListener, null);
    }

    public final void prepareSource(ExoPlayer exoPlayer, boolean z, SourceInfoRefreshListener sourceInfoRefreshListener, @Nullable TransferListener transferListener) {
        boolean z2 = this.player == null || this.player == exoPlayer;
        Assertions.checkArgument(z2);
        this.sourceInfoListeners.add(sourceInfoRefreshListener);
        if (this.player == null) {
            this.player = exoPlayer;
            prepareSourceInternal(exoPlayer, z, transferListener);
        } else if (this.timeline != null) {
            sourceInfoRefreshListener.onSourceInfoRefreshed(this, this.timeline, this.manifest);
        }
    }

    public final void releaseSource(SourceInfoRefreshListener sourceInfoRefreshListener) {
        this.sourceInfoListeners.remove(sourceInfoRefreshListener);
        if (this.sourceInfoListeners.isEmpty()) {
            this.player = null;
            this.timeline = null;
            this.manifest = null;
            releaseSourceInternal();
        }
    }
}
