package com.bumptech.glide.manager;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.bumptech.glide.manager.c.a;
import com.til.colombia.android.internal.d;

class e implements c {
    final a a;
    boolean b;
    private final Context c;
    private boolean d;
    private final BroadcastReceiver e = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            boolean z = e.this.b;
            e.this.b = e.this.a(context);
            if (z != e.this.b) {
                e.this.a.a(e.this.b);
            }
        }
    };

    public void onDestroy() {
    }

    public e(Context context, a aVar) {
        this.c = context.getApplicationContext();
        this.a = aVar;
    }

    private void a() {
        if (!this.d) {
            this.b = a(this.c);
            this.c.registerReceiver(this.e, new IntentFilter(d.a));
            this.d = true;
        }
    }

    private void b() {
        if (this.d) {
            this.c.unregisterReceiver(this.e);
            this.d = false;
        }
    }

    /* Access modifiers changed, original: 0000 */
    @SuppressLint({"MissingPermission"})
    public boolean a(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void onStart() {
        a();
    }

    public void onStop() {
        b();
    }
}
