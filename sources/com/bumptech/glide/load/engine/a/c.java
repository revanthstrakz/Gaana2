package com.bumptech.glide.load.engine.a;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import com.bumptech.glide.f.i;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;

class c implements l {
    private final b a = new b();
    private final h<a, Bitmap> b = new h();

    static class a implements m {
        private final b a;
        private int b;
        private int c;
        private Config d;

        public a(b bVar) {
            this.a = bVar;
        }

        public void a(int i, int i2, Config config) {
            this.b = i;
            this.c = i2;
            this.d = config;
        }

        public boolean equals(Object obj) {
            boolean z = false;
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            if (this.b == aVar.b && this.c == aVar.c && this.d == aVar.d) {
                z = true;
            }
            return z;
        }

        public int hashCode() {
            return (31 * ((this.b * 31) + this.c)) + (this.d != null ? this.d.hashCode() : 0);
        }

        public String toString() {
            return c.c(this.b, this.c, this.d);
        }

        public void a() {
            this.a.a(this);
        }
    }

    static class b extends d<a> {
        b() {
        }

        public a a(int i, int i2, Config config) {
            a aVar = (a) c();
            aVar.a(i, i2, config);
            return aVar;
        }

        /* Access modifiers changed, original: protected */
        /* renamed from: a */
        public a b() {
            return new a(this);
        }
    }

    c() {
    }

    public void a(Bitmap bitmap) {
        this.b.a(this.a.a(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig()), bitmap);
    }

    public Bitmap a(int i, int i2, Config config) {
        return (Bitmap) this.b.a(this.a.a(i, i2, config));
    }

    public Bitmap a() {
        return (Bitmap) this.b.a();
    }

    public String b(Bitmap bitmap) {
        return d(bitmap);
    }

    public String b(int i, int i2, Config config) {
        return c(i, i2, config);
    }

    public int c(Bitmap bitmap) {
        return i.a(bitmap);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("AttributeStrategy:\n  ");
        stringBuilder.append(this.b);
        return stringBuilder.toString();
    }

    private static String d(Bitmap bitmap) {
        return c(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
    }

    static String c(int i, int i2, Config config) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        stringBuilder.append(i);
        stringBuilder.append(AvidJSONUtil.KEY_X);
        stringBuilder.append(i2);
        stringBuilder.append("], ");
        stringBuilder.append(config);
        return stringBuilder.toString();
    }
}
