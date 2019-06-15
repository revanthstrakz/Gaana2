package com.google.android.exoplayer2.offline;

final /* synthetic */ class DownloadManager$Task$$Lambda$1 implements Runnable {
    private final Task arg$1;
    private final Throwable arg$2;

    DownloadManager$Task$$Lambda$1(Task task, Throwable th) {
        this.arg$1 = task;
        this.arg$2 = th;
    }

    public void run() {
        this.arg$1.lambda$run$1$DownloadManager$Task(this.arg$2);
    }
}
