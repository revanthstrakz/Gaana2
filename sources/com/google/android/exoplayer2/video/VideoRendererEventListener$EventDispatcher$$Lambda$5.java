package com.google.android.exoplayer2.video;

import android.view.Surface;
import com.google.android.exoplayer2.video.VideoRendererEventListener.EventDispatcher;

final /* synthetic */ class VideoRendererEventListener$EventDispatcher$$Lambda$5 implements Runnable {
    private final EventDispatcher arg$1;
    private final Surface arg$2;

    VideoRendererEventListener$EventDispatcher$$Lambda$5(EventDispatcher eventDispatcher, Surface surface) {
        this.arg$1 = eventDispatcher;
        this.arg$2 = surface;
    }

    public void run() {
        this.arg$1.lambda$renderedFirstFrame$5$VideoRendererEventListener$EventDispatcher(this.arg$2);
    }
}
