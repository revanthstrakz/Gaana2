package com.i;

import android.os.Process;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class d {
    public static final Executor a;
    private static final int b = Runtime.getRuntime().availableProcessors();
    private static final int c = Math.max(2, Math.min(b - 1, 4));
    private static final int d = ((b * 2) + 1);
    private static final ThreadFactory e = new ThreadFactory() {
        private final AtomicInteger a = new AtomicInteger(1);

        public Thread newThread(Runnable runnable) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("GaanaQueue #");
            stringBuilder.append(this.a.getAndIncrement());
            Thread thread = new Thread(runnable, stringBuilder.toString());
            thread.setPriority(10);
            return thread;
        }
    };
    private static final BlockingQueue<Runnable> f = new LinkedBlockingQueue(128);

    public static class a implements Runnable {
        private Runnable a;
        private int b;

        public a(Runnable runnable, int i) {
            this.b = i;
            this.a = runnable;
        }

        public void run() {
            Process.setThreadPriority(this.b);
            this.a.run();
            Process.setThreadPriority(10);
        }
    }

    static {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(c, d, 30, TimeUnit.SECONDS, f, e, new DiscardOldestPolicy());
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        a = threadPoolExecutor;
    }

    public static void a(Runnable runnable) {
        a.execute(runnable);
    }
}
