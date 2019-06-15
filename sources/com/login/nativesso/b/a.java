package com.login.nativesso.b;

import com.login.nativesso.i.c;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class a {
    private static Map<String, Object> a = new ConcurrentHashMap();

    public static void a(String str, Object obj) {
        if (!c.a(str) && obj != null) {
            a.put(str, obj);
        }
    }

    public static Object a(String str) {
        return !c.a(str) ? a.get(str) : null;
    }

    public static void b(String str) {
        if (!c.a(str) && a.containsKey(str)) {
            a.remove(str);
        }
    }
}
