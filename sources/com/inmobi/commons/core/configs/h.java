package com.inmobi.commons.core.configs;

import com.facebook.internal.ServerProtocol;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class h extends a {
    private static final Object i = new Object();
    int a = 3;
    int b = 60;
    int c = 3;
    public int d = -1;
    b e = new b();
    JSONObject f = new JSONObject();
    public boolean g = false;
    private List<a> h = new ArrayList();

    static final class a {
        String a;
        long b;
        String c;
        String d;
        String e = "https://config.inmobi.cn/config-server/v1/config/secure.cfg";

        a() {
        }
    }

    public static final class b {
        String a = "7.2.1";
        String b = "https://www.inmobi.com/products/sdk/#downloads";
    }

    public final String a() {
        return "root";
    }

    public final JSONObject b() throws JSONException {
        boolean z;
        JSONObject b = super.b();
        JSONArray jSONArray = new JSONArray();
        b.put("maxRetries", this.a);
        b.put("retryInterval", this.b);
        b.put("waitTime", this.c);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, this.e.a);
        jSONObject.put("url", this.e.b);
        b.put("latestSdkInfo", jSONObject);
        synchronized (i) {
            z = false;
            for (int i = 0; i < this.h.size(); i++) {
                a aVar = (a) this.h.get(i);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("type", aVar.a);
                jSONObject2.put("expiry", aVar.b);
                jSONObject2.put("protocol", aVar.c);
                jSONObject2.put("url", aVar.d);
                if ("root".equals(aVar.a)) {
                    jSONObject2.put("fallbackUrl", aVar.e);
                }
                jSONArray.put(jSONObject2);
            }
        }
        b.put("components", jSONArray);
        b.put("monetizationDisabled", this.g);
        JSONObject jSONObject3 = new JSONObject();
        String str = "transmitRequest";
        if (this.d == 1) {
            z = true;
        }
        jSONObject3.put(str, z);
        b.put("gdpr", jSONObject3);
        return b;
    }

    public final void a(JSONObject jSONObject) throws JSONException {
        super.a(jSONObject);
        this.a = jSONObject.getInt("maxRetries");
        this.b = jSONObject.getInt("retryInterval");
        this.c = jSONObject.getInt("waitTime");
        JSONObject jSONObject2 = jSONObject.getJSONObject("latestSdkInfo");
        this.e.a = jSONObject2.getString(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION);
        this.e.b = jSONObject2.getString("url");
        JSONArray jSONArray = jSONObject.getJSONArray("components");
        synchronized (i) {
            this.h.clear();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject3 = jSONArray.getJSONObject(i);
                a aVar = new a();
                aVar.a = jSONObject3.getString("type");
                aVar.b = jSONObject3.getLong("expiry");
                aVar.c = jSONObject3.getString("protocol");
                aVar.d = jSONObject3.getString("url");
                if ("root".equals(aVar.a)) {
                    aVar.e = jSONObject3.getString("fallbackUrl");
                }
                this.h.add(aVar);
            }
        }
        this.g = jSONObject.getBoolean("monetizationDisabled");
        this.d = jSONObject.getJSONObject("gdpr").getBoolean("transmitRequest");
    }

    /* JADX WARNING: Missing block: B:46:0x00b0, code skipped:
            return false;
     */
    /* JADX WARNING: Missing block: B:49:0x00b5, code skipped:
            if (r9.d == -1) goto L_0x00b9;
     */
    /* JADX WARNING: Missing block: B:51:0x00b8, code skipped:
            return true;
     */
    /* JADX WARNING: Missing block: B:52:0x00b9, code skipped:
            return false;
     */
    /* JADX WARNING: Missing block: B:58:0x00be, code skipped:
            return false;
     */
    public final boolean c() {
        /*
        r9 = this;
        r0 = r9.h;
        r1 = 0;
        if (r0 != 0) goto L_0x0006;
    L_0x0005:
        return r1;
    L_0x0006:
        r0 = r9.a;
        if (r0 < 0) goto L_0x00be;
    L_0x000a:
        r0 = r9.b;
        if (r0 < 0) goto L_0x00be;
    L_0x000e:
        r0 = r9.c;
        if (r0 >= 0) goto L_0x0014;
    L_0x0012:
        goto L_0x00be;
    L_0x0014:
        r0 = r9.e;
        r0 = r0.a;
        r0 = r0.trim();
        r0 = r0.length();
        if (r0 == 0) goto L_0x00bd;
    L_0x0022:
        r0 = r9.e;
        r0 = r0.b;
        r2 = "http://";
        r0 = r0.startsWith(r2);
        if (r0 != 0) goto L_0x003c;
    L_0x002e:
        r0 = r9.e;
        r0 = r0.b;
        r2 = "https://";
        r0 = r0.startsWith(r2);
        if (r0 != 0) goto L_0x003c;
    L_0x003a:
        goto L_0x00bd;
    L_0x003c:
        r0 = i;
        monitor-enter(r0);
        r2 = r1;
    L_0x0040:
        r3 = r9.h;	 Catch:{ all -> 0x00ba }
        r3 = r3.size();	 Catch:{ all -> 0x00ba }
        if (r2 >= r3) goto L_0x00b1;
    L_0x0048:
        r3 = r9.h;	 Catch:{ all -> 0x00ba }
        r3 = r3.get(r2);	 Catch:{ all -> 0x00ba }
        r3 = (com.inmobi.commons.core.configs.h.a) r3;	 Catch:{ all -> 0x00ba }
        r4 = r3.a;	 Catch:{ all -> 0x00ba }
        r4 = r4.trim();	 Catch:{ all -> 0x00ba }
        r4 = r4.length();	 Catch:{ all -> 0x00ba }
        if (r4 != 0) goto L_0x005e;
    L_0x005c:
        monitor-exit(r0);	 Catch:{ all -> 0x00ba }
        return r1;
    L_0x005e:
        r4 = r3.b;	 Catch:{ all -> 0x00ba }
        r4 = java.lang.Long.valueOf(r4);	 Catch:{ all -> 0x00ba }
        r4 = r4.longValue();	 Catch:{ all -> 0x00ba }
        r6 = 0;
        r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r8 < 0) goto L_0x00af;
    L_0x006e:
        r4 = r3.b;	 Catch:{ all -> 0x00ba }
        r4 = java.lang.Long.valueOf(r4);	 Catch:{ all -> 0x00ba }
        r4 = r4.longValue();	 Catch:{ all -> 0x00ba }
        r6 = 864000; // 0xd2f00 float:1.210722E-39 double:4.268727E-318;
        r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r8 <= 0) goto L_0x0080;
    L_0x007f:
        goto L_0x00af;
    L_0x0080:
        r4 = r3.c;	 Catch:{ all -> 0x00ba }
        r4 = r4.trim();	 Catch:{ all -> 0x00ba }
        r4 = r4.length();	 Catch:{ all -> 0x00ba }
        if (r4 != 0) goto L_0x008e;
    L_0x008c:
        monitor-exit(r0);	 Catch:{ all -> 0x00ba }
        return r1;
    L_0x008e:
        r4 = r3.d;	 Catch:{ all -> 0x00ba }
        r4 = c(r4);	 Catch:{ all -> 0x00ba }
        if (r4 == 0) goto L_0x0098;
    L_0x0096:
        monitor-exit(r0);	 Catch:{ all -> 0x00ba }
        return r1;
    L_0x0098:
        r4 = "root";
        r5 = r3.a;	 Catch:{ all -> 0x00ba }
        r4 = r4.equals(r5);	 Catch:{ all -> 0x00ba }
        if (r4 == 0) goto L_0x00ac;
    L_0x00a2:
        r3 = r3.e;	 Catch:{ all -> 0x00ba }
        r3 = c(r3);	 Catch:{ all -> 0x00ba }
        if (r3 == 0) goto L_0x00ac;
    L_0x00aa:
        monitor-exit(r0);	 Catch:{ all -> 0x00ba }
        return r1;
    L_0x00ac:
        r2 = r2 + 1;
        goto L_0x0040;
    L_0x00af:
        monitor-exit(r0);	 Catch:{ all -> 0x00ba }
        return r1;
    L_0x00b1:
        monitor-exit(r0);	 Catch:{ all -> 0x00ba }
        r0 = r9.d;
        r2 = -1;
        if (r0 == r2) goto L_0x00b9;
    L_0x00b7:
        r0 = 1;
        return r0;
    L_0x00b9:
        return r1;
    L_0x00ba:
        r1 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x00ba }
        throw r1;
    L_0x00bd:
        return r1;
    L_0x00be:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.commons.core.configs.h.c():boolean");
    }

    private static boolean c(String str) {
        return str == null || str.trim().length() == 0 || !(str.startsWith("http://") || str.startsWith("https://"));
    }

    public final a d() {
        return new h();
    }

    public final long a(String str) {
        synchronized (i) {
            for (int i = 0; i < this.h.size(); i++) {
                a aVar = (a) this.h.get(i);
                if (str.equals(aVar.a)) {
                    long j = aVar.b;
                    return j;
                }
            }
            return 86400;
        }
    }

    public final String b(String str) {
        synchronized (i) {
            for (int i = 0; i < this.h.size(); i++) {
                a aVar = (a) this.h.get(i);
                if (str.equals(aVar.a)) {
                    str = aVar.d;
                    return str;
                }
            }
            str = "";
            return str;
        }
    }

    public final String e() {
        synchronized (i) {
            String str;
            for (a aVar : this.h) {
                if ("root".equals(aVar.a)) {
                    str = aVar.e;
                    return str;
                }
            }
            str = "https://config.inmobi.cn/config-server/v1/config/secure.cfg";
            return str;
        }
    }
}
