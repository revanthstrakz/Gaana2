package com.google.android.exoplayer2.video;

import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.video.VideoRendererEventListener.EventDispatcher;

final /* synthetic */ class VideoRendererEventListener$EventDispatcher$$Lambda$0 implements Runnable {
    private final EventDispatcher arg$1;
    private final DecoderCounters arg$2;

    VideoRendererEventListener$EventDispatcher$$Lambda$0(EventDispatcher eventDispatcher, DecoderCounters decoderCounters) {
        this.arg$1 = eventDispatcher;
        this.arg$2 = decoderCounters;
    }

    public void run() {
        this.arg$1.lambda$enabled$0$VideoRendererEventListener$EventDispatcher(this.arg$2);
    }
}
