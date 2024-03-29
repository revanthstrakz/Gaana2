package com.integralads.avid.library.inmobi.session.internal;

import android.content.Context;
import android.support.annotation.VisibleForTesting;
import android.view.View;
import android.webkit.WebView;
import com.integralads.avid.library.inmobi.session.ExternalAvidAdSessionContext;
import com.integralads.avid.library.inmobi.session.internal.trackingwebview.AvidJavaScriptResourceInjector;
import com.integralads.avid.library.inmobi.session.internal.trackingwebview.AvidTrackingWebViewManager;

public abstract class InternalAvidManagedAdSession extends InternalAvidAdSession<View> {
    private AvidTrackingWebViewManager trackingWebViewManager = new AvidTrackingWebViewManager(this.webView);
    private final WebView webView;

    public InternalAvidManagedAdSession(Context context, String str, ExternalAvidAdSessionContext externalAvidAdSessionContext) {
        super(context, str, externalAvidAdSessionContext);
        this.webView = new WebView(context.getApplicationContext());
    }

    public WebView getWebView() {
        return this.webView;
    }

    public AvidJavaScriptResourceInjector getJavaScriptResourceInjector() {
        return this.trackingWebViewManager;
    }

    public void onStart() {
        super.onStart();
        updateWebViewManager();
        this.trackingWebViewManager.loadHTML();
    }

    /* Access modifiers changed, original: 0000 */
    @VisibleForTesting
    public void setTrackingWebViewManager(AvidTrackingWebViewManager avidTrackingWebViewManager) {
        this.trackingWebViewManager = avidTrackingWebViewManager;
    }
}
