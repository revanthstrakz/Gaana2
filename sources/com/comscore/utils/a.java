package com.comscore.utils;

class a implements Runnable {
    final /* synthetic */ ConnectivityChangeReceiver a;

    a(ConnectivityChangeReceiver connectivityChangeReceiver) {
        this.a = connectivityChangeReceiver;
    }

    public void run() {
        this.a.b(false);
    }
}
