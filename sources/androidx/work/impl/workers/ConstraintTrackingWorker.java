package androidx.work.impl.workers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;
import androidx.work.ListenableWorker;
import androidx.work.ListenableWorker.a;
import androidx.work.WorkerParameters;
import androidx.work.f;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.a.c;
import androidx.work.impl.a.d;
import androidx.work.impl.b.j;
import androidx.work.impl.h;
import androidx.work.impl.utils.futures.b;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Collections;
import java.util.List;

@RestrictTo({Scope.LIBRARY_GROUP})
public class ConstraintTrackingWorker extends ListenableWorker implements c {
    private static final String d = f.a("ConstraintTrkngWrkr");
    final Object a = new Object();
    volatile boolean b = false;
    b<a> c = b.d();
    private WorkerParameters e;
    @Nullable
    private ListenableWorker f;

    public void a(@NonNull List<String> list) {
    }

    public ConstraintTrackingWorker(@NonNull Context context, @NonNull WorkerParameters workerParameters) {
        super(context, workerParameters);
        this.e = workerParameters;
    }

    @NonNull
    public ListenableFuture<a> d() {
        i().execute(new Runnable() {
            public void run() {
                ConstraintTrackingWorker.this.k();
            }
        });
        return this.c;
    }

    /* Access modifiers changed, original: 0000 */
    public void k() {
        String a = c().a("androidx.work.impl.workers.ConstraintTrackingWorker.ARGUMENT_CLASS_NAME");
        if (TextUtils.isEmpty(a)) {
            f.a().e(d, "No worker to delegate to.", new Throwable[0]);
            l();
            return;
        }
        this.f = j().b(a(), a, this.e);
        if (this.f == null) {
            f.a().b(d, "No worker to delegate to.", new Throwable[0]);
            l();
            return;
        }
        j b = n().m().b(b().toString());
        if (b == null) {
            l();
            return;
        }
        d dVar = new d(a(), this);
        dVar.a(Collections.singletonList(b));
        if (dVar.a(b().toString())) {
            f.a().b(d, String.format("Constraints met for delegate %s", new Object[]{a}), new Throwable[0]);
            try {
                final ListenableFuture d = this.f.d();
                d.addListener(new Runnable() {
                    public void run() {
                        synchronized (ConstraintTrackingWorker.this.a) {
                            if (ConstraintTrackingWorker.this.b) {
                                ConstraintTrackingWorker.this.m();
                            } else {
                                ConstraintTrackingWorker.this.c.a(d);
                            }
                        }
                    }
                }, i());
            } catch (Throwable th) {
                f.a().b(d, String.format("Delegated worker %s threw exception in startWork.", new Object[]{a}), th);
                synchronized (this.a) {
                    if (this.b) {
                        f.a().b(d, "Constraints were unmet, Retrying.", new Throwable[0]);
                        m();
                    } else {
                        l();
                    }
                }
            }
        } else {
            f.a().b(d, String.format("Constraints not met for delegate %s. Requesting retry.", new Object[]{a}), new Throwable[0]);
            m();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void l() {
        this.c.a(a.c());
    }

    /* Access modifiers changed, original: 0000 */
    public void m() {
        this.c.a(a.b());
    }

    public void f() {
        super.f();
        if (this.f != null) {
            this.f.e();
        }
    }

    @VisibleForTesting
    @RestrictTo({Scope.LIBRARY_GROUP})
    public WorkDatabase n() {
        return h.b().d();
    }

    public void b(@NonNull List<String> list) {
        f.a().b(d, String.format("Constraints changed for %s", new Object[]{list}), new Throwable[0]);
        synchronized (this.a) {
            this.b = true;
        }
    }
}
