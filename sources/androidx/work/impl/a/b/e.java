package androidx.work.impl.a.b;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.ConnectivityManager.NetworkCallback;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.net.ConnectivityManagerCompat;
import androidx.work.f;
import com.til.colombia.android.internal.d;

@RestrictTo({Scope.LIBRARY_GROUP})
public class e extends d<androidx.work.impl.a.b> {
    static final String b = f.a("NetworkStateTracker");
    private final ConnectivityManager c = ((ConnectivityManager) this.a.getSystemService("connectivity"));
    @RequiresApi(24)
    private b d;
    private a e;

    private class a extends BroadcastReceiver {
        a() {
        }

        /* JADX WARNING: Missing block: B:7:0x002f, code skipped:
            return;
     */
        public void onReceive(android.content.Context r3, android.content.Intent r4) {
            /*
            r2 = this;
            if (r4 == 0) goto L_0x002f;
        L_0x0002:
            r3 = r4.getAction();
            if (r3 != 0) goto L_0x0009;
        L_0x0008:
            goto L_0x002f;
        L_0x0009:
            r3 = r4.getAction();
            r4 = "android.net.conn.CONNECTIVITY_CHANGE";
            r3 = r3.equals(r4);
            if (r3 == 0) goto L_0x002e;
        L_0x0015:
            r3 = androidx.work.f.a();
            r4 = androidx.work.impl.a.b.e.b;
            r0 = "Network broadcast received";
            r1 = 0;
            r1 = new java.lang.Throwable[r1];
            r3.b(r4, r0, r1);
            r3 = androidx.work.impl.a.b.e.this;
            r4 = androidx.work.impl.a.b.e.this;
            r4 = r4.b();
            r3.a(r4);
        L_0x002e:
            return;
        L_0x002f:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.a.b.e$a.onReceive(android.content.Context, android.content.Intent):void");
        }
    }

    @RequiresApi(24)
    private class b extends NetworkCallback {
        b() {
        }

        public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            f.a().b(e.b, String.format("Network capabilities changed: %s", new Object[]{networkCapabilities}), new Throwable[0]);
            e.this.a((Object) e.this.b());
        }

        public void onLost(Network network) {
            f.a().b(e.b, "Network connection lost", new Throwable[0]);
            e.this.a((Object) e.this.b());
        }
    }

    public e(Context context) {
        super(context);
        if (f()) {
            this.d = new b();
        } else {
            this.e = new a();
        }
    }

    /* renamed from: a */
    public androidx.work.impl.a.b c() {
        return b();
    }

    public void d() {
        if (f()) {
            f.a().b(b, "Registering network callback", new Throwable[0]);
            this.c.registerDefaultNetworkCallback(this.d);
            return;
        }
        f.a().b(b, "Registering broadcast receiver", new Throwable[0]);
        this.a.registerReceiver(this.e, new IntentFilter(d.a));
    }

    public void e() {
        if (f()) {
            try {
                f.a().b(b, "Unregistering network callback", new Throwable[0]);
                this.c.unregisterNetworkCallback(this.d);
                return;
            } catch (IllegalArgumentException e) {
                f.a().e(b, "Received exception while unregistering network callback", e);
                return;
            }
        }
        f.a().b(b, "Unregistering broadcast receiver", new Throwable[0]);
        this.a.unregisterReceiver(this.e);
    }

    private static boolean f() {
        return VERSION.SDK_INT >= 24;
    }

    /* Access modifiers changed, original: 0000 */
    public androidx.work.impl.a.b b() {
        NetworkInfo activeNetworkInfo = this.c.getActiveNetworkInfo();
        boolean z = false;
        boolean z2 = activeNetworkInfo != null && activeNetworkInfo.isConnected();
        boolean g = g();
        boolean isActiveNetworkMetered = ConnectivityManagerCompat.isActiveNetworkMetered(this.c);
        if (!(activeNetworkInfo == null || activeNetworkInfo.isRoaming())) {
            z = true;
        }
        return new androidx.work.impl.a.b(z2, g, isActiveNetworkMetered, z);
    }

    private boolean g() {
        boolean z = false;
        if (VERSION.SDK_INT < 23) {
            return false;
        }
        NetworkCapabilities networkCapabilities = this.c.getNetworkCapabilities(this.c.getActiveNetwork());
        if (networkCapabilities != null && networkCapabilities.hasCapability(16)) {
            z = true;
        }
        return z;
    }
}
