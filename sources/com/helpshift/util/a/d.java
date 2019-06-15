package com.helpshift.util.a;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.helpshift.util.a.e.a;

public class d implements a {
    Handler a;
    private Handler b;
    private final Object c = new Object();

    public d(String str) {
        HandlerThread handlerThread = new HandlerThread(str);
        handlerThread.start();
        this.b = new Handler(handlerThread.getLooper());
        this.a = new Handler(Looper.getMainLooper());
    }

    public void a(Runnable runnable) {
        this.b.post(runnable);
    }

    public void c(final Runnable runnable) {
        a(new Runnable() {
            public void run() {
                d.this.a.post(runnable);
            }
        });
    }

    public void b(Runnable runnable) {
        a aVar = new a(runnable);
        synchronized (this.c) {
            this.b.post(aVar);
            aVar.a();
        }
    }
}
