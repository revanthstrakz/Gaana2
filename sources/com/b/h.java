package com.b;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class h {
    private static final String a = "h";

    h() {
    }

    static Field a(Class cls, String str) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField;
        } catch (NoSuchFieldException unused) {
            return null;
        }
    }

    static Object a(Field field, Object obj) {
        try {
            return field.get(obj);
        } catch (IllegalAccessException unused) {
            return null;
        }
    }

    static void a(Field field, Object obj, Object obj2) {
        try {
            field.set(obj, obj2);
        } catch (IllegalAccessException unused) {
        }
    }

    static Method b(Class cls, String str) {
        for (Method method : cls.getMethods()) {
            if (method.getName().equals(str)) {
                method.setAccessible(true);
                return method;
            }
        }
        return null;
    }

    static void a(Object obj, Method method, Object... objArr) {
        if (method != null) {
            try {
                method.invoke(obj, objArr);
            } catch (IllegalAccessException | InvocationTargetException unused) {
            }
        }
    }
}
