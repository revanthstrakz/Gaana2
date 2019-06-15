package com.inmobi.commons.core.a;

import android.support.annotation.Nullable;
import com.facebook.internal.ServerProtocol;
import com.google.android.gms.cast.HlsSegmentFormat;
import com.inmobi.commons.core.b.d;
import com.inmobi.commons.core.b.e;
import com.inmobi.commons.core.configs.b.c;
import com.inmobi.commons.core.e.b;
import com.inmobi.commons.core.e.f;
import com.payu.custombrowser.util.CBConstant;
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
    public ExecutorService a;
    public b c = new b();
    public String d;
    private c h;
    private d i;

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
        Thread.setDefaultUncaughtExceptionHandler(new e(Thread.getDefaultUncaughtExceptionHandler()));
        b.a().a("crashReporting", this.c.i);
        b.a().a("catchReporting", this.c.j);
        this.d = this.c.a;
        this.h = new c();
        this.a = Executors.newSingleThreadExecutor();
    }

    public final void a(com.inmobi.commons.core.configs.a aVar) {
        this.c = (b) aVar;
        this.d = this.c.a;
        b.a().a("crashReporting", this.c.i);
        b.a().a("catchReporting", this.c.j);
    }

    public final void a(final com.inmobi.commons.core.e.a aVar) {
        if (this.c.h) {
            b.a().a(new f("catchReporting", "CatchEventOccurred"));
            this.a.execute(new Runnable() {
                public final void run() {
                    a.this.a(aVar);
                    a.a(a.this);
                }
            });
        }
    }

    public final void a(d dVar) {
        if (!(dVar instanceof com.inmobi.commons.core.e.a)) {
            if (this.c.g) {
                b.a().b(new f("crashReporting", "CrashEventOccurred"));
            } else {
                return;
            }
        }
        this.h.b(this.c.e, CBConstant.DEFAULT_VALUE);
        if ((this.h.a(CBConstant.DEFAULT_VALUE) + 1) - this.c.d >= 0) {
            c.a();
        }
        c.a(dVar);
    }

    public final com.inmobi.commons.core.b.c a(String str) {
        List a;
        if (com.inmobi.commons.core.utilities.b.b.a() != 1) {
            a = c.a(this.c.k.b);
        } else {
            a = c.a(this.c.l.b);
        }
        if (!a.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            for (d dVar : a) {
                arrayList.add(Integer.valueOf(dVar.a));
            }
            str = a((List) a);
            if (str != null) {
                return new com.inmobi.commons.core.b.c(arrayList, str, false);
            }
        }
        return null;
    }

    @Nullable
    private static String a(List<d> list) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.putAll(com.inmobi.commons.core.utilities.b.b.a(false));
            hashMap.put("im-accid", com.inmobi.commons.a.a.e());
            hashMap.put(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, "2.0.0");
            hashMap.put("component", "crash");
            hashMap.put("mk-version", com.inmobi.commons.a.b.a());
            hashMap.putAll(com.inmobi.commons.core.utilities.b.a.a().b);
            JSONObject jSONObject = new JSONObject(hashMap);
            JSONArray jSONArray = new JSONArray();
            for (d dVar : list) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("eventId", dVar.b);
                jSONObject2.put("eventType", dVar.c);
                if (!dVar.a().trim().isEmpty()) {
                    jSONObject2.put("crash_report", dVar.a());
                }
                jSONObject2.put(HlsSegmentFormat.TS, dVar.e);
                jSONArray.put(jSONObject2);
            }
            jSONObject.put("crash", jSONArray);
            return jSONObject.toString();
        } catch (JSONException unused) {
            return null;
        }
    }
}
