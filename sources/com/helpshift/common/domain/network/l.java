package com.helpshift.common.domain.network;

import com.google.api.client.http.HttpStatusCodes;
import com.helpshift.common.exception.NetworkException;
import com.helpshift.common.exception.RootAPIException;
import com.helpshift.common.platform.network.c;
import com.helpshift.common.platform.network.d;
import com.helpshift.common.platform.network.g;
import com.helpshift.common.platform.p;
import com.helpshift.common.util.a;
import java.util.List;
import java.util.Map;

public class l implements h {
    private final h a;
    private final d b;

    public l(h hVar, p pVar) {
        this.a = hVar;
        this.b = pVar.q();
    }

    public g c(Map<String, String> map) {
        return a((Map) map, 3);
    }

    private g a(Map<String, String> map, int i) {
        g c = this.a.c(map);
        if (c.a != HttpStatusCodes.STATUS_CODE_UNPROCESSABLE_ENTITY) {
            return c;
        }
        if (i != 0) {
            i--;
            String a = a(c.c, "HS-UEpoch");
            if (a != null) {
                this.b.a(a.a(a));
            }
            return a((Map) map, i);
        }
        throw RootAPIException.a(null, NetworkException.TIMESTAMP_CORRECTION_RETRIES_EXHAUSTED);
    }

    private String a(List<c> list, String str) {
        for (c cVar : list) {
            if (cVar.a != null && cVar.a.equals(str)) {
                return cVar.b;
            }
        }
        return null;
    }
}
