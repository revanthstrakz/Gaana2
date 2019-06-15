package com.comscore.analytics;

class n implements Runnable {
    final /* synthetic */ boolean a;
    final /* synthetic */ Core b;

    n(Core core, boolean z) {
        this.b = core;
        this.a = z;
    }

    public void run() {
        this.b.v = this.a;
    }
}
