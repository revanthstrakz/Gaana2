package com.comscore.analytics;

import com.comscore.applications.EventType;
import java.util.HashMap;

class z implements Runnable {
    final /* synthetic */ EventType a;
    final /* synthetic */ HashMap b;
    final /* synthetic */ Core c;

    z(Core core, EventType eventType, HashMap hashMap) {
        this.c = core;
        this.a = eventType;
        this.b = hashMap;
    }

    public void run() {
        this.c.a(this.a, this.b);
    }
}
