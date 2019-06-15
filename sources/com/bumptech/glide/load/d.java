package com.bumptech.glide.load;

import android.content.Context;
import com.bumptech.glide.load.engine.q;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Collection;

public class d<T> implements i<T> {
    private final Collection<? extends i<T>> b;

    @SafeVarargs
    public d(i<T>... iVarArr) {
        if (iVarArr.length < 1) {
            throw new IllegalArgumentException("MultiTransformation must contain at least one Transformation");
        }
        this.b = Arrays.asList(iVarArr);
    }

    public q<T> transform(Context context, q<T> qVar, int i, int i2) {
        q qVar2 = qVar;
        for (i transform : this.b) {
            q transform2 = transform.transform(context, qVar2, i, i2);
            if (!(qVar2 == null || qVar2.equals(qVar) || qVar2.equals(transform2))) {
                qVar2.e();
            }
            qVar2 = transform2;
        }
        return qVar2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof d)) {
            return false;
        }
        return this.b.equals(((d) obj).b);
    }

    public int hashCode() {
        return this.b.hashCode();
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) {
        for (i updateDiskCacheKey : this.b) {
            updateDiskCacheKey.updateDiskCacheKey(messageDigest);
        }
    }
}
