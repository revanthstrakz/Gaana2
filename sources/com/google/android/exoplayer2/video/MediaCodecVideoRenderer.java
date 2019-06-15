package com.google.android.exoplayer2.video;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.media.MediaCodec;
import android.media.MediaCodec.OnFrameRenderedListener;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Surface;
import com.comscore.streaming.Constants;
import com.google.android.exoplayer2.BaseRenderer;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.drm.FrameworkMediaCrypto;
import com.google.android.exoplayer2.mediacodec.MediaCodecInfo;
import com.google.android.exoplayer2.mediacodec.MediaCodecRenderer;
import com.google.android.exoplayer2.mediacodec.MediaCodecSelector;
import com.google.android.exoplayer2.mediacodec.MediaCodecUtil;
import com.google.android.exoplayer2.mediacodec.MediaCodecUtil.DecoderQueryException;
import com.google.android.exoplayer2.mediacodec.MediaFormatUtil;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.TraceUtil;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoRendererEventListener.EventDispatcher;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import java.nio.ByteBuffer;
import java.util.List;

@TargetApi(16)
public class MediaCodecVideoRenderer extends MediaCodecRenderer {
    private static final float INITIAL_FORMAT_MAX_INPUT_SIZE_SCALE_FACTOR = 1.5f;
    private static final String KEY_CROP_BOTTOM = "crop-bottom";
    private static final String KEY_CROP_LEFT = "crop-left";
    private static final String KEY_CROP_RIGHT = "crop-right";
    private static final String KEY_CROP_TOP = "crop-top";
    private static final int MAX_PENDING_OUTPUT_STREAM_OFFSET_COUNT = 10;
    private static final int[] STANDARD_LONG_EDGE_VIDEO_PX = new int[]{1920, 1600, 1440, 1280, 960, 854, 640, 540, 480};
    private static final String TAG = "MediaCodecVideoRenderer";
    private static boolean deviceNeedsSetOutputSurfaceWorkaround;
    private static boolean evaluatedDeviceNeedsSetOutputSurfaceWorkaround;
    private final long allowedJoiningTimeMs;
    private int buffersInCodecCount;
    private CodecMaxValues codecMaxValues;
    private boolean codecNeedsSetOutputSurfaceWorkaround;
    private int consecutiveDroppedFrameCount;
    private final Context context;
    private int currentHeight;
    private float currentPixelWidthHeightRatio;
    private int currentUnappliedRotationDegrees;
    private int currentWidth;
    private final boolean deviceNeedsNoPostProcessWorkaround;
    private long droppedFrameAccumulationStartTimeMs;
    private int droppedFrames;
    private Surface dummySurface;
    private final EventDispatcher eventDispatcher;
    @Nullable
    private VideoFrameMetadataListener frameMetadataListener;
    private final VideoFrameReleaseTimeHelper frameReleaseTimeHelper;
    private long initialPositionUs;
    private long joiningDeadlineMs;
    private long lastInputTimeUs;
    private long lastRenderTimeUs;
    private final int maxDroppedFramesToNotify;
    private long outputStreamOffsetUs;
    private int pendingOutputStreamOffsetCount;
    private final long[] pendingOutputStreamOffsetsUs;
    private final long[] pendingOutputStreamSwitchTimesUs;
    private float pendingPixelWidthHeightRatio;
    private int pendingRotationDegrees;
    private boolean renderedFirstFrame;
    private int reportedHeight;
    private float reportedPixelWidthHeightRatio;
    private int reportedUnappliedRotationDegrees;
    private int reportedWidth;
    private int scalingMode;
    private Surface surface;
    private boolean tunneling;
    private int tunnelingAudioSessionId;
    OnFrameRenderedListenerV23 tunnelingOnFrameRenderedListener;

    protected static final class CodecMaxValues {
        public final int height;
        public final int inputSize;
        public final int width;

        public CodecMaxValues(int i, int i2, int i3) {
            this.width = i;
            this.height = i2;
            this.inputSize = i3;
        }
    }

    @TargetApi(23)
    private final class OnFrameRenderedListenerV23 implements OnFrameRenderedListener {
        private OnFrameRenderedListenerV23(MediaCodec mediaCodec) {
            mediaCodec.setOnFrameRenderedListener(this, new Handler());
        }

        public void onFrameRendered(@NonNull MediaCodec mediaCodec, long j, long j2) {
            if (this == MediaCodecVideoRenderer.this.tunnelingOnFrameRenderedListener) {
                MediaCodecVideoRenderer.this.onProcessedTunneledBuffer(j);
            }
        }
    }

    private static boolean isBufferLate(long j) {
        return j < -30000;
    }

    private static boolean isBufferVeryLate(long j) {
        return j < -500000;
    }

    public MediaCodecVideoRenderer(Context context, MediaCodecSelector mediaCodecSelector) {
        this(context, mediaCodecSelector, 0);
    }

    public MediaCodecVideoRenderer(Context context, MediaCodecSelector mediaCodecSelector, long j) {
        this(context, mediaCodecSelector, j, null, null, -1);
    }

    public MediaCodecVideoRenderer(Context context, MediaCodecSelector mediaCodecSelector, long j, @Nullable Handler handler, @Nullable VideoRendererEventListener videoRendererEventListener, int i) {
        this(context, mediaCodecSelector, j, null, false, handler, videoRendererEventListener, i);
    }

    public MediaCodecVideoRenderer(Context context, MediaCodecSelector mediaCodecSelector, long j, @Nullable DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, boolean z, @Nullable Handler handler, @Nullable VideoRendererEventListener videoRendererEventListener, int i) {
        super(2, mediaCodecSelector, drmSessionManager, z, 30.0f);
        this.allowedJoiningTimeMs = j;
        this.maxDroppedFramesToNotify = i;
        this.context = context.getApplicationContext();
        this.frameReleaseTimeHelper = new VideoFrameReleaseTimeHelper(this.context);
        this.eventDispatcher = new EventDispatcher(handler, videoRendererEventListener);
        this.deviceNeedsNoPostProcessWorkaround = deviceNeedsNoPostProcessWorkaround();
        this.pendingOutputStreamOffsetsUs = new long[10];
        this.pendingOutputStreamSwitchTimesUs = new long[10];
        this.outputStreamOffsetUs = C.TIME_UNSET;
        this.lastInputTimeUs = C.TIME_UNSET;
        this.joiningDeadlineMs = C.TIME_UNSET;
        this.currentWidth = -1;
        this.currentHeight = -1;
        this.currentPixelWidthHeightRatio = -1.0f;
        this.pendingPixelWidthHeightRatio = -1.0f;
        this.scalingMode = 1;
        clearReportedVideoSize();
    }

    /* Access modifiers changed, original: protected */
    public int supportsFormat(MediaCodecSelector mediaCodecSelector, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, Format format) throws DecoderQueryException {
        int i = 0;
        if (!MimeTypes.isVideo(format.sampleMimeType)) {
            return 0;
        }
        boolean z;
        DrmInitData drmInitData = format.drmInitData;
        if (drmInitData != null) {
            int i2 = 0;
            z = i2;
            while (i2 < drmInitData.schemeDataCount) {
                z |= drmInitData.get(i2).requiresSecureDecryption;
                i2++;
            }
        } else {
            z = false;
        }
        List decoderInfos = mediaCodecSelector.getDecoderInfos(format.sampleMimeType, z);
        int i3 = 2;
        if (decoderInfos.isEmpty()) {
            if (!z || mediaCodecSelector.getDecoderInfos(format.sampleMimeType, false).isEmpty()) {
                i3 = 1;
            }
            return i3;
        } else if (!BaseRenderer.supportsFormatDrm(drmSessionManager, drmInitData)) {
            return 2;
        } else {
            MediaCodecInfo mediaCodecInfo = (MediaCodecInfo) decoderInfos.get(0);
            boolean isFormatSupported = mediaCodecInfo.isFormatSupported(format);
            int i4 = mediaCodecInfo.isSeamlessAdaptationSupported(format) ? 16 : 8;
            if (mediaCodecInfo.tunneling) {
                i = 32;
            }
            return (isFormatSupported ? 4 : 3) | (i4 | i);
        }
    }

    /* Access modifiers changed, original: protected */
    public void onEnabled(boolean z) throws ExoPlaybackException {
        super.onEnabled(z);
        this.tunnelingAudioSessionId = getConfiguration().tunnelingAudioSessionId;
        this.tunneling = this.tunnelingAudioSessionId != 0;
        this.eventDispatcher.enabled(this.decoderCounters);
        this.frameReleaseTimeHelper.enable();
    }

    /* Access modifiers changed, original: protected */
    public void onStreamChanged(Format[] formatArr, long j) throws ExoPlaybackException {
        if (this.outputStreamOffsetUs == C.TIME_UNSET) {
            this.outputStreamOffsetUs = j;
        } else {
            if (this.pendingOutputStreamOffsetCount == this.pendingOutputStreamOffsetsUs.length) {
                String str = TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Too many stream changes, so dropping offset: ");
                stringBuilder.append(this.pendingOutputStreamOffsetsUs[this.pendingOutputStreamOffsetCount - 1]);
                Log.w(str, stringBuilder.toString());
            } else {
                this.pendingOutputStreamOffsetCount++;
            }
            this.pendingOutputStreamOffsetsUs[this.pendingOutputStreamOffsetCount - 1] = j;
            this.pendingOutputStreamSwitchTimesUs[this.pendingOutputStreamOffsetCount - 1] = this.lastInputTimeUs;
        }
        super.onStreamChanged(formatArr, j);
    }

    /* Access modifiers changed, original: protected */
    public void onPositionReset(long j, boolean z) throws ExoPlaybackException {
        super.onPositionReset(j, z);
        clearRenderedFirstFrame();
        this.initialPositionUs = C.TIME_UNSET;
        this.consecutiveDroppedFrameCount = 0;
        this.lastInputTimeUs = C.TIME_UNSET;
        if (this.pendingOutputStreamOffsetCount != 0) {
            this.outputStreamOffsetUs = this.pendingOutputStreamOffsetsUs[this.pendingOutputStreamOffsetCount - 1];
            this.pendingOutputStreamOffsetCount = 0;
        }
        if (z) {
            setJoiningDeadlineMs();
        } else {
            this.joiningDeadlineMs = C.TIME_UNSET;
        }
    }

