package com.facebook.ads.internal.d;

public class c {

    public static class a {
        public String a;
        public String b;
        public boolean c;

        public a(String str, String str2, boolean z) {
            this.a = str;
            this.b = str2;
            this.c = z;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x0078  */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x006a */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:21|22|(1:24)|25) */
    /* JADX WARNING: Missing block: B:22:?, code skipped:
            r0 = new com.facebook.ads.internal.d.c.a(null, null, false);
     */
    /* JADX WARNING: Missing block: B:23:0x006f, code skipped:
            if (r9 != null) goto L_0x0071;
     */
    /* JADX WARNING: Missing block: B:24:0x0071, code skipped:
            r9.close();
     */
    /* JADX WARNING: Missing block: B:25:0x0074, code skipped:
            return r0;
     */
    /* JADX WARNING: Missing block: B:26:0x0075, code skipped:
            r0 = th;
     */
    /* JADX WARNING: Missing block: B:28:0x0078, code skipped:
            r9.close();
     */
    public static com.facebook.ads.internal.d.c.a a(android.content.ContentResolver r9) {
        /*
        r0 = 3;
        r1 = 0;
        r2 = 0;
        r5 = new java.lang.String[r0];	 Catch:{ Exception -> 0x0069, all -> 0x0066 }
        r0 = "aid";
        r5[r1] = r0;	 Catch:{ Exception -> 0x0069, all -> 0x0066 }
        r0 = 1;
        r3 = "androidid";
        r5[r0] = r3;	 Catch:{ Exception -> 0x0069, all -> 0x0066 }
        r0 = 2;
        r3 = "limit_tracking";
        r5[r0] = r3;	 Catch:{ Exception -> 0x0069, all -> 0x0066 }
        r0 = "content://com.facebook.katana.provider.AttributionIdProvider";
        r4 = android.net.Uri.parse(r0);	 Catch:{ Exception -> 0x0069, all -> 0x0066 }
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r3 = r9;
        r9 = r3.query(r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x0069, all -> 0x0066 }
        if (r9 == 0) goto L_0x005b;
    L_0x0023:
        r0 = r9.moveToFirst();	 Catch:{ Exception -> 0x006a }
        if (r0 != 0) goto L_0x002a;
    L_0x0029:
        goto L_0x005b;
    L_0x002a:
        r0 = "aid";
        r0 = r9.getColumnIndex(r0);	 Catch:{ Exception -> 0x006a }
        r0 = r9.getString(r0);	 Catch:{ Exception -> 0x006a }
        r3 = "androidid";
        r3 = r9.getColumnIndex(r3);	 Catch:{ Exception -> 0x006a }
        r3 = r9.getString(r3);	 Catch:{ Exception -> 0x006a }
        r4 = "limit_tracking";
        r4 = r9.getColumnIndex(r4);	 Catch:{ Exception -> 0x006a }
        r4 = r9.getString(r4);	 Catch:{ Exception -> 0x006a }
        r4 = java.lang.Boolean.valueOf(r4);	 Catch:{ Exception -> 0x006a }
        r5 = new com.facebook.ads.internal.d.c$a;	 Catch:{ Exception -> 0x006a }
        r4 = r4.booleanValue();	 Catch:{ Exception -> 0x006a }
        r5.<init>(r0, r3, r4);	 Catch:{ Exception -> 0x006a }
        if (r9 == 0) goto L_0x005a;
    L_0x0057:
        r9.close();
    L_0x005a:
        return r5;
    L_0x005b:
        r0 = new com.facebook.ads.internal.d.c$a;	 Catch:{ Exception -> 0x006a }
        r0.<init>(r2, r2, r1);	 Catch:{ Exception -> 0x006a }
        if (r9 == 0) goto L_0x0065;
    L_0x0062:
        r9.close();
    L_0x0065:
        return r0;
    L_0x0066:
        r0 = move-exception;
        r9 = r2;
        goto L_0x0076;
    L_0x0069:
        r9 = r2;
    L_0x006a:
        r0 = new com.facebook.ads.internal.d.c$a;	 Catch:{ all -> 0x0075 }
        r0.<init>(r2, r2, r1);	 Catch:{ all -> 0x0075 }
        if (r9 == 0) goto L_0x0074;
    L_0x0071:
        r9.close();
    L_0x0074:
        return r0;
    L_0x0075:
        r0 = move-exception;
    L_0x0076:
        if (r9 == 0) goto L_0x007b;
    L_0x0078:
        r9.close();
    L_0x007b:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.d.c.a(android.content.ContentResolver):com.facebook.ads.internal.d.c$a");
    }
}
