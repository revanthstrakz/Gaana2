package com.comscore.analytics;

class af implements Runnable {
    final /* synthetic */ ApplicationState a;
    final /* synthetic */ ApplicationState b;
    final /* synthetic */ SessionState c;
    final /* synthetic */ SessionState d;
    final /* synthetic */ Core e;

    af(Core core, ApplicationState applicationState, ApplicationState applicationState2, SessionState sessionState, SessionState sessionState2) {
        this.e = core;
        this.a = applicationState;
        this.b = applicationState2;
        this.c = sessionState;
        this.d = sessionState2;
    }

    public void run() {
        Object obj;
        Object obj2 = 1;
        if (this.a != this.b) {
            this.e.a(this.e.y);
            this.e.b(this.b);
            this.e.s();
            this.e.y = this.b;
            obj = 1;
        } else {
            obj = null;
        }
        if (this.c != this.d) {
            this.e.a(this.e.L);
            this.e.b(this.d);
            this.e.t();
            this.e.L = this.d;
        } else {
            obj2 = null;
        }
        if (obj != null) {
            this.e.a(this.a, this.e.y);
        }
        if (obj2 != null) {
            this.e.a(this.c, this.e.L);
        }
    }
}
