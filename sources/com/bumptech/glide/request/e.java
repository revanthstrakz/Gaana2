package com.bumptech.glide.request;

import android.support.annotation.Nullable;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.a.i;

public interface e<R> {
    boolean onLoadFailed(@Nullable GlideException glideException, Object obj, i<R> iVar, boolean z);

    boolean onResourceReady(R r, Object obj, i<R> iVar, DataSource dataSource, boolean z);
}
