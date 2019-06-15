package com.exoplayer2.upstream;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import com.constants.Constants;
import com.exoplayer2.CookieSpan;
import com.exoplayer2.d;
import com.gaana.login.sso.SsoErrorCodes;
import com.google.android.exoplayer2.upstream.BaseDataSource;
import com.google.android.exoplayer2.upstream.DataSourceException;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.upstream.HttpDataSource.HttpDataSourceException;
import com.google.android.exoplayer2.upstream.HttpDataSource.InvalidContentTypeException;
import com.google.android.exoplayer2.upstream.HttpDataSource.InvalidResponseCodeException;
import com.google.android.exoplayer2.upstream.HttpDataSource.RequestProperties;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Predicate;
import com.google.android.exoplayer2.util.Util;
import com.google.api.client.http.HttpStatusCodes;
import com.til.colombia.android.internal.e;
import com.til.colombia.android.internal.h;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.NoRouteToHostException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

public class c extends BaseDataSource implements HttpDataSource {
    static CookieManager a = new CookieManager(new d(), null);
    private static final Pattern b = Pattern.compile("^bytes (\\d+)-(\\d+)/(\\d+)$");
    private static final AtomicReference<byte[]> c = new AtomicReference();
    private final boolean d;
    private boolean e;
    private final int f;
    private final int g;
    private final String h;
    @Nullable
    private final Predicate<String> i;
    @Nullable
    private final RequestProperties j;
    private final RequestProperties k = new RequestProperties();
    @Nullable
    private DataSpec l;
    @Nullable
    private HttpURLConnection m;
    @Nullable
    private InputStream n;
    private boolean o;
    private long p;
    private long q;
    private long r;
    private long s;

    public c(String str, @Nullable Predicate<String> predicate, int i, int i2, boolean z, @Nullable RequestProperties requestProperties, boolean z2) {
        super(true);
        this.h = Assertions.checkNotEmpty(str);
        this.i = predicate;
        this.f = i;
        this.g = i2;
        this.d = z;
        this.j = requestProperties;
        this.e = z2;
    }

    @Nullable
    public Uri getUri() {
        return this.m == null ? null : Uri.parse(this.m.getURL().toString());
    }

    public Map<String, List<String>> getResponseHeaders() {
        return this.m == null ? Collections.emptyMap() : this.m.getHeaderFields();
    }

    public void setRequestProperty(String str, String str2) {
        Assertions.checkNotNull(str);
        Assertions.checkNotNull(str2);
        this.k.set(str, str2);
    }

    public void clearRequestProperty(String str) {
        Assertions.checkNotNull(str);
        this.k.remove(str);
    }

    public void clearAllRequestProperties() {
        this.k.clear();
    }

    private void a(HttpURLConnection httpURLConnection) {
        List<String> list = (List) httpURLConnection.getHeaderFields().get(h.i);
        String url = httpURLConnection.getURL().toString();
        List list2 = null;
        HttpCookie httpCookie;
        if (url.contains("master")) {
            url = url.substring(0, url.indexOf(".mp4") + ".mp4".length());
            if (list != null) {
                for (String parse : list) {
                    httpCookie = (HttpCookie) HttpCookie.parse(parse).get(0);
                    if (list2 != null) {
                        list2.remove(httpCookie);
                        list2.add(httpCookie);
                    } else {
                        list2 = new ArrayList();
                        list2.add(httpCookie);
                    }
                }
                if (this.e) {
                    Constants.ej.a(new CookieSpan(url, TextUtils.join(";", list2), System.currentTimeMillis()));
                    return;
                }
                Constants.ei.put(url, TextUtils.join(";", list2));
            }
        } else if (url.contains("playlist")) {
            String[] split = url.split("/");
            if (split.length > 3) {
                url = split[3];
            }
            if (list != null) {
                for (String parse2 : list) {
                    httpCookie = (HttpCookie) HttpCookie.parse(parse2).get(0);
                    if (list2 != null) {
                        list2.remove(httpCookie);
                        list2.add(httpCookie);
                    } else {
                        list2 = new ArrayList();
                        list2.add(httpCookie);
                    }
                }
                if (this.e) {
                    Constants.ej.a(new CookieSpan(url, TextUtils.join(";", list2), System.currentTimeMillis()));
                    return;
                }
                Constants.ei.put(url, TextUtils.join(";", list2));
            }
        }
    }

