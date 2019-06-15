package com.comscore.analytics;

class h implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ Core b;

    h(Core core, String str) {
        this.b = core;
        this.a = str;
    }

    public void run() {
        this.b.d(this.a);
    }
}
