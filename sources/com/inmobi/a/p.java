package com.inmobi.a;

import com.google.api.client.http.HttpStatusCodes;
import com.til.colombia.android.internal.e;
import org.json.JSONException;
import org.json.JSONObject;

public final class p extends com.inmobi.commons.core.configs.a {
    private static final String d = com.inmobi.commons.core.configs.a.class.getSimpleName();
    public b a = new b();
    a b = new a();
    JSONObject c;

    public static class a {
        boolean a = false;
        String b = "https://dock.inmobi.com/carb/v1/i";
        String c = "https://dock.inmobi.com/carb/v1/o";
        int d = 86400;
        int e = 3;
        int f = 60;
        int g = 60;
        long h = 307200;
    }

    public static class b {
        public boolean a = false;
        int b = HttpStatusCodes.STATUS_CODE_MULTIPLE_CHOICES;
        int c = 3;
        int d = 50;
        String e = "https://sdkm.w.inmobi.com/user/e.asm";
        int f = 3;
        int g = 60;
        boolean h = false;
        boolean i = false;
        public int j = 0;
        boolean k = false;
        public boolean l = false;
        public int m = 0;
        public boolean n = false;
        public boolean o = false;
        public boolean p = false;

        public final boolean a() {
            return this.h && this.a;
        }

        /* Access modifiers changed, original: final */
        public final boolean b() {
            return this.i && this.a;
        }
    }

    public final String a() {
        return "signals";
    }

