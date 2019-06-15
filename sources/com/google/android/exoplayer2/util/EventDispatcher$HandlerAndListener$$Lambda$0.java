package com.google.android.exoplayer2.util;

import com.google.android.exoplayer2.util.EventDispatcher.Event;

final /* synthetic */ class EventDispatcher$HandlerAndListener$$Lambda$0 implements Runnable {
    private final HandlerAndListener arg$1;
    private final Event arg$2;

    EventDispatcher$HandlerAndListener$$Lambda$0(HandlerAndListener handlerAndListener, Event event) {
        this.arg$1 = handlerAndListener;
        this.arg$2 = event;
    }

    public void run() {
        this.arg$1.lambda$dispatch$0$EventDispatcher$HandlerAndListener(this.arg$2);
    }
}
