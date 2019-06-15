package com.til.colombia.android.service;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import com.til.colombia.android.internal.Log;
import com.til.colombia.android.internal.i;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

abstract class ca implements cc {
    final bl a;
    final b b;
    cm c;
    ExecutorService d;
    private int e = 10;

    protected class a implements Runnable {
        protected a() {
        }

        public final void run() {
            ca.this.b.sendEmptyMessage(0);
        }
    }

    protected static class b extends Handler {
        private final ca a;

        b(ca caVar) {
            this.a = caVar;
        }

        @SuppressLint({"NewApi"})
        public final synchronized void handleMessage(Message message) {
            super.handleMessage(message);
            String str;
            StringBuilder stringBuilder;
            try {
                ca caVar = this.a;
                if (caVar == null) {
                    Log.a(i.f, "Item fetcher unavailable.");
                    return;
                }
                str = i.f;
                stringBuilder = new StringBuilder("fetching ads. Build version of Device is :");
                stringBuilder.append(VERSION.SDK_INT);
                android.util.Log.i(str, stringBuilder.toString());
                caVar.c = new cm(caVar);
                if (VERSION.SDK_INT >= 11) {
                    Log.b(i.f, "Running Fetcher on Executor.");
                    caVar.c.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new bl[]{caVar.a});
                } else {
                    Log.b(i.f, "Running Fetcher on execute()");
                    caVar.c.execute(new bl[]{caVar.a});
                    return;
                }
            } catch (Exception e) {
                str = i.f;
                stringBuilder = new StringBuilder("error");
                stringBuilder.append(e);
                android.util.Log.e(str, stringBuilder.toString());
            }
            return;
        }
    }

    public abstract boolean d();

    public ca(ExecutorService executorService, bl blVar) {
        this.d = executorService;
        this.a = blVar;
        this.b = new b(this);
    }

    private void e() {
        if (this.c != null) {
            this.c.cancel(true);
            this.c = null;
        }
        if (this.d != null) {
            this.d.shutdownNow();
            try {
                this.d.awaitTermination((long) this.e, TimeUnit.MILLISECONDS);
                this.d = null;
                Log.a(i.f, "Stopping adFetcher");
            } catch (InterruptedException unused) {
                this.d = null;
            }
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean a() {
        if (this.d == null) {
            Log.b(i.f, "tasker is null. Creating new Thread pool, size:4");
            this.d = Executors.newFixedThreadPool(4);
        }
        this.d.submit(new a());
        android.util.Log.i(i.f, "Starting fetcher");
        return true;
    }

    public final bl b() {
        return this.a;
    }

    public final ExecutorService c() {
        return this.d;
    }
}
