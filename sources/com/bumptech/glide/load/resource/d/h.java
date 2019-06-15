package com.bumptech.glide.load.resource.d;

import android.graphics.Bitmap;
import com.bumptech.glide.b.a;
import com.bumptech.glide.load.engine.a.e;
import com.bumptech.glide.load.engine.q;
import com.bumptech.glide.load.f;
import com.bumptech.glide.load.g;
import com.bumptech.glide.load.resource.bitmap.d;

public final class h implements g<a, Bitmap> {
    private final e a;

    public boolean a(a aVar, f fVar) {
        return true;
    }

    public h(e eVar) {
        this.a = eVar;
    }

    public q<Bitmap> a(a aVar, int i, int i2, f fVar) {
        return d.a(aVar.h(), this.a);
    }
}
