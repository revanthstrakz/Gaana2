package androidx.work.impl.utils;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.WorkerThread;
import androidx.work.WorkInfo.State;
import androidx.work.h;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.b;
import androidx.work.impl.b.k;
import androidx.work.impl.d;
import androidx.work.impl.e;

@RestrictTo({Scope.LIBRARY_GROUP})
public abstract class a implements Runnable {
    private final b a = new b();

    public abstract void a();

    public void run() {
        try {
            a();
            this.a.a(h.a);
        } catch (Throwable th) {
            this.a.a(new androidx.work.h.a.a(th));
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(androidx.work.impl.h hVar, String str) {
        a(hVar.d(), str);
        hVar.g().c(str);
        for (d a : hVar.f()) {
            a.a(str);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(androidx.work.impl.h hVar) {
        e.a(hVar.e(), hVar.d(), hVar.f());
    }

    private void a(WorkDatabase workDatabase, String str) {
        k m = workDatabase.m();
        for (String a : workDatabase.n().b(str)) {
            a(workDatabase, a);
        }
        State f = m.f(str);
        if (f != State.SUCCEEDED && f != State.FAILED) {
            m.a(State.CANCELLED, str);
        }
    }

    public static a a(@NonNull final String str, @NonNull final androidx.work.impl.h hVar, final boolean z) {
        return new a() {
            /* Access modifiers changed, original: 0000 */
            @WorkerThread
            public void a() {
                WorkDatabase d = hVar.d();
                d.f();
                try {
                    for (String a : d.m().h(str)) {
                        a(hVar, a);
                    }
                    d.h();
                    if (z) {
                        a(hVar);
                    }
                } finally {
                    d.g();
                }
            }
        };
    }
}
