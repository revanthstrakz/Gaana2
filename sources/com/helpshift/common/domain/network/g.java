package com.helpshift.common.domain.network;

import com.helpshift.common.exception.NetworkException;
import com.helpshift.common.exception.RootAPIException;
import com.helpshift.common.platform.n;
import com.helpshift.common.platform.p;
import java.util.Map;

public class g implements h {
    private final h a;
    private final n b;

    public g(h hVar, p pVar) {
        this.a = hVar;
        this.b = pVar.n();
    }

    public com.helpshift.common.platform.network.g c(Map<String, String> map) {
        return a(map, 1);
    }

    private com.helpshift.common.platform.network.g a(Map<String, String> map, int i) {
        com.helpshift.common.platform.network.g c = this.a.c(map);
        if (c.a != 413) {
            return c;
        }
        if (i > 0) {
            return a(b(a(map)), i - 1);
        }
        throw RootAPIException.a(null, NetworkException.ENTITY_TOO_LARGE_RETRIES_EXHAUSTED);
    }

    private Map<String, String> a(Map<String, String> map) {
        map.put("meta", this.b.a((String) map.get("meta"), "custom_meta"));
        return map;
    }

    private Map<String, String> b(Map<String, String> map) {
        map.remove("custom_fields");
        return map;
    }
}
