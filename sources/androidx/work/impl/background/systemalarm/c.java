package androidx.work.impl.background.systemalarm;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.WorkerThread;
import androidx.work.f;
import androidx.work.impl.a.d;
import androidx.work.impl.b.j;
import java.util.ArrayList;
import java.util.List;

@RestrictTo({Scope.LIBRARY_GROUP})
class c {
    private static final String a = f.a("ConstraintsCmdHandler");
    private final Context b;
    private final int c;
    private final e d;
    private final d e = new d(this.b, null);

    c(@NonNull Context context, int i, @NonNull e eVar) {
        this.b = context;
        this.c = i;
        this.d = eVar;
    }

    /* Access modifiers changed, original: 0000 */
    @WorkerThread
    public void a() {
        List<j> c = this.d.d().d().m().c();
        ConstraintProxy.a(this.b, c);
        this.e.a((List) c);
        ArrayList<j> arrayList = new ArrayList(c.size());
        long currentTimeMillis = System.currentTimeMillis();
        for (j jVar : c) {
            String str = jVar.a;
            if (currentTimeMillis >= jVar.c() && (!jVar.d() || this.e.a(str))) {
                arrayList.add(jVar);
            }
        }
        for (j jVar2 : arrayList) {
            Intent b = b.b(this.b, jVar2.a);
            f.a().b(a, String.format("Creating a delay_met command for workSpec with id (%s)", new Object[]{r1}), new Throwable[0]);
            this.d.a(new a(this.d, b, this.c));
        }
        this.e.a();
    }
}
