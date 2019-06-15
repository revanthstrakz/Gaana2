package com.helpshift.campaigns.l;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.helpshift.util.u;
import com.payu.custombrowser.util.CBConstant;
import java.util.HashSet;
import java.util.Iterator;

public class g extends SQLiteOpenHelper {
    private static final Integer a = Integer.valueOf(1);
    private HashSet<String> b = new HashSet();

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
    }

    g(Context context) {
        super(context, "__hs__db_properties", null, a.intValue());
    }

    public synchronized void a(SQLiteDatabase sQLiteDatabase, String str) {
        this.b.add(str);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CREATE TABLE IF NOT EXISTS ");
        stringBuilder.append(a(str));
        stringBuilder.append(" (");
        stringBuilder.append(CBConstant.KEY);
        stringBuilder.append(" text primary key, ");
        stringBuilder.append("value");
        stringBuilder.append(" blob, ");
        stringBuilder.append("type");
        stringBuilder.append(" text, ");
        stringBuilder.append("sync_status");
        stringBuilder.append(" int, ");
        stringBuilder.append("extras");
        stringBuilder.append(" blob");
        stringBuilder.append(");");
        sQLiteDatabase.execSQL(stringBuilder.toString());
    }

    public void b(SQLiteDatabase sQLiteDatabase, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DROP TABLE IF EXISTS ");
        stringBuilder.append(a(str));
        stringBuilder.append(";");
        sQLiteDatabase.execSQL(stringBuilder.toString());
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        a(sQLiteDatabase);
        onCreate(sQLiteDatabase);
    }

    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        a(sQLiteDatabase);
        onCreate(sQLiteDatabase);
    }

    public void a(SQLiteDatabase sQLiteDatabase) {
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            b(sQLiteDatabase, (String) it.next());
        }
    }

    /* Access modifiers changed, original: 0000 */
    public String a(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("property_");
        stringBuilder.append(str);
        return u.a(stringBuilder.toString());
    }
}
