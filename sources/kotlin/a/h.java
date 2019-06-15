package kotlin.a;

import java.util.Collections;
import java.util.List;
import kotlin.jvm.internal.c;

class h {
    public static final <T> List<T> a(T t) {
        Object singletonList = Collections.singletonList(t);
        c.a(singletonList, "java.util.Collections.singletonList(element)");
        return singletonList;
    }
}
