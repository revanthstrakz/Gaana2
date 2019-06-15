package com.bumptech.glide.load.b;

import android.support.annotation.NonNull;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.e.c;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.f;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

public class d implements n<File, ByteBuffer> {

    private static class a implements com.bumptech.glide.load.a.b<ByteBuffer> {
        private final File a;

        public void a() {
        }

        public void b() {
        }

        public a(File file) {
            this.a = file;
        }

        public void a(Priority priority, com.bumptech.glide.load.a.b.a<? super ByteBuffer> aVar) {
            try {
                aVar.a(com.bumptech.glide.f.a.a(this.a));
            } catch (IOException e) {
                if (Log.isLoggable("ByteBufferFileLoader", 3)) {
                    Log.d("ByteBufferFileLoader", "Failed to obtain ByteBuffer for file", e);
                }
                aVar.a(e);
            }
        }

        @NonNull
        public Class<ByteBuffer> d() {
            return ByteBuffer.class;
        }

        @NonNull
        public DataSource c() {
            return DataSource.LOCAL;
        }
    }

    public static class b implements o<File, ByteBuffer> {
        public n<File, ByteBuffer> a(r rVar) {
            return new d();
        }
    }

    public boolean a(File file) {
        return true;
    }

    public com.bumptech.glide.load.b.n.a<ByteBuffer> a(File file, int i, int i2, f fVar) {
        return new com.bumptech.glide.load.b.n.a(new c(file), new a(file));
    }
}
