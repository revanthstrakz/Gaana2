package com.inmobi.commons.core.a;

import com.til.colombia.android.internal.e;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class b extends com.inmobi.commons.core.configs.a {
    private static final String m = "b";
    public String a = "https://crash-metrics.sdk.inmobi.com/trace";
    long b = 0;
    int c = 3;
    int d = 50;
    long e = 2592000;
    long f = 86400;
    boolean g = false;
    boolean h = false;
    JSONObject i;
    JSONObject j;
    a k;
    a l;

    public final class a {
        long a;
        int b;

        public final boolean a() {
            return this.b <= b.this.d && this.a > 0 && this.b > 0;
        }
    }

    public final String a() {
        return "crashReporting";
    }

    public b() {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("retryInterval", 10);
            jSONObject2.put("maxBatchSize", 1);
            jSONObject.put(e.ad, jSONObject2);
            jSONObject2 = new JSONObject();
            jSONObject2.put("retryInterval", 10);
            jSONObject2.put("maxBatchSize", 1);
            jSONObject.put("others", jSONObject2);
            b(jSONObject);
            jSONObject = new JSONObject();
            jSONObject2 = new JSONObject();
            jSONObject2.put("enabled", false);
            jSONObject2.put("samplingFactor", 0);
            jSONObject.put("catchEvent", jSONObject2);
            jSONObject2 = new JSONObject();
            jSONObject2.put("enabled", false);
            jSONObject2.put("samplingFactor", 0);
            jSONObject.put("crashEvent", jSONObject2);
            c(jSONObject);
        } catch (JSONException unused) {
        }
    }

    /* JADX WARNING: Missing block: B:29:0x006b, code skipped:
            return false;
     */
    /* JADX WARNING: Missing block: B:30:0x006c, code skipped:
            return false;
     */
    /* JADX WARNING: Missing block: B:31:0x006d, code skipped:
            return false;
     */
    public final boolean c() {
        /*
        r6 = this;
        r0 = r6.a;
        r0 = r0.trim();
        r0 = r0.length();
        r1 = 0;
        if (r0 == 0) goto L_0x006d;
    L_0x000d:
        r0 = r6.a;
        r2 = "http://";
        r0 = r0.startsWith(r2);
        if (r0 != 0) goto L_0x0022;
    L_0x0017:
        r0 = r6.a;
        r2 = "https://";
        r0 = r0.startsWith(r2);
        if (r0 != 0) goto L_0x0022;
    L_0x0021:
        goto L_0x006d;
    L_0x0022:
        r2 = r6.f;
        r4 = r6.b;
        r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r0 < 0) goto L_0x006c;
    L_0x002a:
        r2 = r6.f;
        r4 = r6.e;
        r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r0 > 0) goto L_0x006c;
    L_0x0032:
        r2 = r6.e;
        r4 = r6.b;
        r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r0 >= 0) goto L_0x003b;
    L_0x003a:
        goto L_0x006c;
    L_0x003b:
        r0 = r6.k;
        r0 = r0.a();
        if (r0 == 0) goto L_0x006b;
    L_0x0043:
        r0 = r6.l;
        r0 = r0.a();
        if (r0 != 0) goto L_0x004c;
    L_0x004b:
        goto L_0x006b;
    L_0x004c:
        r2 = r6.b;
        r4 = 0;
        r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r0 < 0) goto L_0x006a;
    L_0x0054:
        r0 = r6.c;
        if (r0 < 0) goto L_0x006a;
    L_0x0058:
        r2 = r6.f;
        r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r0 <= 0) goto L_0x006a;
    L_0x005e:
        r2 = r6.e;
        r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r0 <= 0) goto L_0x006a;
    L_0x0064:
        r0 = r6.d;
        if (r0 <= 0) goto L_0x006a;
    L_0x0068:
        r0 = 1;
        return r0;
    L_0x006a:
        return r1;
    L_0x006b:
        return r1;
    L_0x006c:
        return r1;
    L_0x006d:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.commons.core.a.b.c():boolean");
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
                this.k = aVar;
            } else {
                this.l = aVar;
            }
        }
    }

    private void c(JSONObject jSONObject) throws JSONException {
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            Object obj = -1;
            int hashCode = str.hashCode();
            if (hashCode != -488533857) {
                if (hashCode == 1411010355 && str.equals("crashEvent")) {
                    obj = 1;
                }
            } else if (str.equals("catchEvent")) {
                obj = null;
            }
            switch (obj) {
                case null:
                    this.j = jSONObject.getJSONObject(str);
                    break;
                case 1:
                    this.i = jSONObject.getJSONObject(str);
                    break;
                default:
                    break;
            }
        }
    }

    public final void a(JSONObject jSONObject) throws JSONException {
        super.a(jSONObject);
        this.a = jSONObject.getString("url");
        this.b = jSONObject.getLong("processingInterval");
        this.c = jSONObject.getInt("maxRetryCount");
        this.d = jSONObject.getInt("maxEventsToPersist");
        this.e = jSONObject.getLong("eventTTL");
        this.f = jSONObject.getLong("txLatency");
        this.g = jSONObject.getBoolean("crashEnabled");
        this.h = jSONObject.getBoolean("catchEnabled");
        b(jSONObject.getJSONObject("networkType"));
        c(jSONObject.getJSONObject("telemetry"));
    }

    public final JSONObject b() throws JSONException {
        JSONObject b = super.b();
        JSONObject jSONObject = new JSONObject();
        b.put("url", this.a);
        b.put("processingInterval", this.b);
        b.put("maxRetryCount", this.c);
        b.put("maxEventsToPersist", this.d);
        b.put("eventTTL", this.e);
        b.put("txLatency", this.f);
        b.put("crashEnabled", this.g);
        b.put("catchEnabled", this.h);
        jSONObject = new JSONObject();
        a aVar = this.l;
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("retryInterval", aVar.a);
        jSONObject2.put("maxBatchSize", aVar.b);
        jSONObject.put(e.ad, jSONObject2);
        aVar = this.k;
        jSONObject2 = new JSONObject();
        jSONObject2.put("retryInterval", aVar.a);
        jSONObject2.put("maxBatchSize", aVar.b);
        jSONObject.put("others", jSONObject2);
        b.put("networkType", jSONObject);
        jSONObject = new JSONObject();
        jSONObject.put("crashEvent", this.i);
        jSONObject.put("catchEvent", this.j);
        b.put("telemetry", jSONObject);
        return b;
    }

    public final com.inmobi.commons.core.configs.a d() {
        return new b();
    }
}
