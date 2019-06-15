package com.helpshift.util.a;

import com.helpshift.common.domain.g;
import com.helpshift.util.l;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

public class c {
    private LinkedBlockingQueue<Future> a = new LinkedBlockingQueue();
    private LinkedBlockingQueue<Thread> b = new LinkedBlockingQueue();
    private ExecutorService c;

    public c(boolean z) {
        if (z) {
            this.c = Executors.newCachedThreadPool(new g("cmdpq-a"));
        } else {
            this.c = Executors.newSingleThreadExecutor(new g("cmdpq-b"));
        }
    }

    public void a(Runnable runnable) {
        try {
            this.c.submit(runnable).get();
        } catch (InterruptedException e) {
            l.b("HS_DispatchQueue", "Runnable interrupted : ", e);
        } catch (ExecutionException e2) {
            l.b("HS_DispatchQueue", "Execution exception : ", e2);
        }
    }

    private void a(Future future) {
        this.a.add(future);
    }

    public void b(Runnable runnable) {
        a(this.c.submit(runnable));
    }
}
