package com.comscore.analytics;

class e implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ Core b;

    e(Core core, String str) {
        this.b = core;
        this.a = str;
    }

    public void run() {
        this.b.c(this.a);
    }
}
