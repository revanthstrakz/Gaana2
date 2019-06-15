package com.comscore.analytics;

class l implements Runnable {
    final /* synthetic */ Core a;

    l(Core core) {
        this.a = core;
    }

    public void run() {
        if (!this.a.z()) {
            if (this.a.Q < 0) {
                this.a.Q = 0;
            }
            this.a.A.getAndIncrement();
            this.a.n();
        }
    }
}
