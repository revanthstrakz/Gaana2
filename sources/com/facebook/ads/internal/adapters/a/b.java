package com.facebook.ads.internal.adapters.a;

import android.support.annotation.Nullable;
import java.io.Serializable;

public class b implements Serializable {
    private static final long serialVersionUID = -268645651038092386L;
    private final String a;
    private final int b;
    private final int c;
    private final boolean d;
    private final boolean e;
    private final String f;
    private final int g;
    private final int h;
    @Nullable
    private final j i;
    private String j;

    static class a {
        private String a;
        private int b;
        private int c;
        private boolean d;
        private boolean e;
        private String f;
        private int g;
        private int h;
        private j i;

        a() {
        }

        /* Access modifiers changed, original: 0000 */
        public a a(int i) {
            this.b = i;
            return this;
        }

        /* Access modifiers changed, original: 0000 */
        public a a(@Nullable j jVar) {
            this.i = jVar;
            return this;
        }

        /* Access modifiers changed, original: 0000 */
        public a a(String str) {
            this.a = str;
            return this;
        }

        /* Access modifiers changed, original: 0000 */
        public a a(boolean z) {
            this.d = z;
            return this;
        }

        /* Access modifiers changed, original: 0000 */
        public b a() {
            return new b(this);
        }

        /* Access modifiers changed, original: 0000 */
        public a b(int i) {
            this.c = i;
            return this;
        }

        /* Access modifiers changed, original: 0000 */
        public a b(String str) {
            this.f = str;
            return this;
        }

        /* Access modifiers changed, original: 0000 */
        public a b(boolean z) {
            this.e = z;
            return this;
        }

        /* Access modifiers changed, original: 0000 */
        public a c(int i) {
            this.g = i;
            return this;
        }

        /* Access modifiers changed, original: 0000 */
        public a d(int i) {
            this.h = i;
            return this;
        }
    }

    private b(a aVar) {
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.d;
        this.e = aVar.e;
        this.f = aVar.f;
        this.g = aVar.g;
        this.h = aVar.h;
        this.i = aVar.i;
    }

    public String a() {
        return this.a;
    }

    /* Access modifiers changed, original: 0000 */
    public void a(String str) {
        this.j = str;
    }

    public String b() {
        return this.j;
    }

    public int c() {
        return this.b;
    }

    public int d() {
        return this.c;
    }

    public boolean e() {
        return this.d;
    }

    public boolean f() {
        return this.e;
    }

    public String g() {
        return this.f;
    }

    public int h() {
        return this.g;
    }

    public int i() {
        return this.h;
    }

    @Nullable
    public j j() {
        return this.i;
    }
}
