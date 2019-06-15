package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class hp implements gq {
    private final gy a;
    private final fy b;
    private final gz c;
    private final hk d;

    static abstract class b {
        final String h;
        final boolean i;
        final boolean j;

        protected b(String str, boolean z, boolean z2) {
            this.h = str;
            this.i = z;
            this.j = z2;
        }

        public abstract void a(hx hxVar, Object obj) throws IOException, IllegalAccessException;

        public abstract void a(hz hzVar, Object obj) throws IOException, IllegalAccessException;

        public abstract boolean a(Object obj) throws IOException, IllegalAccessException;
    }

    public static final class a<T> extends gp<T> {
        private final hd<T> a;
        private final Map<String, b> b;

        a(hd<T> hdVar, Map<String, b> map) {
            this.a = hdVar;
            this.b = map;
        }

        public T read(hx hxVar) throws IOException {
            if (hxVar.f() == hy.NULL) {
                hxVar.j();
                return null;
            }
            Object a = this.a.a();
            try {
                hxVar.c();
                while (hxVar.e()) {
                    b bVar = (b) this.b.get(hxVar.g());
                    if (bVar != null) {
                        if (bVar.j) {
                            bVar.a(hxVar, a);
                        }
                    }
                    hxVar.n();
                }
                hxVar.d();
                return a;
            } catch (IllegalStateException e) {
                throw new gn(e);
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            }
        }

        public void write(hz hzVar, T t) throws IOException {
            if (t == null) {
                hzVar.f();
                return;
            }
            hzVar.d();
            try {
                for (b bVar : this.b.values()) {
                    if (bVar.a(t)) {
                        hzVar.a(bVar.h);
                        bVar.a(hzVar, (Object) t);
                    }
                }
                hzVar.e();
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            }
        }
    }

    public hp(gy gyVar, fy fyVar, gz gzVar, hk hkVar) {
        this.a = gyVar;
        this.b = fyVar;
        this.c = gzVar;
        this.d = hkVar;
    }

    public boolean a(Field field, boolean z) {
        return a(field, z, this.c);
    }

    static boolean a(Field field, boolean z, gz gzVar) {
        return (gzVar.a(field.getType(), z) || gzVar.a(field, z)) ? false : true;
    }

    private List<String> a(Field field) {
        gt gtVar = (gt) field.getAnnotation(gt.class);
        if (gtVar == null) {
            return Collections.singletonList(this.b.a(field));
        }
        String a = gtVar.a();
        String[] b = gtVar.b();
        if (b.length == 0) {
            return Collections.singletonList(a);
        }
        ArrayList arrayList = new ArrayList(b.length + 1);
        arrayList.add(a);
        for (Object add : b) {
            arrayList.add(add);
        }
        return arrayList;
    }

    public <T> gp<T> a(fz fzVar, hw<T> hwVar) {
        Class a = hwVar.a();
        if (Object.class.isAssignableFrom(a)) {
            return new a(this.a.a((hw) hwVar), a(fzVar, (hw) hwVar, a));
        }
        return null;
    }

    private b a(fz fzVar, Field field, String str, hw<?> hwVar, boolean z, boolean z2) {
        final fz fzVar2 = fzVar;
        final hw hwVar2 = hwVar;
        final boolean a = he.a(hwVar.a());
        final Field field2 = field;
        gs gsVar = (gs) field2.getAnnotation(gs.class);
        gp a2 = gsVar != null ? this.d.a(this.a, fzVar2, hwVar2, gsVar) : null;
        final boolean z3 = a2 != null;
        if (a2 == null) {
            a2 = fzVar2.a(hwVar2);
        }
        final gp gpVar = a2;
        return new b(str, z, z2) {
            /* Access modifiers changed, original: 0000 */
            public void a(hz hzVar, Object obj) throws IOException, IllegalAccessException {
                gp gpVar;
                obj = field2.get(obj);
                if (z3) {
                    gpVar = gpVar;
                } else {
                    gpVar = new ht(fzVar2, gpVar, hwVar2.b());
                }
                gpVar.write(hzVar, obj);
            }

            /* Access modifiers changed, original: 0000 */
            public void a(hx hxVar, Object obj) throws IOException, IllegalAccessException {
                Object read = gpVar.read(hxVar);
                if (read != null || !a) {
                    field2.set(obj, read);
                }
            }

            public boolean a(Object obj) throws IOException, IllegalAccessException {
                boolean z = false;
                if (!this.i) {
                    return false;
                }
                if (field2.get(obj) != obj) {
                    z = true;
                }
                return z;
            }
        };
    }

    private Map<String, b> a(fz fzVar, hw<?> hwVar, Class<?> cls) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (cls.isInterface()) {
            return linkedHashMap;
        }
        Type b = hwVar.b();
        hw hwVar2 = hwVar;
        Class cls2 = cls;
        while (cls2 != Object.class) {
            Field[] declaredFields = cls2.getDeclaredFields();
            boolean z = false;
            int length = declaredFields.length;
            int i = 0;
            while (i < length) {
                Field field = declaredFields[i];
                boolean a = a(field, true);
                boolean a2 = a(field, z);
                if (a || a2) {
                    b bVar;
                    field.setAccessible(true);
                    Type a3 = gx.a(hwVar2.b(), cls2, field.getGenericType());
                    List a4 = a(field);
                    b bVar2 = null;
                    int i2 = z;
                    while (i2 < a4.size()) {
                        String str = (String) a4.get(i2);
                        boolean z2 = i2 != 0 ? z : a;
                        String str2 = str;
                        bVar = bVar2;
                        int i3 = i2;
                        List list = a4;
                        Type type = a3;
                        Field field2 = field;
                        bVar2 = bVar == null ? (b) linkedHashMap.put(str2, a(fzVar, field, str2, hw.a(a3), z2, a2)) : bVar;
                        i2 = i3 + 1;
                        a = z2;
                        a3 = type;
                        a4 = list;
                        field = field2;
                        z = false;
                    }
                    bVar = bVar2;
                    if (bVar != null) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(b);
                        stringBuilder.append(" declares multiple JSON fields named ");
                        stringBuilder.append(bVar.h);
                        throw new IllegalArgumentException(stringBuilder.toString());
                    }
                }
                i++;
                z = false;
            }
            hwVar2 = hw.a(gx.a(hwVar2.b(), cls2, cls2.getGenericSuperclass()));
            cls2 = hwVar2.a();
        }
        return linkedHashMap;
    }
}
