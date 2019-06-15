package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

public final class hn implements gq {
    final boolean a;
    private final gy b;

    private final class a<K, V> extends gp<Map<K, V>> {
        private final gp<K> b;
        private final gp<V> c;
        private final hd<? extends Map<K, V>> d;

        public a(fz fzVar, Type type, gp<K> gpVar, Type type2, gp<V> gpVar2, hd<? extends Map<K, V>> hdVar) {
            this.b = new ht(fzVar, gpVar, type);
            this.c = new ht(fzVar, gpVar2, type2);
            this.d = hdVar;
        }

        /* renamed from: a */
        public Map<K, V> read(hx hxVar) throws IOException {
            hy f = hxVar.f();
            if (f == hy.NULL) {
                hxVar.j();
                return null;
            }
            Map map = (Map) this.d.a();
            Object read;
            StringBuilder stringBuilder;
            if (f == hy.BEGIN_ARRAY) {
                hxVar.a();
                while (hxVar.e()) {
                    hxVar.a();
                    read = this.b.read(hxVar);
                    if (map.put(read, this.c.read(hxVar)) != null) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("duplicate key: ");
                        stringBuilder.append(read);
                        throw new gn(stringBuilder.toString());
                    }
                    hxVar.b();
                }
                hxVar.b();
            } else {
                hxVar.c();
                while (hxVar.e()) {
                    ha.a.a(hxVar);
                    read = this.b.read(hxVar);
                    if (map.put(read, this.c.read(hxVar)) != null) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("duplicate key: ");
                        stringBuilder.append(read);
                        throw new gn(stringBuilder.toString());
                    }
                }
                hxVar.d();
            }
            return map;
        }

        /* renamed from: a */
        public void write(hz hzVar, Map<K, V> map) throws IOException {
            if (map == null) {
                hzVar.f();
            } else if (hn.this.a) {
                ArrayList arrayList = new ArrayList(map.size());
                ArrayList arrayList2 = new ArrayList(map.size());
                int i = 0;
                int i2 = 0;
                for (Entry entry : map.entrySet()) {
                    gf toJsonTree = this.b.toJsonTree(entry.getKey());
                    arrayList.add(toJsonTree);
                    arrayList2.add(entry.getValue());
                    int i3 = (toJsonTree.g() || toJsonTree.h()) ? 1 : 0;
                    i2 |= i3;
                }
                if (i2 != 0) {
                    hzVar.b();
                    while (i < arrayList.size()) {
                        hzVar.b();
                        hf.a((gf) arrayList.get(i), hzVar);
                        this.c.write(hzVar, arrayList2.get(i));
                        hzVar.c();
                        i++;
                    }
                    hzVar.c();
                } else {
                    hzVar.d();
                    while (i < arrayList.size()) {
                        hzVar.a(a((gf) arrayList.get(i)));
                        this.c.write(hzVar, arrayList2.get(i));
                        i++;
                    }
                    hzVar.e();
                }
            } else {
                hzVar.d();
                for (Entry entry2 : map.entrySet()) {
                    hzVar.a(String.valueOf(entry2.getKey()));
                    this.c.write(hzVar, entry2.getValue());
                }
                hzVar.e();
            }
        }

        private String a(gf gfVar) {
            if (gfVar.i()) {
                gk m = gfVar.m();
                if (m.p()) {
                    return String.valueOf(m.a());
                }
                if (m.o()) {
                    return Boolean.toString(m.f());
                }
                if (m.q()) {
                    return m.b();
                }
                throw new AssertionError();
            } else if (gfVar.j()) {
                return "null";
            } else {
                throw new AssertionError();
            }
        }
    }

    public hn(gy gyVar, boolean z) {
        this.b = gyVar;
        this.a = z;
    }

    public <T> gp<T> a(fz fzVar, hw<T> hwVar) {
        Type b = hwVar.b();
        if (!Map.class.isAssignableFrom(hwVar.a())) {
            return null;
        }
        Type[] b2 = gx.b(b, gx.e(b));
        return new a(fzVar, b2[0], a(fzVar, b2[0]), b2[1], fzVar.a(hw.a(b2[1])), this.b.a((hw) hwVar));
    }

    private gp<?> a(fz fzVar, Type type) {
        if (type == Boolean.TYPE || type == Boolean.class) {
            return hu.f;
        }
        return fzVar.a(hw.a(type));
    }
}
