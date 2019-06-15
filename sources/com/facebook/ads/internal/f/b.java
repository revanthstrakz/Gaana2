package com.facebook.ads.internal.f;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

public class b {
    private static final String a = "b";
    private static final ExecutorService b = Executors.newSingleThreadExecutor();
    private static final ExecutorService c = Executors.newFixedThreadPool(5);
    private final Handler d = new Handler();
    private final c e;
    private final d f;
    private final List<Callable<Boolean>> g;

    private class a implements Callable<Boolean> {
        private final String b;
        private final int c;
        private final int d;

        public a(String str, int i, int i2) {
            this.b = str;
            this.c = i;
            this.d = i2;
        }

        /* renamed from: a */
        public Boolean call() {
            return Boolean.valueOf(b.this.e.a(this.b, this.c, this.d) != null);
        }
    }

    private class b implements Callable<Boolean> {
        private final String b;

        public b(String str) {
            this.b = str;
        }

        /* renamed from: a */
        public Boolean call() {
            return Boolean.valueOf(b.this.f.a(this.b));
        }
    }

    public b(Context context) {
        this.e = c.a(context);
        this.f = d.a(context);
        this.g = new ArrayList();
    }

    public void a(@Nullable final a aVar) {
        final ArrayList arrayList = new ArrayList(this.g);
        b.execute(new Runnable() {
            public void run() {
                ArrayList<Future> arrayList = new ArrayList(arrayList.size());
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    arrayList.add(b.c.submit((Callable) it.next()));
                }
                final AtomicBoolean atomicBoolean = new AtomicBoolean(true);
                try {
                    for (Future future : arrayList) {
                        atomicBoolean.set(((Boolean) future.get()).booleanValue() & atomicBoolean.get());
                    }
                } catch (InterruptedException | ExecutionException e) {
                    Log.e(b.a, "Exception while executing cache downloads.", e);
                    atomicBoolean.set(false);
                }
                b.this.d.post(new Runnable() {
                    public void run() {
                        if (aVar != null) {
                            if (atomicBoolean.get()) {
                                aVar.a();
                                return;
                            }
                            aVar.b();
                        }
                    }
                });
            }
        });
        this.g.clear();
    }

    public void a(String str) {
        this.g.add(new b(str));
    }

    public void a(String str, int i, int i2) {
        this.g.add(new a(str, i, i2));
    }

    public String b(String str) {
        return this.f.b(str);
    }
}
