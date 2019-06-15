package com.bumptech.glide.f.a;

public abstract class b {

    private static class a extends b {
        private volatile boolean a;

        a() {
            super();
        }

        public void b() {
            if (this.a) {
                throw new IllegalStateException("Already released");
            }
        }

        public void a(boolean z) {
            this.a = z;
        }
    }

    public abstract void a(boolean z);

    public abstract void b();

    public static b a() {
        return new a();
    }

    private b() {
    }
}
