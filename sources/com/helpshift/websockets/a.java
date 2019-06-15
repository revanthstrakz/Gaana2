package com.helpshift.websockets;

import java.net.InetSocketAddress;

class a {
    private final String a;
    private final int b;
    private transient String c;

    a(String str, int i) {
        this.a = str;
        this.b = i;
    }

    /* Access modifiers changed, original: 0000 */
    public InetSocketAddress a() {
        return new InetSocketAddress(this.a, this.b);
    }

    /* Access modifiers changed, original: 0000 */
    public String b() {
        return this.a;
    }

    public String toString() {
        if (this.c == null) {
            this.c = String.format("%s:%d", new Object[]{this.a, Integer.valueOf(this.b)});
        }
        return this.c;
    }
}
