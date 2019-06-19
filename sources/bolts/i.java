package bolts;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

public class i<TResult> {
    public static final ExecutorService a = d.a();
    public static final Executor b = a.b();
    private static final Executor c = d.b();
    private static volatile b d;
    private static i<?> m = new i(null);
    private static i<Boolean> n = new i(Boolean.valueOf(true));
    private static i<Boolean> o = new i(Boolean.valueOf(false));
    private static i<?> p = new i(true);
    private final Object e = new Object();
    private boolean f;
    private boolean g;
    private TResult h;
    private Exception i;
    private boolean j;
    private k k;
    private List<h<TResult, Void>> l = new ArrayList();

    public class a extends j<TResult> {
        a() {
        }
    }

    public interface b {
        void a(i<?> iVar, UnobservedTaskException unobservedTaskException);
    }

    public static b a() {
        return d;
    }

    i() {
    }

    private i(TResult tResult) {
        b((Object) tResult);
    }

    private i(boolean z) {
        if (z) {
            i();
        } else {
            b(null);
        }
    }

    public static <TResult> a b() {
        i iVar = new i();
        iVar.getClass();
        return new a();
    }

    public boolean c() {
        boolean z;
        synchronized (this.e) {
            z = this.f;
        }
        return z;
    }

    public boolean d() {
        boolean z;
        synchronized (this.e) {
            z = this.g;
        }
        return z;
    }

    public boolean e() {
        boolean z;
        synchronized (this.e) {
            z = g() != null;
        }
        return z;
    }

    public TResult f() {
        Object obj;
        synchronized (this.e) {
            obj = this.h;
        }
        return obj;
    }

    public Exception g() {
        Exception exception;
        synchronized (this.e) {
            if (this.i != null) {
                this.j = true;
                if (this.k != null) {
                    this.k.a();
                    this.k = null;
                }
            }
            exception = this.i;
        }
        return exception;
    }

    public static <TResult> i<TResult> a(TResult tResult) {
        if (tResult == null) {
            return m;
        }
        if (tResult instanceof Boolean) {
            return ((Boolean) tResult).booleanValue() ? n : o;
        }
        j jVar = new j();
        jVar.b((Object) tResult);
        return jVar.a();
    }

    public static <TResult> i<TResult> a(Exception exception) {
        j jVar = new j();
        jVar.b(exception);
        return jVar.a();
    }

    public static <TResult> i<TResult> h() {
        return p;
    }

    public <TContinuationResult> i<TContinuationResult> a(h<TResult, TContinuationResult> hVar, Executor executor, e eVar) {
        boolean c;
        j jVar = new j();
        synchronized (this.e) {
            c = c();
            if (!c) {
                final j jVar2 = jVar;
                final h<TResult, TContinuationResult> hVar2 = hVar;
                final Executor executor2 = executor;
                final e eVar2 = eVar;
                this.l.add(new h<TResult, Void>() {
                    /* renamed from: a */
                    public Void then(i<TResult> iVar) {
                        i.c(jVar2, hVar2, iVar, executor2, eVar2);
                        return null;
                    }
                });
            }
        }
        if (c) {
            c(jVar, hVar, this, executor, eVar);
        }
        return jVar.a();
    }

    public <TContinuationResult> i<TContinuationResult> a(h<TResult, TContinuationResult> hVar) {
        return a(hVar, c, null);
    }

    public <TContinuationResult> i<TContinuationResult> a(h<TResult, i<TContinuationResult>> hVar, Executor executor) {
        return b(hVar, executor, null);
    }

    public <TContinuationResult> i<TContinuationResult> b(h<TResult, i<TContinuationResult>> hVar, Executor executor, e eVar) {
        boolean c;
        j jVar = new j();
        synchronized (this.e) {
            c = c();
            if (!c) {
                final j jVar2 = jVar;
                final h<TResult, i<TContinuationResult>> hVar2 = hVar;
                final Executor executor2 = executor;
                final e eVar2 = eVar;
                this.l.add(new h<TResult, Void>() {
                    /* renamed from: a */
                    public Void then(i<TResult> iVar) {
                        i.d(jVar2, hVar2, iVar, executor2, eVar2);
                        return null;
                    }
                });
            }
        }
        if (c) {
            d(jVar, hVar, this, executor, eVar);
        }
        return jVar.a();
    }

    public <TContinuationResult> i<TContinuationResult> c(final h<TResult, TContinuationResult> hVar, Executor executor, final e eVar) {
        return a(new h<TResult, i<TContinuationResult>>() {
            /* renamed from: a */
            public i<TContinuationResult> then(i<TResult> iVar) {
                if (eVar != null && eVar.a()) {
                    return i.h();
                }
                if (iVar.e()) {
                    return i.a(iVar.g());
                }
                if (iVar.d()) {
                    return i.h();
                }
                return iVar.a(hVar);
            }
        }, executor);
    }

