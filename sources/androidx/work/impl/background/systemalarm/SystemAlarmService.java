package androidx.work.impl.background.systemalarm;

import android.arch.lifecycle.h;
import android.content.Intent;
import android.support.annotation.MainThread;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import androidx.work.f;

@RestrictTo({Scope.LIBRARY_GROUP})
public class SystemAlarmService extends h implements b {
    private static final String a = f.a("SystemAlarmService");
    private e b;

    public void onCreate() {
        super.onCreate();
        this.b = new e(this);
        this.b.a((b) this);
    }

    public void onDestroy() {
        super.onDestroy();
        this.b.a();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        if (intent != null) {
            this.b.a(intent, i2);
        }
        return 3;
    }

    @MainThread
    public void a() {
        f.a().b(a, "All commands completed in dispatcher", new Throwable[0]);
        androidx.work.impl.utils.h.a();
        stopSelf();
    }
}
