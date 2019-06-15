package com.comscore.analytics;

class v implements Runnable {
    final /* synthetic */ Core a;

    v(Core core) {
        this.a = core;
    }

    public void run() {
        this.a.a.flush();
    }
}
