package kotlin.a;

import java.util.Iterator;

public abstract class r implements Iterator<Integer> {
    public abstract int b();

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* renamed from: a */
    public final Integer next() {
        return Integer.valueOf(b());
    }
}
