package com.comscore.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import com.comscore.analytics.Core;
import java.util.HashSet;

public class ConnectivityChangeReceiver extends BroadcastReceiver {
    protected boolean a = false;
    protected boolean b = false;
    protected long c = -1;
    protected HashSet<String> d = null;
    private Runnable e = null;
    private final Core f;

    public ConnectivityChangeReceiver(Core core) {
        this.f = core;
        this.d = new HashSet();
    }

    /* Access modifiers changed, original: protected */
    public void a() {
        if (this.f.isEnabled()) {
            CSLog.d((Object) this, "onConnectedWifi()");
            a(b(this.f.getAppContext()));
            if (!(this.f.getOfflineTransmissionMode() == TransmissionMode.NEVER || this.f.getOfflineTransmissionMode() == TransmissionMode.DISABLED || this.a)) {
                this.a = true;
                a(false);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(long j) {
        if (this.f.isEnabled()) {
            this.e = new a(this);
            this.f.getTaskExecutor().execute(this.e, j);
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(Context context) {
        if (this.f.isEnabled()) {
            CSLog.d((Object) this, "onConnectedMobile()");
            a(Constants.CONNECTIVITY_MOBILE_MARKER);
            if ((this.f.getOfflineTransmissionMode() == TransmissionMode.DEFAULT || (this.f.getOfflineTransmissionMode() == TransmissionMode.PIGGYBACK && Connectivity.isDataConnected(context))) && !this.a) {
                this.a = true;
                a(false);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(String str) {
        if (this.f.isEnabled() && Utils.isNotEmpty(str) && this.d != null && !this.d.contains(str)) {
            if (this.d.size() != 0) {
                d();
            }
            this.d.add(str);
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(boolean z) {
        if (!this.f.isEnabled()) {
            return;
        }
        if (this.b) {
            c();
            if (this.c < SystemClock.uptimeMillis() || this.c < 0 || !z) {
                this.c = SystemClock.uptimeMillis() + 30000;
            }
            a(this.c - SystemClock.uptimeMillis());
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("scheduleFlushTask(): Flushing in ");
            stringBuilder.append(this.c - SystemClock.uptimeMillis());
            CSLog.d((Object) this, stringBuilder.toString());
            return;
        }
        if (this.c < 0) {
            this.c = SystemClock.uptimeMillis() + 30000;
        }
    }

    /* Access modifiers changed, original: protected */
    public String b(Context context) {
        return Connectivity.getCurrentSSID(context);
    }

    /* Access modifiers changed, original: protected */
    public void b() {
        if (this.f.isEnabled()) {
            CSLog.d((Object) this, "onDisconnected()");
            c();
            this.a = false;
            this.c = -1;
        }
    }

    /* Access modifiers changed, original: protected|declared_synchronized */
    public synchronized void b(boolean z) {
        CSLog.d((Object) this, "flushing");
        this.f.flush(z);
        this.c = -1;
    }

    /* Access modifiers changed, original: protected */
    public void c() {
        if (this.e != null) {
            CSLog.d((Object) this, "cancelFlushTask()");
            this.f.getTaskExecutor().removeEnqueuedTask(this.e);
            this.e = null;
        }
    }

    /* Access modifiers changed, original: protected */
    public void d() {
        if (this.f.isEnabled()) {
            this.f.getKeepAlive().reset(3000);
        }
    }

    public synchronized void onReceive(Context context, Intent intent) {
        if (intent.getExtras() != null) {
            if (Connectivity.isConnectedWiFi(context)) {
                a();
            } else if (Connectivity.isConnectedMobile(context)) {
                a(context);
            } else {
                b();
            }
        }
    }

    /* JADX WARNING: Missing block: B:14:0x001e, code skipped:
            return;
     */
    public synchronized void start() {
        /*
        r6 = this;
        monitor-enter(r6);
        r0 = r6.f;	 Catch:{ all -> 0x001f }
        r0 = r0.isEnabled();	 Catch:{ all -> 0x001f }
        if (r0 != 0) goto L_0x000b;
    L_0x0009:
        monitor-exit(r6);
        return;
    L_0x000b:
        r0 = 1;
        r6.b = r0;	 Catch:{ all -> 0x001f }
        r1 = r6.a;	 Catch:{ all -> 0x001f }
        if (r1 == 0) goto L_0x001d;
    L_0x0012:
        r1 = r6.c;	 Catch:{ all -> 0x001f }
        r3 = 0;
        r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1));
        if (r5 <= 0) goto L_0x001d;
    L_0x001a:
        r6.a(r0);	 Catch:{ all -> 0x001f }
    L_0x001d:
        monitor-exit(r6);
        return;
    L_0x001f:
        r0 = move-exception;
        monitor-exit(r6);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.comscore.utils.ConnectivityChangeReceiver.start():void");
    }

    public synchronized void stop() {
        this.b = false;
        c();
    }
}
