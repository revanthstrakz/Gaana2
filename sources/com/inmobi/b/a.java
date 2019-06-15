package com.inmobi.b;

import android.support.annotation.Nullable;
import com.facebook.internal.ServerProtocol;
import com.google.android.gms.cast.HlsSegmentFormat;
import com.inmobi.commons.core.b.d;
import com.inmobi.commons.core.b.e;
import com.inmobi.commons.core.configs.b.c;
import com.inmobi.commons.core.f.b;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class a implements e, c {
    public static AtomicBoolean b = new AtomicBoolean(false);
    private static final String e = "a";
    private static final Object f = new Object();
    private static volatile a g;
    public ExecutorService a = Executors.newSingleThreadExecutor();
    public com.inmobi.ads.c c = new com.inmobi.ads.c();
    public String d = this.c.b;
    private com.inmobi.commons.core.f.a h = new com.inmobi.commons.core.f.a();
    private d i;

    /* renamed from: com.inmobi.b.a$1 */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ b a;

        public AnonymousClass1(b bVar) {
            this.a = bVar;
        }

        public final void run() {
            com.inmobi.commons.core.utilities.b.e.c();
            if (com.inmobi.commons.core.utilities.b.e.d() == 0) {
                a.e;
                return;
            }
            a.a(a.this, this.a);
            a.a(a.this, this.a.j);
        }
    }

    public static a a() {
        a aVar = g;
        if (aVar == null) {
            synchronized (f) {
                aVar = g;
                if (aVar == null) {
                    aVar = new a();
                    g = aVar;
                }
            }
        }
        return aVar;
    }

    private a() {
    }

    public final void a(com.inmobi.commons.core.configs.a aVar) {
        this.c = (com.inmobi.ads.c) aVar;
        this.d = this.c.b;
    }

    private void b(final String str) {
        this.a.execute(new Runnable() {
            public final void run() {
                a.this.h;
                if (com.inmobi.commons.core.f.a.c(str)) {
                    a.a(a.this, str);
                }
            }
        });
    }

    public final com.inmobi.commons.core.b.c a(String str) {
        com.inmobi.ads.c.a b = this.c.b(str);
        List a;
        if (com.inmobi.commons.core.utilities.b.b.a() != 1) {
            a = com.inmobi.commons.core.f.a.a(b.f.b, str);
        } else {
            a = com.inmobi.commons.core.f.a.a(b.g.b, str);
        }
        if (!a.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            for (b bVar : a) {
                arrayList.add(Integer.valueOf(bVar.a));
            }
            str = a((List) a);
            if (str != null) {
                return new com.inmobi.commons.core.b.c(arrayList, str, false);
            }
        }
        return null;
    }

    @Nullable
    private static String a(List<b> list) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.putAll(com.inmobi.commons.core.utilities.b.b.a(false));
            hashMap.put("im-accid", com.inmobi.commons.a.a.e());
            hashMap.put(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, "2.0.0");
            hashMap.put("component", "trc");
            hashMap.put("adtype", ((b) list.get(0)).j);
            hashMap.put("mk-version", com.inmobi.commons.a.b.a());
            hashMap.putAll(com.inmobi.commons.core.utilities.b.a.a().b);
            JSONObject jSONObject = new JSONObject(hashMap);
            JSONArray jSONArray = new JSONArray();
            for (b bVar : list) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("event-id", bVar.b);
                jSONObject2.put("ad-markup-type", bVar.c);
                jSONObject2.put("event-name", bVar.d);
                jSONObject2.put("im-plid", bVar.e);
                jSONObject2.put("request-id", bVar.f);
                jSONObject2.put("event-type", bVar.g);
                jSONObject2.put("d-nettype-raw", bVar.h);
                jSONObject2.put(HlsSegmentFormat.TS, bVar.i);
                jSONArray.put(jSONObject2);
            }
            jSONObject.put("extra-info", jSONArray);
            return jSONObject.toString();
        } catch (JSONException unused) {
            return null;
        }
    }
}
