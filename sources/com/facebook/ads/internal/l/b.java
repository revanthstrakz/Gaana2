package com.facebook.ads.internal.l;

import java.util.ArrayList;
import java.util.List;

public class b {
    private static final List<a> a = new ArrayList();

    /* JADX WARNING: Missing block: B:10:0x001c, code skipped:
            r0 = new org.json.JSONArray();
            r1 = r1.iterator();
     */
    /* JADX WARNING: Missing block: B:12:0x0029, code skipped:
            if (r1.hasNext() == false) goto L_0x0039;
     */
    /* JADX WARNING: Missing block: B:13:0x002b, code skipped:
            r0.put(((com.facebook.ads.internal.l.a) r1.next()).a());
     */
    /* JADX WARNING: Missing block: B:15:0x003d, code skipped:
            return r0.toString();
     */
    public static java.lang.String a() {
        /*
        r0 = a;
        monitor-enter(r0);
        r1 = a;	 Catch:{ all -> 0x003e }
        r1 = r1.isEmpty();	 Catch:{ all -> 0x003e }
        if (r1 == 0) goto L_0x000f;
    L_0x000b:
        r1 = "";
        monitor-exit(r0);	 Catch:{ all -> 0x003e }
        return r1;
    L_0x000f:
        r1 = new java.util.ArrayList;	 Catch:{ all -> 0x003e }
        r2 = a;	 Catch:{ all -> 0x003e }
        r1.<init>(r2);	 Catch:{ all -> 0x003e }
        r2 = a;	 Catch:{ all -> 0x003e }
        r2.clear();	 Catch:{ all -> 0x003e }
        monitor-exit(r0);	 Catch:{ all -> 0x003e }
        r0 = new org.json.JSONArray;
        r0.<init>();
        r1 = r1.iterator();
    L_0x0025:
        r2 = r1.hasNext();
        if (r2 == 0) goto L_0x0039;
    L_0x002b:
        r2 = r1.next();
        r2 = (com.facebook.ads.internal.l.a) r2;
        r2 = r2.a();
        r0.put(r2);
        goto L_0x0025;
    L_0x0039:
        r0 = r0.toString();
        return r0;
    L_0x003e:
        r1 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x003e }
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.l.b.a():java.lang.String");
    }

    public static void a(a aVar) {
        synchronized (a) {
            a.add(aVar);
        }
    }
}
