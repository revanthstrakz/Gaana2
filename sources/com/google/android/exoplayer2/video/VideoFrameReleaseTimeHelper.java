package com.google.android.exoplayer2.video;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.hardware.display.DisplayManager.DisplayListener;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.Choreographer;
import android.view.Choreographer.FrameCallback;
import android.view.Display;
import android.view.WindowManager;
import com.facebook.internal.ServerProtocol;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.util.Util;

@TargetApi(16)
public final class VideoFrameReleaseTimeHelper {
    private static final long CHOREOGRAPHER_SAMPLE_DELAY_MILLIS = 500;
    private static final long MAX_ALLOWED_DRIFT_NS = 20000000;
    private static final int MIN_FRAMES_FOR_ADJUSTMENT = 6;
    private static final long VSYNC_OFFSET_PERCENTAGE = 80;
    private long adjustedLastFrameTimeNs;
    private final DefaultDisplayListener displayListener;
    private long frameCount;
    private boolean haveSync;
    private long lastFramePresentationTimeUs;
    private long pendingAdjustedFrameTimeNs;
    private long syncFramePresentationTimeNs;
    private long syncUnadjustedReleaseTimeNs;
    private long vsyncDurationNs;
    private long vsyncOffsetNs;
    private final VSyncSampler vsyncSampler;
    private final WindowManager windowManager;

    @TargetApi(17)
    private final class DefaultDisplayListener implements DisplayListener {
        private final DisplayManager displayManager;

        public void onDisplayAdded(int i) {
        }

        public void onDisplayRemoved(int i) {
        }

        public DefaultDisplayListener(DisplayManager displayManager) {
            this.displayManager = displayManager;
        }

        public void register() {
            this.displayManager.registerDisplayListener(this, null);
        }

        public void unregister() {
            this.displayManager.unregisterDisplayListener(this);
        }

        public void onDisplayChanged(int i) {
            if (i == 0) {
                VideoFrameReleaseTimeHelper.this.updateDefaultDisplayRefreshRateParams();
            }
        }
    }

    private static final class VSyncSampler implements Callback, FrameCallback {
        private static final int CREATE_CHOREOGRAPHER = 0;
        private static final VSyncSampler INSTANCE = new VSyncSampler();
        private static final int MSG_ADD_OBSERVER = 1;
        private static final int MSG_REMOVE_OBSERVER = 2;
        private Choreographer choreographer;
        private final HandlerThread choreographerOwnerThread = new HandlerThread("ChoreographerOwner:Handler");
        private final Handler handler;
        private int observerCount;
        public volatile long sampledVsyncTimeNs = C.TIME_UNSET;

        public static VSyncSampler getInstance() {
            return INSTANCE;
        }

        private VSyncSampler() {
            this.choreographerOwnerThread.start();
            this.handler = Util.createHandler(this.choreographerOwnerThread.getLooper(), this);
            this.handler.sendEmptyMessage(0);
        }

        public void addObserver() {
            this.handler.sendEmptyMessage(1);
        }

        public void removeObserver() {
            this.handler.sendEmptyMessage(2);
        }

        public void doFrame(long j) {
            this.sampledVsyncTimeNs = j;
            this.choreographer.postFrameCallbackDelayed(this, VideoFrameReleaseTimeHelper.CHOREOGRAPHER_SAMPLE_DELAY_MILLIS);
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    createChoreographerInstanceInternal();
                    return true;
                case 1:
                    addObserverInternal();
                    return true;
                case 2:
                    removeObserverInternal();
                    return true;
                default:
                    return false;
            }
        }

        private void createChoreographerInstanceInternal() {
            this.choreographer = Choreographer.getInstance();
        }

        private void addObserverInternal() {
            this.observerCount++;
            if (this.observerCount == 1) {
                this.choreographer.postFrameCallback(this);
            }
        }

