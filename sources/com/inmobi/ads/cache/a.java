package com.inmobi.ads.cache;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Random;

public class a {
    private static final String m = "a";
    public long a = 0;
    int b;
    int c;
    public String d;
    public String e;
    long f;
    long g;
    public long h;
    long i;
    public boolean j;
    public String k;
    public int l;

    public static final class a {
        int a = (new Random().nextInt() & Integer.MAX_VALUE);
        int b;
        String c;
        String d;
        long e = System.currentTimeMillis();
        long f = System.currentTimeMillis();
        long g;
        long h;

        public final a a(String str, int i, long j) {
            this.c = str;
            this.b = i;
            this.g = System.currentTimeMillis() + j;
            return this;
        }

        private static long a(String str) {
            try {
                return new SimpleDateFormat("EEE,dd MMM yyyy HH:mm:ss z", Locale.ENGLISH).parse(str).getTime();
            } catch (ParseException e) {
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                return 0;
            }
        }

        @NonNull
        public final a a() {
            return new a(this.a, this.c, this.d, this.b, this.e, this.f, this.g, this.h);
        }

        /* JADX WARNING: Removed duplicated region for block: B:58:0x011a  */
        /* JADX WARNING: Removed duplicated region for block: B:71:0x014a  */
        /* JADX WARNING: Removed duplicated region for block: B:66:0x013c  */
        /* JADX WARNING: Removed duplicated region for block: B:50:0x00f3  */
        /* JADX WARNING: Removed duplicated region for block: B:58:0x011a  */
        /* JADX WARNING: Removed duplicated region for block: B:66:0x013c  */
        /* JADX WARNING: Removed duplicated region for block: B:71:0x014a  */
        /* JADX WARNING: Removed duplicated region for block: B:14:0x0052  */
        /* JADX WARNING: Removed duplicated region for block: B:50:0x00f3  */
        /* JADX WARNING: Removed duplicated region for block: B:58:0x011a  */
        /* JADX WARNING: Removed duplicated region for block: B:71:0x014a  */
        /* JADX WARNING: Removed duplicated region for block: B:66:0x013c  */
        public final com.inmobi.ads.cache.a.a a(java.lang.String r22, java.lang.String r23, com.inmobi.commons.core.network.d r24, int r25, long r26) {
            /*
            r21 = this;
            r1 = r21;
            r2 = r24;
            r2 = r2.d;
            r3 = java.lang.System.currentTimeMillis();
            r5 = "Date";
            r5 = r2.get(r5);
            r5 = (java.util.List) r5;
            r8 = 0;
            if (r5 == 0) goto L_0x0030;
        L_0x0015:
            r5 = r5.size();
            if (r5 <= 0) goto L_0x0030;
        L_0x001b:
            r5 = "Date";
            r5 = r2.get(r5);
            r5 = (java.util.List) r5;
            r5 = r5.get(r8);
            r5 = (java.lang.String) r5;
            if (r5 == 0) goto L_0x0030;
        L_0x002b:
            r9 = a(r5);
            goto L_0x0032;
        L_0x0030:
            r9 = 0;
        L_0x0032:
            r5 = "Cache-Control";
            r5 = r2.get(r5);
            r5 = (java.util.List) r5;
            if (r5 == 0) goto L_0x00cb;
        L_0x003c:
            r5 = r5.size();
            if (r5 <= 0) goto L_0x00cb;
        L_0x0042:
            r5 = "Cache-Control";
            r5 = r2.get(r5);
            r5 = (java.util.List) r5;
            r5 = r5.get(r8);
            r5 = (java.lang.String) r5;
            if (r5 == 0) goto L_0x00cb;
        L_0x0052:
            r12 = ",";
            r5 = r5.split(r12);
            r12 = r5.length;
            r13 = r8;
            r16 = r13;
            r14 = 0;
            r17 = 0;
        L_0x0060:
            if (r13 >= r12) goto L_0x00c8;
        L_0x0062:
            r11 = r5[r13];
            r11 = r11.trim();
            r6 = "no-cache";
            r6 = r11.equals(r6);
            if (r6 != 0) goto L_0x00c5;
        L_0x0070:
            r6 = "no-store";
            r6 = r11.equals(r6);
            if (r6 != 0) goto L_0x00c5;
        L_0x0078:
            r6 = "max-age=";
            r6 = r11.startsWith(r6);
            if (r6 == 0) goto L_0x0095;
        L_0x0080:
            r6 = 8;
            r6 = r11.substring(r6);	 Catch:{ Exception -> 0x008c }
            r6 = java.lang.Long.parseLong(r6);	 Catch:{ Exception -> 0x008c }
            r14 = r6;
            goto L_0x00c5;
        L_0x008c:
            r0 = move-exception;
            r6 = r0;
            com.inmobi.ads.cache.a.m;
            r6.getMessage();
            goto L_0x00c5;
        L_0x0095:
            r6 = "stale-while-revalidate=";
            r6 = r11.startsWith(r6);
            if (r6 == 0) goto L_0x00b3;
        L_0x009d:
            r6 = 23;
            r6 = r11.substring(r6);	 Catch:{ Exception -> 0x00aa }
            r6 = java.lang.Long.parseLong(r6);	 Catch:{ Exception -> 0x00aa }
            r17 = r6;
            goto L_0x00c5;
        L_0x00aa:
            r0 = move-exception;
            r6 = r0;
            com.inmobi.ads.cache.a.m;
            r6.getMessage();
            goto L_0x00c5;
        L_0x00b3:
            r6 = "must-revalidate";
            r6 = r11.equals(r6);
            if (r6 != 0) goto L_0x00c3;
        L_0x00bb:
            r6 = "proxy-revalidate";
            r6 = r11.equals(r6);
            if (r6 == 0) goto L_0x00c5;
        L_0x00c3:
            r16 = 1;
        L_0x00c5:
            r13 = r13 + 1;
            goto L_0x0060;
        L_0x00c8:
            r19 = 1;
            goto L_0x00d3;
        L_0x00cb:
            r16 = r8;
            r19 = r16;
            r14 = 0;
            r17 = 0;
        L_0x00d3:
            r5 = "Expires";
            r5 = r2.get(r5);
            r5 = (java.util.List) r5;
            if (r5 == 0) goto L_0x00f8;
        L_0x00dd:
            r5 = r5.size();
            if (r5 <= 0) goto L_0x00f8;
        L_0x00e3:
            r5 = "Expires";
            r5 = r2.get(r5);
            r5 = (java.util.List) r5;
            r5 = r5.get(r8);
            r5 = (java.lang.String) r5;
            if (r5 == 0) goto L_0x00f8;
        L_0x00f3:
            r6 = a(r5);
            goto L_0x00fa;
        L_0x00f8:
            r6 = 0;
        L_0x00fa:
            r5 = "Last-Modified";
            r5 = r2.get(r5);
            r5 = (java.util.List) r5;
            if (r5 == 0) goto L_0x011d;
        L_0x0104:
            r5 = r5.size();
            if (r5 <= 0) goto L_0x011d;
        L_0x010a:
            r5 = "Last-Modified";
            r5 = r2.get(r5);
            r5 = (java.util.List) r5;
            r5 = r5.get(r8);
            r5 = (java.lang.String) r5;
            if (r5 == 0) goto L_0x011d;
        L_0x011a:
            a(r5);
        L_0x011d:
            r5 = "ETag";
            r5 = r2.get(r5);
            r5 = (java.util.List) r5;
            if (r5 == 0) goto L_0x0138;
        L_0x0127:
            r5 = r5.size();
            if (r5 <= 0) goto L_0x0138;
        L_0x012d:
            r5 = "ETag";
            r2 = r2.get(r5);
            r2 = (java.util.List) r2;
            r2.get(r8);
        L_0x0138:
            r11 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
            if (r19 == 0) goto L_0x014a;
        L_0x013c:
            r14 = r14 * r11;
            r6 = r3 + r14;
            if (r16 == 0) goto L_0x0143;
        L_0x0141:
            r8 = r6;
            goto L_0x0147;
        L_0x0143:
            r17 = r17 * r11;
            r8 = r6 + r17;
        L_0x0147:
            r2 = r22;
            goto L_0x015f;
        L_0x014a:
            r13 = 0;
            r2 = (r9 > r13 ? 1 : (r9 == r13 ? 0 : -1));
            if (r2 <= 0) goto L_0x015b;
        L_0x0150:
            r2 = (r6 > r9 ? 1 : (r6 == r9 ? 0 : -1));
            if (r2 < 0) goto L_0x015b;
        L_0x0154:
            r13 = r6 - r9;
            r6 = r3 + r13;
            r2 = r22;
            goto L_0x015e;
        L_0x015b:
            r2 = r22;
            r6 = r13;
        L_0x015e:
            r8 = r6;
        L_0x015f:
            r1.c = r2;
            r2 = r23;
            r1.d = r2;
            r2 = r25;
            r1.b = r2;
            r10 = r26 * r11;
            r12 = r3 + r10;
            r1.g = r12;
            r1.h = r6;
            r2 = r1.g;
            r2 = java.lang.Math.min(r2, r8);
            r1.g = r2;
            return r1;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.cache.a$a.a(java.lang.String, java.lang.String, com.inmobi.commons.core.network.d, int, long):com.inmobi.ads.cache.a$a");
        }
    }

    a(int i, @NonNull String str, @Nullable String str2, int i2, long j, long j2, long j3, long j4) {
        this.b = i;
        this.d = str;
        this.e = str2;
        this.c = i2;
        this.f = j;
        this.g = j2;
        this.h = j3;
        this.i = j4;
        this.j = false;
        this.k = null;
    }

    public final boolean a() {
        return (this.e == null || this.e.length() == 0 || !new File(this.e).exists()) ? false : true;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.d.equals(((a) obj).d);
    }

    public int hashCode() {
        return this.d.hashCode();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("AdAsset{url='");
        stringBuilder.append(this.d);
        stringBuilder.append('\'');
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
