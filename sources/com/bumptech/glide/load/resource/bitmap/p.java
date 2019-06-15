package com.bumptech.glide.load.resource.bitmap;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import com.bumptech.glide.f.h;
import com.bumptech.glide.f.i;
import com.bumptech.glide.load.engine.a.e;
import com.bumptech.glide.load.engine.n;
import com.bumptech.glide.load.engine.q;

public class p implements n, q<BitmapDrawable> {
    private final Bitmap a;
    private final Resources b;
    private final e c;

    public static p a(Context context, Bitmap bitmap) {
        return a(context.getResources(), com.bumptech.glide.e.b(context).b(), bitmap);
    }

    public static p a(Resources resources, e eVar, Bitmap bitmap) {
        return new p(resources, eVar, bitmap);
    }

    p(Resources resources, e eVar, Bitmap bitmap) {
        this.b = (Resources) h.a((Object) resources);
        this.c = (e) h.a((Object) eVar);
        this.a = (Bitmap) h.a((Object) bitmap);
    }

    public Class<BitmapDrawable> b() {
        return BitmapDrawable.class;
    }

    /* renamed from: f */
    public BitmapDrawable c() {
        return new BitmapDrawable(this.b, this.a);
    }

    public int d() {
        return i.a(this.a);
    }

    public void e() {
        this.c.a(this.a);
    }

    public void a() {
        this.a.prepareToDraw();
    }
}
