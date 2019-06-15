package com.helpshift.common.domain;

import com.helpshift.common.exception.NetworkException;
import com.helpshift.common.exception.RootAPIException;
import com.helpshift.j.c.a;
import com.helpshift.j.c.d;
import com.helpshift.util.l;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class b implements d {
    final ScheduledExecutorService a;

    b(ScheduledExecutorService scheduledExecutorService) {
        this.a = scheduledExecutorService;
    }

    public f a(final f fVar, final long j) {
        return new f() {
            public void a() {
                fVar.e = new Throwable();
                try {
                    b.this.a.schedule(new Runnable() {
                        public void run() {
                            try {
                                fVar.a();
                            } catch (RootAPIException e) {
                                if (e.b()) {
                                    a aVar = null;
                                    String str = e.a == null ? "" : e.a;
                                    if (e.c instanceof NetworkException) {
                                        aVar = d.a("route", ((NetworkException) e.c).route);
                                    }
                                    l.a("Helpshift_CoreDelayTh", str, new Throwable[]{e.b, fVar.e}, aVar);
                                }
                            } catch (Exception e2) {
                                l.b("Helpshift_CoreDelayTh", "Caught unhandled exception inside BackgroundThreader", new Throwable[]{e2, fVar.e}, new a[0]);
                            }
                        }
                    }, j, TimeUnit.MILLISECONDS);
                } catch (RejectedExecutionException e) {
                    l.c("Helpshift_CoreDelayTh", "Rejected execution of task in BackgroundDelayedThreader", e);
                }
            }
        };
    }
}
