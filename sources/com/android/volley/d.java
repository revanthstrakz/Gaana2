package com.android.volley;

import android.os.Handler;
import android.text.TextUtils;
import java.util.concurrent.Executor;

public class d implements j {
    private final Executor a;

    private class a implements Runnable {
        private final Request b;
        private final i c;
        private final Runnable d;

        public a(Request request, i iVar, Runnable runnable) {
            this.b = request;
            this.c = iVar;
            this.d = runnable;
        }

        public void run() {
            if (this.b.isCanceled()) {
                this.b.finish("canceled-at-delivery");
                return;
            }
            if (!this.c.a()) {
                this.b.deliverError(this.c.c);
            } else if (this.b.getTag() == null || TextUtils.isEmpty(this.b.getTag().toString()) || !this.b.getTag().toString().contains("citrus.volley")) {
                this.b.deliverResponse(this.c.a, this.c.e);
            } else {
                this.b.deliverResponse(this.c.a);
            }
            if (this.c.d) {
                this.b.addMarker("intermediate-response");
            } else {
                this.b.finish("done");
            }
            if (this.d != null) {
                this.d.run();
            }
        }
    }

    public d(final Handler handler) {
        this.a = new Executor() {
            public void execute(Runnable runnable) {
                handler.post(runnable);
            }
        };
    }

    public void a(Request<?> request, i<?> iVar) {
        a(request, iVar, null);
    }

    public void a(Request<?> request, i<?> iVar, Runnable runnable) {
        request.markDelivered();
        request.addMarker("post-response");
        this.a.execute(new a(request, iVar, runnable));
    }

    public void a(Request<?> request, VolleyError volleyError) {
        request.addMarker("post-error");
        this.a.execute(new a(request, i.a(volleyError), null));
    }
}
