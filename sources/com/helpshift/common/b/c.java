package com.helpshift.common.b;

import com.helpshift.common.domain.network.j;

public class c {
    private final b a;
    private final b b;

    public static class a {
        final com.helpshift.common.b.b.a a = new com.helpshift.common.b.b.a();
        b b = b.b;

        public a a(b bVar) {
            this.b = bVar;
            return this;
        }

        public a a(a aVar) {
            this.a.a(aVar);
            return this;
        }

        public a b(a aVar) {
            this.a.b(aVar);
            return this;
        }

        public a a(float f) {
            this.a.a(f);
            return this;
        }

        public a b(float f) {
            this.a.b(f);
            return this;
        }

        public a a(int i) {
            this.a.a(i);
            return this;
        }

        public c a() throws IllegalArgumentException {
            this.a.a();
            return new c(this);
        }
    }

    public interface b {
        public static final b a = new b() {
            public boolean a(int i) {
                return j.w.contains(Integer.valueOf(i)) ^ 1;
            }
        };
        public static final b b = new b() {
            public boolean a(int i) {
                return true;
            }
        };
        public static final b c = new b() {
            public boolean a(int i) {
                return false;
            }
        };

        boolean a(int i);
    }

    c(a aVar) {
        this.a = new b(aVar.a);
        this.b = aVar.b;
    }

    public void a() {
        this.a.a();
    }

    public long a(int i) {
        return this.b.a(i) ? this.a.b() : -100;
    }
}
