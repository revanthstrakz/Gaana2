package com.inmobi.a.a;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.location.LocationManager;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import com.facebook.places.model.PlaceFields;
import com.inmobi.a.o;
import com.inmobi.a.p.b;
import com.inmobi.commons.a.a;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@TargetApi(17)
public class c {
    private static final String a = "c";

    private static boolean a(int i, int i2) {
        return (i & i2) == i2;
    }

    public static Map<String, String> a() {
        HashMap hashMap = new HashMap();
        Context b = a.b();
        if (b == null) {
            return hashMap;
        }
        b bVar = o.a().a.a;
        int i = (bVar.n && bVar.a) ? 1 : 0;
        if (i == 0) {
            return hashMap;
        }
        Object obj;
        StringBuilder stringBuilder;
        i = o.a().a.a.m;
        boolean a = a(i, 2);
        boolean a2 = a(i, 1);
        a aVar = new a();
        TelephonyManager telephonyManager = (TelephonyManager) b.getSystemService("phone");
        if (!a) {
            int[] a3 = a(telephonyManager.getNetworkOperator());
            aVar.a = a3[0];
            aVar.b = a3[1];
            String networkCountryIso = telephonyManager.getNetworkCountryIso();
            if (networkCountryIso != null) {
                aVar.e = networkCountryIso.toLowerCase(Locale.ENGLISH);
            }
        }
        if (!a2) {
            int[] a4 = a(telephonyManager.getSimOperator());
            aVar.c = a4[0];
            aVar.d = a4[1];
        }
        String str = "s-ho";
        Object obj2 = null;
        if (aVar.c == -1 && aVar.d == -1) {
            obj = null;
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append(aVar.c);
            stringBuilder.append("_");
            stringBuilder.append(aVar.d);
            obj = stringBuilder.toString();
        }
        hashMap.put(str, obj);
        str = "s-co";
        if (!(aVar.a == -1 && aVar.b == -1)) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(aVar.a);
            stringBuilder.append("_");
            stringBuilder.append(aVar.b);
            obj2 = stringBuilder.toString();
        }
        hashMap.put(str, obj2);
        hashMap.put("s-iso", aVar.e);
        return hashMap;
    }

    private static int[] a(String str) {
        int[] iArr = new int[]{-1, -1};
        if (str == null || str.equals("")) {
            return iArr;
        }
        try {
            int parseInt = Integer.parseInt(str.substring(0, 3));
            int parseInt2 = Integer.parseInt(str.substring(3));
            iArr[0] = parseInt;
            iArr[1] = parseInt2;
        } catch (IndexOutOfBoundsException | NumberFormatException unused) {
        }
        return iArr;
    }

    @SuppressLint({"NewApi"})
    private static boolean d() {
        if (VERSION.SDK_INT < 28) {
            return true;
        }
        LocationManager locationManager = (LocationManager) a.b().getSystemService(PlaceFields.LOCATION);
        return locationManager != null ? locationManager.isLocationEnabled() : false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0047  */
    public static java.util.Map<java.lang.String, java.lang.String> b() {
        /*
        r0 = com.inmobi.a.o.a();
        r0 = r0.a;
        r0 = r0.a;
        r1 = r0.p;
        r2 = 1;
        r3 = 0;
        if (r1 == 0) goto L_0x0014;
    L_0x000e:
        r0 = r0.a;
        if (r0 == 0) goto L_0x0014;
    L_0x0012:
        r0 = r2;
        goto L_0x0015;
    L_0x0014:
        r0 = r3;
    L_0x0015:
        r1 = 0;
        if (r0 == 0) goto L_0x00e6;
    L_0x0018:
        r0 = com.inmobi.commons.a.a.b();
        if (r0 == 0) goto L_0x0034;
    L_0x001e:
        r4 = "signals";
        r5 = "android.permission.ACCESS_COARSE_LOCATION";
        r4 = com.inmobi.commons.core.utilities.e.a(r0, r4, r5);
        r5 = "signals";
        r6 = "android.permission.ACCESS_FINE_LOCATION";
        r0 = com.inmobi.commons.core.utilities.e.a(r0, r5, r6);
        if (r4 != 0) goto L_0x0032;
    L_0x0030:
        if (r0 == 0) goto L_0x0034;
    L_0x0032:
        r0 = r2;
        goto L_0x0035;
    L_0x0034:
        r0 = r3;
    L_0x0035:
        if (r0 == 0) goto L_0x00e6;
    L_0x0037:
        r0 = d();
        if (r0 != 0) goto L_0x003f;
    L_0x003d:
        goto L_0x00e6;
    L_0x003f:
        r0 = com.inmobi.commons.a.a.b();
        if (r0 != 0) goto L_0x0047;
    L_0x0045:
        goto L_0x00e6;
    L_0x0047:
        r4 = "phone";
        r0 = r0.getSystemService(r4);
        r0 = (android.telephony.TelephonyManager) r0;
        r4 = r0.getNetworkOperator();
        r4 = a(r4);
        r5 = r4[r3];
        r6 = java.lang.String.valueOf(r5);
        r2 = r4[r2];
        r7 = java.lang.String.valueOf(r2);
        r2 = android.os.Build.VERSION.SDK_INT;
        r5 = 17;
        if (r2 < r5) goto L_0x0092;
    L_0x0069:
        r2 = r0.getAllCellInfo();
        if (r2 == 0) goto L_0x0092;
    L_0x006f:
        r8 = r1;
        r5 = r3;
    L_0x0071:
        r9 = r2.size();
        if (r5 >= r9) goto L_0x0086;
    L_0x0077:
        r8 = r2.get(r5);
        r8 = (android.telephony.CellInfo) r8;
        r9 = r8.isRegistered();
        if (r9 != 0) goto L_0x0086;
    L_0x0083:
        r5 = r5 + 1;
        goto L_0x0071;
    L_0x0086:
        if (r8 == 0) goto L_0x0092;
    L_0x0088:
        r1 = new com.inmobi.a.a.b;
        r0 = r0.getNetworkType();
        r1.<init>(r8, r6, r7, r0);
        goto L_0x00e6;
    L_0x0092:
        r2 = r0.getCellLocation();
        if (r2 == 0) goto L_0x00e6;
    L_0x0098:
        r3 = r4[r3];
        r4 = -1;
        if (r3 != r4) goto L_0x009e;
    L_0x009d:
        goto L_0x00e6;
    L_0x009e:
        r1 = new com.inmobi.a.a.b;
        r1.<init>();
        r3 = r2 instanceof android.telephony.cdma.CdmaCellLocation;
        r4 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        if (r3 == 0) goto L_0x00c7;
    L_0x00aa:
        r2 = (android.telephony.cdma.CdmaCellLocation) r2;
        r1.b = r4;
        r0 = r0.getNetworkType();
        r1.c = r0;
        r0 = r2.getSystemId();
        r3 = r2.getNetworkId();
        r2 = r2.getBaseStationId();
        r0 = com.inmobi.a.a.b.a(r6, r0, r3, r2);
        r1.a = r0;
        goto L_0x00e6;
    L_0x00c7:
        r2 = (android.telephony.gsm.GsmCellLocation) r2;
        r1.b = r4;
        r0 = r0.getNetworkType();
        r1.c = r0;
        r8 = r2.getLac();
        r9 = r2.getCid();
        r10 = r2.getPsc();
        r11 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        r0 = com.inmobi.a.a.b.a(r6, r7, r8, r9, r10, r11);
        r1.a = r0;
    L_0x00e6:
        r0 = new java.util.HashMap;
        r0.<init>();
        if (r1 == 0) goto L_0x00fa;
    L_0x00ed:
        r2 = "c-sc";
        r1 = r1.a();
        r1 = r1.toString();
        r0.put(r2, r1);
    L_0x00fa:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.a.a.c.b():java.util.Map");
    }

    /* JADX WARNING: Removed duplicated region for block: B:56:0x0127  */
    public static java.util.Map<java.lang.String, java.lang.String> c() {
        /*
        r0 = com.inmobi.commons.a.a.a();
        r1 = 1;
        if (r0 == 0) goto L_0x0117;
    L_0x0007:
        r0 = com.inmobi.commons.a.a.a();
        r2 = 0;
        if (r0 == 0) goto L_0x001e;
    L_0x000e:
        r0 = com.inmobi.commons.a.a.b();
        r3 = "signals";
        r4 = "android.permission.ACCESS_COARSE_LOCATION";
        r0 = com.inmobi.commons.core.utilities.e.a(r0, r3, r4);
        if (r0 == 0) goto L_0x001e;
    L_0x001c:
        r0 = r1;
        goto L_0x001f;
    L_0x001e:
        r0 = r2;
    L_0x001f:
        if (r0 == 0) goto L_0x0117;
    L_0x0021:
        r0 = d();
        if (r0 == 0) goto L_0x0117;
    L_0x0027:
        r0 = com.inmobi.a.o.a();
        r0 = r0.a;
        r0 = r0.a;
        r3 = r0.o;
        if (r3 == 0) goto L_0x0039;
    L_0x0033:
        r0 = r0.a;
        if (r0 == 0) goto L_0x0039;
    L_0x0037:
        r0 = r1;
        goto L_0x003a;
    L_0x0039:
        r0 = r2;
    L_0x003a:
        if (r0 != 0) goto L_0x003e;
    L_0x003c:
        goto L_0x0117;
    L_0x003e:
        r0 = com.inmobi.commons.a.a.b();
        if (r0 != 0) goto L_0x004b;
    L_0x0044:
        r0 = new java.util.ArrayList;
        r0.<init>();
        goto L_0x011c;
    L_0x004b:
        r3 = "phone";
        r0 = r0.getSystemService(r3);
        r0 = (android.telephony.TelephonyManager) r0;
        r3 = new java.util.ArrayList;
        r3.<init>();
        r4 = r0.getNetworkOperator();
        r4 = a(r4);
        r5 = r4[r2];
        r6 = java.lang.String.valueOf(r5);
        r4 = r4[r1];
        r7 = java.lang.String.valueOf(r4);
        r4 = android.os.Build.VERSION.SDK_INT;
        r5 = 17;
        if (r4 < r5) goto L_0x009b;
    L_0x0072:
        r4 = r0.getAllCellInfo();
        if (r4 == 0) goto L_0x009b;
    L_0x0078:
        r2 = r4.iterator();
    L_0x007c:
        r4 = r2.hasNext();
        if (r4 == 0) goto L_0x0109;
    L_0x0082:
        r4 = r2.next();
        r4 = (android.telephony.CellInfo) r4;
        r5 = r4.isRegistered();
        if (r5 != 0) goto L_0x007c;
    L_0x008e:
        r5 = new com.inmobi.a.a.b;
        r8 = r0.getNetworkType();
        r5.<init>(r4, r6, r7, r8);
        r3.add(r5);
        goto L_0x007c;
    L_0x009b:
        r0 = r0.getNeighboringCellInfo();
        if (r0 == 0) goto L_0x0111;
    L_0x00a1:
        r4 = r0.isEmpty();
        if (r4 == 0) goto L_0x00a8;
    L_0x00a7:
        goto L_0x0111;
    L_0x00a8:
        r0 = r0.iterator();
        r4 = r0.hasNext();
        if (r4 == 0) goto L_0x010b;
    L_0x00b2:
        r0 = r0.next();
        r0 = (android.telephony.NeighboringCellInfo) r0;
        r4 = new com.inmobi.a.a.b;
        r4.<init>();
        r5 = r0.getNetworkType();
        r4.c = r5;
        r8 = r0.getRssi();
        r9 = 99;
        if (r8 != r9) goto L_0x00d1;
    L_0x00cb:
        r2 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        r4.b = r2;
        goto L_0x00f4;
    L_0x00d1:
        r8 = 3;
        if (r5 == r8) goto L_0x00dc;
    L_0x00d4:
        r8 = 15;
        if (r5 == r8) goto L_0x00dc;
    L_0x00d8:
        switch(r5) {
            case 8: goto L_0x00dc;
            case 9: goto L_0x00dc;
            case 10: goto L_0x00dc;
            default: goto L_0x00db;
        };
    L_0x00db:
        goto L_0x00dd;
    L_0x00dc:
        r2 = r1;
    L_0x00dd:
        if (r2 == 0) goto L_0x00e9;
    L_0x00df:
        r2 = -116; // 0xffffffffffffff8c float:NaN double:NaN;
        r5 = r0.getRssi();
        r2 = r2 + r5;
        r4.b = r2;
        goto L_0x00f4;
    L_0x00e9:
        r2 = -113; // 0xffffffffffffff8f float:NaN double:NaN;
        r5 = 2;
        r8 = r0.getRssi();
        r5 = r5 * r8;
        r2 = r2 + r5;
        r4.b = r2;
    L_0x00f4:
        r8 = r0.getLac();
        r9 = r0.getCid();
        r10 = -1;
        r11 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        r0 = com.inmobi.a.a.b.a(r6, r7, r8, r9, r10, r11);
        r4.a = r0;
        r3.add(r4);
    L_0x0109:
        r0 = r3;
        goto L_0x011c;
    L_0x010b:
        r0 = new java.util.ArrayList;
        r0.<init>();
        goto L_0x011c;
    L_0x0111:
        r0 = new java.util.ArrayList;
        r0.<init>();
        goto L_0x011c;
    L_0x0117:
        r0 = new java.util.ArrayList;
        r0.<init>();
    L_0x011c:
        r2 = new java.util.HashMap;
        r2.<init>();
        r3 = r0.isEmpty();
        if (r3 != 0) goto L_0x0147;
    L_0x0127:
        r3 = new org.json.JSONArray;
        r3.<init>();
        r4 = r0.size();
        r4 = r4 - r1;
        r0 = r0.get(r4);
        r0 = (com.inmobi.a.a.b) r0;
        r0 = r0.a();
        r3.put(r0);
        r0 = "v-sc";
        r1 = r3.toString();
        r2.put(r0, r1);
    L_0x0147:
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.a.a.c.c():java.util.Map");
    }
}
