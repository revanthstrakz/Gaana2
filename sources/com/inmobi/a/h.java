package com.inmobi.a;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import com.inmobi.a.b.b;
import com.inmobi.a.b.c;
import com.inmobi.commons.core.e.f;
import com.inmobi.commons.core.network.d;
import com.inmobi.commons.core.utilities.b.h;
import com.inmobi.commons.core.utilities.e;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class h {
    private static final String b = "h";
    a a;

    static class a extends Handler {
        private List<l> a = new ArrayList();
        private boolean b;

        a(Looper looper) {
            super(looper);
        }

        public final void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    h.b;
                    sendEmptyMessage(3);
                    return;
                case 2:
                    h.b;
                    removeMessages(3);
                    sendEmptyMessage(4);
                    return;
                case 3:
                    h.b;
                    if (this.b) {
                        sendEmptyMessage(2);
                        return;
                    }
                    final l lVar = new l();
                    lVar.a = b.a();
                    lVar.c = m.a().e();
                    p.b bVar = o.a().a.a;
                    int i = 0;
                    int i2 = (bVar.k && bVar.a) ? 1 : 0;
                    if (i2 != 0) {
                        if (com.inmobi.commons.a.a.a()) {
                            String[] strArr = new String[]{"android.permission.ACCESS_WIFI_STATE", "android.permission.CHANGE_WIFI_STATE", "android.permission.ACCESS_COARSE_LOCATION"};
                            int i3 = 0;
                            while (i3 < 3) {
                                if (e.a(com.inmobi.commons.a.a.b(), "signals", strArr[i3])) {
                                    i3++;
                                }
                            }
                            i = 1;
                        }
                        if (i != 0) {
                            if (!c.a(new com.inmobi.a.b.c.a() {
                                public final void a() {
                                    h.b;
                                    a.this.a(lVar);
                                }

                                public final void a(List<com.inmobi.a.b.a> list) {
                                    h.b;
                                    lVar.b = list;
                                    a.this.a(lVar);
                                }
                            })) {
                                a(lVar);
                            }
                            sendEmptyMessageDelayed(3, (long) (o.a().a.a.b * 1000));
                            return;
                        }
                    }
                    a(lVar);
                    sendEmptyMessageDelayed(3, (long) (o.a().a.a.b * 1000));
                    return;
                case 4:
                    k kVar = new k();
                    kVar.a = m.a().d();
                    kVar.c = this.a;
                    n.a();
                    kVar.b = n.b();
                    p.b bVar2 = o.a().a.a;
                    new Thread(new Runnable() {
                        public final void run() {
                            StringBuilder stringBuilder;
                            int i = 0;
                            while (i <= i.this.b.a) {
                                i.a;
                                long elapsedRealtime = SystemClock.elapsedRealtime();
                                d a = new com.inmobi.commons.core.network.e(i.this.b).a();
                                try {
                                    n.a().a(i.this.b.g());
                                    n.a().b(a.c());
                                    n.a().c(SystemClock.elapsedRealtime() - elapsedRealtime);
                                } catch (Exception e) {
                                    i.a;
                                    new StringBuilder("Error in setting request-response data size. ").append(e.getMessage());
                                }
                                if (a.a()) {
                                    i.a;
                                    i++;
                                    if (i > i.this.b.a) {
                                        try {
                                            com.inmobi.commons.core.e.b.a().a(new f("signals", "RetryCountExceeded"));
                                            return;
                                        } catch (Exception e2) {
                                            i.a;
                                            stringBuilder = new StringBuilder("Error in submitting telemetry event : (");
                                            stringBuilder.append(e2.getMessage());
                                            stringBuilder.append(")");
                                            return;
                                        }
                                    }
                                    try {
                                        Thread.sleep((long) (i.this.b.b * 1000));
                                    } catch (InterruptedException unused) {
                                        i.a;
                                    }
                                } else {
                                    i.a;
                                    try {
                                        HashMap hashMap = new HashMap();
                                        hashMap.put("url", i.this.b.q);
                                        hashMap.put("latency", Long.valueOf(SystemClock.elapsedRealtime() - 0));
                                        hashMap.put("payloadSize", Long.valueOf(i.this.b.g() + a.c()));
                                        com.inmobi.commons.core.e.b.a();
                                        com.inmobi.commons.core.e.b.a("signals", "NICElatency", hashMap);
                                        hashMap = new HashMap();
                                        hashMap.put("sessionId", h.a().a);
                                        n a2 = n.a();
                                        hashMap.put("totalWifiSentBytes", Long.valueOf(a2.a));
                                        hashMap.put("totalWifiReceivedBytes", Long.valueOf(a2.b));
                                        hashMap.put("totalCarrierSentBytes", Long.valueOf(a2.c));
                                        hashMap.put("totalCarrierReceivedBytes", Long.valueOf(a2.d));
                                        hashMap.put("totalNetworkTime", Long.valueOf(a2.e));
                                        com.inmobi.commons.core.e.b.a();
                                        com.inmobi.commons.core.e.b.a("signals", "SDKNetworkStats", hashMap);
                                        return;
                                    } catch (Exception e22) {
                                        i.a;
                                        stringBuilder = new StringBuilder("Error in submitting telemetry event : (");
                                        stringBuilder.append(e22.getMessage());
                                        stringBuilder.append(")");
                                        return;
                                    }
                                }
                            }
                        }
                    }).start();
                    this.a = new ArrayList();
                    break;
            }
        }

        private void a(l lVar) {
            if (this.a != null) {
                int i = (lVar.a == null && lVar.b == null) ? 0 : 1;
                if (i != 0) {
                    this.a.add(lVar);
                    if (this.a.size() > o.a().a.a.d) {
                        try {
                            com.inmobi.commons.core.e.b.a().a(new f("signals", "SampleSizeExceeded"));
                        } catch (Exception e) {
                            h.b;
                            StringBuilder stringBuilder = new StringBuilder("Error in submitting telemetry event : (");
                            stringBuilder.append(e.getMessage());
                            stringBuilder.append(")");
                        }
                        while (this.a.size() > o.a().a.a.d) {
                            this.a.remove(0);
                        }
                    }
                }
            }
        }
    }

    h() {
        HandlerThread handlerThread = new HandlerThread("DataCollectionHandler");
        handlerThread.start();
        this.a = new a(handlerThread.getLooper());
    }

    public final synchronized void a() {
        this.a.b = false;
        if (!this.a.hasMessages(3)) {
            this.a.removeMessages(2);
            this.a.sendEmptyMessage(1);
        }
    }
}
