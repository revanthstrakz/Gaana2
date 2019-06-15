package com.google.ads.interactivemedia.v3.internal;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public final class lp {
    static final a a;
    static final int b;

    static abstract class a {
        protected static final Throwable[] a = new Throwable[0];

        a() {
        }

        public abstract void a(Throwable th);
    }

    static final class b {
        private final ConcurrentHashMap<a, List<Throwable>> a = new ConcurrentHashMap(16, 0.75f, 10);
        private final ReferenceQueue<Throwable> b = new ReferenceQueue();

        private static final class a extends WeakReference<Throwable> {
            private final int a;

            public a(Throwable th, ReferenceQueue<Throwable> referenceQueue) {
                super(th, referenceQueue);
                if (th == null) {
                    throw new NullPointerException("The referent cannot be null");
                }
                this.a = System.identityHashCode(th);
            }

            public int hashCode() {
                return this.a;
            }

            public boolean equals(Object obj) {
                boolean z = false;
                if (obj == null || obj.getClass() != getClass()) {
                    return false;
                }
                if (this == obj) {
                    return true;
                }
                a aVar = (a) obj;
                if (this.a == aVar.a && get() == aVar.get()) {
                    z = true;
                }
                return z;
            }
        }

        b() {
        }

        public List<Throwable> a(Throwable th, boolean z) {
            a();
            List list = (List) this.a.get(new a(th, null));
            if (!z || list != null) {
                return list;
            }
            Vector vector = new Vector(2);
            List<Throwable> list2 = (List) this.a.putIfAbsent(new a(th, this.b), vector);
            if (list2 == null) {
                list2 = vector;
            }
            return list2;
        }

        /* Access modifiers changed, original: 0000 */
        public void a() {
            Object poll = this.b.poll();
            while (poll != null) {
                this.a.remove(poll);
                poll = this.b.poll();
            }
        }
    }

    static final class c extends a {
        private final b b = new b();

        c() {
        }

        public void a(Throwable th) {
            ThrowableExtension.printStackTrace(th);
            List<Throwable> a = this.b.a(th, false);
            if (a != null) {
                synchronized (a) {
                    for (Throwable th2 : a) {
                        System.err.print("Suppressed: ");
                        ThrowableExtension.printStackTrace(th2);
                    }
                }
            }
        }
    }

    static final class d extends a {
        d() {
        }

        public void a(Throwable th) {
            ThrowableExtension.printStackTrace(th);
        }
    }

    static final class e extends a {
        e() {
        }

        public void a(Throwable th) {
            ThrowableExtension.printStackTrace(th);
        }
    }

    public static void a(Throwable th) {
        a.a(th);
    }

    private static boolean a() {
        return Boolean.getBoolean(ThrowableExtension.SYSTEM_PROPERTY_TWR_DISABLE_MIMIC) ^ 1;
    }

    private static Integer b() {
        try {
            return (Integer) Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);
        } catch (Exception e) {
            System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
            ThrowableExtension.printStackTrace(e, System.err);
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0064  */
    static {
        /*
        r0 = b();	 Catch:{ Throwable -> 0x0028 }
        if (r0 == 0) goto L_0x0016;
    L_0x0006:
        r1 = r0.intValue();	 Catch:{ Throwable -> 0x0014 }
        r2 = 19;
        if (r1 < r2) goto L_0x0016;
    L_0x000e:
        r1 = new com.google.ads.interactivemedia.v3.internal.lp$e;	 Catch:{ Throwable -> 0x0014 }
        r1.<init>();	 Catch:{ Throwable -> 0x0014 }
        goto L_0x0060;
    L_0x0014:
        r1 = move-exception;
        goto L_0x002a;
    L_0x0016:
        r1 = a();	 Catch:{ Throwable -> 0x0014 }
        if (r1 == 0) goto L_0x0022;
    L_0x001c:
        r1 = new com.google.ads.interactivemedia.v3.internal.lp$c;	 Catch:{ Throwable -> 0x0014 }
        r1.<init>();	 Catch:{ Throwable -> 0x0014 }
        goto L_0x0060;
    L_0x0022:
        r1 = new com.google.ads.interactivemedia.v3.internal.lp$d;	 Catch:{ Throwable -> 0x0014 }
        r1.<init>();	 Catch:{ Throwable -> 0x0014 }
        goto L_0x0060;
    L_0x0028:
        r1 = move-exception;
        r0 = 0;
    L_0x002a:
        r2 = java.lang.System.err;
        r3 = com.google.ads.interactivemedia.v3.internal.lp.d.class;
        r3 = r3.getName();
        r4 = 132; // 0x84 float:1.85E-43 double:6.5E-322;
        r5 = java.lang.String.valueOf(r3);
        r5 = r5.length();
        r4 = r4 + r5;
        r5 = new java.lang.StringBuilder;
        r5.<init>(r4);
        r4 = "An error has occured when initializing the try-with-resources desuguring strategy. The default strategy ";
        r5.append(r4);
        r5.append(r3);
        r3 = "will be used. The error is: ";
        r5.append(r3);
        r3 = r5.toString();
        r2.println(r3);
        r2 = java.lang.System.err;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r1, r2);
        r1 = new com.google.ads.interactivemedia.v3.internal.lp$d;
        r1.<init>();
    L_0x0060:
        a = r1;
        if (r0 != 0) goto L_0x0066;
    L_0x0064:
        r0 = 1;
        goto L_0x006a;
    L_0x0066:
        r0 = r0.intValue();
    L_0x006a:
        b = r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.lp.<clinit>():void");
    }
}
