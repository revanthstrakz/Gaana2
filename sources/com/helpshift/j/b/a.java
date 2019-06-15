package com.helpshift.j.b;

import android.content.Context;
import android.util.Log;

public class a implements b {
    private static final Object b = new Object();
    private c a;

    public a(Context context, String str) {
        this.a = new c(context, str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0067 A:{SYNTHETIC, Splitter:B:21:0x0067} */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00a8 A:{SYNTHETIC, Splitter:B:54:0x00a8} */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00b8 A:{Catch:{ Exception -> 0x006b }} */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x007d A:{Splitter:B:5:0x000a, ExcHandler: all (th java.lang.Throwable)} */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0093 A:{SYNTHETIC, Splitter:B:42:0x0093} */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00a1 A:{Catch:{ Exception -> 0x006b }} */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00a8 A:{SYNTHETIC, Splitter:B:54:0x00a8} */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00b8 A:{Catch:{ Exception -> 0x006b }} */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0093 A:{SYNTHETIC, Splitter:B:42:0x0093} */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00a1 A:{Catch:{ Exception -> 0x006b }} */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00a8 A:{SYNTHETIC, Splitter:B:54:0x00a8} */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00b8 A:{Catch:{ Exception -> 0x006b }} */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0067 A:{SYNTHETIC, Splitter:B:21:0x0067} */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0079 A:{Splitter:B:10:0x0015, ExcHandler: all (th java.lang.Throwable), PHI: r3 } */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:15:0x0038, code skipped:
            r4 = e;
     */
    /* JADX WARNING: Missing block: B:16:0x0039, code skipped:
            r3 = null;
     */
    /* JADX WARNING: Missing block: B:26:0x0073, code skipped:
            if (r3 == null) goto L_0x00a2;
     */
    /* JADX WARNING: Missing block: B:28:0x0079, code skipped:
            r10 = th;
     */
    /* JADX WARNING: Missing block: B:29:0x007b, code skipped:
            r10 = e;
     */
    /* JADX WARNING: Missing block: B:30:0x007d, code skipped:
            r10 = th;
     */
    /* JADX WARNING: Missing block: B:31:0x007e, code skipped:
            r3 = null;
     */
    /* JADX WARNING: Missing block: B:55:?, code skipped:
            r2.endTransaction();
     */
    /* JADX WARNING: Missing block: B:57:0x00ae, code skipped:
            r1 = move-exception;
     */
    /* JADX WARNING: Missing block: B:59:?, code skipped:
            android.util.Log.e("LogSqliteStorage", "Error inserting log inside finally block: ", r1);
     */
    /* JADX WARNING: Missing block: B:61:0x00b8, code skipped:
            r3.close();
     */
    public void a(com.helpshift.j.d.a r10) {
        /*
        r9 = this;
        r0 = b;
        monitor-enter(r0);
        r1 = 0;
        r2 = r9.a;	 Catch:{ Exception -> 0x0088, all -> 0x0084 }
        r2 = r2.getWritableDatabase();	 Catch:{ Exception -> 0x0088, all -> 0x0084 }
        r2.beginTransaction();	 Catch:{ Exception -> 0x0080, all -> 0x007d }
        r3 = "SELECT rowid FROM LOG_MESSAGES";
        r3 = r2.rawQuery(r3, r1);	 Catch:{ Exception -> 0x0038, all -> 0x007d }
        if (r3 == 0) goto L_0x0059;
    L_0x0015:
        r4 = r3.getCount();	 Catch:{ Exception -> 0x0036, all -> 0x0079 }
        r5 = 100;
        if (r4 < r5) goto L_0x0059;
    L_0x001d:
        r3.moveToFirst();	 Catch:{ Exception -> 0x0036, all -> 0x0079 }
        r4 = 0;
        r5 = r3.getInt(r4);	 Catch:{ Exception -> 0x0036, all -> 0x0079 }
        r6 = "LOG_MESSAGES";
        r7 = "rowid = ?";
        r8 = 1;
        r8 = new java.lang.String[r8];	 Catch:{ Exception -> 0x0036, all -> 0x0079 }
        r5 = java.lang.String.valueOf(r5);	 Catch:{ Exception -> 0x0036, all -> 0x0079 }
        r8[r4] = r5;	 Catch:{ Exception -> 0x0036, all -> 0x0079 }
        r2.delete(r6, r7, r8);	 Catch:{ Exception -> 0x0036, all -> 0x0079 }
        goto L_0x0059;
    L_0x0036:
        r4 = move-exception;
        goto L_0x003a;
    L_0x0038:
        r4 = move-exception;
        r3 = r1;
    L_0x003a:
        r5 = "LogSqliteStorage";
        r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        r6.<init>();	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        r7 = "Error in rotation of logs + ";
        r6.append(r7);	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        r4 = r4.getMessage();	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        r6.append(r4);	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        r4 = r6.toString();	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        android.util.Log.w(r5, r4);	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        r4 = "LOG_MESSAGES";
        r2.delete(r4, r1, r1);	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
    L_0x0059:
        r4 = "LOG_MESSAGES";
        r10 = com.helpshift.j.a.a.a(r10);	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        r2.insert(r4, r1, r10);	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        r2.setTransactionSuccessful();	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        if (r2 == 0) goto L_0x0073;
    L_0x0067:
        r2.endTransaction();	 Catch:{ Exception -> 0x006b }
        goto L_0x0073;
    L_0x006b:
        r10 = move-exception;
        r1 = "LogSqliteStorage";
        r2 = "Error inserting log inside finally block: ";
        android.util.Log.e(r1, r2, r10);	 Catch:{ all -> 0x00ac }
    L_0x0073:
        if (r3 == 0) goto L_0x00a2;
    L_0x0075:
        r3.close();	 Catch:{ all -> 0x00ac }
        goto L_0x00a2;
    L_0x0079:
        r10 = move-exception;
        goto L_0x00a6;
    L_0x007b:
        r10 = move-exception;
        goto L_0x0082;
    L_0x007d:
        r10 = move-exception;
        r3 = r1;
        goto L_0x00a6;
    L_0x0080:
        r10 = move-exception;
        r3 = r1;
    L_0x0082:
        r1 = r2;
        goto L_0x008a;
    L_0x0084:
        r10 = move-exception;
        r2 = r1;
        r3 = r2;
        goto L_0x00a6;
    L_0x0088:
        r10 = move-exception;
        r3 = r1;
    L_0x008a:
        r2 = "LogSqliteStorage";
        r4 = "Error inserting log : ";
        android.util.Log.e(r2, r4, r10);	 Catch:{ all -> 0x00a4 }
        if (r1 == 0) goto L_0x009f;
    L_0x0093:
        r1.endTransaction();	 Catch:{ Exception -> 0x0097 }
        goto L_0x009f;
    L_0x0097:
        r10 = move-exception;
        r1 = "LogSqliteStorage";
        r2 = "Error inserting log inside finally block: ";
        android.util.Log.e(r1, r2, r10);	 Catch:{ all -> 0x00ac }
    L_0x009f:
        if (r3 == 0) goto L_0x00a2;
    L_0x00a1:
        goto L_0x0075;
    L_0x00a2:
        monitor-exit(r0);	 Catch:{ all -> 0x00ac }
        return;
    L_0x00a4:
        r10 = move-exception;
        r2 = r1;
    L_0x00a6:
        if (r2 == 0) goto L_0x00b6;
    L_0x00a8:
        r2.endTransaction();	 Catch:{ Exception -> 0x00ae }
        goto L_0x00b6;
    L_0x00ac:
        r10 = move-exception;
        goto L_0x00bc;
    L_0x00ae:
        r1 = move-exception;
        r2 = "LogSqliteStorage";
        r4 = "Error inserting log inside finally block: ";
        android.util.Log.e(r2, r4, r1);	 Catch:{ all -> 0x00ac }
    L_0x00b6:
        if (r3 == 0) goto L_0x00bb;
    L_0x00b8:
        r3.close();	 Catch:{ all -> 0x00ac }
    L_0x00bb:
        throw r10;	 Catch:{ all -> 0x00ac }
    L_0x00bc:
        monitor-exit(r0);	 Catch:{ all -> 0x00ac }
        throw r10;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.j.b.a.a(com.helpshift.j.d.a):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x002d A:{SYNTHETIC, Splitter:B:19:0x002d} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0035 A:{Catch:{ all -> 0x0032, all -> 0x0039 }} */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:8:0x0016, B:16:0x0024] */
    /* JADX WARNING: Missing block: B:23:0x0032, code skipped:
            r1 = th;
     */
    public java.util.List<com.helpshift.j.d.a> a() {
        /*
        r7 = this;
        r0 = b;
        monitor-enter(r0);
        r1 = 0;
        r2 = r7.a;	 Catch:{ Exception -> 0x0022, all -> 0x001d }
        r2 = r2.getReadableDatabase();	 Catch:{ Exception -> 0x0022, all -> 0x001d }
        r3 = "SELECT * FROM LOG_MESSAGES";
        r2 = r2.rawQuery(r3, r1);	 Catch:{ Exception -> 0x0022, all -> 0x001d }
        r3 = com.helpshift.j.a.a.a(r2);	 Catch:{ Exception -> 0x001b }
        if (r2 == 0) goto L_0x0019;
    L_0x0016:
        r2.close();	 Catch:{ all -> 0x0039 }
    L_0x0019:
        r1 = r3;
        goto L_0x0030;
    L_0x001b:
        r3 = move-exception;
        goto L_0x0024;
    L_0x001d:
        r2 = move-exception;
        r6 = r2;
        r2 = r1;
        r1 = r6;
        goto L_0x0033;
    L_0x0022:
        r3 = move-exception;
        r2 = r1;
    L_0x0024:
        r4 = "LogSqliteStorage";
        r5 = "Error getting all log messages : ";
        android.util.Log.e(r4, r5, r3);	 Catch:{ all -> 0x0032 }
        if (r2 == 0) goto L_0x0030;
    L_0x002d:
        r2.close();	 Catch:{ all -> 0x0039 }
    L_0x0030:
        monitor-exit(r0);	 Catch:{ all -> 0x0039 }
        return r1;
    L_0x0032:
        r1 = move-exception;
    L_0x0033:
        if (r2 == 0) goto L_0x003b;
    L_0x0035:
        r2.close();	 Catch:{ all -> 0x0039 }
        goto L_0x003b;
    L_0x0039:
        r1 = move-exception;
        goto L_0x003c;
    L_0x003b:
        throw r1;	 Catch:{ all -> 0x0039 }
    L_0x003c:
        monitor-exit(r0);	 Catch:{ all -> 0x0039 }
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.j.b.a.a():java.util.List");
    }

    public void b() {
        synchronized (b) {
            try {
                this.a.getWritableDatabase().execSQL("DELETE FROM LOG_MESSAGES");
            } catch (Exception e) {
                Log.e("LogSqliteStorage", "Error deleting all logs from db", e);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0076  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0070  */
    public int a(java.util.List<java.lang.String> r7) {
        /*
        r6 = this;
        r0 = 0;
        if (r7 == 0) goto L_0x007a;
    L_0x0003:
        r1 = r7.isEmpty();
        if (r1 == 0) goto L_0x000b;
    L_0x0009:
        goto L_0x007a;
    L_0x000b:
        r1 = r7.size();
        r1 = r6.a(r1);
        r2 = 0;
        r3 = r6.a;	 Catch:{ Exception -> 0x0053 }
        r3 = r3.getReadableDatabase();	 Catch:{ Exception -> 0x0053 }
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0053 }
        r4.<init>();	 Catch:{ Exception -> 0x0053 }
        r5 = "SELECT COUNT(*) FROM LOG_MESSAGES WHERE LEVEL IN ";
        r4.append(r5);	 Catch:{ Exception -> 0x0053 }
        r4.append(r1);	 Catch:{ Exception -> 0x0053 }
        r1 = r4.toString();	 Catch:{ Exception -> 0x0053 }
        r4 = new java.lang.String[r0];	 Catch:{ Exception -> 0x0053 }
        r7 = r7.toArray(r4);	 Catch:{ Exception -> 0x0053 }
        r7 = (java.lang.String[]) r7;	 Catch:{ Exception -> 0x0053 }
        r7 = r3.rawQuery(r1, r7);	 Catch:{ Exception -> 0x0053 }
        if (r7 == 0) goto L_0x004b;
    L_0x0039:
        r1 = r7.moveToFirst();	 Catch:{ Exception -> 0x0048, all -> 0x0045 }
        if (r1 == 0) goto L_0x004b;
    L_0x003f:
        r1 = r7.getInt(r0);	 Catch:{ Exception -> 0x0048, all -> 0x0045 }
        r0 = r1;
        goto L_0x004b;
    L_0x0045:
        r0 = move-exception;
        r2 = r7;
        goto L_0x0074;
    L_0x0048:
        r1 = move-exception;
        r2 = r7;
        goto L_0x0054;
    L_0x004b:
        if (r7 == 0) goto L_0x0073;
    L_0x004d:
        r7.close();
        goto L_0x0073;
    L_0x0051:
        r0 = move-exception;
        goto L_0x0074;
    L_0x0053:
        r1 = move-exception;
    L_0x0054:
        r7 = "LogSqliteStorage";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0051 }
        r3.<init>();	 Catch:{ all -> 0x0051 }
        r4 = "Error getting logs count : ";
        r3.append(r4);	 Catch:{ all -> 0x0051 }
        r1 = r1.getMessage();	 Catch:{ all -> 0x0051 }
        r3.append(r1);	 Catch:{ all -> 0x0051 }
        r1 = r3.toString();	 Catch:{ all -> 0x0051 }
        android.util.Log.e(r7, r1);	 Catch:{ all -> 0x0051 }
        if (r2 == 0) goto L_0x0073;
    L_0x0070:
        r2.close();
    L_0x0073:
        return r0;
    L_0x0074:
        if (r2 == 0) goto L_0x0079;
    L_0x0076:
        r2.close();
    L_0x0079:
        throw r0;
    L_0x007a:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.j.b.a.a(java.util.List):int");
    }

    private String a(int i) {
        if (i == 0) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder("(");
        for (int i2 = 0; i2 < i - 1; i2++) {
            stringBuilder.append("?,");
        }
        stringBuilder.append("?)");
        return stringBuilder.toString();
    }
}
