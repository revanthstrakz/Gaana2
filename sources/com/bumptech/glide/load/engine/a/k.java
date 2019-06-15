package com.bumptech.glide.load.engine.a;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class k implements e {
    private static final Config a = Config.ARGB_8888;
    private final l b;
    private final Set<Config> c;
    private final int d;
    private final a e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;

    private interface a {
        void a(Bitmap bitmap);

        void b(Bitmap bitmap);
    }

    private static class b implements a {
        public void a(Bitmap bitmap) {
        }

        public void b(Bitmap bitmap) {
        }

        b() {
        }
    }

    k(int i, l lVar, Set<Config> set) {
        this.d = i;
        this.f = i;
        this.b = lVar;
        this.c = set;
        this.e = new b();
    }

    public k(int i) {
        this(i, e(), f());
    }

    public synchronized void a(Bitmap bitmap) {
        if (bitmap == null) {
            try {
                throw new NullPointerException("Bitmap must not be null");
            } catch (Throwable th) {
            }
        } else if (bitmap.isRecycled()) {
            throw new IllegalStateException("Cannot pool recycled bitmap");
        } else {
            StringBuilder stringBuilder;
            if (bitmap.isMutable() && this.b.c(bitmap) <= this.f) {
                if (this.c.contains(bitmap.getConfig())) {
                    int c = this.b.c(bitmap);
                    this.b.a(bitmap);
                    this.e.a(bitmap);
                    this.j++;
                    this.g += c;
                    if (Log.isLoggable("LruBitmapPool", 2)) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Put bitmap in pool=");
                        stringBuilder.append(this.b.b(bitmap));
                        Log.v("LruBitmapPool", stringBuilder.toString());
                    }
                    c();
                    b();
                    return;
                }
            }
            if (Log.isLoggable("LruBitmapPool", 2)) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("Reject bitmap from pool, bitmap: ");
                stringBuilder.append(this.b.b(bitmap));
                stringBuilder.append(", is mutable: ");
                stringBuilder.append(bitmap.isMutable());
                stringBuilder.append(", is allowed config: ");
                stringBuilder.append(this.c.contains(bitmap.getConfig()));
                Log.v("LruBitmapPool", stringBuilder.toString());
            }
            bitmap.recycle();
        }
    }

    private void b() {
        b(this.f);
    }

    @NonNull
    public Bitmap a(int i, int i2, Config config) {
        Bitmap c = c(i, i2, config);
        if (c == null) {
            return Bitmap.createBitmap(i, i2, config);
        }
        c.eraseColor(0);
        return c;
    }

    @NonNull
    public Bitmap b(int i, int i2, Config config) {
        Bitmap c = c(i, i2, config);
        return c == null ? Bitmap.createBitmap(i, i2, config) : c;
    }

    @TargetApi(26)
    private static void a(Config config) {
        if (VERSION.SDK_INT >= 26 && config == Config.HARDWARE) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Cannot create a mutable Bitmap with config: ");
            stringBuilder.append(config);
            stringBuilder.append(". Consider setting Downsampler#ALLOW_HARDWARE_CONFIG to false in your RequestOptions and/or in GlideBuilder.setDefaultRequestOptions");
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    @Nullable
    private synchronized Bitmap c(int i, int i2, Config config) {
        Bitmap a;
        StringBuilder stringBuilder;
        a(config);
        a = this.b.a(i, i2, config != null ? config : a);
        if (a == null) {
            if (Log.isLoggable("LruBitmapPool", 3)) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("Missing bitmap=");
                stringBuilder.append(this.b.b(i, i2, config));
                Log.d("LruBitmapPool", stringBuilder.toString());
            }
            this.i++;
        } else {
            this.h++;
            this.g -= this.b.c(a);
            this.e.b(a);
            b(a);
        }
        if (Log.isLoggable("LruBitmapPool", 2)) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Get bitmap=");
            stringBuilder.append(this.b.b(i, i2, config));
            Log.v("LruBitmapPool", stringBuilder.toString());
        }
        c();
        return a;
    }

    private static void b(Bitmap bitmap) {
        bitmap.setHasAlpha(true);
        c(bitmap);
    }

    @TargetApi(19)
    private static void c(Bitmap bitmap) {
        if (VERSION.SDK_INT >= 19) {
            bitmap.setPremultiplied(true);
        }
    }

    public void a() {
        if (Log.isLoggable("LruBitmapPool", 3)) {
            Log.d("LruBitmapPool", "clearMemory");
        }
        b(0);
    }

    @SuppressLint({"InlinedApi"})
    public void a(int i) {
        if (Log.isLoggable("LruBitmapPool", 3)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("trimMemory, level=");
            stringBuilder.append(i);
            Log.d("LruBitmapPool", stringBuilder.toString());
        }
        if (i >= 40) {
            a();
        } else if (i >= 20) {
            b(this.f / 2);
        }
    }

    private synchronized void b(int i) {
        while (this.g > i) {
            Bitmap a = this.b.a();
            if (a == null) {
                if (Log.isLoggable("LruBitmapPool", 5)) {
                    Log.w("LruBitmapPool", "Size mismatch, resetting");
                    d();
                }
                this.g = 0;
                return;
            }
            this.e.b(a);
            this.g -= this.b.c(a);
            this.k++;
            if (Log.isLoggable("LruBitmapPool", 3)) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Evicting bitmap=");
                stringBuilder.append(this.b.b(a));
                Log.d("LruBitmapPool", stringBuilder.toString());
            }
            c();
            a.recycle();
        }
    }

    private void c() {
        if (Log.isLoggable("LruBitmapPool", 2)) {
            d();
        }
    }

    private void d() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Hits=");
        stringBuilder.append(this.h);
        stringBuilder.append(", misses=");
        stringBuilder.append(this.i);
        stringBuilder.append(", puts=");
        stringBuilder.append(this.j);
        stringBuilder.append(", evictions=");
        stringBuilder.append(this.k);
        stringBuilder.append(", currentSize=");
        stringBuilder.append(this.g);
        stringBuilder.append(", maxSize=");
        stringBuilder.append(this.f);
        stringBuilder.append("\nStrategy=");
        stringBuilder.append(this.b);
        Log.v("LruBitmapPool", stringBuilder.toString());
    }

    private static l e() {
        if (VERSION.SDK_INT >= 19) {
            return new n();
        }
        return new c();
    }

    @TargetApi(26)
    private static Set<Config> f() {
        HashSet hashSet = new HashSet();
        hashSet.addAll(Arrays.asList(Config.values()));
        if (VERSION.SDK_INT >= 19) {
            hashSet.add(null);
        }
        if (VERSION.SDK_INT >= 26) {
            hashSet.remove(Config.HARDWARE);
        }
        return Collections.unmodifiableSet(hashSet);
    }
}
