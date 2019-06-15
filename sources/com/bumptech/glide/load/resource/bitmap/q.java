package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import com.bumptech.glide.load.f;
import com.bumptech.glide.load.g;
import com.bumptech.glide.load.resource.b.e;
import java.io.IOException;

public class q implements g<Uri, Bitmap> {
    private final e a;
    private final com.bumptech.glide.load.engine.a.e b;

    public q(e eVar, com.bumptech.glide.load.engine.a.e eVar2) {
        this.a = eVar;
        this.b = eVar2;
    }

    public boolean a(Uri uri, f fVar) throws IOException {
        return "android.resource".equals(uri.getScheme());
    }

    @Nullable
    public com.bumptech.glide.load.engine.q<Bitmap> a(Uri uri, int i, int i2, f fVar) throws IOException {
        return l.a(this.b, (Drawable) this.a.a(uri, i, i2, fVar).c(), i, i2);
    }
}
