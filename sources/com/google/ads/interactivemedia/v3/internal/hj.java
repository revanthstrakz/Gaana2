package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public final class hj extends gp<Date> {
    public static final gq a = new gq() {
        public <T> gp<T> a(fz fzVar, hw<T> hwVar) {
            return hwVar.a() == Date.class ? new hj() : null;
        }
    };
    private final DateFormat b = DateFormat.getDateTimeInstance(2, 2, Locale.US);
    private final DateFormat c = DateFormat.getDateTimeInstance(2, 2);

    /* renamed from: a */
    public Date read(hx hxVar) throws IOException {
        if (hxVar.f() != hy.NULL) {
            return a(hxVar.h());
        }
        hxVar.j();
        return null;
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0013 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x000b */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:6|7|8|9) */
    /* JADX WARNING: Missing block: B:9:0x0012, code skipped:
            return r2.b.parse(r3);
     */
    /* JADX WARNING: Missing block: B:13:0x001e, code skipped:
            return com.google.ads.interactivemedia.v3.internal.hv.a(r3, new java.text.ParsePosition(0));
     */
    /* JADX WARNING: Missing block: B:14:0x001f, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:17:0x0025, code skipped:
            throw new com.google.ads.interactivemedia.v3.internal.gn(r3, r0);
     */
    private synchronized java.util.Date a(java.lang.String r3) {
        /*
        r2 = this;
        monitor-enter(r2);
        r0 = r2.c;	 Catch:{ ParseException -> 0x000b }
        r0 = r0.parse(r3);	 Catch:{ ParseException -> 0x000b }
        monitor-exit(r2);
        return r0;
    L_0x0009:
        r3 = move-exception;
        goto L_0x0026;
    L_0x000b:
        r0 = r2.b;	 Catch:{ ParseException -> 0x0013 }
        r0 = r0.parse(r3);	 Catch:{ ParseException -> 0x0013 }
        monitor-exit(r2);
        return r0;
    L_0x0013:
        r0 = new java.text.ParsePosition;	 Catch:{ ParseException -> 0x001f }
        r1 = 0;
        r0.<init>(r1);	 Catch:{ ParseException -> 0x001f }
        r0 = com.google.ads.interactivemedia.v3.internal.hv.a(r3, r0);	 Catch:{ ParseException -> 0x001f }
        monitor-exit(r2);
        return r0;
    L_0x001f:
        r0 = move-exception;
        r1 = new com.google.ads.interactivemedia.v3.internal.gn;	 Catch:{ all -> 0x0009 }
        r1.<init>(r3, r0);	 Catch:{ all -> 0x0009 }
        throw r1;	 Catch:{ all -> 0x0009 }
    L_0x0026:
        monitor-exit(r2);
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.hj.a(java.lang.String):java.util.Date");
    }

    /* renamed from: a */
    public synchronized void write(hz hzVar, Date date) throws IOException {
        if (date == null) {
            hzVar.f();
        } else {
            hzVar.b(this.b.format(date));
        }
    }
}
