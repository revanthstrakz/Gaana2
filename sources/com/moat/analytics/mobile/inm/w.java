package com.moat.analytics.mobile.inm;

import android.os.Handler;
import android.os.Looper;
import com.comscore.utils.Constants;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

class w {
    private static w h;
    private static final Queue<c> i = new ConcurrentLinkedQueue();
    volatile d a = d.OFF;
    volatile boolean b = false;
    volatile boolean c = false;
    volatile int d = 200;
    volatile int e = 10;
    private long f = Constants.SESSION_INACTIVE_PERIOD;
    private long g = 60000;
    private Handler j;
    private final AtomicBoolean k = new AtomicBoolean(false);
    private volatile long l = 0;
    private final AtomicInteger m = new AtomicInteger(0);
    private final AtomicBoolean n = new AtomicBoolean(false);

    private class a implements Runnable {
        private final Handler b;
        private final String c;
        private final e d;

        private a(String str, Handler handler, e eVar) {
            this.d = eVar;
            this.b = handler;
            StringBuilder stringBuilder = new StringBuilder("https://z.moatads.com/");
            stringBuilder.append(str);
            stringBuilder.append("/android/");
            stringBuilder.append("c334ae83accfebb8da23104450c896463c9cfab7".substring(0, 7));
            stringBuilder.append("/status.json");
            this.c = stringBuilder.toString();
        }

        /* synthetic */ a(w wVar, String str, Handler handler, e eVar, AnonymousClass1 anonymousClass1) {
            this(str, handler, eVar);
        }

        private void a() {
            String b = b();
            final l lVar = new l(b);
            w.this.b = lVar.a();
            w.this.c = lVar.b();
            w.this.d = lVar.c();
            w.this.e = lVar.d();
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    try {
                        a.this.d.a(lVar);
                    } catch (Exception e) {
                        m.a(e);
                    }
                }
            });
            w.this.l = System.currentTimeMillis();
            w.this.n.compareAndSet(true, false);
            if (b != null) {
                w.this.m.set(0);
            } else if (w.this.m.incrementAndGet() < 10) {
                w.this.a(w.this.g);
            }
        }

        private String b() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.c);
            stringBuilder.append("?ts=");
            stringBuilder.append(System.currentTimeMillis());
            stringBuilder.append("&v=2.5.0");
            try {
                return (String) q.a(stringBuilder.toString()).b();
            } catch (Exception unused) {
                return null;
            }
        }

        public void run() {
            try {
                a();
            } catch (Exception e) {
                m.a(e);
            }
            this.b.removeCallbacks(this);
            Looper myLooper = Looper.myLooper();
            if (myLooper != null) {
                myLooper.quit();
            }
        }
    }

    interface b {
        void c();

        void d();
    }

    private class c {
        final Long a;
        final b b;

        c(Long l, b bVar) {
            this.a = l;
            this.b = bVar;
        }
    }

    enum d {
        OFF,
        ON
    }

    interface e {
        void a(l lVar);
    }

    private w() {
        try {
            this.j = new Handler(Looper.getMainLooper());
        } catch (Exception e) {
            m.a(e);
        }
    }

    static synchronized w a() {
        w wVar;
        synchronized (w.class) {
            if (h == null) {
                h = new w();
            }
            wVar = h;
        }
        return wVar;
    }

    private void a(final long j) {
        if (this.n.compareAndSet(false, true)) {
            p.a(3, "OnOff", (Object) this, "Performing status check.");
            new Thread() {
                public void run() {
                    Looper.prepare();
                    Handler handler = new Handler();
                    handler.postDelayed(new a(w.this, "INM", handler, new e() {
                        public void a(l lVar) {
                            synchronized (w.i) {
                                boolean z = ((k) MoatAnalytics.getInstance()).a;
                                if (w.this.a != lVar.e() || (w.this.a == d.OFF && z)) {
                                    w.this.a = lVar.e();
                                    if (w.this.a == d.OFF && z) {
                                        w.this.a = d.ON;
                                    }
                                    if (w.this.a == d.ON) {
                                        p.a(3, "OnOff", (Object) this, "Moat enabled - Version 2.5.0");
                                    }
                                    for (c cVar : w.i) {
                                        if (w.this.a == d.ON) {
                                            cVar.b.c();
                                        } else {
                                            cVar.b.d();
                                        }
                                    }
                                }
                                while (!w.i.isEmpty()) {
                                    w.i.remove();
                                }
                            }
                        }
                    }, null), j);
                    Looper.loop();
                }
            }.start();
        }
    }

    private void d() {
        synchronized (i) {
            long currentTimeMillis = System.currentTimeMillis();
            Iterator it = i.iterator();
            while (it.hasNext()) {
                if (currentTimeMillis - ((c) it.next()).a.longValue() >= 60000) {
                    it.remove();
                }
            }
            if (i.size() >= 15) {
                for (int i = 0; i < 5; i++) {
                    i.remove();
                }
            }
        }
    }

    private void e() {
        if (this.k.compareAndSet(false, true)) {
            this.j.postDelayed(new Runnable() {
                public void run() {
                    try {
                        if (w.i.size() > 0) {
                            w.this.d();
                            w.this.j.postDelayed(this, 60000);
                            return;
                        }
                        w.this.k.compareAndSet(true, false);
                        w.this.j.removeCallbacks(this);
                    } catch (Exception e) {
                        m.a(e);
                    }
                }
            }, 60000);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(b bVar) {
        if (this.a == d.ON) {
            bVar.c();
            return;
        }
        d();
        i.add(new c(Long.valueOf(System.currentTimeMillis()), bVar));
        e();
    }

    /* Access modifiers changed, original: 0000 */
    public void b() {
        if (System.currentTimeMillis() - this.l > this.f) {
            a(0);
        }
    }
}
