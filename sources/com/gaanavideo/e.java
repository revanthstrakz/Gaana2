package com.gaanavideo;

public class e {
    public static c a;
    public static c b;
    public static c c;

    public static void a() {
        a(1);
        a(2);
        a(0);
    }

    public static void a(int i) {
        if (i == 2) {
            if (c != null) {
                c.c(false);
                c.b(false);
                try {
                    c.y();
                    c.w();
                    c = null;
                } catch (IllegalStateException unused) {
                }
            }
        } else if (i == 0) {
            if (a != null) {
                a.c(false);
                a.b(false);
                a.y();
                a.w();
                a = null;
            }
        } else if (i == 1 && b != null) {
            b.c(false);
            b.b(false);
            b.y();
            b.w();
            b = null;
        }
    }
}
