package com.c.a.a.a;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class c {
    private final String a;
    private final Map<String, Object> b = new HashMap();

    public c(String str) {
        this.a = str;
    }

    public c a(String str, String str2) {
        this.b.put(str, str2);
        return this;
    }

    /* Access modifiers changed, original: 0000 */
    public Object a() {
        Object newInstance;
        try {
            Class cls = Class.forName("com.crashlytics.android.answers.CustomEvent");
            Constructor constructor = cls.getConstructor(new Class[]{String.class});
            Method method = cls.getMethod("putCustomAttribute", new Class[]{String.class, String.class});
            Method method2 = cls.getMethod("putCustomAttribute", new Class[]{String.class, Number.class});
            newInstance = constructor.newInstance(new Object[]{this.a});
            try {
                for (String str : this.b.keySet()) {
                    Object obj = this.b.get(str);
                    if (obj instanceof String) {
                        method.invoke(newInstance, new Object[]{str, (String) obj});
                    } else if (obj instanceof Number) {
                        method2.invoke(newInstance, new Object[]{str, (Number) obj});
                    }
                }
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException unused) {
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException unused2) {
            newInstance = null;
        }
        if (newInstance != null) {
            return newInstance;
        }
        throw new IllegalStateException("Unexpected error on creating custom event");
    }
}
