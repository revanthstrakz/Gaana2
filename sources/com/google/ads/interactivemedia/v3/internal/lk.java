package com.google.ads.interactivemedia.v3.internal;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Map.Entry;

final class lk<K, V> extends lb<K, V> {
    static final lb<Object, Object> b = new lk(null, new Object[0], 0);
    final transient Object[] c;
    private final transient int[] d;
    private final transient int e;

    static class a<K, V> extends lc<Entry<K, V>> {
        private final transient lb<K, V> a;
        private final transient Object[] b;
        private final transient int c;
        private final transient int d;

        a(lb<K, V> lbVar, Object[] objArr, int i, int i2) {
            this.a = lbVar;
            this.b = objArr;
            this.c = i;
            this.d = i2;
        }

        /* Access modifiers changed, original: 0000 */
        public boolean f() {
            return true;
        }

        /* renamed from: a */
        public ln<Entry<K, V>> iterator() {
            return e().iterator();
        }

        /* Access modifiers changed, original: 0000 */
        public int a(Object[] objArr, int i) {
            return e().a(objArr, i);
        }

        /* Access modifiers changed, original: 0000 */
        public la<Entry<K, V>> h() {
            return new la<Entry<K, V>>() {
                public boolean f() {
                    return true;
                }

                /* renamed from: b */
                public Entry<K, V> get(int i) {
                    kr.a(i, a.this.d);
                    int i2 = 2 * i;
                    return new SimpleImmutableEntry(a.this.b[a.this.c + i2], a.this.b[i2 + (a.this.c ^ 1)]);
                }

                public int size() {
                    return a.this.d;
                }
            };
        }

        public boolean contains(Object obj) {
            boolean z = false;
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;
            Object key = entry.getKey();
            obj = entry.getValue();
            if (obj != null && obj.equals(this.a.get(key))) {
                z = true;
            }
            return z;
        }

        public int size() {
            return this.d;
        }
    }

    static final class b<K> extends lc<K> {
        private final transient lb<K, ?> a;
        private final transient la<K> b;

        b(lb<K, ?> lbVar, la<K> laVar) {
            this.a = lbVar;
            this.b = laVar;
        }

        /* Access modifiers changed, original: 0000 */
        public boolean f() {
            return true;
        }

        /* renamed from: a */
        public ln<K> iterator() {
            return e().iterator();
        }

        /* Access modifiers changed, original: 0000 */
        public int a(Object[] objArr, int i) {
            return e().a(objArr, i);
        }

        public la<K> e() {
            return this.b;
        }

        public boolean contains(Object obj) {
            return this.a.get(obj) != null;
        }

        public int size() {
            return this.a.size();
        }
    }

    static final class c extends la<Object> {
        private final transient Object[] a;
        private final transient int b;
        private final transient int c;

        c(Object[] objArr, int i, int i2) {
            this.a = objArr;
            this.b = i;
            this.c = i2;
        }

        /* Access modifiers changed, original: 0000 */
        public boolean f() {
            return true;
        }

        public Object get(int i) {
            kr.a(i, this.c);
            return this.a[(2 * i) + this.b];
        }

        public int size() {
            return this.c;
        }
    }

    static <K, V> lk<K, V> a(int i, Object[] objArr) {
        if (i == 0) {
            return (lk) b;
        }
        if (i == 1) {
            kv.a(objArr[0], objArr[1]);
            return new lk(null, objArr, 1);
        }
        kr.b(i, objArr.length >> 1);
        return new lk(a(objArr, i, lc.a(i), 0), objArr, i);
    }

