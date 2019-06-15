package com.facebook.ads.internal.adapters;

import android.view.View;
import com.facebook.ads.AdError;

public interface BannerAdapterListener {
    void onBannerAdClicked(f fVar);

    void onBannerAdLoaded(f fVar, View view);

    void onBannerError(f fVar, AdError adError);

    void onBannerLoggingImpression(f fVar);
}
