package com.helpshift.campaigns.l;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class k extends SQLiteOpenHelper {
    private static final Integer a = Integer.valueOf(1);

    k(Context context) {
        super(context, "__hs__db_sessions", null, a.intValue());
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE sessions(identifier text primary key, device_identifier text not null, user_identifier text not null, start_time int, end_time int, durations blob, sync_status int, extras blob );");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        a(sQLiteDatabase);
        onCreate(sQLiteDatabase);
    }

    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        a(sQLiteDatabase);
        onCreate(sQLiteDatabase);
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS sessions;");
    }
}
