package com.bumptech.glide.load.engine;

import android.os.Looper;
import android.os.MessageQueue.IdleHandler;
import android.support.v4.util.Pools.Pool;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.g;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.i;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class h implements com.bumptech.glide.load.engine.b.h.a, j, a {
    private final Map<com.bumptech.glide.load.c, i<?>> a;
    private final l b;
    private final com.bumptech.glide.load.engine.b.h c;
    private final b d;
    private final Map<com.bumptech.glide.load.c, WeakReference<m<?>>> e;
    private final t f;
    private final c g;
    private final a h;
    private ReferenceQueue<m<?>> i;

    static class a {
        final d a;
        final Pool<DecodeJob<?>> b = com.bumptech.glide.f.a.a.a(150, new com.bumptech.glide.f.a.a.a<DecodeJob<?>>() {
            /* renamed from: a */
            public DecodeJob<?> b() {
                return new DecodeJob(a.this.a, a.this.b);
            }
        });
        private int c;

        a(d dVar) {
            this.a = dVar;
        }

        /* Access modifiers changed, original: 0000 */
        public <R> DecodeJob<R> a(g gVar, Object obj, k kVar, com.bumptech.glide.load.c cVar, int i, int i2, Class<?> cls, Class<R> cls2, Priority priority, g gVar2, Map<Class<?>, i<?>> map, boolean z, boolean z2, boolean z3, com.bumptech.glide.load.f fVar, a<R> aVar) {
            DecodeJob decodeJob = (DecodeJob) this.b.acquire();
            int i3 = this.c;
            this.c = i3 + 1;
            return decodeJob.a(gVar, obj, kVar, cVar, i, i2, cls, cls2, priority, gVar2, map, z, z2, z3, fVar, aVar, i3);
        }
    }

    static class b {
        final com.bumptech.glide.load.engine.c.a a;
        final com.bumptech.glide.load.engine.c.a b;
        final com.bumptech.glide.load.engine.c.a c;
        final com.bumptech.glide.load.engine.c.a d;
        final j e;
        final Pool<i<?>> f = com.bumptech.glide.f.a.a.a(150, new com.bumptech.glide.f.a.a.a<i<?>>() {
            /* renamed from: a */
            public i<?> b() {
                return new i(b.this.a, b.this.b, b.this.c, b.this.d, b.this.e, b.this.f);
            }
        });

        b(com.bumptech.glide.load.engine.c.a aVar, com.bumptech.glide.load.engine.c.a aVar2, com.bumptech.glide.load.engine.c.a aVar3, com.bumptech.glide.load.engine.c.a aVar4, j jVar) {
            this.a = aVar;
            this.b = aVar2;
            this.c = aVar3;
            this.d = aVar4;
            this.e = jVar;
        }

        /* Access modifiers changed, original: 0000 */
        public <R> i<R> a(com.bumptech.glide.load.c cVar, boolean z, boolean z2, boolean z3) {
            return ((i) this.f.acquire()).a(cVar, z, z2, z3);
        }
    }

    private static class c implements d {
        private final com.bumptech.glide.load.engine.b.a.a a;
        private volatile com.bumptech.glide.load.engine.b.a b;

        public c(com.bumptech.glide.load.engine.b.a.a aVar) {
            this.a = aVar;
        }

        public com.bumptech.glide.load.engine.b.a a() {
            if (this.b == null) {
                synchronized (this) {
                    if (this.b == null) {
                        this.b = this.a.a();
                    }
                    if (this.b == null) {
                        this.b = new com.bumptech.glide.load.engine.b.b();
                    }
                }
            }
            return this.b;
        }
    }

    public static class d {
        private final i<?> a;
        private final com.bumptech.glide.request.g b;

        public d(com.bumptech.glide.request.g gVar, i<?> iVar) {
            this.b = gVar;
            this.a = iVar;
        }

        public void a() {
            this.a.b(this.b);
        }
    }

    private static class e implements IdleHandler {
        private final Map<com.bumptech.glide.load.c, WeakReference<m<?>>> a;
        private final ReferenceQueue<m<?>> b;

        public e(Map<com.bumptech.glide.load.c, WeakReference<m<?>>> map, ReferenceQueue<m<?>> referenceQueue) {
            this.a = map;
            this.b = referenceQueue;
        }

        public boolean queueIdle() {
            f fVar = (f) this.b.poll();
            if (fVar != null) {
                this.a.remove(fVar.a);
            }
            return true;
        }
    }

    private static class f extends WeakReference<m<?>> {
        final com.bumptech.glide.load.c a;

        public f(com.bumptech.glide.load.c cVar, m<?> mVar, ReferenceQueue<? super m<?>> referenceQueue) {
            super(mVar, referenceQueue);
            this.a = cVar;
        }
    }

    public h(com.bumptech.glide.load.engine.b.h hVar, com.bumptech.glide.load.engine.b.a.a aVar, com.bumptech.glide.load.engine.c.a aVar2, com.bumptech.glide.load.engine.c.a aVar3, com.bumptech.glide.load.engine.c.a aVar4, com.bumptech.glide.load.engine.c.a aVar5) {
        this(hVar, aVar, aVar2, aVar3, aVar4, aVar5, null, null, null, null, null, null);
    }

    h(com.bumptech.glide.load.engine.b.h hVar, com.bumptech.glide.load.engine.b.a.a aVar, com.bumptech.glide.load.engine.c.a aVar2, com.bumptech.glide.load.engine.c.a aVar3, com.bumptech.glide.load.engine.c.a aVar4, com.bumptech.glide.load.engine.c.a aVar5, Map<com.bumptech.glide.load.c, i<?>> map, l lVar, Map<com.bumptech.glide.load.c, WeakReference<m<?>>> map2, b bVar, a aVar6, t tVar) {
        b bVar2;
        com.bumptech.glide.load.engine.b.h hVar2 = hVar;
        this.c = hVar2;
        this.g = new c(aVar);
        this.e = map2 == null ? new HashMap() : map2;
        this.b = lVar == null ? new l() : lVar;
        this.a = map == null ? new HashMap() : map;
        if (bVar == null) {
            b bVar3 = new b(aVar2, aVar3, aVar4, aVar5, this);
        } else {
            bVar2 = bVar;
        }
        this.d = bVar2;
        this.h = aVar6 == null ? new a(this.g) : aVar6;
        this.f = tVar == null ? new t() : tVar;
        hVar2.a(this);
    }

    public <R> d a(g gVar, Object obj, com.bumptech.glide.load.c cVar, int i, int i2, Class<?> cls, Class<R> cls2, Priority priority, g gVar2, Map<Class<?>, i<?>> map, boolean z, boolean z2, com.bumptech.glide.load.f fVar, boolean z3, boolean z4, boolean z5, boolean z6, com.bumptech.glide.request.g gVar3) {
        boolean z7 = z3;
        com.bumptech.glide.request.g gVar4 = gVar3;
        com.bumptech.glide.f.i.a();
        long a = com.bumptech.glide.f.d.a();
        com.bumptech.glide.load.c a2 = this.b.a(obj, cVar, i, i2, map, cls, cls2, fVar);
        m b = b(a2, z7);
        if (b != null) {
            gVar4.a(b, DataSource.MEMORY_CACHE);
            if (Log.isLoggable("Engine", 2)) {
                a("Loaded resource from cache", a, a2);
            }
            return null;
        }
        b = a(a2, z7);
        if (b != null) {
            gVar4.a(b, DataSource.MEMORY_CACHE);
            if (Log.isLoggable("Engine", 2)) {
                a("Loaded resource from active resources", a, a2);
            }
            return null;
        }
        i iVar = (i) this.a.get(a2);
        if (iVar != null) {
            iVar.a(gVar4);
            if (Log.isLoggable("Engine", 2)) {
                a("Added to existing load", a, a2);
            }
            return new d(gVar4, iVar);
        }
        i a3 = this.d.a(a2, z7, z4, z5);
        long j = a;
        int i3 = 2;
        DecodeJob a4 = this.h.a(gVar, obj, a2, cVar, i, i2, cls, cls2, priority, gVar2, map, z, z2, z6, fVar, a3);
        this.a.put(a2, a3);
        a3.a(gVar4);
        a3.b(a4);
        if (Log.isLoggable("Engine", i3)) {
            a("Started new load", j, a2);
        }
        return new d(gVar4, a3);
    }

    private static void a(String str, long j, com.bumptech.glide.load.c cVar) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(" in ");
        stringBuilder.append(com.bumptech.glide.f.d.a(j));
        stringBuilder.append("ms, key: ");
        stringBuilder.append(cVar);
        Log.v("Engine", stringBuilder.toString());
    }

    private m<?> a(com.bumptech.glide.load.c cVar, boolean z) {
        m<?> mVar = null;
        if (!z) {
            return null;
        }
        WeakReference weakReference = (WeakReference) this.e.get(cVar);
        if (weakReference != null) {
            mVar = (m) weakReference.get();
            if (mVar != null) {
                mVar.f();
            } else {
                this.e.remove(cVar);
            }
        }
        return mVar;
    }

    private m<?> b(com.bumptech.glide.load.c cVar, boolean z) {
        if (!z) {
            return null;
        }
        m a = a(cVar);
        if (a != null) {
            a.f();
            this.e.put(cVar, new f(cVar, a, a()));
        }
        return a;
    }

    private m<?> a(com.bumptech.glide.load.c cVar) {
        q a = this.c.a(cVar);
        if (a == null) {
            return null;
        }
        if (a instanceof m) {
            return (m) a;
        }
        return new m(a, true);
    }

    public void a(q<?> qVar) {
        com.bumptech.glide.f.i.a();
        if (qVar instanceof m) {
            ((m) qVar).g();
            return;
        }
        throw new IllegalArgumentException("Cannot release anything but an EngineResource");
    }

    public void a(com.bumptech.glide.load.c cVar, m<?> mVar) {
        com.bumptech.glide.f.i.a();
        if (mVar != null) {
            mVar.a(cVar, this);
            if (mVar.a()) {
                this.e.put(cVar, new f(cVar, mVar, a()));
            }
        }
        this.a.remove(cVar);
    }

    public void a(i iVar, com.bumptech.glide.load.c cVar) {
        com.bumptech.glide.f.i.a();
        if (iVar.equals((i) this.a.get(cVar))) {
            this.a.remove(cVar);
        }
    }

    public void b(q<?> qVar) {
        com.bumptech.glide.f.i.a();
        this.f.a(qVar);
    }

    public void b(com.bumptech.glide.load.c cVar, m mVar) {
        com.bumptech.glide.f.i.a();
        this.e.remove(cVar);
        if (mVar.a()) {
            this.c.b(cVar, mVar);
        } else {
            this.f.a(mVar);
        }
    }

    private ReferenceQueue<m<?>> a() {
        if (this.i == null) {
            this.i = new ReferenceQueue();
            Looper.myQueue().addIdleHandler(new e(this.e, this.i));
        }
        return this.i;
    }
}
