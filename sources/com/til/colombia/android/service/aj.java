package com.til.colombia.android.service;

final class aj implements Runnable {
    final /* synthetic */ ah a;

    aj(ah ahVar) {
        this.a = ahVar;
    }

    public final void run() {
        this.a.h.sendEmptyMessage(0);
    }
}
