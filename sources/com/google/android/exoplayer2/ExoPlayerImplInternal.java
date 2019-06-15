package com.google.android.exoplayer2;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Pair;
import com.google.android.exoplayer2.DefaultMediaClock.PlaybackParameterListener;
import com.google.android.exoplayer2.PlayerMessage.Sender;
import com.google.android.exoplayer2.Timeline.Period;
import com.google.android.exoplayer2.Timeline.Window;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSource.MediaPeriodId;
import com.google.android.exoplayer2.source.MediaSource.SourceInfoRefreshListener;
import com.google.android.exoplayer2.source.SampleStream;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector.InvalidationListener;
import com.google.android.exoplayer2.trackselection.TrackSelectorResult;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Clock;
import com.google.android.exoplayer2.util.HandlerWrapper;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.TraceUtil;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

final class ExoPlayerImplInternal implements Callback, PlaybackParameterListener, Sender, MediaPeriod.Callback, SourceInfoRefreshListener, InvalidationListener {
    private static final int IDLE_INTERVAL_MS = 1000;
    private static final int MSG_DO_SOME_WORK = 2;
    public static final int MSG_ERROR = 2;
    private static final int MSG_PERIOD_PREPARED = 9;
    public static final int MSG_PLAYBACK_INFO_CHANGED = 0;
    public static final int MSG_PLAYBACK_PARAMETERS_CHANGED = 1;
    private static final int MSG_PLAYBACK_PARAMETERS_CHANGED_INTERNAL = 16;
    private static final int MSG_PREPARE = 0;
    private static final int MSG_REFRESH_SOURCE_INFO = 8;
    private static final int MSG_RELEASE = 7;
    private static final int MSG_SEEK_TO = 3;
    private static final int MSG_SEND_MESSAGE = 14;
    private static final int MSG_SEND_MESSAGE_TO_TARGET_THREAD = 15;
    private static final int MSG_SET_PLAYBACK_PARAMETERS = 4;
    private static final int MSG_SET_PLAY_WHEN_READY = 1;
    private static final int MSG_SET_REPEAT_MODE = 12;
    private static final int MSG_SET_SEEK_PARAMETERS = 5;
    private static final int MSG_SET_SHUFFLE_ENABLED = 13;
    private static final int MSG_SOURCE_CONTINUE_LOADING_REQUESTED = 10;
    private static final int MSG_STOP = 6;
    private static final int MSG_TRACK_SELECTION_INVALIDATED = 11;
    private static final int PREPARING_SOURCE_INTERVAL_MS = 10;
    private static final int RENDERING_INTERVAL_MS = 10;
    private static final String TAG = "ExoPlayerImplInternal";
    private final long backBufferDurationUs;
    private final BandwidthMeter bandwidthMeter;
    private final Clock clock;
    private final TrackSelectorResult emptyTrackSelectorResult;
    private Renderer[] enabledRenderers;
    private final Handler eventHandler;
    private final HandlerWrapper handler;
    private final HandlerThread internalPlaybackThread;
    private final LoadControl loadControl;
    private final DefaultMediaClock mediaClock;
    private MediaSource mediaSource;
    private int nextPendingMessageIndex;
    private SeekPosition pendingInitialSeekPosition;
    private final ArrayList<PendingMessageInfo> pendingMessages;
    private int pendingPrepareCount;
    private final Period period;
    private boolean playWhenReady;
    private PlaybackInfo playbackInfo;
    private final PlaybackInfoUpdate playbackInfoUpdate;
    private final ExoPlayer player;
    private final MediaPeriodQueue queue = new MediaPeriodQueue();
    private boolean rebuffering;
    private boolean released;
    private final RendererCapabilities[] rendererCapabilities;
    private long rendererPositionUs;
    private final Renderer[] renderers;
    private int repeatMode;
    private final boolean retainBackBufferFromKeyframe;
    private SeekParameters seekParameters;
    private boolean shuffleModeEnabled;
    private final TrackSelector trackSelector;
    private final Window window;

    private static final class MediaSourceRefreshInfo {
        public final Object manifest;
        public final MediaSource source;
        public final Timeline timeline;

        public MediaSourceRefreshInfo(MediaSource mediaSource, Timeline timeline, Object obj) {
            this.source = mediaSource;
            this.timeline = timeline;
            this.manifest = obj;
        }
    }

    private static final class PendingMessageInfo implements Comparable<PendingMessageInfo> {
        public final PlayerMessage message;
        public int resolvedPeriodIndex;
        public long resolvedPeriodTimeUs;
        @Nullable
        public Object resolvedPeriodUid;

        public PendingMessageInfo(PlayerMessage playerMessage) {
            this.message = playerMessage;
        }

        public void setResolvedPosition(int i, long j, Object obj) {
            this.resolvedPeriodIndex = i;
            this.resolvedPeriodTimeUs = j;
            this.resolvedPeriodUid = obj;
        }

        public int compareTo(@NonNull PendingMessageInfo pendingMessageInfo) {
            int i = 1;
            if ((this.resolvedPeriodUid == null ? 1 : 0) != (pendingMessageInfo.resolvedPeriodUid == null ? 1 : 0)) {
                if (this.resolvedPeriodUid != null) {
                    i = -1;
                }
                return i;
            } else if (this.resolvedPeriodUid == null) {
                return 0;
            } else {
                int i2 = this.resolvedPeriodIndex - pendingMessageInfo.resolvedPeriodIndex;
                if (i2 != 0) {
                    return i2;
                }
                return Util.compareLong(this.resolvedPeriodTimeUs, pendingMessageInfo.resolvedPeriodTimeUs);
            }
        }
    }

    private static final class PlaybackInfoUpdate {
        private int discontinuityReason;
        private PlaybackInfo lastPlaybackInfo;
        private int operationAcks;
        private boolean positionDiscontinuity;

        private PlaybackInfoUpdate() {
        }

        public boolean hasPendingUpdate(PlaybackInfo playbackInfo) {
            return playbackInfo != this.lastPlaybackInfo || this.operationAcks > 0 || this.positionDiscontinuity;
        }

        public void reset(PlaybackInfo playbackInfo) {
            this.lastPlaybackInfo = playbackInfo;
            this.operationAcks = 0;
            this.positionDiscontinuity = false;
        }

        public void incrementPendingOperationAcks(int i) {
            this.operationAcks += i;
        }

        public void setPositionDiscontinuity(int i) {
            boolean z = true;
            if (!this.positionDiscontinuity || this.discontinuityReason == 4) {
                this.positionDiscontinuity = true;
                this.discontinuityReason = i;
                return;
            }
            if (i != 4) {
                z = false;
            }
            Assertions.checkArgument(z);
        }
    }

    private static final class SeekPosition {
        public final Timeline timeline;
        public final int windowIndex;
        public final long windowPositionUs;

        public SeekPosition(Timeline timeline, int i, long j) {
            this.timeline = timeline;
            this.windowIndex = i;
            this.windowPositionUs = j;
        }
    }

    public ExoPlayerImplInternal(Renderer[] rendererArr, TrackSelector trackSelector, TrackSelectorResult trackSelectorResult, LoadControl loadControl, BandwidthMeter bandwidthMeter, boolean z, int i, boolean z2, Handler handler, ExoPlayer exoPlayer, Clock clock) {
        this.renderers = rendererArr;
        this.trackSelector = trackSelector;
        this.emptyTrackSelectorResult = trackSelectorResult;
        this.loadControl = loadControl;
        this.bandwidthMeter = bandwidthMeter;
        this.playWhenReady = z;
        this.repeatMode = i;
        this.shuffleModeEnabled = z2;
        this.eventHandler = handler;
        this.player = exoPlayer;
        this.clock = clock;
        this.backBufferDurationUs = loadControl.getBackBufferDurationUs();
        this.retainBackBufferFromKeyframe = loadControl.retainBackBufferFromKeyframe();
        this.seekParameters = SeekParameters.DEFAULT;
        this.playbackInfo = PlaybackInfo.createDummy(C.TIME_UNSET, trackSelectorResult);
        this.playbackInfoUpdate = new PlaybackInfoUpdate();
        this.rendererCapabilities = new RendererCapabilities[rendererArr.length];
        for (int i2 = 0; i2 < rendererArr.length; i2++) {
            rendererArr[i2].setIndex(i2);
            this.rendererCapabilities[i2] = rendererArr[i2].getCapabilities();
        }
        this.mediaClock = new DefaultMediaClock(this, clock);
        this.pendingMessages = new ArrayList();
        this.enabledRenderers = new Renderer[0];
        this.window = new Window();
        this.period = new Period();
        trackSelector.init(this, bandwidthMeter);
        this.internalPlaybackThread = new HandlerThread("ExoPlayerImplInternal:Handler", -16);
        this.internalPlaybackThread.start();
        this.handler = clock.createHandler(this.internalPlaybackThread.getLooper(), this);
    }

