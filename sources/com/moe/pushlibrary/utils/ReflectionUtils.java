package com.moe.pushlibrary.utils;

import com.moengage.core.Logger;
import java.lang.reflect.InvocationTargetException;

public final class ReflectionUtils {
    private ReflectionUtils() {
        throw new IllegalStateException("This class cannot be instantiated");
    }

    public static Object invokeStatic(Class<?> cls, String str, Class<?>[] clsArr, Object[] objArr) {
        return invocationHelper(null, cls, null, str, null, clsArr, objArr);
    }

    public static Object invokeStatic(String str, String str2, Class<?>[] clsArr, Object[] objArr) {
        return invocationHelper(null, null, str, str2, null, clsArr, objArr);
    }

    public static Object getAttribute(String str, String str2) {
        return (str2 == null || str2.isEmpty()) ? null : invocationHelper(null, null, str, str2, null, null, null);
    }

    public static Object invokeInstance(Object obj, String str, Class<?>[] clsArr, Object[] objArr) {
        return invocationHelper(obj, null, null, str, null, clsArr, objArr);
    }

    private static Object invocationHelper(Object obj, Class<?> cls, String str, String str2, String str3, Class<?>[] clsArr, Object[] objArr) {
        Class cls2;
        StringBuilder stringBuilder;
        if (cls2 == null) {
            if (obj != null) {
                try {
                    cls2 = obj.getClass();
                } catch (NoSuchMethodException e) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("ReflectionUtils:invocationHelper");
                    stringBuilder.append(e.getMessage());
                    Logger.e(stringBuilder.toString());
                    return null;
                } catch (IllegalAccessException e2) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("ReflectionUtils:invocationHelper");
                    stringBuilder.append(e2.getMessage());
                    Logger.e(stringBuilder.toString());
                    return null;
                } catch (InvocationTargetException e3) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("ReflectionUtils:invocationHelper");
                    stringBuilder.append(e3.getMessage());
                    Logger.e(stringBuilder.toString());
                    return null;
                } catch (ClassNotFoundException e4) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("ReflectionUtils:invocationHelper");
                    stringBuilder.append(e4.getMessage());
                    Logger.e(stringBuilder.toString());
                    return null;
                } catch (Exception e5) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("ReflectionUtils:invocationHelper");
                    stringBuilder.append(e5.getMessage());
                    Logger.e(stringBuilder.toString());
                    return null;
                }
            }
            cls2 = Class.forName(str);
        }
        if (str3 != null) {
            return cls2.getField(str3);
        }
        return str2 != null ? cls2.getMethod(str2, clsArr).invoke(obj, objArr) : cls2;
    }

    public static boolean isClassPresentInPath(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (Exception e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ReflectionUtils:isClassPresentInPath");
            stringBuilder.append(e.getMessage());
            Logger.e(stringBuilder.toString());
            return false;
        }
    }
}
