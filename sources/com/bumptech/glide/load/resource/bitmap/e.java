package com.bumptech.glide.load.resource.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import com.bumptech.glide.load.engine.q;
import com.bumptech.glide.load.i;

public abstract class e implements i<Bitmap> {
    public abstract Bitmap a(@NonNull com.bumptech.glide.load.engine.a.e eVar, @NonNull Bitmap bitmap, int i, int i2);

    public final q<Bitmap> transform(Context context, q<Bitmap> qVar, int i, int i2) {
        if (com.bumptech.glide.f.i.a(i, i2)) {
            com.bumptech.glide.load.engine.a.e b = com.bumptech.glide.e.b(context).b();
            Bitmap bitmap = (Bitmap) qVar.c();
            if (i == Integer.MIN_VALUE) {
                i = bitmap.getWidth();
            }
            if (i2 == Integer.MIN_VALUE) {
                i2 = bitmap.getHeight();
            }
            Bitmap a = a(b, bitmap, i, i2);
            return bitmap.equals(a) ? qVar : d.a(a, b);
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Cannot apply transformation on width: ");
            stringBuilder.append(i);
            stringBuilder.append(" or height: ");
            stringBuilder.append(i2);
            stringBuilder.append(" less than or equal to zero and not Target.SIZE_ORIGINAL");
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }
}
