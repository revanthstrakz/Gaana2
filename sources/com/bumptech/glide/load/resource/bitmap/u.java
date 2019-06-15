package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import com.bumptech.glide.load.engine.a.f;
import com.bumptech.glide.load.engine.q;
import com.bumptech.glide.load.g;
import java.io.IOException;

public final class u implements g<Bitmap, Bitmap> {
    private static final f a = new f();

    public boolean a(Bitmap bitmap, com.bumptech.glide.load.f fVar) throws IOException {
        return true;
    }

    @Nullable
    public q<Bitmap> a(Bitmap bitmap, int i, int i2, com.bumptech.glide.load.f fVar) throws IOException {
        return new d(bitmap, a);
    }
}
