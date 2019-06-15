package com.bumptech.glide.load.engine;

import android.os.Build.VERSION;
import android.support.v4.os.TraceCompat;
import android.support.v4.util.Pools.Pool;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.Registry.NoResultEncoderAvailableException;
import com.bumptech.glide.g;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.EncodeStrategy;
import com.bumptech.glide.load.f;
import com.bumptech.glide.load.h;
import com.bumptech.glide.load.i;
import com.bumptech.glide.load.resource.bitmap.k;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class DecodeJob<R> implements com.bumptech.glide.f.a.a.c, com.bumptech.glide.load.engine.d.a, Comparable<DecodeJob<?>>, Runnable {
    private com.bumptech.glide.load.a.b<?> A;
    private volatile d B;
    private volatile boolean C;
    private volatile boolean D;
    final e<R> a = new e();
    final c<?> b = new c();
    com.bumptech.glide.load.c c;
    int d;
    int e;
    g f;
    f g;
    com.bumptech.glide.load.c h;
    private final List<Throwable> i = new ArrayList();
    private final com.bumptech.glide.f.a.b j = com.bumptech.glide.f.a.b.a();
    private final d k;
    private final Pool<DecodeJob<?>> l;
    private final e m = new e();
    private g n;
    private Priority o;
    private k p;
    private a<R> q;
    private int r;
    private Stage s;
    private RunReason t;
    private long u;
    private boolean v;
    private Thread w;
    private com.bumptech.glide.load.c x;
    private Object y;
    private DataSource z;

    private enum RunReason {
        INITIALIZE,
        SWITCH_TO_SOURCE_SERVICE,
        DECODE_DATA
    }

    private enum Stage {
        INITIALIZE,
        RESOURCE_CACHE,
        DATA_CACHE,
        SOURCE,
        ENCODE,
        FINISHED
    }

    interface a<R> {
        void a(DecodeJob<?> decodeJob);

        void a(GlideException glideException);

        void a(q<R> qVar, DataSource dataSource);
    }

    private static class c<Z> {
        private com.bumptech.glide.load.c a;
        private h<Z> b;
        private p<Z> c;

        c() {
        }

        /* Access modifiers changed, original: 0000 */
        public <X> void a(com.bumptech.glide.load.c cVar, h<X> hVar, p<X> pVar) {
            this.a = cVar;
            this.b = hVar;
            this.c = pVar;
        }

        /* Access modifiers changed, original: 0000 */
        public void a(d dVar, f fVar) {
            TraceCompat.beginSection("DecodeJob.encode");
            try {
                dVar.a().a(this.a, new c(this.b, this.c, fVar));
            } finally {
                this.c.a();
                TraceCompat.endSection();
            }
        }

        /* Access modifiers changed, original: 0000 */
        public boolean a() {
            return this.c != null;
        }

        /* Access modifiers changed, original: 0000 */
        public void b() {
            this.a = null;
            this.b = null;
            this.c = null;
        }
    }

    interface d {
        com.bumptech.glide.load.engine.b.a a();
    }

    private static class e {
        private boolean a;
        private boolean b;
        private boolean c;

        e() {
        }

        /* Access modifiers changed, original: declared_synchronized */
        public synchronized boolean a(boolean z) {
            this.a = true;
            return b(z);
        }

        /* Access modifiers changed, original: declared_synchronized */
        public synchronized boolean a() {
            this.b = true;
            return b(false);
        }

        /* Access modifiers changed, original: declared_synchronized */
        public synchronized boolean b() {
            this.c = true;
            return b(false);
        }

        /* Access modifiers changed, original: declared_synchronized */
        public synchronized void c() {
            this.b = false;
            this.a = false;
            this.c = false;
        }

        private boolean b(boolean z) {
            return (this.c || z || this.b) && this.a;
        }
    }

    private final class b<Z> implements a<Z> {
        private final DataSource b;

        b(DataSource dataSource) {
            this.b = dataSource;
        }

        public q<Z> a(q<Z> qVar) {
            i iVar;
            q transform;
            EncodeStrategy a;
            Class b = b(qVar);
            h hVar = null;
            if (this.b != DataSource.RESOURCE_DISK_CACHE) {
                i c = DecodeJob.this.a.c(b);
                iVar = c;
                transform = c.transform(DecodeJob.this.n, qVar, DecodeJob.this.d, DecodeJob.this.e);
            } else {
                transform = qVar;
                iVar = null;
            }
            if (!qVar.equals(transform)) {
                qVar.e();
            }
            if (DecodeJob.this.a.a(transform)) {
                hVar = DecodeJob.this.a.b(transform);
                a = hVar.a(DecodeJob.this.g);
            } else {
                a = EncodeStrategy.NONE;
            }
            h hVar2 = hVar;
            if (!DecodeJob.this.f.a(DecodeJob.this.a.a(DecodeJob.this.h) ^ 1, this.b, a)) {
                return transform;
            }
            if (hVar2 == null) {
                throw new NoResultEncoderAvailableException(transform.c().getClass());
            }
            com.bumptech.glide.load.c bVar;
            if (a == EncodeStrategy.SOURCE) {
                bVar = new b(DecodeJob.this.h, DecodeJob.this.c);
            } else if (a == EncodeStrategy.TRANSFORMED) {
                com.bumptech.glide.load.c sVar = new s(DecodeJob.this.h, DecodeJob.this.c, DecodeJob.this.d, DecodeJob.this.e, iVar, b, DecodeJob.this.g);
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Unknown strategy: ");
                stringBuilder.append(a);
                throw new IllegalArgumentException(stringBuilder.toString());
            }
            q<Z> a2 = p.a(transform);
            DecodeJob.this.b.a(bVar, hVar2, a2);
            return a2;
        }

        private Class<Z> b(q<Z> qVar) {
            return qVar.c().getClass();
        }
    }

    DecodeJob(d dVar, Pool<DecodeJob<?>> pool) {
        this.k = dVar;
        this.l = pool;
    }

    /* Access modifiers changed, original: 0000 */
    public DecodeJob<R> a(g gVar, Object obj, k kVar, com.bumptech.glide.load.c cVar, int i, int i2, Class<?> cls, Class<R> cls2, Priority priority, g gVar2, Map<Class<?>, i<?>> map, boolean z, boolean z2, boolean z3, f fVar, a<R> aVar, int i3) {
        this.a.a(gVar, obj, cVar, i, i2, gVar2, cls, cls2, priority, fVar, map, z, z2, this.k);
        this.n = gVar;
        this.c = cVar;
        this.o = priority;
        this.p = kVar;
        this.d = i;
        this.e = i2;
        this.f = gVar2;
        this.v = z3;
        this.g = fVar;
        this.q = aVar;
        this.r = i3;
        this.t = RunReason.INITIALIZE;
        return this;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean a() {
        Stage a = a(Stage.INITIALIZE);
        return a == Stage.RESOURCE_CACHE || a == Stage.DATA_CACHE;
    }

    /* Access modifiers changed, original: 0000 */
    public void a(boolean z) {
        if (this.m.a(z)) {
            g();
        }
    }

    private void e() {
        if (this.m.a()) {
            g();
        }
    }

    private void f() {
        if (this.m.b()) {
            g();
        }
    }

    private void g() {
        this.m.c();
        this.b.b();
        this.a.a();
        this.C = false;
        this.n = null;
        this.c = null;
        this.g = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.s = null;
        this.B = null;
        this.w = null;
        this.h = null;
        this.y = null;
        this.z = null;
        this.A = null;
        this.u = 0;
        this.D = false;
        this.i.clear();
        this.l.release(this);
    }

    /* renamed from: a */
    public int compareTo(DecodeJob<?> decodeJob) {
        int h = h() - decodeJob.h();
        return h == 0 ? this.r - decodeJob.r : h;
    }

    private int h() {
        return this.o.ordinal();
    }

    public void b() {
        this.D = true;
        d dVar = this.B;
        if (dVar != null) {
            dVar.b();
        }
    }

    /* JADX WARNING: Missing block: B:11:0x001a, code skipped:
            if (r0 != null) goto L_0x001c;
     */
    /* JADX WARNING: Missing block: B:12:0x001c, code skipped:
            r0.a();
     */
    /* JADX WARNING: Missing block: B:13:0x001f, code skipped:
            android.support.v4.os.TraceCompat.endSection();
     */
    /* JADX WARNING: Missing block: B:26:0x0064, code skipped:
            if (r0 != null) goto L_0x001c;
     */
    /* JADX WARNING: Missing block: B:27:0x0067, code skipped:
            return;
     */
    public void run() {
        /*
        r5 = this;
        r0 = "DecodeJob#run";
        android.support.v4.os.TraceCompat.beginSection(r0);
        r0 = r5.A;
        r1 = r5.D;	 Catch:{ Throwable -> 0x0025 }
        if (r1 == 0) goto L_0x0017;
    L_0x000b:
        r5.l();	 Catch:{ Throwable -> 0x0025 }
        if (r0 == 0) goto L_0x0013;
    L_0x0010:
        r0.a();
    L_0x0013:
        android.support.v4.os.TraceCompat.endSection();
        return;
    L_0x0017:
        r5.i();	 Catch:{ Throwable -> 0x0025 }
        if (r0 == 0) goto L_0x001f;
    L_0x001c:
        r0.a();
    L_0x001f:
        android.support.v4.os.TraceCompat.endSection();
        goto L_0x0067;
    L_0x0023:
        r1 = move-exception;
        goto L_0x0068;
    L_0x0025:
        r1 = move-exception;
        r2 = "DecodeJob";
        r3 = 3;
        r2 = android.util.Log.isLoggable(r2, r3);	 Catch:{ all -> 0x0023 }
        if (r2 == 0) goto L_0x0051;
    L_0x002f:
        r2 = "DecodeJob";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0023 }
        r3.<init>();	 Catch:{ all -> 0x0023 }
        r4 = "DecodeJob threw unexpectedly, isCancelled: ";
        r3.append(r4);	 Catch:{ all -> 0x0023 }
        r4 = r5.D;	 Catch:{ all -> 0x0023 }
        r3.append(r4);	 Catch:{ all -> 0x0023 }
        r4 = ", stage: ";
        r3.append(r4);	 Catch:{ all -> 0x0023 }
        r4 = r5.s;	 Catch:{ all -> 0x0023 }
        r3.append(r4);	 Catch:{ all -> 0x0023 }
        r3 = r3.toString();	 Catch:{ all -> 0x0023 }
        android.util.Log.d(r2, r3, r1);	 Catch:{ all -> 0x0023 }
    L_0x0051:
        r2 = r5.s;	 Catch:{ all -> 0x0023 }
        r3 = com.bumptech.glide.load.engine.DecodeJob.Stage.ENCODE;	 Catch:{ all -> 0x0023 }
        if (r2 == r3) goto L_0x005f;
    L_0x0057:
        r2 = r5.i;	 Catch:{ all -> 0x0023 }
        r2.add(r1);	 Catch:{ all -> 0x0023 }
        r5.l();	 Catch:{ all -> 0x0023 }
    L_0x005f:
        r2 = r5.D;	 Catch:{ all -> 0x0023 }
        if (r2 != 0) goto L_0x0064;
    L_0x0063:
        throw r1;	 Catch:{ all -> 0x0023 }
    L_0x0064:
        if (r0 == 0) goto L_0x001f;
    L_0x0066:
        goto L_0x001c;
    L_0x0067:
        return;
    L_0x0068:
        if (r0 == 0) goto L_0x006d;
    L_0x006a:
        r0.a();
    L_0x006d:
        android.support.v4.os.TraceCompat.endSection();
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.engine.DecodeJob.run():void");
    }

    private void i() {
        switch (this.t) {
            case INITIALIZE:
                this.s = a(Stage.INITIALIZE);
                this.B = j();
                k();
                return;
            case SWITCH_TO_SOURCE_SERVICE:
                k();
                return;
            case DECODE_DATA:
                n();
                return;
            default:
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Unrecognized run reason: ");
                stringBuilder.append(this.t);
                throw new IllegalStateException(stringBuilder.toString());
        }
    }

    private d j() {
        switch (this.s) {
            case RESOURCE_CACHE:
                return new r(this.a, this);
            case DATA_CACHE:
                return new a(this.a, this);
            case SOURCE:
                return new u(this.a, this);
            case FINISHED:
                return null;
            default:
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Unrecognized stage: ");
                stringBuilder.append(this.s);
                throw new IllegalStateException(stringBuilder.toString());
        }
    }

    private void k() {
        this.w = Thread.currentThread();
        this.u = com.bumptech.glide.f.d.a();
        boolean z = false;
        while (!this.D && this.B != null) {
            z = this.B.a();
            if (z) {
                break;
            }
            this.s = a(this.s);
            this.B = j();
            if (this.s == Stage.SOURCE) {
                c();
                return;
            }
        }
        if ((this.s == Stage.FINISHED || this.D) && !r0) {
            l();
        }
    }

    private void l() {
        m();
        this.q.a(new GlideException("Failed to load resource", new ArrayList(this.i)));
        f();
    }

    private void a(q<R> qVar, DataSource dataSource) {
        m();
        this.q.a(qVar, dataSource);
    }

    private void m() {
        this.j.b();
        if (this.C) {
            throw new IllegalStateException("Already notified");
        }
        this.C = true;
    }

    private Stage a(Stage stage) {
        switch (stage) {
            case RESOURCE_CACHE:
                if (this.f.b()) {
                    stage = Stage.DATA_CACHE;
                } else {
                    stage = a(Stage.DATA_CACHE);
                }
                return stage;
            case DATA_CACHE:
                return this.v ? Stage.FINISHED : Stage.SOURCE;
            case SOURCE:
            case FINISHED:
                return Stage.FINISHED;
            case INITIALIZE:
                if (this.f.a()) {
                    stage = Stage.RESOURCE_CACHE;
                } else {
                    stage = a(Stage.RESOURCE_CACHE);
                }
                return stage;
            default:
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Unrecognized stage: ");
                stringBuilder.append(stage);
                throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    public void c() {
        this.t = RunReason.SWITCH_TO_SOURCE_SERVICE;
        this.q.a(this);
    }

    public void a(com.bumptech.glide.load.c cVar, Object obj, com.bumptech.glide.load.a.b<?> bVar, DataSource dataSource, com.bumptech.glide.load.c cVar2) {
        this.h = cVar;
        this.y = obj;
        this.A = bVar;
        this.z = dataSource;
        this.x = cVar2;
        if (Thread.currentThread() != this.w) {
            this.t = RunReason.DECODE_DATA;
            this.q.a(this);
            return;
        }
        TraceCompat.beginSection("DecodeJob.decodeFromRetrievedData");
        try {
            n();
        } finally {
            TraceCompat.endSection();
        }
    }

    public void a(com.bumptech.glide.load.c cVar, Exception exception, com.bumptech.glide.load.a.b<?> bVar, DataSource dataSource) {
        bVar.a();
        GlideException glideException = new GlideException("Fetching data failed", (Throwable) exception);
        glideException.a(cVar, dataSource, bVar.d());
        this.i.add(glideException);
        if (Thread.currentThread() != this.w) {
            this.t = RunReason.SWITCH_TO_SOURCE_SERVICE;
            this.q.a(this);
            return;
        }
        k();
    }

    private void n() {
        if (Log.isLoggable("DecodeJob", 2)) {
            long j = this.u;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("data: ");
            stringBuilder.append(this.y);
            stringBuilder.append(", cache key: ");
            stringBuilder.append(this.h);
            stringBuilder.append(", fetcher: ");
            stringBuilder.append(this.A);
            a("Retrieved data", j, stringBuilder.toString());
        }
        q qVar = null;
        try {
            qVar = a(this.A, this.y, this.z);
        } catch (GlideException e) {
            e.a(this.x, this.z);
            this.i.add(e);
        }
        if (qVar != null) {
            b(qVar, this.z);
        } else {
            k();
        }
    }

    private void b(q<R> qVar, DataSource dataSource) {
        q qVar2;
        if (qVar2 instanceof n) {
            ((n) qVar2).a();
        }
        p pVar = null;
        if (this.b.a()) {
            qVar2 = p.a(qVar2);
            pVar = qVar2;
        }
        a(qVar2, dataSource);
        this.s = Stage.ENCODE;
        try {
            if (this.b.a()) {
                this.b.a(this.k, this.g);
            }
            if (pVar != null) {
                pVar.a();
            }
            e();
        } catch (Throwable th) {
            if (pVar != null) {
                pVar.a();
            }
            e();
        }
    }

    private <Data> q<R> a(com.bumptech.glide.load.a.b<?> bVar, Data data, DataSource dataSource) throws GlideException {
        if (data == null) {
            bVar.a();
            return null;
        }
        try {
            long a = com.bumptech.glide.f.d.a();
            q a2 = a((Object) data, dataSource);
            if (Log.isLoggable("DecodeJob", 2)) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Decoded result ");
                stringBuilder.append(a2);
                a(stringBuilder.toString(), a);
            }
            bVar.a();
            return a2;
        } catch (Throwable th) {
            bVar.a();
        }
    }

    private <Data> q<R> a(Data data, DataSource dataSource) throws GlideException {
        return a((Object) data, dataSource, this.a.b(data.getClass()));
    }

    private f a(DataSource dataSource) {
        f fVar = this.g;
        if (VERSION.SDK_INT < 26 || fVar.a(k.d) != null) {
            return fVar;
        }
        if (dataSource == DataSource.RESOURCE_DISK_CACHE || this.a.j()) {
            fVar = new f();
            fVar.a(this.g);
            fVar.a(k.d, Boolean.valueOf(true));
        }
        return fVar;
    }

    private <Data, ResourceType> q<R> a(Data data, DataSource dataSource, o<Data, ResourceType, R> oVar) throws GlideException {
        f a = a(dataSource);
        com.bumptech.glide.load.a.c b = this.n.d().b((Object) data);
        try {
            q<R> a2 = oVar.a(b, a, this.d, this.e, new b(dataSource));
            return a2;
        } finally {
            b.b();
        }
    }

    private void a(String str, long j) {
        a(str, j, null);
    }

    private void a(String str, long j, String str2) {
        String str3 = "DecodeJob";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(" in ");
        stringBuilder.append(com.bumptech.glide.f.d.a(j));
        stringBuilder.append(", load key: ");
        stringBuilder.append(this.p);
        if (str2 != null) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(", ");
            stringBuilder2.append(str2);
            str = stringBuilder2.toString();
        } else {
            str = "";
        }
        stringBuilder.append(str);
        stringBuilder.append(", thread: ");
        stringBuilder.append(Thread.currentThread().getName());
        Log.v(str3, stringBuilder.toString());
    }

    public com.bumptech.glide.f.a.b a_() {
        return this.j;
    }
}