    public void prepare(MediaSource mediaSource, boolean z, boolean z2) {
        this.handler.obtainMessage(0, z, z2, mediaSource).sendToTarget();
    }

    public void setPlayWhenReady(boolean z) {
        this.handler.obtainMessage(1, z, 0).sendToTarget();
    }

    public void setRepeatMode(int i) {
        this.handler.obtainMessage(12, i, 0).sendToTarget();
    }

    public void setShuffleModeEnabled(boolean z) {
        this.handler.obtainMessage(13, z, 0).sendToTarget();
    }

    public void seekTo(Timeline timeline, int i, long j) {
        this.handler.obtainMessage(3, new SeekPosition(timeline, i, j)).sendToTarget();
    }

    public void setPlaybackParameters(PlaybackParameters playbackParameters) {
        this.handler.obtainMessage(4, playbackParameters).sendToTarget();
    }

    public void setSeekParameters(SeekParameters seekParameters) {
        this.handler.obtainMessage(5, seekParameters).sendToTarget();
    }

    public void stop(boolean z) {
        this.handler.obtainMessage(6, z, 0).sendToTarget();
    }

    public synchronized void sendMessage(PlayerMessage playerMessage) {
        if (this.released) {
            Log.w(TAG, "Ignoring messages sent after release.");
            playerMessage.markAsProcessed(false);
            return;
        }
        this.handler.obtainMessage(14, playerMessage).sendToTarget();
    }

