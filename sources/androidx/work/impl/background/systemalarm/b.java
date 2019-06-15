package androidx.work.impl.background.systemalarm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.WorkerThread;
import androidx.work.f;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.a;
import androidx.work.impl.b.j;
import java.util.HashMap;
import java.util.Map;

@RestrictTo({Scope.LIBRARY_GROUP})
public class b implements a {
    private static final String a = f.a("CommandHandler");
    private final Context b;
    private final Map<String, a> c = new HashMap();
    private final Object d = new Object();

    static Intent a(@NonNull Context context, @NonNull String str) {
        Intent intent = new Intent(context, SystemAlarmService.class);
        intent.setAction("ACTION_SCHEDULE_WORK");
        intent.putExtra("KEY_WORKSPEC_ID", str);
        return intent;
    }

    static Intent b(@NonNull Context context, @NonNull String str) {
        Intent intent = new Intent(context, SystemAlarmService.class);
        intent.setAction("ACTION_DELAY_MET");
        intent.putExtra("KEY_WORKSPEC_ID", str);
        return intent;
    }

    static Intent c(@NonNull Context context, @NonNull String str) {
        Intent intent = new Intent(context, SystemAlarmService.class);
        intent.setAction("ACTION_STOP_WORK");
        intent.putExtra("KEY_WORKSPEC_ID", str);
        return intent;
    }

    static Intent a(@NonNull Context context) {
        Intent intent = new Intent(context, SystemAlarmService.class);
        intent.setAction("ACTION_CONSTRAINTS_CHANGED");
        return intent;
    }

    static Intent b(@NonNull Context context) {
        Intent intent = new Intent(context, SystemAlarmService.class);
        intent.setAction("ACTION_RESCHEDULE");
        return intent;
    }

    static Intent a(@NonNull Context context, @NonNull String str, boolean z) {
        Intent intent = new Intent(context, SystemAlarmService.class);
        intent.setAction("ACTION_EXECUTION_COMPLETED");
        intent.putExtra("KEY_WORKSPEC_ID", str);
        intent.putExtra("KEY_NEEDS_RESCHEDULE", z);
        return intent;
    }

    b(@NonNull Context context) {
        this.b = context;
    }

