package androidx.work.impl.background.systemalarm;

import android.content.Context;
import android.os.PowerManager.WakeLock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.WorkerThread;
import androidx.work.f;
import androidx.work.impl.a;
import androidx.work.impl.a.c;
import androidx.work.impl.b.j;
import androidx.work.impl.utils.h;
import java.util.Collections;
import java.util.List;

@RestrictTo({Scope.LIBRARY_GROUP})
public class d implements a, c, a {
    private static final String a = f.a("DelayMetCommandHandler");
    private final Context b;
    private final int c;
    private final String d;
    private final e e;
    private final androidx.work.impl.a.d f = new androidx.work.impl.a.d(this.b, this);
    private final Object g = new Object();
    private boolean h = false;
    @Nullable
    private WakeLock i;
    private boolean j = false;

    d(@NonNull Context context, int i, @NonNull String str, @NonNull e eVar) {
        this.b = context;
        this.c = i;
        this.e = eVar;
        this.d = str;
    }

    public void a(@NonNull List<String> list) {
        if (list.contains(this.d)) {
            f.a().b(a, String.format("onAllConstraintsMet for %s", new Object[]{this.d}), new Throwable[0]);
            if (this.e.b().a(this.d)) {
                this.e.c().a(this.d, 600000, this);
            } else {
                c();
            }
        }
    }

    public void a(@NonNull String str, boolean z) {
        f.a().b(a, String.format("onExecuted %s, %s", new Object[]{str, Boolean.valueOf(z)}), new Throwable[0]);
        c();
        if (z) {
            this.e.a(new a(this.e, b.a(this.b, this.d), this.c));
        }
        if (this.j) {
            this.e.a(new a(this.e, b.a(this.b), this.c));
        }
    }

    public void a(@NonNull String str) {
        f.a().b(a, String.format("Exceeded time limits on execution for %s", new Object[]{str}), new Throwable[0]);
        b();
    }

    public void b(@NonNull List<String> list) {
        b();
    }

    /* Access modifiers changed, original: 0000 */
    @WorkerThread
    public void a() {
        this.i = h.a(this.b, String.format("%s (%s)", new Object[]{this.d, Integer.valueOf(this.c)}));
        f.a().b(a, String.format("Acquiring wakelock %s for WorkSpec %s", new Object[]{this.i, this.d}), new Throwable[0]);
        this.i.acquire();
        j b = this.e.d().d().m().b(this.d);
        if (b == null) {
            b();
            return;
        }
        this.j = b.d();
        if (this.j) {
            this.f.a(Collections.singletonList(b));
        } else {
            f.a().b(a, String.format("No constraints for %s", new Object[]{this.d}), new Throwable[0]);
            a(Collections.singletonList(this.d));
        }
    }

    private void b() {
        synchronized (this.g) {
            if (this.h) {
                f.a().b(a, String.format("Already stopped work for %s", new Object[]{this.d}), new Throwable[0]);
            } else {
                f.a().b(a, String.format("Stopping work for workspec %s", new Object[]{this.d}), new Throwable[0]);
                this.e.a(new a(this.e, b.c(this.b, this.d), this.c));
                if (this.e.b().e(this.d)) {
                    f.a().b(a, String.format("WorkSpec %s needs to be rescheduled", new Object[]{this.d}), new Throwable[0]);
                    this.e.a(new a(this.e, b.a(this.b, this.d), this.c));
                } else {
                    f.a().b(a, String.format("Processor does not have WorkSpec %s. No need to reschedule ", new Object[]{this.d}), new Throwable[0]);
                }
                this.h = true;
            }
        }
    }

    private void c() {
        synchronized (this.g) {
            this.e.c().a(this.d);
            if (this.i != null && this.i.isHeld()) {
                f.a().b(a, String.format("Releasing wakelock %s for WorkSpec %s", new Object[]{this.i, this.d}), new Throwable[0]);
                this.i.release();
            }
        }
    }
}
