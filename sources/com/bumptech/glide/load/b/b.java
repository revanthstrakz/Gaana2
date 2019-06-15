package com.bumptech.glide.load.b;

import android.support.annotation.NonNull;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.f;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class b<Data> implements n<byte[], Data> {
    private final b<Data> a;

    public interface b<Data> {
        Class<Data> a();

        Data b(byte[] bArr);
    }

    public static class a implements o<byte[], ByteBuffer> {
        public n<byte[], ByteBuffer> a(r rVar) {
            return new b(new b<ByteBuffer>() {
                /* renamed from: a */
                public ByteBuffer b(byte[] bArr) {
                    return ByteBuffer.wrap(bArr);
                }

                public Class<ByteBuffer> a() {
                    return ByteBuffer.class;
                }
            });
        }
    }

    private static class c<Data> implements com.bumptech.glide.load.a.b<Data> {
        private final byte[] a;
        private final b<Data> b;

        public void a() {
        }

        public void b() {
        }

        public c(byte[] bArr, b<Data> bVar) {
            this.a = bArr;
            this.b = bVar;
        }

        public void a(Priority priority, com.bumptech.glide.load.a.b.a<? super Data> aVar) {
            aVar.a(this.b.b(this.a));
        }

        @NonNull
        public Class<Data> d() {
            return this.b.a();
        }

        @NonNull
        public DataSource c() {
            return DataSource.LOCAL;
        }
    }

    public static class d implements o<byte[], InputStream> {
        public n<byte[], InputStream> a(r rVar) {
            return new b(new b<InputStream>() {
                /* renamed from: a */
                public InputStream b(byte[] bArr) {
                    return new ByteArrayInputStream(bArr);
                }

                public Class<InputStream> a() {
                    return InputStream.class;
                }
            });
        }
    }

    public boolean a(byte[] bArr) {
        return true;
    }

    public b(b<Data> bVar) {
        this.a = bVar;
    }

    public com.bumptech.glide.load.b.n.a<Data> a(byte[] bArr, int i, int i2, f fVar) {
        return new com.bumptech.glide.load.b.n.a(com.bumptech.glide.e.b.a(), new c(bArr, this.a));
    }
}
