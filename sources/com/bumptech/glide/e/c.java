package com.bumptech.glide.e;

import com.bumptech.glide.f.h;
import java.security.MessageDigest;

public final class c implements com.bumptech.glide.load.c {
    private final Object b;

    public c(Object obj) {
        this.b = h.a(obj);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ObjectKey{object=");
        stringBuilder.append(this.b);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof c)) {
            return false;
        }
        return this.b.equals(((c) obj).b);
    }

    public int hashCode() {
        return this.b.hashCode();
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update(this.b.toString().getBytes(a));
    }
}
