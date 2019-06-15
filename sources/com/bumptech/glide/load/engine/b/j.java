package com.bumptech.glide.load.engine.b;

import android.support.v4.util.Pools.Pool;
import com.bumptech.glide.f.a.b;
import com.bumptech.glide.f.e;
import com.bumptech.glide.f.i;
import com.bumptech.glide.load.c;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class j {
    private final e<c, String> a = new e(1000);
    private final Pool<a> b = com.bumptech.glide.f.a.a.b(10, new com.bumptech.glide.f.a.a.a<a>() {
        /* renamed from: a */
        public a b() {
            try {
                return new a(MessageDigest.getInstance("SHA-256"));
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
    });

    private static final class a implements com.bumptech.glide.f.a.a.c {
        final MessageDigest a;
        private final b b = b.a();

        a(MessageDigest messageDigest) {
            this.a = messageDigest;
        }

        public b a_() {
            return this.b;
        }
    }

    public String a(c cVar) {
        Object obj;
        synchronized (this.a) {
            obj = (String) this.a.b((Object) cVar);
        }
        if (obj == null) {
            obj = b(cVar);
        }
        synchronized (this.a) {
            this.a.b(cVar, obj);
        }
        return obj;
    }

    private String b(c cVar) {
        a aVar = (a) this.b.acquire();
        try {
            cVar.updateDiskCacheKey(aVar.a);
            String a = i.a(aVar.a.digest());
            return a;
        } finally {
            this.b.release(aVar);
        }
    }
}
