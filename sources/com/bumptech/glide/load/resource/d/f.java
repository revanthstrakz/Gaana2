package com.bumptech.glide.load.resource.d;

import android.content.Context;
import android.graphics.Bitmap;
import com.bumptech.glide.e;
import com.bumptech.glide.f.h;
import com.bumptech.glide.load.engine.q;
import com.bumptech.glide.load.i;
import com.bumptech.glide.load.resource.bitmap.d;
import java.security.MessageDigest;

public class f implements i<c> {
    private final i<Bitmap> b;

    public f(i<Bitmap> iVar) {
        this.b = (i) h.a((Object) iVar);
    }

    public q<c> transform(Context context, q<c> qVar, int i, int i2) {
        c cVar = (c) qVar.c();
        d dVar = new d(cVar.b(), e.b(context).b());
        q transform = this.b.transform(context, dVar, i, i2);
        if (!dVar.equals(transform)) {
            dVar.e();
        }
        cVar.a(this.b, (Bitmap) transform.c());
        return qVar;
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
        this.b.updateDiskCacheKey(messageDigest);
    }
}
