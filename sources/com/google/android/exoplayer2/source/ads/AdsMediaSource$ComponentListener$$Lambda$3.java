package com.google.android.exoplayer2.source.ads;

import com.google.android.exoplayer2.source.ads.AdsMediaSource.AdLoadException;

final /* synthetic */ class AdsMediaSource$ComponentListener$$Lambda$3 implements Runnable {
    private final ComponentListener arg$1;
    private final AdLoadException arg$2;

    AdsMediaSource$ComponentListener$$Lambda$3(ComponentListener componentListener, AdLoadException adLoadException) {
        this.arg$1 = componentListener;
        this.arg$2 = adLoadException;
    }

    public void run() {
        this.arg$1.lambda$onAdLoadError$3$AdsMediaSource$ComponentListener(this.arg$2);
    }
}
