package com.bumptech.glide.e;

import com.bumptech.glide.load.c;
import java.security.MessageDigest;

public final class b implements c {
    private static final b b = new b();

    public String toString() {
        return "EmptySignature";
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) {
    }

    public static b a() {
        return b;
    }

    private b() {
    }
}
