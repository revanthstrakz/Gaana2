package com.comscore.analytics;

class y implements Runnable {
    final /* synthetic */ Core a;

    y(Core core) {
        this.a = core;
    }

    public void run() {
        this.a.update();
    }
}
