package com.helpshift.util;

import android.location.Location;

public class s {
    private static Location a;

    private static double a(double d) {
        d %= 360.0d;
        return d > 180.0d ? d - 360.0d : d <= -180.0d ? d + 360.0d : d;
    }

    public static boolean a(Location location, Location location2) {
        if (location2 == null) {
            return true;
        }
        long time = location.getTime() - location2.getTime();
        boolean z = time > 120000;
        boolean z2 = time < -120000;
        boolean z3 = time > 0;
        if (z) {
            return true;
        }
        if (z2) {
            return false;
        }
        int accuracy = (int) (location.getAccuracy() - location2.getAccuracy());
        z2 = accuracy > 0;
        boolean z4 = accuracy < 0;
        z = accuracy > 200;
        boolean a = a(location.getProvider(), location2.getProvider());
        if (z4) {
            return true;
        }
        if (z3 && !z2) {
            return true;
        }
        if (z3 && !z && a) {
            return true;
        }
        return false;
    }

    public static Location a(Location location) {
        Location a = a(location.getLatitude(), location.getLongitude());
        location.setLatitude(a.getLatitude());
        location.setLongitude(a.getLongitude());
        return location;
    }

    private static Location a(double d, double d2) {
        d = a(d);
        double d3 = -180.0d;
        Object obj = 1;
        if (d > 90.0d) {
            d = 180.0d - d;
        } else if (d < -90.0d) {
            d = -180.0d - d;
        } else {
            obj = null;
        }
        if (obj != null) {
            if (d2 <= 0.0d) {
                d3 = 180.0d;
            }
            d2 += d3;
        }
        d2 = a(d2);
        Location location = new Location("");
        location.setLatitude(d);
        location.setLongitude(d2);
        return location;
    }

    public static boolean b(Location location, Location location2) {
        boolean z = false;
        if (location == null || location2 == null) {
            if (location == null && location2 == null) {
                z = true;
            }
            return z;
        }
        if (location.distanceTo(location2) <= 10.0f) {
            z = true;
        }
        return z;
    }

    private static boolean a(String str, String str2) {
        if (str != null) {
            return str.equals(str2);
        }
        return str2 == null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0067  */
    @android.annotation.SuppressLint({"all"})
    public static android.location.Location a() {
        /*
        r0 = com.helpshift.util.o.b();
        r1 = "location";
        r0 = r0.getSystemService(r1);
        r0 = (android.location.LocationManager) r0;
        r1 = 0;
        r2 = r0.getAllProviders();	 Catch:{ Exception -> 0x0037 }
        r3 = "network";
        r2 = r2.contains(r3);	 Catch:{ Exception -> 0x0037 }
        if (r2 == 0) goto L_0x0020;
    L_0x0019:
        r2 = "network";
        r2 = r0.getLastKnownLocation(r2);	 Catch:{ Exception -> 0x0037 }
        goto L_0x0021;
    L_0x0020:
        r2 = r1;
    L_0x0021:
        r3 = r0.getAllProviders();	 Catch:{ Exception -> 0x0035 }
        r4 = "gps";
        r3 = r3.contains(r4);	 Catch:{ Exception -> 0x0035 }
        if (r3 == 0) goto L_0x004f;
    L_0x002d:
        r3 = "gps";
        r0 = r0.getLastKnownLocation(r3);	 Catch:{ Exception -> 0x0035 }
        r1 = r0;
        goto L_0x004f;
    L_0x0035:
        r0 = move-exception;
        goto L_0x0039;
    L_0x0037:
        r0 = move-exception;
        r2 = r1;
    L_0x0039:
        r3 = "LocationUtil";
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "getUpdatedCurrentLocation exception: ";
        r4.append(r5);
        r4.append(r0);
        r0 = r4.toString();
        com.helpshift.util.l.b(r3, r0);
    L_0x004f:
        if (r1 == 0) goto L_0x0053;
    L_0x0051:
        r0 = r1;
        goto L_0x0054;
    L_0x0053:
        r0 = r2;
    L_0x0054:
        if (r1 == 0) goto L_0x005f;
    L_0x0056:
        if (r2 == 0) goto L_0x005f;
    L_0x0058:
        r1 = a(r2, r1);
        if (r1 == 0) goto L_0x005f;
    L_0x005e:
        r0 = r2;
    L_0x005f:
        r1 = a;
        r1 = a(r0, r1);
        if (r1 == 0) goto L_0x0069;
    L_0x0067:
        a = r0;
    L_0x0069:
        r0 = a;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.util.s.a():android.location.Location");
    }
}
