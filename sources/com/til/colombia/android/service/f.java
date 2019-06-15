package com.til.colombia.android.service;

final class f implements Runnable {
    final /* synthetic */ AdListener a;
    final /* synthetic */ bl b;
    final /* synthetic */ Exception c;

    f(AdListener adListener, bl blVar, Exception exception) {
        this.a = adListener;
        this.b = blVar;
        this.c = exception;
    }

    public final void run() {
        if (this.a != null) {
            this.a.onItemRequestFailed((ColombiaAdRequest) this.b, this.c);
        }
    }
}
