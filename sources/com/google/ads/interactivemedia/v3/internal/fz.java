package com.google.ads.interactivemedia.v3.internal;

import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

public final class fz {
    private static final hw<?> a = new hw<Object>() {
    };
    private final ThreadLocal<Map<hw<?>, a<?>>> b;
    private final Map<hw<?>, gp<?>> c;
    private final List<gq> d;
    private final gy e;
    private final gz f;
    private final fy g;
    private final boolean h;
    private final boolean i;
    private final boolean j;
    private final boolean k;
    private final boolean l;
    private final hk m;

    static class a<T> extends gp<T> {
        private gp<T> a;

        a() {
        }

        public void a(gp<T> gpVar) {
            if (this.a != null) {
                throw new AssertionError();
            }
            this.a = gpVar;
        }

        public T read(hx hxVar) throws IOException {
            if (this.a != null) {
                return this.a.read(hxVar);
            }
            throw new IllegalStateException();
        }

        public void write(hz hzVar, T t) throws IOException {
            if (this.a == null) {
                throw new IllegalStateException();
            }
            this.a.write(hzVar, t);
        }
    }

    public fz() {
        this(gz.a, fx.IDENTITY, Collections.emptyMap(), false, false, false, true, false, false, false, go.DEFAULT, Collections.emptyList());
    }

    fz(gz gzVar, fy fyVar, Map<Type, gb<?>> map, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, go goVar, List<gq> list) {
        this.b = new ThreadLocal();
        this.c = new ConcurrentHashMap();
        this.e = new gy(map);
        this.f = gzVar;
        this.g = fyVar;
        this.h = z;
        this.j = z3;
        this.i = z4;
        this.k = z5;
        this.l = z6;
        ArrayList arrayList = new ArrayList();
        arrayList.add(hu.Y);
        arrayList.add(ho.a);
        arrayList.add(gzVar);
        arrayList.addAll(list);
        arrayList.add(hu.D);
        arrayList.add(hu.m);
        arrayList.add(hu.g);
        arrayList.add(hu.i);
        arrayList.add(hu.k);
        gp a = a(goVar);
        arrayList.add(hu.a(Long.TYPE, Long.class, a));
        arrayList.add(hu.a(Double.TYPE, Double.class, a(z7)));
        arrayList.add(hu.a(Float.TYPE, Float.class, b(z7)));
        arrayList.add(hu.x);
        arrayList.add(hu.o);
        arrayList.add(hu.q);
        arrayList.add(hu.a(AtomicLong.class, a(a)));
        arrayList.add(hu.a(AtomicLongArray.class, b(a)));
        arrayList.add(hu.s);
        arrayList.add(hu.z);
        arrayList.add(hu.F);
        arrayList.add(hu.H);
        arrayList.add(hu.a(BigDecimal.class, hu.B));
        arrayList.add(hu.a(BigInteger.class, hu.C));
        arrayList.add(hu.J);
        arrayList.add(hu.L);
        arrayList.add(hu.P);
        arrayList.add(hu.R);
        arrayList.add(hu.W);
        arrayList.add(hu.N);
        arrayList.add(hu.d);
        arrayList.add(hj.a);
        arrayList.add(hu.U);
        arrayList.add(hr.a);
        arrayList.add(hq.a);
        arrayList.add(hu.S);
        arrayList.add(hh.a);
        arrayList.add(hu.b);
        arrayList.add(new hi(this.e));
        arrayList.add(new hn(this.e, z2));
        this.m = new hk(this.e);
        arrayList.add(this.m);
        arrayList.add(hu.Z);
        arrayList.add(new hp(this.e, fyVar, gzVar, this.m));
        this.d = Collections.unmodifiableList(arrayList);
    }