        private void removeObserverInternal() {
            this.observerCount--;
            if (this.observerCount == 0) {
                this.choreographer.removeFrameCallback(this);
                this.sampledVsyncTimeNs = C.TIME_UNSET;
            }
        }
    }

    public VideoFrameReleaseTimeHelper() {
        this(null);
    }

    public VideoFrameReleaseTimeHelper(@Nullable Context context) {
        DefaultDisplayListener defaultDisplayListener = null;
        if (context != null) {
            context = context.getApplicationContext();
            this.windowManager = (WindowManager) context.getSystemService("window");
        } else {
            this.windowManager = null;
        }
        if (this.windowManager != null) {
            if (Util.SDK_INT >= 17) {
                defaultDisplayListener = maybeBuildDefaultDisplayListenerV17(context);
            }
            this.displayListener = defaultDisplayListener;
            this.vsyncSampler = VSyncSampler.getInstance();
        } else {
            this.displayListener = null;
            this.vsyncSampler = null;
        }
        this.vsyncDurationNs = C.TIME_UNSET;
        this.vsyncOffsetNs = C.TIME_UNSET;
    }

    public void enable() {
        this.haveSync = false;
        if (this.windowManager != null) {
            this.vsyncSampler.addObserver();
            if (this.displayListener != null) {
                this.displayListener.register();
            }
            updateDefaultDisplayRefreshRateParams();
        }
    }

    public void disable() {
        if (this.windowManager != null) {
            if (this.displayListener != null) {
                this.displayListener.unregister();
            }
            this.vsyncSampler.removeObserver();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0053  */
    public long adjustReleaseTime(long r21, long r23) {
        /*
        r20 = this;
        r0 = r20;
        r1 = r21;
        r3 = r23;
        r5 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r5 = r5 * r1;
        r7 = r0.haveSync;
        if (r7 == 0) goto L_0x004d;
    L_0x000d:
        r7 = r0.lastFramePresentationTimeUs;
        r9 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1));
        if (r9 == 0) goto L_0x001f;
    L_0x0013:
        r7 = r0.frameCount;
        r9 = 1;
        r11 = r7 + r9;
        r0.frameCount = r11;
        r7 = r0.pendingAdjustedFrameTimeNs;
        r0.adjustedLastFrameTimeNs = r7;
    L_0x001f:
        r7 = r0.frameCount;
        r9 = 6;
        r11 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1));
        r7 = 0;
        if (r11 < 0) goto L_0x0045;
    L_0x0028:
        r8 = r0.syncFramePresentationTimeNs;
        r10 = r5 - r8;
        r8 = r0.frameCount;
        r10 = r10 / r8;
        r8 = r0.adjustedLastFrameTimeNs;
        r12 = r8 + r10;
        r8 = r0.isDriftTooLarge(r12, r3);
        if (r8 == 0) goto L_0x003c;
    L_0x0039:
        r0.haveSync = r7;
        goto L_0x004d;
    L_0x003c:
        r7 = r0.syncUnadjustedReleaseTimeNs;
        r9 = r7 + r12;
        r7 = r0.syncFramePresentationTimeNs;
        r14 = r9 - r7;
        goto L_0x004f;
    L_0x0045:
        r8 = r0.isDriftTooLarge(r5, r3);
        if (r8 == 0) goto L_0x004d;
    L_0x004b:
        r0.haveSync = r7;
    L_0x004d:
        r14 = r3;
        r12 = r5;
    L_0x004f:
        r7 = r0.haveSync;
        if (r7 != 0) goto L_0x005e;
    L_0x0053:
        r0.syncFramePresentationTimeNs = r5;
        r0.syncUnadjustedReleaseTimeNs = r3;
        r3 = 0;
        r0.frameCount = r3;
        r3 = 1;
        r0.haveSync = r3;
    L_0x005e:
        r0.lastFramePresentationTimeUs = r1;
        r0.pendingAdjustedFrameTimeNs = r12;
        r1 = r0.vsyncSampler;
        if (r1 == 0) goto L_0x008a;
    L_0x0066:
        r1 = r0.vsyncDurationNs;
        r3 = -9223372036854775807; // 0x8000000000000001 float:1.4E-45 double:-4.9E-324;
        r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1));
        if (r5 != 0) goto L_0x0072;
    L_0x0071:
        goto L_0x008a;
    L_0x0072:
        r1 = r0.vsyncSampler;
        r1 = r1.sampledVsyncTimeNs;
        r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1));
        if (r5 != 0) goto L_0x007b;
    L_0x007a:
        return r14;
    L_0x007b:
        r3 = r0.vsyncDurationNs;
        r16 = r1;
        r18 = r3;
        r1 = closestVsync(r14, r16, r18);
        r3 = r0.vsyncOffsetNs;
        r5 = r1 - r3;
        return r5;
    L_0x008a:
        return r14;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.video.VideoFrameReleaseTimeHelper.adjustReleaseTime(long, long):long");
    }

    @TargetApi(17)
    private DefaultDisplayListener maybeBuildDefaultDisplayListenerV17(Context context) {
        DisplayManager displayManager = (DisplayManager) context.getSystemService(ServerProtocol.DIALOG_PARAM_DISPLAY);
        if (displayManager == null) {
            return null;
        }
        return new DefaultDisplayListener(displayManager);
    }

    private void updateDefaultDisplayRefreshRateParams() {
        Display defaultDisplay = this.windowManager.getDefaultDisplay();
        if (defaultDisplay != null) {
            this.vsyncDurationNs = (long) (1.0E9d / ((double) defaultDisplay.getRefreshRate()));
            this.vsyncOffsetNs = (this.vsyncDurationNs * VSYNC_OFFSET_PERCENTAGE) / 100;
        }
    }

    private boolean isDriftTooLarge(long j, long j2) {
        return Math.abs((j2 - this.syncUnadjustedReleaseTimeNs) - (j - this.syncFramePresentationTimeNs)) > MAX_ALLOWED_DRIFT_NS;
    }

    private static long closestVsync(long j, long j2, long j3) {
        long j4 = j2 + (((j - j2) / j3) * j3);
        if (j <= j4) {
            j2 = j4;
            j4 -= j3;
        } else {
            j2 = j4 + j3;
        }
        return j2 - j < j - j4 ? j2 : j4;
    }
}
