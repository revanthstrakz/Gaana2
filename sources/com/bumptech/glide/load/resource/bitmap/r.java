package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import com.bumptech.glide.f.h;
import com.bumptech.glide.f.i;
import com.bumptech.glide.load.engine.a.e;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

public final class r extends e {
    private static final byte[] b = "com.bumptech.glide.load.resource.bitmap.RoundedCorners".getBytes(a);
    private final int c;

    public r(int i) {
        h.a(i > 0, "roundingRadius must be greater than 0.");
        this.c = i;
    }

    /* Access modifiers changed, original: protected */
    public Bitmap a(@NonNull e eVar, @NonNull Bitmap bitmap, int i, int i2) {
        return t.b(eVar, bitmap, this.c);
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof r)) {
            return false;
        }
        if (this.c == ((r) obj).c) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        return i.b("com.bumptech.glide.load.resource.bitmap.RoundedCorners".hashCode(), i.b(this.c));
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update(b);
        messageDigest.update(ByteBuffer.allocate(4).putInt(this.c).array());
    }
}
