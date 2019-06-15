package com.helpshift.support.search.a;

import android.database.sqlite.SQLiteOpenHelper;
import com.helpshift.util.l;
import com.helpshift.util.o;
import java.util.HashMap;
import java.util.Map;

public class b implements com.helpshift.support.search.a {
    private static SQLiteOpenHelper a;

    private static class a {
        static final com.helpshift.support.search.a a = new b();
    }

    b() {
        a = new a(o.b());
    }

    public static com.helpshift.support.search.a b() {
        return a.a;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x008b A:{SYNTHETIC, Splitter:B:35:0x008b} */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x009f A:{SYNTHETIC, Splitter:B:45:0x009f} */
    public void a(java.util.List<com.helpshift.support.search.b> r7) {
        /*
        r6 = this;
        if (r7 != 0) goto L_0x0003;
    L_0x0002:
        return;
    L_0x0003:
        r0 = new java.util.ArrayList;
        r0.<init>();
        r7 = r7.iterator();
    L_0x000c:
        r1 = r7.hasNext();
        if (r1 == 0) goto L_0x003e;
    L_0x0012:
        r1 = r7.next();
        r1 = (com.helpshift.support.search.b) r1;
        r2 = r1.c;
        r2 = r6.a(r2);
        r3 = new android.content.ContentValues;
        r3.<init>();
        r4 = "token";
        r5 = r1.a;
        r3.put(r4, r5);
        r4 = "type";
        r1 = r1.b;
        r1 = java.lang.Integer.valueOf(r1);
        r3.put(r4, r1);
        r1 = "score";
        r3.put(r1, r2);
        r0.add(r3);
        goto L_0x000c;
    L_0x003e:
        r7 = a;
        monitor-enter(r7);
        r1 = 0;
        r2 = a;	 Catch:{ Exception -> 0x0081 }
        r2 = r2.getWritableDatabase();	 Catch:{ Exception -> 0x0081 }
        r2.beginTransaction();	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        r0 = r0.iterator();	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
    L_0x004f:
        r3 = r0.hasNext();	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        if (r3 == 0) goto L_0x0061;
    L_0x0055:
        r3 = r0.next();	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        r3 = (android.content.ContentValues) r3;	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        r4 = "search_token_table";
        r2.insert(r4, r1, r3);	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        goto L_0x004f;
    L_0x0061:
        r2.setTransactionSuccessful();	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        if (r2 == 0) goto L_0x009b;
    L_0x0066:
        r0 = r2.inTransaction();	 Catch:{ Exception -> 0x0070 }
        if (r0 == 0) goto L_0x009b;
    L_0x006c:
        r2.endTransaction();	 Catch:{ Exception -> 0x0070 }
        goto L_0x009b;
    L_0x0070:
        r0 = move-exception;
        r1 = "Helpshift_SearchToknDao";
        r2 = "Error occurred when calling save method inside finally block";
    L_0x0075:
        com.helpshift.util.l.c(r1, r2, r0);	 Catch:{ all -> 0x00a9 }
        goto L_0x009b;
    L_0x0079:
        r0 = move-exception;
        goto L_0x009d;
    L_0x007b:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0082;
    L_0x007e:
        r0 = move-exception;
        r2 = r1;
        goto L_0x009d;
    L_0x0081:
        r0 = move-exception;
    L_0x0082:
        r2 = "Helpshift_SearchToknDao";
        r3 = "Error occurred when calling save method";
        com.helpshift.util.l.c(r2, r3, r0);	 Catch:{ all -> 0x007e }
        if (r1 == 0) goto L_0x009b;
    L_0x008b:
        r0 = r1.inTransaction();	 Catch:{ Exception -> 0x0095 }
        if (r0 == 0) goto L_0x009b;
    L_0x0091:
        r1.endTransaction();	 Catch:{ Exception -> 0x0095 }
        goto L_0x009b;
    L_0x0095:
        r0 = move-exception;
        r1 = "Helpshift_SearchToknDao";
        r2 = "Error occurred when calling save method inside finally block";
        goto L_0x0075;
    L_0x009b:
        monitor-exit(r7);	 Catch:{ all -> 0x00a9 }
        return;
    L_0x009d:
        if (r2 == 0) goto L_0x00b3;
    L_0x009f:
        r1 = r2.inTransaction();	 Catch:{ Exception -> 0x00ab }
        if (r1 == 0) goto L_0x00b3;
    L_0x00a5:
        r2.endTransaction();	 Catch:{ Exception -> 0x00ab }
        goto L_0x00b3;
    L_0x00a9:
        r0 = move-exception;
        goto L_0x00b4;
    L_0x00ab:
        r1 = move-exception;
        r2 = "Helpshift_SearchToknDao";
        r3 = "Error occurred when calling save method inside finally block";
        com.helpshift.util.l.c(r2, r3, r1);	 Catch:{ all -> 0x00a9 }
    L_0x00b3:
        throw r0;	 Catch:{ all -> 0x00a9 }
    L_0x00b4:
        monitor-exit(r7);	 Catch:{ all -> 0x00a9 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.support.search.a.b.a(java.util.List):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x007e A:{Catch:{ all -> 0x007b, all -> 0x0082 }} */
    /* JADX WARNING: Missing block: B:10:0x0060, code skipped:
            if (r13 != null) goto L_0x0062;
     */
    /* JADX WARNING: Missing block: B:12:?, code skipped:
            r13.close();
     */
    /* JADX WARNING: Missing block: B:20:0x0076, code skipped:
            if (r13 != null) goto L_0x0062;
     */
    @android.support.annotation.Nullable
    public com.helpshift.support.search.b a(java.lang.String r13) {
        /*
        r12 = this;
        r0 = a;
        monitor-enter(r0);
        r1 = 0;
        r2 = a;	 Catch:{ Exception -> 0x006d, all -> 0x0068 }
        r3 = r2.getWritableDatabase();	 Catch:{ Exception -> 0x006d, all -> 0x0068 }
        r2 = 3;
        r5 = new java.lang.String[r2];	 Catch:{ Exception -> 0x006d, all -> 0x0068 }
        r2 = "token";
        r4 = 0;
        r5[r4] = r2;	 Catch:{ Exception -> 0x006d, all -> 0x0068 }
        r2 = "type";
        r6 = 1;
        r5[r6] = r2;	 Catch:{ Exception -> 0x006d, all -> 0x0068 }
        r2 = 2;
        r7 = "score";
        r5[r2] = r7;	 Catch:{ Exception -> 0x006d, all -> 0x0068 }
        r2 = "search_token_table";
        r7 = "token=?";
        r8 = new java.lang.String[r6];	 Catch:{ Exception -> 0x006d, all -> 0x0068 }
        r8[r4] = r13;	 Catch:{ Exception -> 0x006d, all -> 0x0068 }
        r13 = 0;
        r9 = 0;
        r10 = 0;
        r4 = r2;
        r6 = r7;
        r7 = r8;
        r8 = r13;
        r13 = r3.query(r4, r5, r6, r7, r8, r9, r10);	 Catch:{ Exception -> 0x006d, all -> 0x0068 }
        r2 = r13.getCount();	 Catch:{ Exception -> 0x0066 }
        if (r2 <= 0) goto L_0x0060;
    L_0x0035:
        r13.moveToFirst();	 Catch:{ Exception -> 0x0066 }
        r2 = "token";
        r2 = r13.getColumnIndexOrThrow(r2);	 Catch:{ Exception -> 0x0066 }
        r2 = r13.getString(r2);	 Catch:{ Exception -> 0x0066 }
        r3 = "type";
        r3 = r13.getColumnIndexOrThrow(r3);	 Catch:{ Exception -> 0x0066 }
        r3 = r13.getInt(r3);	 Catch:{ Exception -> 0x0066 }
        r4 = "score";
        r4 = r13.getColumnIndexOrThrow(r4);	 Catch:{ Exception -> 0x0066 }
        r4 = r13.getString(r4);	 Catch:{ Exception -> 0x0066 }
        r4 = r12.b(r4);	 Catch:{ Exception -> 0x0066 }
        r5 = new com.helpshift.support.search.b;	 Catch:{ Exception -> 0x0066 }
        r5.<init>(r2, r3, r4);	 Catch:{ Exception -> 0x0066 }
        r1 = r5;
    L_0x0060:
        if (r13 == 0) goto L_0x0079;
    L_0x0062:
        r13.close();	 Catch:{ all -> 0x0082 }
        goto L_0x0079;
    L_0x0066:
        r2 = move-exception;
        goto L_0x006f;
    L_0x0068:
        r13 = move-exception;
        r11 = r1;
        r1 = r13;
        r13 = r11;
        goto L_0x007c;
    L_0x006d:
        r2 = move-exception;
        r13 = r1;
    L_0x006f:
        r3 = "Helpshift_SearchToknDao";
        r4 = "Error occurred when calling get method";
        com.helpshift.util.l.c(r3, r4, r2);	 Catch:{ all -> 0x007b }
        if (r13 == 0) goto L_0x0079;
    L_0x0078:
        goto L_0x0062;
    L_0x0079:
        monitor-exit(r0);	 Catch:{ all -> 0x0082 }
        return r1;
    L_0x007b:
        r1 = move-exception;
    L_0x007c:
        if (r13 == 0) goto L_0x0084;
    L_0x007e:
        r13.close();	 Catch:{ all -> 0x0082 }
        goto L_0x0084;
    L_0x0082:
        r13 = move-exception;
        goto L_0x0085;
    L_0x0084:
        throw r1;	 Catch:{ all -> 0x0082 }
    L_0x0085:
        monitor-exit(r0);	 Catch:{ all -> 0x0082 }
        throw r13;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.support.search.a.b.a(java.lang.String):com.helpshift.support.search.b");
    }

    public void a() {
        synchronized (a) {
            try {
                a.getWritableDatabase().delete("search_token_table", null, null);
            } catch (Exception e) {
                l.c("Helpshift_SearchToknDao", "Error occurred when calling clear method", e);
            }
        }
    }

    private String a(Map<Integer, Double> map) {
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = 1;
        for (Integer num : map.keySet()) {
            if (obj != null) {
                obj = null;
            } else {
                stringBuilder.append('$');
            }
            stringBuilder.append(num);
            stringBuilder.append(':');
            stringBuilder.append(map.get(num));
        }
        return stringBuilder.toString();
    }

    private Map<Integer, Double> b(String str) {
        HashMap hashMap = new HashMap();
        if (str == null) {
            return hashMap;
        }
        String[] split = str.split("[$]");
        for (String str2 : split) {
            if (str2 != null && str2.length() > 0) {
                String[] split2 = str2.split("[:]");
                if (split2 != null && split2.length == 2) {
                    hashMap.put(Integer.valueOf(Integer.valueOf(split2[0]).intValue()), Double.valueOf(Double.valueOf(split2[1]).doubleValue()));
                }
            }
        }
        return hashMap;
    }
}
