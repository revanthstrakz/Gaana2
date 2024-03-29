package com.integralads.avid.library.inmobi.session.internal.jsbridge;

import android.text.TextUtils;
import android.webkit.WebView;
import com.integralads.avid.library.inmobi.AvidBridge;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSessionContext;
import com.integralads.avid.library.inmobi.utils.AvidCommand;
import com.integralads.avid.library.inmobi.weakreference.AvidWebView;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONObject;

public class AvidBridgeManager {
    public static final int VIDEO_EVENT_TAG = 1;
    private final InternalAvidAdSessionContext avidAdSessionContext;
    private AvidWebView avidWebView;
    private boolean isAvidJsInjected;
    private boolean isReadyEventPublished;
    private AvidBridgeManagerListener listener;
    private final ArrayList<AvidEvent> pendingEvents = new ArrayList();

    public interface AvidBridgeManagerListener {
        void avidBridgeManagerDidInjectAvidJs();
    }

    public AvidBridgeManager(InternalAvidAdSessionContext internalAvidAdSessionContext) {
        this.avidAdSessionContext = internalAvidAdSessionContext;
        this.avidWebView = new AvidWebView(null);
    }

    public boolean isActive() {
        return this.isAvidJsInjected;
    }

    public void setListener(AvidBridgeManagerListener avidBridgeManagerListener) {
        this.listener = avidBridgeManagerListener;
    }

    public void onAvidJsReady() {
        injectAvid();
    }

    public void setWebView(WebView webView) {
        if (this.avidWebView.get() != webView) {
            this.avidWebView.set(webView);
            this.isAvidJsInjected = false;
            if (AvidBridge.isAvidJsReady()) {
                injectAvid();
            }
        }
    }

    public void destroy() {
        setWebView(null);
    }

    public void callAvidbridge(String str) {
        this.avidWebView.injectFormattedJavaScript(str);
    }

    public void publishReadyEventForDeferredAdSession() {
        this.isReadyEventPublished = true;
        publishReadyEventIfNeeded();
    }

    public void publishNativeViewState(String str) {
        callAvidbridge(AvidCommand.setNativeViewState(str));
    }

    public void publishAppState(String str) {
        callAvidbridge(AvidCommand.setAppState(str));
    }

    public void publishVideoEvent(String str, JSONObject jSONObject) {
        if (isActive()) {
            invokePublishVideoEvent(str, jSONObject);
        } else {
            this.pendingEvents.add(new AvidEvent(1, str, jSONObject));
        }
    }

    private void injectAvid() {
        if (!this.avidWebView.isEmpty()) {
            this.isAvidJsInjected = true;
            this.avidWebView.injectJavaScript(AvidBridge.getAvidJs());
            setAvidAdSessionContext();
            publishReadyEventIfNeeded();
            publishPendingEvents();
            notifyListener();
        }
    }

    private void publishReadyEventIfNeeded() {
        if (isActive() && this.isReadyEventPublished) {
            callAvidbridge(AvidCommand.publishReadyEventForDeferredAdSession());
        }
    }

    private void invokePublishVideoEvent(String str, JSONObject jSONObject) {
        CharSequence jSONObject2 = jSONObject != null ? jSONObject.toString() : null;
        if (TextUtils.isEmpty(jSONObject2)) {
            callAvidbridge(AvidCommand.publishVideoEvent(str));
        } else {
            callAvidbridge(AvidCommand.publishVideoEvent(str, jSONObject2));
        }
    }

    private void setAvidAdSessionContext() {
        callAvidbridge(AvidCommand.setAvidAdSessionContext(this.avidAdSessionContext.getFullContext().toString()));
    }

    private void notifyListener() {
        if (this.listener != null) {
            this.listener.avidBridgeManagerDidInjectAvidJs();
        }
    }

    private void publishPendingEvents() {
        Iterator it = this.pendingEvents.iterator();
        while (it.hasNext()) {
            AvidEvent avidEvent = (AvidEvent) it.next();
            invokePublishVideoEvent(avidEvent.getType(), avidEvent.getData());
        }
        this.pendingEvents.clear();
    }
}
