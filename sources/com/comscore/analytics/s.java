package com.comscore.analytics;

class s implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ Core b;

    s(Core core, int i) {
        this.b = core;
        this.a = i;
    }

    public void run() {
        this.b.a.setCacheMeasurementExpiry(this.a);
    }
}
