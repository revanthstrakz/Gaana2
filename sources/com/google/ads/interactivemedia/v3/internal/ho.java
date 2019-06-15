package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.util.ArrayList;

public final class ho extends gp<Object> {
    public static final gq a = new gq() {
        public <T> gp<T> a(fz fzVar, hw<T> hwVar) {
            return hwVar.a() == Object.class ? new ho(fzVar) : null;
        }
    };
    private final fz b;

    ho(fz fzVar) {
        this.b = fzVar;
    }

    public Object read(hx hxVar) throws IOException {
        switch (hxVar.f()) {
            case BEGIN_ARRAY:
                ArrayList arrayList = new ArrayList();
                hxVar.a();
                while (hxVar.e()) {
                    arrayList.add(read(hxVar));
                }
                hxVar.b();
                return arrayList;
            case BEGIN_OBJECT:
                hc hcVar = new hc();
                hxVar.c();
                while (hxVar.e()) {
                    hcVar.put(hxVar.g(), read(hxVar));
                }
                hxVar.d();
                return hcVar;
            case STRING:
                return hxVar.h();
            case NUMBER:
                return Double.valueOf(hxVar.k());
            case BOOLEAN:
                return Boolean.valueOf(hxVar.i());
            case NULL:
                hxVar.j();
                return null;
            default:
                throw new IllegalStateException();
        }
    }

    public void write(hz hzVar, Object obj) throws IOException {
        if (obj == null) {
            hzVar.f();
            return;
        }
        gp a = this.b.a(obj.getClass());
        if (a instanceof ho) {
            hzVar.d();
            hzVar.e();
            return;
        }
        a.write(hzVar, obj);
    }
}
