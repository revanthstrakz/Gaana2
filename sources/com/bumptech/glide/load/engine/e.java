package com.bumptech.glide.load.engine;

import com.bumptech.glide.Priority;
import com.bumptech.glide.Registry.NoModelLoaderAvailableException;
import com.bumptech.glide.Registry.NoSourceEncoderAvailableException;
import com.bumptech.glide.g;
import com.bumptech.glide.load.b.n;
import com.bumptech.glide.load.b.n.a;
import com.bumptech.glide.load.c;
import com.bumptech.glide.load.f;
import com.bumptech.glide.load.h;
import com.bumptech.glide.load.i;
import com.bumptech.glide.load.resource.b;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

final class e<Transcode> {
    private final List<a<?>> a = new ArrayList();
    private final List<c> b = new ArrayList();
    private g c;
    private Object d;
    private int e;
    private int f;
    private Class<?> g;
    private d h;
    private f i;
    private Map<Class<?>, i<?>> j;
    private Class<Transcode> k;
    private boolean l;
    private boolean m;
    private c n;
    private Priority o;
    private g p;
    private boolean q;
    private boolean r;

    e() {
    }

    /* Access modifiers changed, original: 0000 */
    public <R> e<R> a(g gVar, Object obj, c cVar, int i, int i2, g gVar2, Class<?> cls, Class<R> cls2, Priority priority, f fVar, Map<Class<?>, i<?>> map, boolean z, boolean z2, d dVar) {
        this.c = gVar;
        this.d = obj;
        this.n = cVar;
        this.e = i;
        this.f = i2;
        this.p = gVar2;
        this.g = cls;
        this.h = dVar;
        this.k = cls2;
        this.o = priority;
        this.i = fVar;
        this.j = map;
        this.q = z;
        this.r = z2;
        return this;
    }

    /* Access modifiers changed, original: 0000 */
    public void a() {
        this.c = null;
        this.d = null;
        this.n = null;
        this.g = null;
        this.k = null;
        this.i = null;
        this.o = null;
        this.j = null;
        this.p = null;
        this.a.clear();
        this.l = false;
        this.b.clear();
        this.m = false;
    }

    /* Access modifiers changed, original: 0000 */
    public com.bumptech.glide.load.engine.b.a b() {
        return this.h.a();
    }

    /* Access modifiers changed, original: 0000 */
    public g c() {
        return this.p;
    }

    /* Access modifiers changed, original: 0000 */
    public Priority d() {
        return this.o;
    }

    /* Access modifiers changed, original: 0000 */
    public f e() {
        return this.i;
    }

    /* Access modifiers changed, original: 0000 */
    public c f() {
        return this.n;
    }

    /* Access modifiers changed, original: 0000 */
    public int g() {
        return this.e;
    }

    /* Access modifiers changed, original: 0000 */
    public int h() {
        return this.f;
    }

    /* Access modifiers changed, original: 0000 */
    public List<Class<?>> i() {
        return this.c.d().b(this.d.getClass(), this.g, this.k);
    }

    /* Access modifiers changed, original: 0000 */
    public boolean a(Class<?> cls) {
        return b((Class) cls) != null;
    }

    /* Access modifiers changed, original: 0000 */
    public <Data> o<Data, ?, Transcode> b(Class<Data> cls) {
        return this.c.d().a((Class) cls, this.g, this.k);
    }

    /* Access modifiers changed, original: 0000 */
    public boolean j() {
        return this.r;
    }

    /* Access modifiers changed, original: 0000 */
    public <Z> i<Z> c(Class<Z> cls) {
        i<Z> iVar = (i) this.j.get(cls);
        if (iVar == null) {
            for (Entry entry : this.j.entrySet()) {
                if (((Class) entry.getKey()).isAssignableFrom(cls)) {
                    iVar = (i) entry.getValue();
                    break;
                }
            }
        }
        if (iVar != null) {
            return iVar;
        }
        if (!this.j.isEmpty() || !this.q) {
            return b.a();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Missing transformation for ");
        stringBuilder.append(cls);
        stringBuilder.append(". If you wish to ignore unknown resource types, use the optional transformation methods.");
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    /* Access modifiers changed, original: 0000 */
    public boolean a(q<?> qVar) {
        return this.c.d().a((q) qVar);
    }

    /* Access modifiers changed, original: 0000 */
    public <Z> h<Z> b(q<Z> qVar) {
        return this.c.d().b((q) qVar);
    }

    /* Access modifiers changed, original: 0000 */
    public List<n<File, ?>> a(File file) throws NoModelLoaderAvailableException {
        return this.c.d().c(file);
    }

    /* Access modifiers changed, original: 0000 */
    public boolean a(c cVar) {
        List k = k();
        int size = k.size();
        for (int i = 0; i < size; i++) {
            if (((a) k.get(i)).a.equals(cVar)) {
                return true;
            }
        }
        return false;
    }

    /* Access modifiers changed, original: 0000 */
    public List<a<?>> k() {
        if (!this.l) {
            this.l = true;
            this.a.clear();
            List c = this.c.d().c(this.d);
            int size = c.size();
            for (int i = 0; i < size; i++) {
                a a = ((n) c.get(i)).a(this.d, this.e, this.f, this.i);
                if (a != null) {
                    this.a.add(a);
                }
            }
        }
        return this.a;
    }

    /* Access modifiers changed, original: 0000 */
    public List<c> l() {
        if (!this.m) {
            this.m = true;
            this.b.clear();
            List k = k();
            int size = k.size();
            for (int i = 0; i < size; i++) {
                a aVar = (a) k.get(i);
                if (!this.b.contains(aVar.a)) {
                    this.b.add(aVar.a);
                }
                for (int i2 = 0; i2 < aVar.b.size(); i2++) {
                    if (!this.b.contains(aVar.b.get(i2))) {
                        this.b.add(aVar.b.get(i2));
                    }
                }
            }
        }
        return this.b;
    }

    /* Access modifiers changed, original: 0000 */
    public <X> com.bumptech.glide.load.a<X> a(X x) throws NoSourceEncoderAvailableException {
        return this.c.d().a((Object) x);
    }
}
