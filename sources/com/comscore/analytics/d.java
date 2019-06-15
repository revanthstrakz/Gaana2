package com.comscore.analytics;

class d implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ Core b;

    d(Core core, String str) {
        this.b = core;
        this.a = str;
    }

    public void run() {
        this.b.b(this.a);
    }
}
