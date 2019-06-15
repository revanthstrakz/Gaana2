package com.comscore.analytics;

class ad implements Runnable {
    final /* synthetic */ Core a;

    ad(Core core) {
        this.a = core;
    }

    public void run() {
        if (!this.a.z() && this.a.z.get() > 0) {
            this.a.z.getAndDecrement();
            this.a.n();
        }
    }
}
