package com.appsflyer;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import java.io.File;
import java.lang.ref.WeakReference;
import java.security.SecureRandom;
import java.util.UUID;

final class ac {
    private static String a;

    public static synchronized String a(WeakReference<Context> weakReference) {
        synchronized (ac.class) {
            String str;
            if (weakReference.get() == null) {
                str = a;
                return str;
            }
            if (a == null) {
                String str2 = null;
                if (weakReference.get() != null) {
                    str2 = ((Context) weakReference.get()).getSharedPreferences("appsflyer-data", 0).getString("AF_INSTALLATION", null);
                }
                if (str2 != null) {
                    a = str2;
                } else {
                    try {
                        String obj;
                        File file = new File(((Context) weakReference.get()).getFilesDir(), "AF_INSTALLATION");
                        if (file.exists()) {
                            a = a(file);
                            file.delete();
                        } else {
                            if (VERSION.SDK_INT >= 9) {
                                long currentTimeMillis = System.currentTimeMillis();
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append(currentTimeMillis);
                                stringBuilder.append("-");
                                stringBuilder.append(Math.abs(new SecureRandom().nextLong()));
                                obj = stringBuilder.toString();
                            } else {
                                obj = UUID.randomUUID().toString();
                            }
                            a = obj;
                        }
                        obj = a;
                        Editor edit = ((Context) weakReference.get()).getSharedPreferences("appsflyer-data", 0).edit();
                        edit.putString("AF_INSTALLATION", obj);
                        if (VERSION.SDK_INT >= 9) {
                            edit.apply();
                        } else {
                            edit.commit();
                        }
                    } catch (Exception e) {
                        AFLogger.a("Error getting AF unique ID", e);
                    }
                }
                if (a != null) {
                    i.a().a("uid", a);
                }
            }
            str = a;
            return str;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0038 A:{SYNTHETIC, Splitter:B:24:0x0038} */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0051 A:{SYNTHETIC, Splitter:B:35:0x0051} */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0038 A:{SYNTHETIC, Splitter:B:24:0x0038} */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0025 A:{ExcHandler: all (th java.lang.Throwable), Splitter:B:3:0x0008} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:11:0x0020, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:12:0x0021, code skipped:
            r4 = r1;
            r1 = r5;
            r5 = r0;
     */
    /* JADX WARNING: Missing block: B:13:0x0025, code skipped:
            r5 = th;
     */
    /* JADX WARNING: Missing block: B:14:0x0026, code skipped:
            r0 = r1;
     */
    /* JADX WARNING: Missing block: B:25:?, code skipped:
            r0.close();
     */
    /* JADX WARNING: Missing block: B:26:0x003c, code skipped:
            r5 = move-exception;
     */
    /* JADX WARNING: Missing block: B:27:0x003d, code skipped:
            com.appsflyer.AFLogger.a("Exception while trying to close the InstallationFile", r5);
     */
    /* JADX WARNING: Missing block: B:36:?, code skipped:
            r0.close();
     */
    /* JADX WARNING: Missing block: B:37:0x0055, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:38:0x0056, code skipped:
            com.appsflyer.AFLogger.a("Exception while trying to close the InstallationFile", r0);
     */
    private static java.lang.String a(java.io.File r5) {
        /*
        r0 = 0;
        r1 = new java.io.RandomAccessFile;	 Catch:{ IOException -> 0x002f }
        r2 = "r";
        r1.<init>(r5, r2);	 Catch:{ IOException -> 0x002f }
        r2 = r1.length();	 Catch:{ IOException -> 0x0028, all -> 0x0025 }
        r5 = (int) r2;	 Catch:{ IOException -> 0x0028, all -> 0x0025 }
        r5 = new byte[r5];	 Catch:{ IOException -> 0x0028, all -> 0x0025 }
        r1.readFully(r5);	 Catch:{ IOException -> 0x0020, all -> 0x0025 }
        r1.close();	 Catch:{ IOException -> 0x0020, all -> 0x0025 }
        r1.close();	 Catch:{ IOException -> 0x0019 }
        goto L_0x0043;
    L_0x0019:
        r0 = move-exception;
        r1 = "Exception while trying to close the InstallationFile";
        com.appsflyer.AFLogger.a(r1, r0);
        goto L_0x0043;
    L_0x0020:
        r0 = move-exception;
        r4 = r1;
        r1 = r5;
        r5 = r0;
        goto L_0x002b;
    L_0x0025:
        r5 = move-exception;
        r0 = r1;
        goto L_0x004f;
    L_0x0028:
        r5 = move-exception;
        r4 = r1;
        r1 = r0;
    L_0x002b:
        r0 = r4;
        goto L_0x0031;
    L_0x002d:
        r5 = move-exception;
        goto L_0x004f;
    L_0x002f:
        r5 = move-exception;
        r1 = r0;
    L_0x0031:
        r2 = "Exception while reading InstallationFile: ";
        com.appsflyer.AFLogger.a(r2, r5);	 Catch:{ all -> 0x002d }
        if (r0 == 0) goto L_0x0042;
    L_0x0038:
        r0.close();	 Catch:{ IOException -> 0x003c }
        goto L_0x0042;
    L_0x003c:
        r5 = move-exception;
        r0 = "Exception while trying to close the InstallationFile";
        com.appsflyer.AFLogger.a(r0, r5);
    L_0x0042:
        r5 = r1;
    L_0x0043:
        r0 = new java.lang.String;
        if (r5 == 0) goto L_0x0048;
    L_0x0047:
        goto L_0x004b;
    L_0x0048:
        r5 = 0;
        r5 = new byte[r5];
    L_0x004b:
        r0.<init>(r5);
        return r0;
    L_0x004f:
        if (r0 == 0) goto L_0x005b;
    L_0x0051:
        r0.close();	 Catch:{ IOException -> 0x0055 }
        goto L_0x005b;
    L_0x0055:
        r0 = move-exception;
        r1 = "Exception while trying to close the InstallationFile";
        com.appsflyer.AFLogger.a(r1, r0);
    L_0x005b:
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.ac.a(java.io.File):java.lang.String");
    }
}