    public void a(@NonNull String str, boolean z) {
        synchronized (this.d) {
            a aVar = (a) this.c.remove(str);
            if (aVar != null) {
                aVar.a(str, z);
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean a() {
        int isEmpty;
        synchronized (this.d) {
            isEmpty = this.c.isEmpty() ^ 1;
        }
        return isEmpty;
    }

    /* Access modifiers changed, original: 0000 */
    @WorkerThread
    public void a(@NonNull Intent intent, int i, @NonNull e eVar) {
        String action = intent.getAction();
        if ("ACTION_CONSTRAINTS_CHANGED".equals(action)) {
            e(intent, i, eVar);
        } else if ("ACTION_RESCHEDULE".equals(action)) {
            f(intent, i, eVar);
        } else {
            if (!a(intent.getExtras(), "KEY_WORKSPEC_ID")) {
                f.a().e(a, String.format("Invalid request for %s, requires %s.", new Object[]{action, "KEY_WORKSPEC_ID"}), new Throwable[0]);
            } else if ("ACTION_SCHEDULE_WORK".equals(action)) {
                b(intent, i, eVar);
            } else if ("ACTION_DELAY_MET".equals(action)) {
                c(intent, i, eVar);
            } else if ("ACTION_STOP_WORK".equals(action)) {
                d(intent, i, eVar);
            } else if ("ACTION_EXECUTION_COMPLETED".equals(action)) {
                g(intent, i, eVar);
            } else {
                f.a().d(a, String.format("Ignoring intent %s", new Object[]{intent}), new Throwable[0]);
            }
        }
    }

    private void b(@NonNull Intent intent, int i, @NonNull e eVar) {
        String string = intent.getExtras().getString("KEY_WORKSPEC_ID");
        f.a().b(a, String.format("Handling schedule work for %s", new Object[]{string}), new Throwable[0]);
        WorkDatabase d = eVar.d().d();
        d.f();
        try {
            j b = d.m().b(string);
            f a;
            String str;
            StringBuilder stringBuilder;
            if (b == null) {
                a = f.a();
                str = a;
                stringBuilder = new StringBuilder();
                stringBuilder.append("Skipping scheduling ");
                stringBuilder.append(string);
                stringBuilder.append(" because it's no longer in the DB");
                a.d(str, stringBuilder.toString(), new Throwable[0]);
            } else if (b.b.isFinished()) {
                a = f.a();
                str = a;
                stringBuilder = new StringBuilder();
                stringBuilder.append("Skipping scheduling ");
                stringBuilder.append(string);
                stringBuilder.append("because it is finished.");
                a.d(str, stringBuilder.toString(), new Throwable[0]);
                d.g();
            } else {
                long c = b.c();
                if (b.d()) {
                    f.a().b(a, String.format("Opportunistically setting an alarm for %s at %s", new Object[]{string, Long.valueOf(c)}), new Throwable[0]);
                    a.a(this.b, eVar.d(), string, c);
                    eVar.a(new a(eVar, a(this.b), i));
                } else {
                    f.a().b(a, String.format("Setting up Alarms for %s at %s", new Object[]{string, Long.valueOf(c)}), new Throwable[0]);
                    a.a(this.b, eVar.d(), string, c);
                }
                d.h();
                d.g();
            }
        } finally {
            d.g();
        }
    }

    private void c(@NonNull Intent intent, int i, @NonNull e eVar) {
        Bundle extras = intent.getExtras();
        synchronized (this.d) {
            String string = extras.getString("KEY_WORKSPEC_ID");
            f.a().b(a, String.format("Handing delay met for %s", new Object[]{string}), new Throwable[0]);
            if (this.c.containsKey(string)) {
                f.a().b(a, String.format("WorkSpec %s is already being handled for ACTION_DELAY_MET", new Object[]{string}), new Throwable[0]);
            } else {
                d dVar = new d(this.b, i, string, eVar);
                this.c.put(string, dVar);
                dVar.a();
            }
        }
    }

    private void d(@NonNull Intent intent, int i, @NonNull e eVar) {
        String string = intent.getExtras().getString("KEY_WORKSPEC_ID");
        f.a().b(a, String.format("Handing stopWork work for %s", new Object[]{string}), new Throwable[0]);
        eVar.d().b(string);
        a.a(this.b, eVar.d(), string);
        eVar.a(string, false);
    }

    private void e(@NonNull Intent intent, int i, @NonNull e eVar) {
        f.a().b(a, String.format("Handling constraints changed %s", new Object[]{intent}), new Throwable[0]);
        new c(this.b, i, eVar).a();
    }

    private void f(@NonNull Intent intent, int i, @NonNull e eVar) {
        f.a().b(a, String.format("Handling reschedule %s, %s", new Object[]{intent, Integer.valueOf(i)}), new Throwable[0]);
        eVar.d().j();
    }

    private void g(@NonNull Intent intent, int i, @NonNull e eVar) {
        Bundle extras = intent.getExtras();
        String string = extras.getString("KEY_WORKSPEC_ID");
        boolean z = extras.getBoolean("KEY_NEEDS_RESCHEDULE");
        f.a().b(a, String.format("Handling onExecutionCompleted %s, %s", new Object[]{intent, Integer.valueOf(i)}), new Throwable[0]);
        a(string, z);
    }

    private static boolean a(@Nullable Bundle bundle, @NonNull String... strArr) {
        if (bundle == null || bundle.isEmpty()) {
            return false;
        }
        for (String str : strArr) {
            if (bundle.get(str) == null) {
                return false;
            }
        }
        return true;
    }
}
