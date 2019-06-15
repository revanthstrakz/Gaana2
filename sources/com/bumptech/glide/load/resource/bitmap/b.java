package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import com.bumptech.glide.load.EncodeStrategy;
import com.bumptech.glide.load.engine.a.e;
import com.bumptech.glide.load.engine.q;
import com.bumptech.glide.load.f;
import com.bumptech.glide.load.h;
import java.io.File;

public class b implements h<BitmapDrawable> {
    private final e a;
    private final h<Bitmap> b;

    public b(e eVar, h<Bitmap> hVar) {
        this.a = eVar;
        this.b = hVar;
    }

    public boolean a(q<BitmapDrawable> qVar, File file, f fVar) {
        return this.b.a(new d(((BitmapDrawable) qVar.c()).getBitmap(), this.a), file, fVar);
    }

    public EncodeStrategy a(f fVar) {
        return this.b.a(fVar);
    }
}
