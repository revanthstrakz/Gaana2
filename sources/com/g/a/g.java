package com.g.a;

import android.content.Context;
import android.net.TrafficStats;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.SystemClock;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.Map;
import java.util.TimeZone;

public class g {
    Context a = null;

    public g(Context context) {
        this.a = context;
    }

    private String a() {
        try {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            long uptimeMillis = SystemClock.uptimeMillis();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(elapsedRealtime);
            stringBuilder.append(";");
            stringBuilder.append(uptimeMillis);
            return stringBuilder.toString();
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            j.a("Exception in getTimeSinceBoot");
            return "";
        }
    }

    private String b() {
        if (VERSION.SDK_INT < 21) {
            return "not_supported";
        }
        try {
            Context context = this.a;
            Context context2 = this.a;
            int intProperty = ((BatteryManager) context.getSystemService("batterymanager")).getIntProperty(4);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(intProperty);
            return stringBuilder.toString();
        } catch (Exception e) {
            j.a("Exception in getBatteryInfo");
            ThrowableExtension.printStackTrace(e);
            return "";
        }
    }

    private String c() {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(TrafficStats.getMobileRxBytes());
            stringBuilder.append(";");
            stringBuilder.append(TrafficStats.getMobileTxBytes());
            stringBuilder.append(";");
            stringBuilder.append(TrafficStats.getTotalRxBytes());
            stringBuilder.append(";");
            stringBuilder.append(TrafficStats.getTotalTxBytes());
            return stringBuilder.toString();
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            j.a("Exception in getTrafficStats");
            return "";
        }
    }

    private String d() {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(VERSION.RELEASE);
            stringBuilder.append(";");
            stringBuilder.append(VERSION.INCREMENTAL);
            stringBuilder.append(";");
            stringBuilder.append(VERSION.SDK_INT);
            stringBuilder.append(";");
            stringBuilder.append(Build.BOARD);
            stringBuilder.append(";");
            stringBuilder.append(Build.BOOTLOADER);
            stringBuilder.append(";");
            stringBuilder.append(Build.BRAND);
            stringBuilder.append(";");
            stringBuilder.append(Build.TAGS);
            stringBuilder.append(";");
            stringBuilder.append(Build.HOST);
            stringBuilder.append(";");
            stringBuilder.append(Build.DISPLAY);
            stringBuilder.append(";");
            stringBuilder.append(Build.FINGERPRINT);
            stringBuilder.append(";");
            stringBuilder.append(Build.HARDWARE);
            stringBuilder.append(";");
            stringBuilder.append(Build.HOST);
            stringBuilder.append(";");
            stringBuilder.append(Build.ID);
            stringBuilder.append(";");
            stringBuilder.append(Build.MANUFACTURER);
            stringBuilder.append(";");
            stringBuilder.append(Build.MODEL);
            stringBuilder.append(";");
            stringBuilder.append(Build.PRODUCT);
            stringBuilder.append(";");
            stringBuilder.append(Build.SERIAL);
            stringBuilder.append(";");
            stringBuilder.append(Build.TAGS);
            stringBuilder.append(";");
            stringBuilder.append(Build.TIME);
            stringBuilder.append(";");
            stringBuilder.append(Build.TYPE);
            stringBuilder.append(";");
            stringBuilder.append("unknown");
            stringBuilder.append(";");
            stringBuilder.append(Build.USER);
            stringBuilder.append(";");
            stringBuilder.append(Build.getRadioVersion());
            String stringBuilder2 = stringBuilder.toString();
            if (VERSION.SDK_INT >= 21) {
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append(stringBuilder2);
                stringBuilder3.append(";");
                stringBuilder3.append(Build.SUPPORTED_ABIS);
                stringBuilder2 = stringBuilder3.toString();
            }
            return stringBuilder2;
        } catch (Exception e) {
            j.a("Exception in getBuildDetails");
            ThrowableExtension.printStackTrace(e);
            return "";
        }
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0041 */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing block: B:12:0x0043, code skipped:
            return "exception";
     */
    private java.lang.String e() {
        /*
        r8 = this;
        r0 = r8.a;	 Catch:{ Exception -> 0x0056 }
        r0 = r0.getPackageManager();	 Catch:{ Exception -> 0x0056 }
        r1 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        r0 = r0.getInstalledApplications(r1);	 Catch:{ Exception -> 0x0056 }
        r0 = r0.listIterator();	 Catch:{ Exception -> 0x0056 }
        r1 = 0;
        r2 = r1;
    L_0x0012:
        r3 = r0.hasNext();	 Catch:{ Exception -> 0x0056 }
        if (r3 == 0) goto L_0x0044;
    L_0x0018:
        r3 = r0.next();	 Catch:{ Exception -> 0x0056 }
        r3 = (android.content.pm.ApplicationInfo) r3;	 Catch:{ Exception -> 0x0056 }
        r4 = r8.a;	 Catch:{ Exception -> 0x0041 }
        r4 = r4.getPackageManager();	 Catch:{ Exception -> 0x0041 }
        r5 = r3.packageName;	 Catch:{ Exception -> 0x0041 }
        r4 = r4.getPackageInfo(r5, r1);	 Catch:{ Exception -> 0x0041 }
        r4 = r4.firstInstallTime;	 Catch:{ Exception -> 0x0041 }
        r6 = r8.a;	 Catch:{ Exception -> 0x0041 }
        r6 = r6.getPackageManager();	 Catch:{ Exception -> 0x0041 }
        r3 = r3.packageName;	 Catch:{ Exception -> 0x0041 }
        r3 = r6.getPackageInfo(r3, r1);	 Catch:{ Exception -> 0x0041 }
        r6 = r3.lastUpdateTime;	 Catch:{ Exception -> 0x0041 }
        r3 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1));
        if (r3 <= 0) goto L_0x0012;
    L_0x003e:
        r2 = r2 + 1;
        goto L_0x0012;
    L_0x0041:
        r0 = "exception";
        return r0;
    L_0x0044:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "";
        r0.append(r1);
        r0.append(r2);
        r0 = r0.toString();
        return r0;
    L_0x0056:
        r0 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);
        r0 = "Exception in getUpdatedPackageCount";
        com.g.a.j.a(r0);
        r0 = "exception";
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.g.a.g.e():java.lang.String");
    }

    private String f() {
        try {
            TimeZone timeZone = TimeZone.getDefault();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(timeZone.getDisplayName(false, 0));
            stringBuilder.append(";");
            stringBuilder.append(timeZone.getID());
            return stringBuilder.toString();
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            j.a("Exception in getTimeZoneInfo");
            return "";
        }
    }

    public Map<String, String> a(Map<String, String> map) {
        try {
            j.a("Collect datapoints D");
            String a = a();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(a);
            map.put("sdk_time_since_boot", stringBuilder.toString());
            a = b();
            stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(a);
            map.put("sdk_battery_info", stringBuilder.toString());
            a = c();
            stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(a);
            map.put("sdk_traffic_stats", stringBuilder.toString());
            a = d();
            stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(a);
            map.put("sdk_build_details", stringBuilder.toString());
            a = e();
            stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(a);
            map.put("sdk_pkg_updates_count", stringBuilder.toString());
            a = f();
            stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(a);
            map.put("sdk_timezone_info", stringBuilder.toString());
            return map;
        } catch (Exception unused) {
            j.a("Exception while collecting datapoints D");
            return map;
        }
    }
}
