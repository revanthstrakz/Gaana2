package com.google.devtools.build.android.desugar.runtime;

import java.io.Closeable;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public final class ThrowableExtension {
    private static final String ANDROID_OS_BUILD_VERSION = "android.os.Build$VERSION";
    static final int API_LEVEL;
    static final AbstractDesugaringStrategy STRATEGY;
    public static final String SYSTEM_PROPERTY_TWR_DISABLE_MIMIC = "com.google.devtools.build.android.desugar.runtime.twr_disable_mimic";

    static abstract class AbstractDesugaringStrategy {
        protected static final Throwable[] EMPTY_THROWABLE_ARRAY = new Throwable[0];

        public abstract void addSuppressed(Throwable th, Throwable th2);

        public abstract Throwable[] getSuppressed(Throwable th);

        public abstract void printStackTrace(Throwable th);

        public abstract void printStackTrace(Throwable th, PrintStream printStream);

        public abstract void printStackTrace(Throwable th, PrintWriter printWriter);

        AbstractDesugaringStrategy() {
        }
    }

    static final class NullDesugaringStrategy extends AbstractDesugaringStrategy {
        public void addSuppressed(Throwable th, Throwable th2) {
        }

        NullDesugaringStrategy() {
        }

        public Throwable[] getSuppressed(Throwable th) {
            return EMPTY_THROWABLE_ARRAY;
        }

        public void printStackTrace(Throwable th) {
            th.printStackTrace();
        }

        public void printStackTrace(Throwable th, PrintStream printStream) {
            th.printStackTrace(printStream);
        }

        public void printStackTrace(Throwable th, PrintWriter printWriter) {
            th.printStackTrace(printWriter);
        }
    }

    static final class ReuseDesugaringStrategy extends AbstractDesugaringStrategy {
        ReuseDesugaringStrategy() {
        }

        public void addSuppressed(Throwable th, Throwable th2) {
            th.addSuppressed(th2);
        }

        public Throwable[] getSuppressed(Throwable th) {
            return th.getSuppressed();
        }

        public void printStackTrace(Throwable th) {
            th.printStackTrace();
        }

        public void printStackTrace(Throwable th, PrintStream printStream) {
            th.printStackTrace(printStream);
        }

        public void printStackTrace(Throwable th, PrintWriter printWriter) {
            th.printStackTrace(printWriter);
        }
    }

    static final class ConcurrentWeakIdentityHashMap {
        private final ConcurrentHashMap<WeakKey, List<Throwable>> map = new ConcurrentHashMap(16, 0.75f, 10);
        private final ReferenceQueue<Throwable> referenceQueue = new ReferenceQueue();

        private static final class WeakKey extends WeakReference<Throwable> {
            private final int hash;

            public WeakKey(Throwable th, ReferenceQueue<Throwable> referenceQueue) {
                super(th, referenceQueue);
                if (th == null) {
                    throw new NullPointerException("The referent cannot be null");
                }
                this.hash = System.identityHashCode(th);
            }

            public int hashCode() {
                return this.hash;
            }

            public boolean equals(Object obj) {
                boolean z = false;
                if (obj == null || obj.getClass() != getClass()) {
                    return false;
                }
                if (this == obj) {
                    return true;
                }
                WeakKey weakKey = (WeakKey) obj;
                if (this.hash == weakKey.hash && get() == weakKey.get()) {
                    z = true;
                }
                return z;
            }
        }

        ConcurrentWeakIdentityHashMap() {
        }

        public List<Throwable> get(Throwable th, boolean z) {
            deleteEmptyKeys();
            List list = (List) this.map.get(new WeakKey(th, null));
            if (!z || list != null) {
                return list;
            }
            Vector vector = new Vector(2);
            List<Throwable> list2 = (List) this.map.putIfAbsent(new WeakKey(th, this.referenceQueue), vector);
            if (list2 == null) {
                list2 = vector;
            }
            return list2;
        }

        /* Access modifiers changed, original: 0000 */
        public int size() {
            return this.map.size();
        }

        /* Access modifiers changed, original: 0000 */
        public void deleteEmptyKeys() {
            while (true) {
                Reference poll = this.referenceQueue.poll();
                if (poll != null) {
                    this.map.remove(poll);
                } else {
                    return;
                }
            }
        }
    }

    static final class MimicDesugaringStrategy extends AbstractDesugaringStrategy {
        static final String SUPPRESSED_PREFIX = "Suppressed: ";
        private final ConcurrentWeakIdentityHashMap map = new ConcurrentWeakIdentityHashMap();

        MimicDesugaringStrategy() {
        }

        public void addSuppressed(Throwable th, Throwable th2) {
            if (th2 == th) {
                throw new IllegalArgumentException("Self suppression is not allowed.", th2);
            } else if (th2 == null) {
                throw new NullPointerException("The suppressed exception cannot be null.");
            } else {
                this.map.get(th, true).add(th2);
            }
        }

        public Throwable[] getSuppressed(Throwable th) {
            List list = this.map.get(th, false);
            if (list == null || list.isEmpty()) {
                return EMPTY_THROWABLE_ARRAY;
            }
            return (Throwable[]) list.toArray(EMPTY_THROWABLE_ARRAY);
        }

        public void printStackTrace(Throwable th) {
            th.printStackTrace();
            List<Throwable> list = this.map.get(th, false);
            if (list != null) {
                synchronized (list) {
                    for (Throwable th2 : list) {
                        System.err.print(SUPPRESSED_PREFIX);
                        th2.printStackTrace();
                    }
                }
            }
        }

        public void printStackTrace(Throwable th, PrintStream printStream) {
            th.printStackTrace(printStream);
            List<Throwable> list = this.map.get(th, false);
            if (list != null) {
                synchronized (list) {
                    for (Throwable th2 : list) {
                        printStream.print(SUPPRESSED_PREFIX);
                        th2.printStackTrace(printStream);
                    }
                }
            }
        }

        public void printStackTrace(Throwable th, PrintWriter printWriter) {
            th.printStackTrace(printWriter);
            List<Throwable> list = this.map.get(th, false);
            if (list != null) {
                synchronized (list) {
                    for (Throwable th2 : list) {
                        printWriter.print(SUPPRESSED_PREFIX);
                        th2.printStackTrace(printWriter);
                    }
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x005b  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x005b  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x005b  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0059  */
    static {
        /*
        r0 = readApiLevelFromBuildVersion();	 Catch:{ Throwable -> 0x0028 }
        if (r0 == 0) goto L_0x0016;
    L_0x0006:
        r1 = r0.intValue();	 Catch:{ Throwable -> 0x0014 }
        r2 = 19;
        if (r1 < r2) goto L_0x0016;
    L_0x000e:
        r1 = new com.google.devtools.build.android.desugar.runtime.ThrowableExtension$ReuseDesugaringStrategy;	 Catch:{ Throwable -> 0x0014 }
        r1.<init>();	 Catch:{ Throwable -> 0x0014 }
        goto L_0x0055;
    L_0x0014:
        r1 = move-exception;
        goto L_0x002a;
    L_0x0016:
        r1 = useMimicStrategy();	 Catch:{ Throwable -> 0x0014 }
        if (r1 == 0) goto L_0x0022;
    L_0x001c:
        r1 = new com.google.devtools.build.android.desugar.runtime.ThrowableExtension$NullDesugaringStrategy;	 Catch:{ Throwable -> 0x0014 }
        r1.<init>();	 Catch:{ Throwable -> 0x0014 }
        goto L_0x0055;
    L_0x0022:
        r1 = new com.google.devtools.build.android.desugar.runtime.ThrowableExtension$NullDesugaringStrategy;	 Catch:{ Throwable -> 0x0014 }
        r1.<init>();	 Catch:{ Throwable -> 0x0014 }
        goto L_0x0055;
    L_0x0028:
        r1 = move-exception;
        r0 = 0;
    L_0x002a:
        r2 = java.lang.System.err;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "An error has occured when initializing the try-with-resources desuguring strategy. The default strategy ";
        r3.append(r4);
        r4 = com.google.devtools.build.android.desugar.runtime.ThrowableExtension.NullDesugaringStrategy.class;
        r4 = r4.getName();
        r3.append(r4);
        r4 = "will be used. The error is: ";
        r3.append(r4);
        r3 = r3.toString();
        r2.println(r3);
        r2 = java.lang.System.err;
        r1.printStackTrace(r2);
        r1 = new com.google.devtools.build.android.desugar.runtime.ThrowableExtension$NullDesugaringStrategy;
        r1.<init>();
    L_0x0055:
        STRATEGY = r1;
        if (r0 != 0) goto L_0x005b;
    L_0x0059:
        r0 = 1;
        goto L_0x005f;
    L_0x005b:
        r0 = r0.intValue();
    L_0x005f:
        API_LEVEL = r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.devtools.build.android.desugar.runtime.ThrowableExtension.<clinit>():void");
    }

    public static AbstractDesugaringStrategy getStrategy() {
        return STRATEGY;
    }

    public static void addSuppressed(Throwable th, Throwable th2) {
        STRATEGY.addSuppressed(th, th2);
    }

    public static Throwable[] getSuppressed(Throwable th) {
        return STRATEGY.getSuppressed(th);
    }

    public static void printStackTrace(Throwable th) {
        STRATEGY.printStackTrace(th);
    }

    public static void printStackTrace(Throwable th, PrintWriter printWriter) {
        STRATEGY.printStackTrace(th, printWriter);
    }

    public static void printStackTrace(Throwable th, PrintStream printStream) {
        STRATEGY.printStackTrace(th, printStream);
    }

    public static void closeResource(Throwable th, Object obj) throws Throwable {
        StringBuilder stringBuilder;
        if (obj != null) {
            try {
                if (API_LEVEL >= 19) {
                    ((AutoCloseable) obj).close();
                } else if (obj instanceof Closeable) {
                    ((Closeable) obj).close();
                } else {
                    obj.getClass().getMethod("close", new Class[0]).invoke(obj, new Object[0]);
                }
            } catch (NoSuchMethodException | SecurityException e) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(obj.getClass());
                stringBuilder.append(" does not have a close() method.");
                throw new AssertionError(stringBuilder.toString(), e);
            } catch (ExceptionInInitializerError | IllegalAccessException | IllegalArgumentException e2) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("Fail to call close() on ");
                stringBuilder.append(obj.getClass());
                throw new AssertionError(stringBuilder.toString(), e2);
            } catch (InvocationTargetException e3) {
                throw e3.getCause();
            } catch (Throwable th2) {
                if (th != null) {
                    addSuppressed(th, th2);
                }
            }
        }
    }

    private static boolean useMimicStrategy() {
        return Boolean.getBoolean(SYSTEM_PROPERTY_TWR_DISABLE_MIMIC) ^ 1;
    }

    private static Integer readApiLevelFromBuildVersion() {
        try {
            return (Integer) Class.forName(ANDROID_OS_BUILD_VERSION).getField("SDK_INT").get(null);
        } catch (Exception e) {
            System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
            e.printStackTrace(System.err);
            return null;
        }
    }
}
