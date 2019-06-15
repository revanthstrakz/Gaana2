package com.til.colombia.android.commons.a;

import android.graphics.Bitmap;
import android.util.LruCache;

final class i extends LruCache<String, Bitmap> {
    i(int i) {
        super(i);
    }

    /* Access modifiers changed, original: protected|final|synthetic */
    public final /* synthetic */ int sizeOf(Object obj, Object obj2) {
        return ((Bitmap) obj2).getByteCount() / 1024;
    }

    private static int a(Bitmap bitmap) {
        return bitmap.getByteCount() / 1024;
    }
}
