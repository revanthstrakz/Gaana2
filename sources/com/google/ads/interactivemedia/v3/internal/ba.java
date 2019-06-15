package com.google.ads.interactivemedia.v3.internal;

import com.google.android.exoplayer2.DefaultLoadControl;

public interface ba {

    public interface a {
        void a(int i, Object obj) throws az;
    }

    public static final class b {
        public static ba a(int i) {
            return new bb(i, DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_MS, 5000);
        }
    }

    public interface c {
        void a();

        void a(az azVar);

        void a(boolean z, int i);
    }

    int a();

    void a(long j);

    void a(a aVar, int i, Object obj);

    void a(c cVar);

    void a(boolean z);

    void a(bq... bqVarArr);

    void b();

    void b(a aVar, int i, Object obj);

    void b(c cVar);

    void c();

    long d();

    long e();
}
