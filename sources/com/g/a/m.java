package com.g.a;

public class m {
    private static final Class<?> a = a();

    public static int a(String str, int i) {
        try {
            return ((Integer) a.getMethod("getInt", new Class[]{String.class, Integer.TYPE}).invoke(null, new Object[]{str, Integer.valueOf(i)})).intValue();
        } catch (Exception unused) {
            return i;
        }
    }

    private static Class<?> a() {
        try {
            return Class.forName("android.os.SystemProperties");
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }
}
