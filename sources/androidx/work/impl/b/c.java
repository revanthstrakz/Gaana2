package androidx.work.impl.b;

import android.arch.persistence.a.e;
import android.arch.persistence.a.f;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.b;
import android.arch.persistence.room.g;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;

public class c implements b {
    private final RoomDatabase a;
    private final b b;

    public c(RoomDatabase roomDatabase) {
        this.a = roomDatabase;
        this.b = new b<a>(roomDatabase) {
            public String a() {
                return "INSERT OR IGNORE INTO `Dependency`(`work_spec_id`,`prerequisite_id`) VALUES (?,?)";
            }

            public void a(f fVar, a aVar) {
                if (aVar.a == null) {
                    fVar.a(1);
                } else {
                    fVar.a(1, aVar.a);
                }
                if (aVar.b == null) {
                    fVar.a(2);
                } else {
                    fVar.a(2, aVar.b);
                }
            }
        };
    }

    public void a(a aVar) {
        this.a.f();
        try {
            this.b.a(aVar);
            this.a.h();
        } finally {
            this.a.g();
        }
    }

    public boolean a(String str) {
        e a = g.a("SELECT COUNT(*)=0 FROM dependency WHERE work_spec_id=? AND prerequisite_id IN (SELECT id FROM workspec WHERE state!=2)", 1);
        if (str == null) {
            a.a(1);
        } else {
            a.a(1, str);
        }
        Cursor a2 = this.a.a(a);
        try {
            boolean z = false;
            if (a2.moveToFirst() && a2.getInt(0) != 0) {
                z = true;
            }
            a2.close();
            a.b();
            return z;
        } catch (Throwable th) {
            a2.close();
            a.b();
        }
    }

    public List<String> b(String str) {
        e a = g.a("SELECT work_spec_id FROM dependency WHERE prerequisite_id=?", 1);
        if (str == null) {
            a.a(1);
        } else {
            a.a(1, str);
        }
        Cursor a2 = this.a.a(a);
        try {
            List<String> arrayList = new ArrayList(a2.getCount());
            while (a2.moveToNext()) {
                arrayList.add(a2.getString(0));
            }
            return arrayList;
        } finally {
            a2.close();
            a.b();
        }
    }

    public boolean c(String str) {
        e a = g.a("SELECT COUNT(*)>0 FROM dependency WHERE prerequisite_id=?", 1);
        if (str == null) {
            a.a(1);
        } else {
            a.a(1, str);
        }
        Cursor a2 = this.a.a(a);
        try {
            boolean z = false;
            if (a2.moveToFirst() && a2.getInt(0) != 0) {
                z = true;
            }
            a2.close();
            a.b();
            return z;
        } catch (Throwable th) {
            a2.close();
            a.b();
        }
    }
}