    public <TContinuationResult> i<TContinuationResult> b(h<TResult, TContinuationResult> hVar) {
        return c(hVar, c, null);
    }

    private static <TContinuationResult, TResult> void c(final j<TContinuationResult> jVar, final h<TResult, TContinuationResult> hVar, final i<TResult> iVar, Executor executor, final e eVar) {
        try {
            executor.execute(new Runnable() {
                public void run() {
                    if (eVar == null || !eVar.a()) {
                        try {
                            jVar.b(hVar.then(iVar));
                        } catch (CancellationException unused) {
                            jVar.c();
                        } catch (Exception e) {
                            jVar.b(e);
                        }
                        return;
                    }
                    jVar.c();
                }
            });
        } catch (Exception e) {
            jVar.b(new ExecutorException(e));
        }
    }

    private static <TContinuationResult, TResult> void d(final j<TContinuationResult> jVar, final h<TResult, i<TContinuationResult>> hVar, final i<TResult> iVar, Executor executor, final e eVar) {
        try {
            executor.execute(new Runnable() {
                public void run() {
                    if (eVar == null || !eVar.a()) {
                        try {
                            i iVar = (i) hVar.then(iVar);
                            if (iVar == null) {
                                jVar.b(null);
                            } else {
                                iVar.a(new h<TContinuationResult, Void>() {
                                    /* renamed from: a */
                                    public Void then(i<TContinuationResult> iVar) {
                                        if (eVar == null || !eVar.a()) {
                                            if (iVar.d()) {
                                                jVar.c();
                                            } else if (iVar.e()) {
                                                jVar.b(iVar.g());
                                            } else {
                                                jVar.b(iVar.f());
                                            }
                                            return null;
                                        }
                                        jVar.c();
                                        return null;
                                    }
                                });
                            }
                        } catch (CancellationException unused) {
                            jVar.c();
                        } catch (Exception e) {
                            jVar.b(e);
                        }
                        return;
                    }
                    jVar.c();
                }
            });
        } catch (Exception e) {
            jVar.b(new ExecutorException(e));
        }
    }

    private void j() {
        synchronized (this.e) {
            for (h then : this.l) {
                try {
                    then.then(this);
                } catch (RuntimeException e) {
                    throw e;
                } catch (Exception e2) {
                    throw new RuntimeException(e2);
                }
            }
            this.l = null;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean i() {
        synchronized (this.e) {
            if (this.f) {
                return false;
            }
            this.f = true;
            this.g = true;
            this.e.notifyAll();
            j();
            return true;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean b(TResult tResult) {
        synchronized (this.e) {
            if (this.f) {
                return false;
            }
            this.f = true;
            this.h = tResult;
            this.e.notifyAll();
            j();
            return true;
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* JADX WARNING: Missing block: B:13:0x002b, code skipped:
            return true;
     */
    public boolean b(java.lang.Exception r4) {
        /*
        r3 = this;
        r0 = r3.e;
        monitor-enter(r0);
        r1 = r3.f;	 Catch:{ all -> 0x002c }
        r2 = 0;
        if (r1 == 0) goto L_0x000a;
    L_0x0008:
        monitor-exit(r0);	 Catch:{ all -> 0x002c }
        return r2;
    L_0x000a:
        r1 = 1;
        r3.f = r1;	 Catch:{ all -> 0x002c }
        r3.i = r4;	 Catch:{ all -> 0x002c }
        r3.j = r2;	 Catch:{ all -> 0x002c }
        r4 = r3.e;	 Catch:{ all -> 0x002c }
        r4.notifyAll();	 Catch:{ all -> 0x002c }
        r3.j();	 Catch:{ all -> 0x002c }
        r4 = r3.j;	 Catch:{ all -> 0x002c }
        if (r4 != 0) goto L_0x002a;
    L_0x001d:
        r4 = a();	 Catch:{ all -> 0x002c }
        if (r4 == 0) goto L_0x002a;
    L_0x0023:
        r4 = new bolts.k;	 Catch:{ all -> 0x002c }
        r4.<init>(r3);	 Catch:{ all -> 0x002c }
        r3.k = r4;	 Catch:{ all -> 0x002c }
    L_0x002a:
        monitor-exit(r0);	 Catch:{ all -> 0x002c }
        return r1;
    L_0x002c:
        r4 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x002c }
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: bolts.i.b(java.lang.Exception):boolean");
    }
}
