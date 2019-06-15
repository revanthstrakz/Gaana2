package com.facebook.ads.internal.f;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import com.facebook.ads.internal.r.b.f;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class d {
    private static final String a = "d";
    private static d b;
    private final Future<f> c;

    private d(final Context context) {
        this.c = Executors.newSingleThreadExecutor().submit(new Callable<f>() {
            /* renamed from: a */
            public f call() {
                return new f(context);
            }
        });
    }

    public static d a(Context context) {
        if (b == null) {
            context = context.getApplicationContext();
            synchronized (d.class) {
                if (b == null) {
                    b = new d(context);
                }
            }
        }
        return b;
    }

    @Nullable
    private f a() {
        try {
            return (f) this.c.get(500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            Log.e(a, "Timed out waiting for cache server.", e);
            return null;
        }
    }

    public boolean a(String str) {
        f a = a();
        return a != null && a.a(str);
    }

    @Nullable
    public String b(String str) {
        f a = a();
        return a == null ? null : a.b(str);
    }
}
