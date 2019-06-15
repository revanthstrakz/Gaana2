package com.google.android.exoplayer2.source.ads;

import com.google.android.exoplayer2.ExoPlayer;

final /* synthetic */ class AdsMediaSource$$Lambda$0 implements Runnable {
    private final AdsMediaSource arg$1;
    private final ExoPlayer arg$2;
    private final ComponentListener arg$3;

    AdsMediaSource$$Lambda$0(AdsMediaSource adsMediaSource, ExoPlayer exoPlayer, ComponentListener componentListener) {
        this.arg$1 = adsMediaSource;
        this.arg$2 = exoPlayer;
        this.arg$3 = componentListener;
    }

    public void run() {
        this.arg$1.lambda$prepareSourceInternal$0$AdsMediaSource(this.arg$2, this.arg$3);
    }
}
