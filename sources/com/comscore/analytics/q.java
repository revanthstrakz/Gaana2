package com.comscore.analytics;

class q implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ Core b;

    q(Core core, int i) {
        this.b = core;
        this.a = i;
    }

    public void run() {
        this.b.a.setCacheMaxPosts(this.a);
    }
}
