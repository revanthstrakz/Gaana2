package com.helpshift.websockets;

class StateManager {
    private WebSocketState a = WebSocketState.CREATED;
    private CloseInitiator b = CloseInitiator.NONE;

    enum CloseInitiator {
        NONE,
        SERVER,
        CLIENT
    }

    public WebSocketState a() {
        return this.a;
    }

    public void a(WebSocketState webSocketState) {
        this.a = webSocketState;
    }

    public void a(CloseInitiator closeInitiator) {
        this.a = WebSocketState.CLOSING;
        if (this.b == CloseInitiator.NONE) {
            this.b = closeInitiator;
        }
    }

    public boolean b() {
        return this.b == CloseInitiator.SERVER;
    }
}
