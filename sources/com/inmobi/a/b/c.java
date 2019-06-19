package com.inmobi.a.b;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;
import com.inmobi.a.o;
import com.til.colombia.android.internal.e;
import java.util.ArrayList;
import java.util.List;

public class c {
    private static final String a = "c";
    @SuppressLint({"StaticFieldLeak"})
    private static Context b;
    private static a c;
    private static Handler d;
    private static boolean e;
    private static final IntentFilter f = new IntentFilter("android.net.wifi.SCAN_RESULTS");
    private static List<a> g;
    private static Runnable h = new Runnable() {
        public final void run() {
            a b = c.c;
            c.f();
            if (b != null) {
                b.a();
            }
        }
    };
    @SuppressLint({"WifiManagerPotentialLeak", "MissingPermission"})
    private static final BroadcastReceiver i = new BroadcastReceiver() {
        public final void onReceive(Context context, Intent intent) {
            a b = c.c;
            WifiManager wifiManager = (WifiManager) c.b.getSystemService(e.ad);
            c.f();
            if (b != null && wifiManager != null) {
                List<ScanResult> scanResults = wifiManager.getScanResults();
                int i = o.a().a.a.j;
                boolean a = b.a(i);
                boolean a2 = b.a(i, 1);
                List arrayList = new ArrayList();
                if (scanResults != null) {
                    for (ScanResult scanResult : scanResults) {
                        if (!b.a(a, scanResult.SSID)) {
                            Object obj = null;
                            if (scanResult != null) {
                                String str;
                                a aVar = new a();
                                aVar.a = b.a(scanResult.BSSID);
                                if (!a2) {
                                    str = scanResult.SSID;
                                }
                                aVar.b = str;
                                aVar.c = scanResult.level;
                                obj = aVar;
                            }
                            arrayList.add(obj);
                        }
                    }
                }
                c.g = arrayList;
                b.a(c.g);
            }
        }
    };

    public interface a {
        void a();

        void a(List<a> list);
    }

    public static boolean a(a aVar) {
        b = com.inmobi.commons.a.a.b();
        return a(Looper.myLooper(), aVar);
    }

    public static List<a> a() {
        return g;
    }

    /* JADX WARNING: Missing block: B:26:0x004d, code skipped:
            return false;
     */
    @android.annotation.SuppressLint({"MissingPermission"})
    private static synchronized boolean a(android.os.Looper r5, com.inmobi.a.b.c.a r6) {
        /*
        r0 = com.inmobi.a.b.c.class;
        monitor-enter(r0);
        r1 = d;	 Catch:{ all -> 0x004e }
        r2 = 0;
        if (r1 == 0) goto L_0x000a;
    L_0x0008:
        monitor-exit(r0);
        return r2;
    L_0x000a:
        r1 = com.inmobi.commons.a.a.b();	 Catch:{ all -> 0x004e }
        if (r1 != 0) goto L_0x0012;
    L_0x0010:
        monitor-exit(r0);
        return r2;
    L_0x0012:
        r3 = "wifi";
        r1 = r1.getSystemService(r3);	 Catch:{ all -> 0x004e }
        r1 = (android.net.wifi.WifiManager) r1;	 Catch:{ all -> 0x004e }
        if (r1 == 0) goto L_0x004c;
    L_0x001c:
        r3 = r1.isWifiEnabled();	 Catch:{ all -> 0x004e }
        if (r3 != 0) goto L_0x0023;
    L_0x0022:
        goto L_0x004c;
    L_0x0023:
        c = r6;	 Catch:{ all -> 0x004e }
        r6 = new android.os.Handler;	 Catch:{ all -> 0x004e }
        r6.<init>(r5);	 Catch:{ all -> 0x004e }
        d = r6;	 Catch:{ all -> 0x004e }
        r5 = h;	 Catch:{ all -> 0x004e }
        r2 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r6.postDelayed(r5, r2);	 Catch:{ all -> 0x004e }
        r5 = e;	 Catch:{ all -> 0x004e }
        if (r5 != 0) goto L_0x0046;
    L_0x0037:
        r5 = 1;
        e = r5;	 Catch:{ all -> 0x004e }
        r5 = b;	 Catch:{ all -> 0x004e }
        r6 = i;	 Catch:{ all -> 0x004e }
        r2 = f;	 Catch:{ all -> 0x004e }
        r3 = 0;
        r4 = d;	 Catch:{ all -> 0x004e }
        r5.registerReceiver(r6, r2, r3, r4);	 Catch:{ all -> 0x004e }
    L_0x0046:
        r5 = r1.startScan();	 Catch:{ all -> 0x004e }
        monitor-exit(r0);
        return r5;
    L_0x004c:
        monitor-exit(r0);
        return r2;
    L_0x004e:
        r5 = move-exception;
        monitor-exit(r0);
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.a.b.c.a(android.os.Looper, com.inmobi.a.b.c$a):boolean");
    }

    private static synchronized void f() {
        synchronized (c.class) {
            if (d == null) {
                return;
            }
            d.removeCallbacks(h);
            if (e) {
                e = false;
                try {
                    b.unregisterReceiver(i);
                } catch (IllegalArgumentException unused) {
                }
            }
            d = null;
            c = null;
            b = null;
        }
    }
}
