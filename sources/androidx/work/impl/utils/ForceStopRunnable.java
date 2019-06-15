package androidx.work.impl.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.NotificationCompat;
import androidx.work.f;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.b.j;
import androidx.work.impl.b.k;
import androidx.work.impl.e;
import androidx.work.impl.h;
import com.google.android.exoplayer2.C;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestrictTo({Scope.LIBRARY_GROUP})
public class ForceStopRunnable implements Runnable {
    private static final String a = f.a("ForceStopRunnable");
    private static final long b = TimeUnit.DAYS.toMillis(3650);
    private final Context c;
    private final h d;

    @RestrictTo({Scope.LIBRARY_GROUP})
    public static class BroadcastReceiver extends android.content.BroadcastReceiver {
        private static final String a = f.a("ForceStopRunnable$Rcvr");

        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                if ("ACTION_FORCE_STOP_RESCHEDULE".equals(intent.getAction())) {
                    f.a().a(a, "Rescheduling alarm that keeps track of force-stops.", new Throwable[0]);
                    ForceStopRunnable.b(context);
                }
            }
        }
    }

    public ForceStopRunnable(@NonNull Context context, @NonNull h hVar) {
        this.c = context.getApplicationContext();
        this.d = hVar;
    }

    public void run() {
        if (b()) {
            f.a().b(a, "Rescheduling Workers.", new Throwable[0]);
            this.d.j();
            this.d.i().a(false);
        } else if (a()) {
            f.a().b(a, "Application was force-stopped, rescheduling.", new Throwable[0]);
            this.d.j();
        } else {
            WorkDatabase d = this.d.d();
            k m = d.m();
            try {
                d.f();
                List<j> d2 = m.d();
                if (!(d2 == null || d2.isEmpty())) {
                    f.a().b(a, "Found unfinished work, scheduling it.", new Throwable[0]);
                    for (j jVar : d2) {
                        m.b(jVar.a, -1);
                    }
                    e.a(this.d.e(), d, this.d.f());
                }
                d.h();
                f.a().b(a, "Unfinished Workers exist, rescheduling.", new Throwable[0]);
            } finally {
                d.g();
            }
        }
        this.d.k();
    }

    @VisibleForTesting
    public boolean a() {
        if (a(this.c, C.ENCODING_PCM_A_LAW) != null) {
            return false;
        }
        b(this.c);
        return true;
    }

    /* Access modifiers changed, original: 0000 */
    @VisibleForTesting
    public boolean b() {
        return this.d.i().a();
    }

    private static PendingIntent a(Context context, int i) {
        return PendingIntent.getBroadcast(context, -1, a(context), i);
    }

    @VisibleForTesting
    static Intent a(Context context) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(context, BroadcastReceiver.class));
        intent.setAction("ACTION_FORCE_STOP_RESCHEDULE");
        return intent;
    }

    static void b(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        PendingIntent a = a(context, 134217728);
        long currentTimeMillis = System.currentTimeMillis() + b;
        if (alarmManager == null) {
            return;
        }
        if (VERSION.SDK_INT >= 19) {
            alarmManager.setExact(0, currentTimeMillis, a);
        } else {
            alarmManager.set(0, currentTimeMillis, a);
        }
    }
}
