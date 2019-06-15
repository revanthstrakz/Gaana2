package androidx.work.impl.background.systemalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.app.NotificationCompat;
import androidx.work.f;
import androidx.work.impl.b.d;
import androidx.work.impl.b.e;
import androidx.work.impl.h;
import androidx.work.impl.utils.c;
import com.google.android.exoplayer2.C;

@RestrictTo({Scope.LIBRARY_GROUP})
class a {
    private static final String a = f.a("Alarms");

    public static void a(@NonNull Context context, @NonNull h hVar, @NonNull String str, long j) {
        e p = hVar.d().p();
        d a = p.a(str);
        if (a != null) {
            a(context, str, a.b);
            a(context, str, a.b, j);
            return;
        }
        int a2 = new c(context).a();
        p.a(new d(str, a2));
        a(context, str, a2, j);
    }

    public static void a(@NonNull Context context, @NonNull h hVar, @NonNull String str) {
        e p = hVar.d().p();
        d a = p.a(str);
        if (a != null) {
            a(context, str, a.b);
            f.a().b(a, String.format("Removing SystemIdInfo for workSpecId (%s)", new Object[]{str}), new Throwable[0]);
            p.b(str);
        }
    }

    private static void a(@NonNull Context context, @NonNull String str, int i) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        PendingIntent service = PendingIntent.getService(context, i, b.b(context, str), C.ENCODING_PCM_A_LAW);
        if (service != null && alarmManager != null) {
            f.a().b(a, String.format("Cancelling existing alarm with (workSpecId, systemId) (%s, %s)", new Object[]{str, Integer.valueOf(i)}), new Throwable[0]);
            alarmManager.cancel(service);
        }
    }

    private static void a(@NonNull Context context, @NonNull String str, int i, long j) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        PendingIntent service = PendingIntent.getService(context, i, b.b(context, str), 1073741824);
        if (alarmManager == null) {
            return;
        }
        if (VERSION.SDK_INT >= 19) {
            alarmManager.setExact(0, j, service);
        } else {
            alarmManager.set(0, j, service);
        }
    }
}
