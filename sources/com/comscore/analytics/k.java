package com.comscore.analytics;

class k implements Runnable {
    final /* synthetic */ boolean a;
    final /* synthetic */ Core b;

    k(Core core, boolean z) {
        this.b = core;
        this.a = z;
    }

    public void run() {
        if (this.b.isSecure() != this.a) {
            this.b.ai = this.a;
        }
    }
}
