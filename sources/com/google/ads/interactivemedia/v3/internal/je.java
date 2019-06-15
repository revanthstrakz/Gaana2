package com.google.ads.interactivemedia.v3.internal;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

@SuppressLint({"SetJavaScriptEnabled", "NewApi"})
public class je {
    private final a a;
    private final WebView b;

    public interface a {
        void a(jc jcVar);
    }

    public je(Context context, a aVar) {
        this(new WebView(context), aVar);
    }

    public je(WebView webView, a aVar) {
        this.a = aVar;
        this.b = webView;
        this.b.setBackgroundColor(0);
        if (VERSION.SDK_INT == 15) {
            this.b.setLayerType(1, null);
        }
        if (VERSION.SDK_INT > 19) {
            webView.getSettings().setMixedContentMode(0);
        }
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (!str.startsWith("gmsg://")) {
                    return false;
                }
                je.this.b(str);
                return true;
            }

            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                String str2 = "Started ";
                str = String.valueOf(str);
                je.c(str.length() != 0 ? str2.concat(str) : new String(str2));
            }

            public void onPageFinished(WebView webView, String str) {
                String str2 = "Finished ";
                str = String.valueOf(str);
                je.c(str.length() != 0 ? str2.concat(str) : new String(str2));
            }

            public void onReceivedError(WebView webView, int i, String str, String str2) {
                StringBuilder stringBuilder = new StringBuilder((20 + String.valueOf(str).length()) + String.valueOf(str2).length());
                stringBuilder.append("Error: ");
                stringBuilder.append(i);
                stringBuilder.append(" ");
                stringBuilder.append(str);
                stringBuilder.append(" ");
                stringBuilder.append(str2);
                je.c(stringBuilder.toString());
            }
        });
        webView.setWebChromeClient(new WebChromeClient());
        jr.a(webView.getSettings());
        CookieManager instance = CookieManager.getInstance();
        instance.setAcceptCookie(true);
        if (VERSION.SDK_INT >= 21) {
            instance.setAcceptThirdPartyCookies(webView, true);
        }
    }

    public WebView a() {
        return this.b;
    }

    public void a(String str) {
        this.b.loadUrl(str);
    }

    @TargetApi(19)
    public void a(jc jcVar) {
        String e = jcVar.e();
        a(true, jcVar, e);
        if (VERSION.SDK_INT >= 19) {
            try {
                this.b.evaluateJavascript(e, null);
                return;
            } catch (IllegalStateException unused) {
                this.b.loadUrl(e);
                return;
            }
        }
        this.b.loadUrl(e);
    }

    /* Access modifiers changed, original: protected */
    public void b(String str) {
        String str2;
        try {
            jc a = jc.a(str);
            a(false, a, str);
            this.a.a(a);
        } catch (IllegalArgumentException unused) {
            String str3 = "IMASDK";
            str2 = "Invalid internal message, ignoring. Please make sure the Google IMA SDK library is up to date. Message: ";
            str = String.valueOf(str);
            Log.w(str3, str.length() != 0 ? str2.concat(str) : new String(str2));
        } catch (Exception e) {
            str2 = "IMASDK";
            String str4 = "An internal error occured parsing message from javascript.  Message to be parsed: ";
            str = String.valueOf(str);
            Log.e(str2, str.length() != 0 ? str4.concat(str) : new String(str4), e);
        }
    }

    static final void a(boolean z, jc jcVar, String str) {
        Object obj = z ? "Sending javascript msg: " : "Received msg: ";
        String valueOf;
        StringBuilder stringBuilder;
        if (com.google.ads.interactivemedia.v3.internal.iz.a.a(com.google.ads.interactivemedia.v3.internal.iz.a.VERBOSE)) {
            valueOf = String.valueOf(jcVar);
            stringBuilder = new StringBuilder(((7 + String.valueOf(obj).length()) + String.valueOf(valueOf).length()) + String.valueOf(str).length());
            stringBuilder.append(obj);
            stringBuilder.append(valueOf);
            stringBuilder.append("; URL: ");
            stringBuilder.append(str);
            Log.d("IMASDK", stringBuilder.toString());
        } else if (com.google.ads.interactivemedia.v3.internal.iz.a.a(com.google.ads.interactivemedia.v3.internal.iz.a.ABRIDGED)) {
            String name = jcVar.a().name();
            valueOf = jcVar.b().name();
            stringBuilder = new StringBuilder(((17 + String.valueOf(obj).length()) + String.valueOf(name).length()) + String.valueOf(valueOf).length());
            stringBuilder.append(obj);
            stringBuilder.append("Channel: ");
            stringBuilder.append(name);
            stringBuilder.append("; type: ");
            stringBuilder.append(valueOf);
            Log.d("IMASDK", stringBuilder.toString());
        }
    }

    static final void c(String str) {
        if (com.google.ads.interactivemedia.v3.internal.iz.a.a(com.google.ads.interactivemedia.v3.internal.iz.a.LIFECYCLE)) {
            Log.d("IMASDK", str);
        }
    }
}
