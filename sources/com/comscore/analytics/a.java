package com.comscore.analytics;

class a implements Runnable {
    final /* synthetic */ Core a;

    a(Core core) {
        this.a = core;
    }

    public void run() {
        this.a.am.resetVisitorId();
    }
}