    private String b(HttpURLConnection httpURLConnection) {
        Object url = httpURLConnection.getURL().toString();
        int indexOf = url.indexOf(".mp4");
        String str = null;
        if (indexOf != -1) {
            if (this.e) {
                if (Constants.eh != null) {
                    str = (String) Constants.eh.get(url.substring(0, indexOf + ".mp4".length()));
                }
            } else if (Constants.ei != null) {
                str = (String) Constants.ei.get(url.substring(0, indexOf + ".mp4".length()));
            }
            return str;
        } else if (url.contains("playlist.m3u8")) {
            return null;
        } else {
            String[] split = url.split("/");
            if (split.length > 3) {
                url = split[3];
            }
            if (this.e) {
                if (Constants.eh != null) {
                    str = (String) Constants.eh.get(url);
                }
            } else if (Constants.ei != null) {
                str = (String) Constants.ei.get(url);
            }
            return str;
        }
    }

    public long open(DataSpec dataSpec) throws HttpDataSourceException {
        StringBuilder stringBuilder;
        this.l = dataSpec;
        long j = 0;
        this.s = 0;
        this.r = 0;
        transferInitializing(dataSpec);
        try {
            this.m = a(dataSpec);
            try {
                int responseCode = this.m.getResponseCode();
                String responseMessage = this.m.getResponseMessage();
                if (responseCode < 200 || responseCode > 299) {
                    Map headerFields = this.m.getHeaderFields();
                    c();
                    InvalidResponseCodeException invalidResponseCodeException = new InvalidResponseCodeException(responseCode, responseMessage, headerFields, dataSpec);
                    if (responseCode == SsoErrorCodes.LIMIT_EXCEEDED) {
                        invalidResponseCodeException.initCause(new DataSourceException(0));
                    }
                    throw invalidResponseCodeException;
                }
                responseMessage = this.m.getContentType();
                if (this.i == null || this.i.evaluate(responseMessage)) {
                    a(this.m);
                    if (responseCode == 200 && dataSpec.position != 0) {
                        j = dataSpec.position;
                    }
                    this.p = j;
                    if (dataSpec.isFlagSet(1)) {
                        this.q = dataSpec.length;
                    } else {
                        long j2 = -1;
                        if (dataSpec.length != -1) {
                            this.q = dataSpec.length;
                        } else {
                            j = c(this.m);
                            if (j != -1) {
                                j2 = j - this.p;
                            }
                            this.q = j2;
                        }
                    }
                    try {
                        this.n = this.m.getInputStream();
                        this.o = true;
                        transferStarted(dataSpec);
                        return this.q;
                    } catch (IOException e) {
                        c();
                        throw new HttpDataSourceException(e, dataSpec, 1);
                    }
                }
                c();
                throw new InvalidContentTypeException(responseMessage, dataSpec);
            } catch (IOException e2) {
                c();
                stringBuilder = new StringBuilder();
                stringBuilder.append("Unable to connect to ");
                stringBuilder.append(dataSpec.uri.toString());
                throw new HttpDataSourceException(stringBuilder.toString(), e2, dataSpec, 1);
            }
        } catch (IOException e22) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Unable to connect to ");
            stringBuilder.append(dataSpec.uri.toString());
            throw new HttpDataSourceException(stringBuilder.toString(), e22, dataSpec, 1);
        }
    }

    public int read(byte[] bArr, int i, int i2) throws HttpDataSourceException {
        try {
            b();
            return a(bArr, i, i2);
        } catch (IOException e) {
            throw new HttpDataSourceException(e, this.l, 2);
        }
    }

    public void close() throws HttpDataSourceException {
        try {
            if (this.n != null) {
                a(this.m, a());
                this.n.close();
            }
            this.n = null;
            c();
            if (this.o) {
                this.o = false;
                transferEnded();
            }
        } catch (IOException e) {
            throw new HttpDataSourceException(e, this.l, 3);
        } catch (Throwable th) {
            this.n = null;
            c();
            if (this.o) {
                this.o = false;
                transferEnded();
            }
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final long a() {
        return this.q == -1 ? this.q : this.q - this.s;
    }

    private HttpURLConnection a(DataSpec dataSpec) throws IOException {
        DataSpec dataSpec2 = dataSpec;
        URL url = new URL(dataSpec2.uri.toString());
        int i = dataSpec2.httpMethod;
        byte[] bArr = dataSpec2.httpBody;
        long j = dataSpec2.position;
        long j2 = dataSpec2.length;
        boolean isFlagSet = dataSpec2.isFlagSet(1);
        c cVar = this;
        if (!cVar.d) {
            return cVar.a(url, i, bArr, j, j2, isFlagSet, true);
        }
        HttpURLConnection a;
        int i2 = 0;
        while (true) {
            int i3 = i2 + 1;
            int i4;
            if (i2 <= 20) {
                i4 = i3;
                long j3 = j2;
                a = cVar.a(url, i, bArr, j, j2, isFlagSet, false);
                int responseCode = a.getResponseCode();
                String headerField = a.getHeaderField(e.e);
                if ((i == 1 || i == 3) && (responseCode == HttpStatusCodes.STATUS_CODE_MULTIPLE_CHOICES || responseCode == HttpStatusCodes.STATUS_CODE_MOVED_PERMANENTLY || responseCode == HttpStatusCodes.STATUS_CODE_FOUND || responseCode == HttpStatusCodes.STATUS_CODE_SEE_OTHER || responseCode == HttpStatusCodes.STATUS_CODE_TEMPORARY_REDIRECT || responseCode == 308)) {
                    a.disconnect();
                    url = a(url, headerField);
                } else if (i != 2 || (responseCode != HttpStatusCodes.STATUS_CODE_MULTIPLE_CHOICES && responseCode != HttpStatusCodes.STATUS_CODE_MOVED_PERMANENTLY && responseCode != HttpStatusCodes.STATUS_CODE_FOUND && responseCode != HttpStatusCodes.STATUS_CODE_SEE_OTHER)) {
                    return a;
                } else {
                    a.disconnect();
                    url = a(url, headerField);
                    bArr = null;
                    i = 1;
                }
                cVar = this;
                i2 = i4;
                j2 = j3;
            } else {
                i4 = i3;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Too many redirects: ");
                stringBuilder.append(i4);
                throw new NoRouteToHostException(stringBuilder.toString());
            }
        }
        return a;
    }

    private HttpURLConnection a(URL url, int i, byte[] bArr, long j, long j2, boolean z, boolean z2) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setConnectTimeout(this.f);
        httpURLConnection.setReadTimeout(this.g);
        if (this.j != null) {
            for (Entry entry : this.j.getSnapshot().entrySet()) {
                httpURLConnection.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
            }
        }
        for (Entry entry2 : this.k.getSnapshot().entrySet()) {
            httpURLConnection.setRequestProperty((String) entry2.getKey(), (String) entry2.getValue());
        }
        if (!(j == 0 && j2 == -1)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("bytes=");
            stringBuilder.append(j);
            stringBuilder.append("-");
            String stringBuilder2 = stringBuilder.toString();
            if (j2 != -1) {
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append(stringBuilder2);
                stringBuilder3.append((j + j2) - 1);
                stringBuilder2 = stringBuilder3.toString();
            }
            httpURLConnection.setRequestProperty("Range", stringBuilder2);
        }
        httpURLConnection.setRequestProperty(e.c, this.h);
        if (!z) {
            httpURLConnection.setRequestProperty("Accept-Encoding", "identity");
        }
        String b = b(httpURLConnection);
        if (!TextUtils.isEmpty(b)) {
            httpURLConnection.setRequestProperty("Cookie", b);
        }
        httpURLConnection.setInstanceFollowRedirects(z2);
        httpURLConnection.setDoOutput(bArr != null);
        httpURLConnection.setRequestMethod(DataSpec.getStringForHttpMethod(i));
        if (bArr != null) {
            httpURLConnection.setFixedLengthStreamingMode(bArr.length);
            httpURLConnection.connect();
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(bArr);
            outputStream.close();
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
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unsupported protocol redirect: ");
        stringBuilder.append(protocol);
        throw new ProtocolException(stringBuilder.toString());
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x003a  */
    private static long c(java.net.HttpURLConnection r10) {
        /*
        r0 = "Content-Length";
        r0 = r10.getHeaderField(r0);
        r1 = android.text.TextUtils.isEmpty(r0);
        if (r1 != 0) goto L_0x002c;
    L_0x000c:
        r1 = java.lang.Long.parseLong(r0);	 Catch:{ NumberFormatException -> 0x0011 }
        goto L_0x002e;
    L_0x0011:
        r1 = "DefaultHttpDataSource";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "Unexpected Content-Length [";
        r2.append(r3);
        r2.append(r0);
        r3 = "]";
        r2.append(r3);
        r2 = r2.toString();
        com.google.android.exoplayer2.util.Log.e(r1, r2);
    L_0x002c:
        r1 = -1;
    L_0x002e:
        r3 = "Content-Range";
        r10 = r10.getHeaderField(r3);
        r3 = android.text.TextUtils.isEmpty(r10);
        if (r3 != 0) goto L_0x00ae;
    L_0x003a:
        r3 = b;
        r3 = r3.matcher(r10);
        r4 = r3.find();
        if (r4 == 0) goto L_0x00ae;
    L_0x0046:
        r4 = 2;
        r4 = r3.group(r4);	 Catch:{ NumberFormatException -> 0x0093 }
        r4 = java.lang.Long.parseLong(r4);	 Catch:{ NumberFormatException -> 0x0093 }
        r6 = 1;
        r3 = r3.group(r6);	 Catch:{ NumberFormatException -> 0x0093 }
        r6 = java.lang.Long.parseLong(r3);	 Catch:{ NumberFormatException -> 0x0093 }
        r8 = r4 - r6;
        r3 = 1;
        r5 = r8 + r3;
        r3 = 0;
        r7 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1));
        if (r7 >= 0) goto L_0x0066;
    L_0x0064:
        r1 = r5;
        goto L_0x00ae;
    L_0x0066:
        r3 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1));
        if (r3 == 0) goto L_0x00ae;
    L_0x006a:
        r3 = "DefaultHttpDataSource";
        r4 = new java.lang.StringBuilder;	 Catch:{ NumberFormatException -> 0x0093 }
        r4.<init>();	 Catch:{ NumberFormatException -> 0x0093 }
        r7 = "Inconsistent headers [";
        r4.append(r7);	 Catch:{ NumberFormatException -> 0x0093 }
        r4.append(r0);	 Catch:{ NumberFormatException -> 0x0093 }
        r0 = "] [";
        r4.append(r0);	 Catch:{ NumberFormatException -> 0x0093 }
        r4.append(r10);	 Catch:{ NumberFormatException -> 0x0093 }
        r0 = "]";
        r4.append(r0);	 Catch:{ NumberFormatException -> 0x0093 }
        r0 = r4.toString();	 Catch:{ NumberFormatException -> 0x0093 }
        com.google.android.exoplayer2.util.Log.w(r3, r0);	 Catch:{ NumberFormatException -> 0x0093 }
        r3 = java.lang.Math.max(r1, r5);	 Catch:{ NumberFormatException -> 0x0093 }
        r1 = r3;
        goto L_0x00ae;
    L_0x0093:
        r0 = "DefaultHttpDataSource";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "Unexpected Content-Range [";
        r3.append(r4);
        r3.append(r10);
        r10 = "]";
        r3.append(r10);
        r10 = r3.toString();
        com.google.android.exoplayer2.util.Log.e(r0, r10);
    L_0x00ae:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.exoplayer2.upstream.c.c(java.net.HttpURLConnection):long");
    }

    private void b() throws IOException {
        if (this.r != this.p) {
            byte[] bArr = (byte[]) c.getAndSet(null);
            if (bArr == null) {
                bArr = new byte[4096];
            }
            while (this.r != this.p) {
                int read = this.n.read(bArr, 0, (int) Math.min(this.p - this.r, (long) bArr.length));
                if (Thread.currentThread().isInterrupted()) {
                    throw new InterruptedIOException();
                } else if (read == -1) {
                    throw new EOFException();
                } else {
                    this.r += (long) read;
                    bytesTransferred(read);
                }
            }
            c.set(bArr);
        }
    }

    private int a(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        if (this.q != -1) {
            long j = this.q - this.s;
            if (j == 0) {
                return -1;
            }
            i2 = (int) Math.min((long) i2, j);
        }
        int read = this.n.read(bArr, i, i2);
        if (read != -1) {
            this.s += (long) read;
            bytesTransferred(read);
            return read;
        } else if (this.q == -1) {
            return -1;
        } else {
            throw new EOFException();
        }
    }

    private static void a(HttpURLConnection httpURLConnection, long j) {
        if (Util.SDK_INT == 19 || Util.SDK_INT == 20) {
            try {
                InputStream inputStream = httpURLConnection.getInputStream();
                if (j == -1) {
                    if (inputStream.read() == -1) {
                        return;
                    }
                } else if (j <= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH) {
                    return;
                }
                String name = inputStream.getClass().getName();
                if ("com.android.okhttp.internal.http.HttpTransport$ChunkedInputStream".equals(name) || "com.android.okhttp.internal.http.HttpTransport$FixedLengthInputStream".equals(name)) {
                    Method declaredMethod = inputStream.getClass().getSuperclass().getDeclaredMethod("unexpectedEndOfInput", new Class[0]);
                    declaredMethod.setAccessible(true);
                    declaredMethod.invoke(inputStream, new Object[0]);
                }
            } catch (Exception unused) {
            }
        }
    }

    private void c() {
        if (this.m != null) {
            try {
                this.m.disconnect();
            } catch (Exception e) {
                Log.e("DefaultHttpDataSource", "Unexpected error while disconnecting", e);
            }
            this.m = null;
        }
    }
}
