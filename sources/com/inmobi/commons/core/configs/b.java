package com.inmobi.commons.core.configs;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.b.e;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class b {
    private static final String a = "b";
    private static Map<a, ArrayList<WeakReference<c>>> b;
    private static h c;
    private static d d;
    private HandlerThread e;
    private b f;
    private boolean g;

    private static class a {
        private static final b a = new b();
    }

    public interface c {
        void a(a aVar);
    }

    static final class b extends Handler implements com.inmobi.commons.core.configs.e.a {
        private List<a> a = new ArrayList();
        private Map<String, Map<String, a>> b = new HashMap();
        private Map<String, a> c = new HashMap();
        private ExecutorService d;

        b(Looper looper) {
            super(looper);
        }

        public final void handleMessage(Message message) {
            Message message2 = message;
            switch (message2.what) {
                case 1:
                    a aVar = (a) message2.obj;
                    b.a;
                    StringBuilder stringBuilder = new StringBuilder("Fetch requested for config:");
                    stringBuilder.append(aVar.a());
                    stringBuilder.append(". IsAlreadyScheduled:");
                    stringBuilder.append(a(aVar.a()));
                    if (a(aVar.a())) {
                        b.a;
                        new StringBuilder("Config fetching already in progress:").append(aVar.a());
                        return;
                    }
                    this.a.add(aVar);
                    if (!hasMessages(2)) {
                        sendEmptyMessage(2);
                        return;
                    }
                    break;
                case 2:
                    sendEmptyMessageDelayed(3, (long) (b.c.c * 1000));
                    return;
                case 3:
                    a(this.a);
                    this.a.clear();
                    if (this.d == null || this.d.isShutdown()) {
                        this.d = Executors.newFixedThreadPool(1);
                        sendEmptyMessage(4);
                        return;
                    }
                case 4:
                    if (this.b.isEmpty()) {
                        b.a;
                        sendEmptyMessage(5);
                        return;
                    }
                    int i;
                    f fVar;
                    Entry entry = (Entry) this.b.entrySet().iterator().next();
                    this.c = (Map) entry.getValue();
                    this.b.remove(entry.getKey());
                    String str = (String) entry.getKey();
                    Map map = this.c;
                    int i2 = b.c.b;
                    int i3 = b.c.a;
                    com.inmobi.commons.core.utilities.uid.d dVar = new com.inmobi.commons.core.utilities.uid.d(b.c.p.a);
                    int d = e.d();
                    if (d == 0 && map.containsKey("root")) {
                        map = b.a(map);
                        i = 1;
                    } else {
                        i = d;
                    }
                    f fVar2 = new f(map, dVar, str, i3, i2, i);
                    if (map.containsKey("root")) {
                        f fVar3 = new f(b.a(map), dVar, b.c.e(), i3, i2, true, i);
                    } else {
                        fVar = null;
                    }
                    this.d.execute(new e(this, fVar2, fVar));
                    return;
                case 5:
                    if (!(this.d == null || this.d.isShutdown())) {
                        this.c = null;
                        this.b.clear();
                        removeMessages(3);
                        this.d.shutdownNow();
                        break;
                    }
            }
        }

        private boolean a(String str) {
            boolean z = this.b.get(b.c.b(str)) != null && ((Map) this.b.get(b.c.b(str))).containsKey(str);
            return (this.c == null || !this.c.containsKey(str)) ? z : true;
        }

        private void a(List<a> list) {
            for (int i = 0; i < list.size(); i++) {
                a aVar = (a) list.get(i);
                HashMap hashMap = (HashMap) this.b.get(b.c.b(aVar.a()));
                if (hashMap == null) {
                    hashMap = new HashMap();
                    this.b.put(b.c.b(aVar.a()), hashMap);
                }
                hashMap.put(aVar.a(), aVar);
            }
        }

        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0061 */
        /* JADX WARNING: Failed to process nested try/catch */
        public final void a(com.inmobi.commons.core.configs.ConfigNetworkResponse.ConfigResponse r6) {
            /*
            r5 = this;
            r0 = new com.inmobi.commons.core.configs.c;
            r0.<init>();
            r1 = r6.a();
            if (r1 != 0) goto L_0x00ca;
        L_0x000b:
            r1 = r6.a;
            r2 = com.inmobi.commons.core.configs.ConfigNetworkResponse.ConfigResponse.ConfigResponseStatus.NOT_MODIFIED;
            if (r1 != r2) goto L_0x0032;
        L_0x0011:
            com.inmobi.commons.core.configs.b.a;
            r1 = new java.lang.StringBuilder;
            r2 = "Config not modified status from server:";
            r1.<init>(r2);
            r2 = r6.b;
            r2 = r2.a();
            r1.append(r2);
            r6 = r6.b;
            r6 = r6.a();
            r1 = java.lang.System.currentTimeMillis();
            r0.a(r6, r1);
            return;
        L_0x0032:
            r1 = r6.b;
            r2 = r0.a;	 Catch:{ JSONException -> 0x0061 }
            r3 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x0061 }
            r3.<init>();	 Catch:{ JSONException -> 0x0061 }
            r4 = r1.a();	 Catch:{ JSONException -> 0x0061 }
            r3.append(r4);	 Catch:{ JSONException -> 0x0061 }
            r4 = "_config";
            r3.append(r4);	 Catch:{ JSONException -> 0x0061 }
            r3 = r3.toString();	 Catch:{ JSONException -> 0x0061 }
            r4 = r1.b();	 Catch:{ JSONException -> 0x0061 }
            r4 = r4.toString();	 Catch:{ JSONException -> 0x0061 }
            r2.a(r3, r4);	 Catch:{ JSONException -> 0x0061 }
            r1 = r1.a();	 Catch:{ JSONException -> 0x0061 }
            r2 = java.lang.System.currentTimeMillis();	 Catch:{ JSONException -> 0x0061 }
            r0.a(r1, r2);	 Catch:{ JSONException -> 0x0061 }
        L_0x0061:
            r0 = new java.util.HashMap;	 Catch:{ Exception -> 0x0083 }
            r0.<init>();	 Catch:{ Exception -> 0x0083 }
            r1 = "configName";
            r2 = r6.b;	 Catch:{ Exception -> 0x0083 }
            r2 = r2.a();	 Catch:{ Exception -> 0x0083 }
            r0.put(r1, r2);	 Catch:{ Exception -> 0x0083 }
            r1 = "latency";
            r2 = "2147483647";
            r0.put(r1, r2);	 Catch:{ Exception -> 0x0083 }
            com.inmobi.commons.core.e.b.a();	 Catch:{ Exception -> 0x0083 }
            r1 = "root";
            r2 = "ConfigFetched";
            com.inmobi.commons.core.e.b.a(r1, r2, r0);	 Catch:{ Exception -> 0x0083 }
            goto L_0x009a;
        L_0x0083:
            r0 = move-exception;
            com.inmobi.commons.core.configs.b.a;
            r1 = new java.lang.StringBuilder;
            r2 = "Error in submitting telemetry event : (";
            r1.<init>(r2);
            r0 = r0.getMessage();
            r1.append(r0);
            r0 = ")";
            r1.append(r0);
        L_0x009a:
            com.inmobi.commons.core.configs.b.a;	 Catch:{ JSONException -> 0x00c9 }
            r0 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x00c9 }
            r1 = "Config cached successfully:";
            r0.<init>(r1);	 Catch:{ JSONException -> 0x00c9 }
            r1 = r6.b;	 Catch:{ JSONException -> 0x00c9 }
            r1 = r1.a();	 Catch:{ JSONException -> 0x00c9 }
            r0.append(r1);	 Catch:{ JSONException -> 0x00c9 }
            com.inmobi.commons.core.configs.b.a;	 Catch:{ JSONException -> 0x00c9 }
            r0 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x00c9 }
            r1 = "Config cached successfully:";
            r0.<init>(r1);	 Catch:{ JSONException -> 0x00c9 }
            r1 = r6.b;	 Catch:{ JSONException -> 0x00c9 }
            r1 = r1.b();	 Catch:{ JSONException -> 0x00c9 }
            r1 = r1.toString();	 Catch:{ JSONException -> 0x00c9 }
            r0.append(r1);	 Catch:{ JSONException -> 0x00c9 }
            r6 = r6.b;	 Catch:{ JSONException -> 0x00c9 }
            com.inmobi.commons.core.configs.b.b(r6);	 Catch:{ JSONException -> 0x00c9 }
        L_0x00c9:
            return;
        L_0x00ca:
            com.inmobi.commons.core.configs.b.a;
            r0 = new java.lang.StringBuilder;
            r1 = "Config fetching failed:";
            r0.<init>(r1);
            r1 = r6.b;
            r1 = r1.a();
            r0.append(r1);
            r1 = ", Error code:";
            r0.append(r1);
            r6 = r6.c;
            r6 = r6.a;
            r0.append(r6);
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.inmobi.commons.core.configs.b$b.a(com.inmobi.commons.core.configs.ConfigNetworkResponse$ConfigResponse):void");
        }

        public final void a() {
            sendEmptyMessage(4);
        }
    }

    static class d implements c {
        d() {
        }

        public final void a(a aVar) {
            b.c = (h) aVar;
        }
    }

    /* synthetic */ b(byte b) {
        this();
    }

    public static b a() {
        return a.a;
    }

    private b() {
        this.g = false;
        b = new HashMap();
        this.e = new HandlerThread("ConfigBootstrapHandler");
        this.e.start();
        this.f = new b(this.e.getLooper());
        c = new h();
    }

    public final synchronized void b() {
        if (!this.g) {
            this.g = true;
            com.inmobi.commons.core.e.b.a().a("root", c.f);
            if (d == null) {
                d = new d();
                a(c, d);
            }
            for (Entry key : b.entrySet()) {
                a aVar = (a) key.getKey();
                c(aVar);
                b(aVar);
            }
        }
    }

    public final synchronized void c() {
        if (this.g) {
            this.g = false;
            this.f.sendEmptyMessage(5);
        }
    }

    private static void b(a aVar) {
        ArrayList arrayList = (ArrayList) b.get(aVar);
        if (arrayList != null) {
            int i = 0;
            while (i < arrayList.size()) {
                if (!(arrayList.get(i) == null || ((WeakReference) arrayList.get(i)).get() == null)) {
                    ((c) ((WeakReference) arrayList.get(i)).get()).a(aVar);
                }
                i++;
            }
        }
    }

    public final synchronized void a(a aVar, c cVar) {
        if (this.g) {
            Object obj;
            ArrayList arrayList = (ArrayList) b.get(aVar);
            if (arrayList == null) {
                arrayList = new ArrayList();
            }
            if (cVar == null) {
                obj = null;
            } else {
                obj = new WeakReference(cVar);
            }
            arrayList.add(obj);
            b.put(aVar, arrayList);
            c(aVar);
            return;
        }
        new StringBuilder("Config component not yet started, config can't be fetched. Requested type:").append(aVar.a());
    }

    public static void d() {
        String str = c.e.a;
        String str2 = c.e.b;
        if (str.trim().length() != 0 && a("7.2.1", str.trim())) {
            InternalLogLevel internalLogLevel = InternalLogLevel.DEBUG;
            String str3 = a;
            StringBuilder stringBuilder = new StringBuilder("A newer version (version ");
            stringBuilder.append(str);
            stringBuilder.append(") of the InMobi SDK is available! You are currently on an older version (Version 7.2.1). Please download the latest InMobi SDK from ");
            stringBuilder.append(str2);
            Logger.a(internalLogLevel, str3, stringBuilder.toString());
        }
    }

    private synchronized void c(a aVar) {
        c cVar = new c();
        if (cVar.a("root")) {
            cVar.a(c);
            if (a(cVar.b("root"), c.a("root"))) {
                d(new h());
            }
            if (cVar.a(aVar.a())) {
                cVar.a(aVar);
                if (a(cVar.b(aVar.a()), c.a(aVar.a()))) {
                    new StringBuilder("Requested config expired. Returning currently cached and fetching. Config type:").append(aVar.a());
                    d(aVar.d());
                    return;
                }
                new StringBuilder("Serving config from cache. Config:").append(aVar.a());
                return;
            }
            new StringBuilder("Requested config not present. Returning default and fetching. Config type:").append(aVar.a());
            d(aVar.d());
            return;
        }
        new StringBuilder("RootConfig not available. Fetching root and returning defaults for config type:").append(aVar.a());
        d(new h());
    }

    private static boolean a(long j, long j2) {
        return System.currentTimeMillis() - j > j2 * 1000;
    }

    private void d(a aVar) {
        Message obtainMessage = this.f.obtainMessage();
        obtainMessage.what = 1;
        obtainMessage.obj = aVar;
        this.f.sendMessage(obtainMessage);
    }

    private static boolean a(String str, String str2) {
        String[] split = str.split("\\.");
        String[] split2 = str2.split("\\.");
        try {
            for (String valueOf : split) {
                if (Integer.valueOf(valueOf).intValue() < 0) {
                    return false;
                }
            }
            for (String valueOf2 : split2) {
                if (Integer.valueOf(valueOf2).intValue() < 0) {
                    return false;
                }
            }
            int length = split.length < split2.length ? split.length : split2.length;
            int i = 0;
            while (i < length) {
                if (split[i].equals(split2[i])) {
                    i++;
                } else if (Integer.valueOf(split[i]).intValue() < Integer.valueOf(split2[i]).intValue()) {
                    return true;
                } else {
                    return false;
                }
            }
            if (split.length < split2.length) {
                return true;
            }
            return false;
        } catch (NumberFormatException unused) {
            return false;
        }
    }
}
