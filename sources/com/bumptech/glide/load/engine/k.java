package com.bumptech.glide.load.engine;

import com.bumptech.glide.f.h;
import com.bumptech.glide.load.c;
import com.bumptech.glide.load.f;
import com.bumptech.glide.load.i;
import java.security.MessageDigest;
import java.util.Map;

class k implements c {
    private final Object b;
    private final int c;
    private final int d;
    private final Class<?> e;
    private final Class<?> f;
    private final c g;
    private final Map<Class<?>, i<?>> h;
    private final f i;
    private int j;

    public k(Object obj, c cVar, int i, int i2, Map<Class<?>, i<?>> map, Class<?> cls, Class<?> cls2, f fVar) {
        this.b = h.a(obj);
        this.g = (c) h.a((Object) cVar, "Signature must not be null");
        this.c = i;
        this.d = i2;
        this.h = (Map) h.a((Object) map);
        this.e = (Class) h.a((Object) cls, "Resource class must not be null");
        this.f = (Class) h.a((Object) cls2, "Transcode class must not be null");
        this.i = (f) h.a((Object) fVar);
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof k)) {
            return false;
        }
        k kVar = (k) obj;
        if (this.b.equals(kVar.b) && this.g.equals(kVar.g) && this.d == kVar.d && this.c == kVar.c && this.h.equals(kVar.h) && this.e.equals(kVar.e) && this.f.equals(kVar.f) && this.i.equals(kVar.i)) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        if (this.j == 0) {
            this.j = this.b.hashCode();
            this.j = (this.j * 31) + this.g.hashCode();
            this.j = (this.j * 31) + this.c;
            this.j = (this.j * 31) + this.d;
            this.j = (this.j * 31) + this.h.hashCode();
            this.j = (this.j * 31) + this.e.hashCode();
            this.j = (this.j * 31) + this.f.hashCode();
            this.j = (31 * this.j) + this.i.hashCode();
        }
        return this.j;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("EngineKey{model=");
        stringBuilder.append(this.b);
        stringBuilder.append(", width=");
        stringBuilder.append(this.c);
        stringBuilder.append(", height=");
        stringBuilder.append(this.d);
        stringBuilder.append(", resourceClass=");
        stringBuilder.append(this.e);
        stringBuilder.append(", transcodeClass=");
        stringBuilder.append(this.f);
        stringBuilder.append(", signature=");
        stringBuilder.append(this.g);
        stringBuilder.append(", hashCode=");
        stringBuilder.append(this.j);
        stringBuilder.append(", transformations=");
        stringBuilder.append(this.h);
        stringBuilder.append(", options=");
        stringBuilder.append(this.i);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) {
        throw new UnsupportedOperationException();
    }
}
