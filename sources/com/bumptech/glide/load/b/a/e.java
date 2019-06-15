package com.bumptech.glide.load.b.a;

import com.bumptech.glide.load.b.g;
import com.bumptech.glide.load.b.n;
import com.bumptech.glide.load.b.o;
import com.bumptech.glide.load.b.r;
import com.bumptech.glide.load.f;
import java.io.InputStream;
import java.net.URL;

public class e implements n<URL, InputStream> {
    private final n<g, InputStream> a;

    public static class a implements o<URL, InputStream> {
        public n<URL, InputStream> a(r rVar) {
            return new e(rVar.a(g.class, InputStream.class));
        }
    }

    public boolean a(URL url) {
        return true;
    }

    public e(n<g, InputStream> nVar) {
        this.a = nVar;
    }

    public com.bumptech.glide.load.b.n.a<InputStream> a(URL url, int i, int i2, f fVar) {
        return this.a.a(new g(url), i, i2, fVar);
    }
}
