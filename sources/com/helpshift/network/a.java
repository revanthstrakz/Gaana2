package com.helpshift.network;

import com.helpshift.common.domain.network.i;
import com.helpshift.common.domain.network.j;
import com.helpshift.network.c.f;
import com.helpshift.network.errors.NetworkError;
import com.helpshift.util.l;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.TreeMap;

public class a implements h {
    protected final e a;
    protected final com.helpshift.network.c.a b = new com.helpshift.network.c.a(i.b);

    public a(e eVar) {
        this.a = eVar;
    }

    protected static Map<String, String> a(b[] bVarArr) {
        TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        for (b bVar : bVarArr) {
            treeMap.put(bVar.a, bVar.b);
        }
        return treeMap;
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:102:0x0180 */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x014c A:{Catch:{ SocketTimeoutException -> 0x0180, MalformedURLException -> 0x0161, InstallException -> 0x015a, SecurityException -> 0x0152, IOException -> 0x013d, all -> 0x013a }} */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x0140 A:{SYNTHETIC, Splitter:B:85:0x0140} */
    /* JADX WARNING: Missing block: B:38:?, code skipped:
            r0 = r7.entrySet().iterator();
     */
    /* JADX WARNING: Missing block: B:40:0x00ab, code skipped:
            if (r0.hasNext() == false) goto L_0x00d6;
     */
    /* JADX WARNING: Missing block: B:41:0x00ad, code skipped:
            r2 = (java.util.Map.Entry) r0.next();
     */
    /* JADX WARNING: Missing block: B:42:0x00bd, code skipped:
            if (r2.getKey().equals("HS-UEpoch") == false) goto L_0x00a7;
     */
    /* JADX WARNING: Missing block: B:43:0x00bf, code skipped:
            com.helpshift.k.b.a().b.a(java.lang.Float.valueOf(com.helpshift.common.util.a.a((java.lang.String) r2.getValue())));
     */
    /* JADX WARNING: Missing block: B:45:0x00dd, code skipped:
            throw new com.helpshift.network.errors.NetworkError(com.helpshift.common.domain.network.j.u);
     */
    public com.helpshift.network.b.d a(com.helpshift.network.a.a r12) throws com.helpshift.network.errors.NetworkError {
        /*
        r11 = this;
    L_0x0000:
        r0 = 0;
        r1 = r11.a;	 Catch:{ SocketTimeoutException -> 0x0180, MalformedURLException -> 0x0161, InstallException -> 0x015a, SecurityException -> 0x0152, IOException -> 0x013d }
        r1 = r1.a(r12);	 Catch:{ SocketTimeoutException -> 0x0180, MalformedURLException -> 0x0161, InstallException -> 0x015a, SecurityException -> 0x0152, IOException -> 0x013d }
        r0 = r1.b();	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        r3 = r0.a();	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        r0 = r1.c();	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        r7 = a(r0);	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        if (r7 == 0) goto L_0x0034;
    L_0x0019:
        r0 = "ETag";
        r0 = r7.containsKey(r0);	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        if (r0 == 0) goto L_0x0034;
    L_0x0021:
        r0 = com.helpshift.k.b.a();	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        r0 = r0.b;	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        r2 = "ETag";
        r2 = r7.get(r2);	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        r2 = (java.lang.String) r2;	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        r4 = r12.b;	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        r0.a(r2, r4);	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
    L_0x0034:
        r0 = 304; // 0x130 float:4.26E-43 double:1.5E-321;
        if (r3 != r0) goto L_0x005a;
    L_0x0038:
        r0 = new com.helpshift.network.b.d;	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        r5 = 304; // 0x130 float:4.26E-43 double:1.5E-321;
        r6 = 0;
        r8 = 1;
        r2 = r12.b();	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        r9 = java.lang.Integer.valueOf(r2);	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        r4 = r0;
        r4.<init>(r5, r6, r7, r8, r9);	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        if (r1 == 0) goto L_0x0059;
    L_0x004c:
        r12 = r1.d();
        if (r12 == 0) goto L_0x0059;
    L_0x0052:
        r12 = r1.d();
        r12.a();
    L_0x0059:
        return r0;
    L_0x005a:
        r0 = r1.a();	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        if (r0 == 0) goto L_0x006a;
    L_0x0060:
        r0 = r1.a();	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        r0 = r11.a(r0);	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
    L_0x0068:
        r4 = r0;
        goto L_0x0072;
    L_0x006a:
        r0 = r12.a;	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        if (r0 != 0) goto L_0x0126;
    L_0x006e:
        r0 = 0;
        r0 = new byte[r0];	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        goto L_0x0068;
    L_0x0072:
        r0 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r3 < r0) goto L_0x009b;
    L_0x0076:
        r0 = 300; // 0x12c float:4.2E-43 double:1.48E-321;
        if (r3 > r0) goto L_0x009b;
    L_0x007a:
        r0 = new com.helpshift.network.b.d;	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        r6 = 0;
        r2 = r12.b();	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        r8 = java.lang.Integer.valueOf(r2);	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        r2 = r0;
        r5 = r7;
        r7 = r8;
        r2.<init>(r3, r4, r5, r6, r7);	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        if (r1 == 0) goto L_0x009a;
    L_0x008d:
        r12 = r1.d();
        if (r12 == 0) goto L_0x009a;
    L_0x0093:
        r12 = r1.d();
        r12.a();
    L_0x009a:
        return r0;
    L_0x009b:
        r0 = 422; // 0x1a6 float:5.91E-43 double:2.085E-321;
        if (r3 != r0) goto L_0x00de;
    L_0x009f:
        r0 = r7.entrySet();	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        r0 = r0.iterator();	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
    L_0x00a7:
        r2 = r0.hasNext();	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        if (r2 == 0) goto L_0x00d6;
    L_0x00ad:
        r2 = r0.next();	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        r2 = (java.util.Map.Entry) r2;	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        r3 = r2.getKey();	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        r4 = "HS-UEpoch";
        r3 = r3.equals(r4);	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        if (r3 == 0) goto L_0x00a7;
    L_0x00bf:
        r0 = com.helpshift.k.b.a();	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        r0 = r0.b;	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        r2 = r2.getValue();	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        r2 = (java.lang.String) r2;	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        r2 = com.helpshift.common.util.a.a(r2);	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        r2 = java.lang.Float.valueOf(r2);	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        r0.a(r2);	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
    L_0x00d6:
        r0 = new com.helpshift.network.errors.NetworkError;	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        r2 = com.helpshift.common.domain.network.j.u;	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        r0.<init>(r2);	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        throw r0;	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
    L_0x00de:
        r0 = 413; // 0x19d float:5.79E-43 double:2.04E-321;
        if (r3 != r0) goto L_0x00ea;
    L_0x00e2:
        r0 = new com.helpshift.network.errors.NetworkError;	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        r2 = com.helpshift.common.domain.network.j.s;	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        r0.<init>(r2);	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        throw r0;	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
    L_0x00ea:
        r0 = 403; // 0x193 float:5.65E-43 double:1.99E-321;
        if (r3 == r0) goto L_0x011e;
    L_0x00ee:
        r0 = 401; // 0x191 float:5.62E-43 double:1.98E-321;
        if (r3 != r0) goto L_0x00f3;
    L_0x00f2:
        goto L_0x011e;
    L_0x00f3:
        r0 = 400; // 0x190 float:5.6E-43 double:1.976E-321;
        r2 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
        if (r3 < r0) goto L_0x0103;
    L_0x00f9:
        if (r3 >= r2) goto L_0x0103;
    L_0x00fb:
        r0 = new com.helpshift.network.errors.NetworkError;	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        r2 = com.helpshift.common.domain.network.j.j;	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        r0.<init>(r2);	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        throw r0;	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
    L_0x0103:
        if (r3 < r2) goto L_0x010d;
    L_0x0105:
        r0 = new com.helpshift.network.errors.NetworkError;	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        r2 = com.helpshift.common.domain.network.j.v;	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        r0.<init>(r2);	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        throw r0;	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
    L_0x010d:
        if (r1 == 0) goto L_0x0000;
    L_0x010f:
        r0 = r1.d();
        if (r0 == 0) goto L_0x0000;
    L_0x0115:
        r0 = r1.d();
        r0.a();
        goto L_0x0000;
    L_0x011e:
        r0 = new com.helpshift.network.errors.NetworkError;	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        r2 = com.helpshift.common.domain.network.j.k;	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        r0.<init>(r2);	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        throw r0;	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
    L_0x0126:
        r0 = new com.helpshift.network.errors.NetworkError;	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        r2 = com.helpshift.common.domain.network.j.m;	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        r0.<init>(r2);	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
        throw r0;	 Catch:{ SocketTimeoutException -> 0x0138, MalformedURLException -> 0x0136, InstallException -> 0x0133, SecurityException -> 0x0131, IOException -> 0x012e }
    L_0x012e:
        r12 = move-exception;
        r0 = r1;
        goto L_0x013e;
    L_0x0131:
        r0 = r1;
        goto L_0x0152;
    L_0x0133:
        r12 = move-exception;
        r0 = r1;
        goto L_0x015b;
    L_0x0136:
        r0 = move-exception;
        goto L_0x0165;
    L_0x0138:
        r0 = r1;
        goto L_0x0180;
    L_0x013a:
        r12 = move-exception;
        r1 = r0;
        goto L_0x0188;
    L_0x013d:
        r12 = move-exception;
    L_0x013e:
        if (r0 != 0) goto L_0x014c;
    L_0x0140:
        r1 = new com.helpshift.network.errors.NetworkError;	 Catch:{ all -> 0x013a }
        r2 = com.helpshift.common.domain.network.j.a;	 Catch:{ all -> 0x013a }
        r12 = r12.getMessage();	 Catch:{ all -> 0x013a }
        r1.<init>(r2, r12);	 Catch:{ all -> 0x013a }
        throw r1;	 Catch:{ all -> 0x013a }
    L_0x014c:
        r1 = new com.helpshift.network.errors.NetworkError;	 Catch:{ all -> 0x013a }
        r1.<init>(r12);	 Catch:{ all -> 0x013a }
        throw r1;	 Catch:{ all -> 0x013a }
    L_0x0152:
        r12 = new com.helpshift.network.errors.NetworkError;	 Catch:{ all -> 0x013a }
        r1 = com.helpshift.common.domain.network.j.a;	 Catch:{ all -> 0x013a }
        r12.<init>(r1);	 Catch:{ all -> 0x013a }
        throw r12;	 Catch:{ all -> 0x013a }
    L_0x015a:
        r12 = move-exception;
    L_0x015b:
        r1 = new com.helpshift.network.errors.NetworkError;	 Catch:{ all -> 0x013a }
        r1.<init>(r12);	 Catch:{ all -> 0x013a }
        throw r1;	 Catch:{ all -> 0x013a }
    L_0x0161:
        r1 = move-exception;
        r10 = r1;
        r1 = r0;
        r0 = r10;
    L_0x0165:
        r2 = new com.helpshift.network.errors.NetworkError;	 Catch:{ all -> 0x017e }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x017e }
        r3.<init>();	 Catch:{ all -> 0x017e }
        r4 = "Bad URL ";
        r3.append(r4);	 Catch:{ all -> 0x017e }
        r12 = r12.b;	 Catch:{ all -> 0x017e }
        r3.append(r12);	 Catch:{ all -> 0x017e }
        r12 = r3.toString();	 Catch:{ all -> 0x017e }
        r2.<init>(r12, r0);	 Catch:{ all -> 0x017e }
        throw r2;	 Catch:{ all -> 0x017e }
    L_0x017e:
        r12 = move-exception;
        goto L_0x0188;
    L_0x0180:
        r12 = new com.helpshift.network.errors.NetworkError;	 Catch:{ all -> 0x013a }
        r1 = com.helpshift.common.domain.network.j.p;	 Catch:{ all -> 0x013a }
        r12.<init>(r1);	 Catch:{ all -> 0x013a }
        throw r12;	 Catch:{ all -> 0x013a }
    L_0x0188:
        if (r1 == 0) goto L_0x0197;
    L_0x018a:
        r0 = r1.d();
        if (r0 == 0) goto L_0x0197;
    L_0x0190:
        r0 = r1.d();
        r0.a();
    L_0x0197:
        throw r12;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.network.a.a(com.helpshift.network.a.a):com.helpshift.network.b.d");
    }

    /* Access modifiers changed, original: protected */
    public byte[] a(c cVar) throws IOException, NetworkError {
        Throwable th;
        f fVar = new f(this.b, (int) cVar.b);
        byte[] bArr = null;
        try {
            InputStream inputStream = cVar.a;
            if (inputStream == null) {
                throw new NetworkError(j.v);
            }
            byte[] a = this.b.a(1024);
            while (true) {
                try {
                    int read = inputStream.read(a);
                    if (read == -1) {
                        break;
                    }
                    fVar.write(a, 0, read);
                } catch (Throwable th2) {
                    th = th2;
                    bArr = a;
                    try {
                        cVar.a();
                    } catch (IOException e) {
                        l.b("Helpshift_BasicNetwork", "Error occurred when calling consumingContent", e);
                    }
                    this.b.a(bArr);
                    fVar.close();
                    throw th;
                }
            }
            bArr = fVar.toByteArray();
            try {
                cVar.a();
            } catch (IOException e2) {
                l.b("Helpshift_BasicNetwork", "Error occurred when calling consumingContent", e2);
            }
            this.b.a(a);
            fVar.close();
            return bArr;
        } catch (Throwable th3) {
            th = th3;
            cVar.a();
            this.b.a(bArr);
            fVar.close();
            throw th;
        }
    }
}
