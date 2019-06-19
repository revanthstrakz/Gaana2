package androidx.work.impl.background.systemalarm;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager.WakeLock;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;
import androidx.work.f;
import androidx.work.impl.h;
import java.util.ArrayList;
import java.util.List;

@RestrictTo({Scope.LIBRARY_GROUP})
public class e implements androidx.work.impl.a {
    static final String a = f.a("SystemAlarmDispatcher");
    final Context b;
    final b c;
    final List<Intent> d;
    Intent e;
    private final g f;
    private final androidx.work.impl.c g;
    private final h h;
    private final Handler i;
    @Nullable
    private b j;

    interface b {
        void a();
    }

    static class a implements Runnable {
        private final e a;
        private final Intent b;
        private final int c;

        a(@NonNull e eVar, @NonNull Intent intent, int i) {
            this.a = eVar;
            this.b = intent;
            this.c = i;
        }

        public void run() {
            this.a.a(this.b, this.c);
        }
    }

    static class c implements Runnable {
        private final e a;

        c(@NonNull e eVar) {
            this.a = eVar;
        }

        public void run() {
            this.a.e();
        }
    }

    e(@NonNull Context context) {
        this(context, null, null);
    }

    @VisibleForTesting
    e(@NonNull Context context, @Nullable androidx.work.impl.c cVar, @Nullable h hVar) {
        this.b = context.getApplicationContext();
        this.c = new b(this.b);
        this.f = new g();
        if (hVar == null) {
            hVar = h.b();
        }
        this.h = hVar;
        if (cVar == null) {
            cVar = this.h.g();
        }
        this.g = cVar;
        this.g.a((androidx.work.impl.a) this);
        this.d = new ArrayList();
        this.e = null;
        this.i = new Handler(Looper.getMainLooper());
    }

    /* Access modifiers changed, original: 0000 */
    public void a() {
        this.g.b((androidx.work.impl.a) this);
        this.j = null;
    }

    public void a(@NonNull String str, boolean z) {
        a(new a(this, b.a(this.b, str, z), 0));
    }

    @MainThread
    public boolean a(@NonNull Intent intent, int i) {
        f.a().b(a, String.format("Adding command %s (%s)", new Object[]{intent, Integer.valueOf(i)}), new Throwable[0]);
        g();
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            f.a().d(a, "Unknown command. Ignoring", new Throwable[0]);
            return false;
        } else if ("ACTION_CONSTRAINTS_CHANGED".equals(action) && a("ACTION_CONSTRAINTS_CHANGED")) {
            return false;
        } else {
            intent.putExtra("KEY_START_ID", i);
            synchronized (this.d) {
                int isEmpty = this.d.isEmpty() ^ 1;
                this.d.add(intent);
                if (isEmpty == 0) {
                    f();
                }
            }
            return true;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(@NonNull b bVar) {
        if (this.j != null) {
            f.a().e(a, "A completion listener for SystemAlarmDispatcher already exists.", new Throwable[0]);
        } else {
            this.j = bVar;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public androidx.work.impl.c b() {
        return this.g;
    }

    /* Access modifiers changed, original: 0000 */
    public g c() {
        return this.f;
    }

    /* Access modifiers changed, original: 0000 */
    public h d() {
        return this.h;
    }

    /* Access modifiers changed, original: 0000 */
    public void a(@NonNull Runnable runnable) {
        this.i.post(runnable);
    }

    /* Access modifiers changed, original: 0000 */
    @MainThread
    public void e() {
        f.a().b(a, "Checking if commands are complete.", new Throwable[0]);
        g();
        synchronized (this.d) {
            if (this.e != null) {
                f.a().b(a, String.format("Removing command %s", new Object[]{this.e}), new Throwable[0]);
                if (((Intent) this.d.remove(0)).equals(this.e)) {
                    this.e = null;
                } else {
                    throw new IllegalStateException("Dequeue-d command is not the first.");
                }
            }
            if (!this.c.a() && this.d.isEmpty()) {
                f.a().b(a, "No more commands & intents.", new Throwable[0]);
                if (this.j != null) {
                    this.j.a();
                }
            } else if (!this.d.isEmpty()) {
                f();
            }
        }
    }

    @MainThread
    private void f() {
        g();
        WakeLock a = androidx.work.impl.utils.h.a(this.b, "ProcessCommand");
        try {
            a.acquire();
            this.h.h().a(new Runnable() {
                public void run() {
                    synchronized (e.this.d) {
                        e.this.e = (Intent) e.this.d.get(0);
                    }
                    if (e.this.e != null) {
                        e eVar;
                        Runnable cVar;
                        String action = e.this.e.getAction();
                        int intExtra = e.this.e.getIntExtra("KEY_START_ID", 0);
                        f.a().b(e.a, String.format("Processing command %s, %s", new Object[]{e.this.e, Integer.valueOf(intExtra)}), new Throwable[0]);
                        WakeLock a = androidx.work.impl.utils.h.a(e.this.b, String.format("%s (%s)", new Object[]{action, Integer.valueOf(intExtra)}));
                        try {
                            f.a().b(e.a, String.format("Acquiring operation wake lock (%s) %s", new Object[]{action, a}), new Throwable[0]);
                            a.acquire();
                            e.this.c.a(e.this.e, intExtra, e.this);
                            f.a().b(e.a, String.format("Releasing operation wake lock (%s) %s", new Object[]{action, a}), new Throwable[0]);
                            a.release();
                            eVar = e.this;
                            cVar = new c(e.this);
                        } catch (Throwable th) {
                            f.a().b(e.a, String.format("Releasing operation wake lock (%s) %s", new Object[]{action, a}), new Throwable[0]);
                            a.release();
                            e.this.a(new c(e.this));
                        }
                        eVar.a(cVar);
                    }
                }
            });
        } finally {
            a.release();
        }
    }

    @MainThread
    private boolean a(@NonNull String str) {
        g();
        synchronized (this.d) {
            for (Intent action : this.d) {
                if (str.equals(action.getAction())) {
                    return true;
                }
            }
            return false;
        }
    }

    private void g() {
        if (this.i.getLooper().getThread() != Thread.currentThread()) {
            throw new IllegalStateException("Needs to be invoked on the main thread.");
        }
    }
}
