package androidx.work.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import androidx.work.a;
import androidx.work.f;
import androidx.work.impl.b.j;
import androidx.work.impl.b.k;
import androidx.work.impl.background.systemalarm.SystemAlarmService;
import androidx.work.impl.background.systemjob.SystemJobService;
import androidx.work.impl.background.systemjob.b;
import androidx.work.impl.utils.d;
import java.util.List;

@RestrictTo({Scope.LIBRARY_GROUP})
public class e {
    private static final String a = f.a("Schedulers");

    public static void a(@NonNull a aVar, @NonNull WorkDatabase workDatabase, List<d> list) {
        if (list != null && list.size() != 0) {
            k m = workDatabase.m();
            workDatabase.f();
            try {
                List<j> a = m.a(aVar.f());
                if (a != null && a.size() > 0) {
                    long currentTimeMillis = System.currentTimeMillis();
                    for (j jVar : a) {
                        m.b(jVar.a, currentTimeMillis);
                    }
                }
                workDatabase.h();
                if (a != null && a.size() > 0) {
                    j[] jVarArr = (j[]) a.toArray(new j[0]);
                    for (d a2 : list) {
                        a2.a(jVarArr);
                    }
                }
            } finally {
                workDatabase.g();
            }
        }
    }

    @SuppressLint({"NewApi"})
    @NonNull
    static d a(@NonNull Context context, @NonNull h hVar) {
        d bVar;
        boolean z = true;
        if (VERSION.SDK_INT >= 23) {
            bVar = new b(context, hVar);
            d.a(context, SystemJobService.class, true);
            f.a().b(a, "Created SystemJobScheduler and enabled SystemJobService", new Throwable[0]);
            z = false;
        } else {
            bVar = new androidx.work.impl.background.systemalarm.f(context);
            f.a().b(a, "Created SystemAlarmScheduler", new Throwable[0]);
        }
        d.a(context, SystemAlarmService.class, z);
        return bVar;
    }
}
