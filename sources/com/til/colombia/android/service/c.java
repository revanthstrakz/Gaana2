package com.til.colombia.android.service;

final class c implements Runnable {
    final /* synthetic */ b a;

    c(b bVar) {
        this.a = bVar;
    }

    public final void run() {
        if (this.a.b != null) {
            this.a.b.onItemLoaded((ColombiaAdRequest) this.a.a, this.a.c);
        }
    }
}
