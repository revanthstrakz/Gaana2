package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.audio.AudioRendererEventListener.EventDispatcher;

final /* synthetic */ class AudioRendererEventListener$EventDispatcher$$Lambda$1 implements Runnable {
    private final EventDispatcher arg$1;
    private final String arg$2;
    private final long arg$3;
    private final long arg$4;

    AudioRendererEventListener$EventDispatcher$$Lambda$1(EventDispatcher eventDispatcher, String str, long j, long j2) {
        this.arg$1 = eventDispatcher;
        this.arg$2 = str;
        this.arg$3 = j;
        this.arg$4 = j2;
    }

    public void run() {
        this.arg$1.lambda$decoderInitialized$1$AudioRendererEventListener$EventDispatcher(this.arg$2, this.arg$3, this.arg$4);
    }
}