    /* JADX WARNING: Missing block: B:9:0x0034, code skipped:
            r12[r7] = r5;
            r3 = r3 + 1;
     */
    static int[] a(java.lang.Object[] r10, int r11, int r12, int r13) {
        /*
        r0 = 1;
        if (r11 != r0) goto L_0x000e;
    L_0x0003:
        r11 = r10[r13];
        r12 = r13 ^ 1;
        r10 = r10[r12];
        com.google.ads.interactivemedia.v3.internal.kv.a(r11, r10);
        r10 = 0;
        return r10;
    L_0x000e:
        r1 = r12 + -1;
        r12 = new int[r12];
        r2 = -1;
        java.util.Arrays.fill(r12, r2);
        r3 = 0;
    L_0x0017:
        if (r3 >= r11) goto L_0x00ae;
    L_0x0019:
        r4 = 2;
        r4 = r4 * r3;
        r5 = r4 + r13;
        r6 = r10[r5];
        r7 = r13 ^ 1;
        r4 = r4 + r7;
        r4 = r10[r4];
        com.google.ads.interactivemedia.v3.internal.kv.a(r6, r4);
        r7 = r6.hashCode();
        r7 = com.google.ads.interactivemedia.v3.internal.ky.a(r7);
    L_0x002f:
        r7 = r7 & r1;
        r8 = r12[r7];
        if (r8 != r2) goto L_0x0039;
    L_0x0034:
        r12[r7] = r5;
        r3 = r3 + 1;
        goto L_0x0017;
    L_0x0039:
        r9 = r10[r8];
        r9 = r9.equals(r6);
        if (r9 == 0) goto L_0x00ab;
    L_0x0041:
        r11 = new java.lang.IllegalArgumentException;
        r12 = java.lang.String.valueOf(r6);
        r13 = java.lang.String.valueOf(r4);
        r1 = r10[r8];
        r1 = java.lang.String.valueOf(r1);
        r0 = r0 ^ r8;
        r10 = r10[r0];
        r10 = java.lang.String.valueOf(r10);
        r0 = 39;
        r2 = java.lang.String.valueOf(r12);
        r2 = r2.length();
        r0 = r0 + r2;
        r2 = java.lang.String.valueOf(r13);
        r2 = r2.length();
        r0 = r0 + r2;
        r2 = java.lang.String.valueOf(r1);
        r2 = r2.length();
        r0 = r0 + r2;
        r2 = java.lang.String.valueOf(r10);
        r2 = r2.length();
        r0 = r0 + r2;
        r2 = new java.lang.StringBuilder;
        r2.<init>(r0);
        r0 = "Multiple entries with same key: ";
        r2.append(r0);
        r2.append(r12);
        r12 = "=";
        r2.append(r12);
        r2.append(r13);
        r12 = " and ";
        r2.append(r12);
        r2.append(r1);
        r12 = "=";
        r2.append(r12);
        r2.append(r10);
        r10 = r2.toString();
        r11.<init>(r10);
        throw r11;
    L_0x00ab:
        r7 = r7 + 1;
        goto L_0x002f;
    L_0x00ae:
        return r12;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.lk.a(java.lang.Object[], int, int, int):int[]");
    }

    private lk(int[] iArr, Object[] objArr, int i) {
        this.d = iArr;
        this.c = objArr;
        this.e = i;
    }

    public int size() {
        return this.e;
    }

    public V get(Object obj) {
        return a(this.d, this.c, this.e, 0, obj);
    }

    static Object a(int[] iArr, Object[] objArr, int i, int i2, Object obj) {
        Object obj2 = null;
        if (obj == null) {
            return null;
        }
        if (i == 1) {
            if (objArr[i2].equals(obj)) {
                obj2 = objArr[i2 ^ 1];
            }
            return obj2;
        } else if (iArr == null) {
            return null;
        } else {
            i = iArr.length - 1;
            i2 = ky.a(obj.hashCode());
            while (true) {
                i2 &= i;
                int i3 = iArr[i2];
                if (i3 == -1) {
                    return null;
                }
                if (objArr[i3].equals(obj)) {
                    return objArr[i3 ^ 1];
                }
                i2++;
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public lc<Entry<K, V>> b() {
        return new a(this, this.c, 0, this.e);
    }

    /* Access modifiers changed, original: 0000 */
    public lc<K> d() {
        return new b(this, new c(this.c, 0, this.e));
    }

    /* Access modifiers changed, original: 0000 */
    public kz<V> f() {
        return new c(this.c, 1, this.e);
    }
}
