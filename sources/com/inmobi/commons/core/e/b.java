package com.inmobi.commons.core.e;

import android.support.annotation.Nullable;
import com.facebook.internal.ServerProtocol;
import com.google.android.gms.cast.HlsSegmentFormat;
import com.inmobi.commons.a.a;
import com.inmobi.commons.core.b.d;
import com.inmobi.commons.core.b.e;
import com.inmobi.commons.core.configs.b.c;
import com.payu.custombrowser.util.CBConstant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class b implements e, c {
    public static AtomicBoolean b = new AtomicBoolean(false);
    private static final String c = "b";
    private static final Object d = new Object();
    private static volatile b e;
    private static Map<String, c> f;
    public ExecutorService a;
    private d g = new d();
    private e h;
    private String i;
    private d j;

    public static b a() {
        b bVar = e;
        if (bVar == null) {
            synchronized (d) {
                bVar = e;
                if (bVar == null) {
                    bVar = new b();
                    e = bVar;
                }
            }
        }
        return bVar;
    }

    public final void b() {
        b.set(false);
        com.inmobi.commons.core.configs.b.a().a(this.g, (c) this);
        a("telemetry", this.g.a);
        this.i = this.g.b;
        this.a.execute(new Runnable() {
            public final void run() {
                b.this.a.execute(new Runnable() {
                    public final void run() {
                        if (b.this.h.a(CBConstant.DEFAULT_VALUE) > 0) {
                            b.d(b.this);
                        }
                    }
                });
            }
        });
    }

    private b() {
        f = new HashMap();
        a("telemetry", this.g.a);
        this.i = this.g.b;
        this.h = new e();
        this.a = Executors.newSingleThreadExecutor();
    }

    public final void a(String str, JSONObject jSONObject) {
        a(str, new c(str, jSONObject, this.g.a));
    }

    private void a(String str, c cVar) {
        if (str != null && !str.trim().equals("")) {
            if (cVar != null) {
                f.put(str, cVar);
            } else {
                f.put(str, new c(str, null, this.g.a));
            }
        }
    }

    @Nullable
    private static c c(f fVar) {
        a();
        String str = fVar.d;
        return (str == null || str.trim().equals("")) ? null : (c) f.get(str);
    }

    public static void a(String str, String str2, Map<String, Object> map) {
        try {
            f fVar = new f(str, str2);
            if (!(map == null || map.isEmpty())) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    for (Entry entry : map.entrySet()) {
                        jSONObject.put((String) entry.getKey(), entry.getValue());
                    }
                    fVar.f = jSONObject.toString();
                } catch (JSONException e) {
                    StringBuilder stringBuilder = new StringBuilder("Error forming JSON payload for ");
                    stringBuilder.append(str2);
                    stringBuilder.append(" Error: ");
                    stringBuilder.append(e);
                }
            }
            a().a(fVar);
        } catch (Exception e2) {
            StringBuilder stringBuilder2 = new StringBuilder("Error in submitting telemetry event : (");
            stringBuilder2.append(e2.getMessage());
            stringBuilder2.append(")");
        }
    }

    public final void a(final f fVar) {
        c c = c(fVar);
        if (c != null && c.b && this.g.a.b) {
            this.a.execute(new Runnable() {
                public final void run() {
                    b.this.b(fVar);
                    b.d(b.this);
                }
            });
            return;
        }
        StringBuilder stringBuilder = new StringBuilder("Telemetry service is not enabled or registered for component: ");
        stringBuilder.append(fVar.d);
        stringBuilder.append("|| type = ");
        stringBuilder.append(fVar.c);
        stringBuilder.append(" Config :");
        stringBuilder.append(c);
    }

    public final void b(f fVar) {
        c c = c(fVar);
        if (c != null && c.b && this.g.a.b) {
            this.h.b(this.g.f, CBConstant.DEFAULT_VALUE);
            if ((this.h.a(CBConstant.DEFAULT_VALUE) + 1) - this.g.e >= 0) {
                e.a();
            }
            e.a(fVar);
            return;
        }
        StringBuilder stringBuilder = new StringBuilder("Telemetry service is not enabled or registered for component: ");
        stringBuilder.append(fVar.d);
        stringBuilder.append("|| type = ");
        stringBuilder.append(fVar.c);
        stringBuilder.append(" Config :");
        stringBuilder.append(c);
    }

    public final com.inmobi.commons.core.b.c a(String str) {
        List a;
        if (com.inmobi.commons.core.utilities.b.b.a() != 1) {
            a = e.a(this.g.h.b);
        } else {
            a = e.a(this.g.i.b);
        }
        if (!a.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            for (f fVar : a) {
                arrayList.add(Integer.valueOf(fVar.a));
            }
            str = a((List) a);
            if (str != null) {
                return new com.inmobi.commons.core.b.c(arrayList, str, true);
            }
        }
        return null;
    }

    @Nullable
    private static String a(List<f> list) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.putAll(com.inmobi.commons.core.utilities.b.b.a(false));
            hashMap.put("im-accid", a.e());
            hashMap.put(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, "2.0.0");
            hashMap.put("component", "telemetry");
            hashMap.put("mk-version", com.inmobi.commons.a.b.a());
            hashMap.putAll(com.inmobi.commons.core.utilities.b.a.a().b);
            JSONObject jSONObject = new JSONObject(hashMap);
            JSONArray jSONArray = new JSONArray();
            for (f fVar : list) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("eventId", fVar.b);
                jSONObject2.put("eventType", fVar.c);
                if (!fVar.a().trim().isEmpty()) {
                    jSONObject2.put("payload", fVar.a());
                }
                jSONObject2.put("componentType", fVar.d);
                jSONObject2.put(HlsSegmentFormat.TS, fVar.e);
                jSONArray.put(jSONObject2);
            }
            jSONObject.put("telemetry", jSONArray);
            return jSONObject.toString();
        } catch (JSONException unused) {
            return null;
        }
    }

    public final void a(com.inmobi.commons.core.configs.a aVar) {
        this.g = (d) aVar;
        this.i = this.g.b;
    }

    static /* synthetic */ void d(b bVar) {
        b bVar2 = bVar;
        if (!b.get()) {
            d dVar = bVar2.g;
            int i = dVar.d;
            long j = dVar.f;
            long j2 = dVar.c;
            long j3 = dVar.g;
            int i2 = dVar.i.b;
            int i3 = dVar.h.b;
            long j4 = dVar.i.a;
            long j5 = dVar.h.a;
            com.inmobi.commons.core.b.a aVar = r2;
            com.inmobi.commons.core.b.a aVar2 = new com.inmobi.commons.core.b.a(i, j, j2, j3, i2, i3, j4, j5);
            aVar.e = bVar2.i;
            aVar.b = CBConstant.DEFAULT_VALUE;
            if (bVar2.j == null) {
                bVar2.j = new d(bVar2.h, bVar2, aVar);
            } else {
                bVar2.j.a(aVar);
            }
            bVar2.j.a(CBConstant.DEFAULT_VALUE);
        }
    }
}
