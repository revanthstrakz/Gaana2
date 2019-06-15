package com.helpshift.support.g.a;

public class a {
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0068 A:{Catch:{ JSONException -> 0x0075 }} */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x007b A:{Catch:{ JSONException -> 0x00a9 }} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0053 */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x007b A:{Catch:{ JSONException -> 0x00a9 }} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0075 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:26:0x00a9 */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0068 A:{Catch:{ JSONException -> 0x0075 }} */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x007b A:{Catch:{ JSONException -> 0x00a9 }} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0062 */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Can't wrap try/catch for region: R(18:0|(7:4|5|6|7|33|31|1)|32|8|9|10|11|12|13|14|(1:16)|17|18|(4:20|(1:22)|23|(1:25))|26|27|28|30) */
    /* JADX WARNING: Can't wrap try/catch for region: R(18:0|(7:4|5|6|7|33|31|1)|32|8|9|10|11|12|13|14|(1:16)|17|18|(4:20|(1:22)|23|(1:25))|26|27|28|30) */
    /* JADX WARNING: Can't wrap try/catch for region: R(18:0|(7:4|5|6|7|33|31|1)|32|8|9|10|11|12|13|14|(1:16)|17|18|(4:20|(1:22)|23|(1:25))|26|27|28|30) */
    /* JADX WARNING: Can't wrap try/catch for region: R(18:0|(7:4|5|6|7|33|31|1)|32|8|9|10|11|12|13|14|(1:16)|17|18|(4:20|(1:22)|23|(1:25))|26|27|28|30) */
    public static java.util.HashMap a(com.helpshift.support.g r4, java.util.List<com.helpshift.j.d.a> r5, java.lang.String r6, java.lang.String r7, java.lang.String r8) {
        /*
        r4 = new org.json.JSONArray;
        r4.<init>();
        r5 = r5.iterator();
    L_0x0009:
        r0 = r5.hasNext();
        if (r0 == 0) goto L_0x0041;
    L_0x000f:
        r0 = r5.next();
        r0 = (com.helpshift.j.d.a) r0;
        r1 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0009 }
        r1.<init>();	 Catch:{ JSONException -> 0x0009 }
        r2 = "dt";
        r3 = r0.a;	 Catch:{ JSONException -> 0x0009 }
        r1.put(r2, r3);	 Catch:{ JSONException -> 0x0009 }
        r2 = "l";
        r3 = r0.d;	 Catch:{ JSONException -> 0x0009 }
        r1.put(r2, r3);	 Catch:{ JSONException -> 0x0009 }
        r2 = "ct";
        r3 = r0.e;	 Catch:{ JSONException -> 0x0009 }
        r1.put(r2, r3);	 Catch:{ JSONException -> 0x0009 }
        r2 = "msg";
        r3 = r0.b;	 Catch:{ JSONException -> 0x0009 }
        r1.put(r2, r3);	 Catch:{ JSONException -> 0x0009 }
        r2 = "st";
        r0 = r0.c;	 Catch:{ JSONException -> 0x0009 }
        r1.put(r2, r0);	 Catch:{ JSONException -> 0x0009 }
        r4.put(r1);	 Catch:{ JSONException -> 0x0009 }
        goto L_0x0009;
    L_0x0041:
        r5 = new org.json.JSONArray;
        r5.<init>();
        r0 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0053 }
        r0.<init>();	 Catch:{ JSONException -> 0x0053 }
        r1 = "domain";
        r0.put(r1, r7);	 Catch:{ JSONException -> 0x0053 }
        r5.put(r0);	 Catch:{ JSONException -> 0x0053 }
    L_0x0053:
        r7 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0062 }
        r7.<init>();	 Catch:{ JSONException -> 0x0062 }
        r0 = "dm";
        r1 = android.os.Build.MODEL;	 Catch:{ JSONException -> 0x0062 }
        r7.put(r0, r1);	 Catch:{ JSONException -> 0x0062 }
        r5.put(r7);	 Catch:{ JSONException -> 0x0062 }
    L_0x0062:
        r7 = android.text.TextUtils.isEmpty(r6);	 Catch:{ JSONException -> 0x0075 }
        if (r7 != 0) goto L_0x0075;
    L_0x0068:
        r7 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0075 }
        r7.<init>();	 Catch:{ JSONException -> 0x0075 }
        r0 = "profId";
        r7.put(r0, r6);	 Catch:{ JSONException -> 0x0075 }
        r5.put(r7);	 Catch:{ JSONException -> 0x0075 }
    L_0x0075:
        r6 = com.helpshift.n.a.a();	 Catch:{ JSONException -> 0x00a9 }
        if (r6 == 0) goto L_0x00a9;
    L_0x007b:
        r7 = r6.b();	 Catch:{ JSONException -> 0x00a9 }
        r6 = r6.a();	 Catch:{ JSONException -> 0x00a9 }
        r0 = android.text.TextUtils.isEmpty(r7);	 Catch:{ JSONException -> 0x00a9 }
        if (r0 != 0) goto L_0x0096;
    L_0x0089:
        r0 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x00a9 }
        r0.<init>();	 Catch:{ JSONException -> 0x00a9 }
        r1 = "did";
        r0.put(r1, r7);	 Catch:{ JSONException -> 0x00a9 }
        r5.put(r0);	 Catch:{ JSONException -> 0x00a9 }
    L_0x0096:
        r7 = android.text.TextUtils.isEmpty(r6);	 Catch:{ JSONException -> 0x00a9 }
        if (r7 != 0) goto L_0x00a9;
    L_0x009c:
        r7 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x00a9 }
        r7.<init>();	 Catch:{ JSONException -> 0x00a9 }
        r0 = "uid";
        r7.put(r0, r6);	 Catch:{ JSONException -> 0x00a9 }
        r5.put(r7);	 Catch:{ JSONException -> 0x00a9 }
    L_0x00a9:
        r6 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x00b8 }
        r6.<init>();	 Catch:{ JSONException -> 0x00b8 }
        r7 = "os";
        r0 = android.os.Build.VERSION.RELEASE;	 Catch:{ JSONException -> 0x00b8 }
        r6.put(r7, r0);	 Catch:{ JSONException -> 0x00b8 }
        r5.put(r6);	 Catch:{ JSONException -> 0x00b8 }
    L_0x00b8:
        r6 = new java.util.HashMap;
        r6.<init>();
        r7 = "id";
        r0 = java.util.UUID.randomUUID();
        r0 = r0.toString();
        r6.put(r7, r0);
        r7 = "v";
        r6.put(r7, r8);
        r7 = com.helpshift.util.o.c();
        r7 = r7.q();
        r7 = r7.a();
        r8 = "ctime";
        r0 = com.helpshift.util.i.d;
        r1 = new java.util.Date;
        r7 = java.lang.Float.valueOf(r7);
        r2 = com.helpshift.util.x.b(r7);
        r1.<init>(r2);
        r7 = r0.format(r1);
        r6.put(r8, r7);
        r7 = "src";
        r8 = "sdk.android.6.4.0";
        r6.put(r7, r8);
        r7 = "md";
        r5 = r5.toString();
        r6.put(r7, r5);
        r5 = "logs";
        r4 = r4.toString();
        r6.put(r5, r4);
        return r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.support.g.a.a.a(com.helpshift.support.g, java.util.List, java.lang.String, java.lang.String, java.lang.String):java.util.HashMap");
    }
}
