package com.bumptech.glide.load.engine.c;

import android.os.Process;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.os.StrictMode.ThreadPolicy.Builder;
import android.support.annotation.NonNull;
import android.util.Log;
import com.facebook.share.internal.ShareConstants;
import java.io.File;
import java.io.FilenameFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public final class a extends ThreadPoolExecutor {
    private static volatile int a;
    private static final long c = TimeUnit.SECONDS.toMillis(10);
    private final boolean b;

    private static final class a implements ThreadFactory {
        final b a;
        final boolean b;
        private final String c;
        private int d;

        a(String str, b bVar, boolean z) {
            this.c = str;
            this.a = bVar;
            this.b = z;
        }

        public synchronized Thread newThread(@NonNull Runnable runnable) {
            AnonymousClass1 anonymousClass1;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("glide-");
            stringBuilder.append(this.c);
            stringBuilder.append("-thread-");
            stringBuilder.append(this.d);
            anonymousClass1 = new Thread(runnable, stringBuilder.toString()) {
                public void run() {
                    Process.setThreadPriority(9);
                    if (a.this.b) {
                        StrictMode.setThreadPolicy(new Builder().detectNetwork().penaltyDeath().build());
                    }
                    try {
                        super.run();
                    } catch (Throwable th) {
                        a.this.a.a(th);
                    }
                }
            };
            this.d++;
            return anonymousClass1;
        }
    }

    public interface b {
        public static final b a = new b() {
            public void a(Throwable th) {
            }
        };
        public static final b b = new b() {
            public void a(Throwable th) {
                if (th != null && Log.isLoggable("GlideExecutor", 6)) {
                    Log.e("GlideExecutor", "Request threw uncaught throwable", th);
                }
            }
        };
        public static final b c = new b() {
            public void a(Throwable th) {
                if (th != null) {
                    throw new RuntimeException("Request threw uncaught throwable", th);
                }
            }
        };
        public static final b d = b;

        void a(Throwable th);
    }

    public static a a() {
        return a(1, "disk-cache", b.d);
    }

    public static a a(int i, String str, b bVar) {
        return new a(i, str, bVar, true, false);
    }

    public static a b() {
        return b(e(), ShareConstants.FEED_SOURCE_PARAM, b.d);
    }

    public static a b(int i, String str, b bVar) {
        return new a(i, str, bVar, false, false);
    }

    public static a c() {
        return new a(0, Integer.MAX_VALUE, c, "source-unlimited", b.d, false, false, new SynchronousQueue());
    }

    public static a d() {
        return new a(0, e() >= 4 ? 2 : 1, c, "animation", b.d, true, false, new PriorityBlockingQueue());
    }

    a(int i, String str, b bVar, boolean z, boolean z2) {
        this(i, i, 0, str, bVar, z, z2);
    }

    a(int i, int i2, long j, String str, b bVar, boolean z, boolean z2) {
        this(i, i2, j, str, bVar, z, z2, new PriorityBlockingQueue());
    }

    a(int i, int i2, long j, String str, b bVar, boolean z, boolean z2, BlockingQueue<Runnable> blockingQueue) {
        super(i, i2, j, TimeUnit.MILLISECONDS, blockingQueue, new a(str, bVar, z));
        this.b = z2;
    }

    public void execute(Runnable runnable) {
        if (this.b) {
            runnable.run();
        } else {
            super.execute(runnable);
        }
    }

    @NonNull
    public Future<?> submit(Runnable runnable) {
        return a(super.submit(runnable));
    }

    private <T> Future<T> a(Future<T> future) {
        if (this.b) {
            Object obj = null;
            while (!future.isDone()) {
                try {
                    future.get();
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException unused) {
                    obj = 1;
                } catch (Throwable th) {
                    if (obj != null) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
            if (obj != null) {
                Thread.currentThread().interrupt();
            }
        }
        return future;
    }

    @NonNull
    public <T> Future<T> submit(Runnable runnable, T t) {
        return a(super.submit(runnable, t));
    }

    public <T> Future<T> submit(Callable<T> callable) {
        return a(super.submit(callable));
    }

    public static int e() {
        if (a == 0) {
            ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
            File[] fileArr = null;
            try {
                File file = new File("/sys/devices/system/cpu/");
                final Pattern compile = Pattern.compile("cpu[0-9]+");
                File[] listFiles = file.listFiles(new FilenameFilter() {
                    public boolean accept(File file, String str) {
                        return compile.matcher(str).matches();
                    }
                });
                StrictMode.setThreadPolicy(allowThreadDiskReads);
                fileArr = listFiles;
            } catch (Throwable th) {
                StrictMode.setThreadPolicy(allowThreadDiskReads);
                throw th;
            }
            a = Math.min(4, Math.max(Math.max(1, Runtime.getRuntime().availableProcessors()), fileArr != null ? fileArr.length : 0));
        }
        return a;
    }
}
