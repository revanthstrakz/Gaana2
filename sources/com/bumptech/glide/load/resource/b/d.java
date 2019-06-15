package com.bumptech.glide.load.resource.b;

import android.graphics.drawable.Drawable;
import com.bumptech.glide.load.engine.q;

final class d extends b<Drawable> {
    public void e() {
    }

    public static q<Drawable> a(Drawable drawable) {
        return new d(drawable);
    }

    private d(Drawable drawable) {
        super(drawable);
    }

    public Class<Drawable> b() {
        return this.a.getClass();
    }

    public int d() {
        return Math.max(1, (this.a.getIntrinsicWidth() * this.a.getIntrinsicHeight()) * 4);
    }
}
