package com.integralads.avid.library.inmobi.session.internal;

import android.content.Context;
import android.webkit.WebView;
import com.integralads.avid.library.inmobi.session.ExternalAvidAdSessionContext;

public abstract class InternalAvidHtmlAdSession extends InternalAvidAdSession<WebView> {
    public InternalAvidHtmlAdSession(Context context, String str, ExternalAvidAdSessionContext externalAvidAdSessionContext) {
        super(context, str, externalAvidAdSessionContext);
    }

    public WebView getWebView() {
        return (WebView) getView();
    }

    /* Access modifiers changed, original: protected */
    public void onViewRegistered() {
        super.onViewRegistered();
        updateWebViewManager();
    }

    /* Access modifiers changed, original: protected */
    public void onViewUnregistered() {
        super.onViewUnregistered();
        updateWebViewManager();
    }
}
