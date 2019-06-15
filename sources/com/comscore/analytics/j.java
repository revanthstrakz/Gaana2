package com.comscore.analytics;

import com.comscore.utils.TransmissionMode;

class j implements Runnable {
    final /* synthetic */ TransmissionMode a;
    final /* synthetic */ Core b;

    j(Core core, TransmissionMode transmissionMode) {
        this.b = core;
        this.a = transmissionMode;
    }

    public void run() {
        this.b.b(this.a);
    }
}
