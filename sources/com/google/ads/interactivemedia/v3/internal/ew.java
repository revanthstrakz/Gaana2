package com.google.ads.interactivemedia.v3.internal;

import android.util.Log;
import com.google.ads.interactivemedia.v3.internal.ez.a;
import com.google.ads.interactivemedia.v3.internal.ez.b;
import com.google.ads.interactivemedia.v3.internal.ez.c;
import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.HttpStatusCodes;
import com.til.colombia.android.internal.e;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.NoRouteToHostException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

public class ew implements ez {
    private static final Pattern b = Pattern.compile("^bytes (\\d+)-(\\d+)/(\\d+)$");
    private static final AtomicReference<byte[]> c = new AtomicReference();
    private final boolean d;
    private final int e;
    private final int f;
    private final String g;
    private final fq<String> h;
    private final HashMap<String, String> i = new HashMap();
    private final fb j;
    private eu k;
    private HttpURLConnection l;
    private InputStream m;
    private boolean n;
    private long o;
    private long p;
    private long q;
    private long r;

    public ew(String str, fq<String> fqVar, fb fbVar, int i, int i2, boolean z) {
        this.g = fe.a(str);
        this.h = fqVar;
        this.j = fbVar;
        this.e = i;
        this.f = i2;
        this.d = z;
    }

