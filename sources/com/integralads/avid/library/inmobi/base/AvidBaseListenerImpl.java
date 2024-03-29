package com.integralads.avid.library.inmobi.base;

import com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSession;
import com.integralads.avid.library.inmobi.session.internal.jsbridge.AvidBridgeManager;

public abstract class AvidBaseListenerImpl {
    private InternalAvidAdSession avidAdSession;
    private AvidBridgeManager avidBridgeManager;

    public AvidBaseListenerImpl(InternalAvidAdSession internalAvidAdSession, AvidBridgeManager avidBridgeManager) {
        this.avidAdSession = internalAvidAdSession;
        this.avidBridgeManager = avidBridgeManager;
    }

    public void destroy() {
        this.avidAdSession = null;
        this.avidBridgeManager = null;
    }

    public InternalAvidAdSession getAvidAdSession() {
        return this.avidAdSession;
    }

    public AvidBridgeManager getAvidBridgeManager() {
        return this.avidBridgeManager;
    }

    public void assertSessionIsNotEnded() {
        if (this.avidAdSession == null) {
            throw new IllegalStateException("The AVID ad session is ended. Please ensure you are not recording events after the session has ended.");
        }
    }
}
