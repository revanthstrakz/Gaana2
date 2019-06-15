package com.moat.analytics.mobile.inm;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

class i {
    private static final i a = new i();
    private final Map<j, String> b = new WeakHashMap();
    private final Map<b, String> c = new WeakHashMap();
    private final ScheduledExecutorService d = Executors.newScheduledThreadPool(1);
    private ScheduledFuture<?> e;
    private ScheduledFuture<?> f;

    private i() {
    }

    static i a() {
        return a;
    }

    private void a(final Context context) {
        if (this.f == null || this.f.isDone()) {
            p.a(3, "JSUpdateLooper", (Object) this, "Starting metadata reporting loop");
            this.f = this.d.scheduleWithFixedDelay(new Runnable() {
                public void run() {
                    try {
                        LocalBroadcastManager.getInstance(context.getApplicationContext()).sendBroadcast(new Intent("UPDATE_METADATA"));
                        if (i.this.b.isEmpty()) {
                            i.this.f.cancel(true);
                        }
                    } catch (Exception e) {
                        m.a(e);
                    }
                }
            }, 0, 50, TimeUnit.MILLISECONDS);
        }
    }

    private void b(final Context context) {
        if (this.e == null || this.e.isDone()) {
            p.a(3, "JSUpdateLooper", (Object) this, "Starting view update loop");
            this.e = this.d.scheduleWithFixedDelay(new Runnable() {
                public void run() {
                    try {
                        LocalBroadcastManager.getInstance(context.getApplicationContext()).sendBroadcast(new Intent("UPDATE_VIEW_INFO"));
                        if (i.this.c.isEmpty()) {
                            p.a(3, "JSUpdateLooper", i.this, "No more active trackers");
                            i.this.e.cancel(true);
                        }
                    } catch (Exception e) {
                        m.a(e);
                    }
                }
            }, 0, (long) w.a().d, TimeUnit.MILLISECONDS);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(Context context, b bVar) {
        if (bVar != null) {
            StringBuilder stringBuilder = new StringBuilder("addActiveTracker");
            stringBuilder.append(bVar.hashCode());
            p.a(3, "JSUpdateLooper", (Object) this, stringBuilder.toString());
            if (this.c != null && !this.c.containsKey(bVar)) {
                this.c.put(bVar, "");
                b(context);
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(Context context, j jVar) {
        if (this.b != null && jVar != null) {
            this.b.put(jVar, "");
            a(context);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(b bVar) {
        if (bVar != null) {
            StringBuilder stringBuilder = new StringBuilder("removeActiveTracker");
            stringBuilder.append(bVar.hashCode());
            p.a(3, "JSUpdateLooper", (Object) this, stringBuilder.toString());
            if (this.c != null) {
                this.c.remove(bVar);
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(j jVar) {
        if (jVar != null) {
            StringBuilder stringBuilder = new StringBuilder("removeSetupNeededBridge");
            stringBuilder.append(jVar.hashCode());
            p.a(3, "JSUpdateLooper", (Object) this, stringBuilder.toString());
            if (this.b != null) {
                this.b.remove(jVar);
            }
        }
    }
}
