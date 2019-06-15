package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import com.bumptech.glide.load.engine.a.e;
import java.security.MessageDigest;

public class g extends e {
    private static final byte[] b = "com.bumptech.glide.load.resource.bitmap.CenterCrop".getBytes(a);

    /* Access modifiers changed, original: protected */
    public Bitmap a(@NonNull e eVar, @NonNull Bitmap bitmap, int i, int i2) {
        return t.a(eVar, bitmap, i, i2);
    }

    public boolean equals(Object obj) {
        return obj instanceof g;
    }

    public int hashCode() {
        return "com.bumptech.glide.load.resource.bitmap.CenterCrop".hashCode();
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update(b);
    }
}
