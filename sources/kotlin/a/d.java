package kotlin.a;

import java.util.List;
import kotlin.jvm.internal.c;

class d extends c {
    public static final <T> List<T> a(T[] tArr) {
        c.b(tArr, "receiver$0");
        Object a = f.a(tArr);
        c.a(a, "ArraysUtilJVM.asList(this)");
        return a;
    }
}
