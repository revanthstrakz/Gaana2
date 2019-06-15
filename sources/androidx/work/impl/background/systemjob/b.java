package androidx.work.impl.background.systemjob;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.VisibleForTesting;
import androidx.work.WorkInfo.State;
import androidx.work.f;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.b.j;
import androidx.work.impl.d;
import androidx.work.impl.h;
import androidx.work.impl.utils.c;
import java.util.List;

@RequiresApi(23)
@RestrictTo({Scope.LIBRARY_GROUP})
public class b implements d {
    private static final String a = f.a("SystemJobScheduler");
    private final JobScheduler b;
    private final h c;
    private final c d;
    private final a e;

    public b(@NonNull Context context, @NonNull h hVar) {
        this(context, hVar, (JobScheduler) context.getSystemService("jobscheduler"), new a(context));
    }

    @VisibleForTesting
    public b(Context context, h hVar, JobScheduler jobScheduler, a aVar) {
        this.c = hVar;
        this.b = jobScheduler;
        this.d = new c(context);
        this.e = aVar;
    }

    public void a(j... jVarArr) {
        WorkDatabase d = this.c.d();
        int length = jVarArr.length;
        int i = 0;
        while (i < length) {
            j jVar = jVarArr[i];
            d.f();
            try {
                j b = d.m().b(jVar.a);
                f a;
                String str;
                StringBuilder stringBuilder;
                if (b == null) {
                    a = f.a();
                    str = a;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Skipping scheduling ");
                    stringBuilder.append(jVar.a);
                    stringBuilder.append(" because it's no longer in the DB");
                    a.d(str, stringBuilder.toString(), new Throwable[0]);
                } else if (b.b != State.ENQUEUED) {
                    a = f.a();
                    str = a;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Skipping scheduling ");
                    stringBuilder.append(jVar.a);
                    stringBuilder.append(" because it is no longer enqueued");
                    a.d(str, stringBuilder.toString(), new Throwable[0]);
                } else {
                    androidx.work.impl.b.d a2 = d.p().a(jVar.a);
                    if (a2 == null || a(this.b, jVar.a) == null) {
                        int a3 = a2 != null ? a2.b : this.d.a(this.c.e().d(), this.c.e().e());
                        if (a2 == null) {
                            this.c.d().p().a(new androidx.work.impl.b.d(jVar.a, a3));
                        }
                        a(jVar, a3);
                        if (VERSION.SDK_INT == 23) {
                            a(jVar, this.d.a(this.c.e().d(), this.c.e().e()));
                        }
                        d.h();
                    } else {
                        f.a().b(a, String.format("Skipping scheduling %s because JobScheduler is aware of it already.", new Object[]{jVar.a}), new Throwable[0]);
                    }
                }
                d.g();
                i++;
            } catch (Throwable th) {
                d.g();
            }
        }
    }

    @VisibleForTesting
    public void a(j jVar, int i) {
        JobInfo a = this.e.a(jVar, i);
        f.a().b(a, String.format("Scheduling work ID %s Job ID %s", new Object[]{jVar.a, Integer.valueOf(i)}), new Throwable[0]);
        this.b.schedule(a);
    }

    public void a(@NonNull String str) {
        List<JobInfo> allPendingJobs = this.b.getAllPendingJobs();
        if (allPendingJobs != null) {
            for (JobInfo jobInfo : allPendingJobs) {
                if (str.equals(jobInfo.getExtras().getString("EXTRA_WORK_SPEC_ID"))) {
                    this.c.d().p().b(str);
                    this.b.cancel(jobInfo.getId());
                    if (VERSION.SDK_INT != 23) {
                        return;
                    }
                }
            }
        }
    }

    public static void a(@NonNull Context context) {
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
        if (jobScheduler != null) {
            List<JobInfo> allPendingJobs = jobScheduler.getAllPendingJobs();
            if (allPendingJobs != null) {
                for (JobInfo jobInfo : allPendingJobs) {
                    if (jobInfo.getExtras().containsKey("EXTRA_WORK_SPEC_ID")) {
                        jobScheduler.cancel(jobInfo.getId());
                    }
                }
            }
        }
    }

    private static JobInfo a(@NonNull JobScheduler jobScheduler, @NonNull String str) {
        List<JobInfo> allPendingJobs = jobScheduler.getAllPendingJobs();
        if (allPendingJobs != null) {
            for (JobInfo jobInfo : allPendingJobs) {
                PersistableBundle extras = jobInfo.getExtras();
                if (extras != null && extras.containsKey("EXTRA_WORK_SPEC_ID") && str.equals(extras.getString("EXTRA_WORK_SPEC_ID"))) {
                    return jobInfo;
                }
            }
        }
        return null;
    }
}
