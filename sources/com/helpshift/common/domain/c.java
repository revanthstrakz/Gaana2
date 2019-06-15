package com.helpshift.common.domain;

import com.helpshift.common.exception.NetworkException;
import com.helpshift.common.exception.RootAPIException;
import com.helpshift.j.c.a;
import com.helpshift.j.c.d;
import com.helpshift.util.l;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;

class c implements j {
    final ExecutorService a;

    c(ExecutorService executorService) {
        this.a = executorService;
    }

    public f a(final f fVar) {
        return new f() {
            public void a() {
                fVar.e = new Throwable();
                try {
                    c.this.a.submit(new Runnable() {
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
                                    l.a("Helpshift_CoreBgTh", str, new Throwable[]{e.b, fVar.e}, aVar);
                                }
                            } catch (Exception e2) {
                                l.b("Helpshift_CoreBgTh", "Caught unhandled exception inside BackgroundThreader", new Throwable[]{e2, fVar.e}, new a[0]);
                            }
                        }
                    });
                } catch (RejectedExecutionException e) {
                    l.c("Helpshift_CoreBgTh", "Rejected execution of task in BackgroundThreader", e);
                }
            }
        };
    }
}
