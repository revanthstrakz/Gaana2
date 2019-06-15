package android.arch.persistence.a.a;

import android.arch.persistence.a.c;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.RequiresApi;

class b implements c {
    private final a a;

    static class a extends SQLiteOpenHelper {
        final a[] a;
        final android.arch.persistence.a.c.a b;
        private boolean c;

        a(Context context, String str, final a[] aVarArr, final android.arch.persistence.a.c.a aVar) {
            super(context, str, null, aVar.a, new DatabaseErrorHandler() {
                public void onCorruption(SQLiteDatabase sQLiteDatabase) {
                    android.arch.persistence.a.b bVar = aVarArr[0];
                    if (bVar != null) {
                        aVar.d(bVar);
                    }
                }
            });
            this.b = aVar;
            this.a = aVarArr;
        }

        /* Access modifiers changed, original: declared_synchronized */
        public synchronized android.arch.persistence.a.b a() {
            this.c = false;
            SQLiteDatabase writableDatabase = super.getWritableDatabase();
            if (this.c) {
                close();
                return a();
            }
            return a(writableDatabase);
        }

        /* Access modifiers changed, original: 0000 */
        public a a(SQLiteDatabase sQLiteDatabase) {
            if (this.a[0] == null) {
                this.a[0] = new a(sQLiteDatabase);
            }
            return this.a[0];
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            this.b.b(a(sQLiteDatabase));
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            this.c = true;
            this.b.a(a(sQLiteDatabase), i, i2);
        }

        public void onConfigure(SQLiteDatabase sQLiteDatabase) {
            this.b.a(a(sQLiteDatabase));
        }

        public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            this.c = true;
            this.b.b(a(sQLiteDatabase), i, i2);
        }

        public void onOpen(SQLiteDatabase sQLiteDatabase) {
            if (!this.c) {
                this.b.c(a(sQLiteDatabase));
            }
        }

        public synchronized void close() {
            super.close();
            this.a[0] = null;
        }
    }

    b(Context context, String str, android.arch.persistence.a.c.a aVar) {
        this.a = a(context, str, aVar);
    }

    private a a(Context context, String str, android.arch.persistence.a.c.a aVar) {
        return new a(context, str, new a[1], aVar);
    }

    @RequiresApi(api = 16)
    public void a(boolean z) {
        this.a.setWriteAheadLoggingEnabled(z);
    }

    public android.arch.persistence.a.b a() {
        return this.a.a();
    }
}
