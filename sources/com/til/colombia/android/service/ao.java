package com.til.colombia.android.service;

final class ao implements Runnable {
    final /* synthetic */ am a;

    ao(am amVar) {
        this.a = amVar;
    }

    public final void run() {
        this.a.g.sendMessage(this.a.g.obtainMessage());
    }
}
