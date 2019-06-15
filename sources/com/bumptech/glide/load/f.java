package com.bumptech.glide.load;

import android.support.v4.util.ArrayMap;
import java.security.MessageDigest;
import java.util.Map.Entry;

public final class f implements c {
    private final ArrayMap<e<?>, Object> b = new ArrayMap();

    public void a(f fVar) {
        this.b.putAll(fVar.b);
    }

    public <T> f a(e<T> eVar, T t) {
        this.b.put(eVar, t);
        return this;
    }

    public <T> T a(e<T> eVar) {
        return this.b.containsKey(eVar) ? this.b.get(eVar) : eVar.a();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof f)) {
            return false;
        }
        return this.b.equals(((f) obj).b);
    }

    public int hashCode() {
        return this.b.hashCode();
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) {
        for (Entry entry : this.b.entrySet()) {
            a((e) entry.getKey(), entry.getValue(), messageDigest);
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Options{values=");
        stringBuilder.append(this.b);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    private static <T> void a(e<T> eVar, Object obj, MessageDigest messageDigest) {
        eVar.a(obj, messageDigest);
    }
}
