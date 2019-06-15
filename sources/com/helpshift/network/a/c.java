package com.helpshift.network.a;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.google.api.client.http.HttpStatusCodes;
import com.helpshift.common.domain.network.j;
import com.helpshift.network.b.d;
import com.helpshift.network.b.e;
import com.helpshift.network.b.f;
import com.helpshift.network.errors.NetworkError;
import com.helpshift.network.h;
import com.helpshift.util.l;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class c {
    final h a;
    final f b;
    private ExecutorService c;

    public static class a {
        public static final Integer a = Integer.valueOf(1);
    }

    protected c(h hVar, f fVar, ExecutorService executorService) {
        this.a = hVar;
        this.c = executorService;
        this.b = fVar;
    }

    public static c a(h hVar, Integer num, ExecutorService executorService) {
        f aVar;
        if (a.a.equals(num)) {
            HandlerThread handlerThread = new HandlerThread("HS-cmnet-rspns");
            handlerThread.start();
            aVar = new com.helpshift.network.b.a(new Handler(handlerThread.getLooper()));
        } else {
            aVar = new com.helpshift.network.b.a(new Handler(Looper.getMainLooper()));
        }
        return new c(hVar, aVar, executorService);
    }

    public Future a(final a aVar) {
        return this.c.submit(new Callable() {
            public Object call() throws Exception {
                try {
                    d a = c.this.a.a(aVar);
                    if (a.a >= HttpStatusCodes.STATUS_CODE_MULTIPLE_CHOICES) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Api result : ");
                        stringBuilder.append(aVar.b);
                        stringBuilder.append(", Status : ");
                        stringBuilder.append(a.a);
                        l.a("HS_RequestQueue", stringBuilder.toString());
                    }
                    if (!a.d) {
                        e a2 = aVar.a(a);
                        c.this.b.a(aVar, a2);
                        return a2;
                    } else if (aVar.h()) {
                        return null;
                    } else {
                        throw new NetworkError(j.i);
                    }
                } catch (NetworkError e) {
                    Throwable[] thArr = new Throwable[]{e};
                    com.helpshift.j.c.a[] aVarArr = new com.helpshift.j.c.a[2];
                    aVarArr[0] = com.helpshift.j.c.d.a("route", aVar.b);
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(e.a());
                    stringBuilder2.append("");
                    aVarArr[1] = com.helpshift.j.c.d.a("reason", stringBuilder2.toString());
                    l.a("HS_RequestQueue", "Network error", thArr, aVarArr);
                    c.this.a(aVar, e);
                    return e;
                }
            }
        });
    }

    /* Access modifiers changed, original: protected */
    public void a(a aVar, NetworkError networkError) {
        this.b.a(aVar, aVar.a(networkError));
    }
}
