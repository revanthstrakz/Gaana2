package kotlin.jvm.internal;

import kotlin.d.a;

public class d {
    private static final e a;
    private static final a[] b = new a[0];

    static {
        e eVar = null;
        try {
            eVar = (e) Class.forName("kotlin.reflect.jvm.internal.ReflectionFactoryImpl").newInstance();
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | InstantiationException unused) {
        }
        if (eVar == null) {
            eVar = new e();
        }
        a = eVar;
    }

    public static String a(Lambda lambda) {
        return a.a(lambda);
    }
}
