package com.e.a;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDoneException;
import android.database.sqlite.SQLiteException;
import com.e.a.e.f;
import com.e.a.e.g;
import com.e.a.e.n;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;

public class d extends a {
    Context a;
    private a b;

    public interface a {
        void a(String str);
    }

    public d(Context context) {
        super(context);
        this.a = context;
    }

    public void a(a aVar) {
        this.b = aVar;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        super.onCreate(sQLiteDatabase);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        super.onUpgrade(sQLiteDatabase, i, i2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0097 A:{Catch:{ SQLiteBlobTooBigException -> 0x00b3, Exception -> 0x00af }} */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x008a A:{SYNTHETIC, Splitter:B:28:0x008a} */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a9 A:{Catch:{ SQLiteBlobTooBigException -> 0x00b3, Exception -> 0x00af }} */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0049  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x008a A:{SYNTHETIC, Splitter:B:28:0x008a} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0097 A:{Catch:{ SQLiteBlobTooBigException -> 0x00b3, Exception -> 0x00af }} */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a9 A:{Catch:{ SQLiteBlobTooBigException -> 0x00b3, Exception -> 0x00af }} */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0043  */
    /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x00b3 */
    public void a(java.lang.String r17, java.lang.String r18, boolean r19, int r20) {
        /*
        r16 = this;
        r1 = r17;
        r3 = r16.getDB();
        r4 = 1;
        r5 = new java.lang.String[r4];
        r6 = 0;
        r5[r6] = r1;
        r5 = -1;
        r7 = 0;
        r11 = "feed_url=?";
        r10 = new java.lang.String[r4];	 Catch:{ Exception -> 0x0047, all -> 0x003f }
        r8 = "feed_id";
        r10[r6] = r8;	 Catch:{ Exception -> 0x0047, all -> 0x003f }
        r12 = new java.lang.String[r4];	 Catch:{ Exception -> 0x0047, all -> 0x003f }
        r12[r6] = r1;	 Catch:{ Exception -> 0x0047, all -> 0x003f }
        r8 = r16.getDB();	 Catch:{ Exception -> 0x0047, all -> 0x003f }
        r9 = com.e.a.e.b.a;	 Catch:{ Exception -> 0x0047, all -> 0x003f }
        r13 = 0;
        r14 = 0;
        r15 = 0;
        r8 = r8.query(r9, r10, r11, r12, r13, r14, r15);	 Catch:{ Exception -> 0x0047, all -> 0x003f }
        r7 = r8.moveToFirst();	 Catch:{ Exception -> 0x003d, all -> 0x0039 }
        if (r7 == 0) goto L_0x0032;
    L_0x002d:
        r7 = r8.getInt(r6);	 Catch:{ Exception -> 0x003d, all -> 0x0039 }
        goto L_0x0033;
    L_0x0032:
        r7 = r5;
    L_0x0033:
        if (r8 == 0) goto L_0x004d;
    L_0x0035:
        r8.close();
        goto L_0x004d;
    L_0x0039:
        r0 = move-exception;
        r1 = r0;
        r7 = r8;
        goto L_0x0041;
    L_0x003d:
        r7 = r8;
        goto L_0x0047;
    L_0x003f:
        r0 = move-exception;
        r1 = r0;
    L_0x0041:
        if (r7 == 0) goto L_0x0046;
    L_0x0043:
        r7.close();
    L_0x0046:
        throw r1;
    L_0x0047:
        if (r7 == 0) goto L_0x004c;
    L_0x0049:
        r7.close();
    L_0x004c:
        r7 = r5;
    L_0x004d:
        r8 = new android.content.ContentValues;
        r8.<init>();
        if (r7 == r5) goto L_0x005d;
    L_0x0054:
        r5 = "feed_id";
        r7 = java.lang.Integer.valueOf(r7);
        r8.put(r5, r7);
    L_0x005d:
        r5 = "feed_content";
        r7 = r18;
        r8.put(r5, r7);
        r5 = "feed_time_stamp";
        r7 = new java.util.Date;
        r7.<init>();
        r9 = r7.getTime();
        r7 = java.lang.String.valueOf(r9);
        r8.put(r5, r7);
        r5 = "feed_url";
        r8.put(r5, r1);
        r5 = "feed_user_type";
        r7 = java.lang.Integer.valueOf(r20);
        r8.put(r5, r7);
        r3.beginTransaction();
        r5 = 5;
        if (r19 == 0) goto L_0x0097;
    L_0x008a:
        r6 = "is_dynamic_cache";
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ SQLiteBlobTooBigException -> 0x00b3, Exception -> 0x00af }
        r8.put(r6, r4);	 Catch:{ SQLiteBlobTooBigException -> 0x00b3, Exception -> 0x00af }
        goto L_0x00a0;
    L_0x0094:
        r0 = move-exception;
        r1 = r0;
        goto L_0x00ed;
    L_0x0097:
        r4 = "is_dynamic_cache";
        r6 = java.lang.Integer.valueOf(r6);	 Catch:{ SQLiteBlobTooBigException -> 0x00b3, Exception -> 0x00af }
        r8.put(r4, r6);	 Catch:{ SQLiteBlobTooBigException -> 0x00b3, Exception -> 0x00af }
    L_0x00a0:
        r4 = "feed_table";
        r6 = "feed_id";
        r3.insertWithOnConflict(r4, r6, r8, r5);	 Catch:{ SQLiteBlobTooBigException -> 0x00b3, Exception -> 0x00af }
        if (r19 == 0) goto L_0x00ac;
    L_0x00a9:
        r16.c();	 Catch:{ SQLiteBlobTooBigException -> 0x00b3, Exception -> 0x00af }
    L_0x00ac:
        r3.setTransactionSuccessful();	 Catch:{ SQLiteBlobTooBigException -> 0x00b3, Exception -> 0x00af }
    L_0x00af:
        r3.endTransaction();
        goto L_0x00ec;
    L_0x00b3:
        r2 = com.managers.u.a();	 Catch:{ all -> 0x0094 }
        r4 = "SQLiteBlobTooBigException";
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0094 }
        r6.<init>();	 Catch:{ all -> 0x0094 }
        r7 = "SQLiteBlobTooBigException ";
        r6.append(r7);	 Catch:{ all -> 0x0094 }
        r6.append(r1);	 Catch:{ all -> 0x0094 }
        r1 = r6.toString();	 Catch:{ all -> 0x0094 }
        r2.b(r4, r1);	 Catch:{ all -> 0x0094 }
        r1 = r3.inTransaction();	 Catch:{ all -> 0x0094 }
        if (r1 == 0) goto L_0x00af;
    L_0x00d3:
        r1 = new com.gaana.models.BusinessObject;	 Catch:{ all -> 0x0094 }
        r1.<init>();	 Catch:{ all -> 0x0094 }
        r1 = com.services.n.a(r1);	 Catch:{ all -> 0x0094 }
        r2 = "feed_content";
        r8.put(r2, r1);	 Catch:{ all -> 0x0094 }
        r1 = "feed_table";
        r2 = "feed_id";
        r3.insertWithOnConflict(r1, r2, r8, r5);	 Catch:{ all -> 0x0094 }
        r3.setTransactionSuccessful();	 Catch:{ all -> 0x0094 }
        goto L_0x00af;
    L_0x00ec:
        return;
    L_0x00ed:
        r3.endTransaction();
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.a.d.a(java.lang.String, java.lang.String, boolean, int):void");
    }

    public void a(String str, String str2, Boolean bool, boolean z) {
        a(str, str2, z, bool.booleanValue());
    }

    public void a(String str, String str2, Boolean bool) {
        a(str, str2, false, bool.booleanValue());
    }

    private void c() {
        if (d() >= 20) {
            try {
                String e = e();
                if (e != null) {
                    String[] strArr = new String[]{e};
                    getDB().delete("feed_table", "feed_url=?", strArr);
                    if (this.b != null) {
                        this.b.a(e);
                    }
                }
            } catch (Exception unused) {
            }
        }
    }

    private int d() {
        try {
            return (int) getDB().compileStatement("SELECT COUNT(*) FROM feed_table WHERE is_dynamic_cache=1").simpleQueryForLong();
        } catch (SQLiteDoneException unused) {
            return 0;
        }
    }

    private String e() {
        try {
            return getDB().compileStatement("SELECT feed_url FROM feed_table WHERE feed_id=(SELECT MIN(feed_id) FROM feed_table WHERE is_dynamic_cache=1)").simpleQueryForString();
        } catch (SQLiteDoneException unused) {
            return null;
        }
    }

    public void a(String str) {
        Throwable e;
        SQLiteDatabase sQLiteDatabase = null;
        try {
            SQLiteDatabase db = getDB();
            try {
                db.beginTransaction();
                String[] strArr = new String[]{String.valueOf(str)};
                db.delete("feed_table", "feed_url=?", strArr);
                db.setTransactionSuccessful();
                db.endTransaction();
            } catch (SQLiteException e2) {
                e = e2;
                sQLiteDatabase = db;
                try {
                    ThrowableExtension.printStackTrace(e);
                    sQLiteDatabase.endTransaction();
                } catch (Throwable th) {
                    e = th;
                    db = sQLiteDatabase;
                    db.endTransaction();
                    throw e;
                }
            } catch (Throwable th2) {
                e = th2;
                db.endTransaction();
                throw e;
            }
        } catch (SQLiteException e3) {
            e = e3;
            ThrowableExtension.printStackTrace(e);
            sQLiteDatabase.endTransaction();
        }
    }

    public void a() {
        SQLiteDatabase db = getDB();
        try {
            db.beginTransaction();
            db.delete("feed_table", "feed_user_type!=2", null);
        } catch (Exception unused) {
        } catch (Throwable th) {
            db.setTransactionSuccessful();
            db.endTransaction();
        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0012 */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x003e A:{Splitter:B:1:0x0006, ExcHandler: all (th java.lang.Throwable)} */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x003e A:{Splitter:B:1:0x0006, ExcHandler: all (th java.lang.Throwable)} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:17:0x003e, code skipped:
            r5 = th;
     */
    /* JADX WARNING: Missing block: B:20:0x0043, code skipped:
            r5 = null;
     */
    public java.lang.String b(java.lang.String r5) {
        /*
        r4 = this;
        r0 = r4.getDB();
        r1 = 1;
        r2 = 0;
        r1 = new java.lang.String[r1];	 Catch:{ Exception -> 0x0043, all -> 0x003e }
        r3 = 0;
        r1[r3] = r5;	 Catch:{ Exception -> 0x0043, all -> 0x003e }
        r5 = "SELECT * FROM feed_table WHERE feed_url=?";
        r5 = r0.rawQuery(r5, r1);	 Catch:{ Exception -> 0x0012, all -> 0x003e }
        goto L_0x0022;
    L_0x0012:
        r5 = new com.e.a.d;	 Catch:{ Exception -> 0x0043, all -> 0x003e }
        r3 = r4.a;	 Catch:{ Exception -> 0x0043, all -> 0x003e }
        r5.<init>(r3);	 Catch:{ Exception -> 0x0043, all -> 0x003e }
        r5.onCreate(r0);	 Catch:{ Exception -> 0x0043, all -> 0x003e }
        r5 = "SELECT * FROM feed_table WHERE feed_url=?";
        r5 = r0.rawQuery(r5, r1);	 Catch:{ Exception -> 0x0043, all -> 0x003e }
    L_0x0022:
        r0 = r5.moveToFirst();	 Catch:{ Exception -> 0x0044, all -> 0x003a }
        if (r0 == 0) goto L_0x0036;
    L_0x0028:
        r0 = "feed_content";
        r0 = r5.getColumnIndex(r0);	 Catch:{ Exception -> 0x0044, all -> 0x003a }
        r0 = r5.getString(r0);	 Catch:{ Exception -> 0x0044, all -> 0x003a }
        r5.close();
        return r0;
    L_0x0036:
        r5.close();
        return r2;
    L_0x003a:
        r0 = move-exception;
        r2 = r5;
        r5 = r0;
        goto L_0x003f;
    L_0x003e:
        r5 = move-exception;
    L_0x003f:
        r2.close();
        throw r5;
    L_0x0043:
        r5 = r2;
    L_0x0044:
        r5.close();
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.a.d.b(java.lang.String):java.lang.String");
    }

    public void b() {
        SQLiteDatabase db = getDB();
        try {
            db.beginTransaction();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("DROP TABLE IF EXISTS ");
            stringBuilder.append(n.a);
            db.execSQL(stringBuilder.toString());
            stringBuilder = new StringBuilder();
            stringBuilder.append("DROP TABLE IF EXISTS ");
            stringBuilder.append(f.a);
            db.execSQL(stringBuilder.toString());
            stringBuilder = new StringBuilder();
            stringBuilder.append("DROP TABLE IF EXISTS ");
            stringBuilder.append(g.a);
            db.execSQL(stringBuilder.toString());
            db.setTransactionSuccessful();
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        } catch (Throwable th) {
            db.endTransaction();
        }
        db.endTransaction();
    }
}
