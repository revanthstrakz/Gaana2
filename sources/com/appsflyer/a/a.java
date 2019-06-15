package com.appsflyer.a;

import android.content.Context;
import android.util.Log;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class a {
    private static a a = new a();

    public static a a() {
        return a;
    }

    private a() {
    }

    public List<b> b(Context context) {
        ArrayList arrayList = new ArrayList();
        try {
            File file = new File(context.getFilesDir(), "AFRequestCache");
            if (file.exists()) {
                for (File file2 : file.listFiles()) {
                    StringBuilder stringBuilder = new StringBuilder("Found cached request");
                    stringBuilder.append(file2.getName());
                    Log.i("AppsFlyer_4.8.13", stringBuilder.toString());
                    arrayList.add(a(file2));
                }
            } else {
                file.mkdir();
            }
        } catch (Exception unused) {
            Log.i("AppsFlyer_4.8.13", "Could not cache request");
        }
        return arrayList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x002d A:{SYNTHETIC, Splitter:B:21:0x002d} */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0026 A:{SYNTHETIC, Splitter:B:13:0x0026} */
    private static com.appsflyer.a.b a(java.io.File r4) {
        /*
        r0 = 0;
        r1 = new java.io.FileReader;	 Catch:{ Exception -> 0x002a, all -> 0x0022 }
        r1.<init>(r4);	 Catch:{ Exception -> 0x002a, all -> 0x0022 }
        r2 = r4.length();	 Catch:{ Exception -> 0x002b, all -> 0x0020 }
        r2 = (int) r2;	 Catch:{ Exception -> 0x002b, all -> 0x0020 }
        r2 = new char[r2];	 Catch:{ Exception -> 0x002b, all -> 0x0020 }
        r1.read(r2);	 Catch:{ Exception -> 0x002b, all -> 0x0020 }
        r3 = new com.appsflyer.a.b;	 Catch:{ Exception -> 0x002b, all -> 0x0020 }
        r3.<init>(r2);	 Catch:{ Exception -> 0x002b, all -> 0x0020 }
        r4 = r4.getName();	 Catch:{ Exception -> 0x002b, all -> 0x0020 }
        r3.a(r4);	 Catch:{ Exception -> 0x002b, all -> 0x0020 }
        r1.close();	 Catch:{ IOException -> 0x001f }
    L_0x001f:
        return r3;
    L_0x0020:
        r4 = move-exception;
        goto L_0x0024;
    L_0x0022:
        r4 = move-exception;
        r1 = r0;
    L_0x0024:
        if (r1 == 0) goto L_0x0029;
    L_0x0026:
        r1.close();	 Catch:{ IOException -> 0x0029 }
    L_0x0029:
        throw r4;
    L_0x002a:
        r1 = r0;
    L_0x002b:
        if (r1 == 0) goto L_0x0030;
    L_0x002d:
        r1.close();	 Catch:{ IOException -> 0x0030 }
    L_0x0030:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.a.a.a(java.io.File):com.appsflyer.a.b");
    }

    public void a(Context context) {
        try {
            if (!new File(context.getFilesDir(), "AFRequestCache").exists()) {
                new File(context.getFilesDir(), "AFRequestCache").mkdir();
            }
        } catch (Exception unused) {
            Log.i("AppsFlyer_4.8.13", "Could not create cache directory");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x00a9 A:{SYNTHETIC, Splitter:B:34:0x00a9} */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00a1 A:{SYNTHETIC, Splitter:B:28:0x00a1} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0098 */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Can't wrap try/catch for region: R(6:23|22|25|26|(2:28|29)|32) */
    /* JADX WARNING: Missing block: B:24:0x0096, code skipped:
            r6 = th;
     */
    /* JADX WARNING: Missing block: B:35:?, code skipped:
            r0.close();
     */
    public void a(com.appsflyer.a.b r6, android.content.Context r7) {
        /*
        r5 = this;
        r0 = 0;
        r1 = new java.io.File;	 Catch:{ Exception -> 0x0098 }
        r2 = r7.getFilesDir();	 Catch:{ Exception -> 0x0098 }
        r3 = "AFRequestCache";
        r1.<init>(r2, r3);	 Catch:{ Exception -> 0x0098 }
        r2 = r1.exists();	 Catch:{ Exception -> 0x0098 }
        if (r2 != 0) goto L_0x0016;
    L_0x0012:
        r1.mkdir();	 Catch:{ Exception -> 0x0098 }
        return;
    L_0x0016:
        r1 = r1.listFiles();	 Catch:{ Exception -> 0x0098 }
        if (r1 == 0) goto L_0x0029;
    L_0x001c:
        r1 = r1.length;	 Catch:{ Exception -> 0x0098 }
        r2 = 40;
        if (r1 <= r2) goto L_0x0029;
    L_0x0021:
        r6 = "AppsFlyer_4.8.13";
        r7 = "reached cache limit, not caching request";
        android.util.Log.i(r6, r7);	 Catch:{ Exception -> 0x0098 }
        return;
    L_0x0029:
        r1 = "AppsFlyer_4.8.13";
        r2 = "caching request...";
        android.util.Log.i(r1, r2);	 Catch:{ Exception -> 0x0098 }
        r1 = new java.io.File;	 Catch:{ Exception -> 0x0098 }
        r2 = new java.io.File;	 Catch:{ Exception -> 0x0098 }
        r7 = r7.getFilesDir();	 Catch:{ Exception -> 0x0098 }
        r3 = "AFRequestCache";
        r2.<init>(r7, r3);	 Catch:{ Exception -> 0x0098 }
        r3 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x0098 }
        r7 = java.lang.Long.toString(r3);	 Catch:{ Exception -> 0x0098 }
        r1.<init>(r2, r7);	 Catch:{ Exception -> 0x0098 }
        r1.createNewFile();	 Catch:{ Exception -> 0x0098 }
        r7 = new java.io.OutputStreamWriter;	 Catch:{ Exception -> 0x0098 }
        r2 = new java.io.FileOutputStream;	 Catch:{ Exception -> 0x0098 }
        r1 = r1.getPath();	 Catch:{ Exception -> 0x0098 }
        r3 = 1;
        r2.<init>(r1, r3);	 Catch:{ Exception -> 0x0098 }
        r7.<init>(r2);	 Catch:{ Exception -> 0x0098 }
        r0 = "version=";
        r7.write(r0);	 Catch:{ Exception -> 0x0094, all -> 0x0091 }
        r0 = r6.a();	 Catch:{ Exception -> 0x0094, all -> 0x0091 }
        r7.write(r0);	 Catch:{ Exception -> 0x0094, all -> 0x0091 }
        r0 = 10;
        r7.write(r0);	 Catch:{ Exception -> 0x0094, all -> 0x0091 }
        r1 = "url=";
        r7.write(r1);	 Catch:{ Exception -> 0x0094, all -> 0x0091 }
        r1 = r6.c();	 Catch:{ Exception -> 0x0094, all -> 0x0091 }
        r7.write(r1);	 Catch:{ Exception -> 0x0094, all -> 0x0091 }
        r7.write(r0);	 Catch:{ Exception -> 0x0094, all -> 0x0091 }
        r1 = "data=";
        r7.write(r1);	 Catch:{ Exception -> 0x0094, all -> 0x0091 }
        r6 = r6.b();	 Catch:{ Exception -> 0x0094, all -> 0x0091 }
        r7.write(r6);	 Catch:{ Exception -> 0x0094, all -> 0x0091 }
        r7.write(r0);	 Catch:{ Exception -> 0x0094, all -> 0x0091 }
        r7.flush();	 Catch:{ Exception -> 0x0094, all -> 0x0091 }
        r7.close();	 Catch:{ IOException -> 0x0090 }
        return;
    L_0x0090:
        return;
    L_0x0091:
        r6 = move-exception;
        r0 = r7;
        goto L_0x00a7;
    L_0x0094:
        r0 = r7;
        goto L_0x0098;
    L_0x0096:
        r6 = move-exception;
        goto L_0x00a7;
    L_0x0098:
        r6 = "AppsFlyer_4.8.13";
        r7 = "Could not cache request";
        android.util.Log.i(r6, r7);	 Catch:{ all -> 0x0096 }
        if (r0 == 0) goto L_0x00a6;
    L_0x00a1:
        r0.close();	 Catch:{ IOException -> 0x00a5 }
        goto L_0x00a6;
    L_0x00a5:
        return;
    L_0x00a6:
        return;
    L_0x00a7:
        if (r0 == 0) goto L_0x00ac;
    L_0x00a9:
        r0.close();	 Catch:{ IOException -> 0x00ac }
    L_0x00ac:
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.a.a.a(com.appsflyer.a.b, android.content.Context):void");
    }

    public void a(String str, Context context) {
        File file = new File(new File(context.getFilesDir(), "AFRequestCache"), str);
        StringBuilder stringBuilder = new StringBuilder("Deleting ");
        stringBuilder.append(str);
        stringBuilder.append(" from cache");
        Log.i("AppsFlyer_4.8.13", stringBuilder.toString());
        if (file.exists()) {
            try {
                file.delete();
            } catch (Exception e) {
                stringBuilder = new StringBuilder("Could not delete ");
                stringBuilder.append(str);
                stringBuilder.append(" from cache");
                Log.i("AppsFlyer_4.8.13", stringBuilder.toString(), e);
            }
        }
    }
}
