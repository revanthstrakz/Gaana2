package com.cast_music.b;

import android.util.Log;
import com.cast_music.a;

public class b {
    private static final int a = "ccl_".length();
    private static boolean b = false;

    public static String a(String str) {
        StringBuilder stringBuilder;
        if (str.length() > 23 - a) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("ccl_");
            stringBuilder.append(str.substring(0, (23 - a) - 1));
            return stringBuilder.toString();
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append("ccl_");
        stringBuilder.append(str);
        return stringBuilder.toString();
    }

    public static String a(Class<?> cls) {
        return a(cls.getSimpleName());
    }

    public static void a(String str, String str2) {
        if (b || Log.isLoggable(str, 3)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(a());
            stringBuilder.append(str2);
            Log.d(str, stringBuilder.toString());
        }
    }

    public static void b(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(a());
        stringBuilder.append(str2);
        Log.e(str, stringBuilder.toString());
    }

    public static void a(String str, String str2, Throwable th) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(a());
        stringBuilder.append(str2);
        Log.e(str, stringBuilder.toString(), th);
    }

    public static String a() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[v");
        stringBuilder.append(a.t());
        stringBuilder.append("] ");
        return stringBuilder.toString();
    }

    public static void a(boolean z) {
        b = z;
    }
}
