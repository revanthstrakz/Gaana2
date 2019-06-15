package kotlin.e;

import java.util.Iterator;
import kotlin.jvm.internal.c;

public final class h<T, R> implements a<R> {
    private final a<T> a;
    private final kotlin.jvm.a.a<T, R> b;

    public static final class a implements Iterator<R> {
        final /* synthetic */ h a;
        private final Iterator<T> b;

        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        a(h hVar) {
            this.a = hVar;
            this.b = hVar.a.a();
        }

        public R next() {
            return this.a.b.a(this.b.next());
        }

        public boolean hasNext() {
            return this.b.hasNext();
        }
    }

    public h(a<? extends T> aVar, kotlin.jvm.a.a<? super T, ? extends R> aVar2) {
        c.b(aVar, "sequence");
        c.b(aVar2, "transformer");
        this.a = aVar;
        this.b = aVar2;
    }

    public Iterator<R> a() {
        return new a(this);
    }
}
