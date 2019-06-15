package com.til.colombia.android.service;

final class g implements Runnable {
    final /* synthetic */ AdListener a;
    final /* synthetic */ ColombiaAdRequest b;
    final /* synthetic */ ItemResponse c;

    g(AdListener adListener, ColombiaAdRequest colombiaAdRequest, ItemResponse itemResponse) {
        this.a = adListener;
        this.b = colombiaAdRequest;
        this.c = itemResponse;
    }

    public final void run() {
        if (this.a != null) {
            this.a.onItemLoaded(this.b, this.c);
        }
    }
}
