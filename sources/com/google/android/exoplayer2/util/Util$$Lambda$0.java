package com.google.android.exoplayer2.util;

import java.util.concurrent.ThreadFactory;

final /* synthetic */ class Util$$Lambda$0 implements ThreadFactory {
    private final String arg$1;

    Util$$Lambda$0(String str) {
        this.arg$1 = str;
    }

    public Thread newThread(Runnable runnable) {
        return new Thread(runnable, this.arg$1);
    }
}
