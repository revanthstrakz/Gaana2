package com.helpshift.util;

import android.os.Environment;
import java.io.File;
import java.io.Serializable;
import java.util.HashMap;

public class e {
    private static String a;

    private static synchronized String a() {
        String str;
        synchronized (e.class) {
            if (a == null) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(".backups/");
                stringBuilder.append(o.b().getPackageName());
                stringBuilder.append("/helpshift/databases/");
                a = stringBuilder.toString();
            }
            str = a;
        }
        return str;
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0022 A:{SYNTHETIC, Splitter:B:20:0x0022} */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x001c A:{SYNTHETIC, Splitter:B:14:0x001c} */
    private static void a(java.io.File r3, java.util.HashMap<java.lang.String, java.io.Serializable> r4) {
        /*
        r0 = 0;
        r1 = new java.io.ObjectOutputStream;	 Catch:{ IOException -> 0x0020, all -> 0x0019 }
        r2 = new java.io.FileOutputStream;	 Catch:{ IOException -> 0x0020, all -> 0x0019 }
        r2.<init>(r3);	 Catch:{ IOException -> 0x0020, all -> 0x0019 }
        r1.<init>(r2);	 Catch:{ IOException -> 0x0020, all -> 0x0019 }
        r1.writeObject(r4);	 Catch:{ IOException -> 0x0017, all -> 0x0014 }
        if (r1 == 0) goto L_0x0025;
    L_0x0010:
        r1.close();	 Catch:{ Exception -> 0x0025 }
        goto L_0x0025;
    L_0x0014:
        r3 = move-exception;
        r0 = r1;
        goto L_0x001a;
    L_0x0017:
        r0 = r1;
        goto L_0x0020;
    L_0x0019:
        r3 = move-exception;
    L_0x001a:
        if (r0 == 0) goto L_0x001f;
    L_0x001c:
        r0.close();	 Catch:{ Exception -> 0x001f }
    L_0x001f:
        throw r3;
    L_0x0020:
        if (r0 == 0) goto L_0x0025;
    L_0x0022:
        r0.close();	 Catch:{ Exception -> 0x0025 }
    L_0x0025:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.util.e.a(java.io.File, java.util.HashMap):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0059 A:{SYNTHETIC, Splitter:B:31:0x0059} */
    /* JADX WARNING: Removed duplicated region for block: B:39:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x005e A:{Catch:{ Exception -> 0x0061 }} */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x004d A:{SYNTHETIC, Splitter:B:21:0x004d} */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0052 A:{Catch:{ Exception -> 0x0055 }} */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0059 A:{SYNTHETIC, Splitter:B:31:0x0059} */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x005e A:{Catch:{ Exception -> 0x0061 }} */
    /* JADX WARNING: Removed duplicated region for block: B:39:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x004d A:{SYNTHETIC, Splitter:B:21:0x004d} */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0052 A:{Catch:{ Exception -> 0x0055 }} */
    private static void a(java.io.File r9, java.lang.String r10) {
        /*
        r0 = com.helpshift.util.o.b();
        r10 = r0.getDatabasePath(r10);
        r10 = r10.getPath();
        r0 = 0;
        r1 = new java.io.File;	 Catch:{ IOException -> 0x0056, all -> 0x0049 }
        r1.<init>(r10);	 Catch:{ IOException -> 0x0056, all -> 0x0049 }
        r10 = new java.io.FileInputStream;	 Catch:{ IOException -> 0x0056, all -> 0x0049 }
        r10.<init>(r1);	 Catch:{ IOException -> 0x0056, all -> 0x0049 }
        r10 = r10.getChannel();	 Catch:{ IOException -> 0x0056, all -> 0x0049 }
        r1 = new java.io.FileOutputStream;	 Catch:{ IOException -> 0x0057, all -> 0x0047 }
        r1.<init>(r9);	 Catch:{ IOException -> 0x0057, all -> 0x0047 }
        r9 = r1.getChannel();	 Catch:{ IOException -> 0x0057, all -> 0x0047 }
        r4 = 0;
        r6 = r10.size();	 Catch:{ IOException -> 0x0045, all -> 0x0040 }
        r2 = r9;
        r3 = r10;
        r2.transferFrom(r3, r4, r6);	 Catch:{ IOException -> 0x0045, all -> 0x0040 }
        r10.close();	 Catch:{ IOException -> 0x0045, all -> 0x0040 }
        r9.close();	 Catch:{ IOException -> 0x0045, all -> 0x0040 }
        if (r10 == 0) goto L_0x003a;
    L_0x0037:
        r10.close();	 Catch:{ Exception -> 0x0061 }
    L_0x003a:
        if (r9 == 0) goto L_0x0061;
    L_0x003c:
        r9.close();	 Catch:{ Exception -> 0x0061 }
        goto L_0x0061;
    L_0x0040:
        r0 = move-exception;
        r8 = r0;
        r0 = r9;
        r9 = r8;
        goto L_0x004b;
    L_0x0045:
        r0 = r9;
        goto L_0x0057;
    L_0x0047:
        r9 = move-exception;
        goto L_0x004b;
    L_0x0049:
        r9 = move-exception;
        r10 = r0;
    L_0x004b:
        if (r10 == 0) goto L_0x0050;
    L_0x004d:
        r10.close();	 Catch:{ Exception -> 0x0055 }
    L_0x0050:
        if (r0 == 0) goto L_0x0055;
    L_0x0052:
        r0.close();	 Catch:{ Exception -> 0x0055 }
    L_0x0055:
        throw r9;
    L_0x0056:
        r10 = r0;
    L_0x0057:
        if (r10 == 0) goto L_0x005c;
    L_0x0059:
        r10.close();	 Catch:{ Exception -> 0x0061 }
    L_0x005c:
        if (r0 == 0) goto L_0x0061;
    L_0x005e:
        r0.close();	 Catch:{ Exception -> 0x0061 }
    L_0x0061:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.util.e.a(java.io.File, java.lang.String):void");
    }

    private static void b(String str, HashMap<String, Serializable> hashMap) {
        if (hashMap != null || c(str)) {
            try {
                File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(a());
                if (!externalStoragePublicDirectory.exists()) {
                    externalStoragePublicDirectory.mkdirs();
                }
                if (externalStoragePublicDirectory.canWrite()) {
                    File file = new File(externalStoragePublicDirectory, str);
                    if (hashMap != null) {
                        a(file, (HashMap) hashMap);
                    } else {
                        a(file, str);
                    }
                }
            } catch (Exception unused) {
            }
        }
    }

    public static void a(String str) {
        b(str, null);
    }

    public static void a(String str, HashMap<String, Serializable> hashMap) {
        b(str, hashMap);
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x0074 A:{SYNTHETIC, Splitter:B:41:0x0074} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0068 A:{SYNTHETIC, Splitter:B:32:0x0068} */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x006d A:{Catch:{ Exception -> 0x0070 }} */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0074 A:{SYNTHETIC, Splitter:B:41:0x0074} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0068 A:{SYNTHETIC, Splitter:B:32:0x0068} */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x006d A:{Catch:{ Exception -> 0x0070 }} */
    /* JADX WARNING: Missing block: B:27:0x005e, code skipped:
            if (r11 != null) goto L_0x0060;
     */
    /* JADX WARNING: Missing block: B:28:0x0060, code skipped:
            r11.close();
     */
    /* JADX WARNING: Missing block: B:43:0x0077, code skipped:
            if (r11 != null) goto L_0x0060;
     */
    public static void b(java.lang.String r11) {
        /*
        r0 = c(r11);
        if (r0 == 0) goto L_0x0007;
    L_0x0006:
        return;
    L_0x0007:
        r0 = 0;
        r1 = a();	 Catch:{ Exception -> 0x0071, all -> 0x0064 }
        r1 = android.os.Environment.getExternalStoragePublicDirectory(r1);	 Catch:{ Exception -> 0x0071, all -> 0x0064 }
        r2 = r1.canRead();	 Catch:{ Exception -> 0x0071, all -> 0x0064 }
        if (r2 == 0) goto L_0x0058;
    L_0x0016:
        r2 = com.helpshift.util.o.b();	 Catch:{ Exception -> 0x0071, all -> 0x0064 }
        r2 = r2.getDatabasePath(r11);	 Catch:{ Exception -> 0x0071, all -> 0x0064 }
        r2 = r2.getPath();	 Catch:{ Exception -> 0x0071, all -> 0x0064 }
        r3 = new java.io.File;	 Catch:{ Exception -> 0x0071, all -> 0x0064 }
        r3.<init>(r1, r11);	 Catch:{ Exception -> 0x0071, all -> 0x0064 }
        r11 = new java.io.File;	 Catch:{ Exception -> 0x0071, all -> 0x0064 }
        r11.<init>(r2);	 Catch:{ Exception -> 0x0071, all -> 0x0064 }
        r1 = new java.io.FileInputStream;	 Catch:{ Exception -> 0x0071, all -> 0x0064 }
        r1.<init>(r3);	 Catch:{ Exception -> 0x0071, all -> 0x0064 }
        r1 = r1.getChannel();	 Catch:{ Exception -> 0x0071, all -> 0x0064 }
        r2 = new java.io.FileOutputStream;	 Catch:{ Exception -> 0x0055, all -> 0x0050 }
        r2.<init>(r11);	 Catch:{ Exception -> 0x0055, all -> 0x0050 }
        r11 = r2.getChannel();	 Catch:{ Exception -> 0x0055, all -> 0x0050 }
        r6 = 0;
        r8 = r1.size();	 Catch:{ Exception -> 0x0056, all -> 0x004b }
        r4 = r11;
        r5 = r1;
        r4.transferFrom(r5, r6, r8);	 Catch:{ Exception -> 0x0056, all -> 0x004b }
        r0 = r1;
        goto L_0x0059;
    L_0x004b:
        r0 = move-exception;
        r10 = r1;
        r1 = r11;
        r11 = r0;
        goto L_0x0053;
    L_0x0050:
        r11 = move-exception;
        r10 = r1;
        r1 = r0;
    L_0x0053:
        r0 = r10;
        goto L_0x0066;
    L_0x0055:
        r11 = r0;
    L_0x0056:
        r0 = r1;
        goto L_0x0072;
    L_0x0058:
        r11 = r0;
    L_0x0059:
        if (r0 == 0) goto L_0x005e;
    L_0x005b:
        r0.close();	 Catch:{ Exception -> 0x007a }
    L_0x005e:
        if (r11 == 0) goto L_0x007a;
    L_0x0060:
        r11.close();	 Catch:{ Exception -> 0x007a }
        goto L_0x007a;
    L_0x0064:
        r11 = move-exception;
        r1 = r0;
    L_0x0066:
        if (r0 == 0) goto L_0x006b;
    L_0x0068:
        r0.close();	 Catch:{ Exception -> 0x0070 }
    L_0x006b:
        if (r1 == 0) goto L_0x0070;
    L_0x006d:
        r1.close();	 Catch:{ Exception -> 0x0070 }
    L_0x0070:
        throw r11;
    L_0x0071:
        r11 = r0;
    L_0x0072:
        if (r0 == 0) goto L_0x0077;
    L_0x0074:
        r0.close();	 Catch:{ Exception -> 0x007a }
    L_0x0077:
        if (r11 == 0) goto L_0x007a;
    L_0x0079:
        goto L_0x0060;
    L_0x007a:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.util.e.b(java.lang.String):void");
    }

    public static boolean c(String str) {
        return o.b().getDatabasePath(str).exists();
    }

    public static boolean d(String str) {
        return new File(Environment.getExternalStoragePublicDirectory(a()), str).exists();
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x003a A:{SYNTHETIC, Splitter:B:18:0x003a} */
    /* JADX WARNING: Missing block: B:13:0x0031, code skipped:
            if (r3 != null) goto L_0x0033;
     */
    /* JADX WARNING: Missing block: B:15:?, code skipped:
            r3.close();
     */
    /* JADX WARNING: Missing block: B:24:0x003f, code skipped:
            if (r3 != null) goto L_0x0033;
     */
    public static java.util.HashMap<java.lang.String, java.io.Serializable> e(java.lang.String r3) {
        /*
        r0 = d(r3);
        r1 = 0;
        if (r0 != 0) goto L_0x0008;
    L_0x0007:
        return r1;
    L_0x0008:
        r0 = a();	 Catch:{ Exception -> 0x003e, all -> 0x0037 }
        r0 = android.os.Environment.getExternalStoragePublicDirectory(r0);	 Catch:{ Exception -> 0x003e, all -> 0x0037 }
        r2 = r0.canRead();	 Catch:{ Exception -> 0x003e, all -> 0x0037 }
        if (r2 == 0) goto L_0x0030;
    L_0x0016:
        r2 = new java.io.File;	 Catch:{ Exception -> 0x003e, all -> 0x0037 }
        r2.<init>(r0, r3);	 Catch:{ Exception -> 0x003e, all -> 0x0037 }
        r3 = new java.io.ObjectInputStream;	 Catch:{ Exception -> 0x003e, all -> 0x0037 }
        r0 = new java.io.FileInputStream;	 Catch:{ Exception -> 0x003e, all -> 0x0037 }
        r0.<init>(r2);	 Catch:{ Exception -> 0x003e, all -> 0x0037 }
        r3.<init>(r0);	 Catch:{ Exception -> 0x003e, all -> 0x0037 }
        r0 = r3.readObject();	 Catch:{ Exception -> 0x003f, all -> 0x002d }
        r0 = (java.util.HashMap) r0;	 Catch:{ Exception -> 0x003f, all -> 0x002d }
        r1 = r0;
        goto L_0x0031;
    L_0x002d:
        r0 = move-exception;
        r1 = r3;
        goto L_0x0038;
    L_0x0030:
        r3 = r1;
    L_0x0031:
        if (r3 == 0) goto L_0x0042;
    L_0x0033:
        r3.close();	 Catch:{ IOException -> 0x0042 }
        goto L_0x0042;
    L_0x0037:
        r0 = move-exception;
    L_0x0038:
        if (r1 == 0) goto L_0x003d;
    L_0x003a:
        r1.close();	 Catch:{ IOException -> 0x003d }
    L_0x003d:
        throw r0;
    L_0x003e:
        r3 = r1;
    L_0x003f:
        if (r3 == 0) goto L_0x0042;
    L_0x0041:
        goto L_0x0033;
    L_0x0042:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.util.e.e(java.lang.String):java.util.HashMap");
    }
}
