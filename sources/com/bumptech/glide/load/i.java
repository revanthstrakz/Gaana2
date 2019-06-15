package com.bumptech.glide.load;

import android.content.Context;
import com.bumptech.glide.load.engine.q;

public interface i<T> extends c {
    boolean equals(Object obj);

    int hashCode();

    q<T> transform(Context context, q<T> qVar, int i, int i2);
}
