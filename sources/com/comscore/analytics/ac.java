package com.comscore.analytics;

class ac implements Runnable {
    final /* synthetic */ Core a;

    ac(Core core) {
        this.a = core;
    }

    public void run() {
        if (!this.a.z()) {
            this.a.z.getAndIncrement();
            this.a.n();
        }
    }
}
