package com.b;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import java.util.HashMap;
import java.util.Map;

public final class i {
    private static final Map<String, Typeface> a = new HashMap();
    private static final Map<Typeface, e> b = new HashMap();

    public static Typeface a(AssetManager assetManager, String str) {
        synchronized (a) {
            try {
                Typeface typeface;
                if (a.containsKey(str)) {
                    typeface = (Typeface) a.get(str);
                    return typeface;
                }
                typeface = Typeface.createFromAsset(assetManager, str);
                a.put(str, typeface);
                return typeface;
            } catch (Exception unused) {
                a.put(str, null);
                return null;
            } catch (Throwable th) {
            }
        }
    }

    public static e a(Typeface typeface) {
        if (typeface == null) {
            return null;
        }
        synchronized (b) {
            if (b.containsKey(typeface)) {
                e eVar = (e) b.get(typeface);
                return eVar;
            }
            e eVar2 = new e(typeface);
            b.put(typeface, eVar2);
            return eVar2;
        }
    }

    public static boolean b(Typeface typeface) {
        return typeface != null && a.containsValue(typeface);
    }
}
