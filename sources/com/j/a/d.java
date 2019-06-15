package com.j.a;

import org.json.JSONObject;

public final class d {
    private final int a = 15000;
    private final int b = 15000;

    /* renamed from: com.j.a.d$1 */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;
        final /* synthetic */ JSONObject c;
        final /* synthetic */ String d = null;

        AnonymousClass1(String str, String str2, JSONObject jSONObject) {
            this.a = str;
            this.b = str2;
            this.c = jSONObject;
        }

        /* JADX WARNING: Removed duplicated region for block: B:28:0x0084  */
        /* JADX WARNING: Removed duplicated region for block: B:24:0x007e  */
        public final void run() {
            /*
            r6 = this;
            r0 = 0;
            r1 = new java.net.URL;	 Catch:{ Exception -> 0x0082, all -> 0x0078 }
            r2 = r6.a;	 Catch:{ Exception -> 0x0082, all -> 0x0078 }
            r1.<init>(r2);	 Catch:{ Exception -> 0x0082, all -> 0x0078 }
            r1 = r1.openConnection();	 Catch:{ Exception -> 0x0082, all -> 0x0078 }
            r1 = (javax.net.ssl.HttpsURLConnection) r1;	 Catch:{ Exception -> 0x0082, all -> 0x0078 }
            r0 = 15000; // 0x3a98 float:2.102E-41 double:7.411E-320;
            r1.setReadTimeout(r0);	 Catch:{ Exception -> 0x0076, all -> 0x0074 }
            r1.setConnectTimeout(r0);	 Catch:{ Exception -> 0x0076, all -> 0x0074 }
            r0 = r6.b;	 Catch:{ Exception -> 0x0076, all -> 0x0074 }
            r1.setRequestMethod(r0);	 Catch:{ Exception -> 0x0076, all -> 0x0074 }
            r0 = r6.c;	 Catch:{ Exception -> 0x0076, all -> 0x0074 }
            if (r0 == 0) goto L_0x003b;
        L_0x001f:
            r0 = r6.c;	 Catch:{ Exception -> 0x0076, all -> 0x0074 }
            r0 = r0.keys();	 Catch:{ Exception -> 0x0076, all -> 0x0074 }
        L_0x0025:
            r2 = r0.hasNext();	 Catch:{ Exception -> 0x0076, all -> 0x0074 }
            if (r2 == 0) goto L_0x003b;
        L_0x002b:
            r2 = r0.next();	 Catch:{ Exception -> 0x0076, all -> 0x0074 }
            r2 = (java.lang.String) r2;	 Catch:{ Exception -> 0x0076, all -> 0x0074 }
            r3 = r6.c;	 Catch:{ Exception -> 0x0076, all -> 0x0074 }
            r3 = r3.getString(r2);	 Catch:{ Exception -> 0x0076, all -> 0x0074 }
            r1.setRequestProperty(r2, r3);	 Catch:{ Exception -> 0x0076, all -> 0x0074 }
            goto L_0x0025;
        L_0x003b:
            r0 = 0;
            r1.setDoOutput(r0);	 Catch:{ Exception -> 0x0076, all -> 0x0074 }
            r0 = r6.d;	 Catch:{ Exception -> 0x0076, all -> 0x0074 }
            if (r0 == 0) goto L_0x006b;
        L_0x0043:
            r0 = 1;
            r1.setDoInput(r0);	 Catch:{ Exception -> 0x0076, all -> 0x0074 }
            r0 = r1.getOutputStream();	 Catch:{ Exception -> 0x0076, all -> 0x0074 }
            r2 = new java.io.BufferedWriter;	 Catch:{ Exception -> 0x0076, all -> 0x0074 }
            r3 = new java.io.OutputStreamWriter;	 Catch:{ Exception -> 0x0076, all -> 0x0074 }
            r4 = "UTF-8";
            r3.<init>(r0, r4);	 Catch:{ Exception -> 0x0076, all -> 0x0074 }
            r2.<init>(r3);	 Catch:{ Exception -> 0x0076, all -> 0x0074 }
            r3 = r6.d;	 Catch:{ Exception -> 0x0076, all -> 0x0074 }
            r4 = "UTF-8";
            r3 = java.net.URLEncoder.encode(r3, r4);	 Catch:{ Exception -> 0x0076, all -> 0x0074 }
            r2.write(r3);	 Catch:{ Exception -> 0x0076, all -> 0x0074 }
            r2.flush();	 Catch:{ Exception -> 0x0076, all -> 0x0074 }
            r2.close();	 Catch:{ Exception -> 0x0076, all -> 0x0074 }
            r0.close();	 Catch:{ Exception -> 0x0076, all -> 0x0074 }
        L_0x006b:
            r1.getResponseCode();	 Catch:{ Exception -> 0x0076, all -> 0x0074 }
            if (r1 == 0) goto L_0x0087;
        L_0x0070:
            r1.disconnect();
            return;
        L_0x0074:
            r0 = move-exception;
            goto L_0x007c;
        L_0x0076:
            r0 = r1;
            goto L_0x0082;
        L_0x0078:
            r1 = move-exception;
            r5 = r1;
            r1 = r0;
            r0 = r5;
        L_0x007c:
            if (r1 == 0) goto L_0x0081;
        L_0x007e:
            r1.disconnect();
        L_0x0081:
            throw r0;
        L_0x0082:
            if (r0 == 0) goto L_0x0087;
        L_0x0084:
            r0.disconnect();
        L_0x0087:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.j.a.d$AnonymousClass1.run():void");
        }
    }
}
