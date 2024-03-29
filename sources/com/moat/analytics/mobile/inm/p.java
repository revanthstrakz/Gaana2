package com.moat.analytics.mobile.inm;

import android.util.Log;
import android.view.View;

class p {
    p() {
    }

    static String a(View view) {
        if (view == null) {
            return "null";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(view.getClass().getSimpleName());
        stringBuilder.append("@");
        stringBuilder.append(view.hashCode());
        return stringBuilder.toString();
    }

    private static String a(String str) {
        StringBuilder stringBuilder = new StringBuilder("Moat");
        stringBuilder.append(str);
        return stringBuilder.toString();
    }

    static void a(int i, String str, Object obj, String str2) {
        if (w.a().b) {
            if (obj == null) {
                Log.println(i, a(str), String.format("message = %s", new Object[]{str2}));
                return;
            }
            Log.println(i, a(str), String.format("id = %s, message = %s", new Object[]{Integer.valueOf(obj.hashCode()), str2}));
        }
    }

    static void a(String str, int i, String str2, Object obj, String str3) {
        a(i, str2, obj, str3);
        a(str, str3);
    }

    static void a(String str, Object obj, String str2, Throwable th) {
        if (w.a().b) {
            Log.e(a(str), String.format("id = %s, message = %s", new Object[]{Integer.valueOf(obj.hashCode()), str2}), th);
        }
    }

    static void a(String str, String str2) {
        if (!w.a().b && ((k) MoatAnalytics.getInstance()).a) {
            int i = 2;
            if (str.equals("[ERROR] ")) {
                i = 6;
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(str2);
            Log.println(i, "MoatAnalytics", stringBuilder.toString());
        }
    }

    static void b(int i, String str, Object obj, String str2) {
        if (w.a().c) {
            str = a(str);
            String str3 = "id = %s, message = %s";
            Object[] objArr = new Object[2];
            objArr[0] = obj == null ? "null" : Integer.valueOf(obj.hashCode());
            objArr[1] = str2;
            Log.println(i, str, String.format(str3, objArr));
        }
    }
}
