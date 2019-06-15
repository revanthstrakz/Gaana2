package com.helpshift.util;

import com.helpshift.j.a;
import java.util.List;

public class l {
    private static a a;

    public static void a(a aVar) {
        a = aVar;
    }

    public static void a(float f) {
        a.a(((long) f) * 1000);
    }

    public static void a(boolean z, boolean z2) {
        if (a != null) {
            a.a(z, z2);
        }
    }

    public static void a(String str, String str2) {
        a(str, str2, null, null);
    }

    public static void b(String str, String str2) {
        b(str, str2, null, null);
    }

    public static void c(String str, String str2) {
        a(str, str2, (Throwable[]) null, null);
    }

    public static void a(String str, String str2, Throwable th) {
        a(str, str2, th, null);
    }

    public static void b(String str, String str2, Throwable th) {
        b(str, str2, th, null);
    }

    public static void c(String str, String str2, Throwable th) {
        a(str, str2, new Throwable[]{th}, null);
    }

    public static void a(String str, String str2, com.helpshift.j.c.a... aVarArr) {
        a(str, str2, null, aVarArr);
    }

    public static void a(String str, String str2, Throwable th, com.helpshift.j.c.a... aVarArr) {
        a(2, str, str2, new Throwable[]{th}, aVarArr);
    }

    public static void b(String str, String str2, Throwable th, com.helpshift.j.c.a... aVarArr) {
        a(4, str, str2, new Throwable[]{th}, aVarArr);
    }

    public static void c(String str, String str2, Throwable th, com.helpshift.j.c.a... aVarArr) {
        a(8, str, str2, new Throwable[]{th}, aVarArr);
    }

    public static void a(String str, String str2, Throwable[] thArr, com.helpshift.j.c.a... aVarArr) {
        a(8, str, str2, thArr, aVarArr);
    }

    public static void d(String str, String str2, Throwable th, com.helpshift.j.c.a... aVarArr) {
        a(16, str, str2, new Throwable[]{th}, aVarArr);
    }

    public static void b(String str, String str2, Throwable[] thArr, com.helpshift.j.c.a... aVarArr) {
        a(16, str, str2, thArr, aVarArr);
    }

    private static void a(int i, String str, String str2, Throwable[] thArr, com.helpshift.j.c.a... aVarArr) {
        if (a != null) {
            if (i == 2) {
                a.a(str, str2, thArr, aVarArr);
            } else if (i == 4) {
                a.b(str, str2, thArr, aVarArr);
            } else if (i == 8) {
                a.c(str, str2, thArr, aVarArr);
            } else if (i == 16) {
                a.d(str, str2, thArr, aVarArr);
            }
        }
    }

    public static List<com.helpshift.j.d.a> a() {
        if (a == null) {
            return null;
        }
        return a.a();
    }

    public static void b() {
        if (a != null) {
            a.b();
        }
    }

    public static int c() {
        if (a == null) {
            return 0;
        }
        return a.a(16);
    }
}
