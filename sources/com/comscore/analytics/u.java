package com.comscore.analytics;

class u implements Runnable {
    final /* synthetic */ String[] a;
    final /* synthetic */ Core b;

    u(Core core, String[] strArr) {
        this.b = core;
        this.a = strArr;
    }

    public void run() {
        this.b.al = this.a;
    }
}
