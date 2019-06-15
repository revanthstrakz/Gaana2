package androidx.work.impl;

import android.content.BroadcastReceiver.PendingResult;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import androidx.work.ExistingWorkPolicy;
import androidx.work.WorkerParameters;
import androidx.work.a;
import androidx.work.f;
import androidx.work.g;
import androidx.work.i;
import androidx.work.impl.utils.ForceStopRunnable;
import androidx.work.impl.utils.a.b;
import androidx.work.impl.utils.e;
import androidx.work.k;
import java.util.Arrays;
import java.util.List;

@RestrictTo({Scope.LIBRARY_GROUP})
public class h extends k {
    private static h k;
    private static h l;
    private static final Object m = new Object();
    private Context a;
    private a b;
    private WorkDatabase c;
    private androidx.work.impl.utils.a.a d;
    private List<d> e;
    private c f;
    private e g;
    private boolean h;
    private PendingResult i;
    private final i j;

    @Nullable
    @RestrictTo({Scope.LIBRARY_GROUP})
    public static h b() {
        synchronized (m) {
            h hVar;
            if (k != null) {
                hVar = k;
                return hVar;
            }
            hVar = l;
            return hVar;
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public static void b(@NonNull Context context, @NonNull a aVar) {
        synchronized (m) {
            if (k == null || l == null) {
                if (k == null) {
                    context = context.getApplicationContext();
                    if (l == null) {
                        l = new h(context, aVar, new b());
                    }
                    k = l;
                }
            } else {
                throw new IllegalStateException("WorkManager is already initialized.  Did you try to initialize it manually without disabling WorkManagerInitializer? See WorkManager#initialize(Context, Configuration) or the class levelJavadoc for more information.");
            }
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public h(@NonNull Context context, @NonNull a aVar, @NonNull androidx.work.impl.utils.a.a aVar2) {
        this(context, aVar, aVar2, context.getResources().getBoolean(i.a.workmanager_test_configuration));
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public h(@NonNull Context context, @NonNull a aVar, @NonNull androidx.work.impl.utils.a.a aVar2, boolean z) {
        this.j = new i();
        Context applicationContext = context.getApplicationContext();
        WorkDatabase a = WorkDatabase.a(applicationContext, z);
        f.a(new f.a(aVar.c()));
        Context context2 = context;
        a aVar3 = aVar;
        androidx.work.impl.utils.a.a aVar4 = aVar2;
        WorkDatabase workDatabase = a;
        List a2 = a(applicationContext);
        a(context2, aVar3, aVar4, workDatabase, a2, new c(context2, aVar3, aVar4, workDatabase, a2));
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public Context c() {
        return this.a;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public WorkDatabase d() {
        return this.c;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @NonNull
    public a e() {
        return this.b;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @NonNull
    public List<d> f() {
        return this.e;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @NonNull
    public c g() {
        return this.f;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @NonNull
    public androidx.work.impl.utils.a.a h() {
        return this.d;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @NonNull
    public e i() {
        return this.g;
    }

    @NonNull
    public androidx.work.h a(@NonNull String str, @NonNull ExistingWorkPolicy existingWorkPolicy, @NonNull List<g> list) {
        return new f(this, str, existingWorkPolicy, list).i();
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void a(String str) {
        a(str, null);
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void a(String str, WorkerParameters.a aVar) {
        this.d.a(new androidx.work.impl.utils.f(this, str, aVar));
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void b(String str) {
        this.d.a(new androidx.work.impl.utils.g(this, str));
    }

    public void j() {
        if (VERSION.SDK_INT >= 23) {
            androidx.work.impl.background.systemjob.b.a(c());
        }
        d().m().b();
        e.a(e(), d(), f());
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void k() {
        synchronized (m) {
            this.h = true;
            if (this.i != null) {
                this.i.finish();
                this.i = null;
            }
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void a(@NonNull PendingResult pendingResult) {
        synchronized (m) {
            this.i = pendingResult;
            if (this.h) {
                this.i.finish();
                this.i = null;
            }
        }
    }

    private void a(@NonNull Context context, @NonNull a aVar, @NonNull androidx.work.impl.utils.a.a aVar2, @NonNull WorkDatabase workDatabase, @NonNull List<d> list, @NonNull c cVar) {
        context = context.getApplicationContext();
        this.a = context;
        this.b = aVar;
        this.d = aVar2;
        this.c = workDatabase;
        this.e = list;
        this.f = cVar;
        this.g = new e(this.a);
        this.h = false;
        this.d.a(new ForceStopRunnable(context, this));
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @NonNull
    public List<d> a(Context context) {
        return Arrays.asList(new d[]{e.a(context, this), new androidx.work.impl.background.a.a(context, this)});
    }
}
