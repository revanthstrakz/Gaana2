package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.f.c;
import com.bumptech.glide.load.engine.a.b;
import com.bumptech.glide.load.engine.a.e;
import com.bumptech.glide.load.engine.q;
import com.bumptech.glide.load.f;
import com.bumptech.glide.load.g;
import java.io.IOException;
import java.io.InputStream;

public class s implements g<InputStream, Bitmap> {
    private final k a;
    private final b b;

    static class a implements com.bumptech.glide.load.resource.bitmap.k.a {
        private final RecyclableBufferedInputStream a;
        private final c b;

        public a(RecyclableBufferedInputStream recyclableBufferedInputStream, c cVar) {
            this.a = recyclableBufferedInputStream;
            this.b = cVar;
        }

        public void a() {
            this.a.a();
        }

        public void a(e eVar, Bitmap bitmap) throws IOException {
            IOException a = this.b.a();
            if (a != null) {
                if (bitmap != null) {
                    eVar.a(bitmap);
                }
                throw a;
            }
        }
    }

    public s(k kVar, b bVar) {
        this.a = kVar;
        this.b = bVar;
    }

    public boolean a(InputStream inputStream, f fVar) throws IOException {
        return this.a.a(inputStream);
    }

    public q<Bitmap> a(InputStream inputStream, int i, int i2, f fVar) throws IOException {
        Object obj;
        if (inputStream instanceof RecyclableBufferedInputStream) {
            inputStream = (RecyclableBufferedInputStream) inputStream;
            obj = null;
        } else {
            InputStream recyclableBufferedInputStream = new RecyclableBufferedInputStream(inputStream, this.b);
            obj = 1;
            inputStream = recyclableBufferedInputStream;
        }
        c a = c.a(inputStream);
        try {
            q<Bitmap> a2 = this.a.a(new com.bumptech.glide.f.f(a), i, i2, fVar, new a(inputStream, a));
            return a2;
        } finally {
            a.b();
            if (obj != null) {
                inputStream.b();
            }
        }
    }
}
