package com.google.android.gms.internal.icing;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

public class zzay {
    public static final Uri CONTENT_URI = Uri.parse("content://com.google.android.gsf.gservices");
    private static final Uri zzcf = Uri.parse("content://com.google.android.gsf.gservices/prefix");
    public static final Pattern zzcg = Pattern.compile("^(1|true|t|on|yes|y)$", 2);
    public static final Pattern zzch = Pattern.compile("^(0|false|f|off|no|n)$", 2);
    private static final AtomicBoolean zzci = new AtomicBoolean();
    private static HashMap<String, String> zzcj;
    private static final HashMap<String, Boolean> zzck = new HashMap();
    private static final HashMap<String, Integer> zzcl = new HashMap();
    private static final HashMap<String, Long> zzcm = new HashMap();
    private static final HashMap<String, Float> zzcn = new HashMap();
    private static Object zzco;
    private static boolean zzcp;
    private static String[] zzcq = new String[0];

    /* JADX WARNING: Missing block: B:16:0x006b, code skipped:
            return r10;
     */
    /* JADX WARNING: Missing block: B:32:0x00a8, code skipped:
            return r10;
     */
    /* JADX WARNING: Missing block: B:34:0x00aa, code skipped:
            return null;
     */
    /* JADX WARNING: Missing block: B:37:0x00af, code skipped:
            r10 = r10.query(CONTENT_URI, null, null, new java.lang.String[]{r11}, null);
     */
    /* JADX WARNING: Missing block: B:38:0x00bd, code skipped:
            if (r10 != null) goto L_0x00c5;
     */
    /* JADX WARNING: Missing block: B:39:0x00bf, code skipped:
            if (r10 == null) goto L_0x00c4;
     */
    /* JADX WARNING: Missing block: B:40:0x00c1, code skipped:
            r10.close();
     */
    /* JADX WARNING: Missing block: B:41:0x00c4, code skipped:
            return null;
     */
    /* JADX WARNING: Missing block: B:44:0x00c9, code skipped:
            if (r10.moveToFirst() != false) goto L_0x00d4;
     */
    /* JADX WARNING: Missing block: B:45:0x00cb, code skipped:
            zza(r0, r11, null);
     */
    /* JADX WARNING: Missing block: B:48:?, code skipped:
            r12 = r10.getString(1);
     */
    /* JADX WARNING: Missing block: B:49:0x00d8, code skipped:
            if (r12 == null) goto L_0x00e1;
     */
    /* JADX WARNING: Missing block: B:51:0x00de, code skipped:
            if (r12.equals(null) == false) goto L_0x00e1;
     */
    /* JADX WARNING: Missing block: B:52:0x00e0, code skipped:
            r12 = null;
     */
    /* JADX WARNING: Missing block: B:53:0x00e1, code skipped:
            zza(r0, r11, r12);
     */
    /* JADX WARNING: Missing block: B:54:0x00e4, code skipped:
            if (r12 == null) goto L_0x00e7;
     */
    /* JADX WARNING: Missing block: B:55:0x00e7, code skipped:
            r12 = null;
     */
    /* JADX WARNING: Missing block: B:56:0x00e8, code skipped:
            if (r10 == null) goto L_0x00ed;
     */
    /* JADX WARNING: Missing block: B:57:0x00ea, code skipped:
            r10.close();
     */
    /* JADX WARNING: Missing block: B:58:0x00ed, code skipped:
            return r12;
     */
    /* JADX WARNING: Missing block: B:60:0x00ef, code skipped:
            if (r10 != null) goto L_0x00f1;
     */
    /* JADX WARNING: Missing block: B:61:0x00f1, code skipped:
            r10.close();
     */
    public static java.lang.String zza(android.content.ContentResolver r10, java.lang.String r11, java.lang.String r12) {
        /*
        r12 = com.google.android.gms.internal.icing.zzay.class;
        monitor-enter(r12);
        r0 = zzcj;	 Catch:{ all -> 0x00f5 }
        r1 = 1;
        r2 = 0;
        r3 = 0;
        if (r0 != 0) goto L_0x002a;
    L_0x000a:
        r0 = zzci;	 Catch:{ all -> 0x00f5 }
        r0.set(r2);	 Catch:{ all -> 0x00f5 }
        r0 = new java.util.HashMap;	 Catch:{ all -> 0x00f5 }
        r0.<init>();	 Catch:{ all -> 0x00f5 }
        zzcj = r0;	 Catch:{ all -> 0x00f5 }
        r0 = new java.lang.Object;	 Catch:{ all -> 0x00f5 }
        r0.<init>();	 Catch:{ all -> 0x00f5 }
        zzco = r0;	 Catch:{ all -> 0x00f5 }
        zzcp = r2;	 Catch:{ all -> 0x00f5 }
        r0 = CONTENT_URI;	 Catch:{ all -> 0x00f5 }
        r4 = new com.google.android.gms.internal.icing.zzaz;	 Catch:{ all -> 0x00f5 }
        r4.<init>(r3);	 Catch:{ all -> 0x00f5 }
        r10.registerContentObserver(r0, r1, r4);	 Catch:{ all -> 0x00f5 }
        goto L_0x0054;
    L_0x002a:
        r0 = zzci;	 Catch:{ all -> 0x00f5 }
        r0 = r0.getAndSet(r2);	 Catch:{ all -> 0x00f5 }
        if (r0 == 0) goto L_0x0054;
    L_0x0032:
        r0 = zzcj;	 Catch:{ all -> 0x00f5 }
        r0.clear();	 Catch:{ all -> 0x00f5 }
        r0 = zzck;	 Catch:{ all -> 0x00f5 }
        r0.clear();	 Catch:{ all -> 0x00f5 }
        r0 = zzcl;	 Catch:{ all -> 0x00f5 }
        r0.clear();	 Catch:{ all -> 0x00f5 }
        r0 = zzcm;	 Catch:{ all -> 0x00f5 }
        r0.clear();	 Catch:{ all -> 0x00f5 }
        r0 = zzcn;	 Catch:{ all -> 0x00f5 }
        r0.clear();	 Catch:{ all -> 0x00f5 }
        r0 = new java.lang.Object;	 Catch:{ all -> 0x00f5 }
        r0.<init>();	 Catch:{ all -> 0x00f5 }
        zzco = r0;	 Catch:{ all -> 0x00f5 }
        zzcp = r2;	 Catch:{ all -> 0x00f5 }
    L_0x0054:
        r0 = zzco;	 Catch:{ all -> 0x00f5 }
        r4 = zzcj;	 Catch:{ all -> 0x00f5 }
        r4 = r4.containsKey(r11);	 Catch:{ all -> 0x00f5 }
        if (r4 == 0) goto L_0x006c;
    L_0x005e:
        r10 = zzcj;	 Catch:{ all -> 0x00f5 }
        r10 = r10.get(r11);	 Catch:{ all -> 0x00f5 }
        r10 = (java.lang.String) r10;	 Catch:{ all -> 0x00f5 }
        if (r10 == 0) goto L_0x0069;
    L_0x0068:
        goto L_0x006a;
    L_0x0069:
        r10 = r3;
    L_0x006a:
        monitor-exit(r12);	 Catch:{ all -> 0x00f5 }
        return r10;
    L_0x006c:
        r4 = zzcq;	 Catch:{ all -> 0x00f5 }
        r5 = r4.length;	 Catch:{ all -> 0x00f5 }
        r6 = r2;
    L_0x0070:
        if (r6 >= r5) goto L_0x00ae;
    L_0x0072:
        r7 = r4[r6];	 Catch:{ all -> 0x00f5 }
        r7 = r11.startsWith(r7);	 Catch:{ all -> 0x00f5 }
        if (r7 == 0) goto L_0x00ab;
    L_0x007a:
        r0 = zzcp;	 Catch:{ all -> 0x00f5 }
        if (r0 == 0) goto L_0x0086;
    L_0x007e:
        r0 = zzcj;	 Catch:{ all -> 0x00f5 }
        r0 = r0.isEmpty();	 Catch:{ all -> 0x00f5 }
        if (r0 == 0) goto L_0x00a9;
    L_0x0086:
        r0 = zzcq;	 Catch:{ all -> 0x00f5 }
        r2 = zzcj;	 Catch:{ all -> 0x00f5 }
        r10 = zza(r10, r0);	 Catch:{ all -> 0x00f5 }
        r2.putAll(r10);	 Catch:{ all -> 0x00f5 }
        zzcp = r1;	 Catch:{ all -> 0x00f5 }
        r10 = zzcj;	 Catch:{ all -> 0x00f5 }
        r10 = r10.containsKey(r11);	 Catch:{ all -> 0x00f5 }
        if (r10 == 0) goto L_0x00a9;
    L_0x009b:
        r10 = zzcj;	 Catch:{ all -> 0x00f5 }
        r10 = r10.get(r11);	 Catch:{ all -> 0x00f5 }
        r10 = (java.lang.String) r10;	 Catch:{ all -> 0x00f5 }
        if (r10 == 0) goto L_0x00a6;
    L_0x00a5:
        goto L_0x00a7;
    L_0x00a6:
        r10 = r3;
    L_0x00a7:
        monitor-exit(r12);	 Catch:{ all -> 0x00f5 }
        return r10;
    L_0x00a9:
        monitor-exit(r12);	 Catch:{ all -> 0x00f5 }
        return r3;
    L_0x00ab:
        r6 = r6 + 1;
        goto L_0x0070;
    L_0x00ae:
        monitor-exit(r12);	 Catch:{ all -> 0x00f5 }
        r5 = CONTENT_URI;
        r6 = 0;
        r7 = 0;
        r8 = new java.lang.String[r1];
        r8[r2] = r11;
        r9 = 0;
        r4 = r10;
        r10 = r4.query(r5, r6, r7, r8, r9);
        if (r10 != 0) goto L_0x00c5;
    L_0x00bf:
        if (r10 == 0) goto L_0x00c4;
    L_0x00c1:
        r10.close();
    L_0x00c4:
        return r3;
    L_0x00c5:
        r12 = r10.moveToFirst();	 Catch:{ all -> 0x00ee }
        if (r12 != 0) goto L_0x00d4;
    L_0x00cb:
        zza(r0, r11, r3);	 Catch:{ all -> 0x00ee }
        if (r10 == 0) goto L_0x00d3;
    L_0x00d0:
        r10.close();
    L_0x00d3:
        return r3;
    L_0x00d4:
        r12 = r10.getString(r1);	 Catch:{ all -> 0x00ee }
        if (r12 == 0) goto L_0x00e1;
    L_0x00da:
        r1 = r12.equals(r3);	 Catch:{ all -> 0x00ee }
        if (r1 == 0) goto L_0x00e1;
    L_0x00e0:
        r12 = r3;
    L_0x00e1:
        zza(r0, r11, r12);	 Catch:{ all -> 0x00ee }
        if (r12 == 0) goto L_0x00e7;
    L_0x00e6:
        goto L_0x00e8;
    L_0x00e7:
        r12 = r3;
    L_0x00e8:
        if (r10 == 0) goto L_0x00ed;
    L_0x00ea:
        r10.close();
    L_0x00ed:
        return r12;
    L_0x00ee:
        r11 = move-exception;
        if (r10 == 0) goto L_0x00f4;
    L_0x00f1:
        r10.close();
    L_0x00f4:
        throw r11;
    L_0x00f5:
        r10 = move-exception;
        monitor-exit(r12);	 Catch:{ all -> 0x00f5 }
        throw r10;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzay.zza(android.content.ContentResolver, java.lang.String, java.lang.String):java.lang.String");
    }

    private static void zza(Object obj, String str, String str2) {
        synchronized (zzay.class) {
            if (obj == zzco) {
                zzcj.put(str, str2);
            }
        }
    }

    private static Map<String, String> zza(ContentResolver contentResolver, String... strArr) {
        Cursor query = contentResolver.query(zzcf, null, null, strArr, null);
        Map<String, String> treeMap = new TreeMap();
        if (query == null) {
            return treeMap;
        }
        while (query.moveToNext()) {
            try {
                treeMap.put(query.getString(0), query.getString(1));
            } finally {
                query.close();
            }
        }
        return treeMap;
    }
}
