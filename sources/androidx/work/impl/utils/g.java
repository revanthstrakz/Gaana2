package androidx.work.impl.utils;

import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import androidx.work.WorkInfo.State;
import androidx.work.f;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.b.k;
import androidx.work.impl.h;

@RestrictTo({Scope.LIBRARY_GROUP})
public class g implements Runnable {
    private static final String a = f.a("StopWorkRunnable");
    private h b;
    private String c;

    public g(h hVar, String str) {
        this.b = hVar;
        this.c = str;
    }

    public void run() {
        WorkDatabase d = this.b.d();
        k m = d.m();
        d.f();
        try {
            if (m.f(this.c) == State.RUNNING) {
                m.a(State.ENQUEUED, this.c);
            }
            boolean b = this.b.g().b(this.c);
            f.a().b(a, String.format("StopWorkRunnable for %s; Processor.stopWork = %s", new Object[]{this.c, Boolean.valueOf(b)}), new Throwable[0]);
            d.h();
        } finally {
            d.g();
        }
    }
}
