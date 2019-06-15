package com.comscore.analytics;

class c implements Runnable {
    final /* synthetic */ boolean a;
    final /* synthetic */ Core b;

    c(Core core, boolean z) {
        this.b = core;
        this.a = z;
    }

    public void run() {
        this.b.ae = this.a;
    }
}
