package com.bumptech.glide.request.a;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.widget.ImageView;

public class c extends d<Drawable> {
    public c(ImageView imageView) {
        super(imageView);
    }

    /* Access modifiers changed, original: protected */
    public void a(@Nullable Drawable drawable) {
        ((ImageView) this.a).setImageDrawable(drawable);
    }
}
