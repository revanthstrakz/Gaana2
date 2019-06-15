package com.facebook.ads.internal.g;

import android.database.sqlite.SQLiteDatabase;

public abstract class g {
    protected final d k;

    protected g(d dVar) {
        this.k = dVar;
    }

    public static String a(String str, b[] bVarArr) {
        StringBuilder stringBuilder = new StringBuilder("SELECT ");
        for (int i = 0; i < bVarArr.length - 1; i++) {
            stringBuilder.append(bVarArr[i].b);
            stringBuilder.append(", ");
        }
        stringBuilder.append(bVarArr[bVarArr.length - 1].b);
        stringBuilder.append(" FROM ");
        stringBuilder.append(str);
        return stringBuilder.toString();
    }

    public static String a(String str, b[] bVarArr, b bVar) {
        StringBuilder stringBuilder = new StringBuilder(a(str, bVarArr));
        stringBuilder.append(" WHERE ");
        stringBuilder.append(bVar.b);
        stringBuilder.append(" = ?");
        return stringBuilder.toString();
    }

    private String c() {
        b[] b = b();
        if (b.length < 1) {
            return null;
        }
        String str = "";
        for (int i = 0; i < b.length - 1; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(b[i].a());
            stringBuilder.append(", ");
            str = stringBuilder.toString();
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(str);
        stringBuilder2.append(b[b.length - 1].a());
        return stringBuilder2.toString();
    }

    public abstract String a();

    public void a(SQLiteDatabase sQLiteDatabase) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CREATE TABLE ");
        stringBuilder.append(a());
        stringBuilder.append(" (");
        stringBuilder.append(c());
        stringBuilder.append(")");
        sQLiteDatabase.execSQL(stringBuilder.toString());
    }

    public void b(SQLiteDatabase sQLiteDatabase) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DROP TABLE IF EXISTS ");
        stringBuilder.append(a());
        sQLiteDatabase.execSQL(stringBuilder.toString());
    }

    public abstract b[] b();

    public void e() {
    }

    /* Access modifiers changed, original: protected */
    public SQLiteDatabase f() {
        return this.k.a();
    }

    /* Access modifiers changed, original: protected */
    public boolean g() {
        return f().delete(a(), null, null) > 0;
    }
}
