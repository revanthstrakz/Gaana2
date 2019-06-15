package androidx.work.impl.background.systemalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import androidx.work.f;
import androidx.work.impl.h;

public class RescheduleReceiver extends BroadcastReceiver {
    private static final String a = f.a("RescheduleReceiver");

    public void onReceive(Context context, Intent intent) {
        f.a().b(a, String.format("Received intent %s", new Object[]{intent}), new Throwable[0]);
        if (VERSION.SDK_INT >= 23) {
            h b = h.b();
            if (b == null) {
                f.a().e(a, "Cannot reschedule jobs. WorkManager needs to be initialized via a ContentProvider#onCreate() or an Application#onCreate().", new Throwable[0]);
                return;
            } else {
                b.a(goAsync());
                return;
            }
        }
        context.startService(b.b(context));
    }
}
