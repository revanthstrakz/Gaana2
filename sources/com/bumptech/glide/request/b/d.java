package com.bumptech.glide.request.b;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;

public interface d<R> {

    public interface a {
        @Nullable
        Drawable a();

        void b(Drawable drawable);
    }

    boolean a(R r, a aVar);
}
