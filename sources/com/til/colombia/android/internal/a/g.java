package com.til.colombia.android.internal.a;

import android.os.AsyncTask;
import com.til.colombia.android.internal.e;
import com.til.colombia.android.internal.h;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public final class g extends AsyncTask<String, Void, Boolean> {
    private static final int a = 26214400;
    private static final int b = 5242880;
    private final a c;

    public interface a {
        void a(boolean z);
    }

    /* Access modifiers changed, original: protected|final|synthetic */
    public final /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((String[]) objArr);
    }

    /* Access modifiers changed, original: protected|final|synthetic */
    public final /* synthetic */ void onPostExecute(Object obj) {
        a((Boolean) obj);
    }

    public g(a aVar) {
        this.c = aVar;
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:67:0x00b5=Splitter:B:67:0x00b5, B:72:0x00d6=Splitter:B:72:0x00d6} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:72:0x00d6 */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x00dc  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x00d2  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x00e2  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0090 A:{SYNTHETIC, Splitter:B:46:0x0090} */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x009f A:{SYNTHETIC, Splitter:B:53:0x009f} */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0095 A:{Catch:{ IOException -> 0x00b0, Throwable -> 0x00ad, all -> 0x00aa }} */
    private static java.lang.Boolean a(java.lang.String... r8) {
        /*
        r0 = 0;
        if (r8 == 0) goto L_0x00eb;
    L_0x0003:
        r1 = r8.length;
        if (r1 == 0) goto L_0x00eb;
    L_0x0006:
        r1 = r8[r0];
        if (r1 != 0) goto L_0x000c;
    L_0x000a:
        goto L_0x00eb;
    L_0x000c:
        r1 = r0;
        r2 = r1;
    L_0x000e:
        r3 = r8.length;
        if (r1 >= r3) goto L_0x00e6;
    L_0x0011:
        r3 = r8[r1];
        r4 = com.til.colombia.android.commons.a.a.a(r3);
        if (r4 == 0) goto L_0x001b;
    L_0x0019:
        r2 = 1;
        goto L_0x0087;
    L_0x001b:
        r4 = 0;
        r5 = com.til.colombia.android.internal.a.h.a(r3);	 Catch:{ IOException -> 0x00d6, Throwable -> 0x00b4 }
        if (r5 == 0) goto L_0x0023;
    L_0x0022:
        goto L_0x0087;
    L_0x0023:
        r5 = com.til.colombia.android.commons.a.a.a(r3);	 Catch:{ IOException -> 0x00d6, Throwable -> 0x00b4 }
        if (r5 == 0) goto L_0x002a;
    L_0x0029:
        goto L_0x0087;
    L_0x002a:
        r5 = new java.net.URL;	 Catch:{ IOException -> 0x00d6, Throwable -> 0x00b4 }
        r6 = com.til.colombia.android.internal.a.d.a(r3);	 Catch:{ IOException -> 0x00d6, Throwable -> 0x00b4 }
        r5.<init>(r6);	 Catch:{ IOException -> 0x00d6, Throwable -> 0x00b4 }
        r5 = r5.openConnection();	 Catch:{ IOException -> 0x00d6, Throwable -> 0x00b4 }
        r5 = (java.net.HttpURLConnection) r5;	 Catch:{ IOException -> 0x00d6, Throwable -> 0x00b4 }
        r6 = "User-Agent";
        com.til.colombia.android.internal.h.i();	 Catch:{ IOException -> 0x00d6, Throwable -> 0x00b4 }
        r7 = com.til.colombia.android.internal.h.j();	 Catch:{ IOException -> 0x00d6, Throwable -> 0x00b4 }
        r5.setRequestProperty(r6, r7);	 Catch:{ IOException -> 0x00d6, Throwable -> 0x00b4 }
        r6 = 15000; // 0x3a98 float:2.102E-41 double:7.411E-320;
        r5.setConnectTimeout(r6);	 Catch:{ IOException -> 0x00d6, Throwable -> 0x00b4 }
        r5.setReadTimeout(r6);	 Catch:{ IOException -> 0x00d6, Throwable -> 0x00b4 }
        r5.setUseCaches(r0);	 Catch:{ IOException -> 0x00d6, Throwable -> 0x00b4 }
        r5.connect();	 Catch:{ IOException -> 0x00d6, Throwable -> 0x00b4 }
        r6 = r5.getResponseCode();	 Catch:{ IOException -> 0x00b0, Throwable -> 0x00ad, all -> 0x00aa }
        r6 = r6 / 10;
        r7 = 20;
        if (r6 != r7) goto L_0x00a0;
    L_0x005d:
        r6 = r5.getContentLength();	 Catch:{ IOException -> 0x00b0, Throwable -> 0x00ad, all -> 0x00aa }
        r7 = 26214400; // 0x1900000 float:5.2897246E-38 double:1.29516345E-316;
        if (r6 >= r7) goto L_0x00a0;
    L_0x0065:
        r6 = new java.io.BufferedInputStream;	 Catch:{ all -> 0x008d }
        r7 = r5.getInputStream();	 Catch:{ all -> 0x008d }
        r6.<init>(r7);	 Catch:{ all -> 0x008d }
        r3 = com.til.colombia.android.commons.a.a.a(r3, r6);	 Catch:{ all -> 0x008a }
        r6.close();	 Catch:{ IOException -> 0x00b0, Throwable -> 0x00ad, all -> 0x00aa }
        if (r3 != 0) goto L_0x0081;
    L_0x0077:
        r8 = java.lang.Boolean.valueOf(r0);	 Catch:{ IOException -> 0x00b0, Throwable -> 0x00ad, all -> 0x00aa }
        if (r5 == 0) goto L_0x0080;
    L_0x007d:
        r5.disconnect();
    L_0x0080:
        return r8;
    L_0x0081:
        if (r5 == 0) goto L_0x0086;
    L_0x0083:
        r5.disconnect();
    L_0x0086:
        r2 = r3;
    L_0x0087:
        r1 = r1 + 1;
        goto L_0x000e;
    L_0x008a:
        r8 = move-exception;
        r4 = r6;
        goto L_0x008e;
    L_0x008d:
        r8 = move-exception;
    L_0x008e:
        if (r4 == 0) goto L_0x0093;
    L_0x0090:
        r4.close();	 Catch:{ IOException -> 0x00b0, Throwable -> 0x00ad, all -> 0x00aa }
    L_0x0093:
        if (r2 != 0) goto L_0x009f;
    L_0x0095:
        r8 = java.lang.Boolean.valueOf(r0);	 Catch:{ IOException -> 0x00b0, Throwable -> 0x00ad, all -> 0x00aa }
        if (r5 == 0) goto L_0x009e;
    L_0x009b:
        r5.disconnect();
    L_0x009e:
        return r8;
    L_0x009f:
        throw r8;	 Catch:{ IOException -> 0x00b0, Throwable -> 0x00ad, all -> 0x00aa }
    L_0x00a0:
        r8 = java.lang.Boolean.valueOf(r0);	 Catch:{ IOException -> 0x00b0, Throwable -> 0x00ad, all -> 0x00aa }
        if (r5 == 0) goto L_0x00a9;
    L_0x00a6:
        r5.disconnect();
    L_0x00a9:
        return r8;
    L_0x00aa:
        r8 = move-exception;
        r4 = r5;
        goto L_0x00e0;
    L_0x00ad:
        r8 = move-exception;
        r4 = r5;
        goto L_0x00b5;
    L_0x00b0:
        r4 = r5;
        goto L_0x00d6;
    L_0x00b2:
        r8 = move-exception;
        goto L_0x00e0;
    L_0x00b4:
        r8 = move-exception;
    L_0x00b5:
        r1 = "";
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00b2 }
        r3 = "Failed to download : ";
        r2.<init>(r3);	 Catch:{ all -> 0x00b2 }
        r8 = r8.getMessage();	 Catch:{ all -> 0x00b2 }
        r2.append(r8);	 Catch:{ all -> 0x00b2 }
        r8 = r2.toString();	 Catch:{ all -> 0x00b2 }
        com.til.colombia.android.internal.Log.a(r1, r8);	 Catch:{ all -> 0x00b2 }
        r8 = java.lang.Boolean.valueOf(r0);	 Catch:{ all -> 0x00b2 }
        if (r4 == 0) goto L_0x00d5;
    L_0x00d2:
        r4.disconnect();
    L_0x00d5:
        return r8;
    L_0x00d6:
        r8 = java.lang.Boolean.valueOf(r0);	 Catch:{ all -> 0x00b2 }
        if (r4 == 0) goto L_0x00df;
    L_0x00dc:
        r4.disconnect();
    L_0x00df:
        return r8;
    L_0x00e0:
        if (r4 == 0) goto L_0x00e5;
    L_0x00e2:
        r4.disconnect();
    L_0x00e5:
        throw r8;
    L_0x00e6:
        r8 = java.lang.Boolean.valueOf(r2);
        return r8;
    L_0x00eb:
        r8 = java.lang.Boolean.valueOf(r0);
        return r8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.til.colombia.android.internal.a.g.a(java.lang.String[]):java.lang.Boolean");
    }

    private static HttpURLConnection a(String str) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(d.a(str)).openConnection();
        String str2 = e.c;
        h.i();
        httpURLConnection.setRequestProperty(str2, h.j());
        httpURLConnection.setConnectTimeout(15000);
        httpURLConnection.setReadTimeout(15000);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.connect();
        return httpURLConnection;
    }

    /* Access modifiers changed, original: protected|final */
    public final void onCancelled() {
        a(Boolean.valueOf(false));
    }

    private void a(Boolean bool) {
        if (this.c != null) {
            this.c.a(bool.booleanValue());
        }
    }
}
