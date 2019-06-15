package com.bumptech.glide.load.b;

import android.support.annotation.NonNull;
import android.util.Base64;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.f;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class e<Data> implements n<String, Data> {
    private final a<Data> a;

    public interface a<Data> {
        Class<Data> a();

        Data a(String str) throws IllegalArgumentException;

        void a(Data data) throws IOException;
    }

    private static final class b<Data> implements com.bumptech.glide.load.a.b<Data> {
        private final String a;
        private final a<Data> b;
        private Data c;

        public void b() {
        }

        public b(String str, a<Data> aVar) {
            this.a = str;
            this.b = aVar;
        }

        public void a(Priority priority, com.bumptech.glide.load.a.b.a<? super Data> aVar) {
            try {
                this.c = this.b.a(this.a);
                aVar.a(this.c);
            } catch (IllegalArgumentException e) {
                aVar.a(e);
            }
        }

        public void a() {
            try {
                this.b.a(this.c);
            } catch (IOException unused) {
            }
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

    public static final class c implements o<String, InputStream> {
        private final a<InputStream> a = new a<InputStream>() {
            /* renamed from: b */
            public InputStream a(String str) {
                if (str.startsWith("data:image")) {
                    int indexOf = str.indexOf(44);
                    if (indexOf == -1) {
                        throw new IllegalArgumentException("Missing comma in data URL.");
                    } else if (str.substring(0, indexOf).endsWith(";base64")) {
                        return new ByteArrayInputStream(Base64.decode(str.substring(indexOf + 1), 0));
                    } else {
                        throw new IllegalArgumentException("Not a base64 image data URL.");
                    }
                }
                throw new IllegalArgumentException("Not a valid image data URL.");
            }

            public void a(InputStream inputStream) throws IOException {
                inputStream.close();
            }

            public Class<InputStream> a() {
                return InputStream.class;
            }
        };

        public final n<String, InputStream> a(r rVar) {
            return new e(this.a);
        }
    }

    public e(a<Data> aVar) {
        this.a = aVar;
    }

    public com.bumptech.glide.load.b.n.a<Data> a(String str, int i, int i2, f fVar) {
        return new com.bumptech.glide.load.b.n.a(new com.bumptech.glide.e.c(str), new b(str, this.a));
    }

    public boolean a(String str) {
        return str.startsWith("data:image");
    }
}
