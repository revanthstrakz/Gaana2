package com.helpshift.network;

import android.os.Build.VERSION;
import com.helpshift.a.a.a.d;
import com.helpshift.exceptions.InstallException;
import com.helpshift.network.a.a;
import com.helpshift.util.o;
import com.til.colombia.android.internal.e;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.HttpsURLConnection;

public class f implements e {
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00c4  */
    public com.helpshift.network.d a(com.helpshift.network.a.a r8) throws java.io.IOException, com.helpshift.exceptions.InstallException {
        /*
        r7 = this;
        r0 = r8.e();
        r1 = "https://";
        r2 = com.helpshift.common.domain.network.i.a;
        r1 = r1.equals(r2);
        if (r1 == 0) goto L_0x001b;
    L_0x000e:
        r0 = r0.openConnection();
        r0 = (javax.net.ssl.HttpsURLConnection) r0;
        r1 = r0;
        r1 = (javax.net.ssl.HttpsURLConnection) r1;
        r7.a(r1);
        goto L_0x0021;
    L_0x001b:
        r0 = r0.openConnection();
        r0 = (java.net.HttpURLConnection) r0;
    L_0x0021:
        r1 = 0;
        r7.a(r0, r8);	 Catch:{ all -> 0x00c0 }
        r8 = r0.getResponseCode();	 Catch:{ all -> 0x00c0 }
        r2 = -1;
        if (r8 != r2) goto L_0x0034;
    L_0x002c:
        r8 = new java.io.IOException;	 Catch:{ all -> 0x00c0 }
        r2 = "Could not retrieve response code from HttpUrlConnection.";
        r8.<init>(r2);	 Catch:{ all -> 0x00c0 }
        throw r8;	 Catch:{ all -> 0x00c0 }
    L_0x0034:
        r8 = new com.helpshift.network.j;	 Catch:{ all -> 0x00c0 }
        r2 = r0.getResponseCode();	 Catch:{ all -> 0x00c0 }
        r3 = r0.getResponseMessage();	 Catch:{ all -> 0x00c0 }
        r8.<init>(r2, r3);	 Catch:{ all -> 0x00c0 }
        r2 = new com.helpshift.network.d;	 Catch:{ all -> 0x00c0 }
        r2.<init>(r8);	 Catch:{ all -> 0x00c0 }
        r8 = r0.getHeaderFields();	 Catch:{ all -> 0x00be }
        r8 = r8.entrySet();	 Catch:{ all -> 0x00be }
        r8 = r8.iterator();	 Catch:{ all -> 0x00be }
        r1 = 0;
        r3 = r1;
    L_0x0054:
        r4 = r8.hasNext();	 Catch:{ all -> 0x00be }
        if (r4 == 0) goto L_0x00a4;
    L_0x005a:
        r4 = r8.next();	 Catch:{ all -> 0x00be }
        r4 = (java.util.Map.Entry) r4;	 Catch:{ all -> 0x00be }
        r5 = r4.getKey();	 Catch:{ all -> 0x00be }
        if (r5 == 0) goto L_0x0054;
    L_0x0066:
        r5 = r4.getKey();	 Catch:{ all -> 0x00be }
        r5 = (java.lang.String) r5;	 Catch:{ all -> 0x00be }
        r6 = "Content-Encoding";
        r5 = r5.equals(r6);	 Catch:{ all -> 0x00be }
        if (r5 == 0) goto L_0x0089;
    L_0x0074:
        r5 = r4.getValue();	 Catch:{ all -> 0x00be }
        r5 = (java.util.List) r5;	 Catch:{ all -> 0x00be }
        r5 = r5.get(r1);	 Catch:{ all -> 0x00be }
        r5 = (java.lang.String) r5;	 Catch:{ all -> 0x00be }
        r6 = "gzip";
        r5 = r5.equalsIgnoreCase(r6);	 Catch:{ all -> 0x00be }
        if (r5 == 0) goto L_0x0089;
    L_0x0088:
        r3 = 1;
    L_0x0089:
        r5 = new com.helpshift.network.b;	 Catch:{ all -> 0x00be }
        r6 = r4.getKey();	 Catch:{ all -> 0x00be }
        r6 = (java.lang.String) r6;	 Catch:{ all -> 0x00be }
        r4 = r4.getValue();	 Catch:{ all -> 0x00be }
        r4 = (java.util.List) r4;	 Catch:{ all -> 0x00be }
        r4 = r4.get(r1);	 Catch:{ all -> 0x00be }
        r4 = (java.lang.String) r4;	 Catch:{ all -> 0x00be }
        r5.<init>(r6, r4);	 Catch:{ all -> 0x00be }
        r2.a(r5);	 Catch:{ all -> 0x00be }
        goto L_0x0054;
    L_0x00a4:
        r8 = a(r0, r3);	 Catch:{ all -> 0x00be }
        r2.a(r8);	 Catch:{ all -> 0x00be }
        r8 = r7.a(r0);	 Catch:{ all -> 0x00be }
        r2.a(r8);	 Catch:{ all -> 0x00be }
        if (r2 != 0) goto L_0x00bd;
    L_0x00b4:
        r8 = r7.a(r0);
        if (r8 == 0) goto L_0x00bd;
    L_0x00ba:
        r8.a();
    L_0x00bd:
        return r2;
    L_0x00be:
        r8 = move-exception;
        goto L_0x00c2;
    L_0x00c0:
        r8 = move-exception;
        r2 = r1;
    L_0x00c2:
        if (r2 != 0) goto L_0x00cd;
    L_0x00c4:
        r0 = r7.a(r0);
        if (r0 == 0) goto L_0x00cd;
    L_0x00ca:
        r0.a();
    L_0x00cd:
        throw r8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.network.f.a(com.helpshift.network.a.a):com.helpshift.network.d");
    }

    private d a(HttpURLConnection httpURLConnection) {
        if (VERSION.SDK_INT >= 16 && VERSION.SDK_INT <= 19 && (httpURLConnection instanceof HttpsURLConnection)) {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) httpURLConnection;
            if (httpsURLConnection.getSSLSocketFactory() instanceof d) {
                return (d) httpsURLConnection.getSSLSocketFactory();
            }
        }
        return null;
    }

    private void a(HttpsURLConnection httpsURLConnection) {
        if (VERSION.SDK_INT >= 16 && VERSION.SDK_INT <= 19) {
            ArrayList arrayList = new ArrayList();
            arrayList.add("TLSv1.2");
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add("SSLv3");
            httpsURLConnection.setSSLSocketFactory(new d(httpsURLConnection.getSSLSocketFactory(), arrayList, arrayList2));
        }
    }

    private static c a(HttpURLConnection httpURLConnection, boolean z) {
        InputStream gZIPInputStream;
        c cVar = new c();
        if (z) {
            try {
                gZIPInputStream = new GZIPInputStream(new BufferedInputStream(httpURLConnection.getInputStream()));
            } catch (IOException unused) {
                gZIPInputStream = httpURLConnection.getErrorStream();
            }
        } else {
            gZIPInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
        }
        cVar.a = gZIPInputStream;
        cVar.b = (long) httpURLConnection.getContentLength();
        return cVar;
    }

    private void a(HttpURLConnection httpURLConnection, a aVar) throws InstallException, IOException {
        Map c = aVar.c();
        for (String str : c.keySet()) {
            httpURLConnection.addRequestProperty(str, (String) c.get(str));
        }
        httpURLConnection.setConnectTimeout(5000);
        httpURLConnection.setReadTimeout(5000);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setRequestProperty(e.c, String.format(Locale.ENGLISH, "Helpshift-%s/%s/%s", new Object[]{o.c().d().a(), o.c().d().b(), o.c().d().c()}));
        httpURLConnection.setRequestMethod(aVar.a());
        if (aVar.a == 1) {
            httpURLConnection.setDoOutput(aVar.i());
            httpURLConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            bufferedWriter.write(aVar.f());
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
        }
    }
}
