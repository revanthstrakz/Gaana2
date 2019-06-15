package com.bumptech.glide.request.a;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import com.bumptech.glide.request.b.d;
import com.bumptech.glide.request.c;

public interface i<R> extends com.bumptech.glide.manager.i {
    @Nullable
    c getRequest();

    void getSize(h hVar);

    void onLoadCleared(@Nullable Drawable drawable);

    void onLoadFailed(@Nullable Drawable drawable);

    void onLoadStarted(@Nullable Drawable drawable);

    void onResourceReady(R r, d<? super R> dVar);

    void removeCallback(h hVar);

    void setRequest(@Nullable c cVar);
}
