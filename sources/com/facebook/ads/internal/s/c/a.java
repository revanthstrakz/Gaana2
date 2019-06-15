package com.facebook.ads.internal.s.c;

import android.content.Context;
import android.os.Build.VERSION;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public abstract class a extends WebView {
    private static final String a = "a";
    private boolean b;

    public a(Context context) {
        super(context);
        d();
    }

    private void d() {
        setWebChromeClient(a());
        setWebViewClient(b());
        b.b(this);
        getSettings().setJavaScriptEnabled(true);
        getSettings().setDomStorageEnabled(true);
        if (VERSION.SDK_INT >= 17) {
            getSettings().setMediaPlaybackRequiresUserGesture(false);
        }
        setHorizontalScrollBarEnabled(false);
        setHorizontalScrollbarOverlay(false);
        setVerticalScrollBarEnabled(false);
        setVerticalScrollbarOverlay(false);
        if (VERSION.SDK_INT >= 21) {
            try {
                CookieManager.getInstance().setAcceptThirdPartyCookies(this, true);
            } catch (Exception unused) {
                Log.w(a, "Failed to initialize CookieManager.");
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public WebChromeClient a() {
        return new WebChromeClient();
    }

    /* Access modifiers changed, original: protected */
    public WebViewClient b() {
        return new WebViewClient();
    }

    public boolean c() {
        return this.b;
    }

    public void destroy() {
        this.b = true;
        super.destroy();
    }
}
