package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

final class fu implements ge<Date>, gm<Date> {
    private final DateFormat a;
    private final DateFormat b;

    fu() {
        this(DateFormat.getDateTimeInstance(2, 2, Locale.US), DateFormat.getDateTimeInstance(2, 2));
    }

    fu(String str) {
        this(new SimpleDateFormat(str, Locale.US), new SimpleDateFormat(str));
    }

    public fu(int i, int i2) {
        this(DateFormat.getDateTimeInstance(i, i2, Locale.US), DateFormat.getDateTimeInstance(i, i2));
    }

    fu(DateFormat dateFormat, DateFormat dateFormat2) {
        this.a = dateFormat;
        this.b = dateFormat2;
    }

    public gf a(Date date, Type type, gl glVar) {
        gk gkVar;
        synchronized (this.b) {
            gkVar = new gk(this.a.format(date));
        }
        return gkVar;
    }

    /* renamed from: a */
    public Date b(gf gfVar, Type type, gd gdVar) throws gj {
        if (gfVar instanceof gk) {
            Date a = a(gfVar);
            if (type == Date.class) {
                return a;
            }
            if (type == Timestamp.class) {
                return new Timestamp(a.getTime());
            }
            if (type == java.sql.Date.class) {
                return new java.sql.Date(a.getTime());
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(getClass());
            stringBuilder.append(" cannot deserialize to ");
            stringBuilder.append(type);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
        throw new gj("The date should be a string value");
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0011 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x001d */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:8|9|10|11|12) */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:13|14|15|16|17) */
    /* JADX WARNING: Missing block: B:9:?, code skipped:
            r1 = r4.a.parse(r5.b());
     */
    /* JADX WARNING: Missing block: B:12:0x001c, code skipped:
            return r1;
     */
    /* JADX WARNING: Missing block: B:14:?, code skipped:
            r1 = com.google.ads.interactivemedia.v3.internal.hv.a(r5.b(), new java.text.ParsePosition(0));
     */
    /* JADX WARNING: Missing block: B:17:0x002c, code skipped:
            return r1;
     */
    /* JADX WARNING: Missing block: B:18:0x002d, code skipped:
            r1 = move-exception;
     */
    /* JADX WARNING: Missing block: B:20:0x0037, code skipped:
            throw new com.google.ads.interactivemedia.v3.internal.gn(r5.b(), r1);
     */
    private java.util.Date a(com.google.ads.interactivemedia.v3.internal.gf r5) {
        /*
        r4 = this;
        r0 = r4.b;
        monitor-enter(r0);
        r1 = r4.b;	 Catch:{ ParseException -> 0x0011 }
        r2 = r5.b();	 Catch:{ ParseException -> 0x0011 }
        r1 = r1.parse(r2);	 Catch:{ ParseException -> 0x0011 }
        monitor-exit(r0);	 Catch:{ all -> 0x000f }
        return r1;
    L_0x000f:
        r5 = move-exception;
        goto L_0x0038;
    L_0x0011:
        r1 = r4.a;	 Catch:{ ParseException -> 0x001d }
        r2 = r5.b();	 Catch:{ ParseException -> 0x001d }
        r1 = r1.parse(r2);	 Catch:{ ParseException -> 0x001d }
        monitor-exit(r0);	 Catch:{ all -> 0x000f }
        return r1;
    L_0x001d:
        r1 = r5.b();	 Catch:{ ParseException -> 0x002d }
        r2 = new java.text.ParsePosition;	 Catch:{ ParseException -> 0x002d }
        r3 = 0;
        r2.<init>(r3);	 Catch:{ ParseException -> 0x002d }
        r1 = com.google.ads.interactivemedia.v3.internal.hv.a(r1, r2);	 Catch:{ ParseException -> 0x002d }
        monitor-exit(r0);	 Catch:{ all -> 0x000f }
        return r1;
    L_0x002d:
        r1 = move-exception;
        r2 = new com.google.ads.interactivemedia.v3.internal.gn;	 Catch:{ all -> 0x000f }
        r5 = r5.b();	 Catch:{ all -> 0x000f }
        r2.<init>(r5, r1);	 Catch:{ all -> 0x000f }
        throw r2;	 Catch:{ all -> 0x000f }
    L_0x0038:
        monitor-exit(r0);	 Catch:{ all -> 0x000f }
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.fu.a(com.google.ads.interactivemedia.v3.internal.gf):java.util.Date");
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(fu.class.getSimpleName());
        stringBuilder.append('(');
        stringBuilder.append(this.b.getClass().getSimpleName());
        stringBuilder.append(')');
        return stringBuilder.toString();
    }
}
