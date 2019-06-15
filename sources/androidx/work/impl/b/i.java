package androidx.work.impl.b;

import android.arch.persistence.a.f;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.b;

public class i implements h {
    private final RoomDatabase a;
    private final b b;

    public i(RoomDatabase roomDatabase) {
        this.a = roomDatabase;
        this.b = new b<g>(roomDatabase) {
            public String a() {
                return "INSERT OR IGNORE INTO `WorkName`(`name`,`work_spec_id`) VALUES (?,?)";
            }

            public void a(f fVar, g gVar) {
                if (gVar.a == null) {
                    fVar.a(1);
                } else {
                    fVar.a(1, gVar.a);
                }
                if (gVar.b == null) {
                    fVar.a(2);
                } else {
                    fVar.a(2, gVar.b);
                }
            }
        };
    }

    public void a(g gVar) {
        this.a.f();
        try {
            this.b.a(gVar);
            this.a.h();
        } finally {
            this.a.g();
        }
    }
}
