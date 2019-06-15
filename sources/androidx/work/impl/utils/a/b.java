package androidx.work.impl.utils.a;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

@RestrictTo({Scope.LIBRARY_GROUP})
public class b implements a {
    volatile Thread a;
    private final Handler b = new Handler(Looper.getMainLooper());
    private final Executor c = new Executor() {
        public void execute(@NonNull Runnable runnable) {
            b.this.b(runnable);
        }
    };
    private final ThreadFactory d = new ThreadFactory() {
        private int b = 0;

        public Thread newThread(@NonNull Runnable runnable) {
            Thread newThread = Executors.defaultThreadFactory().newThread(runnable);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("WorkManager-WorkManagerTaskExecutor-thread-");
            stringBuilder.append(this.b);
            newThread.setName(stringBuilder.toString());
            this.b++;
            b.this.a = newThread;
            return newThread;
        }
    };
    private final ExecutorService e = Executors.newSingleThreadExecutor(this.d);

    public void b(Runnable runnable) {
        this.b.post(runnable);
    }

    public Executor a() {
        return this.c;
    }

    public void a(Runnable runnable) {
        this.e.execute(runnable);
    }

    public Executor c() {
        return this.e;
    }

    @NonNull
    public Thread b() {
        return this.a;
    }
}
