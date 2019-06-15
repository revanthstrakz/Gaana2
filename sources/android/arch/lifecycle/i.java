package android.arch.lifecycle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestrictTo({Scope.LIBRARY_GROUP})
public class i {
    private static Map<Class, Integer> a = new HashMap();
    private static Map<Class, List<Constructor<? extends c>>> b = new HashMap();

    @NonNull
    static GenericLifecycleObserver a(Object obj) {
        if (obj instanceof FullLifecycleObserver) {
            return new FullLifecycleObserverAdapter((FullLifecycleObserver) obj);
        }
        if (obj instanceof GenericLifecycleObserver) {
            return (GenericLifecycleObserver) obj;
        }
        Class cls = obj.getClass();
        if (b(cls) != 2) {
            return new ReflectiveGenericLifecycleObserver(obj);
        }
        List list = (List) b.get(cls);
        int i = 0;
        if (list.size() == 1) {
            return new SingleGeneratedAdapterObserver(a((Constructor) list.get(0), obj));
        }
        c[] cVarArr = new c[list.size()];
        while (i < list.size()) {
            cVarArr[i] = a((Constructor) list.get(i), obj);
            i++;
        }
        return new CompositeGeneratedAdaptersObserver(cVarArr);
    }

    private static c a(Constructor<? extends c> constructor, Object obj) {
        try {
            return (c) constructor.newInstance(new Object[]{obj});
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e2) {
            throw new RuntimeException(e2);
        } catch (InvocationTargetException e3) {
            throw new RuntimeException(e3);
        }
    }

    @Nullable
    private static Constructor<? extends c> a(Class<?> cls) {
        try {
            Package packageR = cls.getPackage();
            String canonicalName = cls.getCanonicalName();
            String name = packageR != null ? packageR.getName() : "";
            if (!name.isEmpty()) {
                canonicalName = canonicalName.substring(name.length() + 1);
            }
            canonicalName = a(canonicalName);
            if (!name.isEmpty()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(name);
                stringBuilder.append(".");
                stringBuilder.append(canonicalName);
                canonicalName = stringBuilder.toString();
            }
            Constructor declaredConstructor = Class.forName(canonicalName).getDeclaredConstructor(new Class[]{cls});
            if (!declaredConstructor.isAccessible()) {
                declaredConstructor.setAccessible(true);
            }
            return declaredConstructor;
        } catch (ClassNotFoundException unused) {
            return null;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private static int b(Class<?> cls) {
        if (a.containsKey(cls)) {
            return ((Integer) a.get(cls)).intValue();
        }
        int c = c(cls);
        a.put(cls, Integer.valueOf(c));
        return c;
    }

    private static int c(Class<?> cls) {
        if (cls.getCanonicalName() == null) {
            return 1;
        }
        Constructor a = a((Class) cls);
        if (a != null) {
            b.put(cls, Collections.singletonList(a));
            return 2;
        } else if (a.a.a(cls)) {
            return 1;
        } else {
            Class superclass = cls.getSuperclass();
            Object obj = null;
            if (d(superclass)) {
                if (b(superclass) == 1) {
                    return 1;
                }
                obj = new ArrayList((Collection) b.get(superclass));
            }
            for (Class cls2 : cls.getInterfaces()) {
                if (d(cls2)) {
                    if (b(cls2) == 1) {
                        return 1;
                    }
                    if (obj == null) {
                        obj = new ArrayList();
                    }
                    obj.addAll((Collection) b.get(cls2));
                }
            }
            if (obj == null) {
                return 1;
            }
            b.put(cls, obj);
            return 2;
        }
    }

    private static boolean d(Class<?> cls) {
        return cls != null && d.class.isAssignableFrom(cls);
    }

    public static String a(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str.replace(".", "_"));
        stringBuilder.append("_LifecycleAdapter");
        return stringBuilder.toString();
    }
}
