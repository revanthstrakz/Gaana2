package androidx.work.impl.background.systemalarm;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import androidx.work.impl.b.j;
import androidx.work.impl.d;

@RestrictTo({Scope.LIBRARY_GROUP})
public class f implements d {
    private static final String a = androidx.work.f.a("SystemAlarmScheduler");
    private final Context b;

    public f(@NonNull Context context) {
        this.b = context.getApplicationContext();
    }

    public void a(j... jVarArr) {
        for (j a : jVarArr) {
            a(a);
        }
    }

    public void a(@NonNull String str) {
        this.b.startService(b.c(this.b, str));
    }

    private void a(@NonNull j jVar) {
        androidx.work.f.a().b(a, String.format("Scheduling work with workSpecId %s", new Object[]{jVar.a}), new Throwable[0]);
        this.b.startService(b.a(this.b, jVar.a));
    }
}