    public p() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("enabled", true);
            jSONObject.put("samplingFactor", 0);
            this.c = jSONObject;
        } catch (JSONException unused) {
        }
    }

    public final void a(JSONObject jSONObject) throws JSONException {
        super.a(jSONObject);
        JSONObject jSONObject2 = jSONObject.getJSONObject("ice");
        this.a.b = jSONObject2.getInt("sampleInterval");
        this.a.d = jSONObject2.getInt("sampleHistorySize");
        this.a.c = jSONObject2.getInt("stopRequestTimeout");
        this.a.a = jSONObject2.getBoolean("enabled");
        this.a.e = jSONObject2.getString("endPoint");
        this.a.f = jSONObject2.getInt("maxRetries");
        this.a.g = jSONObject2.getInt("retryInterval");
        this.a.h = jSONObject2.getBoolean("locationEnabled");
        this.a.i = jSONObject2.getBoolean("sessionEnabled");
        JSONObject jSONObject3 = jSONObject2.getJSONObject(e.G);
        this.a.j = jSONObject3.getInt("wf");
        this.a.l = jSONObject3.getBoolean("cwe");
        this.a.k = jSONObject3.getBoolean("vwe");
        jSONObject2 = jSONObject2.getJSONObject("c");
        this.a.n = jSONObject2.getBoolean("oe");
        this.a.p = jSONObject2.getBoolean("cce");
        this.a.o = jSONObject2.getBoolean("vce");
        this.a.m = jSONObject2.getInt("cof");
        jSONObject2 = jSONObject.getJSONObject("carb");
        this.b.a = jSONObject2.getBoolean("enabled");
        this.b.b = jSONObject2.getString("getEndPoint");
        this.b.c = jSONObject2.getString("postEndPoint");
        this.b.d = jSONObject2.getInt("retrieveFrequency");
        this.b.e = jSONObject2.getInt("maxRetries");
        this.b.f = jSONObject2.getInt("retryInterval");
        this.b.g = jSONObject2.getInt("timeoutInterval");
        this.b.h = jSONObject2.getLong("maxGetResponseSize");
        this.c = jSONObject.optJSONObject("telemetry");
    }

    public final JSONObject b() throws JSONException {
        JSONObject b = super.b();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("sampleInterval", this.a.b);
        jSONObject.put("stopRequestTimeout", this.a.c);
        jSONObject.put("sampleHistorySize", this.a.d);
        jSONObject.put("enabled", this.a.a);
        jSONObject.put("endPoint", this.a.e);
        jSONObject.put("maxRetries", this.a.f);
        jSONObject.put("retryInterval", this.a.g);
        jSONObject.put("locationEnabled", this.a.h);
        jSONObject.put("sessionEnabled", this.a.i);
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("wf", this.a.j);
        jSONObject2.put("vwe", this.a.k);
        jSONObject2.put("cwe", this.a.l);
        jSONObject.put(e.G, jSONObject2);
        jSONObject2 = new JSONObject();
        jSONObject2.put("cof", this.a.m);
        jSONObject2.put("vce", this.a.o);
        jSONObject2.put("cce", this.a.p);
        jSONObject2.put("oe", this.a.n);
        jSONObject.put("c", jSONObject2);
        b.put("ice", jSONObject);
        jSONObject = new JSONObject();
        jSONObject.put("enabled", this.b.a);
        jSONObject.put("getEndPoint", this.b.b);
        jSONObject.put("postEndPoint", this.b.c);
        jSONObject.put("retrieveFrequency", this.b.d);
        jSONObject.put("maxRetries", this.b.e);
        jSONObject.put("retryInterval", this.b.f);
        jSONObject.put("timeoutInterval", this.b.g);
        jSONObject.put("maxGetResponseSize", this.b.h);
        b.put("carb", jSONObject);
        b.put("telemetry", this.c);
        return b;
    }

    /* JADX WARNING: Missing block: B:41:0x00ab, code skipped:
            return false;
     */
    public final boolean c() {
        /*
        r6 = this;
        r0 = r6.a;
        r0 = r0.b;
        r1 = 0;
        if (r0 < 0) goto L_0x00ab;
    L_0x0007:
        r0 = r6.a;
        r0 = r0.d;
        if (r0 < 0) goto L_0x00ab;
    L_0x000d:
        r0 = r6.a;
        r0 = r0.c;
        if (r0 < 0) goto L_0x00ab;
    L_0x0013:
        r0 = r6.a;
        r0 = r0.e;
        r0 = r0.trim();
        r0 = r0.length();
        if (r0 == 0) goto L_0x00ab;
    L_0x0021:
        r0 = r6.a;
        r0 = r0.f;
        if (r0 < 0) goto L_0x00ab;
    L_0x0027:
        r0 = r6.a;
        r0 = r0.g;
        if (r0 < 0) goto L_0x00ab;
    L_0x002d:
        r0 = r6.a;
        r0 = r0.j;
        if (r0 < 0) goto L_0x00ab;
    L_0x0033:
        r0 = r6.a;
        r0 = r0.m;
        if (r0 >= 0) goto L_0x003a;
    L_0x0039:
        goto L_0x00ab;
    L_0x003a:
        r0 = r6.b;
        r0 = r0.b;
        r0 = r0.trim();
        r0 = r0.length();
        if (r0 == 0) goto L_0x00aa;
    L_0x0048:
        r0 = r6.b;
        r0 = r0.c;
        r0 = r0.trim();
        r0 = r0.length();
        if (r0 == 0) goto L_0x00aa;
    L_0x0056:
        r0 = r6.b;
        r0 = r0.b;
        r2 = "http://";
        r0 = r0.startsWith(r2);
        if (r0 != 0) goto L_0x006e;
    L_0x0062:
        r0 = r6.b;
        r0 = r0.b;
        r2 = "https://";
        r0 = r0.startsWith(r2);
        if (r0 == 0) goto L_0x00aa;
    L_0x006e:
        r0 = r6.b;
        r0 = r0.c;
        r2 = "http://";
        r0 = r0.startsWith(r2);
        if (r0 != 0) goto L_0x0086;
    L_0x007a:
        r0 = r6.b;
        r0 = r0.c;
        r2 = "https://";
        r0 = r0.startsWith(r2);
        if (r0 == 0) goto L_0x00aa;
    L_0x0086:
        r0 = r6.b;
        r0 = r0.d;
        if (r0 < 0) goto L_0x00aa;
    L_0x008c:
        r0 = r6.b;
        r0 = r0.e;
        if (r0 < 0) goto L_0x00aa;
    L_0x0092:
        r0 = r6.b;
        r0 = r0.f;
        if (r0 < 0) goto L_0x00aa;
    L_0x0098:
        r0 = r6.b;
        r0 = r0.g;
        if (r0 < 0) goto L_0x00aa;
    L_0x009e:
        r0 = r6.b;
        r2 = r0.h;
        r4 = 0;
        r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r0 < 0) goto L_0x00aa;
    L_0x00a8:
        r0 = 1;
        return r0;
    L_0x00aa:
        return r1;
    L_0x00ab:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.a.p.c():boolean");
    }

    public final com.inmobi.commons.core.configs.a d() {
        return new p();
    }
}
