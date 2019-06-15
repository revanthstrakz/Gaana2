package com.helpshift.websockets;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class m {
    private final ae a;

    public m(ae aeVar) {
        this.a = aeVar;
    }

    public Map<String, List<String>> a(ai aiVar, String str) throws WebSocketException {
        ac a = a(aiVar);
        Map b = b(aiVar);
        a(a, b, aiVar);
        a(a, b);
        b(a, b);
        a(a, b, str);
        c(a, b);
        d(a, b);
        return b;
    }

    private ac a(ai aiVar) throws WebSocketException {
        WebSocketError webSocketError;
        StringBuilder stringBuilder;
        try {
            String a = aiVar.a();
            if (a == null || a.length() == 0) {
                throw new WebSocketException(WebSocketError.STATUS_LINE_EMPTY, "The status line of the opening handshake response is empty.");
            }
            try {
                return new ac(a);
            } catch (Exception unused) {
                webSocketError = WebSocketError.STATUS_LINE_BAD_FORMAT;
                stringBuilder = new StringBuilder();
                stringBuilder.append("The status line of the opening handshake response is badly formatted. The status line is: ");
                stringBuilder.append(a);
                throw new WebSocketException(webSocketError, stringBuilder.toString());
            }
        } catch (IOException e) {
            webSocketError = WebSocketError.OPENING_HANDSHAKE_RESPONSE_FAILURE;
            stringBuilder = new StringBuilder();
            stringBuilder.append("Failed to read an opening handshake response from the server: ");
            stringBuilder.append(e.getMessage());
            throw new WebSocketException(webSocketError, stringBuilder.toString(), e);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0043  */
    private java.util.Map<java.lang.String, java.util.List<java.lang.String>> b(com.helpshift.websockets.ai r6) throws com.helpshift.websockets.WebSocketException {
        /*
        r5 = this;
        r0 = new java.util.TreeMap;
        r1 = java.lang.String.CASE_INSENSITIVE_ORDER;
        r0.<init>(r1);
        r1 = 0;
    L_0x0008:
        r2 = r6.a();	 Catch:{ IOException -> 0x004b }
        if (r2 == 0) goto L_0x0041;
    L_0x000e:
        r3 = r2.length();
        if (r3 != 0) goto L_0x0015;
    L_0x0014:
        goto L_0x0041;
    L_0x0015:
        r3 = 0;
        r3 = r2.charAt(r3);
        r4 = 32;
        if (r3 == r4) goto L_0x0032;
    L_0x001e:
        r4 = 9;
        if (r3 != r4) goto L_0x0023;
    L_0x0022:
        goto L_0x0032;
    L_0x0023:
        if (r1 == 0) goto L_0x002c;
    L_0x0025:
        r1 = r1.toString();
        r5.a(r0, r1);
    L_0x002c:
        r1 = new java.lang.StringBuilder;
        r1.<init>(r2);
        goto L_0x0008;
    L_0x0032:
        if (r1 != 0) goto L_0x0035;
    L_0x0034:
        goto L_0x0008;
    L_0x0035:
        r3 = "^[ \t]+";
        r4 = " ";
        r2 = r2.replaceAll(r3, r4);
        r1.append(r2);
        goto L_0x0008;
    L_0x0041:
        if (r1 == 0) goto L_0x004a;
    L_0x0043:
        r6 = r1.toString();
        r5.a(r0, r6);
    L_0x004a:
        return r0;
    L_0x004b:
        r6 = move-exception;
        r0 = new com.helpshift.websockets.WebSocketException;
        r1 = com.helpshift.websockets.WebSocketError.HTTP_HEADER_FAILURE;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "An error occurred while HTTP header section was being read: ";
        r2.append(r3);
        r3 = r6.getMessage();
        r2.append(r3);
        r2 = r2.toString();
        r0.<init>(r1, r2, r6);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.websockets.m.b(com.helpshift.websockets.ai):java.util.Map");
    }

    private void a(Map<String, List<String>> map, String str) {
        String[] split = str.split(":", 2);
        if (split.length >= 2) {
            String trim = split[0].trim();
            str = split[1].trim();
            List list = (List) map.get(trim);
            if (list == null) {
                list = new ArrayList();
                map.put(trim, list);
            }
            list.add(str);
        }
    }

    private void a(ac acVar, Map<String, List<String>> map, ai aiVar) throws WebSocketException {
        if (acVar.a() != 101) {
            byte[] a = a((Map) map, aiVar);
            WebSocketError webSocketError = WebSocketError.NOT_SWITCHING_PROTOCOLS;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("The status code of the opening handshake response is not '101 Switching Protocols'. The status line is: ");
            stringBuilder.append(acVar);
            throw new OpeningHandshakeException(webSocketError, stringBuilder.toString(), acVar, map, a);
        }
    }

    private byte[] a(Map<String, List<String>> map, ai aiVar) {
        int a = a((Map) map);
        if (a <= 0) {
            return null;
        }
        try {
            byte[] bArr = new byte[a];
            aiVar.a(bArr, a);
            return bArr;
        } catch (Throwable unused) {
            return null;
        }
    }

    private int a(Map<String, List<String>> map) {
        try {
            return Integer.parseInt((String) ((List) map.get("Content-Length")).get(0));
        } catch (Exception unused) {
            return -1;
        }
    }

    private void a(ac acVar, Map<String, List<String>> map) throws WebSocketException {
        List<String> list = (List) map.get("Upgrade");
        if (list == null || list.size() == 0) {
            throw new OpeningHandshakeException(WebSocketError.NO_UPGRADE_HEADER, "The opening handshake response does not contain 'Upgrade' header.", acVar, map);
        }
        for (String split : list) {
            String[] split2 = split.split("\\s*,\\s*");
            int length = split2.length;
            int i = 0;
            while (i < length) {
                if (!"websocket".equalsIgnoreCase(split2[i])) {
                    i++;
                } else {
                    return;
                }
            }
        }
        throw new OpeningHandshakeException(WebSocketError.NO_WEBSOCKET_IN_UPGRADE_HEADER, "'websocket' was not found in 'Upgrade' header.", acVar, map);
    }

    private void b(ac acVar, Map<String, List<String>> map) throws WebSocketException {
        List<String> list = (List) map.get("Connection");
        if (list == null || list.size() == 0) {
            throw new OpeningHandshakeException(WebSocketError.NO_CONNECTION_HEADER, "The opening handshake response does not contain 'Connection' header.", acVar, map);
        }
        for (String split : list) {
            String[] split2 = split.split("\\s*,\\s*");
            int length = split2.length;
            int i = 0;
            while (i < length) {
                if (!"Upgrade".equalsIgnoreCase(split2[i])) {
                    i++;
                } else {
                    return;
                }
            }
        }
        throw new OpeningHandshakeException(WebSocketError.NO_UPGRADE_IN_CONNECTION_HEADER, "'Upgrade' was not found in 'Connection' header.", acVar, map);
    }

    private void a(ac acVar, Map<String, List<String>> map, String str) throws WebSocketException {
        List list = (List) map.get("Sec-WebSocket-Accept");
        if (list == null) {
            throw new OpeningHandshakeException(WebSocketError.NO_SEC_WEBSOCKET_ACCEPT_HEADER, "The opening handshake response does not contain 'Sec-WebSocket-Accept' header.", acVar, map);
        }
        String str2 = (String) list.get(0);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append("258EAFA5-E914-47DA-95CA-C5AB0DC85B11");
        try {
            if (!b.a(MessageDigest.getInstance("SHA-1").digest(p.a(stringBuilder.toString()))).equals(str2)) {
                throw new OpeningHandshakeException(WebSocketError.UNEXPECTED_SEC_WEBSOCKET_ACCEPT_HEADER, "The value of 'Sec-WebSocket-Accept' header is different from the expected one.", acVar, map);
            }
        } catch (Exception unused) {
        }
    }

    private void c(ac acVar, Map<String, List<String>> map) throws WebSocketException {
        List<String> list = (List) map.get("Sec-WebSocket-Extensions");
        if (list != null && list.size() != 0) {
            List arrayList = new ArrayList();
            for (String split : list) {
                String[] split2 = split.split("\\s*,\\s*");
                int length = split2.length;
                int i = 0;
                while (i < length) {
                    String str = split2[i];
                    af a = af.a(str);
                    WebSocketError webSocketError;
                    StringBuilder stringBuilder;
                    if (a == null) {
                        webSocketError = WebSocketError.EXTENSION_PARSE_ERROR;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("The value in 'Sec-WebSocket-Extensions' failed to be parsed: ");
                        stringBuilder.append(str);
                        throw new OpeningHandshakeException(webSocketError, stringBuilder.toString(), acVar, map);
                    }
                    str = a.b();
                    if (this.a.m().d(str)) {
                        a.a();
                        arrayList.add(a);
                        i++;
                    } else {
                        webSocketError = WebSocketError.UNSUPPORTED_EXTENSION;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("The extension contained in the Sec-WebSocket-Extensions header is not supported: ");
                        stringBuilder.append(str);
                        throw new OpeningHandshakeException(webSocketError, stringBuilder.toString(), acVar, map);
                    }
                }
            }
            a(acVar, (Map) map, arrayList);
            this.a.a(arrayList);
        }
    }

    private void a(ac acVar, Map<String, List<String>> map, List<af> list) throws WebSocketException {
        af afVar = null;
        for (af afVar2 : list) {
            if (afVar2 instanceof s) {
                if (afVar == null) {
                    afVar = afVar2;
                } else {
                    throw new OpeningHandshakeException(WebSocketError.EXTENSIONS_CONFLICT, String.format("'%s' extension and '%s' extension conflict with each other.", new Object[]{afVar.b(), afVar2.b()}), acVar, map);
                }
            }
        }
    }

    private void d(ac acVar, Map<String, List<String>> map) throws WebSocketException {
        List list = (List) map.get("Sec-WebSocket-Protocol");
        if (list != null) {
            String str = (String) list.get(0);
            if (str != null && str.length() != 0) {
                if (this.a.m().b(str)) {
                    this.a.d(str);
                    return;
                }
                WebSocketError webSocketError = WebSocketError.UNSUPPORTED_PROTOCOL;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("The protocol contained in the Sec-WebSocket-Protocol header is not supported: ");
                stringBuilder.append(str);
                throw new OpeningHandshakeException(webSocketError, stringBuilder.toString(), acVar, map);
            }
        }
    }
}
