package com.bumptech.glide.load.a;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;

public interface b<T> {

    public interface a<T> {
        void a(Exception exception);

        void a(@Nullable T t);
    }

    void a();

    void a(Priority priority, a<? super T> aVar);

    void b();

    @NonNull
    DataSource c();

    @NonNull
    Class<T> d();
}
