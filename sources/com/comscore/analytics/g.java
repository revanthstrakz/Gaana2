package com.comscore.analytics;

class g implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ Core c;

    g(Core core, String str, String str2) {
        this.c = core;
        this.a = str;
        this.b = str2;
    }

    public void run() {
        this.c.b(this.a, this.b);
    }
}
