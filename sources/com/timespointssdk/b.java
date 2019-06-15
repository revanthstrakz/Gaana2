package com.timespointssdk;

import android.os.AsyncTask;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.IOException;

public class b {

    public static class a extends AsyncTask<String, String, String> {
        private final a a;

        public interface a {
            void a(String str);
        }

        public a(a aVar) {
            this.a = aVar;
        }

        /* Access modifiers changed, original: protected|varargs */
        /* renamed from: a */
        public String doInBackground(String... strArr) {
            try {
                return b.b(strArr[0]);
            } catch (IOException e) {
                ThrowableExtension.printStackTrace(e);
                return null;
            }
        }

        /* Access modifiers changed, original: protected */
        /* renamed from: a */
        public void onPostExecute(String str) {
            super.onPostExecute(str);
            if (this.a != null) {
                this.a.a(str);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x009c  */
    private static java.lang.String b(java.lang.String r6) throws java.io.IOException {
        /*
        r0 = "GetDataFromServer";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = 0;
        r3 = new java.net.URL;	 Catch:{ all -> 0x0099 }
        r3.<init>(r6);	 Catch:{ all -> 0x0099 }
        r6 = r3.openConnection();	 Catch:{ all -> 0x0099 }
        r6 = (java.net.HttpURLConnection) r6;	 Catch:{ all -> 0x0099 }
        r3 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r6.setReadTimeout(r3);	 Catch:{ all -> 0x0099 }
        r3 = 15000; // 0x3a98 float:2.102E-41 double:7.411E-320;
        r6.setConnectTimeout(r3);	 Catch:{ all -> 0x0099 }
        r3 = "GET";
        r6.setRequestMethod(r3);	 Catch:{ all -> 0x0099 }
        r3 = 1;
        r6.setDoInput(r3);	 Catch:{ all -> 0x0099 }
        r3 = "Content-Type";
        r4 = "application/json";
        r6.setRequestProperty(r3, r4);	 Catch:{ all -> 0x0099 }
        r3 = "Accept";
        r4 = "application/json";
        r6.setRequestProperty(r3, r4);	 Catch:{ all -> 0x0099 }
        r6.connect();	 Catch:{ all -> 0x0099 }
        r3 = r6.getResponseCode();	 Catch:{ all -> 0x0099 }
        r4 = com.timespointssdk.a.a;	 Catch:{ all -> 0x0099 }
        r4 = r4.booleanValue();	 Catch:{ all -> 0x0099 }
        if (r4 == 0) goto L_0x0057;
    L_0x0043:
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0099 }
        r4.<init>();	 Catch:{ all -> 0x0099 }
        r5 = "The response is: ";
        r4.append(r5);	 Catch:{ all -> 0x0099 }
        r4.append(r3);	 Catch:{ all -> 0x0099 }
        r3 = r4.toString();	 Catch:{ all -> 0x0099 }
        android.util.Log.d(r0, r3);	 Catch:{ all -> 0x0099 }
    L_0x0057:
        r6 = r6.getInputStream();	 Catch:{ all -> 0x0099 }
        r2 = new java.io.BufferedReader;	 Catch:{ all -> 0x0095 }
        r3 = new java.io.InputStreamReader;	 Catch:{ all -> 0x0095 }
        r3.<init>(r6);	 Catch:{ all -> 0x0095 }
        r2.<init>(r3);	 Catch:{ all -> 0x0095 }
    L_0x0065:
        r3 = r2.readLine();	 Catch:{ all -> 0x0095 }
        if (r3 == 0) goto L_0x006f;
    L_0x006b:
        r1.append(r3);	 Catch:{ all -> 0x0095 }
        goto L_0x0065;
    L_0x006f:
        r1 = r1.toString();	 Catch:{ all -> 0x0095 }
        r2 = com.timespointssdk.a.a;	 Catch:{ all -> 0x0095 }
        r2 = r2.booleanValue();	 Catch:{ all -> 0x0095 }
        if (r2 == 0) goto L_0x008f;
    L_0x007b:
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0095 }
        r2.<init>();	 Catch:{ all -> 0x0095 }
        r3 = "responseString : ";
        r2.append(r3);	 Catch:{ all -> 0x0095 }
        r2.append(r1);	 Catch:{ all -> 0x0095 }
        r2 = r2.toString();	 Catch:{ all -> 0x0095 }
        android.util.Log.e(r0, r2);	 Catch:{ all -> 0x0095 }
    L_0x008f:
        if (r6 == 0) goto L_0x0094;
    L_0x0091:
        r6.close();
    L_0x0094:
        return r1;
    L_0x0095:
        r0 = move-exception;
        r2 = r6;
        r6 = r0;
        goto L_0x009a;
    L_0x0099:
        r6 = move-exception;
    L_0x009a:
        if (r2 == 0) goto L_0x009f;
    L_0x009c:
        r2.close();
    L_0x009f:
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.timespointssdk.b.b(java.lang.String):java.lang.String");
    }
}
