package com.android.volley.toolbox;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.i.a;
import com.android.volley.i.b;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class m<T> implements a, b<T>, Future<T> {
    private Request<?> a;
    private boolean b = false;
    private T c;
    private VolleyError d;

    public static <E> m<E> a() {
        return new m();
    }

    private m() {
    }

    public synchronized boolean cancel(boolean z) {
        if (this.a == null) {
            return false;
        }
        if (isDone()) {
            return false;
        }
        this.a.cancel();
        return true;
    }

    public T get() throws InterruptedException, ExecutionException {
        try {
            return a(null);
        } catch (TimeoutException e) {
            throw new AssertionError(e);
        }
    }

    public T get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return a(Long.valueOf(TimeUnit.MILLISECONDS.convert(j, timeUnit)));
    }

    private synchronized T a(Long l) throws InterruptedException, ExecutionException, TimeoutException {
        if (this.d != null) {
            throw new ExecutionException(this.d);
        } else if (this.b) {
            return this.c;
        } else {
            if (l == null) {
                wait(0);
            } else if (l.longValue() > 0) {
                wait(l.longValue());
            }
            if (this.d != null) {
                throw new ExecutionException(this.d);
            } else if (this.b) {
                return this.c;
            } else {
                throw new TimeoutException();
            }
        }
    }

    public boolean isCancelled() {
        if (this.a == null) {
            return false;
        }
        return this.a.isCanceled();
    }

    public synchronized boolean isDone() {
        boolean z;
        z = this.b || this.d != null || isCancelled();
        return z;
    }

    public synchronized void onResponse(T t) {
        this.b = true;
        this.c = t;
        notifyAll();
    }

    public synchronized void onErrorResponse(VolleyError volleyError) {
        this.d = volleyError;
        notifyAll();
    }
}