    public boolean isReady() {
        if (super.isReady() && (this.renderedFirstFrame || ((this.dummySurface != null && this.surface == this.dummySurface) || getCodec() == null || this.tunneling))) {
            this.joiningDeadlineMs = C.TIME_UNSET;
            return true;
        } else if (this.joiningDeadlineMs == C.TIME_UNSET) {
            return false;
        } else {
            if (SystemClock.elapsedRealtime() < this.joiningDeadlineMs) {
                return true;
            }
            this.joiningDeadlineMs = C.TIME_UNSET;
            return false;
        }
    }

    /* Access modifiers changed, original: protected */
    public void onStarted() {
        super.onStarted();
        this.droppedFrames = 0;
        this.droppedFrameAccumulationStartTimeMs = SystemClock.elapsedRealtime();
        this.lastRenderTimeUs = SystemClock.elapsedRealtime() * 1000;
    }

    /* Access modifiers changed, original: protected */
    public void onStopped() {
        this.joiningDeadlineMs = C.TIME_UNSET;
        maybeNotifyDroppedFrames();
        super.onStopped();
    }

    /* Access modifiers changed, original: protected */
    public void onDisabled() {
        this.currentWidth = -1;
        this.currentHeight = -1;
        this.currentPixelWidthHeightRatio = -1.0f;
        this.pendingPixelWidthHeightRatio = -1.0f;
        this.outputStreamOffsetUs = C.TIME_UNSET;
        this.lastInputTimeUs = C.TIME_UNSET;
        this.pendingOutputStreamOffsetCount = 0;
        clearReportedVideoSize();
        clearRenderedFirstFrame();
        this.frameReleaseTimeHelper.disable();
        this.tunnelingOnFrameRenderedListener = null;
        this.tunneling = false;
        try {
            super.onDisabled();
        } finally {
            this.decoderCounters.ensureUpdated();
            this.eventDispatcher.disabled(this.decoderCounters);
        }
    }

    public void handleMessage(int i, @Nullable Object obj) throws ExoPlaybackException {
        if (i == 1) {
            setSurface((Surface) obj);
        } else if (i == 4) {
            this.scalingMode = ((Integer) obj).intValue();
            MediaCodec codec = getCodec();
            if (codec != null) {
                codec.setVideoScalingMode(this.scalingMode);
            }
        } else if (i == 6) {
            this.frameMetadataListener = (VideoFrameMetadataListener) obj;
        } else {
            super.handleMessage(i, obj);
        }
    }

    private void setSurface(Surface surface) throws ExoPlaybackException {
        if (surface == null) {
            if (this.dummySurface != null) {
                surface = this.dummySurface;
            } else {
                MediaCodecInfo codecInfo = getCodecInfo();
                if (codecInfo != null && shouldUseDummySurface(codecInfo)) {
                    this.dummySurface = DummySurface.newInstanceV17(this.context, codecInfo.secure);
                    surface = this.dummySurface;
                }
            }
        }
        if (this.surface != surface) {
            this.surface = surface;
            int state = getState();
            if (state == 1 || state == 2) {
                MediaCodec codec = getCodec();
                if (Util.SDK_INT < 23 || codec == null || surface == null || this.codecNeedsSetOutputSurfaceWorkaround) {
                    releaseCodec();
                    maybeInitCodec();
                } else {
                    setOutputSurfaceV23(codec, surface);
                }
            }
            if (surface == null || surface == this.dummySurface) {
                clearReportedVideoSize();
                clearRenderedFirstFrame();
                return;
            }
            maybeRenotifyVideoSizeChanged();
            clearRenderedFirstFrame();
            if (state == 2) {
                setJoiningDeadlineMs();
            }
        } else if (surface != null && surface != this.dummySurface) {
            maybeRenotifyVideoSizeChanged();
            maybeRenotifyRenderedFirstFrame();
        }
    }

    /* Access modifiers changed, original: protected */
    public boolean shouldInitCodec(MediaCodecInfo mediaCodecInfo) {
        return this.surface != null || shouldUseDummySurface(mediaCodecInfo);
    }

    /* Access modifiers changed, original: protected */
    public boolean getCodecNeedsEosPropagation() {
        return this.tunneling;
    }

    /* Access modifiers changed, original: protected */
    public void configureCodec(MediaCodecInfo mediaCodecInfo, MediaCodec mediaCodec, Format format, MediaCrypto mediaCrypto, float f) throws DecoderQueryException {
        this.codecMaxValues = getCodecMaxValues(mediaCodecInfo, format, getStreamFormats());
        MediaFormat mediaFormat = getMediaFormat(format, this.codecMaxValues, f, this.deviceNeedsNoPostProcessWorkaround, this.tunnelingAudioSessionId);
        if (this.surface == null) {
            Assertions.checkState(shouldUseDummySurface(mediaCodecInfo));
            if (this.dummySurface == null) {
                this.dummySurface = DummySurface.newInstanceV17(this.context, mediaCodecInfo.secure);
            }
            this.surface = this.dummySurface;
        }
        mediaCodec.configure(mediaFormat, this.surface, mediaCrypto, 0);
        if (Util.SDK_INT >= 23 && this.tunneling) {
            this.tunnelingOnFrameRenderedListener = new OnFrameRenderedListenerV23(mediaCodec);
        }
    }

    /* Access modifiers changed, original: protected */
    public int canKeepCodec(MediaCodec mediaCodec, MediaCodecInfo mediaCodecInfo, Format format, Format format2) {
        int i = 1;
        if (!mediaCodecInfo.isSeamlessAdaptationSupported(format, format2, true) || format2.width > this.codecMaxValues.width || format2.height > this.codecMaxValues.height || getMaxInputSize(mediaCodecInfo, format2) > this.codecMaxValues.inputSize) {
            return 0;
        }
        if (!format.initializationDataEquals(format2)) {
            i = 3;
        }
        return i;
    }

    /* Access modifiers changed, original: protected */
    @CallSuper
    public void releaseCodec() {
        try {
            super.releaseCodec();
        } finally {
            this.buffersInCodecCount = 0;
            if (this.dummySurface != null) {
                if (this.surface == this.dummySurface) {
                    this.surface = null;
                }
                this.dummySurface.release();
                this.dummySurface = null;
            }
        }
    }

    /* Access modifiers changed, original: protected */
    @CallSuper
    public void flushCodec() throws ExoPlaybackException {
        super.flushCodec();
        this.buffersInCodecCount = 0;
    }

    /* Access modifiers changed, original: protected */
    public float getCodecOperatingRate(float f, Format format, Format[] formatArr) {
        float f2 = -1.0f;
        for (Format format2 : formatArr) {
            float f3 = format2.frameRate;
            if (f3 != -1.0f) {
                f2 = Math.max(f2, f3);
            }
        }
        if (f2 == -1.0f) {
            return -1.0f;
        }
        return f2 * f;
    }

    /* Access modifiers changed, original: protected */
    public void onCodecInitialized(String str, long j, long j2) {
        this.eventDispatcher.decoderInitialized(str, j, j2);
        this.codecNeedsSetOutputSurfaceWorkaround = codecNeedsSetOutputSurfaceWorkaround(str);
    }

    /* Access modifiers changed, original: protected */
    public void onInputFormatChanged(Format format) throws ExoPlaybackException {
        super.onInputFormatChanged(format);
        this.eventDispatcher.inputFormatChanged(format);
        this.pendingPixelWidthHeightRatio = format.pixelWidthHeightRatio;
        this.pendingRotationDegrees = format.rotationDegrees;
    }

    /* Access modifiers changed, original: protected */
    @CallSuper
    public void onQueueInputBuffer(DecoderInputBuffer decoderInputBuffer) {
        this.buffersInCodecCount++;
        this.lastInputTimeUs = Math.max(decoderInputBuffer.timeUs, this.lastInputTimeUs);
        if (Util.SDK_INT < 23 && this.tunneling) {
            onProcessedTunneledBuffer(decoderInputBuffer.timeUs);
        }
    }

    /* Access modifiers changed, original: protected */
    public void onOutputFormatChanged(MediaCodec mediaCodec, MediaFormat mediaFormat) {
        int integer;
        int i = (mediaFormat.containsKey(KEY_CROP_RIGHT) && mediaFormat.containsKey(KEY_CROP_LEFT) && mediaFormat.containsKey(KEY_CROP_BOTTOM) && mediaFormat.containsKey(KEY_CROP_TOP)) ? 1 : 0;
        if (i != 0) {
            integer = (mediaFormat.getInteger(KEY_CROP_RIGHT) - mediaFormat.getInteger(KEY_CROP_LEFT)) + 1;
        } else {
            integer = mediaFormat.getInteger("width");
        }
        if (i != 0) {
            i = (mediaFormat.getInteger(KEY_CROP_BOTTOM) - mediaFormat.getInteger(KEY_CROP_TOP)) + 1;
        } else {
            i = mediaFormat.getInteger("height");
        }
        processOutputFormat(mediaCodec, integer, i);
    }

