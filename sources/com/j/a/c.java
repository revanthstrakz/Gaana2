package com.j.a;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public final class c extends WebViewClient {
    @SuppressLint({"NewApi"})
    public final boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
        return shouldOverrideUrlLoading(webView, webResourceRequest.getUrl().toString());
    }

    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        boolean z = true;
        try {
            str = Uri.parse(str).getHost();
            String host = Uri.parse(a.c).getHost();
            if (!(str == null || host == null)) {
                if (!str.equals(host)) {
                    return true;
                }
                z = false;
            }
        } catch (Exception unused) {
        }
        return z;
    }
}
