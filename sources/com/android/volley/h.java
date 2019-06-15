package com.android.volley;

import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class h {
    private AtomicInteger a;
    private final Map<String, Queue<Request<?>>> b;
    private final Set<Request<?>> c;
    private final PriorityBlockingQueue<Request<?>> d;
    private final PriorityBlockingQueue<Request<?>> e;
    private final a f;
    private final e g;
    private final j h;
    private f[] i;
    private b[] j;
    private List<b> k;

    public interface a {
        boolean a(Request<?> request);
    }

    public interface b<T> {
        void a(Request<T> request);
    }

    public h(a aVar, e eVar, int i, int i2, j jVar) {
        this.a = new AtomicInteger();
        this.b = new HashMap();
        this.c = new HashSet();
        this.d = new PriorityBlockingQueue();
        this.e = new PriorityBlockingQueue();
        this.k = new ArrayList();
        this.f = aVar;
        this.g = eVar;
        this.i = new f[i];
        this.h = jVar;
        this.j = new b[i2];
    }

    public h(a aVar, e eVar, int i) {
        this(aVar, eVar, i, 1);
    }

    public h(a aVar, e eVar, int i, int i2) {
        d dVar = new d(new Handler(Looper.getMainLooper()));
        this(aVar, eVar, i, i2, dVar);
    }

    public void a() {
        b();
        int i = 0;
        for (int i2 = 0; i2 < this.j.length; i2++) {
            b bVar = new b(this.d, this.e, this.f, this.h);
            this.j[i2] = bVar;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("VolleyCache# ");
            stringBuilder.append(i2);
            bVar.setName(stringBuilder.toString());
            bVar.setPriority(10);
            bVar.start();
        }
        while (i < this.i.length) {
            f fVar = new f(this.e, this.g, this.f, this.h);
            this.i[i] = fVar;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("VolleyNW#");
            stringBuilder2.append(i);
            fVar.setName(stringBuilder2.toString());
            fVar.setPriority(10);
            fVar.start();
            i++;
        }
    }

    public void b() {
        int i = 0;
        for (int i2 = 0; i2 < this.j.length; i2++) {
            if (this.j[i2] != null) {
                this.j[i2].a();
            }
        }
        while (i < this.i.length) {
            if (this.i[i] != null) {
                this.i[i].a();
            }
            i++;
        }
    }

    public int c() {
        return this.a.incrementAndGet();
    }

    public a d() {
        return this.f;
    }

    public void a(a aVar) {
        synchronized (this.c) {
            for (Request request : this.c) {
                if (aVar.a(request)) {
                    request.cancel();
                }
            }
        }
    }

    public void a(final Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Cannot cancelAll with a null tag");
        }
        a(new a() {
            public boolean a(Request<?> request) {
                return obj != null && obj.equals(request.getTag());
            }
        });
    }

    public <T> Request<T> a(Request<T> request) {
        request.setRequestQueue(this);
        synchronized (this.c) {
            this.c.add(request);
        }
        request.setSequence(c());
        request.addMarker("add-to-queue");
        if (request.shouldCache()) {
            synchronized (this.b) {
                String cacheKey = request.getCacheKey();
                if (this.b.containsKey(cacheKey)) {
                    Queue queue = (Queue) this.b.get(cacheKey);
                    if (queue == null) {
                        queue = new LinkedList();
                    }
                    queue.add(request);
                    this.b.put(cacheKey, queue);
                    if (l.b) {
                        l.a("Request for cacheKey=%s is in flight, putting on hold.", cacheKey);
                    }
                } else {
                    this.b.put(cacheKey, null);
                    this.d.add(request);
                }
            }
            return request;
        }
        this.e.add(request);
        return request;
    }

    /* Access modifiers changed, original: 0000 */
    public <T> void b(Request<T> request) {
        synchronized (this.c) {
            this.c.remove(request);
        }
        synchronized (this.k) {
            for (b a : this.k) {
                a.a(request);
            }
        }
        if (request.shouldCache()) {
            synchronized (this.b) {
                Queue queue = (Queue) this.b.remove(request.getCacheKey());
                if (queue != null) {
                    if (l.b) {
                        l.a("Releasing %d waiting requests for cacheKey=%s.", Integer.valueOf(queue.size()), r7);
                    }
                    this.d.addAll(queue);
                }
            }
        }
    }
}
