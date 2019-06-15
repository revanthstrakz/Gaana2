package com.bumptech.glide.request.a;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class b extends d<Bitmap> {
    public b(ImageView imageView) {
        super(imageView);
    }

    /* Access modifiers changed, original: protected */
    public void a(Bitmap bitmap) {
        ((ImageView) this.a).setImageBitmap(bitmap);
    }
}
