package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class gz implements gq, Cloneable {
    public static final gz a = new gz();
    private double b = -1.0d;
    private int c = 136;
    private boolean d = true;
    private boolean e;
    private List<fv> f = Collections.emptyList();
    private List<fv> g = Collections.emptyList();

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public gz clone() {
        try {
            return (gz) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public gz a(fv fvVar, boolean z, boolean z2) {
        gz a = clone();
        if (z) {
            a.f = new ArrayList(this.f);
            a.f.add(fvVar);
        }
        if (z2) {
            a.g = new ArrayList(this.g);
            a.g.add(fvVar);
        }
        return a;
    }

    public <T> gp<T> a(fz fzVar, hw<T> hwVar) {
        Class a = hwVar.a();
        final boolean a2 = a(a, true);
        final boolean a3 = a(a, false);
        if (!a2 && !a3) {
            return null;
        }
        final fz fzVar2 = fzVar;
        final hw<T> hwVar2 = hwVar;
        return new gp<T>() {
            private gp<T> f;

            public T read(hx hxVar) throws IOException {
                if (!a3) {
                    return a().read(hxVar);
                }
                hxVar.n();
                return null;
            }

            public void write(hz hzVar, T t) throws IOException {
                if (a2) {
                    hzVar.f();
                } else {
                    a().write(hzVar, t);
                }
            }

            private gp<T> a() {
                gp<T> gpVar = this.f;
                if (gpVar != null) {
                    return gpVar;
                }
                gp a = fzVar2.a(gz.this, hwVar2);
                this.f = a;
                return a;
            }
        };
    }

    public boolean a(Field field, boolean z) {
        if ((this.c & field.getModifiers()) != 0) {
            return true;
        }
        if ((this.b != -1.0d && !a((gu) field.getAnnotation(gu.class), (gv) field.getAnnotation(gv.class))) || field.isSynthetic()) {
            return true;
        }
        if (this.e) {
            gr grVar = (gr) field.getAnnotation(gr.class);
            if (grVar == null || (z ? grVar.a() : grVar.b())) {
                return true;
            }
        }
        if ((!this.d && b(field.getType())) || a(field.getType())) {
            return true;
        }
        List<fv> list = z ? this.f : this.g;
        if (!list.isEmpty()) {
            fw fwVar = new fw(field);
            for (fv a : list) {
                if (a.a(fwVar)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean a(Class<?> cls, boolean z) {
        if (this.b != -1.0d && !a((gu) cls.getAnnotation(gu.class), (gv) cls.getAnnotation(gv.class))) {
            return true;
        }
        if ((!this.d && b(cls)) || a((Class) cls)) {
            return true;
        }
        for (fv a : z ? this.f : this.g) {
            if (a.a((Class) cls)) {
                return true;
            }
        }
        return false;
    }

    private boolean a(Class<?> cls) {
        return !Enum.class.isAssignableFrom(cls) && (cls.isAnonymousClass() || cls.isLocalClass());
    }

    private boolean b(Class<?> cls) {
        return cls.isMemberClass() && !c(cls);
    }

    private boolean c(Class<?> cls) {
        return (cls.getModifiers() & 8) != 0;
    }

    private boolean a(gu guVar, gv gvVar) {
        return a(guVar) && a(gvVar);
    }

    private boolean a(gu guVar) {
        return guVar == null || guVar.a() <= this.b;
    }

    private boolean a(gv gvVar) {
        return gvVar == null || gvVar.a() > this.b;
    }
}
