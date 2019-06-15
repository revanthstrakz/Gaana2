package com.helpshift.websockets;

import java.io.IOException;
import java.net.Socket;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

class aa {
    private Socket a;
    private final a b;
    private final int c;
    private final x d;
    private final SSLSocketFactory e;
    private final String f;
    private final int g;

    aa(Socket socket, a aVar, int i) {
        this(socket, aVar, i, null, null, null, 0);
    }

    aa(Socket socket, a aVar, int i, x xVar, SSLSocketFactory sSLSocketFactory, String str, int i2) {
        this.a = socket;
        this.b = aVar;
        this.c = i;
        this.d = xVar;
        this.e = sSLSocketFactory;
        this.f = str;
        this.g = i2;
    }

    public Socket a() {
        return this.a;
    }

    public void b() throws WebSocketException {
        try {
            d();
        } catch (WebSocketException e) {
            try {
                this.a.close();
            } catch (IOException unused) {
            }
            throw e;
        }
    }

    private void d() throws WebSocketException {
        int i = this.d != null ? 1 : 0;
        try {
            this.a.connect(this.b.a(), this.c);
            if (this.a instanceof SSLSocket) {
                a((SSLSocket) this.a, this.b.b());
            }
            if (i != 0) {
                e();
            }
        } catch (IOException e) {
            String str = "Failed to connect to %s'%s': %s";
            Object[] objArr = new Object[3];
            objArr[0] = i != 0 ? "the proxy " : "";
            objArr[1] = this.b;
            objArr[2] = e.getMessage();
            throw new WebSocketException(WebSocketError.SOCKET_CONNECT_ERROR, String.format(str, objArr), e);
        }
    }

    private void a(SSLSocket sSLSocket, String str) throws HostnameUnverifiedException {
        if (!q.a.verify(str, sSLSocket.getSession())) {
            throw new HostnameUnverifiedException(sSLSocket, str);
        }
    }

    private void e() throws WebSocketException {
        try {
            this.d.a();
            if (this.e != null) {
                try {
                    this.a = this.e.createSocket(this.a, this.f, this.g, true);
                    try {
                        ((SSLSocket) this.a).startHandshake();
                        if (this.a instanceof SSLSocket) {
                            a((SSLSocket) this.a, this.d.b());
                        }
                    } catch (IOException e) {
                        throw new WebSocketException(WebSocketError.SSL_HANDSHAKE_ERROR, String.format("SSL handshake with the WebSocket endpoint (%s) failed: %s", new Object[]{this.b, e.getMessage()}), e);
                    }
                } catch (IOException e2) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Failed to overlay an existing socket: ");
                    stringBuilder.append(e2.getMessage());
                    throw new WebSocketException(WebSocketError.SOCKET_OVERLAY_ERROR, stringBuilder.toString(), e2);
                }
            }
        } catch (IOException e3) {
            throw new WebSocketException(WebSocketError.PROXY_HANDSHAKE_ERROR, String.format("Handshake with the proxy server (%s) failed: %s", new Object[]{this.b, e3.getMessage()}), e3);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void c() {
        try {
            this.a.close();
        } catch (Throwable unused) {
        }
    }
}
