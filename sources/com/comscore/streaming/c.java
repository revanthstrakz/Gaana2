package com.comscore.streaming;

class c implements Runnable {
    final /* synthetic */ StreamSense a;

    c(StreamSense streamSense) {
        this.a = streamSense;
    }

    public void run() {
        this.a.b();
    }
}
