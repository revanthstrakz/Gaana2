package com.comscore.analytics;

import com.comscore.utils.Constants;

class m implements Runnable {
    final /* synthetic */ boolean a;
    final /* synthetic */ Core b;

    m(Core core, boolean z) {
        this.b = core;
        this.a = z;
    }

    public void run() {
        Constants.DEBUG = this.a;
    }
}
