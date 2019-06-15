package com.payu.custombrowser;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import com.payu.custombrowser.util.CBConstant;
import com.payu.custombrowser.util.CBUtil;
import com.payu.magicretry.MagicRetryFragment;
import com.payu.magicretry.b.a;

public class PayUWebViewClient extends a {
    private boolean a = true;
    private boolean b = false;
    private String c = "";
    private Bank d;

    public PayUWebViewClient(@NonNull Bank bank, @NonNull String str) {
        this.d = bank;
        if (Bank.keyAnalytics == null) {
            Bank.keyAnalytics = str;
        }
    }

    public PayUWebViewClient(@NonNull Bank bank, @NonNull MagicRetryFragment magicRetryFragment, @NonNull String str) {
        super(magicRetryFragment);
        this.d = bank;
        if (Bank.keyAnalytics == null) {
            Bank.keyAnalytics = str;
        }
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
        this.a = false;
        if (this.d != null) {
            this.d.onPageStartedWebclient(str);
        }
    }

    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        if (!this.b) {
            this.a = true;
        }
        if (str.equals(this.c)) {
            this.a = true;
            this.b = false;
        } else {
            this.b = false;
        }
        if (this.d != null) {
            this.d.onPageFinishWebclient(str);
        }
    }

    public void onLoadResource(WebView webView, String str) {
        if (this.d != null) {
            this.d.onLoadResourse(webView, str);
        }
        super.onLoadResource(webView, str);
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        super.onReceivedError(webView, i, str, str2);
        if (str2 != null && this.d != null && VERSION.SDK_INT < 23) {
            this.d.onReceivedErrorWebClient(i, str);
        }
    }

    @TargetApi(23)
    public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
        super.onReceivedError(webView, webResourceRequest, webResourceError);
        if (this.d != null) {
            this.d.onReceivedErrorWebClient(webResourceError.getErrorCode(), webResourceError.getDescription().toString());
        }
    }

    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        this.d.onReceivedSslError(webView, sslErrorHandler, sslError);
        if (VERSION.SDK_INT <= 10) {
            sslErrorHandler.proceed();
        } else {
            int i = VERSION.SDK_INT;
        }
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        this.c = str;
        if (CBUtil.isPlayStoreUrl(str)) {
            CBUtil.launchPlayStore(this.d.getContext(), str, CBUtil.getWebViewVersion(webView));
            return true;
        } else if (str.startsWith(CBConstant.DEEP_LINK_INTENT_URI)) {
            return true;
        } else {
            if (!this.a) {
                this.b = true;
            }
            this.a = false;
            if (this.d != null) {
                this.d.onOverrideURL(str);
            }
            return false;
        }
    }
}
