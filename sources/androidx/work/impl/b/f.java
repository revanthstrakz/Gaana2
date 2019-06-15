package androidx.work.impl.b;

import android.arch.persistence.a.e;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.b;
import android.arch.persistence.room.g;
import android.arch.persistence.room.h;
import android.database.Cursor;

public class f implements e {
    private final RoomDatabase a;
    private final b b;
    private final h c;

    public f(RoomDatabase roomDatabase) {
        this.a = roomDatabase;
        this.b = new b<d>(roomDatabase) {
            public String a() {
                return "INSERT OR REPLACE INTO `SystemIdInfo`(`work_spec_id`,`system_id`) VALUES (?,?)";
            }

            public void a(android.arch.persistence.a.f fVar, d dVar) {
                if (dVar.a == null) {
                    fVar.a(1);
                } else {
                    fVar.a(1, dVar.a);
                }
                fVar.a(2, (long) dVar.b);
            }
        };
        this.c = new h(roomDatabase) {
            public String a() {
                return "DELETE FROM SystemIdInfo where work_spec_id=?";
            }
        };
    }

    public void a(d dVar) {
        this.a.f();
        try {
            this.b.a(dVar);
            this.a.h();
        } finally {
            this.a.g();
        }
    }

    public void b(String str) {
        android.arch.persistence.a.f c = this.c.c();
        this.a.f();
        if (str == null) {
            try {
                c.a(1);
            } catch (Throwable th) {
                this.a.g();
                this.c.a(c);
            }
        } else {
            c.a(1, str);
        }
        c.a();
        this.a.h();
        this.a.g();
        this.c.a(c);
    }

    public d a(String str) {
        e a = g.a("SELECT * FROM SystemIdInfo WHERE work_spec_id=?", 1);
        if (str == null) {
            a.a(1);
        } else {
            a.a(1, str);
        }
        Cursor a2 = this.a.a(a);
        try {
            d dVar = a2.moveToFirst() ? new d(a2.getString(a2.getColumnIndexOrThrow("work_spec_id")), a2.getInt(a2.getColumnIndexOrThrow("system_id"))) : null;
            a2.close();
            a.b();
            return dVar;
        } catch (Throwable th) {
            a2.close();
            a.b();
        }
    }
}
