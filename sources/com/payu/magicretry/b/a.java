package com.payu.magicretry.b;

import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.os.Message;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.payu.magicretry.MagicRetryFragment;

public class a extends WebViewClient {
    private MagicRetryFragment a;

    public a(MagicRetryFragment magicRetryFragment) {
        this.a = magicRetryFragment;
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("MagicRetryWebViewClient.java: onPageStarted: URL ");
        stringBuilder.append(str);
        com.payu.magicretry.a.a.a("#### PAYU", stringBuilder.toString());
        super.onPageStarted(webView, str, bitmap);
        if (this.a != null) {
            this.a.a(webView, str);
        }
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("MagicRetryWebViewClient.java: shouldOverrideUrlLoading: URL ");
        stringBuilder.append(str);
        com.payu.magicretry.a.a.a("#### PAYU", stringBuilder.toString());
        return super.shouldOverrideUrlLoading(webView, str);
    }

    public void onPageFinished(WebView webView, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("MagicRetryWebViewClient.java: onPageFinished: URL ");
        stringBuilder.append(str);
        com.payu.magicretry.a.a.a("#### PAYU", stringBuilder.toString());
        super.onPageFinished(webView, str);
        if (this.a != null) {
            this.a.b(webView, str);
        }
    }

    public void onFormResubmission(WebView webView, Message message, Message message2) {
        message2.sendToTarget();
    }

    public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
        super.onReceivedError(webView, webResourceRequest, webResourceError);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("MagicRetryWebViewClient.java: onReceivedError: URL ");
        stringBuilder.append(webView.getUrl());
        com.payu.magicretry.a.a.a("#### PAYU", stringBuilder.toString());
        if (this.a != null && webResourceRequest.isForMainFrame()) {
            this.a.c(webView, webResourceRequest.getUrl().toString());
        }
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        super.onReceivedError(webView, i, str, str2);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("MagicRetryWebViewClient.java: onReceivedError: URL ");
        stringBuilder.append(webView.getUrl());
        com.payu.magicretry.a.a.a("#### PAYU", stringBuilder.toString());
        if (VERSION.SDK_INT < 23 && this.a != null) {
            this.a.c(webView, str2);
        }
    }
}
