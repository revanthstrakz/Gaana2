package com.facebook.ads.internal.j;

import com.facebook.ads.internal.protocol.AdPlacementType;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public class d {
    private static final String c = "d";
    private static final AdPlacementType d = AdPlacementType.UNKNOWN;
    public int a = -1;
    public int b = -1;
    private final long e = System.currentTimeMillis();
    private AdPlacementType f = d;
    private int g = 1;
    private int h = 0;
    private int i = 0;
    private int j = 20;
    private int k = 0;
    private int l = 1000;
    private int m = 10000;
    private int n = 200;
    private int o = 3600;
    private boolean p = false;
    private List<b> q = null;

    private d(java.util.Map<java.lang.String, java.lang.String> r11) {
        /*
        r10 = this;
        r10.<init>();
        r0 = -1;
        r10.a = r0;
        r10.b = r0;
        r1 = d;
        r10.f = r1;
        r1 = 1;
        r10.g = r1;
        r2 = 0;
        r10.h = r2;
        r10.i = r2;
        r3 = 20;
        r10.j = r3;
        r10.k = r2;
        r3 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r10.l = r3;
        r3 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r10.m = r3;
        r3 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        r10.n = r3;
        r4 = 3600; // 0xe10 float:5.045E-42 double:1.7786E-320;
        r10.o = r4;
        r10.p = r2;
        r4 = 0;
        r10.q = r4;
        r4 = java.lang.System.currentTimeMillis();
        r10.e = r4;
        r11 = r11.entrySet();
        r11 = r11.iterator();
    L_0x003d:
        r4 = r11.hasNext();
        if (r4 == 0) goto L_0x0237;
    L_0x0043:
        r4 = r11.next();
        r4 = (java.util.Map.Entry) r4;
        r5 = r4.getKey();
        r5 = (java.lang.String) r5;
        r6 = r5.hashCode();
        switch(r6) {
            case -1899431321: goto L_0x00e4;
            case -1561601017: goto L_0x00da;
            case -856794442: goto L_0x00cf;
            case -726276175: goto L_0x00c4;
            case -634541425: goto L_0x00ba;
            case -553208868: goto L_0x00b0;
            case 3575610: goto L_0x00a6;
            case 700812481: goto L_0x009c;
            case 858630459: goto L_0x0092;
            case 986744879: goto L_0x0086;
            case 1085444827: goto L_0x007b;
            case 1183549815: goto L_0x006f;
            case 1503616961: goto L_0x0063;
            case 2002133996: goto L_0x0058;
            default: goto L_0x0056;
        };
    L_0x0056:
        goto L_0x00ef;
    L_0x0058:
        r6 = "placement_width";
        r5 = r5.equals(r6);
        if (r5 == 0) goto L_0x00ef;
    L_0x0060:
        r5 = 7;
        goto L_0x00f0;
    L_0x0063:
        r6 = "placement_height";
        r5 = r5.equals(r6);
        if (r5 == 0) goto L_0x00ef;
    L_0x006b:
        r5 = 8;
        goto L_0x00f0;
    L_0x006f:
        r6 = "viewability_check_initial_delay";
        r5 = r5.equals(r6);
        if (r5 == 0) goto L_0x00ef;
    L_0x0077:
        r5 = 9;
        goto L_0x00f0;
    L_0x007b:
        r6 = "refresh";
        r5 = r5.equals(r6);
        if (r5 == 0) goto L_0x00ef;
    L_0x0083:
        r5 = 3;
        goto L_0x00f0;
    L_0x0086:
        r6 = "video_time_polling_interval";
        r5 = r5.equals(r6);
        if (r5 == 0) goto L_0x00ef;
    L_0x008e:
        r5 = 13;
        goto L_0x00f0;
    L_0x0092:
        r6 = "viewability_check_ticker";
        r5 = r5.equals(r6);
        if (r5 == 0) goto L_0x00ef;
    L_0x009a:
        r5 = 2;
        goto L_0x00f0;
    L_0x009c:
        r6 = "min_viewability_percentage";
        r5 = r5.equals(r6);
        if (r5 == 0) goto L_0x00ef;
    L_0x00a4:
        r5 = r1;
        goto L_0x00f0;
    L_0x00a6:
        r6 = "type";
        r5 = r5.equals(r6);
        if (r5 == 0) goto L_0x00ef;
    L_0x00ae:
        r5 = r2;
        goto L_0x00f0;
    L_0x00b0:
        r6 = "cacheable";
        r5 = r5.equals(r6);
        if (r5 == 0) goto L_0x00ef;
    L_0x00b8:
        r5 = 6;
        goto L_0x00f0;
    L_0x00ba:
        r6 = "invalidation_duration_in_seconds";
        r5 = r5.equals(r6);
        if (r5 == 0) goto L_0x00ef;
    L_0x00c2:
        r5 = 5;
        goto L_0x00f0;
    L_0x00c4:
        r6 = "request_timeout";
        r5 = r5.equals(r6);
        if (r5 == 0) goto L_0x00ef;
    L_0x00cc:
        r5 = 11;
        goto L_0x00f0;
    L_0x00cf:
        r6 = "viewability_check_interval";
        r5 = r5.equals(r6);
        if (r5 == 0) goto L_0x00ef;
    L_0x00d7:
        r5 = 10;
        goto L_0x00f0;
    L_0x00da:
        r6 = "refresh_threshold";
        r5 = r5.equals(r6);
        if (r5 == 0) goto L_0x00ef;
    L_0x00e2:
        r5 = 4;
        goto L_0x00f0;
    L_0x00e4:
        r6 = "conv_tracking_data";
        r5 = r5.equals(r6);
        if (r5 == 0) goto L_0x00ef;
    L_0x00ec:
        r5 = 12;
        goto L_0x00f0;
    L_0x00ef:
        r5 = r0;
    L_0x00f0:
        switch(r5) {
            case 0: goto L_0x0229;
            case 1: goto L_0x021b;
            case 2: goto L_0x020d;
            case 3: goto L_0x01ff;
            case 4: goto L_0x01f1;
            case 5: goto L_0x01e3;
            case 6: goto L_0x01d1;
            case 7: goto L_0x01c3;
            case 8: goto L_0x01b5;
            case 9: goto L_0x01a7;
            case 10: goto L_0x0199;
            case 11: goto L_0x018b;
            case 12: goto L_0x0107;
            case 13: goto L_0x00f5;
            default: goto L_0x00f3;
        };
    L_0x00f3:
        goto L_0x003d;
    L_0x00f5:
        r4 = r4.getValue();	 Catch:{ NumberFormatException -> 0x0103 }
        r4 = (java.lang.String) r4;	 Catch:{ NumberFormatException -> 0x0103 }
        r4 = java.lang.Integer.parseInt(r4);	 Catch:{ NumberFormatException -> 0x0103 }
        r10.n = r4;	 Catch:{ NumberFormatException -> 0x0103 }
        goto L_0x003d;
    L_0x0103:
        r10.n = r3;
        goto L_0x003d;
    L_0x0107:
        r4 = r4.getValue();
        r4 = (java.lang.String) r4;
        r4 = com.facebook.ads.internal.j.b.a(r4);
        r10.q = r4;
        r4 = android.webkit.CookieManager.getInstance();	 Catch:{ Exception -> 0x0181 }
        r5 = r4.acceptCookie();	 Catch:{ Exception -> 0x0181 }
        r4.setAcceptCookie(r1);	 Catch:{ Exception -> 0x0181 }
        r6 = r10.q;	 Catch:{ Exception -> 0x0181 }
        r6 = r6.iterator();	 Catch:{ Exception -> 0x0181 }
    L_0x0124:
        r7 = r6.hasNext();	 Catch:{ Exception -> 0x0181 }
        if (r7 == 0) goto L_0x016f;
    L_0x012a:
        r7 = r6.next();	 Catch:{ Exception -> 0x0181 }
        r7 = (com.facebook.ads.internal.j.b) r7;	 Catch:{ Exception -> 0x0181 }
        r8 = r7.b();	 Catch:{ Exception -> 0x0181 }
        if (r8 == 0) goto L_0x0124;
    L_0x0136:
        r8 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0181 }
        r8.<init>();	 Catch:{ Exception -> 0x0181 }
        r9 = r7.b;	 Catch:{ Exception -> 0x0181 }
        r8.append(r9);	 Catch:{ Exception -> 0x0181 }
        r9 = "=";
        r8.append(r9);	 Catch:{ Exception -> 0x0181 }
        r9 = r7.c;	 Catch:{ Exception -> 0x0181 }
        r8.append(r9);	 Catch:{ Exception -> 0x0181 }
        r9 = ";Domain=";
        r8.append(r9);	 Catch:{ Exception -> 0x0181 }
        r9 = r7.a;	 Catch:{ Exception -> 0x0181 }
        r8.append(r9);	 Catch:{ Exception -> 0x0181 }
        r9 = ";Expires=";
        r8.append(r9);	 Catch:{ Exception -> 0x0181 }
        r9 = r7.a();	 Catch:{ Exception -> 0x0181 }
        r8.append(r9);	 Catch:{ Exception -> 0x0181 }
        r9 = ";path=/";
        r8.append(r9);	 Catch:{ Exception -> 0x0181 }
        r8 = r8.toString();	 Catch:{ Exception -> 0x0181 }
        r7 = r7.a;	 Catch:{ Exception -> 0x0181 }
        r4.setCookie(r7, r8);	 Catch:{ Exception -> 0x0181 }
        goto L_0x0124;
    L_0x016f:
        r6 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x0181 }
        r7 = 21;
        if (r6 >= r7) goto L_0x017c;
    L_0x0175:
        r6 = android.webkit.CookieSyncManager.getInstance();	 Catch:{ Exception -> 0x0181 }
        r6.startSync();	 Catch:{ Exception -> 0x0181 }
    L_0x017c:
        r4.setAcceptCookie(r5);	 Catch:{ Exception -> 0x0181 }
        goto L_0x003d;
    L_0x0181:
        r4 = move-exception;
        r5 = c;
        r6 = "Failed to set cookie.";
        android.util.Log.w(r5, r6, r4);
        goto L_0x003d;
    L_0x018b:
        r4 = r4.getValue();
        r4 = (java.lang.String) r4;
        r4 = java.lang.Integer.parseInt(r4);
        r10.m = r4;
        goto L_0x003d;
    L_0x0199:
        r4 = r4.getValue();
        r4 = (java.lang.String) r4;
        r4 = java.lang.Integer.parseInt(r4);
        r10.l = r4;
        goto L_0x003d;
    L_0x01a7:
        r4 = r4.getValue();
        r4 = (java.lang.String) r4;
        r4 = java.lang.Integer.parseInt(r4);
        r10.k = r4;
        goto L_0x003d;
    L_0x01b5:
        r4 = r4.getValue();
        r4 = (java.lang.String) r4;
        r4 = java.lang.Integer.parseInt(r4);
        r10.b = r4;
        goto L_0x003d;
    L_0x01c3:
        r4 = r4.getValue();
        r4 = (java.lang.String) r4;
        r4 = java.lang.Integer.parseInt(r4);
        r10.a = r4;
        goto L_0x003d;
    L_0x01d1:
        r4 = r4.getValue();
        r4 = (java.lang.String) r4;
        r4 = java.lang.Boolean.valueOf(r4);
        r4 = r4.booleanValue();
        r10.p = r4;
        goto L_0x003d;
    L_0x01e3:
        r4 = r4.getValue();
        r4 = (java.lang.String) r4;
        r4 = java.lang.Integer.parseInt(r4);
        r10.o = r4;
        goto L_0x003d;
    L_0x01f1:
        r4 = r4.getValue();
        r4 = (java.lang.String) r4;
        r4 = java.lang.Integer.parseInt(r4);
        r10.j = r4;
        goto L_0x003d;
    L_0x01ff:
        r4 = r4.getValue();
        r4 = (java.lang.String) r4;
        r4 = java.lang.Integer.parseInt(r4);
        r10.i = r4;
        goto L_0x003d;
    L_0x020d:
        r4 = r4.getValue();
        r4 = (java.lang.String) r4;
        r4 = java.lang.Integer.parseInt(r4);
        r10.h = r4;
        goto L_0x003d;
    L_0x021b:
        r4 = r4.getValue();
        r4 = (java.lang.String) r4;
        r4 = java.lang.Integer.parseInt(r4);
        r10.g = r4;
        goto L_0x003d;
    L_0x0229:
        r4 = r4.getValue();
        r4 = (java.lang.String) r4;
        r4 = com.facebook.ads.internal.protocol.AdPlacementType.fromString(r4);
        r10.f = r4;
        goto L_0x003d;
    L_0x0237:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.j.d.<init>(java.util.Map):void");
    }

    public static d a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        Iterator keys = jSONObject.keys();
        HashMap hashMap = new HashMap();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            hashMap.put(str, String.valueOf(jSONObject.opt(str)));
        }
        return new d(hashMap);
    }

    public long a() {
        return this.e;
    }

    public AdPlacementType b() {
        return this.f;
    }

    public long c() {
        return (long) (this.i * 1000);
    }

    public long d() {
        return (long) (this.j * 1000);
    }

    public boolean e() {
        return this.p;
    }

    public int f() {
        return this.g;
    }

    public int g() {
        return this.h;
    }

    public int h() {
        return this.k;
    }

    public int i() {
        return this.l;
    }

    public int j() {
        return this.m;
    }

    public int k() {
        return this.n;
    }

    public int l() {
        return this.o * 1000;
    }
}
