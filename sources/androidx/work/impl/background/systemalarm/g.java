package androidx.work.impl.background.systemalarm;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import androidx.work.f;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

@RestrictTo({Scope.LIBRARY_GROUP})
class g {
    private static final String d = f.a("WorkTimer");
    final Map<String, b> a = new HashMap();
    final Map<String, a> b = new HashMap();
    final Object c = new Object();
    private final ThreadFactory e = new ThreadFactory() {
        private int b = 0;

        public Thread newThread(@NonNull Runnable runnable) {
            Thread newThread = Executors.defaultThreadFactory().newThread(runnable);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("WorkManager-WorkTimer-thread-");
            stringBuilder.append(this.b);
            newThread.setName(stringBuilder.toString());
            this.b++;
            return newThread;
        }
    };
    private final ScheduledExecutorService f = Executors.newSingleThreadScheduledExecutor(this.e);

    interface a {
        void a(@NonNull String str);
    }

    static class b implements Runnable {
        private final g a;
        private final String b;

        b(@NonNull g gVar, @NonNull String str) {
            this.a = gVar;
            this.b = str;
        }

        public void run() {
            synchronized (this.a.c) {
                if (((b) this.a.a.remove(this.b)) != null) {
                    a aVar = (a) this.a.b.remove(this.b);
                    if (aVar != null) {
                        aVar.a(this.b);
                    }
                } else {
                    f.a().b("WrkTimerRunnable", String.format("Timer with %s is already marked as complete.", new Object[]{this.b}), new Throwable[0]);
                }
            }
        }
    }

    g() {
    }

    /* Access modifiers changed, original: 0000 */
    public void a(@NonNull String str, long j, @NonNull a aVar) {
        synchronized (this.c) {
            f.a().b(d, String.format("Starting timer for %s", new Object[]{str}), new Throwable[0]);
            a(str);
            b bVar = new b(this, str);
            this.a.put(str, bVar);
            this.b.put(str, aVar);
            this.f.schedule(bVar, j, TimeUnit.MILLISECONDS);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(@NonNull String str) {
        synchronized (this.c) {
            if (((b) this.a.remove(str)) != null) {
                f.a().b(d, String.format("Stopping timer for %s", new Object[]{str}), new Throwable[0]);
                this.b.remove(str);
            }
        }
    }
}
