package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.c;
import java.security.MessageDigest;

final class b implements c {
    private final c b;
    private final c c;

    public b(c cVar, c cVar2) {
        this.b = cVar;
        this.c = cVar2;
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        if (this.b.equals(bVar.b) && this.c.equals(bVar.c)) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        return (31 * this.b.hashCode()) + this.c.hashCode();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DataCacheKey{sourceKey=");
        stringBuilder.append(this.b);
        stringBuilder.append(", signature=");
        stringBuilder.append(this.c);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) {
        this.b.updateDiskCacheKey(messageDigest);
        this.c.updateDiskCacheKey(messageDigest);
    }
}
