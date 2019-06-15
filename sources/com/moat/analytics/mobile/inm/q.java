package com.moat.analytics.mobile.inm;

import java.io.InputStream;
import java.io.InputStreamReader;

class q {
    q() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x005a A:{SYNTHETIC, Splitter:B:27:0x005a} */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0054 A:{SYNTHETIC, Splitter:B:22:0x0054} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x004e */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Can't wrap try/catch for region: R(6:17|16|19|20|(2:22|23)|24) */
    /* JADX WARNING: Missing block: B:18:0x004c, code skipped:
            r4 = th;
     */
    /* JADX WARNING: Missing block: B:28:?, code skipped:
            r0.close();
     */
    static com.moat.analytics.mobile.inm.a.b.a<java.lang.String> a(java.lang.String r4) {
        /*
        r0 = 0;
        r1 = new java.net.URL;	 Catch:{ IOException -> 0x004e }
        r1.<init>(r4);	 Catch:{ IOException -> 0x004e }
        r4 = r1.openConnection();	 Catch:{ IOException -> 0x004e }
        r4 = (java.net.HttpURLConnection) r4;	 Catch:{ IOException -> 0x004e }
        r1 = 0;
        r4.setUseCaches(r1);	 Catch:{ IOException -> 0x004e }
        r1 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r4.setReadTimeout(r1);	 Catch:{ IOException -> 0x004e }
        r1 = 15000; // 0x3a98 float:2.102E-41 double:7.411E-320;
        r4.setConnectTimeout(r1);	 Catch:{ IOException -> 0x004e }
        r1 = "GET";
        r4.setRequestMethod(r1);	 Catch:{ IOException -> 0x004e }
        r1 = 1;
        r4.setDoInput(r1);	 Catch:{ IOException -> 0x004e }
        r4.connect();	 Catch:{ IOException -> 0x004e }
        r1 = r4.getResponseCode();	 Catch:{ IOException -> 0x004e }
        r2 = 400; // 0x190 float:5.6E-43 double:1.976E-321;
        if (r1 < r2) goto L_0x0033;
    L_0x002e:
        r4 = com.moat.analytics.mobile.inm.a.b.a.a();	 Catch:{ IOException -> 0x004e }
        return r4;
    L_0x0033:
        r4 = r4.getInputStream();	 Catch:{ IOException -> 0x004e }
        r0 = a(r4);	 Catch:{ IOException -> 0x004a, all -> 0x0045 }
        r0 = com.moat.analytics.mobile.inm.a.b.a.a(r0);	 Catch:{ IOException -> 0x004a, all -> 0x0045 }
        if (r4 == 0) goto L_0x0044;
    L_0x0041:
        r4.close();	 Catch:{ IOException -> 0x0044 }
    L_0x0044:
        return r0;
    L_0x0045:
        r0 = move-exception;
        r3 = r0;
        r0 = r4;
        r4 = r3;
        goto L_0x0058;
    L_0x004a:
        r0 = r4;
        goto L_0x004e;
    L_0x004c:
        r4 = move-exception;
        goto L_0x0058;
    L_0x004e:
        r4 = com.moat.analytics.mobile.inm.a.b.a.a();	 Catch:{ all -> 0x004c }
        if (r0 == 0) goto L_0x0057;
    L_0x0054:
        r0.close();	 Catch:{ IOException -> 0x0057 }
    L_0x0057:
        return r4;
    L_0x0058:
        if (r0 == 0) goto L_0x005d;
    L_0x005a:
        r0.close();	 Catch:{ IOException -> 0x005d }
    L_0x005d:
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moat.analytics.mobile.inm.q.a(java.lang.String):com.moat.analytics.mobile.inm.a.b.a");
    }

    private static String a(InputStream inputStream) {
        char[] cArr = new char[256];
        StringBuilder stringBuilder = new StringBuilder();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
        int i = 0;
        do {
            int read = inputStreamReader.read(cArr, 0, 256);
            if (read <= 0) {
                break;
            }
            i += read;
            stringBuilder.append(cArr, 0, read);
        } while (i < 1024);
        return stringBuilder.toString();
    }

    static void b(final String str) {
        new Thread() {
            public final void run() {
                try {
                    q.a(str);
                } catch (Exception unused) {
                }
            }
        }.start();
    }
}
