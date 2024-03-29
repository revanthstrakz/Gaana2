package com.google.android.gms.dynamite;

import dalvik.system.PathClassLoader;

final class zzh extends PathClassLoader {
    zzh(String str, ClassLoader classLoader) {
        super(str, classLoader);
    }

    /* Access modifiers changed, original: protected|final */
    public final Class<?> loadClass(String str, boolean z) throws ClassNotFoundException {
        if (!(str.startsWith("java.") || str.startsWith("android."))) {
            try {
                return findClass(str);
            } catch (ClassNotFoundException unused) {
            }
        }
        return super.loadClass(str, z);
    }
}
