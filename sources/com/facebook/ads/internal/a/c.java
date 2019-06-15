package com.facebook.ads.internal.a;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import java.util.Map;

public class c {
    private static final String a = "c";

    @Nullable
    public static b a(Context context, com.facebook.ads.internal.o.c cVar, String str, Uri uri, Map<String, String> map) {
        return a(context, cVar, str, uri, map, false);
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x008b  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00a7  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0093  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x008b  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00a7  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0093  */
    @android.support.annotation.Nullable
    public static com.facebook.ads.internal.a.b a(android.content.Context r11, com.facebook.ads.internal.o.c r12, java.lang.String r13, android.net.Uri r14, java.util.Map<java.lang.String, java.lang.String> r15, boolean r16) {
        /*
        r3 = r12;
        r5 = r14;
        r1 = 0;
        if (r5 != 0) goto L_0x0006;
    L_0x0005:
        return r1;
    L_0x0006:
        r2 = r5.getAuthority();
        r4 = "video_url";
        r4 = r5.getQueryParameter(r4);
        r6 = "data";
        r6 = r5.getQueryParameter(r6);
        r6 = android.text.TextUtils.isEmpty(r6);
        if (r6 != 0) goto L_0x004d;
    L_0x001c:
        r6 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0042 }
        r7 = "data";
        r7 = r5.getQueryParameter(r7);	 Catch:{ JSONException -> 0x0042 }
        r6.<init>(r7);	 Catch:{ JSONException -> 0x0042 }
        r7 = r6.keys();	 Catch:{ JSONException -> 0x0042 }
    L_0x002b:
        r8 = r7.hasNext();	 Catch:{ JSONException -> 0x0042 }
        if (r8 == 0) goto L_0x004d;
    L_0x0031:
        r8 = r7.next();	 Catch:{ JSONException -> 0x0042 }
        r8 = (java.lang.String) r8;	 Catch:{ JSONException -> 0x0042 }
        r9 = r6.getString(r8);	 Catch:{ JSONException -> 0x0042 }
        r10 = r15;
        r10.put(r8, r9);	 Catch:{ JSONException -> 0x0040 }
        goto L_0x002b;
    L_0x0040:
        r0 = move-exception;
        goto L_0x0044;
    L_0x0042:
        r0 = move-exception;
        r10 = r15;
    L_0x0044:
        r6 = r0;
        r7 = a;
        r8 = "Unable to parse json data in AdActionFactory.";
        android.util.Log.w(r7, r8, r6);
        goto L_0x004e;
    L_0x004d:
        r10 = r15;
    L_0x004e:
        r6 = com.facebook.ads.internal.s.a.a.a();
        r7 = com.facebook.ads.internal.a.l.a(r3, r6);
        r6 = -1;
        r8 = r2.hashCode();
        r9 = -1458789996; // 0xffffffffa90ca194 float:-3.122639E-14 double:NaN;
        if (r8 == r9) goto L_0x007f;
    L_0x0060:
        r9 = 109770977; // 0x68af8e1 float:5.2275525E-35 double:5.42340686E-316;
        if (r8 == r9) goto L_0x0075;
    L_0x0065:
        r9 = 1546100943; // 0x5c27a0cf float:1.88732528E17 double:7.63875361E-315;
        if (r8 == r9) goto L_0x006b;
    L_0x006a:
        goto L_0x0088;
    L_0x006b:
        r8 = "open_link";
        r2 = r2.equals(r8);
        if (r2 == 0) goto L_0x0088;
    L_0x0073:
        r6 = 1;
        goto L_0x0088;
    L_0x0075:
        r8 = "store";
        r2 = r2.equals(r8);
        if (r2 == 0) goto L_0x0088;
    L_0x007d:
        r6 = 0;
        goto L_0x0088;
    L_0x007f:
        r8 = "passthrough";
        r2 = r2.equals(r8);
        if (r2 == 0) goto L_0x0088;
    L_0x0087:
        r6 = 2;
    L_0x0088:
        switch(r6) {
            case 0: goto L_0x00a7;
            case 1: goto L_0x009d;
            case 2: goto L_0x0093;
            default: goto L_0x008b;
        };
    L_0x008b:
        r1 = new com.facebook.ads.internal.a.k;
        r2 = r11;
        r4 = r13;
        r1.<init>(r2, r3, r4, r5);
        return r1;
    L_0x0093:
        r7 = new com.facebook.ads.internal.a.j;
        r1 = r7;
        r2 = r11;
        r4 = r13;
        r6 = r10;
        r1.<init>(r2, r3, r4, r5, r6);
        return r7;
    L_0x009d:
        r8 = new com.facebook.ads.internal.a.i;
        r1 = r8;
        r2 = r11;
        r4 = r13;
        r6 = r10;
        r1.<init>(r2, r3, r4, r5, r6, r7);
        return r8;
    L_0x00a7:
        if (r4 == 0) goto L_0x00aa;
    L_0x00a9:
        return r1;
    L_0x00aa:
        r9 = new com.facebook.ads.internal.a.f;
        r1 = r9;
        r2 = r11;
        r4 = r13;
        r6 = r10;
        r8 = r16;
        r1.<init>(r2, r3, r4, r5, r6, r7, r8);
        return r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.a.c.a(android.content.Context, com.facebook.ads.internal.o.c, java.lang.String, android.net.Uri, java.util.Map, boolean):com.facebook.ads.internal.a.b");
    }

    public static boolean a(String str) {
        return "store".equalsIgnoreCase(str) || "open_link".equalsIgnoreCase(str);
    }
}
