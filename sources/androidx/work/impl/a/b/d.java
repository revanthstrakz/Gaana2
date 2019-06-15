package androidx.work.impl.a.b;

import android.content.Context;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import androidx.work.f;
import androidx.work.impl.a.a;
import java.util.LinkedHashSet;
import java.util.Set;

@RestrictTo({Scope.LIBRARY_GROUP})
public abstract class d<T> {
    private static final String b = f.a("ConstraintTracker");
    protected final Context a;
    private final Object c = new Object();
    private final Set<a<T>> d = new LinkedHashSet();
    private T e;

    public abstract T c();

    public abstract void d();

    public abstract void e();

    d(Context context) {
        this.a = context.getApplicationContext();
    }

    public void a(a<T> aVar) {
        synchronized (this.c) {
            if (this.d.add(aVar)) {
                if (this.d.size() == 1) {
                    this.e = c();
                    f.a().b(b, String.format("%s: initial state = %s", new Object[]{getClass().getSimpleName(), this.e}), new Throwable[0]);
                    d();
                }
                aVar.a(this.e);
            }
        }
    }

    public void b(a<T> aVar) {
        synchronized (this.c) {
            if (this.d.remove(aVar) && this.d.isEmpty()) {
                e();
            }
        }
    }

    /* JADX WARNING: Missing block: B:17:0x0036, code skipped:
            return;
     */
    public void a(T r4) {
        /*
        r3 = this;
        r0 = r3.c;
        monitor-enter(r0);
        r1 = r3.e;	 Catch:{ all -> 0x0037 }
        if (r1 == r4) goto L_0x0035;
    L_0x0007:
        r1 = r3.e;	 Catch:{ all -> 0x0037 }
        if (r1 == 0) goto L_0x0014;
    L_0x000b:
        r1 = r3.e;	 Catch:{ all -> 0x0037 }
        r1 = r1.equals(r4);	 Catch:{ all -> 0x0037 }
        if (r1 == 0) goto L_0x0014;
    L_0x0013:
        goto L_0x0035;
    L_0x0014:
        r3.e = r4;	 Catch:{ all -> 0x0037 }
        r4 = new java.util.ArrayList;	 Catch:{ all -> 0x0037 }
        r1 = r3.d;	 Catch:{ all -> 0x0037 }
        r4.<init>(r1);	 Catch:{ all -> 0x0037 }
        r4 = r4.iterator();	 Catch:{ all -> 0x0037 }
    L_0x0021:
        r1 = r4.hasNext();	 Catch:{ all -> 0x0037 }
        if (r1 == 0) goto L_0x0033;
    L_0x0027:
        r1 = r4.next();	 Catch:{ all -> 0x0037 }
        r1 = (androidx.work.impl.a.a) r1;	 Catch:{ all -> 0x0037 }
        r2 = r3.e;	 Catch:{ all -> 0x0037 }
        r1.a(r2);	 Catch:{ all -> 0x0037 }
        goto L_0x0021;
    L_0x0033:
        monitor-exit(r0);	 Catch:{ all -> 0x0037 }
        return;
    L_0x0035:
        monitor-exit(r0);	 Catch:{ all -> 0x0037 }
        return;
    L_0x0037:
        r4 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x0037 }
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.a.b.d.a(java.lang.Object):void");
    }
}
