package com.comscore.analytics;

class b implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ Core b;

    b(Core core, String str) {
        this.b = core;
        this.a = str;
    }

    public void run() {
        this.b.a.setUrl(this.a);
    }
}
