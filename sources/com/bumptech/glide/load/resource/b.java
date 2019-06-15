package com.bumptech.glide.load.resource;

import android.content.Context;
import com.bumptech.glide.load.engine.q;
import com.bumptech.glide.load.i;
import java.security.MessageDigest;

public final class b<T> implements i<T> {
    private static final i<?> b = new b();

    public q<T> transform(Context context, q<T> qVar, int i, int i2) {
        return qVar;
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) {
    }

    public static <T> b<T> a() {
        return (b) b;
    }

    private b() {
    }
}
