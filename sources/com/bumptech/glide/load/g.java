package com.bumptech.glide.load;

import android.support.annotation.Nullable;
import com.bumptech.glide.load.engine.q;
import java.io.IOException;

public interface g<T, Z> {
    @Nullable
    q<Z> a(T t, int i, int i2, f fVar) throws IOException;

    boolean a(T t, f fVar) throws IOException;
}
