package com.comscore.analytics;

import com.comscore.utils.TransmissionMode;

class i implements Runnable {
    final /* synthetic */ TransmissionMode a;
    final /* synthetic */ Core b;

    i(Core core, TransmissionMode transmissionMode) {
        this.b = core;
        this.a = transmissionMode;
    }

    public void run() {
        this.b.a(this.a);
    }
}
