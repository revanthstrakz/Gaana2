package com.helpshift.analytics.a;

import com.helpshift.account.a.b;
import com.helpshift.analytics.AnalyticsEventType;
import com.helpshift.common.AutoRetryFailedEventDM.EventType;
import com.helpshift.common.domain.e;
import com.helpshift.common.domain.network.c;
import com.helpshift.common.domain.network.f;
import com.helpshift.common.domain.network.h;
import com.helpshift.common.domain.network.k;
import com.helpshift.common.exception.NetworkException;
import com.helpshift.common.exception.RootAPIException;
import com.helpshift.common.platform.Device;
import com.helpshift.common.platform.n;
import com.helpshift.common.platform.p;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public class a implements com.helpshift.common.a {
    private static final DecimalFormat e = new DecimalFormat("0.000", new DecimalFormatSymbols(Locale.US));
    private final e a;
    private final p b;
    private final n c;
    private final com.helpshift.analytics.a d;
    private List<com.helpshift.analytics.b.a> f;
    private com.helpshift.configuration.a.a g;

    public a(e eVar, p pVar) {
        this.a = eVar;
        this.b = pVar;
        this.c = pVar.n();
        this.d = pVar.h();
        this.g = eVar.c();
        this.a.m().a(EventType.ANALYTICS, (com.helpshift.common.a) this);
    }

    private void a(com.helpshift.analytics.b.a aVar) {
        if (this.f == null) {
            this.f = new ArrayList();
        }
        this.f.add(aVar);
    }

    public synchronized void a(AnalyticsEventType analyticsEventType, Map<String, Object> map) {
        a(new com.helpshift.analytics.b.a(UUID.randomUUID().toString(), analyticsEventType, map, e.format(((double) System.currentTimeMillis()) / 1000.0d)));
    }

    public synchronized void a(AnalyticsEventType analyticsEventType, String str) {
        Map hashMap = new HashMap();
        hashMap.put("id", str);
        a(analyticsEventType, hashMap);
    }

    public synchronized void a(AnalyticsEventType analyticsEventType) {
        a(analyticsEventType, (Map) null);
    }

    public void a(b bVar) {
        List c = c();
        a();
        a(c, bVar);
    }

    public void b() {
        Map a = this.d.a();
        if (a != null && a.size() > 0) {
            h d = d();
            for (String str : a.keySet()) {
                try {
                    d.c((Map) a.get(str));
                    this.d.a(str);
                } catch (RootAPIException e) {
                    if (e.c == NetworkException.NON_RETRIABLE) {
                        this.d.a(str);
                    } else {
                        throw e;
                    }
                }
            }
        }
    }

    private void a(List<com.helpshift.analytics.b.a> list, b bVar) {
        if (!com.helpshift.common.b.a(list)) {
            HashMap a = a(this.c.b((List) list), bVar);
            try {
                d().c(a);
            } catch (RootAPIException e) {
                if (e.c != NetworkException.NON_RETRIABLE) {
                    this.d.a(UUID.randomUUID().toString(), a);
                    this.a.m().a(EventType.ANALYTICS, e.a());
                    throw e;
                }
            }
        }
    }

    private h d() {
        return new f(new c(new k("/events/", this.a, this.b)));
    }

    private HashMap<String, String> a(String str, b bVar) {
        HashMap hashMap = new HashMap();
        hashMap.put(com.facebook.ads.internal.g.e.a, str);
        if (bVar != null) {
            hashMap.put("id", bVar.f);
            hashMap.put("profile-id", bVar.b);
            str = bVar.b();
            if (!com.helpshift.common.c.a(str)) {
                hashMap.put("uid", str);
            }
        }
        Device d = this.b.d();
        hashMap.put("v", d.b());
        hashMap.put(com.til.colombia.android.internal.e.C, d.c());
        hashMap.put("av", d.e());
        hashMap.put("dm", d.k());
        hashMap.put("s", this.g.c("sdkType"));
        String c = this.g.c("pluginVersion");
        String c2 = this.g.c("runtimeVersion");
        if (!com.helpshift.common.c.a(c)) {
            hashMap.put(com.til.colombia.android.internal.e.l, c);
        }
        if (!com.helpshift.common.c.a(c2)) {
            hashMap.put("rv", c2);
        }
        hashMap.put("rs", d.l());
        c = d.m();
        if (!com.helpshift.common.c.a(c)) {
            hashMap.put("cc", c);
        }
        hashMap.put("ln", d.h());
        return hashMap;
    }

    public synchronized void a() {
        if (this.f != null) {
            this.f.clear();
        }
    }

    public synchronized List<com.helpshift.analytics.b.a> c() {
        ArrayList arrayList;
        arrayList = new ArrayList();
        if (this.f != null) {
            arrayList.addAll(this.f);
        }
        return arrayList;
    }

    public void b(b bVar) {
        a(Collections.singletonList(new com.helpshift.analytics.b.a(UUID.randomUUID().toString(), AnalyticsEventType.APP_START, null, e.format(((double) System.currentTimeMillis()) / 1000.0d))), bVar);
    }
}
