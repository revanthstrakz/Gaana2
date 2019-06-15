package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build.VERSION;
import com.facebook.internal.NativeProtocol;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@VisibleForTesting
final class zzbg extends SQLiteOpenHelper {
    private final /* synthetic */ zzbf zzxk;

    zzbg(zzbf zzbf, Context context, String str) {
        this.zzxk = zzbf;
        super(context, str, null, 1);
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public final SQLiteDatabase getWritableDatabase() {
        if (this.zzxk.zzxj.zzj(3600000)) {
            try {
                return super.getWritableDatabase();
            } catch (SQLiteException unused) {
                this.zzxk.zzxj.start();
                this.zzxk.zzu("Opening the database failed, dropping the table and recreating it");
                this.zzxk.getContext().getDatabasePath(zzbf.zzdd()).delete();
                try {
                    SQLiteDatabase writableDatabase = super.getWritableDatabase();
                    this.zzxk.zzxj.clear();
                    return writableDatabase;
                } catch (SQLiteException e) {
                    this.zzxk.zze("Failed to open freshly created database", e);
                    throw e;
                }
            }
        }
        throw new SQLiteException("Database open failed");
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0037  */
    private final boolean zza(android.database.sqlite.SQLiteDatabase r12, java.lang.String r13) {
        /*
        r11 = this;
        r0 = 0;
        r1 = 0;
        r3 = "SQLITE_MASTER";
        r2 = 1;
        r4 = new java.lang.String[r2];	 Catch:{ SQLiteException -> 0x002d }
        r5 = "name";
        r4[r0] = r5;	 Catch:{ SQLiteException -> 0x002d }
        r5 = "name=?";
        r6 = new java.lang.String[r2];	 Catch:{ SQLiteException -> 0x002d }
        r6[r0] = r13;	 Catch:{ SQLiteException -> 0x002d }
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r2 = r12;
        r12 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ SQLiteException -> 0x002d }
        r1 = r12.moveToFirst();	 Catch:{ SQLiteException -> 0x0026, all -> 0x0023 }
        if (r12 == 0) goto L_0x0022;
    L_0x001f:
        r12.close();
    L_0x0022:
        return r1;
    L_0x0023:
        r13 = move-exception;
        r1 = r12;
        goto L_0x003b;
    L_0x0026:
        r1 = move-exception;
        r10 = r1;
        r1 = r12;
        r12 = r10;
        goto L_0x002e;
    L_0x002b:
        r13 = move-exception;
        goto L_0x003b;
    L_0x002d:
        r12 = move-exception;
    L_0x002e:
        r2 = r11.zzxk;	 Catch:{ all -> 0x002b }
        r3 = "Error querying for table";
        r2.zzc(r3, r13, r12);	 Catch:{ all -> 0x002b }
        if (r1 == 0) goto L_0x003a;
    L_0x0037:
        r1.close();
    L_0x003a:
        return r0;
    L_0x003b:
        if (r1 == 0) goto L_0x0040;
    L_0x003d:
        r1.close();
    L_0x0040:
        throw r13;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzbg.zza(android.database.sqlite.SQLiteDatabase, java.lang.String):boolean");
    }

    private static Set<String> zzb(SQLiteDatabase sQLiteDatabase, String str) {
        HashSet hashSet = new HashSet();
        StringBuilder stringBuilder = new StringBuilder(22 + String.valueOf(str).length());
        stringBuilder.append("SELECT * FROM ");
        stringBuilder.append(str);
        stringBuilder.append(" LIMIT 0");
        Cursor rawQuery = sQLiteDatabase.rawQuery(stringBuilder.toString(), null);
        try {
            String[] columnNames = rawQuery.getColumnNames();
            for (Object add : columnNames) {
                hashSet.add(add);
            }
            return hashSet;
        } finally {
            rawQuery.close();
        }
    }

    public final void onOpen(SQLiteDatabase sQLiteDatabase) {
        String[] strArr;
        String str;
        String valueOf;
        if (VERSION.SDK_INT < 15) {
            Cursor rawQuery = sQLiteDatabase.rawQuery("PRAGMA journal_mode=memory", null);
            try {
                rawQuery.moveToFirst();
            } finally {
                rawQuery.close();
            }
        }
        int i = 0;
        if (zza(sQLiteDatabase, "hits2")) {
            Set zzb = zzb(sQLiteDatabase, "hits2");
            strArr = new String[]{"hit_id", "hit_string", "hit_time", "hit_url"};
            int i2 = 0;
            while (i2 < 4) {
                Object obj = strArr[i2];
                if (zzb.remove(obj)) {
                    i2++;
                } else {
                    str = "Database hits2 is missing required column: ";
                    valueOf = String.valueOf(obj);
                    throw new SQLiteException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                }
            }
            int remove = zzb.remove("hit_app_id") ^ 1;
            if (!zzb.isEmpty()) {
                throw new SQLiteException("Database hits2 has extra columns");
            } else if (remove != 0) {
                sQLiteDatabase.execSQL("ALTER TABLE hits2 ADD COLUMN hit_app_id INTEGER");
            }
        } else {
            sQLiteDatabase.execSQL(zzbf.zzxf);
        }
        if (zza(sQLiteDatabase, InAppConstants.INAPP_BACKGROUND_PROPERTIES)) {
            Set zzb2 = zzb(sQLiteDatabase, InAppConstants.INAPP_BACKGROUND_PROPERTIES);
            strArr = new String[]{"app_uid", "cid", "tid", NativeProtocol.WEB_DIALOG_PARAMS, "adid", "hits_count"};
            while (i < 6) {
                Object obj2 = strArr[i];
                if (zzb2.remove(obj2)) {
                    i++;
                } else {
                    str = "Database properties is missing required column: ";
                    valueOf = String.valueOf(obj2);
                    throw new SQLiteException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                }
            }
            if (!zzb2.isEmpty()) {
                throw new SQLiteException("Database properties table has extra columns");
            }
            return;
        }
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS properties ( app_uid INTEGER NOT NULL, cid TEXT NOT NULL, tid TEXT NOT NULL, params TEXT NOT NULL, adid INTEGER NOT NULL, hits_count INTEGER NOT NULL, PRIMARY KEY (app_uid, cid, tid)) ;");
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        String path = sQLiteDatabase.getPath();
        if (zzce.version() >= 9) {
            File file = new File(path);
            file.setReadable(false, false);
            file.setWritable(false, false);
            file.setReadable(true, true);
            file.setWritable(true, true);
        }
    }
}
