package com.appsflyer;

import android.content.Context;
import android.support.annotation.NonNull;

final class z {

    static final class a {
        static final z a = new z();
    }

    z() {
    }

    /* JADX WARNING: Missing block: B:17:0x0045, code skipped:
            if (r9 != null) goto L_0x0059;
     */
    /* JADX WARNING: Missing block: B:20:0x0056, code skipped:
            if (60000 >= (r2.getTime() - r9.getTime())) goto L_0x0059;
     */
    @android.support.annotation.Nullable
    static android.location.Location a(@android.support.annotation.NonNull android.content.Context r9) {
        /*
        r0 = 0;
        r1 = "location";
        r1 = r9.getSystemService(r1);	 Catch:{ Throwable -> 0x005c }
        r1 = (android.location.LocationManager) r1;	 Catch:{ Throwable -> 0x005c }
        r2 = "network";
        r3 = 2;
        r3 = new java.lang.String[r3];	 Catch:{ Throwable -> 0x005c }
        r4 = "android.permission.ACCESS_FINE_LOCATION";
        r5 = 0;
        r3[r5] = r4;	 Catch:{ Throwable -> 0x005c }
        r4 = "android.permission.ACCESS_COARSE_LOCATION";
        r6 = 1;
        r3[r6] = r4;	 Catch:{ Throwable -> 0x005c }
        r3 = a(r9, r3);	 Catch:{ Throwable -> 0x005c }
        if (r3 == 0) goto L_0x0023;
    L_0x001e:
        r2 = r1.getLastKnownLocation(r2);	 Catch:{ Throwable -> 0x005c }
        goto L_0x0024;
    L_0x0023:
        r2 = r0;
    L_0x0024:
        r3 = "gps";
        r4 = new java.lang.String[r6];	 Catch:{ Throwable -> 0x005c }
        r6 = "android.permission.ACCESS_FINE_LOCATION";
        r4[r5] = r6;	 Catch:{ Throwable -> 0x005c }
        r9 = a(r9, r4);	 Catch:{ Throwable -> 0x005c }
        if (r9 == 0) goto L_0x0037;
    L_0x0032:
        r9 = r1.getLastKnownLocation(r3);	 Catch:{ Throwable -> 0x005c }
        goto L_0x0038;
    L_0x0037:
        r9 = r0;
    L_0x0038:
        if (r9 != 0) goto L_0x003e;
    L_0x003a:
        if (r2 != 0) goto L_0x003e;
    L_0x003c:
        r9 = r0;
        goto L_0x0059;
    L_0x003e:
        if (r9 != 0) goto L_0x0043;
    L_0x0040:
        if (r2 == 0) goto L_0x0043;
    L_0x0042:
        goto L_0x0058;
    L_0x0043:
        if (r2 != 0) goto L_0x0047;
    L_0x0045:
        if (r9 != 0) goto L_0x0059;
    L_0x0047:
        r3 = r2.getTime();	 Catch:{ Throwable -> 0x005c }
        r5 = r9.getTime();	 Catch:{ Throwable -> 0x005c }
        r7 = r3 - r5;
        r3 = 60000; // 0xea60 float:8.4078E-41 double:2.9644E-319;
        r1 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1));
        if (r1 >= 0) goto L_0x0059;
    L_0x0058:
        r9 = r2;
    L_0x0059:
        if (r9 == 0) goto L_0x005c;
    L_0x005b:
        r0 = r9;
    L_0x005c:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.z.a(android.content.Context):android.location.Location");
    }

    private static boolean a(@NonNull Context context, @NonNull String[] strArr) {
        for (String a : strArr) {
            if (b.a(context, a)) {
                return true;
            }
        }
        return false;
    }
}
