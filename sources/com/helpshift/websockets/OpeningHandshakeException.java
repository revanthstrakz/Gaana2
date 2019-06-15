package com.helpshift.websockets;

import java.util.List;
import java.util.Map;

public class OpeningHandshakeException extends WebSocketException {
    private static final long serialVersionUID = 1;
    private final ac a;
    private final Map<String, List<String>> b;
    private final byte[] c;

    OpeningHandshakeException(WebSocketError webSocketError, String str, ac acVar, Map<String, List<String>> map) {
        this(webSocketError, str, acVar, map, null);
    }

    OpeningHandshakeException(WebSocketError webSocketError, String str, ac acVar, Map<String, List<String>> map, byte[] bArr) {
        super(webSocketError, str);
        this.a = acVar;
        this.b = map;
        this.c = bArr;
    }
}
