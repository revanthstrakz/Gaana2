package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.text.TextUtils;
import com.facebook.ads.internal.o.c;
import com.facebook.ads.internal.s.c.a;
import java.util.Map;

public class n extends b {
    private static final String c = "n";
    private final a d;
    private final c e;
    private m f;
    private boolean g;

    public n(Context context, c cVar, a aVar, com.facebook.ads.internal.t.a aVar2, c cVar2) {
        super(context, cVar2, aVar2);
        this.e = cVar;
        this.d = aVar;
    }

    public void a(m mVar) {
        this.f = mVar;
    }

    /* Access modifiers changed, original: protected */
    public void a(Map<String, String> map) {
        if (this.f != null && !TextUtils.isEmpty(this.f.c())) {
            this.e.a(this.f.c(), map);
        }
    }

    /* JADX WARNING: Missing block: B:13:0x0028, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:15:0x002a, code skipped:
            return;
     */
    public synchronized void b() {
        /*
        r2 = this;
        monitor-enter(r2);
        r0 = r2.g;	 Catch:{ all -> 0x002b }
        if (r0 != 0) goto L_0x0029;
    L_0x0005:
        r0 = r2.f;	 Catch:{ all -> 0x002b }
        if (r0 != 0) goto L_0x000a;
    L_0x0009:
        goto L_0x0029;
    L_0x000a:
        r0 = 1;
        r2.g = r0;	 Catch:{ all -> 0x002b }
        r0 = r2.d;	 Catch:{ all -> 0x002b }
        if (r0 == 0) goto L_0x0027;
    L_0x0011:
        r0 = r2.f;	 Catch:{ all -> 0x002b }
        r0 = r0.e();	 Catch:{ all -> 0x002b }
        r0 = android.text.TextUtils.isEmpty(r0);	 Catch:{ all -> 0x002b }
        if (r0 != 0) goto L_0x0027;
    L_0x001d:
        r0 = r2.d;	 Catch:{ all -> 0x002b }
        r1 = new com.facebook.ads.internal.adapters.n$1;	 Catch:{ all -> 0x002b }
        r1.<init>();	 Catch:{ all -> 0x002b }
        r0.post(r1);	 Catch:{ all -> 0x002b }
    L_0x0027:
        monitor-exit(r2);
        return;
    L_0x0029:
        monitor-exit(r2);
        return;
    L_0x002b:
        r0 = move-exception;
        monitor-exit(r2);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.adapters.n.b():void");
    }
}
