package com.comscore.utils;

class d implements Runnable {
    final /* synthetic */ Storage a;

    d(Storage storage) {
        this.a = storage;
    }

    public void run() {
        this.a.b();
        this.a.c();
    }
}
