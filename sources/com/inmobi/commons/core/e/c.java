package com.inmobi.commons.core.e;

import java.util.HashMap;
import java.util.Map;

public final class c {
    int a = 0;
    boolean b = false;
    private String c = "telemetry";
    private Map<String, a> d = new HashMap();

    static final class a {
        String a;
        int b;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0051 A:{Catch:{ JSONException -> 0x009f }} */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x004a A:{Catch:{ JSONException -> 0x009f }} */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0064 A:{Catch:{ JSONException -> 0x009f }} */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0034 A:{Catch:{ JSONException -> 0x009f }} */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x004a A:{Catch:{ JSONException -> 0x009f }} */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0051 A:{Catch:{ JSONException -> 0x009f }} */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0064 A:{Catch:{ JSONException -> 0x009f }} */
    public c(java.lang.String r4, org.json.JSONObject r5, com.inmobi.commons.core.e.c r6) {
        /*
        r3 = this;
        r3.<init>();
        r0 = 0;
        r3.a = r0;
        r1 = "telemetry";
        r3.c = r1;
        r3.b = r0;
        r1 = new java.util.HashMap;
        r1.<init>();
        r3.d = r1;
        if (r5 != 0) goto L_0x0019;
    L_0x0015:
        r3.a(r4);
        return;
    L_0x0019:
        if (r4 == 0) goto L_0x0028;
    L_0x001b:
        r1 = r4.trim();	 Catch:{ JSONException -> 0x009f }
        r1 = r1.length();	 Catch:{ JSONException -> 0x009f }
        if (r1 != 0) goto L_0x0026;
    L_0x0025:
        goto L_0x0028;
    L_0x0026:
        r1 = r4;
        goto L_0x002a;
    L_0x0028:
        r1 = r6.c;	 Catch:{ JSONException -> 0x009f }
    L_0x002a:
        r3.c = r1;	 Catch:{ JSONException -> 0x009f }
        r1 = "enabled";
        r1 = r5.has(r1);	 Catch:{ JSONException -> 0x009f }
        if (r1 == 0) goto L_0x003f;
    L_0x0034:
        r1 = "enabled";
        r1 = r5.getBoolean(r1);	 Catch:{ JSONException -> 0x009f }
        if (r1 == 0) goto L_0x003d;
    L_0x003c:
        goto L_0x003f;
    L_0x003d:
        r1 = r0;
        goto L_0x0040;
    L_0x003f:
        r1 = 1;
    L_0x0040:
        r3.b = r1;	 Catch:{ JSONException -> 0x009f }
        r1 = "samplingFactor";
        r1 = r5.has(r1);	 Catch:{ JSONException -> 0x009f }
        if (r1 == 0) goto L_0x0051;
    L_0x004a:
        r6 = "samplingFactor";
        r6 = r5.getInt(r6);	 Catch:{ JSONException -> 0x009f }
        goto L_0x0053;
    L_0x0051:
        r6 = r6.a;	 Catch:{ JSONException -> 0x009f }
    L_0x0053:
        r3.a = r6;	 Catch:{ JSONException -> 0x009f }
        r6 = new java.util.HashMap;	 Catch:{ JSONException -> 0x009f }
        r6.<init>();	 Catch:{ JSONException -> 0x009f }
        r3.d = r6;	 Catch:{ JSONException -> 0x009f }
        r6 = "events";
        r6 = r5.has(r6);	 Catch:{ JSONException -> 0x009f }
        if (r6 == 0) goto L_0x009e;
    L_0x0064:
        r6 = "events";
        r5 = r5.getJSONArray(r6);	 Catch:{ JSONException -> 0x009f }
    L_0x006a:
        r6 = r5.length();	 Catch:{ JSONException -> 0x009f }
        if (r0 >= r6) goto L_0x009e;
    L_0x0070:
        r6 = new com.inmobi.commons.core.e.c$a;	 Catch:{ JSONException -> 0x009f }
        r6.<init>();	 Catch:{ JSONException -> 0x009f }
        r1 = r5.getJSONObject(r0);	 Catch:{ JSONException -> 0x009f }
        r2 = "type";
        r2 = r1.getString(r2);	 Catch:{ JSONException -> 0x009f }
        r6.a = r2;	 Catch:{ JSONException -> 0x009f }
        r2 = "samplingFactor";
        r2 = r1.has(r2);	 Catch:{ JSONException -> 0x009f }
        if (r2 == 0) goto L_0x0090;
    L_0x0089:
        r2 = "samplingFactor";
        r1 = r1.getInt(r2);	 Catch:{ JSONException -> 0x009f }
        goto L_0x0092;
    L_0x0090:
        r1 = r3.a;	 Catch:{ JSONException -> 0x009f }
    L_0x0092:
        r6.b = r1;	 Catch:{ JSONException -> 0x009f }
        r1 = r3.d;	 Catch:{ JSONException -> 0x009f }
        r2 = r6.a;	 Catch:{ JSONException -> 0x009f }
        r1.put(r2, r6);	 Catch:{ JSONException -> 0x009f }
        r0 = r0 + 1;
        goto L_0x006a;
    L_0x009e:
        return;
    L_0x009f:
        r3.a(r4);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.commons.core.e.c.<init>(java.lang.String, org.json.JSONObject, com.inmobi.commons.core.e.c):void");
    }

    private void a(String str) {
        this.b = true;
        this.c = str;
    }
}
