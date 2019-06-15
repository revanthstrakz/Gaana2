package com.helpshift.common.platform;

import com.helpshift.common.platform.network.d;
import com.helpshift.util.l;
import java.io.Serializable;
import java.util.HashMap;

public class j implements d {
    private o a;

    public j(o oVar) {
        this.a = oVar;
    }

    public void a(float f) {
        this.a.a("server_time_delta", Float.valueOf(f));
        l.a(f);
    }

    public float a() {
        return this.a.b("server_time_delta", Float.valueOf(0.0f)).floatValue();
    }

    public void a(String str, String str2) {
        Serializable hashMap;
        Object b = this.a.b("route_etag_map");
        if (b == null) {
            hashMap = new HashMap();
        } else {
            hashMap = (HashMap) b;
        }
        hashMap.put(str, str2);
        this.a.a("route_etag_map", hashMap);
    }

    public String a(String str) {
        Object b = this.a.b("route_etag_map");
        if (b == null) {
            return null;
        }
        return (String) ((HashMap) b).get(str);
    }
}
