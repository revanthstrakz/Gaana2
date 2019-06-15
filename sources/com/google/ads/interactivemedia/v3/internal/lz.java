package com.google.ads.interactivemedia.v3.internal;

import java.util.HashSet;
import java.util.Set;

public class lz {
    private static final ThreadLocal<Set<mb>> a = new ThreadLocal();
    private final int b;
    private int c;

    static Set<mb> a() {
        return (Set) a.get();
    }

    static boolean a(Object obj) {
        Set a = a();
        return a != null && a.contains(new mb(obj));
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0054 */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:17|18|19|20|21) */
    private static void a(java.lang.Object r5, java.lang.Class<?> r6, com.google.ads.interactivemedia.v3.internal.lz r7, boolean r8, java.lang.String[] r9) {
        /*
        r0 = a(r5);
        if (r0 == 0) goto L_0x0007;
    L_0x0006:
        return;
    L_0x0007:
        c(r5);	 Catch:{ all -> 0x0063 }
        r6 = r6.getDeclaredFields();	 Catch:{ all -> 0x0063 }
        r0 = 1;
        java.lang.reflect.AccessibleObject.setAccessible(r6, r0);	 Catch:{ all -> 0x0063 }
        r0 = r6.length;	 Catch:{ all -> 0x0063 }
        r1 = 0;
    L_0x0014:
        if (r1 >= r0) goto L_0x005f;
    L_0x0016:
        r2 = r6[r1];	 Catch:{ all -> 0x0063 }
        r3 = r2.getName();	 Catch:{ all -> 0x0063 }
        r3 = com.google.ads.interactivemedia.v3.internal.lt.b(r9, r3);	 Catch:{ all -> 0x0063 }
        if (r3 != 0) goto L_0x005c;
    L_0x0022:
        r3 = r2.getName();	 Catch:{ all -> 0x0063 }
        r4 = "$";
        r3 = r3.contains(r4);	 Catch:{ all -> 0x0063 }
        if (r3 != 0) goto L_0x005c;
    L_0x002e:
        if (r8 != 0) goto L_0x003a;
    L_0x0030:
        r3 = r2.getModifiers();	 Catch:{ all -> 0x0063 }
        r3 = java.lang.reflect.Modifier.isTransient(r3);	 Catch:{ all -> 0x0063 }
        if (r3 != 0) goto L_0x005c;
    L_0x003a:
        r3 = r2.getModifiers();	 Catch:{ all -> 0x0063 }
        r3 = java.lang.reflect.Modifier.isStatic(r3);	 Catch:{ all -> 0x0063 }
        if (r3 != 0) goto L_0x005c;
    L_0x0044:
        r3 = com.google.ads.interactivemedia.v3.internal.ma.class;
        r3 = r2.isAnnotationPresent(r3);	 Catch:{ all -> 0x0063 }
        if (r3 != 0) goto L_0x005c;
    L_0x004c:
        r2 = r2.get(r5);	 Catch:{ IllegalAccessException -> 0x0054 }
        r7.b(r2);	 Catch:{ IllegalAccessException -> 0x0054 }
        goto L_0x005c;
    L_0x0054:
        r6 = new java.lang.InternalError;	 Catch:{ all -> 0x0063 }
        r7 = "Unexpected IllegalAccessException";
        r6.<init>(r7);	 Catch:{ all -> 0x0063 }
        throw r6;	 Catch:{ all -> 0x0063 }
    L_0x005c:
        r1 = r1 + 1;
        goto L_0x0014;
    L_0x005f:
        d(r5);
        return;
    L_0x0063:
        r6 = move-exception;
        d(r5);
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.lz.a(java.lang.Object, java.lang.Class, com.google.ads.interactivemedia.v3.internal.lz, boolean, java.lang.String[]):void");
    }

    public static <T> int a(int i, int i2, T t, boolean z, Class<? super T> cls, String... strArr) {
        lv.a(t != null, "The object to build a hash code for must not be null", new Object[0]);
        lz lzVar = new lz(i, i2);
        Class<? super T> cls2 = t.getClass();
        a(t, cls2, lzVar, z, strArr);
        while (cls2.getSuperclass() != null && cls2 != cls) {
            cls2 = cls2.getSuperclass();
            a(t, cls2, lzVar, z, strArr);
        }
        return lzVar.b();
    }

    public static int a(Object obj, String... strArr) {
        return a(17, 37, obj, false, null, strArr);
    }

    private static void c(Object obj) {
        Set a = a();
        if (a == null) {
            a = new HashSet();
            a.set(a);
        }
        a.add(new mb(obj));
    }

    private static void d(Object obj) {
        Set a = a();
        if (a != null) {
            a.remove(new mb(obj));
            if (a.isEmpty()) {
                a.remove();
            }
        }
    }

    public lz() {
        this.c = 0;
        this.b = 37;
        this.c = 17;
    }

    public lz(int i, int i2) {
        this.c = 0;
        boolean z = true;
        lv.a(i % 2 != 0, "HashCodeBuilder requires an odd initial value", new Object[0]);
        if (i2 % 2 == 0) {
            z = false;
        }
        lv.a(z, "HashCodeBuilder requires an odd multiplier", new Object[0]);
        this.b = i2;
        this.c = i;
    }

    public lz a(boolean z) {
        this.c = (this.c * this.b) + (z ^ 1);
        return this;
    }

    public lz a(boolean[] zArr) {
        if (zArr == null) {
            this.c *= this.b;
        } else {
            for (boolean a : zArr) {
                a(a);
            }
        }
        return this;
    }

    public lz a(byte b) {
        this.c = (this.c * this.b) + b;
        return this;
    }

    public lz a(byte[] bArr) {
        if (bArr == null) {
            this.c *= this.b;
        } else {
            for (byte a : bArr) {
                a(a);
            }
        }
        return this;
    }

    public lz a(char c) {
        this.c = (this.c * this.b) + c;
        return this;
    }

    public lz a(char[] cArr) {
        if (cArr == null) {
            this.c *= this.b;
        } else {
            for (char a : cArr) {
                a(a);
            }
        }
        return this;
    }

    public lz a(double d) {
        return a(Double.doubleToLongBits(d));
    }

    public lz a(double[] dArr) {
        if (dArr == null) {
            this.c *= this.b;
        } else {
            for (double a : dArr) {
                a(a);
            }
        }
        return this;
    }

    public lz a(float f) {
        this.c = (this.c * this.b) + Float.floatToIntBits(f);
        return this;
    }

    public lz a(float[] fArr) {
        if (fArr == null) {
            this.c *= this.b;
        } else {
            for (float a : fArr) {
                a(a);
            }
        }
        return this;
    }

    public lz a(int i) {
        this.c = (this.c * this.b) + i;
        return this;
    }

    public lz a(int[] iArr) {
        if (iArr == null) {
            this.c *= this.b;
        } else {
            for (int a : iArr) {
                a(a);
            }
        }
        return this;
    }

    public lz a(long j) {
        this.c = (this.c * this.b) + ((int) (j ^ (j >> 32)));
        return this;
    }

    public lz a(long[] jArr) {
        if (jArr == null) {
            this.c *= this.b;
        } else {
            for (long a : jArr) {
                a(a);
            }
        }
        return this;
    }

    public lz b(Object obj) {
        if (obj == null) {
            this.c *= this.b;
        } else if (obj.getClass().isArray()) {
            e(obj);
        } else {
            this.c = (this.c * this.b) + obj.hashCode();
        }
        return this;
    }

    private void e(Object obj) {
        if (obj instanceof long[]) {
            a((long[]) obj);
        } else if (obj instanceof int[]) {
            a((int[]) obj);
        } else if (obj instanceof short[]) {
            a((short[]) obj);
        } else if (obj instanceof char[]) {
            a((char[]) obj);
        } else if (obj instanceof byte[]) {
            a((byte[]) obj);
        } else if (obj instanceof double[]) {
            a((double[]) obj);
        } else if (obj instanceof float[]) {
            a((float[]) obj);
        } else if (obj instanceof boolean[]) {
            a((boolean[]) obj);
        } else {
            a((Object[]) obj);
        }
    }

    public lz a(Object[] objArr) {
        if (objArr == null) {
            this.c *= this.b;
        } else {
            for (Object b : objArr) {
                b(b);
            }
        }
        return this;
    }

    public lz a(short s) {
        this.c = (this.c * this.b) + s;
        return this;
    }

    public lz a(short[] sArr) {
        if (sArr == null) {
            this.c *= this.b;
        } else {
            for (short a : sArr) {
                a(a);
            }
        }
        return this;
    }

    public int b() {
        return this.c;
    }

    public int hashCode() {
        return b();
    }
}
