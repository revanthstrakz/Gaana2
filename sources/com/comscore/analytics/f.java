package com.comscore.analytics;

import com.comscore.utils.Utils;
import java.util.HashMap;

class f implements Runnable {
    final /* synthetic */ HashMap a;
    final /* synthetic */ Core b;

    f(Core core, HashMap hashMap) {
        this.b = core;
        this.a = hashMap;
    }

    public void run() {
        this.b.ac.putAll(Utils.mapOfStrings(this.a));
    }
}