    private gp<Number> a(boolean z) {
        if (z) {
            return hu.v;
        }
        return new gp<Number>() {
            /* renamed from: a */
            public Double read(hx hxVar) throws IOException {
                if (hxVar.f() != hy.NULL) {
                    return Double.valueOf(hxVar.k());
                }
                hxVar.j();
                return null;
            }

            /* renamed from: a */
            public void write(hz hzVar, Number number) throws IOException {
                if (number == null) {
                    hzVar.f();
                    return;
                }
                fz.a(number.doubleValue());
                hzVar.a(number);
            }
        };
    }

    private gp<Number> b(boolean z) {
        if (z) {
            return hu.u;
        }
        return new gp<Number>() {
            /* renamed from: a */
            public Float read(hx hxVar) throws IOException {
                if (hxVar.f() != hy.NULL) {
                    return Float.valueOf((float) hxVar.k());
                }
                hxVar.j();
                return null;
            }

            /* renamed from: a */
            public void write(hz hzVar, Number number) throws IOException {
                if (number == null) {
                    hzVar.f();
                    return;
                }
                fz.a((double) number.floatValue());
                hzVar.a(number);
            }
        };
    }

    static void a(double d) {
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(d);
            stringBuilder.append(" is not a valid double value as per JSON specification. To override this");
            stringBuilder.append(" behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    private static gp<Number> a(go goVar) {
        if (goVar == go.DEFAULT) {
            return hu.t;
        }
        return new gp<Number>() {
            /* renamed from: a */
            public Number read(hx hxVar) throws IOException {
                if (hxVar.f() != hy.NULL) {
                    return Long.valueOf(hxVar.l());
                }
                hxVar.j();
                return null;
            }

            /* renamed from: a */
            public void write(hz hzVar, Number number) throws IOException {
                if (number == null) {
                    hzVar.f();
                } else {
                    hzVar.b(number.toString());
                }
            }
        };
    }

    private static gp<AtomicLong> a(final gp<Number> gpVar) {
        return new gp<AtomicLong>() {
            /* renamed from: a */
            public void write(hz hzVar, AtomicLong atomicLong) throws IOException {
                gpVar.write(hzVar, Long.valueOf(atomicLong.get()));
            }

            /* renamed from: a */
            public AtomicLong read(hx hxVar) throws IOException {
                return new AtomicLong(((Number) gpVar.read(hxVar)).longValue());
            }
        }.nullSafe();
    }

    private static gp<AtomicLongArray> b(final gp<Number> gpVar) {
        return new gp<AtomicLongArray>() {
            /* renamed from: a */
            public void write(hz hzVar, AtomicLongArray atomicLongArray) throws IOException {
                hzVar.b();
                int length = atomicLongArray.length();
                for (int i = 0; i < length; i++) {
                    gpVar.write(hzVar, Long.valueOf(atomicLongArray.get(i)));
                }
                hzVar.c();
            }

            /* renamed from: a */
            public AtomicLongArray read(hx hxVar) throws IOException {
                ArrayList arrayList = new ArrayList();
                hxVar.a();
                while (hxVar.e()) {
                    arrayList.add(Long.valueOf(((Number) gpVar.read(hxVar)).longValue()));
                }
                hxVar.b();
                int size = arrayList.size();
                AtomicLongArray atomicLongArray = new AtomicLongArray(size);
                for (int i = 0; i < size; i++) {
                    atomicLongArray.set(i, ((Long) arrayList.get(i)).longValue());
                }
                return atomicLongArray;
            }
        }.nullSafe();
    }

    /* JADX WARNING: Missing block: B:19:0x0050, code skipped:
            r2.a(r4);
            r5.c.put(r6, r4);
     */
    public <T> com.google.ads.interactivemedia.v3.internal.gp<T> a(com.google.ads.interactivemedia.v3.internal.hw<T> r6) {
        /*
        r5 = this;
        r0 = r5.c;
        if (r6 != 0) goto L_0x0007;
    L_0x0004:
        r1 = a;
        goto L_0x0008;
    L_0x0007:
        r1 = r6;
    L_0x0008:
        r0 = r0.get(r1);
        r0 = (com.google.ads.interactivemedia.v3.internal.gp) r0;
        if (r0 == 0) goto L_0x0011;
    L_0x0010:
        return r0;
    L_0x0011:
        r0 = r5.b;
        r0 = r0.get();
        r0 = (java.util.Map) r0;
        r1 = 0;
        if (r0 != 0) goto L_0x0027;
    L_0x001c:
        r0 = new java.util.HashMap;
        r0.<init>();
        r1 = r5.b;
        r1.set(r0);
        r1 = 1;
    L_0x0027:
        r2 = r0.get(r6);
        r2 = (com.google.ads.interactivemedia.v3.internal.fz.a) r2;
        if (r2 == 0) goto L_0x0030;
    L_0x002f:
        return r2;
    L_0x0030:
        r2 = new com.google.ads.interactivemedia.v3.internal.fz$a;	 Catch:{ all -> 0x007a }
        r2.<init>();	 Catch:{ all -> 0x007a }
        r0.put(r6, r2);	 Catch:{ all -> 0x007a }
        r3 = r5.d;	 Catch:{ all -> 0x007a }
        r3 = r3.iterator();	 Catch:{ all -> 0x007a }
    L_0x003e:
        r4 = r3.hasNext();	 Catch:{ all -> 0x007a }
        if (r4 == 0) goto L_0x0063;
    L_0x0044:
        r4 = r3.next();	 Catch:{ all -> 0x007a }
        r4 = (com.google.ads.interactivemedia.v3.internal.gq) r4;	 Catch:{ all -> 0x007a }
        r4 = r4.a(r5, r6);	 Catch:{ all -> 0x007a }
        if (r4 == 0) goto L_0x003e;
    L_0x0050:
        r2.a(r4);	 Catch:{ all -> 0x007a }
        r2 = r5.c;	 Catch:{ all -> 0x007a }
        r2.put(r6, r4);	 Catch:{ all -> 0x007a }
        r0.remove(r6);
        if (r1 == 0) goto L_0x0062;
    L_0x005d:
        r6 = r5.b;
        r6.remove();
    L_0x0062:
        return r4;
    L_0x0063:
        r2 = new java.lang.IllegalArgumentException;	 Catch:{ all -> 0x007a }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x007a }
        r3.<init>();	 Catch:{ all -> 0x007a }
        r4 = "GSON cannot handle ";
        r3.append(r4);	 Catch:{ all -> 0x007a }
        r3.append(r6);	 Catch:{ all -> 0x007a }
        r3 = r3.toString();	 Catch:{ all -> 0x007a }
        r2.<init>(r3);	 Catch:{ all -> 0x007a }
        throw r2;	 Catch:{ all -> 0x007a }
    L_0x007a:
        r2 = move-exception;
        r0.remove(r6);
        if (r1 == 0) goto L_0x0085;
    L_0x0080:
        r6 = r5.b;
        r6.remove();
    L_0x0085:
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.fz.a(com.google.ads.interactivemedia.v3.internal.hw):com.google.ads.interactivemedia.v3.internal.gp");
    }

    public <T> gp<T> a(gq gqVar, hw<T> hwVar) {
        if (!this.d.contains(gqVar)) {
            gqVar = this.m;
        }
        Object obj = null;
        for (gq gqVar2 : this.d) {
            if (obj != null) {
                gp a = gqVar2.a(this, hwVar);
                if (a != null) {
                    return a;
                }
            } else if (gqVar2 == gqVar) {
                obj = 1;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("GSON cannot serialize ");
        stringBuilder.append(hwVar);
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    public <T> gp<T> a(Class<T> cls) {
        return a(hw.b(cls));
    }

    public String a(Object obj) {
        if (obj == null) {
            return a(gh.a);
        }
        return a(obj, obj.getClass());
    }

    public String a(Object obj, Type type) {
        Appendable stringWriter = new StringWriter();
        a(obj, type, stringWriter);
        return stringWriter.toString();
    }

    public void a(Object obj, Type type, Appendable appendable) throws gg {
        try {
            a(obj, type, a(hf.a(appendable)));
        } catch (IOException e) {
            throw new gg(e);
        }
    }

    public void a(Object obj, Type type, hz hzVar) throws gg {
        gp a = a(hw.a(type));
        boolean g = hzVar.g();
        hzVar.b(true);
        boolean h = hzVar.h();
        hzVar.c(this.i);
        boolean i = hzVar.i();
        hzVar.d(this.h);
        try {
            a.write(hzVar, obj);
            hzVar.b(g);
            hzVar.c(h);
            hzVar.d(i);
        } catch (IOException e) {
            throw new gg(e);
        } catch (Throwable th) {
            hzVar.b(g);
            hzVar.c(h);
            hzVar.d(i);
        }
    }

    public String a(gf gfVar) {
        Appendable stringWriter = new StringWriter();
        a(gfVar, stringWriter);
        return stringWriter.toString();
    }

    public void a(gf gfVar, Appendable appendable) throws gg {
        try {
            a(gfVar, a(hf.a(appendable)));
        } catch (IOException e) {
            throw new gg(e);
        }
    }

    public hz a(Writer writer) throws IOException {
        if (this.j) {
            writer.write(")]}'\n");
        }
        hz hzVar = new hz(writer);
        if (this.k) {
            hzVar.c("  ");
        }
        hzVar.d(this.h);
        return hzVar;
    }

    public hx a(Reader reader) {
        hx hxVar = new hx(reader);
        hxVar.a(this.l);
        return hxVar;
    }

    public void a(gf gfVar, hz hzVar) throws gg {
        boolean g = hzVar.g();
        hzVar.b(true);
        boolean h = hzVar.h();
        hzVar.c(this.i);
        boolean i = hzVar.i();
        hzVar.d(this.h);
        try {
            hf.a(gfVar, hzVar);
            hzVar.b(g);
            hzVar.c(h);
            hzVar.d(i);
        } catch (IOException e) {
            throw new gg(e);
        } catch (Throwable th) {
            hzVar.b(g);
            hzVar.c(h);
            hzVar.d(i);
        }
    }

    public <T> T a(String str, Class<T> cls) throws gn {
        return he.a((Class) cls).cast(a(str, (Type) cls));
    }

    public <T> T a(String str, Type type) throws gn {
        if (str == null) {
            return null;
        }
        return a(new StringReader(str), type);
    }

    public <T> T a(Reader reader, Type type) throws gg, gn {
        hx a = a(reader);
        Object a2 = a(a, type);
        a(a2, a);
        return a2;
    }

    private static void a(Object obj, hx hxVar) {
        if (obj != null) {
            try {
                if (hxVar.f() != hy.END_DOCUMENT) {
                    throw new gg("JSON document was not fully consumed.");
                }
            } catch (ia e) {
                throw new gn(e);
            } catch (IOException e2) {
                throw new gg(e2);
            }
        }
    }

    public <T> T a(hx hxVar, Type type) throws gg, gn {
        boolean q = hxVar.q();
        boolean z = true;
        hxVar.a(true);
        try {
            hxVar.f();
            z = false;
            Object read = a(hw.a(type)).read(hxVar);
            hxVar.a(q);
            return read;
        } catch (EOFException e) {
            if (z) {
                hxVar.a(q);
                return null;
            }
            throw new gn(e);
        } catch (IllegalStateException e2) {
            throw new gn(e2);
        } catch (IOException e22) {
            throw new gn(e22);
        } catch (Throwable th) {
            hxVar.a(q);
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{serializeNulls:");
        stringBuilder.append(this.h);
        stringBuilder.append("factories:");
        stringBuilder.append(this.d);
        stringBuilder.append(",instanceCreators:");
        stringBuilder.append(this.e);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
