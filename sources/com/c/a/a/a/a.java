package com.c.a.a.a;

import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class a implements d {
    private final Method a;
    private final Object b;

    private a(Object obj, Method method) {
        this.b = obj;
        this.a = method;
    }

    public static a a() throws IllegalStateException {
        Object invoke;
        Method method = null;
        try {
            Class cls = Class.forName("com.crashlytics.android.answers.CustomEvent");
            Class cls2 = Class.forName("com.crashlytics.android.answers.Answers");
            invoke = cls2.getMethod("getInstance", new Class[0]).invoke(cls2, new Object[0]);
            try {
                method = cls2.getMethod("logCustom", new Class[]{cls});
            } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            }
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException unused2) {
            invoke = null;
        }
        if (invoke != null && method != null) {
            return new a(invoke, method);
        }
        throw new IllegalStateException("Answers must be initialized before logging kit events");
    }

    public void a(c cVar) {
        try {
            this.a.invoke(this.b, new Object[]{cVar.a()});
        } catch (Throwable th) {
            Log.w("AnswersKitEventLogger", "Unexpected error sending Answers event", th);
        }
    }
}
