package com.helpshift.websockets;

abstract class al extends Thread {
    protected final ae a;
    private final ThreadType b;

    public abstract void a();

    al(String str, ae aeVar, ThreadType threadType) {
        super(str);
        this.a = aeVar;
        this.b = threadType;
    }

    public void run() {
        o l = this.a.l();
        if (l != null) {
            l.b(this.b, (Thread) this);
        }
        a();
        if (l != null) {
            l.c(this.b, this);
        }
    }

    public void b() {
        o l = this.a.l();
        if (l != null) {
            l.a(this.b, (Thread) this);
        }
    }
}
