package com.google.android.exoplayer2.offline;

import com.google.android.exoplayer2.offline.DownloadHelper.AnonymousClass1;
import com.google.android.exoplayer2.offline.DownloadHelper.Callback;
import java.io.IOException;

final /* synthetic */ class DownloadHelper$1$$Lambda$1 implements Runnable {
    private final AnonymousClass1 arg$1;
    private final Callback arg$2;
    private final IOException arg$3;

    DownloadHelper$1$$Lambda$1(AnonymousClass1 anonymousClass1, Callback callback, IOException iOException) {
        this.arg$1 = anonymousClass1;
        this.arg$2 = callback;
        this.arg$3 = iOException;
    }

    public void run() {
        this.arg$1.lambda$run$1$DownloadHelper$1(this.arg$2, this.arg$3);
    }
}
