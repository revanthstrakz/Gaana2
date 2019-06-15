package com.comscore.analytics;

class ah implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ Core b;

    ah(Core core, String str) {
        this.b = core;
        this.a = str;
    }

    public void run() {
        this.b.a(this.a);
    }
}
