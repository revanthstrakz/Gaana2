package com.google.android.exoplayer2.offline;

import com.google.android.exoplayer2.offline.DownloadHelper.AnonymousClass1;
import com.google.android.exoplayer2.offline.DownloadHelper.Callback;

final /* synthetic */ class DownloadHelper$1$$Lambda$0 implements Runnable {
    private final AnonymousClass1 arg$1;
    private final Callback arg$2;

    DownloadHelper$1$$Lambda$0(AnonymousClass1 anonymousClass1, Callback callback) {
        this.arg$1 = anonymousClass1;
        this.arg$2 = callback;
    }

    public void run() {
        this.arg$1.lambda$run$0$DownloadHelper$1(this.arg$2);
    }
}
