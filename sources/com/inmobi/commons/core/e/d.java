package com.inmobi.commons.core.e;

import com.til.colombia.android.internal.e;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

class d extends com.inmobi.commons.core.configs.a {
    private static final String j = "d";
    c a = new c();
    String b = "https://telemetry.sdk.inmobi.com/metrics";
    long c = 150;
    int d = 3;
    int e = 1000;
    long f = 259200;
    long g = 86400;
    a h;
    a i;

    public final class a {
        long a;
        int b;

        public final boolean a() {
            return this.b <= d.this.e && this.a > 0 && this.b > 0;
        }
    }

    public final String a() {
        return "telemetry";
    }

    public d() {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("retryInterval", 10);
            jSONObject2.put("maxBatchSize", 25);
            jSONObject.put(e.ad, jSONObject2);
            jSONObject2 = new JSONObject();
            jSONObject2.put("retryInterval", 10);
            jSONObject2.put("maxBatchSize", 25);
            jSONObject.put("others", jSONObject2);
            b(jSONObject);
        } catch (JSONException unused) {
        }
    }

    /* JADX WARNING: Missing block: B:32:0x0070, code skipped:
            return false;
     */
    /* JADX WARNING: Missing block: B:33:0x0071, code skipped:
            return false;
     */
    /* JADX WARNING: Missing block: B:34:0x0072, code skipped:
            return false;
     */
    public final boolean c() {
        /*
        r6 = this;
        r0 = r6.a;
        r1 = 0;
        if (r0 != 0) goto L_0x0006;
    L_0x0005:
        return r1;
    L_0x0006:
        r0 = r6.b;
        r0 = r0.trim();
        r0 = r0.length();
        if (r0 == 0) goto L_0x0072;
    L_0x0012:
        r0 = r6.b;
        r2 = "http://";
        r0 = r0.startsWith(r2);
        if (r0 != 0) goto L_0x0027;
    L_0x001c:
        r0 = r6.b;
        r2 = "https://";
        r0 = r0.startsWith(r2);
        if (r0 != 0) goto L_0x0027;
    L_0x0026:
        goto L_0x0072;
    L_0x0027:
        r2 = r6.g;
        r4 = r6.c;
        r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r0 < 0) goto L_0x0071;
    L_0x002f:
        r2 = r6.g;
        r4 = r6.f;
        r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r0 > 0) goto L_0x0071;
    L_0x0037:
        r2 = r6.f;
        r4 = r6.c;
        r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r0 >= 0) goto L_0x0040;
    L_0x003f:
        goto L_0x0071;
    L_0x0040:
        r0 = r6.h;
        r0 = r0.a();
        if (r0 == 0) goto L_0x0070;
    L_0x0048:
        r0 = r6.i;
        r0 = r0.a();
        if (r0 != 0) goto L_0x0051;
    L_0x0050:
        goto L_0x0070;
    L_0x0051:
        r2 = r6.c;
        r4 = 0;
        r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r0 < 0) goto L_0x006f;
    L_0x0059:
        r0 = r6.d;
        if (r0 < 0) goto L_0x006f;
    L_0x005d:
        r2 = r6.g;
        r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r0 <= 0) goto L_0x006f;
    L_0x0063:
        r2 = r6.f;
        r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r0 <= 0) goto L_0x006f;
    L_0x0069:
        r0 = r6.e;
        if (r0 <= 0) goto L_0x006f;
    L_0x006d:
        r0 = 1;
        return r0;
    L_0x006f:
        return r1;
    L_0x0070:
        return r1;
    L_0x0071:
        return r1;
    L_0x0072:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.commons.core.e.d.c():boolean");
    }

    private void b(JSONObject jSONObject) throws JSONException {
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            JSONObject jSONObject2 = jSONObject.getJSONObject(str);
            a aVar = new a();
            aVar.a = jSONObject2.getLong("retryInterval");
            aVar.b = jSONObject2.getInt("maxBatchSize");
            Object obj = -1;
            int hashCode = str.hashCode();
            if (hashCode != -1068855134) {
                if (hashCode != -1006804125) {
                    if (hashCode == 3649301 && str.equals(e.ad)) {
                        obj = null;
                    }
                } else if (str.equals("others")) {
                    obj = 2;
                }
            } else if (str.equals("mobile")) {
                obj = 1;
            }
            if (obj != null) {
                this.h = aVar;
            } else {
                this.i = aVar;
            }
        }
    }

    public final void a(JSONObject jSONObject) throws JSONException {
        super.a(jSONObject);
        JSONObject jSONObject2 = jSONObject.getJSONObject("base");
        this.a.b = jSONObject2.getBoolean("enabled");
        this.a.a = jSONObject2.getInt("samplingFactor");
        this.b = jSONObject.getString("telemetryUrl");
        this.c = jSONObject.getLong("processingInterval");
        this.d = jSONObject.getInt("maxRetryCount");
        this.e = jSONObject.getInt("maxEventsToPersist");
        this.f = jSONObject.getLong("eventTTL");
        this.g = jSONObject.getLong("txLatency");
        b(jSONObject.getJSONObject("networkType"));
    }

    public final JSONObject b() throws JSONException {
        JSONObject b = super.b();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("enabled", this.a.b);
        jSONObject.put("samplingFactor", this.a.a);
        b.put("base", jSONObject);
        b.put("telemetryUrl", this.b);
        b.put("processingInterval", this.c);
        b.put("maxRetryCount", this.d);
        b.put("maxEventsToPersist", this.e);
        b.put("eventTTL", this.f);
        b.put("txLatency", this.g);
        jSONObject = new JSONObject();
        a aVar = this.i;
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("retryInterval", aVar.a);
        jSONObject2.put("maxBatchSize", aVar.b);
        jSONObject.put(e.ad, jSONObject2);
        aVar = this.h;
        jSONObject2 = new JSONObject();
        jSONObject2.put("retryInterval", aVar.a);
        jSONObject2.put("maxBatchSize", aVar.b);
        jSONObject.put("others", jSONObject2);
        b.put("networkType", jSONObject);
        return b;
    }

    public final com.inmobi.commons.core.configs.a d() {
        return new d();
    }
}
