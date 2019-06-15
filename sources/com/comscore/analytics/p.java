package com.comscore.analytics;

class p implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ Core b;

    p(Core core, int i) {
        this.b = core;
        this.a = i;
    }

    public void run() {
        this.b.a.setCacheMaxBatchFiles(this.a);
    }
}
