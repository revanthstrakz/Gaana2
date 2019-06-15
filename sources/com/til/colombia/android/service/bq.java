package com.til.colombia.android.service;

final class bq implements Runnable {
    final /* synthetic */ InterstitialActivity a;

    bq(InterstitialActivity interstitialActivity) {
        this.a = interstitialActivity;
    }

    public final void run() {
        this.a.A.sendMessage(this.a.A.obtainMessage());
    }
}
