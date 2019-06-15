package com.helpshift.j.b;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class c extends SQLiteOpenHelper {
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public c(Context context, String str) {
        super(context, str, null, 1);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.beginTransaction();
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS LOG_MESSAGES( TIMESTAMP TEXT, MESSAGE TEXT, LEVEL TEXT, STACKTRACE TEXT, EXTRAS TEXT  );");
            sQLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e("LogSQLiteHelper", "Error creating log storage: ", e);
        } catch (Throwable th) {
            sQLiteDatabase.endTransaction();
        }
        sQLiteDatabase.endTransaction();
    }
}
