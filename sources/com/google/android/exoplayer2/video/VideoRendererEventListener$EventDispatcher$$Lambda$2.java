package com.google.android.exoplayer2.video;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.video.VideoRendererEventListener.EventDispatcher;

final /* synthetic */ class VideoRendererEventListener$EventDispatcher$$Lambda$2 implements Runnable {
    private final EventDispatcher arg$1;
    private final Format arg$2;

    VideoRendererEventListener$EventDispatcher$$Lambda$2(EventDispatcher eventDispatcher, Format format) {
        this.arg$1 = eventDispatcher;
        this.arg$2 = format;
    }

    public void run() {
        this.arg$1.lambda$inputFormatChanged$2$VideoRendererEventListener$EventDispatcher(this.arg$2);
    }
}
