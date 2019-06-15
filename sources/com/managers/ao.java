package com.managers;

import java.util.ArrayList;

final /* synthetic */ class ao implements Runnable {
    private final an a;
    private final ArrayList b;

    ao(an anVar, ArrayList arrayList) {
        this.a = anVar;
        this.b = arrayList;
    }

    public void run() {
        this.a.a(this.b);
    }
}
