package androidx.work.impl;

import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomDatabase.a;
import android.arch.persistence.room.d;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import androidx.work.impl.b.b;
import androidx.work.impl.b.e;
import androidx.work.impl.b.h;
import androidx.work.impl.b.k;
import androidx.work.impl.b.n;
import java.util.concurrent.TimeUnit;

@RestrictTo({Scope.LIBRARY_GROUP})
public abstract class WorkDatabase extends RoomDatabase {
    private static final long d = TimeUnit.DAYS.toMillis(7);

    public abstract k m();

    public abstract b n();

    public abstract n o();

    public abstract e p();

    public abstract h q();

    public static WorkDatabase a(Context context, boolean z) {
        a a;
        if (z) {
            a = d.a(context, WorkDatabase.class).a();
        } else {
            a = d.a(context, WorkDatabase.class, "androidx.work.workdb");
        }
        return (WorkDatabase) a.a(j()).a(g.a).a(new g.a(context, 2, 3)).a(g.b).a(g.c).b().c();
    }

    static RoomDatabase.b j() {
        return new RoomDatabase.b() {
            public void b(@NonNull android.arch.persistence.a.b bVar) {
                super.b(bVar);
                bVar.a();
                try {
                    bVar.c("UPDATE workspec SET state=0, schedule_requested_at=-1 WHERE state=1");
                    bVar.c(WorkDatabase.k());
                    bVar.c();
                } finally {
                    bVar.b();
                }
            }
        };
    }

    static String k() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DELETE FROM workspec WHERE state IN (2, 3, 5) AND (period_start_time + minimum_retention_duration) < ");
        stringBuilder.append(l());
        stringBuilder.append(" AND (SELECT COUNT(*)=0 FROM dependency WHERE     prerequisite_id=id AND     work_spec_id NOT IN         (SELECT id FROM workspec WHERE state IN (2, 3, 5)))");
        return stringBuilder.toString();
    }

    static long l() {
        return System.currentTimeMillis() - d;
    }
}