    /* Access modifiers changed, original: protected */
    public boolean processOutputBuffer(long j, long j2, MediaCodec mediaCodec, ByteBuffer byteBuffer, int i, int i2, long j3, boolean z, Format format) throws ExoPlaybackException {
        long j4 = j;
        long j5 = j2;
        MediaCodec mediaCodec2 = mediaCodec;
        int i3 = i;
        long j6 = j3;
        if (this.initialPositionUs == C.TIME_UNSET) {
            this.initialPositionUs = j4;
        }
        long j7 = j6 - this.outputStreamOffsetUs;
        if (z) {
            skipOutputBuffer(mediaCodec2, i3, j7);
            return true;
        }
        long j8 = j6 - j4;
        if (this.surface != this.dummySurface) {
            long elapsedRealtime = SystemClock.elapsedRealtime() * 1000;
            boolean z2 = getState() == 2;
            if (this.renderedFirstFrame) {
                long j9;
                if (z2) {
                    j9 = j7;
                    if (shouldForceRenderOutputBuffer(j8, elapsedRealtime - this.lastRenderTimeUs)) {
                        j5 = j9;
                    }
                } else {
                    j9 = j7;
                }
                if (!z2 || j4 == this.initialPositionUs) {
                    return false;
                }
                long j10 = j8 - (elapsedRealtime - j5);
                j8 = System.nanoTime();
                j7 = this.frameReleaseTimeHelper.adjustReleaseTime(j6, j8 + (j10 * 1000));
                j10 = (j7 - j8) / 1000;
                if (shouldDropBuffersToKeyframe(j10, j5) && maybeDropBuffersToKeyframe(mediaCodec2, i3, j9, j4)) {
                    return false;
                }
                if (shouldDropOutputBuffer(j10, j5)) {
                    dropOutputBuffer(mediaCodec2, i3, j9);
                    return true;
                }
                j5 = j9;
                if (Util.SDK_INT >= 21) {
                    if (j10 < 50000) {
                        notifyFrameMetadataListener(j5, j7, format);
                        renderOutputBufferV21(mediaCodec2, i3, j5, j7);
                        return true;
                    }
                } else if (j10 < 30000) {
                    if (j10 > 11000) {
                        try {
                            Thread.sleep((j10 - Constants.HEARTBEAT_STAGE_ONE_INTERVAL) / 1000);
                        } catch (InterruptedException unused) {
                            Thread.currentThread().interrupt();
                            return false;
                        }
                    }
                    notifyFrameMetadataListener(j5, j7, format);
                    renderOutputBuffer(mediaCodec2, i3, j5);
                    return true;
                }
                return false;
            }
            j5 = j7;
            j7 = System.nanoTime();
            notifyFrameMetadataListener(j5, j7, format);
            if (Util.SDK_INT >= 21) {
                renderOutputBufferV21(mediaCodec2, i3, j5, j7);
            } else {
                renderOutputBuffer(mediaCodec2, i3, j5);
            }
            return true;
        } else if (!isBufferLate(j8)) {
            return false;
        } else {
            skipOutputBuffer(mediaCodec2, i3, j7);
            return true;
        }
    }

    private void processOutputFormat(MediaCodec mediaCodec, int i, int i2) {
        this.currentWidth = i;
        this.currentHeight = i2;
        this.currentPixelWidthHeightRatio = this.pendingPixelWidthHeightRatio;
        if (Util.SDK_INT < 21) {
            this.currentUnappliedRotationDegrees = this.pendingRotationDegrees;
        } else if (this.pendingRotationDegrees == 90 || this.pendingRotationDegrees == 270) {
            i = this.currentWidth;
            this.currentWidth = this.currentHeight;
            this.currentHeight = i;
            this.currentPixelWidthHeightRatio = 1.0f / this.currentPixelWidthHeightRatio;
        }
        mediaCodec.setVideoScalingMode(this.scalingMode);
    }

    private void notifyFrameMetadataListener(long j, long j2, Format format) {
        if (this.frameMetadataListener != null) {
            this.frameMetadataListener.onVideoFrameAboutToBeRendered(j, j2, format);
        }
    }

    /* Access modifiers changed, original: protected */
    public long getOutputStreamOffsetUs() {
        return this.outputStreamOffsetUs;
    }

    /* Access modifiers changed, original: protected */
    public void onProcessedTunneledBuffer(long j) {
        Format updateOutputFormatForTime = updateOutputFormatForTime(j);
        if (updateOutputFormatForTime != null) {
            processOutputFormat(getCodec(), updateOutputFormatForTime.width, updateOutputFormatForTime.height);
        }
        maybeNotifyVideoSizeChanged();
        maybeNotifyRenderedFirstFrame();
        onProcessedOutputBuffer(j);
    }

    /* Access modifiers changed, original: protected */
    @CallSuper
    public void onProcessedOutputBuffer(long j) {
        this.buffersInCodecCount--;
        while (this.pendingOutputStreamOffsetCount != 0 && j >= this.pendingOutputStreamSwitchTimesUs[0]) {
            this.outputStreamOffsetUs = this.pendingOutputStreamOffsetsUs[0];
            this.pendingOutputStreamOffsetCount--;
            System.arraycopy(this.pendingOutputStreamOffsetsUs, 1, this.pendingOutputStreamOffsetsUs, 0, this.pendingOutputStreamOffsetCount);
            System.arraycopy(this.pendingOutputStreamSwitchTimesUs, 1, this.pendingOutputStreamSwitchTimesUs, 0, this.pendingOutputStreamOffsetCount);
        }
    }

    /* Access modifiers changed, original: protected */
    public boolean shouldDropOutputBuffer(long j, long j2) {
        return isBufferLate(j);
    }

    /* Access modifiers changed, original: protected */
    public boolean shouldDropBuffersToKeyframe(long j, long j2) {
        return isBufferVeryLate(j);
    }

    /* Access modifiers changed, original: protected */
    public boolean shouldForceRenderOutputBuffer(long j, long j2) {
        return isBufferLate(j) && j2 > 100000;
    }

    /* Access modifiers changed, original: protected */
    public void skipOutputBuffer(MediaCodec mediaCodec, int i, long j) {
        TraceUtil.beginSection("skipVideoBuffer");
        mediaCodec.releaseOutputBuffer(i, false);
        TraceUtil.endSection();
        DecoderCounters decoderCounters = this.decoderCounters;
        decoderCounters.skippedOutputBufferCount++;
    }

    /* Access modifiers changed, original: protected */
    public void dropOutputBuffer(MediaCodec mediaCodec, int i, long j) {
        TraceUtil.beginSection("dropVideoBuffer");
        mediaCodec.releaseOutputBuffer(i, false);
        TraceUtil.endSection();
        updateDroppedBufferCounters(1);
    }

    /* Access modifiers changed, original: protected */
    public boolean maybeDropBuffersToKeyframe(MediaCodec mediaCodec, int i, long j, long j2) throws ExoPlaybackException {
        int skipSource = skipSource(j2);
        if (skipSource == 0) {
            return false;
        }
        DecoderCounters decoderCounters = this.decoderCounters;
        decoderCounters.droppedToKeyframeCount++;
        updateDroppedBufferCounters(this.buffersInCodecCount + skipSource);
        flushCodec();
        return true;
    }

    /* Access modifiers changed, original: protected */
    public void updateDroppedBufferCounters(int i) {
        DecoderCounters decoderCounters = this.decoderCounters;
        decoderCounters.droppedBufferCount += i;
        this.droppedFrames += i;
        this.consecutiveDroppedFrameCount += i;
        this.decoderCounters.maxConsecutiveDroppedBufferCount = Math.max(this.consecutiveDroppedFrameCount, this.decoderCounters.maxConsecutiveDroppedBufferCount);
        if (this.maxDroppedFramesToNotify > 0 && this.droppedFrames >= this.maxDroppedFramesToNotify) {
            maybeNotifyDroppedFrames();
        }
    }

    /* Access modifiers changed, original: protected */
    public void renderOutputBuffer(MediaCodec mediaCodec, int i, long j) {
        maybeNotifyVideoSizeChanged();
        TraceUtil.beginSection("releaseOutputBuffer");
        mediaCodec.releaseOutputBuffer(i, true);
        TraceUtil.endSection();
        this.lastRenderTimeUs = SystemClock.elapsedRealtime() * 1000;
        DecoderCounters decoderCounters = this.decoderCounters;
        decoderCounters.renderedOutputBufferCount++;
        this.consecutiveDroppedFrameCount = 0;
        maybeNotifyRenderedFirstFrame();
    }

    /* Access modifiers changed, original: protected */
    @TargetApi(21)
    public void renderOutputBufferV21(MediaCodec mediaCodec, int i, long j, long j2) {
        maybeNotifyVideoSizeChanged();
        TraceUtil.beginSection("releaseOutputBuffer");
        mediaCodec.releaseOutputBuffer(i, j2);
        TraceUtil.endSection();
        this.lastRenderTimeUs = SystemClock.elapsedRealtime() * 1000;
        DecoderCounters decoderCounters = this.decoderCounters;
        decoderCounters.renderedOutputBufferCount++;
        this.consecutiveDroppedFrameCount = 0;
        maybeNotifyRenderedFirstFrame();
    }

    private boolean shouldUseDummySurface(MediaCodecInfo mediaCodecInfo) {
        return Util.SDK_INT >= 23 && !this.tunneling && !codecNeedsSetOutputSurfaceWorkaround(mediaCodecInfo.name) && (!mediaCodecInfo.secure || DummySurface.isSecureSupported(this.context));
    }

    private void setJoiningDeadlineMs() {
        this.joiningDeadlineMs = this.allowedJoiningTimeMs > 0 ? SystemClock.elapsedRealtime() + this.allowedJoiningTimeMs : C.TIME_UNSET;
    }

