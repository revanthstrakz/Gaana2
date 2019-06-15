package com.comscore.utils;

import android.content.Context;
import com.comscore.analytics.Core;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class FileUtils {
    public static boolean deleteFile(Core core, String str) {
        Context appContext = core.getAppContext();
        Storage storage = core.getStorage();
        boolean deleteFile = appContext.deleteFile(str);
        if (deleteFile) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("File");
            stringBuilder.append(str);
            stringBuilder.append(" was removed");
            CSLog.d(FileUtils.class, stringBuilder.toString());
            storage.remove(str);
        }
        return deleteFile;
    }

    public static ArrayList<String> getFileList(Context context) {
        Object[] objArr;
        File filesDir = context.getFilesDir();
        if (filesDir == null || !filesDir.isDirectory()) {
            objArr = null;
        } else {
            objArr = filesDir.list(new b());
            if (objArr != null) {
                Arrays.sort(objArr);
            } else {
                objArr = new String[0];
            }
        }
        return new ArrayList(Arrays.asList(objArr));
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0032 A:{SYNTHETIC, Splitter:B:20:0x0032} */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0046 A:{SYNTHETIC, Splitter:B:27:0x0046} */
    public static java.lang.String[] readCachedEvents(android.content.Context r4, java.lang.String r5) {
        /*
        r0 = new java.util.LinkedList;
        r0.<init>();
        r1 = 0;
        r2 = new java.io.BufferedReader;	 Catch:{ Exception -> 0x002c }
        r3 = new java.io.InputStreamReader;	 Catch:{ Exception -> 0x002c }
        r4 = r4.openFileInput(r5);	 Catch:{ Exception -> 0x002c }
        r3.<init>(r4);	 Catch:{ Exception -> 0x002c }
        r2.<init>(r3);	 Catch:{ Exception -> 0x002c }
    L_0x0014:
        r4 = r2.readLine();	 Catch:{ Exception -> 0x0027, all -> 0x0024 }
        if (r4 == 0) goto L_0x001e;
    L_0x001a:
        r0.add(r4);	 Catch:{ Exception -> 0x0027, all -> 0x0024 }
        goto L_0x0014;
    L_0x001e:
        if (r2 == 0) goto L_0x003a;
    L_0x0020:
        r2.close();	 Catch:{ IOException -> 0x0036 }
        goto L_0x003a;
    L_0x0024:
        r4 = move-exception;
        r1 = r2;
        goto L_0x0044;
    L_0x0027:
        r4 = move-exception;
        r1 = r2;
        goto L_0x002d;
    L_0x002a:
        r4 = move-exception;
        goto L_0x0044;
    L_0x002c:
        r4 = move-exception;
    L_0x002d:
        com.comscore.utils.CSLog.printStackTrace(r4);	 Catch:{ all -> 0x002a }
        if (r1 == 0) goto L_0x003a;
    L_0x0032:
        r1.close();	 Catch:{ IOException -> 0x0036 }
        goto L_0x003a;
    L_0x0036:
        r4 = move-exception;
        com.comscore.utils.CSLog.printStackTrace(r4);
    L_0x003a:
        r4 = r0.size();
        r4 = new java.lang.String[r4];
        r0.toArray(r4);
        return r4;
    L_0x0044:
        if (r1 == 0) goto L_0x004e;
    L_0x0046:
        r1.close();	 Catch:{ IOException -> 0x004a }
        goto L_0x004e;
    L_0x004a:
        r5 = move-exception;
        com.comscore.utils.CSLog.printStackTrace(r5);
    L_0x004e:
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.comscore.utils.FileUtils.readCachedEvents(android.content.Context, java.lang.String):java.lang.String[]");
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x005f A:{SYNTHETIC, Splitter:B:25:0x005f} */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0059 A:{SYNTHETIC, Splitter:B:21:0x0059} */
    public static void writeEvent(com.comscore.analytics.Core r3, java.lang.String r4, int r5, java.lang.String r6) {
        /*
        r0 = r3.getAppContext();
        r3 = r3.getStorage();
        r1 = r3.get(r4);
        r2 = 0;
        r1 = com.comscore.utils.Utils.getInteger(r1, r2);
        r2 = 0;
        r5 = r0.openFileOutput(r4, r5);	 Catch:{ Exception -> 0x0039 }
        r6 = r6.getBytes();	 Catch:{ Exception -> 0x0034, all -> 0x0031 }
        r5.write(r6);	 Catch:{ Exception -> 0x0034, all -> 0x0031 }
        r1 = r1 + 1;
        r6 = java.lang.String.valueOf(r1);	 Catch:{ Exception -> 0x0034, all -> 0x0031 }
        r3.set(r4, r6);	 Catch:{ Exception -> 0x0034, all -> 0x0031 }
        if (r5 == 0) goto L_0x005c;
    L_0x0028:
        r5.close();	 Catch:{ IOException -> 0x002c }
        return;
    L_0x002c:
        r3 = move-exception;
        com.comscore.utils.CSLog.printStackTrace(r3);
        return;
    L_0x0031:
        r3 = move-exception;
        r2 = r5;
        goto L_0x005d;
    L_0x0034:
        r3 = move-exception;
        r2 = r5;
        goto L_0x003a;
    L_0x0037:
        r3 = move-exception;
        goto L_0x005d;
    L_0x0039:
        r3 = move-exception;
    L_0x003a:
        r4 = com.comscore.utils.FileUtils.class;
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0037 }
        r5.<init>();	 Catch:{ all -> 0x0037 }
        r6 = "Exception in writeEvent:";
        r5.append(r6);	 Catch:{ all -> 0x0037 }
        r6 = r3.getLocalizedMessage();	 Catch:{ all -> 0x0037 }
        r5.append(r6);	 Catch:{ all -> 0x0037 }
        r5 = r5.toString();	 Catch:{ all -> 0x0037 }
        com.comscore.utils.CSLog.e(r4, r5);	 Catch:{ all -> 0x0037 }
        com.comscore.utils.CSLog.printStackTrace(r3);	 Catch:{ all -> 0x0037 }
        if (r2 == 0) goto L_0x005c;
    L_0x0059:
        r2.close();	 Catch:{ IOException -> 0x002c }
    L_0x005c:
        return;
    L_0x005d:
        if (r2 == 0) goto L_0x0067;
    L_0x005f:
        r2.close();	 Catch:{ IOException -> 0x0063 }
        goto L_0x0067;
    L_0x0063:
        r4 = move-exception;
        com.comscore.utils.CSLog.printStackTrace(r4);
    L_0x0067:
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.comscore.utils.FileUtils.writeEvent(com.comscore.analytics.Core, java.lang.String, int, java.lang.String):void");
    }
}
