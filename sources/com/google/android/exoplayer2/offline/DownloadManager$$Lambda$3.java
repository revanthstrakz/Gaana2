package com.google.android.exoplayer2.offline;

final /* synthetic */ class DownloadManager$$Lambda$3 implements Runnable {
    private final DownloadManager arg$1;
    private final DownloadAction[] arg$2;

    DownloadManager$$Lambda$3(DownloadManager downloadManager, DownloadAction[] downloadActionArr) {
        this.arg$1 = downloadManager;
        this.arg$2 = downloadActionArr;
    }

    public void run() {
        this.arg$1.lambda$null$0$DownloadManager(this.arg$2);
    }
}
