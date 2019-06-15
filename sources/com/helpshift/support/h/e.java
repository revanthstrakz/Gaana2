package com.helpshift.support.h;

import com.helpshift.q.d;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class e implements d {
    private Map<String, Serializable> a = new HashMap();
    private Lock b;
    private Lock c;

    private static class a {
        static final e a = new e();
    }

    e() {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        this.b = reentrantReadWriteLock.readLock();
        this.c = reentrantReadWriteLock.writeLock();
    }

    public static e b() {
        return a.a;
    }

    public boolean a(String str, Serializable serializable) {
        boolean z = false;
        if (str == null) {
            return false;
        }
        this.c.lock();
        Serializable serializable2 = (Serializable) this.a.put(str, serializable);
        this.c.unlock();
        if (serializable2 != null) {
            z = true;
        }
        return z;
    }

    public void b(String str, Serializable serializable) {
        a(str, serializable);
    }

    /* renamed from: c */
    public Serializable a(String str) {
        if (str == null) {
            return null;
        }
        this.b.lock();
        Serializable serializable = (Serializable) this.a.get(str);
        this.b.unlock();
        return serializable;
    }

    public void b(String str) {
        if (str != null) {
            this.c.lock();
            this.a.remove(str);
            this.c.unlock();
        }
    }

    public void a() {
        this.c.lock();
        this.a.clear();
        this.c.unlock();
    }
}
