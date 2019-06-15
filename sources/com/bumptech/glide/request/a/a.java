package com.bumptech.glide.request.a;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import com.bumptech.glide.request.c;

public abstract class a<Z> implements i<Z> {
    private c request;

    public void onDestroy() {
    }

    public void onLoadCleared(@Nullable Drawable drawable) {
    }

    public void onLoadFailed(@Nullable Drawable drawable) {
    }

    public void onLoadStarted(@Nullable Drawable drawable) {
    }

    public void onStart() {
    }

    public void onStop() {
    }

    public void setRequest(@Nullable c cVar) {
        this.request = cVar;
    }

    @Nullable
    public c getRequest() {
        return this.request;
    }
}
