package com.facebook.ads.internal.g;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.ads.internal.n.a;
import java.util.Locale;

public class e extends SQLiteOpenHelper {
    public static final String a = "e";
    private static String c;
    private final d b;

    public e(Context context, d dVar) {
        super(context, a(context), null, 4);
        if (dVar == null) {
            throw new IllegalArgumentException("AdDatabaseHelper can not be null");
        }
        this.b = dVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x004e  */
    private static java.lang.String a() {
        /*
        r0 = 0;
        r1 = new java.io.BufferedReader;	 Catch:{ all -> 0x004b }
        r2 = new java.io.InputStreamReader;	 Catch:{ all -> 0x004b }
        r3 = new java.io.FileInputStream;	 Catch:{ all -> 0x004b }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x004b }
        r4.<init>();	 Catch:{ all -> 0x004b }
        r5 = "/proc/";
        r4.append(r5);	 Catch:{ all -> 0x004b }
        r5 = android.os.Process.myPid();	 Catch:{ all -> 0x004b }
        r4.append(r5);	 Catch:{ all -> 0x004b }
        r5 = "/cmdline";
        r4.append(r5);	 Catch:{ all -> 0x004b }
        r4 = r4.toString();	 Catch:{ all -> 0x004b }
        r3.<init>(r4);	 Catch:{ all -> 0x004b }
        r4 = "iso-8859-1";
        r2.<init>(r3, r4);	 Catch:{ all -> 0x004b }
        r1.<init>(r2);	 Catch:{ all -> 0x004b }
        r0 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0046 }
        r0.<init>();	 Catch:{ all -> 0x0046 }
    L_0x0031:
        r2 = r1.read();	 Catch:{ all -> 0x0046 }
        if (r2 <= 0) goto L_0x003c;
    L_0x0037:
        r2 = (char) r2;	 Catch:{ all -> 0x0046 }
        r0.append(r2);	 Catch:{ all -> 0x0046 }
        goto L_0x0031;
    L_0x003c:
        r0 = r0.toString();	 Catch:{ all -> 0x0046 }
        if (r1 == 0) goto L_0x0045;
    L_0x0042:
        r1.close();
    L_0x0045:
        return r0;
    L_0x0046:
        r0 = move-exception;
        r6 = r1;
        r1 = r0;
        r0 = r6;
        goto L_0x004c;
    L_0x004b:
        r1 = move-exception;
    L_0x004c:
        if (r0 == 0) goto L_0x0051;
    L_0x004e:
        r0.close();
    L_0x0051:
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.g.e.a():java.lang.String");
    }

    private static String a(Context context) {
        String format = String.format(Locale.US, "ads%s.db", new Object[]{""});
        if (!a.C(context)) {
            return format;
        }
        String packageName = context.getPackageName();
        try {
            if (c == null) {
                c = a();
            }
            if (packageName.equals(c) || TextUtils.isEmpty(c)) {
                return format;
            }
            Object[] objArr = new Object[1];
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append('_');
            stringBuilder.append(c);
            objArr[0] = stringBuilder.toString();
            return String.format(Locale.US, "ads%s.db", objArr);
        } catch (Exception unused) {
            Log.d(a, "Can't fetch process name.");
            return format;
        }
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        for (g a : this.b.c()) {
            a.a(sQLiteDatabase);
        }
    }

    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        for (g gVar : this.b.c()) {
            gVar.b(sQLiteDatabase);
            gVar.a(sQLiteDatabase);
        }
    }

    public void onOpen(SQLiteDatabase sQLiteDatabase) {
        super.onOpen(sQLiteDatabase);
        if (!sQLiteDatabase.isReadOnly()) {
            sQLiteDatabase.execSQL("PRAGMA foreign_keys = ON;");
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i == 2 && i2 >= 3) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS crashes");
        }
        if (i <= 3 && i2 >= 4) {
            b bVar = c.i;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ALTER TABLE events ADD COLUMN ");
            stringBuilder.append(bVar.b);
            stringBuilder.append(" ");
            stringBuilder.append(bVar.c);
            stringBuilder.append(" DEFAULT 0");
            sQLiteDatabase.execSQL(stringBuilder.toString());
        }
    }
}
