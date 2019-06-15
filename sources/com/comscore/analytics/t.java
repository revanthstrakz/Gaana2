package com.comscore.analytics;

class t implements Runnable {
    final /* synthetic */ long a;
    final /* synthetic */ Core b;

    t(Core core, long j) {
        this.b = core;
        this.a = j;
    }

    public void run() {
        this.b.af = this.a;
        if (this.b.d != null) {
            this.b.d.update();
        }
    }
}
