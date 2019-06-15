package com.google.android.exoplayer2.offline;

import android.os.ConditionVariable;

final /* synthetic */ class DownloadManager$$Lambda$0 implements Runnable {
    private final ConditionVariable arg$1;

    private DownloadManager$$Lambda$0(ConditionVariable conditionVariable) {
        this.arg$1 = conditionVariable;
    }

    static Runnable get$Lambda(ConditionVariable conditionVariable) {
        return new DownloadManager$$Lambda$0(conditionVariable);
    }

    public void run() {
        this.arg$1.open();
    }
}
