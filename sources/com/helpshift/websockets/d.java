package com.helpshift.websockets;

class d implements r {
    private long a;

    d() {
    }

    public byte[] a() {
        this.a = Math.max(this.a + 1, 1);
        return p.a(String.valueOf(this.a));
    }
}
