package androidx.work.impl.b;

import android.arch.persistence.a.e;
import android.arch.persistence.a.f;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.b;
import android.arch.persistence.room.g;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;

public class o implements n {
    private final RoomDatabase a;
    private final b b;

    public o(RoomDatabase roomDatabase) {
        this.a = roomDatabase;
        this.b = new b<m>(roomDatabase) {
            public String a() {
                return "INSERT OR IGNORE INTO `WorkTag`(`tag`,`work_spec_id`) VALUES (?,?)";
            }

            public void a(f fVar, m mVar) {
                if (mVar.a == null) {
                    fVar.a(1);
                } else {
                    fVar.a(1, mVar.a);
                }
                if (mVar.b == null) {
                    fVar.a(2);
                } else {
                    fVar.a(2, mVar.b);
                }
            }
        };
    }

    public void a(m mVar) {
        this.a.f();
        try {
            this.b.a(mVar);
            this.a.h();
        } finally {
            this.a.g();
        }
    }

    public List<String> a(String str) {
        e a = g.a("SELECT DISTINCT tag FROM worktag WHERE work_spec_id=?", 1);
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
}
