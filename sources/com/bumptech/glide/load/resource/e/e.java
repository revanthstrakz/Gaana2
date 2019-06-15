package com.bumptech.glide.load.resource.e;

import java.util.ArrayList;
import java.util.List;

public class e {
    private final List<a<?, ?>> a = new ArrayList();

    private static final class a<Z, R> {
        final d<Z, R> a;
        private final Class<Z> b;
        private final Class<R> c;

        a(Class<Z> cls, Class<R> cls2, d<Z, R> dVar) {
            this.b = cls;
            this.c = cls2;
            this.a = dVar;
        }

        public boolean a(Class<?> cls, Class<?> cls2) {
            return this.b.isAssignableFrom(cls) && cls2.isAssignableFrom(this.c);
        }
    }

    public synchronized <Z, R> void a(Class<Z> cls, Class<R> cls2, d<Z, R> dVar) {
        this.a.add(new a(cls, cls2, dVar));
    }

    public synchronized <Z, R> d<Z, R> a(Class<Z> cls, Class<R> cls2) {
        if (cls2.isAssignableFrom(cls)) {
            return f.a();
        }
        for (a aVar : this.a) {
            if (aVar.a(cls, cls2)) {
                return aVar.a;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("No transcoder registered to transcode from ");
        stringBuilder.append(cls);
        stringBuilder.append(" to ");
        stringBuilder.append(cls2);
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    public synchronized <Z, R> List<Class<R>> b(Class<Z> cls, Class<R> cls2) {
        ArrayList arrayList = new ArrayList();
        if (cls2.isAssignableFrom(cls)) {
            arrayList.add(cls2);
            return arrayList;
        }
        for (a a : this.a) {
            if (a.a(cls, cls2)) {
                arrayList.add(cls2);
            }
        }
        return arrayList;
    }
}
