package com.til.colombia.android.service;

import android.graphics.Bitmap;

final class cf implements Runnable {
    final /* synthetic */ Bitmap a;
    final /* synthetic */ ce b;

    cf(ce ceVar, Bitmap bitmap) {
        this.b = ceVar;
        this.a = bitmap;
    }

    public final void run() {
        this.b.a.imgBmp = this.a;
        if (this.b.a.leadImg != null) {
            this.b.a.leadImg.setImageBitmap(this.b.a.imgBmp);
        }
    }
}
