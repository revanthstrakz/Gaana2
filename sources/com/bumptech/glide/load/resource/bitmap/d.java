package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import com.bumptech.glide.f.h;
import com.bumptech.glide.f.i;
import com.bumptech.glide.load.engine.a.e;
import com.bumptech.glide.load.engine.n;
import com.bumptech.glide.load.engine.q;

public class d implements n, q<Bitmap> {
    private final Bitmap a;
    private final e b;

    @Nullable
    public static d a(@Nullable Bitmap bitmap, e eVar) {
        return bitmap == null ? null : new d(bitmap, eVar);
    }

    public d(Bitmap bitmap, e eVar) {
        this.a = (Bitmap) h.a((Object) bitmap, "Bitmap must not be null");
        this.b = (e) h.a((Object) eVar, "BitmapPool must not be null");
    }

    public Class<Bitmap> b() {
        return Bitmap.class;
    }

    /* renamed from: f */
    public Bitmap c() {
        return this.a;
    }

    public int d() {
        return i.a(this.a);
    }

    public void e() {
        this.b.a(this.a);
    }

    public void a() {
        this.a.prepareToDraw();
    }
}
