package com.helpshift.b;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import com.helpshift.common.domain.g;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

@TargetApi(14)
public class b implements ActivityLifecycleCallbacks {
    private static boolean d = false;
    private static boolean e = false;
    final LinkedBlockingDeque<a> a = new LinkedBlockingDeque();
    AtomicInteger b = new AtomicInteger(0);
    AtomicInteger c = new AtomicInteger(0);
    private final ExecutorService f = Executors.newCachedThreadPool(new g("m-lcycle"));

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
    }

    public void onActivityPaused(Activity activity) {
    }

    public void onActivityResumed(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    private void a(final int i) {
        this.f.execute(new Runnable() {
            public void run() {
                Iterator it = b.this.a.iterator();
                while (it.hasNext()) {
                    a aVar = (a) it.next();
                    switch (i) {
                        case 1:
                            aVar.a();
                            break;
                        case 2:
                            aVar.b();
                            break;
                        default:
                            break;
                    }
                }
            }
        });
    }

    public void a(final a aVar) {
        this.a.addFirst(aVar);
        this.f.execute(new Runnable() {
            public void run() {
                if (b.this.b.get() == 1) {
                    aVar.a();
                    if (b.this.c.get() == 1) {
                        aVar.b();
                    }
                }
            }
        });
    }

    public void onActivityStarted(Activity activity) {
        if (!e) {
            if (!d) {
                a(1);
                d = true;
            }
            this.b.incrementAndGet();
        }
        e = false;
    }

    public void onActivityStopped(Activity activity) {
        if (activity == null || !activity.isChangingConfigurations()) {
            e = false;
            this.c.incrementAndGet();
            if (this.b.get() == this.c.get()) {
                a(2);
                d = false;
                return;
            }
            return;
        }
        e = true;
    }
}
