package com.helpshift.support.h;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.helpshift.util.o;

public class c extends SQLiteOpenHelper {

    private static class a {
        static final c a = new c(o.b());
    }

    c(Context context) {
        super(context, "__hs__db_faq", null, 2);
    }

    public static c a() {
        return a.a;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        b(sQLiteDatabase);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        o.c().q().a("/faqs/", null);
        a(sQLiteDatabase);
    }

    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        o.c().q().a("/faqs/", null);
        a(sQLiteDatabase);
    }

    private void b(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE faqs (_id INTEGER PRIMARY KEY AUTOINCREMENT,question_id TEXT NOT NULL,publish_id TEXT NOT NULL,section_id TEXT NOT NULL,title TEXT NOT NULL,body TEXT NOT NULL,helpful INTEGER,rtl INTEGER,tags TEXT,c_tags TEXT,FOREIGN KEY(section_id) REFERENCES sections (_id));");
        sQLiteDatabase.execSQL("CREATE TABLE sections (_id INTEGER PRIMARY KEY AUTOINCREMENT,section_id TEXT NOT NULL,publish_id INTEGER NOT NULL,title TEXT NOT NULL);");
    }

    private void c(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS faqs");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS sections");
    }

    public void a(SQLiteDatabase sQLiteDatabase) {
        c(sQLiteDatabase);
        b(sQLiteDatabase);
    }
}
