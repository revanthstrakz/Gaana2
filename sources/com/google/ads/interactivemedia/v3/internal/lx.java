package com.google.ads.interactivemedia.v3.internal;

import java.util.HashSet;
import java.util.Set;

public class lx {
    private static final ThreadLocal<Set<md<mb, mb>>> a = new ThreadLocal();
    private boolean b = true;
    private boolean c = false;
    private boolean d = false;
    private Class<?> e = null;
    private String[] f = null;

    static Set<md<mb, mb>> a() {
        return (Set) a.get();
    }

    static md<mb, mb> a(Object obj, Object obj2) {
        return md.b(new mb(obj), new mb(obj2));
    }

    static boolean b(Object obj, Object obj2) {
        Set a = a();
        md a2 = a(obj, obj2);
        return a != null && (a.contains(a2) || a.contains(md.b(a2.a(), a2.b())));
    }

    private static void e(Object obj, Object obj2) {
        Set a = a();
        if (a == null) {
            a = new HashSet();
            a.set(a);
        }
        a.add(a(obj, obj2));
    }

    private static void f(Object obj, Object obj2) {
        Set a = a();
        if (a != null) {
            a.remove(a(obj, obj2));
            if (a.isEmpty()) {
                a.remove();
            }
        }
    }

    public lx a(boolean z) {
        this.c = z;
        return this;
    }

    public lx b(boolean z) {
        this.d = z;
        return this;
    }

    public lx a(Class<?> cls) {
        this.e = cls;
        return this;
    }

    public lx a(String... strArr) {
        this.f = strArr;
        return this;
    }

    public static boolean a(Object obj, Object obj2, String... strArr) {
        return a(obj, obj2, false, null, strArr);
    }

    public static boolean a(Object obj, Object obj2, boolean z, Class<?> cls, String... strArr) {
        return a(obj, obj2, z, cls, false, strArr);
    }

    public static boolean a(Object obj, Object obj2, boolean z, Class<?> cls, boolean z2, String... strArr) {
        if (obj == obj2) {
            return true;
        }
        return (obj == null || obj2 == null) ? false : new lx().a(strArr).a((Class) cls).a(z).b(z2).c(obj, obj2).b();
    }

