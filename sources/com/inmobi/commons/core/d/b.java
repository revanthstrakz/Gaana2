package com.inmobi.commons.core.d;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.inmobi.commons.a.a;

public final class b {
    private static final String a = "b";
    private static volatile b b;
    private static final Object c = new Object();
    private static final Object d = new Object();
    private static int e;
    private SQLiteDatabase f;

    private b() {
        try {
            this.f = new a(a.b()).getWritableDatabase();
            b = this;
        } catch (Exception unused) {
        }
    }

    public static synchronized b a() {
        b bVar;
        synchronized (b.class) {
            synchronized (d) {
                e++;
            }
            bVar = b;
            if (bVar == null) {
                b bVar2;
                synchronized (c) {
                    bVar2 = b;
                    if (bVar2 == null) {
                        bVar2 = new b();
                        b = bVar2;
                    }
                }
                bVar = bVar2;
            }
        }
        return bVar;
    }

    public final synchronized void a(String str, ContentValues contentValues, String str2, String[] strArr) {
        try {
            if (!a(str, contentValues)) {
                b(str, contentValues, str2, strArr);
            }
        } catch (Exception e) {
            new StringBuilder("SDK encountered unexpected error in DbStore.insertOrUpdate() method; ").append(e.getMessage());
        }
    }

    public final synchronized boolean a(String str, ContentValues contentValues) {
        try {
            return this.f.insertWithOnConflict(str, null, contentValues, 4) != -1;
        } catch (Exception e) {
            new StringBuilder("SDK encountered unexpected error in DbStore.insert() method; ").append(e.getMessage());
            return false;
        }
    }

    public final synchronized int a(String str, String str2, String[] strArr) {
        try {
        } catch (Exception e) {
            new StringBuilder("SDK encountered an unexpected error in DbStore.delete() method; ").append(e.getMessage());
            return -1;
        }
        return this.f.delete(str, str2, strArr);
    }

