package net.jpountz.a;

import java.util.Random;
import net.jpountz.util.Native;
import net.jpountz.util.Utils;

public final class e {
    private static e a;
    private static e b;
    private static e c;
    private final String d;
    private final c e;
    private final d f;
    private final a g;
    private final a h;

    private static e a(String str) {
        try {
            return new e(str);
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }

    public static synchronized e a() {
        e eVar;
        synchronized (e.class) {
            if (a == null) {
                a = a("JNI");
            }
            eVar = a;
        }
        return eVar;
    }

    public static synchronized e b() {
        e eVar;
        synchronized (e.class) {
            if (c == null) {
                c = a("JavaSafe");
            }
            eVar = c;
        }
        return eVar;
    }

    public static synchronized e c() {
        e eVar;
        synchronized (e.class) {
            if (b == null) {
                b = a("JavaUnsafe");
            }
            eVar = b;
        }
        return eVar;
    }

    public static e d() {
        if (!Utils.isUnalignedAccessAllowed()) {
            return b();
        }
        try {
            return c();
        } catch (Throwable unused) {
            return b();
        }
    }

    public static e e() {
        if (!Native.isLoaded() && Native.class.getClassLoader() != ClassLoader.getSystemClassLoader()) {
            return d();
        }
        try {
            return a();
        } catch (Throwable unused) {
            return d();
        }
    }

    private static <T> T b(String str) throws NoSuchFieldException, SecurityException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
        ClassLoader classLoader = e.class.getClassLoader();
        if (classLoader == null) {
            classLoader = ClassLoader.getSystemClassLoader();
        }
        return classLoader.loadClass(str).getField("INSTANCE").get(null);
    }

    private e(String str) throws ClassNotFoundException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        this.d = str;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("net.jpountz.xxhash.XXHash32");
        stringBuilder.append(str);
        this.e = (c) b(stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("net.jpountz.xxhash.StreamingXXHash32");
        stringBuilder.append(str);
        stringBuilder.append("$Factory");
        this.g = (a) b(stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("net.jpountz.xxhash.XXHash64");
        stringBuilder.append(str);
        this.f = (d) b(stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("net.jpountz.xxhash.StreamingXXHash64");
        stringBuilder.append(str);
        stringBuilder.append("$Factory");
        this.h = (a) b(stringBuilder.toString());
        byte[] bArr = new byte[100];
        Random random = new Random();
        random.nextBytes(bArr);
        int nextInt = random.nextInt();
        int a = this.e.a(bArr, 0, bArr.length, nextInt);
        a a2 = a(nextInt);
        a2.a(bArr, 0, bArr.length);
        int a3 = a2.a();
        long j = (long) nextInt;
        long a4 = this.f.a(bArr, 0, bArr.length, j);
        b a5 = a(j);
        a5.a(bArr, 0, bArr.length);
        long a6 = a5.a();
        if (a != a3) {
            throw new AssertionError();
        } else if (a4 != a6) {
            throw new AssertionError();
        }
    }

    public c f() {
        return this.e;
    }

    public a a(int i) {
        return this.g.a(i);
    }

    public b a(long j) {
        return this.h.a(j);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName());
        stringBuilder.append(":");
        stringBuilder.append(this.d);
        return stringBuilder.toString();
    }
}
