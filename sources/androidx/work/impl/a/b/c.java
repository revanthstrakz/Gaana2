package androidx.work.impl.a.b;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import androidx.work.f;

@RestrictTo({Scope.LIBRARY_GROUP})
public abstract class c<T> extends d<T> {
    private static final String b = f.a("BrdcstRcvrCnstrntTrckr");
    private final BroadcastReceiver c = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                c.this.a(context, intent);
            }
        }
    };

    public abstract void a(Context context, @NonNull Intent intent);

    public abstract IntentFilter b();

    public c(Context context) {
        super(context);
    }

    public void d() {
        f.a().b(b, String.format("%s: registering receiver", new Object[]{getClass().getSimpleName()}), new Throwable[0]);
        this.a.registerReceiver(this.c, b());
    }

    public void e() {
        f.a().b(b, String.format("%s: unregistering receiver", new Object[]{getClass().getSimpleName()}), new Throwable[0]);
        this.a.unregisterReceiver(this.c);
    }
}
