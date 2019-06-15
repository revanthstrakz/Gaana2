package com.bumptech.glide.load.resource.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.bumptech.glide.e;
import com.bumptech.glide.load.engine.q;
import com.bumptech.glide.load.i;
import java.security.MessageDigest;

public class m implements i<Drawable> {
    private final i<Bitmap> b;
    private final boolean c;

    public i<BitmapDrawable> a() {
        return this;
    }

    public m(i<Bitmap> iVar, boolean z) {
        this.b = iVar;
        this.c = z;
    }

    public q<Drawable> transform(Context context, q<Drawable> qVar, int i, int i2) {
        Drawable drawable = (Drawable) qVar.c();
        q a = l.a(e.b(context).b(), drawable, i, i2);
        if (a != null) {
            q transform = this.b.transform(context, a, i, i2);
            if (!transform.equals(a)) {
                return a(context, (Bitmap) transform.c());
            }
            transform.e();
            return qVar;
        } else if (!this.c) {
            return qVar;
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Unable to convert ");
            stringBuilder.append(drawable);
            stringBuilder.append(" to a Bitmap");
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    private q<Drawable> a(Context context, Bitmap bitmap) {
        return p.a(context, bitmap);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof m)) {
            return false;
        }
        return this.b.equals(((m) obj).b);
    }

    public int hashCode() {
        return this.b.hashCode();
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) {
        this.b.updateDiskCacheKey(messageDigest);
    }
}
