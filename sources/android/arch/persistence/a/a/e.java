package android.arch.persistence.a.a;

import android.arch.persistence.a.f;
import android.database.sqlite.SQLiteStatement;

class e extends d implements f {
    private final SQLiteStatement a;

    e(SQLiteStatement sQLiteStatement) {
        super(sQLiteStatement);
        this.a = sQLiteStatement;
    }

    public int a() {
        return this.a.executeUpdateDelete();
    }

    public long b() {
        return this.a.executeInsert();
    }
}
