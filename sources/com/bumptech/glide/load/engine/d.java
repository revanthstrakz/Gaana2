package com.bumptech.glide.load.engine;

import android.support.annotation.Nullable;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.a.b;
import com.bumptech.glide.load.c;

interface d {

    public interface a {
        void a(c cVar, Exception exception, b<?> bVar, DataSource dataSource);

        void a(c cVar, @Nullable Object obj, b<?> bVar, DataSource dataSource, c cVar2);

        void c();
    }

    boolean a();

    void b();
}
