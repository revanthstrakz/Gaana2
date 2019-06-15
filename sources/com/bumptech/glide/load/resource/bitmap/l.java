package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.Log;
import com.bumptech.glide.load.engine.a.e;
import com.bumptech.glide.load.engine.a.f;
import com.bumptech.glide.load.engine.q;
import java.util.concurrent.locks.Lock;

final class l {
    private static final e a = new f() {
        public void a(Bitmap bitmap) {
        }
    };

    @Nullable
    static q<Bitmap> a(e eVar, Drawable drawable, int i, int i2) {
        Bitmap bitmap;
        drawable = drawable.getCurrent();
        Object obj = null;
        if (drawable instanceof BitmapDrawable) {
            bitmap = ((BitmapDrawable) drawable).getBitmap();
        } else if (drawable instanceof Animatable) {
            bitmap = null;
        } else {
            bitmap = b(eVar, drawable, i, i2);
            obj = 1;
        }
        if (obj == null) {
            eVar = a;
        }
        return d.a(bitmap, eVar);
    }

    @Nullable
    private static Bitmap b(e eVar, Drawable drawable, int i, int i2) {
        StringBuilder stringBuilder;
        if (i == Integer.MIN_VALUE && drawable.getIntrinsicWidth() <= 0) {
            if (Log.isLoggable("DrawableToBitmap", 5)) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("Unable to draw ");
                stringBuilder.append(drawable);
                stringBuilder.append(" to Bitmap with Target.SIZE_ORIGINAL because the Drawable has no intrinsic width");
                Log.w("DrawableToBitmap", stringBuilder.toString());
            }
            return null;
        } else if (i2 != Integer.MIN_VALUE || drawable.getIntrinsicHeight() > 0) {
            if (drawable.getIntrinsicWidth() > 0) {
                i = drawable.getIntrinsicWidth();
            }
            if (drawable.getIntrinsicHeight() > 0) {
                i2 = drawable.getIntrinsicHeight();
            }
            Lock a = t.a();
            a.lock();
            Bitmap a2 = eVar.a(i, i2, Config.ARGB_8888);
            try {
                Canvas canvas = new Canvas(a2);
                drawable.setBounds(0, 0, i, i2);
                drawable.draw(canvas);
                canvas.setBitmap(null);
                return a2;
            } finally {
                a.unlock();
            }
        } else {
            if (Log.isLoggable("DrawableToBitmap", 5)) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("Unable to draw ");
                stringBuilder.append(drawable);
                stringBuilder.append(" to Bitmap with Target.SIZE_ORIGINAL because the Drawable has no intrinsic height");
                Log.w("DrawableToBitmap", stringBuilder.toString());
            }
            return null;
        }
    }
}
