package com.android.volley.toolbox;

import com.android.volley.Request;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.e;
import com.android.volley.k;
import com.android.volley.l;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.impl.cookie.DateUtils;

public class a implements e {
    protected static final boolean a = l.b;
    private static int d = 3000;
    private static int e = 4096;
    protected final e b;
    protected final b c;

    public a(e eVar) {
        this(eVar, new b(e));
    }

    public a(e eVar, b bVar) {
        this.b = eVar;
        this.c = bVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:85:? A:{SYNTHETIC, Splitter:B:2:0x0012, ExcHandler: SocketTimeoutException (unused java.net.SocketTimeoutException)} */
    /* JADX WARNING: Removed duplicated region for block: B:83:? A:{SYNTHETIC, Splitter:B:2:0x0012, ExcHandler: ConnectTimeoutException (unused org.apache.http.conn.ConnectTimeoutException)} */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0181 A:{Splitter:B:2:0x0012, ExcHandler: MalformedURLException (r0_8 'e' java.lang.Throwable)} */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x017b A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0101  */
    /* JADX WARNING: Removed duplicated region for block: B:85:? A:{SYNTHETIC, Splitter:B:2:0x0012, ExcHandler: SocketTimeoutException (unused java.net.SocketTimeoutException)} */
    /* JADX WARNING: Removed duplicated region for block: B:83:? A:{SYNTHETIC, Splitter:B:2:0x0012, ExcHandler: ConnectTimeoutException (unused org.apache.http.conn.ConnectTimeoutException)} */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0181 A:{Splitter:B:2:0x0012, ExcHandler: MalformedURLException (r0_8 'e' java.lang.Throwable)} */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0101  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x017b A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:85:? A:{SYNTHETIC, Splitter:B:2:0x0012, ExcHandler: SocketTimeoutException (unused java.net.SocketTimeoutException)} */
    /* JADX WARNING: Removed duplicated region for block: B:83:? A:{SYNTHETIC, Splitter:B:2:0x0012, ExcHandler: ConnectTimeoutException (unused org.apache.http.conn.ConnectTimeoutException)} */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0181 A:{Splitter:B:2:0x0012, ExcHandler: MalformedURLException (r0_8 'e' java.lang.Throwable)} */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x017b A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0101  */
    /* JADX WARNING: Removed duplicated region for block: B:85:? A:{SYNTHETIC, Splitter:B:2:0x0012, ExcHandler: SocketTimeoutException (unused java.net.SocketTimeoutException)} */
    /* JADX WARNING: Removed duplicated region for block: B:83:? A:{SYNTHETIC, Splitter:B:2:0x0012, ExcHandler: ConnectTimeoutException (unused org.apache.http.conn.ConnectTimeoutException)} */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0181 A:{Splitter:B:2:0x0012, ExcHandler: MalformedURLException (r0_8 'e' java.lang.Throwable)} */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0101  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x017b A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:85:? A:{SYNTHETIC, Splitter:B:2:0x0012, ExcHandler: SocketTimeoutException (unused java.net.SocketTimeoutException)} */
    /* JADX WARNING: Removed duplicated region for block: B:83:? A:{SYNTHETIC, Splitter:B:2:0x0012, ExcHandler: ConnectTimeoutException (unused org.apache.http.conn.ConnectTimeoutException)} */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0181 A:{Splitter:B:2:0x0012, ExcHandler: MalformedURLException (r0_8 'e' java.lang.Throwable)} */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x017b A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0101  */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:15:0x0072, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:16:0x0073, code skipped:
            r1 = r0;
            r17 = null;
            r18 = r5;
     */
    /* JADX WARNING: Missing block: B:46:0x00e0, code skipped:
            r0 = e;
     */
    /* JADX WARNING: Missing block: B:47:0x00e2, code skipped:
            r0 = e;
     */
    /* JADX WARNING: Missing block: B:48:0x00e3, code skipped:
            r23 = r5;
     */
    /* JADX WARNING: Missing block: B:49:0x00e5, code skipped:
            r1 = r0;
            r17 = r22;
     */
    /* JADX WARNING: Missing block: B:50:0x00e9, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:51:0x00ea, code skipped:
            r23 = r5;
            r1 = r0;
            r17 = null;
     */
    /* JADX WARNING: Missing block: B:52:0x00ef, code skipped:
            r18 = r23;
     */
    /* JADX WARNING: Missing block: B:53:0x00f2, code skipped:
            r0 = e;
     */
    /* JADX WARNING: Missing block: B:54:0x00f3, code skipped:
            r18 = r1;
            r17 = null;
     */
    /* JADX WARNING: Missing block: B:59:0x0101, code skipped:
            r2 = r14.getStatusLine().getStatusCode();
     */
    /* JADX WARNING: Missing block: B:60:0x010b, code skipped:
            if (r2 == com.google.api.client.http.HttpStatusCodes.STATUS_CODE_MOVED_PERMANENTLY) goto L_0x0124;
     */
    /* JADX WARNING: Missing block: B:62:0x0110, code skipped:
            com.android.volley.l.c("Unexpected response code %d for %s", java.lang.Integer.valueOf(r2), r25.getUrl());
     */
    /* JADX WARNING: Missing block: B:63:0x0124, code skipped:
            com.android.volley.l.c("Request at %s has been redirected to %s", r25.getOriginUrl(), r25.getUrl());
     */
    /* JADX WARNING: Missing block: B:64:0x0137, code skipped:
            if (r17 != null) goto L_0x0139;
     */
    /* JADX WARNING: Missing block: B:65:0x0139, code skipped:
            r15 = new com.android.volley.g(r2, r17, r18, false, android.os.SystemClock.elapsedRealtime() - r9);
     */
    /* JADX WARNING: Missing block: B:66:0x014b, code skipped:
            if (r2 == 401) goto L_0x0169;
     */
    /* JADX WARNING: Missing block: B:69:0x0152, code skipped:
            if (r2 == com.google.api.client.http.HttpStatusCodes.STATUS_CODE_MOVED_PERMANENTLY) goto L_0x015d;
     */
    /* JADX WARNING: Missing block: B:72:0x015c, code skipped:
            throw new com.android.volley.ServerError(r15);
     */
    /* JADX WARNING: Missing block: B:73:0x015d, code skipped:
            a("redirect", r8, new com.android.volley.RedirectError(r15));
     */
    /* JADX WARNING: Missing block: B:74:0x0169, code skipped:
            a("auth", r8, new com.android.volley.AuthFailureError(r15));
     */
    /* JADX WARNING: Missing block: B:76:0x017a, code skipped:
            throw new com.android.volley.NetworkError(r1);
     */
    /* JADX WARNING: Missing block: B:78:0x0180, code skipped:
            throw new com.android.volley.NoConnectionError(r1);
     */
    /* JADX WARNING: Missing block: B:79:0x0181, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:80:0x0182, code skipped:
            r1 = r0;
            r3 = new java.lang.StringBuilder();
            r3.append("Bad URL ");
            r3.append(r25.getUrl());
     */
    /* JADX WARNING: Missing block: B:81:0x019d, code skipped:
            throw new java.lang.RuntimeException(r3.toString(), r1);
     */
    /* JADX WARNING: Missing block: B:82:0x019e, code skipped:
            a("connection", r8, new com.android.volley.TimeoutError());
     */
    /* JADX WARNING: Missing block: B:84:0x01aa, code skipped:
            a("socket", r8, new com.android.volley.TimeoutError());
     */
    public com.android.volley.g a(com.android.volley.Request<?> r25) throws com.android.volley.VolleyError {
        /*
        r24 = this;
        r7 = r24;
        r8 = r25;
        r9 = android.os.SystemClock.elapsedRealtime();
    L_0x0008:
        r1 = java.util.Collections.emptyMap();
        r2 = 0;
        r11 = 0;
        r12 = 302; // 0x12e float:4.23E-43 double:1.49E-321;
        r13 = 301; // 0x12d float:4.22E-43 double:1.487E-321;
        r3 = new java.util.HashMap;	 Catch:{ SocketTimeoutException -> 0x01aa, ConnectTimeoutException -> 0x019e, MalformedURLException -> 0x0181, IOException -> 0x00f8 }
        r3.<init>();	 Catch:{ SocketTimeoutException -> 0x01aa, ConnectTimeoutException -> 0x019e, MalformedURLException -> 0x0181, IOException -> 0x00f8 }
        r4 = r25.getCacheEntry();	 Catch:{ SocketTimeoutException -> 0x01aa, ConnectTimeoutException -> 0x019e, MalformedURLException -> 0x0181, IOException -> 0x00f8 }
        r7.a(r3, r4);	 Catch:{ SocketTimeoutException -> 0x01aa, ConnectTimeoutException -> 0x019e, MalformedURLException -> 0x0181, IOException -> 0x00f8 }
        r4 = r7.b;	 Catch:{ SocketTimeoutException -> 0x01aa, ConnectTimeoutException -> 0x019e, MalformedURLException -> 0x0181, IOException -> 0x00f8 }
        r14 = r4.performRequest(r8, r3);	 Catch:{ SocketTimeoutException -> 0x01aa, ConnectTimeoutException -> 0x019e, MalformedURLException -> 0x0181, IOException -> 0x00f8 }
        r6 = r14.getStatusLine();	 Catch:{ SocketTimeoutException -> 0x01aa, ConnectTimeoutException -> 0x019e, MalformedURLException -> 0x0181, IOException -> 0x00f2 }
        r15 = r6.getStatusCode();	 Catch:{ SocketTimeoutException -> 0x01aa, ConnectTimeoutException -> 0x019e, MalformedURLException -> 0x0181, IOException -> 0x00f2 }
        r3 = r14.getAllHeaders();	 Catch:{ SocketTimeoutException -> 0x01aa, ConnectTimeoutException -> 0x019e, MalformedURLException -> 0x0181, IOException -> 0x00f2 }
        r5 = a(r3);	 Catch:{ SocketTimeoutException -> 0x01aa, ConnectTimeoutException -> 0x019e, MalformedURLException -> 0x0181, IOException -> 0x00f2 }
        r1 = 304; // 0x130 float:4.26E-43 double:1.5E-321;
        if (r15 != r1) goto L_0x007a;
    L_0x0038:
        r1 = r25.getCacheEntry();	 Catch:{ SocketTimeoutException -> 0x01aa, ConnectTimeoutException -> 0x019e, MalformedURLException -> 0x0181, IOException -> 0x0072 }
        if (r1 != 0) goto L_0x0054;
    L_0x003e:
        r1 = new com.android.volley.g;	 Catch:{ SocketTimeoutException -> 0x01aa, ConnectTimeoutException -> 0x019e, MalformedURLException -> 0x0181, IOException -> 0x0072 }
        r17 = 304; // 0x130 float:4.26E-43 double:1.5E-321;
        r18 = 0;
        r20 = 1;
        r3 = android.os.SystemClock.elapsedRealtime();	 Catch:{ SocketTimeoutException -> 0x01aa, ConnectTimeoutException -> 0x019e, MalformedURLException -> 0x0181, IOException -> 0x0072 }
        r21 = r3 - r9;
        r16 = r1;
        r19 = r5;
        r16.<init>(r17, r18, r19, r20, r21);	 Catch:{ SocketTimeoutException -> 0x01aa, ConnectTimeoutException -> 0x019e, MalformedURLException -> 0x0181, IOException -> 0x0072 }
        return r1;
    L_0x0054:
        r3 = r1.g;	 Catch:{ SocketTimeoutException -> 0x01aa, ConnectTimeoutException -> 0x019e, MalformedURLException -> 0x0181, IOException -> 0x0072 }
        r3.putAll(r5);	 Catch:{ SocketTimeoutException -> 0x01aa, ConnectTimeoutException -> 0x019e, MalformedURLException -> 0x0181, IOException -> 0x0072 }
        r3 = new com.android.volley.g;	 Catch:{ SocketTimeoutException -> 0x01aa, ConnectTimeoutException -> 0x019e, MalformedURLException -> 0x0181, IOException -> 0x0072 }
        r16 = 304; // 0x130 float:4.26E-43 double:1.5E-321;
        r4 = r1.a;	 Catch:{ SocketTimeoutException -> 0x01aa, ConnectTimeoutException -> 0x019e, MalformedURLException -> 0x0181, IOException -> 0x0072 }
        r1 = r1.g;	 Catch:{ SocketTimeoutException -> 0x01aa, ConnectTimeoutException -> 0x019e, MalformedURLException -> 0x0181, IOException -> 0x0072 }
        r19 = 1;
        r17 = android.os.SystemClock.elapsedRealtime();	 Catch:{ SocketTimeoutException -> 0x01aa, ConnectTimeoutException -> 0x019e, MalformedURLException -> 0x0181, IOException -> 0x0072 }
        r20 = r17 - r9;
        r15 = r3;
        r17 = r4;
        r18 = r1;
        r15.<init>(r16, r17, r18, r19, r20);	 Catch:{ SocketTimeoutException -> 0x01aa, ConnectTimeoutException -> 0x019e, MalformedURLException -> 0x0181, IOException -> 0x0072 }
        return r3;
    L_0x0072:
        r0 = move-exception;
        r1 = r0;
        r17 = r2;
        r18 = r5;
        goto L_0x00ff;
    L_0x007a:
        if (r15 == r13) goto L_0x007e;
    L_0x007c:
        if (r15 != r12) goto L_0x0098;
    L_0x007e:
        r1 = "location";
        r1 = r5.get(r1);	 Catch:{ SocketTimeoutException -> 0x01aa, ConnectTimeoutException -> 0x019e, MalformedURLException -> 0x0181, IOException -> 0x00e9 }
        r1 = (java.lang.String) r1;	 Catch:{ SocketTimeoutException -> 0x01aa, ConnectTimeoutException -> 0x019e, MalformedURLException -> 0x0181, IOException -> 0x00e9 }
        r3 = "https";
        r3 = r1.contains(r3);	 Catch:{ SocketTimeoutException -> 0x01aa, ConnectTimeoutException -> 0x019e, MalformedURLException -> 0x0181, IOException -> 0x00e9 }
        if (r3 != 0) goto L_0x0095;
    L_0x008e:
        r3 = "http";
        r4 = "https";
        r1.replace(r3, r4);	 Catch:{ SocketTimeoutException -> 0x01aa, ConnectTimeoutException -> 0x019e, MalformedURLException -> 0x0181, IOException -> 0x0072 }
    L_0x0095:
        r8.setRedirectedUrl(r1);	 Catch:{ SocketTimeoutException -> 0x01aa, ConnectTimeoutException -> 0x019e, MalformedURLException -> 0x0181, IOException -> 0x00e9 }
    L_0x0098:
        r1 = r14.getEntity();	 Catch:{ SocketTimeoutException -> 0x01aa, ConnectTimeoutException -> 0x019e, MalformedURLException -> 0x0181, IOException -> 0x00e9 }
        if (r1 == 0) goto L_0x00a7;
    L_0x009e:
        r1 = r14.getEntity();	 Catch:{ SocketTimeoutException -> 0x01aa, ConnectTimeoutException -> 0x019e, MalformedURLException -> 0x0181, IOException -> 0x0072 }
        r1 = r7.a(r1);	 Catch:{ SocketTimeoutException -> 0x01aa, ConnectTimeoutException -> 0x019e, MalformedURLException -> 0x0181, IOException -> 0x0072 }
        goto L_0x00a9;
    L_0x00a7:
        r1 = new byte[r11];	 Catch:{ SocketTimeoutException -> 0x01aa, ConnectTimeoutException -> 0x019e, MalformedURLException -> 0x0181, IOException -> 0x00e9 }
    L_0x00a9:
        r22 = r1;
        r1 = android.os.SystemClock.elapsedRealtime();	 Catch:{ SocketTimeoutException -> 0x01aa, ConnectTimeoutException -> 0x019e, MalformedURLException -> 0x0181, IOException -> 0x00e2 }
        r3 = r1 - r9;
        r1 = r7;
        r2 = r3;
        r4 = r8;
        r23 = r5;
        r5 = r22;
        r1.a(r2, r4, r5, r6);	 Catch:{ SocketTimeoutException -> 0x01aa, ConnectTimeoutException -> 0x019e, MalformedURLException -> 0x0181, IOException -> 0x00e0 }
        r1 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r15 < r1) goto L_0x00da;
    L_0x00bf:
        r1 = 299; // 0x12b float:4.19E-43 double:1.477E-321;
        if (r15 <= r1) goto L_0x00c4;
    L_0x00c3:
        goto L_0x00da;
    L_0x00c4:
        r1 = new com.android.volley.g;	 Catch:{ SocketTimeoutException -> 0x01aa, ConnectTimeoutException -> 0x019e, MalformedURLException -> 0x0181, IOException -> 0x00e0 }
        r19 = 0;
        r2 = android.os.SystemClock.elapsedRealtime();	 Catch:{ SocketTimeoutException -> 0x01aa, ConnectTimeoutException -> 0x019e, MalformedURLException -> 0x0181, IOException -> 0x00e0 }
        r20 = r2 - r9;
        r2 = r15;
        r15 = r1;
        r16 = r2;
        r17 = r22;
        r18 = r23;
        r15.<init>(r16, r17, r18, r19, r20);	 Catch:{ SocketTimeoutException -> 0x01aa, ConnectTimeoutException -> 0x019e, MalformedURLException -> 0x0181, IOException -> 0x00e0 }
        return r1;
    L_0x00da:
        r1 = new java.io.IOException;	 Catch:{ SocketTimeoutException -> 0x01aa, ConnectTimeoutException -> 0x019e, MalformedURLException -> 0x0181, IOException -> 0x00e0 }
        r1.<init>();	 Catch:{ SocketTimeoutException -> 0x01aa, ConnectTimeoutException -> 0x019e, MalformedURLException -> 0x0181, IOException -> 0x00e0 }
        throw r1;	 Catch:{ SocketTimeoutException -> 0x01aa, ConnectTimeoutException -> 0x019e, MalformedURLException -> 0x0181, IOException -> 0x00e0 }
    L_0x00e0:
        r0 = move-exception;
        goto L_0x00e5;
    L_0x00e2:
        r0 = move-exception;
        r23 = r5;
    L_0x00e5:
        r1 = r0;
        r17 = r22;
        goto L_0x00ef;
    L_0x00e9:
        r0 = move-exception;
        r23 = r5;
        r1 = r0;
        r17 = r2;
    L_0x00ef:
        r18 = r23;
        goto L_0x00ff;
    L_0x00f2:
        r0 = move-exception;
        r18 = r1;
        r17 = r2;
        goto L_0x00fe;
    L_0x00f8:
        r0 = move-exception;
        r18 = r1;
        r14 = r2;
        r17 = r14;
    L_0x00fe:
        r1 = r0;
    L_0x00ff:
        if (r14 == 0) goto L_0x017b;
    L_0x0101:
        r2 = r14.getStatusLine();
        r2 = r2.getStatusCode();
        r3 = 1;
        r4 = 2;
        if (r2 == r13) goto L_0x0124;
    L_0x010d:
        if (r2 != r12) goto L_0x0110;
    L_0x010f:
        goto L_0x0124;
    L_0x0110:
        r5 = "Unexpected response code %d for %s";
        r4 = new java.lang.Object[r4];
        r6 = java.lang.Integer.valueOf(r2);
        r4[r11] = r6;
        r6 = r25.getUrl();
        r4[r3] = r6;
        com.android.volley.l.c(r5, r4);
        goto L_0x0137;
    L_0x0124:
        r5 = "Request at %s has been redirected to %s";
        r4 = new java.lang.Object[r4];
        r6 = r25.getOriginUrl();
        r4[r11] = r6;
        r6 = r25.getUrl();
        r4[r3] = r6;
        com.android.volley.l.c(r5, r4);
    L_0x0137:
        if (r17 == 0) goto L_0x0175;
    L_0x0139:
        r1 = new com.android.volley.g;
        r19 = 0;
        r3 = android.os.SystemClock.elapsedRealtime();
        r20 = r3 - r9;
        r15 = r1;
        r16 = r2;
        r15.<init>(r16, r17, r18, r19, r20);
        r3 = 401; // 0x191 float:5.62E-43 double:1.98E-321;
        if (r2 == r3) goto L_0x0169;
    L_0x014d:
        r3 = 403; // 0x193 float:5.65E-43 double:1.99E-321;
        if (r2 != r3) goto L_0x0152;
    L_0x0151:
        goto L_0x0169;
    L_0x0152:
        if (r2 == r13) goto L_0x015d;
    L_0x0154:
        if (r2 != r12) goto L_0x0157;
    L_0x0156:
        goto L_0x015d;
    L_0x0157:
        r2 = new com.android.volley.ServerError;
        r2.<init>(r1);
        throw r2;
    L_0x015d:
        r2 = "redirect";
        r3 = new com.android.volley.RedirectError;
        r3.<init>(r1);
        a(r2, r8, r3);
        goto L_0x0008;
    L_0x0169:
        r2 = "auth";
        r3 = new com.android.volley.AuthFailureError;
        r3.<init>(r1);
        a(r2, r8, r3);
        goto L_0x0008;
    L_0x0175:
        r2 = new com.android.volley.NetworkError;
        r2.<init>(r1);
        throw r2;
    L_0x017b:
        r2 = new com.android.volley.NoConnectionError;
        r2.<init>(r1);
        throw r2;
    L_0x0181:
        r0 = move-exception;
        r1 = r0;
        r2 = new java.lang.RuntimeException;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "Bad URL ";
        r3.append(r4);
        r4 = r25.getUrl();
        r3.append(r4);
        r3 = r3.toString();
        r2.<init>(r3, r1);
        throw r2;
    L_0x019e:
        r1 = "connection";
        r2 = new com.android.volley.TimeoutError;
        r2.<init>();
        a(r1, r8, r2);
        goto L_0x0008;
    L_0x01aa:
        r1 = "socket";
        r2 = new com.android.volley.TimeoutError;
        r2.<init>();
        a(r1, r8, r2);
        goto L_0x0008;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.volley.toolbox.a.a(com.android.volley.Request):com.android.volley.g");
    }

    private void a(long j, Request<?> request, byte[] bArr, StatusLine statusLine) {
        if (a || j > ((long) d)) {
            String str = "HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]";
            Object[] objArr = new Object[5];
            objArr[0] = request;
            objArr[1] = Long.valueOf(j);
            objArr[2] = bArr != null ? Integer.valueOf(bArr.length) : "null";
            objArr[3] = Integer.valueOf(statusLine.getStatusCode());
            objArr[4] = Integer.valueOf(request.getRetryPolicy().b());
            l.b(str, objArr);
        }
    }

    private static void a(String str, Request<?> request, VolleyError volleyError) throws VolleyError {
        k retryPolicy = request.getRetryPolicy();
        int timeoutMs = request.getTimeoutMs();
        try {
            retryPolicy.a(volleyError);
            request.addMarker(String.format("%s-retry [timeout=%s]", new Object[]{str, Integer.valueOf(timeoutMs)}));
        } catch (VolleyError volleyError2) {
            request.addMarker(String.format("%s-timeout-giveup [timeout=%s]", new Object[]{str, Integer.valueOf(timeoutMs)}));
            throw volleyError2;
        }
    }

    private void a(Map<String, String> map, com.android.volley.a.a aVar) {
        if (aVar != null) {
            if (aVar.b != null) {
                map.put("If-None-Match", aVar.b);
            }
            if (aVar.d > 0) {
                map.put("If-Modified-Since", DateUtils.formatDate(new Date(aVar.d)));
            }
        }
    }

    private byte[] a(HttpEntity httpEntity) throws IOException, ServerError {
        Throwable th;
        l lVar = new l(this.c, (int) httpEntity.getContentLength());
        byte[] bArr = null;
        try {
            InputStream content = httpEntity.getContent();
            if (content == null) {
                throw new ServerError();
            }
            byte[] a = this.c.a(1024);
            while (true) {
                try {
                    int read = content.read(a);
                    if (read == -1) {
                        break;
                    }
                    lVar.write(a, 0, read);
                } catch (Throwable th2) {
                    th = th2;
                    bArr = a;
                    try {
                        httpEntity.consumeContent();
                    } catch (IOException unused) {
                        l.a("Error occured when calling consumingContent", new Object[0]);
                    }
                    this.c.a(bArr);
                    lVar.close();
                    throw th;
                }
            }
            bArr = lVar.toByteArray();
            try {
                httpEntity.consumeContent();
            } catch (IOException unused2) {
                l.a("Error occured when calling consumingContent", new Object[0]);
            }
            this.c.a(a);
            lVar.close();
            return bArr;
        } catch (Throwable th3) {
            th = th3;
            httpEntity.consumeContent();
            this.c.a(bArr);
            lVar.close();
            throw th;
        }
    }

    protected static Map<String, String> a(Header[] headerArr) {
        TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < headerArr.length; i++) {
            treeMap.put(headerArr[i].getName(), headerArr[i].getValue());
        }
        return treeMap;
    }
}
