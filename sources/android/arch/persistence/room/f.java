package android.arch.persistence.room;

import android.arch.persistence.a.b;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;

@RestrictTo({Scope.LIBRARY_GROUP})
public class f extends android.arch.persistence.a.c.a {
    @Nullable
    private a b;
    @NonNull
    private final a c;
    @NonNull
    private final String d;
    @NonNull
    private final String e;

    @RestrictTo({Scope.LIBRARY_GROUP})
    public static abstract class a {
        public final int a;

        public abstract void a(b bVar);

        public abstract void b(b bVar);

        public abstract void c(b bVar);

        public abstract void d(b bVar);

        public abstract void e(b bVar);

        public a(int i) {
            this.a = i;
        }
    }

    public f(@NonNull a aVar, @NonNull a aVar2, @NonNull String str, @NonNull String str2) {
        super(aVar2.a);
        this.b = aVar;
        this.c = aVar2;
        this.d = str;
        this.e = str2;
    }

    public void a(b bVar) {
        super.a(bVar);
    }

    public void b(b bVar) {
        f(bVar);
        this.c.b(bVar);
        this.c.d(bVar);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:? A:{SYNTHETIC, RETURN, ORIG_RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x002f  */
    public void a(android.arch.persistence.a.b r3, int r4, int r5) {
        /*
        r2 = this;
        r0 = r2.b;
        if (r0 == 0) goto L_0x002c;
    L_0x0004:
        r0 = r2.b;
        r0 = r0.d;
        r0 = r0.a(r4, r5);
        if (r0 == 0) goto L_0x002c;
    L_0x000e:
        r0 = r0.iterator();
    L_0x0012:
        r1 = r0.hasNext();
        if (r1 == 0) goto L_0x0022;
    L_0x0018:
        r1 = r0.next();
        r1 = (android.arch.persistence.room.a.a) r1;
        r1.a(r3);
        goto L_0x0012;
    L_0x0022:
        r0 = r2.c;
        r0.e(r3);
        r2.f(r3);
        r0 = 1;
        goto L_0x002d;
    L_0x002c:
        r0 = 0;
    L_0x002d:
        if (r0 != 0) goto L_0x007e;
    L_0x002f:
        r0 = r2.b;
        if (r0 == 0) goto L_0x0046;
    L_0x0033:
        r0 = r2.b;
        r0 = r0.a(r4);
        if (r0 != 0) goto L_0x0046;
    L_0x003b:
        r4 = r2.c;
        r4.a(r3);
        r4 = r2.c;
        r4.b(r3);
        goto L_0x007e;
    L_0x0046:
        r3 = new java.lang.IllegalStateException;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "A migration from ";
        r0.append(r1);
        r0.append(r4);
        r4 = " to ";
        r0.append(r4);
        r0.append(r5);
        r4 = " was required but not found. Please provide the ";
        r0.append(r4);
        r4 = "necessary Migration path via ";
        r0.append(r4);
        r4 = "RoomDatabase.Builder.addMigration(Migration ...) or allow for ";
        r0.append(r4);
        r4 = "destructive migrations via one of the ";
        r0.append(r4);
        r4 = "RoomDatabase.Builder.fallbackToDestructiveMigration* methods.";
        r0.append(r4);
        r4 = r0.toString();
        r3.<init>(r4);
        throw r3;
    L_0x007e:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.arch.persistence.room.f.a(android.arch.persistence.a.b, int, int):void");
    }

    public void b(b bVar, int i, int i2) {
        a(bVar, i, i2);
    }

    public void c(b bVar) {
        super.c(bVar);
        e(bVar);
        this.c.c(bVar);
        this.b = null;
    }

    private void e(b bVar) {
        Object obj = null;
        if (h(bVar)) {
            Cursor a = bVar.a(new android.arch.persistence.a.a("SELECT identity_hash FROM room_master_table WHERE id = 42 LIMIT 1"));
            try {
                if (a.moveToFirst()) {
                    obj = a.getString(0);
                }
                a.close();
            } catch (Throwable th) {
                a.close();
            }
        }
        if (!this.d.equals(obj) && !this.e.equals(obj)) {
            throw new IllegalStateException("Room cannot verify the data integrity. Looks like you've changed schema but forgot to update the version number. You can simply fix this by increasing the version number.");
        }
    }

    private void f(b bVar) {
        g(bVar);
        bVar.c(e.a(this.d));
    }

    private void g(b bVar) {
        bVar.c("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
    }

    private static boolean h(b bVar) {
        Cursor b = bVar.b("SELECT 1 FROM sqlite_master WHERE type = 'table' AND name='room_master_table'");
        try {
            boolean z = false;
            if (b.moveToFirst() && b.getInt(0) != 0) {
                z = true;
            }
            b.close();
            return z;
        } catch (Throwable th) {
            b.close();
        }
    }
}
