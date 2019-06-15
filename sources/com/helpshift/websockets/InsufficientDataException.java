package com.helpshift.websockets;

class InsufficientDataException extends WebSocketException {
    private static final long serialVersionUID = 1;
    private final int a;
    private final int b;

    public InsufficientDataException(int i, int i2) {
        super(WebSocketError.INSUFFICENT_DATA, "The end of the stream has been reached unexpectedly.");
        this.a = i;
        this.b = i2;
    }

    public int a() {
        return this.b;
    }
}
