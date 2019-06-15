package com.bumptech.glide.request;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.Nullable;
import com.bumptech.glide.f.i;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.a.h;
import com.bumptech.glide.request.b.d;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class RequestFutureTarget<R> implements b<R>, e<R>, Runnable {
    private static final a a = new a();
    private final Handler b;
    private final int c;
    private final int d;
    private final boolean e;
    private final a f;
    @Nullable
    private R g;
    @Nullable
    private c h;
    private boolean i;
    private boolean j;
    private boolean k;
    @Nullable
    private GlideException l;

    private static class GlideExecutionException extends ExecutionException {
        private final GlideException a;

        GlideExecutionException(GlideException glideException) {
            this.a = glideException;
        }

        public void printStackTrace() {
            ThrowableExtension.printStackTrace((Throwable) this, System.err);
        }

        public void printStackTrace(PrintStream printStream) {
            super.printStackTrace(printStream);
            printStream.print("Caused by: ");
            ThrowableExtension.printStackTrace(this.a, printStream);
        }

        public void printStackTrace(PrintWriter printWriter) {
            super.printStackTrace(printWriter);
            printWriter.print("Caused by: ");
            ThrowableExtension.printStackTrace(this.a, printWriter);
        }
    }

    static class a {
        a() {
        }

        public void a(Object obj, long j) throws InterruptedException {
            obj.wait(j);
        }

        public void a(Object obj) {
            obj.notifyAll();
        }
    }

    public void onDestroy() {
    }

    public void onLoadCleared(Drawable drawable) {
    }

    public void onLoadStarted(Drawable drawable) {
    }

    public void onStart() {
    }

    public void onStop() {
    }

    public void removeCallback(h hVar) {
    }

    public RequestFutureTarget(Handler handler, int i, int i2) {
        this(handler, i, i2, true, a);
    }

    RequestFutureTarget(Handler handler, int i, int i2, boolean z, a aVar) {
        this.b = handler;
        this.c = i;
        this.d = i2;
        this.e = z;
        this.f = aVar;
    }

    /* JADX WARNING: Missing block: B:13:0x0018, code skipped:
            return true;
     */
    public synchronized boolean cancel(boolean r3) {
        /*
        r2 = this;
        monitor-enter(r2);
        r0 = r2.isDone();	 Catch:{ all -> 0x0019 }
        if (r0 == 0) goto L_0x000a;
    L_0x0007:
        r3 = 0;
        monitor-exit(r2);
        return r3;
    L_0x000a:
        r0 = 1;
        r2.i = r0;	 Catch:{ all -> 0x0019 }
        r1 = r2.f;	 Catch:{ all -> 0x0019 }
        r1.a(r2);	 Catch:{ all -> 0x0019 }
        if (r3 == 0) goto L_0x0017;
    L_0x0014:
        r2.a();	 Catch:{ all -> 0x0019 }
    L_0x0017:
        monitor-exit(r2);
        return r0;
    L_0x0019:
        r3 = move-exception;
        monitor-exit(r2);
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.RequestFutureTarget.cancel(boolean):boolean");
    }

    public synchronized boolean isCancelled() {
        return this.i;
    }

    public synchronized boolean isDone() {
        boolean z;
        z = this.i || this.j || this.k;
        return z;
    }

    public R get() throws InterruptedException, ExecutionException {
        try {
            return a(null);
        } catch (TimeoutException e) {
            throw new AssertionError(e);
        }
    }

    public R get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return a(Long.valueOf(timeUnit.toMillis(j)));
    }

    public void getSize(h hVar) {
        hVar.a(this.c, this.d);
    }

    public void setRequest(@Nullable c cVar) {
        this.h = cVar;
    }

    @Nullable
    public c getRequest() {
        return this.h;
    }

    public synchronized void onLoadFailed(Drawable drawable) {
    }

    public synchronized void onResourceReady(R r, d<? super R> dVar) {
    }

    private synchronized R a(Long l) throws ExecutionException, InterruptedException, TimeoutException {
        if (this.e && !isDone()) {
            i.b();
        }
        if (this.i) {
            throw new CancellationException();
        } else if (this.k) {
            throw new ExecutionException(this.l);
        } else if (this.j) {
            return this.g;
        } else {
            if (l == null) {
                this.f.a(this, 0);
            } else if (l.longValue() > 0) {
                this.f.a(this, l.longValue());
            }
            if (Thread.interrupted()) {
                throw new InterruptedException();
            } else if (this.k) {
                throw new GlideExecutionException(this.l);
            } else if (this.i) {
                throw new CancellationException();
            } else if (this.j) {
                return this.g;
            } else {
                throw new TimeoutException();
            }
        }
    }

    public void run() {
        if (this.h != null) {
            this.h.c();
            this.h = null;
        }
    }

    private void a() {
        this.b.post(this);
    }

    public synchronized boolean onLoadFailed(@Nullable GlideException glideException, Object obj, com.bumptech.glide.request.a.i<R> iVar, boolean z) {
        this.k = true;
        this.l = glideException;
        this.f.a(this);
        return false;
    }

    public synchronized boolean onResourceReady(R r, Object obj, com.bumptech.glide.request.a.i<R> iVar, DataSource dataSource, boolean z) {
        this.j = true;
        this.g = r;
        this.f.a(this);
        return false;
    }
}
