package com.bumptech.glide.load.b.a;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import com.bumptech.glide.e.c;
import com.bumptech.glide.load.a.a.b;
import com.bumptech.glide.load.b.n;
import com.bumptech.glide.load.b.o;
import com.bumptech.glide.load.b.r;
import com.bumptech.glide.load.f;
import com.bumptech.glide.load.resource.bitmap.v;
import java.io.InputStream;

public class d implements n<Uri, InputStream> {
    private final Context a;

    public static class a implements o<Uri, InputStream> {
        private final Context a;

        public a(Context context) {
            this.a = context;
        }

        public n<Uri, InputStream> a(r rVar) {
            return new d(this.a);
        }
    }

    d(Context context) {
        this.a = context.getApplicationContext();
    }

    @Nullable
    public com.bumptech.glide.load.b.n.a<InputStream> a(Uri uri, int i, int i2, f fVar) {
        return (b.a(i, i2) && a(fVar)) ? new com.bumptech.glide.load.b.n.a(new c(uri), com.bumptech.glide.load.a.a.c.b(this.a, uri)) : null;
    }

    private boolean a(f fVar) {
        Long l = (Long) fVar.a(v.a);
        return l != null && l.longValue() == -1;
    }

    public boolean a(Uri uri) {
        return b.b(uri);
    }
}
