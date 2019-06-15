package com.comscore.analytics;

class r implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ Core b;

    r(Core core, int i) {
        this.b = core;
        this.a = i;
    }

    public void run() {
        this.b.a.setCacheWaitMinutes(this.a);
    }
}