    private void clearRenderedFirstFrame() {
        this.renderedFirstFrame = false;
        if (Util.SDK_INT >= 23 && this.tunneling) {
            MediaCodec codec = getCodec();
            if (codec != null) {
                this.tunnelingOnFrameRenderedListener = new OnFrameRenderedListenerV23(codec);
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void maybeNotifyRenderedFirstFrame() {
        if (!this.renderedFirstFrame) {
            this.renderedFirstFrame = true;
            this.eventDispatcher.renderedFirstFrame(this.surface);
        }
    }

    private void maybeRenotifyRenderedFirstFrame() {
        if (this.renderedFirstFrame) {
            this.eventDispatcher.renderedFirstFrame(this.surface);
        }
    }

    private void clearReportedVideoSize() {
        this.reportedWidth = -1;
        this.reportedHeight = -1;
        this.reportedPixelWidthHeightRatio = -1.0f;
        this.reportedUnappliedRotationDegrees = -1;
    }

    private void maybeNotifyVideoSizeChanged() {
        if (this.currentWidth != -1 || this.currentHeight != -1) {
            if (this.reportedWidth != this.currentWidth || this.reportedHeight != this.currentHeight || this.reportedUnappliedRotationDegrees != this.currentUnappliedRotationDegrees || this.reportedPixelWidthHeightRatio != this.currentPixelWidthHeightRatio) {
                this.eventDispatcher.videoSizeChanged(this.currentWidth, this.currentHeight, this.currentUnappliedRotationDegrees, this.currentPixelWidthHeightRatio);
                this.reportedWidth = this.currentWidth;
                this.reportedHeight = this.currentHeight;
                this.reportedUnappliedRotationDegrees = this.currentUnappliedRotationDegrees;
                this.reportedPixelWidthHeightRatio = this.currentPixelWidthHeightRatio;
            }
        }
    }

    private void maybeRenotifyVideoSizeChanged() {
        if (this.reportedWidth != -1 || this.reportedHeight != -1) {
            this.eventDispatcher.videoSizeChanged(this.reportedWidth, this.reportedHeight, this.reportedUnappliedRotationDegrees, this.reportedPixelWidthHeightRatio);
        }
    }

    private void maybeNotifyDroppedFrames() {
        if (this.droppedFrames > 0) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            this.eventDispatcher.droppedFrames(this.droppedFrames, elapsedRealtime - this.droppedFrameAccumulationStartTimeMs);
            this.droppedFrames = 0;
            this.droppedFrameAccumulationStartTimeMs = elapsedRealtime;
        }
    }

    @TargetApi(23)
    private static void setOutputSurfaceV23(MediaCodec mediaCodec, Surface surface) {
        mediaCodec.setOutputSurface(surface);
    }

    @TargetApi(21)
    private static void configureTunnelingV21(MediaFormat mediaFormat, int i) {
        mediaFormat.setFeatureEnabled("tunneled-playback", true);
        mediaFormat.setInteger("audio-session-id", i);
    }

    /* Access modifiers changed, original: protected */
    @SuppressLint({"InlinedApi"})
    public MediaFormat getMediaFormat(Format format, CodecMaxValues codecMaxValues, float f, boolean z, int i) {
        MediaFormat mediaFormat = new MediaFormat();
        mediaFormat.setString("mime", format.sampleMimeType);
        mediaFormat.setInteger("width", format.width);
        mediaFormat.setInteger("height", format.height);
        MediaFormatUtil.setCsdBuffers(mediaFormat, format.initializationData);
        MediaFormatUtil.maybeSetFloat(mediaFormat, "frame-rate", format.frameRate);
        MediaFormatUtil.maybeSetInteger(mediaFormat, "rotation-degrees", format.rotationDegrees);
        MediaFormatUtil.maybeSetColorInfo(mediaFormat, format.colorInfo);
        mediaFormat.setInteger("max-width", codecMaxValues.width);
        mediaFormat.setInteger("max-height", codecMaxValues.height);
        MediaFormatUtil.maybeSetInteger(mediaFormat, "max-input-size", codecMaxValues.inputSize);
        if (Util.SDK_INT >= 23) {
            mediaFormat.setInteger("priority", 0);
            if (f != -1.0f) {
                mediaFormat.setFloat("operating-rate", f);
            }
        }
        if (z) {
            mediaFormat.setInteger("no-post-process", 1);
            mediaFormat.setInteger("auto-frc", 0);
        }
        if (i != 0) {
            configureTunnelingV21(mediaFormat, i);
        }
        return mediaFormat;
    }

    /* Access modifiers changed, original: protected */
    public CodecMaxValues getCodecMaxValues(MediaCodecInfo mediaCodecInfo, Format format, Format[] formatArr) throws DecoderQueryException {
        int i = format.width;
        int i2 = format.height;
        int maxInputSize = getMaxInputSize(mediaCodecInfo, format);
        if (formatArr.length == 1) {
            if (maxInputSize != -1) {
                int codecMaxInputSize = getCodecMaxInputSize(mediaCodecInfo, format.sampleMimeType, format.width, format.height);
                if (codecMaxInputSize != -1) {
                    maxInputSize = Math.min((int) (((float) maxInputSize) * INITIAL_FORMAT_MAX_INPUT_SIZE_SCALE_FACTOR), codecMaxInputSize);
                }
            }
            return new CodecMaxValues(i, i2, maxInputSize);
        }
        int length = formatArr.length;
        int i3 = i2;
        int i4 = maxInputSize;
        i2 = 0;
        maxInputSize = i;
        for (i = i2; i < length; i++) {
            Format format2 = formatArr[i];
            if (mediaCodecInfo.isSeamlessAdaptationSupported(format, format2, false)) {
                int i5 = (format2.width == -1 || format2.height == -1) ? 1 : 0;
                i2 |= i5;
                maxInputSize = Math.max(maxInputSize, format2.width);
                i3 = Math.max(i3, format2.height);
                i4 = Math.max(i4, getMaxInputSize(mediaCodecInfo, format2));
            }
        }
        if (i2 != 0) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Resolutions unknown. Codec max resolution: ");
            stringBuilder.append(maxInputSize);
            stringBuilder.append(AvidJSONUtil.KEY_X);
            stringBuilder.append(i3);
            Log.w(str, stringBuilder.toString());
            Point codecMaxSize = getCodecMaxSize(mediaCodecInfo, format);
            if (codecMaxSize != null) {
                maxInputSize = Math.max(maxInputSize, codecMaxSize.x);
                i3 = Math.max(i3, codecMaxSize.y);
                i4 = Math.max(i4, getCodecMaxInputSize(mediaCodecInfo, format.sampleMimeType, maxInputSize, i3));
                String str2 = TAG;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("Codec max resolution adjusted to: ");
                stringBuilder2.append(maxInputSize);
                stringBuilder2.append(AvidJSONUtil.KEY_X);
                stringBuilder2.append(i3);
                Log.w(str2, stringBuilder2.toString());
            }
        }
        return new CodecMaxValues(maxInputSize, i3, i4);
    }

    private static Point getCodecMaxSize(MediaCodecInfo mediaCodecInfo, Format format) throws DecoderQueryException {
        int i = 0;
        int i2 = format.height > format.width ? 1 : 0;
        int i3 = i2 != 0 ? format.height : format.width;
        int i4 = i2 != 0 ? format.width : format.height;
        float f = ((float) i4) / ((float) i3);
        int[] iArr = STANDARD_LONG_EDGE_VIDEO_PX;
        int length = iArr.length;
        while (i < length) {
            int i5 = iArr[i];
            int i6 = (int) (((float) i5) * f);
            if (i5 <= i3 || i6 <= i4) {
                return null;
            }
            int i7;
            if (Util.SDK_INT >= 21) {
                i7 = i2 != 0 ? i6 : i5;
                if (i2 == 0) {
                    i5 = i6;
                }
                Point alignVideoSizeV21 = mediaCodecInfo.alignVideoSizeV21(i7, i5);
                if (mediaCodecInfo.isVideoSizeAndRateSupportedV21(alignVideoSizeV21.x, alignVideoSizeV21.y, (double) format.frameRate)) {
                    return alignVideoSizeV21;
                }
            } else {
                i5 = Util.ceilDivide(i5, 16) * 16;
                i7 = 16 * Util.ceilDivide(i6, 16);
                if (i5 * i7 <= MediaCodecUtil.maxH264DecodableFrameSize()) {
                    int i8 = i2 != 0 ? i7 : i5;
                    if (i2 != 0) {
                        i7 = i5;
                    }
                    return new Point(i8, i7);
                }
            }
            i++;
        }
        return null;
    }

    private static int getMaxInputSize(MediaCodecInfo mediaCodecInfo, Format format) {
        if (format.maxInputSize == -1) {
            return getCodecMaxInputSize(mediaCodecInfo, format.sampleMimeType, format.width, format.height);
        }
        int i = 0;
        int i2 = 0;
        while (i < format.initializationData.size()) {
            i2 += ((byte[]) format.initializationData.get(i)).length;
            i++;
        }
        return format.maxInputSize + i2;
    }

    private static int getCodecMaxInputSize(com.google.android.exoplayer2.mediacodec.MediaCodecInfo r5, java.lang.String r6, int r7, int r8) {
        /*
        r0 = -1;
        if (r7 == r0) goto L_0x009a;
    L_0x0003:
        if (r8 != r0) goto L_0x0007;
    L_0x0005:
        goto L_0x009a;
    L_0x0007:
        r1 = r6.hashCode();
        r2 = 4;
        r3 = 3;
        r4 = 2;
        switch(r1) {
            case -1664118616: goto L_0x0044;
            case -1662541442: goto L_0x003a;
            case 1187890754: goto L_0x0030;
            case 1331836730: goto L_0x0026;
            case 1599127256: goto L_0x001c;
            case 1599127257: goto L_0x0012;
            default: goto L_0x0011;
        };
    L_0x0011:
        goto L_0x004e;
    L_0x0012:
        r1 = "video/x-vnd.on2.vp9";
        r6 = r6.equals(r1);
        if (r6 == 0) goto L_0x004e;
    L_0x001a:
        r6 = 5;
        goto L_0x004f;
    L_0x001c:
        r1 = "video/x-vnd.on2.vp8";
        r6 = r6.equals(r1);
        if (r6 == 0) goto L_0x004e;
    L_0x0024:
        r6 = r3;
        goto L_0x004f;
    L_0x0026:
        r1 = "video/avc";
        r6 = r6.equals(r1);
        if (r6 == 0) goto L_0x004e;
    L_0x002e:
        r6 = r4;
        goto L_0x004f;
    L_0x0030:
        r1 = "video/mp4v-es";
        r6 = r6.equals(r1);
        if (r6 == 0) goto L_0x004e;
    L_0x0038:
        r6 = 1;
        goto L_0x004f;
    L_0x003a:
        r1 = "video/hevc";
        r6 = r6.equals(r1);
        if (r6 == 0) goto L_0x004e;
    L_0x0042:
        r6 = r2;
        goto L_0x004f;
    L_0x0044:
        r1 = "video/3gpp";
        r6 = r6.equals(r1);
        if (r6 == 0) goto L_0x004e;
    L_0x004c:
        r6 = 0;
        goto L_0x004f;
    L_0x004e:
        r6 = r0;
    L_0x004f:
        switch(r6) {
            case 0: goto L_0x0094;
            case 1: goto L_0x0094;
            case 2: goto L_0x0057;
            case 3: goto L_0x0055;
            case 4: goto L_0x0053;
            case 5: goto L_0x0053;
            default: goto L_0x0052;
        };
    L_0x0052:
        return r0;
    L_0x0053:
        r7 = r7 * r8;
        goto L_0x0096;
    L_0x0055:
        r7 = r7 * r8;
        goto L_0x0095;
    L_0x0057:
        r6 = "BRAVIA 4K 2015";
        r1 = com.google.android.exoplayer2.util.Util.MODEL;
        r6 = r6.equals(r1);
        if (r6 != 0) goto L_0x0093;
    L_0x0061:
        r6 = "Amazon";
        r1 = com.google.android.exoplayer2.util.Util.MANUFACTURER;
        r6 = r6.equals(r1);
        if (r6 == 0) goto L_0x0084;
    L_0x006b:
        r6 = "KFSOWI";
        r1 = com.google.android.exoplayer2.util.Util.MODEL;
        r6 = r6.equals(r1);
        if (r6 != 0) goto L_0x0093;
    L_0x0075:
        r6 = "AFTS";
        r1 = com.google.android.exoplayer2.util.Util.MODEL;
        r6 = r6.equals(r1);
        if (r6 == 0) goto L_0x0084;
    L_0x007f:
        r5 = r5.secure;
        if (r5 == 0) goto L_0x0084;
    L_0x0083:
        goto L_0x0093;
    L_0x0084:
        r5 = 16;
        r6 = com.google.android.exoplayer2.util.Util.ceilDivide(r7, r5);
        r7 = com.google.android.exoplayer2.util.Util.ceilDivide(r8, r5);
        r6 = r6 * r7;
        r6 = r6 * r5;
        r7 = r6 * 16;
        goto L_0x0095;
    L_0x0093:
        return r0;
    L_0x0094:
        r7 = r7 * r8;
    L_0x0095:
        r2 = r4;
    L_0x0096:
        r7 = r7 * r3;
        r4 = r4 * r2;
        r7 = r7 / r4;
        return r7;
    L_0x009a:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.video.MediaCodecVideoRenderer.getCodecMaxInputSize(com.google.android.exoplayer2.mediacodec.MediaCodecInfo, java.lang.String, int, int):int");
    }

    private static boolean deviceNeedsNoPostProcessWorkaround() {
        return "NVIDIA".equals(Util.MANUFACTURER);
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Missing block: B:388:0x05ea, code skipped:
            r1 = true;
     */
    /* JADX WARNING: Missing block: B:389:0x05eb, code skipped:
            switch(r1) {
                case 0: goto L_0x05ef;
                case 1: goto L_0x05ef;
                case 2: goto L_0x05ef;
                case 3: goto L_0x05ef;
                case 4: goto L_0x05ef;
                case 5: goto L_0x05ef;
                case 6: goto L_0x05ef;
                case 7: goto L_0x05ef;
                case 8: goto L_0x05ef;
                case 9: goto L_0x05ef;
                case 10: goto L_0x05ef;
                case 11: goto L_0x05ef;
                case 12: goto L_0x05ef;
                case 13: goto L_0x05ef;
                case 14: goto L_0x05ef;
                case 15: goto L_0x05ef;
                case 16: goto L_0x05ef;
                case 17: goto L_0x05ef;
                case 18: goto L_0x05ef;
                case 19: goto L_0x05ef;
                case 20: goto L_0x05ef;
                case 21: goto L_0x05ef;
                case 22: goto L_0x05ef;
                case 23: goto L_0x05ef;
                case 24: goto L_0x05ef;
                case 25: goto L_0x05ef;
                case 26: goto L_0x05ef;
                case 27: goto L_0x05ef;
                case 28: goto L_0x05ef;
                case 29: goto L_0x05ef;
                case 30: goto L_0x05ef;
                case 31: goto L_0x05ef;
                case 32: goto L_0x05ef;
                case 33: goto L_0x05ef;
                case 34: goto L_0x05ef;
                case 35: goto L_0x05ef;
                case 36: goto L_0x05ef;
                case 37: goto L_0x05ef;
                case 38: goto L_0x05ef;
                case 39: goto L_0x05ef;
                case 40: goto L_0x05ef;
                case 41: goto L_0x05ef;
                case 42: goto L_0x05ef;
                case 43: goto L_0x05ef;
                case 44: goto L_0x05ef;
                case 45: goto L_0x05ef;
                case 46: goto L_0x05ef;
                case 47: goto L_0x05ef;
                case 48: goto L_0x05ef;
                case 49: goto L_0x05ef;
                case 50: goto L_0x05ef;
                case 51: goto L_0x05ef;
                case 52: goto L_0x05ef;
                case 53: goto L_0x05ef;
                case 54: goto L_0x05ef;
                case 55: goto L_0x05ef;
                case 56: goto L_0x05ef;
                case 57: goto L_0x05ef;
                case 58: goto L_0x05ef;
                case 59: goto L_0x05ef;
                case 60: goto L_0x05ef;
                case 61: goto L_0x05ef;
                case 62: goto L_0x05ef;
                case 63: goto L_0x05ef;
                case 64: goto L_0x05ef;
                case 65: goto L_0x05ef;
                case 66: goto L_0x05ef;
                case 67: goto L_0x05ef;
                case 68: goto L_0x05ef;
                case 69: goto L_0x05ef;
                case 70: goto L_0x05ef;
                case 71: goto L_0x05ef;
                case 72: goto L_0x05ef;
                case 73: goto L_0x05ef;
                case 74: goto L_0x05ef;
                case 75: goto L_0x05ef;
                case 76: goto L_0x05ef;
                case 77: goto L_0x05ef;
                case 78: goto L_0x05ef;
                case 79: goto L_0x05ef;
                case 80: goto L_0x05ef;
                case 81: goto L_0x05ef;
                case 82: goto L_0x05ef;
                case 83: goto L_0x05ef;
                case 84: goto L_0x05ef;
                case 85: goto L_0x05ef;
                case 86: goto L_0x05ef;
                case 87: goto L_0x05ef;
                case 88: goto L_0x05ef;
                case 89: goto L_0x05ef;
                case 90: goto L_0x05ef;
                case 91: goto L_0x05ef;
                case 92: goto L_0x05ef;
                case 93: goto L_0x05ef;
                case 94: goto L_0x05ef;
                case 95: goto L_0x05ef;
                case 96: goto L_0x05ef;
                case 97: goto L_0x05ef;
                case 98: goto L_0x05ef;
                case 99: goto L_0x05ef;
                case 100: goto L_0x05ef;
                case 101: goto L_0x05ef;
                case 102: goto L_0x05ef;
                case 103: goto L_0x05ef;
                case 104: goto L_0x05ef;
                case 105: goto L_0x05ef;
                case 106: goto L_0x05ef;
                case 107: goto L_0x05ef;
                case 108: goto L_0x05ef;
                case 109: goto L_0x05ef;
                case 110: goto L_0x05ef;
                case 111: goto L_0x05ef;
                case 112: goto L_0x05ef;
                case 113: goto L_0x05ef;
                case 114: goto L_0x05ef;
                case 115: goto L_0x05ef;
                case 116: goto L_0x05ef;
                case 117: goto L_0x05ef;
                case 118: goto L_0x05ef;
                case 119: goto L_0x05ef;
                case 120: goto L_0x05ef;
                case 121: goto L_0x05ef;
                case 122: goto L_0x05ef;
                default: goto L_0x05ee;
            };
     */
    /* JADX WARNING: Missing block: B:391:0x05ef, code skipped:
            deviceNeedsSetOutputSurfaceWorkaround = true;
     */
    /* JADX WARNING: Missing block: B:392:0x05f1, code skipped:
            r1 = com.google.android.exoplayer2.util.Util.MODEL;
            r2 = r1.hashCode();
     */
    /* JADX WARNING: Missing block: B:393:0x05fa, code skipped:
            if (r2 == 2006354) goto L_0x060c;
     */
    /* JADX WARNING: Missing block: B:395:0x05ff, code skipped:
            if (r2 == 2006367) goto L_0x0602;
     */
    /* JADX WARNING: Missing block: B:398:0x0608, code skipped:
            if (r1.equals("AFTN") == false) goto L_0x0615;
     */
    /* JADX WARNING: Missing block: B:399:0x060a, code skipped:
            r0 = true;
     */
    /* JADX WARNING: Missing block: B:401:0x0612, code skipped:
            if (r1.equals("AFTA") == false) goto L_0x0615;
     */
    /* JADX WARNING: Missing block: B:403:0x0615, code skipped:
            r0 = true;
     */
    /* JADX WARNING: Missing block: B:404:0x0616, code skipped:
            switch(r0) {
                case 0: goto L_0x061a;
                case 1: goto L_0x061a;
                default: goto L_0x0619;
            };
     */
    /* JADX WARNING: Missing block: B:406:0x061a, code skipped:
            deviceNeedsSetOutputSurfaceWorkaround = true;
     */
    public boolean codecNeedsSetOutputSurfaceWorkaround(java.lang.String r7) {
        /*
        r6 = this;
        r0 = "OMX.google";
        r7 = r7.startsWith(r0);
        r0 = 0;
        if (r7 == 0) goto L_0x000a;
    L_0x0009:
        return r0;
    L_0x000a:
        r7 = com.google.android.exoplayer2.video.MediaCodecVideoRenderer.class;
        monitor-enter(r7);
        r1 = evaluatedDeviceNeedsSetOutputSurfaceWorkaround;	 Catch:{ all -> 0x0622 }
        if (r1 != 0) goto L_0x061e;
    L_0x0011:
        r1 = com.google.android.exoplayer2.util.Util.SDK_INT;	 Catch:{ all -> 0x0622 }
        r2 = 27;
        r3 = 1;
        if (r1 > r2) goto L_0x0026;
    L_0x0018:
        r1 = "dangal";
        r4 = com.google.android.exoplayer2.util.Util.DEVICE;	 Catch:{ all -> 0x0622 }
        r1 = r1.equals(r4);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x0026;
    L_0x0022:
        deviceNeedsSetOutputSurfaceWorkaround = r3;	 Catch:{ all -> 0x0622 }
        goto L_0x061c;
    L_0x0026:
        r1 = com.google.android.exoplayer2.util.Util.SDK_INT;	 Catch:{ all -> 0x0622 }
        if (r1 < r2) goto L_0x002c;
    L_0x002a:
        goto L_0x061c;
    L_0x002c:
        r1 = com.google.android.exoplayer2.util.Util.DEVICE;	 Catch:{ all -> 0x0622 }
        r4 = r1.hashCode();	 Catch:{ all -> 0x0622 }
        r5 = -1;
        switch(r4) {
            case -2144781245: goto L_0x05df;
            case -2144781185: goto L_0x05d4;
            case -2144781160: goto L_0x05c9;
            case -2097309513: goto L_0x05be;
            case -2022874474: goto L_0x05b3;
            case -1978993182: goto L_0x05a8;
            case -1978990237: goto L_0x059d;
            case -1936688988: goto L_0x0592;
            case -1936688066: goto L_0x0587;
            case -1936688065: goto L_0x057b;
            case -1931988508: goto L_0x056f;
            case -1696512866: goto L_0x0563;
            case -1680025915: goto L_0x0557;
            case -1615810839: goto L_0x054b;
            case -1554255044: goto L_0x053f;
            case -1481772737: goto L_0x0533;
            case -1481772730: goto L_0x0527;
            case -1481772729: goto L_0x051b;
            case -1320080169: goto L_0x050f;
            case -1217592143: goto L_0x0503;
            case -1180384755: goto L_0x04f7;
            case -1139198265: goto L_0x04eb;
            case -1052835013: goto L_0x04df;
            case -993250464: goto L_0x04d4;
            case -965403638: goto L_0x04c8;
            case -958336948: goto L_0x04bc;
            case -879245230: goto L_0x04b0;
            case -842500323: goto L_0x04a4;
            case -821392978: goto L_0x0499;
            case -797483286: goto L_0x048d;
            case -794946968: goto L_0x0481;
            case -788334647: goto L_0x0475;
            case -782144577: goto L_0x0469;
            case -575125681: goto L_0x045d;
            case -521118391: goto L_0x0451;
            case -430914369: goto L_0x0445;
            case -290434366: goto L_0x0439;
            case -282781963: goto L_0x042d;
            case -277133239: goto L_0x0421;
            case -173639913: goto L_0x0415;
            case -56598463: goto L_0x0409;
            case 2126: goto L_0x03fd;
            case 2564: goto L_0x03f1;
            case 2715: goto L_0x03e5;
            case 2719: goto L_0x03d9;
            case 3483: goto L_0x03cd;
            case 73405: goto L_0x03c1;
            case 75739: goto L_0x03b5;
            case 76779: goto L_0x03a9;
            case 78669: goto L_0x039d;
            case 79305: goto L_0x0391;
            case 80618: goto L_0x0385;
            case 88274: goto L_0x0379;
            case 98846: goto L_0x036d;
            case 98848: goto L_0x0361;
            case 99329: goto L_0x0355;
            case 101481: goto L_0x0349;
            case 1513190: goto L_0x033e;
            case 1514184: goto L_0x0333;
            case 1514185: goto L_0x0328;
            case 2436959: goto L_0x031c;
            case 2463773: goto L_0x0310;
            case 2464648: goto L_0x0304;
            case 2689555: goto L_0x02f8;
            case 3154429: goto L_0x02ec;
            case 3284551: goto L_0x02e0;
            case 3351335: goto L_0x02d4;
            case 3386211: goto L_0x02c8;
            case 41325051: goto L_0x02bc;
            case 55178625: goto L_0x02b0;
            case 61542055: goto L_0x02a5;
            case 65355429: goto L_0x0299;
            case 66214468: goto L_0x028d;
            case 66214470: goto L_0x0281;
            case 66214473: goto L_0x0275;
            case 66215429: goto L_0x0269;
            case 66215431: goto L_0x025d;
            case 66215433: goto L_0x0251;
            case 66216390: goto L_0x0245;
            case 76402249: goto L_0x0239;
            case 76404105: goto L_0x022d;
            case 76404911: goto L_0x0221;
            case 80963634: goto L_0x0215;
            case 82882791: goto L_0x0209;
            case 98715550: goto L_0x01fd;
            case 102844228: goto L_0x01f1;
            case 165221241: goto L_0x01e6;
            case 182191441: goto L_0x01da;
            case 245388979: goto L_0x01ce;
            case 287431619: goto L_0x01c2;
            case 307593612: goto L_0x01b6;
            case 308517133: goto L_0x01aa;
            case 316215098: goto L_0x019e;
            case 316215116: goto L_0x0192;
            case 316246811: goto L_0x0186;
            case 316246818: goto L_0x017a;
            case 407160593: goto L_0x016e;
            case 507412548: goto L_0x0162;
            case 793982701: goto L_0x0156;
            case 794038622: goto L_0x014a;
            case 794040393: goto L_0x013e;
            case 835649806: goto L_0x0132;
            case 917340916: goto L_0x0127;
            case 958008161: goto L_0x011b;
            case 1060579533: goto L_0x010f;
            case 1150207623: goto L_0x0103;
            case 1176899427: goto L_0x00f7;
            case 1280332038: goto L_0x00eb;
            case 1306947716: goto L_0x00df;
            case 1349174697: goto L_0x00d3;
            case 1522194893: goto L_0x00c7;
            case 1691543273: goto L_0x00bb;
            case 1709443163: goto L_0x00af;
            case 1865889110: goto L_0x00a3;
            case 1906253259: goto L_0x0097;
            case 1977196784: goto L_0x008b;
            case 2006372676: goto L_0x007f;
            case 2029784656: goto L_0x0073;
            case 2030379515: goto L_0x0067;
            case 2033393791: goto L_0x005b;
            case 2047190025: goto L_0x004f;
            case 2047252157: goto L_0x0044;
            case 2048319463: goto L_0x0038;
            default: goto L_0x0036;
        };	 Catch:{ all -> 0x0622 }
    L_0x0036:
        goto L_0x05ea;
    L_0x0038:
        r2 = "HWVNS-H";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x0040:
        r1 = 53;
        goto L_0x05eb;
    L_0x0044:
        r4 = "ELUGA_Prim";
        r1 = r1.equals(r4);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x004c:
        r1 = r2;
        goto L_0x05eb;
    L_0x004f:
        r2 = "ELUGA_Note";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x0057:
        r1 = 26;
        goto L_0x05eb;
    L_0x005b:
        r2 = "ASUS_X00AD_2";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x0063:
        r1 = 11;
        goto L_0x05eb;
    L_0x0067:
        r2 = "HWCAM-H";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x006f:
        r1 = 52;
        goto L_0x05eb;
    L_0x0073:
        r2 = "HWBLN-H";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x007b:
        r1 = 51;
        goto L_0x05eb;
    L_0x007f:
        r2 = "BRAVIA_ATV3_4K";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x0087:
        r1 = 15;
        goto L_0x05eb;
    L_0x008b:
        r2 = "Infinix-X572";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x0093:
        r1 = 56;
        goto L_0x05eb;
    L_0x0097:
        r2 = "PB2-670M";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x009f:
        r1 = 84;
        goto L_0x05eb;
    L_0x00a3:
        r2 = "santoni";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x00ab:
        r1 = 100;
        goto L_0x05eb;
    L_0x00af:
        r2 = "iball8735_9806";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x00b7:
        r1 = 55;
        goto L_0x05eb;
    L_0x00bb:
        r2 = "CPH1609";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x00c3:
        r1 = 19;
        goto L_0x05eb;
    L_0x00c7:
        r2 = "woods_f";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x00cf:
        r1 = 116; // 0x74 float:1.63E-43 double:5.73E-322;
        goto L_0x05eb;
    L_0x00d3:
        r2 = "htc_e56ml_dtul";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x00db:
        r1 = 49;
        goto L_0x05eb;
    L_0x00df:
        r2 = "EverStar_S";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x00e7:
        r1 = 29;
        goto L_0x05eb;
    L_0x00eb:
        r2 = "hwALE-H";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x00f3:
        r1 = 50;
        goto L_0x05eb;
    L_0x00f7:
        r2 = "itel_S41";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x00ff:
        r1 = 58;
        goto L_0x05eb;
    L_0x0103:
        r2 = "LS-5017";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x010b:
        r1 = 64;
        goto L_0x05eb;
    L_0x010f:
        r2 = "panell_d";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x0117:
        r1 = 80;
        goto L_0x05eb;
    L_0x011b:
        r2 = "j2xlteins";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x0123:
        r1 = 59;
        goto L_0x05eb;
    L_0x0127:
        r2 = "A7000plus";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x012f:
        r1 = 7;
        goto L_0x05eb;
    L_0x0132:
        r2 = "manning";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x013a:
        r1 = 66;
        goto L_0x05eb;
    L_0x013e:
        r2 = "GIONEE_WBL7519";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x0146:
        r1 = 47;
        goto L_0x05eb;
    L_0x014a:
        r2 = "GIONEE_WBL7365";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x0152:
        r1 = 46;
        goto L_0x05eb;
    L_0x0156:
        r2 = "GIONEE_WBL5708";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x015e:
        r1 = 45;
        goto L_0x05eb;
    L_0x0162:
        r2 = "QM16XE_U";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x016a:
        r1 = 98;
        goto L_0x05eb;
    L_0x016e:
        r2 = "Pixi5-10_4G";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x0176:
        r1 = 90;
        goto L_0x05eb;
    L_0x017a:
        r2 = "TB3-850M";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x0182:
        r1 = 108; // 0x6c float:1.51E-43 double:5.34E-322;
        goto L_0x05eb;
    L_0x0186:
        r2 = "TB3-850F";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x018e:
        r1 = 107; // 0x6b float:1.5E-43 double:5.3E-322;
        goto L_0x05eb;
    L_0x0192:
        r2 = "TB3-730X";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x019a:
        r1 = 106; // 0x6a float:1.49E-43 double:5.24E-322;
        goto L_0x05eb;
    L_0x019e:
        r2 = "TB3-730F";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x01a6:
        r1 = 105; // 0x69 float:1.47E-43 double:5.2E-322;
        goto L_0x05eb;
    L_0x01aa:
        r2 = "A7020a48";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x01b2:
        r1 = 9;
        goto L_0x05eb;
    L_0x01b6:
        r2 = "A7010a48";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x01be:
        r1 = 8;
        goto L_0x05eb;
    L_0x01c2:
        r2 = "griffin";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x01ca:
        r1 = 48;
        goto L_0x05eb;
    L_0x01ce:
        r2 = "marino_f";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x01d6:
        r1 = 67;
        goto L_0x05eb;
    L_0x01da:
        r2 = "CPY83_I00";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x01e2:
        r1 = 20;
        goto L_0x05eb;
    L_0x01e6:
        r2 = "A2016a40";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x01ee:
        r1 = 5;
        goto L_0x05eb;
    L_0x01f1:
        r2 = "le_x6";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x01f9:
        r1 = 63;
        goto L_0x05eb;
    L_0x01fd:
        r2 = "i9031";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x0205:
        r1 = 54;
        goto L_0x05eb;
    L_0x0209:
        r2 = "X3_HK";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x0211:
        r1 = 118; // 0x76 float:1.65E-43 double:5.83E-322;
        goto L_0x05eb;
    L_0x0215:
        r2 = "V23GB";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x021d:
        r1 = 111; // 0x6f float:1.56E-43 double:5.5E-322;
        goto L_0x05eb;
    L_0x0221:
        r2 = "Q4310";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x0229:
        r1 = 96;
        goto L_0x05eb;
    L_0x022d:
        r2 = "Q4260";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x0235:
        r1 = 94;
        goto L_0x05eb;
    L_0x0239:
        r2 = "PRO7S";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x0241:
        r1 = 92;
        goto L_0x05eb;
    L_0x0245:
        r2 = "F3311";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x024d:
        r1 = 36;
        goto L_0x05eb;
    L_0x0251:
        r2 = "F3215";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x0259:
        r1 = 35;
        goto L_0x05eb;
    L_0x025d:
        r2 = "F3213";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x0265:
        r1 = 34;
        goto L_0x05eb;
    L_0x0269:
        r2 = "F3211";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x0271:
        r1 = 33;
        goto L_0x05eb;
    L_0x0275:
        r2 = "F3116";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x027d:
        r1 = 32;
        goto L_0x05eb;
    L_0x0281:
        r2 = "F3113";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x0289:
        r1 = 31;
        goto L_0x05eb;
    L_0x028d:
        r2 = "F3111";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x0295:
        r1 = 30;
        goto L_0x05eb;
    L_0x0299:
        r2 = "E5643";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x02a1:
        r1 = 24;
        goto L_0x05eb;
    L_0x02a5:
        r2 = "A1601";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x02ad:
        r1 = 4;
        goto L_0x05eb;
    L_0x02b0:
        r2 = "Aura_Note_2";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x02b8:
        r1 = 12;
        goto L_0x05eb;
    L_0x02bc:
        r2 = "MEIZU_M5";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x02c4:
        r1 = 68;
        goto L_0x05eb;
    L_0x02c8:
        r2 = "p212";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x02d0:
        r1 = 77;
        goto L_0x05eb;
    L_0x02d4:
        r2 = "mido";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x02dc:
        r1 = 70;
        goto L_0x05eb;
    L_0x02e0:
        r2 = "kate";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x02e8:
        r1 = 62;
        goto L_0x05eb;
    L_0x02ec:
        r2 = "fugu";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x02f4:
        r1 = 38;
        goto L_0x05eb;
    L_0x02f8:
        r2 = "XE2X";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x0300:
        r1 = 119; // 0x77 float:1.67E-43 double:5.9E-322;
        goto L_0x05eb;
    L_0x0304:
        r2 = "Q427";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x030c:
        r1 = 95;
        goto L_0x05eb;
    L_0x0310:
        r2 = "Q350";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x0318:
        r1 = 93;
        goto L_0x05eb;
    L_0x031c:
        r2 = "P681";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x0324:
        r1 = 78;
        goto L_0x05eb;
    L_0x0328:
        r2 = "1714";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x0330:
        r1 = 2;
        goto L_0x05eb;
    L_0x0333:
        r2 = "1713";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x033b:
        r1 = r3;
        goto L_0x05eb;
    L_0x033e:
        r2 = "1601";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x0346:
        r1 = r0;
        goto L_0x05eb;
    L_0x0349:
        r2 = "flo";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x0351:
        r1 = 37;
        goto L_0x05eb;
    L_0x0355:
        r2 = "deb";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x035d:
        r1 = 23;
        goto L_0x05eb;
    L_0x0361:
        r2 = "cv3";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x0369:
        r1 = 22;
        goto L_0x05eb;
    L_0x036d:
        r2 = "cv1";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x0375:
        r1 = 21;
        goto L_0x05eb;
    L_0x0379:
        r2 = "Z80";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x0381:
        r1 = 122; // 0x7a float:1.71E-43 double:6.03E-322;
        goto L_0x05eb;
    L_0x0385:
        r2 = "QX1";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x038d:
        r1 = 99;
        goto L_0x05eb;
    L_0x0391:
        r2 = "PLE";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x0399:
        r1 = 91;
        goto L_0x05eb;
    L_0x039d:
        r2 = "P85";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x03a5:
        r1 = 79;
        goto L_0x05eb;
    L_0x03a9:
        r2 = "MX6";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x03b1:
        r1 = 71;
        goto L_0x05eb;
    L_0x03b5:
        r2 = "M5c";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x03bd:
        r1 = 65;
        goto L_0x05eb;
    L_0x03c1:
        r2 = "JGZ";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x03c9:
        r1 = 60;
        goto L_0x05eb;
    L_0x03cd:
        r2 = "mh";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x03d5:
        r1 = 69;
        goto L_0x05eb;
    L_0x03d9:
        r2 = "V5";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x03e1:
        r1 = 112; // 0x70 float:1.57E-43 double:5.53E-322;
        goto L_0x05eb;
    L_0x03e5:
        r2 = "V1";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x03ed:
        r1 = 110; // 0x6e float:1.54E-43 double:5.43E-322;
        goto L_0x05eb;
    L_0x03f1:
        r2 = "Q5";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x03f9:
        r1 = 97;
        goto L_0x05eb;
    L_0x03fd:
        r2 = "C1";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x0405:
        r1 = 16;
        goto L_0x05eb;
    L_0x0409:
        r2 = "woods_fn";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x0411:
        r1 = 117; // 0x75 float:1.64E-43 double:5.8E-322;
        goto L_0x05eb;
    L_0x0415:
        r2 = "ELUGA_A3_Pro";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x041d:
        r1 = 25;
        goto L_0x05eb;
    L_0x0421:
        r2 = "Z12_PRO";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x0429:
        r1 = 121; // 0x79 float:1.7E-43 double:6.0E-322;
        goto L_0x05eb;
    L_0x042d:
        r2 = "BLACK-1X";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x0435:
        r1 = 13;
        goto L_0x05eb;
    L_0x0439:
        r2 = "taido_row";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x0441:
        r1 = 104; // 0x68 float:1.46E-43 double:5.14E-322;
        goto L_0x05eb;
    L_0x0445:
        r2 = "Pixi4-7_3G";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x044d:
        r1 = 89;
        goto L_0x05eb;
    L_0x0451:
        r2 = "GIONEE_GBL7360";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x0459:
        r1 = 41;
        goto L_0x05eb;
    L_0x045d:
        r2 = "GiONEE_CBL7513";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x0465:
        r1 = 39;
        goto L_0x05eb;
    L_0x0469:
        r2 = "OnePlus5T";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x0471:
        r1 = 76;
        goto L_0x05eb;
    L_0x0475:
        r2 = "whyred";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x047d:
        r1 = 115; // 0x73 float:1.61E-43 double:5.7E-322;
        goto L_0x05eb;
    L_0x0481:
        r2 = "watson";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x0489:
        r1 = 114; // 0x72 float:1.6E-43 double:5.63E-322;
        goto L_0x05eb;
    L_0x048d:
        r2 = "SVP-DTV15";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x0495:
        r1 = 102; // 0x66 float:1.43E-43 double:5.04E-322;
        goto L_0x05eb;
    L_0x0499:
        r2 = "A7000-a";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x04a1:
        r1 = 6;
        goto L_0x05eb;
    L_0x04a4:
        r2 = "nicklaus_f";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x04ac:
        r1 = 73;
        goto L_0x05eb;
    L_0x04b0:
        r2 = "tcl_eu";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x04b8:
        r1 = 109; // 0x6d float:1.53E-43 double:5.4E-322;
        goto L_0x05eb;
    L_0x04bc:
        r2 = "ELUGA_Ray_X";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x04c4:
        r1 = 28;
        goto L_0x05eb;
    L_0x04c8:
        r2 = "s905x018";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x04d0:
        r1 = 103; // 0x67 float:1.44E-43 double:5.1E-322;
        goto L_0x05eb;
    L_0x04d4:
        r2 = "A10-70F";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x04dc:
        r1 = 3;
        goto L_0x05eb;
    L_0x04df:
        r2 = "namath";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x04e7:
        r1 = 72;
        goto L_0x05eb;
    L_0x04eb:
        r2 = "Slate_Pro";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x04f3:
        r1 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        goto L_0x05eb;
    L_0x04f7:
        r2 = "iris60";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x04ff:
        r1 = 57;
        goto L_0x05eb;
    L_0x0503:
        r2 = "BRAVIA_ATV2";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x050b:
        r1 = 14;
        goto L_0x05eb;
    L_0x050f:
        r2 = "GiONEE_GBL7319";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x0517:
        r1 = 40;
        goto L_0x05eb;
    L_0x051b:
        r2 = "panell_dt";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x0523:
        r1 = 83;
        goto L_0x05eb;
    L_0x0527:
        r2 = "panell_ds";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x052f:
        r1 = 82;
        goto L_0x05eb;
    L_0x0533:
        r2 = "panell_dl";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x053b:
        r1 = 81;
        goto L_0x05eb;
    L_0x053f:
        r2 = "vernee_M5";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x0547:
        r1 = 113; // 0x71 float:1.58E-43 double:5.6E-322;
        goto L_0x05eb;
    L_0x054b:
        r2 = "Phantom6";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x0553:
        r1 = 88;
        goto L_0x05eb;
    L_0x0557:
        r2 = "ComioS1";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x055f:
        r1 = 17;
        goto L_0x05eb;
    L_0x0563:
        r2 = "XT1663";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x056b:
        r1 = 120; // 0x78 float:1.68E-43 double:5.93E-322;
        goto L_0x05eb;
    L_0x056f:
        r2 = "AquaPowerM";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x0577:
        r1 = 10;
        goto L_0x05eb;
    L_0x057b:
        r2 = "PGN611";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x0583:
        r1 = 87;
        goto L_0x05eb;
    L_0x0587:
        r2 = "PGN610";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x058f:
        r1 = 86;
        goto L_0x05eb;
    L_0x0592:
        r2 = "PGN528";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x059a:
        r1 = 85;
        goto L_0x05eb;
    L_0x059d:
        r2 = "NX573J";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x05a5:
        r1 = 75;
        goto L_0x05eb;
    L_0x05a8:
        r2 = "NX541J";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x05b0:
        r1 = 74;
        goto L_0x05eb;
    L_0x05b3:
        r2 = "CP8676_I02";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x05bb:
        r1 = 18;
        goto L_0x05eb;
    L_0x05be:
        r2 = "K50a40";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x05c6:
        r1 = 61;
        goto L_0x05eb;
    L_0x05c9:
        r2 = "GIONEE_SWW1631";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x05d1:
        r1 = 44;
        goto L_0x05eb;
    L_0x05d4:
        r2 = "GIONEE_SWW1627";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x05dc:
        r1 = 43;
        goto L_0x05eb;
    L_0x05df:
        r2 = "GIONEE_SWW1609";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x05ea;
    L_0x05e7:
        r1 = 42;
        goto L_0x05eb;
    L_0x05ea:
        r1 = r5;
    L_0x05eb:
        switch(r1) {
            case 0: goto L_0x05ef;
            case 1: goto L_0x05ef;
            case 2: goto L_0x05ef;
            case 3: goto L_0x05ef;
            case 4: goto L_0x05ef;
            case 5: goto L_0x05ef;
            case 6: goto L_0x05ef;
            case 7: goto L_0x05ef;
            case 8: goto L_0x05ef;
            case 9: goto L_0x05ef;
            case 10: goto L_0x05ef;
            case 11: goto L_0x05ef;
            case 12: goto L_0x05ef;
            case 13: goto L_0x05ef;
            case 14: goto L_0x05ef;
            case 15: goto L_0x05ef;
            case 16: goto L_0x05ef;
            case 17: goto L_0x05ef;
            case 18: goto L_0x05ef;
            case 19: goto L_0x05ef;
            case 20: goto L_0x05ef;
            case 21: goto L_0x05ef;
            case 22: goto L_0x05ef;
            case 23: goto L_0x05ef;
            case 24: goto L_0x05ef;
            case 25: goto L_0x05ef;
            case 26: goto L_0x05ef;
            case 27: goto L_0x05ef;
            case 28: goto L_0x05ef;
            case 29: goto L_0x05ef;
            case 30: goto L_0x05ef;
            case 31: goto L_0x05ef;
            case 32: goto L_0x05ef;
            case 33: goto L_0x05ef;
            case 34: goto L_0x05ef;
            case 35: goto L_0x05ef;
            case 36: goto L_0x05ef;
            case 37: goto L_0x05ef;
            case 38: goto L_0x05ef;
            case 39: goto L_0x05ef;
            case 40: goto L_0x05ef;
            case 41: goto L_0x05ef;
            case 42: goto L_0x05ef;
            case 43: goto L_0x05ef;
            case 44: goto L_0x05ef;
            case 45: goto L_0x05ef;
            case 46: goto L_0x05ef;
            case 47: goto L_0x05ef;
            case 48: goto L_0x05ef;
            case 49: goto L_0x05ef;
            case 50: goto L_0x05ef;
            case 51: goto L_0x05ef;
            case 52: goto L_0x05ef;
            case 53: goto L_0x05ef;
            case 54: goto L_0x05ef;
            case 55: goto L_0x05ef;
            case 56: goto L_0x05ef;
            case 57: goto L_0x05ef;
            case 58: goto L_0x05ef;
            case 59: goto L_0x05ef;
            case 60: goto L_0x05ef;
            case 61: goto L_0x05ef;
            case 62: goto L_0x05ef;
            case 63: goto L_0x05ef;
            case 64: goto L_0x05ef;
            case 65: goto L_0x05ef;
            case 66: goto L_0x05ef;
            case 67: goto L_0x05ef;
            case 68: goto L_0x05ef;
            case 69: goto L_0x05ef;
            case 70: goto L_0x05ef;
            case 71: goto L_0x05ef;
            case 72: goto L_0x05ef;
            case 73: goto L_0x05ef;
            case 74: goto L_0x05ef;
            case 75: goto L_0x05ef;
            case 76: goto L_0x05ef;
            case 77: goto L_0x05ef;
            case 78: goto L_0x05ef;
            case 79: goto L_0x05ef;
            case 80: goto L_0x05ef;
            case 81: goto L_0x05ef;
            case 82: goto L_0x05ef;
            case 83: goto L_0x05ef;
            case 84: goto L_0x05ef;
            case 85: goto L_0x05ef;
            case 86: goto L_0x05ef;
            case 87: goto L_0x05ef;
            case 88: goto L_0x05ef;
            case 89: goto L_0x05ef;
            case 90: goto L_0x05ef;
            case 91: goto L_0x05ef;
            case 92: goto L_0x05ef;
            case 93: goto L_0x05ef;
            case 94: goto L_0x05ef;
            case 95: goto L_0x05ef;
            case 96: goto L_0x05ef;
            case 97: goto L_0x05ef;
            case 98: goto L_0x05ef;
            case 99: goto L_0x05ef;
            case 100: goto L_0x05ef;
            case 101: goto L_0x05ef;
            case 102: goto L_0x05ef;
            case 103: goto L_0x05ef;
            case 104: goto L_0x05ef;
            case 105: goto L_0x05ef;
            case 106: goto L_0x05ef;
            case 107: goto L_0x05ef;
            case 108: goto L_0x05ef;
            case 109: goto L_0x05ef;
            case 110: goto L_0x05ef;
            case 111: goto L_0x05ef;
            case 112: goto L_0x05ef;
            case 113: goto L_0x05ef;
            case 114: goto L_0x05ef;
            case 115: goto L_0x05ef;
            case 116: goto L_0x05ef;
            case 117: goto L_0x05ef;
            case 118: goto L_0x05ef;
            case 119: goto L_0x05ef;
            case 120: goto L_0x05ef;
            case 121: goto L_0x05ef;
            case 122: goto L_0x05ef;
            default: goto L_0x05ee;
        };	 Catch:{ all -> 0x0622 }
    L_0x05ee:
        goto L_0x05f1;
    L_0x05ef:
        deviceNeedsSetOutputSurfaceWorkaround = r3;	 Catch:{ all -> 0x0622 }
    L_0x05f1:
        r1 = com.google.android.exoplayer2.util.Util.MODEL;	 Catch:{ all -> 0x0622 }
        r2 = r1.hashCode();	 Catch:{ all -> 0x0622 }
        r4 = 2006354; // 0x1e9d52 float:2.811501E-39 double:9.912706E-318;
        if (r2 == r4) goto L_0x060c;
    L_0x05fc:
        r0 = 2006367; // 0x1e9d5f float:2.811519E-39 double:9.91277E-318;
        if (r2 == r0) goto L_0x0602;
    L_0x0601:
        goto L_0x0615;
    L_0x0602:
        r0 = "AFTN";
        r0 = r1.equals(r0);	 Catch:{ all -> 0x0622 }
        if (r0 == 0) goto L_0x0615;
    L_0x060a:
        r0 = r3;
        goto L_0x0616;
    L_0x060c:
        r2 = "AFTA";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0622 }
        if (r1 == 0) goto L_0x0615;
    L_0x0614:
        goto L_0x0616;
    L_0x0615:
        r0 = r5;
    L_0x0616:
        switch(r0) {
            case 0: goto L_0x061a;
            case 1: goto L_0x061a;
            default: goto L_0x0619;
        };	 Catch:{ all -> 0x0622 }
    L_0x0619:
        goto L_0x061c;
    L_0x061a:
        deviceNeedsSetOutputSurfaceWorkaround = r3;	 Catch:{ all -> 0x0622 }
    L_0x061c:
        evaluatedDeviceNeedsSetOutputSurfaceWorkaround = r3;	 Catch:{ all -> 0x0622 }
    L_0x061e:
        monitor-exit(r7);	 Catch:{ all -> 0x0622 }
        r7 = deviceNeedsSetOutputSurfaceWorkaround;
        return r7;
    L_0x0622:
        r0 = move-exception;
        monitor-exit(r7);	 Catch:{ all -> 0x0622 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.video.MediaCodecVideoRenderer.codecNeedsSetOutputSurfaceWorkaround(java.lang.String):boolean");
    }
}
