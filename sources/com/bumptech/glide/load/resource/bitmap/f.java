package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.f.a;
import com.bumptech.glide.load.engine.q;
import com.bumptech.glide.load.g;
import java.io.IOException;
import java.nio.ByteBuffer;

public class f implements g<ByteBuffer, Bitmap> {
    private final k a;

    public f(k kVar) {
        this.a = kVar;
    }

    public boolean a(ByteBuffer byteBuffer, com.bumptech.glide.load.f fVar) throws IOException {
        return this.a.a(byteBuffer);
    }

    public q<Bitmap> a(ByteBuffer byteBuffer, int i, int i2, com.bumptech.glide.load.f fVar) throws IOException {
        return this.a.a(a.b(byteBuffer), i, i2, fVar);
    }
}
