package com.google.android.exoplayer2.video;

import com.google.android.exoplayer2.video.VideoRendererEventListener.EventDispatcher;

final /* synthetic */ class VideoRendererEventListener$EventDispatcher$$Lambda$4 implements Runnable {
    private final EventDispatcher arg$1;
    private final int arg$2;
    private final int arg$3;
    private final int arg$4;
    private final float arg$5;

    VideoRendererEventListener$EventDispatcher$$Lambda$4(EventDispatcher eventDispatcher, int i, int i2, int i3, float f) {
        this.arg$1 = eventDispatcher;
        this.arg$2 = i;
        this.arg$3 = i2;
        this.arg$4 = i3;
        this.arg$5 = f;
    }

    public void run() {
        this.arg$1.lambda$videoSizeChanged$4$VideoRendererEventListener$EventDispatcher(this.arg$2, this.arg$3, this.arg$4, this.arg$5);
    }
}
