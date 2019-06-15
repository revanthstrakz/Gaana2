package androidx.work.impl.utils.futures;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestrictTo({Scope.LIBRARY_GROUP})
public abstract class a<V> implements ListenableFuture<V> {
    static final boolean a = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", InternalLogger.EVENT_PARAM_EXTRAS_FALSE));
    static final a b;
    private static final Logger f = Logger.getLogger(a.class.getName());
    private static final Object g = new Object();
    @Nullable
    volatile Object c;
    @Nullable
    volatile d d;
    @Nullable
    volatile h e;

    private static abstract class a {
        public abstract void a(h hVar, h hVar2);

        public abstract void a(h hVar, Thread thread);

        public abstract boolean a(a<?> aVar, d dVar, d dVar2);

        public abstract boolean a(a<?> aVar, h hVar, h hVar2);

        public abstract boolean a(a<?> aVar, Object obj, Object obj2);

        private a() {
        }
    }

    private static final class b {
        static final b a;
        static final b b;
        final boolean c;
        @Nullable
        final Throwable d;

        static {
            if (a.a) {
                b = null;
                a = null;
                return;
            }
            b = new b(false, null);
            a = new b(true, null);
        }

        b(boolean z, @Nullable Throwable th) {
            this.c = z;
            this.d = th;
        }
    }

    private static final class c {
        static final c a = new c(new AbstractFuture$Failure$1("Failure occurred while trying to finish a future."));
        final Throwable b;

        c(Throwable th) {
            this.b = (Throwable) a.b((Object) th);
        }
    }

    private static final class d {
        static final d a = new d(null, null);
        final Runnable b;
        final Executor c;
        @Nullable
        d d;

        d(Runnable runnable, Executor executor) {
            this.b = runnable;
            this.c = executor;
        }
    }

    private static final class f<V> implements Runnable {
        final a<V> a;
        final ListenableFuture<? extends V> b;

        f(a<V> aVar, ListenableFuture<? extends V> listenableFuture) {
            this.a = aVar;
            this.b = listenableFuture;
        }

        public void run() {
            if (this.a.c == this) {
                if (a.b.a(this.a, (Object) this, a.b(this.b))) {
                    a.a(this.a);
                }
            }
        }
    }

    private static final class h {
        static final h a = new h(false);
        @Nullable
        volatile Thread b;
        @Nullable
        volatile h c;

        h(boolean z) {
        }

        h() {
            a.b.a(this, Thread.currentThread());
        }

        /* Access modifiers changed, original: 0000 */
        public void a(h hVar) {
            a.b.a(this, hVar);
        }

        /* Access modifiers changed, original: 0000 */
        public void a() {
            Thread thread = this.b;
            if (thread != null) {
                this.b = null;
                LockSupport.unpark(thread);
            }
        }
    }

    private static final class e extends a {
        final AtomicReferenceFieldUpdater<h, Thread> a;
        final AtomicReferenceFieldUpdater<h, h> b;
        final AtomicReferenceFieldUpdater<a, h> c;
        final AtomicReferenceFieldUpdater<a, d> d;
        final AtomicReferenceFieldUpdater<a, Object> e;

        e(AtomicReferenceFieldUpdater<h, Thread> atomicReferenceFieldUpdater, AtomicReferenceFieldUpdater<h, h> atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater<a, h> atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater<a, d> atomicReferenceFieldUpdater4, AtomicReferenceFieldUpdater<a, Object> atomicReferenceFieldUpdater5) {
            super();
            this.a = atomicReferenceFieldUpdater;
            this.b = atomicReferenceFieldUpdater2;
            this.c = atomicReferenceFieldUpdater3;
            this.d = atomicReferenceFieldUpdater4;
            this.e = atomicReferenceFieldUpdater5;
        }

        /* Access modifiers changed, original: 0000 */
        public void a(h hVar, Thread thread) {
            this.a.lazySet(hVar, thread);
        }

        /* Access modifiers changed, original: 0000 */
        public void a(h hVar, h hVar2) {
            this.b.lazySet(hVar, hVar2);
        }

        /* Access modifiers changed, original: 0000 */
        public boolean a(a<?> aVar, h hVar, h hVar2) {
            return this.c.compareAndSet(aVar, hVar, hVar2);
        }

