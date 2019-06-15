package com.inmobi.commons.core.c;

import com.google.api.client.http.HttpMethods;
import com.inmobi.commons.core.b.c;
import com.inmobi.commons.core.network.e;
import com.inmobi.commons.core.utilities.uid.d;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class a {
    private static final String a = "a";
    private static volatile a b;
    private static ScheduledExecutorService c;
    private static final Object d = new Object();

    public static a a() {
        a aVar = b;
        if (aVar == null) {
            synchronized (d) {
                aVar = b;
                if (aVar == null) {
                    aVar = new a();
                    b = aVar;
                }
            }
        }
        return aVar;
    }

    private a() {
        c = Executors.newSingleThreadScheduledExecutor();
    }

    public final void a(c cVar, String str, int i, int i2, long j, d dVar, b bVar) {
        final c cVar2 = cVar;
        final int i3 = i;
        final int i4 = i2;
        if (com.inmobi.commons.core.utilities.d.a() && com.inmobi.commons.a.a.g()) {
            final String str2 = str;
            final d dVar2 = dVar;
            final com.inmobi.commons.core.network.c cVar3 = new com.inmobi.commons.core.network.c(HttpMethods.POST, str2, false, dVar2);
            HashMap hashMap = new HashMap();
            hashMap.put("payload", cVar2.b);
            cVar3.c(hashMap);
            int i5 = i3 - i4;
            if (i5 > 0) {
                Map hashMap2 = new HashMap();
                hashMap2.put("X-im-retry-count", String.valueOf(i5));
                cVar3.a(hashMap2);
            }
            cVar3.u = false;
            final long j2 = j;
            final b bVar2 = bVar;
            c.schedule(new Runnable() {
                public final void run() {
                    com.inmobi.commons.core.network.d a = new e(cVar3).a();
                    if (!a.a()) {
                        bVar2.a(cVar2);
                    } else if (i4 > 1) {
                        a.a;
                        StringBuilder stringBuilder = new StringBuilder("Unable to send trc events to server: ");
                        stringBuilder.append(a.b());
                        stringBuilder.append(". Will retry");
                        a.this.a(cVar2, str2, i3, i4 - 1, j2, dVar2, bVar2);
                    } else {
                        bVar2.a(cVar2, true);
                    }
                }
            }, i4 != i3 ? j : 0, TimeUnit.SECONDS);
            return;
        }
        bVar.a(cVar2, false);
    }
}
