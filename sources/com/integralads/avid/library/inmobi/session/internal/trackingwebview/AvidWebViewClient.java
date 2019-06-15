package com.integralads.avid.library.inmobi.session.internal.trackingwebview;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class AvidWebViewClient extends WebViewClient {
    private AvidWebViewClientListener listener;

    public interface AvidWebViewClientListener {
        void webViewDidLoadData();
    }

    public AvidWebViewClientListener getListener() {
        return this.listener;
    }

    public void setListener(AvidWebViewClientListener avidWebViewClientListener) {
        this.listener = avidWebViewClientListener;
    }

    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        if (this.listener != null) {
            this.listener.webViewDidLoadData();
        }
    }
}
