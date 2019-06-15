package com.bumptech.glide.request.a;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import com.bumptech.glide.request.b.d.a;

public abstract class d<Z> extends j<ImageView, Z> implements a {
    @Nullable
    private Animatable b;

    public abstract void a(@Nullable Z z);

    public d(ImageView imageView) {
        super(imageView);
    }

    @Nullable
    public Drawable a() {
        return ((ImageView) this.a).getDrawable();
    }

    public void b(Drawable drawable) {
        ((ImageView) this.a).setImageDrawable(drawable);
    }

    public void onLoadStarted(@Nullable Drawable drawable) {
        super.onLoadStarted(drawable);
        b(null);
        b(drawable);
    }

    public void onLoadFailed(@Nullable Drawable drawable) {
        super.onLoadFailed(drawable);
        b(null);
        b(drawable);
    }

    public void onLoadCleared(@Nullable Drawable drawable) {
        super.onLoadCleared(drawable);
        b(null);
        b(drawable);
    }

    public void onResourceReady(Z z, @Nullable com.bumptech.glide.request.b.d<? super Z> dVar) {
        if (dVar == null || !dVar.a(z, this)) {
            b((Object) z);
        } else {
            c(z);
        }
    }

    public void onStart() {
        if (this.b != null) {
            this.b.start();
        }
    }

    public void onStop() {
        if (this.b != null) {
            this.b.stop();
        }
    }

    private void b(@Nullable Z z) {
        a(z);
        c(z);
    }

    private void c(@Nullable Z z) {
        if (z instanceof Animatable) {
            this.b = (Animatable) z;
            this.b.start();
            return;
        }
        this.b = null;
    }
}
