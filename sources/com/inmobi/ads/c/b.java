package com.inmobi.ads.c;

import com.inmobi.ads.InMobiAdRequestStatus;
import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.ads.ac;
import com.inmobi.ads.bi;
import com.inmobi.ads.i;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class b extends a {
    private static final String d = "b";
    private static volatile b e;
    private static final Object f = new Object();
    private static List<a> g = new LinkedList();

    /* renamed from: com.inmobi.ads.c.b$1 */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ bi a;

        public AnonymousClass1(bi biVar) {
            this.a = biVar;
        }

        public final void run() {
            try {
                b.a(b.this);
                if (!a.a.containsKey(this.a)) {
                    b.d;
                    StringBuilder stringBuilder = new StringBuilder("preLoadAdUnit. pid:");
                    stringBuilder.append(this.a.a);
                    stringBuilder.append(" tp:");
                    stringBuilder.append(this.a.b);
                    if (this.a.c == null && this.a.b != null) {
                        HashMap hashMap = new HashMap();
                        hashMap.put("tp", this.a.b);
                        this.a.c = hashMap;
                    }
                    a aVar = new a(this.a);
                    b.g.add(aVar);
                    ac a = com.inmobi.ads.ac.a.a(com.inmobi.commons.a.a.b(), this.a, aVar);
                    a.e = this.a.d;
                    a.f = this.a.c;
                    a.n = true;
                    a.a.put(this.a, a);
                    a.e(aVar);
                }
            } catch (Exception e) {
                b.d;
                new StringBuilder("SDK encountered an unexpected error preloading ad units; ").append(e.getMessage());
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            }
        }
    }

    static class a extends com.inmobi.ads.i.b {
        private bi a;

        public final boolean i() {
            return false;
        }

        a(bi biVar) {
            this.a = biVar;
        }

        public final void a(boolean z) {
            b.d;
        }

        public final void a() {
            b.d;
            b.g.remove(this);
        }

        public final void a(InMobiAdRequestStatus inMobiAdRequestStatus) {
            b.d;
            new StringBuilder("onAdLoadFailed called. Status:").append(inMobiAdRequestStatus.getMessage());
            i iVar = (i) a.a.remove(this.a);
            if (inMobiAdRequestStatus.getStatusCode() == StatusCode.NO_FILL) {
                iVar.d("PreLoadServerNoFill");
            }
            b.g.remove(this);
        }
    }

    public static b d() {
        b bVar = e;
        if (bVar == null) {
            synchronized (f) {
                bVar = e;
                if (bVar == null) {
                    bVar = new b();
                    e = bVar;
                }
            }
        }
        return bVar;
    }

    private b() {
        super("int");
    }

    public static void a(i iVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("type", iVar.b());
        hashMap.put("plId", Long.valueOf(iVar.d));
        hashMap.put("clientRequestId", iVar.l);
    }

    public static void a(String str, i iVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("errorCode", str);
        hashMap.put("type", iVar.b());
        hashMap.put("plId", Long.valueOf(iVar.d));
        hashMap.put("clientRequestId", iVar.l);
    }
}
