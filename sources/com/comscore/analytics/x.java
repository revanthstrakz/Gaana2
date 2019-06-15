package com.comscore.analytics;

class x implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ boolean b;
    final /* synthetic */ Core c;

    x(Core core, int i, boolean z) {
        this.c = core;
        this.a = i;
        this.b = z;
    }

    public void run() {
        this.c.a(this.a, this.b);
    }
}
