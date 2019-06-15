package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public abstract class jz extends jy {
    static boolean d = false;
    private static Method e;
    private static Method f;
    private static Method g;
    private static Method h;
    private static Method i;
    private static Method j;
    private static Method k;
    private static Method l;
    private static Method m;
    private static Method n;
    private static Method o;
    private static Method p;
    private static Method q;
    private static String r;
    private static String s;
    private static String t;
    private static long u;
    private static kf v;

    static class a extends Exception {
        public a(Throwable th) {
            super(th);
        }
    }

    protected static synchronized void a(String str, Context context, kd kdVar) {
        synchronized (jz.class) {
            if (!d) {
                try {
                    v = new kf(kdVar, null);
                    r = str;
                    k(context);
                    u = b().longValue();
                    d = true;
                } catch (a | UnsupportedOperationException unused) {
                }
            }
        }
    }

    protected jz(Context context, kd kdVar, ke keVar) {
        super(context, kdVar, keVar);
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Removed duplicated region for block: B:41:? A:{SYNTHETIC, Splitter:B:39:0x00ab, ExcHandler: IOException (unused java.io.IOException)} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x0097 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x008d */
    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x007b */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0025 A:{Catch:{ a -> 0x0035 }} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0010 */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Can't wrap try/catch for region: R(34:0|1|2|3|5|6|7|8|(1:10)|11|13|14|15|17|18|19|21|22|23|25|26|27|28|29|30|31|32|33|35|36|37|39|40|42) */
    /* JADX WARNING: Can't wrap try/catch for region: R(26:0|(2:1|2)|3|5|6|7|8|(1:10)|11|(2:13|14)|15|(2:17|18)|19|(2:21|22)|23|25|26|27|28|29|30|(2:31|32)|33|(2:35|36)|37|(3:39|40|42)) */
    /* JADX WARNING: Can't wrap try/catch for region: R(26:0|(2:1|2)|3|5|6|7|8|(1:10)|11|(2:13|14)|15|(2:17|18)|19|(2:21|22)|23|25|26|27|28|29|30|(2:31|32)|33|(2:35|36)|37|(3:39|40|42)) */
    /* JADX WARNING: Missing block: B:43:?, code skipped:
            return;
     */
    public void b(android.content.Context r9) {
        /*
        r8 = this;
        r0 = 1;
        r1 = c();	 Catch:{ a -> 0x0008 }
        r8.a(r0, r1);	 Catch:{ a -> 0x0008 }
    L_0x0008:
        r1 = 2;
        r2 = a();	 Catch:{ a -> 0x0010 }
        r8.a(r1, r2);	 Catch:{ a -> 0x0010 }
    L_0x0010:
        r1 = b();	 Catch:{ a -> 0x0035 }
        r1 = r1.longValue();	 Catch:{ a -> 0x0035 }
        r3 = 25;
        r8.a(r3, r1);	 Catch:{ a -> 0x0035 }
        r3 = u;	 Catch:{ a -> 0x0035 }
        r5 = 0;
        r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1));
        if (r7 == 0) goto L_0x0035;
    L_0x0025:
        r3 = 17;
        r4 = u;	 Catch:{ a -> 0x0035 }
        r6 = r1 - r4;
        r8.a(r3, r6);	 Catch:{ a -> 0x0035 }
        r1 = 23;
        r2 = u;	 Catch:{ a -> 0x0035 }
        r8.a(r1, r2);	 Catch:{ a -> 0x0035 }
    L_0x0035:
        r1 = 0;
        r2 = g(r9);	 Catch:{ a -> 0x0058 }
        r3 = 31;
        r4 = r2.get(r1);	 Catch:{ a -> 0x0058 }
        r4 = (java.lang.Long) r4;	 Catch:{ a -> 0x0058 }
        r4 = r4.longValue();	 Catch:{ a -> 0x0058 }
        r8.a(r3, r4);	 Catch:{ a -> 0x0058 }
        r3 = 32;
        r2 = r2.get(r0);	 Catch:{ a -> 0x0058 }
        r2 = (java.lang.Long) r2;	 Catch:{ a -> 0x0058 }
        r4 = r2.longValue();	 Catch:{ a -> 0x0058 }
        r8.a(r3, r4);	 Catch:{ a -> 0x0058 }
    L_0x0058:
        r2 = 33;
        r3 = d();	 Catch:{ a -> 0x0065 }
        r3 = r3.longValue();	 Catch:{ a -> 0x0065 }
        r8.a(r2, r3);	 Catch:{ a -> 0x0065 }
    L_0x0065:
        r2 = 27;
        r3 = r8.c;	 Catch:{ a -> 0x0070 }
        r3 = a(r9, r3);	 Catch:{ a -> 0x0070 }
        r8.a(r2, r3);	 Catch:{ a -> 0x0070 }
    L_0x0070:
        r2 = 29;
        r3 = r8.c;	 Catch:{ a -> 0x007b }
        r3 = b(r9, r3);	 Catch:{ a -> 0x007b }
        r8.a(r2, r3);	 Catch:{ a -> 0x007b }
    L_0x007b:
        r2 = h(r9);	 Catch:{ a -> 0x008d }
        r3 = 5;
        r1 = r2[r1];	 Catch:{ a -> 0x008d }
        r4 = (long) r1;	 Catch:{ a -> 0x008d }
        r8.a(r3, r4);	 Catch:{ a -> 0x008d }
        r1 = 6;
        r0 = r2[r0];	 Catch:{ a -> 0x008d }
        r2 = (long) r0;	 Catch:{ a -> 0x008d }
        r8.a(r1, r2);	 Catch:{ a -> 0x008d }
    L_0x008d:
        r0 = i(r9);	 Catch:{ a -> 0x0097 }
        r1 = 12;
        r2 = (long) r0;	 Catch:{ a -> 0x0097 }
        r8.a(r1, r2);	 Catch:{ a -> 0x0097 }
    L_0x0097:
        r0 = j(r9);	 Catch:{ a -> 0x00a0 }
        r1 = 3;
        r2 = (long) r0;	 Catch:{ a -> 0x00a0 }
        r8.a(r1, r2);	 Catch:{ a -> 0x00a0 }
    L_0x00a0:
        r0 = 34;
        r1 = e(r9);	 Catch:{ a -> 0x00a9 }
        r8.a(r0, r1);	 Catch:{ a -> 0x00a9 }
    L_0x00a9:
        r0 = 35;
        r9 = f(r9);	 Catch:{ IOException -> 0x00b6, IOException -> 0x00b6 }
        r1 = r9.longValue();	 Catch:{ IOException -> 0x00b6, IOException -> 0x00b6 }
        r8.a(r0, r1);	 Catch:{ IOException -> 0x00b6, IOException -> 0x00b6 }
    L_0x00b6:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.jz.b(android.content.Context):void");
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Removed duplicated region for block: B:23:? A:{SYNTHETIC, Splitter:B:21:0x0065, ExcHandler: IOException (unused java.io.IOException)} */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x004b A:{Catch:{ a -> 0x005a }} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x001d */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Can't wrap try/catch for region: R(14:0|(2:1|2)|3|(2:5|6)|7|9|10|11|12|(1:14)|15|(2:17|18)|19|(3:21|22|24)) */
    /* JADX WARNING: Missing block: B:25:?, code skipped:
            return;
     */
    public void c(android.content.Context r7) {
        /*
        r6 = this;
        r0 = 2;
        r1 = a();	 Catch:{ a -> 0x0008 }
        r6.a(r0, r1);	 Catch:{ a -> 0x0008 }
    L_0x0008:
        r1 = 1;
        r2 = c();	 Catch:{ a -> 0x0010 }
        r6.a(r1, r2);	 Catch:{ a -> 0x0010 }
    L_0x0010:
        r2 = 25;
        r3 = b();	 Catch:{ a -> 0x001d }
        r3 = r3.longValue();	 Catch:{ a -> 0x001d }
        r6.a(r2, r3);	 Catch:{ a -> 0x001d }
    L_0x001d:
        r2 = r6.a;	 Catch:{ a -> 0x005a }
        r3 = r6.b;	 Catch:{ a -> 0x005a }
        r2 = a(r2, r3);	 Catch:{ a -> 0x005a }
        r3 = 14;
        r4 = 0;
        r4 = r2.get(r4);	 Catch:{ a -> 0x005a }
        r4 = (java.lang.Long) r4;	 Catch:{ a -> 0x005a }
        r4 = r4.longValue();	 Catch:{ a -> 0x005a }
        r6.a(r3, r4);	 Catch:{ a -> 0x005a }
        r3 = 15;
        r1 = r2.get(r1);	 Catch:{ a -> 0x005a }
        r1 = (java.lang.Long) r1;	 Catch:{ a -> 0x005a }
        r4 = r1.longValue();	 Catch:{ a -> 0x005a }
        r6.a(r3, r4);	 Catch:{ a -> 0x005a }
        r1 = r2.size();	 Catch:{ a -> 0x005a }
        r3 = 3;
        if (r1 < r3) goto L_0x005a;
    L_0x004b:
        r1 = 16;
        r0 = r2.get(r0);	 Catch:{ a -> 0x005a }
        r0 = (java.lang.Long) r0;	 Catch:{ a -> 0x005a }
        r2 = r0.longValue();	 Catch:{ a -> 0x005a }
        r6.a(r1, r2);	 Catch:{ a -> 0x005a }
    L_0x005a:
        r0 = 34;
        r1 = e(r7);	 Catch:{ a -> 0x0063 }
        r6.a(r0, r1);	 Catch:{ a -> 0x0063 }
    L_0x0063:
        r0 = 35;
        r7 = f(r7);	 Catch:{ IOException -> 0x0070, IOException -> 0x0070 }
        r1 = r7.longValue();	 Catch:{ IOException -> 0x0070, IOException -> 0x0070 }
        r6.a(r0, r1);	 Catch:{ IOException -> 0x0070, IOException -> 0x0070 }
    L_0x0070:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.jz.c(android.content.Context):void");
    }

    static String a() throws a {
        if (r != null) {
            return r;
        }
        throw new a();
    }

    static Long b() throws a {
        if (e == null) {
            throw new a();
        }
        try {
            return (Long) e.invoke(null, new Object[0]);
        } catch (IllegalAccessException e) {
            throw new a(e);
        } catch (InvocationTargetException e2) {
            throw new a(e2);
        }
    }

    static String d(Context context) throws a {
        if (j == null) {
            throw new a();
        }
        try {
            String str = (String) j.invoke(null, new Object[]{context});
            if (str != null) {
                return str;
            }
            throw new a();
        } catch (IllegalAccessException e) {
            throw new a(e);
        } catch (InvocationTargetException e2) {
            throw new a(e2);
        }
    }

    static String c() throws a {
        if (g == null) {
            throw new a();
        }
        try {
            return (String) g.invoke(null, new Object[0]);
        } catch (IllegalAccessException e) {
            throw new a(e);
        } catch (InvocationTargetException e2) {
            throw new a(e2);
        }
    }

    static ArrayList<Long> a(MotionEvent motionEvent, DisplayMetrics displayMetrics) throws a {
        if (i == null || motionEvent == null) {
            throw new a();
        }
        try {
            return (ArrayList) i.invoke(null, new Object[]{motionEvent, displayMetrics});
        } catch (IllegalAccessException e) {
            throw new a(e);
        } catch (InvocationTargetException e2) {
            throw new a(e2);
        }
    }

    static String e(Context context) throws a {
        if (n == null) {
            throw new a();
        }
        try {
            return (String) n.invoke(null, new Object[]{context});
        } catch (IllegalAccessException e) {
            throw new a(e);
        } catch (InvocationTargetException e2) {
            throw new a(e2);
        }
    }

    static Long f(Context context) throws a {
        if (o == null) {
            throw new a();
        }
        try {
            return (Long) o.invoke(null, new Object[]{context});
        } catch (IllegalAccessException e) {
            throw new a(e);
        } catch (InvocationTargetException e2) {
            throw new a(e2);
        }
    }

    static String a(Context context, kd kdVar) throws a {
        if (s != null) {
            return s;
        }
        if (h == null) {
            throw new a();
        }
        try {
            ByteBuffer byteBuffer = (ByteBuffer) h.invoke(null, new Object[]{context});
            if (byteBuffer == null) {
                throw new a();
            }
            s = kdVar.a(byteBuffer.array(), true);
            return s;
        } catch (IllegalAccessException e) {
            throw new a(e);
        } catch (InvocationTargetException e2) {
            throw new a(e2);
        }
    }

    static ArrayList<Long> g(Context context) throws a {
        if (l == null) {
            throw new a();
        }
        try {
            ArrayList arrayList = (ArrayList) l.invoke(null, new Object[]{context});
            if (arrayList != null) {
                if (arrayList.size() == 2) {
                    return arrayList;
                }
            }
            throw new a();
        } catch (IllegalAccessException e) {
            throw new a(e);
        } catch (InvocationTargetException e2) {
            throw new a(e2);
        }
    }

    static String b(Context context, kd kdVar) throws a {
        if (t != null) {
            return t;
        }
        if (k == null) {
            throw new a();
        }
        try {
            ByteBuffer byteBuffer = (ByteBuffer) k.invoke(null, new Object[]{context});
            if (byteBuffer == null) {
                throw new a();
            }
            t = kdVar.a(byteBuffer.array(), true);
            return t;
        } catch (IllegalAccessException e) {
            throw new a(e);
        } catch (InvocationTargetException e2) {
            throw new a(e2);
        }
    }

    static Long d() throws a {
        if (f == null) {
            throw new a();
        }
        try {
            return (Long) f.invoke(null, new Object[0]);
        } catch (IllegalAccessException e) {
            throw new a(e);
        } catch (InvocationTargetException e2) {
            throw new a(e2);
        }
    }

    static int[] h(Context context) throws a {
        if (m == null) {
            throw new a();
        }
        try {
            return (int[]) m.invoke(null, new Object[]{context});
        } catch (IllegalAccessException e) {
            throw new a(e);
        } catch (InvocationTargetException e2) {
            throw new a(e2);
        }
    }

    static int i(Context context) throws a {
        if (p == null) {
            throw new a();
        }
        try {
            return ((Integer) p.invoke(null, new Object[]{context})).intValue();
        } catch (IllegalAccessException e) {
            throw new a(e);
        } catch (InvocationTargetException e2) {
            throw new a(e2);
        }
    }

    static int j(Context context) throws a {
        if (q == null) {
            throw new a();
        }
        try {
            return ((Integer) q.invoke(null, new Object[]{context})).intValue();
        } catch (IllegalAccessException e) {
            throw new a(e);
        } catch (InvocationTargetException e2) {
            throw new a(e2);
        }
    }

    private static String b(byte[] bArr, String str) throws a {
        try {
            return new String(v.a(bArr, str), "UTF-8");
        } catch (com.google.ads.interactivemedia.v3.internal.kf.a e) {
            throw new a(e);
        } catch (UnsupportedEncodingException e2) {
            throw new a(e2);
        }
    }

    private static void k(Context context) throws a {
        Throwable th;
        Throwable th2;
        String name;
        try {
            byte[] a = v.a(kh.a());
            byte[] a2 = v.a(a, kh.b());
            File cacheDir = context.getCacheDir();
            if (cacheDir == null) {
                cacheDir = context.getDir("dex", 0);
                if (cacheDir == null) {
                    throw new a();
                }
            }
            Context context2 = context;
            File createTempFile = File.createTempFile("ads", ".jar", cacheDir);
            FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
            fileOutputStream.write(a2, 0, a2.length);
            fileOutputStream.close();
            File file;
            try {
                DexClassLoader dexClassLoader = new DexClassLoader(createTempFile.getAbsolutePath(), cacheDir.getAbsolutePath(), null, context.getClassLoader());
                Class loadClass = dexClassLoader.loadClass(b(a, kh.k()));
                Class loadClass2 = dexClassLoader.loadClass(b(a, kh.y()));
                Class loadClass3 = dexClassLoader.loadClass(b(a, kh.s()));
                Class loadClass4 = dexClassLoader.loadClass(b(a, kh.o()));
                Class loadClass5 = dexClassLoader.loadClass(b(a, kh.A()));
                Class loadClass6 = dexClassLoader.loadClass(b(a, kh.m()));
                Class loadClass7 = dexClassLoader.loadClass(b(a, kh.w()));
                Class loadClass8 = dexClassLoader.loadClass(b(a, kh.u()));
                Class loadClass9 = dexClassLoader.loadClass(b(a, kh.i()));
                Class loadClass10 = dexClassLoader.loadClass(b(a, kh.g()));
                Class loadClass11 = dexClassLoader.loadClass(b(a, kh.e()));
                File file2 = cacheDir;
                try {
                    Class loadClass12 = dexClassLoader.loadClass(b(a, kh.q()));
                    File file3 = createTempFile;
                    try {
                        Class loadClass13 = dexClassLoader.loadClass(b(a, kh.c()));
                        Class cls = loadClass12;
                        e = loadClass.getMethod(b(a, kh.l()), new Class[0]);
                        f = loadClass2.getMethod(b(a, kh.z()), new Class[0]);
                        g = loadClass3.getMethod(b(a, kh.t()), new Class[0]);
                        h = loadClass4.getMethod(b(a, kh.p()), new Class[]{Context.class});
                        i = loadClass5.getMethod(b(a, kh.B()), new Class[]{MotionEvent.class, DisplayMetrics.class});
                        j = loadClass6.getMethod(b(a, kh.n()), new Class[]{Context.class});
                        k = loadClass7.getMethod(b(a, kh.x()), new Class[]{Context.class});
                        l = loadClass8.getMethod(b(a, kh.v()), new Class[]{Context.class});
                        m = loadClass9.getMethod(b(a, kh.j()), new Class[]{Context.class});
                        n = loadClass10.getMethod(b(a, kh.h()), new Class[]{Context.class});
                        o = loadClass11.getMethod(b(a, kh.f()), new Class[]{Context.class});
                        p = cls.getMethod(b(a, kh.r()), new Class[]{Context.class});
                        q = loadClass13.getMethod(b(a, kh.d()), new Class[]{Context.class});
                        file = file3;
                        String name2 = file.getName();
                        file.delete();
                        new File(file2, name2.replace(".jar", ".dex")).delete();
                    } catch (Throwable th3) {
                        th = th3;
                        cacheDir = file2;
                        file = file3;
                        th2 = th;
                        name = file.getName();
                        file.delete();
                        new File(cacheDir, name.replace(".jar", ".dex")).delete();
                        throw th2;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    file = createTempFile;
                    cacheDir = file2;
                    th2 = th;
                    name = file.getName();
                    file.delete();
                    new File(cacheDir, name.replace(".jar", ".dex")).delete();
                    throw th2;
                }
            } catch (Throwable th5) {
                th = th5;
                file = createTempFile;
                th2 = th;
                name = file.getName();
                file.delete();
                new File(cacheDir, name.replace(".jar", ".dex")).delete();
                throw th2;
            }
        } catch (FileNotFoundException th6) {
            throw new a(th6);
        } catch (IOException th62) {
            throw new a(th62);
        } catch (ClassNotFoundException th622) {
            throw new a(th622);
        } catch (com.google.ads.interactivemedia.v3.internal.kf.a th6222) {
            throw new a(th6222);
        } catch (NoSuchMethodException th62222) {
            throw new a(th62222);
        } catch (NullPointerException th622222) {
            throw new a(th622222);
        }
    }
}
