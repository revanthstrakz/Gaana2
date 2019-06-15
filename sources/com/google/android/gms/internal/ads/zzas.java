package com.google.android.gms.internal.ads;

import android.support.annotation.VisibleForTesting;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.net.ssl.SSLSocketFactory;
import org.apache.http.entity.mime.MIME;

public final class zzas extends zzai {
    private final zzau zzci;
    private final SSLSocketFactory zzcj;

    public zzas() {
        this(null);
    }

    private zzas(zzau zzau) {
        this(null, null);
    }

    private zzas(zzau zzau, SSLSocketFactory sSLSocketFactory) {
        this.zzci = null;
        this.zzcj = null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:58:0x012a  */
    public final com.google.android.gms.internal.ads.zzaq zza(com.google.android.gms.internal.ads.zzr<?> r7, java.util.Map<java.lang.String, java.lang.String> r8) throws java.io.IOException, com.google.android.gms.internal.ads.zza {
        /*
        r6 = this;
        r0 = r7.getUrl();
        r1 = new java.util.HashMap;
        r1.<init>();
        r1.putAll(r8);
        r8 = r7.getHeaders();
        r1.putAll(r8);
        r8 = r6.zzci;
        if (r8 == 0) goto L_0x003c;
    L_0x0017:
        r8 = r6.zzci;
        r8 = r8.zzg(r0);
        if (r8 != 0) goto L_0x003d;
    L_0x001f:
        r7 = new java.io.IOException;
        r8 = "URL blocked by rewriter: ";
        r0 = java.lang.String.valueOf(r0);
        r1 = r0.length();
        if (r1 == 0) goto L_0x0032;
    L_0x002d:
        r8 = r8.concat(r0);
        goto L_0x0038;
    L_0x0032:
        r0 = new java.lang.String;
        r0.<init>(r8);
        r8 = r0;
    L_0x0038:
        r7.<init>(r8);
        throw r7;
    L_0x003c:
        r8 = r0;
    L_0x003d:
        r0 = new java.net.URL;
        r0.<init>(r8);
        r8 = r0.openConnection();
        r8 = (java.net.HttpURLConnection) r8;
        r2 = java.net.HttpURLConnection.getFollowRedirects();
        r8.setInstanceFollowRedirects(r2);
        r2 = r7.zzj();
        r8.setConnectTimeout(r2);
        r8.setReadTimeout(r2);
        r2 = 0;
        r8.setUseCaches(r2);
        r3 = 1;
        r8.setDoInput(r3);
        r4 = "https";
        r0 = r0.getProtocol();
        r4.equals(r0);
        r0 = r1.keySet();	 Catch:{ all -> 0x0127 }
        r0 = r0.iterator();	 Catch:{ all -> 0x0127 }
    L_0x0072:
        r4 = r0.hasNext();	 Catch:{ all -> 0x0127 }
        if (r4 == 0) goto L_0x0088;
    L_0x0078:
        r4 = r0.next();	 Catch:{ all -> 0x0127 }
        r4 = (java.lang.String) r4;	 Catch:{ all -> 0x0127 }
        r5 = r1.get(r4);	 Catch:{ all -> 0x0127 }
        r5 = (java.lang.String) r5;	 Catch:{ all -> 0x0127 }
        r8.setRequestProperty(r4, r5);	 Catch:{ all -> 0x0127 }
        goto L_0x0072;
    L_0x0088:
        r0 = r7.getMethod();	 Catch:{ all -> 0x0127 }
        switch(r0) {
            case -1: goto L_0x00cb;
            case 0: goto L_0x00c6;
            case 1: goto L_0x00bd;
            case 2: goto L_0x00b4;
            case 3: goto L_0x00ae;
            case 4: goto L_0x00a8;
            case 5: goto L_0x00a2;
            case 6: goto L_0x009c;
            case 7: goto L_0x0093;
            default: goto L_0x008f;
        };	 Catch:{ all -> 0x0127 }
    L_0x008f:
        r7 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x0127 }
        goto L_0x0121;
    L_0x0093:
        r0 = "PATCH";
        r8.setRequestMethod(r0);	 Catch:{ all -> 0x0127 }
        zza(r8, r7);	 Catch:{ all -> 0x0127 }
        goto L_0x00cb;
    L_0x009c:
        r0 = "TRACE";
        r8.setRequestMethod(r0);	 Catch:{ all -> 0x0127 }
        goto L_0x00cb;
    L_0x00a2:
        r0 = "OPTIONS";
        r8.setRequestMethod(r0);	 Catch:{ all -> 0x0127 }
        goto L_0x00cb;
    L_0x00a8:
        r0 = "HEAD";
        r8.setRequestMethod(r0);	 Catch:{ all -> 0x0127 }
        goto L_0x00cb;
    L_0x00ae:
        r0 = "DELETE";
        r8.setRequestMethod(r0);	 Catch:{ all -> 0x0127 }
        goto L_0x00cb;
    L_0x00b4:
        r0 = "PUT";
        r8.setRequestMethod(r0);	 Catch:{ all -> 0x0127 }
        zza(r8, r7);	 Catch:{ all -> 0x0127 }
        goto L_0x00cb;
    L_0x00bd:
        r0 = "POST";
        r8.setRequestMethod(r0);	 Catch:{ all -> 0x0127 }
        zza(r8, r7);	 Catch:{ all -> 0x0127 }
        goto L_0x00cb;
    L_0x00c6:
        r0 = "GET";
        r8.setRequestMethod(r0);	 Catch:{ all -> 0x0127 }
    L_0x00cb:
        r0 = r8.getResponseCode();	 Catch:{ all -> 0x0127 }
        r1 = -1;
        if (r0 != r1) goto L_0x00da;
    L_0x00d2:
        r7 = new java.io.IOException;	 Catch:{ all -> 0x0127 }
        r0 = "Could not retrieve response code from HttpUrlConnection.";
        r7.<init>(r0);	 Catch:{ all -> 0x0127 }
        throw r7;	 Catch:{ all -> 0x0127 }
    L_0x00da:
        r7 = r7.getMethod();	 Catch:{ all -> 0x0127 }
        r1 = 4;
        if (r7 == r1) goto L_0x00f3;
    L_0x00e1:
        r7 = 100;
        if (r7 > r0) goto L_0x00e9;
    L_0x00e5:
        r7 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r0 < r7) goto L_0x00f3;
    L_0x00e9:
        r7 = 204; // 0xcc float:2.86E-43 double:1.01E-321;
        if (r0 == r7) goto L_0x00f3;
    L_0x00ed:
        r7 = 304; // 0x130 float:4.26E-43 double:1.5E-321;
        if (r0 == r7) goto L_0x00f3;
    L_0x00f1:
        r7 = r3;
        goto L_0x00f4;
    L_0x00f3:
        r7 = r2;
    L_0x00f4:
        if (r7 != 0) goto L_0x0107;
    L_0x00f6:
        r7 = new com.google.android.gms.internal.ads.zzaq;	 Catch:{ all -> 0x0127 }
        r1 = r8.getHeaderFields();	 Catch:{ all -> 0x0127 }
        r1 = zza(r1);	 Catch:{ all -> 0x0127 }
        r7.<init>(r0, r1);	 Catch:{ all -> 0x0127 }
        r8.disconnect();
        return r7;
    L_0x0107:
        r7 = new com.google.android.gms.internal.ads.zzaq;	 Catch:{ all -> 0x011e }
        r1 = r8.getHeaderFields();	 Catch:{ all -> 0x011e }
        r1 = zza(r1);	 Catch:{ all -> 0x011e }
        r2 = r8.getContentLength();	 Catch:{ all -> 0x011e }
        r4 = new com.google.android.gms.internal.ads.zzat;	 Catch:{ all -> 0x011e }
        r4.<init>(r8);	 Catch:{ all -> 0x011e }
        r7.<init>(r0, r1, r2, r4);	 Catch:{ all -> 0x011e }
        return r7;
    L_0x011e:
        r7 = move-exception;
        r2 = r3;
        goto L_0x0128;
    L_0x0121:
        r0 = "Unknown method type.";
        r7.<init>(r0);	 Catch:{ all -> 0x0127 }
        throw r7;	 Catch:{ all -> 0x0127 }
    L_0x0127:
        r7 = move-exception;
    L_0x0128:
        if (r2 != 0) goto L_0x012d;
    L_0x012a:
        r8.disconnect();
    L_0x012d:
        throw r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzas.zza(com.google.android.gms.internal.ads.zzr, java.util.Map):com.google.android.gms.internal.ads.zzaq");
    }

    @VisibleForTesting
    private static List<zzl> zza(Map<String, List<String>> map) {
        ArrayList arrayList = new ArrayList(map.size());
        for (Entry entry : map.entrySet()) {
            if (entry.getKey() != null) {
                for (String zzl : (List) entry.getValue()) {
                    arrayList.add(new zzl((String) entry.getKey(), zzl));
                }
            }
        }
        return arrayList;
    }

    private static InputStream zza(HttpURLConnection httpURLConnection) {
        try {
            return httpURLConnection.getInputStream();
        } catch (IOException unused) {
            return httpURLConnection.getErrorStream();
        }
    }

    private static void zza(HttpURLConnection httpURLConnection, zzr<?> zzr) throws IOException, zza {
        byte[] zzh = zzr.zzh();
        if (zzh != null) {
            httpURLConnection.setDoOutput(true);
            if (!httpURLConnection.getRequestProperties().containsKey(MIME.CONTENT_TYPE)) {
                String str = MIME.CONTENT_TYPE;
                String str2 = "application/x-www-form-urlencoded; charset=";
                String valueOf = String.valueOf("UTF-8");
                httpURLConnection.setRequestProperty(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            }
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.write(zzh);
            dataOutputStream.close();
        }
    }
}
