package com.til.colombia.android.network;

import com.til.colombia.android.network.a.a;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;

public final class j implements Runnable {
    private i a;
    private a b;

    j(i iVar, a aVar) {
        this.a = iVar;
        this.b = aVar;
    }

    public final void run() {
        if (a().b / 10 == 20) {
            if (this.b != null) {
                this.b.a();
            }
        } else if (this.b != null) {
            this.b.a(this.a);
        }
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:30:0x0066=Splitter:B:30:0x0066, B:24:0x004e=Splitter:B:24:0x004e} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0034 */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00a0 A:{Catch:{ ConnectTimeoutException -> 0x00a1, IOException -> 0x008c, SecurityException -> 0x0077, IllegalArgumentException -> 0x0062, Exception -> 0x004a, OutOfMemoryError -> 0x0034, all -> 0x00b7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x008b A:{Catch:{ ConnectTimeoutException -> 0x00a1, IOException -> 0x008c, SecurityException -> 0x0077, IllegalArgumentException -> 0x0062, Exception -> 0x004a, OutOfMemoryError -> 0x0034, all -> 0x00b7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0076 A:{Catch:{ ConnectTimeoutException -> 0x00a1, IOException -> 0x008c, SecurityException -> 0x0077, IllegalArgumentException -> 0x0062, Exception -> 0x004a, OutOfMemoryError -> 0x0034, all -> 0x00b7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0044  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00ba  */
    /* JADX WARNING: Missing block: B:26:0x005c, code skipped:
            if (r1 != null) goto L_0x005e;
     */
    /* JADX WARNING: Missing block: B:47:0x00b3, code skipped:
            if (r1 != null) goto L_0x005e;
     */
    private com.til.colombia.android.network.k a() {
        /*
        r5 = this;
        r0 = 0;
        r1 = r5.a;	 Catch:{ ConnectTimeoutException -> 0x00a1, IOException -> 0x008c, SecurityException -> 0x0077, IllegalArgumentException -> 0x0062, Exception -> 0x004a, OutOfMemoryError -> 0x0034 }
        r1 = r1.a;	 Catch:{ ConnectTimeoutException -> 0x00a1, IOException -> 0x008c, SecurityException -> 0x0077, IllegalArgumentException -> 0x0062, Exception -> 0x004a, OutOfMemoryError -> 0x0034 }
        r1 = com.til.colombia.android.internal.HttpClient.a.a(r1);	 Catch:{ ConnectTimeoutException -> 0x00a1, IOException -> 0x008c, SecurityException -> 0x0077, IllegalArgumentException -> 0x0062, Exception -> 0x004a, OutOfMemoryError -> 0x0034 }
        r0 = r1.getInputStream();	 Catch:{ ConnectTimeoutException -> 0x002b, IOException -> 0x0028, SecurityException -> 0x0026, IllegalArgumentException -> 0x0024, Exception -> 0x0022, OutOfMemoryError -> 0x0020 }
        r0 = com.til.colombia.android.commons.CommonUtil.a(r0);	 Catch:{ ConnectTimeoutException -> 0x002b, IOException -> 0x0028, SecurityException -> 0x0026, IllegalArgumentException -> 0x0024, Exception -> 0x0022, OutOfMemoryError -> 0x0020 }
        r2 = r1.getResponseCode();	 Catch:{ ConnectTimeoutException -> 0x002b, IOException -> 0x0028, SecurityException -> 0x0026, IllegalArgumentException -> 0x0024, Exception -> 0x0022, OutOfMemoryError -> 0x0020 }
        r3 = new com.til.colombia.android.network.k;	 Catch:{ ConnectTimeoutException -> 0x002b, IOException -> 0x0028, SecurityException -> 0x0026, IllegalArgumentException -> 0x0024, Exception -> 0x0022, OutOfMemoryError -> 0x0020 }
        r3.<init>(r0, r2);	 Catch:{ ConnectTimeoutException -> 0x002b, IOException -> 0x0028, SecurityException -> 0x0026, IllegalArgumentException -> 0x0024, Exception -> 0x0022, OutOfMemoryError -> 0x0020 }
        if (r1 == 0) goto L_0x001f;
    L_0x001c:
        r1.disconnect();
    L_0x001f:
        return r3;
    L_0x0020:
        r0 = r1;
        goto L_0x0034;
    L_0x0022:
        r0 = move-exception;
        goto L_0x004e;
    L_0x0024:
        r0 = move-exception;
        goto L_0x0066;
    L_0x0026:
        r0 = move-exception;
        goto L_0x007b;
    L_0x0028:
        r0 = move-exception;
        goto L_0x0090;
    L_0x002b:
        r0 = move-exception;
        goto L_0x00a5;
    L_0x002e:
        r1 = move-exception;
        r4 = r1;
        r1 = r0;
        r0 = r4;
        goto L_0x00b8;
    L_0x0034:
        r1 = "Col:aos:4.0.0";
        r2 = "OOM error";
        com.til.colombia.android.internal.Log.a(r1, r2);	 Catch:{ all -> 0x002e }
        r1 = new com.til.colombia.android.network.k;	 Catch:{ all -> 0x002e }
        r2 = com.til.colombia.android.network.ErrorCode.INTERNAL_ERROR;	 Catch:{ all -> 0x002e }
        r1.<init>(r2);	 Catch:{ all -> 0x002e }
        if (r0 == 0) goto L_0x0047;
    L_0x0044:
        r0.disconnect();
    L_0x0047:
        r0 = r1;
        goto L_0x00b6;
    L_0x004a:
        r1 = move-exception;
        r4 = r1;
        r1 = r0;
        r0 = r4;
    L_0x004e:
        r2 = "Col:aos:4.0.0";
        r3 = "Exception";
        com.til.colombia.android.internal.Log.a(r2, r3, r0);	 Catch:{ all -> 0x00b7 }
        r0 = new com.til.colombia.android.network.k;	 Catch:{ all -> 0x00b7 }
        r2 = com.til.colombia.android.network.ErrorCode.INTERNAL_ERROR;	 Catch:{ all -> 0x00b7 }
        r0.<init>(r2);	 Catch:{ all -> 0x00b7 }
        if (r1 == 0) goto L_0x00b6;
    L_0x005e:
        r1.disconnect();
        goto L_0x00b6;
    L_0x0062:
        r1 = move-exception;
        r4 = r1;
        r1 = r0;
        r0 = r4;
    L_0x0066:
        r2 = "Col:aos:4.0.0";
        r3 = "IllegalArgumentException";
        com.til.colombia.android.internal.Log.a(r2, r3, r0);	 Catch:{ all -> 0x00b7 }
        r0 = new com.til.colombia.android.network.k;	 Catch:{ all -> 0x00b7 }
        r2 = com.til.colombia.android.network.ErrorCode.INVALID_REQUEST;	 Catch:{ all -> 0x00b7 }
        r0.<init>(r2);	 Catch:{ all -> 0x00b7 }
        if (r1 == 0) goto L_0x00b6;
    L_0x0076:
        goto L_0x005e;
    L_0x0077:
        r1 = move-exception;
        r4 = r1;
        r1 = r0;
        r0 = r4;
    L_0x007b:
        r2 = "Col:aos:4.0.0";
        r3 = "permission internet";
        com.til.colombia.android.internal.Log.a(r2, r3, r0);	 Catch:{ all -> 0x00b7 }
        r0 = new com.til.colombia.android.network.k;	 Catch:{ all -> 0x00b7 }
        r2 = com.til.colombia.android.network.ErrorCode.CONNECTION_ERROR;	 Catch:{ all -> 0x00b7 }
        r0.<init>(r2);	 Catch:{ all -> 0x00b7 }
        if (r1 == 0) goto L_0x00b6;
    L_0x008b:
        goto L_0x005e;
    L_0x008c:
        r1 = move-exception;
        r4 = r1;
        r1 = r0;
        r0 = r4;
    L_0x0090:
        r2 = "Col:aos:4.0.0";
        r3 = "IOException";
        com.til.colombia.android.internal.Log.a(r2, r3, r0);	 Catch:{ all -> 0x00b7 }
        r0 = new com.til.colombia.android.network.k;	 Catch:{ all -> 0x00b7 }
        r2 = com.til.colombia.android.network.ErrorCode.INTERNAL_ERROR;	 Catch:{ all -> 0x00b7 }
        r0.<init>(r2);	 Catch:{ all -> 0x00b7 }
        if (r1 == 0) goto L_0x00b6;
    L_0x00a0:
        goto L_0x005e;
    L_0x00a1:
        r1 = move-exception;
        r4 = r1;
        r1 = r0;
        r0 = r4;
    L_0x00a5:
        r2 = "Col:aos:4.0.0";
        r3 = "ConnectTimeoutException";
        com.til.colombia.android.internal.Log.a(r2, r3, r0);	 Catch:{ all -> 0x00b7 }
        r0 = new com.til.colombia.android.network.k;	 Catch:{ all -> 0x00b7 }
        r2 = com.til.colombia.android.network.ErrorCode.CONNECTION_ERROR;	 Catch:{ all -> 0x00b7 }
        r0.<init>(r2);	 Catch:{ all -> 0x00b7 }
        if (r1 == 0) goto L_0x00b6;
    L_0x00b5:
        goto L_0x005e;
    L_0x00b6:
        return r0;
    L_0x00b7:
        r0 = move-exception;
    L_0x00b8:
        if (r1 == 0) goto L_0x00bd;
    L_0x00ba:
        r1.disconnect();
    L_0x00bd:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.til.colombia.android.network.j.a():com.til.colombia.android.network.k");
    }

    private static HttpURLConnection a(String str) throws IOException, URISyntaxException {
        return com.til.colombia.android.internal.HttpClient.a.a(str);
    }
}
