package com.facebook.ads.internal.adapters;

import com.facebook.ads.AdError;

public interface InterstitialAdapterListener {
    void onInterstitialActivityDestroyed();

    void onInterstitialAdClicked(h hVar, String str, boolean z);

    void onInterstitialAdDismissed(h hVar);

    void onInterstitialAdDisplayed(h hVar);

    void onInterstitialAdLoaded(h hVar);

    void onInterstitialError(h hVar, AdError adError);

    void onInterstitialLoggingImpression(h hVar);
}
