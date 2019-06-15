package com.payu.custombrowser;

import android.os.Message;
import android.support.annotation.NonNull;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class PayUWebChromeClient extends WebChromeClient {
    private Bank a;
    private boolean b = false;

    public boolean onCreateWindow(WebView webView, boolean z, boolean z2, Message message) {
        return false;
    }

    public PayUWebChromeClient(@NonNull Bank bank) {
        this.a = bank;
    }

    public void onProgressChanged(WebView webView, int i) {
        if (this.a != null) {
            if (!this.b && i < 100) {
                this.b = true;
                this.a.onPageStarted();
            } else if (i == 100) {
                this.a.onPageStarted();
                this.b = false;
                this.a.onPageFinished();
            }
            this.a.onProgressChanged(i);
        }
    }
}
