package com.integralads.avid.library.inmobi.session.internal.jsbridge;

import android.support.annotation.VisibleForTesting;
import android.webkit.WebView;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSessionContext;
import com.integralads.avid.library.inmobi.session.internal.jsbridge.AvidJavascriptInterface.AvidJavascriptInterfaceCallback;
import com.integralads.avid.library.inmobi.weakreference.AvidWebView;

public class AvidWebViewManager implements AvidJavascriptInterfaceCallback {
    private final InternalAvidAdSessionContext avidAdSessionContext;
    private final AvidBridgeManager avidBridgeManager;
    private final AvidWebView avidWebView = new AvidWebView(null);
    private AvidJavascriptInterface javascriptInterface;

    public AvidWebViewManager(InternalAvidAdSessionContext internalAvidAdSessionContext, AvidBridgeManager avidBridgeManager) {
        this.avidAdSessionContext = internalAvidAdSessionContext;
        this.avidBridgeManager = avidBridgeManager;
    }

    public void setWebView(WebView webView) {
        if (this.avidWebView.get() != webView) {
            this.avidBridgeManager.setWebView(null);
            clearJavascriptInterface();
            this.avidWebView.set(webView);
            if (webView != null) {
                this.javascriptInterface = new AvidJavascriptInterface(this.avidAdSessionContext);
                this.javascriptInterface.setCallback(this);
                webView.addJavascriptInterface(this.javascriptInterface, AvidJavascriptInterface.AVID_OBJECT);
            }
        }
    }

    public void destroy() {
        setWebView(null);
    }

    private void clearJavascriptInterface() {
        if (this.javascriptInterface != null) {
            this.javascriptInterface.setCallback(null);
            this.javascriptInterface = null;
        }
    }

    public void onAvidAdSessionContextInvoked() {
        this.avidBridgeManager.setWebView((WebView) this.avidWebView.get());
    }

    /* Access modifiers changed, original: 0000 */
    @VisibleForTesting
    public AvidJavascriptInterface getJavascriptInterface() {
        return this.javascriptInterface;
    }
}
