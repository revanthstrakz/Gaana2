package com.helpshift.support.h;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class f extends SQLiteOpenHelper {
    public f(Context context) {
        super(context, "__hs__db_profiles", null, 3);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE profiles(_id INTEGER PRIMARY KEY AUTOINCREMENT, IDENTIFIER TEXT NOT NULL UNIQUE, profile_id TEXT UNIQUE, name TEXT, email TEXT, salt TEXT, uid TEXT, did TEXT, push_token_sync INTEGER );");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (sQLiteDatabase.isOpen()) {
            if (i < 2) {
                sQLiteDatabase.execSQL("ALTER TABLE profiles ADD uid TEXT");
                sQLiteDatabase.execSQL("ALTER TABLE profiles ADD did TEXT");
            }
            if (i < 3) {
                sQLiteDatabase.execSQL("ALTER TABLE profiles ADD push_token_sync INTEGER");
            }
        }
    }

    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (sQLiteDatabase.isOpen()) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS profiles");
            onCreate(sQLiteDatabase);
        }
    }
}
