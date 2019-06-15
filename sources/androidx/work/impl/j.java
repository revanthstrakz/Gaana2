package androidx.work.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.WorkerThread;
import androidx.work.ListenableWorker;
import androidx.work.ListenableWorker.a.c;
import androidx.work.WorkInfo.State;
import androidx.work.WorkerParameters;
import androidx.work.d;
import androidx.work.e;
import androidx.work.f;
import androidx.work.impl.b.b;
import androidx.work.impl.b.k;
import androidx.work.impl.b.n;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

@RestrictTo({Scope.LIBRARY_GROUP})
public class j implements Runnable {
    static final String a = f.a("WorkerWrapper");
    androidx.work.impl.b.j b;
    ListenableWorker c;
    @NonNull
    androidx.work.ListenableWorker.a d = androidx.work.ListenableWorker.a.c();
    @Nullable
    ListenableFuture<androidx.work.ListenableWorker.a> e = null;
    private Context f;
    private String g;
    private List<d> h;
    private androidx.work.WorkerParameters.a i;
    private androidx.work.a j;
    private androidx.work.impl.utils.a.a k;
    private WorkDatabase l;
    private k m;
    private b n;
    private n o;
    private List<String> p;
    private String q;
    @NonNull
    private androidx.work.impl.utils.futures.b<Boolean> r = androidx.work.impl.utils.futures.b.d();
    private volatile boolean s;

    @RestrictTo({Scope.LIBRARY_GROUP})
    public static class a {
        @NonNull
        Context a;
        @Nullable
        ListenableWorker b;
        @NonNull
        androidx.work.impl.utils.a.a c;
        @NonNull
        androidx.work.a d;
        @NonNull
        WorkDatabase e;
        @NonNull
        String f;
        List<d> g;
        @NonNull
        androidx.work.WorkerParameters.a h = new androidx.work.WorkerParameters.a();

        public a(@NonNull Context context, @NonNull androidx.work.a aVar, @NonNull androidx.work.impl.utils.a.a aVar2, @NonNull WorkDatabase workDatabase, @NonNull String str) {
            this.a = context.getApplicationContext();
            this.c = aVar2;
            this.d = aVar;
            this.e = workDatabase;
            this.f = str;
        }

        public a a(List<d> list) {
            this.g = list;
            return this;
        }

        public a a(androidx.work.WorkerParameters.a aVar) {
            if (aVar != null) {
                this.h = aVar;
            }
            return this;
        }

        public j a() {
            return new j(this);
        }
    }

    j(a aVar) {
        this.f = aVar.a;
        this.k = aVar.c;
        this.g = aVar.f;
        this.h = aVar.g;
        this.i = aVar.h;
        this.c = aVar.b;
        this.j = aVar.d;
        this.l = aVar.e;
        this.m = this.l.m();
        this.n = this.l.n();
        this.o = this.l.o();
    }

    @NonNull
    public ListenableFuture<Boolean> a() {
        return this.r;
    }

    @WorkerThread
    public void run() {
        this.p = this.o.a(this.g);
        this.q = a(this.p);
        c();
    }

