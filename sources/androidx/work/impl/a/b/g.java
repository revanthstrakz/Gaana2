package androidx.work.impl.a.b;

import android.content.Context;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;

@RestrictTo({Scope.LIBRARY_GROUP})
public class g {
    private static g a;
    private a b;
    private b c;
    private e d;
    private f e;

    public static synchronized g a(Context context) {
        g gVar;
        synchronized (g.class) {
            if (a == null) {
                a = new g(context);
            }
            gVar = a;
        }
        return gVar;
    }

    private g(Context context) {
        context = context.getApplicationContext();
        this.b = new a(context);
        this.c = new b(context);
        this.d = new e(context);
        this.e = new f(context);
    }

    public a a() {
        return this.b;
    }

    public b b() {
        return this.c;
    }

    public e c() {
        return this.d;
    }

    public f d() {
        return this.e;
    }
}