    public final synchronized int b(String str, ContentValues contentValues, String str2, String[] strArr) {
        try {
        } catch (Exception e) {
            new StringBuilder("SDK encountered an unexpected error in DbStore.delete() method; ").append(e.getMessage());
            return -1;
        }
        return this.f.updateWithOnConflict(str, contentValues, str2, strArr, 4);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0022 A:{LOOP_START, LOOP:1: B:10:0x0022->B:11:0x0031, Catch:{ Exception -> 0x003c }} */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x005c A:{SYNTHETIC, Splitter:B:30:0x005c} */
    /* JADX WARNING: Missing block: B:13:0x0036, code skipped:
            if (r4 != null) goto L_0x0038;
     */
    /* JADX WARNING: Missing block: B:15:?, code skipped:
            r4.close();
     */
    /* JADX WARNING: Missing block: B:24:0x0053, code skipped:
            if (r4 != null) goto L_0x0038;
     */
    public final synchronized java.util.List<android.content.ContentValues> a(java.lang.String r14, java.lang.String[] r15, java.lang.String r16, java.lang.String[] r17, java.lang.String r18, java.lang.String r19, java.lang.String r20, java.lang.String r21) {
        /*
        r13 = this;
        r1 = r13;
        monitor-enter(r13);
        r2 = new java.util.ArrayList;	 Catch:{ all -> 0x0060 }
        r2.<init>();	 Catch:{ all -> 0x0060 }
        r3 = 0;
        r4 = r1.f;	 Catch:{ Exception -> 0x0042, all -> 0x003e }
        r5 = r14;
        r6 = r15;
        r7 = r16;
        r8 = r17;
        r9 = r18;
        r10 = r19;
        r11 = r20;
        r12 = r21;
        r4 = r4.query(r5, r6, r7, r8, r9, r10, r11, r12);	 Catch:{ Exception -> 0x0042, all -> 0x003e }
        r3 = r4.moveToFirst();	 Catch:{ Exception -> 0x003c }
        if (r3 == 0) goto L_0x0033;
    L_0x0022:
        r3 = new android.content.ContentValues;	 Catch:{ Exception -> 0x003c }
        r3.<init>();	 Catch:{ Exception -> 0x003c }
        android.database.DatabaseUtils.cursorRowToContentValues(r4, r3);	 Catch:{ Exception -> 0x003c }
        r2.add(r3);	 Catch:{ Exception -> 0x003c }
        r3 = r4.moveToNext();	 Catch:{ Exception -> 0x003c }
        if (r3 != 0) goto L_0x0022;
    L_0x0033:
        r4.close();	 Catch:{ Exception -> 0x003c }
        if (r4 == 0) goto L_0x0056;
    L_0x0038:
        r4.close();	 Catch:{ all -> 0x0060 }
        goto L_0x0056;
    L_0x003c:
        r0 = move-exception;
        goto L_0x0044;
    L_0x003e:
        r0 = move-exception;
        r2 = r0;
        r4 = r3;
        goto L_0x005a;
    L_0x0042:
        r0 = move-exception;
        r4 = r3;
    L_0x0044:
        r3 = r0;
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0058 }
        r6 = "SDK encountered unexpected error in DbStore.getRows() method; ";
        r5.<init>(r6);	 Catch:{ all -> 0x0058 }
        r3 = r3.getMessage();	 Catch:{ all -> 0x0058 }
        r5.append(r3);	 Catch:{ all -> 0x0058 }
        if (r4 == 0) goto L_0x0056;
    L_0x0055:
        goto L_0x0038;
    L_0x0056:
        monitor-exit(r13);
        return r2;
    L_0x0058:
        r0 = move-exception;
        r2 = r0;
    L_0x005a:
        if (r4 == 0) goto L_0x005f;
    L_0x005c:
        r4.close();	 Catch:{ all -> 0x0060 }
    L_0x005f:
        throw r2;	 Catch:{ all -> 0x0060 }
    L_0x0060:
        r0 = move-exception;
        r2 = r0;
        monitor-exit(r13);
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.commons.core.d.b.a(java.lang.String, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String, java.lang.String, java.lang.String, java.lang.String):java.util.List");
    }

    public final synchronized int a(String str) {
        int i;
        try {
            StringBuilder stringBuilder = new StringBuilder("SELECT COUNT(*) FROM ");
            stringBuilder.append(str);
            stringBuilder.append(" ; ");
            Cursor rawQuery = this.f.rawQuery(stringBuilder.toString(), null);
            rawQuery.moveToFirst();
            i = rawQuery.getInt(0);
            rawQuery.close();
        } catch (Exception e) {
            new StringBuilder("SDK encountered unexpected error in DbStore.getCount() method; ").append(e.getMessage());
            return -1;
        }
        return i;
    }

    public final synchronized int b(String str, String str2, String[] strArr) {
        int i;
        try {
            StringBuilder stringBuilder = new StringBuilder("SELECT COUNT(*) FROM ");
            stringBuilder.append(str);
            stringBuilder.append(" WHERE ");
            stringBuilder.append(str2);
            stringBuilder.append(" ; ");
            Cursor rawQuery = this.f.rawQuery(stringBuilder.toString(), strArr);
            rawQuery.moveToFirst();
            i = rawQuery.getInt(0);
            rawQuery.close();
        } catch (Exception e) {
            new StringBuilder("SDK encountered unexpected error in DbStore.getCount() method; ").append(e.getMessage());
            return -1;
        }
        return i;
    }

    public final synchronized void a(String str, String str2) {
        try {
            StringBuilder stringBuilder = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
            stringBuilder.append(str);
            stringBuilder.append(str2);
            stringBuilder.append(";");
            this.f.execSQL(stringBuilder.toString());
        } catch (Exception e) {
            new StringBuilder("SDK encountered unexpected error in DbStore.createTableIfNotExists() method; ").append(e.getMessage());
        }
    }

    public final synchronized void b() {
        try {
            synchronized (d) {
                int i = e - 1;
                e = i;
                if (i == 0) {
                    this.f.close();
                    b = null;
                }
            }
        } catch (Exception e) {
            new StringBuilder("SDK encountered unexpected error in DbStore.close() method; ").append(e.getMessage());
        }
    }
}
