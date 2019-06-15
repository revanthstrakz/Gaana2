package com.inmobi.commons.core.configs;

import org.json.JSONException;
import org.json.JSONObject;

public final class c {
    com.inmobi.commons.core.d.c a = com.inmobi.commons.core.d.c.b("config_store");

    public final void a(a aVar) {
        com.inmobi.commons.core.d.c cVar = this.a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(aVar.a());
        stringBuilder.append("_config");
        String c = cVar.c(stringBuilder.toString());
        if (c != null) {
            try {
                aVar.a(new JSONObject(c));
            } catch (JSONException unused) {
            }
        }
    }

    public final boolean a(String str) {
        com.inmobi.commons.core.d.c cVar = this.a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append("_config");
        return cVar.c(stringBuilder.toString()) != null;
    }

    public final long b(String str) {
        com.inmobi.commons.core.d.c cVar = this.a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append("_config_update_ts");
        return cVar.b(stringBuilder.toString(), 0);
    }

    public final void a(String str, long j) {
        com.inmobi.commons.core.d.c cVar = this.a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append("_config_update_ts");
        cVar.a(stringBuilder.toString(), j);
    }
}