        /* Access modifiers changed, original: 0000 */
        public boolean a(a<?> aVar, d dVar, d dVar2) {
            return this.d.compareAndSet(aVar, dVar, dVar2);
        }

        /* Access modifiers changed, original: 0000 */
        public boolean a(a<?> aVar, Object obj, Object obj2) {
            return this.e.compareAndSet(aVar, obj, obj2);
        }
    }

    private static final class g extends a {
        g() {
            super();
        }

        /* Access modifiers changed, original: 0000 */
        public void a(h hVar, Thread thread) {
            hVar.b = thread;
        }

        /* Access modifiers changed, original: 0000 */
        public void a(h hVar, h hVar2) {
            hVar.c = hVar2;
        }

        /* Access modifiers changed, original: 0000 */
        public boolean a(a<?> aVar, h hVar, h hVar2) {
            synchronized (aVar) {
                if (aVar.e == hVar) {
                    aVar.e = hVar2;
                    return true;
                }
                return false;
            }
        }

        /* Access modifiers changed, original: 0000 */
        public boolean a(a<?> aVar, d dVar, d dVar2) {
            synchronized (aVar) {
                if (aVar.d == dVar) {
                    aVar.d = dVar2;
                    return true;
                }
                return false;
            }
        }

        /* Access modifiers changed, original: 0000 */
        public boolean a(a<?> aVar, Object obj, Object obj2) {
            synchronized (aVar) {
                if (aVar.c == obj) {
                    aVar.c = obj2;
                    return true;
                }
                return false;
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void a() {
    }

    /* Access modifiers changed, original: protected */
    public void b() {
    }

    static {
        Throwable th;
        a gVar;
        try {
            a eVar = new e(AtomicReferenceFieldUpdater.newUpdater(h.class, Thread.class, com.helpshift.support.webkit.b.a), AtomicReferenceFieldUpdater.newUpdater(h.class, h.class, "c"), AtomicReferenceFieldUpdater.newUpdater(a.class, h.class, com.facebook.ads.internal.g.e.a), AtomicReferenceFieldUpdater.newUpdater(a.class, d.class, "d"), AtomicReferenceFieldUpdater.newUpdater(a.class, Object.class, "c"));
            th = null;
        } catch (Throwable th2) {
            th = th2;
            gVar = new g();
        }
        b = gVar;
        Class cls = LockSupport.class;
        if (th != null) {
            f.log(Level.SEVERE, "SafeAtomicHelper is broken!", th);
        }
    }

    private void a(h hVar) {
        hVar.b = null;
        while (true) {
            hVar = this.e;
            if (hVar != h.a) {
                h hVar2 = null;
                while (hVar != null) {
                    h hVar3 = hVar.c;
                    if (hVar.b != null) {
                        hVar2 = hVar;
                    } else if (hVar2 != null) {
                        hVar2.c = hVar3;
                        if (hVar2.b == null) {
                        }
                    } else if (b.a(this, hVar, hVar3)) {
                    }
                    hVar = hVar3;
                }
                return;
            }
            return;
        }
    }

    protected a() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:53:0x00a0  */
    public final V get(long r19, java.util.concurrent.TimeUnit r21) throws java.lang.InterruptedException, java.util.concurrent.TimeoutException, java.util.concurrent.ExecutionException {
        /*
        r18 = this;
        r0 = r18;
        r1 = r19;
        r3 = r21;
        r4 = r3.toNanos(r1);
        r6 = java.lang.Thread.interrupted();
        if (r6 == 0) goto L_0x0016;
    L_0x0010:
        r1 = new java.lang.InterruptedException;
        r1.<init>();
        throw r1;
    L_0x0016:
        r6 = r0.c;
        r8 = 1;
        if (r6 == 0) goto L_0x001d;
    L_0x001b:
        r9 = r8;
        goto L_0x001e;
    L_0x001d:
        r9 = 0;
    L_0x001e:
        r10 = r6 instanceof androidx.work.impl.utils.futures.a.f;
        if (r10 != 0) goto L_0x0024;
    L_0x0022:
        r10 = r8;
        goto L_0x0025;
    L_0x0024:
        r10 = 0;
    L_0x0025:
        r9 = r9 & r10;
        if (r9 == 0) goto L_0x002d;
    L_0x0028:
        r1 = r0.c(r6);
        return r1;
    L_0x002d:
        r9 = 0;
        r6 = (r4 > r9 ? 1 : (r4 == r9 ? 0 : -1));
        if (r6 <= 0) goto L_0x003a;
    L_0x0033:
        r11 = java.lang.System.nanoTime();
        r13 = r11 + r4;
        goto L_0x003b;
    L_0x003a:
        r13 = r9;
    L_0x003b:
        r11 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r6 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1));
        if (r6 < 0) goto L_0x009c;
    L_0x0041:
        r6 = r0.e;
        r15 = androidx.work.impl.utils.futures.a.h.a;
        if (r6 == r15) goto L_0x0095;
    L_0x0047:
        r15 = new androidx.work.impl.utils.futures.a$h;
        r15.<init>();
    L_0x004c:
        r15.a(r6);
        r7 = b;
        r6 = r7.a(r0, r6, r15);
        if (r6 == 0) goto L_0x008f;
    L_0x0057:
        java.util.concurrent.locks.LockSupport.parkNanos(r0, r4);
        r4 = java.lang.Thread.interrupted();
        if (r4 == 0) goto L_0x0069;
    L_0x0060:
        r0.a(r15);
        r1 = new java.lang.InterruptedException;
        r1.<init>();
        throw r1;
    L_0x0069:
        r4 = r0.c;
        if (r4 == 0) goto L_0x006f;
    L_0x006d:
        r5 = r8;
        goto L_0x0070;
    L_0x006f:
        r5 = 0;
    L_0x0070:
        r6 = r4 instanceof androidx.work.impl.utils.futures.a.f;
        if (r6 != 0) goto L_0x0076;
    L_0x0074:
        r6 = r8;
        goto L_0x0077;
    L_0x0076:
        r6 = 0;
    L_0x0077:
        r5 = r5 & r6;
        if (r5 == 0) goto L_0x007f;
    L_0x007a:
        r1 = r0.c(r4);
        return r1;
    L_0x007f:
        r4 = java.lang.System.nanoTime();
        r6 = r13 - r4;
        r4 = (r6 > r11 ? 1 : (r6 == r11 ? 0 : -1));
        if (r4 >= 0) goto L_0x008d;
    L_0x0089:
        r0.a(r15);
        goto L_0x00c8;
    L_0x008d:
        r4 = r6;
        goto L_0x0057;
    L_0x008f:
        r6 = r0.e;
        r7 = androidx.work.impl.utils.futures.a.h.a;
        if (r6 != r7) goto L_0x004c;
    L_0x0095:
        r1 = r0.c;
        r1 = r0.c(r1);
        return r1;
    L_0x009c:
        r6 = (r4 > r9 ? 1 : (r4 == r9 ? 0 : -1));
        if (r6 <= 0) goto L_0x00ca;
    L_0x00a0:
        r4 = r0.c;
        if (r4 == 0) goto L_0x00a6;
    L_0x00a4:
        r5 = r8;
        goto L_0x00a7;
    L_0x00a6:
        r5 = 0;
    L_0x00a7:
        r6 = r4 instanceof androidx.work.impl.utils.futures.a.f;
        if (r6 != 0) goto L_0x00ad;
    L_0x00ab:
        r6 = r8;
        goto L_0x00ae;
    L_0x00ad:
        r6 = 0;
    L_0x00ae:
        r5 = r5 & r6;
        if (r5 == 0) goto L_0x00b6;
    L_0x00b1:
        r1 = r0.c(r4);
        return r1;
    L_0x00b6:
        r4 = java.lang.Thread.interrupted();
        if (r4 == 0) goto L_0x00c2;
    L_0x00bc:
        r1 = new java.lang.InterruptedException;
        r1.<init>();
        throw r1;
    L_0x00c2:
        r4 = java.lang.System.nanoTime();
        r6 = r13 - r4;
    L_0x00c8:
        r4 = r6;
        goto L_0x009c;
    L_0x00ca:
        r6 = r18.toString();
        r7 = r21.toString();
        r13 = java.util.Locale.ROOT;
        r7 = r7.toLowerCase(r13);
        r13 = new java.lang.StringBuilder;
        r13.<init>();
        r14 = "Waited ";
        r13.append(r14);
        r13.append(r1);
        r1 = " ";
        r13.append(r1);
        r1 = r21.toString();
        r2 = java.util.Locale.ROOT;
        r1 = r1.toLowerCase(r2);
        r13.append(r1);
        r1 = r13.toString();
        r13 = r4 + r11;
        r2 = (r13 > r9 ? 1 : (r13 == r9 ? 0 : -1));
        if (r2 >= 0) goto L_0x0195;
    L_0x0101:
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r2.append(r1);
        r1 = " (plus ";
        r2.append(r1);
        r1 = r2.toString();
        r4 = -r4;
        r2 = java.util.concurrent.TimeUnit.NANOSECONDS;
        r13 = r3.convert(r4, r2);
        r2 = r3.toNanos(r13);
        r11 = r4 - r2;
        r2 = (r13 > r9 ? 1 : (r13 == r9 ? 0 : -1));
        if (r2 == 0) goto L_0x012d;
    L_0x0123:
        r2 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r4 = (r11 > r2 ? 1 : (r11 == r2 ? 0 : -1));
        if (r4 <= 0) goto L_0x012a;
    L_0x0129:
        goto L_0x012d;
    L_0x012a:
        r16 = 0;
        goto L_0x012f;
    L_0x012d:
        r16 = r8;
    L_0x012f:
        r2 = (r13 > r9 ? 1 : (r13 == r9 ? 0 : -1));
        if (r2 <= 0) goto L_0x016e;
    L_0x0133:
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r2.append(r1);
        r2.append(r13);
        r1 = " ";
        r2.append(r1);
        r2.append(r7);
        r1 = r2.toString();
        if (r16 == 0) goto L_0x015d;
    L_0x014c:
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r2.append(r1);
        r1 = ",";
        r2.append(r1);
        r1 = r2.toString();
    L_0x015d:
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r2.append(r1);
        r1 = " ";
        r2.append(r1);
        r1 = r2.toString();
    L_0x016e:
        if (r16 == 0) goto L_0x0184;
    L_0x0170:
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r2.append(r1);
        r2.append(r11);
        r1 = " nanoseconds ";
        r2.append(r1);
        r1 = r2.toString();
    L_0x0184:
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r2.append(r1);
        r1 = "delay)";
        r2.append(r1);
        r1 = r2.toString();
    L_0x0195:
        r2 = r18.isDone();
        if (r2 == 0) goto L_0x01b2;
    L_0x019b:
        r2 = new java.util.concurrent.TimeoutException;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r1);
        r1 = " but future completed as timeout expired";
        r3.append(r1);
        r1 = r3.toString();
        r2.<init>(r1);
        throw r2;
    L_0x01b2:
        r2 = new java.util.concurrent.TimeoutException;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r1);
        r1 = " for ";
        r3.append(r1);
        r3.append(r6);
        r1 = r3.toString();
        r2.<init>(r1);
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.utils.futures.a.get(long, java.util.concurrent.TimeUnit):java.lang.Object");
    }

    public final V get() throws InterruptedException, ExecutionException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        Object obj = this.c;
        if (((obj != null ? 1 : 0) & (!(obj instanceof f) ? 1 : 0)) != 0) {
            return c(obj);
        }
        h hVar = this.e;
        if (hVar != h.a) {
            h hVar2 = new h();
            do {
                hVar2.a(hVar);
                if (b.a(this, hVar, hVar2)) {
                    do {
                        LockSupport.park(this);
                        if (Thread.interrupted()) {
                            a(hVar2);
                            throw new InterruptedException();
                        }
                        obj = this.c;
                    } while (((obj != null ? 1 : 0) & (!(obj instanceof f) ? 1 : 0)) == 0);
                    return c(obj);
                }
                hVar = this.e;
            } while (hVar != h.a);
        }
        return c(this.c);
    }

    private V c(Object obj) throws ExecutionException {
        if (obj instanceof b) {
            throw a("Task was cancelled.", ((b) obj).d);
        } else if (!(obj instanceof c)) {
            return obj == g ? null : obj;
        } else {
            throw new ExecutionException(((c) obj).b);
        }
    }

    public final boolean isDone() {
        Object obj = this.c;
        int i = 0;
        int i2 = obj != null ? 1 : 0;
        if (!(obj instanceof f)) {
            i = 1;
        }
        return i2 & i;
    }

    public final boolean isCancelled() {
        return this.c instanceof b;
    }

    public final boolean cancel(boolean z) {
        Object obj = this.c;
        if (((obj == null ? 1 : 0) | (obj instanceof f)) == 0) {
            return false;
        }
        Object bVar = a ? new b(z, new CancellationException("Future.cancel() was called.")) : z ? b.a : b.b;
        Object obj2 = obj;
        boolean z2 = false;
        a aVar = this;
        while (true) {
            if (b.a(aVar, obj2, bVar)) {
                if (z) {
                    aVar.a();
                }
                a(aVar);
                if (!(obj2 instanceof f)) {
                    return true;
                }
                ListenableFuture listenableFuture = ((f) obj2).b;
                if (listenableFuture instanceof a) {
                    aVar = (a) listenableFuture;
                    obj2 = aVar.c;
                    if (((obj2 == null ? 1 : 0) | (obj2 instanceof f)) == 0) {
                        return true;
                    }
                    z2 = true;
                } else {
                    listenableFuture.cancel(z);
                    return true;
                }
            }
            obj2 = aVar.c;
            if (!(obj2 instanceof f)) {
                return z2;
            }
        }
    }

    public final void addListener(Runnable runnable, Executor executor) {
        b((Object) runnable);
        b((Object) executor);
        d dVar = this.d;
        if (dVar != d.a) {
            d dVar2 = new d(runnable, executor);
            do {
                dVar2.d = dVar;
                if (!b.a(this, dVar, dVar2)) {
                    dVar = this.d;
                } else {
                    return;
                }
            } while (dVar != d.a);
        }
        a(runnable, executor);
    }

    /* Access modifiers changed, original: protected */
    public boolean a(@Nullable V v) {
        Object v2;
        if (v2 == null) {
            v2 = g;
        }
        if (!b.a(this, null, v2)) {
            return false;
        }
        a(this);
        return true;
    }

    /* Access modifiers changed, original: protected */
    public boolean a(Throwable th) {
        if (!b.a(this, null, new c((Throwable) b((Object) th)))) {
            return false;
        }
        a(this);
        return true;
    }

    /* Access modifiers changed, original: protected */
    public boolean a(ListenableFuture<? extends V> listenableFuture) {
        Object cVar;
        b((Object) listenableFuture);
        Object obj = this.c;
        if (obj == null) {
            if (listenableFuture.isDone()) {
                if (!b.a(this, null, b((ListenableFuture) listenableFuture))) {
                    return false;
                }
                a(this);
                return true;
            }
            obj = new f(this, listenableFuture);
            if (b.a(this, null, obj)) {
                try {
                    listenableFuture.addListener(obj, DirectExecutor.INSTANCE);
                } catch (Throwable unused) {
                    cVar = c.a;
                }
                return true;
            }
            obj = this.c;
        }
        if (obj instanceof b) {
            listenableFuture.cancel(((b) obj).c);
        }
        return false;
        b.a(this, obj, cVar);
        return true;
    }

    static Object b(ListenableFuture<?> listenableFuture) {
        if (listenableFuture instanceof a) {
            Object obj = ((a) listenableFuture).c;
            if (obj instanceof b) {
                b bVar = (b) obj;
                if (bVar.c) {
                    obj = bVar.d != null ? new b(false, bVar.d) : b.b;
                }
            }
            return obj;
        }
        boolean isCancelled = listenableFuture.isCancelled();
        if (((a ^ 1) & isCancelled) != 0) {
            return b.b;
        }
        try {
            Object a = a((Future) listenableFuture);
            if (a == null) {
                a = g;
            }
            return a;
        } catch (ExecutionException e) {
            return new c(e.getCause());
        } catch (CancellationException e2) {
            if (isCancelled) {
                return new b(false, e2);
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("get() threw CancellationException, despite reporting isCancelled() == false: ");
            stringBuilder.append(listenableFuture);
            return new c(new IllegalArgumentException(stringBuilder.toString(), e2));
        } catch (Throwable th) {
            return new c(th);
        }
    }

    private static <V> V a(Future<V> future) throws ExecutionException {
        Object obj;
        Object obj2 = null;
        while (true) {
            try {
                obj = future.get();
                break;
            } catch (InterruptedException unused) {
                obj2 = 1;
            } catch (Throwable th) {
                if (obj2 != null) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        if (obj2 != null) {
            Thread.currentThread().interrupt();
        }
        return obj;
    }

    static void a(a<?> aVar) {
        d dVar = null;
        while (true) {
            a aVar2;
            aVar2.d();
            aVar2.b();
            d a = aVar2.a(dVar);
            while (a != null) {
                dVar = a.d;
                Runnable runnable = a.b;
                if (runnable instanceof f) {
                    Object obj = (f) runnable;
                    aVar2 = obj.a;
                    if (aVar2.c == obj) {
                        if (b.a(aVar2, obj, b(obj.b))) {
                        }
                    } else {
                        continue;
                    }
                } else {
                    a(runnable, a.c);
                }
                a = dVar;
            }
            return;
        }
    }

    private void d() {
        h hVar;
        do {
            hVar = this.e;
        } while (!b.a(this, hVar, h.a));
        while (hVar != null) {
            hVar.a();
            hVar = hVar.c;
        }
    }

    private d a(d dVar) {
        d dVar2;
        do {
            dVar2 = this.d;
        } while (!b.a(this, dVar2, d.a));
        d dVar3 = dVar2;
        dVar2 = dVar;
        dVar = dVar3;
        while (dVar != null) {
            d dVar4 = dVar.d;
            dVar.d = dVar2;
            dVar2 = dVar;
            dVar = dVar4;
        }
        return dVar2;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(super.toString());
        stringBuilder.append("[status=");
        if (isCancelled()) {
            stringBuilder.append("CANCELLED");
        } else if (isDone()) {
            a(stringBuilder);
        } else {
            String c;
            try {
                c = c();
            } catch (RuntimeException e) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("Exception thrown from implementation: ");
                stringBuilder2.append(e.getClass());
                c = stringBuilder2.toString();
            }
            if (c != null && !c.isEmpty()) {
                stringBuilder.append("PENDING, info=[");
                stringBuilder.append(c);
                stringBuilder.append("]");
            } else if (isDone()) {
                a(stringBuilder);
            } else {
                stringBuilder.append("PENDING");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    /* Access modifiers changed, original: protected */
    @Nullable
    public String c() {
        Object obj = this.c;
        if (obj instanceof f) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("setFuture=[");
            stringBuilder.append(d(((f) obj).b));
            stringBuilder.append("]");
            return stringBuilder.toString();
        } else if (!(this instanceof ScheduledFuture)) {
            return null;
        } else {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("remaining delay=[");
            stringBuilder2.append(((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS));
            stringBuilder2.append(" ms]");
            return stringBuilder2.toString();
        }
    }

    private void a(StringBuilder stringBuilder) {
        try {
            Object a = a((Future) this);
            stringBuilder.append("SUCCESS, result=[");
            stringBuilder.append(d(a));
            stringBuilder.append("]");
        } catch (ExecutionException e) {
            stringBuilder.append("FAILURE, cause=[");
            stringBuilder.append(e.getCause());
            stringBuilder.append("]");
        } catch (CancellationException unused) {
            stringBuilder.append("CANCELLED");
        } catch (RuntimeException e2) {
            stringBuilder.append("UNKNOWN, cause=[");
            stringBuilder.append(e2.getClass());
            stringBuilder.append(" thrown from get()]");
        }
    }

    private String d(Object obj) {
        return obj == this ? "this future" : String.valueOf(obj);
    }

    private static void a(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (RuntimeException e) {
            Logger logger = f;
            Level level = Level.SEVERE;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("RuntimeException while executing runnable ");
            stringBuilder.append(runnable);
            stringBuilder.append(" with executor ");
            stringBuilder.append(executor);
            logger.log(level, stringBuilder.toString(), e);
        }
    }

    private static CancellationException a(@Nullable String str, @Nullable Throwable th) {
        CancellationException cancellationException = new CancellationException(str);
        cancellationException.initCause(th);
        return cancellationException;
    }

    @NonNull
    static <T> T b(@Nullable T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }
}
