package com.player_framework;

import com.google.ads.interactivemedia.v3.api.AdEvent;

public interface m {
    void onAdEventUpdate(f fVar, AdEvent adEvent);

    void onBufferingUpdate(f fVar, int i);

    void onCompletion(f fVar);

    void onError(f fVar, int i, int i2);

    void onInfo(f fVar, int i, int i2);

    void onPrepared(f fVar);
}