    /* JADX WARNING: Missing block: B:11:0x0020, code skipped:
            if (r2.isInstance(r5) == false) goto L_0x0030;
     */
    /* JADX WARNING: Missing block: B:15:0x002d, code skipped:
            if (r1.isInstance(r6) == false) goto L_0x0031;
     */
    public com.google.ads.interactivemedia.v3.internal.lx c(java.lang.Object r5, java.lang.Object r6) {
        /*
        r4 = this;
        r0 = r4.b;
        if (r0 != 0) goto L_0x0005;
    L_0x0004:
        return r4;
    L_0x0005:
        if (r5 != r6) goto L_0x0008;
    L_0x0007:
        return r4;
    L_0x0008:
        r0 = 0;
        if (r5 == 0) goto L_0x0057;
    L_0x000b:
        if (r6 != 0) goto L_0x000e;
    L_0x000d:
        goto L_0x0057;
    L_0x000e:
        r1 = r5.getClass();
        r2 = r6.getClass();
        r3 = r1.isInstance(r6);
        if (r3 == 0) goto L_0x0023;
    L_0x001c:
        r3 = r2.isInstance(r5);
        if (r3 != 0) goto L_0x0031;
    L_0x0022:
        goto L_0x0030;
    L_0x0023:
        r3 = r2.isInstance(r5);
        if (r3 == 0) goto L_0x0054;
    L_0x0029:
        r3 = r1.isInstance(r6);
        if (r3 != 0) goto L_0x0030;
    L_0x002f:
        goto L_0x0031;
    L_0x0030:
        r1 = r2;
    L_0x0031:
        r2 = r1.isArray();	 Catch:{ IllegalArgumentException -> 0x0051 }
        if (r2 == 0) goto L_0x003b;
    L_0x0037:
        r4.d(r5, r6);	 Catch:{ IllegalArgumentException -> 0x0051 }
        goto L_0x0050;
    L_0x003b:
        r4.a(r5, r6, r1);	 Catch:{ IllegalArgumentException -> 0x0051 }
    L_0x003e:
        r2 = r1.getSuperclass();	 Catch:{ IllegalArgumentException -> 0x0051 }
        if (r2 == 0) goto L_0x0050;
    L_0x0044:
        r2 = r4.e;	 Catch:{ IllegalArgumentException -> 0x0051 }
        if (r1 == r2) goto L_0x0050;
    L_0x0048:
        r1 = r1.getSuperclass();	 Catch:{ IllegalArgumentException -> 0x0051 }
        r4.a(r5, r6, r1);	 Catch:{ IllegalArgumentException -> 0x0051 }
        goto L_0x003e;
    L_0x0050:
        return r4;
    L_0x0051:
        r4.b = r0;
        return r4;
    L_0x0054:
        r4.b = r0;
        return r4;
    L_0x0057:
        r4.b = r0;
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.lx.c(java.lang.Object, java.lang.Object):com.google.ads.interactivemedia.v3.internal.lx");
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0060 */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:21|22|23|24|25) */
    private void a(java.lang.Object r5, java.lang.Object r6, java.lang.Class<?> r7) {
        /*
        r4 = this;
        r0 = b(r5, r6);
        if (r0 == 0) goto L_0x0007;
    L_0x0006:
        return;
    L_0x0007:
        e(r5, r6);	 Catch:{ all -> 0x006f }
        r7 = r7.getDeclaredFields();	 Catch:{ all -> 0x006f }
        r0 = 1;
        java.lang.reflect.AccessibleObject.setAccessible(r7, r0);	 Catch:{ all -> 0x006f }
        r0 = 0;
    L_0x0013:
        r1 = r7.length;	 Catch:{ all -> 0x006f }
        if (r0 >= r1) goto L_0x006b;
    L_0x0016:
        r1 = r4.b;	 Catch:{ all -> 0x006f }
        if (r1 == 0) goto L_0x006b;
    L_0x001a:
        r1 = r7[r0];	 Catch:{ all -> 0x006f }
        r2 = r4.f;	 Catch:{ all -> 0x006f }
        r3 = r1.getName();	 Catch:{ all -> 0x006f }
        r2 = com.google.ads.interactivemedia.v3.internal.lt.b(r2, r3);	 Catch:{ all -> 0x006f }
        if (r2 != 0) goto L_0x0068;
    L_0x0028:
        r2 = r1.getName();	 Catch:{ all -> 0x006f }
        r3 = "$";
        r2 = r2.contains(r3);	 Catch:{ all -> 0x006f }
        if (r2 != 0) goto L_0x0068;
    L_0x0034:
        r2 = r4.c;	 Catch:{ all -> 0x006f }
        if (r2 != 0) goto L_0x0042;
    L_0x0038:
        r2 = r1.getModifiers();	 Catch:{ all -> 0x006f }
        r2 = java.lang.reflect.Modifier.isTransient(r2);	 Catch:{ all -> 0x006f }
        if (r2 != 0) goto L_0x0068;
    L_0x0042:
        r2 = r1.getModifiers();	 Catch:{ all -> 0x006f }
        r2 = java.lang.reflect.Modifier.isStatic(r2);	 Catch:{ all -> 0x006f }
        if (r2 != 0) goto L_0x0068;
    L_0x004c:
        r2 = com.google.ads.interactivemedia.v3.internal.ly.class;
        r2 = r1.isAnnotationPresent(r2);	 Catch:{ all -> 0x006f }
        if (r2 != 0) goto L_0x0068;
    L_0x0054:
        r2 = r1.get(r5);	 Catch:{ IllegalAccessException -> 0x0060 }
        r1 = r1.get(r6);	 Catch:{ IllegalAccessException -> 0x0060 }
        r4.d(r2, r1);	 Catch:{ IllegalAccessException -> 0x0060 }
        goto L_0x0068;
    L_0x0060:
        r7 = new java.lang.InternalError;	 Catch:{ all -> 0x006f }
        r0 = "Unexpected IllegalAccessException";
        r7.<init>(r0);	 Catch:{ all -> 0x006f }
        throw r7;	 Catch:{ all -> 0x006f }
    L_0x0068:
        r0 = r0 + 1;
        goto L_0x0013;
    L_0x006b:
        f(r5, r6);
        return;
    L_0x006f:
        r7 = move-exception;
        f(r5, r6);
        throw r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.lx.a(java.lang.Object, java.lang.Object, java.lang.Class):void");
    }

    public lx d(Object obj, Object obj2) {
        if (!this.b || obj == obj2) {
            return this;
        }
        if (obj == null || obj2 == null) {
            c(false);
            return this;
        }
        Class cls = obj.getClass();
        if (cls.isArray()) {
            g(obj, obj2);
        } else if (!this.d || lu.a(cls)) {
            this.b = obj.equals(obj2);
        } else {
            c(obj, obj2);
        }
        return this;
    }

    private void g(Object obj, Object obj2) {
        if (obj.getClass() != obj2.getClass()) {
            c(false);
        } else if (obj instanceof long[]) {
            a((long[]) obj, (long[]) obj2);
        } else if (obj instanceof int[]) {
            a((int[]) obj, (int[]) obj2);
        } else if (obj instanceof short[]) {
            a((short[]) obj, (short[]) obj2);
        } else if (obj instanceof char[]) {
            a((char[]) obj, (char[]) obj2);
        } else if (obj instanceof byte[]) {
            a((byte[]) obj, (byte[]) obj2);
        } else if (obj instanceof double[]) {
            a((double[]) obj, (double[]) obj2);
        } else if (obj instanceof float[]) {
            a((float[]) obj, (float[]) obj2);
        } else if (obj instanceof boolean[]) {
            a((boolean[]) obj, (boolean[]) obj2);
        } else {
            a((Object[]) obj, (Object[]) obj2);
        }
    }

    public lx a(long j, long j2) {
        if (!this.b) {
            return this;
        }
        this.b = j == j2;
        return this;
    }

    public lx a(int i, int i2) {
        if (!this.b) {
            return this;
        }
        this.b = i == i2;
        return this;
    }

    public lx a(short s, short s2) {
        if (!this.b) {
            return this;
        }
        this.b = s == s2;
        return this;
    }

    public lx a(char c, char c2) {
        if (!this.b) {
            return this;
        }
        this.b = c == c2;
        return this;
    }

    public lx a(byte b, byte b2) {
        if (!this.b) {
            return this;
        }
        this.b = b == b2;
        return this;
    }

    public lx a(double d, double d2) {
        if (this.b) {
            return a(Double.doubleToLongBits(d), Double.doubleToLongBits(d2));
        }
        return this;
    }

    public lx a(float f, float f2) {
        if (this.b) {
            return a(Float.floatToIntBits(f), Float.floatToIntBits(f2));
        }
        return this;
    }

    public lx a(boolean z, boolean z2) {
        if (!this.b) {
            return this;
        }
        this.b = z == z2;
        return this;
    }

    public lx a(Object[] objArr, Object[] objArr2) {
        if (!this.b || objArr == objArr2) {
            return this;
        }
        int i = 0;
        if (objArr == null || objArr2 == null) {
            c(false);
            return this;
        } else if (objArr.length != objArr2.length) {
            c(false);
            return this;
        } else {
            while (i < objArr.length && this.b) {
                d(objArr[i], objArr2[i]);
                i++;
            }
            return this;
        }
    }

    public lx a(long[] jArr, long[] jArr2) {
        if (!this.b || jArr == jArr2) {
            return this;
        }
        int i = 0;
        if (jArr == null || jArr2 == null) {
            c(false);
            return this;
        } else if (jArr.length != jArr2.length) {
            c(false);
            return this;
        } else {
            while (i < jArr.length && this.b) {
                a(jArr[i], jArr2[i]);
                i++;
            }
            return this;
        }
    }

    public lx a(int[] iArr, int[] iArr2) {
        if (!this.b || iArr == iArr2) {
            return this;
        }
        int i = 0;
        if (iArr == null || iArr2 == null) {
            c(false);
            return this;
        } else if (iArr.length != iArr2.length) {
            c(false);
            return this;
        } else {
            while (i < iArr.length && this.b) {
                a(iArr[i], iArr2[i]);
                i++;
            }
            return this;
        }
    }

    public lx a(short[] sArr, short[] sArr2) {
        if (!this.b || sArr == sArr2) {
            return this;
        }
        int i = 0;
        if (sArr == null || sArr2 == null) {
            c(false);
            return this;
        } else if (sArr.length != sArr2.length) {
            c(false);
            return this;
        } else {
            while (i < sArr.length && this.b) {
                a(sArr[i], sArr2[i]);
                i++;
            }
            return this;
        }
    }

    public lx a(char[] cArr, char[] cArr2) {
        if (!this.b || cArr == cArr2) {
            return this;
        }
        int i = 0;
        if (cArr == null || cArr2 == null) {
            c(false);
            return this;
        } else if (cArr.length != cArr2.length) {
            c(false);
            return this;
        } else {
            while (i < cArr.length && this.b) {
                a(cArr[i], cArr2[i]);
                i++;
            }
            return this;
        }
    }

    public lx a(byte[] bArr, byte[] bArr2) {
        if (!this.b || bArr == bArr2) {
            return this;
        }
        int i = 0;
        if (bArr == null || bArr2 == null) {
            c(false);
            return this;
        } else if (bArr.length != bArr2.length) {
            c(false);
            return this;
        } else {
            while (i < bArr.length && this.b) {
                a(bArr[i], bArr2[i]);
                i++;
            }
            return this;
        }
    }

    public lx a(double[] dArr, double[] dArr2) {
        if (!this.b || dArr == dArr2) {
            return this;
        }
        int i = 0;
        if (dArr == null || dArr2 == null) {
            c(false);
            return this;
        } else if (dArr.length != dArr2.length) {
            c(false);
            return this;
        } else {
            while (i < dArr.length && this.b) {
                a(dArr[i], dArr2[i]);
                i++;
            }
            return this;
        }
    }

    public lx a(float[] fArr, float[] fArr2) {
        if (!this.b || fArr == fArr2) {
            return this;
        }
        int i = 0;
        if (fArr == null || fArr2 == null) {
            c(false);
            return this;
        } else if (fArr.length != fArr2.length) {
            c(false);
            return this;
        } else {
            while (i < fArr.length && this.b) {
                a(fArr[i], fArr2[i]);
                i++;
            }
            return this;
        }
    }

    public lx a(boolean[] zArr, boolean[] zArr2) {
        if (!this.b || zArr == zArr2) {
            return this;
        }
        int i = 0;
        if (zArr == null || zArr2 == null) {
            c(false);
            return this;
        } else if (zArr.length != zArr2.length) {
            c(false);
            return this;
        } else {
            while (i < zArr.length && this.b) {
                a(zArr[i], zArr2[i]);
                i++;
            }
            return this;
        }
    }

    public boolean b() {
        return this.b;
    }

    /* Access modifiers changed, original: protected */
    public void c(boolean z) {
        this.b = z;
    }
}
