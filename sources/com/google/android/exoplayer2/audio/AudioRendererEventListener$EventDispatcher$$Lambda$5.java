package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.audio.AudioRendererEventListener.EventDispatcher;

final /* synthetic */ class AudioRendererEventListener$EventDispatcher$$Lambda$5 implements Runnable {
    private final EventDispatcher arg$1;
    private final int arg$2;

    AudioRendererEventListener$EventDispatcher$$Lambda$5(EventDispatcher eventDispatcher, int i) {
        this.arg$1 = eventDispatcher;
        this.arg$2 = i;
    }

    public void run() {
        this.arg$1.lambda$audioSessionId$5$AudioRendererEventListener$EventDispatcher(this.arg$2);
    }
}
