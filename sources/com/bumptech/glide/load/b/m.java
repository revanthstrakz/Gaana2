package com.bumptech.glide.load.b;

import android.support.annotation.Nullable;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import com.bumptech.glide.f.e;
import com.bumptech.glide.f.i;
import java.util.Queue;

public class m<A, B> {
    private final e<a<A>, B> a;

    static final class a<A> {
        private static final Queue<a<?>> a = i.a(0);
        private int b;
        private int c;
        private A d;

        static <A> a<A> a(A a, int i, int i2) {
            a aVar;
            synchronized (a) {
                aVar = (a) a.poll();
            }
            if (aVar == null) {
                aVar = new a();
            }
            aVar.b(a, i, i2);
            return aVar;
        }

        private a() {
        }

        private void b(A a, int i, int i2) {
            this.d = a;
            this.c = i;
            this.b = i2;
        }

        public void a() {
            synchronized (a) {
                a.offer(this);
            }
        }

        public boolean equals(Object obj) {
            boolean z = false;
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            if (this.c == aVar.c && this.b == aVar.b && this.d.equals(aVar.d)) {
                z = true;
            }
            return z;
        }

        public int hashCode() {
            return (31 * ((this.b * 31) + this.c)) + this.d.hashCode();
        }
    }

    public m() {
        this(Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
    }

    public m(int i) {
        this.a = new e<a<A>, B>(i) {
            /* Access modifiers changed, original: protected */
            public void a(a<A> aVar, B b) {
                aVar.a();
            }
        };
    }

    @Nullable
    public B a(A a, int i, int i2) {
        Object a2 = a.a(a, i, i2);
        Object b = this.a.b(a2);
        a2.a();
        return b;
    }

    public void a(A a, int i, int i2, B b) {
        this.a.b(a.a(a, i, i2), b);
    }
}
