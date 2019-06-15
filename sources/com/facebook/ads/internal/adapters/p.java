package com.facebook.ads.internal.adapters;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import com.facebook.ads.AdError;

public class p extends BroadcastReceiver {
    private String a;
    private Context b;
    private InterstitialAdapterListener c;
    private h d;

    public p(Context context, String str, h hVar, InterstitialAdapterListener interstitialAdapterListener) {
        this.b = context;
        this.a = str;
        this.c = interstitialAdapterListener;
        this.d = hVar;
    }

    public void a() {
        IntentFilter intentFilter = new IntentFilter();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("com.facebook.ads.interstitial.impression.logged:");
        stringBuilder.append(this.a);
        intentFilter.addAction(stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("com.facebook.ads.interstitial.displayed:");
        stringBuilder.append(this.a);
        intentFilter.addAction(stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("com.facebook.ads.interstitial.dismissed:");
        stringBuilder.append(this.a);
        intentFilter.addAction(stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("com.facebook.ads.interstitial.clicked:");
        stringBuilder.append(this.a);
        intentFilter.addAction(stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("com.facebook.ads.interstitial.error:");
        stringBuilder.append(this.a);
        intentFilter.addAction(stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("com.facebook.ads.interstitial.activity_destroyed:");
        stringBuilder.append(this.a);
        intentFilter.addAction(stringBuilder.toString());
        LocalBroadcastManager.getInstance(this.b).registerReceiver(this, intentFilter);
    }

    public void b() {
        try {
            LocalBroadcastManager.getInstance(this.b).unregisterReceiver(this);
        } catch (Exception unused) {
        }
    }

    public void onReceive(Context context, Intent intent) {
        Object obj = intent.getAction().split(":")[0];
        if (this.c != null && obj != null) {
            if ("com.facebook.ads.interstitial.clicked".equals(obj)) {
                this.c.onInterstitialAdClicked(this.d, null, true);
            } else if ("com.facebook.ads.interstitial.dismissed".equals(obj)) {
                this.c.onInterstitialAdDismissed(this.d);
            } else if ("com.facebook.ads.interstitial.displayed".equals(obj)) {
                this.c.onInterstitialAdDisplayed(this.d);
            } else if ("com.facebook.ads.interstitial.impression.logged".equals(obj)) {
                this.c.onInterstitialLoggingImpression(this.d);
            } else if ("com.facebook.ads.interstitial.error".equals(obj)) {
                this.c.onInterstitialError(this.d, AdError.INTERNAL_ERROR);
            } else {
                if ("com.facebook.ads.interstitial.activity_destroyed".equals(obj)) {
                    this.c.onInterstitialActivityDestroyed();
                }
            }
        }
    }
}
