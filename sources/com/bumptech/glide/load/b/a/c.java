package com.bumptech.glide.load.b.a;

import android.content.Context;
import android.net.Uri;
import com.bumptech.glide.load.a.a.b;
import com.bumptech.glide.load.b.n;
import com.bumptech.glide.load.b.o;
import com.bumptech.glide.load.b.r;
import com.bumptech.glide.load.f;
import java.io.InputStream;

public class c implements n<Uri, InputStream> {
    public final Context a;

    public static class a implements o<Uri, InputStream> {
        private final Context a;

        public a(Context context) {
            this.a = context;
        }

        public n<Uri, InputStream> a(r rVar) {
            return new c(this.a);
        }
    }

    public c(Context context) {
        this.a = context.getApplicationContext();
    }

    public com.bumptech.glide.load.b.n.a<InputStream> a(Uri uri, int i, int i2, f fVar) {
        return b.a(i, i2) ? new com.bumptech.glide.load.b.n.a(new com.bumptech.glide.e.c(uri), com.bumptech.glide.load.a.a.c.a(this.a, uri)) : null;
    }

    public boolean a(Uri uri) {
        return b.c(uri);
    }
}
