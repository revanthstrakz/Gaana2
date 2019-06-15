package android.arch.persistence.a.a;

import android.database.sqlite.SQLiteProgram;

class d implements android.arch.persistence.a.d {
    private final SQLiteProgram a;

    d(SQLiteProgram sQLiteProgram) {
        this.a = sQLiteProgram;
    }

    public void a(int i) {
        this.a.bindNull(i);
    }

    public void a(int i, long j) {
        this.a.bindLong(i, j);
    }

    public void a(int i, double d) {
        this.a.bindDouble(i, d);
    }

    public void a(int i, String str) {
        this.a.bindString(i, str);
    }

    public void a(int i, byte[] bArr) {
        this.a.bindBlob(i, bArr);
    }

    public void close() {
        this.a.close();
    }
}
