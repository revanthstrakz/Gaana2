package com.helpshift.j;

import android.content.Context;
import java.util.HashMap;

public class d {
    private static HashMap<String, a> a = new HashMap();
    private static Object b = new Object();

    public static a a(Context context, String str) {
        if (context == null) {
            return null;
        }
        a aVar;
        synchronized (b) {
            aVar = (a) a.get(str);
            if (aVar == null) {
                aVar = new c(context, str);
                a.put(str, aVar);
            }
        }
        return aVar;
    }
}
