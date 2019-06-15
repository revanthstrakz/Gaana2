package com.payu.india.c;

import android.content.Context;

public class a {
    private static a a;
    private Context b = null;

    private a(Context context) {
        this.b = context;
    }

    public static a a() {
        return a;
    }

    public static a a(Context context) {
        if (a == null) {
            synchronized (a.class) {
                if (a == null) {
                    a = new a(context);
                }
            }
        }
        return a;
    }

    public Context b() {
        return this.b;
    }
}
