package com.google.android.exoplayer2.video;

import android.support.annotation.Nullable;
import android.view.Surface;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.decoder.DecoderCounters;

public abstract /* synthetic */ class VideoRendererEventListener$$CC {
    public static void onDroppedFrames(VideoRendererEventListener videoRendererEventListener, int i, long j) {
    }

    public static void onRenderedFirstFrame(@Nullable VideoRendererEventListener videoRendererEventListener, Surface surface) {
    }

    public static void onVideoDecoderInitialized(VideoRendererEventListener videoRendererEventListener, String str, long j, long j2) {
    }

    public static void onVideoDisabled(VideoRendererEventListener videoRendererEventListener, DecoderCounters decoderCounters) {
    }

    public static void onVideoEnabled(VideoRendererEventListener videoRendererEventListener, DecoderCounters decoderCounters) {
    }

    public static void onVideoInputFormatChanged(VideoRendererEventListener videoRendererEventListener, Format format) {
    }

    public static void onVideoSizeChanged(VideoRendererEventListener videoRendererEventListener, int i, int i2, int i3, float f) {
    }
}
