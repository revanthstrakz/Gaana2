package com.moat.analytics.mobile.inm;

import android.util.Log;

class m extends Exception {
    private static final Long a = Long.valueOf(60000);
    private static Long b;
    private static Exception c;

    m(String str) {
        super(str);
    }

    static String a(String str, Exception exception) {
        if (exception instanceof m) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(" failed: ");
            stringBuilder.append(exception.getMessage());
            return stringBuilder.toString();
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(str);
        stringBuilder2.append(" failed unexpectedly");
        return stringBuilder2.toString();
    }

    static void a() {
        if (c != null) {
            b(c);
            c = null;
        }
    }

    static void a(Exception exception) {
        if (w.a().b) {
            Log.e("MoatException", Log.getStackTraceString(exception));
        } else {
            b(exception);
        }
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:22:0x00b1 */
    /* JADX WARNING: Failed to process nested try/catch */
    private static void b(java.lang.Exception r12) {
        /*
        r0 = com.moat.analytics.mobile.inm.w.a();	 Catch:{ Exception -> 0x019a }
        r0 = r0.a;	 Catch:{ Exception -> 0x019a }
        r1 = com.moat.analytics.mobile.inm.w.d.ON;	 Catch:{ Exception -> 0x019a }
        if (r0 != r1) goto L_0x0198;
    L_0x000a:
        r0 = com.moat.analytics.mobile.inm.w.a();	 Catch:{ Exception -> 0x019a }
        r0 = r0.e;	 Catch:{ Exception -> 0x019a }
        if (r0 != 0) goto L_0x0013;
    L_0x0012:
        return;
    L_0x0013:
        r1 = 100;
        if (r0 >= r1) goto L_0x0024;
    L_0x0017:
        r1 = (double) r0;	 Catch:{ Exception -> 0x019a }
        r3 = 4636737291354636288; // 0x4059000000000000 float:0.0 double:100.0;
        r1 = r1 / r3;
        r3 = java.lang.Math.random();	 Catch:{ Exception -> 0x019a }
        r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1));
        if (r5 >= 0) goto L_0x0024;
    L_0x0023:
        return;
    L_0x0024:
        r1 = "";
        r2 = "";
        r3 = "";
        r4 = "";
        r5 = "https://px.moatads.com/pixel.gif?e=0&i=MOATSDK1&ac=1";
        r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x019a }
        r6.<init>(r5);	 Catch:{ Exception -> 0x019a }
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x019a }
        r7 = "&zt=";
        r5.<init>(r7);	 Catch:{ Exception -> 0x019a }
        r7 = r12 instanceof com.moat.analytics.mobile.inm.m;	 Catch:{ Exception -> 0x019a }
        r8 = 1;
        r9 = 0;
        if (r7 == 0) goto L_0x0042;
    L_0x0040:
        r7 = r8;
        goto L_0x0043;
    L_0x0042:
        r7 = r9;
    L_0x0043:
        r5.append(r7);	 Catch:{ Exception -> 0x019a }
        r5 = r5.toString();	 Catch:{ Exception -> 0x019a }
        r6.append(r5);	 Catch:{ Exception -> 0x019a }
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x019a }
        r7 = "&zr=";
        r5.<init>(r7);	 Catch:{ Exception -> 0x019a }
        r5.append(r0);	 Catch:{ Exception -> 0x019a }
        r0 = r5.toString();	 Catch:{ Exception -> 0x019a }
        r6.append(r0);	 Catch:{ Exception -> 0x019a }
        r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00b1 }
        r5 = "&zm=";
        r0.<init>(r5);	 Catch:{ Exception -> 0x00b1 }
        r5 = r12.getMessage();	 Catch:{ Exception -> 0x00b1 }
        if (r5 != 0) goto L_0x006e;
    L_0x006b:
        r5 = "null";
        goto L_0x0082;
    L_0x006e:
        r5 = r12.getMessage();	 Catch:{ Exception -> 0x00b1 }
        r7 = "UTF-8";
        r5 = r5.getBytes(r7);	 Catch:{ Exception -> 0x00b1 }
        r5 = android.util.Base64.encodeToString(r5, r9);	 Catch:{ Exception -> 0x00b1 }
        r7 = "UTF-8";
        r5 = java.net.URLEncoder.encode(r5, r7);	 Catch:{ Exception -> 0x00b1 }
    L_0x0082:
        r0.append(r5);	 Catch:{ Exception -> 0x00b1 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x00b1 }
        r6.append(r0);	 Catch:{ Exception -> 0x00b1 }
        r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00b1 }
        r5 = "&k=";
        r0.<init>(r5);	 Catch:{ Exception -> 0x00b1 }
        r12 = android.util.Log.getStackTraceString(r12);	 Catch:{ Exception -> 0x00b1 }
        r5 = "UTF-8";
        r12 = r12.getBytes(r5);	 Catch:{ Exception -> 0x00b1 }
        r12 = android.util.Base64.encodeToString(r12, r9);	 Catch:{ Exception -> 0x00b1 }
        r5 = "UTF-8";
        r12 = java.net.URLEncoder.encode(r12, r5);	 Catch:{ Exception -> 0x00b1 }
        r0.append(r12);	 Catch:{ Exception -> 0x00b1 }
        r12 = r0.toString();	 Catch:{ Exception -> 0x00b1 }
        r6.append(r12);	 Catch:{ Exception -> 0x00b1 }
    L_0x00b1:
        r12 = "INM";
        r0 = "&zMoatMMAKv=c334ae83accfebb8da23104450c896463c9cfab7";
        r6.append(r0);	 Catch:{ Exception -> 0x00e1 }
        r0 = "2.5.0";
        r1 = com.moat.analytics.mobile.inm.s.d();	 Catch:{ Exception -> 0x00de }
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00de }
        r5 = "&zMoatMMAKan=";
        r3.<init>(r5);	 Catch:{ Exception -> 0x00de }
        r5 = r1.a();	 Catch:{ Exception -> 0x00de }
        r3.append(r5);	 Catch:{ Exception -> 0x00de }
        r3 = r3.toString();	 Catch:{ Exception -> 0x00de }
        r6.append(r3);	 Catch:{ Exception -> 0x00de }
        r1 = r1.b();	 Catch:{ Exception -> 0x00de }
        r2 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x00e3 }
        r2 = java.lang.Integer.toString(r2);	 Catch:{ Exception -> 0x00e3 }
        goto L_0x00e4;
    L_0x00de:
        r1 = r2;
        goto L_0x00e3;
    L_0x00e0:
        r12 = r1;
    L_0x00e1:
        r1 = r2;
        r0 = r3;
    L_0x00e3:
        r2 = r4;
    L_0x00e4:
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x019a }
        r4 = "&d=Android:";
        r3.<init>(r4);	 Catch:{ Exception -> 0x019a }
        r3.append(r12);	 Catch:{ Exception -> 0x019a }
        r12 = ":";
        r3.append(r12);	 Catch:{ Exception -> 0x019a }
        r3.append(r1);	 Catch:{ Exception -> 0x019a }
        r12 = ":-";
        r3.append(r12);	 Catch:{ Exception -> 0x019a }
        r12 = r3.toString();	 Catch:{ Exception -> 0x019a }
        r6.append(r12);	 Catch:{ Exception -> 0x019a }
        r12 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x019a }
        r1 = "&bo=";
        r12.<init>(r1);	 Catch:{ Exception -> 0x019a }
        r12.append(r0);	 Catch:{ Exception -> 0x019a }
        r12 = r12.toString();	 Catch:{ Exception -> 0x019a }
        r6.append(r12);	 Catch:{ Exception -> 0x019a }
        r12 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x019a }
        r0 = "&bd=";
        r12.<init>(r0);	 Catch:{ Exception -> 0x019a }
        r12.append(r2);	 Catch:{ Exception -> 0x019a }
        r12 = r12.toString();	 Catch:{ Exception -> 0x019a }
        r6.append(r12);	 Catch:{ Exception -> 0x019a }
        r0 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x019a }
        r12 = java.lang.Long.valueOf(r0);	 Catch:{ Exception -> 0x019a }
        r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x019a }
        r1 = "&t=";
        r0.<init>(r1);	 Catch:{ Exception -> 0x019a }
        r0.append(r12);	 Catch:{ Exception -> 0x019a }
        r0 = r0.toString();	 Catch:{ Exception -> 0x019a }
        r6.append(r0);	 Catch:{ Exception -> 0x019a }
        r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x019a }
        r1 = "&de=";
        r0.<init>(r1);	 Catch:{ Exception -> 0x019a }
        r1 = java.util.Locale.ROOT;	 Catch:{ Exception -> 0x019a }
        r2 = "%.0f";
        r3 = new java.lang.Object[r8];	 Catch:{ Exception -> 0x019a }
        r4 = java.lang.Math.random();	 Catch:{ Exception -> 0x019a }
        r7 = 4621819117588971520; // 0x4024000000000000 float:0.0 double:10.0;
        r10 = 4622945017495814144; // 0x4028000000000000 float:0.0 double:12.0;
        r7 = java.lang.Math.pow(r7, r10);	 Catch:{ Exception -> 0x019a }
        r4 = r4 * r7;
        r4 = java.lang.Math.floor(r4);	 Catch:{ Exception -> 0x019a }
        r4 = java.lang.Double.valueOf(r4);	 Catch:{ Exception -> 0x019a }
        r3[r9] = r4;	 Catch:{ Exception -> 0x019a }
        r1 = java.lang.String.format(r1, r2, r3);	 Catch:{ Exception -> 0x019a }
        r0.append(r1);	 Catch:{ Exception -> 0x019a }
        r0 = r0.toString();	 Catch:{ Exception -> 0x019a }
        r6.append(r0);	 Catch:{ Exception -> 0x019a }
        r0 = "&cs=0";
        r6.append(r0);	 Catch:{ Exception -> 0x019a }
        r0 = b;	 Catch:{ Exception -> 0x019a }
        if (r0 == 0) goto L_0x018e;
    L_0x0178:
        r0 = r12.longValue();	 Catch:{ Exception -> 0x019a }
        r2 = b;	 Catch:{ Exception -> 0x019a }
        r2 = r2.longValue();	 Catch:{ Exception -> 0x019a }
        r4 = r0 - r2;
        r0 = a;	 Catch:{ Exception -> 0x019a }
        r0 = r0.longValue();	 Catch:{ Exception -> 0x019a }
        r2 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1));
        if (r2 <= 0) goto L_0x0197;
    L_0x018e:
        r0 = r6.toString();	 Catch:{ Exception -> 0x019a }
        com.moat.analytics.mobile.inm.q.b(r0);	 Catch:{ Exception -> 0x019a }
        b = r12;	 Catch:{ Exception -> 0x019a }
    L_0x0197:
        return;
    L_0x0198:
        c = r12;	 Catch:{ Exception -> 0x019a }
    L_0x019a:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moat.analytics.mobile.inm.m.b(java.lang.Exception):void");
    }
}
