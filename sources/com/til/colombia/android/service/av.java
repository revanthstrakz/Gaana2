package com.til.colombia.android.service;

import android.graphics.Bitmap;
import android.widget.ImageView;

final class av implements Runnable {
    final /* synthetic */ Bitmap a;
    final /* synthetic */ au b;

    av(au auVar, Bitmap bitmap) {
        this.b = auVar;
        this.a = bitmap;
    }

    public final void run() {
        ((ImageView) this.b.a.a.vastResourceView).setImageBitmap(this.a);
    }
}
