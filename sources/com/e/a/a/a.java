package com.e.a.a;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.e.a.e;
import com.e.a.e.f;
import com.e.a.e.g;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;

public class a extends SQLiteOpenHelper {
    private static SQLiteDatabase a;

    public a(Context context) {
        super(context, "GaanaSyncDB", null, 4);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        String a = e.a(f.a);
        String a2 = e.a(g.a);
        String a3 = e.a("local_favorite_table");
        String a4 = e.a("social_feed_table");
        sQLiteDatabase.execSQL(a);
        sQLiteDatabase.execSQL(a2);
        sQLiteDatabase.execSQL(a3);
        sQLiteDatabase.execSQL(a4);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        a(sQLiteDatabase, i, i2);
    }

    public synchronized SQLiteDatabase k() {
        if (a == null || !a.isOpen()) {
            a = getWritableDatabase();
        }
        return a;
    }

    private void a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i < 3) {
            try {
                sQLiteDatabase.beginTransaction();
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS social_feed_table");
                sQLiteDatabase.setTransactionSuccessful();
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
            } catch (Throwable th) {
                sQLiteDatabase.endTransaction();
            }
            sQLiteDatabase.endTransaction();
            try {
                sQLiteDatabase.beginTransaction();
                sQLiteDatabase.execSQL(e.a("social_feed_table"));
                sQLiteDatabase.setTransactionSuccessful();
            } catch (Exception e2) {
                ThrowableExtension.printStackTrace(e2);
            } catch (Throwable th2) {
                sQLiteDatabase.endTransaction();
            }
            sQLiteDatabase.endTransaction();
        }
        if (i < 4) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ALTER TABLE ");
            stringBuilder.append(f.a);
            stringBuilder.append(" ADD ");
            stringBuilder.append("language");
            stringBuilder.append(" TEXT DEFAULT ");
            stringBuilder.append("English");
            String stringBuilder2 = stringBuilder.toString();
            try {
                sQLiteDatabase.beginTransaction();
                sQLiteDatabase.execSQL(stringBuilder2);
                sQLiteDatabase.setTransactionSuccessful();
            } catch (Exception e3) {
                ThrowableExtension.printStackTrace(e3);
            } catch (Throwable th3) {
                sQLiteDatabase.endTransaction();
            }
            sQLiteDatabase.endTransaction();
        }
    }
}
