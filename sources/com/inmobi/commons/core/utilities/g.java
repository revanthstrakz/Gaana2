package com.inmobi.commons.core.utilities;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.ConnectivityManager.NetworkCallback;
import android.net.Network;
import android.os.Build.VERSION;
import com.til.colombia.android.internal.d;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class g {
    private static final String a = "g";
    private static HashMap<String, CopyOnWriteArrayList<b>> b = new HashMap();
    private static HashMap<String, a> c = new HashMap();
    private static HashMap<String, NetworkCallback> d = new HashMap();
    private static final Object e = new Object();
    private static volatile g f;

    static final class a extends BroadcastReceiver {
        private static final String a = "g$a";

        a() {
        }

        /* JADX WARNING: Missing block: B:23:0x005a, code skipped:
            if ("android.intent.action.USER_PRESENT".equals(r6.getAction()) != false) goto L_0x005c;
     */
        @android.annotation.SuppressLint({"MissingPermission"})
        public final void onReceive(android.content.Context r5, android.content.Intent r6) {
            /*
            r4 = this;
            if (r6 == 0) goto L_0x0089;
        L_0x0002:
            r0 = r6.getAction();	 Catch:{ Exception -> 0x0079 }
            if (r0 == 0) goto L_0x0089;
        L_0x0008:
            r0 = "android.net.conn.CONNECTIVITY_CHANGE";
            r1 = r6.getAction();	 Catch:{ Exception -> 0x0079 }
            r0 = r0.equals(r1);	 Catch:{ Exception -> 0x0079 }
            r1 = 0;
            r2 = 1;
            if (r0 == 0) goto L_0x002d;
        L_0x0016:
            r0 = "connectivity";
            r5 = r5.getSystemService(r0);	 Catch:{ Exception -> 0x0079 }
            r5 = (android.net.ConnectivityManager) r5;	 Catch:{ Exception -> 0x0079 }
            if (r5 == 0) goto L_0x005d;
        L_0x0020:
            r5 = r5.getActiveNetworkInfo();	 Catch:{ Exception -> 0x0079 }
            if (r5 == 0) goto L_0x005d;
        L_0x0026:
            r5 = r5.isConnected();	 Catch:{ Exception -> 0x0079 }
            if (r5 == 0) goto L_0x005d;
        L_0x002c:
            goto L_0x005c;
        L_0x002d:
            r0 = "android.os.action.DEVICE_IDLE_MODE_CHANGED";
            r3 = r6.getAction();	 Catch:{ Exception -> 0x0079 }
            r0 = r0.equalsIgnoreCase(r3);	 Catch:{ Exception -> 0x0079 }
            if (r0 == 0) goto L_0x0050;
        L_0x0039:
            r0 = "power";
            r5 = r5.getSystemService(r0);	 Catch:{ Exception -> 0x0079 }
            r5 = (android.os.PowerManager) r5;	 Catch:{ Exception -> 0x0079 }
            if (r5 == 0) goto L_0x005d;
        L_0x0043:
            r0 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x0079 }
            r3 = 23;
            if (r0 < r3) goto L_0x005d;
        L_0x0049:
            r5 = r5.isDeviceIdleMode();	 Catch:{ Exception -> 0x0079 }
            if (r5 == 0) goto L_0x005d;
        L_0x004f:
            goto L_0x005c;
        L_0x0050:
            r5 = "android.intent.action.USER_PRESENT";
            r0 = r6.getAction();	 Catch:{ Exception -> 0x0079 }
            r5 = r5.equals(r0);	 Catch:{ Exception -> 0x0079 }
            if (r5 == 0) goto L_0x005d;
        L_0x005c:
            r1 = r2;
        L_0x005d:
            r5 = r6.getAction();	 Catch:{ Exception -> 0x0079 }
            com.inmobi.commons.core.utilities.g.b(r1, r5);	 Catch:{ Exception -> 0x0079 }
            r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0079 }
            r5.<init>();	 Catch:{ Exception -> 0x0079 }
            r6 = r6.getAction();	 Catch:{ Exception -> 0x0079 }
            r5.append(r6);	 Catch:{ Exception -> 0x0079 }
            r6 = " Availability:";
            r5.append(r6);	 Catch:{ Exception -> 0x0079 }
            r5.append(r1);	 Catch:{ Exception -> 0x0079 }
            goto L_0x0089;
        L_0x0079:
            r5 = move-exception;
            r6 = new java.lang.StringBuilder;
            r0 = "SDK encountered unexpected error in SystemBroadReceiver.onReceive handler; ";
            r6.<init>(r0);
            r5 = r5.getMessage();
            r6.append(r5);
            return;
        L_0x0089:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.inmobi.commons.core.utilities.g$a.onReceive(android.content.Context, android.content.Intent):void");
        }
    }

    public interface b {
        void a(boolean z);
    }

    public static g a() {
        g gVar = f;
        if (gVar == null) {
            synchronized (e) {
                gVar = f;
                if (gVar == null) {
                    gVar = new g();
                    f = gVar;
                }
            }
        }
        return gVar;
    }

    public final void a(String str, b bVar) {
        Object obj = (CopyOnWriteArrayList) b.get(str);
        if (obj != null) {
            obj.add(bVar);
        } else {
            obj = new CopyOnWriteArrayList();
            obj.add(bVar);
        }
        b.put(str, obj);
        if (obj.size() == 1) {
            Context b = com.inmobi.commons.a.a.b();
            if (b != null) {
                if ("SYSTEM_CONNECTIVITY_CHANGE".equals(str)) {
                    ConnectivityManager connectivityManager = (ConnectivityManager) b.getSystemService("connectivity");
                    if (connectivityManager != null) {
                        AnonymousClass1 anonymousClass1 = new NetworkCallback() {
                            public final void onAvailable(Network network) {
                                super.onAvailable(network);
                                g.b(true, "SYSTEM_CONNECTIVITY_CHANGE");
                            }

                            public final void onLost(Network network) {
                                super.onLost(network);
                                g.b(false, "SYSTEM_CONNECTIVITY_CHANGE");
                            }
                        };
                        d.put(str, anonymousClass1);
                        connectivityManager.registerDefaultNetworkCallback(anonymousClass1);
                    }
                    return;
                }
                a aVar = new a();
                c.put(str, aVar);
                b.registerReceiver(aVar, new IntentFilter(str));
            }
        }
    }

    public final void a(b bVar) {
        if (VERSION.SDK_INT < 28) {
            a(d.a, bVar);
        } else {
            a("SYSTEM_CONNECTIVITY_CHANGE", bVar);
        }
    }

    private static void b(boolean z, String str) {
        CopyOnWriteArrayList copyOnWriteArrayList = (CopyOnWriteArrayList) b.get(str);
        if (copyOnWriteArrayList != null) {
            Iterator it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                try {
                    ((b) it.next()).a(z);
                } catch (Exception e) {
                    new StringBuilder("SDK encountered unexpected error in SystemBroadcastObserver.onServiceChanged event handler; ").append(e.getMessage());
                }
            }
        }
    }

    @SuppressLint({"NewApi"})
    public static void a(b bVar, String str) {
        CopyOnWriteArrayList copyOnWriteArrayList = (CopyOnWriteArrayList) b.get(str);
        if (copyOnWriteArrayList != null) {
            copyOnWriteArrayList.remove(bVar);
            if (copyOnWriteArrayList.size() == 0) {
                Context b = com.inmobi.commons.a.a.b();
                if (b != null) {
                    if ("SYSTEM_CONNECTIVITY_CHANGE".equals(str) && d.get(str) != null) {
                        ConnectivityManager connectivityManager = (ConnectivityManager) b.getSystemService("connectivity");
                        if (connectivityManager != null) {
                            connectivityManager.unregisterNetworkCallback((NetworkCallback) d.get(str));
                            d.remove(str);
                        }
                    } else if (c.get(str) != null) {
                        b.unregisterReceiver((BroadcastReceiver) c.get(str));
                        c.remove(str);
                    }
                }
            }
        }
    }
}