    /* JADX WARNING: Missing block: B:19:0x0022, code skipped:
            return;
     */
    public synchronized void release() {
        /*
        r2 = this;
        monitor-enter(r2);
        r0 = r2.released;	 Catch:{ all -> 0x0023 }
        if (r0 == 0) goto L_0x0007;
    L_0x0005:
        monitor-exit(r2);
        return;
    L_0x0007:
        r0 = r2.handler;	 Catch:{ all -> 0x0023 }
        r1 = 7;
        r0.sendEmptyMessage(r1);	 Catch:{ all -> 0x0023 }
        r0 = 0;
    L_0x000e:
        r1 = r2.released;	 Catch:{ all -> 0x0023 }
        if (r1 != 0) goto L_0x0018;
    L_0x0012:
        r2.wait();	 Catch:{ InterruptedException -> 0x0016 }
        goto L_0x000e;
    L_0x0016:
        r0 = 1;
        goto L_0x000e;
    L_0x0018:
        if (r0 == 0) goto L_0x0021;
    L_0x001a:
        r0 = java.lang.Thread.currentThread();	 Catch:{ all -> 0x0023 }
        r0.interrupt();	 Catch:{ all -> 0x0023 }
    L_0x0021:
        monitor-exit(r2);
        return;
    L_0x0023:
        r0 = move-exception;
        monitor-exit(r2);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ExoPlayerImplInternal.release():void");
    }

    public Looper getPlaybackLooper() {
        return this.internalPlaybackThread.getLooper();
    }

    public void onSourceInfoRefreshed(MediaSource mediaSource, Timeline timeline, Object obj) {
        this.handler.obtainMessage(8, new MediaSourceRefreshInfo(mediaSource, timeline, obj)).sendToTarget();
    }

    public void onPrepared(MediaPeriod mediaPeriod) {
        this.handler.obtainMessage(9, mediaPeriod).sendToTarget();
    }

    public void onContinueLoadingRequested(MediaPeriod mediaPeriod) {
        this.handler.obtainMessage(10, mediaPeriod).sendToTarget();
    }

    public void onTrackSelectionsInvalidated() {
        this.handler.sendEmptyMessage(11);
    }

    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
        this.handler.obtainMessage(16, playbackParameters).sendToTarget();
    }

    public boolean handleMessage(Message message) {
        try {
            switch (message.what) {
                case 0:
                    prepareInternal((MediaSource) message.obj, message.arg1 != 0, message.arg2 != 0);
                    break;
                case 1:
                    setPlayWhenReadyInternal(message.arg1 != 0);
                    break;
                case 2:
                    doSomeWork();
                    break;
                case 3:
                    seekToInternal((SeekPosition) message.obj);
                    break;
                case 4:
                    setPlaybackParametersInternal((PlaybackParameters) message.obj);
                    break;
                case 5:
                    setSeekParametersInternal((SeekParameters) message.obj);
                    break;
                case 6:
                    stopInternal(message.arg1 != 0, true);
                    break;
                case 7:
                    releaseInternal();
                    return true;
                case 8:
                    handleSourceInfoRefreshed((MediaSourceRefreshInfo) message.obj);
                    break;
                case 9:
                    handlePeriodPrepared((MediaPeriod) message.obj);
                    break;
                case 10:
                    handleContinueLoadingRequested((MediaPeriod) message.obj);
                    break;
                case 11:
                    reselectTracksInternal();
                    break;
                case 12:
                    setRepeatModeInternal(message.arg1);
                    break;
                case 13:
                    setShuffleModeEnabledInternal(message.arg1 != 0);
                    break;
                case 14:
                    sendMessageInternal((PlayerMessage) message.obj);
                    break;
                case 15:
                    sendMessageToTargetThread((PlayerMessage) message.obj);
                    break;
                case 16:
                    handlePlaybackParameters((PlaybackParameters) message.obj);
                    break;
                default:
                    return false;
            }
            maybeNotifyPlaybackInfoChanged();
        } catch (ExoPlaybackException e) {
            Log.e(TAG, "Playback error.", e);
            stopInternal(false, false);
            this.eventHandler.obtainMessage(2, e).sendToTarget();
            maybeNotifyPlaybackInfoChanged();
        } catch (IOException e2) {
            Log.e(TAG, "Source error.", e2);
            stopInternal(false, false);
            this.eventHandler.obtainMessage(2, ExoPlaybackException.createForSource(e2)).sendToTarget();
            maybeNotifyPlaybackInfoChanged();
        } catch (RuntimeException e3) {
            Log.e(TAG, "Internal runtime error.", e3);
            stopInternal(false, false);
            this.eventHandler.obtainMessage(2, ExoPlaybackException.createForUnexpected(e3)).sendToTarget();
            maybeNotifyPlaybackInfoChanged();
        }
        return true;
    }

    private void setState(int i) {
        if (this.playbackInfo.playbackState != i) {
            this.playbackInfo = this.playbackInfo.copyWithPlaybackState(i);
        }
    }

    private void setIsLoading(boolean z) {
        if (this.playbackInfo.isLoading != z) {
            this.playbackInfo = this.playbackInfo.copyWithIsLoading(z);
        }
    }

    private void maybeNotifyPlaybackInfoChanged() {
        if (this.playbackInfoUpdate.hasPendingUpdate(this.playbackInfo)) {
            this.eventHandler.obtainMessage(0, this.playbackInfoUpdate.operationAcks, this.playbackInfoUpdate.positionDiscontinuity ? this.playbackInfoUpdate.discontinuityReason : -1, this.playbackInfo).sendToTarget();
            this.playbackInfoUpdate.reset(this.playbackInfo);
        }
    }

    private void prepareInternal(MediaSource mediaSource, boolean z, boolean z2) {
        this.pendingPrepareCount++;
        resetInternal(true, z, z2);
        this.loadControl.onPrepared();
        this.mediaSource = mediaSource;
        setState(2);
        mediaSource.prepareSource(this.player, true, this, this.bandwidthMeter.getTransferListener());
        this.handler.sendEmptyMessage(2);
    }

    private void setPlayWhenReadyInternal(boolean z) throws ExoPlaybackException {
        this.rebuffering = false;
        this.playWhenReady = z;
        if (!z) {
            stopRenderers();
            updatePlaybackPositions();
        } else if (this.playbackInfo.playbackState == 3) {
            startRenderers();
            this.handler.sendEmptyMessage(2);
        } else if (this.playbackInfo.playbackState == 2) {
            this.handler.sendEmptyMessage(2);
        }
    }

    private void setRepeatModeInternal(int i) throws ExoPlaybackException {
        this.repeatMode = i;
        if (!this.queue.updateRepeatMode(i)) {
            seekToCurrentPosition(true);
        }
        handleLoadingMediaPeriodChanged(false);
    }

    private void setShuffleModeEnabledInternal(boolean z) throws ExoPlaybackException {
        this.shuffleModeEnabled = z;
        if (!this.queue.updateShuffleModeEnabled(z)) {
            seekToCurrentPosition(true);
        }
        handleLoadingMediaPeriodChanged(false);
    }

    private void seekToCurrentPosition(boolean z) throws ExoPlaybackException {
        MediaPeriodId mediaPeriodId = this.queue.getPlayingPeriod().info.id;
        long seekToPeriodPosition = seekToPeriodPosition(mediaPeriodId, this.playbackInfo.positionUs, true);
        if (seekToPeriodPosition != this.playbackInfo.positionUs) {
            this.playbackInfo = this.playbackInfo.copyWithNewPosition(mediaPeriodId, seekToPeriodPosition, this.playbackInfo.contentPositionUs, getTotalBufferedDurationUs());
            if (z) {
                this.playbackInfoUpdate.setPositionDiscontinuity(4);
            }
        }
    }

    private void startRenderers() throws ExoPlaybackException {
        int i = 0;
        this.rebuffering = false;
        this.mediaClock.start();
        Renderer[] rendererArr = this.enabledRenderers;
        int length = rendererArr.length;
        while (i < length) {
            rendererArr[i].start();
            i++;
        }
    }

    private void stopRenderers() throws ExoPlaybackException {
        this.mediaClock.stop();
        for (Renderer ensureStopped : this.enabledRenderers) {
            ensureStopped(ensureStopped);
        }
    }

    private void updatePlaybackPositions() throws ExoPlaybackException {
        if (this.queue.hasPlayingPeriod()) {
            MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
            long readDiscontinuity = playingPeriod.mediaPeriod.readDiscontinuity();
            if (readDiscontinuity != C.TIME_UNSET) {
                resetRendererPosition(readDiscontinuity);
                if (readDiscontinuity != this.playbackInfo.positionUs) {
                    this.playbackInfo = this.playbackInfo.copyWithNewPosition(this.playbackInfo.periodId, readDiscontinuity, this.playbackInfo.contentPositionUs, getTotalBufferedDurationUs());
                    this.playbackInfoUpdate.setPositionDiscontinuity(4);
                }
            } else {
                this.rendererPositionUs = this.mediaClock.syncAndGetPositionUs();
                long toPeriodTime = playingPeriod.toPeriodTime(this.rendererPositionUs);
                maybeTriggerPendingMessages(this.playbackInfo.positionUs, toPeriodTime);
                this.playbackInfo.positionUs = toPeriodTime;
            }
            playingPeriod = this.queue.getLoadingPeriod();
            this.playbackInfo.bufferedPositionUs = playingPeriod.getBufferedPositionUs();
            this.playbackInfo.totalBufferedDurationUs = getTotalBufferedDurationUs();
        }
    }

    private void doSomeWork() throws ExoPlaybackException, IOException {
        long uptimeMillis = this.clock.uptimeMillis();
        updatePeriods();
        if (this.queue.hasPlayingPeriod()) {
            MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
            TraceUtil.beginSection("doSomeWork");
            updatePlaybackPositions();
            long elapsedRealtime = SystemClock.elapsedRealtime() * 1000;
            playingPeriod.mediaPeriod.discardBuffer(this.playbackInfo.positionUs - this.backBufferDurationUs, this.retainBackBufferFromKeyframe);
            Renderer[] rendererArr = this.enabledRenderers;
            int length = rendererArr.length;
            int i = 0;
            Object obj = 1;
            boolean z = true;
            while (i < length) {
                Renderer renderer = rendererArr[i];
                int i2 = length;
                renderer.render(this.rendererPositionUs, elapsedRealtime);
                obj = (obj == null || !renderer.isEnded()) ? null : 1;
                Object obj2 = (renderer.isReady() || renderer.isEnded() || rendererWaitingForNextStream(renderer)) ? 1 : null;
                if (obj2 == null) {
                    renderer.maybeThrowStreamError();
                }
                z = z && obj2 != null;
                i++;
                length = i2;
            }
            if (!z) {
                maybeThrowPeriodPrepareError();
            }
            elapsedRealtime = playingPeriod.info.durationUs;
            if (obj != null && ((elapsedRealtime == C.TIME_UNSET || elapsedRealtime <= this.playbackInfo.positionUs) && playingPeriod.info.isFinal)) {
                setState(4);
                stopRenderers();
            } else if (this.playbackInfo.playbackState == 2 && shouldTransitionToReadyState(z)) {
                setState(3);
                if (this.playWhenReady) {
                    startRenderers();
                }
            } else if (this.playbackInfo.playbackState == 3 && (this.enabledRenderers.length != 0 ? z : isTimelineReady())) {
                this.rebuffering = this.playWhenReady;
                setState(2);
                stopRenderers();
            }
            if (this.playbackInfo.playbackState == 2) {
                for (Renderer maybeThrowStreamError : this.enabledRenderers) {
                    maybeThrowStreamError.maybeThrowStreamError();
                }
            }
            if ((this.playWhenReady && this.playbackInfo.playbackState == 3) || this.playbackInfo.playbackState == 2) {
                scheduleNextWork(uptimeMillis, 10);
            } else if (this.enabledRenderers.length == 0 || this.playbackInfo.playbackState == 4) {
                this.handler.removeMessages(2);
            } else {
                scheduleNextWork(uptimeMillis, 1000);
            }
            TraceUtil.endSection();
            return;
        }
        maybeThrowPeriodPrepareError();
        scheduleNextWork(uptimeMillis, 10);
    }

    private void scheduleNextWork(long j, long j2) {
        this.handler.removeMessages(2);
        this.handler.sendEmptyMessageAtTime(2, j + j2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:48:0x00e5  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00e5  */
    private void seekToInternal(com.google.android.exoplayer2.ExoPlayerImplInternal.SeekPosition r26) throws com.google.android.exoplayer2.ExoPlaybackException {
        /*
        r25 = this;
        r1 = r25;
        r2 = r26;
        r3 = r1.playbackInfoUpdate;
        r4 = 1;
        r3.incrementPendingOperationAcks(r4);
        r3 = r1.resolveSeekPosition(r2, r4);
        r7 = 0;
        r8 = -9223372036854775807; // 0x8000000000000001 float:1.4E-45 double:-4.9E-324;
        if (r3 != 0) goto L_0x0026;
    L_0x0016:
        r3 = r1.playbackInfo;
        r10 = r1.shuffleModeEnabled;
        r11 = r1.window;
        r3 = r3.getDummyFirstMediaPeriodId(r10, r11);
        r10 = r3;
        r3 = r4;
        r13 = r8;
        r19 = r13;
        goto L_0x0055;
    L_0x0026:
        r10 = r3.first;
        r11 = r3.second;
        r11 = (java.lang.Long) r11;
        r11 = r11.longValue();
        r13 = r1.queue;
        r10 = r13.resolveMediaPeriodIdForAds(r10, r11);
        r13 = r10.isAd();
        if (r13 == 0) goto L_0x0042;
    L_0x003c:
        r3 = r4;
        r19 = r11;
        r13 = 0;
        goto L_0x0055;
    L_0x0042:
        r3 = r3.second;
        r3 = (java.lang.Long) r3;
        r13 = r3.longValue();
        r5 = r2.windowPositionUs;
        r3 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1));
        if (r3 != 0) goto L_0x0052;
    L_0x0050:
        r3 = r4;
        goto L_0x0053;
    L_0x0052:
        r3 = r7;
    L_0x0053:
        r19 = r11;
    L_0x0055:
        r5 = 2;
        r6 = r1.mediaSource;	 Catch:{ all -> 0x00eb }
        if (r6 == 0) goto L_0x00d0;
    L_0x005a:
        r6 = r1.pendingPrepareCount;	 Catch:{ all -> 0x00eb }
        if (r6 <= 0) goto L_0x0060;
    L_0x005e:
        goto L_0x00d0;
    L_0x0060:
        r2 = (r13 > r8 ? 1 : (r13 == r8 ? 0 : -1));
        if (r2 != 0) goto L_0x006d;
    L_0x0064:
        r2 = 4;
        r1.setState(r2);	 Catch:{ all -> 0x00eb }
        r1.resetInternal(r7, r4, r7);	 Catch:{ all -> 0x00eb }
        goto L_0x00d2;
    L_0x006d:
        r2 = r1.playbackInfo;	 Catch:{ all -> 0x00eb }
        r2 = r2.periodId;	 Catch:{ all -> 0x00eb }
        r2 = r10.equals(r2);	 Catch:{ all -> 0x00eb }
        if (r2 == 0) goto L_0x00c1;
    L_0x0077:
        r2 = r1.queue;	 Catch:{ all -> 0x00eb }
        r2 = r2.getPlayingPeriod();	 Catch:{ all -> 0x00eb }
        if (r2 == 0) goto L_0x008e;
    L_0x007f:
        r8 = 0;
        r6 = (r13 > r8 ? 1 : (r13 == r8 ? 0 : -1));
        if (r6 == 0) goto L_0x008e;
    L_0x0085:
        r2 = r2.mediaPeriod;	 Catch:{ all -> 0x00eb }
        r6 = r1.seekParameters;	 Catch:{ all -> 0x00eb }
        r8 = r2.getAdjustedSeekPositionUs(r13, r6);	 Catch:{ all -> 0x00eb }
        goto L_0x008f;
    L_0x008e:
        r8 = r13;
    L_0x008f:
        r11 = com.google.android.exoplayer2.C.usToMs(r8);	 Catch:{ all -> 0x00eb }
        r2 = r1.playbackInfo;	 Catch:{ all -> 0x00eb }
        r23 = r8;
        r7 = r2.positionUs;	 Catch:{ all -> 0x00eb }
        r6 = com.google.android.exoplayer2.C.usToMs(r7);	 Catch:{ all -> 0x00eb }
        r2 = (r11 > r6 ? 1 : (r11 == r6 ? 0 : -1));
        if (r2 != 0) goto L_0x00be;
    L_0x00a1:
        r2 = r1.playbackInfo;	 Catch:{ all -> 0x00eb }
        r6 = r2.positionUs;	 Catch:{ all -> 0x00eb }
        r2 = r1.playbackInfo;
        r21 = r25.getTotalBufferedDurationUs();
        r15 = r2;
        r16 = r10;
        r17 = r6;
        r2 = r15.copyWithNewPosition(r16, r17, r19, r21);
        r1.playbackInfo = r2;
        if (r3 == 0) goto L_0x00bd;
    L_0x00b8:
        r2 = r1.playbackInfoUpdate;
        r2.setPositionDiscontinuity(r5);
    L_0x00bd:
        return;
    L_0x00be:
        r6 = r23;
        goto L_0x00c2;
    L_0x00c1:
        r6 = r13;
    L_0x00c2:
        r6 = r1.seekToPeriodPosition(r10, r6);	 Catch:{ all -> 0x00eb }
        r2 = (r13 > r6 ? 1 : (r13 == r6 ? 0 : -1));
        if (r2 == 0) goto L_0x00cb;
    L_0x00ca:
        goto L_0x00cc;
    L_0x00cb:
        r4 = 0;
    L_0x00cc:
        r3 = r3 | r4;
        r17 = r6;
        goto L_0x00d4;
    L_0x00d0:
        r1.pendingInitialSeekPosition = r2;	 Catch:{ all -> 0x00eb }
    L_0x00d2:
        r17 = r13;
    L_0x00d4:
        r2 = r1.playbackInfo;
        r21 = r25.getTotalBufferedDurationUs();
        r15 = r2;
        r16 = r10;
        r2 = r15.copyWithNewPosition(r16, r17, r19, r21);
        r1.playbackInfo = r2;
        if (r3 == 0) goto L_0x00ea;
    L_0x00e5:
        r2 = r1.playbackInfoUpdate;
        r2.setPositionDiscontinuity(r5);
    L_0x00ea:
        return;
    L_0x00eb:
        r0 = move-exception;
        r2 = r0;
        r4 = r1.playbackInfo;
        r21 = r25.getTotalBufferedDurationUs();
        r15 = r4;
        r16 = r10;
        r17 = r13;
        r4 = r15.copyWithNewPosition(r16, r17, r19, r21);
        r1.playbackInfo = r4;
        if (r3 == 0) goto L_0x0105;
    L_0x0100:
        r3 = r1.playbackInfoUpdate;
        r3.setPositionDiscontinuity(r5);
    L_0x0105:
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ExoPlayerImplInternal.seekToInternal(com.google.android.exoplayer2.ExoPlayerImplInternal$SeekPosition):void");
    }

    private long seekToPeriodPosition(MediaPeriodId mediaPeriodId, long j) throws ExoPlaybackException {
        return seekToPeriodPosition(mediaPeriodId, j, this.queue.getPlayingPeriod() != this.queue.getReadingPeriod());
    }

    private long seekToPeriodPosition(MediaPeriodId mediaPeriodId, long j, boolean z) throws ExoPlaybackException {
        stopRenderers();
        this.rebuffering = false;
        setState(2);
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        MediaPeriodHolder mediaPeriodHolder = playingPeriod;
        while (mediaPeriodHolder != null) {
            if (mediaPeriodId.equals(mediaPeriodHolder.info.id) && mediaPeriodHolder.prepared) {
                this.queue.removeAfter(mediaPeriodHolder);
                break;
            }
            mediaPeriodHolder = this.queue.advancePlayingPeriod();
        }
        if (playingPeriod != mediaPeriodHolder || z) {
            for (Renderer disableRenderer : this.enabledRenderers) {
                disableRenderer(disableRenderer);
            }
            this.enabledRenderers = new Renderer[0];
            playingPeriod = null;
        }
        if (mediaPeriodHolder != null) {
            updatePlayingPeriodRenderers(playingPeriod);
            if (mediaPeriodHolder.hasEnabledTracks) {
                j = mediaPeriodHolder.mediaPeriod.seekToUs(j);
                mediaPeriodHolder.mediaPeriod.discardBuffer(j - this.backBufferDurationUs, this.retainBackBufferFromKeyframe);
            }
            resetRendererPosition(j);
            maybeContinueLoading();
        } else {
            this.queue.clear(true);
            this.playbackInfo = this.playbackInfo.copyWithTrackInfo(TrackGroupArray.EMPTY, this.emptyTrackSelectorResult);
            resetRendererPosition(j);
        }
        handleLoadingMediaPeriodChanged(false);
        this.handler.sendEmptyMessage(2);
        return j;
    }

    private void resetRendererPosition(long j) throws ExoPlaybackException {
        if (this.queue.hasPlayingPeriod()) {
            j = this.queue.getPlayingPeriod().toRendererTime(j);
        }
        this.rendererPositionUs = j;
        this.mediaClock.resetPosition(this.rendererPositionUs);
        for (Renderer resetPosition : this.enabledRenderers) {
            resetPosition.resetPosition(this.rendererPositionUs);
        }
    }

    private void setPlaybackParametersInternal(PlaybackParameters playbackParameters) {
        this.mediaClock.setPlaybackParameters(playbackParameters);
    }

    private void setSeekParametersInternal(SeekParameters seekParameters) {
        this.seekParameters = seekParameters;
    }

    private void stopInternal(boolean z, boolean z2) {
        resetInternal(true, z, z);
        this.playbackInfoUpdate.incrementPendingOperationAcks(this.pendingPrepareCount + z2);
        this.pendingPrepareCount = 0;
        this.loadControl.onStopped();
        setState(1);
    }

    private void releaseInternal() {
        resetInternal(true, true, true);
        this.loadControl.onReleased();
        setState(1);
        this.internalPlaybackThread.quit();
        synchronized (this) {
            this.released = true;
            notifyAll();
        }
    }

    private void resetInternal(boolean z, boolean z2, boolean z3) {
        this.handler.removeMessages(2);
        this.rebuffering = false;
        this.mediaClock.stop();
        this.rendererPositionUs = 0;
        for (Renderer disableRenderer : this.enabledRenderers) {
            try {
                disableRenderer(disableRenderer);
            } catch (ExoPlaybackException | RuntimeException e) {
                Log.e(TAG, "Stop failed.", e);
            }
        }
        this.enabledRenderers = new Renderer[0];
        this.queue.clear(z2 ^ 1);
        setIsLoading(false);
        if (z2) {
            this.pendingInitialSeekPosition = null;
        }
        if (z3) {
            this.queue.setTimeline(Timeline.EMPTY);
            Iterator it = this.pendingMessages.iterator();
            while (it.hasNext()) {
                ((PendingMessageInfo) it.next()).message.markAsProcessed(false);
            }
            this.pendingMessages.clear();
            this.nextPendingMessageIndex = 0;
        }
        MediaPeriodId dummyFirstMediaPeriodId = z2 ? this.playbackInfo.getDummyFirstMediaPeriodId(this.shuffleModeEnabled, this.window) : this.playbackInfo.periodId;
        long j = C.TIME_UNSET;
        long j2 = z2 ? C.TIME_UNSET : this.playbackInfo.positionUs;
        if (!z2) {
            j = this.playbackInfo.contentPositionUs;
        }
        this.playbackInfo = new PlaybackInfo(z3 ? Timeline.EMPTY : this.playbackInfo.timeline, z3 ? null : this.playbackInfo.manifest, dummyFirstMediaPeriodId, j2, j, this.playbackInfo.playbackState, false, z3 ? TrackGroupArray.EMPTY : this.playbackInfo.trackGroups, z3 ? this.emptyTrackSelectorResult : this.playbackInfo.trackSelectorResult, dummyFirstMediaPeriodId, j2, 0, j2);
        if (z && this.mediaSource != null) {
            this.mediaSource.releaseSource(this);
            this.mediaSource = null;
        }
    }

    private void sendMessageInternal(PlayerMessage playerMessage) throws ExoPlaybackException {
        if (playerMessage.getPositionMs() == C.TIME_UNSET) {
            sendMessageToTarget(playerMessage);
        } else if (this.mediaSource == null || this.pendingPrepareCount > 0) {
            this.pendingMessages.add(new PendingMessageInfo(playerMessage));
        } else {
            PendingMessageInfo pendingMessageInfo = new PendingMessageInfo(playerMessage);
            if (resolvePendingMessagePosition(pendingMessageInfo)) {
                this.pendingMessages.add(pendingMessageInfo);
                Collections.sort(this.pendingMessages);
                return;
            }
            playerMessage.markAsProcessed(false);
        }
    }

    private void sendMessageToTarget(PlayerMessage playerMessage) throws ExoPlaybackException {
        if (playerMessage.getHandler().getLooper() == this.handler.getLooper()) {
            deliverMessage(playerMessage);
            if (this.playbackInfo.playbackState == 3 || this.playbackInfo.playbackState == 2) {
                this.handler.sendEmptyMessage(2);
                return;
            }
            return;
        }
        this.handler.obtainMessage(15, playerMessage).sendToTarget();
    }

    private void sendMessageToTargetThread(PlayerMessage playerMessage) {
        playerMessage.getHandler().post(new ExoPlayerImplInternal$$Lambda$0(this, playerMessage));
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void lambda$sendMessageToTargetThread$0$ExoPlayerImplInternal(PlayerMessage playerMessage) {
        try {
            deliverMessage(playerMessage);
        } catch (ExoPlaybackException e) {
            Log.e(TAG, "Unexpected error delivering message on external thread.", e);
            throw new RuntimeException(e);
        }
    }

    private void deliverMessage(PlayerMessage playerMessage) throws ExoPlaybackException {
        if (!playerMessage.isCanceled()) {
            try {
                playerMessage.getTarget().handleMessage(playerMessage.getType(), playerMessage.getPayload());
            } finally {
                playerMessage.markAsProcessed(true);
            }
        }
    }

    private void resolvePendingMessagePositions() {
        for (int size = this.pendingMessages.size() - 1; size >= 0; size--) {
            if (!resolvePendingMessagePosition((PendingMessageInfo) this.pendingMessages.get(size))) {
                ((PendingMessageInfo) this.pendingMessages.get(size)).message.markAsProcessed(false);
                this.pendingMessages.remove(size);
            }
        }
        Collections.sort(this.pendingMessages);
    }

    private boolean resolvePendingMessagePosition(PendingMessageInfo pendingMessageInfo) {
        if (pendingMessageInfo.resolvedPeriodUid == null) {
            Pair resolveSeekPosition = resolveSeekPosition(new SeekPosition(pendingMessageInfo.message.getTimeline(), pendingMessageInfo.message.getWindowIndex(), C.msToUs(pendingMessageInfo.message.getPositionMs())), false);
            if (resolveSeekPosition == null) {
                return false;
            }
            pendingMessageInfo.setResolvedPosition(this.playbackInfo.timeline.getIndexOfPeriod(resolveSeekPosition.first), ((Long) resolveSeekPosition.second).longValue(), resolveSeekPosition.first);
        } else {
            int indexOfPeriod = this.playbackInfo.timeline.getIndexOfPeriod(pendingMessageInfo.resolvedPeriodUid);
            if (indexOfPeriod == -1) {
                return false;
            }
            pendingMessageInfo.resolvedPeriodIndex = indexOfPeriod;
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:62:0x007e A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00fd  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00f2  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0041 A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0081  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00b0  */
    private void maybeTriggerPendingMessages(long r7, long r9) throws com.google.android.exoplayer2.ExoPlaybackException {
        /*
        r6 = this;
        r0 = r6.pendingMessages;
        r0 = r0.isEmpty();
        if (r0 != 0) goto L_0x0100;
    L_0x0008:
        r0 = r6.playbackInfo;
        r0 = r0.periodId;
        r0 = r0.isAd();
        if (r0 == 0) goto L_0x0014;
    L_0x0012:
        goto L_0x0100;
    L_0x0014:
        r0 = r6.playbackInfo;
        r0 = r0.startPositionUs;
        r2 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1));
        if (r2 != 0) goto L_0x0021;
    L_0x001c:
        r0 = 1;
        r2 = r7 - r0;
        r7 = r2;
    L_0x0021:
        r0 = r6.playbackInfo;
        r0 = r0.timeline;
        r1 = r6.playbackInfo;
        r1 = r1.periodId;
        r1 = r1.periodUid;
        r0 = r0.getIndexOfPeriod(r1);
        r1 = r6.nextPendingMessageIndex;
        r2 = 0;
        if (r1 <= 0) goto L_0x0041;
    L_0x0034:
        r1 = r6.pendingMessages;
        r3 = r6.nextPendingMessageIndex;
        r3 = r3 + -1;
        r1 = r1.get(r3);
        r1 = (com.google.android.exoplayer2.ExoPlayerImplInternal.PendingMessageInfo) r1;
        goto L_0x0042;
    L_0x0041:
        r1 = r2;
    L_0x0042:
        if (r1 == 0) goto L_0x0069;
    L_0x0044:
        r3 = r1.resolvedPeriodIndex;
        if (r3 > r0) goto L_0x0052;
    L_0x0048:
        r3 = r1.resolvedPeriodIndex;
        if (r3 != r0) goto L_0x0069;
    L_0x004c:
        r3 = r1.resolvedPeriodTimeUs;
        r1 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1));
        if (r1 <= 0) goto L_0x0069;
    L_0x0052:
        r1 = r6.nextPendingMessageIndex;
        r1 = r1 + -1;
        r6.nextPendingMessageIndex = r1;
        r1 = r6.nextPendingMessageIndex;
        if (r1 <= 0) goto L_0x0041;
    L_0x005c:
        r1 = r6.pendingMessages;
        r3 = r6.nextPendingMessageIndex;
        r3 = r3 + -1;
        r1 = r1.get(r3);
        r1 = (com.google.android.exoplayer2.ExoPlayerImplInternal.PendingMessageInfo) r1;
        goto L_0x0042;
    L_0x0069:
        r1 = r6.nextPendingMessageIndex;
        r3 = r6.pendingMessages;
        r3 = r3.size();
        if (r1 >= r3) goto L_0x007e;
    L_0x0073:
        r1 = r6.pendingMessages;
        r3 = r6.nextPendingMessageIndex;
        r1 = r1.get(r3);
        r1 = (com.google.android.exoplayer2.ExoPlayerImplInternal.PendingMessageInfo) r1;
        goto L_0x007f;
    L_0x007e:
        r1 = r2;
    L_0x007f:
        if (r1 == 0) goto L_0x00ae;
    L_0x0081:
        r3 = r1.resolvedPeriodUid;
        if (r3 == 0) goto L_0x00ae;
    L_0x0085:
        r3 = r1.resolvedPeriodIndex;
        if (r3 < r0) goto L_0x0093;
    L_0x0089:
        r3 = r1.resolvedPeriodIndex;
        if (r3 != r0) goto L_0x00ae;
    L_0x008d:
        r3 = r1.resolvedPeriodTimeUs;
        r5 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1));
        if (r5 > 0) goto L_0x00ae;
    L_0x0093:
        r1 = r6.nextPendingMessageIndex;
        r1 = r1 + 1;
        r6.nextPendingMessageIndex = r1;
        r1 = r6.nextPendingMessageIndex;
        r3 = r6.pendingMessages;
        r3 = r3.size();
        if (r1 >= r3) goto L_0x007e;
    L_0x00a3:
        r1 = r6.pendingMessages;
        r3 = r6.nextPendingMessageIndex;
        r1 = r1.get(r3);
        r1 = (com.google.android.exoplayer2.ExoPlayerImplInternal.PendingMessageInfo) r1;
        goto L_0x007f;
    L_0x00ae:
        if (r1 == 0) goto L_0x00ff;
    L_0x00b0:
        r3 = r1.resolvedPeriodUid;
        if (r3 == 0) goto L_0x00ff;
    L_0x00b4:
        r3 = r1.resolvedPeriodIndex;
        if (r3 != r0) goto L_0x00ff;
    L_0x00b8:
        r3 = r1.resolvedPeriodTimeUs;
        r5 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1));
        if (r5 <= 0) goto L_0x00ff;
    L_0x00be:
        r3 = r1.resolvedPeriodTimeUs;
        r5 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1));
        if (r5 > 0) goto L_0x00ff;
    L_0x00c4:
        r3 = r1.message;
        r6.sendMessageToTarget(r3);
        r3 = r1.message;
        r3 = r3.getDeleteAfterDelivery();
        if (r3 != 0) goto L_0x00e1;
    L_0x00d1:
        r1 = r1.message;
        r1 = r1.isCanceled();
        if (r1 == 0) goto L_0x00da;
    L_0x00d9:
        goto L_0x00e1;
    L_0x00da:
        r1 = r6.nextPendingMessageIndex;
        r1 = r1 + 1;
        r6.nextPendingMessageIndex = r1;
        goto L_0x00e8;
    L_0x00e1:
        r1 = r6.pendingMessages;
        r3 = r6.nextPendingMessageIndex;
        r1.remove(r3);
    L_0x00e8:
        r1 = r6.nextPendingMessageIndex;
        r3 = r6.pendingMessages;
        r3 = r3.size();
        if (r1 >= r3) goto L_0x00fd;
    L_0x00f2:
        r1 = r6.pendingMessages;
        r3 = r6.nextPendingMessageIndex;
        r1 = r1.get(r3);
        r1 = (com.google.android.exoplayer2.ExoPlayerImplInternal.PendingMessageInfo) r1;
        goto L_0x00ae;
    L_0x00fd:
        r1 = r2;
        goto L_0x00ae;
    L_0x00ff:
        return;
    L_0x0100:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ExoPlayerImplInternal.maybeTriggerPendingMessages(long, long):void");
    }

    private void ensureStopped(Renderer renderer) throws ExoPlaybackException {
        if (renderer.getState() == 2) {
            renderer.stop();
        }
    }

    private void disableRenderer(Renderer renderer) throws ExoPlaybackException {
        this.mediaClock.onRendererDisabled(renderer);
        ensureStopped(renderer);
        renderer.disable();
    }

    private void reselectTracksInternal() throws ExoPlaybackException {
        if (this.queue.hasPlayingPeriod()) {
            float f = this.mediaClock.getPlaybackParameters().speed;
            MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
            MediaPeriodHolder readingPeriod = this.queue.getReadingPeriod();
            boolean z = true;
            while (playingPeriod != null && playingPeriod.prepared) {
                if (playingPeriod.selectTracks(f)) {
                    if (z) {
                        playingPeriod = this.queue.getPlayingPeriod();
                        boolean[] zArr = new boolean[this.renderers.length];
                        long applyTrackSelection = playingPeriod.applyTrackSelection(this.playbackInfo.positionUs, this.queue.removeAfter(playingPeriod), zArr);
                        if (!(this.playbackInfo.playbackState == 4 || applyTrackSelection == this.playbackInfo.positionUs)) {
                            this.playbackInfo = this.playbackInfo.copyWithNewPosition(this.playbackInfo.periodId, applyTrackSelection, this.playbackInfo.contentPositionUs, getTotalBufferedDurationUs());
                            this.playbackInfoUpdate.setPositionDiscontinuity(4);
                            resetRendererPosition(applyTrackSelection);
                        }
                        boolean[] zArr2 = new boolean[this.renderers.length];
                        int i = 0;
                        int i2 = i;
                        while (i < this.renderers.length) {
                            Renderer renderer = this.renderers[i];
                            zArr2[i] = renderer.getState() != 0;
                            SampleStream sampleStream = playingPeriod.sampleStreams[i];
                            if (sampleStream != null) {
                                i2++;
                            }
                            if (zArr2[i]) {
                                if (sampleStream != renderer.getStream()) {
                                    disableRenderer(renderer);
                                } else if (zArr[i]) {
                                    renderer.resetPosition(this.rendererPositionUs);
                                }
                            }
                            i++;
                        }
                        this.playbackInfo = this.playbackInfo.copyWithTrackInfo(playingPeriod.trackGroups, playingPeriod.trackSelectorResult);
                        enableRenderers(zArr2, i2);
                    } else {
                        this.queue.removeAfter(playingPeriod);
                        if (playingPeriod.prepared) {
                            playingPeriod.applyTrackSelection(Math.max(playingPeriod.info.startPositionUs, playingPeriod.toPeriodTime(this.rendererPositionUs)), false);
                        }
                    }
                    handleLoadingMediaPeriodChanged(true);
                    if (this.playbackInfo.playbackState != 4) {
                        maybeContinueLoading();
                        updatePlaybackPositions();
                        this.handler.sendEmptyMessage(2);
                    }
                    return;
                }
                if (playingPeriod == readingPeriod) {
                    z = false;
                }
                playingPeriod = playingPeriod.next;
            }
        }
    }

    private void updateTrackSelectionPlaybackSpeed(float f) {
        for (MediaPeriodHolder frontPeriod = this.queue.getFrontPeriod(); frontPeriod != null; frontPeriod = frontPeriod.next) {
            if (frontPeriod.trackSelectorResult != null) {
                for (TrackSelection trackSelection : frontPeriod.trackSelectorResult.selections.getAll()) {
                    if (trackSelection != null) {
                        trackSelection.onPlaybackSpeed(f);
                    }
                }
            }
        }
    }

    private boolean shouldTransitionToReadyState(boolean z) {
        if (this.enabledRenderers.length == 0) {
            return isTimelineReady();
        }
        boolean z2 = false;
        if (!z) {
            return false;
        }
        if (!this.playbackInfo.isLoading) {
            return true;
        }
        MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
        z = loadingPeriod.isFullyBuffered() && loadingPeriod.info.isFinal;
        if (z || this.loadControl.shouldStartPlayback(getTotalBufferedDurationUs(), this.mediaClock.getPlaybackParameters().speed, this.rebuffering)) {
            z2 = true;
        }
        return z2;
    }

    private boolean isTimelineReady() {
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        long j = playingPeriod.info.durationUs;
        return j == C.TIME_UNSET || this.playbackInfo.positionUs < j || (playingPeriod.next != null && (playingPeriod.next.prepared || playingPeriod.next.info.id.isAd()));
    }

    private void maybeThrowSourceInfoRefreshError() throws IOException {
        if (this.queue.getLoadingPeriod() != null) {
            Renderer[] rendererArr = this.enabledRenderers;
            int length = rendererArr.length;
            int i = 0;
            while (i < length) {
                if (rendererArr[i].hasReadStreamToEnd()) {
                    i++;
                } else {
                    return;
                }
            }
        }
        this.mediaSource.maybeThrowSourceInfoRefreshError();
    }

    private void maybeThrowPeriodPrepareError() throws IOException {
        MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
        MediaPeriodHolder readingPeriod = this.queue.getReadingPeriod();
        if (!(loadingPeriod == null || loadingPeriod.prepared || (readingPeriod != null && readingPeriod.next != loadingPeriod))) {
            Renderer[] rendererArr = this.enabledRenderers;
            int length = rendererArr.length;
            int i = 0;
            while (i < length) {
                if (rendererArr[i].hasReadStreamToEnd()) {
                    i++;
                } else {
                    return;
                }
            }
            loadingPeriod.mediaPeriod.maybeThrowPrepareError();
        }
    }

    private void handleSourceInfoRefreshed(MediaSourceRefreshInfo mediaSourceRefreshInfo) throws ExoPlaybackException {
        MediaSourceRefreshInfo mediaSourceRefreshInfo2 = mediaSourceRefreshInfo;
        if (mediaSourceRefreshInfo2.source == this.mediaSource) {
            Timeline timeline = this.playbackInfo.timeline;
            Timeline timeline2 = mediaSourceRefreshInfo2.timeline;
            Object obj = mediaSourceRefreshInfo2.manifest;
            this.queue.setTimeline(timeline2);
            this.playbackInfo = this.playbackInfo.copyWithTimeline(timeline2, obj);
            resolvePendingMessagePositions();
            long j = 0;
            Pair resolveSeekPosition;
            Object obj2;
            long longValue;
            MediaPeriodId resolveMediaPeriodIdForAds;
            if (this.pendingPrepareCount > 0) {
                this.playbackInfoUpdate.incrementPendingOperationAcks(this.pendingPrepareCount);
                this.pendingPrepareCount = 0;
                if (this.pendingInitialSeekPosition != null) {
                    try {
                        resolveSeekPosition = resolveSeekPosition(this.pendingInitialSeekPosition, true);
                        this.pendingInitialSeekPosition = null;
                        if (resolveSeekPosition == null) {
                            handleSourceInfoRefreshEndedPlayback();
                        } else {
                            obj2 = resolveSeekPosition.first;
                            longValue = ((Long) resolveSeekPosition.second).longValue();
                            resolveMediaPeriodIdForAds = this.queue.resolveMediaPeriodIdForAds(obj2, longValue);
                            this.playbackInfo = this.playbackInfo.resetToNewPosition(resolveMediaPeriodIdForAds, resolveMediaPeriodIdForAds.isAd() ? 0 : longValue, longValue);
                        }
                    } catch (IllegalSeekPositionException e) {
                        IllegalSeekPositionException illegalSeekPositionException = e;
                        this.playbackInfo = this.playbackInfo.resetToNewPosition(this.playbackInfo.getDummyFirstMediaPeriodId(this.shuffleModeEnabled, this.window), C.TIME_UNSET, C.TIME_UNSET);
                        throw illegalSeekPositionException;
                    }
                } else if (this.playbackInfo.startPositionUs == C.TIME_UNSET) {
                    if (timeline2.isEmpty()) {
                        handleSourceInfoRefreshEndedPlayback();
                    } else {
                        resolveSeekPosition = getPeriodPosition(timeline2, timeline2.getFirstWindowIndex(this.shuffleModeEnabled), C.TIME_UNSET);
                        obj2 = resolveSeekPosition.first;
                        longValue = ((Long) resolveSeekPosition.second).longValue();
                        resolveMediaPeriodIdForAds = this.queue.resolveMediaPeriodIdForAds(obj2, longValue);
                        this.playbackInfo = this.playbackInfo.resetToNewPosition(resolveMediaPeriodIdForAds, resolveMediaPeriodIdForAds.isAd() ? 0 : longValue, longValue);
                    }
                }
            } else if (timeline.isEmpty()) {
                if (!timeline2.isEmpty()) {
                    resolveSeekPosition = getPeriodPosition(timeline2, timeline2.getFirstWindowIndex(this.shuffleModeEnabled), C.TIME_UNSET);
                    obj2 = resolveSeekPosition.first;
                    longValue = ((Long) resolveSeekPosition.second).longValue();
                    resolveMediaPeriodIdForAds = this.queue.resolveMediaPeriodIdForAds(obj2, longValue);
                    this.playbackInfo = this.playbackInfo.resetToNewPosition(resolveMediaPeriodIdForAds, resolveMediaPeriodIdForAds.isAd() ? 0 : longValue, longValue);
                }
            } else {
                MediaPeriodHolder frontPeriod = this.queue.getFrontPeriod();
                long j2 = this.playbackInfo.contentPositionUs;
                Object obj3 = frontPeriod == null ? this.playbackInfo.periodId.periodUid : frontPeriod.uid;
                if (timeline2.getIndexOfPeriod(obj3) == -1) {
                    obj2 = resolveSubsequentPeriod(obj3, timeline, timeline2);
                    if (obj2 == null) {
                        handleSourceInfoRefreshEndedPlayback();
                        return;
                    }
                    Pair periodPosition = getPeriodPosition(timeline2, timeline2.getPeriodByUid(obj2, this.period).windowIndex, C.TIME_UNSET);
                    Object obj4 = periodPosition.first;
                    longValue = ((Long) periodPosition.second).longValue();
                    resolveMediaPeriodIdForAds = this.queue.resolveMediaPeriodIdForAds(obj4, longValue);
                    if (frontPeriod != null) {
                        while (frontPeriod.next != null) {
                            frontPeriod = frontPeriod.next;
                            if (frontPeriod.info.id.equals(resolveMediaPeriodIdForAds)) {
                                frontPeriod.info = this.queue.getUpdatedMediaPeriodInfo(frontPeriod.info);
                            }
                        }
                    }
                    if (!resolveMediaPeriodIdForAds.isAd()) {
                        j = longValue;
                    }
                    this.playbackInfo = this.playbackInfo.copyWithNewPosition(resolveMediaPeriodIdForAds, seekToPeriodPosition(resolveMediaPeriodIdForAds, j), longValue, getTotalBufferedDurationUs());
                    return;
                }
                MediaPeriodId mediaPeriodId = this.playbackInfo.periodId;
                if (mediaPeriodId.isAd()) {
                    MediaPeriodId resolveMediaPeriodIdForAds2 = this.queue.resolveMediaPeriodIdForAds(obj3, j2);
                    if (!resolveMediaPeriodIdForAds2.equals(mediaPeriodId)) {
                        if (!resolveMediaPeriodIdForAds2.isAd()) {
                            j = j2;
                        }
                        this.playbackInfo = this.playbackInfo.copyWithNewPosition(resolveMediaPeriodIdForAds2, seekToPeriodPosition(resolveMediaPeriodIdForAds2, j), j2, getTotalBufferedDurationUs());
                        return;
                    }
                }
                if (!this.queue.updateQueuedPeriods(mediaPeriodId, this.rendererPositionUs)) {
                    seekToCurrentPosition(false);
                }
                handleLoadingMediaPeriodChanged(false);
            }
        }
    }

    private void handleSourceInfoRefreshEndedPlayback() {
        setState(4);
        resetInternal(false, true, false);
    }

    @Nullable
    private Object resolveSubsequentPeriod(Object obj, Timeline timeline, Timeline timeline2) {
        int indexOfPeriod = timeline.getIndexOfPeriod(obj);
        int periodCount = timeline.getPeriodCount();
        int i = indexOfPeriod;
        indexOfPeriod = -1;
        for (int i2 = 0; i2 < periodCount && indexOfPeriod == -1; i2++) {
            i = timeline.getNextPeriodIndex(i, this.period, this.window, this.repeatMode, this.shuffleModeEnabled);
            if (i == -1) {
                break;
            }
            indexOfPeriod = timeline2.getIndexOfPeriod(timeline.getUidOfPeriod(i));
        }
        if (indexOfPeriod == -1) {
            return null;
        }
        return timeline2.getUidOfPeriod(indexOfPeriod);
    }

    private Pair<Object, Long> resolveSeekPosition(SeekPosition seekPosition, boolean z) {
        Timeline timeline = this.playbackInfo.timeline;
        Timeline timeline2 = seekPosition.timeline;
        if (timeline.isEmpty()) {
            return null;
        }
        if (timeline2.isEmpty()) {
            timeline2 = timeline;
        }
        try {
            Pair periodPosition = timeline2.getPeriodPosition(this.window, this.period, seekPosition.windowIndex, seekPosition.windowPositionUs);
            if (timeline == timeline2) {
                return periodPosition;
            }
            int indexOfPeriod = timeline.getIndexOfPeriod(periodPosition.first);
            if (indexOfPeriod != -1) {
                return periodPosition;
            }
            if (!z || resolveSubsequentPeriod(periodPosition.first, timeline2, timeline) == null) {
                return null;
            }
            return getPeriodPosition(timeline, timeline.getPeriod(indexOfPeriod, this.period).windowIndex, C.TIME_UNSET);
        } catch (IndexOutOfBoundsException unused) {
            throw new IllegalSeekPositionException(timeline, seekPosition.windowIndex, seekPosition.windowPositionUs);
        }
    }

    private Pair<Object, Long> getPeriodPosition(Timeline timeline, int i, long j) {
        return timeline.getPeriodPosition(this.window, this.period, i, j);
    }

    private void updatePeriods() throws ExoPlaybackException, IOException {
        if (this.mediaSource != null) {
            if (this.pendingPrepareCount > 0) {
                this.mediaSource.maybeThrowSourceInfoRefreshError();
                return;
            }
            maybeUpdateLoadingPeriod();
            MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
            int i = 0;
            if (loadingPeriod == null || loadingPeriod.isFullyBuffered()) {
                setIsLoading(false);
            } else if (!this.playbackInfo.isLoading) {
                maybeContinueLoading();
            }
            if (this.queue.hasPlayingPeriod()) {
                loadingPeriod = this.queue.getPlayingPeriod();
                MediaPeriodHolder readingPeriod = this.queue.getReadingPeriod();
                int i2 = 0;
                while (this.playWhenReady && loadingPeriod != readingPeriod && this.rendererPositionUs >= loadingPeriod.next.getStartPositionRendererTime()) {
                    if (i2 != 0) {
                        maybeNotifyPlaybackInfoChanged();
                    }
                    i2 = loadingPeriod.info.isLastInTimelinePeriod ? 0 : 3;
                    MediaPeriodHolder advancePlayingPeriod = this.queue.advancePlayingPeriod();
                    updatePlayingPeriodRenderers(loadingPeriod);
                    this.playbackInfo = this.playbackInfo.copyWithNewPosition(advancePlayingPeriod.info.id, advancePlayingPeriod.info.startPositionUs, advancePlayingPeriod.info.contentPositionUs, getTotalBufferedDurationUs());
                    this.playbackInfoUpdate.setPositionDiscontinuity(i2);
                    updatePlaybackPositions();
                    i2 = 1;
                    loadingPeriod = advancePlayingPeriod;
                }
                if (readingPeriod.info.isFinal) {
                    while (i < this.renderers.length) {
                        Renderer renderer = this.renderers[i];
                        SampleStream sampleStream = readingPeriod.sampleStreams[i];
                        if (sampleStream != null && renderer.getStream() == sampleStream && renderer.hasReadStreamToEnd()) {
                            renderer.setCurrentStreamFinal();
                        }
                        i++;
                    }
                } else if (readingPeriod.next != null) {
                    int i3 = 0;
                    while (i3 < this.renderers.length) {
                        Renderer renderer2 = this.renderers[i3];
                        SampleStream sampleStream2 = readingPeriod.sampleStreams[i3];
                        if (renderer2.getStream() == sampleStream2 && (sampleStream2 == null || renderer2.hasReadStreamToEnd())) {
                            i3++;
                        } else {
                            return;
                        }
                    }
                    if (readingPeriod.next.prepared) {
                        TrackSelectorResult trackSelectorResult = readingPeriod.trackSelectorResult;
                        readingPeriod = this.queue.advanceReadingPeriod();
                        TrackSelectorResult trackSelectorResult2 = readingPeriod.trackSelectorResult;
                        int i4 = readingPeriod.mediaPeriod.readDiscontinuity() != C.TIME_UNSET ? 1 : 0;
                        for (int i5 = 0; i5 < this.renderers.length; i5++) {
                            Renderer renderer3 = this.renderers[i5];
                            if (trackSelectorResult.isRendererEnabled(i5)) {
                                if (i4 != 0) {
                                    renderer3.setCurrentStreamFinal();
                                } else if (!renderer3.isCurrentStreamFinal()) {
                                    TrackSelection trackSelection = trackSelectorResult2.selections.get(i5);
                                    boolean isRendererEnabled = trackSelectorResult2.isRendererEnabled(i5);
                                    int i6 = this.rendererCapabilities[i5].getTrackType() == 6 ? 1 : 0;
                                    Object obj = trackSelectorResult.rendererConfigurations[i5];
                                    RendererConfiguration rendererConfiguration = trackSelectorResult2.rendererConfigurations[i5];
                                    if (isRendererEnabled && rendererConfiguration.equals(obj) && i6 == 0) {
                                        renderer3.replaceStream(getFormats(trackSelection), readingPeriod.sampleStreams[i5], readingPeriod.getRendererOffset());
                                    } else {
                                        renderer3.setCurrentStreamFinal();
                                    }
                                }
                            }
                        }
                        return;
                    }
                    maybeThrowPeriodPrepareError();
                }
            }
        }
    }

    private void maybeUpdateLoadingPeriod() throws IOException {
        this.queue.reevaluateBuffer(this.rendererPositionUs);
        if (this.queue.shouldLoadNextMediaPeriod()) {
            MediaPeriodInfo nextMediaPeriodInfo = this.queue.getNextMediaPeriodInfo(this.rendererPositionUs, this.playbackInfo);
            if (nextMediaPeriodInfo == null) {
                maybeThrowSourceInfoRefreshError();
                return;
            }
            this.queue.enqueueNextMediaPeriod(this.rendererCapabilities, this.trackSelector, this.loadControl.getAllocator(), this.mediaSource, nextMediaPeriodInfo).prepare(this, nextMediaPeriodInfo.startPositionUs);
            setIsLoading(true);
            handleLoadingMediaPeriodChanged(false);
        }
    }

    private void handlePeriodPrepared(MediaPeriod mediaPeriod) throws ExoPlaybackException {
        if (this.queue.isLoading(mediaPeriod)) {
            MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
            loadingPeriod.handlePrepared(this.mediaClock.getPlaybackParameters().speed);
            updateLoadControlTrackSelection(loadingPeriod.trackGroups, loadingPeriod.trackSelectorResult);
            if (!this.queue.hasPlayingPeriod()) {
                resetRendererPosition(this.queue.advancePlayingPeriod().info.startPositionUs);
                updatePlayingPeriodRenderers(null);
            }
            maybeContinueLoading();
        }
    }

    private void handleContinueLoadingRequested(MediaPeriod mediaPeriod) {
        if (this.queue.isLoading(mediaPeriod)) {
            this.queue.reevaluateBuffer(this.rendererPositionUs);
            maybeContinueLoading();
        }
    }

    private void handlePlaybackParameters(PlaybackParameters playbackParameters) throws ExoPlaybackException {
        this.eventHandler.obtainMessage(1, playbackParameters).sendToTarget();
        updateTrackSelectionPlaybackSpeed(playbackParameters.speed);
        for (Renderer renderer : this.renderers) {
            if (renderer != null) {
                renderer.setOperatingRate(playbackParameters.speed);
            }
        }
    }

    private void maybeContinueLoading() {
        MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
        long nextLoadPositionUs = loadingPeriod.getNextLoadPositionUs();
        if (nextLoadPositionUs == Long.MIN_VALUE) {
            setIsLoading(false);
            return;
        }
        boolean shouldContinueLoading = this.loadControl.shouldContinueLoading(getTotalBufferedDurationUs(nextLoadPositionUs), this.mediaClock.getPlaybackParameters().speed);
        setIsLoading(shouldContinueLoading);
        if (shouldContinueLoading) {
            loadingPeriod.continueLoading(this.rendererPositionUs);
        }
    }

    private void updatePlayingPeriodRenderers(@Nullable MediaPeriodHolder mediaPeriodHolder) throws ExoPlaybackException {
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        if (playingPeriod != null && mediaPeriodHolder != playingPeriod) {
            boolean[] zArr = new boolean[this.renderers.length];
            int i = 0;
            int i2 = i;
            while (i < this.renderers.length) {
                Renderer renderer = this.renderers[i];
                zArr[i] = renderer.getState() != 0;
                if (playingPeriod.trackSelectorResult.isRendererEnabled(i)) {
                    i2++;
                }
                if (zArr[i] && (!playingPeriod.trackSelectorResult.isRendererEnabled(i) || (renderer.isCurrentStreamFinal() && renderer.getStream() == mediaPeriodHolder.sampleStreams[i]))) {
                    disableRenderer(renderer);
                }
                i++;
            }
            this.playbackInfo = this.playbackInfo.copyWithTrackInfo(playingPeriod.trackGroups, playingPeriod.trackSelectorResult);
            enableRenderers(zArr, i2);
        }
    }

    private void enableRenderers(boolean[] zArr, int i) throws ExoPlaybackException {
        this.enabledRenderers = new Renderer[i];
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        int i2 = 0;
        int i3 = 0;
        while (i2 < this.renderers.length) {
            if (playingPeriod.trackSelectorResult.isRendererEnabled(i2)) {
                int i4 = i3 + 1;
                enableRenderer(i2, zArr[i2], i3);
                i3 = i4;
            }
            i2++;
        }
    }

    private void enableRenderer(int i, boolean z, int i2) throws ExoPlaybackException {
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        Renderer renderer = this.renderers[i];
        this.enabledRenderers[i2] = renderer;
        if (renderer.getState() == 0) {
            RendererConfiguration rendererConfiguration = playingPeriod.trackSelectorResult.rendererConfigurations[i];
            Format[] formats = getFormats(playingPeriod.trackSelectorResult.selections.get(i));
            Object obj = (this.playWhenReady && this.playbackInfo.playbackState == 3) ? 1 : null;
            boolean z2 = (z || obj == null) ? false : true;
            renderer.enable(rendererConfiguration, formats, playingPeriod.sampleStreams[i], this.rendererPositionUs, z2, playingPeriod.getRendererOffset());
            this.mediaClock.onRendererEnabled(renderer);
            if (obj != null) {
                renderer.start();
            }
        }
    }

    private boolean rendererWaitingForNextStream(Renderer renderer) {
        MediaPeriodHolder readingPeriod = this.queue.getReadingPeriod();
        return readingPeriod.next != null && readingPeriod.next.prepared && renderer.hasReadStreamToEnd();
    }

    private void handleLoadingMediaPeriodChanged(boolean z) {
        long j;
        MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
        Object obj = loadingPeriod == null ? this.playbackInfo.periodId : loadingPeriod.info.id;
        int equals = this.playbackInfo.loadingMediaPeriodId.equals(obj) ^ 1;
        if (equals != 0) {
            this.playbackInfo = this.playbackInfo.copyWithLoadingMediaPeriodId(obj);
        }
        PlaybackInfo playbackInfo = this.playbackInfo;
        if (loadingPeriod == null) {
            j = this.playbackInfo.positionUs;
        } else {
            j = loadingPeriod.getBufferedPositionUs();
        }
        playbackInfo.bufferedPositionUs = j;
        this.playbackInfo.totalBufferedDurationUs = getTotalBufferedDurationUs();
        if ((equals != 0 || z) && loadingPeriod != null && loadingPeriod.prepared) {
            updateLoadControlTrackSelection(loadingPeriod.trackGroups, loadingPeriod.trackSelectorResult);
        }
    }

    private long getTotalBufferedDurationUs() {
        return getTotalBufferedDurationUs(this.playbackInfo.bufferedPositionUs);
    }

    private long getTotalBufferedDurationUs(long j) {
        MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
        if (loadingPeriod == null) {
            return 0;
        }
        return j - loadingPeriod.toPeriodTime(this.rendererPositionUs);
    }

    private void updateLoadControlTrackSelection(TrackGroupArray trackGroupArray, TrackSelectorResult trackSelectorResult) {
        this.loadControl.onTracksSelected(this.renderers, trackGroupArray, trackSelectorResult.selections);
    }

    private static Format[] getFormats(TrackSelection trackSelection) {
        int i = 0;
        int length = trackSelection != null ? trackSelection.length() : 0;
        Format[] formatArr = new Format[length];
        while (i < length) {
            formatArr[i] = trackSelection.getFormat(i);
            i++;
        }
        return formatArr;
    }
}
