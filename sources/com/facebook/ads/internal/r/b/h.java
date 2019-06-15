package com.facebook.ads.internal.r.b;

import android.text.TextUtils;
import android.util.Log;
import com.google.api.client.http.HttpStatusCodes;
import com.til.colombia.android.internal.e;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class h implements n {
    public final String a;
    private HttpURLConnection b;
    private InputStream c;
    private volatile int d;
    private volatile String e;

    public h(h hVar) {
        this.d = Integer.MIN_VALUE;
        this.a = hVar.a;
        this.e = hVar.e;
        this.d = hVar.d;
    }

    public h(String str) {
        this(str, m.a(str));
    }

    public h(String str, String str2) {
        this.d = Integer.MIN_VALUE;
        this.a = (String) j.a(str);
        this.e = str2;
    }

    private int a(HttpURLConnection httpURLConnection, int i, int i2) {
        int contentLength = httpURLConnection.getContentLength();
        return i2 == 200 ? contentLength : i2 == 206 ? contentLength + i : this.d;
    }

    private HttpURLConnection a(int i, int i2) {
        HttpURLConnection httpURLConnection;
        String str = this.a;
        int i3 = 0;
        Object obj;
        do {
            StringBuilder stringBuilder;
            String stringBuilder2;
            String str2 = "ProxyCache";
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("Open connection ");
            if (i > 0) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(" with offset ");
                stringBuilder.append(i);
                stringBuilder2 = stringBuilder.toString();
            } else {
                stringBuilder2 = "";
            }
            stringBuilder3.append(stringBuilder2);
            stringBuilder3.append(" to ");
            stringBuilder3.append(str);
            Log.d(str2, stringBuilder3.toString());
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            if (i > 0) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("bytes=");
                stringBuilder.append(i);
                stringBuilder.append("-");
                httpURLConnection.setRequestProperty("Range", stringBuilder.toString());
            }
            if (i2 > 0) {
                httpURLConnection.setConnectTimeout(i2);
                httpURLConnection.setReadTimeout(i2);
            }
            int responseCode = httpURLConnection.getResponseCode();
            obj = (responseCode == HttpStatusCodes.STATUS_CODE_MOVED_PERMANENTLY || responseCode == HttpStatusCodes.STATUS_CODE_FOUND || responseCode == HttpStatusCodes.STATUS_CODE_SEE_OTHER) ? 1 : null;
            if (obj != null) {
                str = httpURLConnection.getHeaderField(e.e);
                i3++;
                httpURLConnection.disconnect();
            }
            if (i3 > 5) {
                StringBuilder stringBuilder4 = new StringBuilder();
                stringBuilder4.append("Too many redirects: ");
                stringBuilder4.append(i3);
                throw new l(stringBuilder4.toString());
            }
        } while (obj != null);
        return httpURLConnection;
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x009e  */
    /* JADX WARNING: Removed duplicated region for block: B:30:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x009e  */
    private void d() {
        /*
        r7 = this;
        r0 = "ProxyCache";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Read content info from ";
        r1.append(r2);
        r2 = r7.a;
        r1.append(r2);
        r1 = r1.toString();
        android.util.Log.d(r0, r1);
        r0 = 0;
        r1 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r2 = 0;
        r0 = r7.a(r0, r1);	 Catch:{ IOException -> 0x0073, all -> 0x006e }
        r1 = r0.getContentLength();	 Catch:{ IOException -> 0x0069, all -> 0x0064 }
        r7.d = r1;	 Catch:{ IOException -> 0x0069, all -> 0x0064 }
        r1 = r0.getContentType();	 Catch:{ IOException -> 0x0069, all -> 0x0064 }
        r7.e = r1;	 Catch:{ IOException -> 0x0069, all -> 0x0064 }
        r1 = r0.getInputStream();	 Catch:{ IOException -> 0x0069, all -> 0x0064 }
        r2 = "ProxyCache";
        r3 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0062 }
        r3.<init>();	 Catch:{ IOException -> 0x0062 }
        r4 = "Content info for `";
        r3.append(r4);	 Catch:{ IOException -> 0x0062 }
        r4 = r7.a;	 Catch:{ IOException -> 0x0062 }
        r3.append(r4);	 Catch:{ IOException -> 0x0062 }
        r4 = "`: mime: ";
        r3.append(r4);	 Catch:{ IOException -> 0x0062 }
        r4 = r7.e;	 Catch:{ IOException -> 0x0062 }
        r3.append(r4);	 Catch:{ IOException -> 0x0062 }
        r4 = ", content-length: ";
        r3.append(r4);	 Catch:{ IOException -> 0x0062 }
        r4 = r7.d;	 Catch:{ IOException -> 0x0062 }
        r3.append(r4);	 Catch:{ IOException -> 0x0062 }
        r3 = r3.toString();	 Catch:{ IOException -> 0x0062 }
        android.util.Log.i(r2, r3);	 Catch:{ IOException -> 0x0062 }
        com.facebook.ads.internal.r.b.m.a(r1);
        if (r0 == 0) goto L_0x0097;
    L_0x0061:
        goto L_0x0094;
    L_0x0062:
        r2 = move-exception;
        goto L_0x0077;
    L_0x0064:
        r1 = move-exception;
        r6 = r2;
        r2 = r1;
        r1 = r6;
        goto L_0x0099;
    L_0x0069:
        r1 = move-exception;
        r6 = r2;
        r2 = r1;
        r1 = r6;
        goto L_0x0077;
    L_0x006e:
        r0 = move-exception;
        r1 = r2;
        r2 = r0;
        r0 = r1;
        goto L_0x0099;
    L_0x0073:
        r0 = move-exception;
        r1 = r2;
        r2 = r0;
        r0 = r1;
    L_0x0077:
        r3 = "ProxyCache";
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0098 }
        r4.<init>();	 Catch:{ all -> 0x0098 }
        r5 = "Error fetching info from ";
        r4.append(r5);	 Catch:{ all -> 0x0098 }
        r5 = r7.a;	 Catch:{ all -> 0x0098 }
        r4.append(r5);	 Catch:{ all -> 0x0098 }
        r4 = r4.toString();	 Catch:{ all -> 0x0098 }
        android.util.Log.e(r3, r4, r2);	 Catch:{ all -> 0x0098 }
        com.facebook.ads.internal.r.b.m.a(r1);
        if (r0 == 0) goto L_0x0097;
    L_0x0094:
        r0.disconnect();
    L_0x0097:
        return;
    L_0x0098:
        r2 = move-exception;
    L_0x0099:
        com.facebook.ads.internal.r.b.m.a(r1);
        if (r0 == 0) goto L_0x00a1;
    L_0x009e:
        r0.disconnect();
    L_0x00a1:
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.r.b.h.d():void");
    }

    public synchronized int a() {
        if (this.d == Integer.MIN_VALUE) {
            d();
        }
        return this.d;
    }

    public int a(byte[] bArr) {
        StringBuilder stringBuilder;
        if (this.c == null) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Error reading data from ");
            stringBuilder2.append(this.a);
            stringBuilder2.append(": connection is absent!");
            throw new l(stringBuilder2.toString());
        }
        try {
            return this.c.read(bArr, 0, bArr.length);
        } catch (InterruptedIOException e) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Reading source ");
            stringBuilder.append(this.a);
            stringBuilder.append(" is interrupted");
            throw new i(stringBuilder.toString(), e);
        } catch (IOException e2) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Error reading data from ");
            stringBuilder.append(this.a);
            throw new l(stringBuilder.toString(), e2);
        }
    }

    public void a(int i) {
        try {
            this.b = a(i, -1);
            this.e = this.b.getContentType();
            this.c = new BufferedInputStream(this.b.getInputStream(), 8192);
            this.d = a(this.b, i, this.b.getResponseCode());
        } catch (IOException e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Error opening connection for ");
            stringBuilder.append(this.a);
            stringBuilder.append(" with offset ");
            stringBuilder.append(i);
            throw new l(stringBuilder.toString(), e);
        }
    }

    public void b() {
        if (this.b != null) {
            try {
                this.b.disconnect();
            } catch (NullPointerException e) {
                throw new l("Error disconnecting HttpUrlConnection", e);
            }
        }
    }

    public synchronized String c() {
        if (TextUtils.isEmpty(this.e)) {
            d();
        }
        return this.e;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("HttpUrlSource{url='");
        stringBuilder.append(this.a);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