    private void c() {
        if (!e()) {
            this.l.f();
            try {
                this.b = this.m.b(this.g);
                if (this.b == null) {
                    f.a().e(a, String.format("Didn't find WorkSpec for id %s", new Object[]{this.g}), new Throwable[0]);
                    b(false);
                } else if (this.b.b != State.ENQUEUED) {
                    d();
                    this.l.h();
                    f.a().b(a, String.format("%s is not in ENQUEUED state. Nothing more to do.", new Object[]{this.b.c}), new Throwable[0]);
                    this.l.g();
                } else {
                    d dVar;
                    if (this.b.a() || this.b.b()) {
                        long currentTimeMillis = System.currentTimeMillis();
                        int i = (VERSION.SDK_INT >= 23 || this.b.h == this.b.i || this.b.n != 0) ? 0 : 1;
                        if (i == 0 && currentTimeMillis < this.b.c()) {
                            f.a().b(a, String.format("Delaying execution for %s because it is being executed before schedule.", new Object[]{this.b.c}), new Throwable[0]);
                            b(true);
                            this.l.g();
                            return;
                        }
                    }
                    this.l.h();
                    this.l.g();
                    if (this.b.a()) {
                        dVar = this.b.e;
                    } else {
                        e a = e.a(this.b.d);
                        if (a == null) {
                            f.a().e(a, String.format("Could not create Input Merger %s", new Object[]{this.b.d}), new Throwable[0]);
                            g();
                            return;
                        }
                        List arrayList = new ArrayList();
                        arrayList.add(this.b.e);
                        arrayList.addAll(this.m.g(this.g));
                        dVar = a.a(arrayList);
                    }
                    WorkerParameters workerParameters = new WorkerParameters(UUID.fromString(this.g), dVar, this.p, this.i, this.b.k, this.j.a(), this.k, this.j.b());
                    if (this.c == null) {
                        this.c = this.j.b().b(this.f, this.b.c, workerParameters);
                    }
                    if (this.c == null) {
                        f.a().e(a, String.format("Could not create Worker %s", new Object[]{this.b.c}), new Throwable[0]);
                        g();
                    } else if (this.c.g()) {
                        f.a().e(a, String.format("Received an already-used Worker %s; WorkerFactory should return new instances", new Object[]{this.b.c}), new Throwable[0]);
                        g();
                    } else {
                        this.c.h();
                        if (!f()) {
                            d();
                        } else if (!e()) {
                            final androidx.work.impl.utils.futures.b d = androidx.work.impl.utils.futures.b.d();
                            this.k.a().execute(new Runnable() {
                                public void run() {
                                    try {
                                        f.a().b(j.a, String.format("Starting work for %s", new Object[]{j.this.b.c}), new Throwable[0]);
                                        j.this.e = j.this.c.d();
                                        d.a(j.this.e);
                                    } catch (Throwable th) {
                                        d.a(th);
                                    }
                                }
                            });
                            final String str = this.q;
                            d.addListener(new Runnable() {
                                @SuppressLint({"SyntheticAccessor"})
                                public void run() {
                                    try {
                                        androidx.work.ListenableWorker.a aVar = (androidx.work.ListenableWorker.a) d.get();
                                        if (aVar == null) {
                                            f.a().e(j.a, String.format("%s returned a null result. Treating it as a failure.", new Object[]{j.this.b.c}), new Throwable[0]);
                                        } else {
                                            f.a().b(j.a, String.format("%s returned a %s result.", new Object[]{j.this.b.c, aVar}), new Throwable[0]);
                                            j.this.d = aVar;
                                        }
                                    } catch (CancellationException e) {
                                        f.a().c(j.a, String.format("%s was cancelled", new Object[]{str}), e);
                                    } catch (InterruptedException | ExecutionException e2) {
                                        f.a().e(j.a, String.format("%s failed because it threw an exception/error", new Object[]{str}), e2);
                                    } catch (Throwable th) {
                                        j.this.b();
                                    }
                                    j.this.b();
                                }
                            }, this.k.c());
                        }
                    }
                }
            } finally {
                this.l.g();
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void b() {
        k();
        boolean z = false;
        if (!e()) {
            try {
                boolean z2;
                this.l.f();
                State f = this.m.f(this.g);
                if (f == null) {
                    b(false);
                    z2 = true;
                } else if (f == State.RUNNING) {
                    a(this.d);
                    z2 = this.m.f(this.g).isFinished();
                } else {
                    if (!f.isFinished()) {
                        h();
                    }
                    this.l.h();
                }
                z = z2;
                this.l.h();
            } finally {
                z = this.l;
                z.g();
            }
        }
        if (this.h != null) {
            if (z) {
                for (d a : this.h) {
                    a.a(this.g);
                }
            }
            e.a(this.j, this.l, this.h);
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void a(boolean z) {
        this.s = true;
        e();
        if (this.e != null) {
            this.e.cancel(true);
        }
        if (this.c != null) {
            this.c.e();
        }
    }

    private void d() {
        if (this.m.f(this.g) == State.RUNNING) {
            f.a().b(a, String.format("Status for %s is RUNNING;not doing any work and rescheduling for later execution", new Object[]{this.g}), new Throwable[0]);
            b(true);
            return;
        }
        f.a().b(a, String.format("Status for %s is %s; not doing any work", new Object[]{this.g, r0}), new Throwable[0]);
        b(false);
    }

    private boolean e() {
        if (!this.s) {
            return false;
        }
        f.a().b(a, String.format("Work interrupted for %s", new Object[]{this.q}), new Throwable[0]);
        State f = this.m.f(this.g);
        if (f == null) {
            b(false);
        } else {
            b(f.isFinished() ^ 1);
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:9:0x001e A:{Catch:{ all -> 0x0039 }} */
    private void b(boolean r4) {
        /*
        r3 = this;
        r0 = r3.l;	 Catch:{ all -> 0x0039 }
        r0.f();	 Catch:{ all -> 0x0039 }
        r0 = r3.l;	 Catch:{ all -> 0x0039 }
        r0 = r0.m();	 Catch:{ all -> 0x0039 }
        r0 = r0.a();	 Catch:{ all -> 0x0039 }
        r1 = 0;
        if (r0 == 0) goto L_0x001b;
    L_0x0012:
        r0 = r0.isEmpty();	 Catch:{ all -> 0x0039 }
        if (r0 == 0) goto L_0x0019;
    L_0x0018:
        goto L_0x001b;
    L_0x0019:
        r0 = r1;
        goto L_0x001c;
    L_0x001b:
        r0 = 1;
    L_0x001c:
        if (r0 == 0) goto L_0x0025;
    L_0x001e:
        r0 = r3.f;	 Catch:{ all -> 0x0039 }
        r2 = androidx.work.impl.background.systemalarm.RescheduleReceiver.class;
        androidx.work.impl.utils.d.a(r0, r2, r1);	 Catch:{ all -> 0x0039 }
    L_0x0025:
        r0 = r3.l;	 Catch:{ all -> 0x0039 }
        r0.h();	 Catch:{ all -> 0x0039 }
        r0 = r3.l;
        r0.g();
        r0 = r3.r;
        r4 = java.lang.Boolean.valueOf(r4);
        r0.a(r4);
        return;
    L_0x0039:
        r4 = move-exception;
        r0 = r3.l;
        r0.g();
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.j.b(boolean):void");
    }

    private void a(androidx.work.ListenableWorker.a aVar) {
        if (aVar instanceof c) {
            f.a().c(a, String.format("Worker result SUCCESS for %s", new Object[]{this.q}), new Throwable[0]);
            if (this.b.a()) {
                i();
            } else {
                j();
            }
        } else if (aVar instanceof androidx.work.ListenableWorker.a.b) {
            f.a().c(a, String.format("Worker result RETRY for %s", new Object[]{this.q}), new Throwable[0]);
            h();
        } else {
            f.a().c(a, String.format("Worker result FAILURE for %s", new Object[]{this.q}), new Throwable[0]);
            if (this.b.a()) {
                i();
            } else {
                g();
            }
        }
    }

    private boolean f() {
        this.l.f();
        try {
            boolean z = true;
            if (this.m.f(this.g) == State.ENQUEUED) {
                this.m.a(State.RUNNING, this.g);
                this.m.d(this.g);
            } else {
                z = false;
            }
            this.l.h();
            return z;
        } finally {
            this.l.g();
        }
    }

    private void g() {
        this.l.f();
        try {
            a(this.g);
            this.m.a(this.g, ((androidx.work.ListenableWorker.a.a) this.d).d());
            this.l.h();
        } finally {
            this.l.g();
            b(false);
        }
    }

    private void a(String str) {
        for (String a : this.n.b(str)) {
            a(a);
        }
        if (this.m.f(str) != State.CANCELLED) {
            this.m.a(State.FAILED, str);
        }
    }

    private void h() {
        this.l.f();
        try {
            this.m.a(State.ENQUEUED, this.g);
            this.m.a(this.g, System.currentTimeMillis());
            if (VERSION.SDK_INT < 23) {
                this.m.b(this.g, -1);
            }
            this.l.h();
        } finally {
            this.l.g();
            b(true);
        }
    }

    private void i() {
        this.l.f();
        try {
            this.m.a(this.g, System.currentTimeMillis());
            this.m.a(State.ENQUEUED, this.g);
            this.m.e(this.g);
            if (VERSION.SDK_INT < 23) {
                this.m.b(this.g, -1);
            }
            this.l.h();
        } finally {
            this.l.g();
            b(false);
        }
    }

    private void j() {
        this.l.f();
        try {
            this.m.a(State.SUCCEEDED, this.g);
            this.m.a(this.g, ((c) this.d).d());
            long currentTimeMillis = System.currentTimeMillis();
            for (String str : this.n.b(this.g)) {
                if (this.m.f(str) == State.BLOCKED && this.n.a(str)) {
                    f.a().c(a, String.format("Setting status to enqueued for %s", new Object[]{str}), new Throwable[0]);
                    this.m.a(State.ENQUEUED, str);
                    this.m.a(str, currentTimeMillis);
                }
            }
            this.l.h();
        } finally {
            this.l.g();
            b(false);
        }
    }

    private void k() {
        if (this.k.b() != Thread.currentThread()) {
            throw new IllegalStateException("Needs to be executed on the Background executor thread.");
        }
    }

    private String a(List<String> list) {
        StringBuilder stringBuilder = new StringBuilder("Work [ id=");
        stringBuilder.append(this.g);
        stringBuilder.append(", tags={ ");
        Object obj = 1;
        for (String str : list) {
            if (obj != null) {
                obj = null;
            } else {
                stringBuilder.append(", ");
            }
            stringBuilder.append(str);
        }
        stringBuilder.append(" } ]");
        return stringBuilder.toString();
    }
}
