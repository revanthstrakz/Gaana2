package com.helpshift.d;

import android.os.Handler;
import android.os.HandlerThread;
import com.comscore.measurement.MeasurementDispatcher;
import com.helpshift.b.a;
import com.helpshift.common.b.c;
import com.helpshift.network.errors.NetworkError;
import com.helpshift.o.b;
import com.helpshift.q.d;
import com.helpshift.util.l;
import com.helpshift.util.o;
import com.helpshift.util.x;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class e implements a, b {
    Set<String> a;
    private final x b;
    private final d c;
    private final LinkedBlockingQueue<com.helpshift.h.a> d = new LinkedBlockingQueue();
    private final Map<String, com.helpshift.o.d> e = new HashMap();
    private AtomicBoolean f;
    private Handler g;
    private Runnable h;
    private c i;

    public e(d dVar, x xVar, com.helpshift.o.d... dVarArr) {
        int i = 0;
        this.f = new AtomicBoolean(false);
        this.c = dVar;
        this.b = xVar;
        o.a().a((a) this);
        int length = dVarArr.length;
        while (i < length) {
            com.helpshift.o.d dVar2 = dVarArr[i];
            this.e.put(dVar2.a(), dVar2);
            i++;
        }
    }

    private Runnable e() {
        if (this.h == null) {
            this.h = new Runnable() {
                public void run() {
                    if (e.this.a != null) {
                        e.this.a(false, (String[]) e.this.a.toArray(new String[e.this.a.size()]));
                    }
                    e.this.d();
                }
            };
        }
        return this.h;
    }

    public void a(com.helpshift.o.d dVar) {
        this.e.put(dVar.a(), dVar);
    }

    public void a(com.helpshift.h.a... aVarArr) {
        for (com.helpshift.h.a aVar : aVarArr) {
            if (this.e.containsKey(aVar.e())) {
                this.d.add(aVar);
            }
        }
    }

    /* Access modifiers changed, original: varargs */
    public void a(boolean z, String... strArr) {
        for (String str : strArr) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Triggering sync for  type : ");
            stringBuilder.append(str);
            l.a("Helpshift_SyncControl", stringBuilder.toString());
            if (b(str)) {
                b(str, true);
            } else if (z) {
                com.helpshift.o.d dVar = (com.helpshift.o.d) this.e.get(str);
                if (dVar != null && dVar.a(e(str), f(str))) {
                    b(str, false);
                }
            } else {
                b(str, false);
            }
        }
    }

    private void b(String str, boolean z) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Dispatching sync for type :");
        stringBuilder.append(str);
        stringBuilder.append(", isFullSync : ");
        stringBuilder.append(z);
        l.a("Helpshift_SyncControl", stringBuilder.toString());
        com.helpshift.h.a c = c(str);
        if (c == null) {
            return;
        }
        if (z) {
            c.d();
        } else {
            c.b();
        }
    }

    private com.helpshift.h.a c(String str) {
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            com.helpshift.h.a aVar = (com.helpshift.h.a) it.next();
            if (aVar.e().equals(str)) {
                return aVar;
            }
        }
        return null;
    }

    public void a(String str, long j) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Scheduling sync : ");
        stringBuilder.append(str);
        stringBuilder.append(", Delay : ");
        stringBuilder.append(j);
        l.a("Helpshift_SyncControl", stringBuilder.toString());
        if (j(str)) {
            if (this.f.compareAndSet(false, true)) {
                f();
                this.g.postDelayed(e(), j);
            }
            i(str);
        }
    }

    private void d(String str) {
        a(str, 60000);
    }

    public void a(String str, int i) {
        if (i > 0) {
            HashMap h = h(str);
            h.put("count", Integer.toString(Integer.valueOf((String) h.get("count")).intValue() + i));
            this.c.a(str, h);
            d(str);
        }
    }

    public void b(String str, int i) {
        HashMap h = h(str);
        int intValue = Integer.valueOf((String) h.get("count")).intValue();
        h.put("count", Integer.toString(i));
        this.c.a(str, h);
        if (intValue != i && i > 0) {
            d(str);
        }
    }

    public void a(String str, boolean z) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Data sync complete : ");
        stringBuilder.append(str);
        stringBuilder.append(", Full sync : ");
        stringBuilder.append(z);
        l.a("Helpshift_SyncControl", stringBuilder.toString());
        String l = Long.toString(this.b.c());
        HashMap h = h(str);
        h.put("count", Integer.toString(0));
        h.put("sync_time", l);
        if (z) {
            h.put("full_sync_time", l);
        }
        this.c.a(str, h);
        if (this.i != null) {
            this.i.a();
        }
    }

    public void a(String str, NetworkError networkError) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Data sync failed : ");
        stringBuilder.append(str);
        stringBuilder.append(", Error : ");
        stringBuilder.append(networkError.getMessage());
        l.b("Helpshift_SyncControl", stringBuilder.toString());
        com.helpshift.o.d dVar = (com.helpshift.o.d) this.e.get(str);
        if (dVar != null) {
            Object obj = -1;
            int hashCode = str.hashCode();
            if (hashCode != 1056354406) {
                if (hashCode == 1192222481 && str.equals("data_type_analytics_event")) {
                    obj = 1;
                }
            } else if (str.equals("data_type_switch_user")) {
                obj = null;
            }
            switch (obj) {
                case null:
                case 1:
                    if (dVar instanceof b) {
                        ((b) dVar).b();
                        break;
                    }
                    break;
            }
        }
        if (this.i == null) {
            this.i = new c.a().a(com.helpshift.common.b.a.a(5, TimeUnit.SECONDS)).a(10).a(c.b.a).a();
        }
        Integer a = networkError.a();
        long a2 = a != null ? this.i.a(a.intValue()) : -100;
        if (a2 != -100) {
            a(str, a2);
        }
    }

    private int e(String str) {
        return Integer.valueOf((String) h(str).get("count")).intValue();
    }

    private long f(String str) {
        return this.b.c() - Long.valueOf((String) h(str).get("sync_time")).longValue();
    }

    private long g(String str) {
        return this.b.c() - Long.valueOf((String) h(str).get("full_sync_time")).longValue();
    }

    /* JADX WARNING: Removed duplicated region for block: B:9:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0071  */
    private java.util.HashMap<java.lang.String, java.lang.String> h(java.lang.String r11) {
        /*
        r10 = this;
        r0 = r10.c;
        r0 = r0.a(r11);
        r0 = (java.util.HashMap) r0;
        r1 = 0;
        r2 = 1;
        r3 = 0;
        if (r0 != 0) goto L_0x0030;
    L_0x000e:
        r0 = new java.util.HashMap;
        r0.<init>();
        r5 = "count";
        r1 = java.lang.Integer.toString(r1);
        r0.put(r5, r1);
        r1 = "sync_time";
        r5 = java.lang.Long.toString(r3);
        r0.put(r1, r5);
        r1 = "full_sync_time";
        r5 = java.lang.Long.toString(r3);
        r0.put(r1, r5);
    L_0x002e:
        r1 = r2;
        goto L_0x005d;
    L_0x0030:
        r5 = r10.b;
        r5 = r5.c();
        r7 = "sync_time";
        r7 = r0.get(r7);
        r7 = (java.lang.String) r7;
        r7 = java.lang.Long.valueOf(r7);
        r7 = r7.longValue();
        r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1));
        if (r9 >= 0) goto L_0x005d;
    L_0x004a:
        r1 = "sync_time";
        r5 = java.lang.Long.toString(r3);
        r0.put(r1, r5);
        r1 = "full_sync_time";
        r5 = java.lang.Long.toString(r3);
        r0.put(r1, r5);
        goto L_0x002e;
    L_0x005d:
        r5 = "full_sync_time";
        r5 = r0.containsKey(r5);
        if (r5 != 0) goto L_0x006f;
    L_0x0065:
        r1 = "full_sync_time";
        r3 = java.lang.Long.toString(r3);
        r0.put(r1, r3);
        r1 = r2;
    L_0x006f:
        if (r1 == 0) goto L_0x0076;
    L_0x0071:
        r1 = r10.c;
        r1.a(r11, r0);
    L_0x0076:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.d.e.h(java.lang.String):java.util.HashMap");
    }

    public boolean b(String str) {
        com.helpshift.h.a c = c(str);
        return c != null && c.a() && g(str) > MeasurementDispatcher.MILLIS_PER_DAY;
    }

    public void a(String str) {
        a(c("data_type_switch_user"));
    }

    public void c() {
        a(c("data_type_device"));
    }

    private void a(com.helpshift.h.a aVar) {
        if (aVar != null) {
            Set c = aVar.c();
            if (c != null) {
                Iterator it = c.iterator();
                while (it.hasNext()) {
                    a(false, (String) it.next());
                }
            }
        }
    }

    private void i(String str) {
        if (this.a == null) {
            this.a = new HashSet();
        }
        this.a.add(str);
    }

    public void a() {
        a(true, "data_type_switch_user", "data_type_user", "data_type_analytics_event");
    }

    public void b() {
        g();
        a(true, "data_type_switch_user", "data_type_device", "data_type_user", "data_type_session", "data_type_analytics_event");
    }

    private void f() {
        if (this.g == null) {
            HandlerThread handlerThread = new HandlerThread("HS-cm-agg-sync");
            handlerThread.start();
            this.g = new Handler(handlerThread.getLooper());
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void d() {
        this.f.compareAndSet(true, false);
        if (this.a != null) {
            this.a.clear();
        }
    }

    private void g() {
        d();
        if (this.g != null && this.h != null) {
            this.g.removeCallbacks(e());
        }
    }

    private boolean j(String str) {
        return str.equals("data_type_user") || str.equals("data_type_analytics_event") || ((str.equals("data_type_device") && com.helpshift.k.b.a().b.f().booleanValue()) || str.equals("data_type_switch_user"));
    }
}