    public long a(eu euVar) throws a {
        String str;
        String valueOf;
        this.k = euVar;
        long j = 0;
        this.r = 0;
        this.q = 0;
        try {
            this.l = b(euVar);
            try {
                int responseCode = this.l.getResponseCode();
                if (responseCode < 200 || responseCode > 299) {
                    Map headerFields = this.l.getHeaderFields();
                    d();
                    throw new c(responseCode, headerFields, euVar);
                }
                String contentType = this.l.getContentType();
                if (this.h == null || this.h.a(contentType)) {
                    if (responseCode == 200 && euVar.d != 0) {
                        j = euVar.d;
                    }
                    this.o = j;
                    if ((euVar.g & 1) == 0) {
                        j = a(this.l);
                        long j2 = -1;
                        if (euVar.e != -1) {
                            j2 = euVar.e;
                        } else if (j != -1) {
                            j2 = j - this.o;
                        }
                        this.p = j2;
                    } else {
                        this.p = euVar.e;
                    }
                    try {
                        this.m = this.l.getInputStream();
                        this.n = true;
                        if (this.j != null) {
                            this.j.a();
                        }
                        return this.p;
                    } catch (IOException e) {
                        d();
                        throw new a(e, euVar, 1);
                    }
                }
                d();
                throw new b(contentType, euVar);
            } catch (IOException e2) {
                d();
                str = "Unable to connect to ";
                valueOf = String.valueOf(euVar.a.toString());
                throw new a(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), e2, euVar, 1);
            }
        } catch (IOException e22) {
            str = "Unable to connect to ";
            valueOf = String.valueOf(euVar.a.toString());
            throw new a(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), e22, euVar, 1);
        }
    }

    public int a(byte[] bArr, int i, int i2) throws a {
        try {
            c();
            return b(bArr, i, i2);
        } catch (IOException e) {
            throw new a(e, this.k, 2);
        }
    }

    public void a() throws a {
        try {
            if (this.m != null) {
                ft.a(this.l, b());
                this.m.close();
            }
            this.m = null;
            d();
            if (this.n) {
                this.n = false;
                if (this.j != null) {
                    this.j.b();
                }
            }
        } catch (IOException e) {
            throw new a(e, this.k, 3);
        } catch (Throwable th) {
            this.m = null;
            d();
            if (this.n) {
                this.n = false;
                if (this.j != null) {
                    this.j.b();
                }
            }
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final long b() {
        return this.p == -1 ? this.p : this.p - this.r;
    }

    private HttpURLConnection b(eu euVar) throws IOException {
        ew ewVar;
        boolean z;
        eu euVar2 = euVar;
        URL url = new URL(euVar2.a.toString());
        byte[] bArr = euVar2.b;
        long j = euVar2.d;
        long j2 = euVar2.e;
        int i = 0;
        if ((euVar2.g & 1) != 0) {
            ewVar = this;
            z = true;
        } else {
            ewVar = this;
            z = false;
        }
        if (!ewVar.d) {
            return ewVar.a(url, bArr, j, j2, z, true);
        }
        HttpURLConnection a;
        while (true) {
            int i2 = i + 1;
            if (i <= 20) {
                a = ewVar.a(url, bArr, j, j2, z, false);
                i = a.getResponseCode();
                if (i == HttpStatusCodes.STATUS_CODE_MULTIPLE_CHOICES || i == HttpStatusCodes.STATUS_CODE_MOVED_PERMANENTLY || i == HttpStatusCodes.STATUS_CODE_FOUND || i == HttpStatusCodes.STATUS_CODE_SEE_OTHER || (bArr == null && (i == HttpStatusCodes.STATUS_CODE_TEMPORARY_REDIRECT || i == 308))) {
                    bArr = null;
                    String headerField = a.getHeaderField(e.e);
                    a.disconnect();
                    url = a(url, headerField);
                    ewVar = this;
                    i = i2;
                }
            } else {
                StringBuilder stringBuilder = new StringBuilder(31);
                stringBuilder.append("Too many redirects: ");
                stringBuilder.append(i2);
                throw new NoRouteToHostException(stringBuilder.toString());
            }
        }
        return a;
    }

    private HttpURLConnection a(URL url, byte[] bArr, long j, long j2, boolean z, boolean z2) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setConnectTimeout(this.e);
        httpURLConnection.setReadTimeout(this.f);
        synchronized (this.i) {
            for (Entry entry : this.i.entrySet()) {
                httpURLConnection.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
            }
        }
        if (!(j == 0 && j2 == -1)) {
            StringBuilder stringBuilder = new StringBuilder(27);
            stringBuilder.append("bytes=");
            stringBuilder.append(j);
            stringBuilder.append("-");
            String stringBuilder2 = stringBuilder.toString();
            if (j2 != -1) {
                String valueOf = String.valueOf(stringBuilder2);
                j2 = (j + j2) - 1;
                StringBuilder stringBuilder3 = new StringBuilder(20 + String.valueOf(valueOf).length());
                stringBuilder3.append(valueOf);
                stringBuilder3.append(j2);
                stringBuilder2 = stringBuilder3.toString();
            }
            httpURLConnection.setRequestProperty("Range", stringBuilder2);
        }
        httpURLConnection.setRequestProperty(e.c, this.g);
        if (!z) {
            httpURLConnection.setRequestProperty("Accept-Encoding", "identity");
        }
        httpURLConnection.setInstanceFollowRedirects(z2);
        httpURLConnection.setDoOutput(bArr != null);
        if (bArr != null) {
            httpURLConnection.setRequestMethod(HttpMethods.POST);
            if (bArr.length == 0) {
                httpURLConnection.connect();
            } else {
                httpURLConnection.setFixedLengthStreamingMode(bArr.length);
                httpURLConnection.connect();
                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(bArr);
                outputStream.close();
            }
        } else {
            httpURLConnection.connect();
        }
        return httpURLConnection;
    }

    private static URL a(URL url, String str) throws IOException {
        if (str == null) {
            throw new ProtocolException("Null location redirect");
        }
        URL url2 = new URL(url, str);
        String protocol = url2.getProtocol();
        if ("https".equals(protocol) || "http".equals(protocol)) {
            return url2;
        }
        String str2 = "Unsupported protocol redirect: ";
        protocol = String.valueOf(protocol);
        throw new ProtocolException(protocol.length() != 0 ? str2.concat(protocol) : new String(str2));
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0045  */
    private static long a(java.net.HttpURLConnection r10) {
        /*
        r0 = "Content-Length";
        r0 = r10.getHeaderField(r0);
        r1 = android.text.TextUtils.isEmpty(r0);
        if (r1 != 0) goto L_0x0037;
    L_0x000c:
        r1 = java.lang.Long.parseLong(r0);	 Catch:{ NumberFormatException -> 0x0011 }
        goto L_0x0039;
    L_0x0011:
        r1 = "DefaultHttpDataSource";
        r2 = 28;
        r3 = java.lang.String.valueOf(r0);
        r3 = r3.length();
        r2 = r2 + r3;
        r3 = new java.lang.StringBuilder;
        r3.<init>(r2);
        r2 = "Unexpected Content-Length [";
        r3.append(r2);
        r3.append(r0);
        r2 = "]";
        r3.append(r2);
        r2 = r3.toString();
        android.util.Log.e(r1, r2);
    L_0x0037:
        r1 = -1;
    L_0x0039:
        r3 = "Content-Range";
        r10 = r10.getHeaderField(r3);
        r3 = android.text.TextUtils.isEmpty(r10);
        if (r3 != 0) goto L_0x00d8;
    L_0x0045:
        r3 = b;
        r3 = r3.matcher(r10);
        r4 = r3.find();
        if (r4 == 0) goto L_0x00d8;
    L_0x0051:
        r4 = 2;
        r4 = r3.group(r4);	 Catch:{ NumberFormatException -> 0x00b2 }
        r4 = java.lang.Long.parseLong(r4);	 Catch:{ NumberFormatException -> 0x00b2 }
        r6 = 1;
        r3 = r3.group(r6);	 Catch:{ NumberFormatException -> 0x00b2 }
        r6 = java.lang.Long.parseLong(r3);	 Catch:{ NumberFormatException -> 0x00b2 }
        r8 = r4 - r6;
        r3 = 1;
        r5 = r8 + r3;
        r3 = 0;
        r7 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1));
        if (r7 >= 0) goto L_0x0071;
    L_0x006f:
        r1 = r5;
        goto L_0x00d8;
    L_0x0071:
        r3 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1));
        if (r3 == 0) goto L_0x00d8;
    L_0x0075:
        r3 = "DefaultHttpDataSource";
        r4 = 26;
        r7 = java.lang.String.valueOf(r0);	 Catch:{ NumberFormatException -> 0x00b2 }
        r7 = r7.length();	 Catch:{ NumberFormatException -> 0x00b2 }
        r4 = r4 + r7;
        r7 = java.lang.String.valueOf(r10);	 Catch:{ NumberFormatException -> 0x00b2 }
        r7 = r7.length();	 Catch:{ NumberFormatException -> 0x00b2 }
        r4 = r4 + r7;
        r7 = new java.lang.StringBuilder;	 Catch:{ NumberFormatException -> 0x00b2 }
        r7.<init>(r4);	 Catch:{ NumberFormatException -> 0x00b2 }
        r4 = "Inconsistent headers [";
        r7.append(r4);	 Catch:{ NumberFormatException -> 0x00b2 }
        r7.append(r0);	 Catch:{ NumberFormatException -> 0x00b2 }
        r0 = "] [";
        r7.append(r0);	 Catch:{ NumberFormatException -> 0x00b2 }
        r7.append(r10);	 Catch:{ NumberFormatException -> 0x00b2 }
        r0 = "]";
        r7.append(r0);	 Catch:{ NumberFormatException -> 0x00b2 }
        r0 = r7.toString();	 Catch:{ NumberFormatException -> 0x00b2 }
        android.util.Log.w(r3, r0);	 Catch:{ NumberFormatException -> 0x00b2 }
        r3 = java.lang.Math.max(r1, r5);	 Catch:{ NumberFormatException -> 0x00b2 }
        r1 = r3;
        goto L_0x00d8;
    L_0x00b2:
        r0 = "DefaultHttpDataSource";
        r3 = 27;
        r4 = java.lang.String.valueOf(r10);
        r4 = r4.length();
        r3 = r3 + r4;
        r4 = new java.lang.StringBuilder;
        r4.<init>(r3);
        r3 = "Unexpected Content-Range [";
        r4.append(r3);
        r4.append(r10);
        r10 = "]";
        r4.append(r10);
        r10 = r4.toString();
        android.util.Log.e(r0, r10);
    L_0x00d8:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.ew.a(java.net.HttpURLConnection):long");
    }

    private void c() throws IOException {
        if (this.q != this.o) {
            byte[] bArr = (byte[]) c.getAndSet(null);
            if (bArr == null) {
                bArr = new byte[4096];
            }
            while (this.q != this.o) {
                int read = this.m.read(bArr, 0, (int) Math.min(this.o - this.q, (long) bArr.length));
                if (Thread.interrupted()) {
                    throw new InterruptedIOException();
                } else if (read == -1) {
                    throw new EOFException();
                } else {
                    this.q += (long) read;
                    if (this.j != null) {
                        this.j.a(read);
                    }
                }
            }
            c.set(bArr);
        }
    }

    private int b(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        int read = this.m.read(bArr, i, i2);
        if (read != -1) {
            this.r += (long) read;
            if (this.j != null) {
                this.j.a(read);
            }
            return read;
        } else if (this.p == -1 || this.p == this.r) {
            return -1;
        } else {
            throw new EOFException();
        }
    }

    private void d() {
        if (this.l != null) {
            try {
                this.l.disconnect();
            } catch (Exception e) {
                Log.e("DefaultHttpDataSource", "Unexpected error while disconnecting", e);
            }
            this.l = null;
        }
    }
}
