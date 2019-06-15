package com.helpshift.websockets;

import javax.net.ssl.SSLSocket;

public class HostnameUnverifiedException extends WebSocketException {
    private static final long serialVersionUID = 1;
    private final SSLSocket a;
    private final String b;

    public HostnameUnverifiedException(SSLSocket sSLSocket, String str) {
        super(WebSocketError.HOSTNAME_UNVERIFIED, String.format("The certificate of the peer%s does not match the expected hostname (%s)", new Object[]{a(sSLSocket), str}));
        this.a = sSLSocket;
        this.b = str;
    }

    private static String a(SSLSocket sSLSocket) {
        try {
            return String.format(" (%s)", new Object[]{sSLSocket.getSession().getPeerPrincipal().toString()});
        } catch (Exception unused) {
            return "";
        }
    }
}
