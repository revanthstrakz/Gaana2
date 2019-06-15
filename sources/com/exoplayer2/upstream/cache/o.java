package com.exoplayer2.upstream.cache;

import android.os.ConditionVariable;
import com.exoplayer2.upstream.cache.Cache.CacheException;
import com.exoplayer2.upstream.cache.Cache.a;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public final class o implements Cache {
    private static final HashSet<File> a = new HashSet();
    private static boolean b;
    private final File c;
    private final b d;
    private final h e;
    private final HashMap<String, ArrayList<a>> f;
    private long g;
    private boolean h;

    public o(File file, b bVar, byte[] bArr) {
        this(file, bVar, bArr, bArr != null);
    }

    public o(File file, b bVar, byte[] bArr, boolean z) {
        this(file, bVar, new h(file, bArr, z));
    }

    o(File file, b bVar, h hVar) {
        this.c = file;
        this.d = bVar;
        this.e = hVar;
        this.f = new HashMap();
        final ConditionVariable conditionVariable = new ConditionVariable();
        new Thread("SimpleCache.initialize()") {
            public void run() {
                synchronized (o.this) {
                    conditionVariable.open();
                    o.this.c();
                    o.this.d.a();
                }
            }
        }.start();
        conditionVariable.block();
    }

    public synchronized void b() {
        if (!this.h) {
            this.f.clear();
            d();
            try {
                this.e.b();
                b(this.c);
            } catch (CacheException e) {
                try {
                    Log.e("SimpleCache", "Storing index file failed", e);
                    b(this.c);
                } catch (Throwable th) {
                    b(this.c);
                    this.h = true;
                }
            }
            this.h = true;
            return;
        }
        return;
    }

    public synchronized long a() {
        Assertions.checkState(this.h ^ 1);
        return this.g;
    }

    /* renamed from: d */
    public synchronized p a(String str, long j) throws InterruptedException, CacheException {
        p e;
        while (true) {
            e = b(str, j);
            if (e == null) {
                wait();
            }
        }
        return e;
    }

    /* renamed from: e */
    public synchronized p b(String str, long j) throws CacheException {
        Assertions.checkState(this.h ^ 1);
        p f = f(str, j);
        if (f.d) {
            try {
                d b = this.e.b(str).b(f);
                a(f, b);
                return b;
            } catch (CacheException unused) {
                return f;
            }
        }
        g a = this.e.a(str);
        if (a.b()) {
            return null;
        }
        a.a(true);
        return f;
    }

    public synchronized File a(String str, long j, long j2) throws CacheException {
        g b;
        Assertions.checkState(this.h ^ 1);
        b = this.e.b(str);
        Assertions.checkNotNull(b);
        Assertions.checkState(b.b());
        if (!this.c.exists()) {
            this.c.mkdirs();
            d();
        }
        this.d.a(this, str, j, j2);
        return p.a(this.c, b.a, j, System.currentTimeMillis());
    }

    public synchronized void a(File file) throws CacheException {
        boolean z = true;
        Assertions.checkState(this.h ^ 1);
        p a = p.a(file, this.e);
        Assertions.checkState(a != null);
        g b = this.e.b(a.a);
        Assertions.checkNotNull(b);
        Assertions.checkState(b.b());
        if (!file.exists()) {
            return;
        }
        if (file.length() == 0) {
            file.delete();
            return;
        }
        long a2 = j.a(b.a());
        if (a2 != -1) {
            if (a.b + a.c > a2) {
                z = false;
            }
            Assertions.checkState(z);
        }
        a(a);
        this.e.b();
        notifyAll();
    }

    public synchronized void a(d dVar) {
        Assertions.checkState(this.h ^ 1);
        g b = this.e.b(dVar.a);
        Assertions.checkNotNull(b);
        Assertions.checkState(b.b());
        b.a(false);
        this.e.d(b.b);
        notifyAll();
    }

    public synchronized void c(String str, long j) throws CacheException {
        k kVar = new k();
        j.a(kVar, j);
        a(str, kVar);
    }

    public synchronized long a(String str) {
        return j.a(b(str));
    }

    public synchronized void a(String str, k kVar) throws CacheException {
        Assertions.checkState(this.h ^ 1);
        this.e.a(str, kVar);
        this.e.b();
    }

    public synchronized i b(String str) {
        Assertions.checkState(this.h ^ 1);
        return this.e.e(str);
    }

    private p f(String str, long j) throws CacheException {
        g b = this.e.b(str);
        if (b == null) {
            return p.b(str, j);
        }
        p a;
        while (true) {
            a = b.a(j);
            if (!a.d || a.e.exists()) {
                return a;
            }
            d();
        }
        return a;
    }

    private void c() {
        if (this.c.exists()) {
            this.e.a();
            File[] listFiles = this.c.listFiles();
            if (listFiles != null) {
                for (File file : listFiles) {
                    if (!file.getName().equals(CachedContentIndex.FILE_NAME)) {
                        p a = file.length() > 0 ? p.a(file, this.e) : null;
                        if (a != null) {
                            a(a);
                        } else {
                            file.delete();
                        }
                    }
                }
                this.e.d();
                try {
                    this.e.b();
                } catch (CacheException e) {
                    Log.e("SimpleCache", "Storing index file failed", e);
                }
                return;
            }
            return;
        }
        this.c.mkdirs();
    }

    private void a(p pVar) {
        this.e.a(pVar.a).a(pVar);
        this.g += pVar.c;
        b(pVar);
    }

    private void b(d dVar) {
        g b = this.e.b(dVar.a);
        if (b != null && b.a(dVar)) {
            this.g -= dVar.c;
            this.e.d(b.b);
            c(dVar);
        }
    }

    private void d() {
        ArrayList arrayList = new ArrayList();
        for (g c : this.e.c()) {
            Iterator it = c.c().iterator();
            while (it.hasNext()) {
                d dVar = (d) it.next();
                if (!dVar.e.exists()) {
                    arrayList.add(dVar);
                }
            }
        }
        for (int i = 0; i < arrayList.size(); i++) {
            b((d) arrayList.get(i));
        }
    }

    private void c(d dVar) {
        ArrayList arrayList = (ArrayList) this.f.get(dVar.a);
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                ((a) arrayList.get(size)).b(this, dVar);
            }
        }
        this.d.b(this, dVar);
    }

    private void b(p pVar) {
        ArrayList arrayList = (ArrayList) this.f.get(pVar.a);
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                ((a) arrayList.get(size)).a(this, pVar);
            }
        }
        this.d.a(this, pVar);
    }

    private void a(p pVar, d dVar) {
        ArrayList arrayList = (ArrayList) this.f.get(pVar.a);
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                ((a) arrayList.get(size)).a(this, pVar, dVar);
            }
        }
        this.d.a(this, pVar, dVar);
    }

    private static synchronized void b(File file) {
        synchronized (o.class) {
            if (!b) {
                a.remove(file.getAbsoluteFile());
            }
        }
    }
}
