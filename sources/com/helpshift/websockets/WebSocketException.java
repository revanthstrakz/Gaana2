package com.helpshift.websockets;

public class WebSocketException extends Exception {
    private static final long serialVersionUID = 1;
    private final WebSocketError a;

    public WebSocketException(WebSocketError webSocketError, String str) {
        super(str);
        this.a = webSocketError;
    }

    public WebSocketException(WebSocketError webSocketError, String str, Throwable th) {
        super(str, th);
        this.a = webSocketError;
    }

    public WebSocketError b() {
        return this.a;
    }
}
