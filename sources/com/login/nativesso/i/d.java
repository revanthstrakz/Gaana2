package com.login.nativesso.i;

import com.login.nativesso.d.c;

public class d {
    public static final String a;
    public static final String b;

    static String a() {
        return c.e;
    }

    static String b() {
        return c.f;
    }

    static {
        if (a().equalsIgnoreCase("") || a().equals(null)) {
            a = "https://jsso.indiatimes.com";
        } else {
            a = a();
        }
        if (b().equalsIgnoreCase("") || b().equals(null)) {
            b = "https://socialappsintegrator.indiatimes.com";
        } else {
            b = b();
        }
    }
}
