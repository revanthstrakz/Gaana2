package com.helpshift.websockets;

import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

class ab {
    private SocketFactory a;
    private SSLSocketFactory b;
    private SSLContext c;

    ab() {
    }

    public SocketFactory a(boolean z) {
        if (z) {
            if (this.c != null) {
                return this.c.getSocketFactory();
            }
            if (this.b != null) {
                return this.b;
            }
            return SSLSocketFactory.getDefault();
        } else if (this.a != null) {
            return this.a;
        } else {
            return SocketFactory.getDefault();
        }
    }
}
