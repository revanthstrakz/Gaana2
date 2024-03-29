package bolts;

import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

final class d {
    private static final d a = new d();
    private final ExecutorService b;
    private final ScheduledExecutorService c;
    private final Executor d;

    private static class a implements Executor {
        private ThreadLocal<Integer> a;

        private a() {
            this.a = new ThreadLocal();
        }

        private int a() {
            Integer num = (Integer) this.a.get();
            if (num == null) {
                num = Integer.valueOf(0);
            }
            int intValue = num.intValue() + 1;
            this.a.set(Integer.valueOf(intValue));
            return intValue;
        }

        private int b() {
            Integer num = (Integer) this.a.get();
            if (num == null) {
                num = Integer.valueOf(0);
            }
            int intValue = num.intValue() - 1;
            if (intValue == 0) {
                this.a.remove();
            } else {
                this.a.set(Integer.valueOf(intValue));
            }
            return intValue;
        }

        public void execute(Runnable runnable) {
            if (a() <= 15) {
                try {
                    runnable.run();
                } catch (Throwable th) {
                    b();
                }
            } else {
                d.a().execute(runnable);
            }
            b();
        }
    }

    private static boolean c() {
        String property = System.getProperty("java.runtime.name");
        if (property == null) {
            return false;
        }
        return property.toLowerCase(Locale.US).contains("android");
    }

    private d() {
        this.b = !c() ? Executors.newCachedThreadPool() : a.a();
        this.c = Executors.newSingleThreadScheduledExecutor();
        this.d = new a();
    }

    public static ExecutorService a() {
        return a.b;
    }

    static Executor b() {
        return a.d;
    }
}
