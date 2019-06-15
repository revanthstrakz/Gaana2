package com.comscore.analytics;

class w implements Runnable {
    final /* synthetic */ Core a;

    w(Core core) {
        this.a = core;
    }

    public void run() {
        if (!this.a.z() && this.a.A.get() > 0) {
            this.a.A.getAndDecrement();
            this.a.n();
        }
    }
}
