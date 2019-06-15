package com.helpshift.common.domain.network;

import com.google.api.client.http.HttpStatusCodes;
import com.helpshift.common.platform.network.c;
import com.helpshift.common.platform.network.d;
import com.helpshift.common.platform.network.g;
import com.helpshift.common.platform.p;
import java.util.List;
import java.util.Map;

public class b implements h {
    private final h a;
    private final d b;
    private final String c;

    public b(h hVar, p pVar, String str) {
        this.a = hVar;
        this.b = pVar.q();
        this.c = str;
    }

    public g c(Map<String, String> map) {
        g c = this.a.c(map);
        int i = c.a;
        if (i >= 200 && i < HttpStatusCodes.STATUS_CODE_MULTIPLE_CHOICES) {
            String a = a(c.c, "ETag");
            if (a != null) {
                this.b.a(this.c, a);
            }
        }
        return c;
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
