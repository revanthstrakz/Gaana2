package com.integralads.avid.library.inmobi.session.internal;

import android.content.Context;
import android.support.annotation.VisibleForTesting;
import android.view.View;
import android.webkit.WebView;
import com.integralads.avid.library.inmobi.AvidBridge;
import com.integralads.avid.library.inmobi.deferred.AvidDeferredAdSessionListener;
import com.integralads.avid.library.inmobi.deferred.AvidDeferredAdSessionListenerImpl;
import com.integralads.avid.library.inmobi.session.ExternalAvidAdSessionContext;
import com.integralads.avid.library.inmobi.session.internal.jsbridge.AvidBridgeManager;
import com.integralads.avid.library.inmobi.session.internal.jsbridge.AvidBridgeManager.AvidBridgeManagerListener;
import com.integralads.avid.library.inmobi.session.internal.jsbridge.AvidWebViewManager;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import com.integralads.avid.library.inmobi.utils.AvidTimestamp;
import com.integralads.avid.library.inmobi.weakreference.AvidView;

public abstract class InternalAvidAdSession<T extends View> implements AvidBridgeManagerListener {
    private AdState adState;
    private AvidBridgeManager avidBridgeManager = new AvidBridgeManager(this.internalContext);
    private AvidDeferredAdSessionListenerImpl avidDeferredAdSessionListener;
    private AvidView<T> avidView;
    private final InternalAvidAdSessionContext internalContext;
    private boolean isActive;
    private boolean isReady;
    private double lastUpdated;
    private InternalAvidAdSessionListener listener;
    private final ObstructionsWhiteList obstructionsWhiteList;
    private AvidWebViewManager webViewManager;

    enum AdState {
        AD_STATE_IDLE,
        AD_STATE_VISIBLE,
        AD_STATE_HIDDEN
    }

    public abstract MediaType getMediaType();

    public abstract SessionType getSessionType();

    public abstract WebView getWebView();

    public void onStart() {
    }

    /* Access modifiers changed, original: protected */
    public void onViewRegistered() {
    }

    /* Access modifiers changed, original: protected */
    public void onViewUnregistered() {
    }

    public InternalAvidAdSession(Context context, String str, ExternalAvidAdSessionContext externalAvidAdSessionContext) {
        this.internalContext = new InternalAvidAdSessionContext(context, str, getSessionType().toString(), getMediaType().toString(), externalAvidAdSessionContext);
        this.avidBridgeManager.setListener(this);
        this.webViewManager = new AvidWebViewManager(this.internalContext, this.avidBridgeManager);
        this.avidView = new AvidView(null);
        this.isReady = externalAvidAdSessionContext.isDeferred() ^ 1;
        if (!this.isReady) {
            this.avidDeferredAdSessionListener = new AvidDeferredAdSessionListenerImpl(this, this.avidBridgeManager);
        }
        this.obstructionsWhiteList = new ObstructionsWhiteList();
        onViewChanged();
    }

    public String getAvidAdSessionId() {
        return this.internalContext.getAvidAdSessionId();
    }

    public ExternalAvidAdSessionContext getAvidAdSessionContext() {
        return this.internalContext.getAvidAdSessionContext();
    }

    public T getView() {
        return (View) this.avidView.get();
    }

    public AvidDeferredAdSessionListener getAvidDeferredAdSessionListener() {
        return this.avidDeferredAdSessionListener;
    }

    public InternalAvidAdSessionListener getListener() {
        return this.listener;
    }

    public void setListener(InternalAvidAdSessionListener internalAvidAdSessionListener) {
        this.listener = internalAvidAdSessionListener;
    }

    public boolean isEmpty() {
        return this.avidView.isEmpty();
    }

    public boolean isActive() {
        return this.isActive;
    }

    public boolean isReady() {
        return this.isReady;
    }

    public AvidBridgeManager getAvidBridgeManager() {
        return this.avidBridgeManager;
    }

    public ObstructionsWhiteList getObstructionsWhiteList() {
        return this.obstructionsWhiteList;
    }

    public void registerAdView(T t) {
        if (!doesManageView(t)) {
            onViewChanged();
            this.avidView.set(t);
            onViewRegistered();
            sessionStateCanBeChanged();
        }
    }

    public void unregisterAdView(T t) {
        if (doesManageView(t)) {
            onViewChanged();
            cleanupViewState();
            this.avidView.set(null);
            onViewUnregistered();
            sessionStateCanBeChanged();
        }
    }

    public boolean doesManageView(View view) {
        return this.avidView.contains(view);
    }

    public void onEnd() {
        cleanupViewState();
        if (this.avidDeferredAdSessionListener != null) {
            this.avidDeferredAdSessionListener.destroy();
        }
        this.avidBridgeManager.destroy();
        this.webViewManager.destroy();
        this.isReady = false;
        sessionStateCanBeChanged();
        if (this.listener != null) {
            this.listener.sessionDidEnd(this);
        }
    }

    public void onReady() {
        this.isReady = true;
        sessionStateCanBeChanged();
    }

    public void avidBridgeManagerDidInjectAvidJs() {
        sessionStateCanBeChanged();
    }

    public void setScreenMode(boolean z) {
        if (isActive()) {
            this.avidBridgeManager.publishAppState(z ? "active" : AvidBridge.APP_STATE_INACTIVE);
        }
    }

    public void publishNativeViewStateCommand(String str, double d) {
        if (d > this.lastUpdated) {
            this.avidBridgeManager.callAvidbridge(str);
            this.adState = AdState.AD_STATE_VISIBLE;
        }
    }

    public void publishEmptyNativeViewStateCommand(String str, double d) {
        if (d > this.lastUpdated && this.adState != AdState.AD_STATE_HIDDEN) {
            this.avidBridgeManager.callAvidbridge(str);
            this.adState = AdState.AD_STATE_HIDDEN;
        }
    }

    /* Access modifiers changed, original: protected */
    public void cleanupViewState() {
        if (isActive()) {
            this.avidBridgeManager.publishNativeViewState(AvidJSONUtil.getEmptyTreeJSONObject().toString());
        }
    }

    /* Access modifiers changed, original: protected */
    public void updateWebViewManager() {
        this.webViewManager.setWebView(getWebView());
    }

    /* Access modifiers changed, original: protected */
    public void sessionStateCanBeChanged() {
        boolean z = this.avidBridgeManager.isActive() && this.isReady && !isEmpty();
        if (this.isActive != z) {
            setActive(z);
        }
    }

    /* Access modifiers changed, original: protected */
    public void setActive(boolean z) {
        this.isActive = z;
        if (this.listener != null) {
            if (z) {
                this.listener.sessionHasBecomeActive(this);
                return;
            }
            this.listener.sessionHasResignedActive(this);
        }
    }

    private void onViewChanged() {
        this.lastUpdated = AvidTimestamp.getCurrentTime();
        this.adState = AdState.AD_STATE_IDLE;
    }

    /* Access modifiers changed, original: 0000 */
    @VisibleForTesting
    public void setAvidBridgeManager(AvidBridgeManager avidBridgeManager) {
        this.avidBridgeManager = avidBridgeManager;
    }

    /* Access modifiers changed, original: 0000 */
    @VisibleForTesting
    public AdState getAdState() {
        return this.adState;
    }

    /* Access modifiers changed, original: 0000 */
    @VisibleForTesting
    public double getLastUpdated() {
        return this.lastUpdated;
    }

    /* Access modifiers changed, original: 0000 */
    @VisibleForTesting
    public void setAvidWebViewManager(AvidWebViewManager avidWebViewManager) {
        this.webViewManager = avidWebViewManager;
    }
}
