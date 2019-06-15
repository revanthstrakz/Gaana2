package kotlin.a;

import java.util.Collection;
import kotlin.jvm.internal.c;

class j extends i {
    public static final <T> int a(Iterable<? extends T> iterable, int i) {
        c.b(iterable, "receiver$0");
        return iterable instanceof Collection ? ((Collection) iterable).size() : i;
    }
}
