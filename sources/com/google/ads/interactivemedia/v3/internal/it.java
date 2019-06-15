package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.os.Message;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebView.WebViewTransport;
import android.webkit.WebViewClient;
import com.google.ads.interactivemedia.v3.api.CompanionAdSlot.ClickListener;
import com.google.ads.interactivemedia.v3.impl.data.CompanionData;
import com.google.ads.interactivemedia.v3.impl.data.CompanionData.a;
import java.util.List;

public class it extends WebView {
    public it(final Context context, final jd jdVar, CompanionData companionData, final List<ClickListener> list) {
        super(context);
        getSettings().setJavaScriptEnabled(true);
        getSettings().setSupportMultipleWindows(true);
        setWebChromeClient(new WebChromeClient(this) {
            public boolean onCreateWindow(WebView webView, boolean z, boolean z2, Message message) {
                WebViewTransport webViewTransport = (WebViewTransport) message.obj;
                webViewTransport.setWebView(new WebView(context));
                webViewTransport.getWebView().setWebViewClient(new WebViewClient() {
                    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                        jdVar.c(str);
                        for (ClickListener onCompanionAdClick : list) {
                            onCompanionAdClick.onCompanionAdClick();
                        }
                        return true;
                    }
                });
                message.sendToTarget();
                return true;
            }
        });
        if (companionData.type() == a.Html) {
            loadData(companionData.src(), "text/html", null);
        } else if (companionData.type() == a.IFrame) {
            loadUrl(companionData.src());
        } else {
            String valueOf = String.valueOf(companionData.type());
            StringBuilder stringBuilder = new StringBuilder(51 + String.valueOf(valueOf).length());
            stringBuilder.append("Companion type ");
            stringBuilder.append(valueOf);
            stringBuilder.append(" is not valid for a CompanionWebView");
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }
}
