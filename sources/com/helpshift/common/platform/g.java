package com.helpshift.common.platform;

import android.os.Build.VERSION;
import com.helpshift.a.a.a.d;
import com.helpshift.common.platform.network.b;
import com.helpshift.common.platform.network.f;
import com.helpshift.common.platform.network.i;
import java.util.ArrayList;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

public class g implements b {
    public com.helpshift.common.platform.network.g a(f fVar) {
        if (fVar instanceof i) {
            return a((i) i.class.cast(fVar));
        }
        return b(fVar);
    }

    /* JADX WARNING: Removed duplicated region for block: B:96:0x0217 A:{Catch:{ Exception -> 0x0223 }} */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x021f A:{Catch:{ Exception -> 0x0223 }} */
    private com.helpshift.common.platform.network.g b(com.helpshift.common.platform.network.f r12) {
        /*
        r11 = this;
        r0 = 0;
        r1 = "https://";
        r2 = com.helpshift.common.domain.network.i.a;	 Catch:{ UnknownHostException -> 0x0201, SecurityException | SocketException -> 0x01f0, SecurityException | SocketException -> 0x01f0, SSLPeerUnverifiedException -> 0x01df, SSLHandshakeException -> 0x01ce, IOException -> 0x01bd, all -> 0x01b8 }
        r1 = r1.equals(r2);	 Catch:{ UnknownHostException -> 0x0201, SecurityException | SocketException -> 0x01f0, SecurityException | SocketException -> 0x01f0, SSLPeerUnverifiedException -> 0x01df, SSLHandshakeException -> 0x01ce, IOException -> 0x01bd, all -> 0x01b8 }
        if (r1 == 0) goto L_0x002e;
    L_0x000b:
        r1 = new java.net.URL;	 Catch:{ UnknownHostException -> 0x0201, SecurityException | SocketException -> 0x01f0, SecurityException | SocketException -> 0x01f0, SSLPeerUnverifiedException -> 0x01df, SSLHandshakeException -> 0x01ce, IOException -> 0x01bd, all -> 0x01b8 }
        r2 = r12.c;	 Catch:{ UnknownHostException -> 0x0201, SecurityException | SocketException -> 0x01f0, SecurityException | SocketException -> 0x01f0, SSLPeerUnverifiedException -> 0x01df, SSLHandshakeException -> 0x01ce, IOException -> 0x01bd, all -> 0x01b8 }
        r1.<init>(r2);	 Catch:{ UnknownHostException -> 0x0201, SecurityException | SocketException -> 0x01f0, SecurityException | SocketException -> 0x01f0, SSLPeerUnverifiedException -> 0x01df, SSLHandshakeException -> 0x01ce, IOException -> 0x01bd, all -> 0x01b8 }
        r1 = r1.openConnection();	 Catch:{ UnknownHostException -> 0x0201, SecurityException | SocketException -> 0x01f0, SecurityException | SocketException -> 0x01f0, SSLPeerUnverifiedException -> 0x01df, SSLHandshakeException -> 0x01ce, IOException -> 0x01bd, all -> 0x01b8 }
        r1 = (javax.net.ssl.HttpsURLConnection) r1;	 Catch:{ UnknownHostException -> 0x0201, SecurityException | SocketException -> 0x01f0, SecurityException | SocketException -> 0x01f0, SSLPeerUnverifiedException -> 0x01df, SSLHandshakeException -> 0x01ce, IOException -> 0x01bd, all -> 0x01b8 }
        r2 = r1;
        r2 = (javax.net.ssl.HttpsURLConnection) r2;	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r11.a(r2);	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        goto L_0x003b;
    L_0x001f:
        r0 = move-exception;
        goto L_0x01c1;
    L_0x0022:
        r0 = move-exception;
        goto L_0x01d2;
    L_0x0025:
        r0 = move-exception;
        goto L_0x01e3;
    L_0x0028:
        r0 = move-exception;
        goto L_0x01f4;
    L_0x002b:
        r0 = move-exception;
        goto L_0x0205;
    L_0x002e:
        r1 = new java.net.URL;	 Catch:{ UnknownHostException -> 0x0201, SecurityException | SocketException -> 0x01f0, SecurityException | SocketException -> 0x01f0, SSLPeerUnverifiedException -> 0x01df, SSLHandshakeException -> 0x01ce, IOException -> 0x01bd, all -> 0x01b8 }
        r2 = r12.c;	 Catch:{ UnknownHostException -> 0x0201, SecurityException | SocketException -> 0x01f0, SecurityException | SocketException -> 0x01f0, SSLPeerUnverifiedException -> 0x01df, SSLHandshakeException -> 0x01ce, IOException -> 0x01bd, all -> 0x01b8 }
        r1.<init>(r2);	 Catch:{ UnknownHostException -> 0x0201, SecurityException | SocketException -> 0x01f0, SecurityException | SocketException -> 0x01f0, SSLPeerUnverifiedException -> 0x01df, SSLHandshakeException -> 0x01ce, IOException -> 0x01bd, all -> 0x01b8 }
        r1 = r1.openConnection();	 Catch:{ UnknownHostException -> 0x0201, SecurityException | SocketException -> 0x01f0, SecurityException | SocketException -> 0x01f0, SSLPeerUnverifiedException -> 0x01df, SSLHandshakeException -> 0x01ce, IOException -> 0x01bd, all -> 0x01b8 }
        r1 = (java.net.HttpURLConnection) r1;	 Catch:{ UnknownHostException -> 0x0201, SecurityException | SocketException -> 0x01f0, SecurityException | SocketException -> 0x01f0, SSLPeerUnverifiedException -> 0x01df, SSLHandshakeException -> 0x01ce, IOException -> 0x01bd, all -> 0x01b8 }
    L_0x003b:
        r2 = r12.b;	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r2 = r2.name();	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r1.setRequestMethod(r2);	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r2 = r12.e;	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r1.setConnectTimeout(r2);	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r2 = r12.d;	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r2 = r2.iterator();	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
    L_0x004f:
        r3 = r2.hasNext();	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        if (r3 == 0) goto L_0x0063;
    L_0x0055:
        r3 = r2.next();	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r3 = (com.helpshift.common.platform.network.c) r3;	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r4 = r3.a;	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r3 = r3.b;	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r1.setRequestProperty(r4, r3);	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        goto L_0x004f;
    L_0x0063:
        r2 = r12 instanceof com.helpshift.common.platform.network.e;	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        if (r2 == 0) goto L_0x0094;
    L_0x0067:
        r2 = 1;
        r1.setDoOutput(r2);	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r2 = r1.getOutputStream();	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r3 = new java.io.BufferedWriter;	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r4 = new java.io.OutputStreamWriter;	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r5 = "UTF-8";
        r4.<init>(r2, r5);	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r3.<init>(r4);	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r4 = com.helpshift.common.platform.network.e.class;
        r4 = r4.cast(r12);	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r4 = (com.helpshift.common.platform.network.e) r4;	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r4 = r4.a;	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r3.write(r4);	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r3.flush();	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r3.close();	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r2.flush();	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r2.close();	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
    L_0x0094:
        r2 = r1.getResponseCode();	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r3 = new java.util.ArrayList;	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r3.<init>();	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r4 = r1.getHeaderFields();	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r5 = r4.keySet();	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r5 = r5.iterator();	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
    L_0x00a9:
        r6 = r5.hasNext();	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r7 = 0;
        if (r6 == 0) goto L_0x00d1;
    L_0x00b0:
        r6 = r5.next();	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r6 = (java.lang.String) r6;	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r8 = com.helpshift.common.c.a(r6);	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        if (r8 != 0) goto L_0x00a9;
    L_0x00bc:
        r8 = new com.helpshift.common.platform.network.c;	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r9 = r4.get(r6);	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r9 = (java.util.List) r9;	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r7 = r9.get(r7);	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r7 = (java.lang.String) r7;	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r8.<init>(r6, r7);	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r3.add(r8);	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        goto L_0x00a9;
    L_0x00d1:
        r5 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r2 < r5) goto L_0x0165;
    L_0x00d5:
        r5 = 300; // 0x12c float:4.2E-43 double:1.48E-321;
        if (r2 >= r5) goto L_0x0165;
    L_0x00d9:
        r0 = new java.io.BufferedInputStream;	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r5 = r1.getInputStream();	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r0.<init>(r5);	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r5 = r4.keySet();	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r5 = r5.iterator();	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
    L_0x00ea:
        r6 = r5.hasNext();	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        if (r6 == 0) goto L_0x011f;
    L_0x00f0:
        r6 = r5.next();	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r6 = (java.lang.String) r6;	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r8 = com.helpshift.common.c.a(r6);	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        if (r8 != 0) goto L_0x00ea;
    L_0x00fc:
        r8 = "Content-Encoding";
        r8 = r6.equals(r8);	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        if (r8 == 0) goto L_0x00ea;
    L_0x0104:
        r6 = r4.get(r6);	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r6 = (java.util.List) r6;	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r6 = r6.get(r7);	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r6 = (java.lang.String) r6;	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r8 = "gzip";
        r6 = r6.equals(r8);	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        if (r6 == 0) goto L_0x00ea;
    L_0x0118:
        r6 = new java.util.zip.GZIPInputStream;	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r6.<init>(r0);	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r0 = r6;
        goto L_0x00ea;
    L_0x011f:
        r4 = new java.io.InputStreamReader;	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r4.<init>(r0);	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r5 = new java.io.BufferedReader;	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r5.<init>(r4);	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r6 = new java.lang.StringBuilder;	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r6.<init>();	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
    L_0x012e:
        r7 = r5.readLine();	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        if (r7 == 0) goto L_0x0138;
    L_0x0134:
        r6.append(r7);	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        goto L_0x012e;
    L_0x0138:
        r0.close();	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r4.close();	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r0 = new com.helpshift.common.platform.network.g;	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r4 = r6.toString();	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r0.<init>(r2, r4, r3);	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r2 = r1 instanceof javax.net.ssl.HttpsURLConnection;	 Catch:{ Exception -> 0x0157 }
        if (r2 == 0) goto L_0x0151;
    L_0x014b:
        r2 = r1;
        r2 = (javax.net.ssl.HttpsURLConnection) r2;	 Catch:{ Exception -> 0x0157 }
        r11.b(r2);	 Catch:{ Exception -> 0x0157 }
    L_0x0151:
        if (r1 == 0) goto L_0x0156;
    L_0x0153:
        r1.disconnect();	 Catch:{ Exception -> 0x0157 }
    L_0x0156:
        return r0;
    L_0x0157:
        r0 = move-exception;
        r1 = com.helpshift.common.exception.NetworkException.GENERIC;
        r12 = r12.c;
        r1.route = r12;
        r12 = "Network error";
        r12 = com.helpshift.common.exception.RootAPIException.a(r0, r1, r12);
        throw r12;
    L_0x0165:
        r4 = "Helpshift_HTTPTrnsport";
        r5 = new java.lang.StringBuilder;	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r5.<init>();	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r6 = "Api : ";
        r5.append(r6);	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r6 = r12.c;	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r5.append(r6);	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r6 = " \t Status : ";
        r5.append(r6);	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r5.append(r2);	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r6 = "\t Thread : ";
        r5.append(r6);	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r6 = java.lang.Thread.currentThread();	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r6 = r6.getName();	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r5.append(r6);	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r5 = r5.toString();	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        com.helpshift.util.l.a(r4, r5);	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r4 = new com.helpshift.common.platform.network.g;	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r4.<init>(r2, r0, r3);	 Catch:{ UnknownHostException -> 0x002b, SecurityException | SocketException -> 0x0028, SecurityException | SocketException -> 0x0028, SSLPeerUnverifiedException -> 0x0025, SSLHandshakeException -> 0x0022, IOException -> 0x001f }
        r0 = r1 instanceof javax.net.ssl.HttpsURLConnection;	 Catch:{ Exception -> 0x01aa }
        if (r0 == 0) goto L_0x01a4;
    L_0x019e:
        r0 = r1;
        r0 = (javax.net.ssl.HttpsURLConnection) r0;	 Catch:{ Exception -> 0x01aa }
        r11.b(r0);	 Catch:{ Exception -> 0x01aa }
    L_0x01a4:
        if (r1 == 0) goto L_0x01a9;
    L_0x01a6:
        r1.disconnect();	 Catch:{ Exception -> 0x01aa }
    L_0x01a9:
        return r4;
    L_0x01aa:
        r0 = move-exception;
        r1 = com.helpshift.common.exception.NetworkException.GENERIC;
        r12 = r12.c;
        r1.route = r12;
        r12 = "Network error";
        r12 = com.helpshift.common.exception.RootAPIException.a(r0, r1, r12);
        throw r12;
    L_0x01b8:
        r1 = move-exception;
        r10 = r1;
        r1 = r0;
        r0 = r10;
        goto L_0x0213;
    L_0x01bd:
        r1 = move-exception;
        r10 = r1;
        r1 = r0;
        r0 = r10;
    L_0x01c1:
        r2 = com.helpshift.common.exception.NetworkException.GENERIC;	 Catch:{ all -> 0x0212 }
        r3 = r12.c;	 Catch:{ all -> 0x0212 }
        r2.route = r3;	 Catch:{ all -> 0x0212 }
        r3 = "Network error";
        r0 = com.helpshift.common.exception.RootAPIException.a(r0, r2, r3);	 Catch:{ all -> 0x0212 }
        throw r0;	 Catch:{ all -> 0x0212 }
    L_0x01ce:
        r1 = move-exception;
        r10 = r1;
        r1 = r0;
        r0 = r10;
    L_0x01d2:
        r2 = com.helpshift.common.exception.NetworkException.SSL_HANDSHAKE;	 Catch:{ all -> 0x0212 }
        r3 = r12.c;	 Catch:{ all -> 0x0212 }
        r2.route = r3;	 Catch:{ all -> 0x0212 }
        r3 = "Network error";
        r0 = com.helpshift.common.exception.RootAPIException.a(r0, r2, r3);	 Catch:{ all -> 0x0212 }
        throw r0;	 Catch:{ all -> 0x0212 }
    L_0x01df:
        r1 = move-exception;
        r10 = r1;
        r1 = r0;
        r0 = r10;
    L_0x01e3:
        r2 = com.helpshift.common.exception.NetworkException.SSL_PEER_UNVERIFIED;	 Catch:{ all -> 0x0212 }
        r3 = r12.c;	 Catch:{ all -> 0x0212 }
        r2.route = r3;	 Catch:{ all -> 0x0212 }
        r3 = "Network error";
        r0 = com.helpshift.common.exception.RootAPIException.a(r0, r2, r3);	 Catch:{ all -> 0x0212 }
        throw r0;	 Catch:{ all -> 0x0212 }
    L_0x01f0:
        r1 = move-exception;
        r10 = r1;
        r1 = r0;
        r0 = r10;
    L_0x01f4:
        r2 = com.helpshift.common.exception.NetworkException.NO_CONNECTION;	 Catch:{ all -> 0x0212 }
        r3 = r12.c;	 Catch:{ all -> 0x0212 }
        r2.route = r3;	 Catch:{ all -> 0x0212 }
        r3 = "Network error";
        r0 = com.helpshift.common.exception.RootAPIException.a(r0, r2, r3);	 Catch:{ all -> 0x0212 }
        throw r0;	 Catch:{ all -> 0x0212 }
    L_0x0201:
        r1 = move-exception;
        r10 = r1;
        r1 = r0;
        r0 = r10;
    L_0x0205:
        r2 = com.helpshift.common.exception.NetworkException.UNKNOWN_HOST;	 Catch:{ all -> 0x0212 }
        r3 = r12.c;	 Catch:{ all -> 0x0212 }
        r2.route = r3;	 Catch:{ all -> 0x0212 }
        r3 = "Network error";
        r0 = com.helpshift.common.exception.RootAPIException.a(r0, r2, r3);	 Catch:{ all -> 0x0212 }
        throw r0;	 Catch:{ all -> 0x0212 }
    L_0x0212:
        r0 = move-exception;
    L_0x0213:
        r2 = r1 instanceof javax.net.ssl.HttpsURLConnection;	 Catch:{ Exception -> 0x0223 }
        if (r2 == 0) goto L_0x021d;
    L_0x0217:
        r2 = r1;
        r2 = (javax.net.ssl.HttpsURLConnection) r2;	 Catch:{ Exception -> 0x0223 }
        r11.b(r2);	 Catch:{ Exception -> 0x0223 }
    L_0x021d:
        if (r1 == 0) goto L_0x0222;
    L_0x021f:
        r1.disconnect();	 Catch:{ Exception -> 0x0223 }
    L_0x0222:
        throw r0;
    L_0x0223:
        r0 = move-exception;
        r1 = com.helpshift.common.exception.NetworkException.GENERIC;
        r12 = r12.c;
        r1.route = r12;
        r12 = "Network error";
        r12 = com.helpshift.common.exception.RootAPIException.a(r0, r1, r12);
        throw r12;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.common.platform.g.b(com.helpshift.common.platform.network.f):com.helpshift.common.platform.network.g");
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

    private void b(HttpsURLConnection httpsURLConnection) {
        if (VERSION.SDK_INT >= 16 && VERSION.SDK_INT <= 19 && httpsURLConnection != null) {
            SSLSocketFactory sSLSocketFactory = httpsURLConnection.getSSLSocketFactory();
            if (sSLSocketFactory instanceof d) {
                ((d) sSLSocketFactory).a();
            }
        }
    }

    private boolean a(String str) {
        return "screenshot".equals(str) || "originalFileName".equals(str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:82:0x02ba A:{SYNTHETIC, Splitter:B:82:0x02ba} */
    private com.helpshift.common.platform.network.g a(com.helpshift.common.platform.network.i r15) {
        /*
        r14 = this;
        r0 = 0;
        r1 = new java.net.URL;	 Catch:{ UnknownHostException -> 0x02a6, SecurityException | SocketException -> 0x0295, SecurityException | SocketException -> 0x0295, SSLPeerUnverifiedException -> 0x0284, SSLHandshakeException -> 0x0273, Exception -> 0x0262, all -> 0x025d }
        r2 = r15.c;	 Catch:{ UnknownHostException -> 0x02a6, SecurityException | SocketException -> 0x0295, SecurityException | SocketException -> 0x0295, SSLPeerUnverifiedException -> 0x0284, SSLHandshakeException -> 0x0273, Exception -> 0x0262, all -> 0x025d }
        r1.<init>(r2);	 Catch:{ UnknownHostException -> 0x02a6, SecurityException | SocketException -> 0x0295, SecurityException | SocketException -> 0x0295, SSLPeerUnverifiedException -> 0x0284, SSLHandshakeException -> 0x0273, Exception -> 0x0262, all -> 0x025d }
        r2 = "--";
        r3 = "*****";
        r4 = "\r\n";
        r5 = "https://";
        r6 = com.helpshift.common.domain.network.i.a;	 Catch:{ UnknownHostException -> 0x02a6, SecurityException | SocketException -> 0x0295, SecurityException | SocketException -> 0x0295, SSLPeerUnverifiedException -> 0x0284, SSLHandshakeException -> 0x0273, Exception -> 0x0262, all -> 0x025d }
        r5 = r5.equals(r6);	 Catch:{ UnknownHostException -> 0x02a6, SecurityException | SocketException -> 0x0295, SecurityException | SocketException -> 0x0295, SSLPeerUnverifiedException -> 0x0284, SSLHandshakeException -> 0x0273, Exception -> 0x0262, all -> 0x025d }
        if (r5 == 0) goto L_0x0034;
    L_0x0018:
        r1 = r1.openConnection();	 Catch:{ UnknownHostException -> 0x02a6, SecurityException | SocketException -> 0x0295, SecurityException | SocketException -> 0x0295, SSLPeerUnverifiedException -> 0x0284, SSLHandshakeException -> 0x0273, Exception -> 0x0262, all -> 0x025d }
        r1 = (javax.net.ssl.HttpsURLConnection) r1;	 Catch:{ UnknownHostException -> 0x02a6, SecurityException | SocketException -> 0x0295, SecurityException | SocketException -> 0x0295, SSLPeerUnverifiedException -> 0x0284, SSLHandshakeException -> 0x0273, Exception -> 0x0262, all -> 0x025d }
        r5 = r1;
        r5 = (javax.net.ssl.HttpsURLConnection) r5;	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r14.a(r5);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        goto L_0x003a;
    L_0x0025:
        r0 = move-exception;
        goto L_0x0266;
    L_0x0028:
        r0 = move-exception;
        goto L_0x0277;
    L_0x002b:
        r0 = move-exception;
        goto L_0x0288;
    L_0x002e:
        r0 = move-exception;
        goto L_0x0299;
    L_0x0031:
        r0 = move-exception;
        goto L_0x02aa;
    L_0x0034:
        r1 = r1.openConnection();	 Catch:{ UnknownHostException -> 0x02a6, SecurityException | SocketException -> 0x0295, SecurityException | SocketException -> 0x0295, SSLPeerUnverifiedException -> 0x0284, SSLHandshakeException -> 0x0273, Exception -> 0x0262, all -> 0x025d }
        r1 = (java.net.HttpURLConnection) r1;	 Catch:{ UnknownHostException -> 0x02a6, SecurityException | SocketException -> 0x0295, SecurityException | SocketException -> 0x0295, SSLPeerUnverifiedException -> 0x0284, SSLHandshakeException -> 0x0273, Exception -> 0x0262, all -> 0x025d }
    L_0x003a:
        r5 = 1;
        r1.setDoInput(r5);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r1.setDoOutput(r5);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r5 = 0;
        r1.setUseCaches(r5);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r6 = r15.b;	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r6 = r6.name();	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r1.setRequestMethod(r6);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r6 = r15.e;	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r1.setConnectTimeout(r6);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r6 = r15.e;	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r1.setReadTimeout(r6);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r6 = r15.d;	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r6 = r6.iterator();	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
    L_0x005e:
        r7 = r6.hasNext();	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        if (r7 == 0) goto L_0x0072;
    L_0x0064:
        r7 = r6.next();	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r7 = (com.helpshift.common.platform.network.c) r7;	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r8 = r7.a;	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r7 = r7.b;	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r1.setRequestProperty(r8, r7);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        goto L_0x005e;
    L_0x0072:
        r6 = new java.io.DataOutputStream;	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r7 = r1.getOutputStream();	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r6.<init>(r7);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r7 = new java.lang.StringBuilder;	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r7.<init>();	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r7.append(r2);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r7.append(r3);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r7.append(r4);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r7 = r7.toString();	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r6.writeBytes(r7);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r7 = r15.a;	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r8 = new java.util.ArrayList;	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r9 = r7.keySet();	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r8.<init>(r9);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r8 = r8.iterator();	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
    L_0x009f:
        r9 = r8.hasNext();	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        if (r9 == 0) goto L_0x012e;
    L_0x00a5:
        r9 = r8.next();	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r9 = (java.lang.String) r9;	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r10 = r14.a(r9);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        if (r10 != 0) goto L_0x009f;
    L_0x00b1:
        r10 = r7.get(r9);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r10 = (java.lang.String) r10;	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r11 = new java.lang.StringBuilder;	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r11.<init>();	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r12 = "Content-Disposition: form-data; name=\"";
        r11.append(r12);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r11.append(r9);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r9 = "\"; ";
        r11.append(r9);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r11.append(r4);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r9 = r11.toString();	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r6.writeBytes(r9);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r9 = new java.lang.StringBuilder;	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r9.<init>();	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r11 = "Content-Type: text/plain;charset=UTF-8";
        r9.append(r11);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r9.append(r4);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r9 = r9.toString();	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r6.writeBytes(r9);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r9 = new java.lang.StringBuilder;	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r9.<init>();	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r11 = "Content-Length: ";
        r9.append(r11);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r11 = r10.length();	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r9.append(r11);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r9.append(r4);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r9 = r9.toString();	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r6.writeBytes(r9);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r6.writeBytes(r4);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r9 = new java.lang.StringBuilder;	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r9.<init>();	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r9.append(r10);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r9.append(r4);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r9 = r9.toString();	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r6.writeBytes(r9);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r9 = new java.lang.StringBuilder;	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r9.<init>();	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r9.append(r2);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r9.append(r3);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r9.append(r4);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r9 = r9.toString();	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r6.writeBytes(r9);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        goto L_0x009f;
    L_0x012e:
        r8 = new java.io.File;	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r9 = "screenshot";
        r9 = r7.get(r9);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r9 = (java.lang.String) r9;	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r8.<init>(r9);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r9 = "originalFileName";
        r7 = r7.get(r9);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r7 = (java.lang.String) r7;	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        if (r7 != 0) goto L_0x0149;
    L_0x0145:
        r7 = r8.getName();	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
    L_0x0149:
        r9 = new java.io.FileInputStream;	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r9.<init>(r8);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r10 = new java.lang.StringBuilder;	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r10.<init>();	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r10.append(r2);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r10.append(r3);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r10.append(r4);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r10 = r10.toString();	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r6.writeBytes(r10);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r10 = new java.lang.StringBuilder;	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r10.<init>();	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r11 = "Content-Disposition: form-data; name=\"screenshot\"; filename=\"";
        r10.append(r11);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r10.append(r7);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r7 = "\"";
        r10.append(r7);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r10.append(r4);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r7 = r10.toString();	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r6.writeBytes(r7);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r7 = new java.lang.StringBuilder;	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r7.<init>();	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r10 = "Content-Type: ";
        r7.append(r10);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r10 = r15.f;	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r7.append(r10);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r7.append(r4);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r7 = r7.toString();	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r6.writeBytes(r7);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r7 = new java.lang.StringBuilder;	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r7.<init>();	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r10 = "Content-Length: ";
        r7.append(r10);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r10 = r8.length();	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r7.append(r10);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r7.append(r4);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r7 = r7.toString();	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r6.writeBytes(r7);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r6.writeBytes(r4);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r7 = 1048576; // 0x100000 float:1.469368E-39 double:5.180654E-318;
        r8 = r9.available();	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r8 = java.lang.Math.min(r8, r7);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r10 = new byte[r8];	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r11 = r9.read(r10, r5, r8);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
    L_0x01c6:
        if (r11 <= 0) goto L_0x01d8;
    L_0x01c8:
        r6.write(r10, r5, r8);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r8 = r9.available();	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r8 = java.lang.Math.min(r8, r7);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r11 = r9.read(r10, r5, r8);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        goto L_0x01c6;
    L_0x01d8:
        r6.writeBytes(r4);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r5 = new java.lang.StringBuilder;	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r5.<init>();	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r5.append(r2);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r5.append(r3);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r5.append(r2);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r5.append(r4);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r2 = r5.toString();	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r6.writeBytes(r2);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r9.close();	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r6.flush();	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r6.close();	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r2 = r1.getResponseCode();	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r3 = new java.lang.StringBuilder;	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r3.<init>();	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r4 = r1.getInputStream();	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r5 = new java.io.InputStreamReader;	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r5.<init>(r4);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r4 = new java.io.BufferedReader;	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r4.<init>(r5);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
    L_0x0213:
        r5 = r4.readLine();	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        if (r5 == 0) goto L_0x021d;
    L_0x0219:
        r3.append(r5);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        goto L_0x0213;
    L_0x021d:
        r3 = r3.toString();	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r4 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r2 < r4) goto L_0x0243;
    L_0x0225:
        r4 = 300; // 0x12c float:4.2E-43 double:1.48E-321;
        if (r2 >= r4) goto L_0x0243;
    L_0x0229:
        r4 = new com.helpshift.common.platform.network.g;	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r4.<init>(r2, r3, r0);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        if (r1 == 0) goto L_0x0242;
    L_0x0230:
        r1.disconnect();	 Catch:{ Exception -> 0x0234 }
        goto L_0x0242;
    L_0x0234:
        r0 = move-exception;
        r1 = com.helpshift.common.exception.NetworkException.GENERIC;
        r15 = r15.c;
        r1.route = r15;
        r15 = "Network error";
        r15 = com.helpshift.common.exception.RootAPIException.a(r0, r1, r15);
        throw r15;
    L_0x0242:
        return r4;
    L_0x0243:
        r3 = new com.helpshift.common.platform.network.g;	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        r3.<init>(r2, r0, r0);	 Catch:{ UnknownHostException -> 0x0031, SecurityException | SocketException -> 0x002e, SecurityException | SocketException -> 0x002e, SSLPeerUnverifiedException -> 0x002b, SSLHandshakeException -> 0x0028, Exception -> 0x0025 }
        if (r1 == 0) goto L_0x025c;
    L_0x024a:
        r1.disconnect();	 Catch:{ Exception -> 0x024e }
        goto L_0x025c;
    L_0x024e:
        r0 = move-exception;
        r1 = com.helpshift.common.exception.NetworkException.GENERIC;
        r15 = r15.c;
        r1.route = r15;
        r15 = "Network error";
        r15 = com.helpshift.common.exception.RootAPIException.a(r0, r1, r15);
        throw r15;
    L_0x025c:
        return r3;
    L_0x025d:
        r1 = move-exception;
        r13 = r1;
        r1 = r0;
        r0 = r13;
        goto L_0x02b8;
    L_0x0262:
        r1 = move-exception;
        r13 = r1;
        r1 = r0;
        r0 = r13;
    L_0x0266:
        r2 = com.helpshift.common.exception.NetworkException.GENERIC;	 Catch:{ all -> 0x02b7 }
        r3 = r15.c;	 Catch:{ all -> 0x02b7 }
        r2.route = r3;	 Catch:{ all -> 0x02b7 }
        r3 = "Upload error";
        r0 = com.helpshift.common.exception.RootAPIException.a(r0, r2, r3);	 Catch:{ all -> 0x02b7 }
        throw r0;	 Catch:{ all -> 0x02b7 }
    L_0x0273:
        r1 = move-exception;
        r13 = r1;
        r1 = r0;
        r0 = r13;
    L_0x0277:
        r2 = com.helpshift.common.exception.NetworkException.SSL_HANDSHAKE;	 Catch:{ all -> 0x02b7 }
        r3 = r15.c;	 Catch:{ all -> 0x02b7 }
        r2.route = r3;	 Catch:{ all -> 0x02b7 }
        r3 = "Upload error";
        r0 = com.helpshift.common.exception.RootAPIException.a(r0, r2, r3);	 Catch:{ all -> 0x02b7 }
        throw r0;	 Catch:{ all -> 0x02b7 }
    L_0x0284:
        r1 = move-exception;
        r13 = r1;
        r1 = r0;
        r0 = r13;
    L_0x0288:
        r2 = com.helpshift.common.exception.NetworkException.SSL_PEER_UNVERIFIED;	 Catch:{ all -> 0x02b7 }
        r3 = r15.c;	 Catch:{ all -> 0x02b7 }
        r2.route = r3;	 Catch:{ all -> 0x02b7 }
        r3 = "Upload error";
        r0 = com.helpshift.common.exception.RootAPIException.a(r0, r2, r3);	 Catch:{ all -> 0x02b7 }
        throw r0;	 Catch:{ all -> 0x02b7 }
    L_0x0295:
        r1 = move-exception;
        r13 = r1;
        r1 = r0;
        r0 = r13;
    L_0x0299:
        r2 = com.helpshift.common.exception.NetworkException.NO_CONNECTION;	 Catch:{ all -> 0x02b7 }
        r3 = r15.c;	 Catch:{ all -> 0x02b7 }
        r2.route = r3;	 Catch:{ all -> 0x02b7 }
        r3 = "Upload error";
        r0 = com.helpshift.common.exception.RootAPIException.a(r0, r2, r3);	 Catch:{ all -> 0x02b7 }
        throw r0;	 Catch:{ all -> 0x02b7 }
    L_0x02a6:
        r1 = move-exception;
        r13 = r1;
        r1 = r0;
        r0 = r13;
    L_0x02aa:
        r2 = com.helpshift.common.exception.NetworkException.UNKNOWN_HOST;	 Catch:{ all -> 0x02b7 }
        r3 = r15.c;	 Catch:{ all -> 0x02b7 }
        r2.route = r3;	 Catch:{ all -> 0x02b7 }
        r3 = "Upload error";
        r0 = com.helpshift.common.exception.RootAPIException.a(r0, r2, r3);	 Catch:{ all -> 0x02b7 }
        throw r0;	 Catch:{ all -> 0x02b7 }
    L_0x02b7:
        r0 = move-exception;
    L_0x02b8:
        if (r1 == 0) goto L_0x02cc;
    L_0x02ba:
        r1.disconnect();	 Catch:{ Exception -> 0x02be }
        goto L_0x02cc;
    L_0x02be:
        r0 = move-exception;
        r1 = com.helpshift.common.exception.NetworkException.GENERIC;
        r15 = r15.c;
        r1.route = r15;
        r15 = "Network error";
        r15 = com.helpshift.common.exception.RootAPIException.a(r0, r1, r15);
        throw r15;
    L_0x02cc:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.common.platform.g.a(com.helpshift.common.platform.network.i):com.helpshift.common.platform.network.g");
    }
}
