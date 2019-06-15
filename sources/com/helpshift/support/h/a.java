package com.helpshift.support.h;

import com.helpshift.common.c;
import com.helpshift.common.platform.o;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class a implements com.helpshift.analytics.a {
    private o a;

    public a(o oVar) {
        this.a = oVar;
    }

    public void a(String str, HashMap<String, String> hashMap) {
        Serializable b = b();
        b.put(str, hashMap);
        this.a.a("unsent_analytics_events", b);
    }

    public void a(String str) {
        if (!c.a(str)) {
            Serializable b = b();
            b.remove(str);
            if (b.size() == 0) {
                this.a.a("unsent_analytics_events", null);
            } else {
                this.a.a("unsent_analytics_events", b);
            }
        }
    }

    public Map<String, HashMap<String, String>> a() {
        return b();
    }

    private HashMap<String, HashMap<String, String>> b() {
        Object b = this.a.b("unsent_analytics_events");
        if (b == null) {
            return new HashMap();
        }
        return (HashMap) b;
    }
}
