package com.til.colombia.android.vast;

import android.graphics.Bitmap;
import android.widget.ImageView;

final class d implements Runnable {
    final /* synthetic */ Bitmap a;
    final /* synthetic */ c b;

    d(c cVar, Bitmap bitmap) {
        this.b = cVar;
        this.a = bitmap;
    }

    public final void run() {
        this.b.a.b.fillImageView((ImageView) this.b.a.a, this.a);
    }
}
