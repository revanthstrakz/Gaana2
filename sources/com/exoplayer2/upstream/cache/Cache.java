package com.exoplayer2.upstream.cache;

import android.support.annotation.Nullable;
import java.io.File;
import java.io.IOException;

public interface Cache {

    public static class CacheException extends IOException {
        public CacheException(String str) {
            super(str);
        }

        public CacheException(Throwable th) {
            super(th);
        }
    }

    public interface a {
        void a(Cache cache, d dVar);

        void a(Cache cache, d dVar, d dVar2);

        void b(Cache cache, d dVar);
    }

    long a();

    long a(String str);

    d a(String str, long j) throws InterruptedException, CacheException;

    File a(String str, long j, long j2) throws CacheException;

    void a(d dVar);

    void a(File file) throws CacheException;

    void a(String str, k kVar) throws CacheException;

    @Nullable
    d b(String str, long j) throws CacheException;

    i b(String str);

    void c(String str, long j) throws CacheException;
}
