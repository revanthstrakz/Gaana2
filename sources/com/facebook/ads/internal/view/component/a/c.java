package com.facebook.ads.internal.view.component.a;

import com.facebook.ads.internal.adapters.a.d;
import com.facebook.ads.internal.adapters.a.h;
import com.facebook.ads.internal.s.a.y;
import com.facebook.ads.internal.view.component.a;

public final class c {
    private static final int a = y.a.heightPixels;
    private static final int b = y.a.widthPixels;

    private static float a(h hVar) {
        int h = hVar.c().h();
        int i = hVar.c().i();
        return i > 0 ? ((float) h) / ((float) i) : -1.0f;
    }

    private static int a(int i) {
        return (a - i) - ((y.a(16) + (a.a * 2)) + (2 * e.a));
    }

    public static b a(d dVar) {
        b aVar;
        boolean z = true;
        d a = dVar.k() == 1 ? dVar.g().b().a() : dVar.g().b().b();
        h hVar = (h) dVar.g().d().get(0);
        double a2 = (double) a(hVar);
        if (a(dVar.k(), dVar.j(), a2)) {
            if (dVar.k() != 2) {
                z = false;
            }
            aVar = new a(dVar, a, z);
        } else {
            aVar = new e(dVar, a(a2), a);
        }
        aVar.a(hVar, dVar.g().c(), a2);
        return aVar;
    }

    private static boolean a(double d) {
        return d < 0.9d;
    }

    private static boolean a(double d, int i) {
        return a(i) < b(d);
    }

    private static boolean a(int i, int i2, double d) {
        return i == 2 || a(d, i2);
    }

    private static int b(double d) {
        return (int) (((double) (b - (2 * e.a))) / d);
    }
}
