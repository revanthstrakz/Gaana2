package com.google.android.exoplayer2.offline;

final /* synthetic */ class DownloadManager$$Lambda$2 implements Runnable {
    private final DownloadManager arg$1;
    private final DownloadAction[] arg$2;

    DownloadManager$$Lambda$2(DownloadManager downloadManager, DownloadAction[] downloadActionArr) {
        this.arg$1 = downloadManager;
        this.arg$2 = downloadActionArr;
    }

    public void run() {
        this.arg$1.lambda$saveActions$2$DownloadManager(this.arg$2);
    }
}
