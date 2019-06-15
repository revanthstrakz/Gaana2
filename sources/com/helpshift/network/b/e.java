package com.helpshift.network.b;

import com.helpshift.network.errors.NetworkError;

public class e<T> {
    public final T a;
    public final NetworkError b;

    public interface a {
        void a(NetworkError networkError, Integer num);
    }

    public interface b<T> {
        void a(T t, Integer num);
    }

    public static <T> e<T> a(T t, Integer num) {
        return new e((Object) t, num);
    }

    public static <T> e<T> a(NetworkError networkError, Integer num) {
        return new e(networkError, num);
    }

    public boolean a() {
        return this.b == null;
    }

    private e(T t, Integer num) {
        this.a = t;
        this.b = null;
    }

    private e(NetworkError networkError, Integer num) {
        this.a = null;
        this.b = networkError;
    }
}
