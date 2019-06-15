package com.bumptech.glide.load;

import android.support.annotation.Nullable;
import com.bumptech.glide.f.h;
import java.security.MessageDigest;

public final class e<T> {
    private static final a<Object> a = new a<Object>() {
        public void a(byte[] bArr, Object obj, MessageDigest messageDigest) {
        }
    };
    private final T b;
    private final a<T> c;
    private final String d;
    private volatile byte[] e;

    public interface a<T> {
        void a(byte[] bArr, T t, MessageDigest messageDigest);
    }

    public static <T> e<T> a(String str) {
        return new e(str, null, c());
    }

    public static <T> e<T> a(String str, T t) {
        return new e(str, t, c());
    }

    public static <T> e<T> a(String str, T t, a<T> aVar) {
        return new e(str, t, aVar);
    }

    e(String str, T t, a<T> aVar) {
        this.d = h.a(str);
        this.b = t;
        this.c = (a) h.a((Object) aVar);
    }

    @Nullable
    public T a() {
        return this.b;
    }

    public void a(T t, MessageDigest messageDigest) {
        this.c.a(b(), t, messageDigest);
    }

    private byte[] b() {
        if (this.e == null) {
            this.e = this.d.getBytes(c.a);
        }
        return this.e;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof e)) {
            return false;
        }
        return this.d.equals(((e) obj).d);
    }

    public int hashCode() {
        return this.d.hashCode();
    }

    private static <T> a<T> c() {
        return a;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Option{key='");
        stringBuilder.append(this.d);
        stringBuilder.append('\'');
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
