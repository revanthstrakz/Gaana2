package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.gaana.login.sso.SsoErrorCodes;
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
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

public final class zzpb implements zzov {
    private static final Pattern zzbgd = Pattern.compile("^bytes (\\d+)-(\\d+)/(\\d+)$");
    private static final AtomicReference<byte[]> zzbge = new AtomicReference();
    private zzoz zzazo;
    private boolean zzbfr;
    private final boolean zzbgf;
    private final int zzbgg;
    private final int zzbgh;
    private final String zzbgi;
    private final zzpz<String> zzbgj;
    private final zzpe zzbgk;
    private final zzpe zzbgl;
    private final zzpn<? super zzpb> zzbgm;
    private HttpURLConnection zzbgn;
    private InputStream zzbgo;
    private long zzbgp;
    private long zzbgq;
    private long zzbgr;
    private long zzcd;

    public zzpb(String str, zzpz<String> zzpz, zzpn<? super zzpb> zzpn, int i, int i2, boolean z, zzpe zzpe) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException();
        }
        this.zzbgi = str;
        this.zzbgj = null;
        this.zzbgm = zzpn;
        this.zzbgl = new zzpe();
        this.zzbgg = i;
        this.zzbgh = i2;
        this.zzbgf = true;
        this.zzbgk = null;
    }

    public final Uri getUri() {
        return this.zzbgn == null ? null : Uri.parse(this.zzbgn.getURL().toString());
    }

    public final Map<String, List<String>> getResponseHeaders() {
        return this.zzbgn == null ? null : this.zzbgn.getHeaderFields();
    }

    public final long zza(zzoz zzoz) throws zzpc {
        IOException iOException;
        String valueOf;
        zzoz zzoz2 = zzoz;
        this.zzazo = zzoz2;
        long j = 0;
        this.zzcd = 0;
        this.zzbgr = 0;
        String headerField;
        try {
            int i;
            HttpURLConnection zza;
            URL url = new URL(zzoz2.uri.toString());
            byte[] bArr = zzoz2.zzbft;
            long j2 = zzoz2.zzaha;
            long j3 = zzoz2.zzcc;
            boolean zzbg = zzoz2.zzbg(1);
            if (this.zzbgf) {
                URL url2 = url;
                byte[] bArr2 = bArr;
                i = 0;
                while (true) {
                    int i2 = i + 1;
                    int i3;
                    if (i <= 20) {
                        URL url3 = url2;
                        i3 = i2;
                        long j4 = j3;
                        long j5 = j2;
                        zza = zza(url2, bArr2, j2, j3, zzbg, false);
                        int responseCode = zza.getResponseCode();
                        if (!(responseCode == HttpStatusCodes.STATUS_CODE_MULTIPLE_CHOICES || responseCode == HttpStatusCodes.STATUS_CODE_MOVED_PERMANENTLY || responseCode == HttpStatusCodes.STATUS_CODE_FOUND || responseCode == HttpStatusCodes.STATUS_CODE_SEE_OTHER)) {
                            if (bArr2 == null) {
                                if (responseCode != HttpStatusCodes.STATUS_CODE_TEMPORARY_REDIRECT) {
                                    if (responseCode != 308) {
                                        break;
                                    }
                                }
                            } else {
                                break;
                            }
                        }
                        bArr2 = null;
                        headerField = zza.getHeaderField(e.e);
                        zza.disconnect();
                        if (headerField == null) {
                            throw new ProtocolException("Null location redirect");
                        }
                        url2 = new URL(url3, headerField);
                        String protocol = url2.getProtocol();
                        if ("https".equals(protocol) || "http".equals(protocol)) {
                            i = i3;
                            j3 = j4;
                            j2 = j5;
                        } else {
                            headerField = "Unsupported protocol redirect: ";
                            protocol = String.valueOf(protocol);
                            throw new ProtocolException(protocol.length() != 0 ? headerField.concat(protocol) : new String(headerField));
                        }
                    }
                    i3 = i2;
                    StringBuilder stringBuilder = new StringBuilder(31);
                    stringBuilder.append("Too many redirects: ");
                    stringBuilder.append(i3);
                    throw new NoRouteToHostException(stringBuilder.toString());
                }
            }
            zza = zza(url, bArr, j2, j3, zzbg, true);
            this.zzbgn = zza;
            try {
                i = this.zzbgn.getResponseCode();
                if (i < 200 || i > 299) {
                    Map headerFields = this.zzbgn.getHeaderFields();
                    zzgw();
                    zzpd zzpd = new zzpd(i, headerFields, zzoz2);
                    if (i == SsoErrorCodes.LIMIT_EXCEEDED) {
                        zzpd.initCause(new zzox(0));
                    }
                    throw zzpd;
                }
                this.zzbgn.getContentType();
                if (i == 200 && zzoz2.zzaha != 0) {
                    j = zzoz2.zzaha;
                }
                this.zzbgp = j;
                if (zzoz2.zzbg(1)) {
                    this.zzbgq = zzoz2.zzcc;
                } else {
                    long j6 = -1;
                    if (zzoz2.zzcc != -1) {
                        this.zzbgq = zzoz2.zzcc;
                    } else {
                        long zzc = zzc(this.zzbgn);
                        if (zzc != -1) {
                            j6 = zzc - this.zzbgp;
                        }
                        this.zzbgq = j6;
                    }
                }
                try {
                    this.zzbgo = this.zzbgn.getInputStream();
                    this.zzbfr = true;
                    if (this.zzbgm != null) {
                        this.zzbgm.zza(this, zzoz2);
                    }
                    return this.zzbgq;
                } catch (IOException e) {
                    iOException = e;
                    zzgw();
                    throw new zzpc(iOException, zzoz2, 1);
                }
            } catch (IOException e2) {
                iOException = e2;
                zzgw();
                headerField = "Unable to connect to ";
                valueOf = String.valueOf(zzoz2.uri.toString());
                throw new zzpc(valueOf.length() != 0 ? headerField.concat(valueOf) : new String(headerField), iOException, zzoz2, 1);
            }
        } catch (IOException e22) {
            iOException = e22;
            headerField = "Unable to connect to ";
            valueOf = String.valueOf(zzoz2.uri.toString());
            throw new zzpc(valueOf.length() != 0 ? headerField.concat(valueOf) : new String(headerField), iOException, zzoz2, 1);
        }
    }

    public final int read(byte[] bArr, int i, int i2) throws zzpc {
        try {
            if (this.zzbgr != this.zzbgp) {
                byte[] bArr2 = (byte[]) zzbge.getAndSet(null);
                if (bArr2 == null) {
                    bArr2 = new byte[4096];
                }
                while (this.zzbgr != this.zzbgp) {
                    int read = this.zzbgo.read(bArr2, 0, (int) Math.min(this.zzbgp - this.zzbgr, (long) bArr2.length));
                    if (Thread.interrupted()) {
                        throw new InterruptedIOException();
                    } else if (read == -1) {
                        throw new EOFException();
                    } else {
                        this.zzbgr += (long) read;
                        if (this.zzbgm != null) {
                            this.zzbgm.zzc(this, read);
                        }
                    }
                }
                zzbge.set(bArr2);
            }
            if (i2 == 0) {
                return 0;
            }
            if (this.zzbgq != -1) {
                long j = this.zzbgq - this.zzcd;
                if (j == 0) {
                    return -1;
                }
                i2 = (int) Math.min((long) i2, j);
            }
            int read2 = this.zzbgo.read(bArr, i, i2);
            if (read2 != -1) {
                this.zzcd += (long) read2;
                if (this.zzbgm != null) {
                    this.zzbgm.zzc(this, read2);
                }
                return read2;
            } else if (this.zzbgq == -1) {
                return -1;
            } else {
                throw new EOFException();
            }
        } catch (IOException e) {
            throw new zzpc(e, this.zzazo, 2);
        }
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:26:0x006d */
    /* JADX WARNING: Can't wrap try/catch for region: R(6:4|(1:6)(1:7)|8|(5:13|14|(2:16|(1:18))(1:19)|21|(1:25))|26|27) */
    /* JADX WARNING: Missing block: B:20:0x003a, code skipped:
            if (r3 > android.support.v4.media.session.PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH) goto L_0x003c;
     */
    public final void close() throws com.google.android.gms.internal.ads.zzpc {
        /*
        r11 = this;
        r0 = 0;
        r1 = 0;
        r2 = r11.zzbgo;	 Catch:{ all -> 0x0093 }
        if (r2 == 0) goto L_0x007d;
    L_0x0006:
        r2 = r11.zzbgn;	 Catch:{ all -> 0x0093 }
        r3 = r11.zzbgq;	 Catch:{ all -> 0x0093 }
        r5 = -1;
        r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1));
        if (r7 != 0) goto L_0x0013;
    L_0x0010:
        r3 = r11.zzbgq;	 Catch:{ all -> 0x0093 }
        goto L_0x001a;
    L_0x0013:
        r3 = r11.zzbgq;	 Catch:{ all -> 0x0093 }
        r7 = r11.zzcd;	 Catch:{ all -> 0x0093 }
        r9 = r3 - r7;
        r3 = r9;
    L_0x001a:
        r7 = com.google.android.gms.internal.ads.zzqe.SDK_INT;	 Catch:{ all -> 0x0093 }
        r8 = 19;
        if (r7 == r8) goto L_0x0026;
    L_0x0020:
        r7 = com.google.android.gms.internal.ads.zzqe.SDK_INT;	 Catch:{ all -> 0x0093 }
        r8 = 20;
        if (r7 != r8) goto L_0x006d;
    L_0x0026:
        r2 = r2.getInputStream();	 Catch:{ Exception -> 0x006d }
        r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1));
        if (r7 != 0) goto L_0x0036;
    L_0x002e:
        r3 = r2.read();	 Catch:{ Exception -> 0x006d }
        r4 = -1;
        if (r3 != r4) goto L_0x003c;
    L_0x0035:
        goto L_0x006d;
    L_0x0036:
        r5 = 2048; // 0x800 float:2.87E-42 double:1.0118E-320;
        r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1));
        if (r7 <= 0) goto L_0x006d;
    L_0x003c:
        r3 = r2.getClass();	 Catch:{ Exception -> 0x006d }
        r3 = r3.getName();	 Catch:{ Exception -> 0x006d }
        r4 = "com.android.okhttp.internal.http.HttpTransport$ChunkedInputStream";
        r4 = r3.equals(r4);	 Catch:{ Exception -> 0x006d }
        if (r4 != 0) goto L_0x0054;
    L_0x004c:
        r4 = "com.android.okhttp.internal.http.HttpTransport$FixedLengthInputStream";
        r3 = r3.equals(r4);	 Catch:{ Exception -> 0x006d }
        if (r3 == 0) goto L_0x006d;
    L_0x0054:
        r3 = r2.getClass();	 Catch:{ Exception -> 0x006d }
        r3 = r3.getSuperclass();	 Catch:{ Exception -> 0x006d }
        r4 = "unexpectedEndOfInput";
        r5 = new java.lang.Class[r1];	 Catch:{ Exception -> 0x006d }
        r3 = r3.getDeclaredMethod(r4, r5);	 Catch:{ Exception -> 0x006d }
        r4 = 1;
        r3.setAccessible(r4);	 Catch:{ Exception -> 0x006d }
        r4 = new java.lang.Object[r1];	 Catch:{ Exception -> 0x006d }
        r3.invoke(r2, r4);	 Catch:{ Exception -> 0x006d }
    L_0x006d:
        r2 = r11.zzbgo;	 Catch:{ IOException -> 0x0073 }
        r2.close();	 Catch:{ IOException -> 0x0073 }
        goto L_0x007d;
    L_0x0073:
        r2 = move-exception;
        r3 = new com.google.android.gms.internal.ads.zzpc;	 Catch:{ all -> 0x0093 }
        r4 = r11.zzazo;	 Catch:{ all -> 0x0093 }
        r5 = 3;
        r3.<init>(r2, r4, r5);	 Catch:{ all -> 0x0093 }
        throw r3;	 Catch:{ all -> 0x0093 }
    L_0x007d:
        r11.zzbgo = r0;
        r11.zzgw();
        r0 = r11.zzbfr;
        if (r0 == 0) goto L_0x0092;
    L_0x0086:
        r11.zzbfr = r1;
        r0 = r11.zzbgm;
        if (r0 == 0) goto L_0x0092;
    L_0x008c:
        r0 = r11.zzbgm;
        r0.zze(r11);
        return;
    L_0x0092:
        return;
    L_0x0093:
        r2 = move-exception;
        r11.zzbgo = r0;
        r11.zzgw();
        r0 = r11.zzbfr;
        if (r0 == 0) goto L_0x00a8;
    L_0x009d:
        r11.zzbfr = r1;
        r0 = r11.zzbgm;
        if (r0 == 0) goto L_0x00a8;
    L_0x00a3:
        r0 = r11.zzbgm;
        r0.zze(r11);
    L_0x00a8:
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzpb.close():void");
    }

    private final HttpURLConnection zza(URL url, byte[] bArr, long j, long j2, boolean z, boolean z2) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setConnectTimeout(this.zzbgg);
        httpURLConnection.setReadTimeout(this.zzbgh);
        for (Entry entry : this.zzbgl.zzgx().entrySet()) {
            httpURLConnection.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
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
        httpURLConnection.setRequestProperty(e.c, this.zzbgi);
        if (!z) {
            httpURLConnection.setRequestProperty("Accept-Encoding", "identity");
        }
        httpURLConnection.setInstanceFollowRedirects(z2);
        httpURLConnection.setDoOutput(bArr != null);
        if (bArr != null) {
            httpURLConnection.setRequestMethod(HttpMethods.POST);
            if (bArr.length != 0) {
                httpURLConnection.setFixedLengthStreamingMode(bArr.length);
                httpURLConnection.connect();
                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(bArr);
                outputStream.close();
                return httpURLConnection;
            }
        }
        httpURLConnection.connect();
        return httpURLConnection;
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0045  */
    private static long zzc(java.net.HttpURLConnection r10) {
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
        r3 = zzbgd;
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzpb.zzc(java.net.HttpURLConnection):long");
    }

    private final void zzgw() {
        if (this.zzbgn != null) {
            try {
                this.zzbgn.disconnect();
            } catch (Exception e) {
                Log.e("DefaultHttpDataSource", "Unexpected error while disconnecting", e);
            }
            this.zzbgn = null;
        }
    }
}
