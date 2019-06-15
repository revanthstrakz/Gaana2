package com.google.ads.mediation.inmobi;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

class InMobiMemoryCache {
    private static final String TAG = "MemoryCache";
    private final Map<String, Drawable> mCache = Collections.synchronizedMap(new LinkedHashMap(10, 1.5f, true));
    private long mLimit = 1000000;
    private long mSize = 0;

    InMobiMemoryCache() {
        setLimit(Runtime.getRuntime().maxMemory() / 4);
    }

    private void setLimit(long j) {
        this.mLimit = j;
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("MemoryCache will use up to ");
        stringBuilder.append((((double) this.mLimit) / 1024.0d) / 1024.0d);
        stringBuilder.append("MB");
        Log.i(str, stringBuilder.toString());
    }

    public Drawable get(String str) {
        try {
            if (this.mCache.containsKey(str)) {
                return (Drawable) this.mCache.get(str);
            }
            return null;
        } catch (NullPointerException e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void put(String str, Drawable drawable) {
        try {
            if (this.mCache.containsKey(str)) {
                this.mSize -= getSizeInBytes(((BitmapDrawable) this.mCache.get(str)).getBitmap());
            }
            this.mCache.put(str, drawable);
            this.mSize += getSizeInBytes(((BitmapDrawable) drawable).getBitmap());
            checkSize();
            Log.d(TAG, "Drawable used from cache");
        } catch (Throwable th) {
            ThrowableExtension.printStackTrace(th);
        }
    }

    private void checkSize() {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("cache size=");
        stringBuilder.append(this.mSize);
        stringBuilder.append(" length=");
        stringBuilder.append(this.mCache.size());
        Log.i(str, stringBuilder.toString());
        if (this.mSize > this.mLimit) {
            Iterator it = this.mCache.entrySet().iterator();
            while (it.hasNext()) {
                this.mSize -= getSizeInBytes(((BitmapDrawable) ((Entry) it.next()).getValue()).getBitmap());
                it.remove();
                if (this.mSize <= this.mLimit) {
                    break;
                }
            }
            str = TAG;
            stringBuilder = new StringBuilder();
            stringBuilder.append("Clean cache. New size ");
            stringBuilder.append(this.mCache.size());
            Log.i(str, stringBuilder.toString());
        }
    }

    public void clear() {
        try {
            this.mCache.clear();
            this.mSize = 0;
        } catch (NullPointerException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    private long getSizeInBytes(Bitmap bitmap) {
        return bitmap == null ? 0 : (long) (bitmap.getRowBytes() * bitmap.getHeight());
    }
}
