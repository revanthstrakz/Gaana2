package com.comscore.analytics;

class o implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ Core b;

    o(Core core, int i) {
        this.b = core;
        this.a = i;
    }

    public void run() {
        this.b.a.setCacheMaxMeasurements(this.a);
    }
}
