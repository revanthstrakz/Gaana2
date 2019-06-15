package com.til.colombia.android.service;

import android.webkit.WebView;
import android.webkit.WebViewClient;

final class ak extends WebViewClient {
    final /* synthetic */ ColombiaBannerView a;

    ak(ColombiaBannerView colombiaBannerView) {
        this.a = colombiaBannerView;
    }

    public final void onPageFinished(WebView webView, String str) {
        StringBuilder stringBuilder = new StringBuilder("javascript:setColombiaSDKData(");
        stringBuilder.append(this.a.itemJson);
        stringBuilder.append(")");
        webView.loadUrl(stringBuilder.toString());
    }
}
