package com.payu.magicretry.a;

import android.util.Log;

public class a {
    private static int a = 7;

    public static synchronized void a(String str, String str2) {
        synchronized (a.class) {
            if (a <= 2) {
                Log.v(str, str2);
            }
        }
    }
}
