package com.comscore.analytics;

import com.comscore.utils.Date;

class ae implements Runnable {
    final /* synthetic */ Core a;

    ae(Core core) {
        this.a = core;
    }

    public void run() {
        if (!this.a.z()) {
            if (this.a.Q < 0) {
                this.a.Q = 0;
            }
            if (this.a.R < 0) {
                this.a.R = 0;
            }
            this.a.W = Date.unixTime();
            Core core = this.a;
            core.V++;
            if (this.a.L != SessionState.ACTIVE_USER) {
                this.a.n();
            } else {
                this.a.o();
            }
        }
    }
}
