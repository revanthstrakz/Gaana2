package com.bumptech.glide.load.resource.e;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import com.bumptech.glide.f.h;
import com.bumptech.glide.load.engine.a.e;
import com.bumptech.glide.load.engine.q;
import com.bumptech.glide.load.f;
import com.bumptech.glide.load.resource.bitmap.p;

public class b implements d<Bitmap, BitmapDrawable> {
    private final Resources a;
    private final e b;

    public b(Resources resources, e eVar) {
        this.a = (Resources) h.a((Object) resources);
        this.b = (e) h.a((Object) eVar);
    }

    public q<BitmapDrawable> a(q<Bitmap> qVar, f fVar) {
        return p.a(this.a, this.b, (Bitmap) qVar.c());
    }
}
